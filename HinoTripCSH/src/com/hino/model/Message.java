package com.hino.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Message {
	private long id;
	private String title;
	private String message;
	private Staff sender;
	private Staff reciever;
	private boolean isRead = false;
	private String att_url;
	private Calendar create_time;

	public String getAtt_url() {
		return att_url;
	}

	public Calendar getCreate_time() {
		return create_time;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	@OneToOne(fetch=FetchType.EAGER)
	public Staff getReciver() {
		return reciever;
	}

	@OneToOne(fetch=FetchType.EAGER)
	public Staff getSender() {
		return sender;
	}

	public String getTitle() {
		return title;
	}

	public boolean getIsRead() {
		return isRead;
	}

	public void setAtt_url(String attUrl) {
		att_url = attUrl;
	}

	public void setCreate_time(Calendar createTime) {
		create_time = createTime;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	public void setReciver(Staff reciver) {
		this.reciever = reciver;
	}

	public void setSender(Staff sender) {
		this.sender = sender;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
