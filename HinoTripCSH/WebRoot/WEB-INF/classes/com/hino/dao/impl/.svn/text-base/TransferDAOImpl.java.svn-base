package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.TransferDAO;
import com.hino.model.Booking;
import com.hino.model.Transfer;

public class TransferDAOImpl implements TransferDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	

	@Override
	public void delete(Transfer t) {
		ht.delete(t);

	}

	@Override
	public Transfer getTransferById(long id) {
		return ht.get(Transfer.class, id, LockMode.PESSIMISTIC_WRITE);
	}

	@Override
	public void save(Transfer t) {
		ht.save(t);
	}

	@Override
	public void update(Transfer t) {
		ht.update(t);
	}

	@Override
	public List<Transfer> complexSearch(final Calendar date, final int sid, final long cid, final Integer[] status, final int page, final int size,final long tid, final long pid) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Criteria crit = session.createCriteria(Transfer.class);
        		if(tid!=-1)
        		{
        			crit.add(Restrictions.eq( "id", tid ));
        			return crit.list();
        		}
        		crit.addOrder(Order.desc("dec_time"));
        		if(status!=null)
        		{
        			if(status.length>0) 
        			{
        				crit.add(Restrictions.in( "status", status ));
        			} else
        			{
        				return new ArrayList<Transfer>();
        			}
        		} 
        		
        		if(sid!=-1)
        		{
        			crit.add(Restrictions.eq( "rep.id", sid ));
        		}
        		
        		if(cid!=-1)
        		{
        			crit.add(Restrictions.eq( "customer.id", sid ));
        		}
        		
        		if(date!=null)
        		{
        			crit.add(Restrictions.eq( "dec_time", date ));
        		}
        		
        		if(pid!=-1)
	    		{
	    			crit.add(Restrictions.eq( "payment_method_id", pid ));
	    		}
        		
        		if(size!=-1&&page!=-1)
        		{
        			crit.setFirstResult(page*size);
        			crit.setMaxResults(size);
        		}
        		
        		
                return crit.list();
            }
			
        });
	}

	@Override
	public long complexSearchCount(final Calendar date, final int sid,final long cid,final Integer[] status, final long tid, final long pid) {
		return ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
	    		Criteria crit = session.createCriteria(Transfer.class);
	    		if(tid!=-1)
	    		{
	    			crit.add(Restrictions.eq( "id", tid ));
        			return ((Long)crit.setProjection(Projections.rowCount()).uniqueResult());
	    		}
	    		
	    		crit.addOrder(Order.desc("dec_time"));
	    		if(status!=null)
        		{
	    			if(status.length>0) 
        			{
        				crit.add(Restrictions.in( "status", status ));
        			} else
        			{
        				return new Long(0);
        			}
        		}
	    		
	    		if(sid!=-1)
	    		{
	    			crit.add(Restrictions.eq( "rep.id", sid ));
	    		}
	    		
	    		if(cid!=-1)
	    		{
	    			crit.add(Restrictions.eq( "customer.id", sid ));
	    		}
	    		
	    		if(date!=null)
	    		{
	    			crit.add(Restrictions.eq( "dec_time", date ));
	    		}
	    		
	    		if(pid!=-1)
	    		{
	    			crit.add(Restrictions.eq( "payment_method_id", pid ));
	    		}
	    		
	    		return ((Long)crit.setProjection(Projections.rowCount()).uniqueResult());
	        }
		});
	}

	@Override
	public Transfer findTransferById(long id) {
		return ht.get(Transfer.class, id);
	}

}
