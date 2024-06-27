package com.example.ShoppingManagementSystem.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.ShoppingManagementSystem.model.ERole;
import com.example.ShoppingManagementSystem.model.Role;
import com.example.ShoppingManagementSystem.model.User;
import com.example.ShoppingManagementSystem.repository.RoleRepository;
import com.example.ShoppingManagementSystem.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner{
	
	@Autowired
	RoleRepository rolerepo;
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	PasswordEncoder passenco;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Role adminRole = new Role();
		adminRole.setName(ERole.ADMIN);
		rolerepo.save(adminRole);
		
		Role userRole = new Role();
		userRole.setName(ERole.USER);
		rolerepo.save(userRole);
		
		Role managerRole = new Role();
		managerRole.setName(ERole.MANAGER);
		rolerepo.save(managerRole);
		
		User adminusr = new User();
		adminusr.setUsername("admin");
		adminusr.setPassword(passenco.encode("admin"));
		Set<Role> adminroles = new HashSet<>();
		adminroles.add(adminRole);
		adminroles.add(userRole);
		adminroles.add(managerRole);
		userrepo.save(adminusr);
		
		User usr = new User();
		usr.setUsername("user");
		usr.setPassword(passenco.encode("user"));
		Set<Role> usrroles = new HashSet<>();
		usrroles.add(userRole);
		userrepo.save(usr);
		
		User mgr = new User();
		mgr.setUsername("manager");
		mgr.setPassword(passenco.encode("manager"));
		Set<Role> mgrroles = new HashSet<>();
		mgrroles.add(managerRole);
		userrepo.save(mgr);
	}

}
