package com.networth.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class StockDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long stockId;
	private long count;

	@ManyToOne
	@JsonIgnore
	private PortfolioDetails portfolioDetails;

	public StockDetails(long id, long stockId, long count) {
		super();
		this.id = id;
		this.stockId = stockId;
		this.count = count;
	}

	public PortfolioDetails getPortfolioDetails() {
		return portfolioDetails;
	}

	public void setPortfolioDetails(PortfolioDetails portfolioDetails) {
		this.portfolioDetails = portfolioDetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public StockDetails() {
		super();
	}

}
