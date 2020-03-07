package com.cts.training.stockview.stockpriceservice.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;
import com.cts.training.stockview.stockpriceservice.feignproxy.ZuulGatewayProxy;
import com.cts.training.stockview.stockpriceservice.model.ImportSummary;
import com.cts.training.stockview.stockpriceservice.repo.StockPriceRepository;
import com.cts.training.stockview.stockpriceservice.service.StockPriceService;

@Service
public class StockPriceServiceImpl implements StockPriceService {
	@Autowired
	private StockPriceRepository stockPriceRepo;

	@Autowired
	private ZuulGatewayProxy serviceProxy;

//	@Autowired
//	private CompanyServiceProxy companyServiceProxy;

	@Override
	public List<StockPriceEntity> getAllStockPrices() {
		return stockPriceRepo.findAll();
	}

	@Override
	public StockPriceEntity getStockPriceById(int id) throws NoSuchElementException {
		return stockPriceRepo.findById(id).get();
	}

	@Override
	public StockPriceEntity addStockPrice(StockPriceEntity stockPriceEntity) {
		return stockPriceRepo.save(stockPriceEntity);
	}

	@Override
	public StockPriceEntity updateStockPrice(StockPriceEntity stockPriceEntity) {
		return stockPriceRepo.save(stockPriceEntity);
	}

	@Override
	public void deleteStockPrice(int id) {
		stockPriceRepo.deleteById(id);
	}
	
