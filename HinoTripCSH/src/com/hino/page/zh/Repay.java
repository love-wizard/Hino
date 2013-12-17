package com.hino.page.zh;

import java.util.List;

import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.util.Bindable;

public class Repay extends BasePageOther{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9008172599989526194L;

	@Bindable private String ref;
	
	@Override
	public void onInit() {
		qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);
	}
	
	@Override
    public void onRender() {
		
		if (ref == null)
		{
			ref = "";
		}
		
		com.hino.model.Booking booking = getCustomerService().view_booking(ref);
		
		if (booking == null) {
			addModel("status", 0);
		} else {
			addModel("status", 1);
			addModel("booking", booking);
		}
    }
	
    @Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./css/repay.css"));
        }
        return headElements;
    }
}
