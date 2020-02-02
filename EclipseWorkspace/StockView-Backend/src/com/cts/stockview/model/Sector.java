package com.cts.stockview.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sectors")
public class Sector implements Serializable {

	private static final long serialVersionUID = 4760126337156021862L;
	@Id
	private int id;
	private String name;
	private String brief;

	public Sector() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sector(int id, String name, String brief) {
		super();
		this.id = id;
		this.name = name;
		this.brief = brief;
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

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	@Override
	public String toString() {
		return "Sector [id=" + id + ", name=" + name + ", brief=" + brief + "]";
	}
}
