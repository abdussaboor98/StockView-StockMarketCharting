package com.cts.training.stockview.stockpriceservice.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.training.stockview.stockpriceservice.model.ImportSummary;
import com.cts.training.stockview.stockpriceservice.model.StockPriceOnPeriod;
import com.cts.training.stockview.stockpriceservice.service.StockPriceService;

@CrossOrigin("*")
@RestController
public class StockPriceRestController {
	@Autowired
	private StockPriceService stockPriceService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	
	@GetMapping(value = "/stockPrices/companyStockPriceBetween/{companyCode}/{stockExchange}/{startDate}/{endDate}/{periodicity}", produces = "application/json")
	public ResponseEntity<?> getCompanyStockPricePerDayBetween(@PathVariable String companyCode,@PathVariable String stockExchange,@PathVariable String startDate,@PathVariable  String endDate,@PathVariable String periodicity) {
		
		return new ResponseEntity<List<StockPriceOnPeriod>>(stockPriceService.getCompanyStockPriceBetween(companyCode,stockExchange,LocalDate.parse(startDate),LocalDate.parse(endDate),periodicity),HttpStatus.OK);
	}
	
	@GetMapping(value = "/stockPrices/getMaxDate/{companyCode}/{stockExchange}", produces = "application/json")
	public ResponseEntity<?> getMaxDate(@PathVariable String companyCode,@PathVariable String stockExchange) {
		try {
			logger.info("Request for MaxDate --> {}:{}",companyCode,stockExchange);
			return new ResponseEntity<LocalDate>(stockPriceService.getMaxDate(companyCode,stockExchange), HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("Error for MaxDate --> {}:{}\nError --> {}",companyCode,stockExchange,e);
			return new ResponseEntity<String>("Some error occurred"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/stockPrices/getMinDate/{companyCode}/{stockExchange}", produces = "application/json")
	public ResponseEntity<?> getMinDate(@PathVariable String companyCode,@PathVariable String stockExchange) {
		try {
			logger.info("Request for MinDate --> {}:{}",companyCode,stockExchange);
			return new ResponseEntity<LocalDate>(stockPriceService.getMinDate(companyCode,stockExchange), HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("Error for MinDate --> {}:{}\nError --> {}",companyCode,stockExchange,e);
			return new ResponseEntity<String>("Some error occurred"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/stockPrices/admin/uploadStocksSheet",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadStocksSheet(@RequestParam("stocksSheet") MultipartFile file) {
		logger.info("File recieved: {}",file.getOriginalFilename());
		if (file.getOriginalFilename().endsWith(".xls") || file.getOriginalFilename().endsWith(".xlsx")) {
			try {
				return new ResponseEntity<ImportSummary>(stockPriceService.addStockPricesFromExcelSheet(file),HttpStatus.OK);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Error reading file.",HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<String>("Wrong file extension. The file should be .xls or an .xlsx file.",HttpStatus.BAD_REQUEST);
		}
	}
	
//	@GetMapping(value = "/stockPrices",produces = "application/json")
//	public List<StockPriceEntity> getAllStockPrices(){
//		return stockPriceService.getAllStockPrices();
//	}
//	
//	@GetMapping(value = "/stockPrices/{id}", produces = "application/json")
//	public StockPriceEntity getStockPriceById(@PathVariable("id") int id){
//		return stockPriceService.getStockPriceById(id);
//	}
//	
//	@PostMapping(value = "/stockPrices",consumes = "application/json")
//	public StockPriceEntity addStockPrice(@RequestBody StockPriceEntity stockExchange) {
//		return stockPriceService.addStockPrice(stockExchange);
//	}

//	@PutMapping(value = "/stockPrices",consumes = "application/json")
//	public StockPriceEntity updateStockPrice(@RequestBody StockPriceEntity stockExchange) {
//		return stockPriceService.updateStockPrice(stockExchange);
//	}
//	
//	@DeleteMapping(value = "/stockPrices/{id}")
//	public void deleteStockPrice(@PathVariable int id) {
//		stockPriceService.deleteStockPrice(id);
//	}
}
