package com.hino.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Customer implements Serializable{
	private long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String chinesename;
	private int gender = 0;
	private Calendar dob;
	private String mobile;
	private String phone;
	private String address;
	private String city;
	private String postcode;
	private int point;
	private String cardno;
	private Calendar reg_time;
	private Calendar last_login_time;
	private String resetcode;
	private String university;
	
	
	private Calendar vip_valid;
	
	@Temporal(TemporalType.DATE)
	public Calendar getVip_valid() {
		return vip_valid;
	}
	public void setVip_valid(Calendar vipValid) {
		vip_valid = vipValid;
	}
	public String getAddress() {
		return address;
	}
	public String getCardno() {
		return cardno;
	}
	public String getChinesename() {
		return chinesename;
	}
	public String getCity() {
		return city;
	}
	
	@Temporal(TemporalType.DATE)
	public Calendar getDob() {
		return dob;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public int getGender() {
		return gender;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getLast_login_time() {
		return last_login_time;
	}
	public String getLastname() {
		return lastname;
	}
	public String getMobile() {
		return mobile;
	}
	public String getPassword() {
		return password;
	}
	public String getPhone() {
		return phone;
	}
	public int getPoint() {
		return point;
	}
	public String getPostcode() {
		return postcode;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getReg_time() {
		return reg_time;
	}
	public String getResetcode() {
		return resetcode;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setLast_login_time(Calendar lastLoginTime) {
		last_login_time = lastLoginTime;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public void setReg_time(Calendar regTime) {
		reg_time = regTime;
	}
	public void setResetcode(String resetcode) {
		this.resetcode = resetcode;
	}
	
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String genFullName()
	{
		return lastname + " " + firstname;
	}
	
	public String genGenderStr()
	{
		return (gender==0)?"Male":"Female";
	}
	
	public boolean genIsVip()
	{
		return Calendar.getInstance().before(this.vip_valid);
	}
	
	public boolean withdraw(int p)
	{
		if(point-p>=0)
		{
			point = point - p;
			return true;
		} else
		{
			return false;
		}
	}
	
	public boolean deposit(int p)
	{
		point = point + p;
		return true;
	}
}
