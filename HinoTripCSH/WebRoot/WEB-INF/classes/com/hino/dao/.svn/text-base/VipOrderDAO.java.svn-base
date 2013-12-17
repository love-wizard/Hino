package com.hino.dao;

import java.util.List;

import com.hino.model.VipOrder;

public interface VipOrderDAO {
	public VipOrder viewVipOrderById(long id);
	public VipOrder getVipOrderById(long id);
	public List<VipOrder> getVipOrderByEmail(String email);
	
	public void update(VipOrder vo);
	public void save(VipOrder vo);
	public void delete(VipOrder vo);
	
	public List<VipOrder> list_by_paging(Integer status[], int page, int size, String email);
	
	public Long count_order(Integer status[], String email);
	
}
