package com.hino.service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

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
import com.hino.util.ServiceResponse;

public interface HumanResourceService {
	public ServiceResponse create_new_staff(StaffCreateDto scdto);
	public List<Staff> list_all_staff();
	public List<Staff> list_filtered_staff(String filter, List<Integer> pri);
	public Staff view_staff(int id);

	public Staff getStaffByStaffNo(String sn);
		
	public int staff_status_change(int id);
	public ServiceResponse reset_staff_priviledge(StaffCreateDto scdto);
	public List<AreaBranch> list_all_area();
	public void send_sys_message(MsgEmailCAMDto medto, String attUrl);
	public void send_email(MsgEmailCAMDto medto, String attUrl);
	public void delete_staff(int id);
	

	public List<Message> getPagedMsgList(Staff staff, int page, int size);
	public int getMsgCount(Staff curStaff);
	public Message getMsgById(int id);
	public void updateMsg(Message newMsg);
	public void sendMsgEmail(MsgEmailCAMDto medto);

	public List<Resource> getResourceFileList(int category);
	public void create_new_regulation(RegulationManageDto regulationdto);
	public void delete_resource(Integer id);
	public ServiceResponse update_staff_basic(StaffBasicInfoDto basicinfodto);
	public ServiceResponse update_staff_psd(StaffPasswordDto psddto);
	public void send_staff_passwd_email(Staff s);
	public void create_new_resource(int category, Staff author, FileItem resFileItem);
	public Resource get_resource(Integer id);
	public List<Recruitment> getRecruitmentList();
	public int getRecruitmentCount();
	public Recruitment getRecruitmentById(long id);
	public void updateRecruitment(RecruitmentDto rdto);
	public void addRecruitment(RecruitmentDto rdto);
	public void deleteRecruitmentById(Integer id);
	public List<Recruitment> getActiveRecruitments();
}
