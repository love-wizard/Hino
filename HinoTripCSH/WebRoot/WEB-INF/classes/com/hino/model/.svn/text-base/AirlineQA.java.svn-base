package com.hino.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AirlineQA {
	private long id;
	private String area;
	private String question;
	private String answer;
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String q) {
		question = q;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String a) {
		answer = a;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
