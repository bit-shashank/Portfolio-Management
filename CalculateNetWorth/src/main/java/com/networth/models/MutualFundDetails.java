package com.networth.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class MutualFundDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long mutualFundId;
	private long count;

	@ManyToOne
	@JsonIgnore
	private PortfolioDetails portfolioDetails;

	public PortfolioDetails getPortfolioDetails() {
		return portfolioDetails;
	}

	public void setPortfolioDetails(PortfolioDetails portfolioDetails) {
		this.portfolioDetails = portfolioDetails;
	}

	public MutualFundDetails() {
		super();
	}

	public MutualFundDetails(long id, long mutualFundId, long count) {
		super();
		this.id = id;
		this.mutualFundId = mutualFundId;
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMutualFundId() {
		return mutualFundId;
	}

	public void setMutualFundId(long mutualFundId) {
		this.mutualFundId = mutualFundId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
