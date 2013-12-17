package com.hino.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Resource {
	private long id;
	
	private String name;
	
	private String description;
	private int category;
	private String filename;
	private Staff author;
	private Calendar update_time;
	@OneToOne(cascade={CascadeType.ALL})
	public Staff getAuthor() {
		return author;
	}
	public int getCategory() {
		return category;
	}
	
	public String getDescription() {
		return description;
	}
	public String getFilename() {
		return filename;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getUpdate_time() {
		return update_time;
	}

	public void setAuthor(Staff author) {
		this.author = author;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUpdate_time(Calendar updateTime) {
		update_time = updateTime;
	}
}
