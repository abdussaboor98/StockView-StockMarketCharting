package com.cts.training.stockview.stockpriceservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;
import com.cts.training.stockview.stockpriceservice.model.ImportSummary;
import com.cts.training.stockview.stockpriceservice.service.StockPriceService;

@CrossOrigin("*")
@RestController
public class StockPriceRestController {
	@Autowired
	private StockPriceService stockExchangeService;
	
	@GetMapping(value = "/stockPrices",produces = "application/json")
	public List<StockPriceEntity> getAllStockPrices(){
		return stockExchangeService.getAllStockPrices();
	}
	
	@GetMapping(value = "/stockExchanges/{id}", produces = "application/json")
	public StockPriceEntity getStockPriceById(@PathVariable("id") int id){
		return stockExchangeService.getStockPriceById(id);
	}
	
	@PostMapping(value = "/stockExchanges",consumes = "application/json")
	public StockPriceEntity addStockPrice(@RequestBody StockPriceEntity stockExchange) {
		return stockExchangeService.addStockPrice(stockExchange);
	}
	
	@PostMapping(value = "stockPrices/uploadStocksSheet",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadStocksSheet(@RequestParam("stocksSheet") MultipartFile file) {
		System.out.println("File recieved: " + file.getOriginalFilename());
		if (file.getOriginalFilename().endsWith(".xls") || file.getOriginalFilename().endsWith(".xlsx")) {
			try {
				ImportSummary res = stockExchangeService.addStockPricesFromExcelSheet(file);
				return new ResponseEntity<ImportSummary>(new ImportSummary(),HttpStatus.OK);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Error reading file.",HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<String>("Wrong file extension. The file should be .xls or an .xlsx file.",HttpStatus.BAD_REQUEST);
		}
		
	}

	@PutMapping(value = "/stockExchanges",consumes = "application/json")
	public StockPriceEntity updateStockPrice(@RequestBody StockPriceEntity stockExchange) {
		return stockExchangeService.updateStockPrice(stockExchange);
	}
	
	@DeleteMapping(value = "/stockExchanges/{id}")
	public void deleteStockPrice(@PathVariable int id) {
		stockExchangeService.deleteStockPrice(id);
	}
}
