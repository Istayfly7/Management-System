package com.library.entity;

import java.sql.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name="Copies")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Copy
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int copyId;
	//changed int titleId to an entire Title for a many-to-one-relation
	@ManyToOne
	@JoinColumn(name="ISBN")
	private Title title;
	private boolean inStock = true;
	private boolean onHold;
	private String holder;
	private Date returnDate;
	@ManyToOne
	@JoinColumn(name="id")
	private User user;
	
	public Copy(Title t)
	{
		this.setTitle(t);
	}
	
	public int getCopyId()
	{
		return this.copyId;
	}
	
	public Title getTitle() {
		return title;
	}
	
	public void setTitle(Title t) {
		this.title = t;
	}
	
	public boolean isInStock() {
		return inStock;
	}
	
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	public boolean isOnHold() {
		return onHold;
	}
	
	public void setOnHold(boolean onHold) {
		this.onHold = onHold;
	}
	
	public String getWhoCheckedOut() {
		try {
			if(user != null)
				return user.getUserName();
			else
				throw new NullPointerException();
		}
		catch(NullPointerException ex) {
			return "Copy: User is null.";
		}
	}
	
	public void setWhoCheckedOut(User whoCheckedOut) {
		user = whoCheckedOut;
	}
	
	public String getHolder() {
		try {
			if(holder != null)
				return holder;
			else
				throw new NullPointerException();
		}
		catch(NullPointerException ex) {
			return "Copy: Holder is null.";
		}
	}
	
	public void setHolder(String holder) {
		this.holder = holder;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
