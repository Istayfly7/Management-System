package com.rico.model;

import com.rico.entity.User;

public class Admin extends User{
	
	private boolean admin = true;

	public Admin(String userName, String password) {
		super(userName, password);
	}
	
	
	@Override
	public String toString() {
		return "Admin [Id=" + getId() + ", UserName=" + getUserName() + ", Password=" + getPassword()
				+ "]";
	}
	
}
