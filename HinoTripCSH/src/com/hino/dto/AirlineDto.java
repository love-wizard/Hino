package com.hino.dto;

import java.util.Calendar;
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

import com.hino.model.Airport;

public class AirlineDto {
	private long id;
//	private long departure_id;
//	private long arrival_id;
	private int single_trip_price;
	private int round_trip_price;
	private Calendar departure_begin_date;
	private Calendar departure_end_date;
	private Calendar return_begin_date;
	private Calendar return_end_date;
	private String flight_number;
	private String airline;
	private int luggage_allowance;
	private String catalog_tag;
	private int default_catalog_id;
	private int type;
	private String priceComment;
	private String flightConnections;
	
	private Calendar sale_begin_date;
	private Calendar sale_end_date;
	
	private Airport departure;
	private Airport arrival;
	
	private int max_return_limit_type;
	private int returnPeriod;
	private int discountedPrice;
	private Calendar discountEndTime;
	
	private boolean recommended;
	private Calendar publish_date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	/*
	public long getDeparture_id() {
		return departure_id;
	}
	public void setDeparture_id(long departure_id) {
		this.departure_id = departure_id;
	}
	public long getArrival_id() {
		return arrival_id;
	}
	public void setArrival_id(long arrival_id) {
		this.arrival_id = arrival_id;
	}
	*/
	public int getSingle_trip_price() {
		return single_trip_price;
	}
	public void setSingle_trip_price(int single_trip_price) {
		this.single_trip_price = single_trip_price;
	}
	public int getRound_trip_price() {
		return round_trip_price;
	}
	public void setRound_trip_price(int round_trip_price) {
		this.round_trip_price = round_trip_price;
	}
	public Calendar getDeparture_begin_date() {
		return departure_begin_date;
	}
	public void setDeparture_begin_date(Calendar departure_begin_date) {
		this.departure_begin_date = departure_begin_date;
	}
	public Calendar getDeparture_end_date() {
		return departure_end_date;
	}
	public void setDeparture_end_date(Calendar departure_end_date) {
		this.departure_end_date = departure_end_date;
	}
	public Calendar getReturn_begin_date() {
		return return_begin_date;
	}
	public void setReturn_begin_date(Calendar return_begin_date) {
		this.return_begin_date = return_begin_date;
	}
	public Calendar getReturn_end_date() {
		return return_end_date;
	}
	public void setReturn_end_date(Calendar return_end_date) {
		this.return_end_date = return_end_date;
	}
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public int getLuggage_allowance() {
		return luggage_allowance;
	}
	public void setLuggage_allowance(int luggage_allowance) {
		this.luggage_allowance = luggage_allowance;
	}
	public String getCatalog_tag() {
		return catalog_tag;
	}
	public void setCatalog_tag(String catalog_tag) {
		this.catalog_tag = catalog_tag;
	}
	public int getDefault_catalog_id() {
		return default_catalog_id;
	}
	public void setDefault_catalog_id(int default_catalog_id) {
		this.default_catalog_id = default_catalog_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPriceComment() {
		return priceComment;
	}
	public void setPriceComment(String priceComment) {
		this.priceComment = priceComment;
	}
	public String getFlightConnections() {
		return flightConnections;
	}
	public void setFlightConnections(String flightConnections) {
		this.flightConnections = flightConnections;
	}
	public Calendar getSale_begin_date() {
		return sale_begin_date;
	}
	public void setSale_begin_date(Calendar sale_begin_date) {
		this.sale_begin_date = sale_begin_date;
	}
	public Calendar getSale_end_date() {
		return sale_end_date;
	}
	public void setSale_end_date(Calendar sale_end_date) {
		this.sale_end_date = sale_end_date;
	}
	public Airport getArrival() {
		return arrival;
	}
	public Airport getDeparture() {
		return departure;
	}
	public void setDeparture(Airport departure) {
		this.departure = departure;
	}
	public void setArrival(Airport arrival) {
		this.arrival = arrival;
	}
	public int getReturnPeriod() {
		return returnPeriod;
	}
	public void setReturnPeriod(int returnPeriod) {
		this.returnPeriod = returnPeriod;
	}
	public int getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public Calendar getDiscountEndTime() {
		return discountEndTime;
	}
	public void setDiscountEndTime(Calendar discountEndTime) {
		this.discountEndTime = discountEndTime;
	}
	public int getMax_return_limit_type() {
		return max_return_limit_type;
	}
	public void setMax_return_limit_type(int max_return_limit_type) {
		this.max_return_limit_type = max_return_limit_type;
	}
	public boolean isRecommended() {
		return recommended;
	}
	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}
	public Calendar getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Calendar publish_date) {
		this.publish_date = publish_date;
	}
}
