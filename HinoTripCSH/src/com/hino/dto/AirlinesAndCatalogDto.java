package com.hino.dto;

import java.util.List;

import com.hino.model.Airline;
import com.hino.model.AirlineCatalog;

public class AirlinesAndCatalogDto {

	private AirlineCatalog airlineCatalog;
	private List<Airline> airlines;
	
	public AirlineCatalog getAirlineCatalog() {
		return airlineCatalog;
	}
	public void setAirlineCatalog(AirlineCatalog airlineCatalog) {
		this.airlineCatalog = airlineCatalog;
	}
	public List<Airline> getAirlines() {
		return airlines;
	}
	public void setAirlines(List<Airline> airlines) {
		this.airlines = airlines;
	}
}