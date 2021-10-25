package com.library.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Titles")
public class Title
{
	//Auto-generate values - provides unique values without us having to worry about it.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ISBN;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String author;
	
	@Column(nullable=false)
	private Date publicationDate;
	
	@OneToMany(mappedBy="title", cascade=CascadeType.ALL)
	private List<Copy> copies;
	
	public Title() {}
	
	public Title(String title, String author, Date pubDate)
	{
		this.title = title;
		this.author = author;
		this.publicationDate = pubDate;
	}

	
	public int getISBN()
	{
		return this.ISBN;
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
