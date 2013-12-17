package com.hino.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class GroupSearchInfo {

	private long id;
	
	private Group group;
	
	private int duration;
	
	private boolean hotTrip; // 超值热卖
	
	private boolean themeTrip; // 主题游

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.EAGER)
	public Group getGroup(){
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Column(nullable=false)
	@org.hibernate.annotations.Type(type="yes_no")
	public boolean isHotTrip() {
		return hotTrip;
	}

	public void setHotTrip(boolean hotTrip) {
		this.hotTrip = hotTrip;
	}

	@Column(nullable=false)
	@org.hibernate.annotations.Type(type="yes_no")
	public boolean isThemeTrip() {
		return themeTrip;
	}

	public void setThemeTrip(boolean themeTrip) {
		this.themeTrip = themeTrip;
	}
	
	
}
