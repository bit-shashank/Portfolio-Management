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
	public MutualFund getMutualFundNAV(long id) {

		return mutualfundRepository.findById(id).get();
	}
}
