package com.hino.dto;

import java.util.Calendar;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Staff;

public class GroupSurveyDto {
	private long id;
	private Group group;
	private Customer customer;
	private Staff rep;
	
	private String comments;
	private boolean is_verify;
	private boolean is_add_bonus;
	private Calendar create_date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public Staff getRep() {
		return rep;
	}

	public void setRep(Staff rep) {
		this.rep = rep;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean getIs_verify() {
		return is_verify;
	}
	public void setIs_verify(boolean is_verify) {
		this.is_verify = is_verify;
	}
	public boolean getIs_add_bonus() {
		return is_add_bonus;
	}
	public void setIs_add_bonus(boolean is_add_bonus) {
		this.is_add_bonus = is_add_bonus;
	}
	public Calendar getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Calendar create_date) {
		this.create_date = create_date;
	}

	
 
}
