package com.hino.service;

import com.hino.dto.SearchResultDto;

public interface SearchResultService {

	public SearchResultDto searchInformationByMonth(Integer m, Integer startIndex, Integer pageSize, Integer orderByDepartDate, Integer orderByPrice, Integer orderByDays, String destination, Integer routeType);

	public SearchResultDto searchByDestination(String d, Integer startIndex, int pageSize, Integer orderByDepartDate, Integer orderByPrice, Integer orderByDays, String pickupCity, Integer routeType, Integer month);

	public SearchResultDto searchByPrice(String destination, Integer startIndex,
			int pageSize, Integer orderByDepartDate, Integer orderByPrice,
			Integer orderByDays, String pickupCity, Integer routeType,
			Integer month, double lprice, double uprice);

	public SearchResultDto searchByFamousPlace(String dest, Integer np, int i,
			int obdd, int obp, int obd,
			String tanslateDepartCityFromChineseToEnglish, int routeType,
			int dm, String fp);

	public SearchResultDto searchByScheduleDays(String dest, Integer np, int i,
			int obdd, int obp, int obd,
			String tanslateDepartCityFromChineseToEnglish, int routeType,
			int dm, int ld, int ud);

}
