package com.cts.training.stockview.dao;

import java.util.List;

import com.cts.training.stockview.model.StockPrice;

public interface StockPriceDAO {

	public boolean addStockPrice(StockPrice stockPrice);

	public boolean updateStockPrice(StockPrice stockPrice);

	public boolean deleteStockPrice(StockPrice stockPrice);

	public StockPrice getStockPriceById(int id);

	public List<StockPrice> getAllStockPrices();
	
}
