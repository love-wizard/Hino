package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.ServiceOptionDAO;
import com.hino.model.ServiceOption;

public class ServiceOptionDAOImpl implements ServiceOptionDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	@Override
	public ServiceOption getServiceOptionById(long id) {
		return ht.get(ServiceOption.class, id);
	}

	@Override
	public void save(ServiceOption so) {
		ht.save(so);
	}

	@Override
	public void delete(long id) {
		ServiceOption so = new ServiceOption();
		so.setId(id);
		ht.delete(so);
	}

	@Override
	public void update(ServiceOption so) {
		ht.update(so);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceOption> listServiceOptions() {
		return (List<ServiceOption>)ht.find("from ServiceOption");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceOption> listServiceOptionsByPaging(final int start,
			final int count) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from ServiceOption");
                query.setMaxResults(count);
                query.setFirstResult(count * start);
                return query.list();
            }
			
        });
	}

	@Override
	public int getServiceOptionCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from ServiceOption"));
	}

	@Override
	public List<ServiceOption> listDGServiceOptions(long dgid) {
		//TODO
		return (List<ServiceOption>)ht.find("from ServiceOption so");
	}

	@Override
	public int getDGServiceOptionCount(long dgId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
