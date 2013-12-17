package com.hino.dao;

import java.util.Calendar;
import java.util.List;

import com.hino.model.Airline;

public interface AirlineDAO {
	public void update(Airline al);
	public void save(Airline al);
	public void delete(long id);
	public Airline getAirlineById(long id);
	public List<Airline> getAllAirline();
	public int getAllAirlineCount();
	public List<Airline> getAirlineList(long catalogId);	
	public List<Airline> getAirlineList(long did, long aid, Calendar departureDate, Calendar returnDate, boolean isRoundTrip);
	public List<Airline> getAirlinesByCat(long catId);
	public List<Airline> getAirlinesByCriteria(long type, long departCityId, long landCityId, long noofPeople, String departDate, String returnDate);
	public void deletePastAirline(String departureEndDate);
}

