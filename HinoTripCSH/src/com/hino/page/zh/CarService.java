package com.hino.page.zh;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hino.dto.BookingReserveDto;
import com.hino.dto.CarRequestDto;
import com.hino.model.Customer;
import com.hino.model.Vehicle;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class CarService extends BasePageOther {
	private static final long serialVersionUID = -421125561789927051L;

	@Bindable
	public Button car_request = new Button("car_request", "d");
	
	@Bindable 
	public List<Vehicle> carList;
	
	public CarService()
	{
		car_request.setId("car_request");
		car_request.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	int nop = Integer.parseInt(getContext().getRequest().getParameter("nop"));
            	int nod = Integer.parseInt(getContext().getRequest().getParameter("nod"));
            	String car = getContext().getRequest().getParameter("car");
            	String name = getContext().getRequest().getParameter("name");
            	String email = getContext().getRequest().getParameter("email");
            	String phone = getContext().getRequest().getParameter("phone");
            	String detail = getContext().getRequest().getParameter("detail");
            	String dstr = getContext().getRequest().getParameter("date");
            	
            	CarRequestDto crdto = new CarRequestDto(); 
            	crdto.setCar_type(car);
            	crdto.setNo_of_days(nod);
            	crdto.setNo_of_persons(nop);
            	
            	DateFormat format = new SimpleDateFormat("yyyyMMdd");
                Date date = null;
                try {
                    // Fri Feb 24 00:00:00 CST 2012
                    date = format.parse(dstr); 
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                crdto.setDate(date);
            	crdto.setEmail(email);
            	crdto.setPhone(phone);
            	crdto.setDetail(detail);
            	crdto.setName(name);
            	
				String result_json ="";
				//jsondata = "{" + "\"result\":\"登录失败，请检查用户名密码\"}";
				
				
				
				getCustomerService().external_car_request(crdto);
				
            	
            	return new ActionResult(result_json, ActionResult.JSON);
            	
            }
        });
		
		
	}
	
	public void onInit(){
		qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);	
	}
	
	 public void onRender()
	 {
		 carList = getTravelResourceService().getAllVehicle();
	 }
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./images/car.css"));
            headElements.add(new JsImport("./js/car.js"));
            //headElements.add(new JsImport("js/jquery.tools.min.js"));
        }
        return headElements;
    }

}
