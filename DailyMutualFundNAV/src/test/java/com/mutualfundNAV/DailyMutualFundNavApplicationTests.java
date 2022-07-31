package com.mutualfundNAV;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mutualfundNAV.models.MutualFund;
import com.mutualfundNAV.repositories.MutualfundRepository;
import com.mutualfundNAV.services.MutualFundService;


@SpringBootTest
class DailyMutualFundNavApplicationTests {
	
	@Mock
	private MutualfundRepository mockMutualfundRepository;
	   
	@Autowired
	private MutualfundRepository mutualfundRepository;
	
	private MutualFundService mutualFundService;
	
	@BeforeEach
	void setUp() {
		this.mutualFundService = new MutualFundService(mockMutualfundRepository);
	}
	
	//Testing for services 
	@Test
	void toTestNav() {
		 MutualFund mutualFund = mutualfundRepository.findById(1L).get();
		 assertThat(mutualFund.getCurrentNav()).isGreaterThan(0);
	}
	
	@Test
	void toTestGetAllMutualFunds() {
		this.mutualFundService.getAllFunds();
		verify(mockMutualfundRepository).findAll();
	}
	
	@Test
	void toTestMutualFund() {
		 MutualFund mutualFund = mutualfundRepository.findById(1L).get();
		 assertThat(mutualFund.getId()).isEqualTo(1);
	}	
	
}
