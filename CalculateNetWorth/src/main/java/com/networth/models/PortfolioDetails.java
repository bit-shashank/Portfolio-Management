package com.networth.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PortfolioDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portfolioDetails")
	@JsonIgnore
	private List<StockDetails> stoclDetails;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portfolioDetails")
	@JsonIgnore
	private List<MutualFundDetails> mutualFundDetails;

	public PortfolioDetails(long id, List<StockDetails> stoclDetails, List<MutualFundDetails> mutualFundDetails) {
		super();
		this.id = id;
		this.stoclDetails = stoclDetails;
		this.mutualFundDetails = mutualFundDetails;
	}

	public PortfolioDetails() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<StockDetails> getStoclDetails() {
		return stoclDetails;
	}

	public void setStoclDetails(List<StockDetails> stoclDetails) {
		this.stoclDetails = stoclDetails;
	}

	public List<MutualFundDetails> getMutualFundDetails() {
		return mutualFundDetails;
	}

	public void setMutualFundDetails(List<MutualFundDetails> mutualFundDetails) {
		this.mutualFundDetails = mutualFundDetails;
	}

}
