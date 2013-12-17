package com.hino.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Voucher {
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Calendar getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(Calendar expireDate) {
		expire_date = expireDate;
	}
	public String getMatch_type() {
		return match_type;
	}
	public void setMatch_type(String matchType) {
		match_type = matchType;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getNo_of_use() {
		return no_of_use;
	}
	public void setNo_of_use(int noOfUse) {
		no_of_use = noOfUse;
	}
	public int getNo_used() {
		return no_used;
	}
	public void setNo_used(int noUsed) {
		no_used = noUsed;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String code;
	private Calendar expire_date;
	private String match_type;
	private double value;
	private int no_of_use;
	private int no_used = 0;
	
	

}
