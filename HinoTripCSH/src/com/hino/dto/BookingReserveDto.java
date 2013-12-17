package com.hino.dto;

public class BookingReserveDto {
	private long cid; //injected at page class
	private int sid; //injected at page class
	private String fn;
	private String ln;
	private String voucher;
	private String cn;
	private String email;
	private String phone;
	private String pickup;
	private int gender;
	private String room;
	private int pd_point;
	private int paymethod;
	private String comments;
	private double pd_credit;
	private int groupid;
	
	public double getPd_credit() {
		return pd_credit;
	}
	public void setPd_credit(double pdCredit) {
		pd_credit = pdCredit;
	}
	public long getCid() {
		return cid;
	}
	public String getCn() {
		return cn;
	}
	public String getComments() {
		return comments;
	}
	public String getEmail() {
		return email;
	}
	public String getFn() {
		return fn;
	}
	public int getGender() {
		return gender;
	}
	
	public String getLn() {
		return ln;
	}
	public int getPaymethod() {
		return paymethod;
	}
	public int getPd_point() {
		return pd_point;
	}
	public int getGroupId() {
		return groupid;
	}
	public String getPhone() {
		return phone;
	}
	public String getPickup() {
		return pickup;
	}
	public String getRoom() {
		return room;
	}
	public int getSid() {
		return sid;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFn(String fn) {
		this.fn = fn;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setLn(String ln) {
		this.ln = ln;
	}
	public void setPaymethod(int paymethod) {
		this.paymethod = paymethod;
	}
	
	public void setPd_point(int pdPoint) {
		pd_point = pdPoint;
	}
	public void setGroupId(int groupId) {
		groupid = groupId;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPickup(String pickup) {
		this.pickup = pickup;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
}
