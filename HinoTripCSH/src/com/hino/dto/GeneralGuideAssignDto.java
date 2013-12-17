package com.hino.dto;

public class GeneralGuideAssignDto {
	private int id;
	private String groupGuide1;
	private String groupGuide2;
	private boolean guidereadycheck;
	private String caution;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupGuide1() {
		return groupGuide1;
	}

	public void setGroupGuide1(String groupGuide1) {
		this.groupGuide1 = groupGuide1;
	}

	public String getGroupGuide2() {
		return groupGuide2;
	}

	public void setGroupGuide2(String groupGuide2) {
		this.groupGuide2 = groupGuide2;
	}

	public boolean getGuidereadycheck() {
		return guidereadycheck;
	}

	public void setGuidereadycheck(boolean guidereadycheck) {
		this.guidereadycheck = guidereadycheck;
	}

	public String getCaution() {
		return caution;
	}

	public void setCaution(String caution) {
		this.caution = caution;
	}

}
