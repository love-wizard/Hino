package com.hino.service;

import java.util.List;

import com.hino.dto.GroupSurveyAnswerDto;
import com.hino.dto.GroupSurveyDto;
import com.hino.model.Group;
import com.hino.model.GroupSurvey;
import com.hino.model.GroupSurveyAnswer;
import com.hino.util.ServiceResponse;

public interface GroupSurveyService {
	public ServiceResponse addGroupSurvey(GroupSurveyDto gsDto);
	public ServiceResponse updateGroupSurvey(GroupSurveyDto gsDto);
	public ServiceResponse deleteGroupSurvey(long id);
	public GroupSurvey getGroupSurvey(long id);
	public List<GroupSurvey> getGroupSurveyListByGroupId(long gid);
	public int getGroupSurveyCountByGroupId(long gid);
	
	public ServiceResponse addGroupSurveyAnswer(GroupSurveyAnswerDto gsaDto);
	public ServiceResponse updateGroupSurveyAnswer(GroupSurveyAnswerDto gsaDto);
	public ServiceResponse deleteGroupSurveyAnswer(long id);
	public GroupSurveyAnswer getGroupSurveyAnswer(long id);
	public List<GroupSurveyAnswer> getGroupSurveyAnswerListByGroupId(long gid);
	public int getGroupSurveyAnswerCountByGroupId(long gid);
	
	public ServiceResponse setupGroupSurvey(Integer gid, GroupSurveyDto gsDto,List<GroupSurveyAnswerDto> lgsaDto);
	public List<Group> getSurveyBookingGroup(long l);

}
