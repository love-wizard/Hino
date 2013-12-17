package com.hino.dao;

import com.hino.model.GroupHistory;

public interface GroupHistoryDAO {
	public void delete(int id);
	public void update(GroupHistory gh);
	public void delete(GroupHistory gh);
	public void save(GroupHistory gh);
	public GroupHistory getGroupById(int id);
}
