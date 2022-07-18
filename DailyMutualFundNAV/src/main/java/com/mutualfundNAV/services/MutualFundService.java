package com.mutualfundNAV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfundNAV.models.MutualFund;
import com.mutualfundNAV.repositories.MutualfundRepository;

@Service
public class MutualFundService {

	@Autowired
	private MutualfundRepository mutualfundRepository;

	// get mutualfund nav price using stock id
	public double getMutualFundNAV(long id) {

		MutualFund mutualFund = mutualfundRepository.findById(id).get();

		return mutualFund.getCurrentNav();
	}
}
