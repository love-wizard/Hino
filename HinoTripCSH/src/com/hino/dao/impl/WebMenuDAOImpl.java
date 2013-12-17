package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.WebMenuDAO;
import com.hino.model.WebMenu;

public class WebMenuDAOImpl implements WebMenuDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WebMenu> getAllWebMenu() {
		return ht.find("from WebMenu");
	}

	@Override
	public WebMenu getWebMenuById(long id) {
		return ht.get(WebMenu.class, id);
	}

	@Override
	public void save(WebMenu wm) {
		ht.save(wm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WebMenu> list_menu_by_paging(final int start, final int count) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from WebMenu");
                query.setMaxResults(count);
                query.setFirstResult(count*start);
                return query.list();
            }
			
        });
	}

	@Override
	public int getMenuCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from WebMenu"));
	}

	@Override
	public void delete(long id) {
		WebMenu targetMenu = new WebMenu();
		targetMenu.setId(id);
		ht.delete(targetMenu);
	}
	
	@Override
	public void update(WebMenu s) {
		ht.update(s);
	}

	@Override
	public void create_index_menu() {
		ht.executeWithNativeSession(
			new HibernateCallback<WebMenu>() {
				public WebMenu doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createSQLQuery("INSERT INTO `WebMenu` "+
											"(`id` ,`description` ,`imageUrl`, `name` )"+
											"VALUES ('-1', 'ErrorForHinoIndex', '', 'ErrorForHinoIndex');");
					query.executeUpdate();
				return null;
				}
			});
	}

}
