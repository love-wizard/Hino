package com.hino.page.zh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
import org.apache.click.control.Label;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Div;
import com.hino.click.extension.Image;
import com.hino.click.extension.Link;
import com.hino.dto.AirlinesAndCatalogDto;
import com.hino.model.AirlineCatalog;
import com.hino.model.AirlineCompany;
import com.hino.model.AirlineQA;
import com.hino.model.Airport;
import com.hino.model.QQService;
import com.hino.model.WebMenu;
import com.hino.model.WebMenuRoute;
import com.hino.util.Info;
import com.hino.util.NavigationUtil;

public class Airline extends BasePageOther{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9008172599989526194L;
	@Bindable private Button ticket_enquiry = new Button("ticket_enquiry", "ticket_enquiry");
	@Bindable private Button ajax_airports = new Button("ajax_airports", "ajax_airports");
	
	@Override
	public void onInit() {
		
		/* QQ 客服  */
		qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);
		
		
		/* + 问题及答案区块  */
		List<AirlineQA> bookingProcedureQAs = new ArrayList<AirlineQA>();
		List<AirlineQA> payMethodQAs = new ArrayList<AirlineQA>();
		List<AirlineQA> electricTicketQAs = new ArrayList<AirlineQA>();
		List<AirlineQA> updateTicketQAs = new ArrayList<AirlineQA>();
		List<AirlineQA> flightInfoConfirmQAs = new ArrayList<AirlineQA>();
		List<AirlineQA> networkCheckinQAs = new ArrayList<AirlineQA>();
		List<AirlineQA> baggageQuestionQAs = new ArrayList<AirlineQA>();
		List<AirlineQA> specialTicketQAs = new ArrayList<AirlineQA>();
		List<AirlineQA> otherQuestionQAs = new ArrayList<AirlineQA>();
		
		List<AirlineQA> airlineQAs = getAirlineService().getAllAirlineQAs();
		for (AirlineQA qa : airlineQAs) {
			if (Info.AIRLINE_AREA_BOOKING_PROCEDURE.equals(qa.getArea())) {
				bookingProcedureQAs.add(qa);
			} else if (Info.AIRLINE_AREA_PAY_METHOD.equals(qa.getArea())) {
				payMethodQAs.add(qa);
			} else if (Info.AIRLINE_AREA_ELECTRIC_TICKET.equals(qa.getArea())) {
				electricTicketQAs.add(qa);
			} else if (Info.AIRLINE_AREA_UPDATE_TICKET.equals(qa.getArea())) {
				updateTicketQAs.add(qa);
			} else if (Info.AIRLINE_AREA_FLIGHT_INFO_CONFIRM.equals(qa.getArea())) {
				flightInfoConfirmQAs.add(qa);
			} else if (Info.AIRLINE_AREA_NETWORK_CHECKIN.equals(qa.getArea())) {
				networkCheckinQAs.add(qa);
			} else if (Info.AIRLINE_AREA_BAGGAGE_QUESTION.equals(qa.getArea())) {
				baggageQuestionQAs.add(qa);
			} else if (Info.AIRLINE_AREA_SPECIAL_TICKET.equals(qa.getArea())) {
				specialTicketQAs.add(qa);
			} else if (Info.AIRLINE_AREA_OTHER_QUESTION.equals(qa.getArea())) {
				otherQuestionQAs.add(qa);
			}
		}
		
		addModel("bookingProcedureQAs", bookingProcedureQAs);
		addModel("payMethodQAs", payMethodQAs);
		addModel("electricTicketQAs", electricTicketQAs);
		addModel("updateTicketQAs", updateTicketQAs);
		addModel("flightInfoConfirmQAs", flightInfoConfirmQAs);
		addModel("networkCheckinQAs", networkCheckinQAs);
		addModel("baggageQuestionQAs", baggageQuestionQAs);
		addModel("specialTicketQAs", specialTicketQAs);
		addModel("otherQuestionQAs", otherQuestionQAs);
		/* - 问题及答案区块  */
		
		/* + 航线列表区块 */
		List<AirlinesAndCatalogDto> airlinesCatList = new ArrayList<AirlinesAndCatalogDto>();
		
		// Get airline catalogs
		List<AirlineCatalog> airlineCatalogs = getAirlineService().getAirlineCatalogList();
		
		for (AirlineCatalog aircat: airlineCatalogs) {
			AirlinesAndCatalogDto airlinesCat = new AirlinesAndCatalogDto();
			airlinesCat.setAirlineCatalog(aircat);
			airlinesCat.setAirlines(getAirlineService().getAirlinesByCat(aircat.getId()));
			airlinesCatList.add(airlinesCat);
		}
		
		addModel("alsCatList", airlinesCatList);		
		/* - 航线列表区块 */
		
		/* + 航线城市列表 */
		List<Airport> airports = getAirlineService().getAirportList();
		addModel("airports", airports);
		/* - 航线城市列表 */
		
		List<AirlineCompany> airlineCompanies = getAirlineService().getAirlineCompanyList();
		addModel("airlineCompanies", airlineCompanies);
		
