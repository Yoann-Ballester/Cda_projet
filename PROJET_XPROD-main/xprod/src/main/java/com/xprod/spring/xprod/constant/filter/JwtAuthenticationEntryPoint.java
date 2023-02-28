package com.xprod.spring.xprod.constant.filter;

import java.io.IOException;
import java.io.OutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xprod.spring.xprod.constant.SecurityConstant;
import com.xprod.spring.xprod.domain.HttpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpStatus.FORBIDDEN;

//Je cree mon objet response de l instance Http Response
@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {
	@Override
	public void commence(HttpServletRequest request,
						HttpServletResponse response,
						AuthenticationException arg2)
						throws IOException {
		HttpResponse httpResponse = new HttpResponse(
				HttpStatus.FORBIDDEN.value(),
				HttpStatus.FORBIDDEN,
				HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(),
				SecurityConstant.FORBIDDEN_MESSAGE
				);
		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(FORBIDDEN.value());
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, httpResponse);
		outputStream.flush();//liberer memoire
		}
	}