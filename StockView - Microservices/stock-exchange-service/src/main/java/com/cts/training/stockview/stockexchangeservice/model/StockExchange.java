package com.cts.training.stockview.stockexchangeservice.model;

public class StockExchange {

	private int id;
	private String name;
	private String contactAddress;
	private String brief;
	private String remarks;
	
	public StockExchange() {
	
	}

	public StockExchange(int id, String name, String contactAddress, String brief, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.contactAddress = contactAddress;
		this.brief = brief;
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
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
		return "StockExchange [id=" + id + ", name=" + name + ", contactAddress=" + contactAddress + ", brief=" + brief
				+ ", remarks=" + remarks + "]";
	}
}
