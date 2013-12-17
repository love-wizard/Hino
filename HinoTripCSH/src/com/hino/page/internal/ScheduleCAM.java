package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.CheckList;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.RouteCAMDto;
import com.hino.dto.ScheduleDto;
import com.hino.model.Route;
import com.hino.model.Schedule;
import com.hino.model.Site;
import com.hino.util.Info;

public class ScheduleCAM extends BasePage{
	private static final String DEFAULT_ID_VALUE = "系统自动生成";
	
	@Bindable
	private int id;
	
	@Bindable
	private Form form;

	@Bindable
	private int rid;
	
	private HiddenField sid = new HiddenField("sid", "sid");
	private HiddenField routeId = new HiddenField("routeId", "routeId");

	private TextField sequenceId = new TextField("sequenceId", "序号", 50, true);
	private TextField title = new TextField("title", "线路行程名称", 50, true);
	private TextField titleDesc = new TextField("titleDesc", "行程简介", 50, true);
	private TextArea scheduleDesc = new TextArea("scheduleDesc", "行程描述", 85, 15);

	private TextField breakfast = new TextField("breakfast", "早餐", 50, false);
	private TextField lunch = new TextField("lunch", "午餐", 50, false);
	private TextField dinner = new TextField("dinner", "晚饭", 50, false);
	private TextField hotel = new TextField("hotel", "宾馆", 50, false);
	private TextField transport = new TextField("transport", "交通", 50, false);
	private TextField flight = new TextField("flight", "航班号", 50, false);
		
	private FileField material1 = new FileField("material1", "行程图片1");
	private FileField material2 = new FileField("material2", "行程图片2");
	private FileField material3 = new FileField("material3", "行程图片3");
	
	@Bindable
	private Image image1;
	@Bindable
	private Image image2;
	@Bindable
	private Image image3;
	
	public ScheduleCAM() {
		form = new Form("schedule_cam_form");
	}

	public void onInit()
	{
		
        form.add(sid);
        form.add(routeId);
        form.add(sequenceId);
		form.add(title);
		form.add(titleDesc);
		form.add(scheduleDesc);
		form.add(breakfast);
		form.add(lunch);
		form.add(dinner);
		form.add(transport);
		form.add(hotel);
		form.add(flight);
		form.add(material1);
		form.add(material2);
		form.add(material3);
	
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));
		
		Route route = new Route();
		if(rid>0)
		{
			route = getTravelResourceService().getRouteById(rid);
			routeId.setValue(String.valueOf(route.getId()));
			//form.saveState(getContext());
		}
		

		if(id<=0)
		{
			sid.setValue(DEFAULT_ID_VALUE);
			if(rid>0)
			{
				this.sequenceId.setValue(String.valueOf(getTravelResourceService().getNextScheduleSequenceId(route.getId())));
				route = getTravelResourceService().getRouteById(rid);
				
				routeId.setValue(String.valueOf(route.getId()));
			}
			else
			{
				this.sequenceId.setValue("1");
			}
			
			this.breakfast.setValue("酒店早餐");
			this.lunch.setValue("导游推荐");
			this.dinner.setValue("导游推荐");
			this.transport.setValue("旅游巴士");
			
		}
		else
		{
			final Schedule schedule = getTravelResourceService().getScheduleById(id);
			rid = (int) schedule.getRoute_id();
			sid.setValue(String.valueOf(schedule.getId()));
			route = getTravelResourceService().getRouteById(rid);
			
			routeId.setValue(String.valueOf(route.getId()));
			this.sequenceId.setValue(String.valueOf(schedule.getSequence_id()));
			this.title.setValue(schedule.getTitle());
			this.titleDesc.setValue(schedule.getTitle_desc());
			this.scheduleDesc.setValue(schedule.getSchedule_desc());
			
			this.breakfast.setValue(schedule.getBreakfast());
			this.lunch.setValue(schedule.getLunch());
			this.dinner.setValue(schedule.getDinner());
			this.hotel.setValue(schedule.getHotel());
			this.transport.setValue(schedule.getTransport());
			this.flight.setValue(schedule.getFlight());
			
			if (schedule.getMaterial_1()!= null) {
				image1 = new Image("image1");
				image1.setSrc(Info.INTERNAL_PATH_PREFIX+schedule.getMaterial_1());
			}
			
			if (schedule.getMaterial_2()!= null) {
				image2 = new Image("image2");
				image2.setSrc(Info.INTERNAL_PATH_PREFIX+schedule.getMaterial_2());
			}
			
			if (schedule.getMaterial_3()!= null) {
				image3 = new Image("image3");
				image3.setSrc(Info.INTERNAL_PATH_PREFIX+schedule.getMaterial_3());
			}
			
		}
		//form.restoreState(getContext());
	}
	
	public boolean onOkClick() {

		if (form.isValid()) {
			ScheduleDto scheduleDto = new ScheduleDto();
			scheduleDto.setRoute_id(Long.parseLong(routeId.getValue()));
			scheduleDto.setSequence_id(Long.parseLong(this.sequenceId.getValue()));
			scheduleDto.setTitle(this.title.getValue());
			scheduleDto.setTitle_desc(this.titleDesc.getValue());
			//TD59 Kevin
			scheduleDto.setSchedule_desc(this.scheduleDesc.getValue());
			scheduleDto.setBreakfast(this.breakfast.getValue());
			scheduleDto.setLunch(this.lunch.getValue());
			scheduleDto.setDinner(this.dinner.getValue());
			scheduleDto.setHotel(this.hotel.getValue());
			scheduleDto.setTransport(this.transport.getValue());
			scheduleDto.setFlight(this.flight.getValue());
			
			if (!material1.getFileItem().getName().trim().equals("")) {
				scheduleDto.setMaterial_1(material1.getFileItem());
			}

			if (!material2.getFileItem().getName().trim().equals("")) {
				scheduleDto.setMaterial_2(material2.getFileItem());
			}
			
			if (!material3.getFileItem().getName().trim().equals("")) {
				scheduleDto.setMaterial_3(material3.getFileItem());
			}
						
			try {
				if (!sid.getValue().equals(DEFAULT_ID_VALUE)) {
					//scheduleDto.setId(Integer.parseInt(sid.getValue()));
					scheduleDto.setId(Long.parseLong(sid.getValue()));
					getTravelResourceService().modify_schedule(scheduleDto);
				} else{
					getTravelResourceService().create_schedule(scheduleDto);
				}
			} catch (NumberFormatException nfe) {
				//
			} finally {
				//
			}
			
			form.clearValues();
			form.removeState(getContext());
			getHeadElements().add(new JsScript("alert('更新成功')"));
			getHeadElements().add(
					new JsScript("window.location = './schedule_manage.htm?id=" + scheduleDto.getRoute_id()+ "'"));
			//setRedirect ("./schedule_manage.htm?id="+scheduleDto.getRoute_id());
		}
		return true;
	}

	public boolean onCancelClick() {
		setRedirect ("./schedule_manage.htm?id="+routeId.getValue());
		form.removeState(getContext());
		return true;
	}

}
