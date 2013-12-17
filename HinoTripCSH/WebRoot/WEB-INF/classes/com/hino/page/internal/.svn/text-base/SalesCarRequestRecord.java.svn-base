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

import com.hino.model.CarRequest;
import com.hino.model.Staff;
import com.hino.model.VipOrder;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class SalesCarRequestRecord extends BasePage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Bindable
	private Form search_form = new Form("sform");
	@Bindable
    private Table r_table = new Table("vtable");
	
    private Submit search = new Submit("search", "搜索");
    private Submit clear = new Submit("clear", "重置");
    
    private String keyword_str = null;
    private Integer[] status;
    private Checkbox statu0 = new Checkbox("statu0", "未处理");
    private Checkbox statu1 = new Checkbox("statu1", "已处理");
    
    public ActionLink confirmLink = new ActionLink("confirm", "设为已处理");
    @Override
    public void onInit() {
    	confirmLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	getSalesService().setCarRequestRead(confirmLink.getValueInteger());
            	getHeadElements().add(new JsScript("alert('操作执行成功')"));
                return true;
            }
        });
    	
    	
    	

        search_form.add(statu0);
        search_form.add(statu1);

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
        
        r_table.setClass(Table.CLASS_SIMPLE);
        r_table.setPageSize(25);
	
        r_table.setShowBanner(true);
		
        Column column = new Column("id", "ID");
        r_table.addColumn(column);
		
		column = new Column("email", "Email");
		r_table.addColumn(column);
		
		column = new Column("name", "申请人");
		r_table.addColumn(column);
		
		column = new Column("phone", "联系电话");
		r_table.addColumn(column);
		
		column = new Column("car_type", "车型");
		r_table.addColumn(column);
		
		column = new Column("no_of_days", "天数");
		r_table.addColumn(column);
		
		column = new Column("no_of_persons", "人数");
		r_table.addColumn(column);
		
		column = new Column("date_time", "订车时间");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				
				return TimeFormater.format2(((CarRequest)object).getDate());
			}
		});
		r_table.addColumn(column);
		
		column = new Column("detail", "其他要求");
		r_table.addColumn(column);
		
		column = new Column("status", "状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return (0==((CarRequest) object).getStatus())?"未处理":"已处理";
			}
		});
		r_table.addColumn(column);
		
		column = new Column("action", "操作");
		ActionLink[] links = {confirmLink}; 
		column.setDecorator(new LinkDecorator(r_table, links, "id")); 
		r_table.addColumn(column);
		
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
			
		status = new Integer[str_list.size()];
		for (int i=0;i<str_list.size();i++)
		{
			status[i]=str_list.get(i);
		}
		
		r_table.setDataProvider(new PagingDataProvider<CarRequest>() {
			public List<CarRequest> getData() {
				int p = r_table.getPageNumber();
				int count = r_table.getPageSize();
				return getSalesService().list_carrequest(p, count, status);
			}

			@Override
			public int size() {
				return (int)getSalesService().countCarRequest(status);
			}
		});
	}

}
