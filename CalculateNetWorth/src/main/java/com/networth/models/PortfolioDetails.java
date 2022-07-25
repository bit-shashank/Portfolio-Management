package com.networth.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PortfolioDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "portfolioDetails")
	private List<MutualFundDetails> mutualFundDetails;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "portfolioDetails")
	private List<StockDetails> stockDetails;
	
	
    
	@Column
	private String username;
	
	@Column
	private String password;
	
	public PortfolioDetails(long id, List<StockDetails> stockDetails, List<MutualFundDetails> mutualFundDetails,
			String username, String password, String email) {
		super();
		this.id = id;
		this.stockDetails = stockDetails;
		this.mutualFundDetails = mutualFundDetails;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column
	private String email;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<StockDetails> getStockDetails() {
		return stockDetails;
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
		return stockDetails;
	}

	public void setStockDetails(List<StockDetails> stockDetails) {
		this.stockDetails = stockDetails;
	}

	public List<MutualFundDetails> getMutualFundDetails() {
		return mutualFundDetails;
	}

	public void setMutualFundDetails(List<MutualFundDetails> mutualFundDetails) {
		this.mutualFundDetails = mutualFundDetails;
	}

	@Override
	public String toString() {
		return "PortfolioDetails [id=" + id + ", mutualFundDetails=" + mutualFundDetails + ", stockDetails="
				+ stockDetails + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}

	
	
}
