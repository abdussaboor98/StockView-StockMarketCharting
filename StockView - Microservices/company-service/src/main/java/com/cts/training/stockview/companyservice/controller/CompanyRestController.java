package com.cts.training.stockview.companyservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.cts.training.stockview.companyservice.entity.CompanyEntity;
import com.cts.training.stockview.companyservice.feignproxy.IPOServiceProxy;
import com.cts.training.stockview.companyservice.model.IPO;
import com.cts.training.stockview.companyservice.model.StockPriceRequest;
import com.cts.training.stockview.companyservice.service.CompanyService;

@CrossOrigin(origins = "*")
@RestController
public class CompanyRestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private IPOServiceProxy ipoServiceProxy;

	@GetMapping(value = "/companies", produces = "application/json")
	public ResponseEntity<?> getAllCompanys() {
		List<CompanyEntity> companies = companyService.getAllCompanys();
		if (companies.size() > 0) {
			return new ResponseEntity<List<CompanyEntity>>(companies, HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("No companies found", HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/companies/{id}", produces = "application/json")
	public ResponseEntity<?> getCompanyById(@PathVariable("id") int id) {
		CompanyEntity company = companyService.getCompanyById(id);
		return new ResponseEntity<CompanyEntity>(company,HttpStatus.OK);
	}
	
	@GetMapping(value = "/companies/getCompanyByStockCodeAndExchangeName/{stockCode}/{stockExchangeName}", produces = "application/json")
	public ResponseEntity<?> getCompanyByStockCodeAndExchangeName(@PathVariable("stockCode") String stockCode, @PathVariable("stockExchangeName") String stockExchangeName) {
		return new ResponseEntity<Boolean>(companyService.getCompanyByStockCodeAndExchangeName(stockCode, stockExchangeName).isPresent(),HttpStatus.OK);
	}

	@PostMapping(value = "/companies", consumes = "application/json")
	public CompanyEntity addCompany(@RequestBody CompanyEntity company) {
		return companyService.addCompany(company);
	}

	@PutMapping(value = "/companies", consumes = "application/json")
	public CompanyEntity updateCompany(@RequestBody CompanyEntity company) {
		return companyService.updateCompany(company);
	}

	@DeleteMapping(value = "/companies/{id}")
	public void deleteCompany(@PathVariable int id) {
		companyService.deleteCompany(id);
	}
	
	@GetMapping(value = "/companies/getCompanyStockPrice")
	public ResponseEntity<?> getCompanyStockPrice(@RequestBody StockPriceRequest request) {
		return new ResponseEntity<StockPriceRequest>(request,HttpStatus.OK);
	}
	
	@GetMapping("/companies/getipos")
	public ResponseEntity<?> getIpos(){
		ResponseEntity<?> response = ipoServiceProxy.getAllIPOs();
		logger.info("All IPOs accessed --> {}",response);
		return response;
	}
	
	@GetMapping(value = "/companies/getCompaniesByStockExchange/{stockExchange}")
	public ResponseEntity<?> getCompaniesByStockExchange(@PathVariable String stockExchange) {
		List<CompanyEntity> companies = companyService.getCompaniesByStockExchange(stockExchange);
		if(companies.size() > 0) {
			return new ResponseEntity<List<CompanyEntity>>(companies,HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No companies found", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/companies/getCompaniesByPattern/{pattern}")
	public ResponseEntity<?> getCompaniesByPattern(@PathVariable String pattern) {
		List<CompanyEntity> companies = companyService.getCompaniesByPattern(pattern);
		return new ResponseEntity<List<CompanyEntity>>(companies,HttpStatus.OK);
	}
}
