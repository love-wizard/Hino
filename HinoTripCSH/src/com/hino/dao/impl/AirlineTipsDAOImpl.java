package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.AirlineTipsDAO;
import com.hino.model.AirlineTips;

public class AirlineTipsDAOImpl implements AirlineTipsDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		AirlineTips ab = new AirlineTips();
		ab.setId(id);
		ht.delete(ab);

	}

	@Override
	public List<AirlineTips> getAllAirlineTips() {
		return ht.find("from AirlineTips");
	}

	@Override
	public AirlineTips getAirlineTipsById(long id) {
		return ht.get(AirlineTips.class, id);
	}

	@Override
	public void save(AirlineTips at) {
		ht.save(at);
	}

	@Override
	public void update(AirlineTips at) {
		ht.update(at);
	}

	@Override
	public List<AirlineTips> getAirlineTips(long tid) {
		return ht.find("from AirlineTips where catalog_id = " + tid);
	}

	@Override
	public int getAllAirlineTipsCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from AirlineTips"));
	}



}
