package com.example.ShoppingManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShoppingManagementSystem.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	Optional<User> findByUsername(String username);

}
