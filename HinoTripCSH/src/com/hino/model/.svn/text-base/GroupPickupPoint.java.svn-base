package com.hino.model;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class GroupPickupPoint {
	private long id;
	private int index;
	private String description;
	private Calendar time;
	private RORegion rel_region;
	private Group rel_group;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
	
	@OneToOne
	public RORegion getRel_region() {
		return rel_region;
	}
	public void setRel_region(RORegion rel_region) {
		this.rel_region = rel_region;
	}
	
	@OneToOne
	public Group getRel_group() {
		return rel_group;
	}
	public void setRel_group(Group rel_group) {
		this.rel_group = rel_group;
	}
	
	
	
}
