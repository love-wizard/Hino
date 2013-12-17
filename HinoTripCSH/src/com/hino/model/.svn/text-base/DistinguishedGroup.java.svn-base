package com.hino.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class DistinguishedGroup {
	
	private long id;
	
	private String title;
	
	private String description;
	
	private double price;
	
	private Route route;
	
	private String image;
	
	private boolean visible;
	
	private List<ServiceOption> serviceOptions;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@OneToOne
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(nullable=false)
	@org.hibernate.annotations.Type(type="yes_no")
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

//	@OneToMany(fetch=FetchType.EAGER)  
//	@JoinTable(name = "distgroupoption", joinColumns = {@JoinColumn(name = "distinguished_group_id")},  
//	inverseJoinColumns = {@JoinColumn(name = "id")})  
//	@Fetch(FetchMode.SUBSELECT)  
//	public List<ServiceOption> getServiceOptions() {
//		return serviceOptions;
//	}
//
//	public void setServiceOptions(
//			List<ServiceOption> serviceOptions) {
//		this.serviceOptions = serviceOptions;
//	}
}
