package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.DiyRouteDAO;
import com.hino.model.DiyRoute;

public class DiyRouteDAOImpl implements DiyRouteDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public DiyRoute getDiyRouteById(long id) {
		return ht.get(DiyRoute.class, id);
		
	}

	@Override
	public void save(DiyRoute dr) {
		ht.save(dr);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiyRoute> list_all_diy_route() {
		
		return (List<DiyRoute>)ht.find("from DiyRoute");
	}

	@Override
	public int getDiyRouteCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from DiyRoute"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiyRoute> list_diy_route_by_paging(final int start,final int count) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from DiyRoute");
                query.setMaxResults(count);
                query.setFirstResult(count*start);
                return query.list();
            }
			
        });
	}

	/*@Override
	public void delete(long id) {
		Route r = new Route();
		r.setId(id);
		ht.delete(r);
	}*/

	/*@Override
	public void update(Route r) {
		ht.update(r);
		
	}*/

}
