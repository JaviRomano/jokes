package com.fdez_rumi_jokes.app.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.service.UserService;


@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserRestController {

	@Autowired
    private UserService userService;
	
	@GetMapping("/all")
    public ResponseEntity<List<User>> showAllUser() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(users);
    }
	
	@GetMapping("{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(user);
    }
	
	@DeleteMapping("{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
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

        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
