package com.hino.page.zh;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.click.Page;
import org.apache.click.element.Element;
import org.apache.click.util.Bindable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.dto.AddressDto;
import com.hino.dto.BookingReserveDto;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.service.CustomerService;
import com.hino.service.TravelResourceService;
import com.hino.service.WebService;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class GroupsBookingMbl extends Page implements ApplicationContextAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ApplicationContext applicationContext;
	@Bindable private String routeid; // GET request: groups_booking.htm?routeid=...
	@Bindable private int gid;
	private int step;
	
	@Bindable
	public String pathpre = Info.EXTERNAL_PATH_PREFIX;

	public GroupsBookingMbl() {
	}

	public void onInit() {
				
		String s = getContext().getRequest().getParameter("step");
		if (null != s && !"".equals(s)) {
			step = Integer.valueOf(s);
		} else {
			step = 1;
		}
		addModel("step", step);
		Route curRoute = getTravelResourceService().getRouteById(Long.parseLong(routeid));
		
		if (step == 1) {
			List<Group> groupList = getWebService().getGroupByRouteIdOriginal(Integer.valueOf(routeid));
			
			addModel("groups", groupList);
			addModel("step", 2);
		} else if (step == 2) {
			
			Group targetGroup = getCustomerService().getGroupById(gid);
			// Get pickup information
			List<String> l = targetGroup.genPickupFull();
			List<String> pickups = new ArrayList<String>();
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
			Matcher m;  
			for (int i = 0; i < l.size(); i++)
			{
				m = p.matcher(l.get(i));  
				pickups.add(m.replaceAll(""));				
			}
			
			addModel("group", targetGroup);
			addModel("pickups", pickups);
			// Next step 3
			addModel("step", 3);
		} else if (step == 3) {
			String email = getContext().getRequest().getParameter("e");
			String name = getContext().getRequest().getParameter("n");
			String cname = getContext().getRequest().getParameter("cn");
			int gender = (null != getContext().getRequest().getParameter("g")? Integer.valueOf(getContext().getRequest().getParameter("g").trim()): 0) ;			
			String phone = getContext().getRequest().getParameter("p");
			String pickup = getContext().getRequest().getParameter("pus");
			int paymethod = (null != getContext().getRequest().getParameter("pm")? Integer.valueOf(getContext().getRequest().getParameter("pm").trim()): 0) ;
			
			if(null != email) email = email.trim();
			if(null != name) name = name.trim();
			if(null != cname) cname = cname.trim();
			if(null != phone) phone = phone.trim();
			if(null != pickup) pickup = pickup.trim();
			
			BookingReserveDto br = new BookingReserveDto();
			br.setFn(name);
			br.setLn("");
			br.setCn(cname);
			br.setEmail(email);
			br.setPickup(pickup);
			br.setPd_point(0);
			br.setPhone(phone);
			br.setGender(gender);
			br.setPaymethod(paymethod);
			
			long cid = -1;
			boolean okToBook = false;
			Group targetGroup = getCustomerService().getGroupById(gid);
			ServiceResponse sr = getCustomerService().getCustomerByEmail(email);
			if (sr.isSucc()) {
				Customer customer = (Customer) sr.getResponse();
				cid = customer.getId();
				if (customer.getPoint() > 0 && targetGroup.getGroup_price() == 0) {
					// applied point?
					addModel("customer", customer);
					addModel("br", br);
					addModel("step", 4);
				} else {
					okToBook = true;
				}
			} else {
				okToBook = true;
			}
			
			if (okToBook) {
				// directly start booking
				int bktype = 0;
				if (targetGroup.getGroup_price() > 0) {
					bktype = 1;
				}
				
				List<BookingReserveDto> result = new ArrayList<BookingReserveDto>();
				result.add(br);
				
				AddressDto address = null;
				sr = getCustomerService().external_booking(gid, cid, result, bktype, address);
				
				if (sr.isSucc()) {
					com.hino.model.Booking booking = (com.hino.model.Booking )sr.getResponse();
					
					if (paymethod == Info.BKM_EXTERNAL_GOOGLECHECKOUT) { // PayPal - 2
						addModel("booking", booking);
					} else if (paymethod == Info.BKM_EXTERNAL_BARCLAYCARD) { // 4
						addModel("booking", booking);
						addModel("newtopy", Math.round(booking.getPd_credit() * 100));
					}
					
					addModel("paymethod", paymethod);
					
					addModel("status", "1");
					addModel("message", "预定成功！订单号：" + booking.getBookingRef() + ".");
				} else {
					addModel("status", "0");
					addModel("message", "Sorry, 预定失败！" + EscapeHtml.nrTobr(sr.list_msg()));
				}
				addModel("step", 5);
			}
			
			addModel("group", targetGroup);
		} else if (step == 4) {
			String email = getContext().getRequest().getParameter("e");			
			String name = getContext().getRequest().getParameter("n");
			String cname = getContext().getRequest().getParameter("cn");
			int gender = (null != getContext().getRequest().getParameter("g")? Integer.valueOf(getContext().getRequest().getParameter("g").trim()): 0) ;
			String phone = getContext().getRequest().getParameter("p");
			String pickup = getContext().getRequest().getParameter("pus");
			int point = Integer.valueOf(getContext().getRequest().getParameter("pt"));
			long cid = Long.valueOf(getContext().getRequest().getParameter("cid"));			
			int paymethod = (null != getContext().getRequest().getParameter("pm")? Integer.valueOf(getContext().getRequest().getParameter("pm").trim()): 0) ;
			
			if(null != email) email = email.trim();
			if(null != name) name = name.trim();
			if(null != cname) cname = cname.trim();
			if(null != phone) phone = phone.trim();
			if(null != pickup) pickup = pickup.trim();
			
			BookingReserveDto br = new BookingReserveDto();
			br.setFn(name);
			br.setLn("");
			br.setCn(cname);
			br.setEmail(email);
			br.setPickup(pickup);
			br.setPd_point(point);
			br.setPhone(phone);
			br.setGender(gender);
			br.setPaymethod(paymethod);

			boolean okToBook = true;
			// directly start booking
			int bktype = 0;
			Group targetGroup = getCustomerService().getGroupById(gid);
			if (targetGroup.getGroup_price() > 0) {
				bktype = 1;
			} else {
				
				if (point > targetGroup.getMax_point()) {
					addModel("status", "0");
					addModel("message", "Sorry, 预定失败！积分使用超过最大值（" + targetGroup.getMax_point() + "）！");
					okToBook = false;
				}
			}
			
			if (okToBook) {
				List<BookingReserveDto> result = new ArrayList<BookingReserveDto>();
				result.add(br);
				
				AddressDto address = null;
				ServiceResponse sr = getCustomerService().external_booking(gid, cid, result, bktype, address);
				
				if (sr.isSucc()) {
					com.hino.model.Booking booking = (com.hino.model.Booking )sr.getResponse();
									
					if (paymethod == Info.BKM_EXTERNAL_GOOGLECHECKOUT) { // PayPal - 2
						addModel("booking", booking);
					} else if (paymethod == Info.BKM_EXTERNAL_BARCLAYCARD) { // 4
						addModel("booking", booking);
						addModel("newtopy", Math.round(booking.getPd_credit() * 100));
					}
					
					addModel("paymethod", paymethod);
					
					addModel("status", "1");
					addModel("message", "预定成功！订单号：" + booking.getBookingRef() + ".");
				} else {
					addModel("status", "0");
					addModel("message", "Sorry, 预定失败！" + EscapeHtml.nrTobr(sr.list_msg()));
				}
			}
			addModel("step", 5);
		} else {
			
			List<Group> groupList = getWebService().getGroupByRouteIdOriginal(Integer.valueOf(routeid));
			
			addModel("groups", groupList);
			addModel("step", 2);
		}
		
		addModel("route", curRoute);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;

	}

	@Override
	public List<Element> getHeadElements() {
		if (headElements == null) {
			headElements = super.getHeadElements();
		}
		return headElements;
	}

	protected TravelResourceService getTravelResourceService() {
		return (TravelResourceService) applicationContext
				.getBean("travelResourceServiceImpl");
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
