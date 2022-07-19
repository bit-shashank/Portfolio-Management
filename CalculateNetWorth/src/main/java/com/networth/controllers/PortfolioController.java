package com.networth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.networth.services.PortfolioServices;

@RestController
public class PortfolioController {
	
	@Autowired
	private PortfolioServices portfolioServices;
	
	@GetMapping("/calculateNetworth/{id}")
	public String calculateNetWorth(@PathVariable("id") long id) {
		return portfolioServices.calculateNetWorth(id);
	}
}
