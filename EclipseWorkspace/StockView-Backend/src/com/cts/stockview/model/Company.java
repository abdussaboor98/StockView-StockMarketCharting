package com.cts.stockview.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company implements Serializable {

	private static final long serialVersionUID = -7577210155812795334L;
	@Id
	private int id;
	private String name;
	private String ceoName;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "directors")
	private List<String> directors;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "in_stock_exchanges")
	private List<String> stockExchanges;
	private String sector;
	private String brief;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "stock_codes")
	private List<String> stockCodes;
	private double turnover;

	public Company() {

	}

	public Company(int id, String name, String ceoName, List<String> directors, List<String> stockExchanges,
			String sector, String brief, List<String> stockCodes, double turnover) {
		super();
		this.id = id;
		this.name = name;
		this.ceoName = ceoName;
		this.directors = directors;
		this.stockExchanges = stockExchanges;
		this.sector = sector;
		this.brief = brief;
		this.stockCodes = stockCodes;
		this.turnover = turnover;
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

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public List<String> getStockExchanges() {
		return stockExchanges;
	}

	public void setStockExchanges(List<String> stockExchanges) {
		this.stockExchanges = stockExchanges;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public List<String> getStockCodes() {
		return stockCodes;
	}

	public void setStockCodes(List<String> stockCodes) {
		this.stockCodes = stockCodes;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", ceoName=" + ceoName + ", directors=" + directors
				+ ", stockExchanges=" + stockExchanges + ", sector=" + sector + ", brief=" + brief + ", stockCodes="
				+ stockCodes + ", turnover=" + turnover + "]";
	}

}
