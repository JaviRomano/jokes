package com.fdez_rumi_jokes.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdez_rumi_jokes.app.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/login")
	public String login() {
		String username = "user";
		List<String> roles = List.of("USER");
		return jwtService.generateToken(username, roles);        
	}
}