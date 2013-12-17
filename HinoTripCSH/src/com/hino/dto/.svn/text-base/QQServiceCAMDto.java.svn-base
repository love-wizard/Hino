package com.hino.dto;

public class QQServiceCAMDto {
	private int id;
	private String serviceName;
	private String qqNumber;
	private int priority;
	private String usedPlace;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getQqNumber() {
		return qqNumber;
	}
	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getUsedPlace() {
		return usedPlace;
	}
	public void setUsedPlace(String usedPlace) {
		if(null==usedPlace)
		{
			this.usedPlace = "INDEX";
		}
		else
		{
			this.usedPlace = usedPlace;
		}
	}
	public String genLink() {
		return "http://wpa.qq.com/msgrd?V=1&Uin=" + qqNumber;
	}

	
}
