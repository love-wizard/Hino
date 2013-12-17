package com.hino.page;

import java.util.List;

import org.apache.click.util.Bindable;

import com.hino.click.extension.GroupDiv;
import com.hino.click.extension.Image;
import com.hino.click.extension.NavigationDiv;
import com.hino.click.extension.SiteListDiv;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.util.Info;
import com.hino.util.NavigationUtil;

public class RouteDetail extends BasePage {
	private static final long serialVersionUID = -421125561789927053L;

	@Bindable private String routeid; // GET request: reoute_detail.htm?routeid=...
	@Bindable private Image routeImg = new Image("route_image");
	@Bindable private String route_schedule;
	@Bindable private String route_service;
	@Bindable private String route_hint;
	@Bindable private SiteListDiv siteList;
	@Bindable private NavigationDiv navigationDiv;
	@Bindable private GroupDiv groupDiv;

	@Override
	public void onInit() {
		Route curRoute = getTravelResourceService().getRouteById(Long.parseLong(routeid));
		routeImg.setSrc(Info.EXTERNAL_PATH_PREFIX+curRoute.getImageUrl());
		route_schedule = (""+curRoute.getSchedule()).replace("\n","<br />");
		route_service = (""+curRoute.getService()).replace("\n","<br />");
		route_hint = (""+curRoute.getHint()).replace("\n","<br />");
		siteList = new SiteListDiv("route_site_list", curRoute.getSitelist(), false);
		
		// Update navigation link
		NavigationUtil.updateRouteLevel(this.getContext().getSession(), curRoute.getName(), Integer.parseInt(routeid));
		// Create navigation link
		navigationDiv = new NavigationDiv("navigation_link", this.getContext().getSession(), false);
		
		List<Group> groupList = getWebService().getGroupByRouteId(Integer.valueOf(routeid));
		groupDiv = new GroupDiv("group_list", groupList, false);
	}
}
