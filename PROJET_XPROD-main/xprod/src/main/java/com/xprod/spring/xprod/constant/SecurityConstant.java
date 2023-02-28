package com.xprod.spring.xprod.constant;

public class SecurityConstant {
	
	public static final long EXPIRATION_TIME = 432_000_000; // Environ 5 jours (la valeur est en millisecondes)
	public static final String TOKEN_PREFIXE = "Bearer"; //C'est une convention de commencer par "Bearer", No futher check for verification when getting a token starting with Bearred
	public static final String JWT_TOKEN_HEADER ="Jwt-token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
	public static final String GET_ARRAYS_XPROD = "Get Arrays, XPROD"; //XPROD name of the compagny
	public static final String GET_ARRAYS_ADMINISTRATION = "User management XPROD";
	public static final String AUTHORITIES = "authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
	public static final String ACCESS_DENIED_MESSAGE = "You do not have the permission to access this page";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
//	public static final String [] PUBLIC_URLS = {"/user/login","/user/register","/user/resetpassword/**, /user/image/**"}; //URL QUE NE VOULONS PAS BLOQUER Et we allow anything after /**
	
	public static final String [] PUBLIC_URLS = {"*"} ;
	
	public static final String DEFAULT_USER_IMAGE_PATH = "/user/image/profile/temp";
	public static final String USER_ALREADY_EXIST_WITH_THIS_EMAIL = "Un utilisateur avec cette adresse mail exist déjà";
	public static final String USERNAME_ALREADY_EXIST = "L'username existe déjà ";
	public static final String FOUND_USER_BY_USERNAME = "Returning : trouver l'utilisateur par l'username : ";
	public static final String USER_NOT_FOUND_BY_USERNAME = "User not found by username";
	public static final String NEW_USER_PASSWORD = "New user password : "; 
}
