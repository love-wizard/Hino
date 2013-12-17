package com.hino.page.internal;

import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.model.Group;
import com.hino.util.TimeFormater;

public class SalesBookingSelect extends BasePage{
	@Bindable
	private Table table = new Table("groupTable");
	
	public ActionLink bookingLink = new ActionLink("booking", "Book");

	public void onInit()
	{
		bookingLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
                setRedirect("./sales_rep_booking_form.htm?gid="+bookingLink.getValueInteger());
                return true;
            }
        });
		//table = new Table("siteTable"); 
		table.setClass(Table.CLASS_COMPLEX);
		table.setHoverRows(true);
		
		Column column = new Column("id", "Tour ID");
		column.setSortable(true);
		table.addColumn(column); 
		
		column = new Column("name_both", "Tour Name");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				String name_cn = ((Group) object).getName();
				String name_en = ((Group) object).getName_en();
					return name_cn + "/" +name_en;
			}
		});
		column.setSortable(true);
		table.addColumn(column);
		
		column = new Column("depart_date", "Date");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar c = ((Group) object).getDepart_date();
				if (c != null)
					return TimeFormater.format2(c);
				else
					return "Not Confirmed";
			}
		});
		column.setSortable(true);
		table.addColumn(column);
		
		column = new Column("depart_city", "City");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Group) object);
				return g.genPickupCity()+"";
			}
		});
		column.setSortable(false);
		table.addColumn(column);
		
		column = new Column("booking_status", "Booking Status");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Group) object);
				return "Confirmed" + g.getSeats_taken() + "/ Unpaid" + g.getSeats_reserved() + "/ Total" +g.getSeats();
			}
		});
		column.setSortable(false);
		table.addColumn(column);
		
		column = new Column("action", "Process");
		 
		ActionLink[] links = {bookingLink}; 
		column.setDecorator(new LinkDecorator(table, links, "id")); 
		column.setSortable(false); 
		table.addColumn(column);
		
		column = new Column("g_booking", "Group-Book");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Group) object);
				String glink;
				if (g.getInt_groupon()) 
				{
					glink = "<a href='./sales_rep_booking_form.htm?gid="+g.getId()+"&bktype=1'>Book</a>";
				} else
				{
					glink = "unavailable";
				}
				return glink;
			}
		});
		column.setSortable(false);
		table.addColumn(column);
	}
	
	public void onRender()
	{
		table.setDataProvider(new DataProvider<Group>() {
			 public List<Group> getData() {
				 int p = table.getPageNumber();
				 int count = table.getPageSize();
				 String sortColumn = table.getSortedColumn();
	             boolean ascending = table.isSortedAscending();
	             
	             return getSalesService().list_group_intbookable(sortColumn, ascending);
			 }
			  				 
		});
	}
}
