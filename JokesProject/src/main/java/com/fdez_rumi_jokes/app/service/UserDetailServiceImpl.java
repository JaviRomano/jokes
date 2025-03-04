package com.fdez_rumi_jokes.app.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Usuario no encontrado [" + username + "]");
		}
		
		User userLoad = user.get();
		
		return org.springframework.security.core.userdetails.User
				.withUsername(userLoad.getUsername())
                .password(userLoad.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userLoad.getRole().name())))
                .build();
	}
}