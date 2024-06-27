package com.example.ShoppingManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.ShoppingManagementSystem.model.Order;
import com.example.ShoppingManagementSystem.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderser;
	
	@GetMapping("/getallorders")
	public List<Order> getallorders(){
		return orderser.getallorders();
	}
	
	@GetMapping("/getorderbyid/{id}")
	public Order getorderbyid(@PathVariable(name="id") int id){
		return orderser.getorderbyid(id);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping("/addorder")
	public String addorder(@RequestBody Order order){
		return orderser.addorder(order);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping("/deleteorderbyid/{id}")
	public String deleteorderbyid(@PathVariable(name="id") int id){
		return orderser.deleteorderbyid(id);
	}

}
