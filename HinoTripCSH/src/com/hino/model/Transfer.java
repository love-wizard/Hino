package com.hino.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Transfer {
	private long id;
	private Staff rep;
	private Customer customer;
	private double p_amount;
	private Calendar dec_time;
	private Calendar trans_time;
	private String trans_method;
	private int status;
	private String comment;
	private String feedback;
	private long payment_method_id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	public Staff getRep() {
		return rep;
	}

	public void setRep(Staff rep) {
		this.rep = rep;
	}

	@OneToOne
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getP_amount() {
		return p_amount;
	}

	public void setP_amount(double pAmount) {
		p_amount = pAmount;
	}

	public Calendar getDec_time() {
		return dec_time;
	}

	public void setDec_time(Calendar decTime) {
		dec_time = decTime;
	}

	public Calendar getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(Calendar transTime) {
		trans_time = transTime;
	}

	public String getTrans_method() {
		return trans_method;
	}

	public void setTrans_method(String transMethod) {
		trans_method = transMethod;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public long getPayment_method_id() {
		return payment_method_id;
	}

	public void setPayment_method_id(long payment_method_id) {
		this.payment_method_id = payment_method_id;
	}

}
