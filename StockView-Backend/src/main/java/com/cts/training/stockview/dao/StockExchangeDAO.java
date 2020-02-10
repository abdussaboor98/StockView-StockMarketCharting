package com.cts.training.stockview.dao;

import java.util.List;

import com.cts.training.stockview.model.StockExchangeEntity;

public interface StockExchangeDAO {

	public boolean addStockExchange(StockExchangeEntity stockExchange);

	public boolean updateStockExchange(StockExchangeEntity stockExchange);

	public boolean deleteStockExchange(StockExchangeEntity stockExchange);

	public StockExchangeEntity getStockExchangeById(int id);

	public List<StockExchangeEntity> getAllStockExchanges();
}
