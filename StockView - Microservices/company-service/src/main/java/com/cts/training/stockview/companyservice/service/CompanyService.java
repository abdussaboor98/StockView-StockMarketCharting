package com.cts.training.stockview.companyservice.service;

import java.util.List;
import java.util.Optional;

import com.cts.training.stockview.companyservice.entity.CompanyEntity;

public interface CompanyService {

	public List<CompanyEntity> getAllCompanys();

	public CompanyEntity getCompanyById(int id);

	public CompanyEntity addCompany(CompanyEntity company);

	public CompanyEntity updateCompany(CompanyEntity company);

	public void deleteCompany(int id);

	public Optional<CompanyEntity> getCompanyByStockCodeAndExchangeName(String stockCode, String stockExchangeName);

	public List<CompanyEntity> getCompaniesByStockExchange(String stockExchange);

	public List<CompanyEntity> getCompaniesByPattern(String pattern);
}
