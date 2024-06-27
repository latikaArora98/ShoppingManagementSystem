package com.example.ShoppingManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ShoppingManagementSystem.model.Order;
import com.example.ShoppingManagementSystem.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderrepo;

	public List<Order> getallorders() {
		// TODO Auto-generated method stub
		return orderrepo.findAll();
	}

	public Order getorderbyid(int id) {
		// TODO Auto-generated method stub
		return orderrepo.findById(id).get();
	}

	public String addorder(Order order) {
		// TODO Auto-generated method stub
		orderrepo.save(order);
		return "Order added successfully!!";
	}

	public String deleteorderbyid(int id) {
		// TODO Auto-generated method stub
		orderrepo.deleteById(id);
		return "Order deleted successfully!!";
	}

}
