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
import org.apache.click.control.Radio;
import org.apache.click.control.RadioGroup;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.CheckList;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.RouteCAMDto;
import com.hino.model.Route;
import com.hino.model.Site;
import com.hino.util.Info;

public class RouteCAM extends BasePage{
	private static final String DEFAULT_ID_VALUE = "系统自动生成";
	private List<Option> site_options = new ArrayList<Option>();
	private CheckList sitechecklist = new CheckList("sitelist", "可选择景点");

	@Bindable
	private Integer id;

	@Bindable
	private Form form;

	private HiddenField routeid = new HiddenField("routeid", "routeid");
	private TextField routename = new TextField("routename", "线路名称", 50, true);
	private TextField routename_en = new TextField("routename_en", "线路名称(English)", 50, true);
	private TextArea routedesc = new TextArea("routedesc", "参考行程", 50, 10);
	private TextArea routedesc_en = new TextArea("routedesc_en", "参考行程(English)", 50, 10);
	private TextArea routeservice = new TextArea("routeservice", "服务标准", 85, 15);
	private TextArea routeservice_en = new TextArea("routeservice_en", "服务标准(English)", 50, 10);
	private TextArea routetips = new TextArea("routetips", "友情提示", 85, 15);
	private TextArea routetips_en = new TextArea("routetips_en", "友情提示(English)", 50, 10);
	private TextArea compimgdesc = new TextArea("compimgdesc", "缩略图描述", 50, 10);
	private TextArea compimgdesc_en = new TextArea("compimgdesc_en", "缩略图描述(English)", 50, 10);
	private FileField fullimgfile = new FileField("siteimg", "线路图片");
	private FileField compimgfile = new FileField("compsiteimg", "线路缩略图"); 
	
	private Select original_route = new Select("base_route", "选择源线路");
	private TextArea pickup_info = new TextArea("pickup_info", "接站信息", 85, 15);
	// Ken Chen 2012/11/01 页面新需求 begin
	private TextArea characteristic = new TextArea("characteristic", "行程特色", 85, 15);
	private TextField satisfaction = new TextField("satisfaction", "线路满意度", 50, true);
	private FileField routeMap = new FileField("routeMap", "路线行程图");
	private FileField routeMapThumbl = new FileField("routeMapThumbl", "路线行程缩略图");
	private FileField imageUrl1 = new FileField("imageUrl1", "线路轮换图片2");
	private FileField imageUrl2 = new FileField("imageUrl2", "线路轮换图片3");
	private FileField imageUrl3 = new FileField("imageUrl3", "线路轮换图片4");
	private FileField imageUrl4 = new FileField("imageUrl4", "线路轮换图片5");
	@Bindable
	private Image routeMapImg;
	@Bindable
	private Image routeMapThumblImg;
	@Bindable
	private Image image1;
	@Bindable
	private Image image2;
	@Bindable
	private Image image3;
	@Bindable
	private Image image4; 
	// Ken Chen 2012/11/01 页面新需求 end
	public ActionLink addSchedule = new ActionLink("addSchedule", "添加行程");
	public ActionLink manageSchedule = new ActionLink("manageSchedule", "管理行程");
	
	@Bindable
	private Image fullimg;
	@Bindable
	private Image compimg;

	public RouteCAM() {
		form = new Form("route_cam_form");
	}

