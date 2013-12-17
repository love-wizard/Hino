package com.hino.page.internal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.MenuCAMDto;
import com.hino.dto.VoucherCAMDto;
import com.hino.model.Voucher;
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GeneralSalesVoucherView extends BasePage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2867459591796165520L;
	
	@Bindable
	private Form search_form = new Form("sform");
	@Bindable
    private Table voucher_table = new Table("vtable");
    private TextField keyword = new TextField("ktextfield", "优惠券代码", true);
    private Submit search = new Submit("search", "搜索");
    private DateField expire_date = new DateField("expire_date", "新过期时间", 15);
    private Submit amend_date = new Submit("amend_date", "修改过期时间",this,"onAmendDateClick");
    private Voucher v;
    
    @Override
    public void onInit() {
        search_form.add(keyword);
        search_form.add(search);
		search_form.add(amend_date);
		search_form.add(expire_date);
        search.setActionListener(new ActionListener() {

			@Override
			public boolean onAction(Control source) {
				search_form.saveState(getContext());
				return true;
			}
		});
        
       
        voucher_table.setClass(Table.CLASS_SIMPLE);

	
        voucher_table.setShowBanner(true); //table.setSortable(true);
		//Column column = new Column("id", "ID");
		//booking_table.addColumn(column); 
		
        Column column = new Column("id", "ID");
        voucher_table.addColumn(column);
        
        column = new Column("code", "CODE");
        voucher_table.addColumn(column);
        
        column = new Column("no_of_use", "可使用次数");
        voucher_table.addColumn(column);
        
        column = new Column("no_used", "已使用次数");
        voucher_table.addColumn(column);
        
        column = new Column("match_type", "Match Type");
        voucher_table.addColumn(column);
        
        column = new Column("value", "抵价");
        voucher_table.addColumn(column);
        
        column = new Column("expire", "过期时间"); 
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				v = ((Voucher)object);
				return TimeFormater.format2(v.getExpire_date());
			}
		});
		voucher_table.addColumn(column);

		search_form.restoreState(getContext());
    }
    
public void onRender() {
		
		voucher_table.setDataProvider(new DataProvider<Voucher>() {
			public List<Voucher> getData() {		
				return getSalesService().list_voucher(keyword.getValue(), null);
			}
		});
	}

public boolean onAmendDateClick() {
	if ( expire_date.getValue()!="" && keyword.getValue()!="" && expire_date.isValid()){
		v = getSalesService().get_voucher(0, keyword.getValue());
		Calendar expireDate = Calendar.getInstance();
		expireDate.setTime(expire_date.getDate());
		v.setExpire_date(expireDate);
		
		getSalesService().update_voucher(v);
		
		// Show javascript message window and redirect the page 
		getHeadElements().add(new JsScript("alert('板块操作成功')"));
		getHeadElements().add(
				new JsScript("window.location = './general_sales_voucher_view.htm'"));
	}
    return true;
}

}

