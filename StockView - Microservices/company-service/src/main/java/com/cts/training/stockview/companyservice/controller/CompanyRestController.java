package com.cts.training.stockview.companyservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.stockview.companyservice.entity.CompanyEntity;
import com.cts.training.stockview.companyservice.service.CompanyService;

@CrossOrigin(origins = "*")
@RestController
public class CompanyRestController {

	@Autowired
	private CompanyService companyService;
	
	@GetMapping(value = "/companies",produces = "application/json")
	public List<CompanyEntity> getAllCompanys(){
		return companyService.getAllCompanys();
	}
	
	@GetMapping(value = "/companies/{id}", produces = "application/json")
	public CompanyEntity getCompanyById(@PathVariable("id") int id){
		CompanyEntity company = companyService.getCompanyById(id);
		return company;
	}
	
	@PostMapping(value = "/companies",consumes = "application/json")
	public CompanyEntity addCompany(@RequestBody CompanyEntity company) {
		return companyService.addCompany(company);
	}
	
	@PutMapping(value = "/companies",consumes = "application/json")
	public CompanyEntity updateCompany(@RequestBody CompanyEntity company) {
		return companyService.updateCompany(company);
	}
	
	@DeleteMapping(value = "/companies/{id}")
	public void deleteCompany(@PathVariable int id) {
		companyService.deleteCompany(id);
	}
}
