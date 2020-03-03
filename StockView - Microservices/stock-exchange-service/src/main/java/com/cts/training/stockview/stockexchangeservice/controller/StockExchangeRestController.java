package com.cts.training.stockview.stockexchangeservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.stockview.stockexchangeservice.entity.StockExchangeEntity;
import com.cts.training.stockview.stockexchangeservice.model.StockExchange;
import com.cts.training.stockview.stockexchangeservice.repo.StockExchangeRepository;
import com.cts.training.stockview.stockexchangeservice.service.StockExchangeService;


@CrossOrigin(origins = "*")
@RestController
public class StockExchangeRestController {

	@Autowired
	private StockExchangeService stockExchangeService;
	
	@GetMapping(value = "/stockExchanges",produces = "application/json")
	public List<StockExchange> getAllStockExchanges(){
		return stockExchangeService.getAllStockExchanges();
	}
	
	@GetMapping(value = "/stockExchanges/{id}", produces = "application/json")
	public StockExchange getStockExchangeById(@PathVariable("id") int id){
		return stockExchangeService.getStockExchangeById(id);
	}
	
	@PostMapping(value = "/stockExchanges",consumes = "application/json")
	public StockExchange addStockExchange(@RequestBody StockExchange stockExchange) {
		return stockExchangeService.addStockExchange(stockExchange);
	}
	
	@PutMapping(value = "/stockExchanges",consumes = "application/json")
	public StockExchange updateStockExchange(@RequestBody StockExchange stockExchange) {
		return stockExchangeService.updateStockExchange(stockExchange);
	}
	
	@DeleteMapping(value = "/stockExchanges/{id}")
	public void deleteStockExchange(@PathVariable int id) {
		stockExchangeService.deleteStockExchange(id);
	}
	
}
