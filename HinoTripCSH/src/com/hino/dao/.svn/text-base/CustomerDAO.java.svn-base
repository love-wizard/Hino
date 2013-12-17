package com.hino.dao;


import java.util.List;

import com.hino.model.Customer;

public interface CustomerDAO {
	
	public Customer getCustomerById(long cid);
	
	public void save(Customer c);
	
	public Customer find(long id);
	
	public void update_basic_info(Customer c);
	
	public void update_password(Customer c);
	
	public void update_login_time(Customer c);
	
	public boolean exist_email(String email);
	
	public Customer findCustomerByEmail(String email);
	
	public List<Customer> list_customer_by_paging(int page, int size);
	
	public int getCustomerCount();

	public void update_resetcode(Customer c);

	public Customer getCustomerByResetcode(String resetcode);

	public void update(Customer c);

	public List<Customer> list_all_customer();

	void cancelVip(Customer c);
	
}
