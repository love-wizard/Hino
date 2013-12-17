package com.hino.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class WebMenu {
	private long id;
	private String name;
	private String description;
	private String imageUrl;
	
	private String name_en;
	private String description_en;
	private String imageUrl_en;
	
	public String getName_en() {
		return name_en;
	}

	public void setName_en(String nameEn) {
		name_en = nameEn;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String descriptionEn) {
		description_en = descriptionEn;
	}

	public String getImageUrl_en() {
		return imageUrl_en;
	}

	public void setImageUrl_en(String imageUrlEn) {
		imageUrl_en = imageUrlEn;
	}

	private List<WebMenuRoute> menu_routes;

	public String getDescription() {
		return description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@OneToMany(fetch = FetchType.EAGER)
	public List<WebMenuRoute> getMenu_routes() {
		return menu_routes;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setMenu_routes(List<WebMenuRoute> menuRoutes) {
		menu_routes = menuRoutes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
