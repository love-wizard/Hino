package com.hino.page.zh;

import java.util.List;

import org.apache.click.Page;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.util.Bindable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.dao.DistinguishedGroupDAO;
import com.hino.model.DistinguishedGroup;
import com.hino.model.Group;
import com.hino.service.NoticeService;
import com.hino.service.TravelResourceService;
import com.hino.util.Info;

public class JingPing extends Page implements ApplicationContextAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ApplicationContext applicationContext;
	@Bindable
	private List<DistinguishedGroup> DistGroupList;
	@Bindable
	public String pathpre = Info.EXTERNAL_PATH_PREFIX;

	public JingPing() {
	}

	public void onInit() {
		DistGroupList = this.getTravelResourceService()
				.getPagedDistinguishedGroupList(0, 99);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		applicationContext = arg0;

	}

	@Override
	public List<Element> getHeadElements() {
		if (headElements == null) {
			headElements = super.getHeadElements();
			headElements.add(new CssImport("./css/jingping.css"));
		}
		return headElements;
	}

	protected TravelResourceService getTravelResourceService() {
		return (TravelResourceService) applicationContext
				.getBean("travelResourceServiceImpl");
	}
}
