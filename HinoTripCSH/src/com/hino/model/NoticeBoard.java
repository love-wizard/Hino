package com.hino.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NoticeBoard {
	private long id;
	private String content;
	private Calendar startDate;
	private Calendar endDate;
	private String status;
	private String link;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public String getContent(){
		return content;
	}
	
	public String getStatus(){
		return status;
	}
	
	public Calendar getStartDate(){
		return startDate;
	}
	
	public Calendar getEndDate(){
		return endDate;
	}
	
	public String getLink(){
		return link;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setStartDate(Calendar date){
		this.startDate = date;
	}
	
	public void setEndDate(Calendar date){
		this.endDate = date;
	}
	
	public void setLink(String link){
		this.link = link;
	}
}
