package com.hino.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.AirlineDAO;
import com.hino.model.Airline;
import com.hino.util.Info;

public class AirlineDAOImpl implements AirlineDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		Airline ab = new Airline();
		ab.setId(id);
		ht.delete(ab);

	}

	@Override
	public List<Airline> getAllAirline() {
		return ht.find("from Airline");
	}

	@Override
	public Airline getAirlineById(long id) {
		return ht.get(Airline.class, id);
	}

	@Override
	public void save(Airline al) {
		ht.save(al);
	}

	@Override
	public void update(Airline al) { 
		ht.update(al);
	}

	@Override
	public List<Airline> getAirlineList(long did, long aid, Calendar departureDate, Calendar returnDate, boolean isRoundTrip) {
		return ht.find("from Airline ");
	}

	@Override
	public List<Airline> getAirlineList(long catalogId) {
		return ht.find("from Airline where default_catalog_id = " + catalogId);
	}

	@Override
	public int getAllAirlineCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from Airline"));
	}

	@Override
	public List<Airline> getAirlinesByCat(long catId) {
		//return ht.find("from Airline where CONCAT(',', trim(catalog_tag)) like '%," + catId + ",%'");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar realCalendar = Calendar.getInstance();
		Calendar lowCalendar = Calendar.getInstance();
		Calendar highCalendar = Calendar.getInstance();
		lowCalendar.set(realCalendar.get(Calendar.YEAR), realCalendar.get(Calendar.MONTH), realCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		highCalendar.set(realCalendar.get(Calendar.YEAR), realCalendar.get(Calendar.MONTH), realCalendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		return ht.find("from Airline where default_catalog_id = " + catId + " and sale_begin_date <= '" + df.format(highCalendar.getTime()) + "' and sale_end_date >= '" +  df.format(lowCalendar.getTime()) + "' and recommended = true");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Airline> getAirlinesByCriteria(final long type, final long departAirportId,
			final long landAirportId, final long noofPeople, final String departDate,
			final String returnDate) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		
        		String hql = "select {al.*} from airline al where 1=1 ";
        		
        		if(departAirportId > 0) {
        			hql = hql + "and al.departure_id=:departCityId ";
        		}
        		
        		if(landAirportId > 0) {
        			hql = hql + "and al.arrival_id=:landCityId ";
        		} else if (landAirportId == Info.AIRLINE_ALL_LAND_CITY) { 
        			// gAll
        		}
        		
        		if(type == 1 || type == 2) { // Single Trip / Round Trip
        			hql = hql + "and al.type=:type ";
        		}
        		
        		if (null != departDate & !"".equals(departDate)) {
        			//hql = hql + "and (:departureDate = al.departure_begin_date) ";
        			hql = hql + "and (:departureDate between al.departure_begin_date and al.departure_end_date) ";
        		}
        		
//        		if (null != returnDate && !"".equals(returnDate)) {
//        			hql = hql + "and (date_sub(:returnCDate, interval returnPeriod day) between al.departure_begin_date and al.departure_end_date) ";
//        		}
        		if(type==2)
        		{
        			hql = hql + " and ((max_return_limit_type = 0 and return_begin_date >= CAST(:returnCDate AS DATETIME)) ";
        			hql = hql + " or (max_return_limit_type = 1 and CAST(:returnCDate AS DATETIME) <= date_add(CAST(:departureDate AS DATETIME), INTERVAL returnPeriod DAY)) ";
        			hql = hql + " or (max_return_limit_type = 2 and CAST(:returnCDate AS DATETIME) <= date_add(CAST(:departureDate AS DATETIME), INTERVAL returnPeriod MONTH))) ";
        		}
        		
        		Query query = session.createSQLQuery(hql).addEntity("al", Airline.class);
        		
        		if(departAirportId > 0) {
        			query.setParameter("departCityId", departAirportId);
        		}
        		
        		if(landAirportId > 0) {
        			query.setParameter("landCityId", landAirportId);
        		} else if (landAirportId == Info.AIRLINE_ALL_LAND_CITY) { 
        			// gAll
        		}
        		
        		if(type == 1 || type == 2) { // Single Trip / Round Trip
        			query.setParameter("type", type);
        		}
        		
        		if (null != departDate & !"".equals(departDate)) {
        			Calendar departureDate = Calendar.getInstance();
        			departureDate.set(Integer.valueOf(departDate.substring(0, 4)), Integer.valueOf(departDate.substring(5, 7)) - 1, Integer.valueOf(departDate.substring(8, 10)), 0, 0, 0);
        			System.out.println(departDate.substring(0, 4) + " - " + departDate.substring(5, 7) + " - " + departDate.substring(8, 10) + "   " + departureDate.getTime());
        			query.setParameter("departureDate", departureDate);
        		}
        		
        		/*
        		if (null != returnDate && !"".equals(returnDate)) {
        			Calendar returnCDate = Calendar.getInstance();
        			returnCDate.set(Integer.valueOf(returnDate.substring(0, 4)), Integer.valueOf(returnDate.substring(5, 7)) - 1, Integer.valueOf(returnDate.substring(8, 10)), 0, 0, 0);
        			query.setParameter("returnCDate", returnCDate);
        		}else
        		{
        			Calendar returnCDate = Calendar.getInstance();
        			returnCDate.set(Integer.valueOf("1000"), Integer.valueOf("01"), Integer.valueOf("01"), 0, 0, 0);
        			query.setParameter("returnCDate", returnCDate);
        		}*/
        		
        		if (type==2) {
        			Calendar returnCDate = Calendar.getInstance();
        			returnCDate.set(Integer.valueOf(returnDate.substring(0, 4)), Integer.valueOf(returnDate.substring(5, 7)) - 1, Integer.valueOf(returnDate.substring(8, 10)), 0, 0, 0);
        			query.setParameter("returnCDate", returnCDate);
        		}
        		
        		query.setMaxResults(1000);
                return query.list();
            }
			
        });
	}

	@Override
	public void deletePastAirline(final String departureEndDate) {
		ht.executeWithNativeSession(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createSQLQuery("delete from Airline where departure_end_date <='"+departureEndDate+"'");
						query.executeUpdate();
					return null;
					}
				});
	}
}
