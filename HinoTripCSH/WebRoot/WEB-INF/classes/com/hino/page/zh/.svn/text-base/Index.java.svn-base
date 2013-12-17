package com.hino.page.zh;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.util.Bindable;

import com.hino.dto.GroupSumByPickupDto;
import com.hino.dto.SearchResultDto;
import com.hino.dto.SearchResultItemDto;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.QQService;
import com.hino.model.Route;
import com.hino.model.WebMenuRoute;
import com.hino.util.Info;

public class Index extends BasePageOther {
	private static final long serialVersionUID = 9084137430097090709L;
	private List<com.hino.model.Info> info;


	@Bindable private Customer cur_customer = null;
	@Bindable private String pathpre = Info.EXTERNAL_PATH_PREFIX;
	@Bindable private String pathpre1 = "internal/resource";

	@Bindable protected int page_type = 1;
	@Bindable private String notice = "";
	@Bindable private String link = "";
	@Bindable private List<Group> golist;
	@Bindable private List<Group> golist2;
	@Bindable private List<Group> recent4;
	@Bindable private List<Group> groupOnlist;//热门团购
	@Bindable private List<Group> superValuelist;//超值热卖
	@Bindable private List<Group> themelist;//主题旅游
	@Bindable private List<Group> secKilllist;//秒杀
	@Bindable private List<Group> secopeninglist;
	@Bindable private List<Route> lazyOnelist;//轻松一日
	@Bindable private List<Route> lazyTwolist;//轻松二日
	@Bindable private List<Route> civillist;//境内多日
	@Bindable private List<Route> oversealist;//境外多日
	@Bindable private List<Group> recomandlist;//经理推荐
	
	
	@Bindable private String imagel1 = Info.imgLink1;
	@Bindable private String imagel2 = Info.imgLink2;
	@Bindable private String imagel3 = Info.imgLink3;
	@Bindable private String imagel4 = Info.imgLink4;
	@Bindable private String imagel5 = Info.imgLink5;
	@Bindable private String imagel6 = Info.imgLink6;
	@Bindable private String vipImglink = Info.imgLink5;
	
	@Bindable private Button smart_search = new Button("smart_search", "smart_search");
	@Bindable private Button choose_depart_city = new Button("choose_depart_city", "choose_depart_city");
	@Bindable private Button create_kefu_list = new Button("create_kefu_list", "create_kefu_list");	
	
	public Index()
	{


	}
	
