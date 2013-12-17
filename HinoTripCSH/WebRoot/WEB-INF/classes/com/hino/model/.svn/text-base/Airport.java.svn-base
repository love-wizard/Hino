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
@Table(name = "airport")
public class Airport {
	private long id;
	private String airport_name;
	private String airport_city;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAirport_name() {
		return airport_name;
	}
	public void setAirport_name(String airport_name) {
		this.airport_name = airport_name;
	}
	public String getAirport_city() {
		return airport_city;
	}
	public void setAirport_city(String airport_city) {
		this.airport_city = airport_city;
	}
	public String genCityAirport()
	{
		String cityAirport = "";
		String[] p = airport_name.split("/");
		cityAirport = p[0].trim();
		return cityAirport;
	}
	
	public String genShortName()
	{
		String[] s = this.airport_name.split("/");
		return s[s.length-1];
		
	}
	
 
}
