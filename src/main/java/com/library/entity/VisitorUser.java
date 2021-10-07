package com.library.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.enumType.UserType;

@Entity
@DiscriminatorValue("VISITOR")
public class VisitorUser extends User{

	@JsonIgnore
	@Override
	public UserType getType() {
		return UserType.VISITOR;
	}
	
}
