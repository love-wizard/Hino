package com.hino.page;

import java.util.List;

import org.apache.click.control.Form;
import org.apache.click.control.Label;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.EmailField;
import org.apache.click.extras.control.IntegerField;
import org.apache.click.extras.control.TelephoneField;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Div;
import com.hino.click.extension.Image;
import com.hino.click.extension.Link;
import com.hino.dto.CarRequestDto;
import com.hino.dto.DiyRouteDto;
import com.hino.model.Vehicle;
import com.hino.model.WebMenu;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.NavigationUtil;

public class Carservice extends BasePage {

	@Bindable Div divCarList = new Div("car_list_div");
	@Bindable private Form requestForm = new Form("requestform");
	public Carservice()
	{

	}
	
	
	@Override
	public void onInit()
	{
		divCarList.setAttribute("align", "center");
		List<Vehicle> carList = getTravelResourceService().getAllVehicle();
        for(Vehicle v:carList) {
        	
    		
    		Image imgCar = new Image("imgCar"+v.getName());
    		imgCar.setSrc(Info.EXTERNAL_PATH_PREFIX+v.getImg_path());
    		Label labelBr = new Label("<br />");
    		
    		divCarList.add(imgCar);
    		divCarList.add(labelBr);
        	
        }

		TextField diyerName = new TextField("name", "称呼", 50, true);
		diyerName.setTrim(true);
		requestForm.add(diyerName);

		EmailField diyerEmail = new EmailField("email", "电子邮箱地址", 50, true);
		requestForm.add(diyerEmail);
		
		TelephoneField diyerPhone = new TelephoneField("phone", "联系电话", 50, true);
		requestForm.add(diyerPhone);
		
		Select car_type = new Select("car_type", "车型");
		car_type.add("");
		for(Vehicle v:getTravelResourceService().getAllVehicle())
		{
			car_type.add(v.getName());
		}
		requestForm.add(car_type);
		
		IntegerField nod = new IntegerField("no_of_days", "天数", 3, false);
		requestForm.add(nod);
		IntegerField nop = new IntegerField("no_of_persons", "人数", 3, false);
		requestForm.add(nop);
		DateField start = new DateField("date", "开始日期", false);
		requestForm.add(start);
		
		TextArea diyerDesc = new TextArea("detail", "其他信息", 35, 10, false);
		diyerDesc.setTrim(true);
		requestForm.add(diyerDesc);
		
		requestForm.add(new Submit("save", "提交", this, "onSaveClick"));
		requestForm.add(new Submit("cancel", "取消", this, "onCancelClick"));
	}
	
	 public boolean onSaveClick() {
	        if (requestForm.isValid()) {
	        	CarRequestDto crdto = new CarRequestDto(); 
	        	requestForm.copyTo(crdto);

	    		// Change < into &lt;
	        	crdto.setName(EscapeHtml.htmlEncode(crdto.getName().trim()));
	        	crdto.setDetail(EscapeHtml.htmlEncode(crdto.getDetail().trim()));
	    		
	    		getCustomerService().external_car_request(crdto);
	    		// Show javascript message window and redirect the page 
				getHeadElements().add(new JsScript("alert('申请提交成功，我们工作人员会很快联系您。')"));
				getHeadElements().add(
						new JsScript("window.location = './carservice.htm'"));
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

	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new JsImport("./js/carservice.js"));
            //headElements.add(new CssImport("./css/booking.css"));
        }
        return headElements;
    }

}
