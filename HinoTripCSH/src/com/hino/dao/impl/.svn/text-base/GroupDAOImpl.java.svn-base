package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.GroupDAO;
import com.hino.dao.GroupHistoryDAO;
import com.hino.dto.GroupSumByPickupDto;
import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.GroupHistory;
import com.hino.util.Info;
import com.hino.util.Page;
import com.hino.util.TimeFormater;

public class GroupDAOImpl implements GroupDAO {
	
	protected HibernateTemplate ht;	
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public void delete(int id) {
		Group g = new Group();
		g.setId(id);
		ht.delete(g);
		
	}

	@Override
	public List<Group> getAllGroup() {
		return ht.find("from Group");
	}

	@Override
	public Group getGroupById(int id) {
		return ht.get(Group.class, id, LockMode.PESSIMISTIC_WRITE);
	}

	@Override
	public void save(Group g) {
		ht.save(g);

	}

	@Override
	public void update(Group g) {
		ht.update(g);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_guide_group_by_paging(final int guideid, final int page, final int size) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Group where (guide1.id = "+guideid+
                		" or guide2.id = "+guideid+") and guide_ready = '1'");
				query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }
			
        });
	}

	@Override
	public int getGuideGroupCount(int guideid) {
		return DataAccessUtils.intResult(ht.find("select count(*) from Group where (guide1.id = "+guideid+
				" or guide2.id = "+guideid+") and guide_ready='1'"));
	}

	@Override
	public int getAllGroupCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from Group"));
	}


	// Ken Chen 2012/10/03 add month parameter 
	@Override
	public List<Group> list_group_by_status_paging_ordering(final int status, final String month,
			final int page, final int size, final String orderingAttr, final boolean isAscending) {
		final String ordering = (isAscending)?"asc":"desc";
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "from Group where 1=1 ";
        		
        		if(status!=Info.GS_ALL)
        		{
        			hql = hql + " and status = " + status;
        		}
        		
        		if(!(null == month || month.equals("")))
        		{
        			hql = hql + " and depart_date like '" + month + "%'";
        		}
        		hql = hql + "order by "+orderingAttr+" " + ordering ;
        		
                Query query = session.createQuery(hql);
				query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }
        });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_all_group_by_paging(final int page, final int size,
			final String orderingAttr, final boolean isAscending) {
		final String ordering = (isAscending)?"asc":"desc";
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Group order by "+orderingAttr+" " + ordering );
				query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }
			
        });
	}

	@Override
	public List<Group> list_group_by_viewable(final String orderingAttr, boolean isAscending) {
		final String ordering = (isAscending)?"asc":"desc";
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Group where status ='"+Info.GS_OPENNING+"' and internalView=true order by "+orderingAttr+" " + ordering);
                
                return query.list();
            }
        });
	}

	@Override
	public int count_group_by_status(int status) {
		return DataAccessUtils.intResult(ht.find("select count(*) from Group where status='"+status+"'"));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_group_guide_not_ready() {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Group where status ='"+Info.GS_OPENNING+"' and guide_ready='0' order by depart_date");
                return query.list();
            }
			
        });
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_reviewable_group(final int page, final int size,
			final String orderingAttr, final boolean isAscending) {
		final String ordering = (isAscending)?"asc":"desc";
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Group where status ='"+Info.GS_PROCESSING+"' or status ='"+Info.GS_FINISHED+"' order by "+orderingAttr+" " + ordering );
				query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }
			
        });
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_external_viewable_group(final int routeid) {
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Group where status =1 and externalView = '1' and route_id = '"+routeid+"' order by depart_date asc");
                return query.list();
            }
			
        });
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_external_viewable_group_original(final int routeid) {
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select {g.*} from t_group g where g.status =1 and g.externalView = '1' ";
					hql = hql + "and g.route_id in (select r.id from Route r where r.original_route_id = (select ori.original_route_id from Route ori where ori.id = " + routeid + ")) order by g.depart_date asc";
				Query query = session.createSQLQuery(hql).addEntity("g", Group.class);                
                return query.list();
            }
			
        });
	}
	
	@Override
	public List<Group> list_external_viewable_group_original(final int routeid, final Calendar start, final Calendar end) {
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select {g.*} from t_group g where g.status =1 and g.externalView = '1' ";
        			hql = hql + "and g.route_id in (select r.id from Route r where r.original_route_id = (select ori.original_route_id from Route ori where ori.id = " + routeid + ")) and g.depart_date between :start and :end order by g.depart_date asc";
        		Query query = session.createSQLQuery(hql).addEntity("g", Group.class);
                query.setParameter("start", start);
        		query.setParameter("end", end);
                return query.list();
            }
			
        });
	}
	
	@Override
	public List<Group> list_ext_view_ori_group_by_priority(final int routeid, final Calendar start, final Calendar end) {
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select {g.*} from t_group g where g.status =1 and g.externalView = '1' ";
        			hql = hql + "and g.route_id in (select r.id from Route r where r.original_route_id = (select ori.original_route_id from Route ori where ori.id = " + routeid + ")) and g.depart_date between :start and :end order by g.priority desc, g.depart_date asc";
        		Query query = session.createSQLQuery(hql).addEntity("g", Group.class);
                query.setParameter("start", start);
        		query.setParameter("end", end);
                return query.list();
            }
			
        });
	}
	
	@Override
	public List<Group> list_external_viewable_group(final int routeid, final Calendar start, final Calendar end) {
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Group where status =1 and externalView = '1' and route_id = '"+routeid+"' and depart_date between :start and :end order by depart_date asc");
                query.setParameter("start", start);
        		query.setParameter("end", end);
                return query.list();
            }
			
        });
	}

	@SuppressWarnings("unchecked")
	@Override
	//	Ken Chen - 2012-09-11 - 获取外部可以预定的团
	public List<Group> list_group_by_date(final Calendar start, final Calendar end,
			final int status, final int max) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("from Group where status=:status and depart_date between :start and :end and externalBookable = 1 order by depart_date asc");
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		query.setParameter("status", status);
        		query.setMaxResults(max);
                return query.list();
            }
			
        });
	}
	
	/**
	 * Created by Ken Chen - 2012/09/13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getCountGroupInfo(final Calendar start, final Calendar end,
			final int status, final String groupByColumn, final String orderByColumn) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("select count(*) as count, " + groupByColumn + " from Group where status=:status and depart_date between :start and :end and externalBookable = 1 group by "+ groupByColumn +" order by " + orderByColumn + " asc");        		
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		query.setParameter("status", status);
                return query.list();
            }
			
        });
	}
	
	/**
	 * Created by Ken Chen - 2012/09/13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_group_by_route_and_date(final int iRouteId,final Calendar start, final Calendar end,
			final int status, final int max, final int orderByPrice, final String destination) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "from Group where route_id=:route_id ";
        		if(status != - 1) {
        			hql = hql + "and status=:status ";
        		}
        		hql = hql + "and lcase(pickup_info) like :dest and price > 0 ";
        		hql = hql + "and depart_date between :start and :end and externalBookable = 1 "; 
        		hql = hql + "order by ";
        		if (orderByPrice == 1) {
        			hql = hql + "price asc, ";
        		}else if (orderByPrice == 2){
        			hql = hql + "price asc, ";
        		}
        			
        		hql = hql + "depart_date asc";
        		Query query = session.createQuery(hql);
        		query.setParameter("route_id", iRouteId);
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		if(status != - 1) {
        			query.setParameter("status", status);
        		}
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		query.setMaxResults(max);
                return query.list();
            }
			
        });
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_group_by_route_and_date_and_price(final int iRouteId,final Calendar start, final Calendar end,
			final int status, final int max, final int orderByPrice, final String destination,
			final Double lprice, final Double uprice) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String sPriceRange = " and (price > 0) ";
        		if(lprice >= 0 && uprice > 0) {
        			sPriceRange = " and (price between " + lprice + " and " + uprice + ") ";
        		} else if (lprice >= 0 && uprice < 0) {
        			sPriceRange = " and (price > " + lprice + ") ";
        		} else if (lprice < 0 && uprice >= 0) {
        			sPriceRange = " and (price < " + uprice + ") ";
        		}
        		
        		String hql = "from Group where route_id=:route_id ";
        		if(status != - 1) {
        			hql = hql + "and status=:status ";
        		}
        		hql = hql + "and lcase(pickup_info) like :dest ";
        		hql = hql + sPriceRange;
        		hql = hql + "and depart_date between :start and :end and externalBookable = 1 "; 
        		hql = hql + "order by ";
        		if (orderByPrice == 1) {
        			hql = hql + "price asc, ";
        		}else if (orderByPrice == 2){
        			hql = hql + "price asc, ";
        		}
        			
        		hql = hql + "depart_date asc";
        		Query query = session.createQuery(hql);
        		query.setParameter("route_id", iRouteId);
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		if(status != - 1) {
        			query.setParameter("status", status);
        		}
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		query.setMaxResults(max);
                return query.list();
            }
			
        });
	}
	
	
	/**
	 * Created by Devon King - 2012/09/08
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> pagingGroupByDate(final Calendar start, final Calendar end,
			final Page page, final String destination) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("from Group where status = " + Info.GS_OPENNING + " and depart_date between :start and :end and externalBookable = 1 and lcase(pickup_info) like :dest order by depart_date asc");
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		long totalCount = query.list().size();
        		page.setTotalCount(totalCount);
        		
        		query.setMaxResults(page.getSize());
        		query.setFirstResult((page.getCurrentPage() > 0? page.getCurrentPage() - 1: 0) * page.getSize());
                return query.list();
            }
			
        });
	}

	@Override
	public List<Group> list_group_status_profit(final Integer[] status, final Boolean[] ready, final int page, final int size) {
		if(status==null||status.length<1||ready==null||ready.length<1)
		{
			return new ArrayList<Group>();
		}
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("from Group where status in (:status) and profit_ready in (:ready) order by depart_date asc");
        		query.setParameterList("status", status);
        		query.setParameterList("ready", ready);
        		query.setFirstResult(page*size);
        		query.setMaxResults(size);
                return query.list();
            }
        });
	}

	@Override
	public long list_group_status_profit_count(final Integer[] status,final Boolean[] ready) {
		if(status==null||status.length<1||ready==null||ready.length<1)
		{
			return 0;
		}
		return ht.execute(new HibernateCallback() {
        	@Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("select count(*) from Group where status in (:status) and profit_ready in (:ready) order by depart_date asc");
        		query.setParameterList("status", status);
        		query.setParameterList("ready", ready);
        		return (Long)query.uniqueResult();
            }
			
        });
	}

	@Override
	public void delete(Group g) {
		ht.delete(g);
	}

	@Override
	public Group viewGroupById(int id) {
		return ht.get(Group.class, id);
	}

	@Override
	public long list_external_viewable_group_count() {
		return ht.execute(new HibernateCallback() {
        	@Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("select count(*) from Group where status ='"+Info.GS_OPENNING+"' and externalView=true");
        		return (Long)query.uniqueResult();
            }
        });
	}

	@Override
	public List<Group> list_external_viewable_group_paging(final int page, final int size,
			final String orderingAttr, final boolean isAscending) {
		final String ordering = (isAscending)?"asc":"desc";
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Group where status ='"+Info.GS_OPENNING+"' and externalView=true order by "+orderingAttr+" " + ordering );
				query.setMaxResults(size);
                query.setFirstResult(size*page);
                return query.list();
            }		
        });
	}

	@Override
	public List<Group> getGoGroup(final int size, final int type) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("from Group where ext_groupon=:ext_group and groupon_end_time between :start and :end order by groupon_end_time asc");
        		query.setParameter("ext_group", true);
        		
        		if (type==0)
        		{
        			Calendar start = Calendar.getInstance();
        			query.setParameter("start", start);
        			Calendar end = Calendar.getInstance();
        			end.add(end.YEAR, 2);
        			query.setParameter("end", end);
        		} else
        		{
        			Calendar start = Calendar.getInstance();
        			
        			Calendar end = Calendar.getInstance();
        			end.add(end.YEAR, -2);
        			query.setParameter("start", end);
        			query.setParameter("end", start);
        		}
        		if (size!=-1)
        		{
        			query.setMaxResults(size);
        		}
                return query.list();
            }
			
        });
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroupsByDateRange(final int size, final Calendar start, final Calendar end) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("from Group where ext_groupon=:ext_group and (depart_date between :start and :end) order by groupon_end_time asc");
        		query.setParameter("ext_group", true);
    			query.setParameter("start", start);
    			query.setParameter("end", end);
    		
        		if (size!=-1)
        		{
        			query.setMaxResults(size);
        		}
        		
                return query.list();
            }
			
        });
	}

	@Override
	public List<Group> getGoGroup(final Page page, final int type) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("from Group where ext_groupon=:ext_group and groupon_end_time between :start and :end order by groupon_end_time asc");
        		query.setParameter("ext_group", true);
        		
        		if (type==0)
        		{
        			Calendar start = Calendar.getInstance();
        			query.setParameter("start", start);
        			Calendar end = Calendar.getInstance();
        			end.add(end.YEAR, 2);
        			query.setParameter("end", end);
        		} else
        		{
        			Calendar start = Calendar.getInstance();
        			
        			Calendar end = Calendar.getInstance();
        			end.add(end.YEAR, -2);
        			query.setParameter("start", end);
        			query.setParameter("end", start);
        		}
        		long totalCount = query.list().size();
        		page.setTotalCount(totalCount);
        		
                query.setMaxResults(page.getSize());
        		query.setFirstResult((page.getCurrentPage() > 0? page.getCurrentPage() - 1: 0) * page.getSize());
        		return query.list();
            }
			
        });
	}
	@Override
	public List<Group> getSecGroup(final int size, final int type) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		Query query = session.createQuery("from Group where ext_seckill=:ext_sec and seckill_end_time between :start and :end order by seckill_end_time asc");
        		query.setParameter("ext_sec", true);
        		
        		if (type==0)
        		{
        			Calendar start = Calendar.getInstance();
        			query.setParameter("start", start);
        			Calendar end = Calendar.getInstance();
        			end.add(end.YEAR, 2);
        			query.setParameter("end", end);
        		} else
        		{
        			Calendar start = Calendar.getInstance();
        			
        			Calendar end = Calendar.getInstance();
        			end.add(end.YEAR, -2);
        			query.setParameter("start", end);
        			query.setParameter("end", start);
        		}
        		if (size!=-1)
        		{
        		query.setMaxResults(size);
        		}
                return query.list();
            }
			
        });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> pagingGroupByDateAndDest(final Calendar start, final Calendar end,
			final Page page, final String pickupCity, final String destination) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select {g.*} from t_group g join Route r on g.route_id = r.id "; 
        			   hql = hql + " where g.depart_date between :start and :end and g.externalBookable = 1 and g.price > 0 ";
        			   hql = hql + " and (lcase(r.name) like :dest "
           					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
        			   hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
        			   hql = hql + " and lcase(g.pickup_info) like :pickupcity order by g.depart_date asc";
        		Query query = session.createSQLQuery(hql).addEntity("g", Group.class);   
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		query.setParameter("pickupcity", (null == pickupCity || pickupCity.equals("") ? "%": "%" + pickupCity.toLowerCase() + "%"));
        		long totalCount = query.list().size();
        		page.setTotalCount(totalCount);
        		
        		query.setMaxResults(page.getSize());
        		query.setFirstResult((page.getCurrentPage() > 0? page.getCurrentPage() - 1: 0) * page.getSize());
                return query.list();
            }
			
        });
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> pagingGroupByDateAndDestAndPrice(final Calendar start, final Calendar end,
			final Page page, final String pickupCity, final String destination,
			final Double lprice, final Double uprice) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String sPriceRange = " and (g.price > 0) ";
        		if(lprice >= 0 && uprice > 0) {
        			sPriceRange = " and (g.price between " + lprice + " and " + uprice + ") ";
        		} else if (lprice >= 0 && uprice < 0) {
        			sPriceRange = " and (g.price > " + lprice + ") ";
        		} else if (lprice < 0 && uprice >= 0) {
        			sPriceRange = " and (g.price < " + uprice + ") ";
        		}
        		
        		String hql = "select {g.*} from t_group g join Route r on g.route_id = r.id "; 
        			   hql = hql + " where g.depart_date between :start and :end and g.externalBookable = 1 ";
        			   hql = hql + sPriceRange;
        			   hql = hql + " and (lcase(r.name) like :dest "
           					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
        			   hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
        			   hql = hql + " and lcase(g.pickup_info) like :pickupcity order by g.depart_date asc";
        		Query query = session.createSQLQuery(hql).addEntity("g", Group.class);   
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		query.setParameter("pickupcity", (null == pickupCity || pickupCity.equals("") ? "%": "%" + pickupCity.toLowerCase() + "%"));
        		long totalCount = query.list().size();
        		page.setTotalCount(totalCount);
        		
        		query.setMaxResults(page.getSize());
        		query.setFirstResult((page.getCurrentPage() > 0? page.getCurrentPage() - 1: 0) * page.getSize());
                return query.list();
            }
			
        });
	}
	
	@Override
	public int count_group(int status, String month) {
		String hql = "select count(*) from Group where 1=1 ";
		if(status!=Info.GS_ALL)
		{
			hql = hql + " and status = " + status;
		}
		if(!(null == month || month.equals("")))
		{
			hql = hql + " and depart_date like '" + month + "%'";
		}
		
		return DataAccessUtils.intResult(ht.find(hql));
        
	}

	@Override
	public List<Group> getGroupByTag(final int size, final int TagId, final String TagValue) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select {g.*}  from t_group g, group_tag gt where g.id =gt.group_id and gt.tag_id = :TagId " 
        				+ " and gt.value like :TagValue and depart_date between :start and :end order by depart_date asc";
        		
        		Query query = session.createSQLQuery(hql).addEntity("g", Group.class); 
        		query.setParameter("TagId", TagId);
        		query.setParameter("TagValue", (null == TagValue || TagValue.equals("") ? TagId +"%": TagValue + "%"));
        		
				Calendar start = Calendar.getInstance();
				query.setParameter("start", start);
				Calendar end = Calendar.getInstance();
				end.add(end.YEAR, 2);
				query.setParameter("end", end);
			
        		if (size!=-1)
        		{
        			query.setMaxResults(size);
        		}
                return query.list();
            }
			
        });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_group_by_route_and_date_and_famous_place(
			final int iRouteId, final Calendar start, final Calendar end,
			final int status, final int max, final int orderByPrice, final String destination,
			String fp) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String sPriceRange = " and (price > 0) ";
        		
        		String hql = "from Group where route_id=:route_id ";
        		if(status != - 1) {
        			hql = hql + "and status=:status ";
        		}
        		hql = hql + "and lcase(pickup_info) like :dest ";
        		hql = hql + sPriceRange;
        		hql = hql + "and depart_date between :start and :end and externalBookable = 1 "; 
        		hql = hql + "order by ";
        		if (orderByPrice == 1) {
        			hql = hql + "price asc, ";
        		}else if (orderByPrice == 2){
        			hql = hql + "price asc, ";
        		}
        			
        		hql = hql + "depart_date asc";
        		Query query = session.createQuery(hql);
        		query.setParameter("route_id", iRouteId);
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		if(status != - 1) {
        			query.setParameter("status", status);
        		}
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		query.setMaxResults(max);
                return query.list();
            }
			
        });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> pagingGroupByDateAndDestAndFamousPlace(
			final Calendar start, final Calendar end, final Page page,
			final String pickupCity, final String destination, final String fp) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String sPriceRange = " and (g.price > 0) ";
        		
        		String sFamousPlaces = "";        		
				if(null != fp && !"".equals(fp.trim())) {
					//sFamousPlaces = " and lcase(r.sitelist.name) like '%" + fp + "%' ";
					sFamousPlaces = " and exists ( select 1 from route_site rs join Site s on rs.site_id = s.id where rs.route_id = r.id and lcase(s.name) like '%" + fp + "%') ";
        		}
				
        		String hql = "select {g.*} from t_group g join Route r on g.route_id = r.id "; 
        			   hql = hql + " where g.depart_date between :start and :end and g.externalBookable = 1 ";
        			   hql = hql + sFamousPlaces;
        			   hql = hql + sPriceRange;
        			   hql = hql + " and (lcase(r.name) like :dest "
           					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
        			   hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
        			   hql = hql + " and lcase(g.pickup_info) like :pickupcity order by g.depart_date asc";
        		Query query = session.createSQLQuery(hql).addEntity("g", Group.class);   
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		query.setParameter("pickupcity", (null == pickupCity || pickupCity.equals("") ? "%": "%" + pickupCity.toLowerCase() + "%"));
        		long totalCount = query.list().size();
        		page.setTotalCount(totalCount);
        		
        		query.setMaxResults(page.getSize());
        		query.setFirstResult((page.getCurrentPage() > 0? page.getCurrentPage() - 1: 0) * page.getSize());
                return query.list();
            }
			
        });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list_group_by_route_and_date_and_schedule_days(
			final int iRouteId, final Calendar start, final Calendar end,
			final int status, final int max, final int orderByPrice, final String destination, final int ld,
			final int ud) {
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String sPriceRange = " and (price > 0) ";
        		
        		String hql = "from Group where route_id=:route_id ";
        		if(status != - 1) {
        			hql = hql + "and status=:status ";
        		}
        		hql = hql + "and lcase(pickup_info) like :dest ";
        		hql = hql + sPriceRange;
        		hql = hql + "and depart_date between :start and :end and externalBookable = 1 "; 
        		hql = hql + "order by ";
        		if (orderByPrice == 1) {
        			hql = hql + "price asc, ";
        		}else if (orderByPrice == 2){
        			hql = hql + "price asc, ";
        		}
        			
        		hql = hql + "depart_date asc";
        		Query query = session.createQuery(hql);
        		query.setParameter("route_id", iRouteId);
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		if(status != - 1) {
        			query.setParameter("status", status);
        		}
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		query.setMaxResults(max);
                return query.list();
            }
			
        });

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> pagingGroupByDateAndDestAndScheduleDays(
			final Calendar start, final Calendar end, final Page page,
			final String pickupCity, final String destination, final int ld, final int ud) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String sPriceRange = " and (g.price > 0) ";
        		
        		String sSchudleDays = " and ((select count(id) from schedule sc where sc.route_id = r.id) between " + ld + " and " + ud + ") ";

        		String hql = "select {g.*} from t_group g join Route r on g.route_id = r.id "; 
        			   hql = hql + " where g.depart_date between :start and :end and g.externalBookable = 1 ";
        			   hql = hql + sSchudleDays;
        			   hql = hql + sPriceRange;
        			   hql = hql + " and (lcase(r.name) like :dest "
           					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
        			   hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
        			   hql = hql + " and lcase(g.pickup_info) like :pickupcity order by g.depart_date asc";
        		Query query = session.createSQLQuery(hql).addEntity("g", Group.class);   
        		query.setParameter("start", start);
        		query.setParameter("end", end);
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		query.setParameter("pickupcity", (null == pickupCity || pickupCity.equals("") ? "%": "%" + pickupCity.toLowerCase() + "%"));
        		long totalCount = query.list().size();
        		page.setTotalCount(totalCount);
        		
        		query.setMaxResults(page.getSize());
        		query.setFirstResult((page.getCurrentPage() > 0? page.getCurrentPage() - 1: 0) * page.getSize());
                return query.list();
            }
			
        });
	}

	@Override
	public List<GroupSumByPickupDto> getGroupSumByPickupCity(final String pickupCity, final String destination) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select 'Nottingham' as pickupCity, sum(case when instr(lcase(g.pickup_info),'nottingham')>0 then 1 else 0 end) as groupCount "; 	
					hql = hql + " from t_group g join Route r on g.route_id = r.id ";
					hql = hql + " where g.depart_date >= current_date()";
					hql = hql + " and g.internalView=true";
					hql = hql + " and g.externalBookable = 1 ";
					hql = hql + " and (lcase(r.name) like :dest "
					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
					hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
					hql = hql + " and lcase(g.pickup_info) like :pickupcity";
					hql = hql + " union ";
					
					hql = hql + " select 'Manchester' as pickupCity, sum(case when instr(lcase(g.pickup_info),'manchester')>0 then 1 else 0  end) as groupCount " ;
					hql = hql + " from t_group g join Route r on g.route_id = r.id ";
					hql = hql + " where g.depart_date >= current_date()";
					hql = hql + " and g.internalView=true";
					hql = hql + " and g.externalBookable = 1 ";
					hql = hql + " and (lcase(r.name) like :dest "
					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
					hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
					hql = hql + " and lcase(g.pickup_info) like :pickupcity";
					hql = hql + " union ";
					
					hql = hql + " select 'Birmingham' as pickupCity, sum(case when instr(lcase(g.pickup_info),'birmingham')>0 then 1 else 0  end) as groupCount ";
					hql = hql + " from t_group g join Route r on g.route_id = r.id ";
					hql = hql + " where g.depart_date >= current_date()";
					hql = hql + " and g.internalView=true";
					hql = hql + " and g.externalBookable = 1 ";
					hql = hql + " and (lcase(r.name) like :dest "
					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
					hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
					hql = hql + " and lcase(g.pickup_info) like :pickupcity";
					hql = hql + " union ";
					
					hql = hql + " select 'Coventry' as pickupCity, sum(case when instr(lcase(g.pickup_info),'coventry')>0 then 1 else 0  end) as groupCount ";
					hql = hql + " from t_group g join Route r on g.route_id = r.id ";
					hql = hql + " where g.depart_date >= current_date()";
					hql = hql + " and g.internalView=true";
					hql = hql + " and g.externalBookable = 1 ";
					hql = hql + " and (lcase(r.name) like :dest "
					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
					hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
					hql = hql + " and lcase(g.pickup_info) like :pickupcity";
					hql = hql + " union ";
					
					hql = hql + " select 'Loughborough' as pickupCity, sum(case when instr(lcase(g.pickup_info),'loughborough')>0 then 1 else 0  end) as groupCount ";
					hql = hql + " from t_group g join Route r on g.route_id = r.id ";
					hql = hql + " where g.depart_date >= current_date()";
					hql = hql + " and g.internalView=true";
					hql = hql + " and g.externalBookable = 1 ";
					hql = hql + " and (lcase(r.name) like :dest "
					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
					hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
					hql = hql + " and lcase(g.pickup_info) like :pickupcity";
					hql = hql + " union ";
					
					hql = hql + " select 'Sheffield' as pickupCity, sum(case when instr(lcase(g.pickup_info),'sheffield')>0 then 1 else 0  end) as groupCount ";
					hql = hql + " from t_group g join Route r on g.route_id = r.id ";
					hql = hql + " where g.depart_date >= current_date()";
					hql = hql + " and g.internalView=true";
					hql = hql + " and g.externalBookable = 1 ";
					hql = hql + " and (lcase(r.name) like :dest "
					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
					hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
					hql = hql + " and lcase(g.pickup_info) like :pickupcity";
					hql = hql + " union ";
					
					hql = hql + " select 'Leicester' as pickupCity, sum(case when instr(lcase(g.pickup_info),'leicester')>0 then 1 else 0  end) as groupCount ";
					hql = hql + " from t_group g join Route r on g.route_id = r.id ";
					hql = hql + " where g.depart_date >= current_date()";
					hql = hql + " and g.internalView=true";
					hql = hql + " and g.externalBookable = 1 ";
					hql = hql + " and (lcase(r.name) like :dest "
					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
					hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
					hql = hql + " and lcase(g.pickup_info) like :pickupcity";
					hql = hql + " union ";
					
					hql = hql + " select 'London' as pickupCity, sum(case when instr(lcase(g.pickup_info),'london')>0 then 1 else 0  end) as groupCount ";
					hql = hql + " from t_group g join Route r on g.route_id = r.id ";
					hql = hql + " where g.depart_date >= current_date()";
					hql = hql + " and g.internalView=true";
					hql = hql + " and g.externalBookable = 1 ";
					hql = hql + " and (lcase(r.name) like :dest "
					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
					hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
					hql = hql + " and lcase(g.pickup_info) like :pickupcity";
					hql = hql + " union ";
					
					hql = hql + " select 'All' as pickupCity, " +
							"sum(case when instr(lcase(g.pickup_info),'nottingham')>0 then 1 else 0  end) + " +
							"sum(case when instr(lcase(g.pickup_info),'manchester')>0 then 1 else 0  end) +  " +
							"sum(case when instr(lcase(g.pickup_info),'birmingham')>0 then 1 else 0  end) +  " +
							"sum(case when instr(lcase(g.pickup_info),'coventry')>0 then 1 else 0  end) +  " +
							"sum(case when instr(lcase(g.pickup_info),'loughborough')>0 then 1 else 0  end) +  " +
							"sum(case when instr(lcase(g.pickup_info),'sheffield')>0 then 1 else 0  end) +  " +
							"sum(case when instr(lcase(g.pickup_info),'london')>0 then 1 else 0  end) " +
							"as groupCount ";
					hql = hql + " from t_group g join Route r on g.route_id = r.id ";
					hql = hql + " where g.depart_date >= current_date()";
					hql = hql + " and g.internalView=true";
					hql = hql + " and g.externalBookable = 1 ";
					hql = hql + " and (lcase(r.name) like :dest "
					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc) like :dest ))";
					hql = hql + " and g.status = " + Info.GS_OPENNING + " ";
					hql = hql + " and lcase(g.pickup_info) like :pickupcity";
					
				SQLQuery  query = session.createSQLQuery(hql);   
				query.addScalar("pickupCity", Hibernate.STRING);
                query.addScalar("groupCount", Hibernate.LONG);
                query.setResultTransformer(Transformers.aliasToBean(GroupSumByPickupDto.class));
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%": "%" + destination.toLowerCase() + "%"));
        		query.setParameter("pickupcity", (null == pickupCity || pickupCity.equals("") ? "%": "%" + pickupCity.toLowerCase() + "%"));
        		for(Object obj : query.list()){
        			System.out.println(obj.toString());
        			
        		}
                return query.list();
            }
			
        });	
	}

	@Override
	public List<Group> getSurveyBookingGroup(final long cid) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		StringBuffer hql = new StringBuffer("select {g.*} from t_group g where depart_date <= now() and  id in (select group_id from Booking where customer_id = :cid and status='2' ) and not exists (select 1 from group_survey where group_id = g.id ) order by id ");
        		Query query = session.createSQLQuery(hql.toString()).addEntity("g", Group.class); 
        		query.setParameter("cid", cid);
        		return query.list();
        	};
		});
	}   	
}
