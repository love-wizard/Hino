package com.hino.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RORegion {
	private long id;
	private int region_type;//0-town 1-city 2-shire 3-area 4-country 5-contient
	private String name;
	private String name_en;
	private String description;
	private String description_en;
	private double locale_x;//used for map
	private double locale_y;//used for map
	private RORegion father_region;
	private String Zipcode;

	
	public String getZipcode() {
		return Zipcode;
	}
	public void setZipcode(String zipcode) {
		Zipcode = zipcode;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRegion_type() {
		return region_type;
	}
	public void setRegion_type(int region_type) {
		this.region_type = region_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription_en() {
		return description_en;
	}
	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}
	public double getLocale_x() {
		return locale_x;
	}
	public void setLocale_x(double locale_x) {
		this.locale_x = locale_x;
	}
	public double getLocale_y() {
		return locale_y;
	}
	public void setLocale_y(double locale_y) {
		this.locale_y = locale_y;
	}
	public RORegion getFather_region() {
		return father_region;
	}
	public void setFather_region(RORegion father_region) {
		this.father_region = father_region;
	}
	
	
}
