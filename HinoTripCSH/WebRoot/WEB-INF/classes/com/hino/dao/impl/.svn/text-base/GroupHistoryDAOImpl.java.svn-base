package com.hino.dao.impl;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.GroupHistoryDAO;
import com.hino.model.Group;
import com.hino.model.GroupHistory;

public class GroupHistoryDAOImpl implements GroupHistoryDAO {
	protected HibernateTemplate ht;	
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(int id) {
		GroupHistory gh = new GroupHistory();
		gh.setId(id);
		ht.delete(gh);

	}

	@Override
	public void update(GroupHistory gh) {
		ht.update(gh);
	}

	@Override
	public void delete(GroupHistory gh) {
		ht.delete(gh);
	}

	@Override
	public void save(GroupHistory gh) {
		ht.save(gh);
	}

	@Override
	public GroupHistory getGroupById(int id) {
		return ht.get(GroupHistory.class, id, LockMode.PESSIMISTIC_WRITE);
	}

}
