package com.hino.page;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.util.Bindable;

import com.hino.model.Customer;
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;

public class FeedbackForm extends BasePage{
	@Bindable public Long value;
	@Bindable
	private Form form = new Form("feedback_form");
	
	private HiddenField bid = new HiddenField("bvalue", Long.class);
	private HiddenField fb = new HiddenField("fbvalue", String.class);
	private Submit submitBtn = new Submit("submitBtn", "提交问卷");

	
	public void onInit()
	{
		if(value!=null)
		{
			bid.setValueObject(value);
		} else
		{
			setRedirect(Index.class);
		}
		
		form.add(bid);
		form.add(fb);
		form.add(submitBtn);
		submitBtn.setId("submitBtn");
		submitBtn.addBehavior(new DefaultAjaxBehavior() {

	            @Override
	            public ActionResult onAction(Control source) {
	                long b = Long.parseLong(getContext().getRequest().getParameter("bvalue"));
	                String feedback = getContext().getRequest().getParameter("fbvalue");
	                Customer curCustomer = (Customer)getContext().getSession().getAttribute("customer");
	            	if(curCustomer == null) {
	            		return new ActionResult("3", ActionResult.HTML);
	            	}
	            	
	            	if(!feedback_validate(feedback)) {
	            		return new ActionResult("2", ActionResult.HTML);
	            	}
	            	ServiceResponse sr = getCustomerService().addFeedBack(curCustomer.getId(), b, feedback);
	            	if(sr.isSucc())
	            	{
	            		return new ActionResult("0", ActionResult.HTML);
	            	} else
	            	{
	            		
	            		return new ActionResult(EscapeHtml.nrTonnrr(sr.list_msg()), ActionResult.HTML);
	            	}
	            	
	            }
	        });


	}
	
	private boolean feedback_validate(String fb)
	{
		if(fb==null)
		{
			return false;
		}
		
		String[] element = fb.split("-");
		if(element.length<9)
		{
			return false;
		}
		return true;
	}
}
