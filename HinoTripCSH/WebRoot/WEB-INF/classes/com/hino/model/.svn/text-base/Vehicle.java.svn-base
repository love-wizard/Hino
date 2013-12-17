package com.hino.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle {
    private long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	private String name;
	private String name_en;
	
	private String desci;
	public String getDesci() {
		return desci;
	}
	public void setDesci(String desci) {
		this.desci = desci;
	}
	public String getDesci_en() {
		return desci_en;
	}
	public void setDesci_en(String desciEn) {
		desci_en = desciEn;
	}

	private String desci_en;
	
	private String img_path;
	private String img_path_en;
	
	
	public String getImg_path_en() {
		return img_path_en;
	}
	public void setImg_path_en(String imgPathEn) {
		img_path_en = imgPathEn;
	}

	private double price;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String nameEn) {
		name_en = nameEn;
	}
	
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String imgPath) {
		img_path = imgPath;
	}
	
}
