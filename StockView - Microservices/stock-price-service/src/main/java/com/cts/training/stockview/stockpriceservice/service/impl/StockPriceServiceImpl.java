package com.cts.training.stockview.stockpriceservice.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
import org.springframework.web.multipart.MultipartFile;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;
import com.cts.training.stockview.stockpriceservice.repo.StockPriceRepository;
import com.cts.training.stockview.stockpriceservice.service.StockPriceService;

@Service
public class StockPriceServiceImpl implements StockPriceService {
	@Autowired
	private StockPriceRepository stockPriceRepo;

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
	public void addStockPricesFromExcelSheet(MultipartFile file) throws IOException {
		InputStream in = file.getInputStream();
		int currentRowNum = 1;
		int currentCellNum = 0;
		List<StockPriceEntity> stockPricesEntities = new ArrayList<StockPriceEntity>();
		try {
			// if (file.getOriginalFilename().endsWith(".xls")) {
			// HSSFWorkbook workbook = new HSSFWorkbook(in);
			// HSSFSheet stockPricesSheet = workbook.getSheetAt(0);
			// HSSFRow row = stockPricesSheet.getRow(1);
			// System.out.println(row.getCell(0).getStringCellValue());
			// } else if (file.getOriginalFilename().endsWith(".xlsx")) {
			XSSFWorkbook workbook = new XSSFWorkbook(in);
			XSSFSheet stockPricesSheet = workbook.getSheetAt(0);
			System.out.println(stockPricesSheet.getLastRowNum());
			XSSFRow row = stockPricesSheet.getRow(currentRowNum);
			while (row != null && row.getCell(0) != null) {
				System.out.print(currentRowNum);
				String companyCode = row.getCell(currentCellNum++).getStringCellValue().trim();
				String stockExchangeName = row.getCell(currentCellNum++).getStringCellValue().trim();
				long stockPrice = (long) row.getCell(currentCellNum++).getNumericCellValue();
				LocalDate date = row.getCell(currentCellNum++).getDateCellValue().toInstant()
						.atZone(ZoneId.of("+05:30")).toLocalDate();
				LocalTime time = LocalTime.parse(row.getCell(currentCellNum++).getStringCellValue().trim());
				if (!stockPriceRepo.getIfAlreadyExists(companyCode, date, time).isPresent()) {
					StockPriceEntity stockPriceEntity = new StockPriceEntity(companyCode, stockExchangeName, stockPrice,
							date, time);
					stockPricesEntities.add(stockPriceEntity);
				} else {
					System.out.println("The record at row "+(currentRowNum+1)+" is duplicate.");
				}
				row = stockPricesSheet.getRow(++currentRowNum);
				currentCellNum = 0;
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(currentRowNum + ":" + currentCellNum);
		}
		stockPriceRepo.saveAll(stockPricesEntities);

	}
}
