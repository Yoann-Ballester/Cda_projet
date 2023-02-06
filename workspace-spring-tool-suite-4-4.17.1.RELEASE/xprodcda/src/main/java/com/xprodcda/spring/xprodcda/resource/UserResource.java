package com.xprodcda.spring.xprodcda.resource;


import java.util.List;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.xprodcda.spring.xprodcda.domain.User;
import com.xprodcda.spring.xprodcda.domain.UserPrincipal;
import com.xprodcda.spring.xprodcda.exception.domain.EmailExistException;
import com.xprodcda.spring.xprodcda.exception.domain.ExceptionHandling;
import com.xprodcda.spring.xprodcda.service.IUserService;
import com.xprodcda.spring.xprodcda.utility.JWTTokenProvider;

import lombok.RequiredArgsConstructor;

import static com.xprodcda.spring.xprodcda.constant.SecurityConstant.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserResource extends ExceptionHandling{

	@Autowired
	private final IUserService userService;
	private AuthenticationManager authenticationManager;
	private JWTTokenProvider jwtTokenProvider;
	
	@Autowired	
	public UserResource(IUserService userService, AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider) {
		super();
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}


	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		authenticate(user.getUsername(),user.getPassword());
		User loginUser = userService.findUserByUsername(user.getUsername());
		
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		return new ResponseEntity<>(loginUser,jwtHeader, HttpStatus.OK);
	}
	
	
	private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
		return httpHeaders;
	}



	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
	}



	@GetMapping("/register")
	//@getMapping
	public ResponseEntity<List<User>>getUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
		return new ResponseEntity<>(newUser,HttpStatus.OK);
	}
	
	
	/*	@GetMapping("/home")
	public String showUser() throws EmailExistException {
		//return "application works !";
		throw new EmailExistException("This email is already taken !");
	}
	*/
	
}
