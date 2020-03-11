package com.cts.training.stockview.stockexchangeservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getAllStockExchanges(){
		List<StockExchange> stockExchanges =  stockExchangeService.getAllStockExchanges();
		if(stockExchanges.size()>0) {
			return new ResponseEntity<List<StockExchange>>(stockExchanges,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("No stock exchanges",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/stockExchanges/{id}", produces = "application/json")
	public ResponseEntity<?> getStockExchangeById(@PathVariable("id") int id){
		try {
			StockExchange stockExchange = stockExchangeService.getStockExchangeById(id);
			return new ResponseEntity<StockExchange>(stockExchange,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("No stock exchange",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/stockExchanges/getAllStockExchangesNames", produces = "application/json")
	public ResponseEntity<?> getAllStockExchangesNames() {
		List<String> names = stockExchangeService.getAllStockExchangesNames();
		return new ResponseEntity<List<String>>(names, HttpStatus.OK);
	}
	
	@PostMapping(value = "/stockExchanges/admin",consumes = "application/json")
	public ResponseEntity<?> addStockExchange(@RequestBody StockExchange stockExchange) {
		return new ResponseEntity<StockExchange>(stockExchangeService.addStockExchange(stockExchange),HttpStatus.OK);
	}
	
	@PutMapping(value = "/stockExchanges/admin",consumes = "application/json")
	public ResponseEntity<?> updateStockExchange(@RequestBody StockExchange stockExchange) {
		return new ResponseEntity<StockExchange>(stockExchangeService.updateStockExchange(stockExchange),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/stockExchanges/admin/{id}")
	public ResponseEntity<?> deleteStockExchange(@PathVariable int id) {
		stockExchangeService.deleteStockExchange(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
}
