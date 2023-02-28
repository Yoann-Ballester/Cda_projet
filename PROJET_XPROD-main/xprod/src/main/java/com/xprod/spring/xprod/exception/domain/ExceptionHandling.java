package com.xprod.spring.xprod.exception.domain;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xprod.spring.xprod.domain.HttpResponse;

import jakarta.persistence.NoResultException;



@RestControllerAdvice
public class ExceptionHandling implements ErrorController{
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private static final String ACCOUNT_LOCKED = "Ton compte à été bloqué, veuillez contacter l'administrateur";
	private static final String METHOD_IS_NOT_ALLOWED = "Cette méthode de demande n'est pas autorisée sur ce point d'accès. Please send a '%s' request"; // '%s' remplace les méthodes supportées : POST, GE, ...
	private static final String INTERNAL_SERVER_ERROR_MSG = "Une erreur s'est produite lors du traitement de la demande ";
	private static final String INCORRECT_CREDENTIALS = "Username / password erroné. Veuillez essayer de nouveau";
	private static final String ACCOUNT_DISABLED = "Votre compte a été désactivé. S'il s'agit d'une erreur, veuillez contacter l'administrateur.";
	private static final String ERROR_PROCESSING_FILE = "Une erreur s'est produite lors du traitement du fichier";
	private static final String NOT_ENOUGH_PERMISSION ="Vous n'avez pas la permission necessaire";
	private static final String ERROR_PATH= "/error";
	
	@ExceptionHandler(DisabledException.class)
	private ResponseEntity<HttpResponse> accountDisabledException(){
		return createHttpResponse(HttpStatus.BAD_REQUEST ,ACCOUNT_DISABLED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	private ResponseEntity<HttpResponse> badCredentialsException(){
		return createHttpResponse(HttpStatus.BAD_REQUEST ,INCORRECT_CREDENTIALS);
	}

	@ExceptionHandler(AccessDeniedException.class)
	private ResponseEntity<HttpResponse> accessDeniedException(){
		return createHttpResponse(HttpStatus.FORBIDDEN,NOT_ENOUGH_PERMISSION);
	}

	@ExceptionHandler(LockedException.class)
	private ResponseEntity<HttpResponse> lockedException(){
		return createHttpResponse(HttpStatus.UNAUTHORIZED ,ACCOUNT_LOCKED);
	}

	@ExceptionHandler(TokenExpiredException.class)
	private ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException exeption){
		return createHttpResponse(HttpStatus.UNAUTHORIZED ,exeption.getMessage().toUpperCase());
	}

	@ExceptionHandler(EmailExistException.class)
	private ResponseEntity<HttpResponse> emailExistException(EmailExistException exeption){
		return createHttpResponse(HttpStatus.BAD_REQUEST ,exeption.getMessage());
	}

	@ExceptionHandler(UsernameExistException.class)
	private ResponseEntity<HttpResponse> usernameExistException(UsernameExistException exeption){
		return createHttpResponse(HttpStatus.BAD_REQUEST ,exeption.getMessage());
	}

	@ExceptionHandler(EmailNotFoundException.class)
	private ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exeption){
		return createHttpResponse(HttpStatus.BAD_REQUEST ,exeption.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	private ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exeption){
		return createHttpResponse(HttpStatus.BAD_REQUEST ,exeption.getMessage());
	}
	

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	private ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exeption){
		HttpMethod supportedMethod = Objects.requireNonNull(exeption.getSupportedHttpMethods()).iterator().next();
		return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED ,String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
	}
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<HttpResponse> internalServerErrorException(Exception exeption){
		LOGGER.error(exeption.getMessage());
		return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR , INTERNAL_SERVER_ERROR_MSG);
	}
	
	@ExceptionHandler(NoResultException.class)
	private ResponseEntity<HttpResponse> notFoundException(NoResultException exeption){
		LOGGER.error(exeption.getMessage());
		return createHttpResponse(HttpStatus.NOT_FOUND , exeption.getMessage());
	}
	
	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message){
		HttpResponse httpResponse = new HttpResponse(
				httpStatus.value(),
				httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(),
				message);
		
		return new ResponseEntity<>(httpResponse, httpStatus);
		
		}
	@ExceptionHandler(IOException.class)
	private ResponseEntity<HttpResponse> IOException(IOException exeption){
		LOGGER.error(exeption.getMessage());
		return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR , INTERNAL_SERVER_ERROR_MSG);
	}
	
	@ExceptionHandler(HttpClientErrorException.NotFound.class)
	private ResponseEntity<HttpResponse> notFound404Exception(HttpStatus httpStatus, String message){
		return createHttpResponse(HttpStatus.NOT_FOUND , "Il n'y a pas de mapping pour cette URL");
	}
	
	public String getErrorPath() {
		return ERROR_PATH;
		
	}
	
}
