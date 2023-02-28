package com.xprod.spring.xprod.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.xprod.spring.xprod.domain.UserPrincipal;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

import static com.xprod.spring.xprod.constant.SecurityConstant.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


@Component

public class JWTTokenProvider {
	
	@Value("${jwt.secret}") // Comes from application.properties
	private String secret;
	
	
	public String generateJwtToken(UserPrincipal userPrincipal) {
		String[] claims = getClaimsFromUser(userPrincipal);
		return JWT.create().withIssuer(GET_ARRAYS_XPROD).withAudience(GET_ARRAYS_ADMINISTRATION)
				.withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
				.withArrayClaim(AUTHORITIES,claims).withExpiresAt(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
				.sign(HMAC512(secret.getBytes()));
		
	}
	
	public List<GrantedAuthority>getAuthorities(String token){
		String[] claims = getClaimsFromToken(token);
		return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
	}


	private String[] getClaimsFromToken(String token) {
		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
	}

	

	private JWTVerifier getJWTVerifier() {
		JWTVerifier verifier;
		try {
			Algorithm algorithm = HMAC512(secret);
			verifier = JWT.require(algorithm).withIssuer(GET_ARRAYS_XPROD).build();
		} catch (JWTVerificationException e) {
			throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
		}
		
		return verifier;
	}

	private String[] getClaimsFromUser(UserPrincipal user) {
		List<String> authorities = new ArrayList<>();
		for(GrantedAuthority grantedAuthority : user.getAuthorities()) {
			authorities.add(grantedAuthority.getAuthority());
		}
		
		return authorities.toArray(new String[0]);
	}
	
	// Récupére les authentification when we verify the token
	public Authentication getAuthentification(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		return usernamePasswordAuthenticationToken;
	}
	
	public boolean isTokenValid(String username, String token) {
		JWTVerifier verifier = getJWTVerifier();
		return StringUtils.isNotEmpty(username)&& !isTokenExpired(verifier, token);
	}
	 // Nous vérifions la  date d'expiration du token 
	private boolean isTokenExpired(JWTVerifier verifier, String token) {
		Date expiration = verifier.verify(token).getExpiresAt();
		return expiration.before(new Date());
	}
	
	// On verifie que l'utilisateur à le bon token
	public String getSubject(String token) {
		JWTVerifier verifier  = getJWTVerifier();
		return verifier.verify(token).getSubject();
		
	}

}
