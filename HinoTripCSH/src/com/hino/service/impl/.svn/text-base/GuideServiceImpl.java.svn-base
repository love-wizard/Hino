package com.hino.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.hino.dao.GroupDAO;
import com.hino.dao.GroupHistoryDAO;
import com.hino.dao.ResourceDAO;
import com.hino.dao.StaffDAO;
import com.hino.dto.GeneralGuideAssignDto;
import com.hino.dto.GuideReportUpdateDto;
import com.hino.dto.GuideReviewUpdateDto;
import com.hino.model.Group;
import com.hino.model.GroupHistory;
import com.hino.model.Staff;
import com.hino.service.GuideService;
import com.hino.util.FileCenter;
import com.hino.util.Info;

public class GuideServiceImpl implements GuideService {
	private FileCenter fileCenter;
	private GroupDAO groupDao;
	private StaffDAO staffDao;
	private ResourceDAO resourceDao;
	private GroupHistoryDAO groupHistoryDao;
	
	public GroupHistoryDAO getGroupHistoryDao() {
		return groupHistoryDao;
	}

	public void setGroupHistoryDao(GroupHistoryDAO groupHistoryDao) {
		this.groupHistoryDao = groupHistoryDao;
	}

	public FileCenter getFileCenter() {
		return fileCenter;
	}

	public GroupDAO getGroupDao() {
		return groupDao;
	}

	public ResourceDAO getResourceDao() {
		return resourceDao;
	}

	public StaffDAO getStaffDao() {
		return staffDao;
	}

	public void setFileCenter(FileCenter fileCenter) {
		this.fileCenter = fileCenter;
	}

	public void setGroupDao(GroupDAO groupDao) {
		this.groupDao = groupDao;
	}

	public void setResourceDao(ResourceDAO resourceDao) {
		this.resourceDao = resourceDao;
	}

	public void setStaffDao(StaffDAO staffDao) {
		this.staffDao = staffDao;
	}

	/**
	 * Get groups that are guide_ready and assigned to current staff
	 * @param curStaff
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public List<Group> getPagedAssignedGroupList(Staff curStaff, int page, int size) {
		return groupDao.list_guide_group_by_paging(curStaff.getId(), page, size);
	}

	@Override
	public int getAssignedGroupCount(Staff curStaff) {
		return groupDao.getGuideGroupCount(curStaff.getId());
	}

	@Override
	public void uploadGuideReport(GuideReportUpdateDto guidereportdto) {
		// Upload report
		String reportUrl = "";
		if (guidereportdto.getReportfile().getName().trim().compareTo("") != 0) {
			try {
				reportUrl = fileCenter.save_file(guidereportdto.getReportfile(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Group targetGroup = groupDao.getGroupById((int)guidereportdto.getGroupid());
		GroupHistory gh = new GroupHistory(targetGroup);
		
		if(targetGroup.getGuide1().getId() == guidereportdto.getGuide().getId()) {
			targetGroup.setGuide1ReportUrl(reportUrl);
		}
		
		if(targetGroup.getGuide2().getId() == guidereportdto.getGuide().getId()) {
			targetGroup.setGuide2ReportUrl(reportUrl);
		}
		
		this.groupHistoryDao.save(gh);
		groupDao.update(targetGroup);
	}

	@Override
	public void uploadGuideReview(GuideReviewUpdateDto guidereviewdto) {
		// Upload report
		String reviewUrl = "";
		if (guidereviewdto.getReviewfile().getName().trim().compareTo("") != 0) {
			try {
				reviewUrl = fileCenter.save_file(guidereviewdto.getReviewfile(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Group targetGroup = groupDao.getGroupById((int)guidereviewdto.getGroupid());
		GroupHistory gh = new GroupHistory(targetGroup);
		if(guidereviewdto.getGuidetype().compareTo("guide1") == 0) {
			targetGroup.setGuide1ReviewUrl(reviewUrl);
		}
		
		if(guidereviewdto.getGuidetype().compareTo("guide2") == 0) {
			targetGroup.setGuide2ReviewUrl(reviewUrl);
		}
		
		this.groupHistoryDao.save(gh);
		groupDao.update(targetGroup);
	}
		
	/**
	 * Get all groups that not applied by the current staff
	 * @param curStaff
	 * @return
	 */
	private List<Group> getAllApplicableGroup(Staff curStaff) {
		List<Group> allApplicableGroup = groupDao.list_group_guide_not_ready();
		for(int index=(allApplicableGroup.size()-1); index>=0; index--) {
			Set<Staff> applyingStaff = allApplicableGroup.get(index).getApplying_guide();
			boolean removeGroup = false;
			// If current staff already applied this group, remove it
			for(Staff curAppStaff : applyingStaff) {
				if(curAppStaff.getId() == curStaff.getId())
					removeGroup = true;
			}
			if(removeGroup)
				allApplicableGroup.remove(index);
		}
		return allApplicableGroup;
	}
	
