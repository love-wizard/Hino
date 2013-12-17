package com.hino.service.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.commons.fileupload.FileItem;

import com.hino.dao.AreaBranchDAO;
import com.hino.dao.MessageDAO;
import com.hino.dao.RecruitmentDAO;
import com.hino.dao.ResourceDAO;
import com.hino.dao.StaffDAO;
import com.hino.dto.MsgEmailCAMDto;
import com.hino.dto.RecruitmentDto;
import com.hino.dto.RegulationManageDto;
import com.hino.dto.StaffBasicInfoDto;
import com.hino.dto.StaffCreateDto;
import com.hino.dto.StaffPasswordDto;
import com.hino.model.AreaBranch;
import com.hino.model.Message;
import com.hino.model.Recruitment;
import com.hino.model.Resource;
import com.hino.model.Staff;
import com.hino.service.HumanResourceService;
import com.hino.util.EmailUtil;
import com.hino.util.EscapeHtml;
import com.hino.util.FileCenter;
import com.hino.util.Info;
import com.hino.util.PriviledgeParser;
import com.hino.util.RandomGenerator;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class HumanResourceServiceImpl implements HumanResourceService {
	private StaffDAO staffDao;
	private MessageDAO messageDao;
	private FileCenter fileCenter;
	private EmailUtil emailUtil;
	private ResourceDAO resourceDao;
	private RecruitmentDAO recruitmentDao;
	
	public RecruitmentDAO getRecruitmentDao() {
		return recruitmentDao;
	}

	public void setRecruitmentDao(RecruitmentDAO recruitmentDao) {
		this.recruitmentDao = recruitmentDao;
	}

	public EmailUtil getEmailUtil() {
		return emailUtil;
	}

	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}

	public ResourceDAO getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDAO resourceDao) {
		this.resourceDao = resourceDao;
	}

	private AreaBranchDAO areaBranchDao;

	
	public MessageDAO getMessageDao() {
		return messageDao;
	}

	public FileCenter getFileCenter() {
		return fileCenter;
	}

	public void setFileCenter(FileCenter fileCenter) {
		this.fileCenter = fileCenter;
	}

	public void setMessageDao(MessageDAO messageDao) {
		this.messageDao = messageDao;
	}

	public StaffDAO getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDAO staffDao) {
		this.staffDao = staffDao;
	}

	public AreaBranchDAO getAreaBranchDao() {
		return areaBranchDao;
	}

	public void setAreaBranchDao(AreaBranchDAO areaBranchDao) {
		this.areaBranchDao = areaBranchDao;
	}
	
	@Override
	public Staff getStaffByStaffNo(String sn) {
		return staffDao.findbyStaffno(sn);
	}

	@Override
	public ServiceResponse create_new_staff(StaffCreateDto scdto) {

		ServiceResponse sr = new ServiceResponse();
		long aid = scdto.getAreaselect();
		
		AreaBranch abu = null;
		String staffno = scdto.getStaffno();
		String staffemail = scdto.getStaffemail();
		
		if (staffDao.findbyStaffno(staffno) != null) {
			sr.addInfo(Info.STAFF_CREATE_FAILED_SN_EXIST);						
			sr.setSucc(false);
			return sr;
		}
		Staff s = new Staff();
		if(aid!=-1)
		{
			abu = areaBranchDao.getAreaBranchById(aid);
			s.setArea(abu.getName());
			s.setAreaid((int)abu.getId());
		}

		s.setStaffno(staffno);
		s.setEmail(staffemail);
		s.setPriviledge(scdto.getPriviledge());
		s.setPassword(RandomGenerator.randomString(8)); 

		s.setCreate_time(Calendar.getInstance());
		
		if (PriviledgeParser.has_priviledge(scdto.getPriviledge(), PriviledgeParser.AREA_REP)) {
			if(abu==null)
			{
				sr.addInfo(Info.STAFF_CREATE_FAILED_AREA_EMPTY);						
				sr.setSucc(false);
				return sr;
			} else
			{
				abu.setLeader(s);
				s.setArea(abu.getName());
				s.setAreaid((int)abu.getId());
			}
		}

		if (PriviledgeParser.has_priviledge(scdto.getPriviledge(),PriviledgeParser.SALES_REP)) {
			if(abu==null)
			{
				sr.addInfo(Info.STAFF_CREATE_FAILED_AREA_EMPTY);						
				sr.setSucc(false);
				return sr;
			} else
			{
				abu.addFollows(s);
				s.setArea(abu.getName());
				s.setAreaid((int)abu.getId());
			}
		}

		// TODO send password to staff email
		staffDao.save(s);
		if(abu!=null)
		{
			areaBranchDao.update(abu);
		}
		
		sr.setResponse(s);
		sr.setSucc(true);
		return sr;
	}

	@Override
	public List<AreaBranch> list_all_area() {
		return areaBranchDao.getAllAreaBranch();
	}

	@Override
	public List<Staff> list_all_staff() {
		return staffDao.getAllStaff();
	}

	@Override
	public ServiceResponse reset_staff_priviledge(StaffCreateDto scdto) {
		ServiceResponse sr = new ServiceResponse();
		long aid = scdto.getAreaselect();
		
		AreaBranch abu = null;
		Staff s = staffDao.find((int)scdto.getId());
		if(aid!=-1)
		{
			areaBranchDao.removeLeaderByStaff(s.getId());
			abu = areaBranchDao.getAreaBranchById(aid);
			s.setArea(abu.getName());
			s.setAreaid((int)abu.getId());
		} else
		{
			s.setArea(null);
			s.setAreaid(null);
		}		
		
		s.setPriviledge(scdto.getPriviledge());	
		
		if (PriviledgeParser.has_priviledge(scdto.getPriviledge(), PriviledgeParser.AREA_REP)) {
			if(abu==null)
			{
				sr.addInfo(Info.STAFF_CREATE_FAILED_AREA_EMPTY);						
				sr.setSucc(false);
				return sr;
			} else
			{
				abu.setLeader(s);
				s.setArea(abu.getName());
				s.setAreaid((int)abu.getId());
			}
		}

		if (PriviledgeParser.has_priviledge(scdto.getPriviledge(),PriviledgeParser.SALES_REP)) {
			if(abu==null)
			{
				sr.addInfo(Info.STAFF_CREATE_FAILED_AREA_EMPTY);						
				sr.setSucc(false);
				return sr;
			} else
			{
				abu.addFollows(s);
				s.setArea(abu.getName());
				s.setAreaid((int)abu.getId());
			}
		}

		staffDao.update(s);	
		if(abu!=null)
		{
			areaBranchDao.update(abu);
		}
		sr.setSucc(true);
		return sr;
	}

	@Override
	public Staff view_staff(int id) {
		return staffDao.find(id);
	}

	@Override
	public void delete_staff(int id) {
		Staff s = new Staff();
		s.setId(id);
		areaBranchDao.removeLeaderByStaff(id);
		staffDao.delete(s);
	}

	@Override
	public int staff_status_change(int id) {
		Staff s = staffDao.find(id);
		if(s!=null)
		{
			s.change_status();
			staffDao.update(s);
		}
		return s.getStatus();
	}

	@Override
	public List<Message> getPagedMsgList(Staff staff, int page, int size) {
		return messageDao.getPagedMsgByReceiverId(staff.getId(), page, size);
	}

	@Override
	public int getMsgCount(Staff curStaff) {
		if(curStaff!=null)
		{
			return messageDao.getMsgCountByReceiverId(curStaff.getId());
		} else
		{
			return 0;
		}
		
	}

	@Override
	public List<Staff> list_filtered_staff(String filter, List<Integer> pri) {
		return staffDao.findbyPartStaffno(filter, PriviledgeParser.make_hql_like_string(pri));
	}

	@Override
	public Message getMsgById(int id) {
		return messageDao.getMessageById(id);
	}

	@Override
	public void updateMsg(Message newMsg) {
		messageDao.update(newMsg);
	}

	@Override
	public void send_email(MsgEmailCAMDto medto, String attUrl) {
		String toList = "";
		try {
		    for(String curStaffId : medto.getStaffidlist()) {
		    	if(toList.compareTo("") != 0)
		    		toList = toList+",";
		    	toList = toList+view_staff(Integer.parseInt(curStaffId)).getEmail();
		    }
		    emailUtil.setTo(toList);
		    String filePath = new FileCenter().getProtectedDir()+attUrl;

		    emailUtil.send(toList, Info.EMAIL_TO,
		    		EscapeHtml.htmlDecode(medto.getTitle()),
		    		EscapeHtml.htmlDecode(medto.getContent()),
		    		filePath, attUrl);
		    System.out.println("SUCCESS");
		} catch (Exception e) {
			System.out.println("FAIL"+e.getMessage());
		}
	}

	@Override
	public void send_sys_message(MsgEmailCAMDto medto, String attUrl) {

		for(String curStaffId : medto.getStaffidlist()) {
			Message msg = new Message();
			
			msg.setCreate_time(Calendar.getInstance());
			msg.setIsRead(false);
			msg.setMessage(medto.getContent());
			msg.setReciver(view_staff(Integer.parseInt(curStaffId)));
			msg.setSender(medto.getSender());
			msg.setTitle(medto.getTitle());
			msg.setAtt_url(attUrl);
			
			messageDao.save(msg);
		}
	}

	@Override
	public void sendMsgEmail(MsgEmailCAMDto medto) {
		try{
						
			// Upload attachment
			String attUrl = "";
			if (medto.getAttachment().getName().trim().compareTo("") != 0) {
				try {
					attUrl = fileCenter.save_file(medto.getAttachment(), true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			for(String curType : medto.getTypelist()) {
				if(curType.compareTo(Info.TYPE_MSG) == 0) {
					medto.setTitle(EscapeHtml.htmlEncode(medto.getTitle()));
					medto.setContent(EscapeHtml.htmlEncode(medto.getContent()));
					send_sys_message(medto, attUrl);
				}
				else {
					send_email(medto, attUrl);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
						
		}
		

	}

	@Override
	public List<Resource> getResourceFileList(int category) {
		return resourceDao.getResourceByCat(category);
	}

	@Override
	public void create_new_regulation(RegulationManageDto regulationdto) {
		Resource regResource = new Resource();
		String regFileUrl = "";
		FileItem regulationFileItem = regulationdto.getRegulationfile();
		if (regulationFileItem.getName().trim().compareTo("") != 0) {
			try {
				regFileUrl = fileCenter.save_file(regulationFileItem, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		regResource.setAuthor(regulationdto.getAuthor());
		regResource.setCategory(regulationdto.getCategory());
		regResource.setFilename(regFileUrl);
		regResource.setName(fileCenter.getFileItemName(regulationFileItem));
		regResource.setUpdate_time(Calendar.getInstance());
		
		resourceDao.save(regResource);
	}

	@Override
	public void delete_resource(Integer id) {
		resourceDao.delete(id);
	}

	@Override
	public ServiceResponse update_staff_basic(StaffBasicInfoDto basicinfodto) {
		ServiceResponse sr = new ServiceResponse();
		
		Staff targetStaff = staffDao.find((int)basicinfodto.getId());
		
		// When email is changed, check if it is unique
		if(targetStaff.getEmail().compareTo(basicinfodto.getEmail()) != 0) {
			if(staffDao.checkEmailUnique(basicinfodto.getEmail()) == false) {
				sr.addInfo(Info.STAFF_UPDATE_FAILED_DUPLICATE_EMAIL);
				sr.setSucc(false);
				return sr;
			}
		}
		
		// Set new attributes' values
		targetStaff.setLastname(basicinfodto.getLastname());
		targetStaff.setFirstname(basicinfodto.getFirstname());
		targetStaff.setChinesename(basicinfodto.getChinesename());
		targetStaff.setGender(basicinfodto.getGender());
		
		// Update avatar if a new image is uploaded
		String avatarUrl = "";
		if (basicinfodto.getAvatar().getName().trim().compareTo("") != 0) {
			try {
				avatarUrl = fileCenter.save_file(basicinfodto.getAvatar(), false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(avatarUrl != "")
			targetStaff.setAvatar_url(avatarUrl);
		
		targetStaff.setEmail(basicinfodto.getEmail());
		targetStaff.setPhone(basicinfodto.getTelephone());
		targetStaff.setDob(basicinfodto.getDob());
		targetStaff.setAddress(basicinfodto.getAddress());
		targetStaff.setCity(basicinfodto.getCity());
		targetStaff.setPostcode(basicinfodto.getPostcode());
		
		staffDao.update(targetStaff);
		
		sr.setSucc(true);
		sr.setResponse(targetStaff);
		return sr;
	}

	@Override
	public ServiceResponse update_staff_psd(StaffPasswordDto psddto) {
		ServiceResponse sr = new ServiceResponse();
		
		Staff targetStaff = staffDao.find((int)psddto.getId());
		
		// Check if old password is correct
		if(targetStaff.getPassword().compareTo(psddto.getOldpsd()) != 0) {
			sr.addInfo(Info.STAFF_UPDATE_FAILED_PASSWORD_ERROR);
			sr.setSucc(false);
			return sr;
		}
		
		targetStaff.setPassword(psddto.getNewpsd());
		
		staffDao.update(targetStaff);
		
		sr.setSucc(true);
		return sr;
	}


	@Override
	public void send_staff_passwd_email(Staff s) {
		try {
			String title = "[海诺旅游后台管理系统]新员工登录信息";
			String content = "您好：\n\n"+
							"　　我们已经为您创建了海诺旅游后台管理系统的登录账号和密码。\n"+
							"　　您的登录账号为："+s.getStaffno()+"\n"+
							"　　您的登录密码为："+s.getPassword()+"\n"+
							"　　登录系统后请尽快修改密码。\n"+
							"　　衷心祝贺您成为海诺的一员。\n\n"+
							"海诺旅游\n"+
							TimeFormater.format2(Calendar.getInstance());
			emailUtil.send(s.getEmail(), Info.EMAIL_TO, title, content, null, null);
		    System.out.println("SUCCESS");
		} catch (Exception e) {
			System.out.println("FAIL"+e.getMessage());
		}
	}


	@Override
	public void create_new_resource(int category, Staff author, FileItem resFileItem) {
		Resource resource = new Resource();
		String resFileUrl = "";
		if (resFileItem.getName().trim().compareTo("") != 0) {
			try {
				resFileUrl = fileCenter.save_file(resFileItem, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		resource.setAuthor(author);
		resource.setCategory(category);
		resource.setFilename(resFileUrl);
		resource.setName(fileCenter.getFileItemName(resFileItem));
		resource.setUpdate_time(Calendar.getInstance());
		
		resourceDao.save(resource);
	}

	@Override
	public Resource get_resource(Integer id) {
		return resourceDao.getResourceById(id);
	}

	@Override
	public List<Recruitment> getRecruitmentList() {
		return recruitmentDao.getAllRecruitments();
	}

	@Override
	public int getRecruitmentCount() {
		return recruitmentDao.getAllRecruitmentCount();
	}

	@Override
	public Recruitment getRecruitmentById(long id) {
		return recruitmentDao.getRecruitmentById(id);
	}

	@Override
	public void updateRecruitment(RecruitmentDto rdto) {
		Recruitment r = recruitmentDao.getRecruitmentById(rdto.getId());
		if (r != null) {
			r.setPosition(rdto.getPosition());
			r.setDescription(rdto.getDescription());
			r.setExpiryDate(rdto.getExpiryDate());
			recruitmentDao.update(r);
		}		
	}

	@Override
	public void addRecruitment(RecruitmentDto rdto) {
		Recruitment r = new Recruitment();		
		r.setPosition(rdto.getPosition());
		r.setDescription(rdto.getDescription());
		r.setExpiryDate(rdto.getExpiryDate());
		recruitmentDao.save(r);
	}

	@Override
	public void deleteRecruitmentById(Integer id) {
		recruitmentDao.delete(id);
	}

	@Override
	public List<Recruitment> getActiveRecruitments() {
		return recruitmentDao.getActiveRecruitments();
	}
}
