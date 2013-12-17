package com.hino.dto;

import org.apache.commons.fileupload.FileItem;

public class GuideReviewUpdateDto {
	private long groupid;
	private String guidetype;
	private FileItem reviewfile;

	public long getGroupid() {
		return groupid;
	}

	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}

	public String getGuidetype() {
		return guidetype;
	}

	public void setGuidetype(String guidetype) {
		this.guidetype = guidetype;
	}

	public FileItem getReviewfile() {
		return reviewfile;
	}

	public void setReviewfile(FileItem reviewfile) {
		this.reviewfile = reviewfile;
	}

}
