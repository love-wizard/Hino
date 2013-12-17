package com.hino.dto;

import org.apache.commons.fileupload.FileItem;

import com.hino.model.Staff;

public class RegulationManageDto {
	private long id;
	private int category;
	private FileItem regulationfile;
	private Staff author;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public FileItem getRegulationfile() {
		return regulationfile;
	}

	public void setRegulationfile(FileItem regulationfile) {
		this.regulationfile = regulationfile;
	}

	public Staff getAuthor() {
		return author;
	}

	public void setAuthor(Staff author) {
		this.author = author;
	}

}
