package com.hino.page;

import java.util.List;

import org.apache.click.element.Element;
import org.apache.click.element.JsImport;

public class Aboutus extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4936611915315267006L;
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new JsImport("./js/carservice.js"));
            //headElements.add(new CssImport("./css/booking.css"));
        }
        return headElements;
    }

}