	@Override
	public ImportSummary addStockPricesFromExcelSheet(MultipartFile file) throws IOException, Exception {
		InputStream in = file.getInputStream();
		int currentRowNum = 1;
		int currentCellNum = 0;
		LocalDate startDate = LocalDate.MAX;
		LocalDate endDate = LocalDate.MIN;
		List<String> duplicates = new ArrayList<String>();
		List<StockPriceEntity> stockPricesEntities = new ArrayList<StockPriceEntity>();
		Set<String> companyCodes = new HashSet<String>();
		Set<String> stockExchanges = new HashSet<String>();
		if (file.getOriginalFilename().endsWith(".xls")) {
			try (HSSFWorkbook workbook = new HSSFWorkbook(in)) {
				HSSFSheet stockPricesSheet = workbook.getSheetAt(0);
				HSSFRow row = stockPricesSheet.getRow(currentRowNum);
				while (row != null && row.getCell(0) != null) {
					String companyCode = row.getCell(currentCellNum++).getStringCellValue().trim();
					String stockExchangeName = row.getCell(currentCellNum++).getStringCellValue().trim();
					double stockPrice = (double) row.getCell(currentCellNum++).getNumericCellValue();
					companyCodes.add(companyCode);
					stockExchanges.add(stockExchangeName);
					LocalDate date = row.getCell(currentCellNum++).getDateCellValue()
							.toInstant()
							.atZone(ZoneId.of("+05:30"))
							.toLocalDate();
					startDate = date.isBefore(startDate) ? date : startDate;
					endDate = date.isAfter(endDate) ? date : endDate;
					LocalTime time = LocalTime.parse(row.getCell(currentCellNum++).getStringCellValue().trim());

					if (!stockPriceRepo.getIfAlreadyExists(companyCode, stockExchangeName, date, time).isPresent()) {
						StockPriceEntity stockPriceEntity = new StockPriceEntity(companyCode, stockExchangeName,
								stockPrice, date, time);
						stockPricesEntities.add(stockPriceEntity);
					} else {
						duplicates.add("The record at row " + (currentRowNum + 1) + " is duplicate.");
					}
					row = stockPricesSheet.getRow(++currentRowNum);
					currentCellNum = 0;
				}
			} catch (NullPointerException e) {
				throw new Exception("The file has some missing value at " + (currentRowNum + 1) + ":" + (currentCellNum)
						+ " (row:column). ");
			} catch (IllegalStateException e) {
				throw new Exception("The file has some wrong value at " + (currentRowNum + 1) + ":" + (currentCellNum)
						+ " (row:column). ");
			} catch (DateTimeParseException e) {
				throw new Exception("The file has wrong date/time format value at " + (currentRowNum + 1) + ":"
						+ (currentCellNum) + " (row:column). ");
			}
		} else if (file.getOriginalFilename().endsWith(".xlsx")) {
			try (XSSFWorkbook workbook = new XSSFWorkbook(in)) {
				XSSFSheet stockPricesSheet = workbook.getSheetAt(0);
				System.out.println(stockPricesSheet.getLastRowNum());
				XSSFRow row = stockPricesSheet.getRow(currentRowNum);
				while (row != null && row.getCell(0) != null) {
					String companyCode = row.getCell(currentCellNum++).getStringCellValue().trim();
					String stockExchangeName = row.getCell(currentCellNum++).getStringCellValue().trim();
					long stockPrice = (long) row.getCell(currentCellNum++).getNumericCellValue();
					if (!serviceProxy.getAllStockExchangesNames().contains(stockExchangeName)) {
						throw new Exception("The file has unkown stock exchange value at " + (currentRowNum + 1) + ":"
								+ (currentCellNum - 1) + " (row:column). ");
					}
					if (!serviceProxy.getCompanyByStockCodeAndExchangeName(companyCode, stockExchangeName)) {
						System.out.println(serviceProxy.getCompanyByStockCodeAndExchangeName(companyCode, stockExchangeName));
						throw new Exception("The file has unkown company code value at " + (currentRowNum + 1) + ":"
								+ (currentCellNum - 2) + " (row:column). ");
					}
					companyCodes.add(companyCode);
					stockExchanges.add(stockExchangeName);
					LocalDate date = row.getCell(currentCellNum++).getDateCellValue().toInstant()
							.atZone(ZoneId.of("+05:30")).toLocalDate();
					startDate = date.isBefore(startDate) ? date : startDate;
					endDate = date.isAfter(endDate) ? date : endDate;
					LocalTime time = LocalTime.parse(row.getCell(currentCellNum++).getStringCellValue().trim());

					if (!stockPriceRepo.getIfAlreadyExists(companyCode, stockExchangeName, date, time).isPresent()) {
						StockPriceEntity stockPriceEntity = new StockPriceEntity(companyCode, stockExchangeName,
								stockPrice, date, time);
						stockPricesEntities.add(stockPriceEntity);
					} else {
						duplicates.add("The record at row " + (currentRowNum + 1) + " is duplicate.");
					}
					row = stockPricesSheet.getRow(++currentRowNum);
					currentCellNum = 0;
				}
			} catch (NullPointerException e) {
				throw new Exception("The file has some missing value at " + (currentRowNum + 1) + ":" + (currentCellNum)
						+ " (row:column). ");
			} catch (IllegalStateException e) {
				throw new Exception("The file has some wrong value at " + (currentRowNum + 1) + ":" + (currentCellNum)
						+ " (row:column). ");
			} catch (DateTimeParseException e) {
				throw new Exception("The file has wrong date/time format value at " + (currentRowNum + 1) + ":"
						+ (currentCellNum) + " (row:column). ");
			}
		}
		stockPriceRepo.saveAll(stockPricesEntities);
		in.close();
		return new ImportSummary(stockPricesEntities.size(), startDate, endDate, companyCodes, stockExchanges,
				duplicates);
	}

//	@Override
//	public ImportSummary addStockPricesFromExcelSheet(MultipartFile file) throws IOException, Exception {
//		InputStream in = file.getInputStream();
//		int currentRowNum = 1;
//		int currentCellNum = 0;
//		LocalDate startDate = LocalDate.MAX;
//		LocalDate endDate = LocalDate.MIN;
//		List<String> duplicates = new ArrayList<String>();
//		List<StockPriceEntity> stockPricesEntities = new ArrayList<StockPriceEntity>();
//		Set<String> companyCodes = new HashSet<String>();
//		Set<String> stockExchanges = new HashSet<String>();
//		if (file.getOriginalFilename().endsWith(".xls")) {
//			try (HSSFWorkbook workbook = new HSSFWorkbook(in)) {
//				HSSFSheet stockPricesSheet = workbook.getSheetAt(0);
//				HSSFRow row = stockPricesSheet.getRow(currentRowNum);
//				while (row != null && row.getCell(0) != null) {
//					String companyCode = row.getCell(currentCellNum++).getStringCellValue().trim();
//					String stockExchangeName = row.getCell(currentCellNum++).getStringCellValue().trim();
//					long stockPrice = (long) row.getCell(currentCellNum++).getNumericCellValue();
//					if (!serviceProxy.getAllStockExchangesNames().contains(stockExchangeName)) {
//						throw new Exception("The file has unkown stock exchange value at " + (currentRowNum + 1) + ":"
//								+ (currentCellNum - 1) + " (row:column). ");
//					}
//					if (!serviceProxy.getCompanyByStockCodeAndExchangeName(companyCode, stockExchangeName)) {
//						throw new Exception("The file has unkown company code value at " + (currentRowNum + 1) + ":"
//								+ (currentCellNum - 2) + " (row:column). ");
//					}
//					companyCodes.add(companyCode);
//					stockExchanges.add(stockExchangeName);
//					LocalDate date = row.getCell(currentCellNum++).getDateCellValue().toInstant()
//							.atZone(ZoneId.of("+05:30")).toLocalDate();
//					startDate = date.isBefore(startDate) ? date : startDate;
//					endDate = date.isAfter(endDate) ? date : endDate;
//					LocalTime time = LocalTime.parse(row.getCell(currentCellNum++).getStringCellValue().trim());
//
//					if (!stockPriceRepo.getIfAlreadyExists(companyCode, stockExchangeName, date, time).isPresent()) {
//						StockPriceEntity stockPriceEntity = new StockPriceEntity(companyCode, stockExchangeName,
//								stockPrice, date, time);
//						stockPricesEntities.add(stockPriceEntity);
//					} else {
//						duplicates.add("The record at row " + (currentRowNum + 1) + " is duplicate.");
//					}
//					row = stockPricesSheet.getRow(++currentRowNum);
//					currentCellNum = 0;
//				}
//			} catch (NullPointerException e) {
//				throw new Exception("The file has some missing value at " + (currentRowNum + 1) + ":" + (currentCellNum)
//						+ " (row:column). ");
//			} catch (IllegalStateException e) {
//				throw new Exception("The file has some wrong value at " + (currentRowNum + 1) + ":" + (currentCellNum)
//						+ " (row:column). ");
//			} catch (DateTimeParseException e) {
//				throw new Exception("The file has wrong date/time format value at " + (currentRowNum + 1) + ":"
//						+ (currentCellNum) + " (row:column). ");
//			}
//		} else if (file.getOriginalFilename().endsWith(".xlsx")) {
//			try (XSSFWorkbook workbook = new XSSFWorkbook(in)) {
//				XSSFSheet stockPricesSheet = workbook.getSheetAt(0);
//				System.out.println(stockPricesSheet.getLastRowNum());
//				XSSFRow row = stockPricesSheet.getRow(currentRowNum);
//				while (row != null && row.getCell(0) != null) {
//					String companyCode = row.getCell(currentCellNum++).getStringCellValue().trim();
//					companyCode = companyCode.trim();
//					String stockExchangeName = row.getCell(currentCellNum++).getStringCellValue().trim();
//					long stockPrice = (long) row.getCell(currentCellNum++).getNumericCellValue();
//					if (!serviceProxy.getAllStockExchangesNames().contains(stockExchangeName)) {
//						throw new Exception("The file has unkown stock exchange value at " + (currentRowNum + 1) + ":"
//								+ (currentCellNum - 1) + " (row:column). ");
//					}
//					if (!serviceProxy.getCompanyByStockCodeAndExchangeName(companyCode, stockExchangeName)) {
//						System.out.println(serviceProxy.getCompanyByStockCodeAndExchangeName(companyCode, stockExchangeName));
//						throw new Exception("The file has unkown company code value at " + (currentRowNum + 1) + ":"
//								+ (currentCellNum - 2) + " (row:column). ");
//					}
//					companyCodes.add(companyCode);
//					stockExchanges.add(stockExchangeName);
//					LocalDate date = row.getCell(currentCellNum++).getDateCellValue().toInstant()
//							.atZone(ZoneId.of("+05:30")).toLocalDate();
//					startDate = date.isBefore(startDate) ? date : startDate;
//					endDate = date.isAfter(endDate) ? date : endDate;
//					LocalTime time = LocalTime.parse(row.getCell(currentCellNum++).getStringCellValue().trim());
//
//					if (!stockPriceRepo.getIfAlreadyExists(companyCode, stockExchangeName, date, time).isPresent()) {
//						StockPriceEntity stockPriceEntity = new StockPriceEntity(companyCode, stockExchangeName,
//								stockPrice, date, time);
//						stockPricesEntities.add(stockPriceEntity);
//					} else {
//						duplicates.add("The record at row " + (currentRowNum + 1) + " is duplicate.");
//					}
//					row = stockPricesSheet.getRow(++currentRowNum);
//					currentCellNum = 0;
//				}
//			} catch (NullPointerException e) {
//				throw new Exception("The file has some missing value at " + (currentRowNum + 1) + ":" + (currentCellNum)
//						+ " (row:column). ");
//			} catch (IllegalStateException e) {
//				throw new Exception("The file has some wrong value at " + (currentRowNum + 1) + ":" + (currentCellNum)
//						+ " (row:column). ");
//			} catch (DateTimeParseException e) {
//				throw new Exception("The file has wrong date/time format value at " + (currentRowNum + 1) + ":"
//						+ (currentCellNum) + " (row:column). ");
//			}
//		}
//		stockPriceRepo.saveAll(stockPricesEntities);
//		return new ImportSummary(stockPricesEntities.size(), startDate, endDate, companyCodes, stockExchanges,
//				duplicates);
//	}
}
