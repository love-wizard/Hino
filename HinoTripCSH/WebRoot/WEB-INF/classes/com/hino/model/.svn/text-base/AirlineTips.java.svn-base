package com.hino.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "airline_tips")
public class AirlineTips {
	private long id;
	private long catalog_id;
	private String tips_query;
	private String tips_answer;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCatalog_id() {
		return catalog_id;
	}
	public void setCatalog_id(long catalog_id) {
		this.catalog_id = catalog_id;
	}
	public String getTips_query() {
		return tips_query;
	}
	public void setTips_query(String tips_query) {
		this.tips_query = tips_query;
	}
	public String getTips_answer() {
		return tips_answer;
	}
	public void setTips_answer(String tips_answer) {
		this.tips_answer = tips_answer;
	}
	
 
}
