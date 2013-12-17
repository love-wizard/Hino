package com.hino.page.internal;


import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class SalesBookingRecord extends BasePage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3715625717652237900L;
	@Bindable
	private Form search_form = new Form("sform");
	@Bindable
    private Table booking_table = new Table("btable");
    private Select key_select = new Select("kselect", "关键词类别");
    private TextField keyword = new TextField("ktextfield", "关键词搜索");
    private Submit search = new Submit("search", "搜索");
    private Submit clear = new Submit("clear", "重置");
    //private CheckList status_list = new CheckList("status", "预订状态");
    private Checkbox statu0 = new Checkbox("statu0", "未确认");
    private Checkbox statu1 = new Checkbox("statu1", "付款中");
    private Checkbox statu2 = new Checkbox("statu2", "已确认");
    private Checkbox statu3 = new Checkbox("statu3", "已取消");
    private String keyword_str = "";
    private int keytype = -1;
    private Integer[] status;// = new Integer[0];
    
    public ActionLink deleteLink = new ActionLink("delete", "删除预订");
	public ActionLink ticketLink = new ActionLink("ticket", "电子票");
	public ActionLink detailLink = new ActionLink("booking", "详细信息");
	
	private Staff s;
	
	public SalesBookingRecord()
	{
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	ServiceResponse sr = getSalesService().delete_booking(deleteLink.getValueInteger(), (Staff)getContext().getSession().getAttribute("staff"));
            	if(sr.isSucc())
				{
            		getHeadElements().add(new JsScript("alert('操作执行成功!')"));
				} else
				{
					getHeadElements().add(new JsScript("alert(\"" +EscapeHtml.nrTonnrr(sr.list_msg())+ "\")"));
				}
                return true;
            }
        });
    	
    	ticketLink.setAttribute("class", "ajaxTicket");
    	ticketLink.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {
				
				ServiceResponse sr = getSalesService().issueTicket(Long.parseLong(ticketLink.getValue()),(Staff)getContext().getSession().getAttribute("staff"));
				
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
    	
    	detailLink.setAttribute("class", "ajaxDetail");
    	detailLink.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {
				Booking b = null;
				ServiceResponse sr = getSalesService().view_booking(Long.parseLong(detailLink.getValue()),(Staff)getContext().getSession().getAttribute("staff"));
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
				return optionList;
			}
		});
        
        key_select.setValueObject(0);
        
        search_form.add(keyword);
        search_form.add(key_select);
        //search_form.add(status_list);
        search_form.add(statu0);
        search_form.add(statu1);
        search_form.add(statu2);
        search_form.add(statu3);
        search_form.add(search);
        search_form.add(clear);
        
        statu0.setChecked(true);
        statu1.setChecked(true);
        statu2.setChecked(true);
        statu3.setChecked(true);
        
        booking_table.setClass(Table.CLASS_SIMPLE);
        booking_table.setPageSize(20);
        booking_table.setShowBanner(true); 
		
        Column column = new Column("bookingRef", "订单号");
		booking_table.addColumn(column);
		
		column = new Column("book_type", "类型0普1团2秒");
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
		
		column = new Column("pd_credit", "实际售价");
		booking_table.addColumn(column);
		
		column = new Column("pd_point", "积分使用");
		booking_table.addColumn(column);
		
		column = new Column("pd_voucher", "优惠券使用");
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
		
		column = new Column("booking_status", "订单状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genStatusStr();
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("action", "操作");
		ActionLink[] links = {detailLink, ticketLink ,deleteLink }; 
		column.setDecorator(new LinkDecorator(booking_table, links, "id")); 
		booking_table.addColumn(column);
		
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
		        key_select.setValueObject(0);
		        statu0.setChecked(true);
		        statu1.setChecked(true);
		        statu2.setChecked(true);
		        statu3.setChecked(true);
		        search_form.removeState(getContext());
				return true;
			}

		});
	}
	
    @Override
    public void onInit() {
		search_form.restoreState(getContext());
    }
    
    public void onRender()
    {
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
		s = (Staff)getContext().getSession().getAttribute("staff");
		if(s==null)
		{
			setRedirect(Index.class);
		} else
		{
			booking_table.setDataProvider(new PagingDataProvider<Booking>() {
				public List<Booking> getData() {
				int p = booking_table.getPageNumber();
				int count = booking_table.getPageSize();
				return getSalesService().bookingSearch(keytype, keyword_str,s.getId() , status, count, p);
				}
		
		   		@Override 
		   		public int size() {    			
		   			return getSalesService().bookingSearchCount(keytype, keyword_str, s.getId(), status);
		   		}
	   		});
		}
		
    }


}
