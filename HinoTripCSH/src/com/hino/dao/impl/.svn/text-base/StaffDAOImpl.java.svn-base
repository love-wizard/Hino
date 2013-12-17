package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.hino.dao.StaffDAO;
import com.hino.model.Staff;
import com.hino.util.PriviledgeParser;

public class StaffDAOImpl implements StaffDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	@Override
	public Staff find(int id) {
		return (Staff)ht.get(Staff.class, id);
	}

	@Override
	public void save(Staff s) {
		ht.save(s);
	}

	@Override
	public Staff findbyStaffno(final String sn) {
		Staff s = null;
		s = ht.executeWithNativeSession(
			new HibernateCallback<Staff>() {
				public Staff doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery("from Staff where staffno=:stn");
					query.setParameter("stn", sn);
				return (Staff)query.uniqueResult();
				}
			});
		return s;
	}

	@Override
	public void update_login_time(final Staff s) {
		ht.executeWithNativeSession(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("update Staff set last_login_time = :time" +
						" where id = :id");
						query.setParameter("time", s.getLast_login_time());
						query.setParameter("id", s.getId());
						query.executeUpdate();
					return null;
					}
				});
		
	}

	@Override
	public List<Staff> getAllStaff() {
		return ht.find("from Staff");
	}

	@Override
	public void update(Staff s) {
		ht.update(s);
	}

	@Override
	public void update_priviledge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Staff s) {
		ht.delete(s);
		
	}

	@Override
	public List<Staff> findbyPartStaffno(final String part, final String pri) {
		return ht.executeWithNativeSession(
				new HibernateCallback< List<Staff>>() {
					@SuppressWarnings("unchecked")
					public  List<Staff> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Staff where staffno like " + "'" + part + "%' and priviledge like '"+pri+"'");
					return query.list();
					}
				});
	}
	
	/**
	 * Check if the given email is unique in database.
	 * @param email email to be checked
	 * @return true: this email is unique and can be used; false: existing email in database
	 */
	@Override
	public boolean checkEmailUnique(final String email) {
		if(DataAccessUtils.intResult(ht.find("select count(*) from Staff where email = '"+email+"'")) > 0)
			return false;
		else
			return true;
	}

	@Override
	public Staff getStaffByEmail(final String email) {
		Staff s = null;
		s = ht.executeWithNativeSession(
			new HibernateCallback<Staff>() {
				public Staff doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery("from Staff where email=:email");
					query.setParameter("email", email);
				return (Staff)query.uniqueResult();
				}
			});
		return s;
	}

	@Override
	public void update_resetcode(final Staff targetStaff) {
		ht.executeWithNativeSession(
			new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery("update Staff set resetcode = :resetcode" +
					" where id = :id");
					query.setParameter("resetcode", targetStaff.getResetcode());
					query.setParameter("id", targetStaff.getId());
					query.executeUpdate();
				return null;
				}
			});
	}

	@Override
	public Staff getStaffByResetcode(final String resetcode) {
		Staff s = null;
		s = ht.executeWithNativeSession(
			new HibernateCallback<Staff>() {
				public Staff doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery("from Staff where resetcode=:resetcode");
					query.setParameter("resetcode", resetcode);
				return (Staff)query.uniqueResult();
				}
			});
		return s;
	}

	@Override
	public void update_password(final Staff targetStaff) {
		ht.executeWithNativeSession(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("update Staff set password = :password" +
						" where id = :id");
						query.setParameter("password", targetStaff.getPassword());
						query.setParameter("id", targetStaff.getId());
						query.executeUpdate();
					return null;
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> findbyPriviledge(final List<Integer> list) {
		final String pri = PriviledgeParser.make_hql_like_string(list);
		return ht.executeFind(
				new HibernateCallback< List<Staff>>() {
					public  List<Staff> doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery("from Staff where priviledge like '"+pri+"'");
					return query.list();
					}
				});
	}

}
