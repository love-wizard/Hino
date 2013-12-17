package com.hino.page.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ControlRegistry;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Button;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.element.Element;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hino.dto.BookingReserveDto;
import com.hino.dto.ReserveBookingDto;
import com.hino.dto.SalesBookingDto1;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.Voucher;
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class SalesBookingForm extends BasePage {
	
	@Bindable
	private String data;
	
	@Bindable
	private Integer gid;
	
	@Bindable 
	private Integer bktype;
	
	@Bindable 
	private String groupinfo;
	
	@Bindable
	public Button internal_reserve = new Button("internal_reserve", "d");
	@Bindable
	public Button load_customer = new Button("load_customer", "load_customer");
	@Bindable
	public Button check_voucher = new Button("check_voucher", "check_voucher");
	
	public HiddenField idfield = new HiddenField("idfield", Integer.class);
	public HiddenField bktypefield = new HiddenField("bktypefield", Integer.class);
	
	public void onInit()
	{
		if (bktype==null)
		{
			bktype=0;
		}
		bktypefield.setValueObject(bktype);
		Group g = null;
		if(gid!=null)
		{
			g = this.getSalesService().viewGroupById(gid);
		}
		
		if(g!=null)
		{   		
			//+Kevin Zhong - 05/09/2012 - TD#4
			if (bktype == 0)  
			{
				groupinfo = g.genBookingInfo();
			}else
			{
				groupinfo = g.genGroupBookingInfo();
			}
			//Kevin Zhong - 02/10/2012 - TD#32 Add group vip price
			groupinfo = groupinfo + " Max Point use: " + g.getMax_point() + ";Standard Price: " + g.getPrice() + "; VIP Price: " + g.getVip_price() + "; Group Price: " + g.getGroup_price() + "; Group VIP Price: " + g.getGroup_vip_price() + "; Group End Time: " + TimeFormater.format3(g.getGroupon_end_time()) + ";Voucher match code: " + g.getVoucher_match();
			//-Kevin Zhong - 05/09/2012 - TD#4
			
			List<String> l = g.genPickupFull();
			data = "<script>var ginfo = [";
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
			Matcher m;  
			for (int i=0;i<l.size();i++)
			{
				m = p.matcher(l.get(i));  
				data = data + "\"" + m.replaceAll("") + "\"";
				
				if(i!=l.size()-1)
				{
					data = data + ",";
				}
			}
			data = data + 
			"];\n var gmaxpoint = "+g.getMax_point()+
			";\n var s_price = "+g.getPrice()+
			";\n var v_price = "+g.getVip_price()+
			"\n var go_price = "+g.getGroup_price()+
			"\n var go_vip_price = "+g.getGroup_vip_price()+
			"\n var sk_price = "+g.getSeckill_price()+
			"\n var sk_vip_price = "+g.getSeckill_vip_price()+
			"\n var bkt = "+bktype+
			";</script>";
			idfield.setValueObject(gid);
			
			
			
		} else
		{
			data ="<script>var ginfo = [\"Not exist\"];</script>";
			idfield.setValueObject(null);
			bktypefield.setValueObject(null);
		}
	
		
		internal_reserve.setId("internal_reserve");
		internal_reserve.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata = getContext().getRequest().getParameter("jsondata");
            	int groupid = Integer.parseInt(getContext().getRequest().getParameter("gid"));
            	int btype = Integer.parseInt(getContext().getRequest().getParameter("bktype"));
            	
            	ObjectMapper mapper = new ObjectMapper();
            	List<BookingReserveDto> result = null;
            	try {
					 result = mapper.readValue(jsondata, TypeFactory.collectionType(ArrayList.class, BookingReserveDto.class));
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.println("result=" + result);
				
				Staff s = (Staff)getContext().getSession().getAttribute("staff");
				if (s==null)
				{
					return new ActionResult("No Action Right！", ActionResult.HTML);
				}
				ServiceResponse sr = getSalesService().internal_reserve_booking(groupid, result, s.getId(), btype);
				if(sr.isSucc())
				{
					jsondata = "Process successfully！";
				} else
				{
					jsondata = EscapeHtml.nrTonnrr(sr.list_msg());
				}
            	//System.out.println(result.get(0) + "gid:" + groupid);
                return new ActionResult(jsondata, ActionResult.HTML);
            }
        });
		
		check_voucher.setId("check_voucher");
		addControl(check_voucher);
		//load_customer.
		check_voucher.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String vcode = getContext().getRequest().getParameter("vcode");
            	String groupid = getContext().getRequest().getParameter("groupid");
            	
            	int i = Integer.parseInt(groupid);
            	
            	ServiceResponse sr = getSalesService().check_voucher(vcode, i);
            	String jsondata = "";
            	if(sr.isSucc())
            	{
            		Voucher v = (Voucher)sr.getResponse();
            		jsondata = "{" + "\"values\":\"" + v.getValue() +"\", \"dates\":\""+TimeFormater.format2(v.getExpire_date())+"\"}";	
            	} else
            	{
            		jsondata = "{"+"\"result\":\"0\"}";
            	}
                return new ActionResult(jsondata, ActionResult.JSON);
            }
        });
		
		load_customer.setId("load_customer");
		addControl(load_customer);
		//load_customer.
		load_customer.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String email = getContext().getRequest().getParameter("email");
            	
            	ServiceResponse sr = getCustomerService().getCustomerByEmail(email);
            	String jsondata = "";
            	if(sr.isSucc())
            	{
            		
            		Customer c = (Customer)sr.getResponse();
            		jsondata = "{" + "\"fn\":\"" +c.getFirstname() + "\", \"ln\":\""+c.getLastname()+"\", \"cn\":\""+c.getChinesename() + "\", \"gender\":\"" + c.getGender() + "\", \"pt\":\"" +c.getPoint() + "\", \"isvip\":\"" +c.genIsVip() + "\", \"phone\":\"" + c.getPhone() +"\"}";
            		
            	} else
            	{
            		jsondata = "{"+"\"result\":\"0\"}";
            	}
                return new ActionResult(jsondata, ActionResult.JSON);
            }
        });
		
	}
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            //headElements.add(new JsImport("/assets/js/jquery-1.4.2.js"));
            headElements.add(new JsScript("/internal/js/sales_booking_form.js", new HashMap()));
        }
        return headElements;
    }
}
