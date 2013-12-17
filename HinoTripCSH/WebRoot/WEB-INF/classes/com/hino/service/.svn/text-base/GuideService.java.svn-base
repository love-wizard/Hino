package com.hino.service;

import java.util.List;

import com.hino.dto.GeneralGuideAssignDto;
import com.hino.dto.GuideReportUpdateDto;
import com.hino.dto.GuideReviewUpdateDto;
import com.hino.model.Group;
import com.hino.model.Staff;

public interface GuideService {

	public void uploadGuideReport(GuideReportUpdateDto guidereportdto);
	public void uploadGuideReview(GuideReviewUpdateDto guidereviewdto);

	public List<Group> getPagedApplicableGroup(Staff curStaff, int page, int size);
	public int getApplicableGroupCount(Staff curStaff);

	public List<Group> getPagedAppliedGroup(Staff curStaff, int page, int size);
	public int getAppliedGroupCount(Staff curStaff);
	
	public List<Group> getPagedAssignedGroupList(Staff curStaff, int page, int size);
	public int getAssignedGroupCount(Staff curStaff);

	public void applyGroup(int groupid, Staff cutStaff);

	public Group getGroupById(String groupid);

	public  List<Group> getPagedAssignableGroupList(int page, int size);
	public int getAssignableGroupCount();

	public void updateGuideAssign(GeneralGuideAssignDto guideassigndto);

	public List<Group> getPagedReviewableGroupList(int page, int size);
	public int getReviewableGroupCount();

}
