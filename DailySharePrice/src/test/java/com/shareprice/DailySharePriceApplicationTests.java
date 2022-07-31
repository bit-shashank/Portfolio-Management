package com.shareprice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shareprice.models.Stock;
import com.shareprice.repositories.StockRepository;
import com.shareprice.services.StockService;

@SpringBootTest
class DailySharePriceApplicationTests {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Mock
	private StockRepository mockStockRepository;
	
	private StockService stockService;
	
	@BeforeEach
	void setUp() {
		this.stockService = new StockService(mockStockRepository);
	}
	
	//Testing for services 
	@Test
	void toTestPrice() {
		 Stock stock = stockRepository.findById(1L).get();
		 assertThat(stock.getCurrentPrice()).isGreaterThan(0);
	}
	
	@Test
	void toTestGetAllStocks() {
		this.stockService.getAllStocks();
		verify(mockStockRepository).findAll();
	}
	
	@Test
	void toTestStock() {
		 Stock stock = stockRepository.findById(1L).get();
		 assertThat(stock.getId()).isEqualTo(1);
	}	
	
}
