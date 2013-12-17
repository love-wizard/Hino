package com.hino.page.zh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.*;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.EmailField;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hino.dto.BookingReserveDto;
import com.hino.model.Customer;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class Registration extends BasePageOther{
	private static final long serialVersionUID = -7459342666984226689L;
	
	
	@Bindable
	private Button reg_step1 = new Button("reg_step1", "reg_step1");
	@Bindable
	private Button reg_step2 = new Button("reg_step2", "reg_step2");
	@Bindable
	private Button reg_step3 = new Button("reg_step3", "reg_step3");
	
	public void onInit()
	{
		//submit.setAttribute("class", "reg_submit");
		qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);				
		reg_step2.setId("reg_step2");
		reg_step2.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata ="";
            	String email = getContext().getRequest().getParameter("em");
            	String fn = getContext().getRequest().getParameter("fn");
            	String ln = getContext().getRequest().getParameter("ln");
            	String cn = getContext().getRequest().getParameter("cn");
            	String ph = getContext().getRequest().getParameter("ph");
            	String uni = getContext().getRequest().getParameter("uni");
            	String city = getContext().getRequest().getParameter("city");
            	int gender = Integer.parseInt(getContext().getRequest().getParameter("gender"));
            	String pass1 = getContext().getRequest().getParameter("pass1");
            	String pass2 = getContext().getRequest().getParameter("pass2");
            	
            	if(!pass1.equals(pass2))
        		{
            		jsondata = "{" + "\"result\":\"1\",\"data\":\"两次密码不同\"}";
        			return new ActionResult(jsondata, ActionResult.JSON);
        		}
        		
        		if(pass1.length()<6)
        		{
        			jsondata = "{" + "\"result\":\"1\",\"data\":\"密码需大于5位\"}";
        			return new ActionResult(jsondata, ActionResult.JSON);
        		}
        		
        		ServiceResponse sr = getCustomerService().registeration(email, fn, ln, pass1);
        		
        		if(!sr.isSucc())
        		{
        			jsondata = "{" + "\"result\":\"1\",\"data\":\"该email已注册！\"}";
        			return new ActionResult(jsondata, ActionResult.JSON);
        		} else
        		{
        			getCustomerService().update_customer_basic_2(email, uni, ph, city, cn, gender);
        			
        			
        			jsondata = "{" + "\"result\":\"0\",\"data\":\"成功\"}";
        			System.out.println(jsondata);
        			return new ActionResult(jsondata, ActionResult.JSON);
        		}
				
            	
            }
        });
		
		reg_step1.setId("reg_step1");
		reg_step1.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata ="";
            	String email = getContext().getRequest().getParameter("em");
            	

            	
        		
        		ServiceResponse sr = getCustomerService().getCustomerByEmail(email);
        		
        		if(sr.isSucc())
        		{
        			jsondata = "{" + "\"result\":\"1\",\"data\":\"该email已注册！\"}";
        			return new ActionResult(jsondata, ActionResult.JSON);
        		} else
        		{
        			//getCustomerService().update_customer_basic_2(email, uni, ph, city);
        			
        			jsondata = "{" + "\"result\":\"0\",\"data\":\"成功\"}";
        			return new ActionResult(jsondata, ActionResult.JSON);
        		}
				
            	
            }
        });
	}
	
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new JsImport("./js/reg.js"));
            headElements.add(new CssImport("./images/reg.css"));
            
        }
        return headElements;
    }
}
