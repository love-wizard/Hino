package com.hino.dao;

import java.util.Calendar;
import java.util.List;

import com.hino.model.Route;
import com.hino.model.WebMenuRoute;
import com.hino.util.Page;

public interface RouteDAO {
	public Route getRouteById(long id);
	public void save(Route r);
	public List<Route> list_all_route();
	
	public List<Route> list_route_by_paging(int start, int count);
	public int getRouteCount();
	public void delete(long id);
	public void update(Route r);
	/**
	 * 
	 * @author Devon King 
	 * @param start
	 * @param end
	 * @param page
	 * @return
	 */
	public List<Route> pagingRouteByGroupDate(Calendar start, Calendar end, Page page, Integer orderByDepartDate, Integer orderByPrice, Integer orderByDays, String destination, Integer routeType);
	
	/**
	 * 
	 * 
	 * @author Devon King - 2012/09/17 - T#12
	 * @param webMenuId
	 * @return
	 */
	public List<WebMenuRoute> getOrderedRoutesByWebMenuId(long webMenuId);
	
	/**
	 * 
	 * @author Devon King - 2012/10/02 - T#27
	 * 
	 * @param destination
	 * @param startCalendar
	 * @param page
	 * @param orderByDepartDate
	 * @param orderByPrice
	 * @param orderByDays
	 * @param pickupCity
	 * @param routeType
	 * @return
	 */
	public List<Route> pagingRouteByDestination(String destination, Calendar startCalendar, Calendar endCalendar, Page page, Integer orderByDepartDate, Integer orderByPrice, Integer orderByDays, String pickupCity, Integer routeType);
	
	public List<Route> pagingRouteByPrice(String destination, Calendar startCalendar, Calendar endCalendar, Page page, Integer orderByDepartDate, Integer orderByPrice, Integer orderByDays, String pickupCity, Integer routeType, Double lprice, Double uprice);
	public List<Route> pagingRouteByFamousPalce(String destination,
			Calendar startCalendar, Calendar endCalendar, Page page,
			Integer orderByDepartDate, Integer orderByPrice, Integer orderByDays, String pickupCity, Integer routeType, String fp);
	public List<Route> pagingRouteByScheduleDays(String destination,
			Calendar startCalendar, Calendar endCalendar, Page page,
			Integer orderByDepartDate, Integer orderByPrice, Integer orderByDays,
			String pickupCity, Integer routeType, Integer ld, Integer ud);
	public List<Route> getRouteByTag(int size, int tid, String tValue);
}