	@Override
	public void onInit() {
		// Update navigation link
		//notice=Info.indexText;
		
		
		cur_customer = (Customer)this.getContext().getSession().getAttribute("customer");
		info = this.getWebService().getIndexInfo();
		if(info.size()>0)
		{
			imagel1 = info.get(0).getLinkUrl();
			imagel2 = info.get(1).getLinkUrl();
			imagel3 = info.get(2).getLinkUrl();
			imagel4 = info.get(3).getLinkUrl();
			imagel5 = info.get(4).getLinkUrl();
			vipImglink = info.get(5).getLinkUrl();
			imagel6 = info.get(6).getLinkUrl();
		}
		
		 List<com.hino.model.Info> naviInfos = this.getWebService().getIndexNavigationFamousPlace();
		 List<String> famousPlaces = new ArrayList<String>();
		 if(naviInfos.size() > 0){
			 famousPlaces = naviInfos.get(0).genFamousPlaces();
		 }
		 addModel("famousPlaces", famousPlaces);
		 
		 String defKey = "";
		 List<com.hino.model.Info> defKeys = this.getWebService().getDefalutSearchKeywords();
		 if(defKeys.size() > 0) {
			 defKey = defKeys.get(0).getLinkUrl();
		 }
		 addModel("defKey", defKey);
		 
		 qqList = getWebService().getIndexQQService();
		 addModel("qqList", qqList);
		 
		 create_kefu_list.setId("create_kefu_list");
		 create_kefu_list.addBehavior(new DefaultAjaxBehavior() {
			public ActionResult onAction(Control source) {
				StringBuffer jsondata = new StringBuffer();				
				jsondata.append("{\"kefus\": [");				
			
				if(qqList.size() == 0) {
					qqList = getWebService().getIndexQQService();
				}
				
				boolean isFirstTimeIn = true;
				for(QQService kf : qqList){
					
					if(! isFirstTimeIn) {
						jsondata.append(",");
					} else {
						isFirstTimeIn = false;
					}
					
					jsondata.append("{");
					jsondata.append("\"sn\":\"").append(kf.getServiceName()).append("\"");
					jsondata.append(", \"qq\":\"").append(kf.getQqNumber()).append("\"");
					jsondata.append("}");
				}
				
				jsondata.append("]}");
				System.out.println(qqList.size() + "\t" + jsondata.toString());
				return new ActionResult(jsondata.toString(), ActionResult.JSON);
			}});
		 
		 choose_depart_city.setId("choose_depart_city");
		 choose_depart_city.addBehavior(new DefaultAjaxBehavior() {
			public ActionResult onAction(Control source) {
				String key = getContext().getRequest().getParameter("key");
				StringBuffer jsondata = new StringBuffer();
				jsondata.append("{\"key\":\"").append(key).append("\"");
				jsondata.append(", \"cities\": [");
				
				List<GroupSumByPickupDto> groupSumByPickups = getWebService().getGroupSumByPickupCity("", key);
				boolean isFirstTimeIn = true;
				for(GroupSumByPickupDto gsp : groupSumByPickups){
					
					// if (gsp.getGroupCount() == 0) continue;
					
					if(! isFirstTimeIn) {
						jsondata.append(",");
					} else {
						isFirstTimeIn = false;
					}
					
					jsondata.append("{");
					jsondata.append("\"name\":\"").append(gsp.getPickupCity()).append("\"");
					jsondata.append(", \"cname\":\"").append(gsp.genChineseCityName()).append("\"");
					jsondata.append(", \"code\":\"").append(gsp.genCityCode()).append("\"");
					jsondata.append(", \"count\":\"").append(gsp.getGroupCount()).append("\"");
					jsondata.append("}");
				}
				
				jsondata.append("]}");
				System.out.println(groupSumByPickups.size() + "\t" + jsondata.toString());
				return new ActionResult(jsondata.toString(), ActionResult.JSON);
			}});
		 		 		 
		 smart_search.setId("smart_search");
		 smart_search.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String key = getContext().getRequest().getParameter("key");             	
            	String dcode = getContext().getRequest().getParameter("dcode");
            	
            	StringBuffer jsondata = new StringBuffer();
            	jsondata.append("{\"key\":\"").append(key).append("\"");
            	jsondata.append(", \"dcode\":\"").append(dcode).append("\"");
            	
            	// order by depart date ascending
            	SearchResultDto searchResult = getSearchResultService().searchByDestination(key, 0, 10000, 0, 0, 0, Info.translatePlaceName(dcode, Info.TRANSLATE_FROM_CODE_TO_ENGLISH), 0, 0);
            	
            	List<SearchResultItemDto> searchResultItems = searchResult.getItems();
            	
            	StringBuffer routeitem = new StringBuffer();
            	double groupPrice = 0;
            	String departDays = "";
            	String departCities = "";
            	boolean isFirstComingIn = true;
            	boolean isFirstComingIn2 = true;
            	boolean hasGroups = false;
            	int count = 0;
            	int daysCount = 0;
            	jsondata.append(", \"set\": [");
            	if(searchResultItems.size() > 0){
            		ArrayList<String> yearMonths = new ArrayList<String>();
            		String ym = "";
            		
            		// work out how many months have groups associated
            		for(SearchResultItemDto sri : searchResultItems) {
            			for(Group g : sri.getGroups()){
            				ym = String.valueOf(g.getDepart_date().get(Calendar.YEAR)) + (g.getDepart_date().get(Calendar.MONTH) < 10? "0" + String.valueOf(g.getDepart_date().get(Calendar.MONTH)): String.valueOf(g.getDepart_date().get(Calendar.MONTH)));
            				
            				if(!yearMonths.contains(ym)) {
            					yearMonths.add(ym);
            				}
            			}
            		}
            		
            		// Sort 
            		Collections.sort(yearMonths);
            		
            		for(String ymi : yearMonths) {
            			isFirstComingIn = true;            			
            			
            			if (!isFirstComingIn2) {
            				jsondata.append(",");            				
            			} else {
            				isFirstComingIn2 = false;
            			}
            			
            			jsondata.append("{\"month\":\"").append(Info.translateToChineseMonth(Integer.valueOf(ymi.substring(4)) + 1)).append("\", \"routes\": [");
            			
	            		for(SearchResultItemDto sri : searchResultItems) {
	            			groupPrice = 0;
	            			departDays = "";
	            			departCities = "";
	            			hasGroups = false;	            			
	            			daysCount = 0;
	            			
	            			for(Group g : sri.getGroups()){
	            				// only target month's groups
	            				if(ymi.equals(String.valueOf(g.getDepart_date().get(Calendar.YEAR)) + (g.getDepart_date().get(Calendar.MONTH) < 10? "0" + String.valueOf(g.getDepart_date().get(Calendar.MONTH)): String.valueOf(g.getDepart_date().get(Calendar.MONTH))))) {
		            				if(groupPrice <= 0) {
		            					// Ken Chen 2013-03-27 如果是团购显示团购价格
		            					if(g.getRoute().genRouteType() == Info.ROUTE_TYPE_CZTG){
		            						groupPrice = (g.getGroup_price() > 0 ? g.getGroup_price() : g.getPrice());
		            					}
		            					else
		            					{
		            						groupPrice = g.getPrice();
		            					}
		            					for(String pc : g.genFuzzyPickupCity()) {
		            						departCities = departCities + pc + " ";
		            					}
		            				}
		            				
		            				departDays = departDays + g.getDepart_date().get(Calendar.DAY_OF_MONTH) + "号 ";
		            				
		            				hasGroups = true;
		            				
		            				// Only show a maximum of 4 days on page
		            				if(++ daysCount >= 4) break;
	            				}
	            			}
	            			
	            			if(hasGroups) {
		            			// skip the first time
		            			if(!isFirstComingIn) {
		            				jsondata.append(", ");		            				
		            			} else {
		            				isFirstComingIn = false;
		            			}
		            			
		            			routeitem = new StringBuffer();
		            			
		            			routeitem.append("{\"route\":{\"name\":\"")
		            					 .append(sri.getRoute().genPureName())
		            					 .append("\",\"id\":\"")
		            					 .append(sri.getRoute().getId())
		            					 .append("\",\"type\":\"")
		            					 .append(sri.getRoute().genRouteType())
		            					 .append("\",\"price\":\"")
		            					 .append(groupPrice)
		            					 .append("\", \"departDays\":\"")
		            					 .append(departDays)
		            					 .append("\", \"departCities\":\"")
		            					 .append(Info.cutEnglishString(departCities, 20, "..."))
		            					 .append("\"}}");
		            			
		            			jsondata.append(routeitem);
	            			}
	            		}
	            		
	            		jsondata.append("]}");
	            		
	            		// maximum showing 3 sets of routes on index page - 1 set corresponds to 1 month
	            		if (++ count >= 3) break;
            		}
            	}
            	
            	jsondata.append("], \"count\":\"").append(count).append("\"}");
            	
            	System.out.println(jsondata.toString());
            	
                return new ActionResult(jsondata.toString(), ActionResult.JSON);
            }
        });
	}


	/**
	 * Clear all session objects to logout
	 * @return
	 */
	public boolean logout() {
		getContext().getSession().invalidate();
    	setRedirect(Index.class);
    	return true;
	}
	
	@Override
    public void onRender() {
		List<WebMenuRoute> webMenuRouteList = getWebService().getIndexMenu().getMenu_routes();
		// Sort routes according to their prioritys
		Collections.sort(webMenuRouteList);
        //List<Customer> customers = customerService.getCustomersSortedByName(7);
        addModel("webMenuRouteList", webMenuRouteList);
        //getFormat().setEmptyString("&nbsp;");
        golist = getWebService().getGoGroup(4, 0);
        golist2 = getWebService().getGoGroup(6, 0);
        recent4=getWebService().getRecentGroup();
        //Kevin Zhong - 26/09/2012 - Notice Board
        notice=getNoticeService().GetNoticeBoardForToday().getContent();
        link = getNoticeService().GetNoticeBoardForToday().getLink();
        if( notice.equals("")) {
        	notice=Info.indexText;
        }
        
        if (recent4.size()>4)
        {
        //Kevin Zhong - 12/09/2012 - show 4 groups
        recent4 = recent4.subList(0, 4);
        } 
        
        if (webMenuRouteList.size()>3)
        {
        	webMenuRouteList = webMenuRouteList.subList(0, 3);
        } 
        secopeninglist= this.getWebService().getSecGroup(2, 0);
        
        groupOnlist = (List<Group>)this.getSalesService().list_group_by_group_tag(4,Info.GROUP_TAG_SELL_TYPE, Info.GROUP_TAG_SELL_TYPE_GROUP_ON);
        superValuelist = (List<Group>)this.getSalesService().list_group_by_group_tag(4,Info.GROUP_TAG_SELL_TYPE, Info.GROUP_TAG_SELL_TYPE_SUPER_VALUE);
        secKilllist = (List<Group>)this.getSalesService().list_group_by_group_tag(4,Info.GROUP_TAG_SELL_TYPE, Info.GROUP_TAG_SELL_TYPE_SEC_KILL);
        themelist = (List<Group>)this.getSalesService().list_group_by_group_tag(4,Info.GROUP_TAG_CLASS, Info.GROUP_TAG_CLASS_THEME);
        lazyOnelist = (List<Route>)this.getSalesService().list_route_by_group_tag(100,Info.GROUP_TAG_TRAVEL_DAY, Info.GROUP_TAG_TRAVEL_DAY_LAZY_ONE);
        lazyTwolist = (List<Route>)this.getSalesService().list_route_by_group_tag(100,Info.GROUP_TAG_TRAVEL_DAY, Info.GROUP_TAG_TRAVEL_DAY_LAZY_TWO);
        civillist = (List<Route>)this.getSalesService().list_route_by_group_tag(100,Info.GROUP_TAG_TRAVEL_DAY, Info.GROUP_TAG_TRAVEL_DAY_CIVIL_MANY);
        oversealist = (List<Route>)this.getSalesService().list_route_by_group_tag(100,Info.GROUP_TAG_TRAVEL_DAY, Info.GROUP_TAG_TRAVEL_DAY_ABORD_MANY);
        recomandlist= (List<Group>)this.getSalesService().list_group_by_group_tag(100,Info.GROUP_TAG_SELL_TYPE, Info.GROUP_TAG_SELL_TYPE_MANAGE_RECOMAND);
       
        addModel("groupOnlist", groupOnlist);
        addModel("superValuelist", superValuelist);
        addModel("secKilllist", secKilllist);
        addModel("secopeninglist", secopeninglist);
        addModel("themelist", themelist);
        addModel("lazyOnelist", lazyOnelist);
        addModel("lazyTwolist", lazyTwolist);
        addModel("civillist", civillist);
        addModel("oversealist", oversealist);
        addModel("recomandlist", recomandlist);
        
    }
	
    @Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./images/index_new.css"));
            headElements.add(new CssImport("./images/index_hot_group.css"));
        }
        return headElements;
    }
}
