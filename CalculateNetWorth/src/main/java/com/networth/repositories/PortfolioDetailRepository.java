package com.networth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.networth.models.PortfolioDetails;

public interface PortfolioDetailRepository extends JpaRepository<PortfolioDetails, Long> {

}
