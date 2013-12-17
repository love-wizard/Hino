package com.hino.page.internal;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Checkbox;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.model.AreaBranch;
import com.hino.model.Group;
import com.hino.model.GroupProfit;
import com.hino.model.Site;
import com.hino.model.Staff;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class FinancialGroupProfitManage extends BasePage {
	public Table table = new Table("groupTable");
	public ActionLink settingLink = new ActionLink("setting", "清算设置");
	public ActionLink finishLink = new ActionLink("finish", "清算结束");
	
	public Form form = new Form("groupFilter");
	private Checkbox gstatu0 = new Checkbox("gstatu0", "计划中");
    private Checkbox gstatu1 = new Checkbox("gstatu1", "报名中");
    private Checkbox gstatu2 = new Checkbox("gstatu2", "出团中");
    private Checkbox gstatu3 = new Checkbox("gstatu3", "出团结束");
    private Checkbox gstatu4 = new Checkbox("gstatu4", "已取消");
	
    private Checkbox pnready = new Checkbox("pstatu0", "未清算");
    private Checkbox pready = new Checkbox("pstatu1", "已清算");
    
    private Integer[] gstatus;
    private Boolean[] pstatus;
    
	private Submit confirm = new Submit("submit", "筛选");
	private Submit clear = new Submit("clear", "重置筛选");
	public void onInit() {
		settingLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {     	
            	setRedirect("./financial_group_profit_setting_view.htm?gid="+settingLink.getValueInteger());
                return true;
            }
        });
		
		finishLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	if(getFinancialService().profit_finish_setting(finishLink.getValueInteger()))
            	{
            		getHeadElements().add(new JsScript("alert('操作执行成功')"));
            	} else
            	{
            		getHeadElements().add(new JsScript("alert('操作执行失败')"));
            	}
                return true;
            }
        });
		
		confirm.setActionListener(new ActionListener() {
			@Override
			public boolean onAction(Control source) {
				form.saveState(getContext());
				return true;
			}

		});
		
		clear.setActionListener(new ActionListener() {
			@Override
			public boolean onAction(Control source) {
				form.clearErrors();
		        form.clearValues();
		        // Clear table state
		        table.setPageNumber(0);
		        // Remove table and form state from the session
		        form.removeState(getContext());
				return true;
			}

		});
		form.add(gstatu0);
		form.add(gstatu1);
		form.add(gstatu2);
		form.add(gstatu3);
		form.add(gstatu4);
		
		gstatu1.setChecked(true);
		
		form.add(pnready);
		form.add(pready);
		
		pnready.setChecked(true);
		pready.setChecked(true);
		form.add(confirm);
		form.add(clear);
		
		table.setClass(Table.CLASS_SIMPLE);
		table.setPageSize(20);
		table.setShowBanner(true); //table.setSortable(true);
		
		Column column = new Column("id", "团ID");
		table.addColumn(column); 
		
		column = new Column("name", "团名称");
		table.addColumn(column);
		
		column = new Column("depart_date", "出发时间");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar c = ((Group) object).getDepart_date();
				if (c != null)
					return TimeFormater.format2(c);
				else
					return "未指定";
			}
		});
		table.addColumn(column);
		
		column = new Column("booking_status", "订单情况");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Group) object);
				return "已确认订单" + g.getSeats_taken();
			}
		});
		table.addColumn(column);
	
		column = new Column("group_status", "团状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Group) object).genStatusStr();
			}
		});
		table.addColumn(column);
		
		column = new Column("profit_status", "清算状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Group) object).genProfitStatus();
			}
		});
		table.addColumn(column);
		
		column = new Column("last_update", "最新设置时间");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				GroupProfit gp = ((Group) object).getProfit();
				if(gp==null)
				{
					return "尚未更新";
				} else
				{
					return TimeFormater.format1(gp.getLast_modify());
				}
			}
		});
		table.addColumn(column);
		
		column = new Column("total_income", "总收入");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				GroupProfit gp = ((Group) object).getProfit();
				if(gp==null)
				{
					return "尚未更新";
				} else
				{
					return gp.getTotal_in()+"";
				}
			}
		});
		table.addColumn(column);
		
		column = new Column("total_exp", "总支出");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				GroupProfit gp = ((Group) object).getProfit();
				if(gp==null)
				{
					return "尚未更新";
				} else
				{
					return gp.getTotal_exp()+"";
				}
			}
		});
		table.addColumn(column);
		
		column = new Column("total_profit", "总利润");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				GroupProfit gp = ((Group) object).getProfit();
				if(gp==null)
				{
					return "尚未更新";
				} else
				{
					return gp.getTotal_profit()+"";
				}
			}
		});
		table.addColumn(column);
		
		column = new Column("action", "操作");
		ActionLink[] links = {settingLink, finishLink}; 
		column.setDecorator(new LinkDecorator(table, links, "id")); 
		table.addColumn(column);
		form.restoreState(getContext());
		
	}
	
	public void onRender() {
		// Set data provider to populate the table row list from
		List<Integer> str_list = new ArrayList<Integer>();
		if(gstatu0.isChecked())
		{
			str_list.add(0);
		}
		
		if(gstatu1.isChecked())
		{
			str_list.add(1);
		}
		
		if(gstatu2.isChecked())
		{
			str_list.add(2);
		}
		
		if(gstatu3.isChecked())
		{
			str_list.add(3);
		}
		
		if(gstatu4.isChecked())
		{
			str_list.add(4);
		}

		gstatus = new Integer[str_list.size()];
		for (int i=0;i<str_list.size();i++)
		{
			gstatus[i]=str_list.get(i);
		}
		
		List<Boolean> str_list1 = new ArrayList<Boolean>();
		if(pready.isChecked())
		{
			str_list1.add(true);
		}
		
		if(pnready.isChecked())
		{
			str_list1.add(false);
		}
		
		pstatus = new Boolean[str_list1.size()];
		for (int i=0;i<str_list1.size();i++)
		{
			pstatus[i]=str_list1.get(i);
		}
		
		table.setDataProvider(new PagingDataProvider<Group>() {
			public List<Group> getData() {
				int p = table.getPageNumber();
				int count = table.getPageSize();
				return getFinancialService().profit_group_list(gstatus, pstatus, p, count);
			}

			@Override
			public int size() {
				return getFinancialService().profit_group_list_count(gstatus, pstatus);
			}

		});
	}


}




