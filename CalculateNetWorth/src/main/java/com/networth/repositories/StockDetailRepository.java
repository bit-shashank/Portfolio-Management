package com.networth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.networth.models.StockDetails;

public interface StockDetailRepository extends JpaRepository<StockDetails, Long>{

}
