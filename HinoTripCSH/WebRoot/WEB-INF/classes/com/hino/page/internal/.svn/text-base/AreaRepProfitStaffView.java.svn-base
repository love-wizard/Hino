package com.hino.page.internal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.Column;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.DateField;
import org.apache.click.util.Bindable;

import com.hino.dto.SalesRewardDto;
import com.hino.model.Staff;

public class AreaRepProfitStaffView extends BasePage{
	@Bindable private Integer groupid;
	
	@Bindable
	private Table table = new Table("staff_table");
	Staff s;
	
	
	public void onInit()
	{
		table.setClass(Table.CLASS_SIMPLE);
		
		Column column = new Column("staffno", "员工工号");
		table.addColumn(column); 
		
		column = new Column("fullname", "姓名");
		table.addColumn(column);
		
		column = new Column("rep_order", "销售订单数");
		table.addColumn(column);
		
		column = new Column("sales_total", "销售总额");
		table.addColumn(column);
	
		
		
	}
	
	public void onRender()
	{
		s =(Staff)getContext().getSession().getAttribute("staff");
	
		if(s!=null&&groupid!=null)
		{
			table.setDataProvider(new DataProvider<SalesRewardDto>() {
				public List<SalesRewardDto> getData() {
					return getFinancialService().sales_summary_forarea(groupid, s.getAreaid());
				}
			});
		}
		
		
	}
}
