package com.webapp.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.user.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	Optional<User> findByEmailId(String emailId);
}
