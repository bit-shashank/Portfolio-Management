package com.shareprice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shareprice.models.Stock;
import com.shareprice.repositories.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	// get stock price using stock id
	public double getPrice(long id) {

		Stock stock = stockRepository.findById(id).get();

		return stock.getCurrentPrice();
	}
}
