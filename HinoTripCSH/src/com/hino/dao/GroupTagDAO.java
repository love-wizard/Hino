package com.hino.dao;

import java.util.List;

import com.hino.model.Group;
import com.hino.model.GroupTag;

public interface GroupTagDAO {
	public void update(GroupTag groupTag);
	public void save(GroupTag groupTag);
	public void delete(long id);
	public void deleteGroupTag(long gid);
	public GroupTag getGroupTag(long id);
	public List<GroupTag> getGroupTagByGroupId(long gid);
	public void deleteGroupTagByRouteId(long rid);
	public List<GroupTag> getGroupTagByRouteId(long rid);
	public List<Group> getTop10HotestGroups();
}
