package com.xprodcda.spring.xprodcda.service;



import java.util.List;

import com.xprodcda.spring.xprodcda.domain.User;

public interface IUserService {
	User register(String firstName, String lastName, String username, String email);
	List<User> getUsers();
	User findUserByUsername(String username);
	User findUserByEmail(String email);
	
}
