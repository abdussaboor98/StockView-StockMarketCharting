package com.cts.stockview.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import com.cts.stockview.dao.StockPriceDAO;
import com.cts.stockview.daoimpl.StockPriceDAOImpl;
import com.cts.stockview.model.StockPrice;

public class StockPriceController {

	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		
		StockPrice stockPrice = new StockPrice(101, 101, 234234L, date,time);
		
		StockPriceDAO stockPriceDAO = new StockPriceDAOImpl();
		
		stockPriceDAO.addStockPrice(stockPrice);
	}
}
