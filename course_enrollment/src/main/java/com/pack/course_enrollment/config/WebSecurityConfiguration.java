package com.pack.course_enrollment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Autowired
	public AuthenticationProvider authenticationProvider;
	@Autowired
	public JwtAuthenticationFilter jwtAuthFilter;

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				.csrf(AbstractHttpConfigurer::disable)
//				.authorizeHttpRequests(req -> 
//						req.requestMatchers("/trainees/**")
//						.permitAll()
//						.anyRequest()
//						.authenticated())
//				.build();
//	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
		return http .csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorizeRequests -> 
				authorizeRequests .requestMatchers("/trainees/login","/**")
				.permitAll()
				.anyRequest()
				.authenticated() ) 
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) ) 
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) 
				.build(); 
		}
	}
	
	


