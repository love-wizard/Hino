package com.hino.page.en;

import org.apache.click.util.Bindable;

import com.hino.click.extension.NavigationDiv;
import com.hino.click.extension.RouteListDiv;
import com.hino.model.WebMenu;
import com.hino.util.NavigationUtil;

public class RouteList extends BasePage {
	private static final long serialVersionUID = -421125561789927053L;

	@Bindable private int regid; // GET request: route_list.htm?regid=...
	@Bindable private String reg_name;
	@Bindable private String reg_desc;
	@Bindable private RouteListDiv routeList;
	@Bindable private NavigationDiv navigationDiv;
	
	@Override
	public void onInit() {
		WebMenu curRegion = getWebService().getMenuById(regid);
		reg_name = ""+curRegion.getName_en();
		reg_desc = (""+curRegion.getDescription_en()).replace("\n","<br />");
		
		// Create index site list panel
		routeList = new RouteListDiv("route_list_panel", curRegion.getMenu_routes(), true);
		
		// Update navigation link
		NavigationUtil.updateRegionLevel(this.getContext().getSession(), reg_name, regid);
		// Create navigation link
		navigationDiv = new NavigationDiv("navigation_link", this.getContext().getSession(), true);
		
	}

}
