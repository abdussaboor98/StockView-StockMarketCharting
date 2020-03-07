package com.cts.training.stockview.stockpriceservice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ImportSummary {

	private int numberOfRecordsInserted;
	private LocalDate startDate;
	private LocalDate endDate;
	private Set<String> companyCodes;
	private Set<String> stockExchanges;
	private List<String> duplicates;
	
	public ImportSummary(int numberOfRecordsInserted, LocalDate startDate, LocalDate endDate, Set<String> companyCodes,
			Set<String> stockExchanges, List<String> duplicates) {
		super();
		this.numberOfRecordsInserted = numberOfRecordsInserted;
		this.startDate = startDate;
		this.endDate = endDate;
		this.companyCodes = companyCodes;
		this.stockExchanges = stockExchanges;
		this.duplicates = duplicates;
	}
	public ImportSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNumberOfRecordsInserted() {
		return numberOfRecordsInserted;
	}
	public void setNumberOfRecordsInserted(int numberOfRecordsInserted) {
		this.numberOfRecordsInserted = numberOfRecordsInserted;
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
	public Set<String> getCompanyCodes() {
		return companyCodes;
	}
	public void setCompanyCodes(Set<String> companyCodes) {
		this.companyCodes = companyCodes;
	}
	public Set<String> getStockExchanges() {
		return stockExchanges;
	}
	public void setStockExchanges(Set<String> stockExchanges) {
		this.stockExchanges = stockExchanges;
	}
	public List<String> getDuplicates() {
		return duplicates;
	}
	public void setDuplicates(List<String> duplicates) {
		this.duplicates = duplicates;
	}
	
	
	
}
