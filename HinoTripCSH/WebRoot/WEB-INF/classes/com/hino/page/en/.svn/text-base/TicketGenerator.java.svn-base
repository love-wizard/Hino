package com.hino.page.en;


import org.apache.click.Page;
import org.apache.click.control.*;
import org.apache.click.util.Bindable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.dao.BookingDAO;
import com.hino.model.Booking;
import com.hino.service.SalesService;


public class TicketGenerator extends Page implements ApplicationContextAware{
	@Bindable 
	private String authcode;
	
	@Bindable
	private String error;
	
	
	@Bindable private String title;
	@Bindable private String name;
	@Bindable private String ref;
	@Bindable private String gender;
	@Bindable private String price;
	@Bindable private String email;
	@Bindable private String pickup;
	@Bindable private String phone;
	@Bindable private String room;
	
	private ApplicationContext applicationContext;
	
	public void onInit()
	{
		
			//SalesService ss = (SalesService)applicationContext.getBean("salesServiceImpl");
			BookingDAO bd = (BookingDAO)applicationContext.getBean("bookingDaoImpl");
			Booking b = bd.findBookingByAuth(authcode);
			if(b != null)
			{
				title = b.genTicketTitle(true);
				name = b.getChinesename() + "(" + b.genFullname()+")";
				ref = b.getBookingRef();
				gender = b.genGenderStr();
				price = b.getGroup().getPrice() +"";
				email = b.getEmail();
				pickup = b.getPickup();
				phone = b.getPhone();
				room = b.getRoom();
			} else
			{
				title = "Invalid Data";
				name = "Invalid Data";
				ref = "Invalid Data";
				gender = "Invalid Data";
				price = "Invalid Data";
				email = "Invalid Data";
				pickup = "Invalid Data";
				phone = "Invalid Data";
				room = "Invalid Data";
			}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
		
	}
	
}
