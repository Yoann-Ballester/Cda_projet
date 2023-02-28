package com.xprod.spring.xprod.ressource;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xprod.spring.xprod.domain.User;
import com.xprod.spring.xprod.domain.UserPrincipal;
import com.xprod.spring.xprod.exception.domain.ExceptionHandling;
import com.xprod.spring.xprod.service.IUserService;
import com.xprod.spring.xprod.utility.JWTTokenProvider;


import static com.xprod.spring.xprod.constant.SecurityConstant.*;

@RestController
@RequestMapping(path = {"/" , "/user"})
@CrossOrigin(origins ="http://localhost:4200")
public class UserResource extends ExceptionHandling{
	@Autowired
	private final IUserService iUserService;
	private AuthenticationManager authenticationManager;
	private JWTTokenProvider jwtTokenProvider;
	
	@Autowired
	public UserResource(IUserService iUserService, AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider) {
		super();
		this.iUserService = iUserService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> register(@RequestBody User user) {
		authenticate(user.getUsername(), user.getPassword());
		User loginUser = iUserService.findUserByUsername(user.getUsername());
		
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		
		HttpHeaders jwtHeaders = getJwtHeader(userPrincipal);
		
		return new ResponseEntity<>(loginUser,jwtHeaders, HttpStatus.OK);
		
	}
	
	private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
		return headers;
	}

	private void authenticate(String username, String password) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
	}

	@PostMapping("/register")
	public ResponseEntity<User> login(@RequestBody User user) {
		
		User newUser = iUserService.register(user.getFirstName(),user.getLastName(), user.getUsername(), user.getEmail());
		
		return new ResponseEntity<>(newUser, HttpStatus.OK);
		
	}
	
	

	@GetMapping("/register")
	public ResponseEntity<List<User>>getUser() {
		return ResponseEntity.ok(iUserService.getUsers());
	}
	
	

	
	
	
	
//	@GetMapping("/home")
//	public String showUser() throws EmailExistException{
////		return "application works !" ;
//		throw new EmailExistException("Cette adresse email est déjà prise ! ");
//	}
	
	

}
