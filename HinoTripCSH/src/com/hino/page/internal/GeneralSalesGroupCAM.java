package com.hino.page.internal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.control.Checkbox;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Radio;
import org.apache.click.control.RadioGroup;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.CheckList;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.DoubleField;
import org.apache.click.extras.control.IntegerField;
import org.apache.click.util.Bindable;
import org.apache.click.util.ClickUtils;
import org.apache.commons.io.IOUtils;

import com.hino.click.extension.Image;
import com.hino.dto.GroupCAMDto;
import com.hino.dto.RouteCAMDto;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.model.Site;
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GeneralSalesGroupCAM extends BasePage{

	@Bindable
	private Integer id;

	@Bindable private Form form = new Form("group_cam_form");
	
	private DateField depart_time = new DateField("depart_time", "出发时间", 15, true);
	private HiddenField groupid = new HiddenField("groupid", "groupid");
	private TextField group_name = new TextField("group_name", "团名称", true);
	private TextField group_name_en = new TextField("group_name_en", "团名称(English)", true);
	private DoubleField group_price = new DoubleField("group_price", "常规团价格", 5,true);
	private DoubleField vip_price = new DoubleField("vip_price", "常规团会员价", 5,true);
	private IntegerField max_point = new IntegerField("max_point", "最大积分使用", 5, true);
	private IntegerField group_seats = new IntegerField("group_seats", "常规团座位数", 5, true);
	private TextArea group_traffichotel = new TextArea("group_traffichotel", "交通住宿信息", 50, 10);
	private TextArea group_pickup = new TextArea("group_pickup", "接车地点信息", 50, 10);
	private RadioGroup group_route = new RadioGroup("group_route", "选择线路", true);
	private FileField group_ticket = new FileField("group_ticket", "上传票模板");
	private Image group_ticket_template = new Image("group_ticket_template");
	
	private Checkbox multi_days = new Checkbox("multi_days", "多日游");
	//Devon King - 2012/09/27 - TD#20 To indicate if the group is able to use voucher 
	private RadioGroup voucher_applied = new RadioGroup("is_voucher_applied", "是否使用优惠券？");
	private TextField match_code = new TextField("match_code", "优惠券对应码");
	private IntegerField priority = new IntegerField("priority", "显示优先级", 5, true);
	
	//private TextField external_indicator = new TextField("external_indicator", "外部提示", false);
	//private TextField external_indicator_en = new TextField("external_indicator_en", "外部提示EN", false);
	
	//private Checkbox internalBookable = new Checkbox("internalBookable", "常规团内部可预订");
	//private Checkbox externalBookable = new Checkbox("externalBookable", "常规团外部可预订");
	//private Checkbox internalView = new Checkbox("internalView", "常规团内部可见");
	//private Checkbox externalView = new Checkbox("externalView", "常规团外部可见");
	
	//private Checkbox ext_groupon = new Checkbox("ext_groupon", "团购外部可预订");
	//private Checkbox int_groupon = new Checkbox("int_groupon", "团购内部可预订");
	//private Checkbox ext_seckill = new Checkbox("ext_seckill", "秒杀外部可预订");
	//private Checkbox int_seckill = new Checkbox("int_seckill", "秒杀内部可预订");
	private IntegerField seats_groupon_min = new IntegerField("groupon_seats_min", "团购最低座位数", 5, true);
	private IntegerField seats_groupon = new IntegerField("groupon_seats", "团购座位数", 5, true);
	private DoubleField groupon_price = new DoubleField("groupon_price", "团购价格", 5,true);
	private DoubleField groupon_vip_price = new DoubleField("groupon_vip_price", "团购VIP价格", 5,true);
	private TextField groupon_end = new TextField("groupon_end", "团购结束时间", false);
	
	private IntegerField seats_seckill = new IntegerField("seats_seckill", "秒杀座位数", 5, true);
	private DoubleField seckill_price = new DoubleField("seckill_price", "秒杀价格", 5,true);
	private DoubleField seckill_vip_price = new DoubleField("seckill_vip_price", "秒杀VIP价格", 5,true);
	private TextField seckill_start = new TextField("seckill_start", "秒杀开始时间", false);
	private TextField seckill_end = new TextField("seckill_end", "秒杀结束时间", false);
	
	private FileField go_img1 = new FileField("go_img1", "团购大图");
	private FileField go_img2 = new FileField("go_img2", "团购小图");
	private FileField sk_img1 = new FileField("sk_img1", "秒杀大图");
	private FileField sk_img2 = new FileField("sk_img2", "秒杀小图");
	
	private TextField mobilePageLink = new TextField("mobilePageLink", "手机支付页面地址", 50, false);
	
	public void onInit() {
		//routeid.setDisabled(true);
		
		depart_time.setFormatPattern("yyyy-MM-dd");
		
		List<Route> ls = this.getTravelResourceService().getAllRoute();
		Route temp;
		for(int i=0;i<ls.size();i++)
		{
			temp = ls.get(i);
			group_route.add(new Radio(""+temp.getId(),temp.getName()));
		}
		
		//+ Devon King - 2012/09/27 - TD#20 
		voucher_applied.add(new Radio("Y", "是"));
		voucher_applied.add(new Radio("N", "否"));
		
		if(id!=null)
		{
			Group gp = this.getSalesService().viewGroupById(id);
			if(gp!=null)
			{
				groupid.setValue(id+"");
				group_name.setValue(gp.getName());
				group_name_en.setValue(gp.getName_en());
				group_price.setValue(gp.getPrice()+"");
				vip_price.setValue(gp.getVip_price()+"");
				max_point.setValue(gp.getMax_point()+"");
				group_seats.setValue(gp.getSeats()+"");
				group_traffichotel.setValue(gp.getTraffic_hotel_info());
				group_pickup.setValue(gp.getPickup_info());
				depart_time.setValue(TimeFormater.format2(gp.getDepart_date()));
				group_route.setValue(gp.getRoute().getId()+"");
				//group_ticket_template.setSrc("general_sales_group_cam.htm?pageAction=onRenderImage&filename="+gp.getTicket_url());//TODO
				multi_days.setChecked(gp.getMulti_days());
				
				//+ Devon King - 2012/09/27 - TD#20 Voucher applied status
				voucher_applied.setValue(gp.getVoucher_applied());
				priority.setValue(gp.getPriority() + "");
				
				match_code.setValue(gp.getVoucher_match());
				/*
				external_indicator.setValue(gp.getExternal_indicator());
				external_indicator_en.setValue(gp.getExternal_indicator_en());
				internalBookable.setChecked(gp.getInternalBookable());
				externalBookable.setChecked(gp.getExternalBookable());
				internalView.setChecked(gp.getInternalView());
				externalView.setChecked(gp.getExternalView());
				ext_groupon.setChecked(gp.getExt_groupon());
				int_groupon.setChecked(gp.getInt_groupon());
				ext_seckill.setChecked(gp.getExt_seckill());
				int_seckill.setChecked(gp.getInt_seckill());
				*/
				seats_groupon_min.setInteger(gp.getSeats_groupon_min());
				seats_groupon.setInteger(gp.getSeats_groupon());
				groupon_price.setDouble(gp.getGroup_price());
				groupon_vip_price.setDouble(gp.getGroup_vip_price());
				groupon_end.setValue(TimeFormater.format3(gp.getGroupon_end_time()));
				
				seats_seckill.setInteger(gp.getSeats_seckill());
				seckill_price.setDouble(gp.getSeckill_price());
				seckill_vip_price.setDouble(gp.getSeckill_vip_price());
				seckill_start.setValue(TimeFormater.format3(gp.getSeckill_start_time()));
				seckill_end.setValue(TimeFormater.format3(gp.getSeckill_end_time()));
				
				mobilePageLink.setValue("www.hinotravel.com/zh/mobile/groups_booking.htm?routeid=" + gp.getRoute().getId() + "&gid=" + gp.getId() + "&step=2");
				mobilePageLink.setReadonly(true);
				form.add(mobilePageLink);				
				//group_ticket_template.setActionListener(new ActionListener())
				//group_ticket.setRequired(false);
			}
		} else
		{
			groupid.setValue(-1+"");
			group_price.setValue("0.0");
			group_seats.setValue("0");
			vip_price.setValue("0.0");
			max_point.setValue("0");
			seats_groupon.setInteger(0);
			seats_groupon_min.setInteger(0);
			groupon_price.setDouble(0.0);
			groupon_vip_price.setDouble(0.0);
			//groupon_end.setValue(TimeFormater.format3(gp.getGroupon_end_time()));
			
			//+ Devon King - 2012/09/27 - TD#20 Default is false
			voucher_applied.setValue("N");
			priority.setValue("0");
			
			seats_seckill.setInteger(0);
			seckill_price.setDouble(0.0);
			seckill_vip_price.setDouble(0.0);
			//seckill_start.setValue(TimeFormater.format3(gp.getSeckill_start_time()));
			//seckill_end.setValue(TimeFormater.format3(gp.getSeckill_end_time()));
			//group_ticket.setRequired(true);
			//group_ticket_template.setSrc(Info);//TODO
		}
		
		form.add(groupid);
		form.add(group_name);
		form.add(group_name_en);
		form.add(group_price);
		form.add(vip_price);
		form.add(max_point);
		form.add(group_seats);
		form.add(depart_time);
		form.add(group_pickup);
		form.add(group_traffichotel);
		form.add(group_route);
		form.add(group_ticket);
		
		form.add(multi_days);
		form.add(match_code);
		//Devon King - 2012/09/30 - TD#20 Shows voucher_applied radio button on the form 
		form.add(voucher_applied);
		form.add(priority);		
		/*
		form.add(external_indicator);
		form.add(external_indicator_en);
		form.add(internalBookable);
		form.add(externalBookable);
		form.add(internalView);
		form.add(externalView);
		//form.add(ext_groupon);
		//form.add(int_groupon);
		//form.add(ext_seckill);
		//form.add(int_seckill);
		*/
		form.add(seats_groupon);
		form.add(seats_groupon_min);
		form.add(groupon_price);
		form.add(groupon_vip_price);
		form.add(groupon_end);
		form.add(go_img1);
		form.add(go_img2);

		
		form.add(seats_seckill);
		form.add(seckill_price);
		form.add(seckill_vip_price);
		form.add(seckill_start);
		form.add(seckill_end);
		form.add(sk_img1);
		form.add(sk_img2);

		
		if(id!=null)
		{
			form.add(group_ticket_template);
		} 
		
		form.add(new Submit("submit", "保存", this, "onOkClick"));
		
	}
	
	/*
	public ActionResult onRenderImage() {
		byte[] imageData = null;
		try {
            InputStream is = new FileInputStream(getSalesService().getTicketTemplateFile(getContext().getRequestParameter("filename")));
            imageData = IOUtils.toByteArray(is);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

		String contentType = ClickUtils.getMimeType("png");
		return new ActionResult(imageData, contentType);
	}
	*/
	

	public boolean onOkClick() {
		/*
		if(groupid.getValue().equals("-1"))
		{
			group_ticket.setRequired(true);
		} else
		{
			group_ticket.setError(null);
		}*/

		if (form.isValid()) {
			GroupCAMDto gdto = new GroupCAMDto();
			gdto.setName(group_name.getValue());
			gdto.setName_en(group_name_en.getValue());
			gdto.setPickup_info(group_pickup.getValue());
			gdto.setPrice(group_price.getDouble());
			gdto.setVip_price(vip_price.getDouble());
			gdto.setMax_point(max_point.getInteger());
			gdto.setSeats(group_seats.getInteger());
			Calendar c = Calendar.getInstance();
			c.setTime(depart_time.getDate());
			gdto.setDepart_date(c);
			gdto.setTraffic_hotel_info(group_traffichotel.getValue());
			
			gdto.setMulti_days(multi_days.isChecked());
			gdto.setVoucher_match(match_code.getValue());
			//Devon King - 2012/09/30 - TD#20 Get voucher applied value
			gdto.setVoucher_applied(voucher_applied.getValue());
			gdto.setPriority(priority.getInteger());
			
			/*
			gdto.setExternal_indicator(external_indicator.getValue());
			gdto.setExternal_indicator_en(external_indicator_en.getValue());
			gdto.setInternalBookable(internalBookable.isChecked());
			gdto.setExternalBookable(externalBookable.isChecked());
			gdto.setInternalView(internalView.isChecked());
			gdto.setExternalView(externalView.isChecked());
			
			
			gdto.setExt_groupon(ext_groupon.isChecked());
			gdto.setInt_groupon(int_groupon.isChecked());
			gdto.setExt_seckill(ext_seckill.isChecked());
			gdto.setInt_seckill(int_seckill.isChecked());
			*/	
			
			if (!go_img1.getFileItem().getName().trim().equals("")) {
				gdto.setGo_img_1(go_img1.getFileItem());
			}

			if (!go_img2.getFileItem().getName().trim().equals("")) {
				gdto.setGo_img_2(go_img2.getFileItem());
			}
			
			if (!sk_img1.getFileItem().getName().trim().equals("")) {
				gdto.setSk_img_1(sk_img1.getFileItem());
			}
			
			if (!sk_img2.getFileItem().getName().trim().equals("")) {
				gdto.setSk_img_2(sk_img2.getFileItem());
			}

			gdto.setSeats_groupon_min(seats_groupon_min.getInteger());
			gdto.setSeats_groupon(seats_groupon.getInteger());
			gdto.setGroup_price(groupon_price.getDouble());
			gdto.setGroup_vip_price(groupon_vip_price.getDouble());
			gdto.setGroupon_end_time(TimeFormater.parse3(groupon_end.getValue()));
			gdto.setSeats_seckill(seats_seckill.getInteger());
			gdto.setSeckill_price(seckill_price.getDouble());
			gdto.setSeckill_vip_price(seckill_vip_price.getDouble());
			gdto.setSeckill_start_time(TimeFormater.parse3(seckill_start.getValue()));
			gdto.setSeckill_end_time(TimeFormater.parse3(seckill_end.getValue()));
			
			try {
			long rid = Long.parseLong(group_route.getValue());
			gdto.setRouteid(rid);
			} catch(NumberFormatException nfe)
			{
				getHeadElements().add(new JsScript("alert('保存失败')"));
				getHeadElements().add(new JsScript("window.location = './general_sales_group_manage.htm'"));
				return true;
			}
			
			
			ServiceResponse sr;
			int gid = Integer.parseInt(groupid.getValue());
			if(gid==-1)
			{
				if(group_ticket.getFileItem().getSize()!=0)
				{
					gdto.setTicket_file(group_ticket.getFileItem());
				}
				sr = getSalesService().create_group(gdto);
			} else
			{
				gdto.setId(gid);
				if(group_ticket.getFileItem().getSize()!=0)
				{
					gdto.setTicket_file(group_ticket.getFileItem());
				}
				sr = getSalesService().sales_edit_group(gdto);
			}
			
			if(sr.isSucc())
			{
				form.clearValues();
				getHeadElements().add(new JsScript("alert('保存成功')"));
				getHeadElements().add(new JsScript("window.location = './general_sales_group_manage.htm'"));
			} else
			{
				getHeadElements().add(new JsScript("alert('保存失败')"));
				getHeadElements().add(new JsScript("alert(\""+EscapeHtml.nrTonnrr(sr.list_msg())+"\")"));
			}
		}
		return true;
	}

}
