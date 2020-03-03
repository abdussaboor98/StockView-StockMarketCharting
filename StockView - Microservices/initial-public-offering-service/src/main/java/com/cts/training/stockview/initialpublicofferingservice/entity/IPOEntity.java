package com.cts.training.stockview.initialpublicofferingservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component(value = "ipoEntity")
@Table(name = "ipos")
public class IPOEntity implements Serializable {

	private static final long serialVersionUID = 471662972314035222L;
	@Id
	@GeneratedValue
	private int id;
	private String companyName;
	private String stockExchange;
	private long pricePerShare;
	private long totalShares;
	private LocalDateTime openDateTime;
	private String remarks;

	public IPOEntity() {

	}

	public IPOEntity(int id, String companyName, String stockExchange, long pricePerShare, long totalStocks,
			LocalDateTime openDateTime, String remarks) {
		this.id = id;
		this.companyName = companyName;
		this.stockExchange = stockExchange;
		this.pricePerShare = pricePerShare;
		this.totalShares = totalStocks;
		this.openDateTime = openDateTime;
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public long getPricePerShare() {
		return pricePerShare;
	}

	public void setPricePerShare(long pricePerShare) {
		this.pricePerShare = pricePerShare;
	}

	public long getTotalShares() {
		return totalShares;
	}

	public void setTotalShares(long totalShares) {
		this.totalShares = totalShares;
	}

	public LocalDateTime getOpenDateTime() {
		return openDateTime;
	}

	public void setOpenDateTime(LocalDateTime openDateTime) {
		this.openDateTime = openDateTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "IPO [id=" + id + ", companyName=" + companyName + ", stockExchange=" + stockExchange
				+ ", pricePerShare=" + pricePerShare + ", totalStocks=" + totalShares + ", openDateTime=" + openDateTime
				+ ", remarks=" + remarks + "]";
	}
}
