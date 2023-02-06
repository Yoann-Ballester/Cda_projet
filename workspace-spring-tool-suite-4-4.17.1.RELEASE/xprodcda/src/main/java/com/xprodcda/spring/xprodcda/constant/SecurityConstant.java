package com.xprodcda.spring.xprodcda.constant;

public class SecurityConstant {

	
	public static final long EXPIRATION_TIME = 432_000_000; // 5 jours en millisecondes 	
	public static final String TOKEN_PREFIX = "Bearer"; // no further check if "Bearer" is not present at  the start of the token
	public static final String JWT_TOKEN_HEADER="Jwt-token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
	public static final String GET_ARRAYS_LLC = "Get Arrays, XPROD"; // LLC  name of the company
	public static final String GET_ARRAYS_ADMINISTRATION = "User Management XPROD";
	public static final String AUTHORITIES = "authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
	public static final String ACCES_DENIES_MESSAGE = "You do not have the permission to access this page";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
	public static final String[] PUBLIC_URLS = {"*"};
	public static final String FOUND_USER_BY_USERNAME = "Returning found by username: ";
	public static final String DEFAULT_USER_IMAGE_PATH = "/user/image/profile/temp";	
	public static final String EMAIL_ALREADY_EXIST = "Email already exist";
	public static final String USERNAME_ALREADY_EXIST = "Username already exist";
	public static final String NO_USER_FOUND_BY_USERNAME = "User not found ";
	public static final String NEW_USER_PASSWORD = "New user password : ";
	//public static final String[] PUBLIC_URLS = {"/user/login","user/register","/user/resetpassword/**","/user/image/**"}; // URL qui ne sont pas bloqué
	
}