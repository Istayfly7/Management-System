package com.library.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	@OneToOne(mappedBy="holder")
	private Copy bookOnHold;
	
	@Column(insertable=false, updatable=false)
	private UserType type;
	
	@OneToMany(mappedBy="user")
	private List<Copy> books;

	public abstract UserType getType();
	
	
//Additonal Methods
	public boolean checkOut(Copy book) {
		if(!book.isOnHold() && book.isInStock()) {
			books.add(book);
			book.setInStock(false);
			book.setWhoCheckedOut(this);
			return true;
		}
		else if(book.isOnHold() && 
				(book.getHolder().getFirst() == this.getId() && book.getHolder().getSecond() == this.getUserName())
				&& book.isInStock()) {
			books.add(book);
			book.setInStock(false);
			book.setWhoCheckedOut(this);
			book.setOnHold(false);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkIn(Copy book) {
		if(books.remove(book)) {
			book.setInStock(true);
			book.setWhoCheckedOut(null);
			return true;
		}
		return false;	
	}
	
	public boolean putOnHold(Copy book) {
		if(!book.isOnHold() && !book.isInStock()) {
			book.setOnHold(true);
			book.setHolder(this);
			return true;
		}
		else
			return false;
	}
	
	
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
	
	public List<Copy> getBooks() {
		return books;
	}
	
	public Copy getBookOnHold() {
		return bookOnHold;
	} 
	
	void update(User user) {
		this.userName = user.userName;
		this.password = user.password;
	}
	
}
