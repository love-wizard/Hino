package com.hino.page.internal;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ControlRegistry;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Button;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.Element;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

import com.hino.dto.AirlineBookingDto;
import com.hino.dto.AirlineCustomerBookingDto;
import com.hino.dto.BookingReserveDto;
import com.hino.dto.CustomerBookingDto;
import com.hino.dto.ReserveBookingDto;
import com.hino.dto.SalesBookingDto1;
import com.hino.model.Airline;
import com.hino.model.AirlineCompany;
import com.hino.model.Airport;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.model.Staff;
import com.hino.model.Voucher;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class AirlineInternalBooking extends BasePage {
	
	public Form airlineForm = new Form("airlineForm");
	private Select airlineSelect = new Select("airlineSelect", "航线选择");
	private Select airportSelect = new Select("airportSelect", "出发机场");
	private DateField depart_time = new DateField("depart_time", "出发日期&时间（当地）", 25, true);
	private TextField price = new TextField("price", "价格", 10, true);
	private TextArea note = new TextArea("note", "备注", 50, 10);
	Select AirlineCompnay = new Select("AirlineCompnay", "航空公司", true);
	private Select select_payer = new Select("select_payer", "付款人");
    private Select select_payment_method = new Select("select_payment_method", "支付方式");
	
	@Bindable
	public Button internal_airline_reserve = new Button("internal_airline_reserve", "d");
	
	@Bindable
	public String data;
	
	@Bindable
	public Button load_customer = new Button("load_customer", "load_customer");
	
	public void onInit()
	{
		//airlineSelect.setDefaultOption(new Option("请选择预定航线"));

        DataProvider<Option> airlineDataPrvd = new DataProvider<Option>() {
			private static final long serialVersionUID = -8107625833664571011L;

			public List<Option> getData() {
				List<Airline> allAirline = getAirlineService().getAirlineList();
				List<Option> Options = new ArrayList<Option>();
				for(Airline curAirline : allAirline) {
					String display = curAirline.getDeparture().getAirport_name() + "-" + curAirline.getArrival().getAirport_name();
					Options.add(new Option(curAirline.getId(), EscapeHtml.htmlDecode(display)));
				}
                return Options;
            }
        };
        airlineSelect.setDataProvider(airlineDataPrvd);
        
        //airportSelect.setDefaultOption(new Option("请选择出发机场"));
        DataProvider<Option> airportDataPrvd = new DataProvider<Option>() {
			private static final long serialVersionUID = -8107625833664571011L;

			public List<Option> getData() {
				List<Airport> allAirprot = getAirlineService().getAirportList();
				List<Option> Options = new ArrayList<Option>();
				for(Airport curAirport : allAirprot) {
					Options.add(new Option(curAirport.getId(), EscapeHtml.htmlDecode(curAirport.getAirport_name())));
				}
                return Options;
            }
        };
        airportSelect.setDataProvider(airportDataPrvd);
        depart_time.setFormatPattern("yyyy-MM-dd");
        
        AirlineCompnay.setDefaultOption(new Option("请选择航空公司"));
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
        AirlineCompnay.setDataProvider(routeDataPrvd4);
        

        select_payer.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				
				optionList.add(new Option(0, Info.BKP_STR_CN[0]));
				optionList.add(new Option(1, Info.BKP_STR_CN[1]));
				
				return optionList;
			}
		});
        
//        select_payer.setValue("0");
        
        select_payment_method.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				
				optionList.add(new Option(0, Info.ABKM_STR_CN[0]));
				optionList.add(new Option(1, Info.ABKM_STR_CN[1]));
				optionList.add(new Option(2, Info.ABKM_STR_CN[2]));
				optionList.add(new Option(3, Info.ABKM_STR_CN[3]));
				optionList.add(new Option(4, Info.ABKM_STR_CN[4]));
				optionList.add(new Option(5, Info.ABKM_STR_CN[5]));
				return optionList;
			}
		});
