package com.hino.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.click.control.Option;


public final class Info {
	
	public static String indexText = "服务四海 一诺千金, Welcome to Hino travel!";
	public static String imgLink1 = "http://www.hinotravel.com/";
	public static String imgLink2 = "http://www.hinotravel.com/";
	public static String imgLink3 = "http://www.hinotravel.com/";
	public static String imgLink4 = "http://www.hinotravel.com/";
	public static String imgLink5 = "http://www.hinotravel.com/";
	public static String imgLink6 = "http://www.hinotravel.com/";
	
	public static String imgLink11 = "http://www.hinotravel.com/";
	public static String imgLink22 = "http://www.hinotravel.com/";
	public static String imgLink33 = "http://www.hinotravel.com/";
	public static String imgLink44 = "http://www.hinotravel.com/";
	public static String imgLink55 = "http://www.hinotravel.com/";
	
	
	public static final byte LANG_CN = 0;
	// Email receiver type
	public static final int EMAIL_TO = 0;
	public static final int EMAIL_CC = 1;
	public static final int EMAIL_BC = 2;
	
	//Gender String
	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;
	public final static String[] GENDER_STR_CN = new String[]{"男","女"};
	public final static String[] GENDER_STR_EN = new String[]{"MALE","FEMALE"};
	// Customer email type
	public static final int CUSTOMER_GROUP = 0;
	public static final int CUSTOMER_ALL = 1;
	public static final int CUSTOMER_LAST_MONTH = 2;
	public static final int CUSTOMER_LAST_THREEE_MONTH = 3;
	public static final int CUSTOMER_LAST_SIX_MONTH = 4;
	
	// Staff message/email type
	public static final String TYPE_MSG = "msg";
	public static final String TYPE_EMAIL = "email";
	
	// Group status
	public final static int GS_PLANNING = 0;//计划中
	public final static int GS_OPENNING = 1;
	public final static int GS_PROCESSING = 2;
	public final static int GS_FINISHED = 3;
	public final static int GS_CANCELED = 4;
	public final static int GS_ALL = 5;//所有状态	
	
	public final static String[] GS_STR_CN = new String[]{"计划中","报名中","出团中","已结束","已取消"};

	//Booking status
	public final static int BKS_RESERVING = 0;
	public final static int BKS_TRANSFERING = 1;
	public final static int BKS_CONFIRMED = 2;
	public final static int BKS_CANCELED = 3;
	
	public final static String[] BKS_STR_CN = new String[]{"未确认","付款中","已确认","已取消"};
	public final static String[] BKS_STR_EN = new String[]{"unconfirmed", "processing", "confirmed", "canceled"};
	//Booking method & VIP order method
	public final static int BKM_INTERNAL = 0;
	public final static int BKM_EXTERNAL_BANKTRANSFER = 1;
	public final static int BKM_EXTERNAL_GOOGLECHECKOUT = 2;
	public final static int BKM_EXTERNAL_PAYATBRANCH =3;
	public final static int BKM_EXTERNAL_BARCLAYCARD =4;
	public final static String[] BKM_STR_CN = new String[]{"内部预订", "外部/银行转帐", "外部/GC", "外部/前台", "Barclaycard"};
	
	//Airline Booking payer
	public final static int BKP_PAYER_SELF=0;
	public final static int BKP_PAYER_SALES=1;
	public final static int BKP_PAYER_OTHER=2;
	public final static String[] BKP_STR_CN = new String[]{"客人自行转账", "销售本人转账", "其他"};
	
	//Airline Booking payment method
	public final static int ABKM_PAYMENT_PAYPAL=0;
	public final static int ABKM_PAYMENT_BARCLAYS=1;
	public final static int ABKM_PAYMENT_CASH=2;
	public final static int ABKM_PAYMENT_COMPANY_TRANSFER_CAD=3;
	public final static int ABKM_PAYMENT_ONLINE_TRANSFER=4;
	public final static int ABKM_PAYMENT_OTHER=5;
	public final static String[] ABKM_STR_CN = new String[]{"paypal付款", "barclays转账", "现金付款", "公司刷卡", "在线刷卡", "其他"};
	
	//Airline 
	public final static int AL_BACK_LIMIT_DEADLINE = 0;
	public final static int AL_BACK_LIMIT_MAX_DAY = 1;
	public final static int AL_BACK_LIMIT_MAX_MONTH = 2;
	public final static String[] ALLM_STR_CN = new String[]{"旅行截止日期","最大停留天数", "最大停留月"};
	
	//VIP order collection method
	public final static int VCM_DELIVERY = 0;
	public final static int VCM_COLLECT = 1;
	
	public final static String[] VCM_STR_CN = new String[]{"邮递", "自取"};
	
	//VIP order status
	public final static int VS_RESERVING = 0;
	public final static int VS_CONIFRMED = 1;
	public final static int VS_DELIVERED = 2;
	public final static int VS_CONIFRMED_NOTPAID = 3;
	
	public final static String[] VS_STR_CN = new String[]{"未确认", "已确认", "已发卡", "已确认未付款"};
	//transfer status
	public final static int TS_REVIEWING = 0;
	public final static int TS_PASSED = 1;
	public final static int TS_FAILED = 2;
	
	public final static String[] TS_STR_CN = new String[]{"审核中", "已通过", "未通过"};
	// File path prefix
	public final static String INTERNAL_PATH_PREFIX = "resource/";
	public final static String EXTERNAL_PATH_PREFIX = "internal/"+INTERNAL_PATH_PREFIX;
	
	// Define resource index
	// Regulation
	public final static int FILE_CATEGORY_INDEX_REG_SALESMAN = 0;
	public final static int FILE_CATEGORY_INDEX_REG_GUIDE = 1;
	public final static int FILE_CATEGORY_INDEX_REG_MARKET = 2;
	public final static int FILE_CATEGORY_INDEX_REG_SALES = 3;
	public final static int FILE_CATEGORY_INDEX_REG_FINANCE = 4;
	// Report
	public final static int FILE_CATEGORY_INDEX_REP_MARKET = 5;
	// Map
	public final static int FILE_CATEGORY_INDEX_MAP = 6;
	// General Guidebook
	public final static int FILE_CATEGORY_INDEX_GENERAL_GUIDEBOOK = 7;
	// Guidebook
	public final static int FILE_CATEGORY_INDEX_GUIDEBOOK = 8;
	// Maximum
	public final static int FILE_CATEGORY_MAXIMUM = 9;
	public final static String[] FILE_CATEGORY_NAME = 
		new String[FILE_CATEGORY_MAXIMUM];
	// Set resource names
	static {
		FILE_CATEGORY_NAME[FILE_CATEGORY_INDEX_REG_SALESMAN] = "销售代表规章制度";
		FILE_CATEGORY_NAME[FILE_CATEGORY_INDEX_REG_GUIDE] = "导游规章制度";
		FILE_CATEGORY_NAME[FILE_CATEGORY_INDEX_REG_MARKET] = "市场管理规章制度";
		FILE_CATEGORY_NAME[FILE_CATEGORY_INDEX_REG_SALES] = "销售管理规章制度";
		FILE_CATEGORY_NAME[FILE_CATEGORY_INDEX_REG_FINANCE] = "财务管理规章制度";
		FILE_CATEGORY_NAME[FILE_CATEGORY_INDEX_REP_MARKET] = "市场调研报告";
		FILE_CATEGORY_NAME[FILE_CATEGORY_INDEX_MAP] = "地图";
		FILE_CATEGORY_NAME[FILE_CATEGORY_INDEX_GENERAL_GUIDEBOOK] = "公司导游辞";
		FILE_CATEGORY_NAME[FILE_CATEGORY_INDEX_GUIDEBOOK] = "导游导游辞";
	}
	
	// Set Price Range
	public final static String PRICE_RANGE_1_20 = "20以下";
	public final static String PRICE_RANGE_20_100 = "20-100";
	public final static String PRICE_RANGE_101_200 = "101-200";
	public final static String PRICE_RANGE_201_300 = "201-300";
	public final static String PRICE_RANGE_301_400 = "301-400";
	public final static String PRICE_RANGE_401_500 = "401-500";
	public final static String PRICE_RANGE_500 = "500以上";
	
