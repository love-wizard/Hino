package com.hino.page.internal;

import org.apache.click.Page;
import org.apache.click.control.*;
import org.apache.click.util.Bindable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.dto.StaffLoginDto;
import com.hino.model.Staff;
import com.hino.service.WebService;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;


public class Index extends Page implements ApplicationContextAware{
	@Bindable 
	private Form form;
	
	@Bindable
	private String error;
	
	private TextField staffnoField;
	private PasswordField passwordField;
	private ApplicationContext applicationContext;
	
	public Index()
	{
		form = new Form("form");
		form.setMethod("post");
		
		staffnoField = new TextField("staffno", "ID", true);
		staffnoField.setFocus(true);
		staffnoField.setAttribute("class", "text-input");
		
		passwordField = new PasswordField("password", "Password", true);
		passwordField.setFocus(true);
		passwordField.setAttribute("class", "text-input");
		
		Submit submit = new Submit("ok", "Login", this, "onOkClick"); 
		submit.setAttribute("class", "button");
		
		form.add(staffnoField);
		form.add(passwordField);
		form.add(submit); 

	}
	
	public boolean onOkClick() {
        if (form.isValid()) { 
            //User user = new User();
        	StaffLoginDto sdto = new StaffLoginDto();
            form.copyTo(sdto); 
            
            WebService ws = (WebService)applicationContext.getBean("webServiceImpl");
            ServiceResponse sr = ws.staff_login(sdto);

            //System.out.println("Form >SN:" + sdto.getStaffno() + " PWD" + sdto.getPassword()
            //		+" Success: "+sr.isSucc());
            
            if(sr.isSucc())
            {
            	getContext().setSessionAttribute("staff", (Staff)sr.getResponse());
            	setRedirect(MsgEmailManage.class);
            } else
            {
            	//form.setError(getMessage("authentication-error")); 
            	//setRedirect("http://www.baidu.com/");
            	error = Info.toStringInfo(sr.getInfo_code());
            }
             
        } 
        return true; 
    }

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
		
	}
	
}
