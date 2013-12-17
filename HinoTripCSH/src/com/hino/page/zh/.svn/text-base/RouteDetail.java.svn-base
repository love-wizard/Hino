package com.hino.page.zh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Button;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;

import com.hino.click.extension.GroupDiv;
import com.hino.click.extension.Image;
import com.hino.click.extension.NavigationDiv;
import com.hino.click.extension.SiteListDiv;
import com.hino.dto.GroupDayDto;
import com.hino.dto.PlaceDto;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.model.WebMenuRoute;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.NavigationUtil;
import com.hino.util.ServiceResponse;

public class RouteDetail extends BasePageOther {

	@Bindable private String css = "line.css";
	/**
	 * 
	 */
	private static final long serialVersionUID = 2293821318864576058L;
	@Bindable private String routeid; // GET request: reoute_detail.htm?routeid=...
	@Bindable private String route_img;
	@Bindable private String route_name;
	@Bindable private String route_schedule;
	@Bindable private String route_service;
	@Bindable private String route_hint;
	@Bindable private SiteListDiv siteList;
	@Bindable private NavigationDiv navigationDiv;
	@Bindable private GroupDiv groupDiv;
	
	@Bindable public Button calendarLink = new Button("calendar_action", "cl");
	
	@Bindable private String d;
	@Bindable private String ym;
	@Bindable private String a;
	@Bindable private Boolean isOverseaRoute;

