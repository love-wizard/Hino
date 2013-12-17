package com.hino.page.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Checkbox;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GroupFeedbackSingle extends BasePage{
	@Bindable
	private Integer gid;
	
	@Bindable
	private Table table = new Table ("feedbacktable");
	
	
	public void onInit()
	{
		table.setClass(Table.CLASS_SIMPLE);
		//table.getControlLink().setParameter("gid", gid);
		Column column = new Column("bookingRef", "订单编号");
		table.addColumn(column);
		
		column = new Column("fullname", "姓名PinYin");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking)object).genFullname();
			}
		});
		table.addColumn(column);
		
		column = new Column("chinesename", "中文名");
		table.addColumn(column);
		
		column = new Column("email", "Email");
		table.addColumn(column);
		
		column = new Column("phone", "电话");
		table.addColumn(column);
		
		column = new Column("gender", "性别");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genGenderStr();
			}
		});
		table.addColumn(column);
	
		column = new Column("a17", "1-7题答案");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genFeedback(0);
			}
		});
		table.addColumn(column);
		
		column = new Column("a8", "8题答案");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genFeedback(1);
			}
		});
		table.addColumn(column);
		
		column = new Column("a9", "9题答案");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genFeedback(2);
			}
		});
		table.addColumn(column);
	}
	
	public void onRender()
	{
		if(gid!=null)
		{
			Group g = this.getSalesService().viewGroupById(gid);
			if(g!=null)
			{
				
				table.setDataProvider(new DataProvider<Booking>() {
		        	
		            public List<Booking> getData() { 
		                return getSalesService().findGroupFeedbackbooking(gid);
		            }
		        }); 
			}
		}	
	}
}
