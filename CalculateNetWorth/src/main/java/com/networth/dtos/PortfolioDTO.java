package com.networth.dtos;

import java.util.List;

import com.networth.models.MutualFundDetails;
import com.networth.models.StockDetails;

public class PortfolioDTO {
     
	public PortfolioDTO() {
		super();
	}
	private String netWorth;
	private String username;
	private List<StockDto> stockDetails;
	private List<FundDto> mutualFundDetails;
	public String getNetWorth() {
		return netWorth;
	}
	public void setNetWorth(String netWorth) {
		this.netWorth = netWorth;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<StockDto> getStockDetails() {
		return stockDetails;
	}
	public void setStockDetails(List<StockDto> stockDetails) {
		this.stockDetails = stockDetails;
	}
	public List<FundDto> getMutualFundDetails() {
		return mutualFundDetails;
	}
	public void setMutualFundDetails(List<FundDto> mutualFundDetails) {
		this.mutualFundDetails = mutualFundDetails;
	}
	public PortfolioDTO(String netWorth, String username, List<StockDto> stockDetails,
			List<FundDto> mutualFundDetails) {
		super();
		this.netWorth = netWorth;
		this.username = username;
		this.stockDetails = stockDetails;
		this.mutualFundDetails = mutualFundDetails;
	}
	
}
