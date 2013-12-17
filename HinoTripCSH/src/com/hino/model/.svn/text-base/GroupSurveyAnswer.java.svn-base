package com.hino.model;

import java.util.Calendar;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "group_survey_answers")
public class GroupSurveyAnswer {
	private long id;
	private GroupSurvey survey;
	private GroupSurveyAnswer supper_answer;
	private String topic_title;
	private long topic_xh;
	private long topic_type;
	private String answer_prefix;
	private String answer;	
	private String other;
	private Group group;
	private Customer customer;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	@OneToOne
	public GroupSurvey getSurvey() {
		return survey;
	}
	public void setSurvey(GroupSurvey survey) {
		this.survey = survey;
	}
	
	@OneToOne
	public GroupSurveyAnswer getSupperSurvey() {
		return supper_answer;
	}
	@OneToOne
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	@OneToOne
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setSupperSurvey(GroupSurveyAnswer supper_answer) {
		this.supper_answer = supper_answer;
	}
	public String getTopic_title() {
		return topic_title;
	}
	public void setTopic_title(String topic_title) {
		this.topic_title = topic_title;
	}
	public long getTopic_type() {
		return topic_type;
	}
	public void setTopic_type(long topic_type) {
		this.topic_type = topic_type;
	}
	public String getAnswer_prefix() {
		return answer_prefix;
	}
	public void setAnswer_prefix(String answer_prefix) {
		this.answer_prefix = answer_prefix;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public long getTopic_xh() {
		return topic_xh;
	}
	public void setTopic_xh(long topic_xh) {
		this.topic_xh = topic_xh;
	}

	
	
 
}
