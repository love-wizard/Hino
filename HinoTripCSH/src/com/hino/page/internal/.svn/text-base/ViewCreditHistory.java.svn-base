package com.hino.page.internal;

import java.util.Calendar;
import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.util.Bindable;

import com.hino.model.Customer;
import com.hino.model.CustomerHistory;
import com.hino.model.Staff;
import com.hino.util.TimeFormater;

public class ViewCreditHistory extends BasePage {
	
	@Bindable
	private Integer id;
	@Bindable private Table creditHistory = new Table("creditHistory");
	
	@Override
    public void onInit() {
		
		Column column = new Column("id", "History ID");
		creditHistory.addColumn(column);
		
		// Use setDecorator to show Chinese characters and special symbos like < and >
        column = new Column("email", "账号");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				CustomerHistory curCustomer = (CustomerHistory) object;
				return curCustomer.getEmail(); 
			}
        });
        creditHistory.addColumn(column);
        
        column = new Column("chinesename","中文名");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				CustomerHistory curCustomer = (CustomerHistory) object;
				return curCustomer.getChinesename();
			}
        });
        creditHistory.addColumn(column);
        
        column = new Column("bopomofo","拼音");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				CustomerHistory curCustomer = (CustomerHistory) object;
				return curCustomer.genFullName();
			}
        });
        creditHistory.addColumn(column);
        

        column = new Column("point","Point");
//        column.setDecorator(new Decorator() {
//			@Override
//			public String render(Object object, Context context) {
//				Customer curCustomer = (Customer) object;
//				return curCustomer.genIsVip()?"是":"否";
//			}
//        });
        creditHistory.addColumn(column);
        
        column = new Column("createUid","修改人");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				CustomerHistory curCustomer = (CustomerHistory) object;
				curCustomer.setStaff(getHumanResourceService().view_staff((int)curCustomer.getCreateUid()));
				return curCustomer.getStaff().genFullName();
			}
        });
        creditHistory.addColumn(column);
        

        column = new Column("createDate","修改时间");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				CustomerHistory curCustomer = (CustomerHistory) object;
				return TimeFormater.format1(curCustomer.getCreateDate());
			}
        });
        creditHistory.addColumn(column);
        
		
	}
	
	
	public void onRender()
    {
		// ----- Display customer table -----
		creditHistory.setClass(Table.CLASS_SIMPLE);
		creditHistory.setWidth("100%");
		creditHistory.setPageSize(20);
		creditHistory.setShowBanner(true);
		
		creditHistory.setDataProvider(new PagingDataProvider<CustomerHistory>() {
			private static final long serialVersionUID = 2539474463720137969L;
	
			// Customer data List
			public List<CustomerHistory> getData() { 
	         	int page = creditHistory.getPageNumber();
	             int size = creditHistory.getPageSize(); 
	             List<CustomerHistory> pagedList = getCustomerService().getCustomeHistoryByCid(id);
	             return pagedList;
	         }
	
			// Menu item data size
			@Override
			public int size() {
				return getCustomerService().getCustomerHistoryCountByCid(id);
				//return 0;
			} 
	     });	
    }
}
