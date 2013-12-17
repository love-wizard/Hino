package com.hino.dto;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.hino.model.Staff;

public class AirlineBookingDto {
	private long id;
	private long airline_id;
	private long airline_company_id;
	private String customer;
	private String first_name;
	private String chinese_name;
	private String last_name;
	private String	email;
	private String phone;
	private String flight_number;
	private long airport_id;
	private Calendar flight_date;
	private double price;
	private long status;
	private long rep_id;
	private Calendar transfer_apply_date;
	private String transfer_apply_comments;
	private long transfer_audit_id;
	private Staff transfer_audit_rep;
	
	private Calendar transfer_audit_date;
	private String transfer_audit_comments;
	private long financial_audit_id;
	private Staff finanical_audit_rep;
	private Calendar financial_audit_date;
	private String financial_audit_feedback;
	private int payer_type_id;
	private int payment_method_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAirline_id() {
		return airline_id;
	}
	public void setAirline_id(long airline_id) {
		this.airline_id = airline_id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getChinese_name() {
		return chinese_name;
	}
	public void setChinese_name(String chinese_name) {
		this.chinese_name = chinese_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	public long getAirport_id() {
		return airport_id;
	}
	public void setAirport_id(long airport_id) {
		this.airport_id = airport_id;
	}
	public Calendar getFlight_date() {
		return flight_date;
	}
	public void setFlight_date(Calendar flight_date) {
		this.flight_date = flight_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getRep_id() {
		return rep_id;
	}
	public void setRep_id(long rep_id) {
		this.rep_id = rep_id;
	}
	
	public Calendar getTransfer_apply_date() {
		return transfer_apply_date;
	}
	public void setTransfer_apply_date(Calendar transfer_apply_date) {
		this.transfer_apply_date = transfer_apply_date;
	}
	public String getTransfer_apply_comments() {
		return transfer_apply_comments;
	}
	public void setTransfer_apply_comments(String transfer_apply_comments) {
		this.transfer_apply_comments = transfer_apply_comments;
	}
	public long getTransfer_audit_id() {
		return transfer_audit_id;
	}
	public void setTransfer_audit_id(long transfer_audit_id) {
		this.transfer_audit_id = transfer_audit_id;
	}
	public Calendar getTransfer_audit_date() {
		return transfer_audit_date;
	}
	public void setTransfer_audit_date(Calendar transfer_audit_date) {
		this.transfer_audit_date = transfer_audit_date;
	}
	public String getTransfer_audit_comments() {
		return transfer_audit_comments;
	}
	public void setTransfer_audit_comments(String transfer_audit_comments) {
		this.transfer_audit_comments = transfer_audit_comments;
	}
	public long getFinancial_audit_id() {
		return financial_audit_id;
	}
	public void setFinancial_audit_id(long financial_audit_id) {
		this.financial_audit_id = financial_audit_id;
	}
	public Calendar getFinancial_audit_date() {
		return financial_audit_date;
	}
	public void setFinancial_audit_date(Calendar financial_audit_date) {
		this.financial_audit_date = financial_audit_date;
	}
	public String getFinancial_audit_feedback() {
		return financial_audit_feedback;
	}
	public void setFinancial_audit_feedback(String financial_audit_feedback) {
		this.financial_audit_feedback = financial_audit_feedback;
	}
	
	public Staff getTransfer_audit_rep() {
		return transfer_audit_rep;
	}
	public void setTransfer_audit_rep(Staff transfer_audit_rep) {
		this.transfer_audit_rep = transfer_audit_rep;
	}
	
	public Staff getFinanical_audit_rep() {
		return finanical_audit_rep;
	}
	public void setFinanical_audit_rep(Staff finanical_audit_rep) {
		this.finanical_audit_rep = finanical_audit_rep;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getAirline_company_id() {
		return airline_company_id;
	}
	public void setAirline_company_id(long airline_company_id) {
		this.airline_company_id = airline_company_id;
	}
	public int getPayer_type_id() {
		return payer_type_id;
	}
	public void setPayer_type_id(int payer_type_id) {
		this.payer_type_id = payer_type_id;
	}
	public int getPayment_method_id() {
		return payment_method_id;
	}
	public void setPayment_method_id(int payment_method_id) {
		this.payment_method_id = payment_method_id;
	}
}
