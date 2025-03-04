package com.fdez_rumi_jokes.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.entity.UserFactory;
import com.fdez_rumi_jokes.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepository;	
	private final UserFactory userFactory;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userFactory = new UserFactory(passwordEncoder);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User createAdmin(String email, String username, String password, String name, String surname, LocalDate dateOfBirth) {
        User admin = userFactory.createAdmin(email, username, password, name, surname, dateOfBirth);
        return userRepository.save(admin);
    }

    public User createUser(String email, String username, String password, String name, String surname, LocalDate dateOfBirth) {
        User user = userFactory.createUser(email, username, password, name, surname, dateOfBirth);
        return userRepository.save(user);
    }
	public void deleteByEmail(String email) {
		userRepository.deleteByEmail(email);
	}


	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
