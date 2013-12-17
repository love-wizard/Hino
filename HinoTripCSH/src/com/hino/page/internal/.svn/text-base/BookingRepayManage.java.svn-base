package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.AddressDto;
import com.hino.dto.AirlineCompanyDto;
import com.hino.dto.AirlineQACAMDto;
import com.hino.dto.AirportDto;
import com.hino.dto.BookingReserveDto;
import com.hino.model.AirlineCompany;
import com.hino.model.AirlineQA;
import com.hino.model.Airport;
import com.hino.model.Customer;
import com.hino.model.Site;
import com.hino.util.ServiceResponse;

public class BookingRepayManage extends BasePage {
	private static final String DEFAULT_ID_VALUE = "系统自动生成";
	public String title = "追回订单管理";
	@Bindable
	private Integer id;
	@Bindable private String cref;
	@Bindable
	private Form form = new Form("booking_repay_cam_form");
	public Table table = new Table("bookingTable");
	private HiddenField bookingid = new HiddenField("boookingid", "bookingid");
	private TextField username= new TextField("username", "输入客户账号", 50, true);
	private TextField amount= new TextField("amount", "所欠金额", 50, true);
	private TextField group= new TextField("group", "输入团次ID", 50, true);
	
	public void onInit()
	{
		bookingid.setDisabled(true);
		
		form.add(username);
		form.add(amount);
		form.add(group);
		form.add(new Submit("ok", "生成链接", this, "onOkClick"));
		
		if(cref == null) {
			cref = "";
		}
		
		addModel("cref", cref);
	}

	public boolean onOkClick() {
		String ref = "";
		if (form.isValid()) {
			BookingReserveDto apdto = new BookingReserveDto();
			// siteid.getValue()
			apdto.setEmail(username.getValue());
			apdto.setPd_credit(Double.parseDouble(amount.getValue()));
			apdto.setGroupId(Integer.parseInt(group.getValue()));
			
			try {
				ServiceResponse sr = getCustomerService().repay_booking(Integer.parseInt(group.getValue()), username.getValue(), apdto);
				ref=sr.getMsg_list().get(0);
				if(sr.isSucc()){
					form.clearValues();
					getHeadElements().add(
							new JsScript("window.location = './booking_repay.htm?cref="+ref.replace("链接为", "")+"'"));
					return true;
				}
				
				
			} catch (NumberFormatException nfe) {
				//
			} finally {
				//
			}

			if(ref == null) ref = "";
			
			// fullimgfile.getFileItem().getClass().getName();
			form.clearValues();
			getHeadElements().add(new JsScript( "alert('"+ref+"')"));
			getHeadElements().add(
					new JsScript("window.location = './booking_repay.htm?cref="+ref.replace("链接为", "")+"'"));
			
			// setRedirect(SiteCAM.class);
			
		}
		return true;
	}
}
