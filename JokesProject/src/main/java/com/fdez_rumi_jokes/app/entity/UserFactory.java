package com.fdez_rumi_jokes.app.entity;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserFactory {
	
	private PasswordEncoder passwordEncoder;
	
	public UserFactory(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public User createAdmin(String email, String username, String password, String name, String surname, LocalDate dateOfBirth) {
		return createUserParams(email, username, password, Role.ADMIN, name, surname, dateOfBirth);
	}
	
	public User createUser(String email, String username, String password, String name, String surname, LocalDate dateOfBirth) {
		return createUserParams(email, username, password, Role.USER, name, surname, dateOfBirth);
	}
	
	private User createUserParams(String email, String username, String password, Role role, String name, String surname, LocalDate dateOfBirth) {
		Assert.hasText(email, "El campo email no puede estar vacío");
		Assert.hasText(username, "El campo username no puede estar vacío");
		Assert.hasText(password, "El campo password no puede estar vacío");
		Assert.hasText(name, "El campo name no puede estar vacío");
		Assert.hasText(surname, "Surname no puede estar vacío");
        Assert.notNull(dateOfBirth, "Date of Birth no puede ser nulo");
        
        String encodedPassword = passwordEncoder.encode(password);
        
        return new User(email, username, encodedPassword, role, name, surname, dateOfBirth);		
	}
}