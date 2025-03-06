package com.fdez_rumi_jokes.app.controller.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.service.UserService;

@RestController
@RequestMapping("/api/admin/users")
public class UserRestController {

	@Autowired
	private final UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> showAllUser() {
		List<User> users = userService.getAllUsers();
		return users.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(users);
	}

	@GetMapping("{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		Optional<User> user = userService.findByEmail(email);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }

	@PostMapping("/create/admin")
	public ResponseEntity<User> createAdmin(@RequestParam String email, @RequestParam String username,
			@RequestParam String password, @RequestParam String name, @RequestParam String surname,
			@RequestParam LocalDate dateOfBirth) {
		User admin = userService.createAdmin(email, username, password, name, surname, dateOfBirth);
		return ResponseEntity.ok(admin);
	}

	@PostMapping("/create/user")
	public ResponseEntity<User> createUser(@RequestParam String email, @RequestParam String username,
			@RequestParam String password, @RequestParam String name, @RequestParam String surname,
			@RequestParam LocalDate dateOfBirth) {
		User user = userService.createUser(email, username, password, name, surname, dateOfBirth);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("{email}")
	public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
		Optional<User> user = userService.findByEmail(email);
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		userService.deleteByEmail(email);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("{email}")
	public ResponseEntity<User> updateUserByEmail(@PathVariable String email, @RequestBody User updatedUser) {
		Optional<User> userOptional = userService.findByEmail(email);

		if (userOptional == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		User user = userOptional.get();
		user.setUsername(updatedUser.getUsername());
		user.setPassword(updatedUser.getPassword());
		user.setRole(updatedUser.getRole());
		user.setName(updatedUser.getName());
		user.setSurname(updatedUser.getSurname());
		user.setDateOfBirth(updatedUser.getDateOfBirth());

		User savedUser = userService.updateUser(user);
		return ResponseEntity.ok(savedUser);
	}
}
