package com.xprodcda.spring.xprodcda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class XprodcdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(XprodcdaApplication.class, args);
		
	
		
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
