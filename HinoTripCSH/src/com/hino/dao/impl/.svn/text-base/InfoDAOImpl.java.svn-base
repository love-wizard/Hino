package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.InfoDAO;
import com.hino.model.Info;
import com.hino.model.Schedule;

public class InfoDAOImpl implements InfoDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	@Override
	public void update(Info info) {
		ht.update(info);	
	}

	@Override
	public void save(Info info) {
		ht.save(info);

	}

	@Override
	public void delete(long id) {
		Info info = this.getInfoById(id);
		ht.delete(info);

	}

	@Override
	public Info getInfoById(long id) {
		if(ht.find("from Info where id = " + id).size()>0)
		{
			return (Info) ht.find("from Info where id = " + id).get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<Info> getIndexInfo() {
		return ht.find("from Info where source = 'INDEX'");
	}

	@Override
	public List<Info> getIndexNavigationFamousPlace() {
		return ht.find("from Info where source = 'FAMOUS_PLACE'");
	}

	@Override
	public List<Info> getDefaultSearchKeywords() {
		return ht.find("from Info where source = 'DEFAULT_KEYWORDS'");
	}

	@Override
	public List<Info> getInfo(final String source) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Info where source =  = '"+source+"'");
                return query.list();
            }
        });
	}
}
