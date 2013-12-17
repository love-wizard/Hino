package com.hino.page.internal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Checkbox;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.IntegerField;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.AirlineDto;
import com.hino.dto.SiteCAMDto;
import com.hino.model.Airline;
import com.hino.model.AirlineCatalog;
import com.hino.model.AirlineCompany;
import com.hino.model.Airport;
import com.hino.model.Route;
import com.hino.model.Site;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;

public class AirlineCAM extends BasePage {
	private static final String DEFAULT_ID_VALUE = "系统自动生成";

	@Bindable
	private Integer id;

	@Bindable
	private Form form = new Form("airline_cam_form");

	private HiddenField airlineid = new HiddenField("airlineid", "airlineid");
	//private TextField AirlineCompnay = new TextField("AirlineCompnay", "航空公司", 50, true);
	private TextField flight_number = new TextField("flight_number", "flight_number", 50, true);
	private DateField depart_time = new DateField("depart_time", "出发开始日期&时间（当地）", 25, true);
	private DateField depart_end_time = new DateField("depart_end_time", "出发结束日期&时间（当地）", 25, true);	
	private DateField return_depart_time = new DateField("return_depart_time", "返程截止日期（当地）", 25, false);
	private IntegerField return_period = new IntegerField("return_period", "返程时间长度（天/月）", 5, false);
	private TextField luggage_allowance = new TextField("luggage_allowance", "行李重量", 10, true);
	private TextField price = new TextField("price", "价格", 10, true);
	private TextArea note = new TextArea("note", "备注", 50, 10);
	public ActionLink ManageAirportLink = new ActionLink("ManageAirport", "管理城市&机场");
	Select routeSlct = new Select("airportid", "出发城市&机场", true);
	Select routeSlct2 = new Select("airportid2", "到达城市&机场", true);
	Select routeSlct3 = new Select("type", "单程/往返", true);
	Select routeSlct4 = new Select("airline_company", "航空公司", true);
	Select airlineAreas = new Select("airline_area", "所属区域", true);
	private TextField flight_connections = new TextField("flight_connections", "转机信息", 100, false);
	private TextField price_comment = new TextField("price_comment", "价格备注", 10, false);
	
	private DateField sale_begin_date = new DateField("sale_begin_date", "起售日期", 15, true);
	private DateField sale_end_date = new DateField("sale_end_date", "停售日期", 15, true);
	
	private TextField discounted_price = new TextField("discounted_price", "特价价格", 10, false);
	private DateField discount_end_time = new DateField("discount_end_time", "特价截止时间（当地）", 25, false);
	
	private Select select_max_return_limit_type = new Select("select_max_return_limit_type", "返程日期限制类型");
	private Checkbox recommended = new Checkbox("recommended", "推荐航线");
	private DateField publish_date = new DateField("publish_date", "发布日期", 25, false);
	
