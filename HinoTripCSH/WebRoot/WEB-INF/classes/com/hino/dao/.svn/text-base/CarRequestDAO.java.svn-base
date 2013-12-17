package com.hino.dao;

import java.util.List;

import com.hino.model.CarRequest;

public interface CarRequestDAO {
	public CarRequest getCarRequestById(long id);
	public void save(CarRequest v);
	public List<CarRequest> list_all_carrequest();
	public List<CarRequest> list_carrequest_by_paging(int start, int count, final Integer[] status);
	public long getCarRequestCount(final Integer[] status);
	public void delete(long id);
	public void update(CarRequest v);
}
