package com.org.host.sprbootpkg.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity
public class LoginController {
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
//	getters and setters
	
	
	
	

}