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
import static com.xprodcda.spring.xprodcda.constant.SecurityConstant.*;


@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements IUserService, UserDetailsService {

	
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private IUserRepository  userRepository; 
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	
	public UserServiceImpl(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder= passwordEncoder;
	}
	
	





	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if(user==null) {
			LOGGER.error(NO_USER_FOUND_BY_USERNAME+username);
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME+username);
		}else {
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepository.save(user);
			UserPrincipal userPrincipal = new UserPrincipal(user);
			LOGGER.info(FOUND_USER_BY_USERNAME+username);
			return userPrincipal;
		}
				
	}


	// Ajoute également un objet utilisateur dans la base de données, réserver au front office elle est destinée 
			// à l'ajout d'un utilisateur lorsqu'un utilisateur créé un compte dans l'application
	
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
			LOGGER.info(NEW_USER_PASSWORD + password);
			return user;
			
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
		
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH).toString();
	}



	// validateNewUsernameAndEmail() est appelé par validateNewUsernameAndEmail() et register()
			// elle vérifie si les valeurs Username et Email appartiennent déjà à un utlisateur 
	
	
	
	private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail)
			throws UserNotFoundException, UsernameExistException, EmailExistException {

		User userByNewUsername = findUserByUsername(newUsername);
		User userByNewEmail = findUserByEmail(newEmail);

		if (StringUtils.isNotBlank(currentUsername)) {
			User currentUser = findUserByUsername(currentUsername);

			if (currentUser == null) {
				throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
			}

			if (userByNewUsername != null && !currentUser.getUserId().equals(userByNewUsername.getUserId())) {

				throw new UsernameExistException(USERNAME_ALREADY_EXIST);
			}

			if (userByNewEmail != null && !currentUser.getUserId().equals(userByNewEmail.getUserId())) {
				throw new EmailExistException(EMAIL_ALREADY_EXIST);
			}
			return currentUser;
		} else {

			if (userByNewUsername != null) {
				throw new UsernameExistException(USERNAME_ALREADY_EXIST + userByNewUsername);
			}

			if (userByNewEmail != null) {
				throw new EmailExistException(EMAIL_ALREADY_EXIST + currentUsername + userByNewEmail);
			}
			return null;
		}
	}
	


	@Override
	public List<User> getUsers() {
		
		return userRepository.findAll();
	}



	@Override
	public User findUserByUsername(String username) {
		
		return userRepository.findUserByUsername(username);
	}



	@Override
	public User findUserByEmail(String email) {
		
		return userRepository.findUserByEmail(email);
	}
	
	

}
