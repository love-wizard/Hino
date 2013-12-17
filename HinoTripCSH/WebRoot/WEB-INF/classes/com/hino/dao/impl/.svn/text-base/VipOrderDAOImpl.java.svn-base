package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.VipOrderDAO;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.VipOrder;
import com.hino.util.Info;

public class VipOrderDAOImpl implements VipOrderDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	@Override
	public void delete(VipOrder vo) {
		ht.delete(vo);

	}



	@Override
	public VipOrder getVipOrderById(long id) {
		return ht.get(VipOrder.class, id, LockMode.PESSIMISTIC_WRITE);
	}

	@Override
	public List<VipOrder> list_by_paging(final Integer status[], final int page, final int size, final String email) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Criteria crit = session.createCriteria(VipOrder.class);
        		crit.addOrder(Order.asc("order_time"));
        		
        		if(status!=null&&status.length>0)
        		{
        			crit.add(Restrictions.in( "status", status ));
        		} else
        		{
        			return new ArrayList<VipOrder>();
        		}

        		if(email!=null)
        		{
        			crit.add(Restrictions.eq("email", email));
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
	public void save(VipOrder vo) {
		ht.save(vo);

	}

	@Override
	public void update(VipOrder vo) {
		ht.update(vo);

	}

	@Override
	public VipOrder viewVipOrderById(long id) {
		return ht.get(VipOrder.class, id);
	}

	@Override
	public Long count_order(final Integer[] status, final String email) {
		return ht.execute(new HibernateCallback() {
        	@Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
        		Criteria crit = session.createCriteria(VipOrder.class);
        		crit.setFetchMode("group", FetchMode.SELECT);
        		
        		if(status!=null&&status.length>0)
        		{
        			crit.add(Restrictions.in( "status", status ));
        		} else
        		{
        			return new Long(0);
        		}
        		
        		if(email!=null)
        		{
        			crit.add(Restrictions.eq("email", email));
        		}
        		
                return (Long)crit.setProjection(Projections.rowCount()).uniqueResult();
            }
			
        });
}

	@Override
	public List<VipOrder> getVipOrderByEmail(final String email) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from VipOrder where email ='"+email+"'");
                return query.list();
        	}
		});
	}
}
