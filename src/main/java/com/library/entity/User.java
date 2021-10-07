package com.library.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.library.enumType.UserType;

@Entity
@Table(name="Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = VisitorUser.class, name = "VISITOR"),
        @JsonSubTypes.Type(value = AdminUser.class, name = "ADMIN")
})
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	
	@Column(insertable=false, updatable=false)
	private UserType type;
	//private List<Copy> books;
	
	public abstract UserType getType();
	

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
	
	void update(User user) {
		this.userName = user.userName;
		this.password = user.password;
	}
	
}
