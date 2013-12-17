package com.hino.page.zh;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.util.Bindable;

import com.hino.dto.SearchResultDto;
import com.hino.util.Info;
import com.hino.util.Page;
import com.hino.util.SearchType;

/**
 * 
 * @author Devon King - 2012/09/02
 *
 */
public class SearchResult extends BasePageOther {

	private static final long serialVersionUID = -233174730958051481L;
	@Bindable private String k; // keyword
	@Bindable private Integer st; // search type
	@Bindable private Integer ct;
	@Bindable private Integer np; // next page number
	
	@Bindable private String dest = ""; // destination
		
	private String sRedirctUrl = "";
	@Bindable private String sKeywords;
	
	@Bindable private int obdd = 0;
	@Bindable private int obp = 0;
	@Bindable private int obd = 0;
	@Bindable private int dcode = 0; // depart city code
	@Bindable private int dm = 0; // depart month
	@Bindable private int routeType = 0;
	@Bindable private int idx = 0; // from index page
	@Bindable private double lp = 0;
	@Bindable private double up = 0;
	@Bindable private String fp = ""; // famous place
	@Bindable private int ld = 0; // lower bound days
	@Bindable private int ud = 0; // upper bound days
	
	@Override
	public void onInit() {
		qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);		
		//setRedirect(MyAccount.class);
		String message = "Some messages for testing...";
		
		addModel("message", message);
		
