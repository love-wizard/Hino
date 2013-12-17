package com.hino.page.en;

import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.EmailField;
import org.apache.click.extras.control.TelephoneField;
import org.apache.click.util.Bindable;

import com.hino.dto.CustomerLoginDto;
import com.hino.dto.DiyRouteDto;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class Login extends BasePage {

	@Bindable private Form form = new Form("form");
	@Bindable private String error;
	@Bindable private Integer gid;
	private EmailField cemailField;
	private PasswordField passwordField;

    @Override
    public void onInit() {
		
    	form.setMethod("post");
		form.setAttribute("class", "niceform");
		
		cemailField = new EmailField("email", "Email", 50, true);
		cemailField.setAttribute("class", "text-input");
		
		passwordField = new PasswordField("password", "Password", 50, true);
		passwordField.setAttribute("class", "text-input");
		
		Submit submit = new Submit("ok", "Sign in", this, "onOkClick"); 
		submit.setAttribute("class", "button");

		HiddenField groupIdFld = new HiddenField("gid", String.class);
		groupIdFld.setValue(String.valueOf(gid));
		form.add(groupIdFld);
		
		form.add(cemailField);
		form.add(passwordField);
		form.add(submit); 
    }

    public boolean onOkClick() {
        if (form.isValid()) { 
            CustomerLoginDto cdto = new CustomerLoginDto();
            cdto.setEmail(cemailField.getValue());
            cdto.setPassword(passwordField.getValue());
            ServiceResponse sr = getWebService().customer_login(cdto);

            if(sr.isSucc())
            {
            	getContext().setSessionAttribute("customer", sr.getResponse());
            	getHeadElements().add(new JsScript("alert('Welcome back.')"));
    			getHeadElements().add(
    					new JsScript("window.location = './booking.htm?gid="+gid+"'"));
            } else
            {
            	error = Info.toStringInfo(sr.getInfo_code());
            }
             
        } 
        return true; 
    }

}
