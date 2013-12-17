package com.hino.page.internal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Checkbox;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.LinkDecorator;

import sun.util.calendar.LocalGregorianCalendar.Date;

import com.hino.dto.AirlineDto;
import com.hino.model.Airline;
import com.hino.model.AirlineCompany;
import com.hino.model.Airport;
import com.hino.model.Group;
import com.hino.model.Site;
import com.hino.util.TimeFormater;

public class AirlineManage extends BasePage {
	public String title = "Airline管理";
	public Table table = new Table("airlineTable");
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改");
	public ActionLink addAirlineLink = new ActionLink("addAirline", "添加新Airline路线");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	public Form form = new Form("airlineFilter");
	private DateField depart_end_time = new DateField("depart_end_time", "出发结束日期&时间（当地）", 25, true);	
	private Submit delete = new Submit("delete", "删除过期航线", this, "onOkClick");
	
	public void onInit()
	{
		delete.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
		depart_end_time.setFormatPattern("yyyy-MM-dd");
		form.add(depart_end_time);
		form.add(delete);

		addAirlineLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(AirlineCAM.class);
                return true;
            }
        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer id = editLink.getValueInteger(); 
                setRedirect("./airline_cam.htm?id=" + id);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getAirlineService().deleteAirline(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");

		//table = new Table("siteTable");
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setPageSize(10);
        
        table.setShowBanner(true); 
        //table.setSortable(true); 6
        
        table.addColumn(new Column("id", "线路ID")); 
        
        //发布日期
        Column column = new Column("publish_date", "发布日期");
        column.setSortable(true);
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar c = ((Airline) object).getPublish_date();
				if (c != null)
					return TimeFormater.format2(c);
				else
					return "未指定";
			}
		});
        table.addColumn(column);
        
        column = new Column("airline", "航空公司");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				AirlineCompany c = getAirlineService().getAirlineCompany(Long.parseLong(((Airline) object).getAirline()));
				if (c != null)
//					return c.getCompany_display_name();
					return c.genShortName();
				else
					return "未指定";
			}
		});
        table.addColumn(column); 
       
        column = new Column("departure_id", "出发机场");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Airport c = getAirlineService().getAirport(((Airline) object).getDeparture().getId());
				if (c != null)
//					return c.getAirport_city()+c.getAirport_name();
					return c.genShortName();
				else
					return "未指定";
			}
		});
        table.addColumn(column); 

        column = new Column("arrival_id", "到达机场");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Airport c = getAirlineService().getAirport(((Airline) object).getArrival().getId());
				if (c != null)
//					return c.getAirport_city()+c.getAirport_name();
					return c.genShortName();
				else
					return "未指定";
			}
		});
        table.addColumn(column);
            
        column = new Column("departure_begin_date", "出发日期开始");
        column.setSortable(true);
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar c = ((Airline) object).getDeparture_begin_date();
				if (c != null)
					return TimeFormater.format2(c);
				else
					return "未指定";
			}
		});
        table.addColumn(column);
        
        column = new Column("departure_end_date", "出发日期结束");
        column.setSortable(true);
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar c = ((Airline) object).getDeparture_end_date();
				if (c != null)
					return TimeFormater.format2(c);
				else
					return "未指定";
			}
		});
        table.addColumn(column);
/*        
        column = new Column("departure_end_date", "到达时间");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar c = ((Airline) object).getDeparture_end_date();
				if (c != null)
					return TimeFormater.format1(c);
				else
					return "未指定";
			}
		});
        table.addColumn(column); */
        
        column = new Column("return_begin_date", "出发时间(返程)");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar c = ((Airline) object).getReturn_begin_date();
				if (c != null)
					return TimeFormater.format2(c);
				else
					return "未指定";
			}
		});
        table.addColumn(column); 
/*        
        column = new Column("return_end_date", "到达时间(返程)");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar c = ((Airline) object).getReturn_end_date();
				if (c != null)
					return TimeFormater.format1(c);
				else
					return "未指定";
			}
		}); 
        table.addColumn(column);*/
        
        column = new Column("type", "1-Single/2-Return");
        table.addColumn(column);
        
        column = new Column("recommended", "true/false");
        table.addColumn(column);
        
        column = new Column("price", "价格");
        column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Airline al = (Airline) object;
				if(al.getType()==1)
				{
					return String.valueOf(al.getSingle_trip_price());
				}
				else
				{
					return String.valueOf(al.getRound_trip_price());
				}
			}
		});
        table.addColumn(column);
        
        column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<Airline>() {
        	
            public List<Airline> getData() { 
            	int p = table.getPageNumber();
            	//table.get
                int count = table.getPageSize(); 
                List<Airline> l = getAirlineService().getAirlineList();
                //System.out.println("List site:" +l);
                //System.out.println("List site size:" +l.size());
                return l;
            }

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return getAirlineService().getAirlineCount();
			} 
        }); 
        
        
	}
	
	public boolean onOkClick() {

		if (form.isValid()) {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
			Calendar cal2=Calendar.getInstance();
			cal2.setTime(depart_end_time.getDate());
			java.util.Date date=cal2.getTime(); 
			try {
				getAirlineService().deletePastAirline(df.format(date));
			} catch (NumberFormatException nfe) {
				//
			} finally {
				//
			}
			form.clearValues();
			getHeadElements().add(new JsScript("alert('删除成功')"));
			getHeadElements().add(
					new JsScript("window.location = './airline_manage.htm'"));

			// setRedirect(SiteCAM.class);

		}
		return true;
	}

}
