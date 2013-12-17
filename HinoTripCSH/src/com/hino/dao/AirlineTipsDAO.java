package com.hino.dao;

import java.util.List;

import com.hino.model.AirlineTips;

public interface AirlineTipsDAO {
	public void update(AirlineTips at);
	public void save(AirlineTips at);
	public void delete(long id);
	public AirlineTips getAirlineTipsById(long id);
	public List<AirlineTips> getAllAirlineTips();
	public int getAllAirlineTipsCount();
	public List<AirlineTips> getAirlineTips(long tid);	
}