	public void onInit() {
		airlineid.setDisabled(true);
		//change to city-airport : From	
		routeSlct.setDefaultOption(new Option("请选择出发城市&机场"));
        DataProvider<Option> routeDataPrvd = new DataProvider<Option>() {
			private static final long serialVersionUID = -8107625833664571011L;

			public List<Option> getData() {
				List<Airport> allAirport = getAirlineService().getAirportList();
				List<Option> Options = new ArrayList<Option>();
				for(Airport airport : allAirport) {
					Options.add(new Option(airport.getId(), EscapeHtml.htmlDecode(airport.getAirport_city() + airport.getAirport_name())));
				}
                return Options;
            }
        };
        routeSlct.setDataProvider(routeDataPrvd);

        //change to city-airport : To
		routeSlct2.setDefaultOption(new Option("请选择到达城市&机场"));
		DataProvider<Option> routeDataPrvd2 = new DataProvider<Option>() {
			private static final long serialVersionUID = -8107625833664571011L;

			public List<Option> getData() {
				List<Airport> allAirport = getAirlineService().getAirportList();
				List<Option> Options = new ArrayList<Option>();
				for(Airport airport : allAirport) {
					Options.add(new Option(airport.getId(), EscapeHtml.htmlDecode(airport.getAirport_city() + airport.getAirport_name())));
				}
                return Options;
            }
        };
        routeSlct2.setDataProvider(routeDataPrvd2);
        
        routeSlct3.add(new Option("请选择单程/往返"));
        routeSlct3.add(new Option("1","单程"));
        routeSlct3.add(new Option("2","往返"));
                
        airlineAreas.setDefaultOption(new Option("请选择所属区域"));
		DataProvider<Option> airlineAreasDataPrvd = new DataProvider<Option>() {
			private static final long serialVersionUID = -8107625833664571011L;

			public List<Option> getData() {
				List<AirlineCatalog> allAirlineCats = getAirlineService().getAirlineCatalogList();
				List<Option> Options = new ArrayList<Option>();
				for(AirlineCatalog acat : allAirlineCats) {
					Options.add(new Option(acat.getId(), acat.getCatalog_name()));
				}
                return Options;
            }
        };
        airlineAreas.setDataProvider(airlineAreasDataPrvd);
 
        routeSlct4.setDefaultOption(new Option("请选择航空公司"));
        DataProvider<Option> routeDataPrvd4 = new DataProvider<Option>() {
			private static final long serialVersionUID = -8107625833664571011L;

			public List<Option> getData() {
				List<AirlineCompany> allAirlineCompany = getAirlineService().getAirlineCompanyList();
				List<Option> Options = new ArrayList<Option>();
				for(AirlineCompany airlineCompany : allAirlineCompany) {
					Options.add(new Option(airlineCompany.getId(), EscapeHtml.htmlDecode(airlineCompany.getCompany_full_name())));
				}
                return Options;
            }
        };
        routeSlct4.setDataProvider(routeDataPrvd4);
   
        ManageAirportLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(AirportManage.class);
                return true;
            }
        });
        
        select_max_return_limit_type.setDefaultOption(new Option("请选择返程日期限制类型"));
        select_max_return_limit_type.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				
				optionList.add(new Option(0, Info.ALLM_STR_CN[0]));
				optionList.add(new Option(1, Info.ALLM_STR_CN[1]));
				optionList.add(new Option(2, Info.ALLM_STR_CN[2]));
				
				return optionList;
			}
		});
        
        form.add(routeSlct);
        form.add(routeSlct2);
        form.add(airlineAreas);
        form.add(airlineid);
        depart_time.setFormatPattern("yyyy-MM-dd");
        depart_end_time.setFormatPattern("yyyy-MM-dd");
        return_depart_time.setFormatPattern("yyyy-MM-dd");
        form.add(depart_time);
        form.add(depart_end_time);
        form.add(routeSlct3);
        form.add(select_max_return_limit_type);
        
        form.add(return_depart_time);
        form.add(return_period);
		form.add(routeSlct4);
		form.add(luggage_allowance);
		form.add(price);
		form.add(price_comment);
		form.add(flight_connections);
		sale_begin_date.setFormatPattern("yyyy-MM-dd");
		sale_end_date.setFormatPattern("yyyy-MM-dd");
		form.add(sale_begin_date);
		form.add(sale_end_date);
		form.add(discounted_price);
		discount_end_time.setFormatPattern("yyyy-MM-dd");
		form.add(discount_end_time);
		form.add(recommended);
		publish_date.setFormatPattern("yyyy-MM-dd");
		form.add(publish_date);
		//form.add(note);
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));

		if (id == null) {
			airlineid.setValue(DEFAULT_ID_VALUE);
		} else {
			Airline s = getAirlineService().getAirline(id);
			
			if(s!=null)
			{
				airlineid.setValue("" + s.getId());
				routeSlct.setValue(Long.toString(getAirlineService().getAirport(s.getDeparture().getId()).getId()));
				routeSlct2.setValue(Long.toString(getAirlineService().getAirport(s.getArrival().getId()).getId()));
				routeSlct3.setValue(Long.toString(s.getType()));
				airlineAreas.setValue(Long.toString(s.getDefault_catalog_id()));

				routeSlct4.setValue(Long.toString(getAirlineService().getAirlineCompany(Long.parseLong(s.getAirline())).getId()));
				flight_number.setValue(s.getFlight_number());
				depart_time.setDate(s.getDeparture_begin_date().getTime());
				depart_end_time.setDate(s.getDeparture_end_date().getTime());
				if(s.getType() == 2)
				{
					if(s.getReturn_begin_date()!=null){
						return_depart_time.setDate(s.getReturn_begin_date().getTime());
					}
					if(s.getReturnPeriod()!=0){
						return_period.setInteger(s.getReturnPeriod());
					}
					price.setValue(Long.toString(s.getRound_trip_price()));
					select_max_return_limit_type.setValue(Long.toString(s.getMax_return_limit_type()));
				}
				else
				{
					price.setValue(Long.toString(s.getSingle_trip_price()));
				}
				luggage_allowance.setValue(Long.toString(s.getLuggage_allowance()));
				price_comment.setValue(s.getPriceComment());
				flight_connections.setValue(s.getFlightConnections());
				sale_begin_date.setDate(s.getSale_begin_date().getTime());
				sale_end_date.setDate(s.getSale_end_date().getTime());
				if(s.getDiscountedPrice()!=0){
					discounted_price.setValue(Long.toString(s.getDiscountedPrice()));
				}
				if(s.getDiscountEndTime()!=null){
					discount_end_time.setDate(s.getDiscountEndTime().getTime());
				}
				
				((Checkbox)form.getField("recommended")).setChecked(s.isRecommended());
				if(null!=s.getPublish_date())
				{
					this.publish_date.setDate(s.getPublish_date().getTime());
				}
				else
				{
					Calendar cal2=Calendar.getInstance();
					this.publish_date.setDate(cal2.getTime());
				}
				
			} else {
				getHeadElements().add(new JsScript("alert('不存在')"));
				getHeadElements().add(
						new JsScript("window.location = './airline_manage.htm'"));
			}
		}
	}

	public boolean onOkClick() {

		if (form.isValid()) {
			AirlineDto scamdto = new AirlineDto();
			// siteid.getValue()
			Calendar cal1=Calendar.getInstance();
			cal1.setTime(depart_time.getDate());
			
			Calendar cal2=Calendar.getInstance();
			cal2.setTime(depart_end_time.getDate());
			scamdto.setDeparture_end_date(cal2);
			if("2".equals(routeSlct3.getValue()))
			{
				if("".equals(select_max_return_limit_type.getValue()))
				{
					getHeadElements().add(new JsScript("alert('往返类型航班，请选择“返程日期限制类型”！')"));
					return false;

				}
				else
				{
					scamdto.setMax_return_limit_type(Integer.parseInt((select_max_return_limit_type.getValue())));
				}
				
				Calendar cal3=Calendar.getInstance();
				Calendar cal4=Calendar.getInstance();
				if(return_depart_time.getDate()!=null){
					cal3.setTime(return_depart_time.getDate());
					scamdto.setReturn_begin_date(cal3);
				}
				else
				{
					if(scamdto.getMax_return_limit_type()==Info.AL_BACK_LIMIT_DEADLINE)
					{	
						getHeadElements().add(new JsScript("alert('返程日期限制类型“旅行截止日期“，请输入“旅行截止日期“！')"));
						return false;
					}
				}
				
				scamdto.setReturn_end_date(cal4);
				if("".equals(return_period.getValue()))
				{
					if(scamdto.getMax_return_limit_type()==Info.AL_BACK_LIMIT_MAX_DAY || scamdto.getMax_return_limit_type()==Info.AL_BACK_LIMIT_MAX_MONTH)
					{	
						getHeadElements().add(new JsScript("alert('返程日期限制类型“" + Info.ALLM_STR_CN[scamdto.getMax_return_limit_type()] + "“，请输入“" + Info.ALLM_STR_CN[scamdto.getMax_return_limit_type()] + "”！')"));
						return false;
					}
				}
				else
				{	
					scamdto.setReturnPeriod(return_period.getInteger());
				}
			}

			Airport departure = new Airport();
			departure.setId(Long.parseLong(routeSlct.getValue()));
			scamdto.setDeparture(departure);
			
			Airport arrival = new Airport();
			arrival.setId(Long.parseLong(routeSlct2.getValue()));
			scamdto.setArrival(arrival);			
			scamdto.setDefault_catalog_id(Integer.valueOf(airlineAreas.getValue()));
			scamdto.setType(Integer.valueOf(routeSlct3.getValue()));
			scamdto.setDeparture_begin_date(cal1);
			scamdto.setAirline(routeSlct4.getValue());
			scamdto.setLuggage_allowance(Integer.parseInt((luggage_allowance.getValue())));
			scamdto.setPriceComment(price_comment.getValue());
			scamdto.setFlightConnections(flight_connections.getValue());
			if(discounted_price.getValue() != ""){
				scamdto.setDiscountedPrice(Integer.parseInt(discounted_price.getValue()));
			}
			Calendar cal7 = Calendar.getInstance();
			if(discount_end_time.getDate()!=null){
				cal7.setTime(discount_end_time.getDate());
				scamdto.setDiscountEndTime(cal7);
			}
			
			Calendar cal5 = Calendar.getInstance();
			Calendar cal6 = Calendar.getInstance();
			cal5.setTime(sale_begin_date.getDate());
			cal6.setTime(sale_end_date.getDate());
			scamdto.setSale_begin_date(cal5);
			scamdto.setSale_end_date(cal6);
			
			if ("1".equals(routeSlct3.getValue()))
				scamdto.setSingle_trip_price(Integer.parseInt(price.getValue()));
			else
				scamdto.setRound_trip_price(Integer.parseInt(price.getValue()));
			
			scamdto.setFlight_number(flight_number.getValue());
			scamdto.setRecommended(((Checkbox)form.getField("recommended")).isChecked());
			if(publish_date.equals(""))
			{
				cal2.setTime(this.publish_date.getDate());
			}
			else
			{
				cal2=Calendar.getInstance();
			}
			scamdto.setPublish_date(cal2);
			try {
				if (!airlineid.getValue().equals(DEFAULT_ID_VALUE)) {
					scamdto.setId(Integer.parseInt(airlineid.getValue()));
					getAirlineService().updateAirline(scamdto);
				} else{
					getAirlineService().addAirline(scamdto);
				}
			} catch (NumberFormatException nfe) {
				//
			} finally {
				//
			}

			form.clearValues();
			getHeadElements().add(new JsScript("alert('更新成功')"));
			getHeadElements().add(
					new JsScript("window.location = './airline_manage.htm'"));

			// setRedirect(SiteCAM.class);

		}
		return true;
	}

	public boolean onCancelClick() {
		setRedirect(AirlineManage.class);
		return true;
	}

}
