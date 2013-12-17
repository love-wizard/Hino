package com.hino.page.internal;


import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

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
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.CheckList;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.Transfer;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class FinancialBookingRecord extends BasePage{
	@Bindable
	private Form search_form = new Form("sform");
	@Bindable
    private Table booking_table = new Table("btable");
    private Select key_select = new Select("kselect", "关键词类别");
    private TextField keyword = new TextField("ktextfield", "关键词搜索");
    private Submit search = new Submit("search", "搜索");
    private Submit clear = new Submit("clear", "重置");
    
    @Bindable private String newcredit;
    
    
    private String keyword_str = "";
    private int keytype = 11;
    private Integer[] status;
    private Checkbox statu0 = new Checkbox("statu0", "未确认");
    private Checkbox statu1 = new Checkbox("statu1", "付款中");
    private Checkbox statu2 = new Checkbox("statu2", "已确认");
    private Checkbox statu3 = new Checkbox("statu3", "已取消");
    public ActionLink deleteLink = new ActionLink("delete", "删除");
    public ActionLink confirmLink = new ActionLink("confirm", "确认");
	public ActionLink detailLink = new ActionLink("booking", "详细");
	public ActionLink cancelLink = new ActionLink("cancel", "取消");
	public ActionLink modifyLink = new ActionLink("modify", "改价");
    @Override
    public void onInit() {
    	
    	 
    	confirmLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	//Ken Chen 2013/04/07 TD#129 秒杀确认只可以确认当天的
            	Booking b = null;
				ServiceResponse sr = null;
//				sr = getFinancialService().view_booking(confirmLink.getValueInteger());
//				if(sr.isSucc())
//				{
//					b = (Booking)sr.getResponse();
//	        		if(b.getBook_type()==Info.BOOKING_TYPE_SECKILL)
//	        		{
//	        			Calendar realCalendar = Calendar.getInstance();
//	        			//if(!TimeFormater.format2(b.getBooking_time()).equals(TimeFormater.format2(realCalendar)))
//	        			if(TimeFormater.format2(b.getBooking_time()).equals(TimeFormater.format2(realCalendar)))	
//	        			{
//	        				getHeadElements().add(new JsScript("return window.confirm('确定要删除吗?');"));
//	        				
//	        				
//	        				return true;
//	        			}
//	        		}
//				}
//				
            	sr = getFinancialService().confirm_booking(confirmLink.getValueInteger());
            	if(sr.isSucc())
            	{
            		getHeadElements().add(new JsScript("alert('操作执行成功')"));
            	} else
            	{
            		getHeadElements().add(new JsScript("alert(\"" +EscapeHtml.nrTonnrr(sr.list_msg())+ "\")"));
            	}
                return true;
            }
        });
    	
    	//Ken Chen 2012/11/08 修改为 click 的方法
    	deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
    	deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	 //Kevin Zhong - Add a confirmation dialog for delete booking
            	 //int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION);
            	 //if (n == JOptionPane.YES_OPTION){
	            	ServiceResponse sr = getFinancialService().delete_booking(deleteLink.getValueInteger());
	            	if(sr.isSucc())
	            	{
	            		getHeadElements().add(new JsScript("alert('操作执行成功')"));
	            	} else
	            	{
	            		getHeadElements().add(new JsScript("alert(\"" +EscapeHtml.nrTonnrr(sr.list_msg())+ "\")"));
	            	}
            	 //}
                return true;
            }
        });
    	
    	cancelLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	ServiceResponse sr = getFinancialService().cancel_booking(cancelLink.getValueInteger());
            	if(sr.isSucc())
            	{
            		getHeadElements().add(new JsScript("alert('操作执行成功')"));
            	} else
            	{
            		getHeadElements().add(new JsScript("alert(\"" +EscapeHtml.nrTonnrr(sr.list_msg())+ "\")"));
            	}
                return true;
            }
        });
    	
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
					bookingHash.put("voucher", b.getVoucher());
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
    	
    	 modifyLink.setAttribute("class", "ajaxCredit");
         modifyLink.addBehavior(new DefaultAjaxBehavior() {
 			@Override
 			public ActionResult onAction(Control source) {
 				String jsonResult = "";
 				double newCredit;
 				try {
 					newCredit = Double.parseDouble(newcredit);
 					
 					// Update credit of chosen customer
 					ServiceResponse sr = getFinancialService().modify_booking_price(modifyLink.getValueLong(), newCredit, (Staff)getContext().getSession().getAttribute("staff"));
 					if(sr.isSucc())
 					{
 						jsonResult = "操作执行成功";
 					} else
 					{
 						jsonResult = "操作执行失败";
 					}
 					
 				}
 				catch (NumberFormatException e) {
 					jsonResult = "数字不合法";
 				}
 				
 				jsonResult = "{\"result\": \""+jsonResult+"\"}";
 				return new ActionResult(jsonResult, ActionResult.JSON);
 			}
 		});

        key_select.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				
				optionList.add(new Option(0, "订单号"));
				optionList.add(new Option(1, "团ID"));
				optionList.add(new Option(2, "团名称"));
				optionList.add(new Option(3, "客户Email"));
				optionList.add(new Option(4, "客户中文名"));
				optionList.add(new Option(5, "客户姓名(PinYin)"));
				optionList.add(new Option(6, "客户电话"));
				optionList.add(new Option(10, "员工工号"));
				optionList.add(new Option(11, "所有订单"));
				return optionList;
			}
		});
        
        key_select.setValue("11");
        
        search_form.add(keyword);
        search_form.add(key_select);
        search_form.add(statu0);
        search_form.add(statu1);
        search_form.add(statu2);
        search_form.add(statu3);
        search_form.add(search);
        search_form.add(clear);
        
        search.setActionListener(new ActionListener() {

			@Override
			public boolean onAction(Control source) {
				search_form.saveState(getContext());
				return true;
			}
		});
        
        clear.setActionListener(new ActionListener() {

			@Override
			public boolean onAction(Control source) {
				search_form.clearErrors();
		        search_form.clearValues();
		        key_select.setValue("11");
		        // Remove table and form state from the session
		        search_form.removeState(getContext());
				return true;
			}

		});
        
        booking_table.setClass(Table.CLASS_SIMPLE);
        booking_table.setPageSize(20);
	
        booking_table.setShowBanner(true); //table.setSortable(true);
		//Column column = new Column("id", "ID");
		//booking_table.addColumn(column); 
		
        Column column = new Column("bookingRef", "订单号");
		booking_table.addColumn(column);
		
		column = new Column("book_type", "类型0普1团2秒");
		booking_table.addColumn(column);

		column = new Column("sales_info", "销售代表");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genSalesStr();
			}
		});
		booking_table.addColumn(column);
		
		//Ken Chen 2013/05/13 TD#142 add 出团日期
		column = new Column("depart_date", "出团日期");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Booking)object).getGroup();
				return TimeFormater.format2(g.getDepart_date());
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("group_name_id", "团名称(ID)");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Booking)object).getGroup();
				return g.getName()+"("+g.getId()+")";
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("cunstomer_name_id", "客户姓名(English)");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).getChinesename()+"("+((Booking) object).genFullname()+")";
			}
		});
		booking_table.addColumn(column);
				
		column = new Column("cunstomer_isVip", "客户是否VIP");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Customer c = ((Booking) object).getCustomer();
				if(c==null)
				{
					return "否";
				} else
				{
					return c.genIsVip()?"是":"否";
				}
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("pd_credit", "现金");
		booking_table.addColumn(column);
		
		column = new Column("pd_point", "积分");
		booking_table.addColumn(column);
		column = new Column("pd_voucher", "优惠券");
		booking_table.addColumn(column);
		column = new Column("total_credit", "共计");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking)object).genTotalPaid() + "";
			}
		});
		booking_table.addColumn(column);
	
		column = new Column("org_credit", "标准价");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Booking)object).getGroup();
				return g.getPrice()+"";
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("vip_credit", "会员价");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Booking)object).getGroup();
				return g.getVip_price()+"";
			}
		});
		booking_table.addColumn(column);
				
		column = new Column("payment_info", "支付方式");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genBookingMethodStr();
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("transfer_info", "转账申报");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Transfer t = (Transfer)((Booking) object).getTransfer();
				return (t==null)?"无":t.getId()+"";
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("booking_status", "订单状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genStatusStr();
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("refund_status", "退款需求");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genRefundStr();
			}
		});
		booking_table.addColumn(column);

		column = new Column("action", "操作");
		ActionLink[] links = {detailLink, confirmLink, cancelLink,deleteLink, modifyLink}; 
		column.setDecorator(new LinkDecorator(booking_table, links, "id")); 
		booking_table.addColumn(column);
		
		search_form.restoreState(getContext());
    }
    
	public void onRender() {
		
		keyword_str = keyword.getValue();
		keytype = Integer.parseInt(key_select.getValue());
		
		List<Integer> str_list = new ArrayList<Integer>();
		if(statu0.isChecked())
		{
			str_list.add(0);
		}
		
		if(statu1.isChecked())
		{
			str_list.add(1);
		}
		
		if(statu2.isChecked())
		{
			str_list.add(2);
		}
		if(statu3.isChecked())
		{
			str_list.add(3);
		}
		status = new Integer[str_list.size()];
		for (int i=0;i<str_list.size();i++)
		{
			status[i]=str_list.get(i);
		}
		
		booking_table.setDataProvider(new PagingDataProvider<Booking>() {
			public List<Booking> getData() {
				int p = booking_table.getPageNumber();
				int count = booking_table.getPageSize();
				return getFinancialService().bookingSearch(keytype, keyword_str, -1, status, count, p);
			}

			@Override
			public int size() {
				return getFinancialService().bookingSearchCount(keytype, keyword_str, -1, status);
			}
		});
	}


}
