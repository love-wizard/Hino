package com.hino.dto;

import org.apache.commons.fileupload.FileItem;

public class VehicleCAMDto {
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVehicledesc() {
		return vehicledesc;
	}
	public void setVehicledesc(String vehicledesc) {
		this.vehicledesc = vehicledesc;
	}
	public String getVehicledesc_en() {
		return vehicledesc_en;
	}
	public void setVehicledesc_en(String vehicledescEn) {
		vehicledesc_en = vehicledescEn;
	}
	public FileItem getFullimgfile() {
		return fullimgfile;
	}
	public void setFullimgfile(FileItem fullimgfile) {
		this.fullimgfile = fullimgfile;
	}
	public String getVehiclename_en() {
		return vehiclename_en;
	}
	public void setVehiclename_en(String vehiclenameEn) {
		vehiclename_en = vehiclenameEn;
	}
	public String getVehiclename() {
		return vehiclename;
	}
	public void setVehiclename(String vehiclename) {
		this.vehiclename = vehiclename;
	}
	private String vehicledesc;
	private String vehicledesc_en;
	private FileItem fullimgfile;
	private String vehiclename_en;
	private String vehiclename;
	
	
}
