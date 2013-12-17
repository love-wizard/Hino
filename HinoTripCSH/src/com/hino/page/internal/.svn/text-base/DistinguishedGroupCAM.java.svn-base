package com.hino.page.internal;

import java.util.List;

import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.DistinguishedGroupCAMDto;
import com.hino.model.DistinguishedGroup;
import com.hino.model.Route;
import com.hino.util.Info;

public class DistinguishedGroupCAM extends BasePage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_ID_VALUE = "系统自动生成";

	@Bindable
	private Integer id;

	@Bindable
	private Form form;

	private HiddenField distinguishedGroupId = new HiddenField("distinguishedGroupId", "distinguishedGroupId");
	private TextField title = new TextField("title", "尊享团名称", 50, true);
	private TextArea description = new TextArea("description", "尊享团简介", 50, 10);
	private TextField price = new TextField("price", "价格", 50, true);	
	private Select routeSelect = new Select("route", "选择线路");	

	private FileField image = new FileField("image", "介绍图片");
	
	@Bindable
	private Image imageImg;

	public DistinguishedGroupCAM() {
		form = new Form("distinguished_group_cam_form");
	}

	public void onInit() {
		distinguishedGroupId.setDisabled(true);
		form.add(title);
		form.add(description);
		form.add(routeSelect);
		
		routeSelect.add(new Option("-1", "请选择一条关联路线！"));
		List<Route> orls = this.getTravelResourceService().getAllRoute();
		Route temp;
		for(int i = 0; i < orls.size(); i ++)
		{
			temp = orls.get(i);
			routeSelect.add(new Option("" + temp.getId(), temp.getName()));			
		}
		form.add(distinguishedGroupId);
		form.add(price);
		form.add(image);

		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));

		if (id == null) {
			distinguishedGroupId.setValue(DEFAULT_ID_VALUE);
			routeSelect.setValue("-1");
		} else {
			DistinguishedGroup dg = getTravelResourceService().getDistinguishedGroupById(id);
			distinguishedGroupId.setValue("" + dg.getId());
			title.setValue(dg.getTitle());
			description.setValue(dg.getDescription());
			if(null == dg.getRoute()) {
				routeSelect.setValue("-1");
			} else {
				routeSelect.setValue("" + dg.getRoute().getId());
			}
		
			this.price.setValue(String.valueOf(dg.getPrice()));
			
			if (dg.getImage()!= null) {
				imageImg = new Image("imageImg");
				imageImg.setSrc(Info.INTERNAL_PATH_PREFIX + dg.getImage());
			}
		}
	}

	public boolean onOkClick() {

		if (form.isValid()) {

			DistinguishedGroupCAMDto dgcamdto = new DistinguishedGroupCAMDto();
			dgcamdto.setTitle(title.getValue());
			dgcamdto.setDescription(description.getValue());
			dgcamdto.setPrice(Double.parseDouble(this.price.getValue()));
			
			Route selectedRoute = null;
			if(null != routeSelect.getValue() && !"".equals(routeSelect.getValue())){
				selectedRoute = getTravelResourceService().getRouteById(Long.parseLong(routeSelect.getValue()));
			}			
			dgcamdto.setRoute(selectedRoute);			
			
			if (!image.getFileItem().getName().trim().equals("")) {
				dgcamdto.setImage(image.getFileItem());
			}
			
			try {
				if (!distinguishedGroupId.getValue().equals(DEFAULT_ID_VALUE)) {
					dgcamdto.setId(Integer.parseInt(distinguishedGroupId.getValue()));
					getTravelResourceService().modifyDistinguishedGroup(dgcamdto);
				} else{
					getTravelResourceService().createDistinguishedGroup(dgcamdto);
				}
			} catch (NumberFormatException nfe) {
				//
			} finally {
				//
			}
			
			form.clearValues();
			getHeadElements().add(new JsScript("alert('更新成功')"));
			getHeadElements().add(
					new JsScript("window.location = './distinguished_group_manage.htm'"));
		}
		return true;
	}

	public boolean onCancelClick() {
		setRedirect(DistinguishedGroupManage.class);
		return true;
	}
}
