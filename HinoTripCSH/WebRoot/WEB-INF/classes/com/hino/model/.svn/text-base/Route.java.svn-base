package com.hino.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.hino.util.Info;

@Entity
public class Route {
	private long id;
	private String name;
	private List<Site> sitelist;
	
	private String service;
	private String schedule;
	private String hint;
	private String imageUrl;
	private String thumbDesc;
	private String thumbUrl;
	
	private String name_en;
	private String service_en;
	private String schedule_en;
	private String hint_en;
	private String thumbDesc_en;
	private String thumbUrl_en;
	
	// Ken Chen 2012/11/01 页面新需求 begin
	private double scatisfaction;
	private String routeMap;
	private String routeMapThumbl;
	private String imageUrl1;
	private String imageUrl2;
	private String imageUrl3;
	private String imageUrl4;
	private List<Schedule> schedules;
	private String characteristic;	//行程特色
	private String originalRouteName;
	// Ken Chen 2012/11/01 页面新需求 end
	
	private String pickup_info;
	private Route originalRoute;
	
	/*
	 * 
	 */
	private List<Group> groups;
	
	public String getThumbUrl_en() {
		return thumbUrl_en;
	}
	public void setThumbUrl_en(String thumbUrlEn) {
		thumbUrl_en = thumbUrlEn;
	}
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String nameEn) {
		name_en = nameEn;
	}
	public String getService_en() {
		return service_en;
	}
	public void setService_en(String serviceEn) {
		service_en = serviceEn;
	}
	public String getSchedule_en() {
		return schedule_en;
	}
	public void setSchedule_en(String scheduleEn) {
		schedule_en = scheduleEn;
	}
	public String getHint_en() {
		return hint_en;
	}
	public void setHint_en(String hintEn) {
		hint_en = hintEn;
	}
	public String getThumbDesc_en() {
		return thumbDesc_en;
	}
	public void setThumbDesc_en(String thumbDescEn) {
		thumbDesc_en = thumbDescEn;
	}
	public String getHint() {
		return hint;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
		
	public String getImageUrl() {
		if(null == imageUrl) {
			imageUrl = "";
		}
		return imageUrl;
	}
	public String getName() {
		return name;
	}
	
	public String getSchedule() {
		return schedule;
	}
	
	public String getService() {
		return service;
	}
	//cascade = {CascadeType.},
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="route_site",
		joinColumns={@JoinColumn(name="route_id")},
		inverseJoinColumns={@JoinColumn(name="site_id")}
		)
	public List<Site> getSitelist() {
		return sitelist;
	}
	public String getThumbDesc() {
		return thumbDesc;
	}
	
	/**
	 * @author Devon King - 2012/09/30 - TD#13
	 * @return a string with limited number of characters
	 */
	@Transient
	public String getLimitedThumbDesc() {
		String temp = thumbDesc.trim();
		
		// Only show defined number of double-byte characters
		if(temp.length() > Info.MAX_NUMBER_OF_CHARACTERS_TO_SHOW){
			int noOfSingleBytes = 0;
			int noOfDoubleBytes = 0;
			
			char[] cs = temp.toCharArray();	
			
			for(char c : cs){
				if (c >= 0 && c < 128){
					noOfSingleBytes ++;
				}else{
					noOfDoubleBytes ++;
				}

				// Two and a little bit more single-byte character equals on double-byte character
				if ((noOfDoubleBytes + (noOfSingleBytes + (noOfSingleBytes / 3)) / 2) > Info.MAX_NUMBER_OF_CHARACTERS_TO_SHOW){ 
					break; 
				}
			}

			// Append an Ellipsis mark to indicate description has been cut
			temp = temp.substring(0, noOfDoubleBytes + noOfSingleBytes - 3) + "……";
		}
		
		return temp;
	}
	
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public void setService(String service) {
		this.service = service;
	}
	public void setSitelist(List<Site> sitelist) {
		this.sitelist = sitelist;
	}
	public void setThumbDesc(String thumbDesc) {
		this.thumbDesc = thumbDesc;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	
	public String short_name()
	{
		return name.substring(0,4);
	}
	
	public String genScheduleHTML() {
		return schedule.replace("\n","<br />");
	}
	
	public String genServiceHTML() {
		return service.replace("\n","<br />");
	}
	public String genHintHTML() {
		return hint.replace("\n","<br />");
	}
	
	//Ken Chen 2012/12/29 TD#81 团购页面 信息应包含行程简介，行程特色，服务标准，友情提示，去掉线内景点和出团信息
	public String genThumblDescHTML() {
		return this.thumbDesc.replace("\n","<br />");
	}
	
	public String genCharacteristicHTML() {
		return this.characteristic.replace("\n","<br />");
	}
	
	
	public int genRouteType()
	{
		int iReturn = Info.ROUTE_TYPE_CGT;
		String s[] = this.name.split("【|】");
		if(s.length>1)
		{
			String routeType = s[1];
			if( routeType.equalsIgnoreCase("常规团"))
			{
				iReturn = Info.ROUTE_TYPE_CGT;
			}
			else if( routeType.equalsIgnoreCase("精品小团"))
			{
				iReturn = Info.ROUTE_TYPE_JPXT;
			}
			else if( routeType.equalsIgnoreCase("团购"))
			{
				iReturn = Info.ROUTE_TYPE_CZTG;
			}else if( routeType.equalsIgnoreCase("DIY"))
			{
				iReturn = Info.ROUTE_TYPE_DIY;
			}else if( routeType.equalsIgnoreCase("热门推荐"))
			{
				iReturn = Info.ROUTE_TYPE_RMTJ;
			}	
		}
		return iReturn;
		
	}
	
	public String genPureName()
	{
		return this.name.substring(this.name.indexOf("】") + 1);
	}
	
	public String getRouteMap() {
		return routeMap;
	}
	
	@Transient
	public String getPageRouteMap(){
		if(null == routeMap || "".equals(routeMap)) {
			return "default_route_map.jpg";
		} else {
			return routeMap;
		}
	}
	
	public void setRouteMap(String routeMap) {
		this.routeMap = routeMap;
	}
	public double getScatisfaction() {
		return scatisfaction;
	}
	public void setScatisfaction(double scatisfaction) {
		this.scatisfaction = scatisfaction;
	}
	public String getRouteMapThumbl() {
		return routeMapThumbl;
	}
	
	@Transient
	public String getPageRouteMapThumbl() {
		if(null == routeMapThumbl || "".equals(routeMapThumbl)) {
			return "default_route_map.jpg";
		} else {
			return routeMapThumbl;
		}
	}
	
	public void setRouteMapThumbl(String routeMapThumbl) {
		this.routeMapThumbl = routeMapThumbl;
	}
	public String getImageUrl2() {
		if (null == imageUrl2) {
			imageUrl2 = "";
		}
		return imageUrl2;
	}
	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}
	public String getImageUrl1() {
		if (null == imageUrl1) {
			imageUrl1 = "";
		}
		return imageUrl1;
	}
	public void setImageUrl1(String imageUrl1) {
		this.imageUrl1 = imageUrl1;
	}
	public String getImageUrl3() {
		if (null == imageUrl3) {
			imageUrl3 = "";
		}
		return imageUrl3;
	}
	public void setImageUrl3(String imageUrl3) {
		this.imageUrl3 = imageUrl3;
	}
	public String getImageUrl4() {
		if (null == imageUrl4) {
			imageUrl4 = "";
		}
		return imageUrl4;
	}
	public void setImageUrl4(String imageUrl4) {
		this.imageUrl4 = imageUrl4;
	}
	
	//cascade = {CascadeType.},
//	@OneToMany(fetch=FetchType.EAGER)
	@Transient
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	public String getCharacteristic() {
		if(null == characteristic) {
			characteristic = "";
		}
		return characteristic;
	}
	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}
	public String getPickup_info() {
		if(null == pickup_info) {
			pickup_info = "";
		}
		return pickup_info;
	}
	public void setPickup_info(String pickup_info) {
		this.pickup_info = pickup_info;
	}
	
	@ManyToOne()
	@JoinColumn(name = "original_route_id")
	public Route getOriginalRoute() {
		return originalRoute;
	}
	public void setOriginalRoute(Route originalRoute) {
		this.originalRoute = originalRoute;
	}
	
	@Transient
	public String getOriginalRouteName()
	{
		String sOriginalName = new String();
		String s[] = this.name.split("【|】");
		if(s.length>1)
		{
			sOriginalName = s[2];	
		}
		else
		{
			sOriginalName = this.name;
		}
		return sOriginalName;
		
	}
	
//	@OneToMany(fetch=FetchType.EAGER)
//	public List<Group> getGroups() {
//		return groups;
//	}
//	
//	public void setGroups(List<Group> groups) {
//		this.groups = groups;
//	}
	
}
