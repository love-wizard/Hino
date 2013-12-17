package com.hino.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lookup {
	private long id;
	private String lookup_type;
	private String lookup_desc;
	private long lookup_counter;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public String getLookup_type() {
		return lookup_type;
	}

	public void setLookup_type(String lookup_type) {
		this.lookup_type = lookup_type;
	}

	public String getLookup_desc() {
		return lookup_desc;
	}

	public void setLookup_desc(String lookup_desc) {
		this.lookup_desc = lookup_desc;
	}

	public long getLookup_counter() {
		return lookup_counter;
	}

	public void setLookup_counter(long lookup_counter) {
		this.lookup_counter = lookup_counter;
	}
	
	
}
