package com.networth.services;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.networth.models.MutualFund;
import com.networth.models.MutualFundDetails;
import com.networth.models.PortfolioDetails;
import com.networth.models.Stock;
import com.networth.models.StockDetails;
import com.networth.repositories.MutualFundDetailRepository;
import com.networth.repositories.PortfolioDetailRepository;
import com.networth.repositories.StockDetailRepository;

@Service
public class PortfolioServices {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PortfolioDetailRepository portfolioDetailRepository;

	// calculate net worth of a single portfolio
	public String calculateNetWorth(long id) {

		long stockCount = 0;
		long mutualFundCount = 0;
		double amount = 0;

		// getting the portfolio and its corresponding stocks and mutual funds
		PortfolioDetails portfolioDetails = portfolioDetailRepository.findById(id).get();
		List<StockDetails> stockDetailsList = portfolioDetails.getStoclDetails();
		List<MutualFundDetails> mutualFundDetailsList = portfolioDetails.getMutualFundDetails();

		// calculating stock part
		for (StockDetails stockDetails : stockDetailsList) {
			stockCount = stockDetails.getCount();
			Stock stock = restTemplate.getForObject(
					"http://localhost:4500/stock/dailyshareprice/" + String.valueOf(stockDetails.getStockId()),
					Stock.class);
			amount = amount + (stockCount * stock.getCurrentPrice());
		}

		// calculate Mutual fund part
		for (MutualFundDetails mutualFundDetails : mutualFundDetailsList) {
			mutualFundCount = mutualFundDetails.getCount();
			MutualFund mutualFund = restTemplate.getForObject("http://localhost:5500/mutualfund/mutualFundNav/"
					+ String.valueOf(mutualFundDetails.getMutualFundId()), MutualFund.class);
			amount = amount + (mutualFundCount * mutualFund.getCurrentNav());
		}

		// represent in rupees 
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

		String moneyString = formatter.format(amount);

		return moneyString;
	}
	
	
	//sell assets using portfolio entity
	public String netWorthAfterSelling() {
		return "xyz";
	}
}