	// Route type
	public final static int ROUTE_TYPE_CGT = 1;  // 常规团
	public final static int ROUTE_TYPE_JPXT = 2;  // 精品小团
	public final static int ROUTE_TYPE_CZTG = 3;  // 超值团购
	public final static int ROUTE_TYPE_DIY = 4;  // DIY拼团游
	public final static int ROUTE_TYPE_RMTJ = 5;  // 热门推荐
	
	public static String convertRouteTypeToName(int routeType)
	{
		String sReturn = "";
		switch(routeType)
		{
		case Info.ROUTE_TYPE_CGT:
			sReturn = "常规团";
			break;
		case Info.ROUTE_TYPE_JPXT:
			sReturn = "精品小团";
			break;
		case Info.ROUTE_TYPE_CZTG:
			sReturn = "团购";
			break;
		case Info.ROUTE_TYPE_DIY:
			sReturn = "DIY";
			break;
		case Info.ROUTE_TYPE_RMTJ:
			sReturn = "热门推荐";
			break;
		default:
			sReturn = "";
		}
		
		return sReturn;
	}
	
	//Booking type constant
	public final static int BOOKING_TYPE_EXTERNAL =0; 	//web（外部）直接预订
	public final static int BOOKING_TYPE_GROUPON =1; 	//团购预订
	public final static int BOOKING_TYPE_SECKILL =2; 	//秒杀预订
	public final static int BOOKING_TYPE_INTERNAL =3; 	//内部预订（员工）
	
	//Group Tag constant
	public final static int GROUP_TAG_TYPE = 1; 		//为团的类别
	public final static int GROUP_TAG_DEPARTURE = 2; 	//为出发地
	public final static int GROUP_TAG_DESTINATION = 3; 	//为目的地
	public final static int GROUP_TAG_GRADE = 4; 		//按产品等级 
	public final static int GROUP_TAG_CLASS = 5; 		//按产品类型
	public final static int GROUP_TAG_TRAVEL_DAY = 6; 	//按行程天数
	public final static int GROUP_TAG_SELL_TYPE = 7; 	//按贩卖类型
	
	public final static String GROUP_TAG_DESTINATION_UK = "31"; 	//为目的地 - UK
	public final static String GROUP_TAG_DESTINATION_EUROPE = "32"; 	//为目的地 - 非UK
	public final static String GROUP_TAG_DESTINATION_OTHER = "33"; 	//为目的地 - 非UK
	
	public final static String GROUP_TAG_CLASS_TOURISM = "51"; 	//按产品类型 - 观光
	public final static String GROUP_TAG_CLASS_THEME = "52"; 	//按产品类型 - 主题
	public final static String GROUP_TAG_CLASS_VACATION = "53"; 	//按产品类型 - 度假
	public final static String GROUP_TAG_CLASS_CRUISE = "54"; 	//按产品类型 - 邮轮
	
	public final static String GROUP_TAG_TRAVEL_DAY_LAZY_ONE = "61"; 	//按行程天数 - 休闲一日游
	public final static String GROUP_TAG_TRAVEL_DAY_LAZY_TWO = "62"; 	//按行程天数 - 休闲二日游
	public final static String GROUP_TAG_TRAVEL_DAY_CIVIL_MANY = "63";  //按行程天数 - 惬意境内多日游
	public final static String GROUP_TAG_TRAVEL_DAY_ABORD_MANY = "64";  //按行程天数 - 心醉境外多日游

	public final static String GROUP_TAG_SELL_TYPE_GROUP_ON = "71"; 		//按贩卖类型 - 1 团购 
	public final static String GROUP_TAG_SELL_TYPE_SUPER_VALUE = "72"; 		//按贩卖类型 - 2 超值热买
	public final static String GROUP_TAG_SELL_TYPE_SEC_KILL = "73"; 		//按贩卖类型 - 3 秒杀
	public final static String GROUP_TAG_SELL_TYPE_MANAGE_RECOMAND = "74"; 	//按贩卖类型 - 4 经理推荐
	public final static String GROUP_TAG_SELL_TYPE_DIY = "75"; 			//按贩卖类型 - 5 DIY
	
	public final static String INFO_DATA_SOURCE_SEARCH_KEYWORDS = "DEFAULT_KEYWORDS";	//Search Default keywords
	
	
	public final static int INVAILD_LONG_VALUE = -1;
	
	// Error code HashMap
	public final static int UNDEFINED = 1000;
	public final static int TARGET_OBJECT_NOT_FOUND = 1001;
	
	public final static int CUSTOMER_LOGIN_FAILED_USERNAME_NOT_FOUND = 2001;
	public final static int CUSTOMER_LOGIN_FAILED_PASSWORD_NOT_MATCHED = 2002;
	public final static int CUSTOMER_REG_FAILED_EMAIL_EXIST = 2003;
	public final static int CUSTOMER_RESET_PASSWORD_SUCCEED = 2004;
	public final static int CUSTOMER_RESET_PASSWORD_FAILED = 2005;
	
	public final static int STAFF_LOGIN_FAIL_EMAIL_EXIST = 2003;
	public final static int STAFF_LOGIN_FAILED_EMAIL_EXIST = 2003;
	public final static int STAFF_REG_FAILED_EMAIL_EXIST = 2003;
	
	public final static int STAFF_CREATE_FAILED_SN_EXIST = 3001;
	public final static int STAFF_CREATE_FAILED_AREA_EMPTY = 3002;
	public final static int STAFF_CREATE_FAILED_AREA_EXIST = 3003;
	public final static int STAFF_CREATE_FAILED_AREA_EMPTY2 = 3004;

	public final static int STAFF_UPDATE_FAILED_DUPLICATE_EMAIL = 4001;
	public final static int STAFF_UPDATE_FAILED_PASSWORD_ERROR = 4002;

	public final static int RESET_PASSWORD_FAILED_EMAIL_NOT_EXIST = 5001;
	public final static int RESET_PASSWORD_FAILED_SEND_EMAIL_ERROR = 5002;
	public final static int RESET_PASSWORD_FAILED_RESETCODE_NOT_EXIST = 5003;
	
	public final static int CUSTOMER_UPDATE_FAILED_PASSWORD_ERROR = 6001;
	
	public final static int CUSTOMER_EMAIL_NOT_FOUND = 7001;
	
	public final static String SHA_PASSPHRASE = "HinoPS"; 
	
	
	public final static HashMap<Integer, String> info = new HashMap<Integer, String>();
	
	static {
		info.put(UNDEFINED, "Undefined exception: 1000");
		
		
		info.put(CUSTOMER_LOGIN_FAILED_USERNAME_NOT_FOUND, "登录失败：用户名或密码错误！");
		info.put(CUSTOMER_LOGIN_FAILED_PASSWORD_NOT_MATCHED, "登录失败：用户名或密码错误！");
		info.put(CUSTOMER_REG_FAILED_EMAIL_EXIST, "exception: 2003, This email has already been registered!");
		
		info.put(CUSTOMER_RESET_PASSWORD_SUCCEED, "密码重置成功，请去该注册邮箱查看重置密码！");
		info.put(CUSTOMER_RESET_PASSWORD_FAILED, "密码重置失败，请联系工作人员！");
		
		info.put(CUSTOMER_REG_FAILED_EMAIL_EXIST, "exception: 2010, This email has already been registered!");
		
		info.put(STAFF_CREATE_FAILED_SN_EXIST, "exception: 3001, Staff creation faild caused by staff no already exists!");
		info.put(STAFF_CREATE_FAILED_AREA_EMPTY, "exception: 3002, Staff creation faild caused by Area field is empty!");
		info.put(STAFF_CREATE_FAILED_AREA_EXIST, "exception: 3003, Staff creation faild caused by Area field already exists!");
		
		info.put(STAFF_UPDATE_FAILED_DUPLICATE_EMAIL, "exception: 4001, Staff update faild caused by email already exists!");
		info.put(STAFF_UPDATE_FAILED_PASSWORD_ERROR, "exception: 4002, Staff update faild caused by incorrect password!");

		info.put(RESET_PASSWORD_FAILED_EMAIL_NOT_EXIST, "exception: 5001, Email doesn't exist!");
		info.put(RESET_PASSWORD_FAILED_SEND_EMAIL_ERROR, "exception: 5002, Can't send email!");
		info.put(RESET_PASSWORD_FAILED_RESETCODE_NOT_EXIST, "exception: 5003, Invalid reset code!");
		
		info.put(CUSTOMER_UPDATE_FAILED_PASSWORD_ERROR, "exception: 6001, Customer update faild caused by incorrect password!");
		
		info.put(CUSTOMER_EMAIL_NOT_FOUND, "exception: 7001, No customer email available for your choice!");
		
		info.put(TARGET_OBJECT_NOT_FOUND, "Exception: 1001, Requested object was no longer available!");
	}
	
