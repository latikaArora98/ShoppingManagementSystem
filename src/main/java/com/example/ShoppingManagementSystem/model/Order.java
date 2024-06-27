package com.example.ShoppingManagementSystem.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToMany
	@JoinTable(
	        name = "orderProducts",
	        joinColumns = @JoinColumn(name = "orderId"),
	        inverseJoinColumns = @JoinColumn(name = "productId")
	    )
	private List<Product> products;

	public int getOrderId() {
		return orderId;
	}

	public User getUser() {
		return user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
}
