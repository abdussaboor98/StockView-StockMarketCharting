package com.cts.training.stockview.stockexchangeservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.training.stockview.stockexchangeservice.entity.StockExchangeEntity;

public interface StockExchangeRepository extends JpaRepository<StockExchangeEntity, Integer> {

}
