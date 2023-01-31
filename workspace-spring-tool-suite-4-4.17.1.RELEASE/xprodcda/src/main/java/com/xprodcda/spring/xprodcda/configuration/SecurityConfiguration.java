package com.xprodcda.spring.xprodcda.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.xprodcda.spring.xprodcda.constant.SecurityConstant;
import com.xprodcda.spring.xprodcda.constant.filter.JwtAccessDeniedHandler;
import com.xprodcda.spring.xprodcda.constant.filter.JwtAuthenticationEntryPoint;
import com.xprodcda.spring.xprodcda.constant.filter.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfiguration{
	
private JwtAuthorizationFilter jwtAuthorizationFilter;
private JwtAccessDeniedHandler jwtAccessDeniedHandler;
private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
private UserDetailsService userDetailsService;

private BCryptPasswordEncoder bCryptPasswordEncoder;

@Autowired
public SecurityConfiguration(JwtAuthorizationFilter jwtAuthorizationFilter,
		JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
		UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
	super();
	this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	this.userDetailsService = userDetailsService;
	this.bCryptPasswordEncoder = bCryptPasswordEncoder;
}

protected void configure(AuthenticationManagerBuilder auth)throws Exception{
	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
}

protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().cors()
	.and()
	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and()
	/*.authorizeRequests()*/
	.authorizeHttpRequests().requestMatchers(SecurityConstant.PUBLIC_URLS).permitAll()
	.anyRequest().authenticated()
	.and()
	.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
	.authenticationEntryPoint(jwtAuthenticationEntryPoint)
	.and()
	.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
}

/* Si Adapter (qui est déprécié)
@Override
@Bean
public AuthenticationManager authenticationManagerBean() throws Exception{
	return super.authenticationManagerBean();}
*/

}
