package com.hino.dto;

import java.util.ArrayList;
import java.util.HashMap;

import com.hino.util.Page;
import com.hino.util.SearchType;
import java.util.LinkedHashMap;

public class SearchResultSummayDto {
	
	//产品类型
	private HashMap<String, String> routeType = new HashMap<String, String>();
	
	//出发地
	private HashMap<String, PlaceDto> departPlace = new HashMap<String, PlaceDto>();
	
	//出发地
	private LinkedHashMap<String, PlaceDto> lkDepartPlace = new LinkedHashMap<String, PlaceDto>();
		
	//目的地
	private HashMap<String, String> destination = new HashMap<String, String>();
	
	//行程天数
	private HashMap<String, String> travelTime = new HashMap<String, String>();
	
	//出发时间
	private HashMap<String, String> departDate = new HashMap<String, String>();
	
	//价格区间
	private LinkedHashMap priceRange = new LinkedHashMap();

	public HashMap<String, PlaceDto> getDepartPlace() {
		return departPlace;
	}

	public void setDepartPlace(HashMap<String, PlaceDto> departPlace) {
		this.departPlace = departPlace;
	}

	public HashMap<String, String> getDestination() {
		return destination;
	}

	public void setDestination(HashMap<String, String> destination) {
		this.destination = destination;
	}

	public HashMap<String, String> getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(HashMap<String, String> travelTime) {
		this.travelTime = travelTime;
	}

	public HashMap<String, String> getDepartDate() {
		return departDate;
	}

	public void setDepartDate(HashMap<String, String> departDate) {
		this.departDate = departDate;
	}

	public LinkedHashMap getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(LinkedHashMap priceRange) {
		this.priceRange = priceRange;
	}

	public HashMap<String, String> getRouteType() {
		return routeType;
	}

	public void setRouteType(HashMap<String, String> routeType) {
		this.routeType = routeType;
	}

	public LinkedHashMap<String, PlaceDto> getLkDepartPlace() {
		return lkDepartPlace;
	}

	public void setLkDepartPlace(LinkedHashMap<String, PlaceDto> lkDepartPlace) {
		this.lkDepartPlace = lkDepartPlace;
	}

	
}
