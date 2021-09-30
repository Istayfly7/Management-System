package com.rico.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	private boolean admin;
	//private List<Copy> books;
	
	
//Default Constructor
	public User() {}
	
//Constructor
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	

//Additonal Methods
	//public List<Title> viewCatalog();
	//public boolean checkOut(Copy book) {return false;}
	//public boolean checkIn(Copy book) {return false;}
	//public boolean putOnHold(Copy book) {return false;}
	

	
//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public boolean isAdmin() {
		return admin;
	}
	
	
	
}
