package com.hino.page.internal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.SalesRewardDto;
import com.hino.model.Group;
import com.hino.model.GroupProfit;
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class FinancialSalesReward extends BasePage {
	@Bindable
	private Form form = new Form("search_form");
	@Bindable
	private Table table = new Table("staff_table");
	
	private DateField start = new DateField("start", "开始日期", true);
	private DateField end = new DateField("end", "截至日期", true);
	
	private Submit calculate = new Submit("calculate", "生成工资表");
	
	public void onInit()
	{
		form.add(start);
		form.add(end);
		form.add(calculate);
		
		table.setClass(Table.CLASS_SIMPLE);
		
		Column column = new Column("staffno", "员工工号");
		table.addColumn(column); 
		
		column = new Column("fullname", "姓名");
		table.addColumn(column);
		
		column = new Column("rep_order", "销售订单数");
		table.addColumn(column);
		
		column = new Column("sales_total", "销售总额");
		table.addColumn(column);
	
		column = new Column("sales_reward", "销售提成");
		table.addColumn(column);
		
		/*
		column = new Column("group_status", "基本底薪");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Group) object).genStatusStr();
			}
		});
		table.addColumn(column);
		
		column = new Column("group_status", "总计薪资");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Group) object).genStatusStr();
			}
		});
		table.addColumn(column);
		*/
		
		
		calculate.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	Date s = start.getDate();
        		Date e = end.getDate();
        		if(s!=null&&e!=null)
        		{
        			final Calendar c1 = Calendar.getInstance();
        			final Calendar c2 = Calendar.getInstance();
        			
        			c1.setTime(s);
        			c2.setTime(e);

        			table.setDataProvider(new DataProvider<SalesRewardDto>() {
        				public List<SalesRewardDto> getData() {
        					return getFinancialService().sales_summary(c1, c2);
        				}

        			
        			});
        		}
            	
                return true;
            }
        });
		
	}
	
	
}
