package com.hino.page.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
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
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.model.AirlineBooking;
import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.Transfer;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class SalesTransferRecord extends BasePage {
	@Bindable
	private Form search_form = new Form("sform");
	@Bindable
    private Table booking_table = new Table("ttable");
    private Submit search = new Submit("search", "搜索");
    private Submit clear = new Submit("clear", "重置");
    
    private Object keyword_obj;
    private int keytype = 5;
    private long payment_method_id = -1;
    private Integer[] status;
    private Checkbox statu0 = new Checkbox("statu0", "审核中");
    private Checkbox statu1 = new Checkbox("statu1", "已通过");
    private Checkbox statu2 = new Checkbox("statu2", "未通过");
    private Select select_payment_method = new Select("select_payer_type", "支付方式");
    
    public ActionLink deleteLink = new ActionLink("delete", "删除");
	public ActionLink detailLink = new ActionLink("booking", "详细");
    @Override
    public void onInit() {
    	
    	deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	ServiceResponse sr = getFinancialService().transfer_delete(deleteLink.getValueInteger(),(Staff)getContext().getSession().getAttribute("staff"));
            	if(sr.isSucc())
            	{
            		getHeadElements().add(new JsScript(EscapeHtml.nrTonnrr("alert('操作执行成功\n" + sr.list_msg()+ "')")));
            		
            	} else
            	{
            		getHeadElements().add(new JsScript(EscapeHtml.nrTonnrr("alert('操作执行失败\n" + sr.list_msg()+ "')")));
            	}
                return true;
            }
        });
    	
    	detailLink.setAttribute("class", "ajaxDetail");
    	detailLink.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {

				ServiceResponse sr = getFinancialService().transfer_booking_view(detailLink.getValueInteger(),(Staff)getContext().getSession().getAttribute("staff"));
				if(sr.isSucc())
				{
					List<Booking> bl = (List<Booking>)sr.getResponse();
					Map<String, String> bookingHash;
					Booking b;
					ArrayList<Map<String, String>> jsonobjarr = new ArrayList<Map<String, String>>();
					for(int i=0;i<bl.size();i++)
					{
						b = bl.get(i);
						bookingHash = new HashMap<String, String>();
						bookingHash.put("brf", b.getBookingRef());
						bookingHash.put("cn", b.getChinesename());
						bookingHash.put("pp",b.getPd_credit()+"");
						bookingHash.put("gp", b.getGroup().getPrice()+"");
						bookingHash.put("sts", b.genStatusStr());
						jsonobjarr.add(bookingHash);
					}
					
					StringWriter writer = new StringWriter();
					ObjectMapper mapper = new ObjectMapper();
					try {
						mapper.writeValue(writer, jsonobjarr);
						System.out.println(writer.toString());
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

        statu0.setChecked(true);
        statu1.setChecked(true);
        statu2.setChecked(true);
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
        
        search_form.add(select_payment_method);
        search_form.add(statu0);
        search_form.add(statu1);
        search_form.add(statu2);
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
		        statu0.setChecked(true);
		        statu1.setChecked(true);
		        statu2.setChecked(true);
		        // Remove table and form state from the session
		        search_form.removeState(getContext());
				return true;
			}

		});
        
        booking_table.setClass(Table.CLASS_SIMPLE);
        booking_table.setPageSize(20);
	
        booking_table.setShowBanner(true); 
		
        Column column = new Column("id", "申报ID");
		booking_table.addColumn(column);
		column = new Column("dec_time", "申报时间");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return TimeFormater.format2(((Transfer)object).getDec_time());
			}
		});
		
		column = new Column("p_amount", "申报金额");
		booking_table.addColumn(column);
		column = new Column("trans_method", "付款人");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				String pay_type_id = ((Transfer) object).getTrans_method();
				return Info.BKP_STR_CN[Integer.parseInt(pay_type_id)];
			}
		});
		booking_table.addColumn(column);
		

		column = new Column("payment_method", "付款方式");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				
				return Info.ABKM_STR_CN[(int) ((Transfer) object).getPayment_method_id()];
			}
		});
		booking_table.addColumn(column);

		
		column = new Column("trans_time", "转账时间");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return TimeFormater.format2(((Transfer)object).getTrans_time());
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("comment", "申报备注");
		booking_table.addColumn(column);
	
		column = new Column("feedback", "申报反馈");
		booking_table.addColumn(column);
		
		column = new Column("sales_info", "申报状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return Info.TS_STR_CN[((Transfer)object).getStatus()];
			}
		});
		booking_table.addColumn(column);
			
		column = new Column("action", "操作");
		ActionLink[] links = {detailLink, deleteLink}; 
		column.setDecorator(new LinkDecorator(booking_table, links, "id")); 
		booking_table.addColumn(column);
		
		search_form.restoreState(getContext());
    }
    
	public void onRender() {
		
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

		status = new Integer[str_list.size()];
		for (int i=0;i<str_list.size();i++)
		{
			status[i]=str_list.get(i);
		}
		Staff s = (Staff)this.getContext().getSession().getAttribute("staff");
		if(s==null)
		{
			keytype=-1;
		} else
		{
			keytype=2;
			keyword_obj = s.getId();
		}
		if("".equals(select_payment_method.getValue()))
		{
			payment_method_id =-1;
		}
		else
		{
			payment_method_id = Long.parseLong(select_payment_method.getValue());
		}
		
		booking_table.setDataProvider(new PagingDataProvider<Transfer>() {
			public List<Transfer> getData() {
				int p = booking_table.getPageNumber();
				int count = booking_table.getPageSize();
				return getFinancialService().transferSearch(keytype, keyword_obj, status, payment_method_id, p, count);
			}

			@Override
			public int size() {
				return getFinancialService().transferSearchCount(keytype, keyword_obj, status, payment_method_id);
			}
		});
	}
	

}
