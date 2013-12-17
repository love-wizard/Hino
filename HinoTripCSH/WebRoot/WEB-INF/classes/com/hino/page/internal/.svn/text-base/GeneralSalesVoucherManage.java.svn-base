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
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Transfer;
import com.hino.model.Voucher;
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GeneralSalesVoucherManage extends BasePage{
	@Bindable
	private Form search_form = new Form("sform");
	@Bindable
    private Table voucher_table = new Table("vtable");
    private TextField keyword = new TextField("ktextfield", "关键词搜索");
    private Submit search = new Submit("search", "搜索");
    private Submit clear = new Submit("clear", "重置");
    
    private String keyword_str = "";
    private Integer[] status;
    private Checkbox statu0 = new Checkbox("statu0", "未确认");
    private Checkbox statu1 = new Checkbox("statu1", "付款中");
    private Checkbox statu2 = new Checkbox("statu2", "已确认");
    private Checkbox statu3 = new Checkbox("statu3", "已取消");
    public ActionLink deleteLink = new ActionLink("delete", "删除");

    @Override
    public void onInit() {

    	
    	deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	ServiceResponse sr = getFinancialService().delete_booking(deleteLink.getValueInteger());
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
        
        voucher_table.setClass(Table.CLASS_SIMPLE);
        voucher_table.setPageSize(20);
	
        voucher_table.setShowBanner(true); //table.setSortable(true);
		//Column column = new Column("id", "ID");
		//booking_table.addColumn(column); 
		
        Column column = new Column("id", "ID");
        voucher_table.addColumn(column);
        
        column = new Column("code", "CODE");
        voucher_table.addColumn(column);
        
        column = new Column("no_of_use", "使用次数");
        voucher_table.addColumn(column);
        
        column = new Column("match_type", "Match Type");
        voucher_table.addColumn(column);
        
        column = new Column("value", "抵价");
        voucher_table.addColumn(column);
        
        column = new Column("expire", "过期时间");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Voucher v = ((Voucher)object);
				return TimeFormater.format2(v.getExpire_date());
			}
		});
        
        
		
        /*
		column = new Column("group_name_id", "团名称(ID)");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Voucher v = ((Voucher)object);
				return ;
			}
		});
		booking_table.addColumn(column);
		*/

		column = new Column("action", "操作");
		ActionLink[] links ={deleteLink}; 
		column.setDecorator(new LinkDecorator(voucher_table, links, "id")); 
		voucher_table.addColumn(column);
		
		search_form.restoreState(getContext());
    }
    
	public void onRender() {
		
		keyword_str = keyword.getValue();
		
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
		
		voucher_table.setDataProvider(new PagingDataProvider<Voucher>() {
			public List<Voucher> getData() {
				int p = voucher_table.getPageNumber();
				int count = voucher_table.getPageSize();
				return null;
			}

			@Override
			public int size() {
				return 0;
			}
		});
	}
}
