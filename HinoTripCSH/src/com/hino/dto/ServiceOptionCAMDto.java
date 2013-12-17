package com.hino.dto;

import org.apache.commons.fileupload.FileItem;

public class ServiceOptionCAMDto {
	
	private long id;
	
	private String title;
	
	private String description;
	
	private FileItem image;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FileItem getImage() {
		return image;
	}

	public void setImage(FileItem image) {
		this.image = image;
	}
}
