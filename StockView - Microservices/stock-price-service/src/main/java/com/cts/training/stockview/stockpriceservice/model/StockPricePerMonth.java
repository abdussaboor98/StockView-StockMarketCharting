package com.cts.training.stockview.stockpriceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StockPricePerMonth {

	private String companyCode;
	private String stockExchange;
	private int month;
	private double avgPrice;
	public StockPricePerMonth() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StockPricePerMonth(String companyCode, String stockExchange, int month, double avgPrice) {
		super();
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.month = month;
		this.avgPrice = avgPrice;
	}
	@Override
	public String toString() {
		return "StockPricePerMonth [companyCode=" + companyCode + ", stockExchange=" + stockExchange + ", month="
				+ month + ", avgPrice=" + avgPrice + "]";
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
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	
	
}
