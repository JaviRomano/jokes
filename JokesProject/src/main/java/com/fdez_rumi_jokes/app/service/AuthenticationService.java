package com.fdez_rumi_jokes.app.service;

import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthenticationService(UserRepository usuarioRepository, 
								 PasswordEncoder passwordEncoder) {
        this.userRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
	
	public String login(String email, String password) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if (user.isEmpty()) {
            throw new RuntimeException("No existen datos de Usuario.");
        }
		
		User person = user.get();				
		if (!passwordEncoder.matches(password, person.getPassword())) {
			throw new RuntimeException("Usuario o contraseña incorrectos");
		}
		return password;				
	}
}