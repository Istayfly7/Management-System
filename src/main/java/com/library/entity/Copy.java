package com.library.entity;

import java.sql.Date;
import java.util.Map;

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
	private boolean inStock;
	private boolean onHold;
	private Map<Integer, String> whoCheckedOut;
	private Map<Integer, String> holder;
	private Date returnDate;
	@ManyToOne
	@JoinColumn(name="id")
	private User user;
	
	public Copy(int cid, Title t, boolean is, boolean oh, Map<Integer, String> wco, Map<Integer, String> holder, Date rd)
	{
		this.setCopyId(cid);
		this.setTitle(t);
		this.setInStock(is);
		this.setOnHold(oh);
		this.setWhoCheckedOut(wco);
		this.setHolder(holder);
		this.setReturnDate(rd);
	}
	
	public int getCopyId()
	{
		return this.copyId;
	}
	public void setCopyId(int id)
	{
		this.copyId = id;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title t) {
		this.title = t;
	}
	/*
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}*/
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
	public Map<Integer, String> getWhoCheckedOut() {
		return whoCheckedOut;
	}
	public void setWhoCheckedOut(Map<Integer, String> whoCheckedOut) {
		this.whoCheckedOut = whoCheckedOut;
	}
	public Map<Integer, String> getHolder() {
		return holder;
	}
	public void setHolder(Map<Integer, String> holder) {
		this.holder = holder;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
