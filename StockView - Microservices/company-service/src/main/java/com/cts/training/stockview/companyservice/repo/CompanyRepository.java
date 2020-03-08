package com.cts.training.stockview.companyservice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.training.stockview.companyservice.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

	public Optional<CompanyEntity> findByListedIn_StockCode_AndListedIn_StockExchangeName(String stockCode, String stockExchangeName);
	
	public List<CompanyEntity> findByListedIn_StockExchangeName(String stockExchange);
}
