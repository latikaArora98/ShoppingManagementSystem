package com.example.ShoppingManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShoppingManagementSystem.model.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
