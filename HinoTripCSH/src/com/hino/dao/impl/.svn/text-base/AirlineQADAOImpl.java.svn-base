package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.AirlineQADAO;
import com.hino.dao.SiteDAO;
import com.hino.model.AirlineQA;
import com.hino.model.Site;

public class AirlineQADAOImpl implements AirlineQADAO{
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	@Override
	public AirlineQA getAirlineQAById(long id) {
		return ht.get(AirlineQA.class, id);
	}

	@Override
	public void save(AirlineQA s) {
		ht.save(s);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineQA> list_all_AirlineQA() {
		return ht.find("from AirlineQA");
	}

	@Override
	public int getAirlineQACount() {
		// TODO Auto-generated method stub
		return DataAccessUtils.intResult(ht.find("select count(*) from AirlineQA"));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineQA> list_AirlineQA_by_paging(final int start, final int count) {
	        return ht.executeFind(new HibernateCallback() {
	        	@Override
	            public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                Query query = session.createQuery("from AirlineQA order by area");
	                query.setMaxResults(count);
	                query.setFirstResult(count*start);
	                return query.list();
	            }
				
	        });
	}

	@Override
	public void delete(long id) {
		AirlineQA s = new AirlineQA();
		s.setId(id);
		ht.delete(s);
		
	}

	@Override
	public void update(AirlineQA s) {
		ht.update(s);
	}

	@Override
	public List<AirlineQA> getAirlineQAsByArea(final String area) {
		 return ht.executeFind(new HibernateCallback() {
	        	@Override
	            public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                Query query = session.createQuery("from AirlineQA where trim(area) = :area order by area");
	                query.setParameter("area", area);
	                return query.list();
	            }
				
	        });
	}

	@Override
	public List<AirlineQA> getAllAirlineQAs() {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from AirlineQA order by area");
                return query.list();
            }
			
        });
	}
	
	
}
