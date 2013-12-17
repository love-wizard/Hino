package com.hino.page.zh;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.control.Label;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Div;
import com.hino.click.extension.Image;
import com.hino.click.extension.Link;
import com.hino.model.WebMenu;
import com.hino.model.WebMenuRoute;
import com.hino.util.Info;
import com.hino.util.NavigationUtil;

public class Classic extends BasePageOther{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9008172599989526194L;
	@Bindable Div divRegionList = new Div("region_list_div");
	@Bindable protected int page_type=2;

	@Bindable private Integer rid;
	@Override
	public void onInit() {
		qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);
		
		if (rid==null)
		{
			rid=1;
		}
		
		
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
	
	@Override
    public void onRender() {
		List<WebMenu> regionList = getWebService().getAllMenu();
		
		List<WebMenu> menulist= new ArrayList<WebMenu>();
		for(WebMenu wb: regionList)
		{
			if(wb.getId()!=-1)
			{
				menulist.add(wb);
			}
		}
        addModel("menulist", menulist);
        
        WebMenu wm = getWebService().getMenuById(rid);
        
        //Devon King - 2012/09/17 - TD#12 使用HQL获取排好序的路线信息
        //List<WebMenuRoute> wmrlist = wm.getMenu_routes();
        List<WebMenuRoute> wmrlist = getWebService().getOrderedMenuRoutes(wm.getId());
        
        addModel("wm", wm);
        
        addModel("wmrlist", wmrlist);
        //List<Route> rlist = 
        //getFormat().setEmptyString("&nbsp;");
    }
	
    @Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./images/line.css"));
        }
        return headElements;
    }
}
