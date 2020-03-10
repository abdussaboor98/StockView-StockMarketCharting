package com.cts.training.stockview.stockpriceservice.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.training.stockview.stockpriceservice.entity.StockPriceEntity;
import com.cts.training.stockview.stockpriceservice.model.StockPricePerDay;
import com.cts.training.stockview.stockpriceservice.model.StockPricePerMonth;

public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Integer> {

	@Query("From StockPriceEntity where companyCode = ?1 and stockExchange=?2 and date = ?3 and time = ?4")
	public Optional<StockPriceEntity> getIfAlreadyExists(String companyCode, String stockExchange, LocalDate date,
			LocalTime time);

	@Query("SELECT new com.cts.training.stockview.stockpriceservice.model.StockPricePerDay(companyCode,stockExchange,date,AVG(currentPrice)) "
			+"FROM StockPriceEntity "
			+"WHERE companyCode=?1 AND stockExchange=?2 AND date BETWEEN ?3 AND ?4 "
			+"GROUP BY date")
	public List<StockPricePerDay> getStockPriceBetweenDates(String companyCode,String stockExchange,LocalDate startDate, LocalDate endDate);
	
	@Query("SELECT MAX(date) FROM StockPriceEntity WHERE companyCode=?1 AND stockExchange=?2")
	public Optional<LocalDate> getMaxDate(String companyCode,String stockExchange);
	
	@Query("SELECT MIN(date) FROM StockPriceEntity WHERE companyCode=?1 AND stockExchange=?2")
	public Optional<LocalDate> getMinDate(String companyCode,String stockExchange);
	
	@Query("SELECT new com.cts.training.stockview.stockpriceservice.model.StockPricePerMonth(companyCode, stockExchange, MONTH(date) ,AVG(currentPrice)) "
			+"FROM StockPriceEntity "
			+"WHERE companyCode=?1 AND stockExchange=?2 AND date BETWEEN ?3 AND ?4 "
			+"GROUP BY MONTH(date)")
	public List<StockPricePerMonth> getAverageStockPriceByMonth(String companyCode,String stockExchange,LocalDate startDate, LocalDate endDate);
}
