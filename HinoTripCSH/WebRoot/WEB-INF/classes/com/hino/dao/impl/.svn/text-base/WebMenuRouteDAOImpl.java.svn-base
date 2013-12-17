package com.hino.dao.impl;


import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.WebMenuRouteDAO;
import com.hino.model.WebMenuRoute;

public class WebMenuRouteDAOImpl implements WebMenuRouteDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public WebMenuRoute getWebMenuRouteId(long id) {
		return ht.get(WebMenuRoute.class, id);
	}

	@Override
	public void save(WebMenuRoute wmr) {
		ht.save(wmr);

	}

	@Override
	public void delete(long id) {
		WebMenuRoute targetMenuRoute = new WebMenuRoute();
		targetMenuRoute.setId(id);
		ht.delete(targetMenuRoute);
	}

	@Override
	public void update(WebMenuRoute wmr) {
		ht.update(wmr);
	}
	
}
