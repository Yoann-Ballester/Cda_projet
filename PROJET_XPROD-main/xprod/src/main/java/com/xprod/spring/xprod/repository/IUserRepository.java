package com.xprod.spring.xprod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xprod.spring.xprod.domain.User;

public interface IUserRepository extends JpaRepository<User, Long>{
	
	User findUserByUsername(String username);
	User findUserByEmail (String email);

}
