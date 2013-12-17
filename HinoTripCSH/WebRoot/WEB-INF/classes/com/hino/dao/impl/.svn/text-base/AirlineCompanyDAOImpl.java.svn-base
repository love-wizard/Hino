package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.AirlineCompanyDAO;
import com.hino.dao.AirportDAO;
import com.hino.model.AirlineCompany;
import com.hino.model.Airport;

public class AirlineCompanyDAOImpl implements AirlineCompanyDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		AirlineCompany ab = new AirlineCompany();
		ab.setId(id);
		ht.delete(ab);

	}

	@Override
	public List<AirlineCompany> getAllAirlineCompany() {
		return ht.find("from AirlineCompany");
	}

	@Override
	public AirlineCompany getAirlineCompanyById(long id) {
		return ht.get(AirlineCompany.class, id);
	}

	@Override
	public void save(AirlineCompany ap) {
		ht.save(ap);
	}

	@Override
	public void update(AirlineCompany ap) {
		ht.update(ap);
	}

	@Override
	public int getAllAirlineCompanyCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from AirlineCompany"));
	}



}
