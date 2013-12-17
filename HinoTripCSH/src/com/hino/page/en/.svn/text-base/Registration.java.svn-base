package com.hino.page.en;

import java.util.List;

import org.apache.click.control.*;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.EmailField;
import org.apache.click.util.Bindable;

import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class Registration extends BasePage{
	private static final long serialVersionUID = -7459342666984226689L;
	
	@Bindable
	private Form regForm = new Form("reg_form");
	private TextField fn = new TextField("fn","First Name", 50, true);
	private TextField ln = new TextField("ln","Last Name", 50, true);
	private EmailField email = new EmailField("email", "Email", 50, true);
	private PasswordField pass1 = new PasswordField("pass1", "Password", 50, true);
	private PasswordField pass2 = new PasswordField("pass2", "Confirm Password", 50, true);
	private Submit submit = new Submit("submit", "Submit", this, "onSaveClick");
	
	public void onInit()
	{
		regForm.add(email);
		regForm.add(fn);
		regForm.add(ln);
		regForm.add(pass1);
		regForm.add(pass2);
		regForm.add(submit);
		regForm.setAttribute("class", "niceform");
	}
	
	public boolean onSaveClick() {
		if(regForm.isValid())
    	{
    		if(!pass1.getValue().equals(pass2.getValue()))
    		{
    			regForm.setError("Inconsistent password");
    			return true;
    		}
    		
    		if(pass1.getValue().length()<6)
    		{
    			regForm.setError("Password must longer than 6 characters");
    			return true;
    		}
    		
    		ServiceResponse sr = getCustomerService().registeration(email.getValue(), fn.getValue(), ln.getValue(), pass1.getValue());
    		
    		if(!sr.isSucc())
    		{
    			List<Integer> li = sr.getInfo_code();
    			for(int index : li)
    			{
    				regForm.setError(Info.getMsg(index));
    			}
    			
    		} else
    		{
    			this.getContext().getSession().setAttribute("customer", sr.getResponse());
    			getHeadElements().add(new JsScript("alert('Registeration finished. Congratulations!')"));
    			getHeadElements().add(new JsScript("window.location = './index.htm'"));
    		} 		
    	}
        return true;
    }
}
