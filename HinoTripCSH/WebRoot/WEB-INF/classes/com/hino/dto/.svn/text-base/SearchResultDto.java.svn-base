package com.hino.dto;

import java.util.ArrayList;
import java.util.HashMap;

import com.hino.util.Page;
import com.hino.util.SearchType;

public class SearchResultDto {

	/**
	 * Words to be searched
	 */
	private String keyword;
	
	/**
	 * 
	 */
	private int searchType;
	
	/**
	 * 
	 */
	private Page page;
	
	/**
	 * 
	 */
	private HashMap<String, String> orderBys = new HashMap<String, String>();
	
	/**
	 * 
	 */
	private ArrayList<SearchResultItemDto> items = new ArrayList<SearchResultItemDto>();
	
	private SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

	public String getTanslatedKeyword() {
		if (searchType == SearchType.HOT_SEARCH_MONTH) {
			return translateDigitMonth(Integer.valueOf(keyword));
		}
		return keyword;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public HashMap<String, String> getOrderBys() {
		return orderBys;
	}

	public void setOrderBys(HashMap<String, String> orderBys) {
		this.orderBys = orderBys;
	}

	public ArrayList<SearchResultItemDto> getItems() {
		return items;
	}

	public void setItems(ArrayList<SearchResultItemDto> items) {
		this.items = items;
	}
	
	
	public String translateDigitMonth(int month){
		switch(month) {
			case 1:
				return "一月";
			case 2:
				return "二月";
			case 3:
				return "三月";
			case 4:
				return "四月";
			case 5:
				return "五月";
			case 6:
				return "六月";
			case 7:
				return "七月";
			case 8:
				return "八月";
			case 9:
				return "九月";
			case 10:
				return "十月";
			case 11:
				return "十一月";
			case 12:
				return "十二月";
		}
		return "";
	}

	public SearchResultSummayDto getSearchResultSummay() {
		return searchResultSummay;
	}

	public void setSearchResultSummay(SearchResultSummayDto searchResultSummay) {
		this.searchResultSummay = searchResultSummay;
	}

}
