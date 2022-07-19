package com.shareprice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shareprice.models.Stock;
import com.shareprice.services.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;

	@GetMapping("/dailyshareprice/{id}")
	public Stock getSharePrice(@PathVariable("id") long id) {
		return stockService.getPrice(id);
	}

}
