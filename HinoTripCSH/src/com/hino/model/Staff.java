package com.hino.model;
import java.util.Calendar;

import javax.persistence.*;

@Entity
public class Staff {
	private int id;
	private String staffno;
	private String password;
	private Calendar dob;
	private String email;
	private String firstname;
	private String lastname;
	private String chinesename;
	private int gender = 0;
	private String mobile;
	private String phone;
	private String address;
	private String city;
	private String postcode;
	private Calendar create_time;
	private Calendar last_login_time;
	private String resetcode;
	private String avatar_url;
	private int status = 0;
	private String area;
	private Integer areaid;
	
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

	private String priviledge;
	
	public String getPriviledge() {
		return priviledge;
	}
	public void setPriviledge(String priviledge) {
		this.priviledge = priviledge;
	}
	public String getAddress() {
		return address;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public String getChinesename() {
		return chinesename;
	}
	public String getCity() {
		return city;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getCreate_time() {
		return create_time;
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
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
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
	public String getPostcode() {
		return postcode;
	}

	public String getResetcode() {
		return resetcode;
	}
	public String getStaffno() {
		return staffno;
	}
	public int getStatus() {
		return status;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setAvatar_url(String avatarUrl) {
		avatar_url = avatarUrl;
	}
	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCreate_time(Calendar createTime) {
		create_time = createTime;
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
	public void setId(int id) {
		this.id = id;
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
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public void setResetcode(String resetcode) {
		this.resetcode = resetcode;
	}

	public void setStaffno(String staffno) {
		this.staffno = staffno;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String genFullName()
	{
		return lastname + " " + firstname;
	}
	
	public String genGenderStr()
	{
		return (gender==0)?"Male":"Female";
	}
	
	public String genStatusStr()
	{
		return (status==0)?"Activated":"Unactivated";
	}
	
	public void change_status()
	{
		if (status == 1)
		{
			status = 0;
		} else
		{
			status = 1;
		}
	}
}
