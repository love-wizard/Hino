package com.hino.page.internal;

import java.util.List;

import org.apache.click.Page;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Submit;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.EmailField;
import org.apache.click.util.Bindable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.service.WebService;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class ResetPassword extends Page implements ApplicationContextAware {
	private static final long serialVersionUID = 9165138557545153212L;
	
	@Bindable private String resetCode;
	@Bindable private Form emailForm = new Form("emailform");
	@Bindable private Form newPsdForm = new Form("newpsdform");

	private EmailField targetEmail;
	private PasswordField pass1;
	private PasswordField pass2;
	protected ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		applicationContext = arg0;
	}
	
	protected WebService getWebService()
	{
		return (WebService)applicationContext.getBean("webServiceImpl");
	}
	
    @Override
    public void onInit() {
		
    	if(resetCode == null) {
			// ----- email form -----
	    	emailForm.setAttribute("class", "niceform");
	
	    	targetEmail = new EmailField("email", "电子邮箱地址", 50, true);
	    	targetEmail.setAttribute("class", "text-input");
			emailForm.add(targetEmail);
			
			Submit sendEmail = new Submit("sendEmail", "发送重置密码邮件", this, "onSendEmailClick");
			sendEmail.setAttribute("class", "button");
			emailForm.add(sendEmail);
    	}
    	else {
			// ----- reset password form -----
			newPsdForm.setAttribute("class", "niceform");
	
			pass1 = new PasswordField("pass1", "密码", 50, true);
			pass1.setMinLength(6);
			pass1.setAttribute("class", "text-input");
			pass2 = new PasswordField("pass2", "确认密码", 50, true);
			pass2.setMinLength(6);
			pass2.setAttribute("class", "text-input");
			
			newPsdForm.add(new HiddenField("resetCode", resetCode));
			newPsdForm.add(pass1);
			newPsdForm.add(pass2);
			Submit save = new Submit("save", "提交新密码", this, "onNewPsdClick");
			save.setAttribute("class", "button");
			newPsdForm.add(save);
    	}
    }

    /**
     * Send email with reset link
     * @return
     */
    public boolean onSendEmailClick() {
        if (emailForm.isValid()) {
        	String email = targetEmail.getValue();
        	
        	ServiceResponse rsp = getWebService().staffResetPsdEmail(email);
        	
        	if(!rsp.isSucc()) {
        		List<Integer> li = rsp.getInfo_code();
    			for(int index : li)
    			{
    				emailForm.setError(Info.getMsg(index));
    			}
        	}
        	else {
        		// Show javascript message window and redirect the page 
    			getHeadElements().add(new JsScript("alert('重置密码邮件已发送到指定邮箱，请及时查收。')"));
    			getHeadElements().add(
    					new JsScript("window.location = './index.htm'"));
        	}
        }
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
    			newPsdForm.setError("两次密码不同");
    			return true;
    		}

    		ServiceResponse rsp = getWebService().staffResetPsd(resetCode, pass1.getValue());
    		
    		if(!rsp.isSucc())
    		{
    			List<Integer> li = rsp.getInfo_code();
    			for(int index : li)
    			{
    				newPsdForm.setError(Info.getMsg(index));
    			}
    			
    		} else
    		{
    			getHeadElements().add(new JsScript("alert('密码重置完成，即将返回首页。')"));
    			getHeadElements().add(new JsScript("window.location = './index.htm'"));
    		} 		
    	}
        return true;
    }
}
