package com.xprod.spring.xprod.service;

import java.util.List;

import com.xprod.spring.xprod.domain.User;

public interface IUserService {
	
	User register(String firstName, String lastName, String username, String email);
	
	List<User> getUsers();
	
	User findUserByUsername(String username);
	
	User findUserByEmail(String email);
	
}
