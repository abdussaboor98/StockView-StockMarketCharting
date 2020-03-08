package com.cts.training.stockview.stockpriceservice.model;

import java.time.LocalDate;

public class CompanyStockPriceRequest {

	private String companyCode;
	private String stockExchange;
	private LocalDate startDate;
	private LocalDate endDate;
	private int periodicity;

	public CompanyStockPriceRequest() {
	}

	public CompanyStockPriceRequest(String companyCode, String stockExchange, LocalDate startDate, LocalDate endDate,
			int periodicity) {
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.startDate = startDate;
		this.endDate = endDate;
		this.periodicity = periodicity;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}

	@Override
	public String toString() {
		return "CompanyStockPriceRequest [companyCode=" + companyCode + ", stockExchange=" + stockExchange
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", periodicity=" + periodicity + "]";
	}

}
