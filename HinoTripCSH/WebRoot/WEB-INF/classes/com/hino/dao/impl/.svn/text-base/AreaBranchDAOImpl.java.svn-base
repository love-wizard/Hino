package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.AreaBranchDAO;
import com.hino.model.AreaBranch;

public class AreaBranchDAOImpl implements AreaBranchDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(long id) {
		AreaBranch ab = new AreaBranch();
		ab.setId(id);
		ht.delete(ab);

	}

	@Override
	public List<AreaBranch> getAllAreaBranch() {
		return ht.find("from AreaBranch");
	}

	@Override
	public AreaBranch getAreaBranchById(long id) {
		return ht.get(AreaBranch.class, id);
	}

	@Override
	public void save(AreaBranch ab) {
		ht.save(ab);
	}

	@Override
	public void update(AreaBranch ab) {
		ht.update(ab);
	}

	@Override
	public boolean exist(String areaname) {
		return DataAccessUtils.intResult(ht.find("select count(*) from AreaBranch where name='" + areaname+"'"))!=0;
	}

	@Override
	public AreaBranch removeLeaderByStaff(final long id) {
		ht.executeWithNativeSession(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createSQLQuery("update AreaBranch set `leader_id`=NULL where `leader_id` ='"+id+"'");
						query.executeUpdate();
						query = session.createSQLQuery("delete from AreaBranch_Staff where `follows_id` ='"+id+"'");
						query.executeUpdate();
					return null;
					}
				});
		return null;
	}


}
