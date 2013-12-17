package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.VehicleDAO;
import com.hino.model.Vehicle;

public class VehicleDAOImpl implements VehicleDAO {
    private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		Vehicle v = new Vehicle();
		v.setId(id);
		ht.delete(v);
		
	}

	@Override
	public Vehicle getVehicleById(long id) {
		return ht.get(Vehicle.class, id);
	}

	@Override
	public int getVehicleCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from Vehicle"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> list_all_vehicle() {
		return ht.find("from Vehicle");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> list_vehicle_by_paging(final int start,final int count) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Vehicle");
                query.setMaxResults(count);
                query.setFirstResult(count*start);
                return query.list();
            }
			
        });
	}

	@Override
	public void save(Vehicle v) {
		ht.save(v);
		
	}

	@Override
	public void update(Vehicle v) {
		ht.update(v);
		
	}

}