	public RouteDetail(){
		calendarLink.setId("calendar_action");
		calendarLink.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata = "";
            	
            	if ("day".equalsIgnoreCase(a)) {
            	
	            	if (null == d || "".equals(d)) {
	            		d = "0";
	            	}
	            	
	            	if (null == ym || "".equals(ym)) {
	            		ym = "999912";
	            	}
	            	
	            	if (! (d.equals("0") || ym.equals("999912"))) {
		            	SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		            	
		            	Calendar startDate = Calendar.getInstance();
		            	startDate.set(Integer.valueOf(ym.substring(0, 4)), Integer.valueOf(ym.substring(4, 6)) - 1, Integer.valueOf(d), 0, 0, 0);
		            	
		            	List<Group> groupList = getWebService().getGroupByRouteIdOriginalByPriority(Integer.valueOf(routeid), startDate, startDate);
		            	
		            	if (groupList.size() > 0) {
		            		Group group = groupList.get(0);
		            		int routeType = group.genGroupTypeCode();
		            		String cssClass = "";
		            		String emTitle = "";
		            		switch (routeType) {
		            		case Info.ROUTE_TYPE_CGT:
		            			cssClass = "rt_cgy"; //"orange";
		            			emTitle = "常规团";
		            			break;
		            		case Info.ROUTE_TYPE_DIY:
		            			cssClass = "rt_diy"; //"orange";
		            			emTitle = "DIY团";
		            			break;
		            		case Info.ROUTE_TYPE_JPXT:
		            			cssClass = "rt_jpxt"; //"grey";
		            			emTitle = "精品小团";
		            			break;
		            		case Info.ROUTE_TYPE_CZTG:
		            			cssClass = "rt_cztg"; //"green";
		            			emTitle = "超值团购";
		            			break;
		            		case Info.ROUTE_TYPE_RMTJ:
		            			cssClass = "rt_rmtj" ; //"purple";
		            			emTitle = "热门推荐团";
		            			break;
		            		}
		            		
		            		int emptySeats = group.getSeats() + group.getSeats_groupon() + group.getSeats_seckill() - group.gen_all_seats_for_go();
		            		String sPrice = new String();
		            		if(group.getGroup_price()>0)
		            		{
		            			sPrice="\"line2\":\"" + "原价：" + group.getGroup_price() + " / <em style='color:red;'>VIP: " + group.getGroup_vip_price() + "</em>\",";
		            		}
		            		else{
		            			sPrice="\"line2\":\"" + "原价：" + group.getPrice() + " / <em style='color:red;'>VIP: " + group.getVip_price() + "</em>\",";
		            		}
			            	jsondata = "{" + "\"line1\":\"" 		            				
			            				+ df.format(group.getDepart_date().getTime())
									 	+ "出发："
									 	+ "<em class='" + cssClass + "' title='" + group.getName() + "'>" 
			            				+ Info.cutString(group.getName(), 17, "...")
			            				+ "</em>"
			            				+ "\"," 		            				
			            				+ sPrice 
			            				+ "\"line3\":\"" + "剩余名额：" + (emptySeats == 0? "<em style='color:red;'>满团</em>": emptySeats + "名") 
			            				+ "\"}";
		            	}
		            	//System.out.println(jsondata);
	            	}
            	} else if ("month".equalsIgnoreCase(a)) {
            		
            		if (null == ym || "".equals(ym)) {
	            		ym = "999912";
	            	}
	            	
	            	if (! ym.equals("999912")) {
	            		Calendar currentCalendar = Calendar.getInstance(); // default to current month
	            		currentCalendar.set(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	            		
	            		Calendar groupDepartDate; 
	            		Calendar firstDayOfCurMonth = Calendar.getInstance(); 
	            		Calendar groupCalendar = Calendar.getInstance(); // default to current month
	            		Calendar endCalendar = Calendar.getInstance();
	            		
	            		groupCalendar.set(Integer.valueOf(ym.substring(0, 4)), Integer.valueOf(ym.substring(4, 6)) - 1, 1, 0, 0, 0);
	            		
	            		if(groupCalendar.compareTo(currentCalendar) < 0) {
	            			groupCalendar = currentCalendar;
	            		}
	            		
	            		endCalendar.set(2101, 12, 31, 0, 0, 0);
	            		firstDayOfCurMonth.set(Integer.valueOf(ym.substring(0, 4)), Integer.valueOf(ym.substring(4, 6)) - 1, 1, 0, 0, 0);
	            		
	            		List<Group> groupList = getWebService().getGroupByRouteIdOriginal(Integer.valueOf(routeid), groupCalendar, endCalendar);
	            		
	            		ArrayList<GroupDayDto> groupdays = new ArrayList<GroupDayDto>();
	            		
	            		// group list is ordered by depart date 
	            		for(Group g: groupList){
	            			groupDepartDate = g.getDepart_date();
	            			GroupDayDto groupDayDto = new GroupDayDto();
	            			if ((groupDepartDate.getTimeInMillis() / 1000000 >= groupCalendar.getTimeInMillis() / 1000000) && groupDepartDate.get(Calendar.YEAR) == groupCalendar.get(Calendar.YEAR) &&  groupDepartDate.get(Calendar.MONTH) == groupCalendar.get(Calendar.MONTH)) {
	            				if(! groupdays.contains(groupDepartDate.get(Calendar.DAY_OF_MONTH))) {
	            					groupDayDto.setDay(groupDepartDate.get(Calendar.DAY_OF_MONTH));
	            					groupDayDto.setRouteType(g.genGroupTypeCode());
	            					groupdays.add(groupDayDto);					
	            				}
	            			}
	            		}

	            		int daysofmonth = groupCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	            		int dayofweek = firstDayOfCurMonth.get(Calendar.DAY_OF_WEEK) - 1;
						StringBuffer strTable = new StringBuffer();
						strTable.append("<table style='text-align:center; border:0px;'>");

						int day; boolean dayHasGroup; String gdayCls = "";
						
	        			for(int i = 1; i <= 42; i ++) {
	        				day = i - dayofweek;
	        				
	        				dayHasGroup = false;
	        				
	        				for(GroupDayDto gday : groupdays) {
	        					if (gday.getDay() == day) {
	        						dayHasGroup = true;
        							gdayCls = gday.getCssClass();
	        					}
	        				}
	        				
	        				if(i == 1) {
	        					strTable.append("<tr style='line-height: 19px;'>");
	        					strTable.append("<td style='width:38px;'>");
		        				if(day > 0 && day <= daysofmonth) { 
		        						if(dayHasGroup) {
		        							strTable.append("<a href='javascript:void(0);' class='" + gdayCls + " gdayShadow' style='font-weight:bold;' onmouseover='showGroup(" + day + ")'><em class='" + gdayCls + " gdayShadow'>" + day + "</em></a>"); 
		        						} else {
		        							strTable.append(day);
		        						} 
		        				}
		        				strTable.append("</td>");
	        				} else if (i % 7 == 1) {
	        					strTable.append("<tr style='line-height: 18px;'>");
	        					strTable.append("<td style='width:38px;'>");
	        						if(day > 0 && day <= daysofmonth) { 
		        						if(dayHasGroup) {
		        							strTable.append("<a href='javascript:void(0);' class='" + gdayCls + " gdayShadow' style='font-weight:bold;' onmouseover='showGroup(" + day + ")'><em class='" + gdayCls + " gdayShadow'>" + day + "</em></a>"); 
		        						} else {
		        							strTable.append(day);
		        						} 
	        						}
	        					strTable.append("</td>");
	        				} else if(i % 7 == 0) {
	        					strTable.append("<td style='width:40px;'>");
	        						if(day > 0 && day <= daysofmonth) { 
		        						if(dayHasGroup) {
		        							strTable.append("<a href='javascript:void(0);' class='" + gdayCls + " gdayShadow' style='font-weight:bold;' onmouseover='showGroup(" + day + ")'><em class='" + gdayCls + " gdayShadow'>" + day + "</em></a>"); 
		        						} else {
		        							strTable.append(day);
		        						}
	        						}
	        					strTable.append("</td>");
	        					strTable.append("</tr>");
	        				} else if(i % 7 == 2) {
	        					strTable.append("<td style='width:38px;'>");
									if(day > 0 && day <= daysofmonth) {
		        						if(dayHasGroup) {
		        							strTable.append("<a href='javascript:void(0);' style='font-weight:bold;' onmouseover='showGroup(" + day + ")'><em class='" + gdayCls + " gdayShadow'>" + day + "</em></a>");
		        						} else {
		        							strTable.append(day);
		        						} 
									}
								strTable.append("</td>");
	        				} else {
	        					strTable.append("<td style='width:40px;'>");
	        						if(day > 0 && day <= daysofmonth) {
		        						if(dayHasGroup) {
		        							strTable.append("<a href='javascript:void(0);' class='" + gdayCls + " gdayShadow' style='font-weight:bold;' onmouseover='showGroup(" + day + ")'><em class='" + gdayCls + " gdayShadow'>" + day + "</em></a>"); 
		        						} else {
		        							strTable.append(day);
		        						}
	        						}
		        				strTable.append("</td>");
	        				}
	        			}
	        			strTable.append("</table>");
	        			
	        			int firstDayHasGroup = 0;
	        			for(GroupDayDto gday : groupdays) { 	        				
	        				firstDayHasGroup = gday.getDay();
       						break;
	        			}
	        			
	        			jsondata = "{" + "\"firstDayHasGroup\":\""
	        						+ firstDayHasGroup
	        						+ "\"," 
	        						+ "\"tableStr\":\"" + strTable.toString() + "\"}";
	            	}
            	}
            	return new ActionResult(jsondata, ActionResult.JSON);
            }
        });
	}
	
	@Override
	public void onInit() {
		qqList = getWebService().getIndexQQService();
		 addModel("qqList", qqList);
	}
	
	@Override
    public void onRender() {
		Route curRoute = getTravelResourceService().getRouteById(Long.parseLong(routeid));
		isOverseaRoute = Info.IsOverseaRoute(curRoute.getId());
		
		route_name = curRoute.getOriginalRouteName();
		route_img=Info.EXTERNAL_PATH_PREFIX+curRoute.getImageUrl();
		route_schedule = (""+curRoute.getSchedule());
		route_service = (""+curRoute.getService());
		route_hint = (""+curRoute.getHint());
		siteList = new SiteListDiv("route_site_list", curRoute.getSitelist(), false);
		
		curRoute.setSchedules(getTravelResourceService().getRouteScheduleList(Long.parseLong(routeid)));
		
		// Update navigation link
		NavigationUtil.updateRouteLevel(this.getContext().getSession(), curRoute.getName(), Integer.parseInt(routeid));
		// Create navigation link
		navigationDiv = new NavigationDiv("navigation_link", this.getContext().getSession(), false);
		
		List<Group> groupList = getWebService().getGroupByRouteIdOriginal(Integer.valueOf(routeid));
		groupDiv = new GroupDiv("group_list", groupList, false);
		
		Calendar groupCalendar = Calendar.getInstance(); // default to current month
		Calendar groupDepartDate; 
		groupCalendar.set(groupCalendar.get(Calendar.YEAR), groupCalendar.get(Calendar.MONTH), groupCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		ArrayList<GroupDayDto> groupdays = new ArrayList<GroupDayDto>();
		HashMap<String, String> hmDepartPlace = new HashMap<String, String>();
		
		boolean groupInAMonthFound = false;
		// group list is ordered by depart date 
		for(Group g: groupList){
			groupDepartDate = g.getDepart_date();
			GroupDayDto groupDayDto = new GroupDayDto();
			if ((groupDepartDate.getTimeInMillis() / 1000000 >= groupCalendar.getTimeInMillis() / 1000000) && groupDepartDate.get(Calendar.YEAR) == groupCalendar.get(Calendar.YEAR) &&  groupDepartDate.get(Calendar.MONTH) == groupCalendar.get(Calendar.MONTH)) {
				if(! groupdays.contains(groupDepartDate.get(Calendar.DAY_OF_MONTH))) {
					groupDayDto.setDay(groupDepartDate.get(Calendar.DAY_OF_MONTH));
					groupDayDto.setRouteType(g.genGroupTypeCode());
					groupdays.add(groupDayDto);					
				}
				groupInAMonthFound = true;
			} else if (! groupInAMonthFound && (groupDepartDate.getTimeInMillis() / 1000000 > groupCalendar.getTimeInMillis() / 1000000)) {
				// 进到这里表示当前月没有团了， 所以我们将显示下一个有团的月
				if(! groupdays.contains(groupDepartDate.get(Calendar.DAY_OF_MONTH))) {
					groupDayDto.setDay(groupDepartDate.get(Calendar.DAY_OF_MONTH));
					groupDayDto.setRouteType(g.genGroupTypeCode());
					groupdays.add(groupDayDto);					
				}
				groupInAMonthFound = true;
				// 改变groupCalendar到下一个有团的月， 这里只会进来一次
				groupCalendar.set(groupDepartDate.get(Calendar.YEAR), groupDepartDate.get(Calendar.MONTH), groupDepartDate.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			}
			
		}

        //List<Customer> customers = customerService.getCustomersSortedByName(7);
        addModel("site_list", curRoute.getSitelist());
        addModel("groups", groupList);
        //getFormat().setEmptyString("&nbsp;");
        addModel("groupdays", groupdays);
        addModel("daysofmonth", groupCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        addModel("yearmonth", df.format(groupCalendar.getTime()));
        
        Calendar firstDayOfCurGroupMonth = Calendar.getInstance();
        firstDayOfCurGroupMonth.set(groupCalendar.get(Calendar.YEAR), groupCalendar.get(Calendar.MONTH), 1);         
        addModel("dayofweek", firstDayOfCurGroupMonth.get(Calendar.DAY_OF_WEEK) - 1);
        
        df = new SimpleDateFormat("MM");
        addModel("groupMonth",  df.format(groupCalendar.getTime()));
        
        String cxt = getContext().getRequest().getContextPath();
        //System.out.println( " ================  " + cxt );
        addModel("cxt", cxt);
        addModel("schedules", curRoute.getSchedules());
        addModel("satisfaction", curRoute.getScatisfaction());
        addModel("curRoute", curRoute);
    }
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./images/line.css"));
        }
        return headElements;
    }
    
}
