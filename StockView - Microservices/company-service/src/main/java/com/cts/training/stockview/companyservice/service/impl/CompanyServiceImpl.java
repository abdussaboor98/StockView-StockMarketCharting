package com.cts.training.stockview.companyservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.training.stockview.companyservice.entity.CompanyEntity;
import com.cts.training.stockview.companyservice.entity.ListedInEntity;
import com.cts.training.stockview.companyservice.repo.CompanyRepository;
import com.cts.training.stockview.companyservice.repo.ListedInRepository;
import com.cts.training.stockview.companyservice.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private ListedInRepository listedInRepo;

	@Override
	public List<CompanyEntity> getAllCompanys() {
		return companyRepo.findAll();
	}

	@Override
	public CompanyEntity getCompanyById(int id) {
		Optional<CompanyEntity> companies = companyRepo.findById(id);
		return companies.get();
	}

	@Override
	public CompanyEntity addCompany(CompanyEntity company) {
		List<ListedInEntity> list = new ArrayList<ListedInEntity>();
		for (ListedInEntity enity : company.getListedIn()) {
			System.out.println(enity);
			list.add(listedInRepo.save(enity));
		}
		company.setListedIn(list);
		return companyRepo.save(company);
	}

	@Override
	public CompanyEntity updateCompany(CompanyEntity company) {
		List<ListedInEntity> list = new ArrayList<ListedInEntity>();
		for (ListedInEntity entity : company.getListedIn()) {
			list.add(listedInRepo.save(entity));
		}
		company.setListedIn(list);
		return companyRepo.save(company);
	}

	@Override
	public void deleteCompany(int id) {
		companyRepo.deleteById(id);
	}

	@Override
	public Optional<CompanyEntity> getCompanyByStockCodeAndExchangeName(String stockCode, String stockExchangeName) {
		return companyRepo.findByListedIn_StockCode_AndListedIn_StockExchangeName(stockCode, stockExchangeName);
	}

	@Override
	public List<CompanyEntity> getCompaniesByStockExchange(String stockExchange) {
		return companyRepo.findByListedIn_StockExchangeName(stockExchange);
	}

	@Override
	public List<CompanyEntity> getCompaniesByPattern(String pattern) {
//		pattern = pattern + "%";
		return companyRepo.findAllByNameContaining(pattern);
	}
}
