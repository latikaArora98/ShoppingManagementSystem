package com.example.ShoppingManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="quantity")
	private Integer quantity;

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
}
