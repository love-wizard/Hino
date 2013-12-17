package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.AirlineQACAMDto;
import com.hino.dto.SiteCAMDto;
import com.hino.model.AirlineQA;
import com.hino.model.Route;
import com.hino.model.Site;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;

public class AirlineQACAM extends BasePage {
	private static final String DEFAULT_ID_VALUE = "系统自动生成";

	@Bindable
	private Integer id;

	@Bindable
	private Form form = new Form("airline_qa_form");

	private HiddenField airlineqaid = new HiddenField("airlineqaid", "airlineqaid");
	private TextField question = new TextField("question", "问题", 50, true);
	private TextArea answer = new TextArea("answer", "答案", 50, 10);
	Select routeSlct = new Select("airportaeraid", "问题区域");
	
	public void onInit() {
		airlineqaid.setDisabled(true);
		//change to city-airport : From	
		routeSlct.add(new Option(Info.AIRLINE_AREA_BOOKING_PROCEDURE, "订票流程"));
		routeSlct.add(new Option(Info.AIRLINE_AREA_PAY_METHOD, "付款方式"));
		routeSlct.add(new Option(Info.AIRLINE_AREA_ELECTRIC_TICKET, "电子票"));
		routeSlct.add(new Option(Info.AIRLINE_AREA_UPDATE_TICKET, "改退机票"));
		routeSlct.add(new Option(Info.AIRLINE_AREA_FLIGHT_INFO_CONFIRM, "航班信息确认"));
		routeSlct.add(new Option(Info.AIRLINE_AREA_NETWORK_CHECKIN, "网络Check-in"));
		routeSlct.add(new Option(Info.AIRLINE_AREA_BAGGAGE_QUESTION, "行李问题"));
		routeSlct.add(new Option(Info.AIRLINE_AREA_SPECIAL_TICKET, "特殊机票"));
		routeSlct.add(new Option(Info.AIRLINE_AREA_OTHER_QUESTION, "其他问题"));
		
		form.add(airlineqaid);
		form.add(routeSlct);
		form.add(question);
		form.add(answer);
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));

		if (id == null) {
			airlineqaid.setValue(DEFAULT_ID_VALUE);
		} else {
			AirlineQA s = getWebService().getAirlineQAById(id);
			
			if(s!=null)
			{
				airlineqaid.setValue("" + s.getId());
				question.setValue(s.getQuestion());
				answer.setValue(s.getAnswer());
				routeSlct.setValue(s.getArea());

			} else
			{
				getHeadElements().add(new JsScript("alert('不存在')"));
				getHeadElements().add(
						new JsScript("window.location = './airline_qa_manage.htm'"));
			}
		}
	}

	public boolean onOkClick() {

		if (form.isValid()) {
			AirlineQACAMDto qadto = new AirlineQACAMDto();
			// siteid.getValue()
			qadto.setArea(routeSlct.getValue());
			qadto.setQuestion(question.getValue());
			qadto.setAnswer(answer.getValue());
			
			try {
				if (!airlineqaid.getValue().equals(DEFAULT_ID_VALUE)) {
					qadto.setId(Integer.parseInt(airlineqaid.getValue()));
					getWebService().modify_airlineQA(qadto);
				} else{
					getWebService().create_airlineQA(qadto);
				}
			} catch (NumberFormatException nfe) {
				//
			} finally {
				//
			}

			// fullimgfile.getFileItem().getClass().getName();

			form.clearValues();
			getHeadElements().add(new JsScript("alert('更新成功')"));
			getHeadElements().add(
					new JsScript("window.location = './airline_qa_manage.htm'"));

			// setRedirect(SiteCAM.class);

		}
		return true;
	}

	public boolean onCancelClick() {
		setRedirect(SiteManage.class);
		return true;
	}

}
