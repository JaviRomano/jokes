package com.fdez_rumi_jokes.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	@Email
	private String email;	
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	@Size(min = 6)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
    private Role role;

	private String name;
	private String surname;
	private LocalDate dateOfBirth;

	public User(String email, String username, String password, Role role, String name, String surname,
			LocalDate dateOfBirth) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}	
}

/*  $2a$10$FwxVG51tnWJZoFjeRxH9QuPjmAsW8A8dKTW6L78Y3gB0.FnMdC3q2  */