	public static String getMsg(int key) {
		if (info.containsKey(key))
		{
			return info.get(key);
		}
		return info.get(UNDEFINED);
	}
	
	public static void printInfo(ArrayList<Integer> all)
	{
		for (int i=0;i<all.size();i++)
		{
			System.out.println(info.get(all.get(i)));
		}
	}
	
	public static String toStringInfo(ArrayList<Integer> all)
	{
		String infomsg = "";
		for (int i=0;i<all.size();i++)
		{
			infomsg = infomsg + info.get(all.get(i))+"<br>";
		}
		return infomsg;
	}
	
	
	public static String str_len_limit(String s, int len)
	{
		if(s==null)
		{
		return "";
		}else return s.substring(0, len-1);
	}
	/*
	public static void read() {
		try {
			File f = new File(System.getProperty("baobaotao.root") + "WEB-INF" + File.separator + "hinoconfig.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element foo;
			for (Iterator i = root.elementIterator("webmsg"); i.hasNext();) {
				foo = (Element) i.next();
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public final static int MAX_NUMBER_OF_CHARACTERS_TO_SHOW = 160;
	
	public final static int TRANSLATE_FROM_ENGLISH_TO_CHINESE = 1;
	public final static int TRANSLATE_FROM_CHINESE_TO_ENGLISH = 2;
	public final static int TRANSLATE_FROM_CODE_TO_ENGLISH =3;
	public final static int TRANSLATE_FROM_CODE_TO_CHINESE = 4;
	public final static int TRANSLATE_FROM_ENGLISH_TO_CODE = 5;
	public final static int TRANSLATE_FROM_CHINESE_TO_CODE = 6;
	public final static int AIRLINE_ALL_LAND_CITY = -999;
	
	public final static int AIRLINE_IMAGE_ID_1 = 10;
	public final static int AIRLINE_IMAGE_ID_2 = 11;
	public final static int AIRLINE_IMAGE_ID_3 = 12;
	public final static int AIRLINE_IMAGE_ID_4 = 13;
	public final static int AIRLINE_IMAGE_ID_5 = 14;
	public final static int AIRLINE_IMAGE_ID_6 = 15;
	
	
	public static String translatePlaceName(String oriVal, int transDirection){
		
		String[] places = {   "诺丁汉", "Nottingham", "178"
							, "曼彻斯特", "Manchester", "179"
							, "伯明翰", "Birmingham", "180"
							, "华威", "Warwick", "181"
							, "考文垂", "Coventry", "182"
							, "拉夫堡", "Loughborough", "183"
							, "谢非尔德", "Sheffield", "184"
							, "莱斯特", "Leicester", "185"
							, "伦敦", "London", "186"
							, "所有出发地", "All", "0"
							};
		int startIndex = 0;
		int offset = 0;
		
		switch (transDirection){
		case Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE: // English to Chinese
			startIndex = 1;
			offset = -1;
			break;
		case Info.TRANSLATE_FROM_CHINESE_TO_ENGLISH: // Chinese to English
			startIndex = 0;
			offset = 1;
			break;
		case Info.TRANSLATE_FROM_CODE_TO_ENGLISH: // Code to English
			if("0".equals(oriVal)){
				return "";
			}
			startIndex = 2;
			offset = -1;
			break;
		case Info.TRANSLATE_FROM_CODE_TO_CHINESE: // Code to Chinese
			if("0".equals(oriVal)){
				return "";
			}
			startIndex = 2;
			offset = -2;
			break;
		case Info.TRANSLATE_FROM_ENGLISH_TO_CODE:
			startIndex = 1;
			offset = 1;
			break;
		case Info.TRANSLATE_FROM_CHINESE_TO_CODE:
			startIndex = 0;
			offset = 2;
			break;
		//...
		default:
			return "";
		}
		
		for(int i = startIndex; i < places.length; i = i + 3){
			if(oriVal.equalsIgnoreCase(places[i])){
				return places[i + offset];
			}
		}
		
		return "";
	}
	
	public static String translatePlaces(List<String> places, int transDirection, String separater){
		if (places.size() <= 0) {
			return "";
		}
				
		StringBuffer translatedPlaceString = new StringBuffer(20);
		String translatedPalce;  
		boolean firstPlace = true;
		for (String place: places){
			translatedPalce = translatePlaceName(place.trim(), transDirection);
			if (!"".equals(translatedPalce)){
				if(!firstPlace){
					translatedPlaceString.append(separater);
				}else{
					firstPlace = false;
				}
				
				translatedPlaceString.append(translatedPalce);				
			}
		}
		
		return translatedPlaceString.toString();
	}
	
	public static String translatePlaceString(String oriPlaceStr, int transDirection, String delimiter){
		if (null==oriPlaceStr || "".equals(oriPlaceStr.trim())) {
			return "";
		}
		
		String[] places = oriPlaceStr.split(delimiter);
		StringBuffer translatedPlaceString = new StringBuffer(20);
		String translatedPalce;  
		boolean firstPlace = true;
		for (String place: places){
			translatedPalce = translatePlaceName(place.trim(), transDirection);
			if (!"".equals(translatedPalce)){
				if(firstPlace){
					translatedPlaceString.append(delimiter);
					firstPlace = false;
				}
				
				translatedPlaceString.append(translatedPalce);				
			}
		}
		
		return translatedPlaceString.toString();
	}
	
	public static String translateToChineseMonth(int month) {
		String chineseMonth = "";
		
		switch (month) {
		case 1:
			chineseMonth = "一月";
			break;
		case 2:
			chineseMonth = "二月";
			break;
		case 3:
			chineseMonth = "三月";
			break;
		case 4:
			chineseMonth = "四月";
			break;
		case 5:
			chineseMonth = "五月";
			break;
		case 6:
			chineseMonth = "六月";
			break;
		case 7:
			chineseMonth = "七月";
			break;
		case 8:
			chineseMonth = "八月";
			break;
		case 9:
			chineseMonth = "九月";
			break;
		case 10:
			chineseMonth = "十月";
			break;
		case 11:
			chineseMonth = "十一月";
			break;
		case 12:
			chineseMonth = "十二月";
			break;
		}
		
		return chineseMonth;
	}
	
	public static String GetHelpNameById(int helpId){
		String helpName = "";
		switch (helpId) {
		case 1:
			helpName = "支付宝网银支付";
			break;
		case 2:
			helpName = "信用卡电话支付";
			break;
		case 3:
			helpName = "门店支付或银行汇款";
			break;
		case 4:
			helpName = "悠币抵用和发票";
			break;
		case 5:
			helpName = "关于跟团地点以及接送详情";
			break;
		case 6:
			helpName = "团费介绍";
			break;
		case 7:
			helpName = "酒店相关事宜";
			break;
		case 8:
			helpName = "其他旅行小贴士";
			break;
		case 9:
			helpName = "关于团次区别";
			break;
		case 10:
			helpName = "参团相关事宜";
			break;
		case 11:
			helpName = "关于多日旅行中酒店的问题";
			break;
		case 12:
			helpName = "其他旅游小贴士";
			break;
		case 13:
			helpName = "海诺出境游旅游合同范本";
			break;
		case 14:
			helpName = "签证须知";
			break;
		case 15:
			helpName = "海诺旅游公司英国境内旅游合同";
			break;
		case 17:
			helpName = "海诺会员注册";
			break;
		case 18:
			helpName = "海诺会员积分";
			break;
		case 19:
			helpName = "会员种类介绍";
			break;
		case 20:
			helpName = "海诺会刊";
			break;
		}	
		return helpName;
	}
	
	public static String GetHelpDetailsById(int helpId){
		String helpDetails = "";
		switch (helpId) {
		case 9:
			helpDetails = "1.【普通多日游】与【精品小团游】的区别" + "<br>"+

						"普通多日游是海诺常规团，每周有针对不同路线的出团计划，通常情况下，报名人数达到30人可确认出团。我们会于出团日期前3-5天以电话或短信的形式告知是否正常出团。" +
						"与普通多日游相比，精品小团游将为您提供一些优惠和增值服务，每团仅限14人，报满为止。我们会为每位游客配备无线讲解器，由海诺资深导游全程提供中文讲解；此外，我们每日为游客贴心提供小零食或者小点心以及矿泉水或饮料；您更可以享受免费升级为拥有超级大床的房间或三人家庭房。"+"<br><br>"+
						
						"2.【苏格兰豪华4日游】和【苏格兰Skye岛神秘5日游】的区别"+"<br><br>"+
						
						"苏格兰豪华四日游：" + "<br>"+
						"每周四海诺都安排了“苏格兰豪华四日游”出团计划，为您的协调自己的出行时间提供了便利。凡报名人数达到30人的团次即确认出团，我们会于出团日期前三天以电话或短信的形式告知已报名该团是否正常出团。"+"<br>"+
						"豪华四日游的行程如下："+"<br>"+
						"第一天	出发地–湖区（Lake District）- 苏格兰（Scotland）"+"<br>"+
						"第二天	苏格兰高地（Scotland Highland）- 罗蒙湖（Loch Lomond）- 峡谷（Glencoe）- 威廉堡（Fort Williams）- 本尼威斯山口（Ben Nevis）- 尼斯湖（Fort Augustus）"+"<br>"+
						"第三天	爱丁堡（Edinburgh）"+"<br>"+
						"第四天	圣安德鲁斯（St.Andrews）- 苏格兰边境浪漫婚礼小镇（Gretna Green）- 出发地"+"<br>"+
						
						"该团特色：这是一条经典的湖区及苏格兰旅游路线，用诗情画意形容这次旅行绝不过分；鉴于苏格兰首府爱丁堡具有非常高的可看性，我们特别为游客预留一天时间游览；游客可在高尔夫球发源地，英国威廉王子和凯特王妃学习地—圣安德鲁斯免费游览；苏格兰豪华四日游价格实惠。"+"<br><br>"+
						
						"苏格兰Skye岛神秘5日游："+"<br>"+
						"该团增加了苏格兰SKY岛，这里被誉为英国最美丽的岛，也是很多游客心心向往的世外桃源般的景点。为了让游客充分领略SKY岛的美景，海诺特别将酒店预定在该岛上。该团会定期发精品小团，每团仅限14人，报满为止；配备无线讲解器，游客可在旅游全程享受优质、清晰的中文讲解。"+"<br>"+
						"SKY岛神秘5日游的行程："+"<br>"+
						"除第3日增加SKY岛全天游览，他日行程皆同于苏格兰豪华四日游"+"<br><br>"+
						
						"海诺集团是英国最大的华人组团和地接商之一，是英国旅游局和驻华英国大使馆权威认证的旅游目的地ADS（Approved Destination Status）接待商、英国入境旅行商协会（UK Inbound）成员。海诺集团现拥有资深中英文双语导游团队，为您提供最优质的中（英）文讲解和带团服务；旗下巴士服务公司，拥有“海诺”品牌巴士5辆、“利德斯”品牌巴士20辆、16座尼桑巴士、17座奔驰商务巴士以及9座奔驰、大众商务车。";
			break;
		case 10:
			helpDetails = "1. 关于跟团地点以及接送详情" + "<br>"+
						"接送站点" + "<br>"+
						"为方便不同城市的游客跟团，海诺提供以下接、送站点，请游客就近选择站点跟团。"+ "<br><br>"+
						
						"谢菲尔德："+ "<br>"+
						"谢菲尔德火车站，火车站正门对面的巴士停车点"+ "<br>"+
						"The bus stop opposite to SheffieldRailway Station,S1 2BP;"+ "<br><br>"+
						
						"诺丁汉："+ "<br>"+
						"诺丁汉市中心Corner house皇家剧院旁，市中心电影院对面"+ "<br>"+
						"Nottingham Corner House, Bus Stop, City Center near Royal Theatre, NG1 4DB;"+ "<br><br>"+
						
						"诺丁汉大学Jubliee校区，Exchange Building"+ "<br>"+
						"Nottingham --Exchange Building, Jubilee Campus; The University of Nottingham Jubilee Campus, Wollaton Road,Nottingham,NG8 1BB;"+ "<br><br>"+
						
						"诺丁汉大学主校区，湖边主道 East Drive巴士接车点(Stop UN37)；"+ "<br>"+
						"Nottingham --- East Drive (Stop UN37) , University Park, Nottingham University Main Campus，NG7 2RD"+ "<br><br>"+
						
						"拉夫堡："+ "<br>"+
						"拉夫堡大学校正门口，Student Union门口；"+ "<br>"+
						"LoughboroughStudent Union,LE11 3TT;"+ "<br><br>"+
						
						"莱斯特："+ "<br>"+
						"莱斯特大学第二入口"+ "<br>"+
						"Leicester University 2th Entrance, close to Mayor’s Walk，LE1 7RH;"+ "<br><br>"+
						
						"莱斯特 Saint Nicholas Circle, 巴士接车点，假日快捷酒店对面"+ "<br>"+
						"Leicester-Saint Nicholas Circle，the bus stop（12， 13A，18， 70，162）, opposite to Holiday inn express Hotel,LE1 4LF;"+ "<br><br>"+
						
						"伯明翰"+ "<br>"+
						"Ibis酒店前（中国城附近）"+ "<br>"+
						"In front of IBIS Hotel(Close to Chinatown) Ibis Hotel Birmingham Centre‎，21 Ladywell Walk，Birmingham，B5 4ST"+ "<br><br>"+
						
						"考文垂"+ "<br>"+
						"Coventry Sports&Leisure Centre, Fairfax Street; "+ "<br><br>"+
						
						"华威"+ "<br>"+
						"Warwick Main Library, Library Road, University of Warwick "+ "<br><br>"+
						
						"友情提示：如果不能当天到达上述接车点的同学，请前一天到达接车城市。我们会根据团次不同，安排不同的接车地点，具体的出站点我们会在出团前5 天通知。如果遇到极少数情况车辆调度原因，我们无法提供当地接送，我们会支付您火车或长途汽车交通费用前往最近的出团点上下团。详情请咨询："+ "<br>"+
						"info@hinotravel.com"+ "<br><br>"+
						
						"友情提示："+ "<br>"+
						"若您所住城市不再上述接车点中，那么您需要合理安排自己的行程。"+ "<br><br>"+
						
						"1.如果您需要预定交通，我们为您推荐以下链接，您可以直接输入您的乘车地点、时间，通过价格或时间的综合情况进行选择，并在线直接预订。"+ "<br><br>"+
						
						"火车票在线预订网站："+ "<br>"+
						"http://www.thetrainline.com/"+ "<br>"+
						"www.eastcoast.co.uk"  + "<br>"+  
						"http://www.nationalexpress.com/home"+ "<br><br>"+
						
						"2.如许提前一日到达接车城市，最好提前订好酒店。海诺可以为您提供酒店预订服务，您可直接与我们联系，也可以从海诺官网首页-酒店预订网页预定符合自己要求和预算的酒店。"+ "<br>"+
						"http://www.hinotravel.com/partner.htm"+ "<br><br>"+
						
						"3.如果您需要接送机服务，可以联系独立成团部门，我们需要您的接送机日期，人数等详细情况。您可以发邮件至op2@hinotravel.com，或者直接拨打免费电话03339009888转VIP部，咨询接机服务详情。"+ "<br><br>"+
						
						
						"各接送站点返回时间"+ "<br>"+
						"谢菲尔德：约18:00"+ "<br>"+
						"诺丁汉：约 19:00-20:00"+ "<br>"+
						"拉夫堡：约 20:00"+ "<br>"+
						"莱斯特：约20:30"+ "<br>"+
						"伯明翰：约"+ "<br>"+
						"考文垂："+ "<br>"+
						"华威："+ "<br><br>"+
						
						"注：返回时间为预估时间，具体视当天的交通状况而定。如果需要预定返程火车票，建议请留出至少1 小时的换乘时间"+ "<br><br>"+
						
						
						"1.我们会在出团前1天通过短信方式通知所有游客的接站点以及接站方式。如果遇到极少数情况车辆调度原因，我们无法提供当地接送，我们会支付您火车或长途汽车交通费，供您前往最近的出团点上下团，其他相关交通费用自理。"+ "<br><br>"+
						 
						
						"2.通常情况，海诺默认游客所选的接站点为送站点。如果你希望在不同的送站点下车，需在报名时告知我们，以便我们提前安排。比如，您在报名时可以选择在莱斯特上车，在谢菲尔德下车。但是需要提醒您，接送站地点一经出具电子票确认，无法更改。如需临时更换接送站地点，请尽早通知我们，我们会尽量协调，但不保证一定可以安排。我们不承担顾客报名后更换接送站地点而产生的任何争议。"+ "<br><br>"+
						
						"3. 我们的导游会在出团前一日，与您通过短信的方式确认接站地点和接车时间，如您无法找到接站地点，则可提前通过谷歌地图，联系我们的工作人员，或咨询其他朋友的方式提前找到地点。如出团当天有问题，可直接电话联系导游。"+ "<br><br>"+
						
						"4.我们希望每一位游客能秉着尊重他人的原则按照通知的接站时间到达接站地点，如在预定时间无法到达接站地，可尽早与当日带团的导游取得联系，以便我们提前安排解决方式，避免耽误其他游客的时间。"+ "<br><br>"+
						
						
						"2.团费详情"+ "<br><br>"+
						
						"团费价格包含："+ "<br>"+
						"•	49座豪华巴士，专业外籍司机（报名人数少于15人时，采用9-14座车中文导游兼司机）"+ "<br>"+
						"•	全程三星假日酒店或同级酒店双人标准间"+ "<br>"+
						"•	酒店内欧陆式早餐（面包，鸡蛋，果酱，黄油，水果，咖啡/茶，橙汁，牛奶等）"+ "<br>"+
						"•	专业中文导游"+ "<br>"+
						"•	部分城市地图和旅游信息"+ "<br>"+
						"•	单间附加差（保证给单独或单数出团的顾客安排同屋人员, 拒不服从安排或自行要求居住单人间者除外）"+ "<br><br>"+
						
						"团费价格不包含："+ "<br>"+
						"•	小费：每人每天需付2 英镑小费"+ "<br>"+
						"•	个人旅行保险：旅游保险虽然不是参加英国团次的必要条件，提醒您出外旅行务必购买保险，以保证在出现个人意外，例如：生病、丢失财物等情况时，有保险公司保障您的权益。"+ "<br>"+
						"•	景点门票：为尊重您自由旅行的选择，我们的行程当中绝无变相购物或强制游览，请自主选择购买您心仪景点的门票"+ "<br>"+
						"•	中餐晚餐：丰俭由人，我们的导游会向您推荐地道美味的当地餐馆，也会向您介绍实惠高效的快餐店。在旅行过程当中的中餐晚餐消费是您自己的选择午餐、晚餐餐费平均在10 英镑/顿。每日平均消费为40 英镑/人/天（包括每日餐费和景点门票费用或少数纪念品购买费用）。以上为人均参考消费金额，实际花费根据每个人的选择和购买量会有所不同"+ "<br><br>"+
						
						"关于小费"+ "<br>"+
						"每位游客每天需支付2 英镑小费，将由导游统一收取。根据英国的风俗习惯，在享受服务后支付小费是一种惯例。我们的导游和司机的服务需要您的肯定和认可，对于您的理解和配合我们深表感谢。"+ "<br><br>"+
						
						"3.关于付款"+ "<br><br>"+
						
						"当您通过公司或者销售报名海诺某个团次之后，可以通过下列三种方式付款"+ "<br><br>"+
						
						"a. 银行汇款或转账到公司英国账户（推荐）"+ "<br>"+
						"请根据您选择的团次价格、报名人数和选择增值服务的情况，直接通过银行柜台汇款或网上银行转帐到我们的帐户中，Hino Travel英国银行信息（请尽量采用即时到帐的形式付款）"+ "<br>"+
						"公司账户信息:" + "<br>"+
						"银行账户:Hino Travel"+ "<br>"+
						"Sort code: 20-63-25 "+ "<br>"+
						"Account number: 33250482"+ "<br>"+
						"请在填写reference时，务必写清楚您的名字拼音。"+ "<br><br>"+
						
						"b.在线Paypal支付：请点击相应线路“日期预定”页面，直接点击“Buy Now”进行网上支付即可。"+ "<br><br>"+
						
						"（友情提示：如果您的参团条件可以享受特别折扣，建议您使用银行转账或者是到银行柜台付现金的方式付款，因为海诺官网的paypal在线付款只能接受正常成人价格，无法支付特殊折扣价格。如果您使用paypal支付了成人价，但实际应该享受团体价或儿童价等优惠，请联系我们，我们在核实之后，会把多付的款项退还给您）"+ "<br><br>"+
						
						"c.中国付款：我们可以提供中国的人民币收款帐户，方便您在中国支付团款，具体账户信息，请点击这里下载："+ "<br>"+
						"Hino Travel中国银行信息（请尽量采用即时到帐的形式付款）。有关当天汇率及其他付款细节，请您在付款前联系我们进一步确认。"+ "<br><br>"+
						
						"d. 目前公司还不接受信用卡刷卡支付团款的服务。如果您持有信用卡，建议您选择网上在线Paypal支付。或者参考上述其他付款方式。"+ "<br><br>"+
						
						"无论您最终选择了何种付款方式，请在付款后当天务必发送邮件到："+ "<br>"+
						"info@hinotravel.com或sales@hinotravel.com，邮件中请写清下列信息：您所报团次、支付日期、支付金额、留下的reference（团号+名字）和付款的具体方式（例如：我两个人报名1月10日出发苏格兰4日游，今天1月1日通过我的Natwest银行在线转账方式支付了358英镑到HinoTravel Barclays 账户，留下的reference 是Mr XXXXXX）。"+ "<br>"+
						"您也可以通过致电方式（Telephone: 03339009888，01159881662），直接告知海诺办公室工作人员为您确认订单。谢谢！"+ "<br><br>"+
						
						"（友情提示：请以收到我们出具的电子票作为您报名成功的凭证，在未收到电子票之前请不要预定衔接参团的交通或酒店，以免满团产生不必要的麻烦和损失。）"+ "<br><br>"+
						
						"4.关于付款日期和付款金额"+ "<br><br>"+
						
						"当您确认参加海诺某个团次后，我们希望您能尽快付款。特别是一些热门团次，我们以游客付款到账的先后顺序来确认参团位置。请以收到我们出具的电子票作为报名成功的凭证，如果您未收到我们出具的电子票，表示您的预定并未成功。请尽快与我们的工作人员联系。海诺所有的英国团次都需要顾客支付全额团款来确认，不接受任何定金预定。当确认收到您的全额团费后，我们会立即发电子票给您以确认位置。通常情况我们会在出团前1 天通过电子邮件或者手机短信的形式发给您电子票。参团当天，也希望您出示打印的电子票或者手机短信电子票。"+ "<br><br>"+
						
						"5.关于旅游保险"+ "<br><br>"+
			
						"旅游保险不是参加英国境内旅游团必须准备的，但是我们强烈建议您出外旅行请务必购买保险，以保证在出现个人意外，例如：生病、丢失财物等情况时，有保险公司保障您的权益。"+ "<br>"+
						"海诺不可以帮您代买旅游保险，但我们可为您提供一些保险购买的建议。每个人可根据自己的身体状况，出行携带贵重物品的价值，以及出行天数等因素综合考虑购买一定金额的保险。一般的旅游保险在邮局可买到，价格在10-15 镑左右，具体细节可向邮局工作人员咨询确认。"+ "<br><br>"+
						
						"6.关于满团退团"+ "<br><br>"+
						
						"海诺以顾客付款到账的先后顺序来确认参团状态。如果我们确认收到您的团款，但是该团已经满团，我们的工作人员会及时联系您，询您是否愿意免费转到其他团次，或者安排为您全额退款。"+ "<br><br>"+
						
						"7.有关转团、退团"+ "<br><br>"+
						
						"如因个人原因不能参加已经报名的境内团，且在出团前7 天外及时联系我们，海诺可以提供限量的“免费转团”服务。"+ "<br>"+
						"如遇所转出发日期的团次价格更高，需要顾客补齐差价。出团前7 天以内顾客提出更改日期将按照正常条款处理。此项服务的承诺以收到顾客书面Email 通知的日期为准，享受了特殊折扣的顾客不适用于此项保证，按照正常条款处理。"+ "<br>"+
						"如果您取消参团：如果您在付款确认报名之后由于因任何个人原因需要临时退团或转团，请书面通知我们。一般情况下，我们将按照相对应条款扣除以下费用：（个别团次须参考相关团次条款，根据距离出团时间的不同扣除相对应费用）"+ "<br>"+
						"出团前14 天或以上，团款的25%；"+ "<br>"+
						"出团前13-8 天，团款的50%；"+ "<br>"+
						"出团前 7-4 天，团款的85%；"+ "<br>"+
						"出团前 4-0 天，全部团款"+ "<br>"+
						"更多条款细节，您可以参考我们网站上的旅游协议："+ "<br><br>"+
						
						"我们建议您在报名前能够确保自己的出行时间，以免产生不必要的损失。若由于特殊情况在出团前7 天内不得不转团或取消既定团次，我们将按照正常的退团条款来执行。由于此而产生的费用，建议联系您的旅行保险提供商进行理赔。";
			break;
		case 11:
			helpDetails = "海诺秉承优质服务的原则，我们所提供的酒店皆为三星假日酒店或同级酒店双人标准间，标准间内为两张单人床。"+ "<br><br>"+

						"1.如果您与您的同伴希望共同享用一间大床标准间，我们将在收到您的书面确认之后免费为您预定相应的房间。"+ "<br><br>"+
						
						"(友情提示：我们不能绝对保证为您预定到大床标准间，此项服务的提供取决于酒店的空缺情况；此外，我们以收到顾客书面申请的先后顺序来安排，先报名先得。我们不承担因不能兑现大床房间的承诺而产生的任何争议，请您谅解。）"+ "<br><br>"+
						
						"2.如果游客是三人共同报团，您可以选择其中两位住一个房间，单独的一位由海诺为您安排与团内同性成员合住。若三人希望住同一间房，您也可以选择入住三人家庭间（1 个双人床+1 个单人沙发床），您需要在报名时和我们的工作人员和销售提出申请。无论如何住房，所交团费皆为正常优惠价格。由于假日酒店通常没有三个单人床的房间，我们无法为游客提供这样的房间。"+ "<br><br>"+
						
						"(友情提示：三人家庭间的提供取决于酒店的空缺情况，同时我们以收到顾客书面申请的先后顺序来安排，先报名先得。根据房间空缺情况，我们会保证至少两晚家庭房，尽量安排全程家庭房。我们不承担因不能兑现三人家庭间的承诺而产生的任何争议，请您谅解。）"+ "<br><br>"+
						
						"3. 如果您单独报团，我们会尽量安排同性的团员与您合合住。如果我们没有找到合适的团员与您共住一间房，海诺会为您支付单人房价，且保证不收取额外费用（拒不服从安排或自行要求居住单人间者除外）。如果您在报名时提出申请希望保证旅游全程一个人住的话，我们会收取单间差：30 英镑/人/晚。"+ "<br><br>"+
						
						"4.12岁以下儿童报名参加三日及三日以上团次，且和父母一起报团，则可享受五折优惠。但如果团次行程中包括乘坐游轮，如北爱尔兰四日游，布莱顿怀特岛三日游，则该儿童只能享受八折优惠。"+ "<br><br>"+
						
						"5. 通常在三星酒店都能提供网络，一些酒店的会有免费的上网区域，比如酒店大堂；一些酒店会提供可以连接因特网的电脑，如需使用，则须支付一定的费用，通常为2-5 英镑/小时";
			break;
		case 12:
			helpDetails = "1.对行李的要求"+ "<br><br>"+

						"由于我们途经不同地点，车上的位置也比较有限，请尽可能准备轻便的随身行李。请尽量确保您的箱子不超出55cm x 40cm x 20cm，并且不超重15KG。如超过此规格，我们不能保证有足够的空间存放您的行李。出团当天我们的导游会尽量协调，但如果实在无法放置的话，请您精简行李，换小行李箱，并自行寄存大件行李，下团后再取回，海诺不能承担所产生的寄存费用。为使您的旅途更加轻松，我们建议您尽量精简行李到我们的标准。"+ "<br><br>"+
						
						"（友情提示：建议您挑选坚固耐用带有轮子的行李箱包，并在箱包上贴上您的名字。同时携带一个随身的小包，以便存放您的护照、钱包等贵重物品。）"+ "<br><br>"+
						
						"2.旅行中的必备行李"+ "<br><br>"+
						
						"出于环保的原因，英国假日酒店一般只提供洗发露，沐浴露，吹风机，但不提供牙刷，牙膏，拖鞋等。请根据个人需要自备一些个人洗漱或生活用品。"+ "<br><br>"+
						
						"此外，请您将参团需要的文件（比如电子票、手机短信电子票、个人证件、银行卡、学生证等）。需要强调的是学生证，因为在一些可以享受学生折扣的景点，我们需要您的学生证来购买学生票。英国境内团不要求必须带护照，但一些酒店需要提前统计入住顾客的个人信息以及护照号码，我们需要您在报名之时，配合填写护照号码。"+ "<br><br>"+
						"一些必备的衣物、舒适的鞋子。"+ "<br><br>"+

						"3.由于个别景点地处偏远，比如苏格兰高地，会导致团次的车程加长。希望容易晕车或年纪较大的游客提前准备好晕车药品或其他防护措施，避免因晕车影响旅途的心情。"+ "<br><br>"+
						
						"友情提示：一般情况下，第一天和最后一天行程结束之后约有2-3个小时需要接送站。通常，两个景点之间的车程不超过2 个小时，每一个城市游览的停留时间在1-2 个小时，具体游览时间根据当天的交通和游览状况而定。"+ "<br><br>"+
						
						"4. 海诺承诺绝对不会强制游客购物。我们所有的团次都以游览景点为主，当然导游也会尽量给顾客留出自由活动的时间，如果您有需要，可以利用自由活动的时间自行安排购物或拍照。"+ "<br><br>"+
		
						"5.天气情况"+ "<br><br>"+
						
						"英国的天气较为凉爽，一般南部地区相对暖和。越往北部，气温越低。特别是苏格兰、北爱尔兰天气变化极快。为保证旅途的顺利和舒适，我们建议您带一些保暖的衣服，舒适的鞋子和雨伞。我们也会在出团前及时告知当地天气情况，您也可以随时登陆此网站查询最新天气情况http://news.bbc.co.uk/weather/forecast/5/";
			break;
		case 5:
			helpDetails = "接送站点"+ "<br>"+
						"为方便不同城市的游客跟团，海诺提供以下接、送站点，请游客就近选择站点跟团。"+ "<br><br>"+
						
						"谢菲尔德："+ "<br>"+
						"谢菲尔德火车站，火车站正门对面的巴士停车点"+ "<br>"+
						"The bus stop opposite to SheffieldRailway Station,S1 2BP;"+ "<br><br>"+
						
						"诺丁汉："+ "<br>"+
						"诺丁汉市中心Corner house皇家剧院旁，市中心电影院对面"+ "<br>"+
						"Nottingham Corner House, Bus Stop, City Center near Royal Theatre, NG1 4DB;"+ "<br><br>"+
						
						"诺丁汉大学Jubliee校区，Exchange Building"+ "<br>"+
						"Nottingham --Exchange Building, Jubilee Campus; The University of Nottingham Jubilee Campus, Wollaton Road,Nottingham,NG8 1BB;"+ "<br><br>"+
						
						"诺丁汉大学主校区，湖边主道 East Drive巴士接车点(Stop UN37)；"+ "<br>"+
						"Nottingham --- East Drive (Stop UN37) , University Park, Nottingham University Main Campus，NG7 2RD"+ "<br><br>"+
						
						"拉夫堡："+ "<br>"+
						"拉夫堡大学校正门口，Student Union门口；"+ "<br>"+
						"LoughboroughStudent Union,LE11 3TT;"+ "<br><br>"+
						
						"莱斯特："+ "<br>"+
						"莱斯特大学第二入口"+ "<br>"+
						"Leicester University 2th Entrance, close to Mayor’s Walk，LE1 7RH;"+ "<br><br>"+
						
						"莱斯特 Saint Nicholas Circle, 巴士接车点，假日快捷酒店对面"+ "<br>"+
						"Leicester-Saint Nicholas Circle，the bus stop（12， 13A，18， 70，162）, opposite to Holiday inn express Hotel,LE1 4LF;"+ "<br><br>"+
						
						"伯明翰"+ "<br>"+
						"Ibis酒店前（中国城附近）"+ "<br>"+
						"In front of IBIS Hotel(Close to Chinatown) Ibis Hotel Birmingham Centre‎，21 Ladywell Walk，Birmingham，B5 4ST  "+ "<br><br>"+
						"考文垂"+ "<br>"+
						"Coventry Sports&Leisure Centre, Fairfax Street; "+ "<br><br>"+
						
						"华威"+ "<br>"+
						"Warwick Main Library, Library Road, University of Warwick "+ "<br><br>"+
						
						"友情提示：如果不能当天到达上述接车点的同学，请前一天到达接车城市。我们会根据团次不同，安排不同的接车地点，具体的出站点我们会在出团前5 天通知。如果遇到极少数情况车辆调度原因，我们无法提供当地接送，我们会支付您火车或长途汽车交通费用前往最近的出团点上下团。详情请咨询："+ "<br>"+
						"info@hinotravel.com"+ "<br><br>"+
						
						"友情提示："+ "<br>"+
						"若您所住城市不再上述接车点中，那么您需要合理安排自己的行程。"+ "<br><br>"+
						
						"1.如果您需要预定交通，我们为您推荐以下链接，您可以直接输入您的乘车地点、时间，通过价格或时间的综合情况进行选择，并在线直接预订。"+ "<br><br>"+
						
						"火车票在线预订网站："+ "<br>"+
						"http://www.thetrainline.com/"+ "<br>"+
						"www.eastcoast.co.uk    "+ "<br>"+
						"http://www.nationalexpress.com/home"+ "<br><br>"+
						
						"2.如许提前一日到达接车城市，最好提前订好酒店。海诺可以为您提供酒店预订服务，您可直接与我们联系，也可以从海诺官网首页-酒店预订网页预定符合自己要求和预算的酒店。"+ "<br>"+
						"http://www.hinotravel.com/partner.htm"+ "<br><br>"+
						
						"3.如果您需要接送机服务，可以联系独立成团部门，我们需要您的接送机日期，人数等详细情况。您可以发邮件至op2@hinotravel.com，或者直接拨打免费电话03339009888转VIP部，咨询接机服务详情。";


			break;
		case 6:
			helpDetails = "团费价格包含："+ "<br>"+
						"•	全程豪华巴士，专业外籍司机"+ "<br>"+
						"•	全程三星假日酒店或同级酒店双人标准间（标准为二人一房，如需入住单间则另付单价差费用）。我公司有权根据团队报名情况，调整分房情况（包括夫妻分开住宿或者加床）"+ "<br>"+
						"•	酒店内欧陆式早餐（面包，鸡蛋，果酱，黄油，水果，咖啡/茶，橙汁，牛奶等）"+ "<br>"+
						"•	专业中文导游"+ "<br>"+
						"•	部分城市地图和旅游信息"+ "<br><br>"+
	
						"团费价格不包含："+ "<br>"+
						"•	不含返程机票"+ "<br>"+
						"•	客人境内任何私人费用"+ "<br>"+
						"•	每人每天导游小费4欧元"+ "<br>"+
						"•	个人旅行保险：旅游保险是参加境外团次的必要条件，提醒您出外旅行务必购买保险，以保证在出现个人意外，例如：生病、丢失财物等情况时，有保险公司保障您的权益。"+ "<br>"+
						"•	景点门票：为尊重您自由旅行的选择，我们的行程当中绝无变相购物或强制游览，请自主选择购买您心仪景点的门票"+ "<br>"+
						"•	中餐晚餐：丰俭由人，我们的导游会向您推荐地道美味的当地餐馆，也会向您介绍实惠高效的快餐店。在旅行过程当中的中餐晚餐消费是您自己的选择。"+ "<br>"+
						"午餐、晚餐餐费平均在10 欧/顿。每日平均消费为40 欧/人/天（包括每日餐费和景点门票费用或少数纪念品购买费用）。以上为人均参考消费金额，实际花费根据每个人的选择和购买量会有所不同"+ "<br><br>"+
						"友情提示"+ "<br>"+
						"1．关于小费"+ "<br>"+
						"每位游客每天需支付4欧元小费，将由导游统一收取。根据欧洲大陆的风俗习惯，在享受服务后支付小费是一种惯例。我们的导游和司机的服务需要您的肯定和认可，对于您的理解和配合我们深表感谢。"+ "<br>"+
						"2. 关于机票"+ "<br>"+
						"参加需要购买机票的团（如法意瑞梵，希腊，双牙）的游客，您需尽早购买返程机票，以避免最后购买高价机票。"+ "<br>"+
						"3. 关于保险"+ "<br>"+
						"旅行保险是欧洲游所必须的，一方面用于签证，另一方面用于保障自己的财产和生命安全。如果您不幸发生丢失行李或贵重物品，旅行社无法承担任何责任。";
			break;
		case 7:
			helpDetails = "1.通常入住饭店在饭店中都是含早餐的，但房间里不提供拖鞋、开水、洗浴液之类的设施，需要自备。" + "<br><br>"+

						"2.酒店通常不备转换插座或者只有少量的可供借用，请客人最好自备。"+ "<br><br>"+
						
						"3. 在酒店房间内打长途电话，会被加收服务费和附加税，最经济的方式是使用磁卡或投币式电话，电话磁卡可在加油站，小商店，烟店，书报摊等处买到，请客人向导游、领队预先了解清楚.请根据自己情况决定是否购买电话卡。在酒店洗衣、发传真、迷你酒吧消费、收费电视等杂费均请个人结账。请爱护客房中各种设备，烟灰不要到处乱扔、不要随地吐痰，洗浴时拉好浴帘，如果水流出浸泡地毯，酒店会追加客人的损失，请避免不必要的纠纷。"+ "<br><br>"+
						
						"4. 欧洲酒店普遍都比较小，因为欧洲人重视建筑物的年代感，而不是奢华程度，所以和国人的观念 有偏差，西欧酒店的等级与国内的酒店等级通常降为一档。大堂的空间及客房的空间偏小。酒店距离的远近不会影响酒店的品质，旅行社安排行程都会把酒店的地点考虑进去，尽量的让行程舒适合理。欧洲的水质过关。当地习惯饮用凉水，所以酒店不具备热水。请客人谅解！请自备电热壶。"+ "<br><br>"+
						
						"5. 客人所携带的贵重物品及现金应妥善保管或存入酒店保险箱。否则如发生失窃事件旅行社不承担任何责任。";
			break;
		case 8:
			helpDetails = "1.证件。一定要把护照 （英国ID卡）以及学生证ID卡复印两份，将原件都收好，以防旅行途中丢失。如出现丢失情况请及时联系团上导游及领队，向机场或海关出示复印件，补办临时证件需根据当地海关及机场要求而定。"+ "<br><br>"+

						"2.欧元。请先在英国兑换好足够的欧元，有买旅行支票的同学先在英国将支票兑换，请务必兑换一些小面值欧元，如1、2、5、10为佳，以备零用。出团后没有足够的时间让您在当地找银行。一般卡境外刷卡会产生一定的手续费。"+ "<br>"+
						"（友情提示：出访西欧来说比较安全,但均不同程度地存在社会治安问题，请务必注意安全，贵重物品及现金应随身携带。除公务活动时需要西装外，其他游览观光、购物时最好穿休闲装；行李物品在入住饭店、装卸车辆时要加倍小心。下车参观游览时请将现金，相机，首饰，手表等贵重物品必须随身携带，步行时，注意看管好自己的随身物品，乘火车时更应提高警惕；容易出现偷窃的地方有：各国火车站，地铁，以及参观景点等公共场等。 ）"+ "<br><br>"+
						
						"3．转换插头。西欧的电源插头为双圆孔（德标），电压为220伏,但是瑞士和意大利使用的是小型多功能转换插头，英国电压为230伏，电源插头是三向扁状。"+ "<br><br>"+
						
						"4. 为了环保和交通管理的考虑，很多参观景点附近不准停车或只准上下车，所以会造成停车的地点距离观光点之间有些路途，或者上下车时必须动作快一点，请客人予以理解和配合，上下车时均需询问清楚集合时间及游览车泊车的地点。 "+ "<br><br>"+
						
						"5.其他物品。请随身携带自用牙具、拖鞋、雨具、相机、胶卷、电池、太阳镜、护肤品：北欧国家的气候与西欧略有分别，出于位居高纬度，属寒冷地带，但受大西洋暖流影响，为海洋性气候，年温差少，日间清凉，晚上寒冷，建议带足相应衣物；客人根据自身身体状况要携带所需物品（在国外买不到中药）。"+ "<br><br>"+
						
						"6.根据欧洲交通法律法规，司机每天要保证10个小时以上的睡眠，工作不得超过8个小时，故请客人尽量配合导游的安排，如果需要司机导游额外加班，请征求他们的意见，并请支付相应的加班费。 "+ "<br><br>"+
						
						"7. 如需要境外通话，请和电信公司确认是否开通境外通话功能。 "+ "<br><br>"+
						
						"8.公众假期：在很多公众假期时，商店及娱乐场所会缩短营业时间或者停业。公交车辆此时也会减少运营班次。"+ "<br><br>"+
			
						"9.海诺温馨提醒：具体天气请出行前查看，瑞士少女峰常年积雪，请注意天气变化和保暖工作。";
			break;
		case 13:
			helpDetails = "<a href=\""+"resource/Contract.pdf"+"\">海诺国际旅游公司出境游旅游合同 下载</a>";
			break;
		case 14:
			helpDetails = "<a href=\""+"resource/Visa.pdf"+"\">签证须知 下载</a>";
			break;
		case 15:
			helpDetails = "<a href=\""+"../resource/Hino_Customer_Contract.pdf"+"\">海诺旅游公司英国境内旅游合同 下载</a>";
			break;

		case 17:
			helpDetails = "注册会员：您可以通过网络注册或电话注册的方式免费成为海诺旅游的会员，海诺会赠送您500个积分；";
			break;
		case 18:
			helpDetails = "1.1.	什么是积分？"+ "<br>"+
						"积分是海诺旅游网的抵用卷，100积分可以抵用1镑。在旅游产品订单中，您可以使用积分来抵用产品限定的金额。比如预定线路的时候官网显示此线路可用800积分抵用8镑折扣，您有1000积分，则每个出行人可以抵用8镑现金；"+ "<br>"+
						"1.2.	如何得到积分？"+ "<br>"+
						"您每次预定旅游产品并成功出行，都可以获得团次价格的5倍数量的积分，比如您购买了价格100镑的旅游产品，您将获得500个积分。每当您参加海诺旅游团的时候，您可以选择仔细填写问卷意见表，海诺客服会定期为填写完整的客户增加500个积分；"+ "<br>"+
						"1.3.	如何使用积分 ？"+ "<br>"+
						"您可以登录您的会员账户或拨打免费热线0333 9009 888查看您的积分，在您预订团次的时候，可以根据团次最高积分使用量属于100的倍数的积分，在您付款时会自动生成使用积分后的价格。某些特价团此无积分折扣，请您仔细查看。如果您通过销售人员报名，销售人员也会帮您使用积分；";
			break;
		case 19:
			helpDetails = "普通会员	" +"<br>"+
						  "升级条件: 注册默认" +"<br>"+
						  "有效时限: 无                " +"<br>"+
						  "会员特权: 注册赠送500积分;可使用积分抵用现金;可获得预定累计积分" +
						  "可免费获得Dida Mobile SIM卡一张;有资格参与秒杀活动;有资格参与团购活动"+"<br><br>"+
						  "VIP会员	" +"<br>"+
						  "升级条件: 10镑卡费" +"<br>"+
						  "有效时限: 1年                " +"<br>"+
						  "会员特权: 注册赠送500积分;可使用积分抵用现金;可获得预定累计积分;有资格参与团购活动"+
						  "                             可免费获得Dida Mobile SIM卡一张;可以VIP价格购买旅游产品，最高可享受单团40镑折扣"+
						  "                             可获得海诺精美品牌2GB U盘一个;可以95折优惠购买Dida Mobile充值卡;有资格参与秒杀活动"+"<br><br>"+
						  
						  "会员福利：这是海诺旅游为忠实旅客提供的特殊福利，在您的积分出现使用不完的情况，您可以选择使用5000个积分兑换海诺旅游20镑的优惠劵，可用于任何旅游团次，您可以选择价格20镑以内的团次，但不予找零，也不会保留代金卷余额。";
			break;
		case 20:
			helpDetails = "《异国印象》会刊：我们会定期为您的注册邮箱发送电子版旅游会刊，如果您想获得纸质版，请在官网上提交地址，我们会为您邮寄到指定地址；";
			break;
		}	
		return helpDetails;
	}
	
	public static String cutString(String strTobeCut, int length, String appender) {
		
		// Only show defined number of double-byte characters
		if(strTobeCut.length() > length){
			int noOfSingleBytes = 0;
			int noOfDoubleBytes = 0;
			
			char[] cs = strTobeCut.toCharArray();	
			
			for(char c : cs){
				if (c >= 0 && c < 128){
					noOfSingleBytes ++;
				}else{
					noOfDoubleBytes ++;
				}

				// Two and a little bit more single-byte character equals on double-byte character
				if ((noOfDoubleBytes + (noOfSingleBytes + (noOfSingleBytes / 3)) / 2) > length){ 
					break; 
				}
			}

			// Append an Ellipsis mark to indicate description has been cut
			strTobeCut = strTobeCut.substring(0, noOfDoubleBytes + noOfSingleBytes - 3) + appender;
		}
		
		return strTobeCut;		
	}
	
	public static String cutEnglishString(String strTobeCut, int length, String appender) {
		
		// Only show defined number of characters
		if(strTobeCut.length() > length){

			// Append an Ellipsis mark to indicate description has been cut
			strTobeCut = strTobeCut.substring(0, length) + appender;
		}
		
		return strTobeCut;		
	}
	
	// Ken Chen 2013-04-01 TD#130 境外团的支付方式里面，请把支付方式3 paypal去掉，境外团不能用paypal支付
	public static boolean IsOverseaRoute(long routeId) {
		long[] overseaRoute = { 10, 23, 24, 25, 37, 54, 61, 63, 75, 76, 127, 128 };
		
		Arrays.sort(overseaRoute);
		int location = Arrays.binarySearch(overseaRoute, routeId);
		if (location >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String AIRLINE_AREA_BOOKING_PROCEDURE = "BKPROC";
	public static String AIRLINE_AREA_PAY_METHOD = "PAYMTD";
	public static String AIRLINE_AREA_ELECTRIC_TICKET = "ELETKT";
	public static String AIRLINE_AREA_UPDATE_TICKET = "UDTTKT";
	public static String AIRLINE_AREA_FLIGHT_INFO_CONFIRM = "FLTCFM";
	public static String AIRLINE_AREA_NETWORK_CHECKIN = "NETCKI";
	public static String AIRLINE_AREA_BAGGAGE_QUESTION = "BAGQUS";
	public static String AIRLINE_AREA_SPECIAL_TICKET = "SPETKT";
	public static String AIRLINE_AREA_OTHER_QUESTION = "OTHQUS";
	
}