//        select_payment_method.setValue("0");
        
        airlineForm.add(airlineSelect);
        airlineForm.add(AirlineCompnay);
        airlineForm.add(airportSelect);
        airlineForm.add(depart_time);
        airlineForm.add(price);
        airlineForm.add(select_payer);
        airlineForm.add(select_payment_method);
        
        
		load_customer.setId("load_customer");
		addControl(load_customer);
		//load_customer.
		load_customer.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String email = getContext().getRequest().getParameter("email");
            	
            	ServiceResponse sr = getCustomerService().getCustomerByEmail(email);
            	String jsondata = "";
            	if(sr.isSucc())
            	{
            		
            		Customer c = (Customer)sr.getResponse();
            		jsondata = "{" + "\"fn\":\"" +c.getFirstname() + "\", \"ln\":\""+c.getLastname()+"\", \"cn\":\""+c.getChinesename() + "\", \"gender\":\"" + c.getGender() + "\", \"pt\":\"" +c.getPoint() + "\", \"isvip\":\"" +c.genIsVip() + "\", \"phone\":\"" + c.getPhone() +"\"}";
            		
            	} else
            	{
            		jsondata = "{"+"\"result\":\"0\"}";
            	}
                return new ActionResult(jsondata, ActionResult.JSON);
            }
        });
		
		internal_airline_reserve.setId("internal_airline_reserve");
		internal_airline_reserve.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String jsondata = (getContext().getRequest().getParameter("jsondata")).replace("\\", "").replace(" ", "");
            	jsondata = (String) jsondata.substring(1, jsondata.length()-1);
            	
            	ObjectMapper mapper = new ObjectMapper();
            	List<AirlineCustomerBookingDto> result = null;
            	try {
            		result = mapper.readValue(jsondata, TypeFactory.collectionType(ArrayList.class, AirlineCustomerBookingDto.class));
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
            	
            	long airline_id =  Long.parseLong(getContext().getRequest().getParameter("airline"));
            	String airline_company =  getContext().getRequest().getParameter("airline_company");
            	long airport_id =  Long.parseLong(getContext().getRequest().getParameter("airport"));
            	double price = Double.parseDouble(getContext().getRequest().getParameter("price"));
            	int payer_type_id = Integer.parseInt(getContext().getRequest().getParameter("payer_type"));
            	int payment_method_id = Integer.parseInt(getContext().getRequest().getParameter("payment_method"));
            	
            	String depart_time =  getContext().getRequest().getParameter("depart_time");
            	Calendar depart = Calendar.getInstance();
            	depart.setTime(java.sql.Date.valueOf(depart_time));
				
				Staff s = (Staff)getContext().getSession().getAttribute("staff");
				if (s==null)
				{
					return new ActionResult("No Action Right！", ActionResult.HTML);
				}
				
				List<AirlineBookingDto> abList = new ArrayList<AirlineBookingDto>();
				
				for(int i=0;i<result.size();i++)
				{
					AirlineBookingDto ab = new AirlineBookingDto(); 
					

					ab.setId(0);
					
					ab.setAirline_id(airline_id);
					ab.setAirport_id(airport_id);
					ab.setFlight_date(depart);
					//ab.setFlight_number(Airline.getValue());
					ab.setPrice(price);
//					ab.setCustomer(result.get(i).getCustomer());
//					ab.setFinancial_audit_date(result.get(i).getFinancial_audit_date());
//					ab.setFinancial_audit_feedback(result.get(i).getFinancial_audit_feedback());
//					ab.setFinancial_audit_id(result.get(i).getFinancial_audit_id());
					//ab.setFinanical_audit_rep(result.get(i).getFinanical_audit_rep());
					ab.setFirst_name(result.get(i).getFn());
					ab.setLast_name(result.get(i).getLn());
					ab.setChinese_name(result.get(i).getCn());

					//ab.setRep(result.get(i).getRep());
					ab.setRep_id(s.getId());
					
					ab.setStatus(Info.BKS_RESERVING);
					ab.setemail(result.get(i).getEmail());
					ab.setPhone(result.get(i).getPhone());
//					ab.setTransfer_apply_comments(result.get(i).getTransfer_apply_comments());
//					ab.setTransfer_apply_date(result.get(i).getTransfer_apply_date());
//					ab.setTransfer_audit_comments(result.get(i).getTransfer_apply_comments());
//					ab.setTransfer_audit_date(result.get(i).getTransfer_audit_date());
//					ab.setTransfer_audit_id(result.get(i).getTransfer_audit_id());
					//ab.setTransfer_audit_rep(result.get(i).getTransfer_audit_rep());
					ab.setPayer_type_id(payer_type_id);
					ab.setPayment_method_id(payment_method_id);
					
					
					
					abList.add(i,ab);
					
				}
				
				ServiceResponse sr = getAirlineService().internal_reserve_booking(abList, s);
				if(sr.isSucc())
				{
					jsondata = "Process successfully！";
				} else
				{
					jsondata = EscapeHtml.nrTonnrr(sr.list_msg());
				}

				return new ActionResult(jsondata, ActionResult.HTML);
            }
        });
		
		
	}
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new JsScript("/internal/js/airline_internal_booking.js", new HashMap()));
        }
        return headElements;
    }
}
