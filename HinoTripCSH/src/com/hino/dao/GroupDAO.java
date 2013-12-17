package com.hino.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hino.dto.GroupSumByPickupDto;
import com.hino.model.Group;
import com.hino.util.Page;

public interface GroupDAO {
	public Group getGroupById(int id);
	public Group viewGroupById(int id);
	public List<Group> getAllGroup();
	public void delete(int id);
	public void update(Group g);
	public void delete(Group g);
	public void save(Group g);
	public List<Group> list_guide_group_by_paging(final int guideid, final int page, final int size);
	public int getGuideGroupCount(int guideid);
	
	// Ken Chen 2012/10/03 add month parameter
	public int count_group(int status, final String month);
	public List<Group> list_group_by_status_paging_ordering(final int status, final String month, final int page, final int size, String orderingAttr, boolean isAscending);	
	public List<Group> list_all_group_by_paging(final int page, final int size, String orderingAttr, boolean isAscending);
	public List<Group> list_group_by_viewable(String orderingAttr, boolean isAscending);
	public int getAllGroupCount();
	
	public int count_group_by_status(int status);
	public List<Group> list_group_guide_not_ready();
	
	public List<Group> list_reviewable_group(int page, int size, String orderingAttr, boolean isAscending);
	
	public List<Group> list_external_viewable_group(int routeid);
	public List<Group> list_external_viewable_group_paging(int page, int size, String orderingAttr, boolean isAscending);
	public long list_external_viewable_group_count();
	
	public List<Group> list_group_by_date(Calendar start, Calendar end, int status, int max);
	
	public List<Group> list_group_status_profit(Integer[] status, Boolean[] ready, int page, int size);
	public long list_group_status_profit_count(Integer[] status, Boolean[] ready);

	public List<Group> getGoGroup(int size, int type);
	public List<Group> getGoGroup(Page page, int type);
	public List<Group> getSecGroup(int size, int type);
	
	/**
	 * Created by Devon king - 2012/09/08
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */	
	public List<Group> pagingGroupByDate(Calendar start, Calendar end, Page page,String destination);
	public List<Group> list_group_by_route_and_date(int iRouteId, Calendar start,
			Calendar end, int status, int max, int orderByPrice, String destination);
	List<?> getCountGroupInfo(Calendar start, Calendar end, int status,
			String groupByColumn, String orderByColumn);
	
	public List<Group> pagingGroupByDateAndDest(Calendar start, Calendar end, Page page, String pickupCity, String destination);
	public List<Group> list_external_viewable_group(int routeid, Calendar start,
			Calendar end);
	public List<Group> list_external_viewable_group_original(int routeid);
	public List<Group> list_external_viewable_group_original(int routeid,
			Calendar start, Calendar end);
	public List<Group> list_group_by_route_and_date_and_price(int iRouteId,
			Calendar start, Calendar end, int status, int max,
			int orderByPrice, String destination, Double lprice, Double uprice);
	public List<Group> pagingGroupByDateAndDestAndPrice(Calendar start, Calendar end,
			Page page, String pickupCity, String destination, Double lprice,
			Double uprice);
	public List<Group> list_group_by_route_and_date_and_famous_place(
			int routeId, Calendar startCalendar, Calendar endCalendar,
			int status, int max, int orderByPrice, String destination,
			String fp);
	public List<Group> pagingGroupByDateAndDestAndFamousPlace(
			Calendar startCalendar, Calendar endCalendar, Page page,
			String departPlace, String destination, String fp);
	public List<Group> list_group_by_route_and_date_and_schedule_days(int iRouteId,
			Calendar start, Calendar end, int status, int max,
			int orderByPrice, String destination, int ld, int ud);
	public List<Group> pagingGroupByDateAndDestAndScheduleDays(Calendar start,
			Calendar end, Page page, String pickupCity, String destination,
			int ld, int ud);
	
	//Ken Chen 2013/01/10
	public List<Group> getGroupByTag(int size, int TagId, String TagValue);
	
	public List<GroupSumByPickupDto> getGroupSumByPickupCity(String pickupCity, String destination);
	public List<Group> list_ext_view_ori_group_by_priority(int routeid, Calendar start, Calendar end);
	
	public List<Group> getSurveyBookingGroup(long cid);
	public List<Group> getGroupsByDateRange(int size, Calendar start, Calendar end);
	
	
}
