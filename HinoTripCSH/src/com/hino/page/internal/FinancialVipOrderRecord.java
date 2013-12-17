package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;
import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Checkbox;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;


import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.Transfer;
import com.hino.model.VipOrder;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class FinancialVipOrderRecord extends BasePage{
	@Bindable
	private Form search_form = new Form("sform");
	@Bindable
    private Table vip_table = new Table("vtable");
	
	private TextField keyword = new TextField("ktextfield", "Email查找");
    private Submit search = new Submit("search", "搜索");
    private Submit clear = new Submit("clear", "重置");
    
    private String keyword_str = null;
    private Integer[] status;
    private Checkbox statu0 = new Checkbox("statu0", "未确认");
    private Checkbox statu1 = new Checkbox("statu1", "已确认");
    private Checkbox statu2 = new Checkbox("statu2", "已发卡");
    private Checkbox statu3 = new Checkbox("statu3", "已确认未付款");
    
    public ActionLink deleteLink = new ActionLink("delete", "删除");
    public ActionLink confirmLink = new ActionLink("confirm", "确认");
    @Override
    public void onInit() {
    	confirmLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	ServiceResponse sr = getFinancialService().vip_order_confirm(confirmLink.getValueInteger());
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
    	
    	deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	ServiceResponse sr = getFinancialService().vip_order_delete(deleteLink.getValueInteger());
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
    	
    	
        search_form.add(keyword);
        search_form.add(statu0);
        search_form.add(statu1);
        search_form.add(statu2);
        search_form.add(statu3);
        search_form.add(search);
        search_form.add(clear);
        statu0.setChecked(true);
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
		        // Remove table and form state from the session
		        search_form.removeState(getContext());
				return true;
			}

		});
        
        vip_table.setClass(Table.CLASS_SIMPLE);
        vip_table.setPageSize(25);
	
        vip_table.setShowBanner(true);
		
        Column column = new Column("id", "ID");
		vip_table.addColumn(column);
		
		column = new Column("email", "绑定Email");
		vip_table.addColumn(column);
		
		column = new Column("cunstomer_name_id", "绑定客户");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((VipOrder) object).getCustomer().genFullName() + ((VipOrder) object).getCustomer().getChinesename() ;
			}
		});
		vip_table.addColumn(column);
		
		column = new Column("sales_info", "销售代表");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Staff s = ((VipOrder) object).getStaff();
				return s==null?"无":s.getChinesename() + "(" + s.genFullName() +")";
			}
		});
		vip_table.addColumn(column);
		
		column = new Column("order_method", "订购方式");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				
				return Info.BKM_STR_CN[((VipOrder) object).getOrder_method()];
			}
		});
		vip_table.addColumn(column);
		
		column = new Column("order_time", "订购时间");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				
				return TimeFormater.format2(((VipOrder)object).getOrder_time());
			}
		});
		vip_table.addColumn(column);
		
		column = new Column("order_status", "订单状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return Info.VS_STR_CN[((VipOrder) object).getStatus()];
			}
		});
		vip_table.addColumn(column);
		

		column = new Column("action", "操作");
		ActionLink[] links = {confirmLink, deleteLink}; 
		column.setDecorator(new LinkDecorator(vip_table, links, "id")); 
		vip_table.addColumn(column);
		
		search_form.restoreState(getContext());
    }
    
	public void onRender() {
		
		keyword_str = keyword.getValue();
		if(keyword_str.trim().equals(""))
		{
			keyword_str = null;
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
		
		status = new Integer[str_list.size()];
		for (int i=0;i<str_list.size();i++)
		{
			status[i]=str_list.get(i);
		}
		
		vip_table.setDataProvider(new PagingDataProvider<VipOrder>() {
			public List<VipOrder> getData() {
				int p = vip_table.getPageNumber();
				int count = vip_table.getPageSize();
				return getFinancialService().list_vip_order(keyword_str, status, p, count);
			}

			@Override
			public int size() {
				return (int)getFinancialService().count_list_vip_order(keyword_str, status);
			}
		});
	}

}