	public void onInit() {
		routeid.setDisabled(true);
		form.add(routename);
		form.add(routedesc);
		form.add(addSchedule);
		form.add(manageSchedule);
		form.add(routeservice);
		form.add(routetips);
		form.add(routename_en);
		form.add(routedesc_en);
		form.add(routeservice_en);
		form.add(routetips_en);
		form.add(original_route);
		
		original_route.add(new Option("-1", "请选择一个原始路线！"));
		List<Route> orls = this.getTravelResourceService().getAllRoute();
		Route temp;
		for(int i = 0; i < orls.size(); i ++)
		{
			temp = orls.get(i);
			original_route.add(new Option("" + temp.getId(), temp.getName()));			
		}
		form.add(pickup_info);
		form.add(characteristic);
		form.add(routeid);
		sitechecklist.setOptionList(site_options);
		
		form.add(sitechecklist);
		List<Site> sl = getTravelResourceService().getAllSite();
		for (int i = 0; i < sl.size(); i++) {
			site_options.add(new Option(sl.get(i).getId(),sl.get(i).getName()));
        }

		form.add(fullimgfile);
		form.add(compimgfile);
		form.add(compimgdesc);
		form.add(compimgdesc_en);
		
		form.add(satisfaction);
		form.add(routeMap);
		form.add(routeMapThumbl);
		form.add(imageUrl1);
		form.add(imageUrl2);
		form.add(imageUrl3);
		form.add(imageUrl4);
		
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));

		if (id == null) {
			routeid.setValue(DEFAULT_ID_VALUE);
			original_route.setValue("-1");
		} else {
			Route r = getTravelResourceService().getRouteById(id);
			routeid.setValue("" + r.getId());
			routename.setValue(r.getName());
			routedesc.setValue(r.getSchedule());
			routeservice.setValue(r.getService());
			routetips.setValue(r.getHint());
			routename_en.setValue(r.getName_en());
			routedesc_en.setValue(r.getSchedule_en());
			routeservice_en.setValue(r.getService_en());
			routetips_en.setValue(r.getHint_en());
			compimgdesc_en.setValue(r.getThumbDesc_en());
			compimgdesc.setValue(r.getThumbDesc());
			if(null == r.getOriginalRoute()) {
				original_route.setValue("-1");
			} else {
				original_route.setValue("" + r.getOriginalRoute().getId());
			}
			
			pickup_info.setValue(r.getPickup_info());
			characteristic.setValue(r.getCharacteristic());
			
			List<String> ls = new ArrayList<String>();
			List<Site> lst = r.getSitelist();
			for (int i=0;i<lst.size();i++)
			{
				ls.add(lst.get(i).getId()+"");
			}

			sitechecklist.setSelectedValues(ls);
			
			if (r.getImageUrl()!= null) {
				fullimg = new Image("fullimg");
				fullimg.setSrc(Info.INTERNAL_PATH_PREFIX+r.getImageUrl());
			}

			if (r.getThumbUrl()!= null) {
				compimg = new Image("compimg");
				compimg.setSrc(Info.INTERNAL_PATH_PREFIX+r.getThumbUrl());
			}
			
			this.satisfaction.setValue(String.valueOf(r.getScatisfaction()));
			
			if (r.getRouteMap()!= null) {
				routeMapImg = new Image("routeMapImg");
				routeMapImg.setSrc(Info.INTERNAL_PATH_PREFIX+r.getRouteMap());
			}

			if (r.getRouteMapThumbl()!= null) {
				this.routeMapThumblImg = new Image("routeMapThumblImg");
				routeMapThumblImg.setSrc(Info.INTERNAL_PATH_PREFIX+r.getRouteMapThumbl());
			}
			if (r.getImageUrl1()!= null) {
				this.image1 = new Image("image1");
				image1.setSrc(Info.INTERNAL_PATH_PREFIX+r.getImageUrl1());
			}

			if (r.getImageUrl2()!= null) {
				image2 = new Image("image2");
				image2.setSrc(Info.INTERNAL_PATH_PREFIX+r.getImageUrl2());
			}
			if (r.getImageUrl3()!= null) {
				image3 = new Image("image3");
				image3.setSrc(Info.INTERNAL_PATH_PREFIX+r.getImageUrl3());
			}

			if (r.getImageUrl4()!= null) {
				image4 = new Image("image4");
				image4.setSrc(Info.INTERNAL_PATH_PREFIX+r.getImageUrl4());
			}
			
			addSchedule.setActionListener(new ActionListener() {
	            private static final long serialVersionUID = 1L;
	            public boolean onAction(Control source) {
	            	//setRedirect("./schedule_cam.htm?id=" + id);
	            	setRedirect(ScheduleCAM.class);
	                return false;
	            }
	        });
			
			manageSchedule.setActionListener(new ActionListener() {
				private static final long serialVersionUID = 1L;
				public boolean onAction(Control source) {
	                setRedirect("./schedule_manage.htm?id=" + id);
	                return false;
	            }
			});
		}
	}

	public boolean onOkClick() {

		if (form.isValid()) {
			
			List<String> ls = sitechecklist.getSelectedValues();
			//System.out.println(ls);
			RouteCAMDto rcamdto = new RouteCAMDto();
			rcamdto.setRoutename(routename.getValue());
			rcamdto.setRoutedesc(routedesc.getValue());
			rcamdto.setRouteservice(routeservice.getValue());
			rcamdto.setRoutetips(routetips.getValue());
			
			rcamdto.setRoutename_en(routename_en.getValue());
			rcamdto.setRoutedesc_en(routedesc_en.getValue());
			rcamdto.setRouteservice_en(routeservice_en.getValue());
			rcamdto.setRoutetips_en(routetips_en.getValue());
			Route originalRoute = null;
			if(null != original_route.getValue() && !"".equals(original_route.getValue())){
				originalRoute = getTravelResourceService().getRouteById(Long.parseLong(original_route.getValue()));
			}			
			rcamdto.setOriginalRoute(originalRoute);
			rcamdto.setPickup_info(pickup_info.getValue());
			//Kevin Zhong - Replce \r\n with <br>
			rcamdto.setCharacteristic(characteristic.getValue());
			
			rcamdto.setSitelist(sitechecklist.getSelectedValues());
			rcamdto.setCompdesc(compimgdesc.getValue());
			rcamdto.setCompdesc_en(compimgdesc_en.getValue());
			if (!fullimgfile.getFileItem().getName().trim().equals("")) {
				rcamdto.setFullimgfile(fullimgfile.getFileItem());
			}

			if (!compimgfile.getFileItem().getName().trim().equals("")) {
				rcamdto.setCompimgfile(compimgfile.getFileItem());
			}
			
			rcamdto.setScatisfaction(Double.parseDouble(this.satisfaction.getValue()));
			
			if (!routeMap.getFileItem().getName().trim().equals("")) {
				rcamdto.setRouteMap(routeMap.getFileItem());
			}

			if (!this.routeMapThumbl.getFileItem().getName().trim().equals("")) {
				rcamdto.setRouteMapThumbl(routeMapThumbl.getFileItem());
			}
			
			if (!this.imageUrl1.getFileItem().getName().trim().equals("")) {
				rcamdto.setImageUrl1(imageUrl1.getFileItem());
			}
			
			if (!this.imageUrl2.getFileItem().getName().trim().equals("")) {
				rcamdto.setImageUrl2(imageUrl2.getFileItem());
			}

			if (!this.imageUrl3.getFileItem().getName().trim().equals("")) {
				rcamdto.setImageUrl3(imageUrl3.getFileItem());
			}

			if (!this.imageUrl4.getFileItem().getName().trim().equals("")) {
				rcamdto.setImageUrl4(imageUrl4.getFileItem());
			}
			
			
			try {
				if (!routeid.getValue().equals(DEFAULT_ID_VALUE)) {
					rcamdto.setRouteid(Integer.parseInt(routeid.getValue()));
					getTravelResourceService().modify_route(rcamdto);
				} else{
					getTravelResourceService().create_route(rcamdto);
				}
			} catch (NumberFormatException nfe) {
				//
			} finally {
				//
			}
			
			/*
			SiteCAMDto scamdto = new SiteCAMDto();
			// siteid.getValue()

			scamdto.setSitename(routename.getValue());
			scamdto.setSitedesc(routedesc.getValue());
			// fullimgfile.getFileItem().getClass().getName();

			// setRedirect(SiteCAM.class);
			 */
			form.clearValues();
			getHeadElements().add(new JsScript("alert('更新成功')"));
			getHeadElements().add(
					new JsScript("window.location = './route_manage.htm'"));
		}
		return true;
	}

	public boolean onCancelClick() {
		setRedirect(RouteManage.class);
		return true;
	}
}
