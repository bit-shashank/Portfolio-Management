package com.networth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.networth.dtos.AuthRequest;
import com.networth.dtos.FundDto;
import com.networth.dtos.PortfolioDTO;
import com.networth.dtos.SaleDetail;
import com.networth.dtos.StockDto;
import com.networth.models.AssertSaleResponse;
import com.networth.models.PortfolioDetails;
import com.networth.repositories.PortfolioDetailRepository;
import com.networth.services.PortfolioServices;
import com.networth.util.JwtUtil;

@RestController
public class PortfolioController {
	
	@Autowired
	private PortfolioServices portfolioServices;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private PortfolioDetailRepository detailRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest ) throws Exception{
		try {
			authenticationManager.authenticate(
				     new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())		
				);	
		}
		catch(BadCredentialsException e) {
			throw new Exception("Login Not Succesfull",e);
		}
		String token=jwtUtil.generateToken(authRequest.getUsername());
		return ResponseEntity.ok(token);
	}

	@GetMapping("/calculateNetworth/{id}")
	public PortfolioDTO calculateNetWorth(@PathVariable("id") long id,@RequestHeader("Authorization") String token) {
		token=token.substring(7);
		String curruser=jwtUtil.extractUsername(token);
		PortfolioDetails userDetails=detailRepository.findById(id).get();
		String username=userDetails.getUsername();
		if(!curruser.equals(username))return null;
		return portfolioServices.calculateNetWorth(id);
	}
	
	@PostMapping("sellAssets/{id}")
	public PortfolioDTO sellAssests(@PathVariable("id") long id,@RequestBody SaleDetail saleDetail) {
		return portfolioServices.sellAssets(id, saleDetail);
	}
	
	@GetMapping("/stocks")
	public StockDto[] getAllStocks(){
		return portfolioServices.getAllStocks();
	}
	
	@GetMapping("/mutualfunds")
	public FundDto[] getAllFunds() {
		return portfolioServices.getAllFunds();
	}
}
