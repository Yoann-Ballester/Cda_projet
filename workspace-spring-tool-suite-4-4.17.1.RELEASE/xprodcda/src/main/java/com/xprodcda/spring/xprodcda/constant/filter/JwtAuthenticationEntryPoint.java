package com.xprodcda.spring.xprodcda.constant.filter;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xprodcda.spring.xprodcda.constant.SecurityConstant;
import com.xprodcda.spring.xprodcda.domain.HttpResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpStatus.FORBIDDEN;


@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint{
	public void commence(HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		// je cree mon objet response de mon instance httpresponse
		HttpResponse httpResponse = new HttpResponse(HttpStatus.FORBIDDEN.value(),
													 HttpStatus.FORBIDDEN,
													 HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(),
													 SecurityConstant.FORBIDDEN_MESSAGE
													 );
		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(FORBIDDEN.value());
		
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, httpResponse);
		outputStream.flush(); // Stream end to free memory
				
	}
	

}
