package com.hino.dao;

import java.util.List;

import com.hino.model.AirlineCatalog;

public interface AirlineCatalogDAO {
	public void update(AirlineCatalog ac);
	public void save(AirlineCatalog ac);
	public void delete(long id);
	public AirlineCatalog getAirlineCatalogById(long id);
	public List<AirlineCatalog> getAllAirlineCatalog();
	public int getAllAirlineCatalogCount();
}
