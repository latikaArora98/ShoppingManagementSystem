package com.example.ShoppingManagementSystem.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	@Column(unique=true, name="username", nullable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@ManyToMany
	@JoinTable(name = "userRoles",
    joinColumns = @JoinColumn(name = "userId"),
    inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roles;

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
