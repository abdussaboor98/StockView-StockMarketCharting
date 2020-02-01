package com.cts.stockview.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_prices")
public class StockPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6893738367295298635L;
	
	@Id
	private int id;
	private int companyId;
	private long currentPrice;
	private LocalDate date;
	private LocalTime time;
	
	public StockPrice(int id, int companyId, long currentPrice, LocalDate date, LocalTime time) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.currentPrice = currentPrice;
		this.date = date;
		this.time = time;
	}
	public StockPrice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public long getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(long currentPrice) {
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
		return "StockPrice [id=" + id + ", companyId=" + companyId + ", currentPrice=" + currentPrice + ", dateTime="
				+ date + "]";
	}	
}
