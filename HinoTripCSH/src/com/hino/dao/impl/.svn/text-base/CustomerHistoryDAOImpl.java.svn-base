package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.CustomerHistoryDAO;
import com.hino.model.Customer;
import com.hino.model.CustomerHistory;

public class CustomerHistoryDAOImpl implements CustomerHistoryDAO {
	private HibernateTemplate ht;
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public CustomerHistory getCustomerHistory(long id) {
		return ht.get(CustomerHistory.class, id);
	}

	
	@Override
	public void update(CustomerHistory c) {
		ht.update(c);

	}

	@Override
	public void save(CustomerHistory c) {
		ht.save(c);

	}

	@Override
	public List<CustomerHistory> list_customer_history_by_paging(final long cid,
			final int page, final int size) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from CustomerHistory where customerId = :cid ");
                query.setParameter("cid", cid);
                query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }
			
        });
	}

	@Override
	public List<CustomerHistory> list_customer_history(final long cid) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from CustomerHistory where customerId = :cid ");
                query.setParameter("cid", cid);
                return query.list();
            }
			
        });
	}

	@Override
	public List<CustomerHistory> list_customer_history(final String email) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from CustomerHistory where email = :email ");
                query.setParameter("email", email);
                return query.list();
            }
			
        });
	}

	@Override
	public int getCustomerHistoryCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from CustomerHistory"));
	}

	@Override
	public int getCustomerHistoryCount(long cid) {
		return DataAccessUtils.intResult(ht.find("select count(*) from CustomerHistory where customerId = " + cid + ""));
	}

	@Override
	public int getCustomerHistoryCount(String email) {
		return DataAccessUtils.intResult(ht.find("select count(*) from CustomerHistory where email = '" + email + "'"));
	}

	@Override
	public void SaveCustomerHistory(Customer c, CustomerHistory ch) {
		ch.setCustomerId(c.getId());
		ch.setAddress(c.getAddress());
		ch.setCardno(c.getCardno());
		ch.setChinesename(c.getChinesename());
		ch.setCity(c.getCity());
		ch.setCreateDate(Calendar.getInstance());
//		ch.setCreateUserId(c.getDob())
		ch.setDob(c.getDob());
		ch.setEmail(c.getEmail());
		ch.setFirstname(c.getFirstname());
		ch.setGender(c.getGender());
		ch.setLast_login_time(c.getLast_login_time());
		ch.setLastname(c.getLastname());
		ch.setMobile(c.getMobile());
		ch.setPassword(c.getPassword());
		ch.setPhone(c.getPhone());
		ch.setPoint(c.getPoint());
		ch.setPostcode(c.getPostcode());
		ch.setReg_time(c.getReg_time());
		ch.setResetcode(c.getResetcode());
		ch.setUniversity(c.getUniversity());
		ch.setVip_valid(c.getVip_valid());
		
	}
	
	

}
