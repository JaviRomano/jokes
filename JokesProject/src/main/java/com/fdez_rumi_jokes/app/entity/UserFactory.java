package com.fdez_rumi_jokes.app.entity;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserFactory {
	
	private PasswordEncoder passwordEncoder;
	
	public UserFactory(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public User createAdmin(String email, String username, String password, String name, String surname, LocalDate dateOfBirth) {
		return new User(email, username, passwordEncoder.encode(password), Role.ADMIN, name, surname, dateOfBirth);
	}
	
	public User createUser(String email, String username, String password, String name, String surname, LocalDate dateOfBirth) {
		return new User(email, username, passwordEncoder.encode(password), Role.USER, name, surname, dateOfBirth);
	}

}
