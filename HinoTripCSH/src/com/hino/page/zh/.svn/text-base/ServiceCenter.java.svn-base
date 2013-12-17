package com.hino.page.zh;

import java.util.List;

import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.util.Bindable;

import com.hino.click.extension.GroupDiv;
import com.hino.click.extension.Image;
import com.hino.click.extension.NavigationDiv;
import com.hino.click.extension.SiteListDiv;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.model.WebMenuRoute;
import com.hino.util.Info;
import com.hino.util.NavigationUtil;

public class ServiceCenter extends BasePageOther {

	@Bindable private String css = "service_center.css";
	/**
	 * 
	 */
	private static final long serialVersionUID = 2293821318864576058L;
	@Bindable private int helpId; // GET request: reoute_detail.htm?routeid=...
	@Bindable private String help_name;
	@Bindable private String help_details;
	@Override
	public void onInit() {
		qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);		
	}
	
	
	@Override
    public void onRender() {
		help_name = Info.GetHelpNameById(helpId);
		help_details = Info.GetHelpDetailsById(helpId);
    }
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./images/service_center.css"));
        }
        return headElements;
    }
    
}
