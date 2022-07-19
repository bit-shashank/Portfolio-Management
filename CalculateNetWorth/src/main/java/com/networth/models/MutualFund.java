package com.networth.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mutualfunds")
public class MutualFund {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private double currentNav;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCurrentNav() {
		return currentNav;
	}

	public void setCurrentNav(double currentNav) {
		this.currentNav = currentNav;
	}

	public MutualFund(long id, String name, double currentNav) {
		super();
		this.id = id;
		this.name = name;
		this.currentNav = currentNav;
	}

	public MutualFund() {
		super();
	}

}
