package com.xprodcda.spring.xprodcda.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xprodcda.spring.xprodcda.domain.User;
import com.xprodcda.spring.xprodcda.domain.UserPrincipal;
import com.xprodcda.spring.xprodcda.exception.domain.EmailExistException;
import com.xprodcda.spring.xprodcda.exception.domain.NotAnImageFileException;
import com.xprodcda.spring.xprodcda.exception.domain.UserNotFoundException;
import com.xprodcda.spring.xprodcda.exception.domain.UsernameExistException;
import com.xprodcda.spring.xprodcda.repository.IUserRepository;
import com.xprodcda.spring.xprodcda.service.EmailService;
import com.xprodcda.spring.xprodcda.service.IUserService;
import com.xprodcda.spring.xprodcda.service.LoginAttemptService;

import org.apache.commons.lang3.StringUtils;
import jakarta.transaction.Transactional;

import static com.xprodcda.spring.xprodcda.enumeration.Role.*;
import static com.xprodcda.spring.xprodcda.constant.SecurityConstant.*;


@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements IUserService, UserDetailsService {

	
	private static final String EMPTY = null;
	private LoginAttemptService loginAttemptService;
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private IUserRepository  userRepository; 
	private BCryptPasswordEncoder passwordEncoder;
	private EmailService emailService;
	
	
	
	
	public UserServiceImpl(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder,  LoginAttemptService loginAttemptService, EmailService emailService) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder= passwordEncoder;
		this.loginAttemptService = loginAttemptService;
		this.emailService = emailService;
	}
	
	





	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if(user==null) {
			LOGGER.error(NO_USER_FOUND_BY_USERNAME+username);
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME+username);
		}else {
			try {
				validateLoginAttempt(user);
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepository.save(user);
			UserPrincipal userPrincipal = new UserPrincipal(user);
			LOGGER.info(FOUND_USER_BY_USERNAME+username);
			
			return userPrincipal;
		}
				
	}


	private void validateLoginAttempt(User user) throws ExecutionException {
		if(user.isNotLocked()) {
			if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
				user.setNotLocked(false);
			}else {
				user.setNotLocked(true);
			}
		}else {
			loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
		}
		
	}







	// Ajoute ??galement un objet utilisateur dans la base de donn??es, r??server au front office elle est destin??e 
			// ?? l'ajout d'un utilisateur lorsqu'un utilisateur cr???? un compte dans l'application
	
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
			
			
			emailService.sendNewPasswordEmail(firstname, password, email);
			
			return user;
			
		} catch (UserNotFoundException | UsernameExistException | EmailExistException | MessagingException e) {
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



	// validateNewUsernameAndEmail() est appel?? par validateNewUsernameAndEmail() et register()
			// elle v??rifie si les valeurs Username et Email appartiennent d??j?? ?? un utlisateur 
	
	
	
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


	public User addNewUser(String firstname, String lastname, String username, String email, String role,
            boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws NotAnImageFileException {
        try {
            validateNewUsernameAndEmail(EMPTY, username, email);
            User user = new User();

            String password = generatePassword();
            String encodedPassword = encodePassword(password);
            user.setUserId(generateUserId());
            user.setUserId(username);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setUsername(username);
            user.setEmail(email);
            user.setJoinDate(new Date());
            user.setPassword(encodedPassword);
            user.setActive(isActive);
            user.setNotLocked(isNonLocked);
            user.setRole(getRoleEnumName(role).name());
            user.setAuthorities(getRoleEnumName(role).getAuthorities());
            user.setProfileImageURL(setProfileImageUrl(username));

            /*
             * user.setProfileImageURL(getTemporaryProfileImageURL(username));
             */
            userRepository.save(user);
            LOGGER.info(NEW_USER_PASSWORD + password);
            saveProfileImage(user, profileImage);
            System.out.println(" ALERTE authorities : " + getRoleEnumName(role).getAuthorities());

            return user;
        } catch (UserNotFoundException | UsernameExistException | EmailExistException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


	@Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);

    }


	@Override
	public User updateUser(String currentUsername, String firstName, String lastName, String username, String email,
			String role, boolean parseBoolean, boolean parseBoolean2, MultipartFile profileImage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
