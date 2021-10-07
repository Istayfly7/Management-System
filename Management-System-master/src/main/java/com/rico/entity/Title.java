package com.rico.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name="Titles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Title
{
	//Since we're using book ISBNs as the primary key, we shouldn't auto-generate values
	@Id
	private int ISBN;
	private String title;
	private String author;
	private Date publicationDate;
	@OneToMany(mappedBy="Titles", cascade=CascadeType.ALL)
	private List<Copy> copies;
	
	public Title(int isbn, String title, String author, Date pubDate, List<Copy> copies)
	{
		this.ISBN = isbn;
		this.title = title;
		this.author = author;
		this.publicationDate = pubDate;
		this.copies = copies;
	}

	public int getISBN()
	{
		return this.ISBN;
	}
	public void setISBN(int isbn)
	{
		this.ISBN = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public List<Copy> getCopies() {
		return copies;
	}
	public void setCopies(List<Copy> copies) {
		this.copies = copies;
	}
}
