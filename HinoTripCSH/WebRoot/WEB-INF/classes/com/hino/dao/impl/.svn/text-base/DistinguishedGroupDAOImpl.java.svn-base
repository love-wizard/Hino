package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.DistinguishedGroupDAO;
import com.hino.model.DistinguishedGroup;

public class DistinguishedGroupDAOImpl implements DistinguishedGroupDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	@Override
	public DistinguishedGroup getDistinguishedGroupById(long id) {
		return ht.get(DistinguishedGroup.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DistinguishedGroup> listAllDistinguishedGroup() {
		return (List<DistinguishedGroup>)ht.find("from DistinguishedGroup");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DistinguishedGroup> listDistinguishedGroupByPaging(final int start,
			final int count) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from DistinguishedGroup");
                query.setMaxResults(count);
                query.setFirstResult(count * start);
                return query.list();
            }
			
        });
	}

	@Override
	public int getDistinguishedGroupCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from DistinguishedGroup"));
	}

	@Override
	public void delete(long id) {
		DistinguishedGroup dg = new DistinguishedGroup();
		dg.setId(id);
		ht.delete(dg);
	}

	@Override
	public void update(DistinguishedGroup dg) {
		ht.update(dg);
	}

	@Override
	public void save(DistinguishedGroup dg) {
		ht.save(dg);
	}

}
