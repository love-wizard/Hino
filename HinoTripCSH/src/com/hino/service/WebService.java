package com.hino.service;

import java.util.Calendar;
import java.util.List;

import com.hino.dto.AirlineQACAMDto;
import com.hino.dto.CustomerBasicInfoDto;
import com.hino.dto.CustomerLoginDto;
import com.hino.dto.CustomerPasswordDto;
import com.hino.dto.GroupSumByPickupDto;
import com.hino.dto.IndexImageSettingDto;
import com.hino.dto.MenuCAMDto;
import com.hino.dto.MenuRouteCAMDto;
import com.hino.dto.QQServiceCAMDto;
import com.hino.dto.StaffLoginDto;
import com.hino.model.AirlineQA;
import com.hino.model.Group;
import com.hino.model.Info;
import com.hino.model.QQService;
import com.hino.model.Site;
import com.hino.model.WebMenu;
import com.hino.model.WebMenuRoute;
import com.hino.util.Page;
import com.hino.util.ServiceResponse;

public interface WebService {
	public ServiceResponse customer_login(CustomerLoginDto cldto);
	public ServiceResponse customer_resetpwd(CustomerLoginDto cldto);	
	
	public ServiceResponse staff_login(StaffLoginDto sldto);

	public List<WebMenu> getAllMenu();
	public int getMenuCount();
	public WebMenu getMenuById(long id);
	public List<WebMenu> getPagedMenuList(int start, int count);
	public void create_menu(MenuCAMDto menucamdto);
	public void modify_menu(MenuCAMDto menucamdto);
	public void delete_menu(long id);
	
	public WebMenu getIndexMenu();

	public void create_menu_route(MenuRouteCAMDto menuroutedto);
	public void modify_menu_route(MenuRouteCAMDto menuroutedto);
	public void delete_menu_route(long id);

	public ServiceResponse customerResetPsdEmail(String email, boolean isEn);
	public ServiceResponse customerResetPsd(String resetcode, String password);

	public ServiceResponse staffResetPsdEmail(String email);
	public ServiceResponse staffResetPsd(String resetcode, String password);

	public ServiceResponse update_customer_psd(CustomerPasswordDto psddto);
	
	public List<Group> getRecentGroup();
	public List<Group> getGoGroup(int size, int type);
	public List<Group> getGoGroup(Page page, int type);
	public List<Group> getSecGroup(int size, int type);

	public List<Group> getGroupByRouteId(int routeid);
	public List<Group> getGroupExListView(int page, String orderingAttr, boolean isAscending);
	public int getGroupExListViewCount();
	
	public void indexImageSetting(IndexImageSettingDto iisdto);
	
	/**
	 * Get a list of routes associated with the specified web menu and ordered by defined priority
	 * 
	 * @author Devon King - 2012/09/17 - T#12
	 * @param webMenuId
	 * @return a list of routes
	 */
	public List<WebMenuRoute> getOrderedMenuRoutes(long webMenuId);

	public List<Group> getGroupByRouteIdAndDate(Calendar startDate, Calendar endDate,
			int routeId, int status, int max, int orderByPrice);
	
	public List<Info> getIndexInfo();
	public List<Info> getInfo(String source);

	public List<Group> getGroupByRouteId(int routeid, Calendar startDate,
			Calendar endDate);

	public List<Group> getGroupByRouteIdOriginal(int routeid, Calendar startDate, Calendar startDate2);

	public List<Group> getGroupByRouteIdOriginal(int routeid);

	public void indexNavigationSetting(com.hino.model.Info inFamousPlaces);
	public List<com.hino.model.Info> getIndexNavigationFamousPlace();
	
	public List<com.hino.model.Info> getDefalutSearchKeywords();
	public void SetupDefalutSearchKeywords(com.hino.model.Info keyWords);
	
	public List<QQService> getIndexQQService();
	public QQService getIndexQQService(int id);
	public void AddQQService(QQServiceCAMDto qqCamDto);
	public void UpdateQQService(QQServiceCAMDto qqCamDto);
	public void DeleteQQService(long id);
	
	public List<GroupSumByPickupDto> getGroupSumByPickupCity(String pickupCity, String destination);
	public List<Group> getGroupByRouteIdOriginalByPriority(int routeid, Calendar startDate, Calendar endDate);
	
	public int getAirlineQACount();
	public List<AirlineQA> getPagedAirlineQAList(int pageNumber, int pageSize);
	public AirlineQA getAirlineQAById(long id);
	public void modify_airlineQA(AirlineQACAMDto qaCamDto);
	public void create_airlineQA(AirlineQACAMDto qaCamDto);
	public void delete_airlineQA(long id);
	public void airlineImageSetting(IndexImageSettingDto airlineImageSettingDto);
	public List<Group> getGroupsByDateRange(int i, Calendar start, Calendar end);
	
}
