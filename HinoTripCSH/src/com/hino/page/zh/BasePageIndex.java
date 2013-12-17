package com.hino.page.zh;

import org.apache.click.Page;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.service.CustomerService;
import com.hino.service.TravelResourceService;
import com.hino.service.WebService;

public class BasePageIndex extends Page implements ApplicationContextAware{
	
	
	private static final long serialVersionUID = -2102122967024400020L;
	protected ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		applicationContext = arg0;
		
	}
	
	@Override
	public void onInit()
	{
		
	}
	
	
	protected TravelResourceService getTravelResourceService()
	{
		return (TravelResourceService)applicationContext.getBean("travelResourceServiceImpl");
	}

	protected WebService getWebService()
	{
		return (WebService)applicationContext.getBean("webServiceImpl");
	}

	protected CustomerService getCustomerService()
	{
		return (CustomerService)applicationContext.getBean("customerServiceImpl");
	}

}
