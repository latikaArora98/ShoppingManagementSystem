package com.example.ShoppingManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.ShoppingManagementSystem.model.Product;
import com.example.ShoppingManagementSystem.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService prodser;
	
	@GetMapping("/getallproducts")
	public List<Product> getallproducts(){
		return prodser.getallproducts();
	}	
	
	@GetMapping("/getproductbyid/{id}")
	public Product getproductbyid(@PathVariable(name="id") int id){
		return prodser.getproductbyid(id);
	}	
	
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	@PostMapping("/addproduct")
	public String addproduct(@RequestBody Product prod){
		return prodser.addproduct(prod);
	}	
	
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	@DeleteMapping("/deleteproduct/{id}")
	public String deleteproduct(@PathVariable(name="id") int id){
		return prodser.deleteproduct(id);
	}	

}
