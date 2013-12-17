package com.hino.dao;

import java.util.List;

import com.hino.model.Resource;
import com.hino.model.Staff;

public interface ResourceDAO {
	public Resource getResourceById(long id);
	public void save(Resource r);
	public void update(Resource r);
	public void delete(long id);
	public List<Resource> getAllResource();
	public List<Resource> getResourceByCat(int c);
	
	public int getSalesMarketReportCount(Staff curStaff);
	public List<Resource> list_sales_market_report_by_paging(Staff curStaff,
			int page, int size);
	public int getGenMarketReportCount();
	public List<Resource> list_gen_market_report_by_paging(int page, int size);
	
}
