package com.cts.training.stockview.stockexchangeservice.service;

import java.util.List;
import com.cts.training.stockview.stockexchangeservice.model.StockExchange;

public interface StockExchangeService {

	public List<StockExchange> getAllStockExchanges();

	public StockExchange getStockExchangeById(int id);

	public StockExchange addStockExchange(StockExchange stockExchange);
	
	public StockExchange updateStockExchange(StockExchange stockExchange);

	public void deleteStockExchange(int id);
	
	public List<String> getAllStockExchangesNames();
}
