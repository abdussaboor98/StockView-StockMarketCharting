package com.cts.stockview.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_exchanges")
public class StockExchange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5971304677677187555L;
	@Id
	private int id;
	private String exchangeName;
	private String contractAddress;
	private String brief;
	private String remarks;
	public StockExchange() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StockExchange(int id, String exchangeName, String contractAddress, String brief, String remarks) {
		super();
		this.id = id;
		this.exchangeName = exchangeName;
		this.contractAddress = contractAddress;
		this.brief = brief;
		this.remarks = remarks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public String getContractAddress() {
		return contractAddress;
	}
	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "StockExchange [id=" + id + ", exchangeName=" + exchangeName + ", contractAddress=" + contractAddress
				+ ", brief=" + brief + ", remarks=" + remarks + "]";
	}	
	
	
}