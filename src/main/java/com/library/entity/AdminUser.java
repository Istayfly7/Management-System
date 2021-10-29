package com.library.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User{
	
	@JsonIgnore
	@Override
	public String getType() {
		return "ADMIN";
	}
	
}
