package com.example.ShoppingManagementSystem.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name="roles")
@Data
public class Role {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="name")
	private ERole name;
	
	@ManyToMany(mappedBy="roles")
	private Set<User> users;

	public int getRoleId() {
		return roleId;
	}

	public ERole getName() {
		return name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	

}
