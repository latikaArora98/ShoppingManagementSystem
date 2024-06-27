package com.example.ShoppingManagementSystem.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import com.example.ShoppingManagementSystem.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.example.ShoppingManagementSystem.model.User user= userrepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found!!"));
		return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
	}
	
	

}
