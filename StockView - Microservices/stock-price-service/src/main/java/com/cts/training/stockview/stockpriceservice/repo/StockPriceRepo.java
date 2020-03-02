package com.cts.training.stockview.stockpriceservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;

public interface StockPriceRepo extends JpaRepository<StockPriceEntity, Integer>{

}
