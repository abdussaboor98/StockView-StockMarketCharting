package com.cts.training.stockview.stockpriceservice.model;

import java.time.LocalDate;

public class StockPricePerDay {

	private String companyCode;
	private String stockExchange;
	private LocalDate date;
	private double avgPrice;

	public StockPricePerDay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockPricePerDay(String companyCode, String stockExchange, LocalDate date, double avgPrice) {
		super();
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.date = date;
		this.avgPrice = avgPrice;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	@Override
	public String toString() {
		return "StockPricePerDay [companyCode=" + companyCode + ", stockExchange=" + stockExchange + ", date=" + date
				+ ", avgPrice=" + avgPrice + "]";
	}

}
