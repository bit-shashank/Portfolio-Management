package com.networth.services;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.networth.dtos.FundDto;
import com.networth.dtos.PortfolioDTO;
import com.networth.dtos.SaleDetail;
import com.networth.dtos.StockDto;
import com.networth.models.AssertSaleResponse;
import com.networth.models.MutualFundDetails;
import com.networth.models.PortfolioDetails;
import com.networth.models.StockDetails;
import com.networth.repositories.PortfolioDetailRepository;

@Service
public class PortfolioServices {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PortfolioDetailRepository portfolioDetailRepository;

	// calculate net worth of a single portfolio
	public PortfolioDTO calculateNetWorth(long id) {

		long stockCount = 0;
		long mutualFundCount = 0;
		double amount = 0;
		
		// getting the portfolio and its corresponding stocks and mutual funds
		PortfolioDetails portfolioDetails = portfolioDetailRepository.findById(id).get();
		
		List<StockDetails> stockDetailsList = portfolioDetails.getStoclDetails();
		List<MutualFundDetails> mutualFundDetailsList = portfolioDetails.getMutualFundDetails();
		
        List<StockDto> stockDtos=new ArrayList<>();
        List<FundDto> fundDtos=new ArrayList<>();
		// calculating stock part
		for (StockDetails stockDetails : stockDetailsList) {
			stockCount = stockDetails.getCount();
			StockDto stock = restTemplate.getForObject(
					"http://localhost:4500/stock/" + String.valueOf(stockDetails.getStockId()),
					StockDto.class);
			stock.setQty(stockCount);
			stock.setId(stockDetails.getStockId());
			amount = amount + (stockCount * stock.getCurrentPrice());
			stockDtos.add(stock);
		}

		// calculate Mutual fund part
		for (MutualFundDetails mutualFundDetails : mutualFundDetailsList) {
			mutualFundCount = mutualFundDetails.getCount();
			FundDto fundDto = restTemplate.getForObject("http://localhost:5510/mutualfund/"
					+ String.valueOf(mutualFundDetails.getMutualFundId()), FundDto.class);
			fundDto.setQty(mutualFundCount);
			amount = amount + (mutualFundCount * fundDto.getCurrentNav());
			fundDto.setId(mutualFundDetails.getMutualFundId());
			fundDtos.add(fundDto);
		}

		// represent in rupees
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

		String moneyString = formatter.format(amount);
         
		return new PortfolioDTO(moneyString,portfolioDetails.getUsername(), stockDtos,fundDtos);
	}
    public StockDto[] getAllStocks(){
    	StockDto[] portfolioDTO=restTemplate.getForObject("http://localhost:4500/stock/all", StockDto[].class);
    	return portfolioDTO;
    }
	// sell assets using portfolio entity
	public PortfolioDTO sellAssets(long id, SaleDetail saleDetail) {

		// getting the portfolio and its corresponding stocks and mutual funds
		PortfolioDetails portfolioDetails = portfolioDetailRepository.findById(id).get();
		List<StockDetails> stockDetailsList = portfolioDetails.getStoclDetails();
		List<MutualFundDetails> mutualFundDetailsList = portfolioDetails.getMutualFundDetails();

		// get stocks and mutual funds to sell
		List<StockDetails> saleStockDetailsList = saleDetail.getStockDetails();
		List<MutualFundDetails> saleMutualFundDetailsList = saleDetail.getMutualFundDetails();

		List<StockDetails> tempStocks = new ArrayList<StockDetails>();
		List<MutualFundDetails> tempFunds = new ArrayList<MutualFundDetails>();
		
		// compare and verify and calculate stocks
		for (StockDetails saleStockDetails : saleStockDetailsList) {
			
			for (StockDetails stockDetails : stockDetailsList) {
				if (saleStockDetails.getStockId() == stockDetails.getStockId()) {
					if (saleStockDetails.getCount() <= stockDetails.getCount()) {
						
						// update the number of stock holdings
						stockDetails.setCount(stockDetails.getCount() - saleStockDetails.getCount());
						if(stockDetails.getCount() <= 0) {
							tempStocks.add(stockDetails);
						}
					} 
				} else {
					// to show error when he/she don't even have such stocks
				}
			}
		}
		
		stockDetailsList.removeAll(tempStocks);
		
		
		// compare and verify and calculate mutual funds
		for (MutualFundDetails saleMutualFundDetails : saleMutualFundDetailsList) {
			for (MutualFundDetails mutualFundDetails : mutualFundDetailsList) {
				if (saleMutualFundDetails.getMutualFundId() == mutualFundDetails.getMutualFundId()) {
					if (saleMutualFundDetails.getCount() <= mutualFundDetails.getCount()) {
						// update the number of stock holdings
						mutualFundDetails.setCount(mutualFundDetails.getCount() - saleMutualFundDetails.getCount());
						if(mutualFundDetails.getCount() <= 0) {
							tempFunds.add(mutualFundDetails);
						}
					} 
				} else {
					// to show error when he/she don't even have such mutual fund
				}
			}
		}
		
		mutualFundDetailsList.removeAll(tempFunds);
		
		
		
		portfolioDetailRepository.save(portfolioDetails);
		PortfolioDTO currPortfolio=calculateNetWorth(id);
        return currPortfolio;
	}
	public FundDto[] getAllFunds() {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:5510/mutualfund/all", FundDto[].class);
	}
}
