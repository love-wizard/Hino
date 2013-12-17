package com.hino.page.en;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
import org.apache.click.control.HiddenField;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hino.dto.BookingReserveDto;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class Booking extends BasePage {
	
	private static final long serialVersionUID = 7200048873445736234L;
	
	@Bindable private Integer gid;
	@Bindable long routeid;
	@Bindable String groupname;
	@Bindable String groupdate;
	@Bindable String groupprice;
	@Bindable String customerFisrtName;
	@Bindable String customerLastName;
	@Bindable String customerCnName;
	@Bindable String customerGenderM;
	@Bindable String customerGenderFM;
	@Bindable String customerEmail;
	@Bindable String customerPhone;
	@Bindable String customerCredit;
	@Bindable String pickupinfo;
	@Bindable String jsData;
	
	@Bindable
	public Button external_reserve = new Button("external_reserve", "d");
	
	public HiddenField idfield = new HiddenField("idfield", Integer.class);
	
	public void onInit()
	{
		// Customers must log in to booking
    	Customer curCustomer = (Customer)getContext().getSession().getAttribute("customer");
    	
    	// Must log in before booking
		if(curCustomer == null) {
			getHeadElements().add(new JsScript("alert('Please sign in before booking.')"));
			getHeadElements().add(
					new JsScript("window.location = './login.htm?gid="+gid+"'"));
			return;
		}
		
		if(gid!=null)
		{
			Group targetGroup = getCustomerService().getGroupById(gid);
			
			// Invalid group id
			if(targetGroup == null) {
				getHeadElements().add(new JsScript("alert('Illegal group. Booking failed.')"));
				getHeadElements().add(
						new JsScript("window.location = './index.htm'"));
				return;
			}
			
			// Get route information
			routeid = targetGroup.getRoute().getId();
			
			// Get group information
			groupname = ""+targetGroup.getName_en();
			groupdate = TimeFormater.format2(targetGroup.getDepart_date());
			groupprice = String.valueOf(targetGroup.getPrice());
			
			// Get current customer information
			customerFisrtName = curCustomer.getFirstname();
			customerLastName = curCustomer.getLastname();
			customerCnName = curCustomer.getChinesename();
			if(curCustomer.getGender() == 0) {
				customerGenderM = "selected=\"selected\"";
			} else {
				customerGenderFM = "selected=\"selected\"";
			}
			customerEmail = curCustomer.getEmail();
			customerPhone = curCustomer.getPhone();
			customerCredit = String.valueOf(curCustomer.getPoint());
			
			// Get pickup information
			List<String> l = targetGroup.genPickupFull();
			pickupinfo = "";
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
			Matcher m;  
			for (int i=0;i<l.size();i++)
			{
				m = p.matcher(l.get(i));  
				pickupinfo = pickupinfo + "\"" + m.replaceAll("") + "\"";
				
				if(i!=l.size()-1)
				{
					pickupinfo = pickupinfo + ",";
				}
			}
			
			jsData = "<script language=\"javascript\">"+
				"var pickup = ["+pickupinfo+"];"+
				"var singleprice = "+groupprice+";"+
				"var credit = "+customerCredit+";"+
				"var groupid = "+gid+";"+
				"var groupname = \""+targetGroup.getName()+"\";"+
				"var groupdesc = \""+targetGroup.getRoute().getThumbDesc()+"\";"+
				"var groupmaxcredit = \""+targetGroup.getMax_point()+"\";"+
				"var groupvipprice = \""+targetGroup.getVip_price()+"\";"+
				"var isvip = \""+curCustomer.genIsVip()+"\";"+
				"</script>";
		} 
		// No group id passed 
		else {
			getHeadElements().add(new JsScript("alert('No such group. Booking failed.')"));
			getHeadElements().add(
					new JsScript("window.location = './index.htm'"));
			return;
		}
		
		external_reserve.setId("external_reserve");
		external_reserve.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata = getContext().getRequest().getParameter("jsondata");
            	
            	//int groupid = Integer.parseInt(getContext().getRequest().getParameter("gid"));
            	
            	ObjectMapper mapper = new ObjectMapper();
            	List<BookingReserveDto> result = null;
            	try {
					 result = mapper.readValue(jsondata, TypeFactory.collectionType(ArrayList.class, BookingReserveDto.class));
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				long cid = -1;
				Customer c = ((Customer)(getContext().getSession().getAttribute("customer")));
				if(c!=null)
				{
					cid = c.getId();
				} else
				{
					return new ActionResult("Illegal parameters!", ActionResult.HTML);
				}
				
				boolean validateResult = true;
				for(int index=0; index<result.size(); index++) {
					BookingReserveDto curCBdto = result.get(index);
					if((curCBdto.getFn() == null || curCBdto.getFn().length() == 0)
						|| (curCBdto.getLn() == null || curCBdto.getLn().length() == 0)
						|| (curCBdto.getPickup() == null || curCBdto.getPickup().length() == 0)
						|| (curCBdto.getGender() != 0 && curCBdto.getGender() != 1)
						|| (curCBdto.getPaymethod() < Info.BKM_EXTERNAL_BANKTRANSFER 
								|| curCBdto.getPaymethod() > Info.BKM_EXTERNAL_PAYATBRANCH)
						|| (curCBdto.getPd_point()%100 != 0 
								|| curCBdto.getPd_point() > 
									result.size()*getCustomerService().getGroupById(gid).getMax_point())
						)
						validateResult = false;
					
					//curCBdto.setCid(cid);
					curCBdto.setEmail(c.getEmail());
				}
				if(!validateResult)
					return new ActionResult("Illegal parameters!", ActionResult.HTML);
				
				ServiceResponse sr = getCustomerService().external_booking(gid, cid, result, 0, null);
				
            	if(sr.isSucc()) {
            		String bookingRefs = EscapeHtml.nrTobr(sr.list_msg());
            		return new ActionResult(bookingRefs, ActionResult.HTML);
            	}
            	else
            		return new ActionResult("Sorry! Booking failed!"+sr.list_msg(), ActionResult.HTML);
            }
        });
		
	}
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new JsImport("../js/booking_en.js"));
            headElements.add(new CssImport("../css/booking.css"));
        }
        return headElements;
    }
}
