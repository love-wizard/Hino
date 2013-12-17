package com.hino.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class VipOrder {
	private long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	private Customer customer;
	private Staff staff;
	private int order_method;
	private Calendar order_time;
	private String reciever;
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	private String address;
	private String phone;
	private String email;
	private int delivery;
	private int status;
	private int pay_status;
	private int expire_year;	//Ken Chen 23/09/2012 添加vip过期时间，默认为9999-12-31
	
	
	public int getPay_status() {
		return pay_status;
	}
	public void setPay_status(int payStatus) {
		pay_status = payStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@OneToOne
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@OneToOne
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public int getOrder_method() {
		return order_method;
	}
	public void setOrder_method(int orderMethod) {
		order_method = orderMethod;
	}
	public Calendar getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Calendar orderTime) {
		order_time = orderTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public int getExpire_year() {
		return expire_year;
	}
	public void setExpire_year(int expire_year) {
		this.expire_year = expire_year;
	}
	
	
	
}
