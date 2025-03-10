package com.fdez_rumi_jokes.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.repository.UserRepository;
/*
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
				
		return org.springframework.security.core.userdetails.User.builder()
	            .username(user.getUsername())
	            .password(user.getPassword())
	            .roles(user.getRole().name())
	            .build();
	    }
}*/