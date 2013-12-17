package com.hino.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.GroupSurveyDAO;
import com.hino.model.GroupSurvey;

public class GroupSurveyDAOImpl implements GroupSurveyDAO {

	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	
	@Override
	public void update(GroupSurvey gs) {
		ht.update(gs);

	}

	@Override
	public void save(GroupSurvey gs) {
		ht.save(gs);

	}

	@Override
	public void delete(long id) {
		GroupSurvey gs = new GroupSurvey();
		gs.setId(id);
		ht.delete(gs);

	}

	@Override
	public GroupSurvey getGroupSurveyById(long id) {
		return ht.get(GroupSurvey.class, id);
	}

	@Override
	public List<GroupSurvey> getGroupSurveyByGroupId(long gid) {
		return ht.find(" from group_survey where group_id = " + gid);
	}
	
	@Override
	public int getGroupSurveyByGroupIdCount(long gid) {
		return DataAccessUtils.intResult(ht.find("select count(*) from group_survey where group_id = " + gid));
	}

}
