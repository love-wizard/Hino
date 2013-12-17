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
import org.apache.click.control.Reset;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.DoubleField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class SalesTransferForm extends BasePage{
	@Bindable
	private Form tran_form = new Form("tform");
	@Bindable
    private Table booking_table = new Table("btable");
	
	private DoubleField amount = new DoubleField("amount", "申报金额", true);
	private Select tran_method = new Select("method", "付款人", true);
	private DateField time = new DateField("time", "转帐时间", true);
	private TextArea ref = new TextArea("comments", "转帐备注", 50, 10, true);
	private HiddenField bl = new HiddenField("booking_list", String.class);
    private Select select_payment_method = new Select("select_payment_method", "支付方式",true);
	
	private Reset reset = new Reset("reset", "重置");
	private Submit submit = new Submit("submit", "提交");
	
	private Staff s;
	
	public void onInit()
	{
		tran_form.add(amount);
		tran_form.add(tran_method);
		tran_form.add(select_payment_method);
		tran_form.add(time);
		tran_form.add(ref);
		tran_form.add(bl);
		tran_form.add(submit);
		tran_form.add(reset);
		
		submit.setActionListener(new ActionListener()
		{
			@Override
			public boolean onAction(Control source) {
				if(tran_form.isValid())
				{
					List<Long> b_l = new ArrayList<Long>();
					String[] bl_str = bl.getValue().split(" ");
					for(int i=0;i<bl_str.length;i++)
					{
						try {
							b_l.add(Long.parseLong(bl_str[i]));
						} catch (NumberFormatException nfe)
						{
							//TODO
						}
					}
					
					Staff s = (Staff)getContext().getSession().getAttribute("staff");
					if(s!=null)
					{
						Calendar c = Calendar.getInstance();
						c.setTime(time.getDate());
						ServiceResponse sr = getFinancialService().transfer_declare(b_l, s, c, Calendar.getInstance(), amount.getDouble(), ref.getValue(), tran_method.getValue(), select_payment_method.getValue());
						if (sr.isSucc())
						{
							getHeadElements().add(new JsScript("alert('操作执行成功')"));
							tran_form.clearValues();
							amount.setValue("0.0");
						} else
						{
							getHeadElements().add(new JsScript("alert('操作执行失败')"));
						}
					}
				} 
				
				return true;
			}
			
		});
		
		time.setFormatPattern("yyyy-MM-dd");
		tran_method.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();

				optionList.add(new Option(""));
				optionList.add(new Option(0, Info.BKP_STR_CN[0]));
				optionList.add(new Option(1, Info.BKP_STR_CN[1]));
//				optionList.add(new Option("其他"));				
				return optionList;
			}
		});
		
		select_payment_method.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				optionList.add(new Option(""));
				optionList.add(new Option(0, Info.ABKM_STR_CN[0]));
				optionList.add(new Option(1, Info.ABKM_STR_CN[1]));
				optionList.add(new Option(2, Info.ABKM_STR_CN[2]));
				optionList.add(new Option(3, Info.ABKM_STR_CN[3]));
				optionList.add(new Option(4, Info.ABKM_STR_CN[4]));
				
				return optionList;
			}
		});
		amount.setValue("0.0");

		//tran_form;
		
		booking_table.setClass(Table.CLASS_SIMPLE);
        Column column = new Column("selected", "选中");
        column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return "<input type='checkbox' name='name_bookingid[]' value='"+((Booking) object).getId()+"' />" ;
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("bookingRef", "订单号");
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

		column = new Column("price", "实际售价");
        column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return "<input type='hidden' id='pri_"+((Booking) object).getId()+"' value='"+((Booking) object).getPd_credit()+"' />" + ((Booking) object).getPd_credit() ;
			}
		});
		booking_table.addColumn(column);
		
		column = new Column("pd_point", "使用积分");
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
		
		column = new Column("booking_status", "订单状态");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				return ((Booking) object).genStatusStr();
			}
		});
		booking_table.addColumn(column);
    }
    
	public void onRender() {
		s = (Staff) getContext().getSession().getAttribute("staff");
		if (s==null)
		{
			setRedirect(Index.class);
		} else
		{
			booking_table.setDataProvider(new DataProvider<Booking>() {
				
				public List<Booking> getData() {
					
					return getFinancialService().bookingSearch(12, "", s.getId(), null, -1, -1);
				}
			});
		}
		
	}
}
