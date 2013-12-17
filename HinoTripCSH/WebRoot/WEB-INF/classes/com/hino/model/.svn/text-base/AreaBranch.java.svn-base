package com.hino.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AreaBranch {
	private long id;
	private String name;
	private String description;
	private Staff leader;
	private List<Staff> follows;

	public String getDescription() {
		return description;
	}

	@OneToMany(fetch=FetchType.EAGER)
	public List<Staff> getFollows() {
		return follows;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@OneToOne(fetch=FetchType.EAGER)
	public Staff getLeader() {
		return leader;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFollows(List<Staff> follows) {
		this.follows = follows;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLeader(Staff leader) {
		this.leader = leader;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addFollows(Staff s)
	{
		follows.add(s);
	}
	
	public boolean hasFollows(Staff s)
	{
		return follows.contains(s);
	}
	
	public void removeFollows(Staff s)
	{
		follows.remove(s);
	}

}
