package com.hino.page;


import java.util.List;

import org.apache.click.control.ActionLink;
import org.apache.click.control.Form;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.EmailField;
import org.apache.click.util.Bindable;

import com.hino.click.extension.RecentGroupDiv;
import com.hino.click.extension.RouteListDiv;
import com.hino.dto.CustomerLoginDto;
import com.hino.model.Customer;
import com.hino.model.WebMenuRoute;
import com.hino.util.Info;
import com.hino.util.NavigationUtil;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class Index extends BasePage {
	private static final long serialVersionUID = 9084137430097090709L;
	
	@Bindable private Form form = new Form("form");
	@Bindable private String error;
	@Bindable private RouteListDiv indexRouteList;
	@Bindable private RecentGroupDiv indexGroupList;
	@Bindable private ActionLink logoutLink = 
		new ActionLink("logoutLink", "注销", this, "logout");
	@Bindable private Customer cur_customer = null;
	@Bindable private String notVip = null;
	@Bindable private String vipexpdate = "";
	@Bindable private String notice = "";
	
	private EmailField cemailField;
	private PasswordField passwordField;
	
	public Index()
	{
		/*
		notice = Info.indexText;
		form.setMethod("post");
		form.setAttribute("class", "niceform");
		
		cemailField = new EmailField("email", "Email", 20, true);
		cemailField.setAttribute("class", "text-input");
		
		passwordField = new PasswordField("password", "密码", true);
		passwordField.setAttribute("class", "text-input");
		
		Submit submit = new Submit("ok", "登录", this, "onOkClick"); 
		submit.setAttribute("class", "button");
		
		form.add(cemailField);
		form.add(passwordField);
		form.add(submit); 

		logoutLink.setAttribute("class", "ls_style");
		*/
		this.setRedirect(com.hino.page.zh.Index.class);
	}
	
	@Override
	public void onInit() {
		// Update navigation link
		/*
		NavigationUtil.updateTopLevel(this.getContext().getSession(), true, false);
		
		// Create index group list panel
		indexGroupList = new RecentGroupDiv("index_group_list_panel", getWebService().getRecentGroup(), "最近出团线路", false);
		
		// Create index route list panel
		indexRouteList = new RouteListDiv("index_route_list_panel", getWebService().getIndexMenu().getMenu_routes(), false);
		
		cur_customer = (Customer)this.getContext().getSession().getAttribute("customer");
		if(cur_customer == null)
		{
			
		} else {
			if(cur_customer.genIsVip() == false)
				notVip = "NotVip";
			else
				vipexpdate = TimeFormater.format2(cur_customer.getVip_valid());
		}
		*/
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
            	setRedirect(Index.class);
            } else
            {
            	error = Info.toStringInfo(sr.getInfo_code());
            }
             
        } 
        return true; 
    }

	/**
	 * Clear all session objects to logout
	 * @return
	 */
	public boolean logout() {
		getContext().getSession().invalidate();
    	setRedirect(Index.class);
    	return true;
	}
	
	
	
}
