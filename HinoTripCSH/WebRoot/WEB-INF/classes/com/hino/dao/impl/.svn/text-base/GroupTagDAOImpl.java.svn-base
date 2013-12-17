package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.GroupTagDAO;
import com.hino.model.Group;
import com.hino.model.GroupTag;

public class GroupTagDAOImpl implements GroupTagDAO {

	protected HibernateTemplate ht;	
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void update(GroupTag groupTag) {
		ht.update(groupTag);
	}

	@Override
	public void save(GroupTag groupTag) {
		ht.save(groupTag);

	}

	@Override
	public void delete(long id) {
		GroupTag gTag = new GroupTag();
		gTag = this.getGroupTag(id);
		ht.delete(gTag);
	}
	public void delete(GroupTag gTag) {
		ht.delete(gTag);
	}

	@Override
	public GroupTag getGroupTag(long id) {
		return ht.get(GroupTag.class, id, LockMode.PESSIMISTIC_WRITE);
	}

	@Override
	public List<GroupTag> getGroupTagByGroupId(final long gid) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select {gt.*} from group_tag gt where group_id = " + gid;
				Query query = session.createSQLQuery(hql).addEntity("gt", GroupTag.class);                
                return query.list();
            }
			
        });	
	}
	
	@Override
	public List<GroupTag> getGroupTagByRouteId(final long rid) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select {gt.*} from group_tag gt where route_id = " + rid;
				Query query = session.createSQLQuery(hql).addEntity("gt", GroupTag.class);                
                return query.list();
            }
			
        });	
	}

	@Override
	public void deleteGroupTag(final long gid) {	
//		ht.executeWithNativeSession(
//				new HibernateCallback<Object>() {
//					public Object doInHibernate(Session session) throws HibernateException, SQLException {
//						Query	query = session.createSQLQuery("delete from group_tag where group_id ="+gid+"");
//						query.executeUpdate();			
//						return null;
//					}
//				});
		List<GroupTag> listGroupTag = this.getGroupTagByGroupId(gid);
		for(int i =0 ;i < listGroupTag.size();i++)
		{
			GroupTag gTag = listGroupTag.get(i);
			this.delete(gTag);
		}
				
	}

	@Override
	public void deleteGroupTagByRouteId(long rid) {
		List<GroupTag> listGroupTag = this.getGroupTagByRouteId(rid);
		for(int i = 0; i < listGroupTag.size(); i++)
		{
			GroupTag gTag = listGroupTag.get(i);
			this.delete(gTag);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getTop10HotestGroups() {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Calendar now = Calendar.getInstance();

        		String hql = "select {g.*} from t_group g join "; 
        		hql = hql + " (select group_id, count(*) hot_count ";
        		hql = hql + " from Booking where year(booking_time) = " + now.get(Calendar.YEAR) + "  and week(booking_time) = " + (now.get(Calendar.WEEK_OF_YEAR) - 1);
        		hql = hql + " group by group_id) c ";
        		hql = hql + " on g.id = c.group_id order by hot_count desc limit 0, 10";

        		System.out.println(hql);
				Query query = session.createSQLQuery(hql).addEntity("g", Group.class);                
                return query.list();
            }
			
        });	
	}


}
