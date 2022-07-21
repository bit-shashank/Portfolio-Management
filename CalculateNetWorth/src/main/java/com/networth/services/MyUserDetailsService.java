package com.networth.services;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.networth.models.PortfolioDetails;
import com.networth.repositories.PortfolioDetailRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
	PortfolioDetailRepository detailRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		PortfolioDetails user=detailRepository.findByUsername(username);
		return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
	}

}
