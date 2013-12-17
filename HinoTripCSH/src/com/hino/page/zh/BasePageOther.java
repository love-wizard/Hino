package com.hino.page.zh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ControlRegistry;
import org.apache.click.Page;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
import org.apache.click.control.Form;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Submit;
import org.apache.click.extras.control.EmailField;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.dto.BookingReserveDto;
import com.hino.dto.CustomerLoginDto;
import com.hino.model.Customer;
import com.hino.model.QQService;
import com.hino.model.Staff;
import com.hino.model.Voucher;
import com.hino.page.Index;
import com.hino.service.AirlineService;
import com.hino.service.CustomerService;
import com.hino.service.GroupSurveyService;
import com.hino.service.HumanResourceService;
import com.hino.service.NoticeService;
import com.hino.service.SalesService;
import com.hino.service.SearchResultService;
import com.hino.service.TravelResourceService;
import com.hino.service.WebService;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class BasePageOther extends Page implements ApplicationContextAware{
	

	private static final long serialVersionUID = -2102122967024400020L;
	protected ApplicationContext applicationContext;
	@Bindable protected int page_type = 0;
	@Bindable public String pathpre = Info.EXTERNAL_PATH_PREFIX;
	@Bindable protected Customer cur_customer;// = null;
	@Bindable protected int curMonth = 0; // Ken Chen 2012/09/16 - Index 最新团次链接到当前月份搜索

	@Bindable protected Button login_submit = new Button("login_action","lgi"); 
	@Bindable protected Button logout_submit = new Button("logout_action","lgo");
	@Bindable protected Button check_voucher = new Button("check_voucher", "check_voucher");
	@Bindable protected Button resetpwd_submit = new Button("resetpwd_action","resetpwd");	//Ken Chen 2013/03/24 TD#128 找回用户注册密码
	@Bindable List<QQService> qqList;
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		applicationContext = arg0;
		
	}
	
	public BasePageOther()
	{
		
		//Devon King - hot search of months
		int month = 0;
		ArrayList<Integer> mList = new ArrayList<Integer>();
		Calendar curCal = Calendar.getInstance();
		month = curCal.get(Calendar.MONTH) + 1;
		curMonth = month;
		for (int i = 0; i <5; i ++){
			mList.add((month + i) == 12 ? 12: (month + i) % 12);
		}
		addModel("mlist", mList);
		
		cur_customer = (Customer)this.getContext().getSession().getAttribute("customer");
		login_submit.setId("login_action");
		login_submit.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String username = getContext().getRequest().getParameter("un");
            	String password = getContext().getRequest().getParameter("ps");
            	String error = "";
            	CustomerLoginDto cdto = new CustomerLoginDto();
                cdto.setEmail(username);
                cdto.setPassword(password);
                ServiceResponse sr = getWebService().customer_login(cdto);
                String jsondata = "";
            	
                if(sr.isSucc())
                {
                	Customer c = (Customer)sr.getResponse();
                	getContext().setSessionAttribute("customer", c);
                	//setRedirect(Index.class);
                	jsondata = "{" + "\"result\":\"0\","+ "\"fn\":\"" +c.getFirstname() + "\", \"ln\":\""+c.getLastname() +"\"}";
                	System.out.println(jsondata);
                	
                } else
                {
                	//Info.toStringInfo(sr.getInfo_code())
                	jsondata = "{" + "\"result\":\"登录失败，请检查用户名密码\"}";
                	System.out.println(jsondata);
                }
            	
            	/*
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
				//System.out.println("result=" + result);
				
				Staff s = (Staff)getContext().getSession().getAttribute("staff");
				if (s==null)
				{
					return new ActionResult("No Action Right！", ActionResult.HTML);
				}
				ServiceResponse sr = getSalesService().internal_reserve_booking(groupid, result, s.getId(),btype);
				if(sr.isSucc())
				{
					jsondata = "Process successfully！";
				} else
				{
					jsondata = EscapeHtml.nrTonnrr(sr.list_msg());
				}
				*/
            	//System.out.println(result.get(0) + "gid:" + groupid);
                return new ActionResult(jsondata, ActionResult.JSON);
            }
        });
		
		logout_submit.setId("logout_action");
		logout_submit.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	getContext().getSession().invalidate();

                String jsondata = "";
                        	
                jsondata = "{" + "\"result\":\"登出成功！欢迎再次光临\"}";       	
                return new ActionResult(jsondata, ActionResult.JSON);
            }
        });
		
		// Ken Chen 2013-03-24 TD#128 添加找回密码功能
		resetpwd_submit.setId("resetpwd_action");
		resetpwd_submit.addBehavior(new DefaultAjaxBehavior() {
			public ActionResult onAction(Control source) {
            	String username = getContext().getRequest().getParameter("un");
            	String password = getContext().getRequest().getParameter("ps");
            	String error = "";
            	CustomerLoginDto cdto = new CustomerLoginDto();
                cdto.setEmail(username);
                //cdto.setPassword(password);
                ServiceResponse sr = getWebService().customer_resetpwd(cdto);
                String jsondata = "";
            	
                if(sr.isSucc())
                {
                	jsondata = "{" + "\"result\":\"0\","+ "\"fn\":\"" + username + "\", \"ln\":\""+"\"}";
                	System.out.println(jsondata);
                	
                } else
                {
                	jsondata = "{" + "\"result\":\"密码重置失败，请检查用户名\"}";
                	System.out.println(jsondata);
                }
            	
                return new ActionResult(jsondata, ActionResult.JSON);
            }
        });
		
		check_voucher.setId("check_voucher");
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
            		jsondata = "{" + "\"values\":\"" + v.getValue() +"\", \"dates\":\""+TimeFormater.format2(v.getExpire_date())+"\", \"usage\":\""+(v.getNo_of_use()-v.getNo_used())+"\"}";
            	} else if (sr.getInfo_code().contains(1)) {
            		jsondata = "{"+"\"result\":\"1\"}";
            	} else
            	{
            		jsondata = "{"+"\"result\":\"0\"}";
            	}
                return new ActionResult(jsondata, ActionResult.JSON);
            }
        });
	}
	
	@Override
	public void onInit()
	{
		
	}
	@Override
    public void onRender()
	{
		
	}
	
	@Override
	public String getTemplate() {
		return "./zh/basepage.htm";
	}
	
	protected AirlineService getAirlineService()
	{
		return (AirlineService)applicationContext.getBean("airlineServiceImpl");
	}
	
	protected TravelResourceService getTravelResourceService()
	{
		return (TravelResourceService)applicationContext.getBean("travelResourceServiceImpl");
	}

	protected WebService getWebService()
	{
		return (WebService)applicationContext.getBean("webServiceImpl");
	}

	protected CustomerService getCustomerService()
	{
		return (CustomerService)applicationContext.getBean("customerServiceImpl");
	}
	
	protected SalesService getSalesService()
	{
		return (SalesService)applicationContext.getBean("salesServiceImpl");
	}
	
	/**
	 * Get an instance of SearchResultService class from Sprint Bean Container
	 * 
	 * @author Devon King
	 * @return An instance of SearchResultService class
	 */
	protected SearchResultService getSearchResultService(){
		
		return (SearchResultService)applicationContext.getBean("searchResultServiceImpl");
	}
	//Kevin Zhong - 26/09/2012 - NoticeBoard
	protected NoticeService getNoticeService(){
		
		return (NoticeService)applicationContext.getBean("noticeServiceImpl");
	}
	
	protected HumanResourceService getHumanResourceService()
	{
		return (HumanResourceService)applicationContext.getBean("humanResourceServiceImpl");
	}
	
	protected GroupSurveyService getGroupSurveyService()
	{
		return (GroupSurveyService)applicationContext.getBean("groupSurveyServiceImpl");
	}
}
