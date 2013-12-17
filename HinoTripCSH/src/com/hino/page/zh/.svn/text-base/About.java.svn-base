package com.hino.page.zh;

import java.util.List;

import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.util.Bindable;

import com.hino.model.Recruitment;

public class About extends BasePageOther {
	private static final long serialVersionUID = -421125561789927051L;

	public About()
	{

	}
	
	public void onInit(){
		qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);
		
		List<Recruitment> recruitments = getHumanResourceService().getActiveRecruitments();
		addModel("recruitments", recruitments);
	}
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./images/about.css"));
            headElements.add(new JsImport("js/jquery.tools.min.js"));
        }
        return headElements;
    }

}
