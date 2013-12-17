package com.hino.dao;

import java.util.List;

import com.hino.model.Airport;

public interface AirportDAO {
	public void update(Airport ap);
	public void save(Airport ap);
	public void delete(long id);
	public Airport getAirportById(long id);
	public List<Airport> getAllAirport();	
	public int getAllAirportCount();
	public List<Airport> getAirportsByName(String airportName);	
}
