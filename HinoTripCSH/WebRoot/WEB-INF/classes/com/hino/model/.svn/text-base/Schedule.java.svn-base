package com.hino.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hino.util.Info;

@Entity
@Table(name = "schedule")
public class Schedule {
	private long id;
	
	private long route_id;
	private long sequence_id;
	
	private String title;
	private String title_desc;
	private String schedule_desc;
	
	private String breakfast;
	private String lunch;
	private String dinner;
	private String transport;
	private String hotel;
	private String flight;
	
	private String material_1;
	private String material_2;
	private String material_3;
	private String material_1_type;
	private String material_2_type;
	private String material_3_type;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getRoute_id() {
		return route_id;
	}
	public void setRoute_id(long route_id) {
		this.route_id = route_id;
	}
	
	public long getSequence_id() {
		return sequence_id;
	}
	public void setSequence_id(long sequence_id) {
		this.sequence_id = sequence_id;
	}
	
	@Transient
	public String getShortTitleDesc() {
		String temp = title_desc.trim();
		return Info.cutString(temp, 38, " ...");
	}
		
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle_desc() {
		return title_desc;
	}
	public void setTitle_desc(String title_desc) {
		this.title_desc = title_desc;
	}
	public String getSchedule_desc() {
		return schedule_desc;
	}
	public void setSchedule_desc(String schedule_desc) {
		this.schedule_desc = schedule_desc;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getLunch() {
		return lunch;
	}
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}
	public String getDinner() {
		return dinner;
	}
	public void setDinner(String dinner) {
		this.dinner = dinner;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public String getFlight() {
//		if (null == flight) {
//			flight = "";
//		}
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getMaterial_1() {
		if(null == material_1){
			material_1 = "schedule_default.jpg";
		}
		return material_1;
	}
	public void setMaterial_1(String material_1) {
		this.material_1 = material_1;
	}
	public String getMaterial_2() {
		if(null == material_2){
			material_2 = "schedule_default.jpg";
		}
		return material_2;
	}
	public void setMaterial_2(String material_2) {
		this.material_2 = material_2;
	}
	public String getMaterial_3() {
		if(null == material_3){
			material_3 = "schedule_default.jpg";
		}
		return material_3;
	}
	public void setMaterial_3(String material_3) {
		this.material_3 = material_3;
	}
	public String getMaterial_1_type() {
		return material_1_type;
	}
	public void setMaterial_1_type(String material_1_type) {
		this.material_1_type = material_1_type;
	}
	public String getMaterial_2_type() {
		return material_2_type;
	}
	public void setMaterial_2_type(String material_2_type) {
		this.material_2_type = material_2_type;
	}
	public String getMaterial_3_type() {
		return material_3_type;
	}
	public void setMaterial_3_type(String material_3_type) {
		this.material_3_type = material_3_type;
	}
	
}
