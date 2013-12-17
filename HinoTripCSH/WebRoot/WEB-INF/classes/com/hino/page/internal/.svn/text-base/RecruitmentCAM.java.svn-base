package com.hino.page.internal;

import java.util.Calendar;

import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.util.Bindable;

import com.hino.dto.RecruitmentDto;
import com.hino.model.Airline;
import com.hino.model.Recruitment;

public class RecruitmentCAM extends BasePage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6393703863029810965L;

	private static final String DEFAULT_ID_VALUE = "系统自动生成";

	@Bindable
	private Integer id;

	@Bindable
	private Form form = new Form("recruitment_cam_form");

	private HiddenField recruitmentid = new HiddenField("recruitmentid", "recruitmentid");

	private TextField position = new TextField("position", "岗位名称", 50, true);
	private TextArea description = new TextArea("description", "岗位描述/要求", 85, 15);
	private DateField expiryDate = new DateField("expiryDate", "截止日期", 25, true);
	
	public void onInit() {
		recruitmentid.setDisabled(true);
        form.add(recruitmentid);
        form.add(position);
        form.add(description);
        expiryDate.setFormatPattern("yyyy-MM-dd");
        form.add(expiryDate);
		
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));

		if (id == null) {
			recruitmentid.setValue(DEFAULT_ID_VALUE);
			
		} else {
			Recruitment r = getHumanResourceService().getRecruitmentById(id);
			
			if(r!=null)
			{
				recruitmentid.setValue("" + r.getId());
				position.setValue(r.getPosition());
				description.setValue(r.getDescription());
				expiryDate.setDate(r.getExpiryDate().getTime());
			} else {
				getHeadElements().add(new JsScript("alert('不存在')"));
				getHeadElements().add(
						new JsScript("window.location = './recruitment_manage.htm'"));
			}
		}
	}

	public boolean onOkClick() {

		if (form.isValid()) {
			RecruitmentDto rdto = new RecruitmentDto();
			Calendar cal1=Calendar.getInstance();
			cal1.setTime(expiryDate.getDate());
			
			rdto.setPosition(position.getValue());
			rdto.setDescription(description.getValue());
			rdto.setExpiryDate(cal1);
			
			try {
				if (!recruitmentid.getValue().equals(DEFAULT_ID_VALUE)) {
					rdto.setId(Integer.parseInt(recruitmentid.getValue()));
					getHumanResourceService().updateRecruitment(rdto);
				} else{
					getHumanResourceService().addRecruitment(rdto);
				}
			} catch (NumberFormatException nfe) {
				//
			} finally {
				//
			}
			form.clearValues();
			getHeadElements().add(new JsScript("alert('更新成功')"));
			getHeadElements().add(
					new JsScript("window.location = './recruitment_manage.htm'"));
		}
		return true;
	}

	public boolean onCancelClick() {
		setRedirect(RecruitmentManage.class);
		return true;
	}

}
