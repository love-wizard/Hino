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
import com.hino.dto.SiteCAMDto;
import com.hino.model.Site;
import com.hino.util.Info;

public class SiteCAM extends BasePage {
	private static final String DEFAULT_ID_VALUE = "系统自动生成";

	@Bindable
	private Integer id;

	@Bindable
	private Form form = new Form("site_cam_form");

	private HiddenField siteid = new HiddenField("siteid", "siteid");
	private TextField sitename = new TextField("sitename", "景点名称", 50, true);
	private TextField sitename_en = new TextField("sitename_cn", "景点名称(English)", 50, true);
	private TextArea sitedesc = new TextArea("sitedesc", "景点描述", 50, 10);
	private TextArea sitedesc_en = new TextArea("sitedesc_en", "景点描述(English)", 50, 10);
	private FileField fullimgfile = new FileField("siteimg", "景点图片");
	private FileField compimgfile = new FileField("compsiteimg", "景点缩略图");
	private FileField compimgfile_en = new FileField("compsiteimg_en", "景点缩略图(English)");

	@Bindable
	private Image fullimg;
	@Bindable
	private Image compimg;
	@Bindable
	private Image compimg_en;

	public void onInit() {
		siteid.setDisabled(true);
		form.add(siteid);
		form.add(sitename);
		form.add(sitename_en);
		form.add(sitedesc);
		form.add(sitedesc_en);
		form.add(fullimgfile);
		form.add(compimgfile);
		form.add(compimgfile_en);
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));

		if (id == null) {
			siteid.setValue(DEFAULT_ID_VALUE);
		} else {
			Site s = getTravelResourceService().getSiteById(id);
			
			if(s!=null)
			{
				siteid.setValue("" + s.getId());
				sitename_en.setValue(s.getName_en());
				sitedesc_en.setValue(s.getDescription_en());
				sitename.setValue(s.getName());
				sitedesc.setValue(s.getDescription());
				if (s.getImageurl() != null) {
					fullimg = new Image("fullimg");
					fullimg.setSrc(Info.INTERNAL_PATH_PREFIX+s.getImageurl());
				}

				if (s.getThumburl() != null) {
					compimg = new Image("compimg");
					compimg.setSrc(Info.INTERNAL_PATH_PREFIX+s.getThumburl());
				}
				
				if (s.getThumburl() != null) {
					compimg_en = new Image("compimg_en");
					compimg_en.setSrc(Info.INTERNAL_PATH_PREFIX+s.getThumburl_en());
				}
			} else
			{
				getHeadElements().add(new JsScript("alert('不存在')"));
				getHeadElements().add(
						new JsScript("window.location = './site_manage.htm'"));
			}
			
		}
	}

	public boolean onOkClick() {

		if (form.isValid()) {
			SiteCAMDto scamdto = new SiteCAMDto();
			// siteid.getValue()

			scamdto.setSitename(sitename.getValue());
			scamdto.setSitedesc(sitedesc.getValue());
			scamdto.setSitename_en(sitename_en.getValue());
			scamdto.setSitedesc_en(sitedesc_en.getValue());
			if (!fullimgfile.getFileItem().getName().trim().equals("")) {
				scamdto.setFullimgfile(fullimgfile.getFileItem());
			}

			if (!compimgfile.getFileItem().getName().trim().equals("")) {
				scamdto.setCompimgfile(compimgfile.getFileItem());
			}

			if (!compimgfile_en.getFileItem().getName().trim().equals("")) {
				scamdto.setCompimgfile_en(compimgfile_en.getFileItem());
			}
			
			try {
				if (!siteid.getValue().equals(DEFAULT_ID_VALUE)) {
					scamdto.setId(Integer.parseInt(siteid.getValue()));
					getTravelResourceService().modify_site(scamdto);
				} else{
					getTravelResourceService().create_site(scamdto);
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
					new JsScript("window.location = './site_manage.htm'"));

			// setRedirect(SiteCAM.class);

		}
		return true;
	}

	public boolean onCancelClick() {
		setRedirect(SiteManage.class);
		return true;
	}

}
