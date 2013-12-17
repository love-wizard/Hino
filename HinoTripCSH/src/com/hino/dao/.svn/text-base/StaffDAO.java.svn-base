package com.hino.dao;

import java.util.List;

import com.hino.model.Staff;

public interface StaffDAO {
	public void save(Staff c);
	
	public Staff find(int id);
	
	public Staff findbyStaffno(String id);
	
	public void update_login_time(Staff s);
	
	public List<Staff> getAllStaff();
	
	public void update_priviledge();
	
	public void update(Staff s);
	
	public void delete(Staff s);
	
	public List<Staff> findbyPartStaffno(String part, String pri);

	boolean checkEmailUnique(String email);

	public Staff getStaffByEmail(String email);

	public void update_resetcode(Staff targetStaff);

	public Staff getStaffByResetcode(String resetcode);

	public void update_password(Staff targetStaff);
	
	public List<Staff> findbyPriviledge(List<Integer> list);
	
}