		sKeywords = this.getContext().getRequest().getParameter("txt_s_k");
		
	}
	
	@Override
	public void onRender(){
		
		SearchResultDto searchResult = null;
		if (null == np || np == 0) {
			np = 1;
		}
		
		// Searching by departure month 
		if (null != st && st == SearchType.HOT_SEARCH_MONTH) {
			int month = Integer.valueOf(k);
			if (month > 0 && month <=12) {
				searchResult = getSearchResultService().searchInformationByMonth(month, np, 5, obdd, obp, obd, tanslateDepartCityFromChineseToEnglish(dcode),routeType);
			}
			addModel("searchResult", searchResult);
			addModel("orderByDepartDate", obdd);
			addModel("orderByPrice", obp);
			addModel("orderByDays", obd);
			addModel("departCityCode", dcode);
			addModel("destination", "");
			addModel("departMonthCode", month);
			
			addModel("departMonth", "");
			
			addModel("routeType", routeType);
			addModel("routeTypeName", Info.convertRouteTypeToName(routeType));
			
			addModel("pickupCity", Info.translatePlaceName(String.valueOf(dcode), Info.TRANSLATE_FROM_CODE_TO_CHINESE));
			addModel("noResult", 0);

		// Devon King - 2012/10/02 - TD#27 Searching by destination
		} else if (null != st && st == SearchType.HOT_SEARCH_DESTINATION) {

			// Only for request from index page
			if (idx == 1) {
				dest = sKeywords;
			} else if (idx == 2) {
				dest = this.getContext().getRequest().getParameter("txt_line_s_k");
			}
			
			if (dest == null) {
				dest = "";
			}
			
			searchResult = getSearchResultService().searchByDestination(dest, np, 5, obdd, obp, obd, tanslateDepartCityFromChineseToEnglish(dcode), routeType, dm);
			
			addModel("searchResult", searchResult);
			addModel("orderByDepartDate", obdd); 
			addModel("orderByPrice", obp);
			addModel("orderByDays", obd);
			addModel("departCityCode", dcode);
			addModel("destination", dest);
			addModel("routeType", routeType);
			addModel("routeTypeName", Info.convertRouteTypeToName(routeType));
			addModel("departMonthCode", dm);
			
			if (1 <= dm && dm <= 12) {
				addModel("departMonth", Info.translateToChineseMonth(dm));
			} else {
				addModel("departMonth", "");
			}
			
			addModel("pickupCity", Info.translatePlaceName(String.valueOf(dcode), Info.TRANSLATE_FROM_CODE_TO_CHINESE));
			addModel("noResult", 0);

		} else if (null != st && (st == SearchType.NAVIGATION_SEARCH_PRICE || st == SearchType.NAVIGATION_SEARCH_SCHEDULES || st == SearchType.NAVIGATION_SEARCH_FAMOUS_PLACE)) {

			//./search_result.htm?idx=1&dcode=178&lp=1&up=200
			// Only for request from index page
			if (idx == 1) {				
				dest = sKeywords;			
			} else if (idx == 2) {
				dest = this.getContext().getRequest().getParameter("txt_line_s_k");
			}
			
			if (dest == null) {
				dest = "";
			}
			
			String priceRange = "£";
			if (st == SearchType.NAVIGATION_SEARCH_FAMOUS_PLACE) {
				if(idx != 0) {
					fp = sKeywords;
					dest = "";
				}
				searchResult = getSearchResultService().searchByFamousPlace(dest, np, 5, obdd, obp, obd, tanslateDepartCityFromChineseToEnglish(dcode), routeType, dm, fp);
				addModel("famousPlace", fp);
			} else if (st == SearchType.NAVIGATION_SEARCH_SCHEDULES) {				
				addModel("dayRange", ld + "-" + ud + "天");
				searchResult = getSearchResultService().searchByScheduleDays(dest, np, 5, obdd, obp, obd, tanslateDepartCityFromChineseToEnglish(dcode), routeType, dm, ld, ud);
			} else if (st == SearchType.NAVIGATION_SEARCH_PRICE) {
				searchResult = getSearchResultService().searchByPrice(dest, np, 5, obdd, obp, obd, tanslateDepartCityFromChineseToEnglish(dcode), routeType, dm, lp, up);
				if ((long)lp <= 1 && (long)up > 1) {
					priceRange = priceRange + (long)up + "以下";
				} else if ((long)up >= 1000000) {
					priceRange = priceRange + (long)lp + "以上";
				} else {
					priceRange = priceRange + (long)lp + "-" + (long)up;
				}
				
				addModel("priceRange",  priceRange);
			}
			
			addModel("searchResult", searchResult);
			addModel("orderByDepartDate", obdd); 
			addModel("orderByPrice", obp);
			addModel("orderByDays", obd);
			addModel("departCityCode", dcode);
			addModel("destination", dest);
			addModel("routeType", routeType);
			addModel("routeTypeName", Info.convertRouteTypeToName(routeType));
			addModel("departMonthCode", dm);
			
			if (1 <= dm && dm <= 12) {
				addModel("departMonth", Info.translateToChineseMonth(dm));
			} else {
				addModel("departMonth", "");
			}
			
			addModel("pickupCity", Info.translatePlaceName(String.valueOf(dcode), Info.TRANSLATE_FROM_CODE_TO_CHINESE));
			addModel("noResult", 0);

		} else {
			// Process the Defined Keywords			
			if(redirectSpecialKeywords() == true) {
				this.setRedirect(sRedirctUrl);
				return;
			}else{
				searchResult = new SearchResultDto();
				Page page = new Page();
				page.setCurrentPage(0);
				page.setSize(1);
				page.setTotalCount(0);
				searchResult.setPage(page);
				searchResult.setKeyword(sKeywords);
				addModel("searchResult", searchResult);
				addModel("noResult", 1);				
			}
		}
	}
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./images/search.css"));
        }
        return headElements;
    }	
	
	public String tanslateDepartCityFromChineseToEnglish(int destCode){
		String dest = "";
		
		switch (destCode){
		case 178: //诺丁汉
			dest = "Nottingham";
			break;
		case 179: //曼彻斯特
			dest = "Manchester";
			break;
		case 180: //伯明翰
			dest = "Birmingham";
			break;
		case 181: //华 威
			dest = "Warwick";
			break;
		case 182: //考文垂
			dest = "Coventry";
			break;
		case 183: //拉夫堡
			dest = "Loughborough";
			break;
		case 184: //谢非尔德
			dest = "Sheffield";
			break;
		case 185: //莱斯特
			dest = "Leicester";
			break;
		case 186: //伦敦
			dest = "London";
			break;
		default:
			dest = "";
			break;
		}
		
		return dest;
	}
	
	public boolean redirectSpecialKeywords(){
		HashMap<String, String> hm  = new HashMap<String, String> ();
				
		hm.put("剑桥","http://www.hinotravel.com/zh/route_detail.htm?routeid=32");
		hm.put("牛津","http://www.hinotravel.com/zh/route_detail.htm?routeid=31");
		hm.put("比斯特","http://www.hinotravel.com/zh/route_detail.htm?routeid=16");
		hm.put("温莎","http://www.hinotravel.com/zh/route_detail.htm?routeid=4");
		hm.put("巴斯","http://www.hinotravel.com/zh/route_detail.htm?routeid=4");
		hm.put("巨石阵","http://www.hinotravel.com/zh/route_detail.htm?routeid=4");
		hm.put("布莱顿"," http://www.hinotravel.com/zh/route_detail.htm?routeid=47");
		hm.put("怀特岛"," http://www.hinotravel.com/zh/route_detail.htm?routeid=47");
		hm.put("斯卡博勒集市","http://www.hinotravel.com/zh/route_detail.htm?routeid=78");
		hm.put("斯卡博勒海滩","http://www.hinotravel.com/zh/route_detail.htm?routeid=78");
		hm.put("伦敦","http://www.hinotravel.com/zh/route_detail.htm?routeid=11");
		hm.put("约克","http://www.hinotravel.com/zh/route_detail.htm?routeid=30");
		hm.put("约克教堂","http://www.hinotravel.com/zh/route_detail.htm?routeid=30");
		hm.put("巧克力（工厂）","http://www.hinotravel.com/zh/route_detail.htm?routeid=43");
		hm.put("巧克力工厂","http://www.hinotravel.com/zh/route_detail.htm?routeid=43");
		hm.put("天涯海角","http://www.hinotravel.com/zh/route_detail.htm?routeid=6");
		hm.put("伊甸园","http://www.hinotravel.com/zh/route_detail.htm?routeid=6");
		hm.put("浪漫","http://www.hinotravel.com/zh/route_detail.htm?routeid=6");
		hm.put("Alton Towers","http://www.hinotravel.com/zh/route_detail.htm?routeid=42");
		hm.put("游乐场","http://www.hinotravel.com/zh/route_detail.htm?routeid=42");
		hm.put("苏格兰","http://www.hinotravel.com/zh/route_detail.htm?routeid=13");
		hm.put("湖区","http://www.hinotravel.com/zh/route_detail.htm?routeid=13");
		hm.put("爱丁堡","http://www.hinotravel.com/zh/route_detail.htm?routeid=13");
		hm.put("尼斯湖","http://www.hinotravel.com/zh/route_detail.htm?routeid=13");
		hm.put("苏格兰skye岛","http://www.hinotravel.com/zh/route_detail.htm?routeid=44");
		hm.put("北爱尔兰","http://www.hinotravel.com/zh/route_detail.htm?routeid=14");
		hm.put("巨人之路","http://www.hinotravel.com/zh/route_detail.htm?routeid=14");
		hm.put("索桥","http://www.hinotravel.com/zh/route_detail.htm?routeid=14");
		hm.put("泰坦尼克","http://www.hinotravel.com/zh/route_detail.htm?routeid=14");
		hm.put("贝尔法斯特","http://www.hinotravel.com/zh/route_detail.htm?routeid=14");
		hm.put("埃及","http://www.hinotravel.com/zh/route_detail.htm?routeid=10");
		hm.put("非洲","http://www.hinotravel.com/zh/route_detail.htm?routeid=10");
		hm.put("红海","http://www.hinotravel.com/zh/route_detail.htm?routeid=10");
		hm.put("金字塔","http://www.hinotravel.com/zh/route_detail.htm?routeid=10");
		hm.put("开罗","http://www.hinotravel.com/zh/route_detail.htm?routeid=10");
		hm.put("西班牙","http://www.hinotravel.com/zh/route_detail.htm?routeid=37");
		hm.put("葡萄牙","http://www.hinotravel.com/zh/route_detail.htm?routeid=37");
		hm.put("里斯本","http://www.hinotravel.com/zh/route_detail.htm?routeid=37");
		hm.put("马德里","http://www.hinotravel.com/zh/route_detail.htm?routeid=37");
		hm.put("塞维利亚","http://www.hinotravel.com/zh/route_detail.htm?routeid=37");
		hm.put("托雷多","http://www.hinotravel.com/zh/route_detail.htm?routeid=37");
		hm.put("巴塞罗那","http://www.hinotravel.com/zh/route_detail.htm?routeid=37");
		hm.put("瓦伦西亚","http://www.hinotravel.com/zh/route_detail.htm?routeid=37");
		hm.put("法国","http://www.hinotravel.com/zh/route_detail.htm?routeid=23");
		hm.put("巴黎","http://www.hinotravel.com/zh/route_detail.htm?routeid=23");
		hm.put("荷兰","http://www.hinotravel.com/zh/route_detail.htm?routeid=23");
		hm.put("比利时","http://www.hinotravel.com/zh/route_detail.htm?routeid=23");
		hm.put("德国","http://www.hinotravel.com/zh/route_detail.htm?routeid=23");
		hm.put("卢森堡 ","http://www.hinotravel.com/zh/route_detail.htm?routeid=23");
		hm.put("希腊","http://www.hinotravel.com/zh/route_detail.htm?routeid=49");
		hm.put("爱琴海","http://www.hinotravel.com/zh/route_detail.htm?routeid=49");
		hm.put("雅典","http://www.hinotravel.com/zh/route_detail.htm?routeid=49");
		hm.put("圣托里尼","http://www.hinotravel.com/zh/route_detail.htm?routeid=49");
		hm.put("意大利","http://www.hinotravel.com/zh/route_detail.htm?routeid=63");
		hm.put("瑞士","http://www.hinotravel.com/zh/route_detail.htm?routeid=63");
		hm.put("梵蒂冈","http://www.hinotravel.com/zh/route_detail.htm?routeid=63");
		hm.put("日内瓦","http://www.hinotravel.com/zh/route_detail.htm?routeid=63");
		hm.put("米兰","http://www.hinotravel.com/zh/route_detail.htm?routeid=63");
		hm.put("威尼斯","http://www.hinotravel.com/zh/route_detail.htm?routeid=63");
		hm.put("罗马","http://www.hinotravel.com/zh/route_detail.htm?routeid=63");
		hm.put("冰岛","http://www.hinotravel.com/zh/route_detail.htm?routeid=61");
		hm.put("极光","http://www.hinotravel.com/zh/route_detail.htm?routeid=61");
		hm.put("南法","http://www.hinotravel.com/zh/route_detail.htm?routeid=76");
		hm.put("薰衣草","http://www.hinotravel.com/zh/route_detail.htm?routeid=76");
		hm.put("圣十字湖","http://www.hinotravel.com/zh/route_detail.htm?routeid=76");
		hm.put("阿尔卑斯","http://www.hinotravel.com/zh/route_detail.htm?routeid=76");
		hm.put("北欧","http://www.hinotravel.com/zh/route_detail.htm?routeid=75");
		hm.put("丹麦","http://www.hinotravel.com/zh/route_detail.htm?routeid=75");
		hm.put("挪威","http://www.hinotravel.com/zh/route_detail.htm?routeid=75");
		hm.put("瑞典","http://www.hinotravel.com/zh/route_detail.htm?routeid=75");
		hm.put("奥斯陆","http://www.hinotravel.com/zh/route_detail.htm?routeid=75");
		hm.put("哥德堡","http://www.hinotravel.com/zh/route_detail.htm?routeid=75");
		hm.put("哥本哈根","http://www.hinotravel.com/zh/route_detail.htm?routeid=75");
		hm.put("北威尔士","http://www.hinotravel.com/zh/route_detail.htm?routeid=62");
		hm.put("卡迪夫","http://www.hinotravel.com/zh/route_detail.htm?routeid=5");
		hm.put("斯旺西","http://www.hinotravel.com/zh/route_detail.htm?routeid=5");
		hm.put("海滨","http://www.hinotravel.com/zh/route_detail.htm?routeid=5");
		hm.put("城堡","http://www.hinotravel.com/zh/route_detail.htm?routeid=5");
		hm.put("Vip","http://www.hinotravel.com/zh/route_detail.htm?routeid=60");
		hm.put("会员","http://www.hinotravel.com/zh/route_detail.htm?routeid=60");
		hm.put("包车","http://www.hinotravel.com/zh/car_service.htm");
		hm.put("拼车","http://www.hinotravel.com/zh/car_service.htm");
		hm.put("巴士","http://www.hinotravel.com/zh/car_service.htm");
		hm.put("送机","http://www.hinotravel.com/zh/car_service.htm");
		
		Iterator<Entry<String, String>> iter = hm.entrySet().iterator();
		while (iter.hasNext()) {
			java.util.Map.Entry entry = (java.util.Map.Entry) iter.next();
			if(entry.getKey().equals(sKeywords))
			{
				sRedirctUrl = entry.getValue().toString();
				return true;
			}
		}
		
		return false;
	}
	
}
