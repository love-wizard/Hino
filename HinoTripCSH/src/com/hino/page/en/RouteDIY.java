package com.hino.page.en;

import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.EmailField;
import org.apache.click.extras.control.TelephoneField;
import org.apache.click.util.Bindable;

import com.hino.dto.DiyRouteDto;
import com.hino.util.EscapeHtml;

public class RouteDIY extends BasePage {
	private static final long serialVersionUID = -421125561789927053L;

	@Bindable private Form diyForm = new Form("diyform");

    @Override
    public void onInit() {
		
		// ----- Display diy form -----
    	diyForm.setAttribute("class", "niceform");

		TextField diyerName = new TextField("name", "Name", 50, true);
		diyerName.setTrim(true);
		diyForm.add(diyerName);

		EmailField diyerEmail = new EmailField("email", "Email", 50, true);
		diyForm.add(diyerEmail);
		
		TelephoneField diyerPhone = new TelephoneField("phone", "Phone", 50, false);
		diyForm.add(diyerPhone);
		
		TextArea diyerDesc = new TextArea("description", "DIY description", 35, 10, false);
		diyerDesc.setTrim(true);
		diyForm.add(diyerDesc);
		
		diyForm.add(new Submit("save", "Submit DIY", this, "onSaveClick"));
		diyForm.add(new Submit("cancel", "Cancel DIY", this, "onCancelClick"));

    }

    /**
     * Create a new DIY request
     * @return
     */
    public boolean onSaveClick() {
        if (diyForm.isValid()) {
        	DiyRouteDto diydto = new DiyRouteDto(); 
        	diyForm.copyTo(diydto);

    		// Change < into &lt;
        	diydto.setName(EscapeHtml.htmlEncode(diydto.getName().trim()));
        	diydto.setDescription(EscapeHtml.htmlEncode(diydto.getDescription().trim()));
    		
        	getTravelResourceService().create_dir_route(diydto);
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('DIY route applied. We will contact you later. Thanks.')"));
			getHeadElements().add(
					new JsScript("window.location = './route_diy.htm'"));
        }
        return true;
    }

    /**
     * Redirect to route_diy.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(RouteDIY.class);
        return true;
    }

}
