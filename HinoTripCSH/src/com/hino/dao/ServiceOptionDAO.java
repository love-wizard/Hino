package com.hino.dao;

import java.util.List;

import com.hino.model.ServiceOption;

public interface ServiceOptionDAO {
	public ServiceOption getServiceOptionById(long id);

	public void save(ServiceOption so);
	public void delete(long id);
	public void update(ServiceOption so);
	
	public List<ServiceOption> listServiceOptions();
	public int getServiceOptionCount();
	
	public List<ServiceOption> listDGServiceOptions(long dgid);
	public int getDGServiceOptionCount(long dgId);

	public List<ServiceOption> listServiceOptionsByPaging(int start, int count);
}
