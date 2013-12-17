package com.hino.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarRequest {
	private long id;
	private int status;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String carType) {
		car_type = carType;
	}
	public int getNo_of_persons() {
		return no_of_persons;
	}
	public void setNo_of_persons(int noOfPersons) {
		no_of_persons = noOfPersons;
	}
	public int getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(int noOfDays) {
		no_of_days = noOfDays;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	private String name;
	private String email;
	private String phone;
	private Calendar date;
	private String car_type;
	private int no_of_persons;
	private int no_of_days;
	private String detail;
	
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	
}
