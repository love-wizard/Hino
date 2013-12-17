package com.hino.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "info")
public class Info {
	private int id;
	private String source;
	private String image;
	private String linkUrl;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public List<String> genFamousPlaces(){
		ArrayList<String> famousPlaces = new ArrayList<String>();
		if("FAMOUS_PLACE".equals(source)) {
			String ts = linkUrl.replace("$", "||");
			String[] fps = ts.split("\\|");
			for(String s : fps) {
				if("".equals(s.trim())){
					famousPlaces.add("$");
				}else{
					famousPlaces.add(s.trim());
				}
			}
		}
		
		return famousPlaces;
	}
}