		ticket_enquiry.setId("ticket_enquiry");
		ticket_enquiry.addBehavior(new DefaultAjaxBehavior() {
			public ActionResult onAction(Control source) {
				long type = Long.valueOf(getContext().getRequest().getParameter("ty"));
				long departAirportId = Long.valueOf(getContext().getRequest().getParameter("dc"));
				long landAirportId = Long.valueOf(getContext().getRequest().getParameter("lc"));
				long noofPeople = Long.valueOf(getContext().getRequest().getParameter("np"));
				String departDate = getContext().getRequest().getParameter("dd");
				String returnDate = getContext().getRequest().getParameter("rd");				
				
				SimpleDateFormat df;
				
				StringBuffer jsondata = new StringBuffer();
				List<com.hino.model.Airline> airlines = getAirlineService().getAirlinesByCriteria(type, departAirportId, landAirportId, noofPeople, departDate, returnDate);
				
				int tn = airlines.size();
				jsondata.append("{\"tn\":\"").append(tn).append("\", \"tickets\": [");	
				
				if (tn == 1) {
					df = new SimpleDateFormat("yy年MM月dd日");
					Date dd = new Date();
					try {
						dd = (new SimpleDateFormat("yyyy-MM-dd")).parse(departDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					for (com.hino.model.Airline al : airlines) {
						long price = (al.getType() == 1? al.getSingle_trip_price(): al.getRound_trip_price());
						
						// 特价期内用特价价格
						if(al.getDiscountEndTime()!=null){
							if (al.getDiscountEndTime().getTime().compareTo(dd) >= 0) {
								price = al.getDiscountedPrice();
							}
						}
						jsondata.append("{");
						jsondata.append("\"d\":\"").append(df.format(al.getDeparture_begin_date().getTime())).append("\"");
						jsondata.append(", \"dc\":\"").append(al.getDeparture().getAirport_city()).append("\"");
						jsondata.append(", \"lc\":\"").append(al.getArrival().getAirport_city()).append("\"");
						jsondata.append(", \"p\":\"").append(price).append("\"");
						jsondata.append(", \"c\":\"").append(getAirlineService().getAirlineCompany(Long.parseLong(al.getAirline())).getCompany_full_name()).append("\"");
						jsondata.append(", \"m\":\"").append(al.getFlight_number()).append("\"");
						jsondata.append(", \"fc\":\"").append(al.getFlightConnections()).append("\"");
						jsondata.append("}");
						
						break;
					}
					
					
				} else if (tn > 1) {
					boolean isFirstTimeIn = true;
					df = new SimpleDateFormat("yyyy年MM月dd日");
					
					for (com.hino.model.Airline al : airlines) {
						
						if(! isFirstTimeIn) {
							jsondata.append(",");
						} else {
							isFirstTimeIn = false;
						}
						
						long price = (al.getType() == 1? al.getSingle_trip_price(): al.getRound_trip_price());
						Date dd = new Date();
						try {
							dd =  (new SimpleDateFormat("yyyy-MM-dd")).parse(departDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						// 特价期内用特价价格
						if(al.getDiscountEndTime()!=null){
							if (al.getDiscountEndTime().getTime().compareTo(dd) >= 0) {
								price = al.getDiscountedPrice();
							}
						}
						
						jsondata.append("{");
						jsondata.append("\"d\":\"").append(df.format(al.getDeparture_begin_date().getTime())).append("\"");
						jsondata.append(", \"t\":\"").append((al.getType() == 1?"单程":"往返")).append("\"");
						jsondata.append(", \"dc\":\"").append(al.getDeparture().getAirport_city()).append("\"");
						jsondata.append(", \"lc\":\"").append(al.getArrival().getAirport_city()).append("\"");
						jsondata.append(", \"p\":\"").append(price).append("\"");
						jsondata.append(", \"rm\":\"").append(("".equals(al.getPriceComment())? "": "(" + al.getPriceComment() + ")")).append("\"");
						jsondata.append("}");
					}
				} else {
					
				}
				
				jsondata.append("]}");
				System.out.println(jsondata.toString());
				return new ActionResult(jsondata.toString(), ActionResult.JSON);
			}});
		
		
		ajax_airports.setId("ajax_airports");
		ajax_airports.addBehavior(new DefaultAjaxBehavior() {
			public ActionResult onAction(Control source) {
				String airportName = getContext().getRequest().getParameter("an");
				
				StringBuffer jsondata = new StringBuffer();
				List<Airport> airports = getAirlineService().getAirportsByName(airportName);
				
				jsondata.append("{\"pan\":\"").append(airportName).append("\", \"airports\": [");	

				boolean isFirstTimeIn = true;
				
				for (Airport ap : airports) {
					
					if(! isFirstTimeIn) {
						jsondata.append(",");
					} else {
						isFirstTimeIn = false;
					}
					
					jsondata.append("{");
					jsondata.append("\"id\":\"").append(ap.getId()).append("\"");
					jsondata.append(", \"n\":\"").append(ap.genCityAirport()).append("\"");
					jsondata.append(", \"c\":\"").append(ap.getAirport_city()).append("\"");
					jsondata.append("}");
				}
				
				jsondata.append("]}");
				System.out.println(jsondata.toString());
				return new ActionResult(jsondata.toString(), ActionResult.JSON);
			}});
	}
	
	@Override
    public void onRender() {

    }
	
    @Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new JsImport("./js/jquery-1.8.3.js"));
            headElements.add(new JsImport("./js/jquery-ui-1.9.2.custom.js"));
            headElements.add(new JsImport("./js/jquery.ui.datepicker-zh-CN.js"));
            
            String dp = "$(function() {$(\"#datepicker\").datepicker();});";
            headElements.add(new JsScript(dp));
            headElements.add(new CssImport("./css/airline.css"));
        }
        return headElements;
    }
}
