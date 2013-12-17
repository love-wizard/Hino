package com.hino.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hino.util.TimeFormater;
@Entity
public class TourSurvey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String est_price;
	private Calendar est_date;
	private String description;
	private int posi = 0;
	private int negi = 0;
	private String img_link;
	private int status = 0; //0 off 1 on 2 finish
	private Calendar posted_date;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Calendar getPosted_date() {
		return posted_date;
	}
	public void setPosted_date(Calendar postedDate) {
		posted_date = postedDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEst_price() {
		return est_price;
	}
	public void setEst_price(String estPrice) {
		est_price = estPrice;
	}
	public Calendar getEst_date() {
		return est_date;
	}
	public void setEst_date(Calendar estDate) {
		est_date = estDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public int getPosi() {
		return posi;
	}
	public void setPosi(int posi) {
		this.posi = posi;
	}
	public int getNegi() {
		return negi;
	}
	public void setNegi(int negi) {
		this.negi = negi;
	}
	public String getImg_link() {
		return img_link;
	}
	public void setImg_link(String imgLink) {
		img_link = imgLink;
	}
	
	public String genEst_string()
	{
		return TimeFormater.format2(est_date);
	}
	
	
}
