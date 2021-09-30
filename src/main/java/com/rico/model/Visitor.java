package com.rico.model;

import com.rico.entity.User;

public class Visitor extends User{

	public Visitor(String userName, String password) {
		super(userName, password);
	}

	@Override
	public String toString() {
		return "Visitor [UserName=" + getUserName() + ", Password=" + getPassword() + "]";
	}
	
}
