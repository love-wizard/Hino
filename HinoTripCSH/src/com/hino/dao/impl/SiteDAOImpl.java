package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.SiteDAO;
import com.hino.model.Site;

public class SiteDAOImpl implements SiteDAO{
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	@Override
	public Site getSiteById(long id) {
		return ht.get(Site.class, id);
	}

	@Override
	public void save(Site s) {
		ht.save(s);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Site> list_all_site() {
		return ht.find("from Site");
	}

	@Override
	public int getSiteCount() {
		// TODO Auto-generated method stub
		return DataAccessUtils.intResult(ht.find("select count(*) from Site"));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Site> list_site_by_paging(final int start, final int count) {
	        return ht.executeFind(new HibernateCallback() {
	        	@Override
	            public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                Query query = session.createQuery("from Site");
	                query.setMaxResults(count);
	                query.setFirstResult(count*start);
	                return query.list();
	            }
				
	        });
	}

	@Override
	public void delete(long id) {
		Site s = new Site();
		s.setId(id);
		ht.delete(s);
		
	}

	@Override
	public void update(Site s) {
		ht.update(s);
	}
	
	
}
