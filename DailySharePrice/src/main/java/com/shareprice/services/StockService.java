package com.shareprice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shareprice.models.Stock;
import com.shareprice.repositories.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	// get stock using stock id
	public Stock getStock(long id) {
		return stockRepository.findById(id).get();
	}

	public StockService(StockRepository stockRepository) {
		super();
		this.stockRepository = stockRepository;
	}

	// get stock price using stock id
	public double getPrice(long id) {
		return stockRepository.findById(id).get().getCurrentPrice();
	}

	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}

}
