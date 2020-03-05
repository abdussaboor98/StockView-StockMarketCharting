package com.cts.training.stockview.stockpriceservice.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
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
		if(file.getOriginalFilename().endsWith(".xls")) {
			HSSFWorkbook workbook = new HSSFWorkbook(in); 
			HSSFSheet stockPricesSheet = workbook.getSheet("Sheet1");
			HSSFRow row = stockPricesSheet.getRow(1);
			System.out.println(row.getCell(0).getStringCellValue());
		}
		else if(file.getOriginalFilename().endsWith(".xlsx")) {
			XSSFWorkbook workbook = new XSSFWorkbook(in); 
			XSSFSheet stockPricesSheet = workbook.getSheet("Sheet1");
			System.out.println(stockPricesSheet.getLastRowNum());
			int currentRowNum = 0;
			XSSFRow row = stockPricesSheet.getRow(currentRowNum);
			while(row.getCell(0)!= null) {
				System.out.print(currentRowNum+" : ");
				int cellNum = 0;
				while(row.getCell(cellNum)!=null) {
					System.out.print(row.getCell(cellNum).getCellTypeEnum());
					if(row.getCell(cellNum).getCellTypeEnum() == CellType.STRING) {
						System.out.print(row.getCell(cellNum++).getStringCellValue()+"\t");
					}
					else if(row.getCell(cellNum).getCellTypeEnum() == CellType.NUMERIC) {
						System.out.print(row.getCell(cellNum++).getNumericCellValue()+"\t");
					}
				}
				row = stockPricesSheet.getRow(++currentRowNum);
				System.out.println();
			}
		}
		
	}
}
