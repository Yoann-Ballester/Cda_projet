package com.xprodcda.spring.xprodcda.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xprodcda.spring.xprodcda.domain.User;
import com.xprodcda.spring.xprodcda.domain.UserPrincipal;
import com.xprodcda.spring.xprodcda.exception.domain.EmailExistException;
import com.xprodcda.spring.xprodcda.exception.domain.UserNotFoundException;
import com.xprodcda.spring.xprodcda.exception.domain.UsernameExistException;
import com.xprodcda.spring.xprodcda.repository.IUserRepository;
import com.xprodcda.spring.xprodcda.service.IUserService;


import org.apache.commons.lang3.StringUtils;
import jakarta.transaction.Transactional;

import static com.xprodcda.spring.xprodcda.enumeration.Role.*;


@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements IUserService, UserDetailsService {

	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private IUserRepository  userRepository; 
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImpl(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder= passwordEncoder;
	}



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
			UserPrincipal userPrincipal = new UserPrincipal(user);
			LOGGER.info("Returning found by username: "+username);
			return userPrincipal;
		}
				
	}



	@Override
	public User register(String firstname, String lastname, String username, String email) {
		try {
			validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
			User user = new User();
			user.setUserId(generateUserId());
			String password = generatePassword();
			String encodedPassword = encodePassword(password);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setUsername(username);
			user.setEmail(email);
			user.setJoinDate(new Date());
			user.setPassword(encodedPassword);
			user.setActive(true);
			user.setNotLocked(true);
			user.setRole(ROLE_USER.name());
			user.setAuthorities(ROLE_USER.getAuthorities());
			user.setProfileImageUrl(getTemporaryProfileImageUrl());
			
			userRepository.save(user);
			LOGGER.info("New User password : " + password);
			
		} catch (UserNotFoundException | UsernameExistException | EmailExistException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String encodePassword(String password) {
		
		return passwordEncoder.encode(password);
	}



	private String generateUserId() {
		
		return RandomStringUtils.randomNumeric(10);
	}



	private String generatePassword() {
		
		return RandomStringUtils.randomAlphanumeric(10);
	}



	private String getTemporaryProfileImageUrl() {
		
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/image/profile/temp").toString();
	}



	private User validateNewUsernameAndEmail(String currentUsername, String userNewByUsername, String userNewByEmail)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		
		User userByUsername = findUserByUsername(userNewByUsername);
		User userByEmail = findUserByUsername(userNewByEmail);
		
		if (StringUtils.isNotBlank(currentUsername)) {
			
			User currentUser = findUserByUsername(currentUsername);
					
			if (currentUser == null) {
				throw new UserNotFoundException("No user found by username" + currentUsername);
			}
			if (userByUsername != null && !currentUser.equals(userByUsername)) {
				throw new UsernameExistException("Username already exists");
			}
			if (currentUser != null && !currentUser.equals(userByEmail)) {
				throw new EmailExistException("Username already exists");
			}
			return currentUser;
		} else {

			if (userNewByUsername != null) {
				throw new UsernameExistException("Username already exists"+userNewByUsername);
			}

			if (userNewByEmail != null) {
				throw new EmailExistException("Username already exists"+userNewByEmail);
			}
		}
		return null;

	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
