package com.cts.training.stockview.stockpriceservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;

public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Integer>{

}
