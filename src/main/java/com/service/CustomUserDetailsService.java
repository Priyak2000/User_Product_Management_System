package com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dao.UserRepository;


@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.enitity.User user =userRepository.findByUserName(userName)
				.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
		System.out.println("Trying to load user: " + userName);
//		com.enitity.User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User name not found!"));
//		 return User.withUsername(user.getUserName())   // your entity's userName
////	            .password(user.getPassWord()) 
////	            .roles("User")
////	            .roles("Admin")
//	            // your entity's passWord
//	                       // Hardcoding ROLE_USER for now
//	         
//	            .build();
		return new User(user.getUserName(), user.getPassWord(), Collections.singleton(new SimpleGrantedAuthority("USER_ROLE")));
		
		//return  new org.springframework.security.core.userdetails.User(user.getPassWord(),user.getPassWord(),new ArrayList<>());
	}

}
