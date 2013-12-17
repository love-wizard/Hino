package com.hino.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Validator {
	public final static int UNDEFINED = 1000;
	public final static HashMap<Integer, String> info = new HashMap<Integer, String>();
	
	static {
		info.put(UNDEFINED, "Undefined exception: 1000");
	}
	
	public static String getMsg(int key) {
		if (info.containsKey(key))
		{
			return info.get(key);
		}
		return info.get(UNDEFINED);
	}
	
	public static boolean checkEmail(String mail){  
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";  
		Pattern   p   =   Pattern.compile(regex);  
		Matcher   m   =   p.matcher(mail);  
		return m.find();
	}
	
	public static boolean checkChinese(String s){
		s=new String(s.getBytes());
        String pattern="[\u4e00-\u9fa5]+";  
        Pattern p=Pattern.compile(pattern);  
        Matcher result=p.matcher(s);                  
        return result.find();
	}
	
	
}
