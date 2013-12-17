package com.hino.dao;


import com.hino.model.WebMenuRoute;

public interface WebMenuRouteDAO {
	public WebMenuRoute getWebMenuRouteId(long id);
	public void save(WebMenuRoute wmr);
	public void delete(long id);
	public void update(WebMenuRoute s);
}
