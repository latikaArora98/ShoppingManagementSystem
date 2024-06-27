package com.example.ShoppingManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShoppingManagementSystem.model.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
