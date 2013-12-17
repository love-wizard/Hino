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

import com.hino.model.Airline;
import com.hino.model.AirlineBooking;
import com.hino.model.Airport;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.Transfer;
import com.hino.model.VipOrder;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class AirlineBookingRecord extends BasePage{
	@Bindable
	private Form search_form = new Form("sform");
	@Bindable
    private Table booking_table = new Table("btable");
    private Select key_select = new Select("kselect", "关键词类别");
    private TextField keyword = new TextField("ktextfield", "关键词搜索");
    private Submit search = new Submit("search", "搜索");
    private Submit clear = new Submit("clear", "重置");
    private Select select_payer_type = new Select("select_payer_type", "付款人");
    private Select select_payment_method = new Select("select_payer_type", "支付方式");
    
    @Bindable private String newcredit;
    
    
    private String keyword_str = "";
    private int keytype = 11;
    private int payer_type_id = -1;
    private int payment_method_id = -1;
    private int[] status;
    private Checkbox statu0 = new Checkbox("statu0", "未确认");
    private Checkbox statu1 = new Checkbox("statu1", "付款中");
    private Checkbox statu2 = new Checkbox("statu2", "已确认");
    private Checkbox statu3 = new Checkbox("statu3", "已取消");
    public ActionLink deleteLink = new ActionLink("delete", "删除");
    public ActionLink confirmLink = new ActionLink("confirm", "确认");
	public ActionLink detailLink = new ActionLink("booking", "详细");
	public ActionLink modifyLink = new ActionLink("modify", "改价");
    @Override
    public void onInit() {
    	
    	 
    	confirmLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
				ServiceResponse sr = null;
            	sr = getAirlineService().confirm_booking(confirmLink.getValueInteger());
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
    	
    	deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
    	deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
        	 
            	ServiceResponse sr = getAirlineService().delete_booking(deleteLink.getValueInteger());
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
				AirlineBooking b = null;
				Airline a = null;
				ServiceResponse sr = getAirlineService().view_booking(detailLink.getValueInteger(),(Staff)getContext().getSession().getAttribute("staff"));
				if(sr.isSucc())
				{
					b = (AirlineBooking)sr.getResponse();
					Map<String, String> bookingHash = new HashMap<String, String>();
					a = getAirlineService().getAirline(b.getAirline_id());
					bookingHash.put("airline", a.getDeparture().getAirport_name());
					bookingHash.put("bookingRef", b.getBookingRef());
					bookingHash.put("departure", TimeFormater.format2(b.getFlight_date()));
					bookingHash.put("chinesename", b.getChinese_name());
					bookingHash.put("email", b.getEmail());
					bookingHash.put("phone", b.getPhone());
					if(b.getRep()!=null)
					{
						bookingHash.put("rep", b.getRep().getStaffno());
					}
					bookingHash.put("status", b.genStatusStr());
					
					bookingHash.put("comments", b.getTransfer_apply_comments());
					bookingHash.put("time", TimeFormater.format1(b.getTransfer_apply_date()));

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
 					ServiceResponse sr = getAirlineService().modify_booking_price(modifyLink.getValueLong(), newCredit);
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
//				optionList.add(new Option(1, "团ID"));
//				optionList.add(new Option(2, "团名称"));
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
        
        select_payer_type.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				
				optionList.add(new Option(0, Info.BKP_STR_CN[0]));
				optionList.add(new Option(1, Info.BKP_STR_CN[1]));
				
				return optionList;
			}
		});
        
//        select_payer_type.setValue("0");
        
        select_payment_method.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				
				optionList.add(new Option(0, Info.ABKM_STR_CN[0]));
				optionList.add(new Option(1, Info.ABKM_STR_CN[1]));
				optionList.add(new Option(2, Info.ABKM_STR_CN[2]));
				optionList.add(new Option(3, Info.ABKM_STR_CN[3]));
				optionList.add(new Option(4, Info.ABKM_STR_CN[4]));
				optionList.add(new Option(5, Info.ABKM_STR_CN[5]));
				
				return optionList;
			}
		});
//        select_payment_method.setValue("0");
        
        search_form.add(keyword);
        search_form.add(key_select);
        search_form.add(select_payer_type);
        search_form.add(select_payment_method);
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
		
		column = new Column("flight_date", "航班出发日期");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return TimeFormater.format1(((AirlineBooking) object).getFlight_date());
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("cunstomer_name_id", "客户姓名(English)");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((AirlineBooking) object).getChinese_name();
			}
		});
		booking_table.addColumn(column);
		
		
		column = new Column("price", "票价");
		booking_table.addColumn(column);
		
		column = new Column("sales_info", "销售代表");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((AirlineBooking) object).genSalesStr();
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("booking_status", "订单状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((AirlineBooking) object).genStatusStr();
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("payer", "付款人");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return Info.BKP_STR_CN[((AirlineBooking) object).getPayer_type_id()];
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("payment_method", "付款方式");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return Info.ABKM_STR_CN[((AirlineBooking) object).getPayment_method_id()];
			}
		});
		booking_table.addColumn(column);

		column = new Column("action", "操作");
		ActionLink[] links = {detailLink, confirmLink, deleteLink}; 
		column.setDecorator(new LinkDecorator(booking_table, links, "id")); 
		booking_table.addColumn(column);
		
		search_form.restoreState(getContext());
    }
    
	public void onRender() {
		
		keyword_str = keyword.getValue();
		keytype = Integer.parseInt(key_select.getValue());
		if(select_payer_type.getValue()=="")
		{
			payer_type_id = -1;
		}
		else
		{
			System.out.println(select_payer_type.getValue());
			payer_type_id = Integer.parseInt(select_payer_type.getValue());
		}
		if(""==select_payment_method.getValue())
		{
			payment_method_id = -1;
		}
		else
		{
			payment_method_id = Integer.parseInt(select_payment_method.getValue());
		}
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
		status = new int[str_list.size()];
		for (int i=0;i<str_list.size();i++)
		{
			status[i]=str_list.get(i);
		}
		
		booking_table.setDataProvider(new PagingDataProvider<AirlineBooking>() {
			public List<AirlineBooking> getData() {
				int p = booking_table.getPageNumber();
				int count = booking_table.getPageSize();
				return getAirlineService().bookingSearch(keytype, keyword_str, payer_type_id, payment_method_id, -1, status, count, p);
			}

			@Override
			public int size() {
				return (int)getAirlineService().bookingSearchCount(keytype, keyword_str, payer_type_id, payment_method_id, -1, status);
			}
		});
	}


}
