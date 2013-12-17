package com.hino.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.InfoDAO;
import com.hino.dao.QQServiceDAO;
import com.hino.model.Info;
import com.hino.model.QQService;
import com.hino.model.Schedule;

public class QQServiceDAOImpl implements QQServiceDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		QQService qqService = this.getQQServiceById(id);
		ht.delete(qqService);

	}

	
	@Override
	public void update(QQService qqService) {
		ht.update(qqService);
	}

	@Override
	public void save(QQService qqService) {
		ht.save(qqService);
	}

	@Override
	public QQService getQQServiceById(long id) {
		if(ht.find("from QQService where id = " + id).size()>0)
		{
			return (QQService) ht.find("from QQService where id = " + id).get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<QQService> getIndexQQService() {
		return ht.find("from QQService order by priority ");
	}

	@Override
	public List<QQService> getAllQQService() {
		return ht.find("from QQService order by priority ");
	}
}
