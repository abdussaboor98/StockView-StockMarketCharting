package com.cts.training.stockview.companyservice.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "companies")
public class CompanyEntity implements Serializable {

	private static final long serialVersionUID = -7577210155812795334L;
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String ceo;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@CollectionTable(name = "directors")
	private List<String> directors;

	@OneToMany(cascade = CascadeType.ALL)
	private List<ListedInEntity> listedIn;
	private String sector;
	private String brief;
	private double turnover;

	public CompanyEntity() {

	}

	public CompanyEntity(int id, String name, String ceoName, List<String> directors, List<ListedInEntity> listedIn,
			String sector, String brief, double turnover) {
		this.id = id;
		this.name = name;
		this.ceo = ceoName;
		this.directors = directors;
		this.listedIn = listedIn;
		this.sector = sector;
		this.brief = brief;
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

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public List<ListedInEntity> getListedIn() {
		return listedIn;
	}

	public void setListedIn(List<ListedInEntity> listedIn) {
		this.listedIn = listedIn;
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

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	@Override
	public String toString() {
		return "CompanyEntity [id=" + id + ", name=" + name + ", ceoName=" + ceo + ", directors=" + directors
				+ ", listedIn=" + listedIn + ", sector=" + sector + ", brief=" + brief + ", turnover=" + turnover + "]";
	}

}
