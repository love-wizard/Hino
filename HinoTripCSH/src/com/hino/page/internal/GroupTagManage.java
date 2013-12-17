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
import com.hino.page.Index;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GroupTagManage extends BasePage{

	@Bindable 
	private Integer id;
	@Bindable
	private Form form = new Form("groupTag");
	@Bindable
	private Group gp;
	@Bindable
	private String name;
	private Select groupSelect = new Select("groupSelect", "团名称");
	private Select classSelect = new Select("classSelect", "产品类型");
	//private Select travelDaySelect = new Select("travelDaySelect", "行程天数");
	private Select sellTypeSelect = new Select("sellTypeSelect", "贩卖类型");
	
	private Select destinationArea = new Select("destinationArea", "目的地区域");
	
	private Submit clear = new Submit("clear", "取消");

	private String tables = "";
	private ApplicationContext applicationContext;
		
	public void onInit() {
		
		if(id!=null)
		{
			Group gp = this.getSalesService().viewGroupById(id);
			name = gp.getRoute().getName();
		}
		
		groupSelect.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				
				optionList.add(new Option(id, name));

				return optionList;
			}
		});
		
		classSelect.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();

				optionList.add(new Option(0, "无"));
				optionList.add(new Option(51, "观光"));
				optionList.add(new Option(52, "主题"));
				optionList.add(new Option(53, "度假"));
				optionList.add(new Option(54, "邮轮"));

				return optionList;
			}
		});		
		
		/*
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
		*/
		
		
		sellTypeSelect.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();

				optionList.add(new Option(0, "无"));
				optionList.add(new Option(71, "团购"));
				optionList.add(new Option(72, "超值热买"));
				optionList.add(new Option(73, "秒杀"));
				optionList.add(new Option(74, "经理推荐"));
//				optionList.add(new Option(75, "DIY"));

				return optionList;
			}
		});		
		
		destinationArea.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();

				optionList.add(new Option(0, "无"));
				optionList.add(new Option(31, "英国境内"));
				optionList.add(new Option(32, "欧洲大陆"));

				return optionList;
			}
		});		
		
		
		clear.setActionListener(new ActionListener() {
			@Override
			public boolean onAction(Control source) {
				form.clearErrors();
		        form.clearValues();
		        classSelect.setValue("0");
		        //travelDaySelect.setValue("0");
		        sellTypeSelect.setValue("0");
		        destinationArea.setValue("0");
		        
		        // Remove table and form state from the session
		        form.removeState(getContext());
				return true;
			}

		});	
		
		form.add(groupSelect);
		form.add(classSelect);
		//form.add(travelDaySelect);
		form.add(sellTypeSelect);
//		form.add(destinationArea);
//		form.add(clear);
		
		form.add(new Submit("ok", "设置/更新", this, "onOkClick"));
		
		if(id!=null)
		{
			Group gp = this.getSalesService().viewGroupById(id);
			name = gp.getRoute().getName();
			
			if(gp!=null)
			{
				List<GroupTag> listTag = this.getSalesService().list_group_tag_by_group_id(id);
				for(int i = 0;i<listTag.size();i++){
					GroupTag gTag = (GroupTag) listTag.get(i);
					int j = gTag.getTag_id();
					switch(j)
					{
						case Info.GROUP_TAG_CLASS:
							classSelect.setValue(gTag.getValue());
						break;
						//case Info.GROUP_TAG_TRAVEL_DAY:
						//	travelDaySelect.setValue(gTag.getValue());
						//break;
						case Info.GROUP_TAG_SELL_TYPE:
							sellTypeSelect.setValue(gTag.getValue());
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
			classSelect.setValue("0");
			sellTypeSelect.setValue("0");
			//travelDaySelect.setValue("0");
			destinationArea.setValue("0");
		}
		
		
	}
	
	public boolean onOkClick() {

		if (form.isValid()) {
			Group gp = this.getSalesService().viewGroupById( Integer.parseInt(this.groupSelect.getValue()) );
	
			this.getSalesService().delete_group_tag_by_group_id(gp.getId());
			for(int i = 0; i < 3; i++)
			{
				GroupTagDto gTag = new GroupTagDto();
				gTag.setGroup_id(gp.getId());
				gTag.setRoute_id(0);
				switch(i)
				{
					case 0:
						gTag.setTag_id(Info.GROUP_TAG_CLASS);
						if(Integer.parseInt(classSelect.getValue())==0)
						{
							continue;
						}
						gTag.setValue(classSelect.getValue());
					break;
					//case 1:
					//	gTag.setTag_id(Info.GROUP_TAG_TRAVEL_DAY);
					//	if(Integer.parseInt(travelDaySelect.getValue())==0)
					//	{
					//		continue;
					//	}
					//	gTag.setValue(travelDaySelect.getValue());
					//break;
					case 1:
						if(Integer.parseInt(sellTypeSelect.getValue())==0)
						{
							continue;
						}
						gTag.setTag_id(Info.GROUP_TAG_SELL_TYPE);
						gTag.setValue(sellTypeSelect.getValue());
					break;	
					case 2:
						if(Integer.parseInt(this.destinationArea.getValue())==0)
						{
							continue;
						}
						gTag.setTag_id(Info.GROUP_TAG_DESTINATION);
						gTag.setValue(destinationArea.getValue());
					break;	
				}
				this.getSalesService().create_group_tag(gTag);
			}

		}
		setRedirect(GeneralSalesGroupManage.class);
		return false;
	}

}
