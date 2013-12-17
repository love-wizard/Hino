package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.AirlineCatalogDAO;
import com.hino.model.AirlineCatalog;

public class AirlineCatalogDAOImpl implements AirlineCatalogDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		AirlineCatalog ab = new AirlineCatalog();
		ab.setId(id);
		ht.delete(ab);

	}

	@Override
	public List<AirlineCatalog> getAllAirlineCatalog() {
		return ht.find("from AirlineCatalog order by catalog_default desc");
	}

	@Override
	public AirlineCatalog getAirlineCatalogById(long id) {
		return ht.get(AirlineCatalog.class, id);
	}

	@Override
	public void save(AirlineCatalog ac) {
		ht.save(ac);
	}

	@Override
	public void update(AirlineCatalog ac) {
		ht.update(ac);
	}

	@Override
	public int getAllAirlineCatalogCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from AirlineCatalog"));
	}



}
