package com.xprod.rest.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xprod.rest.entity.User;
import com.xprod.rest.entity.UserPrincipal;
import com.xprod.rest.enumeration.Role;
import com.xprod.rest.exception.domain.EmailExistException;
import com.xprod.rest.exception.domain.EmailNotFoundException;
import com.xprod.rest.exception.domain.NotAnImageFileException;
import com.xprod.rest.exception.domain.UserNotFoundException;
import com.xprod.rest.exception.domain.UsernameExistException;
import com.xprod.rest.repository.UserRepository;
import com.xprod.rest.service.LoginAttemptService;
import com.xprod.rest.service.UserService;

import static com.xprod.rest.constant.UserImplConstant.NEW_USER_PASSWORD;
import static com.xprod.rest.constant.FileConstant.*;
import static com.xprod.rest.constant.UserImplConstant.*;
import static com.xprod.rest.enumeration.Role.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.http.MediaType.*;

@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	
	@Autowired // permet d'activer l'injection automatique de d??pendance
	private UserRepository iUserRepository;
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private BCryptPasswordEncoder passwordEncoder;
	private LoginAttemptService loginAttemptService;

	@Autowired
	public UserServiceImpl(UserRepository iUserRepository, BCryptPasswordEncoder passwordEncoder,
			LoginAttemptService loginAttemptService) {
		super();
		this.iUserRepository = iUserRepository;
		this.passwordEncoder = passwordEncoder;
		this.loginAttemptService = loginAttemptService;

	}
	
	// Ajoute un objet utilisateur dans la base de donn??es, r??server au back office elle est destin??e ?? l'ajout d'un
	// utilisateur ex??cut?? par un administrateur de l'application
	public User addNewUser(String firstname, String lastname, String username, String email, String role,
			boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws NotAnImageFileException {
		try {
			validateNewUsernameAndEmail(EMPTY, username, email);
			User user = new User();

			String password = generatePassword();
			String encodedPassword = encodePassword(password);
			user.setUserId(generateUserId());
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setUsername(username);
			user.setEmail(email);
			user.setJoinDate(new Date());
			user.setPassword(encodedPassword);
			user.setActive(isActive);
			user.setNotLocked(isNonLocked);
			user.setRole(getRoleEnumName(role).name());
			user.setAuthorities(getRoleEnumName(role).getAuthorities());
			user.setProfileImageURL(setProfileImageUrl(username));
	
			iUserRepository.save(user);
			LOGGER.info(NEW_USER_PASSWORD + password);
			saveProfileImage(user, profileImage);

			return user;
		} catch (UserNotFoundException | UsernameExistException | EmailExistException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Ajoute ??galement un objet utilisateur dans la base de donn??es, r??server au front office elle est destin??e 
	// ?? l'ajout d'un utilisateur lorsqu'un utilisateur cr???? un compte dans l'application
	@Override
	public User register(String firstname, String lastname, String username, String email) {
		try {
			validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
			User user = new User();
			String password = generatePassword();
			String encodedPassword = encodePassword(password);
			user.setUserId(generateUserId());			
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setUsername(username);
			user.setEmail(email);
			user.setJoinDate(new Date());
			user.setPassword(encodedPassword);
			user.setActive(true);
			user.setNotLocked(true);
			user.setRole(ROLE_USER.name());
			user.setAuthorities(ROLE_USER.getAuthorities());
			user.setProfileImageURL(setProfileImageUrl(username));
			iUserRepository.save(user);
			LOGGER.info(NEW_USER_PASSWORD + password);
			return user;

		} catch (UserNotFoundException | UsernameExistException | EmailExistException  e) {
			e.printStackTrace();
		}
		return null;
	}

	// saveProfileImage() est appel??e par addNewUser() elle permet la cr??ation d'une image de profile
	private void saveProfileImage(User user, MultipartFile profileImage) throws IOException,NotAnImageFileException {
		if (profileImage != null) {
			if(!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(profileImage.getContentType())) {
				throw new NotAnImageFileException(profileImage.getOriginalFilename()+ NOT_AN_IMAGE_FILE);
			}
			
			Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
			
			if (!Files.exists(userFolder)) {
				Files.createDirectories(userFolder);
				LOGGER.info(DIRECTORY_CREATED);
			}
			Files.deleteIfExists(Paths.get(userFolder+user.getUsername()+ DOT +JPG_EXTENSION));
			Files.copy(profileImage.getInputStream(),userFolder.resolve(user.getUsername()+ DOT+JPG_EXTENSION),REPLACE_EXISTING);
			user.setProfileImageURL(setProfileImageUrl(user.getUsername()));
			iUserRepository.save(user);
			LOGGER.info(FILE_SAVED_IN_SYSTEM +profileImage.getOriginalFilename());
		}

	}

	// setProfileImageUrl() est appel??e par register() elle permet la cr??ation d'un avatar pour l'image de profile de 
	//l'utilisateur
	private String setProfileImageUrl(String username) {

        return TEMP_PROFILE_IMAGE_BASE_URL + FORWARD_SLASH + username;
  }

	// Enum??ration d'une liste de r??les pour d??terminer le(s) droit(s) d'un l'utilisateur dans l'application 
	// Chaque r??le peut contenir un ou plusieurs droit(s)(create, read, update, delete)
	private Role getRoleEnumName(String role) {
		return Role.valueOf(role);
	}

	// loadUserByUsername() cette m??thode cherche un utilisateur en faisant et ex??cute des actions en foction du r??sultat 
	//retourn??
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = iUserRepository.findUserByUsername(username);

		if (user == null) {
			LOGGER.error(USER_NOT_FOUND_BY_USERNAME + username);
			throw new UsernameNotFoundException(USER_NOT_FOUND_BY_USERNAME + username);
		} else {
			try {
				validateLoginAttempt(user);
				user.setLastLoginDateDisplay(user.getLastLoginDate());
				user.setLastLoginDate(new Date());
				iUserRepository.save(user);
				UserPrincipal userPrincipal = new UserPrincipal(user);
				return userPrincipal;
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	// getUsers() renvoie dans une liste tous les objets User utilisateurs enregistr??s dans la base de donn??es
	@Override
	public List<User> getUsers() {
		return iUserRepository.findAll();
	}

	// findUserByEmail() cette m??thode trouve un utilisateur en le recherchant par son email
	@Override
	public User findUserByEmail(String email) {
		return iUserRepository.findUserByEmail(email);
	}
	
	// findUserByUsername() cette m??thode trouve un utilisateur en le recherchant par : username
	@Override
	public User findUserByUsername(String username) {
		return iUserRepository.findUserByUsername(username);
	}

	// updateUser met ?? jour les donn??es d'un utilisateur
	@Override
	public User updateUser(String currentUsername, String newFirstname, String newLastname, String newUsername,
			String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) 
			throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException {
		
			User currentUser = validateNewUsernameAndEmail(currentUsername, newUsername, newEmail);

			currentUser.setFirstname(newFirstname);
			currentUser.setLastname(newLastname);
			currentUser.setUsername(newUsername);
			currentUser.setEmail(newEmail);
			currentUser.setActive(isActive);
			currentUser.setNotLocked(isNonLocked);
			currentUser.setRole(getRoleEnumName(role).name());
			currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());

			iUserRepository.save(currentUser);
			saveProfileImage(currentUser, profileImage);
			
			System.out.println("Lett username :"+newUsername );

			return currentUser;
		
	}

	// deleteUser() supprime un objet User en le ciblant via son ID
	@Override
	public void deleteUser(long id) {

		iUserRepository.deleteById(id);
	}
	
	// Cette m??thode permet de r??initialiser le mot de passe de l'utilisateur
	@Override
	public void resetPassword(String email) throws EmailNotFoundException, MessagingException {
		User user = iUserRepository.findUserByEmail(email);
		if (user == null) {
			throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL);
		}
		String password = generatePassword();
		user.setPassword(encodePassword(password));
		iUserRepository.save(user);
	}

	// updateProfileImage() met ?? jour l'image de profile
	@Override
	public User updateProfileImage(String username, MultipartFile profileImage) throws NotAnImageFileException {
		try {
			User user = validateNewUsernameAndEmail(username, null, null);
			saveProfileImage(user, profileImage);
			return user;
		} catch (UserNotFoundException | UsernameExistException | EmailExistException | IOException e) {
		}
		return null;
	}

	// encodePassword() encode le mot de passe de l'utilisateur
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	// generatePassword() g??n??re un mot de passe ?? l'utilisateur
	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	// generateUserId() g??n??re un uid 
	private String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
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

			if (userByNewUsername != null && !currentUser.getUid().equals(userByNewUsername.getUid())) {

				throw new UsernameExistException(USERNAME_ALREADY_EXIST);
			}

			if (userByNewEmail != null && !currentUser.getUid().equals(userByNewEmail.getUid())) {
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

	// validateLoginAttempt() block un utilisateur si celui-ci ?? ex??cut?? trop de tentatives de connexion 
	//avec un mauvais mot de passe
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
}
