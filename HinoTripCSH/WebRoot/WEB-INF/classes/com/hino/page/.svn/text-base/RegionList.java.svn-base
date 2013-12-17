package com.hino.page;

import java.util.List;

import org.apache.click.control.Label;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Div;
import com.hino.click.extension.Image;
import com.hino.click.extension.Link;
import com.hino.model.WebMenu;
import com.hino.util.Info;
import com.hino.util.NavigationUtil;

public class RegionList extends BasePage {
	private static final long serialVersionUID = -421125561789927053L;
	
	@Bindable Div divRegionList = new Div("region_list_div");

	@Override
	public void onInit() {
		// Update navigation link
		NavigationUtil.updateTopLevel(this.getContext().getSession(), false, false);

		divRegionList.setCss("col_full");
		
		// Get WebMenu (except index) to form up all regions
		List<WebMenu> regionList = getWebService().getAllMenu();
        for(int index=0; index<regionList.size(); index++) {
        	WebMenu curRegion = regionList.get(index);
        	if(curRegion.getId() != -1) {
        		Link aRegion = new Link("aRegion"+index);
        		aRegion.setHref("route_list.htm?regid="+curRegion.getId());
        		Image imgRegion = new Image("imgRegion"+index);
        		imgRegion.setSrc(Info.EXTERNAL_PATH_PREFIX+curRegion.getImageUrl());
        		Label labelBr = new Label("br"+index, "<br />");
        		
        		/* HTML example:
        		 * 
        		 * <div class="col_full">
        		 * 	<a href="route_list.htm?regid=1"><img src="images/region_01.png" /></a>
        		 * 	<br />
        		 * 	<a href="route_list.htm?regid=2"><img src="images/region_02.png" /></a>
        		 * 	<br />
        		 * 	<a href="route_list.htm"><img src="images/region_european_union.png" /></a>
        		 * </div>
        		 */
        		aRegion.add(imgRegion);
        		divRegionList.add(aRegion);
        		divRegionList.add(labelBr);
        	}
        }
	}
}
