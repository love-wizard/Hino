package com.hino.page.internal;

import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.util.Bindable;

import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Transfer;
import com.hino.util.TimeFormater;

public class GeneralExternalBooking extends BasePage{
	@Bindable
	private Table booking_table = new Table ("externalBookingTable");
	public void onInit()
	{
		booking_table.setClass(Table.CLASS_SIMPLE);
		//Column column = new Column("id", "ID");
		//booking_table.addColumn(column); 
		
        Column column = new Column("bookingRef", "订单号");
		booking_table.addColumn(column);
		
		column = new Column("group_name_id", "团名称(ID)");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Booking)object).getGroup();
				return g.getName()+"("+g.getId()+")";
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("cunstomer_name_id", "客户姓名(English)");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).getChinesename()+"("+((Booking) object).genFullname()+")";
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("cunstomer_isVip", "客户是否VIP");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Customer c = ((Booking) object).getCustomer();
				if(c==null)
				{
					return "否";
				} else
				{
					return c.genIsVip()?"是":"否";
				}
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("pd_credit", "现金");
		booking_table.addColumn(column);
		
		column = new Column("pd_point", "积分");
		booking_table.addColumn(column);
		
		column = new Column("total_credit", "共计");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking)object).genTotalPaid() + "";
			}
		});
		booking_table.addColumn(column);
	
		column = new Column("org_credit", "标准价");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Booking)object).getGroup();
				return g.getPrice()+"";
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("vip_credit", "会员价");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Group g = ((Booking)object).getGroup();
				return g.getVip_price()+"";
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("sales_info", "销售代表");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genSalesStr();
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("transfer_info", "转账申报");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Transfer t = (Transfer)((Booking) object).getTransfer();
				return (t==null)?"无":t.getId()+"";
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("payment_info", "支付方式");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genBookingMethodStr();
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("booking_status", "订单状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genStatusStr();
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("refund_status", "退款需求");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genRefundStr();
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("booking_time", "下单时间");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return TimeFormater.format1(((Booking) object).getBooking_time());
			}
		});
		booking_table.addColumn(column);
	}
	public void onRender()
	{
		booking_table.setDataProvider(new DataProvider(){
			@Override
			public List<Booking> getData() {
				return getSalesService().list_unconfirmed_external_booking();
			}
			
		});
	}
}
