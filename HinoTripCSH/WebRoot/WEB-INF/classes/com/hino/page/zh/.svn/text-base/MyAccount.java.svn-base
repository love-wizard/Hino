package com.hino.page.zh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Button;
import org.apache.click.control.Form;
import org.apache.click.control.Table;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;

import com.hino.click.extension.GroupCalendarDiv;
import com.hino.dto.CustomerBasicInfoDto;
import com.hino.dto.CustomerPasswordDto;
import com.hino.dto.GroupSurveyAnswerDto;
import com.hino.dto.GroupSurveyDto;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class MyAccount extends BasePageOther {
	private static final long serialVersionUID = -7633737420541155842L;
	
	@Bindable private Form surveyForm = new Form("surveyForm");
	@Bindable private Form basicForm = new Form("basic_form");
	@Bindable private Form psdForm = new Form("psd_form");
	@Bindable private Table booking_table = new Table("booking_table");
	@Bindable private String notice = "";
	@Bindable private List<Group> recomandlist;//经理推荐
	@Bindable private List<Group> top10list;//top 10
	@Bindable private List<Group> secopeninglist;
	@Bindable private List<Group> golist;
	@Bindable private List<Group> surveyList;
	@Bindable private Group vipGroup;
	@Bindable private int y;
	@Bindable private int m;
	public ActionLink ticketLink = new ActionLink("ticket", "电子票");
	public ActionLink feedbackLink = new ActionLink("feedback", "反馈问卷");
	public Button commit_survey = new Button("commit_survey", "提交问卷");
	
	
	@Bindable
	public Button view_booking = new Button("view_booking", "d");
	
	@Bindable
	public Button ajax_groups = new Button("ajax_groups", "ag");
	
	/**
	 * Devon King - 23/09/2012 - T#21 Basic info submit button and reset password button
	 */
	@Bindable
	public Button acc_basic = new Button("acc_basic", "acc_basic");
	@Bindable
	public Button acc_pass = new Button("acc_pass", "acc_pass");
	
	public List<Booking> mybooking;
	public List<Booking> mydepart;
    @Override
    public void onInit() {
    	
    	qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);		
    	notice=Info.indexText;
    	
    	// Customers must log in to visit this page    	
    	if(cur_customer == null) {
    		setRedirect(Index.class);
        	return;
    	}
    	    
    	
    	ajax_groups.setId("ajax_groups");
    	ajax_groups.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String date = getContext().getRequest().getParameter("date");
            	StringBuffer jsondata = new StringBuffer();
            	Calendar tdate = Calendar.getInstance();
            	String[] ymd = date.split("-");
            	tdate.set(Integer.valueOf(ymd[0]), Integer.valueOf(ymd[1]) - 1, Integer.valueOf(ymd[2]), 0, 0, 0);
            	System.out.println(tdate.getTime());
            	List<Group> groups = getWebService().getGroupsByDateRange(-1, tdate, tdate);
            	
            	jsondata.append("{\"date\":\"").append(date).append("\", \"groups\": [");
    			
            	boolean isFirstComingIn = true;
            	
            	for(Group g : groups) {
            		
        			// skip the first time
        			if(!isFirstComingIn) {
        				jsondata.append(", ");		            				
        			} else {
        				isFirstComingIn = false;
        			}
        			
        			StringBuffer groupitem = new StringBuffer();
        			
        			groupitem.append("{\"id\":\"")
        					 .append(g.getId())
        					 .append("\",\"n\":\"")
        					 .append(g.getName())
        					 .append("\",\"sn\":\"")
        					 .append(g.genMobileGroupName())
        					 .append("\",\"tu\":\"")
        					 .append("../" + Info.EXTERNAL_PATH_PREFIX + g.getRoute().getThumbUrl())
        					 .append("\"}");
        			
        			jsondata.append(groupitem);	
            	}
            	
            	jsondata.append("]}");
            	
            	return new ActionResult(jsondata.toString(), ActionResult.JSON);
            }
        });
    	
    	view_booking.setId("view_booking");
    	view_booking.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String booking_ref = getContext().getRequest().getParameter("bookingref");
            	String pmval = getContext().getRequest().getParameter("pmval");
            	
				String result_json ="";
				if(null != pmval & !"".equals(pmval)) {
					getCustomerService().update_booking_payment_method(booking_ref, Integer.valueOf(pmval));
				}
				
				Booking b = getCustomerService().view_booking(booking_ref, cur_customer.getId());
								
				//System.out.println(b.getBookingRef() + " id=" +b.getCustomer().getId());
				//System.out.println(b.getBookingRef());
            	if(b!=null) {
            		int isOverseaRoute = (Info.IsOverseaRoute(b.getGroup().getRoute().getId())?1:0);
            		
            		result_json= "{\"result\":\"0\", \"bookingsum\":\""+b.genBookingSummary()+"\", \"status\":\""+ b.getStatus() +"\", \"pm\":\""+b.getBooking_method()
            				+"\", \"topy\":\""+b.getPd_credit()+"\", \"groupname\":\""+b.getGroup().getName()+"\", \"overseaRoute\":\""+isOverseaRoute+
            				"\", \"cn\":\""+b.genFullname()+"\", \"groupname_en\":\""+b.getGroup().getName_en()+ "\", \"email\":\""+b.getCustomer().getEmail()+"\"}";
            		result_json = EscapeHtml.nrTobr(result_json);
            		//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa" + result_json);
            		return new ActionResult(result_json, ActionResult.JSON);
            	}
            	else
            	{//
            		
            		//String errors = EscapeHtml.nrTobr(sr.list_msg());
            		result_json= "{\"result\":\"1\", \"message\":\"Sorry! Booking failed!\"}";
            		//result_json= "{\"result\":\"1\", \"message\":\"Sorry! Booking failed!\"}";
            		return new ActionResult(result_json, ActionResult.JSON);
            	}
            }
        });
    	
    	acc_basic.setId("acc_basic");
    	acc_basic.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata ="";            	
            	String fn = getContext().getRequest().getParameter("fn");
            	String ln = getContext().getRequest().getParameter("ln");
            	String cn = getContext().getRequest().getParameter("cn");
            	String ph = getContext().getRequest().getParameter("ph");
            	String uni = getContext().getRequest().getParameter("uni");
            	String ad = getContext().getRequest().getParameter("ad");
            	        		
            	
            	CustomerBasicInfoDto basicinfodto = new CustomerBasicInfoDto();
            	//Set id
            	basicinfodto.setId(cur_customer.getId());
            	
            	//Set new values
            	basicinfodto.setFirstname(fn.trim());
            	basicinfodto.setLastname(ln.trim());
            	basicinfodto.setChinesename(cn.trim());
            	basicinfodto.setTelephone(ph.trim());
            	basicinfodto.setUniversity(uni.trim());
            	basicinfodto.setAddress(ad.trim());            	
            	        		
        		ServiceResponse updateRes = getCustomerService().update_customer_basicinfo(basicinfodto);
        		
            	if(updateRes.isSucc() == true) {
            		// Update session
            		getContext().getSession().setAttribute("customer", updateRes.getResponse());           		
        			
        			jsondata = "{" + "\"result\":\"1\",\"data\":\"成功\"}";
        	        return new ActionResult(jsondata, ActionResult.JSON);
            	}
            	else {
            		
            		jsondata = "{" + "\"result\":\"0\",\"data\":\"失败\"}";
            		return new ActionResult(jsondata, ActionResult.JSON);
            	}				            	
            }
        });
    	
    	
    	acc_pass.setId("acc_pass");
    	acc_pass.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata ="";     
            	boolean dataValid = true;
            	
            	String po = getContext().getRequest().getParameter("po");
            	String p1 = getContext().getRequest().getParameter("p1");
            	String p2 = getContext().getRequest().getParameter("p2");
            	
            	if(null == po || "".equals(po.trim())) {
            		dataValid = false;
            	}
            	
            	if(null == p1 || "".equals(p1.trim())) {
            		dataValid = false;
            	}
            	
            	if(null == p2 || "".equals(p2.trim())) {
            		dataValid = false;
            	}
            	
            	if(!p1.equals(p2)) {
            		dataValid = false;
            	}
            	
            	if(dataValid) {
	            	ServiceResponse updateRes = getCustomerService().resetAccountPassword(po.trim(), p1.trim(), cur_customer.getId());
	        		if (updateRes.isSucc() == true ){
	        			
	            		// Update session
	            		getContext().getSession().setAttribute("customer", updateRes.getResponse());    
	        		}else{
	        			
	        			dataValid = false;
	        		}
            	}
            	
            	if(dataValid) {
            		
        			jsondata = "{" + "\"result\":\"1\",\"data\":\"成功\"}";
        	        return new ActionResult(jsondata, ActionResult.JSON);
            	} else {
            		
            		jsondata = "{" + "\"result\":\"0\",\"data\":\"失败\"}";
            		return new ActionResult(jsondata, ActionResult.JSON);
            	}				            	
            }
        });
    	
    	commit_survey.setId("commit_survey");
    	commit_survey.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	boolean dataValid = true;
            	String jsondata = new String();
            	Integer gid;
            	gid = Integer.parseInt(getContext().getRequest().getParameter("gid"));
            	List<GroupSurveyAnswerDto> lgsaDto= new ArrayList<GroupSurveyAnswerDto>();
            	GroupSurveyDto gsDto = new GroupSurveyDto();
            	
            	for(int i=1; i < 13 ; i ++ )
            	{
            		GroupSurveyAnswerDto gsaDto = new GroupSurveyAnswerDto();
            		String pf = new String();
            		String yj = new String();
            		String ot = new String();
            		gsaDto.setTopic_xh(i);
            		pf = getContext().getRequest().getParameter("r"+i);
            		yj = getContext().getRequest().getParameter("c"+i);
            		ot = getContext().getRequest().getParameter("t"+i);
            		
            		if(i==1||i==3||i==4||i==5||i==6||i==7)
            		{
            			if( pf==null || "".equals(pf))
            			{
            				jsondata = "{" + "\"result\":\"1\",\"data\":\"请完成问卷所有内容，谢谢\"}";
                    		return new ActionResult(jsondata, ActionResult.JSON);
            			}
            		
            		}
            		if(i==2||i==8||i==9||i==10)
            		{
            			if( yj==null || "".equals(yj))
            			{
            				jsondata = "{" + "\"result\":\"1\",\"data\":\"请完成问卷所有内容，谢谢\"}";
                    		return new ActionResult(jsondata, ActionResult.JSON);
            			}
            		
            		}
            		if(i==11||i==12)
            		{
            			if( ot==null || "".equals(ot))
            			{
            				jsondata = "{" + "\"result\":\"1\",\"data\":\"请完成问卷所有内容，谢谢\"}";
                    		return new ActionResult(jsondata, ActionResult.JSON);
            			}
            		
            		}
            		
            		gsaDto.setOther(ot);
            		gsaDto.setAnswer(pf+";"+yj);
            		
            		lgsaDto.add(gsaDto);
            	}
            	
            	
            	dataValid = onSurveySubmitClick(gid,gsDto,lgsaDto);
				if(dataValid) {
            		
        			jsondata = "{" + "\"result\":\"0\",\"data\":\"成功\"}";
        	        return new ActionResult(jsondata, ActionResult.JSON);
            	} else {
            		
            		jsondata = "{" + "\"result\":\"1\",\"data\":\"失败\"}";
            		return new ActionResult(jsondata, ActionResult.JSON);
            	}
            }
        });
    }

    /**
     * Update customer basic information
     * @return
     */
    public boolean onBasicSaveClick() {
    	System.out.println("onBasicSaveClick");
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
    
    public boolean onSurveySubmitClick(Integer gid, GroupSurveyDto gsDto, List<GroupSurveyAnswerDto> lgsaDto) {
    	
    	cur_customer = (Customer)this.getContext().getSession().getAttribute("customer");
    	gsDto.setCustomer(cur_customer);
    	ServiceResponse updateRes = this.getGroupSurveyService().setupGroupSurvey(gid, gsDto, lgsaDto);
    	
    	if(updateRes.isSucc() == true) {
			getHeadElements().add(new JsScript("alert('更新信息成功')"));
	        return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    public void onRender()
    {
    	// Customers must log in to visit this page    	
    	if(cur_customer == null) {
    		setRedirect(Index.class);
        	return;
    	}
    	
    	if(!cur_customer.genIsVip()) {
        	// Get VIP groups
    		List<Group> vipGroups = this.getWebService().getGroupByRouteId(60);
    		if(vipGroups.size() > 0) {
				vipGroup = vipGroups.get(0);
				addModel("vipGroup", vipGroup);
    		}
    	}
    	
    	recomandlist = (List<Group>)this.getSalesService().list_group_by_group_tag(100,Info.GROUP_TAG_SELL_TYPE, Info.GROUP_TAG_SELL_TYPE_MANAGE_RECOMAND);
    	top10list = (List<Group>)this.getSalesService().getTop10HotestGroups();
    	secopeninglist= this.getWebService().getSecGroup(-1, 0);
    	golist= this.getWebService().getGoGroup(-1, 0);
			//return getCustomerService().getMyBookingList(curCustomer.getId());
    	surveyList = this.getGroupSurveyService().getSurveyBookingGroup(cur_customer.getId());
    	
    	List<Booking> bookinglist = getCustomerService().getMyBookingList(cur_customer.getId());
		mybooking = new ArrayList<Booking>();
		
		for(Booking b:bookinglist)
		{
			if (Calendar.getInstance().after(b.getGroup().getDepart_date())) {
				mybooking.add(b);
			}
		}
		
		mydepart = new ArrayList<Booking>();
		for(Booking b:bookinglist)
		{
			if (Calendar.getInstance().before(b.getGroup().getDepart_date())) {
				mydepart.add(b);
			}
		}
		
		if(y == 0 || m == 0) {
			Calendar currentDate = Calendar.getInstance();
			y = currentDate.get(Calendar.YEAR);
			m = currentDate.get(Calendar.MONTH) + 1;
		}		
		
		GroupCalendarDiv gcDiv = new GroupCalendarDiv("", golist, y, m - 1);
		
		addModel("gcDiv", gcDiv);
    }
    
    @Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new JsImport("./js/jquery.form.js"));
            headElements.add(new JsImport("./js/myac.js"));
            headElements.add(new CssImport("./images/user.css"));
        }
        return headElements;
    }

}
