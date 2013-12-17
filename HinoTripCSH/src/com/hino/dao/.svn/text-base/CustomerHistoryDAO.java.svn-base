package com.hino.dao;


import java.util.List;

import com.hino.model.Customer;
import com.hino.model.CustomerHistory;

public interface CustomerHistoryDAO {
	
	public CustomerHistory getCustomerHistory(long id);
	
	public List<CustomerHistory> list_customer_history_by_paging(long cid, int page, int size);
	public List<CustomerHistory> list_customer_history(long cid);
	public List<CustomerHistory> list_customer_history(String email);
	
	public int getCustomerHistoryCount();
	public int getCustomerHistoryCount(long cid);
	public int getCustomerHistoryCount(String email);

	public void update(CustomerHistory c);
	public void save(CustomerHistory c);
	public void SaveCustomerHistory(Customer c, CustomerHistory ch);
	
	
}
