package com.xprodcda.spring.xprodcda.exception.domain;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Objects;

import javax.security.auth.login.AccountLockedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xprodcda.spring.xprodcda.domain.HttpResponse;

import jakarta.persistence.NoResultException;



@RestControllerAdvice
public class ExceptionHandling implements ErrorController{

	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private static final String ACCOUNT_LOCKED="Your account has been locked. Please contact administration";
	private static final String METHOD_IS_NOT_ALLOWED="This request method is not allowed on this endpoint. Please send a '%s' request"; // '%s' remplace les methodes supportées : POST, GET, PUT,DELETE....
	private static final String INTERNAL_SERVER_ERROR= "An error occured while processing the request";
	private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
	private static final String ACCOUNT_DISABLED = "Your account has been disabled. PLease contact administration for more information";
	private static final String ERROR_PROCESSING_FILE = "Error occured while processing file";
	private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
	private static final String ERROR_PATH ="/error";
	
	@ExceptionHandler(DisabledException.class)
	private ResponseEntity<HttpResponse> accountDisabledException(){
		return createHttpResponse(HttpStatus.BAD_REQUEST,ACCOUNT_DISABLED);
	}
	
	@ExceptionHandler(AccountLockedException.class)
	private ResponseEntity<HttpResponse> accountLocked(){
		return createHttpResponse(HttpStatus.UNAUTHORIZED,ACCOUNT_LOCKED);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	private ResponseEntity<HttpResponse> badCredentialsException(){
		return createHttpResponse(HttpStatus.BAD_REQUEST,INCORRECT_CREDENTIALS);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	private ResponseEntity<HttpResponse> accessDeniedException(){
		return createHttpResponse(HttpStatus.FORBIDDEN,NOT_ENOUGH_PERMISSION);
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	private ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException e){
		return createHttpResponse(HttpStatus.UNAUTHORIZED,e.getMessage().toUpperCase());
	}
	
	@ExceptionHandler(EmailExistException.class)
	private ResponseEntity<HttpResponse> emailExistException(EmailExistException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST,e.getMessage());
	}
	
	@ExceptionHandler(UsernameExistException.class)
	private ResponseEntity<HttpResponse> usernameExistException(UsernameExistException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST,e.getMessage());
	}
	
	@ExceptionHandler(EmailNotFoundException.class)
	private ResponseEntity<HttpResponse> EmailNotFoundException(EmailNotFoundException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST,e.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	private ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST,e.getMessage());
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	private ResponseEntity<HttpResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
		HttpMethod supportedMethod = Objects.requireNonNull(e.getSupportedHttpMethods()).iterator().next();
		return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED,String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
	}
	
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<HttpResponse> internalServerErrorException(Exception e){
		LOGGER.error(e.getMessage());
		return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoResultException.class)
	private ResponseEntity<HttpResponse> noResultException(NoResultException e){
		LOGGER.error(e.getMessage());
		return createHttpResponse(HttpStatus.NOT_FOUND,e.getMessage());
	}
	
	@ExceptionHandler(IOException.class)
	private ResponseEntity<HttpResponse> iOexception(IOException e){
		LOGGER.error(e.getMessage());
		return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR,INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFound.class)
	private ResponseEntity<HttpResponse> notFound404(HttpStatus httpStatus, String message){
		
		return createHttpResponse(HttpStatus.NOT_FOUND,"There is no mapping for this URL");
	}
	
	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	
	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message){
		HttpResponse httpResponse = new HttpResponse(
				httpStatus.value(),
				httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(),
				message
				);
				
		return new ResponseEntity<>(httpResponse,httpStatus);		
	}
}
