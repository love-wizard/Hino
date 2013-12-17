package com.hino.page.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
import org.apache.click.element.Element;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hino.dao.impl.VipOrderDAOImpl;
import com.hino.dto.BookingReserveDto;
import com.hino.dto.VipOrderReserveDto;
import com.hino.model.Customer;
import com.hino.model.Staff;
import com.hino.page.VipOrder;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class SalesVipOrderForm extends BasePage {
	@Bindable
	public Button internal_reserve = new Button("internal_reserve", "d");
	@Bindable
	public Button load_customer = new Button("load_customer", "load_customer");

	@Bindable
	public Button check_vip_applied = new Button("check_vip_applied",
			"check_vip_applied");

	public void onInit() {
		internal_reserve.setId("internal_reserve");
		internal_reserve.addBehavior(new DefaultAjaxBehavior() {
			public ActionResult onAction(Control source) {
				String jsondata = getContext().getRequest().getParameter(
						"jsondata");

				ObjectMapper mapper = new ObjectMapper();
				VipOrderReserveDto result = null;
				try {
					result = mapper.readValue(jsondata,
							VipOrderReserveDto.class);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("result=" + result);
				if (result == null) {
					return new ActionResult("error", ActionResult.HTML);
				}
				//TD#150 VIP系统问题
				if(checkVipApplied(result.getEmail()))
				{
					return new ActionResult("该客户正在申请过VIP，请不要重复申请！", ActionResult.HTML);
				}

				result.setOrder_method(Info.BKM_INTERNAL);
				Staff s = (Staff) getContext().getSession().getAttribute(
						"staff");
				if (s == null) {
					return new ActionResult("无权操作！", ActionResult.HTML);
				} else {
					result.setStaffid(s.getId());
				}

				ServiceResponse sr = getCustomerService().vip_reserve(result);
				if (sr.isSucc()) {
					jsondata = "操作执行成功！";
				} else {
					jsondata = EscapeHtml.nrTonnrr(sr.list_msg());
				}
				// System.out.println(result.get(0) + "gid:" + groupid);
				return new ActionResult(jsondata, ActionResult.HTML);
			}
		});

		load_customer.setId("load_customer");
		addControl(load_customer);
		// load_customer.
		load_customer.addBehavior(new DefaultAjaxBehavior() {
			public ActionResult onAction(Control source) {
				String email = getContext().getRequest().getParameter("email");

				ServiceResponse sr = getCustomerService().getCustomerByEmail(
						email);

				String jsondata = "";
				if (sr.isSucc()) {

					Customer c = (Customer) sr.getResponse();
					jsondata = "{" + "\"fn\":\"" + c.genFullName() + "("
							+ c.getChinesename() + ")" + "\", \"ad\":\""
							+ c.getAddress() + "\", \"rn\":\""
							+ c.genFullName() + "\", \"ph\":\"" + c.getPhone()
							+ "\"}";

				} else {
					jsondata = "{" + "\"result\":\"0\"}";
				}
				return new ActionResult(jsondata, ActionResult.JSON);
			}
		});

		// Ken Chen 2012/09/24 Add vip applied before validation
		check_vip_applied.setId("check_vip_applied");
		addControl(check_vip_applied);
		
		check_vip_applied.addBehavior(new DefaultAjaxBehavior() {
			public ActionResult onAction(Control source) {
				String email = getContext().getRequest().getParameter("email");
				String jsondata = "";
				
				if(checkVipApplied(email))
				{
					jsondata = "{" + "\"result\":\"1\"}";
				}
				else
				{
					jsondata = "{" + "\"result\":\"0\"}";
				}
				return new ActionResult(jsondata, ActionResult.JSON);
			}
		});
	}

	public boolean checkVipApplied(String email)
	{
		List<com.hino.model.VipOrder> vip = getCustomerService().getVipOrderByEmail(email);

		if (vip.size()>0) {
			for(int i=0 ; i < vip.size(); i++)
			{
				if(vip.get(i).getStatus()==Info.VS_RESERVING || vip.get(i).getStatus() == Info.VS_CONIFRMED_NOTPAID)
				{
					return true;
				}
			}
			return false;
			
		} else {
			return false;	
		}	
	}
	
	@Override
	public List<Element> getHeadElements() {
		if (headElements == null) {
			headElements = super.getHeadElements();
			// headElements.add(new JsImport("/assets/js/jquery-1.4.2.js"));
			headElements.add(new JsScript(
					"/internal/js/sales_viporder_form.js", new HashMap()));
		}
		return headElements;
	}
}
