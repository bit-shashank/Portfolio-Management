package com.networth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.networth.models.AssertSaleResponse;
import com.networth.models.SaleDetail;
import com.networth.services.PortfolioServices;

@RestController
public class PortfolioController {
	
	@Autowired
	private PortfolioServices portfolioServices;
	
	@GetMapping("/calculateNetworth/{id}")
	public String calculateNetWorth(@PathVariable("id") long id) {
		return portfolioServices.calculateNetWorth(id);
	}
	
	@PostMapping("sellAssets/{id}")
	public String sellAssests(@PathVariable("id") long id,@RequestBody SaleDetail saleDetail) {
		AssertSaleResponse assertSaleResponse =  portfolioServices.sellAssets(id, saleDetail);
		System.out.println(assertSaleResponse);
		return assertSaleResponse.getNetworth();
	}
}
