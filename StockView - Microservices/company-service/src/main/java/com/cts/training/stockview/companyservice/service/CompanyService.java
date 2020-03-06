package com.cts.training.stockview.companyservice.service;

import java.util.List;


import com.cts.training.stockview.companyservice.entity.CompanyEntity;

public interface CompanyService {

	public List<CompanyEntity> getAllCompanys();
	
	public CompanyEntity getCompanyById(int id);
	
	public CompanyEntity addCompany(CompanyEntity company);
	
	public CompanyEntity updateCompany(CompanyEntity company);
	
	public void deleteCompany(int id);
	public boolean getCompanyByStockCodeAndExchangeName(String stockCode,String stockExchangeName);
}
