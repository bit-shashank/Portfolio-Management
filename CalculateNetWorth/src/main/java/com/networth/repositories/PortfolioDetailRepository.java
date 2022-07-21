package com.networth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import com.networth.models.PortfolioDetails;

public interface PortfolioDetailRepository extends JpaRepository<PortfolioDetails, Long> {

	PortfolioDetails findByUsername(String username);

}
