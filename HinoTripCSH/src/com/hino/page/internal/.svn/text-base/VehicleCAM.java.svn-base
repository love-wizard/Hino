package com.hino.page.internal;

import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DoubleField;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.VehicleCAMDto;
import com.hino.model.Vehicle;
import com.hino.util.Info;

public class VehicleCAM extends BasePage{
	private static final String DEFAULT_ID_VALUE = "系统自动生成";

	@Bindable
	private Integer id;

	@Bindable
	private Form form = new Form("vehicle_cam_form");

	private HiddenField vehicleid = new HiddenField("vehicleid", "vehicleid");
	private TextField vehiclename = new TextField("vehiclename", "车辆名称", 50, true);
	private TextField vehiclename_en = new TextField("vehiclename_cn", "车辆名称(English)", 50, true);
	private TextArea vehicledesc = new TextArea("vehicledesc", "车辆描述", 50, 10);
	private TextArea vehicledesc_en = new TextArea("vehicledesc_en", "车辆描述(English)", 50, 10);
	private FileField fullimgfile = new FileField("vehicleimg", "车辆图片");
	private DoubleField vehicleprice = new DoubleField("vehicleprice", "价格");
	
	@Bindable
	private Image fullimg;

	public void onInit() {
		vehicleid.setDisabled(true);
		form.add(vehicleid);
		form.add(vehiclename);
		form.add(vehiclename_en);
		form.add(vehicledesc);
		form.add(vehicledesc_en);
		form.add(fullimgfile);
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));

		if (id == null) {
			vehicleid.setValue(DEFAULT_ID_VALUE);
		} else {
			Vehicle s = getTravelResourceService().getVehicleById(id);
			
			if(s!=null)
			{
				vehicleid.setValue("" + s.getId());
				vehiclename_en.setValue(s.getName_en());
				vehicledesc_en.setValue(s.getDesci_en());
				vehiclename.setValue(s.getName());
				vehicledesc.setValue(s.getDesci());
				if (s.getImg_path() != null) {
					fullimg = new Image("fullimg");
					fullimg.setSrc(Info.INTERNAL_PATH_PREFIX+s.getImg_path());
				}

			} else
			{
				getHeadElements().add(new JsScript("alert('不存在')"));
				getHeadElements().add(
						new JsScript("window.location = './vehicle_manage.htm'"));
			}
			
		}
	}

	public boolean onOkClick() {

		if (form.isValid()) {
			VehicleCAMDto scamdto = new VehicleCAMDto();
			// vehicleid.getValue()

			scamdto.setVehiclename(vehiclename.getValue());
			scamdto.setVehicledesc(vehicledesc.getValue());
			scamdto.setVehiclename_en(vehiclename_en.getValue());
			scamdto.setVehicledesc_en(vehicledesc_en.getValue());
			if (!fullimgfile.getFileItem().getName().trim().equals("")) {
				scamdto.setFullimgfile(fullimgfile.getFileItem());
			}

			try {
				if (!vehicleid.getValue().equals(DEFAULT_ID_VALUE)) {
					scamdto.setId(Integer.parseInt(vehicleid.getValue()));
					getTravelResourceService().modify_vehicle(scamdto);
				} else{
					getTravelResourceService().create_vehicle(scamdto);
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
					new JsScript("window.location = './vehicle_manage.htm'"));

			// setRedirect(SiteCAM.class);

		}
		return true;
	}

	public boolean onCancelClick() {
		setRedirect(VehicleManage.class);
		return true;
	}
}
