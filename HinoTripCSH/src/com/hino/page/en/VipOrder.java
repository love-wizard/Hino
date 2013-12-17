package com.hino.page.en;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
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
import com.hino.dto.VipOrderReserveDto;
import com.hino.model.Customer;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class VipOrder extends BasePage {
	private static final long serialVersionUID = 5613035213723036160L;
	
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
	public Button vip_order = new Button("vip_order", "d");

	public void onInit()
	{
		// Customers must log in to booking
    	Customer curCustomer = (Customer)getContext().getSession().getAttribute("customer");
    	
    	// Must log in before booking
		if(curCustomer == null) {
			getHeadElements().add(new JsScript("alert('Please sign in before VIP upgrading')"));
			getHeadElements().add(
					new JsScript("window.location = './login.htm?gid="+gid+"'"));
			return;
		}
    	
    	// One can upgrade to VIP only once
		if(curCustomer.genIsVip() == true) {
			getHeadElements().add(new JsScript("alert('You'are already VIP')"));
			getHeadElements().add(
					new JsScript("window.location = './index.htm'"));
			return;
		}
		
		customerEmail = curCustomer.getEmail();			
		
		jsData = "<script language=\"javascript\">"+
			"var email = \""+customerEmail+"\";"+
			"</script>";
		
		vip_order.setId("vip_order");
		vip_order.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata = getContext().getRequest().getParameter("jsondata");
            	
            	ObjectMapper mapper = new ObjectMapper();
            	List<VipOrderReserveDto> result = null;
            	try {
					 result = mapper.readValue(jsondata, TypeFactory.collectionType(ArrayList.class, VipOrderReserveDto.class));
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
				
				boolean validateResult = true;
				VipOrderReserveDto vodrto = result.get(0);
				
				//System.out.println("*****"+vodrto.getName()+" "+vodrto.getPhone()+" "+vodrto.getDelivery()+" "+vodrto.getAddress()+" "+vodrto.getOrder_method());
				
				if((vodrto.getName() == null || vodrto.getPhone() == null)
					|| (vodrto.getDelivery() == Info.VCM_DELIVERY && vodrto.getAddress() == null)
					|| (vodrto.getOrder_method() < Info.BKM_EXTERNAL_BANKTRANSFER || vodrto.getOrder_method() > Info.BKM_EXTERNAL_PAYATBRANCH)
					)
					validateResult = false;
				
				// Set email address to the current customer's email
				Customer c = ((Customer)(getContext().getSession().getAttribute("customer")));
				if(c!=null)
				{
					vodrto.setEmail(c.getEmail());
				}
				
				if(vodrto.getAddress() != null)
					vodrto.setAddress(EscapeHtml.htmlEncode(vodrto.getAddress()));
				
				if(!validateResult)
					return new ActionResult("Illegal parameters!", ActionResult.HTML);

				ServiceResponse sr = getCustomerService().vip_reserve(vodrto);
				
            	if(sr.isSucc()) {
            		return new ActionResult(c.getEmail(), ActionResult.HTML);
            	}
            	else
            		return new ActionResult("Internal Error! Upgrading VIP failed!", ActionResult.HTML);
            }
        });
		
	}
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new JsImport("../js/viporder_en.js"));
            //headElements.add(new CssImport("./css/booking.css"));
        }
        return headElements;
    }
}
