package com.hino.service.impl;

import java.util.List;

import com.hino.dao.AreaBranchDAO;
import com.hino.dao.StaffDAO;
import com.hino.dto.AreaCAMDto;
import com.hino.model.AreaBranch;
import com.hino.service.StrategyService;
import com.hino.util.ServiceResponse;

public class StrategyServiceImpl implements StrategyService{
	private AreaBranchDAO areaBranchDao;
	private StaffDAO staffDao;
	
	public StaffDAO getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDAO staffdao) {
		this.staffDao = staffdao;
	}

	public AreaBranchDAO getAreaBranchDao() {
		return areaBranchDao;
	}

	public void setAreaBranchDao(AreaBranchDAO adao) {
		this.areaBranchDao = adao;
	}

	@Override
	public ServiceResponse create_areabranch(AreaCAMDto acam) {
		AreaBranch a = new AreaBranch();
		a.setName(acam.getName());
		a.setDescription(acam.getDescription());
		areaBranchDao.save(a);
		return null;
	}

	@Override
	public void delete_areabranch(long id) {
		areaBranchDao.delete(id);
		
	}

	@Override
	public ServiceResponse edit_areabranch(AreaCAMDto acam) {
		AreaBranch a = areaBranchDao.getAreaBranchById(acam.getId());
		a.setDescription(acam.getDescription());
		a.setName(acam.getName());
		areaBranchDao.update(a);
		return null;
	}

	@Override
	public List<AreaBranch> list_areabranch() {
		return areaBranchDao.getAllAreaBranch();
	}

	@Override
	public AreaBranch view_areabranch(long id) {
		return areaBranchDao.getAreaBranchById(id);
		
	}

}
