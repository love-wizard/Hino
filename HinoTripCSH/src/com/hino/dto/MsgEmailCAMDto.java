package com.hino.dto;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.hino.model.Staff;

public class MsgEmailCAMDto {
	private long id;
	private String title;
	private String content;
	private Staff sender;
	private FileItem attachment;
	private List<String> typelist;
	private List<String> staffidlist; // Staff id list submitted by the form

	public List<String> getStaffidlist() {
		return staffidlist;
	}

	public void setStaffidlist(List<String> staffidlist) {
		this.staffidlist = staffidlist;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FileItem getAttachment() {
		return attachment;
	}

	public void setAttachment(FileItem attachment) {
		this.attachment = attachment;
	}

	public List<String> getTypelist() {
		return typelist;
	}

	public void setTypelist(List<String> typelist) {
		this.typelist = typelist;
	}

	public Staff getSender() {
		return sender;
	}

	public void setSender(Staff sender) {
		this.sender = sender;
	}

}
