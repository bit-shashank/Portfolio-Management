package com.networth.models;

import java.util.List;

public class SaleDetail {

	private List<StockDetails> stockDetails;

	private List<MutualFundDetails> mutualFundDetails;

	public SaleDetail(List<StockDetails> stockDetails, List<MutualFundDetails> mutualFundDetails) {
		super();
		this.stockDetails = stockDetails;
		this.mutualFundDetails = mutualFundDetails;
	}

	public SaleDetail() {
		super();
	}

	public List<StockDetails> getStockDetails() {
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

}
