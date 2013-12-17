package com.hino.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WebMenuRoute implements Comparable<WebMenuRoute> {
	private long id;
	private long priority;
	private Route route;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public long getPriority() {
		return priority;
	}

	@OneToOne(fetch = FetchType.EAGER)
	public Route getRoute() {
		return route;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	/**
	 * WebMenuRoute with lower priority will appear first 
	 */
	@Override
	public int compareTo(WebMenuRoute o) {
		return (int)(this.priority-o.getPriority());
	}
}
