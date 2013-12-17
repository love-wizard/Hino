package com.hino.page.internal;

import java.util.List;

import org.apache.click.Context;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Table;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.dao.BookingDAO;
import com.hino.dao.GroupDAO;
import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.VipOrder;
import com.hino.service.CustomerService;
import com.hino.util.PriviledgeParser;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class CardLetter extends Page implements ApplicationContextAware{
	@Bindable 
	private Integer vid;
	@Bindable 
	private String name;
	@Bindable 
	private String email;
	@Bindable
	private String phone;
	@Bindable
	private String address;
	
	private ApplicationContext applicationContext;
	

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
		
	}
	
	public void onRender()
	{

		if(vid!=null)
		{
			CustomerService cs = (CustomerService)applicationContext.getBean("customerServiceImpl");
			Staff s = (Staff)getContext().getSession().getAttribute("staff");
			if(s!=null&&PriviledgeParser.has_priviledge(s.getPriviledge(), PriviledgeParser.MARKETING))
			{
				VipOrder vo = cs.vip_issue_card(s.getId(), vid);
				name = vo.getReciever();
				email = vo.getEmail();
				phone = vo.getPhone();
				address = vo.getAddress();
			} else
			{
				name = "No Data";
				email = "No Data";
				phone = "No Data";
				address = "No Data";
			}
		}
		
		
		
	}
	
}