package com.hino.page;

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

		TextField diyerName = new TextField("name", "称呼", 50, true);
		diyerName.setTrim(true);
		diyForm.add(diyerName);

		EmailField diyerEmail = new EmailField("email", "电子邮箱地址", 50, true);
		diyForm.add(diyerEmail);
		
		TelephoneField diyerPhone = new TelephoneField("phone", "联系电话", 50, false);
		diyForm.add(diyerPhone);
		
		TextArea diyerDesc = new TextArea("description", "DIY描述", 35, 10, false);
		diyerDesc.setTrim(true);
		diyForm.add(diyerDesc);
		
		diyForm.add(new Submit("save", "提交DIY", this, "onSaveClick"));
		diyForm.add(new Submit("cancel", "取消DIY", this, "onCancelClick"));

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
			getHeadElements().add(new JsScript("alert('DIY线路申请成功成功，请耐心等待工作人员联系。')"));
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
