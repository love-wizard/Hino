package com.hino.dto;

import java.util.HashMap;
import java.util.List;

import com.hino.util.Info;

public class PlaceDto {
	
	private HashMap<Long, List<Long>> groupsOfRoutes;

	private String chineseName;
	private String englishName;
	private String placeCode;
	private int count;
	
	
	public HashMap<Long, List<Long>> getGroupsOfRoutes() {
		return groupsOfRoutes;
	}
	public void setGroupsOfRoutes(HashMap<Long, List<Long>> groupsOfRoutes) {
		this.groupsOfRoutes = groupsOfRoutes;
	}
	
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getPlaceCode() {
		return placeCode;
	}
	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public void genInfo(String englishName)
	{
		this.englishName= englishName;
		this.setChineseName(Info.translatePlaceName(englishName,
				Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE));
		this.setPlaceCode(Info.translatePlaceName(englishName,
				Info.TRANSLATE_FROM_ENGLISH_TO_CODE));
	}
	
}
