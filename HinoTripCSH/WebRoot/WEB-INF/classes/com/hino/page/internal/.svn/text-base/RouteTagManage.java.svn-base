package com.hino.page.internal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.util.Bindable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hino.dto.GroupCAMDto;
import com.hino.dto.GroupTagDto;
import com.hino.model.Group;
import com.hino.model.GroupTag;
import com.hino.model.Route;
import com.hino.page.Index;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class RouteTagManage extends BasePage{

	@Bindable 
	private Integer id;
	@Bindable
	private Form form = new Form("routeTag");
	@Bindable
	private Route rt;
	@Bindable
	private String name;
	private Select routeSelect = new Select("routeSelect", "路线名称");
	private Select travelDaySelect = new Select("travelDaySelect", "行程天数");
	private Select destinationArea = new Select("destinationArea", "DIY目的地区域");
	
	private Submit clear = new Submit("clear", "取消");

	private String tables = "";
	private ApplicationContext applicationContext;
		
	public void onInit() {
		
		if(id != null)
		{
			Route rt = this.getTravelResourceService().getRouteById(id);			
			name = rt.getName();
		}
		
		routeSelect.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				
				optionList.add(new Option(id, name));

				return optionList;
			}
		});
		
		
		travelDaySelect.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();

				optionList.add(new Option(0, "无"));
				optionList.add(new Option(61, "休闲一日游"));
				optionList.add(new Option(62, "休闲二日游"));
				optionList.add(new Option(63, "惬意境内多日游"));
				optionList.add(new Option(64, "心醉境外多日游"));

				return optionList;
			}
		});		
		
		destinationArea.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();

				optionList.add(new Option(0, "无"));
				optionList.add(new Option(31, "英国境内"));
				optionList.add(new Option(32, "欧洲大陆"));
				optionList.add(new Option(33, "其他"));

				return optionList;
			}
		});
		
		clear.setActionListener(new ActionListener() {
			@Override
			public boolean onAction(Control source) {
				form.clearErrors();
		        form.clearValues();
		        travelDaySelect.setValue("0");
		        
		        // Remove table and form state from the session
		        form.removeState(getContext());
				return true;
			}

		});
		
		form.add(routeSelect);
		form.add(travelDaySelect);
		form.add(destinationArea);
//		form.add(clear);
		
		form.add(new Submit("ok", "设置/更新", this, "onOkClick"));
		
		if(id != null)
		{
			Route rt = this.getTravelResourceService().getRouteById(id);
			name = rt.getName();
			
			if(rt != null)
			{
				List<GroupTag> listTag = this.getSalesService().list_group_tag_by_route_id(id);
				for(int i = 0; i < listTag.size(); i++){
					GroupTag gTag = (GroupTag) listTag.get(i);
					int j = gTag.getTag_id();
					switch(j)
					{
						case Info.GROUP_TAG_TRAVEL_DAY:
							travelDaySelect.setValue(gTag.getValue());
							break;
						case Info.GROUP_TAG_DESTINATION:
							destinationArea.setValue(gTag.getValue());
							break;
					}
				}
			}
		}
		else
		{
			travelDaySelect.setValue("0");
		}
		
		
	}
	
	public boolean onOkClick() {

		if (form.isValid()) {
			Route rt = this.getTravelResourceService().getRouteById(Integer.parseInt(this.routeSelect.getValue()));
			
			this.getSalesService().delete_group_tag_by_route_id(rt.getId());
			if(Integer.parseInt(travelDaySelect.getValue()) != 0)
			{
				GroupTagDto gTag = new GroupTagDto();
				gTag.setGroup_id(0);
				gTag.setRoute_id(rt.getId());
				gTag.setTag_id(Info.GROUP_TAG_TRAVEL_DAY);
				gTag.setValue(travelDaySelect.getValue());
		
				this.getSalesService().create_group_tag(gTag);
			}
			
			if(Integer.parseInt(destinationArea.getValue()) != 0)
			{
				GroupTagDto gTag = new GroupTagDto();
				gTag.setGroup_id(0);
				gTag.setRoute_id(rt.getId());
				gTag.setTag_id(Info.GROUP_TAG_DESTINATION);
				gTag.setValue(destinationArea.getValue());
		
				this.getSalesService().create_group_tag(gTag);
			}
		}
		setRedirect(RouteManage.class);
		return false;
	}

}
