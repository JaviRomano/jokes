package com.fdez_rumi_jokes.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdez_rumi_jokes.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);	
	Optional<User> findByEmail(String email);
	void deleteByEmail(String email);
	void deleteById(Long id);
}