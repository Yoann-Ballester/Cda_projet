package com.xprod.spring.xprod.service.impl;


import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.xprod.spring.xprod.domain.User;
import com.xprod.spring.xprod.domain.UserPrincipal;
import com.xprod.spring.xprod.exception.domain.EmailExistException;
import com.xprod.spring.xprod.exception.domain.UserNotFoundException;
import com.xprod.spring.xprod.exception.domain.UsernameExistException;
import com.xprod.spring.xprod.repository.IUserRepository;
import com.xprod.spring.xprod.service.EmailService;
import com.xprod.spring.xprod.service.IUserService;
import com.xprod.spring.xprod.service.LoginAttemptService;

import jakarta.transaction.Transactional;

import static com.xprod.spring.xprod.enumeration.Role.*;

import static com.xprod.spring.xprod.constant.SecurityConstant.*;


@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements IUserService, UserDetailsService{
	


	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private IUserRepository iUserRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private LoginAttemptService loginAttemptService;
	private EmailService emailService;
	

	@Autowired
	public UserServiceImpl(IUserRepository iUserRepository, BCryptPasswordEncoder passwordEncoder,
			LoginAttemptService loginAttemptService, EmailService emailService) {
		super();
		this.iUserRepository = iUserRepository;
		this.passwordEncoder = passwordEncoder;
		this.loginAttemptService = loginAttemptService;
		this.emailService = emailService;
	}
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user  = iUserRepository.findUserByUsername(username); 
		if (user == null) {
			LOGGER.error(USER_NOT_FOUND_BY_USERNAME + username);
			throw new UsernameNotFoundException(USER_NOT_FOUND_BY_USERNAME + username);
			
		}else {
			try {
				validateLoginAttempt(user);
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			iUserRepository.save(user);
			UserPrincipal userPrincipal = new UserPrincipal(user);
			LOGGER.info(FOUND_USER_BY_USERNAME + username);
			return userPrincipal;
	
		}
		
	}
	private void validateLoginAttempt(User user) throws ExecutionException {
		if (user.isNotLocked()) {
			if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
				
				user.setNotLocked(false);
				
			} else {
				user.setNotLocked(true);
			}
			
		} else {
			loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
		}
	}

	// Ajoute également un objet utilisateur dans la base de données, réserver au front office elle est destinée 
	// à l'ajout d'un utilisateur lorsqu'un utilisateur créé un compte dans l'application
	@Override
	public User register(String firstName, String lastName, String username, String email) {
		try {
			validatedNewUsernameAndEmail(StringUtils.EMPTY, username,email);
			
			User user = new User();
			
			user.setIdUser(generateUserId());
			String password = generatePassword();
			String encodedPassword = encodePassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUsername(username);
			user.setEmail(email);
			user.setJoinDate(new Date());
			user.setPassword(encodedPassword);
			user.setActive(true);
			user.setNotLocked(true);
			user.setRole(ROLE_USER.name());
			user.setAuthorities(ROLE_USER.getAuthorities());
			user.setProfileImageURL(getTemporaryProfilImageUrl());			
			iUserRepository.save(user);
			LOGGER.info(NEW_USER_PASSWORD+password);
			emailService.sendNewPasswordEmail(firstName, password, email);
			
		} catch (UserNotFoundException | UsernameExistException |EmailExistException  | MessagingException e) {
			
			e.printStackTrace();
		
		}
		return null;
	}

	private String getTemporaryProfilImageUrl() {
		
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH).toString();
	}

	private String encodePassword(String password) {
		
		return passwordEncoder.encode(password);
	}

	private String generatePassword() {
		
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private String generateUserId() {
		
		return RandomStringUtils.randomNumeric(10);
	}

	// validateNewUsernameAndEmail() est appelé par validateNewUsernameAndEmail() et register()
			// elle vérifie si les valeurs Username et Email appartiennent déjà à un utlisateur 
	private User validatedNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail)
			throws UserNotFoundException, UsernameExistException, EmailExistException {

		User userByNewUsername = findUserByUsername(newUsername);
		User userByNewEmail = findUserByEmail(newEmail);

		if (StringUtils.isNotBlank(currentUsername)) {
			User currentUser = findUserByUsername(currentUsername);

			if (currentUser == null) {
				throw new UserNotFoundException(USER_NOT_FOUND_BY_USERNAME + currentUsername);
			}

			if (userByNewUsername != null && !currentUser.getIdUser().equals(userByNewUsername.getIdUser())) {

				throw new UsernameExistException(USERNAME_ALREADY_EXIST);
			}

			if (userByNewEmail != null && !currentUser.getIdUser().equals(userByNewEmail.getIdUser())) {
				throw new EmailExistException(USER_ALREADY_EXIST_WITH_THIS_EMAIL);
			}
			return currentUser;
		} else {

			if (userByNewUsername != null) {
				throw new UsernameExistException(USERNAME_ALREADY_EXIST + userByNewUsername);
			}

			if (userByNewEmail != null) {
				throw new EmailExistException(USER_ALREADY_EXIST_WITH_THIS_EMAIL + currentUsername + userByNewEmail);
			}
			return null;
		}
	}



	@Override
	public List<User> getUsers() {
		return  iUserRepository.findAll();
	}



	@Override
	public User findUserByUsername(String username) {
		return iUserRepository.findUserByUsername(username);
	}



	@Override
	public User findUserByEmail(String email) {
		return iUserRepository.findUserByEmail(email);
	}
	
	
	
	

}
