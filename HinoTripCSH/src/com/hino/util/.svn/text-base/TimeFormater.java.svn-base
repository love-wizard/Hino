package com.hino.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class TimeFormater {
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat sdf4 = new SimpleDateFormat("yy/MM/dd");
	//yy-MM-dd hh:mm:ss
	public static String format1(Calendar c)
	{
		return (c!=null)?sdf1.format(c.getTime()):"NULL DATA";
	}
	
	//yyyy-MM-dd
	public static String format2(Calendar c)
	{
		return (c!=null)?sdf2.format(c.getTime()):"NULL DATA";
	}
	
	public static String format3(Calendar c)
	{
		return (c!=null)?sdf3.format(c.getTime()):"NULL DATA";
	}
	
	public static String format4(Calendar c)
	{
		return (c!=null)?sdf4.format(c.getTime()):"NULL DATA";
	}
	
	//yy-MM-dd hh:mm:ss -> Calendar
	public static Calendar parse1(String s)
	{
		Calendar calendar = null;
		
		try {
			Date date = sdf1.parse(s);
			calendar = Calendar.getInstance(); 
			calendar.setTime(date); 
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return calendar;
	}
	
	//yyyyMMddhhmmss -> Calendar
	public static Calendar parse3(String s)
	{
		Calendar calendar = null;
		
		try {
			Date date = sdf3.parse(s);
			calendar = Calendar.getInstance(); 
			calendar.setTime(date); 
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return calendar;
	}
	
	//yyyy-MM-dd -> Calendar
	public static Calendar parse2(String s)
	{
		Calendar calendar = null;
		
		try {
			Date date = sdf2.parse(s);
			calendar = Calendar.getInstance(); 
			calendar.setTime(date); 
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return calendar;
	}
	
	 /**
     * 根据传入的年份和月份获得这个月的最大天数<br>
     *
     * @param year
     * @param month
	 * @return 
     * @return
     */
	public static int getMaxDaybyYearAndMonth(int year, int month) {
        int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (2 == month && 0 == (year % 4)
                && (0 != (year % 100) || 0 == (year % 400))) {
            days[1] = 29;
        }
        return (days[month - 1]);

    }
}
