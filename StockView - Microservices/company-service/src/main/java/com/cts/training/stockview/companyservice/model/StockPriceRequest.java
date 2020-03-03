package com.cts.training.stockview.companyservice.model;

import java.time.LocalDate;

public class StockPriceRequest {

	int companyId;
	LocalDate from;
	LocalDate to;
	int periodicity;

	public StockPriceRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockPriceRequest(int companyId, LocalDate from, LocalDate to, int periodicity) {
		super();
		this.companyId = companyId;
		this.from = from;
		this.to = to;
		this.periodicity = periodicity;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public LocalDate getFrom() {
		return from;
	}

	public void setFrom(LocalDate from) {
		this.from = from;
	}

	public LocalDate getTo() {
		return to;
	}

	public void setTo(LocalDate to) {
		this.to = to;
	}

	public int getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}

	@Override
	public String toString() {
		return "StockPriceRequest [companyId=" + companyId + ", from=" + from + ", to=" + to + ", periodicity="
				+ periodicity + "]";
	}

}
