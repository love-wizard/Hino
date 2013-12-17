package com.hino.dao.impl;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.GroupProfitDAO;
import com.hino.model.GroupProfit;

public class GroupProfitDAOImpl implements GroupProfitDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		GroupProfit gp = new GroupProfit();
		ht.delete(gp);
	}

	@Override
	public GroupProfit findGroupProfitById(long id) {
		ht.get(GroupProfit.class, id);
		return null;
	}

	@Override
	public void save(GroupProfit gp) {
		ht.save(gp);

	}

	@Override
	public void update(GroupProfit gp) {
		ht.update(gp);

	}

}
