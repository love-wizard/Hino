package com.hino.page.internal;

import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;

import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.ServiceOptionCAMDto;
import com.hino.model.ServiceOption;
import com.hino.model.DistinguishedGroup;
import com.hino.util.Info;

public class ServiceOptionCAM extends BasePage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_ID_VALUE = "系统自动生成";
	
	@Bindable
	private int id;
	
	@Bindable
	private Form form;
	
	private HiddenField sid = new HiddenField("sid", "sid");

	private TextField title = new TextField("title", "尊享团项目名称", 50, true);
	private TextArea description = new TextArea("description", "尊享团项目简介", 50, 10);
		
	private FileField image = new FileField("image", "尊享团项目图片");

	@Bindable
	private Image imageImg;
	
	public ServiceOptionCAM() {
		form = new Form("service_option_cam_form");
	}

	public void onInit()
	{
		
        form.add(sid);
		form.add(title);
		form.add(description);
		form.add(image);
	
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));

		if(id<=0)
		{
			sid.setValue(DEFAULT_ID_VALUE);
		}
		else
		{
			final ServiceOption serviceOption = getTravelResourceService().getServiceOptionById(id);
			sid.setValue(String.valueOf(serviceOption.getId()));
			this.title.setValue(serviceOption.getTitle());
			this.description.setValue(serviceOption.getDescription());
			
			if (serviceOption.getImage()!= null) {
				imageImg = new Image("imageImg");
				imageImg.setSrc(Info.INTERNAL_PATH_PREFIX + serviceOption.getImage());
			}
		}
	}
	
	public boolean onOkClick() {

		if (form.isValid()) {
			ServiceOptionCAMDto serviceOptionDto = new ServiceOptionCAMDto();
			serviceOptionDto.setTitle(this.title.getValue());
			serviceOptionDto.setDescription(this.description.getValue());

			if (!image.getFileItem().getName().trim().equals("")) {
				serviceOptionDto.setImage(image.getFileItem());
			}
						
			try {
				if (!sid.getValue().equals(DEFAULT_ID_VALUE)) {
					serviceOptionDto.setId(Long.parseLong(sid.getValue()));
					getTravelResourceService().modifyServiceOption(serviceOptionDto);
				} else{
					getTravelResourceService().createServiceOption(serviceOptionDto);
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
					new JsScript("window.location = './service_option_manage.htm'"));
		}
		return true;
	}

	public boolean onCancelClick() {
		setRedirect ("./service_option_manage.htm");
		form.removeState(getContext());
		return true;
	}

}
