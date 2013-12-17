package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.VoucherDAO;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Voucher;

public class VoucherDAOImpl implements VoucherDAO{

	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		Voucher v = new Voucher();
		v.setId(id);
		ht.delete(v);
		
	}

	@Override
	public Voucher getVoucherByCode(final String code) {
		return ht.executeWithNativeSession(
				new HibernateCallback<Voucher>() {
					public Voucher doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Voucher where code = :code");
						query.setParameter("code", code);
						return (Voucher)query.uniqueResult();
					}
		});
	}

	@Override
	public Voucher getVoucherById(long id) {
		return ht.get(Voucher.class, id);
	}

	@Override
	public void save(Voucher v) {
		ht.save(v);
		
	}

	@Override
	public void update(Voucher v) {
		ht.update(v);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voucher> list_voucher(final String keywords, final String type,final Long no, final int size,
			final int page) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Criteria crit = session.createCriteria(Voucher.class);
        		
        		if(keywords!=null)
        		{
        			crit.add(Restrictions.eq( "code", keywords ));
        		} else
        		{
        			return new Long(0);
        		}
        		
        		if(type!=null)
        		{
        			crit.add(Restrictions.eq( "match_type", type ));
        		}
        		
        		if(no!=null)
        		{
        			crit.add(Restrictions.eq("id", no));
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

}
