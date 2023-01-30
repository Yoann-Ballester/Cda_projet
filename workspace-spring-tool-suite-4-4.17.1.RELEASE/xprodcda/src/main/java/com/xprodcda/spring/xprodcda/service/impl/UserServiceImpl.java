package com.xprodcda.spring.xprodcda.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xprodcda.spring.xprodcda.domain.User;
import com.xprodcda.spring.xprodcda.repository.IUserRepository;
import com.xprodcda.spring.xprodcda.service.IUserService;



public class UserServiceImpl implements IUserService, UserDetailsService {

	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private IUserRepository  userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if(user==null) {
			LOGGER.error("User not found by username: "+username);
			throw new UsernameNotFoundException("User not found by username: "+username);
		}else {
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepository.save(user);
			
		}
		return null;
	}

}
