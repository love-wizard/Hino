package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.CarRequestDAO;
import com.hino.model.Booking;
import com.hino.model.CarRequest;

public class CarRequestDAOImpl implements CarRequestDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		CarRequest cr = new CarRequest();
		cr.setId(id);
		ht.delete(cr);

	}

	@Override
	public CarRequest getCarRequestById(long id) {
		return ht.get(CarRequest.class, id);
	}

	@Override
	public long getCarRequestCount(final Integer[] status) {
		return ht.execute(new HibernateCallback() {
        	@Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
        		Criteria crit = session.createCriteria(CarRequest.class);
        		
        		if(status!=null&&status.length>0)
        		{
        			crit.add(Restrictions.in( "status", status ));
        		} else
        		{
        			return new Long(0);
        		}
        		
                return (Long)crit.setProjection(Projections.rowCount()).uniqueResult();
            }
			
        });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarRequest> list_all_carrequest() {
		return ht.find("from CarRequest");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarRequest> list_carrequest_by_paging(final int start, final int count, final Integer[] status) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		StringBuffer hql = new StringBuffer("select distinct c from CarRequest c");
        		
        		
        		if(status!=null&&status.length>0)
        		{
        			
        			hql.append(" where status in (:status) order by c.date desc");
        		} else
        		{
        			return new ArrayList<Booking>();
        		}
        		
        		Query query = session.createQuery(hql.toString());
        		
        		if(start!=-1&&count!=-1)
        		{
        			query.setFirstResult(start*count);
        			query.setMaxResults(count);
        		}
        		if(status!=null&&status.length>0)
        		{
        			query.setParameterList("status", status);
        		}
        		
                return query.list();
            }
			
        });
	}

	@Override
	public void save(CarRequest v) {
		ht.save(v);
	}

	@Override
	public void update(CarRequest v) {
		ht.update(v);
	}
}
