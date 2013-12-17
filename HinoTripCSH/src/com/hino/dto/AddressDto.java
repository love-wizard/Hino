package com.hino.dto;

public class AddressDto {

	private String road;
	
	private String city;
	
	private String other;
	
	private String postcode;
	
	private boolean isPost;

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public boolean isPost() {
		return isPost;
	}

	public void setPost(boolean isPost) {
		this.isPost = isPost;
	}
	
	public String genAddress() {
		return road + ", " + city + ", " + other;
	}
}
