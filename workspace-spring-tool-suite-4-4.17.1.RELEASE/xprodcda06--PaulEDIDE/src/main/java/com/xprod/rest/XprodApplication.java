package com.xprod.rest;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.xprod.rest.constant.FileConstant.*;

@SpringBootApplication
public class XprodApplication {

	public static void main(String[] args) {
		SpringApplication.run(XprodApplication.class, args);
		new File(USER_FOLDER).mkdirs();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); 
	}

}
