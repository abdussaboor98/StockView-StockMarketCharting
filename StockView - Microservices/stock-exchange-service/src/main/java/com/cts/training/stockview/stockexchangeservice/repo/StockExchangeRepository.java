package com.cts.training.stockview.stockexchangeservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.training.stockview.stockexchangeservice.entity.StockExchangeEntity;

public interface StockExchangeRepository extends JpaRepository<StockExchangeEntity, Integer> {

	@Query("SELECT name FROM StockExchangeEntity")
	public List<String> getAllStockExchangesNames();
}
