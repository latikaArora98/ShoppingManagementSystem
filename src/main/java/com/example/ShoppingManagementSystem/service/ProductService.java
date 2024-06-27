package com.example.ShoppingManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ShoppingManagementSystem.model.Product;
import com.example.ShoppingManagementSystem.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository prodrepo;

	public List<Product> getallproducts() {
		return prodrepo.findAll();
		// TODO Auto-generated method stub
		
	}

	public Product getproductbyid(int id) {
		return prodrepo.findById(id).get();
		// TODO Auto-generated method stub
		
	}

	public String deleteproduct(int id) {
		// TODO Auto-generated method stub
		prodrepo.deleteById(id);
		return "Product deleted successfully!!";
	}

	public String addproduct(Product prod) {
		// TODO Auto-generated method stub
		prodrepo.save(prod);
		return "Product added successfully!!";
	}

}
