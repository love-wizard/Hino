package com.hino.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PriviledgeParser {
	//权限在model中表示为一个16字符长度string。相对各位代表对应权限是否开放 自右往左 0销售代表 1地区代表... 
	//eg 0000000000000001 为销售代表; 0000000000001001 为销售代表和市场管理
	public final static int SALES_REP = 0;
	public final static int AREA_REP = 1;
	public final static int FINIANCE = 2;
	public final static int MARKETING = 3;
	public final static int HR = 4;
	public final static int GUIDE = 5;
	public final static int SALES_MANAGE = 6;
	public final static int GUIDE_MANAGE = 7;
	public final static int RESOURCE = 8;
	public final static int WEBMASTER = 9;
	
	
	public final static HashMap<Integer, String> pri_map = new HashMap<Integer, String>(); 
	static {
		pri_map.put(SALES_REP, "销售代表");
		pri_map.put(AREA_REP, "地区代表");
		pri_map.put(FINIANCE, "财务管理");
		pri_map.put(MARKETING, "市场管理");
		pri_map.put(HR, "人力资源管理");
		pri_map.put(GUIDE, "导游");
		pri_map.put(SALES_MANAGE, "销售管理");
		pri_map.put(GUIDE_MANAGE, "导游管理");
		pri_map.put(RESOURCE, "旅游资料管理");
		pri_map.put(WEBMASTER, "网站管理");
	}
	
	/**
	 * This method checks if the given priviledge contains the given role.
	 * e.g. priviledge is "0000000000000001", role is "销售代表", then this method returns true
	 * 
	 * @param priviledge the priviledge String such as "0000000000000001"
	 * @param role the role String such as "销售代表"
	 * @return true if SALES_REP bit is 1; false if not
	 */
	public static boolean has_priviledge_by_role(String priviledge, String role) {
		// Find the key of this role
		int keyOfRole = -1;
		Set<Map.Entry<Integer,String>> set = pri_map.entrySet();
		Iterator<Entry<Integer, String>> it=set.iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, String> entry=(Map.Entry<Integer, String>)it.next();
			if(entry.getValue().equals(role)) {
				keyOfRole=entry.getKey();
				break;
			}
		}
		
		// Check if this priviledge String contains this key
		return has_priviledge(priviledge, keyOfRole);
	}
	
	public static boolean has_priviledge(String s, int p){
		if(s!=null)
		{
			if(s.charAt(15-p)=='1')
			{
				return true;
			} else {
				return false;
			}
			
		} else
		{
			return false;
		}
	}
	
	public static List<Integer> list_all_priviledge_index(String s)
	{
		ArrayList<Integer> al = new ArrayList<Integer>();

		//现在只用10位 从6为开始记
		for (int i=15;i>0;i--)
		{
			if(s.charAt(i)=='1')
			{
				al.add(15-i);
			}
		}
		return al;
	}
	
	public static List<String> list_all_priviledge_string(String s)
	{
		ArrayList<String> al = new ArrayList<String>();

		//现在只用10位 从6为开始记
		for (int i=15;i>0;i--)
		{
			if(s.charAt(i)=='1')
			{
				al.add(pri_map.get(15-i));
			}
		}
		return al;
	}
	
	public static String make_pri_string_inline(String s)
	{
		String str = "";

		//现在只用10位 从6为开始记
		for (int i=15;i>0;i--)
		{
			if(s.charAt(i)=='1')
			{
				str = str + "|" + (pri_map.get(15-i));
			}
		}
		return str;
	}
	
	public static String make_pri_string(int pri)
	{
		String str="";
		for (int i=0;i<16;i++)
		{
			if(i==pri){
				str = "1" + str;
			}else{
				str = "0" + str;
			}
		}
		return str;
	}
	
	public static String make_pri_string(List<Integer> list)
	{
		String str="";
		for (int i=0;i<16;i++)
		{
			if(list.contains(i)){
				str = "1" + str;
			}else{
				str = "0" + str;
			}
		}
		return str;
	}
	
	public static String make_pri_hql_like_string(int pri)
	{
		String str="";
		for (int i=0;i<16;i++)
		{
			if(i==pri){
				str = "1" + str;
			}else{
				str = "_" + str;
			}
		}
		return str;
	}
	
	public static String make_hql_like_string(List<Integer> list)
	{
		String str="";
		for (int i=0;i<16;i++)
		{
			if(list.contains(i)){
				str = "1" + str;
			}else{
				str = "_" + str;
			}
		}
		return str;
	}
}
