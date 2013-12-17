package com.hino.page;

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
	private TextField fn = new TextField("fn","名(PinYin)", 50, true);
	private TextField ln = new TextField("ln","姓(PinYin)", 50, true);
	private EmailField email = new EmailField("email", "Email", 50, true);
	private PasswordField pass1 = new PasswordField("pass1", "密码", 50, true);
	private PasswordField pass2 = new PasswordField("pass2", "确认密码", 50, true);
	private Submit submit = new Submit("submit", "提交注册信息", this, "onSaveClick");
	
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
    			regForm.setError("两次密码不同");
    			return true;
    		}
    		
    		if(pass1.getValue().length()<6)
    		{
    			regForm.setError("密码必须大于5位");
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
    			getHeadElements().add(new JsScript("alert('恭喜您，注册成功！点击确定登陆并跳转到首页')"));
    			getHeadElements().add(new JsScript("window.location = './index.htm'"));
    		} 		
    	}
        return true;
    }
}
