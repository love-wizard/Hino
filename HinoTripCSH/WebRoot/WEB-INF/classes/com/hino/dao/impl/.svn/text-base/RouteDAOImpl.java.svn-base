package com.hino.dao.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.RouteDAO;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.model.WebMenuRoute;
import com.hino.util.Info;
import com.hino.util.Page;

public class RouteDAOImpl implements RouteDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	@Override
	public Route getRouteById(long id) {
		return ht.get(Route.class, id);
		
	}

	@Override
	public void save(Route r) {
		ht.save(r);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Route> list_all_route() {
		
		return (List<Route>)ht.find("from Route");
	}

	@Override
	public void delete(long id) {
		Route r = new Route();
		r.setId(id);
		ht.delete(r);
	}

	@Override
	public int getRouteCount() {
		return DataAccessUtils.intResult(ht.find("select count(*) from Route"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Route> list_route_by_paging(final int start,final int count) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Route");
                query.setMaxResults(count);
                query.setFirstResult(count*start);
                return query.list();
            }
			
        });
	}	

	@Override
	public void update(Route r) {
		ht.update(r);
		
	}

	// Ken Chen 2012/09/28 Add routeType parameter to get route
	@SuppressWarnings("unchecked")
	@Override
	public List<Route> pagingRouteByGroupDate(final Calendar start, final Calendar end,
			final Page page, final Integer orderByDepartDate, final Integer orderByPrice, 
			final Integer orderByDays, final String destination, final Integer routeType) {		
		
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "";
        		String sRouteType = Info.convertRouteTypeToName(routeType);
        		if (routeType == Info.ROUTE_TYPE_CGT){
        			sRouteType = " and ("; 
        			sRouteType = sRouteType + "	(r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_CZTG) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_DIY) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_JPXT) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_RMTJ) + "%') ";
        			sRouteType = sRouteType + ") ";
        		} else {
	        		
	        		if(sRouteType!="")
	        		{
	        			sRouteType = " and r.name like '%" + sRouteType + "%' ";
	        		}
        		}
        		
        		String sGroupStatus = " and g.status = " + Info.GS_OPENNING + " ";
        		
        		if(orderByDepartDate != 0 && orderByPrice == 0 &&  orderByDays == 0) {
	        		hql =  
		        		 "select {rt.*} from Route rt join ( "
		        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
		        		 + "where exists ( "
		 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
		 				 + "Route r join t_group g "
		 				 + "on r.id = g.route_id and g.route_id <> '' and g.price > 0 "
		 				 + "where lcase(g.pickup_info) like :dest and g.depart_date between :start and :end " 
		 				 + sRouteType 
		 				 + sGroupStatus
		 				 + "and g.externalView = 1  group by r.id "
		 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1"
						+ ") ) b on rt.id = b.route_id " ;
		        		
		        		if(orderByDepartDate == 1) {
		        			hql = hql + "order by b.depart_date desc";	        		
		        		}else{
		        			hql = hql + "order by b.depart_date asc";
		        		}
		        		
	        		
	        	}else if (orderByDepartDate == 0 && orderByPrice != 0 &&  orderByDays == 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.price as price from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.price) as price from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' and g.price > 0 "
       	 				 + "where lcase(g.pickup_info) like lcase(:dest) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.price = c.price and g1.externalView = 1"
       					+ ") ) b on rt.id = b.route_id " ;
        			
        			if(orderByPrice == 1) {
	        			hql = hql + "order by b.price desc";	        		
	        		}else{
	        			hql = hql + "order by b.price asc";
	        		}
        			
	        	}else if (orderByDepartDate == 0 && orderByPrice == 0 &&  orderByDays != 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' and g.price > 0 "
       	 				 + "where lcase(g.pickup_info) like lcase(:dest) and g.depart_date between :start and :end " 
		 				 + sRouteType
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
       					+ ") ) b on rt.id = b.route_id " ;
       	        		
        			if(orderByDays == 1){
	        			hql = hql + "order by b.depart_date desc";
        			}else{ 
	        			hql = hql + "order by b.depart_date asc";
	        			}
        			
        		}else{
	        		
	        		hql =  
	   	        		 "select {rt.*} from Route rt join ( "
	   	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
	   	        		 + "where exists ( "
	   	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
	   	 				 + "Route r join t_group g "
	   	 				 + "on r.id = g.route_id and g.route_id <> '' and g.price > 0 "
	   	 				 + "where lcase(g.pickup_info) like lcase(:dest) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sGroupStatus
	   	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
	   	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
	   					+ ") ) b on rt.id = b.route_id " ;
	   	        		
	   	        		hql = hql + "order by b.depart_date asc";
	   	        		
	        	}
        		
        		/*"from Route as r " 
				+ "where exists (" 
				+ "  from Group as g where g.route = r and g. depart_date between :start and :end"
				+ ")";*/	
        		System.out.println("destination = " + destination);
        		
                Query query = session.createSQLQuery(hql).addEntity("rt", Route.class);                
                query.setParameter("start", start);
        		query.setParameter("end", end);
        		query.setParameter("dest", (null == destination || destination.equals("") ? "%" : "%" + destination.toLowerCase() + "%"));        		
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
	public List<WebMenuRoute> getOrderedRoutesByWebMenuId(final long webMenuId) {
		
		return ht.executeFind(new HibernateCallback<Object>() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		//Ken Chen 2013-02-03 TD#98/TD#103
        		//String hql = "select wmr.* from WebMenuRoute wmr where route_id in （select distinct oirginal_route_id from route where id in (select {wr.*} from WebMenuRoute wr join WebMenu_WebMenuRoute ww on wr.id = ww.menu_routes_id where ww.webmenu_id = :wmid order by wr.priority asc ) and webmenu_id = :wmid order by priority asc";
//        		String hql = "select wmr.* from WebMenuRoute wmr " +
//        				"join WebMenu_WebMenuRoute wwr on wmr.id = wwr.menu_routes_id " +
//        				"where route_id in ( select distinct original_route_id  " +
//        				"from Route where id in (select wr.route_id " +
//        				"from WebMenuRoute wr " +
//        				"join WebMenu_WebMenuRoute ww on wr.id = ww.menu_routes_id " +
//        				"where ww.webmenu_id = :wmid ) ) " +
//        				"and wwr.webmenu_id = :wmid " +
//        				"order by priority asc ";
        		
        		String hql = "select  " +
        	    "    wmr.* " +
        	    "from " +
        	    "    WebMenu_WebMenuRoute wwr  " +
        	    "inner join " +
        	    "    WebMenuRoute wmr " + 
        	    "        on wwr.menu_routes_id=wmr.id  " +
        	    "left outer join " +
        	    "    Route route2_  " +
        	    "        on wmr.route_id=route2_.id  " +
        	    "left outer join " +
        	    "    Route route3_  " +
        	    "        on route2_.original_route_id=route3_.id  " +
        	    "where " +
        	    "    wwr.WebMenu_id=:wmid " +
        		"order by wmr.priority ";
        	    
                Query query = session.createSQLQuery(hql).addEntity("wmr", WebMenuRoute.class) ;
//                session.createSQLQuery(hql).addEntity("wmr", WebMenuRoute.class) ;
                
                query.setParameter("wmid", webMenuId);
                
                return query.list();
            }
        });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Route> pagingRouteByDestination(final String destination,
			final Calendar startCalendar, final Calendar endCalendar, final Page page, final Integer orderByDepartDate,
			final Integer orderByPrice, final Integer orderByDays, final String pickupCity,
			final Integer routeType) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "";
        		String sRouteType = Info.convertRouteTypeToName(routeType);
        		if (routeType == Info.ROUTE_TYPE_CGT){
        			sRouteType = " and ("; 
        			sRouteType = sRouteType + "	(r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_CZTG) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_DIY) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_JPXT) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_RMTJ) + "%') ";
        			sRouteType = sRouteType + ") ";
        		} else {
	        		
	        		if(sRouteType!="")
	        		{
	        			sRouteType = " and r.name like '%" + sRouteType + "%' ";
	        		}
        		}
        		
        		String sRouteDestination = "";
        		if(null != destination && !"".equals(destination.trim())) {
        			sRouteDestination = " and (lcase(r.name) like '%" + destination.toLowerCase() + "%' "
        					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc)"
        					+ " like '%" + destination.toLowerCase() + "%' ))";
        		}
        		
        		String sGroupStatus = " and g.status = " + Info.GS_OPENNING + " ";
        		
        		System.out.println("====> \t" + sRouteDestination);
        		
        		if(orderByDepartDate != 0 && orderByPrice == 0 &&  orderByDays == 0) {
	        		hql =  
		        		 "select {rt.*} from Route rt join ( "
		        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
		        		 + "where exists ( "
		 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
		 				 + "Route r join t_group g "
		 				 + "on r.id = g.route_id and g.route_id <> '' and g.price > 0 "
		 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end " 
		 				 + sRouteType  
		 				 + sRouteDestination
		 				 + sGroupStatus
		 				 + "and g.externalView = 1  group by r.id "
		 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1"
						+ ") ) b on rt.id = b.route_id " ;
		        		
		        		if(orderByDepartDate == 1) {
		        			hql = hql + "order by b.depart_date desc";	        		
		        		}else{
		        			hql = hql + "order by b.depart_date asc";
		        		}
		        		
	        		
	        	}else if (orderByDepartDate == 0 && orderByPrice != 0 &&  orderByDays == 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.price as price from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.price) as price from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' and g.price > 0 "
       	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.price = c.price and g1.externalView = 1"
       					+ ") ) b on rt.id = b.route_id " ;
        			
        			if(orderByPrice == 1) {
	        			hql = hql + "order by b.price desc";	        		
	        		}else{
	        			hql = hql + "order by b.price asc";
	        		}
        			
	        	}else if (orderByDepartDate == 0 && orderByPrice == 0 &&  orderByDays != 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' and g.price > 0 "
       	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end " 
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
       					+ ") ) b on rt.id = b.route_id " ;
       	        		
        			if(orderByDays == 1){
	        			hql = hql + "order by b.depart_date desc";
        			}else{ 
	        			hql = hql + "order by b.depart_date asc";
	        			}
        			
        		}else{
	        		
	        		hql =  
	   	        		 "select {rt.*} from Route rt join ( "
	   	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
	   	        		 + "where exists ( "
	   	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
	   	 				 + "Route r join t_group g "
	   	 				 + "on r.id = g.route_id and g.route_id <> '' and g.price > 0 "
	   	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
	   	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
	   	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
	   					+ ") ) b on rt.id = b.route_id " ;
	   	        		
	   	        		hql = hql + "order by b.depart_date asc";
	   	        		
	        	}
        		        		
                Query query = session.createSQLQuery(hql).addEntity("rt", Route.class);                
                query.setParameter("start", startCalendar);
        		query.setParameter("end", endCalendar);
        		query.setParameter("pickupcicy", (null == pickupCity || pickupCity.equals("") ? "%" : "%" + pickupCity.toLowerCase() + "%"));        		
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
	public List<Route> pagingRouteByPrice(final String destination,
			final Calendar startCalendar, final Calendar endCalendar, final Page page,
			final Integer orderByDepartDate, final Integer orderByPrice,
			final Integer orderByDays, final String pickupCity, final Integer routeType,
			final Double lprice, final Double uprice) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "";
        		String sRouteType = Info.convertRouteTypeToName(routeType);
        		if (routeType == Info.ROUTE_TYPE_CGT){
        			sRouteType = " and ("; 
        			sRouteType = sRouteType + "	(r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_CZTG) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_DIY) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_JPXT) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_RMTJ) + "%') ";
        			sRouteType = sRouteType + ") ";
        		} else {
	        		
	        		if(sRouteType!="")
	        		{
	        			sRouteType = " and r.name like '%" + sRouteType + "%' ";
	        		}
        		}
        		
        		String sRouteDestination = "";
        		if(null != destination && !"".equals(destination.trim())) {
        			sRouteDestination = " and (lcase(r.name) like '%" + destination.toLowerCase() + "%' "
        					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc)"
        					+ " like '%" + destination.toLowerCase() + "%' ))";
        		}
        		
        		String sGroupStatus = " and g.status = " + Info.GS_OPENNING + " ";
        		
        		System.out.println("====> \t" + sRouteDestination);
        		
        		String sPriceRange1 = " and (g.price > 0) ";
        		String sPriceRange2 = " and (g1.price > 0) ";
        		if(lprice >= 0 && uprice > 0) {
        			sPriceRange1 = " and (g.price between " + lprice + " and " + uprice + ") ";
        			sPriceRange2 = " and (g1.price between " + lprice + " and " + uprice + ") ";
        		} else if (lprice >= 0 && uprice < 0) {
        			sPriceRange1 = " and (g.price > " + lprice + ") ";
        			sPriceRange2 = " and (g1.price > " + lprice + ") ";
        		} else if (lprice < 0 && uprice >= 0) {
        			sPriceRange1 = " and (g.price < " + uprice + ") ";
        			sPriceRange2 = " and (g1.price < " + uprice + ") ";
        		}
        		
        		if(orderByDepartDate != 0 && orderByPrice == 0 &&  orderByDays == 0) {
	        		hql =  
		        		 "select {rt.*} from Route rt join ( "
		        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
		        		 + "where exists ( "
		 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
		 				 + "Route r join t_group g "
		 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
		 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end " 
		 				 + sRouteType  
		 				 + sRouteDestination
		 				 + sGroupStatus
		 				 + "and g.externalView = 1  group by r.id "
		 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
		 				 + sPriceRange2
		 				 + ") ) b on rt.id = b.route_id " ;
		        		
		        		if(orderByDepartDate == 1) {
		        			hql = hql + "order by b.depart_date desc";	        		
		        		}else{
		        			hql = hql + "order by b.depart_date asc";
		        		}
		        		
	        		
	        	}else if (orderByDepartDate == 0 && orderByPrice != 0 &&  orderByDays == 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.price as price from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.price) as price from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
       	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.price = c.price and g1.externalView = 1 "
       	 				 + sPriceRange2
       	 				 + ") ) b on rt.id = b.route_id " ;
        			
        			if(orderByPrice == 1) {
	        			hql = hql + "order by b.price desc";	        		
	        		}else{
	        			hql = hql + "order by b.price asc";
	        		}
        			
	        	}else if (orderByDepartDate == 0 && orderByPrice == 0 &&  orderByDays != 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
       	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end " 
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
       	 				 + sPriceRange2
       	 				 + ") ) b on rt.id = b.route_id " ;
       	        		
        			if(orderByDays == 1){
	        			hql = hql + "order by b.depart_date desc";
        			}else{ 
	        			hql = hql + "order by b.depart_date asc";
	        			}
        			
        		}else{
	        		
	        		hql =  
	   	        		 "select {rt.*} from Route rt join ( "
	   	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
	   	        		 + "where exists ( "
	   	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
	   	 				 + "Route r join t_group g "
	   	 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
	   	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
	   	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
	   	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
	   	 				 + sPriceRange2
	   					 + ") ) b on rt.id = b.route_id " ;
	   	        		
	   	        		hql = hql + "order by b.depart_date asc";
	   	        		
	        	}
        		        		
                Query query = session.createSQLQuery(hql).addEntity("rt", Route.class);                
                query.setParameter("start", startCalendar);
        		query.setParameter("end", endCalendar);
        		query.setParameter("pickupcicy", (null == pickupCity || pickupCity.equals("") ? "%" : "%" + pickupCity.toLowerCase() + "%"));        		
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
	public List<Route> pagingRouteByFamousPalce(final String destination,
			final Calendar startCalendar, final Calendar endCalendar, final Page page,
			final Integer orderByDepartDate, final Integer orderByPrice,
			final Integer orderByDays, final String pickupCity, final Integer routeType, final String fp) {

		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "";
        		String sRouteType = Info.convertRouteTypeToName(routeType);
        		if (routeType == Info.ROUTE_TYPE_CGT){
        			sRouteType = " and ("; 
        			sRouteType = sRouteType + "	(r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_CZTG) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_DIY) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_JPXT) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_RMTJ) + "%') ";
        			sRouteType = sRouteType + ") ";
        		} else {
	        		
	        		if(sRouteType!="")
	        		{
	        			sRouteType = " and r.name like '%" + sRouteType + "%' ";
	        		}
        		}
        		
        		String sRouteDestination = "";
        		if(null != destination && !"".equals(destination.trim())) {
        			sRouteDestination = " and (lcase(r.name) like '%" + destination.toLowerCase() + "%' "
        					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc)"
        					+ " like '%" + destination.toLowerCase() + "%' ))";
        		}
        		
        		String sGroupStatus = " and g.status = " + Info.GS_OPENNING + " ";
        		
        		System.out.println("====> \t" + sRouteDestination);
  
        		String sPriceRange1 = " and (g.price > 0) ";
        		String sPriceRange2 = " and (g1.price > 0) ";
        		
        		String sFamousPlaces = "";        		
				if(null != fp && !"".equals(fp.trim())) {
					sFamousPlaces = " and exists ( select 1 from route_site rs join Site s on rs.site_id = s.id where rs.route_id = rt.id and lcase(s.name) like '%" + fp + "%') ";
        		}

        		if(orderByDepartDate != 0 && orderByPrice == 0 &&  orderByDays == 0) {
	        		hql =  
		        		 "select {rt.*} from Route rt join ( "
		        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
		        		 + "where exists ( "
		 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
		 				 + "Route r join t_group g "
		 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
		 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end " 
		 				 + sRouteType  
		 				 + sRouteDestination
		 				 + sGroupStatus
		 				 + "and g.externalView = 1  group by r.id "
		 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
		 				 + sPriceRange2
		 				 + ") ) b on rt.id = b.route_id where 1 = 1 "
	        			 + sFamousPlaces;
		        		
		        		if(orderByDepartDate == 1) {
		        			hql = hql + "order by b.depart_date desc";	        		
		        		}else{
		        			hql = hql + "order by b.depart_date asc";
		        		}
		        		
	        		
	        	}else if (orderByDepartDate == 0 && orderByPrice != 0 &&  orderByDays == 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.price as price from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.price) as price from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
       	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.price = c.price and g1.externalView = 1 "
       	 				 + sPriceRange2
       	 				 + ") ) b on rt.id = b.route_id where 1 = 1 "
	        			 + sFamousPlaces;
        			
        			if(orderByPrice == 1) {
	        			hql = hql + "order by b.price desc";	        		
	        		}else{
	        			hql = hql + "order by b.price asc";
	        		}
        			
	        	}else if (orderByDepartDate == 0 && orderByPrice == 0 &&  orderByDays != 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
       	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end " 
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
       	 				 + sPriceRange2
       	 				 + ") ) b on rt.id = b.route_id where 1 = 1 "
	        			 + sFamousPlaces;
       	        		
        			if(orderByDays == 1){
	        			hql = hql + "order by b.depart_date desc";
        			}else{ 
	        			hql = hql + "order by b.depart_date asc";
	        			}
        			
        		}else{
	        		
	        		hql =  
	   	        		 "select {rt.*} from Route rt join ( "
	   	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
	   	        		 + "where exists ( "
	   	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
	   	 				 + "Route r join t_group g "
	   	 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
	   	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
	   	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
	   	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
	   	 				 + sPriceRange2
	   	 				 + ") ) b on rt.id = b.route_id where 1 = 1 "
	        			 + sFamousPlaces;
	   	        		
	   	        		hql = hql + "order by b.depart_date asc";
	   	        		
	        	}
        		        		
                Query query = session.createSQLQuery(hql).addEntity("rt", Route.class);                
                query.setParameter("start", startCalendar);
        		query.setParameter("end", endCalendar);
        		query.setParameter("pickupcicy", (null == pickupCity || pickupCity.equals("") ? "%" : "%" + pickupCity.toLowerCase() + "%"));        		
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
	public List<Route> pagingRouteByScheduleDays(final String destination,
			final Calendar startCalendar, final Calendar endCalendar, final Page page,
			final Integer orderByDepartDate, final Integer orderByPrice,
			final Integer orderByDays, final String pickupCity, final Integer routeType,
			final Integer ld, final Integer ud) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "";
        		String sRouteType = Info.convertRouteTypeToName(routeType);
        		if (routeType == Info.ROUTE_TYPE_CGT){
        			sRouteType = " and ("; 
        			sRouteType = sRouteType + "	(r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_CZTG) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_DIY) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_JPXT) + "%') ";
        			sRouteType = sRouteType + " and (r.name not like '%" + Info.convertRouteTypeToName(Info.ROUTE_TYPE_RMTJ) + "%') ";
        			sRouteType = sRouteType + ") ";
        		} else {
	        		
	        		if(sRouteType!="")
	        		{
	        			sRouteType = " and r.name like '%" + sRouteType + "%' ";
	        		}
        		}
        		
        		String sRouteDestination = "";
        		if(null != destination && !"".equals(destination.trim())) {
        			sRouteDestination = " and (lcase(r.name) like '%" + destination.toLowerCase() + "%' "
        					+ " or exists (select 1 from schedule s where r.id = s.route_id and lcase(s.title_desc)"
        					+ " like '%" + destination.toLowerCase() + "%' ))";
        		}
        		
        		String sGroupStatus = " and g.status = " + Info.GS_OPENNING + " ";
        		
        		System.out.println("====> \t" + sRouteDestination);
  
        		String sPriceRange1 = " and (g.price > 0) ";
        		String sPriceRange2 = " and (g1.price > 0) ";
        		
        		String sSchudleDays = " and (( select count(id) from schedule sc where sc.route_id = rt.id) between " + ld + " and " + ud + ") ";

        		if(orderByDepartDate != 0 && orderByPrice == 0 &&  orderByDays == 0) {
	        		hql =  
		        		 "select {rt.*} from Route rt join ( "
		        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
		        		 + "where exists ( "
		 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
		 				 + "Route r join t_group g "
		 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
		 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end " 
		 				 + sRouteType  
		 				 + sRouteDestination
		 				 + sGroupStatus
		 				 + "and g.externalView = 1  group by r.id "
		 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
		 				 + sPriceRange2
		 				 + ") ) b on rt.id = b.route_id where 1 = 1 ";
		        		
		        		if(orderByDepartDate == 1) {
		        			hql = hql + "order by b.depart_date desc";	        		
		        		}else{
		        			hql = hql + "order by b.depart_date asc";
		        		}
		        		
	        		
	        	}else if (orderByDepartDate == 0 && orderByPrice != 0 &&  orderByDays == 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.price as price from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.price) as price from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
       	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.price = c.price and g1.externalView = 1 "
       	 				 + sPriceRange2
       	 				 + ") ) b on rt.id = b.route_id where 1 = 1 "
	        			 + sSchudleDays;
        			
        			if(orderByPrice == 1) {
	        			hql = hql + "order by b.price desc";	        		
	        		}else{
	        			hql = hql + "order by b.price asc";
	        		}
        			
	        	}else if (orderByDepartDate == 0 && orderByPrice == 0 &&  orderByDays != 0) {
        			
        			hql =  
       	        		 "select {rt.*} from Route rt join ( "
       	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
       	        		 + "where exists ( "
       	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
       	 				 + "Route r join t_group g "
       	 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
       	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end " 
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
       	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
       	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
       	 				 + sPriceRange2
       	 				 + ") ) b on rt.id = b.route_id where 1 = 1 "
	        			 + sSchudleDays;
       	        		
        			if(orderByDays == 1){
	        			hql = hql + "order by b.depart_date desc";
        			}else{ 
	        			hql = hql + "order by b.depart_date asc";
	        			}
        			
        		}else{
	        		
	        		hql =  
	   	        		 "select {rt.*} from Route rt join ( "
	   	        		 + "select distinct g1.route_id as route_id,  g1.depart_date as depart_date from t_group g1 "
	   	        		 + "where exists ( "
	   	 				 + "select 1 from (select r.id as id, min(g.depart_date) as depart_date from " 
	   	 				 + "Route r join t_group g "
	   	 				 + "on r.id = g.route_id and g.route_id <> '' "
	   	 				 + sPriceRange1
	   	 				 + "where lcase(g.pickup_info) like lcase(:pickupcicy) and g.depart_date between :start and :end "
		 				 + sRouteType
		 				 + sRouteDestination
		 				 + sGroupStatus
	   	 				 + "and g.externalBookable = 1 and g.externalView = 1 group by r.id "
	   	 				 + ") c where g1.route_id = c.id and g1.depart_date = c.depart_date and g1.externalView = 1 "
	   	 				 + sPriceRange2
	   	 				 + ") ) b on rt.id = b.route_id where 1 = 1 "
	        			 + sSchudleDays;
	   	        		
	   	        		hql = hql + "order by b.depart_date asc";
	   	        		
	        	}
        		        		
                Query query = session.createSQLQuery(hql).addEntity("rt", Route.class);                
                query.setParameter("start", startCalendar);
        		query.setParameter("end", endCalendar);
        		query.setParameter("pickupcicy", (null == pickupCity || pickupCity.equals("") ? "%" : "%" + pickupCity.toLowerCase() + "%"));        		
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
	public List<Route> getRouteByTag(final int size, final int TagId, final String TagValue) {
		return ht.executeFind(new HibernateCallback() {
        	@Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
        		String hql = "select distinct {r.*} from Route r inner join group_tag gt on r.id = gt.route_id left join WebMenuRoute wm on r.id = wm.route_id where gt.tag_id = :TagId " 
        				+ " and gt.value like :TagValue order by wm.priority asc";
        		
        		Query query = session.createSQLQuery(hql).addEntity("r", Route.class); 
        		query.setParameter("TagId", TagId);
        		query.setParameter("TagValue", (null == TagValue || TagValue.equals("") ? TagId +"%": TagValue + "%"));
        									
        		if (size!=-1)
        		{
        			query.setMaxResults(size);
        		}
                return query.list();
            }
			
        });
	}
}
