package com.cts.training.stockview.companyservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class StockPrice {

	private int id;
	private String companyCode;
	private String stockExchange;
	private double currentPrice;
	private LocalDate date;
	private LocalTime time;

	public StockPrice() {

	}

	public StockPrice(int id, String companyCode, String stockExchange, double currentPrice, LocalDate date,
			LocalTime time) {
		this.id = id;
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.currentPrice = currentPrice;
		this.date = date;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "StockPrice [id=" + id + ", companyCode=" + companyCode + ", stockExchange=" + stockExchange
				+ ", currentPrice=" + currentPrice + ", date=" + date + ", time=" + time + "]";
	}

}
