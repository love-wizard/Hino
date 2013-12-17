package com.hino.dto;

import com.hino.util.Info;

public class GroupDayDto {

	private int day;
	
	private int routeType;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getRouteType() {
		return routeType;
	}

	public void setRouteType(int routeType) {
		this.routeType = routeType;
	}
	
	public String getStrRouteType() {
		return 	Info.convertRouteTypeToName(routeType);
	}
	
	public String getCssClass() {
		String cssClass = "";
		switch (routeType) {
		case Info.ROUTE_TYPE_CGT:
			cssClass = "rt_cgy"; //"orange";
			break;
		case Info.ROUTE_TYPE_DIY:
			cssClass = "rt_diy"; //"orange";
			break;
		case Info.ROUTE_TYPE_JPXT:
			cssClass = "rt_jpxt"; //"grey";
			break;
		case Info.ROUTE_TYPE_CZTG:
			cssClass = "rt_cztg"; //"green";
			break;
		case Info.ROUTE_TYPE_RMTJ:
			cssClass = "rt_rmtj" ; //"purple";
			break;
		}
		return cssClass;
	}
}
