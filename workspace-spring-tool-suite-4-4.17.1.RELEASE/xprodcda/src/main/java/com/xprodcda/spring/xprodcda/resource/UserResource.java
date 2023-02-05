package com.xprodcda.spring.xprodcda.resource;

import javax.imageio.spi.RegisterableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xprodcda.spring.xprodcda.domain.User;
import com.xprodcda.spring.xprodcda.exception.domain.EmailExistException;
import com.xprodcda.spring.xprodcda.exception.domain.ExceptionHandling;
import com.xprodcda.spring.xprodcda.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserResource extends ExceptionHandling{

	
	private IUserService userService;

	
	@Autowired
	public UserResource(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
		return new ResponseEntity<>(newUser,HttpStatus.OK);
	}
	
	
	
//	@GetMapping("/home")
//	public String showUser() throws EmailExistException {
//		//return "Application works !";
//		throw new EmailExistException("This email is already taken !");
//	}
	
}
