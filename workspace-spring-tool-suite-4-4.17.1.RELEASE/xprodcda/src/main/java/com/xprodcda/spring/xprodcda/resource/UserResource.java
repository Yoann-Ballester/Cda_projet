package com.xprodcda.spring.xprodcda.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xprodcda.spring.xprodcda.exception.domain.EmailExistException;
import com.xprodcda.spring.xprodcda.exception.domain.ExceptionHandling;

@RestController
@RequestMapping("/user")
public class UserResource extends ExceptionHandling{

	@GetMapping("/home")
	public String showUser() throws EmailExistException {
		//return "Application works !";
		throw new EmailExistException("This email is already taken !");
	}
	
}
