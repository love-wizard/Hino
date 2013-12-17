package com.hino.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hino.dao.GroupDAO;
import com.hino.dao.RouteDAO;
import com.hino.dto.PlaceDto;
import com.hino.dto.SearchResultDto;
import com.hino.dto.SearchResultItemDto;
import com.hino.dto.SearchResultSummayDto;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.service.SearchResultService;
import com.hino.util.Info;
import com.hino.util.Page;
import com.hino.util.SearchType;
import com.hino.util.TimeFormater;

public class SearchResultServiceImpl implements SearchResultService {

	private static final String PlaceDto = null;
	private GroupDAO groupDao;
	private RouteDAO routeDao;

	public GroupDAO getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDAO groupDao) {
		this.groupDao = groupDao;
	}

	public RouteDAO getRouteDao() {
		return routeDao;
	}

	public void setRouteDao(RouteDAO routeDao) {
		this.routeDao = routeDao;
	}

	@Override
	public SearchResultDto searchInformationByMonth(Integer m,
			Integer startIndex, Integer pageSize, Integer orderByDepartDate,
			Integer orderByPrice, Integer orderByDays, String destination, Integer routeType) {
		
		// SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		Calendar realCalendar = Calendar.getInstance();
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();

		SearchResultDto searchResultDto = new SearchResultDto();
		searchResultDto.setSearchType(SearchType.HOT_SEARCH_MONTH);
		searchResultDto.setKeyword(m.toString());

		ArrayList<SearchResultItemDto> searchResultItems = new ArrayList<SearchResultItemDto>();
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		long routeId;
		ArrayList<Long> processedRouteIds = new ArrayList<Long>();
		
		
		m = m - 1;

		// Cover from current month to December of current year
		if (m == realCalendar.get(Calendar.MONTH)) {
			startCalendar.set(realCalendar.get(Calendar.YEAR), m,
					realCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			endCalendar.set(realCalendar.get(Calendar.YEAR), m,
					realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		//Kevin Zhong - 23/09/2012 - If new month, start from day 1
		} else if (m > realCalendar.get(Calendar.MONTH)){
			startCalendar.set(realCalendar.get(Calendar.YEAR), m, 1, 0, 0, 0);
			int maxDayOfMonth = TimeFormater.getMaxDaybyYearAndMonth(realCalendar.get(Calendar.YEAR), m+1);
			endCalendar.set(realCalendar.get(Calendar.YEAR), m, maxDayOfMonth, 0, 0, 0);
			
		} else { // Cover from January to the month before current month of next
					// year.
			startCalendar.set(realCalendar.get(Calendar.YEAR) + 1, m, 1, 0, 0, 0);
			int maxDayOfMonth = TimeFormater.getMaxDaybyYearAndMonth(realCalendar.get(Calendar.YEAR)+1, m+1);
			
			endCalendar.set(realCalendar.get(Calendar.YEAR) + 1, m, maxDayOfMonth, 0, 0, 0);
		}

		Page page = new Page();
		page.setCurrentPage(startIndex);
		page.setSize(pageSize);

		// Get Search Result Items
		List<Route> routes = routeDao.pagingRouteByGroupDate(startCalendar,
				endCalendar, page, orderByDepartDate, orderByPrice,
				orderByDays, destination, routeType);

		searchResultDto.setPage(page);

		for (Route route : routes) {

			routeId = route.getId();
			SearchResultItemDto searchResultItemDto = new SearchResultItemDto();

			searchResultItemDto.setRoute(route);
			searchResultItemDto.setTitle(route.getName());
			searchResultItemDto.setThumbUrl(route.getThumbUrl());
			
			// Ken Chen 2012/09/26 Add route type info
			searchResultItemDto.setRouteType(route.genRouteType());
			searchResultItemDto.setRouteBg("./images/t_bg_"+searchResultItemDto.getRouteType() +".png");

			List<Group> groups = groupDao.list_group_by_route_and_date(
					(int) routeId, startCalendar, endCalendar, Info.GS_OPENNING, 2,
					orderByPrice, destination);
			searchResultItemDto.setGroups(groups);

			StringBuffer ftDepartDate = new StringBuffer(20);
			StringBuffer ftDepartDateAndSeat = new StringBuffer(40);

			for (Group group : groups) {
				ftDepartDate
						.append(df.format(group.getDepart_date().getTime()));
				ftDepartDate.append("   ");

				ftDepartDateAndSeat.append(df.format(group.getDepart_date()
						.getTime()));
				ftDepartDateAndSeat.append("（");
				
				// Ken Chen 2012/09/26 设置团购座位
				if(group.getRoute().genRouteType()==Info.ROUTE_TYPE_CZTG)
				{
					ftDepartDateAndSeat.append(group.getSeats_groupon() - group.gen_seats_for_go());
				}
				else
				{
					ftDepartDateAndSeat.append(group.getSeats() - group.gen_all_seats_for_go());
				}
				ftDepartDateAndSeat.append("个座位）   ");
				
//				ftDepartDateAndSeat.append(" (已报");
//				ftDepartDateAndSeat.append(group.getSeats_taken()
//						+ group.getSeats_reserved());
//				ftDepartDateAndSeat.append("人/剩余");
//				ftDepartDateAndSeat.append(group.getSeats()
//						- group.getSeats_taken() - group.getSeats_reserved());
//				ftDepartDateAndSeat.append("人）");
			}

			searchResultItemDto.setDepartDate(ftDepartDate.toString());
			searchResultItemDto.setSeats(ftDepartDateAndSeat.toString());

			Group showGroup = null;
			if (groups.size() > 0) {
				// display the first one
				showGroup = groups.get(0);
				searchResultItemDto.setPrice(showGroup.getPrice());
				searchResultItemDto.setVipPrice(showGroup.getVip_price());
				searchResultItemDto.setVoucher(showGroup.getMax_point());
				searchResultItemDto.setGroupPrice(showGroup.getGroup_price());
				searchResultItemDto.setGroupVipPrice(showGroup.getGroup_vip_price());
				
				//ken chen 2012/09/18 仅显示出发站城市
				List<String> list = new ArrayList<String>();
				list.add(destination);
				searchResultItemDto.setDepartPlace(Info.translatePlaces(
						list,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE, "， "));
			
				// Put into list 
				//Ken Chen 2012/09/20 仅添加有团的路线		
				searchResultItems.add(searchResultItemDto);
				
				
			}
		}

		searchResultDto.setItems(searchResultItems);

		// Get Summay Info
		searchResultSummay = getSummay(startCalendar, endCalendar, m, "",
				destination);
		searchResultDto.setSearchResultSummay(searchResultSummay);

		// System.out.println(psGroup.getStartIndex());

		return searchResultDto;
	}

	private SearchResultSummayDto getSummay(Calendar startCalendar,
			Calendar endCalendar, Integer month, String departPlace,
			String destination) {
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		LinkedHashMap lkmRouteTypeSummary = new LinkedHashMap();
		initRouteTypeSummary(lkmRouteTypeSummary);
		LinkedHashMap lmDepartPlaceSummary = new LinkedHashMap();
		initDepartPlaceSummary(lmDepartPlaceSummary);
		
		HashMap<String, PlaceDto> hmDepartPlace = new HashMap<String, PlaceDto>();
		HashMap<String, String> hmDestnation = new HashMap<String, String>();
		
		HashMap<String, String> hmTravelTime = new HashMap<String, String>();
		LinkedHashMap hmPriceRange = new LinkedHashMap();
		long price1 = 0, price2 = 0, price3 = 0, price4 = 0, price5 = 0, price6 = 0, price7 = 0;

		Calendar realCalendar = Calendar.getInstance();

		long routeId;
		
		Page page = new Page();
		page.setCurrentPage(0);
		page.setSize(1000);

		// Get Search Result Items
		List<Group> groups = groupDao.pagingGroupByDate(startCalendar,
				endCalendar, page, "");
		boolean placeOfGroupCounted = false;

		for (Group group : groups) {
			routeId = group.getRoute().getId();

			ArrayList<String> pickupCity = new ArrayList<String>();
			//pickupCity = (ArrayList<String>) group.genPickupCity();
			pickupCity = (ArrayList<String>) group.genFuzzyPickupCity();
			
			// Get Pickup City Summary
			for (Iterator<String> it = pickupCity.iterator(); it.hasNext();) {
				placeOfGroupCounted = false;
				String placeName = ((String) it.next()).toLowerCase();

				// Only show defined place
				if (!"".equals(Info.translatePlaceName(placeName,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE))) {
					if (hmDepartPlace.containsKey(placeName)) {
						PlaceDto place = hmDepartPlace.get(placeName);
						HashMap<Long, List<Long>> groupsOfRoutes = place
								.getGroupsOfRoutes();
						List<Long> groupIds = groupsOfRoutes.get(routeId);
						if (null == groupIds) {
							groupIds = new ArrayList<Long>();
							groupIds.add((long) group.getId());
							groupsOfRoutes.put(routeId, groupIds);
							place.setCount(place.getCount() + 1);
						} else {
							if (groupIds.size() < 1) {
								for (long groupId : groupIds) {
									if (groupId == group.getId()) {
										System.out.println("Duplicate place - "
												+ placeName + ", group id - "
												+ group.getId());
										placeOfGroupCounted = true;
										break;
									}
								}
								if (!placeOfGroupCounted) {
									groupIds.add((long) group.getId());
									place.setCount(place.getCount() + 1);
								}
							}
						}
						
						lmDepartPlaceSummary.put(placeName, place);
						

					} else {
						PlaceDto place = new PlaceDto();
						ArrayList<Long> groupIds = new ArrayList<Long>();
						HashMap<Long, List<Long>> groupsOfRoutes = new HashMap<Long, List<Long>>();
						groupsOfRoutes.put(routeId, groupIds);
						groupIds.add((long) group.getId());

						place.setGroupsOfRoutes(groupsOfRoutes);
						place.setEnglishName(placeName);
						place.setChineseName(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE));
						place.setPlaceCode(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CODE));
						place.setCount(1);

						hmDepartPlace.put(placeName, place);
						lmDepartPlaceSummary.put(placeName, place);
					}
				}

			}
		}
		
		ArrayList<Long> processedRouteIds = new ArrayList<Long>();
		Page pagePrice = new Page();
		pagePrice.setCurrentPage(0);
		pagePrice.setSize(1000);
		// Get Search Result Items
		List<Group> groupPrices = groupDao.pagingGroupByDate(startCalendar,
				endCalendar, pagePrice, destination);

		for (Group group : groupPrices) {
			routeId = group.getRoute().getId();
			if (!processedRouteIds.contains(routeId)) {
				// Get Price Summary
				processedRouteIds.add(routeId);
				double price = group.getPrice();

				if (price <= 20) {
					price1 = price1 + 1;
				} else if (20 < price && price < 100) {
					price2 = price2 + 1;
				} else if (100 < price && price < 200) {
					price3 = price3 + 1;
				} else if (200 < price && price < 300) {
					price4 = price4 + 1;
				} else if (300 < price && price < 400) {
					price5 = price5 + 1;
				} else if (400 < price && price < 500) {
					price6 = price6 + 1;
				} else if (price > 500) {
					price7 = price7 + 1;
				}
				
				if(lkmRouteTypeSummary.containsKey(group.getRoute().genRouteType()))
				{
					int i = (Integer) lkmRouteTypeSummary.get(group.getRoute().genRouteType());
					i = i +1;
					lkmRouteTypeSummary.put(group.getRoute().genRouteType(), i);
				} else {
					// Counting all non-defined groups as a normal group
					int i = (Integer) lkmRouteTypeSummary.get(Info.ROUTE_TYPE_CGT);
					i = i +1;
					lkmRouteTypeSummary.put(Info.ROUTE_TYPE_CGT, i);
				}
			}
		}

		Iterator iter = hmDepartPlace.entrySet().iterator();
		Iterator iter2;
		System.out.println("==============================");
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();

			System.out.println("Place Name =" + entry.getKey());
			iter2 = ((PlaceDto) entry.getValue()).getGroupsOfRoutes()
					.entrySet().iterator();
			while (iter2.hasNext()) {
				Map.Entry entry2 = (Map.Entry) iter2.next();
				System.out.println("Ruote Id =" + entry2.getKey());
				System.out.println("Group Counter ="
						+ ((List<Long>) entry2.getValue()).size());
			}
			System.out.println(" ------------------- ");
		}
		System.out.println("");
		System.out.println("==============================");

		hmPriceRange.put(Info.PRICE_RANGE_1_20, String.valueOf(price1));
		hmPriceRange.put(Info.PRICE_RANGE_20_100, String.valueOf(price2));
		hmPriceRange.put(Info.PRICE_RANGE_101_200, String.valueOf(price3));
		hmPriceRange.put(Info.PRICE_RANGE_201_300, String.valueOf(price4));
		hmPriceRange.put(Info.PRICE_RANGE_301_400, String.valueOf(price5));
		hmPriceRange.put(Info.PRICE_RANGE_401_500, String.valueOf(price6));
		hmPriceRange.put(Info.PRICE_RANGE_500, String.valueOf(price7));

		searchResultSummay.setDepartPlace(lmDepartPlaceSummary);
		searchResultSummay.setLkDepartPlace(lmDepartPlaceSummary);	
		//searchResultSummay.setDepartPlace(hmDepartPlace);
		searchResultSummay.setPriceRange(hmPriceRange);
		searchResultSummay.setRouteType(lkmRouteTypeSummary);

		return searchResultSummay;

	}
	
	private void ArrayList(String placedto2) {
		// TODO Auto-generated method stub
		
	}

	public void initRouteTypeSummary(LinkedHashMap lkm)
	{
		lkm.put(Info.ROUTE_TYPE_CGT, 0);
		lkm.put(Info.ROUTE_TYPE_RMTJ, 0);
		lkm.put(Info.ROUTE_TYPE_CZTG, 0);
		lkm.put(Info.ROUTE_TYPE_JPXT, 0);
		lkm.put(Info.ROUTE_TYPE_DIY, 0);
		
	}
	public void initDepartPlaceSummary(LinkedHashMap lm)
	{
		PlaceDto pDto = new PlaceDto();
		String englishName = "";
		
		englishName = "nottingham";
		pDto.genInfo(englishName);
		lm.put((Object)englishName,  pDto);
		
		PlaceDto pDto1 = new PlaceDto();
		englishName = "manchester";
		pDto1.genInfo(englishName);
		lm.put((Object)englishName,  pDto1);
		
		PlaceDto pDto2 = new PlaceDto();
		englishName = "birmingham";
		pDto2.genInfo(englishName);
		lm.put((Object)englishName,  pDto2);
		
		PlaceDto pDto3 = new PlaceDto();
		englishName = "warwick";
		pDto3.genInfo(englishName);
		lm.put((Object)englishName,  pDto3);
		
		PlaceDto pDto4 = new PlaceDto();
		englishName = "coventry";
		pDto4.genInfo(englishName);
		lm.put((Object)englishName,  pDto4);
		
		PlaceDto pDto5 = new PlaceDto();
		englishName = "leicester";
		pDto5.genInfo(englishName);
		lm.put((Object)englishName,  pDto5);
		
		PlaceDto pDto6 = new PlaceDto();
		englishName = "london";
		pDto6.genInfo(englishName);
		lm.put((Object)englishName,  pDto6);
		
		PlaceDto pDto7 = new PlaceDto();
		englishName = "loughborough";
		pDto7.genInfo(englishName);
		lm.put((Object)englishName,  pDto7);
		
		PlaceDto pDto8 = new PlaceDto();
		englishName = "sheffield";
		pDto8.genInfo(englishName);
		lm.put((Object)englishName,  pDto8);
		
	}
	

	@Override
	public SearchResultDto searchByDestination(String destination, Integer startIndex, int pageSize,
			Integer orderByDepartDate, Integer orderByPrice, Integer orderByDays, String pickupCity, Integer routeType, Integer month) {
		
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		
		Calendar realCalendar = Calendar.getInstance();
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		
		if (month == 0) {
			// Set to a relevant high date
			endCalendar.set(2100, 12, 30);
			
		} else {
		
			month = month - 1;
			
			// Cover from current month to December of current year
			if (month == realCalendar.get(Calendar.MONTH)) {
				startCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
				
			} else if (month > realCalendar.get(Calendar.MONTH)){
				startCalendar.set(realCalendar.get(Calendar.YEAR), month, 1, 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
				
			// Cover from January to the month before current month of next year
			} else { 
				startCalendar.set(realCalendar.get(Calendar.YEAR) + 1, month, 1, 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR) + 1, month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
			}
		}

		SearchResultDto searchResultDto = new SearchResultDto();
		searchResultDto.setSearchType(SearchType.HOT_SEARCH_DESTINATION);
		searchResultDto.setKeyword(destination);

		ArrayList<SearchResultItemDto> searchResultItems = new ArrayList<SearchResultItemDto>();
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		long routeId;

		Page page = new Page();
		page.setCurrentPage(startIndex);
		page.setSize(pageSize);

		// Get Search Result Items
		List<Route> routes = routeDao.pagingRouteByDestination(destination, startCalendar, endCalendar,
				page, orderByDepartDate, orderByPrice,
				orderByDays, pickupCity, routeType);

		searchResultDto.setPage(page);

		for (Route route : routes) {

			routeId = route.getId();
			SearchResultItemDto searchResultItemDto = new SearchResultItemDto();

			searchResultItemDto.setRoute(route);
			searchResultItemDto.setTitle(route.getName());
			searchResultItemDto.setThumbUrl(route.getThumbUrl());
			
			// Ken Chen 2012/09/26 Add route type info
			searchResultItemDto.setRouteType(route.genRouteType());
			searchResultItemDto.setRouteBg("./images/t_bg_"+searchResultItemDto.getRouteType() +".png");

			List<Group> groups = groupDao.list_group_by_route_and_date(
					(int) routeId, startCalendar, endCalendar, Info.GS_OPENNING, 2,
					orderByPrice, pickupCity);
			searchResultItemDto.setGroups(groups);

			StringBuffer ftDepartDate = new StringBuffer(20);
			StringBuffer ftDepartDateAndSeat = new StringBuffer(40);

			for (Group group : groups) {
				ftDepartDate
						.append(df.format(group.getDepart_date().getTime()));
				ftDepartDate.append("   ");

				ftDepartDateAndSeat.append(df.format(group.getDepart_date()
						.getTime()));
				ftDepartDateAndSeat.append("（");
				
				// Ken Chen 2012/09/26 设置团购座位
				if(group.getRoute().genRouteType()==Info.ROUTE_TYPE_CZTG)
				{
					ftDepartDateAndSeat.append(group.getSeats_groupon() - group.gen_seats_for_go());
				}
				else
				{
					ftDepartDateAndSeat.append(group.getSeats() - group.gen_all_seats_for_go());
				}
				ftDepartDateAndSeat.append("个座位）   ");
			}

			searchResultItemDto.setDepartDate(ftDepartDate.toString());
			searchResultItemDto.setSeats(ftDepartDateAndSeat.toString());

			Group showGroup = null;
			if (groups.size() > 0) {
				// display the first one
				showGroup = groups.get(0);
				searchResultItemDto.setPrice(showGroup.getPrice());
				searchResultItemDto.setVipPrice(showGroup.getVip_price());
				searchResultItemDto.setVoucher(showGroup.getMax_point());
				searchResultItemDto.setGroupPrice(showGroup.getGroup_price());
				searchResultItemDto.setGroupVipPrice(showGroup.getGroup_vip_price());
				
				//ken chen 2012/09/18 仅显示出发站城市
				List<String> list = new ArrayList<String>();
				list.add(pickupCity);
				searchResultItemDto.setDepartPlace(Info.translatePlaces(
						list,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE, "， "));
			
				// Put into list 
				//Ken Chen 2012/09/20 仅添加有团的路线		
				searchResultItems.add(searchResultItemDto);
			}
		}

		searchResultDto.setItems(searchResultItems);

		// Get Summary Info
		searchResultSummay = getSummaryForDestSearch(startCalendar, endCalendar, 0, pickupCity,
				destination);
		searchResultDto.setSearchResultSummay(searchResultSummay);

		return searchResultDto;
	}

	private SearchResultSummayDto getSummaryForDestSearch(Calendar startCalendar,
			Calendar endCalendar, Integer month, String departPlace,
			String destination) {
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		LinkedHashMap lkmRouteTypeSummary = new LinkedHashMap();
		initRouteTypeSummary(lkmRouteTypeSummary);
		
		HashMap<String, PlaceDto> hmDepartPlace = new HashMap<String, PlaceDto>();
		LinkedHashMap lmDepartPlaceSummary = new LinkedHashMap();
		initDepartPlaceSummary(lmDepartPlaceSummary);
		LinkedHashMap hmPriceRange = new LinkedHashMap();
		long price1 = 0, price2 = 0, price3 = 0, price4 = 0, price5 = 0, price6 = 0, price7 = 0;

		long routeId;
		
		Page page = new Page();
		page.setCurrentPage(0);
		page.setSize(1000);

		// Get Search Result Items
		List<Group> groups = groupDao.pagingGroupByDateAndDest(startCalendar,
				endCalendar, page, "", destination);
		boolean placeOfGroupCounted = false;

		for (Group group : groups) {
			routeId = group.getRoute().getId();

			ArrayList<String> pickupCity = new ArrayList<String>();
			pickupCity = (ArrayList<String>) group.genFuzzyPickupCity();
			
			// Get Pickup City Summary
			for (Iterator<String> it = pickupCity.iterator(); it.hasNext();) {
				placeOfGroupCounted = false;
				String placeName = ((String) it.next()).toLowerCase();

				// Only show defined place
				if (!"".equals(Info.translatePlaceName(placeName,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE))) {
					if (hmDepartPlace.containsKey(placeName)) {
						PlaceDto place = hmDepartPlace.get(placeName);
						HashMap<Long, List<Long>> groupsOfRoutes = place
								.getGroupsOfRoutes();
						List<Long> groupIds = groupsOfRoutes.get(routeId);
						if (null == groupIds) {
							groupIds = new ArrayList<Long>();
							groupIds.add((long) group.getId());
							groupsOfRoutes.put(routeId, groupIds);
							place.setCount(place.getCount() + 1);
						} else {
							if (groupIds.size() < 1) {
								for (long groupId : groupIds) {
									if (groupId == group.getId()) {
										System.out.println("Duplicate place - "
												+ placeName + ", group id - "
												+ group.getId());
										placeOfGroupCounted = true;
										break;
									}
								}
								if (!placeOfGroupCounted) {
									groupIds.add((long) group.getId());
									place.setCount(place.getCount() + 1);
								}
							}
						}
						
						lmDepartPlaceSummary.put(placeName, place);

					} else {
						PlaceDto place = new PlaceDto();
						ArrayList<Long> groupIds = new ArrayList<Long>();
						HashMap<Long, List<Long>> groupsOfRoutes = new HashMap<Long, List<Long>>();
						groupsOfRoutes.put(routeId, groupIds);
						groupIds.add((long) group.getId());

						place.setGroupsOfRoutes(groupsOfRoutes);
						place.setEnglishName(placeName);
						place.setChineseName(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE));
						place.setPlaceCode(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CODE));
						place.setCount(1);

						hmDepartPlace.put(placeName, place);
						
						lmDepartPlaceSummary.put(placeName, place);

					}
				}

			}
		}
		
		ArrayList<Long> processedRouteIds = new ArrayList<Long>();
		Page pagePrice = new Page();
		pagePrice.setCurrentPage(0);
		pagePrice.setSize(1000);
		// Get Search Result Items
		List<Group> groupPrices = groupDao.pagingGroupByDateAndDest(startCalendar,
				endCalendar, pagePrice, departPlace, destination);

		for (Group group : groupPrices) {
			routeId = group.getRoute().getId();
			if (!processedRouteIds.contains(routeId)) {
				// Get Price Summary
				processedRouteIds.add(routeId);
				double price = group.getPrice();

				if (price <= 20) {
					price1 = price1 + 1;
				} else if (20 < price && price < 100) {
					price2 = price2 + 1;
				} else if (100 < price && price < 200) {
					price3 = price3 + 1;
				} else if (200 < price && price < 300) {
					price4 = price4 + 1;
				} else if (300 < price && price < 400) {
					price5 = price5 + 1;
				} else if (400 < price && price < 500) {
					price6 = price6 + 1;
				} else if (price > 500) {
					price7 = price7 + 1;
				}
				
				if(lkmRouteTypeSummary.containsKey(group.getRoute().genRouteType()))
				{
					int i = (Integer) lkmRouteTypeSummary.get(group.getRoute().genRouteType());
					i = i +1;
					lkmRouteTypeSummary.put(group.getRoute().genRouteType(), i);
				} else {
					// Counting all non-defined groups as a normal group
					int i = (Integer) lkmRouteTypeSummary.get(Info.ROUTE_TYPE_CGT);
					i = i +1;
					lkmRouteTypeSummary.put(Info.ROUTE_TYPE_CGT, i);
				}
			}
		}

		hmPriceRange.put(Info.PRICE_RANGE_1_20, String.valueOf(price1));
		hmPriceRange.put(Info.PRICE_RANGE_20_100, String.valueOf(price2));
		hmPriceRange.put(Info.PRICE_RANGE_101_200, String.valueOf(price3));
		hmPriceRange.put(Info.PRICE_RANGE_201_300, String.valueOf(price4));
		hmPriceRange.put(Info.PRICE_RANGE_301_400, String.valueOf(price5));
		hmPriceRange.put(Info.PRICE_RANGE_401_500, String.valueOf(price6));
		hmPriceRange.put(Info.PRICE_RANGE_500, String.valueOf(price7));

		searchResultSummay.setDepartPlace(hmDepartPlace);
		searchResultSummay.setLkDepartPlace(lmDepartPlaceSummary);
		searchResultSummay.setPriceRange(hmPriceRange);
		searchResultSummay.setRouteType(lkmRouteTypeSummary);

		return searchResultSummay;
	}

	@Override
	public SearchResultDto searchByPrice(String destination, Integer startIndex, int pageSize,
			Integer orderByDepartDate, Integer orderByPrice, Integer orderByDays, String pickupCity, Integer routeType, Integer month, double lprice, double uprice) {
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		
		Calendar realCalendar = Calendar.getInstance();
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		
		if (month == 0) {
			// Set to a relevant high date
			endCalendar.set(2100, 12, 30);
			
		} else {
		
			month = month - 1;
			
			// Cover from current month to December of current year
			if (month == realCalendar.get(Calendar.MONTH)) {
				startCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
				
			} else if (month > realCalendar.get(Calendar.MONTH)){
				startCalendar.set(realCalendar.get(Calendar.YEAR), month, 1, 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
				
			// Cover from January to the month before current month of next year
			} else { 
				startCalendar.set(realCalendar.get(Calendar.YEAR) + 1, month, 1, 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR) + 1, month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
			}
		}

		SearchResultDto searchResultDto = new SearchResultDto();
		searchResultDto.setSearchType(SearchType.NAVIGATION_SEARCH_PRICE);
		searchResultDto.setKeyword(destination);

		ArrayList<SearchResultItemDto> searchResultItems = new ArrayList<SearchResultItemDto>();
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		long routeId;

		Page page = new Page();
		page.setCurrentPage(startIndex);
		page.setSize(pageSize);

		// Get Search Result Items
		List<Route> routes = routeDao.pagingRouteByPrice(destination, startCalendar, endCalendar,
				page, orderByDepartDate, orderByPrice,
				orderByDays, pickupCity, routeType, lprice, uprice);

		searchResultDto.setPage(page);

		for (Route route : routes) {

			routeId = route.getId();
			SearchResultItemDto searchResultItemDto = new SearchResultItemDto();

			searchResultItemDto.setRoute(route);
			searchResultItemDto.setTitle(route.getName());
			searchResultItemDto.setThumbUrl(route.getThumbUrl());
			
			// Ken Chen 2012/09/26 Add route type info
			searchResultItemDto.setRouteType(route.genRouteType());
			searchResultItemDto.setRouteBg("./images/t_bg_"+searchResultItemDto.getRouteType() +".png");

			List<Group> groups = groupDao.list_group_by_route_and_date_and_price(
					(int) routeId, startCalendar, endCalendar, Info.GS_OPENNING, 2,
					orderByPrice, pickupCity, lprice, uprice);
			searchResultItemDto.setGroups(groups);

			StringBuffer ftDepartDate = new StringBuffer(20);
			StringBuffer ftDepartDateAndSeat = new StringBuffer(40);

			for (Group group : groups) {
				ftDepartDate
						.append(df.format(group.getDepart_date().getTime()));
				ftDepartDate.append("   ");

				ftDepartDateAndSeat.append(df.format(group.getDepart_date()
						.getTime()));
				ftDepartDateAndSeat.append("（");
				
				// Ken Chen 2012/09/26 设置团购座位
				if(group.getRoute().genRouteType()==Info.ROUTE_TYPE_CZTG)
				{
					ftDepartDateAndSeat.append(group.getSeats_groupon() - group.gen_seats_for_go());
				}
				else
				{
					ftDepartDateAndSeat.append(group.getSeats() - group.gen_all_seats_for_go());
				}
				ftDepartDateAndSeat.append("个座位）   ");
			}

			searchResultItemDto.setDepartDate(ftDepartDate.toString());
			searchResultItemDto.setSeats(ftDepartDateAndSeat.toString());

			Group showGroup = null;
			if (groups.size() > 0) {
				// display the first one
				showGroup = groups.get(0);
				searchResultItemDto.setPrice(showGroup.getPrice());
				searchResultItemDto.setVipPrice(showGroup.getVip_price());
				searchResultItemDto.setVoucher(showGroup.getMax_point());
				searchResultItemDto.setGroupPrice(showGroup.getGroup_price());
				searchResultItemDto.setGroupVipPrice(showGroup.getGroup_vip_price());
				
				//ken chen 2012/09/18 仅显示出发站城市
				List<String> list = new ArrayList<String>();
				list.add(pickupCity);
				searchResultItemDto.setDepartPlace(Info.translatePlaces(
						list,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE, "， "));
			
				// Put into list 
				//Ken Chen 2012/09/20 仅添加有团的路线		
				searchResultItems.add(searchResultItemDto);
			}
		}

		searchResultDto.setItems(searchResultItems);

		// Get Summary Info
		searchResultSummay = getSummaryForPriceSearch(startCalendar, endCalendar, 0, pickupCity,
				destination, lprice, uprice);
		searchResultDto.setSearchResultSummay(searchResultSummay);

		return searchResultDto;
	}
	
	private SearchResultSummayDto getSummaryForPriceSearch(Calendar startCalendar,
			Calendar endCalendar, Integer month, String departPlace,
			String destination, double lprice, double uprice) {
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		LinkedHashMap lkmRouteTypeSummary = new LinkedHashMap();
		initRouteTypeSummary(lkmRouteTypeSummary);
		
		HashMap<String, PlaceDto> hmDepartPlace = new HashMap<String, PlaceDto>();
		LinkedHashMap lmDepartPlaceSummary = new LinkedHashMap();
		initDepartPlaceSummary(lmDepartPlaceSummary);
		LinkedHashMap hmPriceRange = new LinkedHashMap();
		long price1 = 0, price2 = 0, price3 = 0, price4 = 0, price5 = 0, price6 = 0, price7 = 0;

		long routeId;
		
		Page page = new Page();
		page.setCurrentPage(0);
		page.setSize(1000);

		// Get Search Result Items
		List<Group> groups = groupDao.pagingGroupByDateAndDestAndPrice(startCalendar,
				endCalendar, page, "", destination, lprice, uprice);
		boolean placeOfGroupCounted = false;

		for (Group group : groups) {
			routeId = group.getRoute().getId();

			ArrayList<String> pickupCity = new ArrayList<String>();
			pickupCity = (ArrayList<String>) group.genFuzzyPickupCity();
			
			// Get Pickup City Summary
			for (Iterator<String> it = pickupCity.iterator(); it.hasNext();) {
				placeOfGroupCounted = false;
				String placeName = ((String) it.next()).toLowerCase();

				// Only show defined place
				if (!"".equals(Info.translatePlaceName(placeName,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE))) {
					if (hmDepartPlace.containsKey(placeName)) {
						PlaceDto place = hmDepartPlace.get(placeName);
						HashMap<Long, List<Long>> groupsOfRoutes = place
								.getGroupsOfRoutes();
						List<Long> groupIds = groupsOfRoutes.get(routeId);
						if (null == groupIds) {
							groupIds = new ArrayList<Long>();
							groupIds.add((long) group.getId());
							groupsOfRoutes.put(routeId, groupIds);
							place.setCount(place.getCount() + 1);
						} else {
							if (groupIds.size() < 1) {
								for (long groupId : groupIds) {
									if (groupId == group.getId()) {
										System.out.println("Duplicate place - "
												+ placeName + ", group id - "
												+ group.getId());
										placeOfGroupCounted = true;
										break;
									}
								}
								if (!placeOfGroupCounted) {
									groupIds.add((long) group.getId());
									place.setCount(place.getCount() + 1);
								}
							}
						}
						
						lmDepartPlaceSummary.put(placeName, place);

					} else {
						PlaceDto place = new PlaceDto();
						ArrayList<Long> groupIds = new ArrayList<Long>();
						HashMap<Long, List<Long>> groupsOfRoutes = new HashMap<Long, List<Long>>();
						groupsOfRoutes.put(routeId, groupIds);
						groupIds.add((long) group.getId());

						place.setGroupsOfRoutes(groupsOfRoutes);
						place.setEnglishName(placeName);
						place.setChineseName(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE));
						place.setPlaceCode(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CODE));
						place.setCount(1);

						hmDepartPlace.put(placeName, place);
						
						lmDepartPlaceSummary.put(placeName, place);

					}
				}

			}
		}
		
		ArrayList<Long> processedRouteIds = new ArrayList<Long>();
		Page pageProduct = new Page();
		pageProduct.setCurrentPage(0);
		pageProduct.setSize(1000);
		// Get Search Result Items
		List<Group> groupProducts = groupDao.pagingGroupByDateAndDestAndPrice(startCalendar,
				endCalendar, pageProduct, departPlace, destination, lprice, uprice);
		
		for (Group group : groupProducts) {
			routeId = group.getRoute().getId();
			if (!processedRouteIds.contains(routeId)) {
				processedRouteIds.add(routeId);
				
				if(lkmRouteTypeSummary.containsKey(group.getRoute().genRouteType()))
				{
					int i = (Integer) lkmRouteTypeSummary.get(group.getRoute().genRouteType());
					i = i +1;
					lkmRouteTypeSummary.put(group.getRoute().genRouteType(), i);
				} else {
					// Counting all non-defined groups as a normal group
					int i = (Integer) lkmRouteTypeSummary.get(Info.ROUTE_TYPE_CGT);
					i = i +1;
					lkmRouteTypeSummary.put(Info.ROUTE_TYPE_CGT, i);
				}
			}
		}
		
		processedRouteIds = new ArrayList<Long>();
		Page pagePrice = new Page();
		pagePrice.setCurrentPage(0);
		pagePrice.setSize(1000);
		// Get Search Result Items
		List<Group> groupPrices = groupDao.pagingGroupByDateAndDestAndPrice(startCalendar,
				endCalendar, pagePrice, departPlace, destination, 0d, 1000000d);

		for (Group group : groupPrices) {
			routeId = group.getRoute().getId();
			if (!processedRouteIds.contains(routeId)) {
				// Get Price Summary
				processedRouteIds.add(routeId);
				double price = group.getPrice();

				if (price <= 20) {
					price1 = price1 + 1;
				} else if (20 < price && price < 100) {
					price2 = price2 + 1;
				} else if (100 < price && price < 200) {
					price3 = price3 + 1;
				} else if (200 < price && price < 300) {
					price4 = price4 + 1;
				} else if (300 < price && price < 400) {
					price5 = price5 + 1;
				} else if (400 < price && price < 500) {
					price6 = price6 + 1;
				} else if (price > 500) {
					price7 = price7 + 1;
				}
			}
		}

		hmPriceRange.put(Info.PRICE_RANGE_1_20, String.valueOf(price1));
		hmPriceRange.put(Info.PRICE_RANGE_20_100, String.valueOf(price2));
		hmPriceRange.put(Info.PRICE_RANGE_101_200, String.valueOf(price3));
		hmPriceRange.put(Info.PRICE_RANGE_201_300, String.valueOf(price4));
		hmPriceRange.put(Info.PRICE_RANGE_301_400, String.valueOf(price5));
		hmPriceRange.put(Info.PRICE_RANGE_401_500, String.valueOf(price6));
		hmPriceRange.put(Info.PRICE_RANGE_500, String.valueOf(price7));

		searchResultSummay.setDepartPlace(hmDepartPlace);
		searchResultSummay.setLkDepartPlace(lmDepartPlaceSummary);
		searchResultSummay.setPriceRange(hmPriceRange);
		searchResultSummay.setRouteType(lkmRouteTypeSummary);

		return searchResultSummay;
	}

	@Override
	public SearchResultDto searchByFamousPlace(String destination, Integer startIndex, int pageSize,
			int orderByDepartDate, int orderByPrice, int orderByDays,
			String pickupCity, int routeType,
			int month, String fp) {
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		
		Calendar realCalendar = Calendar.getInstance();
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		
		if (month == 0) {
			// Set to a relevant high date
			endCalendar.set(2100, 12, 30);
			
		} else {
		
			month = month - 1;
			
			// Cover from current month to December of current year
			if (month == realCalendar.get(Calendar.MONTH)) {
				startCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
				
			} else if (month > realCalendar.get(Calendar.MONTH)){
				startCalendar.set(realCalendar.get(Calendar.YEAR), month, 1, 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
				
			// Cover from January to the month before current month of next year
			} else { 
				startCalendar.set(realCalendar.get(Calendar.YEAR) + 1, month, 1, 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR) + 1, month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
			}
		}

		SearchResultDto searchResultDto = new SearchResultDto();
		searchResultDto.setSearchType(SearchType.NAVIGATION_SEARCH_FAMOUS_PLACE);
		searchResultDto.setKeyword(destination);

		ArrayList<SearchResultItemDto> searchResultItems = new ArrayList<SearchResultItemDto>();
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		long routeId;

		Page page = new Page();
		page.setCurrentPage(startIndex);
		page.setSize(pageSize);

		// Get Search Result Items
		List<Route> routes = routeDao.pagingRouteByFamousPalce(destination, startCalendar, endCalendar,
				page, orderByDepartDate, orderByPrice,
				orderByDays, pickupCity, routeType, fp);

		searchResultDto.setPage(page);

		for (Route route : routes) {

			routeId = route.getId();
			SearchResultItemDto searchResultItemDto = new SearchResultItemDto();

			searchResultItemDto.setRoute(route);
			searchResultItemDto.setTitle(route.getName());
			searchResultItemDto.setThumbUrl(route.getThumbUrl());
			
			// Ken Chen 2012/09/26 Add route type info
			searchResultItemDto.setRouteType(route.genRouteType());
			searchResultItemDto.setRouteBg("./images/t_bg_"+searchResultItemDto.getRouteType() +".png");

			List<Group> groups = groupDao.list_group_by_route_and_date_and_famous_place(
					(int) routeId, startCalendar, endCalendar, Info.GS_OPENNING, 2,
					orderByPrice, pickupCity, fp);
			searchResultItemDto.setGroups(groups);

			StringBuffer ftDepartDate = new StringBuffer(20);
			StringBuffer ftDepartDateAndSeat = new StringBuffer(40);

			for (Group group : groups) {
				ftDepartDate
						.append(df.format(group.getDepart_date().getTime()));
				ftDepartDate.append("   ");

				ftDepartDateAndSeat.append(df.format(group.getDepart_date()
						.getTime()));
				ftDepartDateAndSeat.append("（");
				
				// Ken Chen 2012/09/26 设置团购座位
				if(group.getRoute().genRouteType()==Info.ROUTE_TYPE_CZTG)
				{
					ftDepartDateAndSeat.append(group.getSeats_groupon() - group.gen_seats_for_go());
				}
				else
				{
					ftDepartDateAndSeat.append(group.getSeats() - group.gen_all_seats_for_go());
				}
				ftDepartDateAndSeat.append("个座位）   ");
			}

			searchResultItemDto.setDepartDate(ftDepartDate.toString());
			searchResultItemDto.setSeats(ftDepartDateAndSeat.toString());

			Group showGroup = null;
			if (groups.size() > 0) {
				// display the first one
				showGroup = groups.get(0);
				searchResultItemDto.setPrice(showGroup.getPrice());
				searchResultItemDto.setVipPrice(showGroup.getVip_price());
				searchResultItemDto.setVoucher(showGroup.getMax_point());
				searchResultItemDto.setGroupPrice(showGroup.getGroup_price());
				searchResultItemDto.setGroupVipPrice(showGroup.getGroup_vip_price());
				
				//ken chen 2012/09/18 仅显示出发站城市
				List<String> list = new ArrayList<String>();
				list.add(pickupCity);
				searchResultItemDto.setDepartPlace(Info.translatePlaces(
						list,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE, "， "));
			
				// Put into list 
				//Ken Chen 2012/09/20 仅添加有团的路线		
				searchResultItems.add(searchResultItemDto);
			}
		}

		searchResultDto.setItems(searchResultItems);

		// Get Summary Info
		searchResultSummay = getSummaryForFamousPlaceSearch(startCalendar, endCalendar, 0, pickupCity,
				destination, fp);
		searchResultDto.setSearchResultSummay(searchResultSummay);

		return searchResultDto;
	}

	private SearchResultSummayDto getSummaryForFamousPlaceSearch(
			Calendar startCalendar, Calendar endCalendar, int month,
			String departPlace, String destination, String fp) {
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		LinkedHashMap lkmRouteTypeSummary = new LinkedHashMap();
		initRouteTypeSummary(lkmRouteTypeSummary);
		
		HashMap<String, PlaceDto> hmDepartPlace = new HashMap<String, PlaceDto>();
		LinkedHashMap lmDepartPlaceSummary = new LinkedHashMap();
		initDepartPlaceSummary(lmDepartPlaceSummary);
		LinkedHashMap hmPriceRange = new LinkedHashMap();
		long price1 = 0, price2 = 0, price3 = 0, price4 = 0, price5 = 0, price6 = 0, price7 = 0;

		long routeId;
		
		Page page = new Page();
		page.setCurrentPage(0);
		page.setSize(1000);

		// Get Search Result Items
		List<Group> groups = groupDao.pagingGroupByDateAndDestAndFamousPlace(startCalendar,
				endCalendar, page, "", destination, fp);
		boolean placeOfGroupCounted = false;

		for (Group group : groups) {
			routeId = group.getRoute().getId();

			ArrayList<String> pickupCity = new ArrayList<String>();
			pickupCity = (ArrayList<String>) group.genFuzzyPickupCity();
			
			// Get Pickup City Summary
			for (Iterator<String> it = pickupCity.iterator(); it.hasNext();) {
				placeOfGroupCounted = false;
				String placeName = ((String) it.next()).toLowerCase();

				// Only show defined place
				if (!"".equals(Info.translatePlaceName(placeName,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE))) {
					if (hmDepartPlace.containsKey(placeName)) {
						PlaceDto place = hmDepartPlace.get(placeName);
						HashMap<Long, List<Long>> groupsOfRoutes = place
								.getGroupsOfRoutes();
						List<Long> groupIds = groupsOfRoutes.get(routeId);
						if (null == groupIds) {
							groupIds = new ArrayList<Long>();
							groupIds.add((long) group.getId());
							groupsOfRoutes.put(routeId, groupIds);
							place.setCount(place.getCount() + 1);
						} else {
							if (groupIds.size() < 1) {
								for (long groupId : groupIds) {
									if (groupId == group.getId()) {
										System.out.println("Duplicate place - "
												+ placeName + ", group id - "
												+ group.getId());
										placeOfGroupCounted = true;
										break;
									}
								}
								if (!placeOfGroupCounted) {
									groupIds.add((long) group.getId());
									place.setCount(place.getCount() + 1);
								}
							}
						}
						
						lmDepartPlaceSummary.put(placeName, place);

					} else {
						PlaceDto place = new PlaceDto();
						ArrayList<Long> groupIds = new ArrayList<Long>();
						HashMap<Long, List<Long>> groupsOfRoutes = new HashMap<Long, List<Long>>();
						groupsOfRoutes.put(routeId, groupIds);
						groupIds.add((long) group.getId());

						place.setGroupsOfRoutes(groupsOfRoutes);
						place.setEnglishName(placeName);
						place.setChineseName(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE));
						place.setPlaceCode(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CODE));
						place.setCount(1);

						hmDepartPlace.put(placeName, place);
						
						lmDepartPlaceSummary.put(placeName, place);

					}
				}

			}
		}
		
		ArrayList<Long> processedRouteIds = new ArrayList<Long>();
		Page pagePrice = new Page();
		pagePrice.setCurrentPage(0);
		pagePrice.setSize(1000);
		// Get Search Result Items
		List<Group> groupPrices = groupDao.pagingGroupByDateAndDestAndFamousPlace(startCalendar,
				endCalendar, pagePrice, departPlace, destination, fp);

		for (Group group : groupPrices) {
			routeId = group.getRoute().getId();
			if (!processedRouteIds.contains(routeId)) {
				// Get Price Summary
				processedRouteIds.add(routeId);
				double price = group.getPrice();

				if (price <= 20) {
					price1 = price1 + 1;
				} else if (20 < price && price < 100) {
					price2 = price2 + 1;
				} else if (100 < price && price < 200) {
					price3 = price3 + 1;
				} else if (200 < price && price < 300) {
					price4 = price4 + 1;
				} else if (300 < price && price < 400) {
					price5 = price5 + 1;
				} else if (400 < price && price < 500) {
					price6 = price6 + 1;
				} else if (price > 500) {
					price7 = price7 + 1;
				}
				
				if(lkmRouteTypeSummary.containsKey(group.getRoute().genRouteType()))
				{
					int i = (Integer) lkmRouteTypeSummary.get(group.getRoute().genRouteType());
					i = i +1;
					lkmRouteTypeSummary.put(group.getRoute().genRouteType(), i);
				} else {
					// Counting all non-defined groups as a normal group
					int i = (Integer) lkmRouteTypeSummary.get(Info.ROUTE_TYPE_CGT);
					i = i +1;
					lkmRouteTypeSummary.put(Info.ROUTE_TYPE_CGT, i);
				}
			}
		}

		hmPriceRange.put(Info.PRICE_RANGE_1_20, String.valueOf(price1));
		hmPriceRange.put(Info.PRICE_RANGE_20_100, String.valueOf(price2));
		hmPriceRange.put(Info.PRICE_RANGE_101_200, String.valueOf(price3));
		hmPriceRange.put(Info.PRICE_RANGE_201_300, String.valueOf(price4));
		hmPriceRange.put(Info.PRICE_RANGE_301_400, String.valueOf(price5));
		hmPriceRange.put(Info.PRICE_RANGE_401_500, String.valueOf(price6));
		hmPriceRange.put(Info.PRICE_RANGE_500, String.valueOf(price7));

		searchResultSummay.setDepartPlace(hmDepartPlace);
		searchResultSummay.setLkDepartPlace(lmDepartPlaceSummary);
		searchResultSummay.setPriceRange(hmPriceRange);
		searchResultSummay.setRouteType(lkmRouteTypeSummary);

		return searchResultSummay;
	}

	@Override
	public SearchResultDto searchByScheduleDays(String destination, Integer startIndex, int pageSize,
			int orderByDepartDate, int orderByPrice, int orderByDays,
			String pickupCity, int routeType,
			int month, int ld, int ud) {
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		
		Calendar realCalendar = Calendar.getInstance();
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		
		if (month == 0) {
			// Set to a relevant high date
			endCalendar.set(2100, 12, 30);
			
		} else {
		
			month = month - 1;
			
			// Cover from current month to December of current year
			if (month == realCalendar.get(Calendar.MONTH)) {
				startCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
				
			} else if (month > realCalendar.get(Calendar.MONTH)){
				startCalendar.set(realCalendar.get(Calendar.YEAR), month, 1, 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR), month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
				
			// Cover from January to the month before current month of next year
			} else { 
				startCalendar.set(realCalendar.get(Calendar.YEAR) + 1, month, 1, 0, 0, 0);
				endCalendar.set(realCalendar.get(Calendar.YEAR) + 1, month, realCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
			}
		}

		SearchResultDto searchResultDto = new SearchResultDto();
		searchResultDto.setSearchType(SearchType.NAVIGATION_SEARCH_SCHEDULES);
		searchResultDto.setKeyword(destination);

		ArrayList<SearchResultItemDto> searchResultItems = new ArrayList<SearchResultItemDto>();
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		long routeId;

		Page page = new Page();
		page.setCurrentPage(startIndex);
		page.setSize(pageSize);

		// Get Search Result Items
		List<Route> routes = routeDao.pagingRouteByScheduleDays(destination, startCalendar, endCalendar,
				page, orderByDepartDate, orderByPrice,
				orderByDays, pickupCity, routeType, ld, ud);

		searchResultDto.setPage(page);

		for (Route route : routes) {

			routeId = route.getId();
			SearchResultItemDto searchResultItemDto = new SearchResultItemDto();

			searchResultItemDto.setRoute(route);
			searchResultItemDto.setTitle(route.getName());
			searchResultItemDto.setThumbUrl(route.getThumbUrl());
			
			// Ken Chen 2012/09/26 Add route type info
			searchResultItemDto.setRouteType(route.genRouteType());
			searchResultItemDto.setRouteBg("./images/t_bg_"+searchResultItemDto.getRouteType() +".png");

			List<Group> groups = groupDao.list_group_by_route_and_date_and_schedule_days(
					(int) routeId, startCalendar, endCalendar, Info.GS_OPENNING, 2,
					orderByPrice, pickupCity, ld, ud);
			searchResultItemDto.setGroups(groups);

			StringBuffer ftDepartDate = new StringBuffer(20);
			StringBuffer ftDepartDateAndSeat = new StringBuffer(40);

			for (Group group : groups) {
				ftDepartDate
						.append(df.format(group.getDepart_date().getTime()));
				ftDepartDate.append("   ");

				ftDepartDateAndSeat.append(df.format(group.getDepart_date()
						.getTime()));
				ftDepartDateAndSeat.append("（");
				
				// Ken Chen 2012/09/26 设置团购座位
				if(group.getRoute().genRouteType()==Info.ROUTE_TYPE_CZTG)
				{
					ftDepartDateAndSeat.append(group.getSeats_groupon() - group.gen_seats_for_go());
				}
				else
				{
					ftDepartDateAndSeat.append(group.getSeats() - group.gen_all_seats_for_go());
				}
				ftDepartDateAndSeat.append("个座位）   ");
			}

			searchResultItemDto.setDepartDate(ftDepartDate.toString());
			searchResultItemDto.setSeats(ftDepartDateAndSeat.toString());

			Group showGroup = null;
			if (groups.size() > 0) {
				// display the first one
				showGroup = groups.get(0);
				searchResultItemDto.setPrice(showGroup.getPrice());
				searchResultItemDto.setVipPrice(showGroup.getVip_price());
				searchResultItemDto.setVoucher(showGroup.getMax_point());
				searchResultItemDto.setGroupPrice(showGroup.getGroup_price());
				searchResultItemDto.setGroupVipPrice(showGroup.getGroup_vip_price());
				
				//ken chen 2012/09/18 仅显示出发站城市
				List<String> list = new ArrayList<String>();
				list.add(pickupCity);
				searchResultItemDto.setDepartPlace(Info.translatePlaces(
						list,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE, "， "));
			
				// Put into list 
				//Ken Chen 2012/09/20 仅添加有团的路线		
				searchResultItems.add(searchResultItemDto);
			}
		}

		searchResultDto.setItems(searchResultItems);

		// Get Summary Info
		searchResultSummay = getSummaryForScheduleDaysSearch(startCalendar, endCalendar, 0, pickupCity,
				destination, ld, ud);
		searchResultDto.setSearchResultSummay(searchResultSummay);

		return searchResultDto;
	}

	private SearchResultSummayDto getSummaryForScheduleDaysSearch(
			Calendar startCalendar, Calendar endCalendar, int month,
			String departPlace, String destination, int ld, int ud) {
		SearchResultSummayDto searchResultSummay = new SearchResultSummayDto();

		LinkedHashMap lkmRouteTypeSummary = new LinkedHashMap();
		initRouteTypeSummary(lkmRouteTypeSummary);
		
		HashMap<String, PlaceDto> hmDepartPlace = new HashMap<String, PlaceDto>();
		LinkedHashMap lmDepartPlaceSummary = new LinkedHashMap();
		initDepartPlaceSummary(lmDepartPlaceSummary);
		LinkedHashMap hmPriceRange = new LinkedHashMap();
		long price1 = 0, price2 = 0, price3 = 0, price4 = 0, price5 = 0, price6 = 0, price7 = 0;

		long routeId;
		
		Page page = new Page();
		page.setCurrentPage(0);
		page.setSize(1000);

		// Get Search Result Items
		List<Group> groups = groupDao.pagingGroupByDateAndDestAndScheduleDays(startCalendar,
				endCalendar, page, "", destination, ld, ud);
		boolean placeOfGroupCounted = false;

		for (Group group : groups) {
			routeId = group.getRoute().getId();

			ArrayList<String> pickupCity = new ArrayList<String>();
			pickupCity = (ArrayList<String>) group.genFuzzyPickupCity();
			
			// Get Pickup City Summary
			for (Iterator<String> it = pickupCity.iterator(); it.hasNext();) {
				placeOfGroupCounted = false;
				String placeName = ((String) it.next()).toLowerCase();

				// Only show defined place
				if (!"".equals(Info.translatePlaceName(placeName,
						Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE))) {
					if (hmDepartPlace.containsKey(placeName)) {
						PlaceDto place = hmDepartPlace.get(placeName);
						HashMap<Long, List<Long>> groupsOfRoutes = place
								.getGroupsOfRoutes();
						List<Long> groupIds = groupsOfRoutes.get(routeId);
						if (null == groupIds) {
							groupIds = new ArrayList<Long>();
							groupIds.add((long) group.getId());
							groupsOfRoutes.put(routeId, groupIds);
							place.setCount(place.getCount() + 1);
						} else {
							if (groupIds.size() < 1) {
								for (long groupId : groupIds) {
									if (groupId == group.getId()) {
										System.out.println("Duplicate place - "
												+ placeName + ", group id - "
												+ group.getId());
										placeOfGroupCounted = true;
										break;
									}
								}
								if (!placeOfGroupCounted) {
									groupIds.add((long) group.getId());
									place.setCount(place.getCount() + 1);
								}
							}
						}
						
						lmDepartPlaceSummary.put(placeName, place);

					} else {
						PlaceDto place = new PlaceDto();
						ArrayList<Long> groupIds = new ArrayList<Long>();
						HashMap<Long, List<Long>> groupsOfRoutes = new HashMap<Long, List<Long>>();
						groupsOfRoutes.put(routeId, groupIds);
						groupIds.add((long) group.getId());

						place.setGroupsOfRoutes(groupsOfRoutes);
						place.setEnglishName(placeName);
						place.setChineseName(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CHINESE));
						place.setPlaceCode(Info.translatePlaceName(placeName,
								Info.TRANSLATE_FROM_ENGLISH_TO_CODE));
						place.setCount(1);

						hmDepartPlace.put(placeName, place);
						
						lmDepartPlaceSummary.put(placeName, place);

					}
				}

			}
		}
		
		ArrayList<Long> processedRouteIds = new ArrayList<Long>();
		Page pagePrice = new Page();
		pagePrice.setCurrentPage(0);
		pagePrice.setSize(1000);
		// Get Search Result Items
		List<Group> groupPrices = groupDao.pagingGroupByDateAndDestAndScheduleDays(startCalendar,
				endCalendar, pagePrice, departPlace, destination, ld, ud);

		for (Group group : groupPrices) {
			routeId = group.getRoute().getId();
			if (!processedRouteIds.contains(routeId)) {
				// Get Price Summary
				processedRouteIds.add(routeId);
				double price = group.getPrice();

				if (price <= 20) {
					price1 = price1 + 1;
				} else if (20 < price && price < 100) {
					price2 = price2 + 1;
				} else if (100 < price && price < 200) {
					price3 = price3 + 1;
				} else if (200 < price && price < 300) {
					price4 = price4 + 1;
				} else if (300 < price && price < 400) {
					price5 = price5 + 1;
				} else if (400 < price && price < 500) {
					price6 = price6 + 1;
				} else if (price > 500) {
					price7 = price7 + 1;
				}
				
				if(lkmRouteTypeSummary.containsKey(group.getRoute().genRouteType()))
				{
					int i = (Integer) lkmRouteTypeSummary.get(group.getRoute().genRouteType());
					i = i +1;
					lkmRouteTypeSummary.put(group.getRoute().genRouteType(), i);
				} else {
					// Counting all non-defined groups as a normal group
					int i = (Integer) lkmRouteTypeSummary.get(Info.ROUTE_TYPE_CGT);
					i = i +1;
					lkmRouteTypeSummary.put(Info.ROUTE_TYPE_CGT, i);
				}
			}
		}

		hmPriceRange.put(Info.PRICE_RANGE_1_20, String.valueOf(price1));
		hmPriceRange.put(Info.PRICE_RANGE_20_100, String.valueOf(price2));
		hmPriceRange.put(Info.PRICE_RANGE_101_200, String.valueOf(price3));
		hmPriceRange.put(Info.PRICE_RANGE_201_300, String.valueOf(price4));
		hmPriceRange.put(Info.PRICE_RANGE_301_400, String.valueOf(price5));
		hmPriceRange.put(Info.PRICE_RANGE_401_500, String.valueOf(price6));
		hmPriceRange.put(Info.PRICE_RANGE_500, String.valueOf(price7));

		searchResultSummay.setDepartPlace(hmDepartPlace);
		searchResultSummay.setLkDepartPlace(lmDepartPlaceSummary);
		searchResultSummay.setPriceRange(hmPriceRange);
		searchResultSummay.setRouteType(lkmRouteTypeSummary);

		return searchResultSummay;
	}	
}
