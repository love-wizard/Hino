package com.hino.page;


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
				title = b.genTicketTitle(false);
				name = b.getChinesename() + "(" + b.genFullname()+")";
				ref = b.getBookingRef();
				gender = b.genGenderStr();
				
				//Ken Chen 2013/02/13 TD#89 电子票按团的实际价格来处出
				//0=normal //1=groupon 2=seckill
				switch(b.getBook_type())
				{
					case 1:
						price = b.getGroup().getGroup_price() +"";
						break;
					case 2:
						price = b.getGroup().getSeckill_price() +"";
						break;
					default :
						price = b.getGroup().getPrice() +"";
				}
				
				email = b.getEmail();
				pickup = b.getPickup();
				phone = b.getPhone();
				room = b.getRoom();
			} else
			{
				title = "无效数据";
				name = "无效数据";
				ref = "无效数据";
				gender = "无效数据";
				price = "无效数据";
				email = "无效数据";
				pickup = "无效数据";
				phone = "无效数据";
				room = "无效数据";
			}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
		
	}
	
}
