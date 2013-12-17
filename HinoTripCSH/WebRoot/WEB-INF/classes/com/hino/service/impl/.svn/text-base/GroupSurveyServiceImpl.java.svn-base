package com.hino.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hino.dao.CustomerDAO;
import com.hino.dao.GroupDAO;
import com.hino.dao.GroupSurveyAnswerDAO;
import com.hino.dao.GroupSurveyDAO;
import com.hino.dto.GroupSurveyAnswerDto;
import com.hino.dto.GroupSurveyDto;
import com.hino.model.Airport;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.GroupSurveyAnswer;
import com.hino.service.GroupSurveyService;
import com.hino.util.ServiceResponse;
import com.hino.model.GroupSurvey;

public class GroupSurveyServiceImpl implements GroupSurveyService {
	private GroupSurveyDAO groupSurveyDAO;
	private GroupSurveyAnswerDAO groupSurveyAnswerDAO;
	private GroupDAO groupDAO;
	private CustomerDAO customerDAO;

	@Override
	public ServiceResponse addGroupSurvey(GroupSurveyDto gsDto) {
		com.hino.model.GroupSurvey gs = (com.hino.model.GroupSurvey) new GroupSurvey();
		gs.setGroup(gsDto.getGroup());
		gs.setCustomer(gsDto.getCustomer());
		gs.setRep(gsDto.getRep());
		gs.setCreate_date(gsDto.getCreate_date());
		gs.setIs_verify(gsDto.getIs_verify());
		gs.setIs_add_bonus(gsDto.getIs_add_bonus());
		this.groupSurveyDAO.save(gs);
		
		return null;
	}

	@Override
	public ServiceResponse updateGroupSurvey(GroupSurveyDto gsDto) {
		com.hino.model.GroupSurvey gs = (com.hino.model.GroupSurvey) new GroupSurvey();
		gs.setId(gsDto.getId());
		gs.setGroup(gsDto.getGroup());
		gs.setCustomer(gsDto.getCustomer());
		gs.setRep(gsDto.getRep());
		gs.setCreate_date(gsDto.getCreate_date());
		gs.setIs_verify(gsDto.getIs_verify());
		gs.setIs_add_bonus(gsDto.getIs_add_bonus());
		this.groupSurveyDAO.save(gs);
		return null;
	}

	@Override
	public ServiceResponse deleteGroupSurvey(long id) {
		GroupSurvey gs;
		ServiceResponse sr = new ServiceResponse();
		
		gs = groupSurveyDAO.getGroupSurveyById(id);
		if(gs==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target group survey was no longer avaliable!");
			return sr;
		}
		
		groupSurveyDAO.delete(id);
		return sr;
	}

	@Override
	public GroupSurvey getGroupSurvey(long id) {
		return groupSurveyDAO.getGroupSurveyById(id);
	}

	@Override
	public List<GroupSurvey> getGroupSurveyListByGroupId(long gid) {
		return groupSurveyDAO.getGroupSurveyByGroupId(gid);
	}

	@Override
	public int getGroupSurveyCountByGroupId(long gid) {
		return groupSurveyDAO.getGroupSurveyByGroupIdCount(gid);
	}

	@Override
	public ServiceResponse addGroupSurveyAnswer(GroupSurveyAnswerDto gsaDto) {
		GroupSurveyAnswer gsa = new GroupSurveyAnswer();
		gsa.setSurvey(gsaDto.getSurvey());
		gsa.setTopic_type(gsaDto.getTopic_type());
		gsa.setTopic_title(gsaDto.getTopic_title());
		gsa.setSupperSurvey(gsaDto.getSupperSurvey());
		gsa.setAnswer_prefix(gsaDto.getAnswer_prefix());
		gsa.setAnswer(gsaDto.getAnswer());
		gsa.setOther(gsaDto.getOther());
		gsa.setCustomer(gsaDto.getCustomer());
		gsa.setGroup(gsaDto.getGroup());
		this.groupSurveyAnswerDAO.save(gsa);
		
		return null;
	}

	@Override
	public ServiceResponse updateGroupSurveyAnswer(GroupSurveyAnswerDto gsaDto) {
		GroupSurveyAnswer gsa = new GroupSurveyAnswer();
		gsa.setId(gsaDto.getId());
		gsa.setSurvey(gsaDto.getSurvey());
		gsa.setTopic_type(gsaDto.getTopic_type());
		gsa.setTopic_title(gsaDto.getTopic_title());
		gsa.setSupperSurvey(gsaDto.getSupperSurvey());
		gsa.setAnswer_prefix(gsaDto.getAnswer_prefix());
		gsa.setAnswer(gsaDto.getAnswer());
		gsa.setOther(gsaDto.getOther());
		gsa.setCustomer(gsaDto.getCustomer());
		gsa.setGroup(gsaDto.getGroup());
		this.groupSurveyAnswerDAO.update(gsa);
		return null;
	}

	@Override
	public ServiceResponse deleteGroupSurveyAnswer(long id) {
		GroupSurveyAnswer gsa;
		ServiceResponse sr = new ServiceResponse();
		
		gsa = groupSurveyAnswerDAO.getGroupSurveyAnswerById(id);
		if(gsa==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target group survey answer was no longer avaliable!");
			return sr;
		}
		
		groupSurveyAnswerDAO.delete(id);
		return sr;
	}

	@Override
	public GroupSurveyAnswer getGroupSurveyAnswer(long id) {
		return groupSurveyAnswerDAO.getGroupSurveyAnswerById(id);
	}

	@Override
	public List<GroupSurveyAnswer> getGroupSurveyAnswerListByGroupId(long gid) {
		return groupSurveyAnswerDAO.getGroupSurveyAnswerByGroupSurveyId(gid);
	}

	@Override
	public int getGroupSurveyAnswerCountByGroupId(long gid) {
		return groupSurveyAnswerDAO.getGroupSurveyAnswerCountByGroupSurveyId(gid);
	}

	public GroupSurveyDAO getGroupSurveyDAO() {
		return groupSurveyDAO;
	}

	public void setGroupSurveyDAO(GroupSurveyDAO groupSurveyDAO) {
		this.groupSurveyDAO = groupSurveyDAO;
	}

	public GroupSurveyAnswerDAO getGroupSurveyAnswerDAO() {
		return groupSurveyAnswerDAO;
	}

	public void setGroupSurveyAnswerDAO(GroupSurveyAnswerDAO groupSurveyAnswerDAO) {
		this.groupSurveyAnswerDAO = groupSurveyAnswerDAO;
	}

	@Override
	public List<Group> getSurveyBookingGroup(long cid) {
		return this.groupDAO.getSurveyBookingGroup(cid);
	}

	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse setupGroupSurvey(Integer gid, GroupSurveyDto gsDto,
			List<GroupSurveyAnswerDto> lgsaDto) {
		ServiceResponse sr = new ServiceResponse();
		gsDto.setGroup(this.groupDAO.getGroupById(gid));
		Calendar realCalendar = Calendar.getInstance();
		gsDto.setCreate_date(realCalendar);
		gsDto.setIs_add_bonus(true);
		
		sr = this.addGroupSurvey(gsDto);
		for(int i = 0;i<lgsaDto.size();i++)
		{
			lgsaDto.get(i).setCustomer(gsDto.getCustomer());
			lgsaDto.get(i).setGroup(gsDto.getGroup());
			sr = this.addGroupSurveyAnswer(lgsaDto.get(i));
		}
		
		Customer c = gsDto.getCustomer();
		c.deposit(500);
		
		this.customerDAO.update(c);
		
		sr = new ServiceResponse();
		sr.setSucc(true);
		return sr;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

}
