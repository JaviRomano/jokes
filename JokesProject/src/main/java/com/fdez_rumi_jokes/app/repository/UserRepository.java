package com.fdez_rumi_jokes.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdez_rumi_jokes.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}