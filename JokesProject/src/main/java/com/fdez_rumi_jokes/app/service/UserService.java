package com.fdez_rumi_jokes.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepository;	
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public void deleteByEmail(String email) {
		userRepository.deleteById(findByEmail(email).getId());
	}

	public User save(User user) {
		return userRepository.save(user);
	}
}
