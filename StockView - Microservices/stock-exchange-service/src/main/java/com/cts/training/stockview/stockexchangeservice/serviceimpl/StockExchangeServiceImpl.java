package com.cts.training.stockview.stockexchangeservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.training.stockview.stockexchangeservice.entity.StockExchangeEntity;
import com.cts.training.stockview.stockexchangeservice.model.StockExchange;
import com.cts.training.stockview.stockexchangeservice.repo.StockExchangeRepository;
import com.cts.training.stockview.stockexchangeservice.service.StockExchangeService;

@Service
public class StockExchangeServiceImpl implements StockExchangeService{
	
	@Autowired
	private StockExchangeRepository stockExchangeRepo;
	
	public List<StockExchange> getAllStockExchanges(){
		List<StockExchangeEntity> entities = stockExchangeRepo.findAll();
		List<StockExchange> exchanges = new ArrayList<StockExchange>();
		for(StockExchangeEntity entity : entities) {
			StockExchange exchange = new StockExchange();
			BeanUtils.copyProperties(entity, exchange);
			exchanges.add(exchange);
		}
		return exchanges;
	}

	public StockExchange getStockExchangeById(int id){
		Optional<StockExchangeEntity> stockExchangeEntity = stockExchangeRepo.findById(id);
		StockExchange stockExchange = new StockExchange();
		BeanUtils.copyProperties(stockExchangeEntity.orElse(new StockExchangeEntity()), stockExchange);
		return stockExchange;
	}

	public StockExchange addStockExchange(StockExchange stockExchange) {
		StockExchangeEntity stockExchangeEntity = new StockExchangeEntity();
		BeanUtils.copyProperties(stockExchange, stockExchangeEntity);
		BeanUtils.copyProperties(stockExchangeRepo.save(stockExchangeEntity),stockExchange);
		return stockExchange;
	}
	
	public StockExchange updateStockExchange(StockExchange stockExchange) {
		StockExchangeEntity stockExchangeEntity = new StockExchangeEntity();
		BeanUtils.copyProperties(stockExchange, stockExchangeEntity);
		BeanUtils.copyProperties(stockExchangeRepo.save(stockExchangeEntity),stockExchange);
		return stockExchange;
	}

	public void deleteStockExchange(int id) {
		stockExchangeRepo.deleteById(id);
	}
	
	public List<String> getAllStockExchangesNames() {
		return stockExchangeRepo.getAllStockExchangesNames();
	}

}
