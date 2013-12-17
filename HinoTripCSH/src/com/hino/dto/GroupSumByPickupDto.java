package com.hino.dto;

import com.hino.util.Info;


public class GroupSumByPickupDto {
	
	private String pickupCity;
	private Long groupCount;
	
	public String getPickupCity() {
		return pickupCity;
	}
	
	public void setPickupCity(String pickupCity) {
		this.pickupCity = pickupCity;
	}
	
	public Long getGroupCount() {
		return (groupCount == null? 0: groupCount);
	}
	
	public void setGroupCount(Long groupCount) {
		this.groupCount = groupCount;
	}
	
	public String genChineseCityName()
	{
		if(pickupCity.equalsIgnoreCase("all")) {
			return "所有出发地";
		} else {
			return Info.translatePlaceName(pickupCity, Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE);
		}
	}
	
	public String genCityCode()
	{
		if(pickupCity.equalsIgnoreCase("all")) {
			return "0";
		} else {
			return Info.translatePlaceName(pickupCity, Info.TRANSLATE_FROM_ENGLISH_TO_CODE);
		}
	}
}
