package com.cts.training.stockview.stockpriceservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.multipart.MultipartFile;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;
import com.cts.training.stockview.stockpriceservice.model.CompanyStockPriceRequest;
import com.cts.training.stockview.stockpriceservice.model.ImportSummary;
import com.cts.training.stockview.stockpriceservice.model.StockPricePerDay;;

public interface StockPriceService {

	public List<StockPriceEntity> getAllStockPrices();

	public StockPriceEntity getStockPriceById(int id) throws NoSuchElementException;

	public StockPriceEntity addStockPrice(StockPriceEntity stockPriceEntity);
	
	public ImportSummary addStockPricesFromExcelSheet(MultipartFile file) throws Exception; 

//	public List<StockPricePerDay> getCompanyStockPricePerDayBetween(CompanyStockPriceRequest stockPriceRequest);
	
	public List<StockPricePerDay> getCompanyStockPricePerDayBetween(String companyCode,String stockExchange,LocalDate startDate, LocalDate endDate);
	
	public StockPriceEntity updateStockPrice(StockPriceEntity stockPriceEntity);

	public void deleteStockPrice(int id);

	public LocalDate getMaxDate(String companyCode,String stockExchange);
	
	public LocalDate getMinDate(String companyCode,String stockExchange);
}
