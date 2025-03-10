package com.fdez_rumi_jokes.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.entity.UserFactory;
import com.fdez_rumi_jokes.app.exception.DataIntegrityException;
import com.fdez_rumi_jokes.app.repository.UserRepository;

import jakarta.transaction.Transactional;

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

	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User createAdmin(String email, String username, String password, String name, String surname,
			LocalDate dateOfBirth) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new DataIntegrityException("El email ya está en uso.");
		}
		if (userRepository.findByUsername(username).isPresent()) {
			throw new DataIntegrityException("El username ya está en uso.");
		}
		try {
			User admin = userFactory.createAdmin(email, username, password, name, surname, dateOfBirth);
			return userRepository.save(admin);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Error de integridad de datos.", ex);
		}
	}

	public User createUser(String email, String username, String password, String name, String surname,
			LocalDate dateOfBirth) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new DataIntegrityException("El email ya está en uso.");
		}
		if (userRepository.findByUsername(username).isPresent()) {
			throw new DataIntegrityException("El username ya está en uso.");
		}
		try {
			User user = userFactory.createUser(email, username, password, name, surname, dateOfBirth);
			return userRepository.save(user);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Error de integridad de datos.", ex);
		}
	}

	@Transactional
	public Optional<User> updateUser(String username, User updateUser) {
		return userRepository.findByUsername(username).map(existingUser -> {
			updateUserFields(existingUser, updateUser);
			return userRepository.save(existingUser);
		});
	}

	@Transactional
	private void updateUserFields(User existingUser, User updatedUser) {
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setUsername(updatedUser.getUsername());
		existingUser.setPassword(updatedUser.getPassword());
		existingUser.setRole(updatedUser.getRole());
		existingUser.setName(updatedUser.getName());
		existingUser.setSurname(updatedUser.getSurname());
		existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
	}

	@Transactional
	public boolean deleteUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			userRepository.delete(user.get());
			return true;
		}
		return false;
	}
}