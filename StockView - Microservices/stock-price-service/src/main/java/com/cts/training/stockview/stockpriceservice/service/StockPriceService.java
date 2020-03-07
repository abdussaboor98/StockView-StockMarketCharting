package com.cts.training.stockview.stockpriceservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.multipart.MultipartFile;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;
import com.cts.training.stockview.stockpriceservice.model.ImportSummary;;

public interface StockPriceService {

	public List<StockPriceEntity> getAllStockPrices();

	public StockPriceEntity getStockPriceById(int id) throws NoSuchElementException;

	public StockPriceEntity addStockPrice(StockPriceEntity stockPriceEntity);
	
	public ImportSummary addStockPricesFromExcelSheet(MultipartFile file) throws Exception; 

	public StockPriceEntity updateStockPrice(StockPriceEntity stockPriceEntity);

	public void deleteStockPrice(int id);
}
