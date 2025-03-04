package com.fdez_rumi_jokes.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.fdez_rumi_jokes.app.entity.Role;
import com.fdez_rumi_jokes.app.entity.User;
import com.fdez_rumi_jokes.app.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public String showUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users";
	}
	
	@GetMapping("/{id}")
	public String getUserById(@PathVariable Long id, Model model) {
		Optional<User> user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "userInfo";
	}
	
	@GetMapping("/add")
	public String addUser(Model model) {
		model.addAttribute("role", new User());
		return "addUser";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user) {
		userService.updateUser(user);
		return "redirect:/user/all";
	}
	
}
