package com.xprodcda.spring.xprodcda.constant.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.xprodcda.spring.xprodcda.utility.JWTTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static com.xprodcda.spring.xprodcda.constant.SecurityConstant.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
 
public class JwtAuthorizationFilter extends OncePerRequestFilter{
	
	private JWTTokenProvider jwtTokenProvider;
	
	
	

	public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
	}




	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
		response.setStatus(OK.value());
		
		}else {
		    String authorizationHeader	= request.getHeader(AUTHORIZATION);
		
		
		if (authorizationHeader==null || authorizationHeader.startsWith(TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return; // empty return stop execution
		}	
		String token = authorizationHeader.substring(TOKEN_PREFIX.length()); // we remove the word bearer
		String username = jwtTokenProvider.getSubject(token);
		if(jwtTokenProvider.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication()==null) {
			List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
			Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}else {
			SecurityContextHolder.clearContext();
		}
		}
		filterChain.doFilter(request, response);
	}

	
	
}
