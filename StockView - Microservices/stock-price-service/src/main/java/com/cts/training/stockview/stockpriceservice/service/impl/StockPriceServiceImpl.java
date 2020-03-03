package com.cts.training.stockview.stockpriceservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;
import com.cts.training.stockview.stockpriceservice.repo.StockPriceRepository;
import com.cts.training.stockview.stockpriceservice.service.StockPriceService;

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
}
