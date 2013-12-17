package com.hino.dto;


public class StaffCreateDto {
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private String staffno;
	public String getStaffno() {
		return staffno;
	}
	public void setStaffno(String staffno) {
		this.staffno = staffno;
	}
	public String getStaffemail() {
		return staffemail;
	}
	public void setStaffemail(String staffemail) {
		this.staffemail = staffemail;
	}
	public String getPriviledge() {
		return priviledge;
	}
	public void setPriviledge(String priviledge) {
		this.priviledge = priviledge;
	}
	public long getAreaselect() {
		return areaselect;
	}
	public void setAreaselect(long areaselect) {
		this.areaselect = areaselect;
	}
	private String staffemail;
	private String priviledge;
	private long areaselect;
	
	public String toString()
	{
		return "Staff id: " + id +"\nStaff No : " + staffno +"\n" + "Email: " + staffemail +"\n" + "P: " + priviledge + "\n" + "selectedArea: " + areaselect;
	}
}
