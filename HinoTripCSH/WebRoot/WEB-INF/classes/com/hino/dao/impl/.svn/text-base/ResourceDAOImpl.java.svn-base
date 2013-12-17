package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.ResourceDAO;
import com.hino.model.Resource;
import com.hino.model.Staff;
import com.hino.util.Info;

public class ResourceDAOImpl implements ResourceDAO{
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	
	
	@Override
	public Resource getResourceById(long id) {
		// TODO Auto-generated method stub
		return ht.get(Resource.class, id);
	}

	@Override
	public void save(Resource r) {
		ht.save(r);
		
	}

	@Override
	public void delete(long id) {
		Resource r = new Resource();
		r.setId(id);
		ht.delete(r);
		
	}

	@Override
	public void update(Resource r) {
		ht.update(r);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getAllResource() {
		return ht.find("from Resource order by category");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getResourceByCat(int c) {
		return ht.find("from Resource where category='" + c +"'");
	}

	@Override
	public int getSalesMarketReportCount(Staff curStaff) {
		return DataAccessUtils.intResult(
				ht.find("select count(*) from Resource where category='" + Info.FILE_CATEGORY_INDEX_REP_MARKET +"'"+
						" and author_id='" + curStaff.getId() +"'"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> list_sales_market_report_by_paging(final Staff curStaff,
			final int page, final int size) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Resource where category='" + Info.FILE_CATEGORY_INDEX_REP_MARKET +"'"+
						" and author_id='" + curStaff.getId() +"'");
                query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }
			
        });
	}

	@Override
	public int getGenMarketReportCount() {
		return DataAccessUtils.intResult(
				ht.find("select count(*) from Resource where category='" + Info.FILE_CATEGORY_INDEX_REP_MARKET +"'"));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> list_gen_market_report_by_paging(final int page, final int size) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Resource where category='" + Info.FILE_CATEGORY_INDEX_REP_MARKET +"'");
                query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }
			
        });
	}

}
