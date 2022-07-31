package com.networth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.networth.models.MutualFundDetails;
import com.networth.models.PortfolioDetails;
import com.networth.models.StockDetails;
import com.networth.repositories.MutualFundDetailRepository;
import com.networth.repositories.PortfolioDetailRepository;
import com.networth.repositories.StockDetailRepository;


@SpringBootTest
class CalculateNetWorthApplicationTests {
	
	@Autowired
	private PortfolioDetailRepository portfolioDetailRepository;
	
	@Autowired
	private StockDetailRepository stockDetailRepository;
	
	@Autowired
	private MutualFundDetailRepository mutualFundDetailRepository;
	
	//Testing 
	@Test
	void toTestPortforlioDetails() {
		PortfolioDetails portfolioDetails = portfolioDetailRepository.findById(1L).get();
		 assertThat(portfolioDetails.getId()).isEqualTo(1);
	}
	
	@Test
	void toTestPortforlioDetailsByUsername() {
		PortfolioDetails portfolioDetails = portfolioDetailRepository.findByUsername("admin1");
		 assertThat(portfolioDetails.getUsername()).isEqualTo("admin1");
	}
	
	
	@Test
	void toTestStockDetails() {
		StockDetails stockDetails = stockDetailRepository.findById(1L).get();
		 assertThat(stockDetails.getId()).isEqualTo(1);
	}
	
	@Test
	void toTestMutualFundDetails() {
		MutualFundDetails mutualFundDetails = mutualFundDetailRepository.findById(1L).get();
		assertThat(mutualFundDetails.getId()).isEqualTo(1);
	}
	
}

