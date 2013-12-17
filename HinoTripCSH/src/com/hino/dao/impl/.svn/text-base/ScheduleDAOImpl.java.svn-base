package com.hino.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.ScheduleDAO;
import com.hino.model.Schedule;
import com.hino.model.Site;

public class ScheduleDAOImpl implements ScheduleDAO {
	private HibernateTemplate ht;
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public Schedule getScheduleById(long routeId,long sequenceId) {
		return (Schedule) ht.find("from Schedule where route_id = " + routeId + " and sequence_id = " + sequenceId).get(0);
	}

	@Override
	public void save(Schedule schedule) {
		ht.save(schedule);
	}

	@Override
	public List<Schedule> list_route_schedule(long routeId) {
		return ht.find("from Schedule where route_id = " + routeId + " order by sequence_id ");

	}

	@Override
	public void delete(long routeId, long sequenceId) {
		Schedule s = this.getScheduleById(routeId, sequenceId);
		ht.delete(s);

	}

	@Override
	public void update(Schedule schedule) {
		ht.update(schedule);

	}

	@Override
	public int getNextScheduleSequenceId(long routeId) {
		return DataAccessUtils.intResult(ht.find("select COALESCE(max(sequence_id)+1,1) from Schedule where route_id = " + routeId));
	}

	@Override
	public void delete(long id) {
		Schedule entity = new Schedule();
		//entity.setId(id);
		entity.setId(id);
		ht.delete(entity);
	}

	@Override
	public Schedule getScheduleById(long id) {
		return (Schedule) ht.find("from Schedule where id = " + id).get(0) ;
	}

	@Override
	public int list_route_schedule_count(long routeId) {
		return DataAccessUtils.intResult(ht.find("select count(*) from Schedule where route_id = " + routeId));	}

}
