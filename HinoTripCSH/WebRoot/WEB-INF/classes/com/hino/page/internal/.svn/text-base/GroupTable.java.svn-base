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
import com.hino.util.PriviledgeParser;
import com.hino.util.TimeFormater;

public class GroupTable extends Page implements ApplicationContextAware{
	@Bindable 
	private Integer groupid;
	
	@Bindable

	private String tables = "";
	
	private ApplicationContext applicationContext;
	


	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
		
	}
	
	public void onRender()
	{
		Group g = null;
		if(groupid!=null)
		{
			GroupDAO gd = (GroupDAO)applicationContext.getBean("groupDaoImpl");
			g = gd.viewGroupById(groupid);
		}
		Staff s = (Staff)getContext().getSession().getAttribute("staff");
		if(g!=null&&s!=null)
		{
			boolean write = false;
			if(PriviledgeParser.has_priviledge(s.getPriviledge(), PriviledgeParser.GUIDE_MANAGE)||PriviledgeParser.has_priviledge(s.getPriviledge(), PriviledgeParser.SALES_MANAGE))
			{
				write = true;
			} else if(g.getGuide1()!=null&&g.getGuide1().getId()==s.getId())
			{
				write = true;
			} else if(g.getGuide2()!=null&&g.getGuide2().getId()==s.getId())
			{
				write = true;
				
				
			} else 
			{
				write =false;
				
			}
			
			if(write)
			{
				tables = tables + "<h2>"+g.getName() + " " + TimeFormater.format2(g.getDepart_date())+"接车表</h2>\n" + "<table border='2' width='1000'>\n" + "<tr><th>序号</th><th>姓名</th><th>中文</th><th>性别</th><th>电话</th><th>接车点</th><th>住宿安排</th></tr>\n";
				
				BookingDAO bd = (BookingDAO)applicationContext.getBean("bookingDaoImpl");
				List<Booking> ld = bd.findBookingForGroupTable(groupid);
				int i=0;
				for(Booking b : ld)
				{
					tables = tables +  "<tr><td>"+ ++i + "&nbsp;</td><td>"+b.genFullname()+"</td><td>"+b.getChinesename()+"</td><td>"+b.genGenderStr()+"</td><td>"+b.getPhone()+"</td><td>" +b.getPickup()+"</td><td>" +b.getRoom()+"</td></tr>";
				}
				tables = tables + "</table>\n";
				tables = tables + "<h2>Important to Remind</h2>\n"+g.getCaution() + "<br/>";
				tables = tables + "<h2>Transport and Hotel Information</h2>\n"+g.getTraffic_hotel_info() + "<br/>";
				
			} else
			{
				tables = "Unable to view!";
			}
			
		} else
		{
			tables = "Unable to view!";
		}
		
	}
	
}