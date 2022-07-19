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
	public Stock getPrice(long id) {

		return stockRepository.findById(id).get();
	}
}
