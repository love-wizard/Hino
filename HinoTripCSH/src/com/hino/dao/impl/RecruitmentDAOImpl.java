package com.hino.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hino.dao.RecruitmentDAO;
import com.hino.model.Recruitment;

public class RecruitmentDAOImpl implements RecruitmentDAO {
	private HibernateTemplate ht;
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	@Override
	public void update(Recruitment r) {
		ht.update(r);
	}

	@Override
	public void save(Recruitment r) {
		ht.save(r);
	}

	@Override
	public void delete(long id) {
		Recruitment r = new Recruitment();
		r.setId(id);
		ht.delete(r);
	}

	@Override
	public Recruitment getRecruitmentById(long id) {
		
		return ht.get(Recruitment.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recruitment> getAllRecruitments() {
		
		return ht.find("from Recruitment");
	}

	@Override
	public int getAllRecruitmentCount() {

		return DataAccessUtils.intResult(ht.find("select count(*) from Recruitment"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recruitment> getActiveRecruitments() {
		
		return ht.find("from Recruitment where expiryDate >= now()");
	}
	

}
