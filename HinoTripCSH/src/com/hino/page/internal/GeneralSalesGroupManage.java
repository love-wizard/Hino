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
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.model.AreaBranch;
import com.hino.model.Group;
import com.hino.model.Site;
import com.hino.model.Staff;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.PriviledgeParser;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GeneralSalesGroupManage extends BasePage {
	public Table table = new Table("groupTable");
	public ActionLink cancelLink = new ActionLink("cancel", "取消本团");
	public ActionLink deleteLink = new ActionLink("delete", "删除本团");
	public ActionLink basicLink = new ActionLink("basic", "基本信息");
	public ActionLink bookingLink = new ActionLink("booking", "报名信息");
	public ActionLink editTagLink = new ActionLink("tag", "设置团Tag"); //Ken Chen 2013-01-11 
	public ActionLink finishLink = new ActionLink("finish", "结束本团");
	
	public Form form = new Form("groupFilter");
	private Select catSelect = new Select("catSelect", "团类别筛选");
	DateField month = new DateField("month",  "出团年月", 20, false);
	private Submit confirm = new Submit("submit", "筛选");
	private Submit clear = new Submit("clear", "重置筛选");
	public void onInit() {
		
		editTagLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
                setRedirect("./group_tag_manage.htm?id="+editTagLink.getValueInteger());
                return true;
            }
        });

		basicLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
                setRedirect("./general_sales_group_cam.htm?id="+basicLink.getValueInteger());
                return true;
            }
        });
		
		bookingLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
                setRedirect("./general_sales_group_booking.htm?gid="+bookingLink.getValueInteger());
                return true;
            }
        });
		
		cancelLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	ServiceResponse sr = getSalesService().set_cancel_stage(cancelLink.getValueInteger());
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
		
		finishLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	ServiceResponse sr = getSalesService().set_finish_stage(finishLink.getValueInteger());
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
            	
            	ServiceResponse sr = getSalesService().delete_group((deleteLink.getValueInteger()));
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
		
		catSelect.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();

				optionList.add(new Option(0, "计划中"));
				optionList.add(new Option(1, "报名中"));
				optionList.add(new Option(2, "出团中"));
				optionList.add(new Option(3, "出团结束"));
				optionList.add(new Option(4, "出团取消"));
				optionList.add(new Option(5, "所有状态"));

				return optionList;
			}
		});
		
		catSelect.setValue("1");

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
		        catSelect.setValue("1");
		        // Clear table state
		        table.setPageNumber(0);
		        table.setSortedColumn(null);
		        // Remove table and form state from the session
		        form.removeState(getContext());
				return true;
			}

		});

		form.add(catSelect);
		month.setFormatPattern("yyyy-MM");// Ken Chen 2012/10/03 增加出团年月条件，默认为空
		form.add(month);
		form.add(confirm);
		form.add(clear);

		table.setClass(Table.CLASS_SIMPLE);
		table.setPageSize(25);
	
		table.setShowBanner(true); //table.setSortable(true);
		Column column = new Column("id", "团ID");
		column.setSortable(true);
		table.addColumn(column); 
		
		column = new Column("name", "团名称");
		column.setSortable(true);
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
		column.setSortable(true);
		table.addColumn(column);

		column = new Column("info_all", "总体情况");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				StringBuffer sb = new StringBuffer("");
				Group g= (Group) object;
				sb.append("已" + (g.getSeats_taken_seckill()+g.getSeats_taken_groupon()+g.getSeats_taken()) + "/ 未" + (g.getSeats_reserved()+g.getSeats_reserved_seckill()+ g.getSeats_reserved_groupon()) + "/ 总" +(g.getSeats()+g.getSeats_groupon()+g.getSeats_seckill()));
				//sb.append(g.getSeats_groupon());
				//sb.append("秒杀时间"+TimeFormater.format3(g.getSeckill_start_time())+"-"+TimeFormater.format3(g.getSeckill_end_time()));
				return sb.toString();
			}
		});
		column.setSortable(false);
		table.addColumn(column);
		
		column = new Column("booking_status", "普通报名");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Group) object);
				/*
				return "未确认" + getSalesService().countGroupBooking(g.getId(), Info.BKS_RESERVING) + 
				"付款中" + getSalesService().countGroupBooking(g.getId(), Info.BKS_TRANSFERING) +  
				"已确认" + getSalesService().countGroupBooking(g.getId(), Info.BKS_CONFIRMED) + 
				"已取消" + getSalesService().countGroupBooking(g.getId(), Info.BKS_CANCELED);
				*/
				return "已" + g.getSeats_taken() + "/ 未" + g.getSeats_reserved() + "/ 总" +g.getSeats();
			}
		});
		column.setSortable(false);
		table.addColumn(column);
	
		column = new Column("group_status", "团状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Group) object).genStatusStr();
			}
		});
		column.setSortable(false);
		table.addColumn(column);
		
		column = new Column("info_group", "团购信息");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				StringBuffer sb = new StringBuffer("");
				Group g= (Group) object;
				if (g.getExt_groupon())
				{
					sb.append("外部/");
				}
				if (g.getInt_groupon())
				{
					sb.append("内部/");
				}
				//sb.append(g.getSeats_groupon());
				sb.append("已" + g.getSeats_taken_groupon() + "/ 未" + g.getSeats_reserved_groupon() + "/ 总" +g.getSeats_groupon());
				//sb.append("截至时间"+TimeFormater.format3(g.getGroupon_end_time()));
				return sb.toString();
			}
		});
		column.setSortable(false);
		table.addColumn(column);
		
		column = new Column("info_sk", "秒杀信息");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				StringBuffer sb = new StringBuffer("");
				Group g= (Group) object;
				if (g.getExt_seckill())
				{
					sb.append("外部/");
				}
				if (g.getInt_seckill())
				{
					sb.append("内部/");
				}
				//sb.append(g.getSeats_groupon());
				sb.append("已" + g.getSeats_taken_seckill() + "/ 未" + g.getSeats_reserved_seckill() + "/ 总" +g.getSeats_seckill());
				//sb.append("秒杀时间"+TimeFormater.format3(g.getSeckill_start_time())+"-"+TimeFormater.format3(g.getSeckill_end_time()));
				return sb.toString();
			}
		});
		column.setSortable(false);
		table.addColumn(column);

		column = new Column("action", "操作");
		
		ActionLink[] links = {basicLink, bookingLink, finishLink, cancelLink, deleteLink, editTagLink}; 
		column.setDecorator(new LinkDecorator(table, links, "id")); 
		column.setSortable(false); 
		table.addColumn(column);
		form.restoreState(getContext());
		
	}
	
	public void onRender() {
		// Set data provider to populate the table row list from
		table.setDataProvider(new PagingDataProvider<Group>() {
			public List<Group> getData() {
				int p = table.getPageNumber();
				int count = table.getPageSize();
				String sortColumn = table.getSortedColumn();
				String departMonth = month.getValue(); 
				
				boolean ascending = table.isSortedAscending();
				if (sortColumn ==null)
				{
					sortColumn = "depart_date";
				}
				
				int viewCat = 5;
				try {
					viewCat = Integer.parseInt(catSelect.getValue());
				} catch (NumberFormatException nfe) {

				} 
				
				// Ken Chen 2012/10/03 添加年月
				//if (viewCat != 5) {
					return getSalesService()
							.list_group_by_status_paging_ordering(viewCat,departMonth, p,
									count, sortColumn, ascending);
				//}

				// return getSalesService().list_all_group_by_paging(p,
				// count,sortColumn, ascending);
			}

			@Override
			public int size() {
				
				String departMonth = month.getValue(); 
				
				int viewCat = 5;
				try {
					viewCat = Integer.parseInt(catSelect.getValue());
				} catch (NumberFormatException nfe) {
				} 
				//if (viewCat != 5) {
					return getSalesService().count_group(viewCat, departMonth);
				//}

				//return getSalesService().getAllGroupCount();
			}

		});
	}

}
