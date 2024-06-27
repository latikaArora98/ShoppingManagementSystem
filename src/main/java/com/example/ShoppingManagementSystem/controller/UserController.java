package com.example.ShoppingManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.ShoppingManagementSystem.config.JwtUtil;
import com.example.ShoppingManagementSystem.model.JwtRequest;
import com.example.ShoppingManagementSystem.model.JwtResponse;
import com.example.ShoppingManagementSystem.model.User;
import com.example.ShoppingManagementSystem.repository.UserRepository;
import com.example.ShoppingManagementSystem.service.MyUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private AuthenticationManager authmgr;
	
	@Autowired
	JwtUtil jwtutil;
	
	@Autowired
	MyUserDetailsService uds;
	
	@Autowired
	UserRepository userrepo;
	
	@PostMapping("/login")
	public JwtResponse login(@RequestBody JwtRequest req) {
		
		authmgr.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword()));
		final UserDetails usr = uds.loadUserByUsername(req.getUsername());
		final String jwt = jwtutil.generateToken(usr.getUsername());
		return new JwtResponse(jwt);
	}
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		if(userrepo.findByUsername(user.getUsername())!=null) {
			return "User already exists!!";
		}
		else {
			userrepo.save(user);
			return "User successfully added!!";
		}
		
	}

}
