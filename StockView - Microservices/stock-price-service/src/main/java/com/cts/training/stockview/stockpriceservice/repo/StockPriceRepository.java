package com.cts.training.stockview.stockpriceservice.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;

public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Integer>{
	
	@Query("From StockPriceEntity where companyCode = ?1 and date = ?2 and time = ?3")
	public Optional<StockPriceEntity> getIfAlreadyExists(String companyCode,LocalDate data, LocalTime time);
}
