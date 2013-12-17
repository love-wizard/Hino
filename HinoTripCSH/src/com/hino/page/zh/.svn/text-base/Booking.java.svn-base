package com.hino.page.zh;

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

import com.hino.dto.AddressDto;
import com.hino.dto.BookingReserveDto;
//import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Voucher;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class Booking extends BasePageOther {
	
	private static final long serialVersionUID = 7200048873445736234L;

	@Bindable private Integer gid;
	@Bindable private Integer bt; 
	@Bindable long routeid;
	@Bindable String groupname;
	@Bindable String groupdate;
	//@Bindable String groupprice;
	@Bindable String targetprice;
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
	@Bindable String groupcredituse;
	@Bindable boolean isOverseaRoute;//TD#130 境外团的支付方式里面，请把支付方式3 paypal去掉，境外团不能用paypal支付
	
	@Bindable private Integer num;
	@Bindable private Integer post;
	@Bindable private String road;
	@Bindable private String city;
	@Bindable private String other;
	@Bindable private String postcode;
	
	@Bindable
	public Button external_reserve = new Button("external_reserve", "d");

	
	public HiddenField idfield = new HiddenField("idfield", Integer.class);
	public HiddenField tpfield = new HiddenField("tpfield", Integer.class);

	long cid = -1;
	
	public Booking()
	{
		external_reserve.setId("external_reserve");
		external_reserve.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata = getContext().getRequest().getParameter("jsondata");
            	//System.out.println("dddddddddddddddddddddddd");
            	//System.out.println(jsondata);
            	int bktype = Integer.parseInt(getContext().getRequest().getParameter("bt"));
            	int gid = Integer.parseInt(getContext().getRequest().getParameter("gid")); 
            	String rd = getContext().getRequest().getParameter("rd");
            	String ct = getContext().getRequest().getParameter("ct");
            	String ot = getContext().getRequest().getParameter("ot");
            	String pc = getContext().getRequest().getParameter("pc");
            	int pt = Integer.parseInt(getContext().getRequest().getParameter("pt"));
            	
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
				String result_json ="";
				//jsondata = "{" + "\"result\":\"登录失败，请检查用户名密码\"}";
//				long cid = -1;
				Customer c = ((Customer)(getContext().getSession().getAttribute("customer")));
				if(c!=null)
				{
					cid = c.getId();
				} else
				{
//					result_json= "{\"result\":1 ,\"message\":\"用户不存在\"}";
//					return new ActionResult(result_json, ActionResult.JSON);
					
				}
				
				boolean validateResult = true;
				for(int index=0; index<result.size(); index++) {
					BookingReserveDto curCBdto = result.get(index);
					if((curCBdto.getFn() == null || curCBdto.getFn().length() == 0)
						|| (curCBdto.getLn() == null || curCBdto.getLn().length() == 0)
						|| (curCBdto.getPickup() == null || curCBdto.getPickup().length() == 0)
						|| (curCBdto.getGender() != 0 && curCBdto.getGender() != 1)
						|| (curCBdto.getPaymethod() < Info.BKM_EXTERNAL_BANKTRANSFER 
						|| curCBdto.getPaymethod() > Info.BKM_EXTERNAL_BARCLAYCARD)
						|| (curCBdto.getPd_point()%100 != 0 
								|| curCBdto.getPd_point() > 
									result.size()*getCustomerService().getGroupById(gid).getMax_point())
						)
						validateResult = false;
					
					//curCBdto.setCid(cid);
					if(c!=null)
					{
						curCBdto.setEmail(c.getEmail());
					}
					
				}
				if(!validateResult) {
					result_json= "{\"result\":\"1\" ,\"message\":\"信息错误\"}";
					return new ActionResult(result_json, ActionResult.JSON);
				}
				
				AddressDto address = new AddressDto();
				address.setRoad(rd);				
				address.setCity(ct);
				address.setOther(ot);
				address.setPostcode(pc);
				address.setPost((pt == 1? true: false));
				ServiceResponse sr = getCustomerService().external_booking(gid, cid, result, bktype, address);
				
            	if(sr.isSucc()) {
            		String bookingRefs = sr.list_msg();
            		bookingRefs = bookingRefs.replaceAll("\n","");
            		bookingRefs = bookingRefs.replaceAll("\r","");
            		
            		com.hino.model.Booking b = getCustomerService().view_booking(bookingRefs);
            		
//            		result_json= "{\"result\":\"0\", \"message\":\""+bookingRefs+"\", \"bookingsum\":\""+b.genBookingSummary()+"\", \"pm\":\""+b.getBooking_method()+"\", \"topy\":\""+b.getPd_credit()+"\", \"groupname\":\""+b.getGroup().getName()+"\"}";
//            		result_json = EscapeHtml.nrTobr(result_json);
            		result_json= "{\"result\":\"0\", \"message\":\""+bookingRefs+"\", \"bookingsum\":\""+b.genBookingSummary()+"\", \"status\":\""+ b.getStatus() +"\", \"pm\":\""+b.getBooking_method()
            				+"\", \"topy\":\""+b.getPd_credit()+"\", \"groupname\":\""+b.getGroup().getName()+"\", \"overseaRoute\":\""+isOverseaRoute+
            				"\", \"cn\":\""+b.genFullname()+"\", \"groupname_en\":\""+b.getGroup().getName_en()+ "\", \"email\":\""+b.getCustomer().getEmail()+"\"}";
            		result_json = EscapeHtml.nrTobr(result_json);
            		//result_json= "{\"result\":\"0\", \"message\":\""+bookingRefs+"\"}";
            		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa" + result_json);
            		return new ActionResult(result_json, ActionResult.JSON);
            	}
            	else
            	{//
            		System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
            		String errors = EscapeHtml.nrTobr(sr.list_msg());
            		result_json= "{\"result\":\"1\", \"message\":\"Sorry! Booking failed!"+errors+"\"}";
            		//result_json= "{\"result\":\"1\", \"message\":\"Sorry! Booking failed!\"}";
            		return new ActionResult(result_json, ActionResult.JSON);
            	}
            	
            	
            }
        });
	}
	
	public void onInit()
	{
		qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);		
		//Customers must log in to booking
    	//Customer curCustomer = (Customer)getContext().getSession().getAttribute("customer");
    	
    	// Must log in before booking
		if(cur_customer == null) {
			//getHeadElements().add(new JsScript("alert('请登录后进行预订')"));
			//return;
			cur_customer = new Customer();
		}
		
		isOverseaRoute = false;
		if(gid!=null)
		{
			if (bt==null)
				bt= 0;
			
			Group targetGroup = getCustomerService().getGroupById(gid);
			
			isOverseaRoute = Info.IsOverseaRoute(targetGroup.getRoute().getId());
			
			// Invalid group id
			if(targetGroup == null) {
				getHeadElements().add(new JsScript("alert('选择的团次不存在，无法进行预订')"));
				getHeadElements().add(
						new JsScript("window.location = './route_detail.htm?gid="+gid+"'"));
				return;
			}
			
			// Get route information
			routeid = targetGroup.getRoute().getId();
			
			// Get group information
			groupname = targetGroup.getName();
			groupdate = TimeFormater.format2(targetGroup.getDepart_date());
			
			//groupprice = String.valueOf(targetGroup.getPrice());
			if (bt==0)
			{
				if(cur_customer.genIsVip())
				{
					targetprice = String.valueOf(targetGroup.getVip_price());
				} else
				{
					targetprice = String.valueOf(targetGroup.getPrice());
				}
			}
			if (bt==1)
			{
				if(cur_customer.genIsVip())
				{
					targetprice = String.valueOf(targetGroup.getGroup_vip_price());
				} else
				{
					targetprice = String.valueOf(targetGroup.getGroup_price());
				}
			}
			if (bt==2)
			{
				if(cur_customer.genIsVip())
				{
					targetprice = String.valueOf(targetGroup.getSeckill_vip_price());
				} else
				{
					targetprice = String.valueOf(targetGroup.getSeckill_price());
				}
			}
			groupcredituse = String.valueOf(targetGroup.getMax_point());
			
			// Get current customer information
			customerFisrtName = cur_customer.getFirstname();
			customerLastName = cur_customer.getLastname();
			customerCnName = cur_customer.getChinesename();
			if(cur_customer.getGender() == 0) {
				customerGenderM = "checked=\"checked\"";
			} else {
				customerGenderFM = "checked=\"checked\"";
			}
			customerEmail = cur_customer.getEmail();
			customerPhone = cur_customer.getPhone();
			customerCredit = String.valueOf(cur_customer.getPoint());
			
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
			
			//Devon King - 2012/09/30 - TD#20 Pass voucher setting to front end
			addModel("voucherApplied", targetGroup.getVoucher_applied());
			
			jsData = "<script language=\"javascript\">"+
				"var pickup = ["+pickupinfo+"];"+
				"var singleprice = "+targetGroup.getPrice()+";"+
				"var credit = "+customerCredit+";"+
				"var groupid = "+gid+";"+
				"var groupname = \""+targetGroup.getName()+"\";"+
				"var groupdesc = \""+targetGroup.getRoute().getThumbDesc()+"\";"+
				"var groupmaxcredit = \""+targetGroup.getMax_point()+"\";"+
				"var groupvipprice = \""+targetGroup.getVip_price()+"\";"+
				"var isvip = \""+cur_customer.genIsVip()+"\";"+
				//TD#137 客人自己预定的时候，只要有团购，客人付款的时候就付团购的价格
				"var bt = \""+ (bt == 0 && targetGroup.getGroup_price() > 0 && targetGroup.getSeats_groupon() > 0? 1: bt)+"\";"+
				"var groupprice = \""+targetGroup.getPrice()+"\";"+
				"var goprice = \""+targetGroup.getGroup_price()+"\";"+
				"var go_vip_price = \""+targetGroup.getGroup_vip_price()+"\";"+
				"var skprice = \""+targetGroup.getSeckill_price()+"\";"+
				"var sk_vip_price = \""+targetGroup.getSeckill_vip_price()+"\";"+
				"var booking_num = \""+ num +"\";"+
				"</script>";
			
			System.out.println(jsData);
		} 
		// No group id passed 
		else {
			getHeadElements().add(new JsScript("alert('没有选择团次，无法进行预订')"));
			getHeadElements().add(
					new JsScript("window.location = './index.htm'"));
			return;
		}
		
	}
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new JsImport("./js/ext_booking.js"));
            headElements.add(new CssImport("./images/pay.css"));
            
        }
        return headElements;
    }
}
