package com.hino.page.en;

import java.util.List;

import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Submit;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.EmailField;
import org.apache.click.util.Bindable;

import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class ResetPassword extends BasePage {
	private static final long serialVersionUID = -421125561789927053L;

	@Bindable private String resetCode;
	@Bindable private Form emailForm = new Form("emailform");
	@Bindable private Form newPsdForm = new Form("newpsdform");

	private EmailField targetEmail;
	private PasswordField pass1;
	private PasswordField pass2;
	
    @Override
    public void onInit() {
		
    	if(resetCode == null) {
			// ----- email form -----
	    	emailForm.setAttribute("class", "niceform");
	
	    	targetEmail = new EmailField("email", "Email", 50, true);
			emailForm.add(targetEmail);
			
			emailForm.add(new Submit("sendEmail", "Send reset email", this, "onSendEmailClick"));
			emailForm.add(new Submit("cancel", "cancel", this, "onCancelClick"));
    	}
    	else {
			// ----- reset password form -----
			newPsdForm.setAttribute("class", "niceform");
	
			pass1 = new PasswordField("pass1", "Password", 50, true);
			pass1.setMinLength(6);
			pass2 = new PasswordField("pass2", "Confirm Password", 50, true);
			pass2.setMinLength(6);
			
			newPsdForm.add(new HiddenField("resetCode", resetCode));
			newPsdForm.add(pass1);
			newPsdForm.add(pass2);
			newPsdForm.add(new Submit("save", "Change password", this, "onNewPsdClick"));
    	}
    }

    /**
     * Send email with reset link
     * @return
     */
    public boolean onSendEmailClick() {
        if (emailForm.isValid()) {
        	String email = targetEmail.getValue();
        	
        	ServiceResponse rsp = getWebService().customerResetPsdEmail(email, true);
        	
        	if(!rsp.isSucc()) {
        		List<Integer> li = rsp.getInfo_code();
    			for(int index : li)
    			{
    				emailForm.setError(Info.getMsg(index));
    			}
        	}
        	else {
        		// Show javascript message window and redirect the page 
    			getHeadElements().add(new JsScript("alert('Password resetting email has been sent. Please check your email box.')"));
    			getHeadElements().add(
    					new JsScript("window.location = './index.htm'"));
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

    /**
     * Update password
     * @return
     */
	public boolean onNewPsdClick() {
		if(newPsdForm.isValid())
    	{
    		if(!pass1.getValue().equals(pass2.getValue()))
    		{
    			newPsdForm.setError("Inconsistent password");
    			return true;
    		}

    		ServiceResponse rsp = getWebService().customerResetPsd(resetCode, pass1.getValue());
    		
    		if(!rsp.isSucc())
    		{
    			List<Integer> li = rsp.getInfo_code();
    			for(int index : li)
    			{
    				newPsdForm.setError(Info.getMsg(index));
    			}
    			
    		} else
    		{
    			getHeadElements().add(new JsScript("alert('Password changed.')"));
    			getHeadElements().add(new JsScript("window.location = './index.htm'"));
    		} 		
    	}
        return true;
    }
}
