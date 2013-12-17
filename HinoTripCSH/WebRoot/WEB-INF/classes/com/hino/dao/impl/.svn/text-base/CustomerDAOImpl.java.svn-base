package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.hino.dao.CustomerDAO;
import com.hino.model.Customer;

public class CustomerDAOImpl implements CustomerDAO{
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void save(Customer c) {
		ht.save(c);
	}

	@Override
	public void update_basic_info(Customer c) {
		
	}

	@Override
	public void update_login_time(final Customer c) {
		ht.executeWithNativeSession(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("update Customer set last_login_time = :time" +
						" where email = :email");
						query.setParameter("time", c.getLast_login_time());
						query.setParameter("email", c.getEmail());
						query.executeUpdate();
						return null;
					
					}
		});
		
		
	}

	@Override
	public void update_password(final Customer c) {
		ht.executeWithNativeSession(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("update Customer set password = :password" +
						" where email = :email");
						query.setParameter("password", c.getPassword());
						query.setParameter("email", c.getEmail());
						query.executeUpdate();
						return null;
					
					}
		});
		
	}

	@Override
	public Customer findCustomerByEmail(final String email) {
		return ht.executeWithNativeSession(
				new HibernateCallback<Customer>() {
					public Customer doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Customer where email = :email");
						query.setParameter("email", email);
						return (Customer)query.uniqueResult();
					}
		});
	}

	/*
	public ArrayList<Customer> getCustomerWhere(String hql) {
		Customer c;
		ArrayList<Customer> al = new ArrayList<Customer>();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Customer");
		

		al = (ArrayList<Customer>)query.list();
		session.getTransaction().commit();
		return al;
	}
	*/
	
	/*
	public ArrayList<Customer> getCustomerByRegTime() {
		Customer c;
		ArrayList<Customer> al = new ArrayList<Customer>();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Customer where reg_time>=:begin and reg_time<:end order by email");
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.set(2010, 10, 15);
		c2.set(2010, 11, 28);
		query.setParameter("begin", c1);
		query.setParameter("end", c2);
		System.out.println(c1.getTime());
		al = (ArrayList<Customer>) query.list();
		session.getTransaction().commit();
		return al;
	}
	*/
	
	@Override
	public Customer find(long id) {
		return ht.get(Customer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> list_customer_by_paging(final int page, final int size) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Customer");
                query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }
			
        });
	}

	@Override
	public int getCustomerCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from Customer"));
	}

	@Override
	public boolean exist_email(String email) {
		int a = DataAccessUtils.intResult(ht.find("select count(*) from Customer where email='"+ email + "'"));
		return (a==0)?false:true;
	}


	@Override
	public void update_resetcode(final Customer c) {
		ht.executeWithNativeSession(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("update Customer set resetcode = :resetcode" +
						" where email = :email");
						query.setParameter("resetcode", c.getResetcode());
						query.setParameter("email", c.getEmail());
						query.executeUpdate();
						return null;
					
					}
		});
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer getCustomerByResetcode(String resetcode) {
		return DataAccessUtils.uniqueResult(ht.find("from Customer where resetcode='"+resetcode+"'"));
	}
	
	@Override
	public void update(Customer c) {
		ht.update(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> list_all_customer() {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Customer");
                return query.list();
            }
			
        });
	}

	@Override
	public Customer getCustomerById(long cid) {
		return ht.get(Customer.class, cid, LockMode.PESSIMISTIC_WRITE);
	}
	
	@Override
	public void cancelVip(final Customer c) {
		ht.executeWithNativeSession(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("update Customer set vip_valid = null" +
						" where email = :email");
						query.setParameter("email", c.getEmail());
						query.executeUpdate();
						return null;
					
					}
		});
		
	}

}
