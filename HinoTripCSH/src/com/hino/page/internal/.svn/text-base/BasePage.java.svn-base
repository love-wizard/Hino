package com.hino.page.internal;

import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.util.Bindable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.model.Staff;
import com.hino.service.AirlineService;
import com.hino.service.CustomerService;
import com.hino.service.FinancialService;
import com.hino.service.GuideService;
import com.hino.service.HumanResourceService;
import com.hino.service.NoticeService;
import com.hino.service.SalesService;
import com.hino.service.StrategyService;
import com.hino.service.TravelResourceService;
import com.hino.service.WebService;
import com.hino.util.PriviledgeParser;

public class BasePage extends Page implements ApplicationContextAware{
	private static final long serialVersionUID = 8431798952050290731L;

	protected ApplicationContext applicationContext;
	
	@Bindable private ActionLink logoutLink = 
		new ActionLink("logoutLink", "Logout", this, "logout");
	@Bindable private String curStaffName;
	
	// Switch menu items on/off
	@Bindable private String switchSalesRep = null;
	@Bindable private String switchAreaRep = null;
	@Bindable private String switchFinance = null;
	@Bindable private String switchMarketing = null;
	@Bindable private String switchHr = null;
	@Bindable private String switchGuide = null;
	@Bindable private String switchSalesManage = null;
	@Bindable private String switchGuideManage = null;
	@Bindable private String switchResource = null;
	@Bindable private String switchWebMaster = null;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		applicationContext = arg0;
		
	}
	
	public BasePage()
	{
		super();
		
		Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");
		// Session check will be done by Spring Security
		// If session is expired, go to login page again.
		if(curStaff == null) {
			setRedirect(Index.class);
			return;
		}
		
		// Get chinese name of the logged in Staff
		curStaffName = curStaff.getChinesename();
		if(curStaffName == null) {
			curStaffName = "Hino Staff";
		}
		
		// Switch menu items on/off
		String staffPriviledge = curStaff.getPriviledge();
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.SALES_REP)) switchSalesRep = "on";
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.AREA_REP)) switchAreaRep = "on";
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.FINIANCE)) switchFinance = "on";
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.MARKETING)) switchMarketing = "on";
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.HR)) switchHr = "on";
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.GUIDE)) switchGuide = "on";
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.SALES_MANAGE)) switchSalesManage = "on";
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.GUIDE_MANAGE)) switchGuideManage = "on";
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.RESOURCE)) switchResource = "on";
		if(PriviledgeParser.has_priviledge(staffPriviledge, PriviledgeParser.WEBMASTER)) switchWebMaster = "on";
		
		String icxt = getContext().getRequest().getContextPath();
        
        addModel("icxt", icxt);		
	}
	
	@Override
	public String getTemplate() {
		return "/internal/basepage.htm";
	}
	
	protected TravelResourceService getTravelResourceService()
	{
		return (TravelResourceService)applicationContext.getBean("travelResourceServiceImpl");
	}

	protected HumanResourceService getHumanResourceService()
	{
		return (HumanResourceService)applicationContext.getBean("humanResourceServiceImpl");
	}

	protected WebService getWebService()
	{
		return (WebService)applicationContext.getBean("webServiceImpl");
	}
	
	protected StrategyService getStrategyService()
	{
		return (StrategyService)applicationContext.getBean("strategyServiceImpl");
	}

	protected CustomerService getCustomerService()
	{
		return (CustomerService)applicationContext.getBean("customerServiceImpl");
	}
	
	protected SalesService getSalesService()
	{
		return (SalesService)applicationContext.getBean("salesServiceImpl");
	}

	protected GuideService getGuideService()
	{
		return (GuideService)applicationContext.getBean("guideServiceImpl");
	}
	
	protected FinancialService getFinancialService()
	{
		return (FinancialService)applicationContext.getBean("financialServiceImpl");
	}
	
	//Kevin ZHong - 26/09/2012 - Notice Board
	protected NoticeService getNoticeService()
	{
		return (NoticeService)applicationContext.getBean("noticeServiceImpl");
	}
	
	protected AirlineService getAirlineService()
	{
		return (AirlineService)applicationContext.getBean("airlineServiceImpl");
	}
	/**
	 * Clear all session objects to logout
	 * @return
	 */
	public boolean logout() {
		getContext().getSession().invalidate();
    	setRedirect(Index.class);
    	return true;
	}
}
