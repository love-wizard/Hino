package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.AirportDAO;
import com.hino.model.Airport;

public class AirportDAOImpl implements AirportDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		Airport ab = new Airport();
		ab.setId(id);
		ht.delete(ab);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Airport> getAllAirport() {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery("select {a.*} from airport a order by convert(airport_name using gb2312) asc").addEntity("a", Airport.class);                
                return query.list();
            }
        });		
	}

	@Override
	public Airport getAirportById(long id) {
		return ht.get(Airport.class, id);
	}

	@Override
	public void save(Airport ap) {
		ht.save(ap);
	}

	@Override
	public void update(Airport ap) {
		ht.update(ap);
	}

	@Override
	public int getAllAirportCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from Airport"));
	}

	@Override
	public List<Airport> getAirportsByName(String airportName) {
		return ht.find("from Airport where lcase(airport_name) like '%" + (airportName == null? "": airportName.toLowerCase()) + "%' or lcase(airport_city) like '%" + (airportName == null? "": airportName.toLowerCase()) + "%'");
	}
}
