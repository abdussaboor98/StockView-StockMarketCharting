package com.cts.training.stockview.stockexchangeservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "stock_exchanges")
public class StockExchangeEntity implements Serializable {

	private static final long serialVersionUID = 5971304677677187555L;
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String contactAddress;
	private String brief;
	private String remarks;

	public StockExchangeEntity() {

	}

	public StockExchangeEntity(int id, String exchangeName, String contractAddress, String brief, String remarks) {
		super();
		this.id = id;
		this.name = exchangeName;
		this.contactAddress = contractAddress;
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

	public void setContactAddress(String contractAddress) {
		this.contactAddress = contractAddress;
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
		return "StockExchange [id=" + id + ", exchangeName=" + name + ", contractAddress=" + contactAddress + ", brief="
				+ brief + ", remarks=" + remarks + "]";
	}

}