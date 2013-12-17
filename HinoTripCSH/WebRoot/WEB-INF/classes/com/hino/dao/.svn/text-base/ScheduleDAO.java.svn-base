package com.hino.dao;

import java.util.List;

import com.hino.model.Route;
import com.hino.model.Schedule;

public interface ScheduleDAO {

	public Schedule getScheduleById(long routeId, long sequenceId);
	public Schedule getScheduleById(long id);

	public void save(Schedule schedule);
	public List<Schedule> list_route_schedule(long routeId);
	public int list_route_schedule_count(long routeId);
	
	public void delete(long routeId, long sequenceId);
	public void delete(long id);
	
	void update(Schedule schedule);
	public int getNextScheduleSequenceId(long routeId);
}
