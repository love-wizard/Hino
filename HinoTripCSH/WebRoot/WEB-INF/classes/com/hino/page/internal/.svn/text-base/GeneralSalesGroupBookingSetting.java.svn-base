package com.hino.page.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.*;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.dto.GroupCAMDto;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Booking;
import com.hino.model.Staff;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GeneralSalesGroupBookingSetting extends BasePage {
	@Bindable
	private Integer gid;
	
	@Bindable
	private Form form = new Form("bookingSettingForm");
	private Checkbox internalBooking = new Checkbox("internal", "内部预订可见");
	private Checkbox externalBooking = new Checkbox("external", "外部预订可见");
	private Checkbox internalbookable = new Checkbox("intbookable", "开启内部预订");
	private Checkbox externalbookable = new Checkbox("extbookable", "开启外部预订");
	private TextField tf = new TextField("exinfo", "外部预订提示");
	private TextField tf1 = new TextField("exinfo1", "外部预订提示(English)");
	private HiddenField hf = new HiddenField("groupid", Integer.class);
	
	private Submit update = new Submit("update", "更新");
	private Submit setbooking = new Submit("setbooking", "设为报名中");

	private Submit setProcessing = new Submit("setProcessing", "设为出团中");
	
	@Bindable
	private Table table = new Table ("reserveBookingTable");
	
	@Bindable
	private String groupinfo = "缺少信息";
	
	public ActionLink deleteLink = new ActionLink("delete", "删除订单");
	public ActionLink downloadticket = new ActionLink("downloadticket","电子票");
	public ActionLink detailLink = new ActionLink("booking", "详细");
	
	
	
	private Checkbox ext_groupon = new Checkbox("ext_groupon", "团购外部可预订");
	private Checkbox int_groupon = new Checkbox("int_groupon", "团购内部可预订");
	private Checkbox ext_seckill = new Checkbox("ext_seckill", "秒杀外部可预订");
	private Checkbox int_seckill = new Checkbox("int_seckill", "秒杀内部可预订");
	
	
	
	
	
	
	public void onInit()
	{
		detailLink.setAttribute("class", "ajaxDetail");
    	detailLink.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {
				Booking b = null;
				ServiceResponse sr = getFinancialService().view_booking(detailLink.getValueInteger());
				if(sr.isSucc())
				{
					b = (Booking)sr.getResponse();
					Map<String, String> bookingHash = new HashMap<String, String>();
					
					bookingHash.put("bookingRef", b.getBookingRef());
					if(b.getGroup()!=null)
					{
					bookingHash.put("groupname", b.getGroup().getName());
					bookingHash.put("departure", TimeFormater.format2(b.getGroup().getDepart_date()));
					}
					bookingHash.put("fullname", b.genFullname());
					bookingHash.put("chinesename", b.getChinesename());
					bookingHash.put("email", b.getEmail());
					bookingHash.put("phone", b.getPhone());
					bookingHash.put("gender", b.genGenderStr());
					if(b.getRep()!=null)
					{
						bookingHash.put("rep", b.getRep().getStaffno());
					}
					bookingHash.put("status", b.genStatusStr());
					
					bookingHash.put("pickup", b.getPickup());
					bookingHash.put("room", b.getRoom());
					bookingHash.put("comments", b.getComments());
					bookingHash.put("time", TimeFormater.format1(b.getBooking_time()));

					StringWriter writer = new StringWriter();
					  ObjectMapper mapper = new ObjectMapper();
					  try {
					   mapper.writeValue(writer, bookingHash);
					  } catch (JsonGenerationException e) {
					   e.printStackTrace();
					  } catch (JsonMappingException e) {
					   e.printStackTrace();
					  } catch (IOException e) {
					   e.printStackTrace();
					  }

					return new ActionResult(writer.toString(), ActionResult.JSON);
				} else
				{
					return new ActionResult("failed", ActionResult.JSON);
				}
				
				
			}
		});
		
		update.setActionListener(new ActionListener()
		{

			@Override
			public boolean onAction(Control source) {
				if(hf.getValueObject()!=null)
				{
					GroupCAMDto gdto = new GroupCAMDto();
					gdto.setId((Integer)(hf.getValueObject()));
					gdto.setExternalBookable(externalbookable.isChecked());
					gdto.setInternalBookable(internalbookable.isChecked());
					gdto.setExternalView(externalBooking.isChecked());
					gdto.setInternalView(internalBooking.isChecked());
					gdto.setExternal_indicator(tf.getValue());
					gdto.setExternal_indicator_en(tf1.getValue());
					gdto.setExt_groupon(ext_groupon.isChecked());
					gdto.setExt_seckill(ext_seckill.isChecked());
					gdto.setInt_groupon(int_groupon.isChecked());
					gdto.setInt_seckill(int_seckill.isChecked());
					getSalesService().change_booking_status(gdto);
					setRedirect(GeneralSalesGroupManage.class);
				}
				return true;
			}
			
		});
		
		setbooking.setActionListener(new ActionListener()
		{

			@Override
			public boolean onAction(Control source) {
				if(hf.getValueObject()!=null)
				{
					ServiceResponse sr = getSalesService().set_booking_stage((Integer)hf.getValueObject());
					
					if(sr.isSucc())
					{
						getHeadElements().add(new JsScript("alert('操作执行成功')"));
					} else
					{
						getHeadElements().add(new JsScript("alert(\""+EscapeHtml.nrTonnrr(sr.list_msg())+"\")"));
					}
            	} else
            	{
            		getHeadElements().add(new JsScript("alert('缺少数据-更改状态失败 ')"));
            	}
				getHeadElements().add(new JsScript("window.location = './general_sales_group_manage.htm'"));
				return true;
			}
			
		});
		
		setProcessing.setActionListener(new ActionListener()
		{
			@Override
			public boolean onAction(Control source) {
				if(hf.getValueObject()!=null)
				{
					ServiceResponse sr = getSalesService().set_process_stage((Integer)hf.getValueObject());
					
					if(sr.isSucc())
					{
						getHeadElements().add(new JsScript("alert('更改状态成功')"));
					} else
					{
						getHeadElements().add(new JsScript("alert(\""+EscapeHtml.nrTonnrr(sr.list_msg())+"\")"));
					}
            	} else
            	{
            		getHeadElements().add(new JsScript("alert('缺少数据-更改状态失败 ')"));
            	}
				getHeadElements().add(new JsScript("window.location = './general_sales_group_manage.htm'"));
				return true;
			}
			
		});
		
		
		deleteLink.setAttribute("class", "ajaxDelete");
		deleteLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 7586052196315020080L;

			@Override
            public boolean onAction(Control source) {
				ServiceResponse sr = getSalesService().delete_booking(deleteLink.getValueInteger(), (Staff)getContext().getSession().getAttribute("staff"));
            	if(sr.isSucc())
            	{
            		getHeadElements().add(new JsScript("alert('操作执行成功')"));
            	} else
            	{
            		getHeadElements().add(new JsScript("alert(\"" +EscapeHtml.nrTonnrr(sr.list_msg())+ "\")"));
            	}
                //setRedirect("./general_sales_group_booking.htm?gid="+(Integer)hf.getValueObject());
                return true;
            }
        });
		
		
		downloadticket.setAttribute("class", "ajaxTicket");
		downloadticket.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {
				
				ServiceResponse sr = getSalesService().issueTicket(Long.parseLong(downloadticket.getValue()),(Staff)getContext().getSession().getAttribute("staff"));
				
				if(sr.isSucc())
				{
					
					Map<String, String> bookingHash = new HashMap<String, String>();
					bookingHash.put("authcode", (String)sr.getResponse());
					
					StringWriter writer = new StringWriter();
					  ObjectMapper mapper = new ObjectMapper();
					  try {
					   mapper.writeValue(writer, bookingHash);
					  } catch (JsonGenerationException e) {
					   e.printStackTrace();
					  } catch (JsonMappingException e) {
					   e.printStackTrace();
					  } catch (IOException e) {
					   e.printStackTrace();
					  }

					return new ActionResult(writer.toString(), ActionResult.JSON);
				} else
				{
					return new ActionResult("1", ActionResult.HTML);
				}
			}
		}); 
		
		tf.setValue("火爆预订中");
		tf1.setValue("Openning");
		form.add(internalBooking);
		form.add(externalBooking);
		form.add(internalbookable);
		form.add(externalbookable);
		form.add(tf);
		form.add(tf1);
		form.add(hf);
		
		form.add(ext_groupon);
		form.add(int_groupon);
		form.add(ext_seckill);
		form.add(int_seckill);
		
		form.add(update);
		form.add(setbooking);
		form.add(setProcessing);
		
		table.setClass(Table.CLASS_SIMPLE);
		//table.getControlLink().setParameter("gid", gid);		
		Column column = new Column("no", "序号");
		column.setDecorator(new Decorator(){
			int no = 0;
			@Override
			public String render(Object object, Context context) {
				return "" + ++ no;
			}
		});
		table.addColumn(column); 
		
		column = new Column("id", "ID");
		table.addColumn(column); 
		
		column = new Column("bookingRef", "订单编号");
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
		
		column = new Column("pickup", "接车点");
		table.addColumn(column);
		
		column = new Column("room", "住宿安排");
		table.addColumn(column);
		
		column = new Column("phone", "电话");
		table.addColumn(column);
		
		column = new Column("sales_info", "销售代表");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genSalesStr();
			}
		});
		table.addColumn(column);
		
		column = new Column("gender", "性别");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genGenderStr();
			}
		});
		table.addColumn(column);
	
		column = new Column("booking_status", "订单状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genStatusStr();
			}
		});
		table.addColumn(column);
		
		column = new Column("action", "操作");
		
		ActionLink[] links = {detailLink, deleteLink, downloadticket}; 
		column.setDecorator(new LinkDecorator(table, links, "id")); 
		column.setSortable(false); 
		table.addColumn(column);
		
	}
	
	public void onRender()
	{
		if(gid!=null)
		{
			Group g = this.getSalesService().viewGroupById(gid);
			if(g!=null)
			{
				//System.out.println(g.getName());
				//System.out.println(g.getInternalView());
				//System.out.println(g.getExternalView());
				internalBooking.setChecked(g.getInternalView());
				externalBooking.setChecked(g.getExternalView());
				internalbookable.setChecked(g.getInternalBookable());
				externalbookable.setChecked(g.getExternalBookable());
				ext_groupon.setChecked(g.getExt_groupon());
				int_groupon.setChecked(g.getInt_groupon());
				ext_seckill.setChecked(g.getExt_seckill());
				int_seckill.setChecked(g.getInt_seckill());
				
				tf.setValue(g.getExternal_indicator());
				tf1.setValue(g.getExternal_indicator_en());
				hf.setValueObject(g.getId());
				//hf.setValue(""+g.getId());
				groupinfo = g.genBookingInfo();
				
				deleteLink.setParameter("gid", g.getId());
				table.setDataProvider(new DataProvider<Booking>() {
		        	
		            public List<Booking> getData() { 
		                return getSalesService().list_booking_by_group_orderby_bstatus(gid);
		            }
		        }); 
			}
		}
		
		
	}
}
