package com.hino.page;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Radio;
import org.apache.click.control.RadioGroup;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.EmailField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.extras.control.RegexField;
import org.apache.click.extras.control.TelephoneField;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.dto.CustomerBasicInfoDto;
import com.hino.dto.CustomerPasswordDto;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class AccountSetting extends BasePage {
	private static final long serialVersionUID = -7633737420541155842L;
	
	@Bindable private String type;
	@Bindable private Form basicForm = new Form("basic_form");
	@Bindable private Form psdForm = new Form("psd_form");
	@Bindable private Table booking_table = new Table("booking_table");
	
	public ActionLink ticketLink = new ActionLink("ticket", "电子票");
	public ActionLink feedbackLink = new ActionLink("feedback", "反馈问卷");
    @Override
    public void onInit() {
		
    	// Customers must log in to visit this page
    	Customer curCustomer = (Customer)getContext().getSession().getAttribute("customer");
    	if(type == null || curCustomer == null) {
    		setRedirect(Index.class);
        	return;
    	}
    	
    	if(type.compareTo("basic") == 0) {
    		// ----- Display customer basic information form -----
    		basicForm.setAttribute("class", "niceform");
    		
    		HiddenField customerIdFld = new HiddenField("id", String.class);
    		customerIdFld.setValue(String.valueOf(curCustomer.getId()));
    		basicForm.add(customerIdFld);

    		HiddenField typeFld = new HiddenField("type", String.class);
    		typeFld.setValue(String.valueOf(type));
    		basicForm.add(typeFld);

    		EmailField email = new EmailField("email", "电子邮箱地址", 50, false);
    		email.setValue(curCustomer.getEmail());
    		email.setDisabled(true);
    		basicForm.add(email);
    		
    		RegexField lastName = new RegexField("lastname", "名(pinyin)", 50, false);
    		lastName.setValue(curCustomer.getLastname());
    		lastName.setPattern("[a-zA-Z ]++");
    		basicForm.add(lastName);
    		
    		RegexField firstName = new RegexField("firstname", "姓(pinyin)", 50, false);
    		firstName.setValue(curCustomer.getFirstname());
    		firstName.setPattern("[a-zA-Z ]++");
    		basicForm.add(firstName);
    		
    		RegexField chineseName = new RegexField("chinesename", "中文名", 50, false);
    		chineseName.setValue(EscapeHtml.htmlDecode(curCustomer.getChinesename()));
    		basicForm.add(chineseName);
    		
    		RadioGroup gender = new RadioGroup("gender", "性别", false);
    		gender.add(new Radio("0", "Male "));
    		gender.add(new Radio("1", "Female "));
    		gender.setValue(String.valueOf(curCustomer.getGender()));
    		gender.setVerticalLayout(true);
    		basicForm.add(gender);
    		
    		DateField dob = new DateField("dob", "生日", 45, false);
    		dob.setFormatPattern("yyyy-MM-dd");
    		dob.setValue(TimeFormater.format2(curCustomer.getDob()));
    		basicForm.add(dob);
    		
    		TelephoneField telephone = new TelephoneField("telephone", "电话号码", 50, false);
    		telephone.setValue(curCustomer.getPhone());
    		basicForm.add(telephone);

    		TextArea address = new TextArea("address", "地址", 50, 10, false);
    		address.setValue(EscapeHtml.htmlDecode(curCustomer.getAddress()));
    		address.setTrim(true);
    		basicForm.add(address);	
    		
    		TextField city = new TextField("city", "城市", 50, false);
    		city.setValue(EscapeHtml.htmlDecode(curCustomer.getCity()));
    		basicForm.add(city);
    		
    		TextField postcode = new TextField("postcode", "邮编", 50, false);
    		postcode.setValue(EscapeHtml.htmlDecode(curCustomer.getPostcode()));
    		basicForm.add(postcode);
    		
    		basicForm.add(new Submit("save", "提交修改", this, "onBasicSaveClick"));
    		basicForm.add(new Submit("cancel", "取消修改", this, "onCancelClick"));
    	}
    	else if(type.compareTo("psd") == 0) {
    		// ----- Display password form -----
    		psdForm.setAttribute("class", "niceform");
    		
    		HiddenField customerIdFld = new HiddenField("id", String.class);
    		customerIdFld.setValue(String.valueOf(curCustomer.getId()));
    		psdForm.add(customerIdFld);

    		HiddenField typeFld = new HiddenField("type", String.class);
    		typeFld.setValue(String.valueOf(type));
    		psdForm.add(typeFld);

    		PasswordField oldPsd = new PasswordField("oldpsd", "旧密码", 50, true);
    		psdForm.add(oldPsd);
    		PasswordField newPsd = new PasswordField("newpsd", "新密码", 50, true);
    		psdForm.add(newPsd);
    		PasswordField confPsd = new PasswordField("confpsd", "重复新密码", 50, true);
    		psdForm.add(confPsd);

    		psdForm.add(new Submit("save", "提交修改", this, "onPsdSaveClick"));
    		psdForm.add(new Submit("cancel", "取消修改", this, "onCancelClick"));
    	}
    	else if(type.compareTo("mybooking") == 0) {
    		ticketLink.setParameter("type", "mybooking");
    		ticketLink.setAttribute("class", "ajaxTicket");
        	ticketLink.addBehavior(new DefaultAjaxBehavior() {
    			@Override
    			public ActionResult onAction(Control source) {
    				Customer curCustomer = (Customer)getContext().getSession().getAttribute("customer");
    		    	long cid = -1;
    		    	if(curCustomer!=null)
    		    	{
    		    		cid = curCustomer.getId();
    		    	}
    				ServiceResponse sr = getCustomerService().issueTicket(ticketLink.getValueLong(),cid);
    				
    				if(sr.isSucc())
    				{
    					
    					Map<String, String> bookingHash = new HashMap<String, String>();
    					bookingHash.put("authcode", (String)sr.getResponse());
    					System.out.println((String)sr.getResponse());
    					StringWriter writer = new StringWriter();
    					  ObjectMapper mapper = new ObjectMapper();
    					  try {
    					   mapper.writeValue(writer, bookingHash);
    					  } catch (JsonGenerationException e) {
    					   e.printStackTrace();
    					  } catch (JsonMappingException e) {
    					   e.printStackTrace();
    					  } catch (IOException e) {
    					   e.printStackTrace();
    					  }

    					return new ActionResult(writer.toString(), ActionResult.JSON);
    				} else
    				{
    					return new ActionResult("1", ActionResult.HTML);
    				}
    			}
    		}); 
        	
        	feedbackLink.setParameter("type", "mybooking");
        	feedbackLink.setActionListener(new ActionListener() {
                private static final long serialVersionUID = 1L;
                public boolean onAction(Control source) {
                	Customer curCustomer = (Customer)getContext().getSession().getAttribute("customer");
    		    	long cid = -1;
    		    	if(curCustomer!=null)
    		    	{
    		    		cid = curCustomer.getId();
    		    	}
                	ServiceResponse sr = getCustomerService().requestFeedBack(cid, feedbackLink.getValueLong());
                	if(sr.isSucc())
                	{
                		setRedirect("./feedback_form.htm?value=" + feedbackLink.getValueLong());
                	} else
                	{
                		getHeadElements().add(new JsScript("alert(\"" +EscapeHtml.nrTonnrr(sr.list_msg())+ "\")"));
                	}
                    return true;
                }
            }); 
    		
        	
    		booking_table.setClass(Table.CLASS_BLUE1);
    		
            Column column = new Column("bookingRef", "订单号");
    		booking_table.addColumn(column);
    		
    		column = new Column("route_name", "出游线路");
    		column.setDecorator(new Decorator(){
    			@Override
    			public String render(Object object, Context context) {
    				Group g = ((Booking)object).getGroup();
    				return g.getRoute().getName();
    			}
    		});
    		booking_table.addColumn(column);
    		
    		column = new Column("depart_time", "出团日期");
    		column.setDecorator(new Decorator(){
    			@Override
    			public String render(Object object, Context context) {
    				Group g = ((Booking)object).getGroup();
    				return TimeFormater.format2(g.getDepart_date());
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
    		
    		/*
    		column = new Column("pd_credit", "实际售价");
    		booking_table.addColumn(column);
    	
    		column = new Column("pd_point", "积分抵用");
    		booking_table.addColumn(column);
    		*/
    		
    		column = new Column("pickup_city", "接车城市");
    		column.setDecorator(new Decorator(){
    			@Override
    			public String render(Object object, Context context) {
    				return ((Booking) object).getPickup().split("-")[0];
    			}
    		});
    		booking_table.addColumn(column);
    		
    		column = new Column("book_time", "下单时间");
    		column.setDecorator(new Decorator(){
    			@Override
    			public String render(Object object, Context context) {
    				return TimeFormater.format1(((Booking) object).getBooking_time());
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
    		//ticketLink.setParameter("type", value)
    		column = new Column("action", "操作");
    		ActionLink[] links = {ticketLink, feedbackLink}; 
    		column.setDecorator(new LinkDecorator(booking_table, links, "id")); 
    		booking_table.addColumn(column);
    	}
    	else {
    		setRedirect(Index.class);
    	}

    }

    /**
     * Update customer basic information
     * @return
     */
    public boolean onBasicSaveClick() {
        if (basicForm.isValid()) {
        	CustomerBasicInfoDto basicinfodto = new CustomerBasicInfoDto();
        	basicForm.copyTo(basicinfodto);
        	
        	// Convert String into Calendar
        	basicinfodto.setDob(TimeFormater.parse2(basicForm.getField("dob").getValue()));
        	
        	basicinfodto.setChinesename(EscapeHtml.htmlEncode(basicinfodto.getChinesename()));
        	basicinfodto.setAddress(EscapeHtml.htmlEncode(basicinfodto.getAddress()));
        	basicinfodto.setCity(EscapeHtml.htmlEncode(basicinfodto.getCity()));
        	basicinfodto.setPostcode(EscapeHtml.htmlEncode(basicinfodto.getPostcode()));
        	
        	ServiceResponse updateRes = getCustomerService().update_customer_basic(basicinfodto);
        	
        	if(updateRes.isSucc() == true) {
        		// Update session
        		getContext().getSession().setAttribute("customer", updateRes.getResponse());
        		
    			getHeadElements().add(new JsScript("alert('更新信息成功')"));
    			getHeadElements().add(
    					new JsScript("window.location = './index.htm'"));
    	        return true;
        	}
        	else {
        		basicForm.setError(Info.toStringInfo(updateRes.getInfo_code()));
        		return false;
        	}
        }
    	return true;
    }
    
    /**
     * Update password
     * @return
     */
    public boolean onPsdSaveClick() {
        if (psdForm.isValid()) {
        	CustomerPasswordDto psddto = new CustomerPasswordDto();
        	psdForm.copyTo(psddto);

    		if(psddto.getNewpsd().compareTo(psddto.getConfpsd()) == 0) {
    			ServiceResponse updateRes = getWebService().update_customer_psd(psddto);
    			
    			if(updateRes.isSucc()) {
	    			getHeadElements().add(new JsScript("alert('修改密码成功')"));
	    			getHeadElements().add(
	    					new JsScript("window.location = './index.htm'"));
	    			return true;
    			}
    			else {
    				psdForm.setError(Info.toStringInfo(updateRes.getInfo_code()));
        			return false;
    			}
    		}
    		else {
    			psdForm.setError("新密码两次输入不一致");
    			return false;
    		}
        }
        return true;
    }

    /**
     * Redirect to index.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(Index.class);
        return true;
    }
    
    public void onRender()
    {
    	final Customer curCustomer = (Customer)getContext().getSession().getAttribute("customer");
    	if(type==null|| curCustomer == null) {
    		setRedirect(Index.class);
        	return;
    	}
    	else
    	{
			booking_table.setDataProvider(new DataProvider<Booking>() {
				private static final long serialVersionUID = -734178779071663380L;

				public List<Booking> getData() {
				//return getCustomerService().getMyBookingList(curCustomer.getId());
					return getCustomerService().getMyBookingList(curCustomer.getEmail());
				}

	   		});
    		
    	}
    }

}
