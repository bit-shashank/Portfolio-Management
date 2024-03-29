package com.mutualfundNAV.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutualfundNAV.models.MutualFund;
import com.mutualfundNAV.services.MutualFundService;

@RestController
@RequestMapping("/mutualfund")
public class MutualFundController {

	@Autowired
	private MutualFundService mutualFundService;

	@GetMapping("/mutualFundNav/{id}")
	public double getMutualFundNAV(@PathVariable("id") long id) {
		return mutualFundService.getMutualFundNAV(id);
	}
	
	@GetMapping("/{id}")
	public MutualFund getMutualFund(@PathVariable("id") long id) {
		return mutualFundService.getMutualFund(id);
	}
	@GetMapping("/all")
	public List<MutualFund> getAllFunds(){
		return mutualFundService.getAllFunds();
	}

}
