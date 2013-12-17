package com.hino.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.GroupSurveyAnswerDAO;
import com.hino.model.GroupSurveyAnswer;

public class GroupSurveyAnswerDAOImpl implements GroupSurveyAnswerDAO {

	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void update(GroupSurveyAnswer gsa) {
		this.ht.update(gsa);
	}

	@Override
	public void save(GroupSurveyAnswer gsa) {
		this.ht.save(gsa);
	}

	@Override
	public void delete(long id) {
		GroupSurveyAnswer gsa = new GroupSurveyAnswer();
		this.delete(id);
	}

	@Override
	public GroupSurveyAnswer getGroupSurveyAnswerById(long id) {
		return this.ht.get(GroupSurveyAnswer.class, id);
	}

	@Override
	public List<GroupSurveyAnswer> getGroupSurveyAnswerByGroupSurveyId(long gsid) {
		return ht.find(" from group_survey_answers where group_survey_id in (select id from where group_id = " + gsid + ")");
	}
	
	@Override
	public int getGroupSurveyAnswerCountByGroupSurveyId(long gsid) {
		return DataAccessUtils.intResult(ht.find(" select count(*) from group_survey_answers where group_survey_id in (select id from where group_id = " + gsid + ")"));
	}

}
