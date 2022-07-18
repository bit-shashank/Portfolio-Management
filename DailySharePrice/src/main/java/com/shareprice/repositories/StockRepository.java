package com.shareprice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shareprice.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
	
	
}	
