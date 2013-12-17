package com.hino.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Site {
	private long id;
	private String name;
	private String description;
	private String imageurl;
	private String thumburl;
	
	private String name_en;
	private String description_en;
	private String thumburl_en;
	
	public String getThumburl_en() {
		return thumburl_en;
	}

	public void setThumburl_en(String thumburlEn) {
		thumburl_en = thumburlEn;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String nameEn) {
		name_en = nameEn;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String descriptionEn) {
		description_en = descriptionEn;
	}

	public String getDescription() {
		return description;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public String getImageurl() {
		return imageurl;
	}
	public String getName() {
		return name;
	}
	public String getThumburl() {
		return thumburl;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setThumburl(String thumburl) {
		this.thumburl = thumburl;
	}
}
