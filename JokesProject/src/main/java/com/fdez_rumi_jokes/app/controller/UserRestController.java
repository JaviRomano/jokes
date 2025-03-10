package com.fdez_rumi_jokes.app.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<List<User>> showAllUser() {
		List<User> users = userService.getAllUsers();
		return users.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		Optional<User> user = userService.getUserByEmail(email);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping("/create/admin")
	public ResponseEntity<User> createAdmin(@RequestBody User user) {
		User admin = userService.createAdmin(user.getEmail(), user.getUsername(), user.getPassword(), user.getName(),
				user.getSurname(), user.getDateOfBirth());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(admin.getId())
				.toUri();
		return ResponseEntity.created(location).body(admin);
	}

	@PostMapping("/create/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User userBase = userService.createUser(user.getEmail(), user.getUsername(), user.getPassword(), user.getName(),
				user.getSurname(), user.getDateOfBirth());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(userBase.getId())
				.toUri();
		return ResponseEntity.created(location).body(userBase);
	}

	@DeleteMapping("/email/{email}")
	public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
		boolean isDeleted = userService.deleteUserByEmail(email);
		return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	@PutMapping("/update/{username}")
	public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User updatedUser) {
		return userService.updateUser(username, updatedUser)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
