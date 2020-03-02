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
import com.cts.training.stockview.stockexchangeservice.repo.StockExchangeRepository;


@CrossOrigin(origins = "*")
@RestController
public class StockExchangeRestController {

	@Autowired
	private StockExchangeRepository stockExchangeRepo;
	
	@GetMapping(value = "/stockExchanges",produces = "application/json")
	public List<StockExchangeEntity> getAllStockExchanges(){
		return stockExchangeRepo.findAll();
	}
	
	@GetMapping(value = "/stockExchanges/{id}", produces = "application/json")
	public StockExchangeEntity getStockExchangeById(@PathVariable("id") int id){
		Optional<StockExchangeEntity> stockExchanges = stockExchangeRepo.findById(id);
		return stockExchanges.get();
	}
	
	@PostMapping(value = "/stockExchanges",consumes = "application/json")
	public StockExchangeEntity addStockExchange(@RequestBody StockExchangeEntity stockExchange) {
		return stockExchangeRepo.save(stockExchange);
	}
	
	@PutMapping(value = "/stockExchanges",consumes = "application/json")
	public StockExchangeEntity updateStockExchange(@RequestBody StockExchangeEntity stockExchange) {
		return stockExchangeRepo.save(stockExchange);
	}
	
	@DeleteMapping(value = "/stockExchanges/{id}")
	public void deleteStockExchange(@PathVariable int id) {
		stockExchangeRepo.deleteById(id);
	}
	
}
