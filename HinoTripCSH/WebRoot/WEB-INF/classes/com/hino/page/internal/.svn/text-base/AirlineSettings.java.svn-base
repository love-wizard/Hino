package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;

import com.hino.dto.IndexImageSettingDto;
import com.hino.util.Info;

public class AirlineSettings extends BasePage {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Bindable private Form form = new Form("imageform");
	
	private FileField image1 = new FileField("image1", "图片1", false);
	private FileField image2 = new FileField("image2", "图片2", false);
	private FileField image3 = new FileField("image3", "图片3", false);
	private FileField image4 = new FileField("image4", "图片4", false);
	private FileField image5 = new FileField("image5", "	图片5", false);
	private FileField image6 = new FileField("image6", "图片6", false);
	
	private TextField link_img1 = new TextField("link1","Link1",50,false);
	private TextField link_img2 = new TextField("link2","Link2",50,false);
	private TextField link_img3 = new TextField("link3","Link3",50,false);
	private TextField link_img4 = new TextField("link4","Link4",50,false);
	private TextField link_img5 = new TextField("link5","Link5",50,false);
	private TextField link_img6 = new TextField("link6","Link6",50,false);
	
	private Submit submit = new Submit("sumbit", "更新图片及链接");
	
	public void onInit()
	{
	
		form.add(image1);
		//form.add(link_img1);
		form.add(image2);
		//form.add(link_img2);
		form.add(image3);
		//form.add(link_img3);
		form.add(image4);
		//form.add(link_img4);
		form.add(image5);
		//form.add(link_img5);
		form.add(image6);
		//form.add(link_img6);
		
		form.add(submit);
		
		link_img1.setValue(Info.imgLink1);
		link_img2.setValue(Info.imgLink2);
		link_img3.setValue(Info.imgLink3);
		link_img4.setValue(Info.imgLink4);
		link_img5.setValue(Info.imgLink5);
		link_img6.setValue(Info.imgLink6);

		List<com.hino.model.Info> info;
		info = this.getWebService().getIndexInfo();
//		if(null!=info)
//		{
//			link_img1.setValue( info.get(0).getLinkUrl());
//			link_img2.setValue( info.get(1).getLinkUrl());
//			link_img3.setValue( info.get(2).getLinkUrl());
//			link_img4.setValue( info.get(3).getLinkUrl());
//			link_img5.setValue( info.get(4).getLinkUrl());
//			link_img6.setValue( info.get(6).getLinkUrl());
//		}

		form.restoreState(getContext());
		submit.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	if(form.isValid())
            	{
            		IndexImageSettingDto airlineImageSettingDto = new IndexImageSettingDto();
            		
            		airlineImageSettingDto.setImage1(image1.getFileItem());
            		airlineImageSettingDto.setImage2(image2.getFileItem());
            		airlineImageSettingDto.setImage3(image3.getFileItem());
            		airlineImageSettingDto.setImage4(image4.getFileItem());
            		airlineImageSettingDto.setImage5(image5.getFileItem());
            		airlineImageSettingDto.setImage6(image6.getFileItem());
            		
            		airlineImageSettingDto.setLink1(link_img1.getValue());
            		airlineImageSettingDto.setLink2(link_img2.getValue());
            		airlineImageSettingDto.setLink3(link_img3.getValue());
            		airlineImageSettingDto.setLink4(link_img4.getValue());
            		airlineImageSettingDto.setLink5(link_img5.getValue());
            		airlineImageSettingDto.setLink6(link_img6.getValue());

            		getWebService().airlineImageSetting(airlineImageSettingDto);
            		getHeadElements().add(new JsScript("alert('操作执行成功!')"));
            	}
            	
                return true;
            }
        });
	}
	
	public void onRender() {

	}
}
