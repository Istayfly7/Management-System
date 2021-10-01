package com.rico.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rico.enumType.UserType;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User{
	
	@JsonIgnore
	@Override
	public UserType getType() {
		return UserType.ADMIN;
	}
	
}