	@Override
	public List<Group> getPagedApplicableGroup(Staff curStaff, int page, int size) {
		List<Group> allApplicableGroup = getAllApplicableGroup(curStaff);
		
		List<Group> pagedApplicableGroup = new ArrayList<Group>();
		for(int index=(page*size); index<(page*size+size-1); index++) {
			if(index < allApplicableGroup.size())
				pagedApplicableGroup.add(allApplicableGroup.get(index));
		}
		return pagedApplicableGroup;
	}
	
	@Override
	public int getApplicableGroupCount(Staff curStaff) {
		return getAllApplicableGroup(curStaff).size();
	}
	
	/**
	 * Get all groups that applied by the current staff
	 * @param curStaff
	 * @return
	 */
	private List<Group> getAllAppliedGroup(Staff curStaff) {
		List<Group> allApplicableGroup = groupDao.list_group_guide_not_ready();
		for(int index=(allApplicableGroup.size()-1); index>=0; index--) {
			Set<Staff> applyingStaff = allApplicableGroup.get(index).getApplying_guide();
			boolean keepGroup = false;
			// If current staff already applied this group, keep it
			for(Staff curAppStaff : applyingStaff) {
				if(curAppStaff.getId() == curStaff.getId())
					keepGroup = true;
			}
			if(!keepGroup)
				allApplicableGroup.remove(index);
		}
		return allApplicableGroup;
	}

	@Override
	public List<Group> getPagedAppliedGroup(Staff curStaff, int page, int size) {
		List<Group> allAppliedGroup = getAllAppliedGroup(curStaff);
		
		List<Group> pagedAppliedGroup = new ArrayList<Group>();
		for(int index=(page*size); index<(page*size+size-1); index++) {
			if(index < allAppliedGroup.size())
				pagedAppliedGroup.add(allAppliedGroup.get(index));
		}
		return pagedAppliedGroup;
	}
	
	@Override
	public int getAppliedGroupCount(Staff curStaff) {
		return getAllAppliedGroup(curStaff).size();
	}

	@Override
	public void applyGroup(int groupid, Staff cutStaff) {
		Group targetGrp = groupDao.getGroupById(groupid);
		//Ken Chen 2013-06-22 记录被更新前的团信息到History表
		GroupHistory gh = new GroupHistory(targetGrp);
				
		targetGrp.getApplying_guide().add(cutStaff);
		
		this.groupHistoryDao.save(gh);
		groupDao.update(targetGrp);
	}

	@Override
	public Group getGroupById(String groupid) {
		return groupDao.getGroupById(Integer.valueOf(groupid));
	}

	/**
	 * Get groups that are in applicable status for all guide
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public List<Group> getPagedAssignableGroupList(int page, int size) {
		return groupDao.list_group_by_status_paging_ordering(Info.GS_OPENNING, null,page, size, "depart_date", true);
	}

	@Override
	public int getAssignableGroupCount() {
		return groupDao.count_group_by_status(Info.GS_OPENNING);
	}

	@Override
	public void updateGuideAssign(GeneralGuideAssignDto guideassigndto) {
		Group targetGroup = groupDao.getGroupById(guideassigndto.getId());
		//Ken Chen 2013-06-22 记录被更新前的团信息到History表
		GroupHistory gh = new GroupHistory(targetGroup);
		
		if(guideassigndto.getGroupGuide1() != null) {
			Staff guide = staffDao.find(Integer.valueOf(guideassigndto.getGroupGuide1()));
			if(guide != null)
				targetGroup.setGuide1(guide);
			else
				targetGroup.setGuide1(null);
		}
		
		if(guideassigndto.getGroupGuide2() != null) {
			Staff guide = staffDao.find(Integer.valueOf(guideassigndto.getGroupGuide2()));
			if(guide != null)
				targetGroup.setGuide2(guide);
			else
				targetGroup.setGuide2(null);
		}
		
		targetGroup.setGuide_ready(guideassigndto.getGuidereadycheck());
		
		targetGroup.setCaution(guideassigndto.getCaution());
		
		this.groupHistoryDao.save(gh);
		groupDao.update(targetGroup);
	}
	

	/**
	 * Get groups that reviews can be uploaded
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public List<Group> getPagedReviewableGroupList(int page, int size) {
		return groupDao.list_reviewable_group(page, size, "depart_date", false);
	}

	@Override
	public int getReviewableGroupCount() {
		return groupDao.count_group_by_status(Info.GS_PROCESSING) + groupDao.count_group_by_status(Info.GS_FINISHED);
	}

}
