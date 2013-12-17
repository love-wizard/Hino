package com.hino.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recruitment")
public class Recruitment {

	private long id;
	
	private String position;
	
	private String description;
	
	private Calendar expiryDate;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}
}
