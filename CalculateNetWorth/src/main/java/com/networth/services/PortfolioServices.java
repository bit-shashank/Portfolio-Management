package com.networth.services;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.networth.models.AssertSaleResponse;
import com.networth.models.MutualFundDetails;
import com.networth.models.PortfolioDetails;
import com.networth.models.SaleDetail;
import com.networth.models.StockDetails;
import com.networth.repositories.PortfolioDetailRepository;

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
		double stockPrice = 0;
		double mutualfundPrice = 0;

		// getting the portfolio and its corresponding stocks and mutual funds
		PortfolioDetails portfolioDetails = portfolioDetailRepository.findById(id).get();
		List<StockDetails> stockDetailsList = portfolioDetails.getStoclDetails();
		List<MutualFundDetails> mutualFundDetailsList = portfolioDetails.getMutualFundDetails();

		// calculating stock part
		for (StockDetails stockDetails : stockDetailsList) {
			stockCount = stockDetails.getCount();
			stockPrice = restTemplate.getForObject(
					"http://localhost:4500/stock/dailyshareprice/" + String.valueOf(stockDetails.getStockId()),
					Double.class);
			amount = amount + (stockCount * stockPrice);
		}

		// calculate Mutual fund part
		for (MutualFundDetails mutualFundDetails : mutualFundDetailsList) {
			mutualFundCount = mutualFundDetails.getCount();
			mutualfundPrice = restTemplate.getForObject("http://localhost:5500/mutualfund/mutualFundNav/"
					+ String.valueOf(mutualFundDetails.getMutualFundId()), Double.class);
			amount = amount + (mutualFundCount * mutualfundPrice);
		}

		// represent in rupees
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

		String moneyString = formatter.format(amount);

		return moneyString;
	}

	// sell assets using portfolio entity
	public AssertSaleResponse sellAssets(long id, SaleDetail saleDetail) {

		// getting the portfolio and its corresponding stocks and mutual funds
		PortfolioDetails portfolioDetails = portfolioDetailRepository.findById(id).get();
		List<StockDetails> stockDetailsList = portfolioDetails.getStoclDetails();
		List<MutualFundDetails> mutualFundDetailsList = portfolioDetails.getMutualFundDetails();

		// get stocks and mutual funds to sell
		List<StockDetails> saleStockDetailsList = saleDetail.getStockDetails();
		List<MutualFundDetails> saleMutualFundDetailsList = saleDetail.getMutualFundDetails();

		// fail list of stocks and mutual funds
		List<StockDetails> failStockDetailsList = saleDetail.getStockDetails();
		List<MutualFundDetails> failMutualFundDetailsList = saleDetail.getMutualFundDetails();

		// Assert Sale Response
		AssertSaleResponse assertSaleResponse = new AssertSaleResponse();
		assertSaleResponse.setSaleStatus(true);

		// compare and verify and calculate stocks
		for (StockDetails saleStockDetails : saleStockDetailsList) {
			int i = 0;
			for (StockDetails stockDetails : stockDetailsList) {
				i++;
				if (saleStockDetails.getStockId() == stockDetails.getStockId()) {
					if (saleStockDetails.getCount() <= stockDetails.getCount()) {
						// update the number of stock holdings
						stockDetails.setCount(stockDetails.getCount() - saleStockDetails.getCount());
					} else {
						// used to show the list of stocks that has less count with holder
						assertSaleResponse.setSaleStatus(false);
						failStockDetailsList.add(stockDetails);
					}
				} else {
					// to show error when he/she don't even have such stocks
				}
			}
		}

		// compare and verify and calculate mutual funds
		for (MutualFundDetails saleMutualFundDetails : saleMutualFundDetailsList) {
			int i = 0;
			for (MutualFundDetails mutualFundDetails : mutualFundDetailsList) {
				i++;
				if (saleMutualFundDetails.getMutualFundId() == mutualFundDetails.getMutualFundId()) {
					if (saleMutualFundDetails.getCount() <= mutualFundDetails.getCount()) {
						// update the number of stock holdings
						mutualFundDetails.setCount(mutualFundDetails.getCount() - saleMutualFundDetails.getCount());
					} else {
						// used to show the list of stocks that has less count with holder
						assertSaleResponse.setSaleStatus(false);
						failMutualFundDetailsList.add(mutualFundDetails);
					}
				} else {
					// to show error when he/she don't even have such mutual fund
				}
			}
		}

		portfolioDetailRepository.save(portfolioDetails);
		assertSaleResponse.setNetworth(calculateNetWorth(id));

		return assertSaleResponse;
	}
}
