package com.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	@Autowired
//	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz
			//.requestMatchers("/api/addUser1").hasAnyRole("User", "Admin")
			.requestMatchers("/home", "/api/addUser1", "/error").permitAll() // allow homepage, root, and error
			.anyRequest().authenticated() // protect other endpoints
		)
		.formLogin(form -> form
			.defaultSuccessUrl("/api/dashboard", true) // redirect to root after login
			.permitAll()
		)
		.csrf(csrf -> csrf.disable());

		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.print("This method return Custom user details!");
		return new CustomUserDetailsService();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.print("Password encrpting");
		
		return new BCryptPasswordEncoder();
		
	}
	
//	 @Bean
//	   public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
//		 
//		 return http.getSharedObject(AuthenticationManagerBuilder.class)
//                 .userDetailsService(customUserDetailsService)
//                 .passwordEncoder(passwordEncoder())
//                 .and()
//                 .build();
//		 
//	 }
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
		
	}
   }


