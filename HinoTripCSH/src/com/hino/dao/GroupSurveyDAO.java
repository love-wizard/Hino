package com.hino.dao;

import java.util.List;

import com.hino.model.GroupSurvey;

public interface GroupSurveyDAO {
	public void update(GroupSurvey gs);
	public void save(GroupSurvey gs);
	public void delete(long id);
	public GroupSurvey getGroupSurveyById(long id);
	public List<GroupSurvey> getGroupSurveyByGroupId(long gid);
	int getGroupSurveyByGroupIdCount(long gid);

}
