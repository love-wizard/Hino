package com.hino.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hino.dao.AirlineBookingDAO;
import com.hino.dao.AirlineCatalogDAO;
import com.hino.dao.AirlineCompanyDAO;
import com.hino.dao.AirlineDAO;
import com.hino.dao.AirlineQADAO;
import com.hino.dao.AirlineTipsDAO;
import com.hino.dao.AirportDAO;
import com.hino.dao.CustomerDAO;
import com.hino.dto.AirlineBookingDto;
import com.hino.dto.AirlineCatalogDto;
import com.hino.dto.AirlineCompanyDto;
import com.hino.dto.AirlineDto;
import com.hino.dto.AirlineTipsDto;
import com.hino.dto.AirportDto;
import com.hino.dto.BookingReserveDto;
import com.hino.model.Airline;
import com.hino.model.AirlineBooking;
import com.hino.model.AirlineCatalog;
import com.hino.model.AirlineCompany;
import com.hino.model.AirlineQA;
import com.hino.model.AirlineTips;
import com.hino.model.Airport;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.Voucher;
import com.hino.service.AirlineService;
import com.hino.util.HinoMailUtil;
import com.hino.util.Info;
import com.hino.util.PriviledgeParser;
import com.hino.util.RandomGenerator;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class AirlineServiceImpl implements AirlineService {
	private AirportDAO airportDao;
	private AirlineCatalogDAO airlineCatalogDao;
	private AirlineTipsDAO airlineTipsDao;
	private AirlineDAO airlineDao;
	private AirlineBookingDAO airlineBookingDao;
	private CustomerDAO customerDao;
	private AirlineQADAO airlineQADao;
	private AirlineCompanyDAO airlineCompanyDao;
	
	public AirlineQADAO getAirlineQADao() {
		return airlineQADao;
	}

	public void setAirlineQADao(AirlineQADAO airlineQADao) {
		this.airlineQADao = airlineQADao;
	}
	
	public AirlineCompanyDAO getAirlineCompanyDao() {
		return airlineCompanyDao;
	}

	public void setAirlineCompanyDao(AirlineCompanyDAO airlineCompanyDao) {
		this.airlineCompanyDao = airlineCompanyDao;
	}

	public AirportDAO getAirportDao() {
		return airportDao;
	}

	public void setAirportDao(AirportDAO airportDao) {
		this.airportDao = airportDao;
	}
	
	public AirlineCatalogDAO getAirlineCatalogDao() {
		return airlineCatalogDao;
	}

	public void setAirlineCatalogDao(AirlineCatalogDAO airlineCatalogDao) {
		this.airlineCatalogDao = airlineCatalogDao;
	}

	public AirlineTipsDAO getAirlineTipsDao() {
		return airlineTipsDao;
	}

	public void setAirlineTipsDao(AirlineTipsDAO airlineTipsDao) {
		this.airlineTipsDao = airlineTipsDao;
	}

	public AirlineDAO getAirlineDao() {
		return airlineDao;
	}

	public void setAirlineDao(AirlineDAO airlineDao) {
		this.airlineDao = airlineDao;
	}

	public AirlineBookingDAO getAirlineBookingDao() {
		return airlineBookingDao;
	}

	public void setAirlineBookingDao(AirlineBookingDAO airlineBookingDao) {
		this.airlineBookingDao = airlineBookingDao;
	}


	public CustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public ServiceResponse addAirport(AirportDto airportDto) {
		Airport airport = new Airport();
		airport.setAirport_name(airportDto.getAirport_name());
		airport.setAirport_city(airportDto.getAirport_city());
		airportDao.save(airport);
		
		return null;
	}

	@Override
	public ServiceResponse updateAirport(AirportDto airportDto) {
		Airport airport = new Airport();
		airport.setId(airportDto.getId());
		airport.setAirport_name(airportDto.getAirport_name());
		airport.setAirport_city(airportDto.getAirport_city());
		airportDao.update(airport);
		return null;
	}

	@Override
	public ServiceResponse deleteAirport(long id) {
		Airport airport;
		ServiceResponse sr = new ServiceResponse();
		
		airport = airportDao.getAirportById(id);
		if(airport==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target group was no longer avaliable!");
			return sr;
		}
		
		airportDao.delete(id);
		return sr;
		
	}

	@Override
	public Airport getAirport(long id) {
		Airport airport ;
		airport = airportDao.getAirportById(id);
		return(airport);
	}

	@Override
	public List<Airport> getAirportList() {
		List<Airport> al;
		al = airportDao.getAllAirport();
		return al;
	}

	@Override
	public ServiceResponse addAirlineCatalog(
			AirlineCatalogDto airlineCatalogtDto) {
		AirlineCatalog ac = new AirlineCatalog();
		ac.setCatalog_name(airlineCatalogtDto.getCatalog_name());
		ac.setCatalog_type(airlineCatalogtDto.getCatalog_type());
		ac.setCatalog_default(airlineCatalogtDto.isCatalog_default());
		airlineCatalogDao.save(ac);
		
		return null;
	}

	@Override
	public ServiceResponse updateAirlineCatalog(
			AirlineCatalogDto airlineCatalogtDto) {
		AirlineCatalog ac = new AirlineCatalog();
		ac.setCatalog_name(airlineCatalogtDto.getCatalog_name());
		ac.setCatalog_type(airlineCatalogtDto.getCatalog_type());
		ac.setCatalog_default(airlineCatalogtDto.isCatalog_default());
		ac.setId(airlineCatalogtDto.getId());
		airlineCatalogDao.update(ac);
		
		return null;
	}

	@Override
	public ServiceResponse deleteAirlineCatalog(long id) {
		airlineCatalogDao.delete(id);
		return null;
	}

	@Override
	public AirlineCatalog getAirlineCatalog(long id) {
		return airlineCatalogDao.getAirlineCatalogById(id);
	}

	@Override
	public List<AirlineCatalog> getAirlineCatalogList() {
		return airlineCatalogDao.getAllAirlineCatalog();
	}

	@Override
	public ServiceResponse addAirlineTips(AirlineTipsDto airlineTipsDto) {
		AirlineTips at = new AirlineTips();
		at.setCatalog_id(airlineTipsDto.getCatalog_id());
		at.setTips_query(airlineTipsDto.getTips_query());
		at.setTips_answer(airlineTipsDto.getTips_answer());
		
		airlineTipsDao.save(at);
		
		return null;
	}

	@Override
	public ServiceResponse updateAirlineTips(AirlineTipsDto airlineTipsDto) {
		AirlineTips at = new AirlineTips();
		at.setCatalog_id(airlineTipsDto.getCatalog_id());
		at.setTips_query(airlineTipsDto.getTips_query());
		at.setTips_answer(airlineTipsDto.getTips_answer());
		at.setId(airlineTipsDto.getId());
		
		airlineTipsDao.update(at);
		return null;
	}

	@Override
	public ServiceResponse deleteAirlineTips(long id) {
		airlineTipsDao.delete(id);
		return null;
	}

	@Override
	public AirlineTips getAirlineTips(long id) {
		return airlineTipsDao.getAirlineTipsById(id);
	}

	@Override
	public List<AirlineTips> getAirlineTipsList(long tid) {
		return airlineTipsDao.getAirlineTips(tid);
	}

	@Override
	public ServiceResponse addAirline(AirlineDto airlineDto) {
		Airline airline = new Airline();
		
		Airport depature = airportDao.getAirportById(airlineDto.getDeparture().getId());
		airline.setDeparture(depature);
		
		Airport arrival = airportDao.getAirportById(airlineDto.getArrival().getId());
		airline.setArrival(arrival);
		
		airline.setSingle_trip_price(airlineDto.getSingle_trip_price());
		airline.setRound_trip_price(airlineDto.getRound_trip_price());
		airline.setDeparture_begin_date(airlineDto.getDeparture_begin_date());
		airline.setDeparture_end_date(airlineDto.getDeparture_end_date());
		airline.setReturn_begin_date(airlineDto.getReturn_begin_date());
		airline.setReturn_end_date(airlineDto.getReturn_end_date());
		airline.setFlight_number(airlineDto.getFlight_number());
		airline.setAirline(airlineDto.getAirline());
		airline.setLuggage_allowance(airlineDto.getLuggage_allowance());
		airline.setCatalog_tag(airlineDto.getCatalog_tag());
		airline.setDefault_catalog_id(airlineDto.getDefault_catalog_id());
		airline.setSale_begin_date(airlineDto.getSale_begin_date());
		airline.setSale_end_date(airlineDto.getSale_end_date());
		airline.setType(airlineDto.getType());
		airline.setPriceComment(airlineDto.getPriceComment());
		airline.setFlightConnections(airlineDto.getFlightConnections());
		airline.setSale_begin_date(airlineDto.getSale_begin_date());
		airline.setSale_end_date(airlineDto.getSale_end_date());
		airline.setReturnPeriod(airlineDto.getReturnPeriod());
		airline.setDiscountedPrice(airlineDto.getDiscountedPrice());
		airline.setDiscountEndTime(airlineDto.getDiscountEndTime());
		airline.setRecommended(airlineDto.isRecommended());
		airline.setPublish_date(airlineDto.getPublish_date());
		
		airlineDao.save(airline);
		
		return null;
	}

	
	@Override
	public ServiceResponse updateAirline(AirlineDto airlineDto) {
		Airline airline = new Airline();
		
		airline.setId(airlineDto.getId());
		
		Airport depature = airportDao.getAirportById(airlineDto.getDeparture().getId());
		airline.setDeparture(depature);
		
		Airport arrival = airportDao.getAirportById(airlineDto.getArrival().getId());
		airline.setArrival(arrival);		
		
		airline.setSingle_trip_price(airlineDto.getSingle_trip_price());
		airline.setRound_trip_price(airlineDto.getRound_trip_price());
		airline.setDeparture_begin_date(airlineDto.getDeparture_begin_date());
		airline.setDeparture_end_date(airlineDto.getDeparture_end_date());
		airline.setReturn_begin_date(airlineDto.getReturn_begin_date());
		airline.setReturn_end_date(airlineDto.getReturn_end_date());
		airline.setFlight_number(airlineDto.getFlight_number());
		airline.setAirline(airlineDto.getAirline());
		airline.setLuggage_allowance(airlineDto.getLuggage_allowance());
		airline.setCatalog_tag(airlineDto.getCatalog_tag());
		airline.setDefault_catalog_id(airlineDto.getDefault_catalog_id());
		airline.setSale_begin_date(airlineDto.getSale_begin_date());
		airline.setSale_end_date(airlineDto.getSale_end_date());
		airline.setType(airlineDto.getType());
		airline.setPriceComment(airlineDto.getPriceComment());
		airline.setFlightConnections(airlineDto.getFlightConnections());
		airline.setSale_begin_date(airlineDto.getSale_begin_date());
		airline.setSale_end_date(airlineDto.getSale_end_date());
		airline.setReturnPeriod(airlineDto.getReturnPeriod());
		airline.setDiscountedPrice(airlineDto.getDiscountedPrice());
		airline.setDiscountEndTime(airlineDto.getDiscountEndTime());
		airline.setMax_return_limit_type(airlineDto.getMax_return_limit_type());
		airline.setRecommended(airlineDto.isRecommended());
		airline.setPublish_date(airlineDto.getPublish_date());
		
		airlineDao.update(airline);
		return null;
	}

	@Override
	public ServiceResponse deleteAirline(long id) {
		airlineDao.delete(id);
		return null;
	}

	@Override
	public Airline getAirline(long id) {
		return airlineDao.getAirlineById(id);
	}

	@Override
	public List<Airline> getAirlineList() {
		return airlineDao.getAllAirline();
	}

	@Override
	public List<Airline> getAirlineList(long did, long aid, Calendar departureDate, Calendar returnDate, boolean isRoundTrip) {
		return airlineDao.getAllAirline();
	}
	
	@Override
	public List<Airline> getAirlinesByCat(long catId) {
		return airlineDao.getAirlinesByCat(catId);
	}
	
	@Override
	public List<Airline> getAirlinesByCriteria(long type, long departAirportyId,
			long landAirportId, long noofPeople, String departDate,
			String returnDate) {
		return airlineDao.getAirlinesByCriteria(type, departAirportyId, landAirportId, noofPeople, departDate, returnDate);
	}

	@Override
	public int getAirportCount() {
		return airportDao.getAllAirportCount();
	}

	@Override
	public int getAirlineCatalogCount() {
		return airlineCatalogDao.getAllAirlineCatalogCount();
	}

	@Override
	public int getAirlineCount() {
		return airlineDao.getAllAirlineCount();
	}

	@Override
	public ServiceResponse addAirlineBooking(AirlineBookingDto abDto, Staff s) {
		AirlineBooking  ab = new AirlineBooking();
		
		ab.setAirline_id(abDto.getAirline_id());
		ab.setAirport_id(abDto.getAirport_id());
		ab.setAirline_company_id(abDto.getAirline_company_id());
		ab.setCustomer(abDto.getCustomer());
		ab.setFinancial_audit_date(abDto.getFinancial_audit_date());
		ab.setFinancial_audit_feedback(abDto.getFinancial_audit_feedback());
//		ab.setFinancial_audit_id(abDto.getFinancial_audit_id());
		//ab.setFinanical_audit_rep(abDto.getFinanical_audit_rep());
		ab.setFirst_name(abDto.getFirst_name());
		ab.setFlight_date(abDto.getFlight_date());
		ab.setFlight_number(abDto.getFlight_number());
		ab.setLast_name(abDto.getLast_name());
		ab.setChinese_name(abDto.getChinese_name());
		ab.setPrice(abDto.getPrice());
		//ab.setRep(abDto.getRep());
		ab.setRep(s);
		ab.setStatus(abDto.getStatus());
		ab.setEmail(abDto.getemail());
		ab.setPhone(abDto.getPhone());
		ab.setTransfer_apply_comments(abDto.getTransfer_apply_comments());
		ab.setTransfer_apply_date(abDto.getTransfer_apply_date());
		ab.setTransfer_audit_comments(abDto.getTransfer_apply_comments());
		ab.setTransfer_audit_date(abDto.getTransfer_audit_date());
//		ab.setTransfer_audit_id(abDto.getTransfer_audit_id());
		//ab.setTransfer_audit_rep(abDto.getTransfer_audit_rep());
		
		ab.setPayer_type_id(abDto.getPayer_type_id());
		ab.setPayment_method_id(abDto.getPayment_method_id());
		
		airlineBookingDao.save(ab);
		return null;
	}

	@Override
	public ServiceResponse updateAirlineBooking(AirlineBookingDto abDto, Staff s) {
		AirlineBooking  ab = new AirlineBooking();
		
		ab.setId(abDto.getId());
		ab.setAirline_id(abDto.getAirline_id());
		ab.setAirline_company_id(abDto.getAirline_company_id());
		ab.setAirport_id(abDto.getAirport_id());
		ab.setCustomer(abDto.getCustomer());
		ab.setFinancial_audit_date(abDto.getFinancial_audit_date());
		ab.setFinancial_audit_feedback(abDto.getFinancial_audit_feedback());
//		ab.setFinancial_audit_id(abDto.getFinancial_audit_id());
		//ab.setFinanical_audit_rep(abDto.getFinanical_audit_rep());
		ab.setFirst_name(abDto.getFirst_name());
		ab.setFlight_date(abDto.getFlight_date());
		ab.setFlight_number(abDto.getFlight_number());
		ab.setLast_name(abDto.getLast_name());
		ab.setChinese_name(abDto.getChinese_name());
		ab.setPrice(abDto.getPrice());
		//ab.setRep(abDto.getRep());
		ab.setRep(s);
		ab.setStatus(abDto.getStatus());
		ab.setEmail(abDto.getemail());
		ab.setPhone(abDto.getPhone());
		ab.setTransfer_apply_comments(abDto.getTransfer_apply_comments());
		ab.setTransfer_apply_date(abDto.getTransfer_apply_date());
		ab.setTransfer_audit_comments(abDto.getTransfer_apply_comments());
		ab.setTransfer_audit_date(abDto.getTransfer_audit_date());
//		ab.setTransfer_audit_id(abDto.getTransfer_audit_id());
		//ab.setTransfer_audit_rep(abDto.getTransfer_audit_rep());
		
		ab.setPayer_type_id(abDto.getPayer_type_id());
		ab.setPayment_method_id(abDto.getPayment_method_id());
		
		airlineBookingDao.update(ab);
		return null;
	}

	@Override
	public ServiceResponse deleteAirlineBooking(long id) {
		airlineBookingDao.delete(id);
		return null;
	}

	@Override
	public AirlineBooking getAirlineBooking(long id) {
		return airlineBookingDao.getAirlineBookingById(id);
	}

	@Override
	public List<AirlineBooking> getAirlineListBooking() {
		return airlineBookingDao.getAllAirlineBooking();
	}

	@Override
	public int getAirlineBookingCount() {
		return airlineBookingDao.getAllAirlineBookingCount();
	}

	@Override
	public List<AirlineBooking> getAirlineLisBooking(long did, long aid,
			Calendar departureDate, Calendar returnDate, boolean isRoundTrip,
			long status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse internal_reserve_booking(
			List<AirlineBookingDto> bookingList, Staff s) {
		ServiceResponse sr = new ServiceResponse();
		
		if(!PriviledgeParser.has_priviledge(s.getPriviledge(),PriviledgeParser.SALES_REP))
		{
			sr.setSucc(false);
			sr.addMsg("Access denied!");
			return sr;
		}
		

		if(bookingList==null)
		{
			sr.setSucc(false);
			sr.addMsg("No booking info.");
			return sr;
		}
		
		if(bookingList.size()==0)
		{
			sr.setSucc(false);
			sr.addMsg("Invalid booking size.");
			return sr;
		}
		
		
		AirlineBookingDto abDto;
		
		for(int i=0;i<bookingList.size();i++)
		{
			abDto = bookingList.get(i);
			String curRef = randomnBookingRef();
//			Customer c = this.customerDao.findCustomerByEmail(abDto.getemail());
			AirlineBooking  ab = new AirlineBooking();
			Airline airline = this.airlineDao.getAirlineById(abDto.getId());

			ab.setId(abDto.getId());
			ab.setBookingRef(curRef);
			ab.setAirline_id(abDto.getAirline_id());
			ab.setAirline_company_id(abDto.getAirline_id());
			ab.setAirport_id(abDto.getAirport_id());
			ab.setCustomer(abDto.getCustomer());
			ab.setFinancial_audit_date(abDto.getFinancial_audit_date());
			ab.setFinancial_audit_feedback(abDto.getFinancial_audit_feedback());
//			ab.setFinancial_audit_id(abDto.getFinancial_audit_id());
			ab.setFinanical_audit_rep(s);
			ab.setFirst_name(abDto.getFirst_name());
			ab.setFlight_date(abDto.getFlight_date());
			ab.setFlight_number(abDto.getFlight_number());
			ab.setLast_name(abDto.getLast_name());
			ab.setChinese_name(abDto.getChinese_name());
			ab.setPrice(abDto.getPrice());
			//ab.setRep(abDto.getRep());
			ab.setRep(s);
			ab.setStatus(abDto.getStatus());
			ab.setEmail(abDto.getemail());
			ab.setPhone(abDto.getPhone());
			ab.setTransfer_apply_comments(abDto.getTransfer_apply_comments());
			ab.setTransfer_apply_date(abDto.getTransfer_apply_date());
			ab.setTransfer_audit_comments(abDto.getTransfer_apply_comments());
			ab.setTransfer_audit_date(abDto.getTransfer_audit_date());
			ab.setTransfer_audit_rep(s);
			ab.setPayer_type_id(abDto.getPayer_type_id());
			ab.setPayment_method_id(abDto.getPayment_method_id());
			
			this.airlineBookingDao.save(ab);
			
		}
		
			
		sr.setSucc(true);
		return sr;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse modify_booking_price(long bid, double newpd) {
		ServiceResponse sr = new ServiceResponse();
		AirlineBooking b = airlineBookingDao.getAirlineBookingById(bid);
		if (b!=null&&b.getStatus()==Info.BKS_RESERVING)
		{
			b.setPrice(newpd);
			sr.setSucc(true);
		}else
		{
			sr.addMsg("unable to change pd credit for this booking check status!");
		}
		return sr;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse delete_booking(long bid) {
		ServiceResponse sr = new ServiceResponse();
		
		AirlineBooking b = airlineBookingDao.getAirlineBookingById(bid);
		
		if(b==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target booking was no longer avaliable!");
			return sr;
		}
		
		if(b.getStatus()==Info.BKS_RESERVING || b.getStatus() == Info.BKS_TRANSFERING)
		{

			airlineBookingDao.delete(bid);
			sr.setSucc(true);
			return sr;
		} else
		{
			sr.setSucc(false);
			sr.addMsg("Only reserving booking could be deleted!");
			return sr;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse confirm_booking(long bid) {
		ServiceResponse sr = new ServiceResponse();
				
		AirlineBooking b = airlineBookingDao.getAirlineBookingById(bid);
		if(b==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target booking was no longer avaliable!");
			return sr;
		}
		
		if(b.getStatus()!=Info.BKS_RESERVING&&b.getStatus()!=Info.BKS_TRANSFERING)
		{
			sr.setSucc(false);
			sr.addMsg("Only reserving or transfering airline booking could be confirmed");
			return sr;
		}
		
		b.setStatus(Info.BKS_CONFIRMED);
		sr.setResponse(b);
		sr.setSucc(true);
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS ,readOnly=true, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse view_booking(long id, Staff s) {
		ServiceResponse sr = new ServiceResponse();
		AirlineBooking b = null;
		if(s==null)
		{
			sr.addMsg("Access denied!");
			sr.setSucc(false);
			return sr;
		}
		
		if(PriviledgeParser.has_priviledge(s.getPriviledge(), PriviledgeParser.SALES_MANAGE))
		{
			b = airlineBookingDao.getAirlineBookingById(id);
			if(b!=null)
			{
				sr.setSucc(true);
				sr.setResponse(b);
				return sr;
			} else
			{
				sr.addMsg("Access denied!");
				sr.setSucc(false);
				return sr;
			}
		} else if(PriviledgeParser.has_priviledge(s.getPriviledge(), PriviledgeParser.SALES_REP))
		{
			b = airlineBookingDao.getAirlineBookingById(id);
			if(b!=null)
			{
				Staff rep = b.getRep();
				if(rep!=null)
				{
					if(rep.getId()==s.getId())
					{
						sr.setSucc(true);
						sr.setResponse(b);
						return sr;
					}
				}
			} 
		} else
		{
			sr.addMsg("Access denied!");
			sr.setSucc(false);
		}
		sr.addMsg("Access denied!");
		return sr;
	}

	@Override
	public List<AirlineBooking> bookingSearch(int attrType, String keyword, int sid, int payer_type_id, int payment_method_id,  
			int[] status, int size, int page) {
		if(keyword.equals(""))
		{
			return airlineBookingDao.complexSearching(-1, keyword, sid, payer_type_id, payment_method_id, status, size, page, null, null);
		} else
		{
			if (attrType==1)
			{
				try{
					Integer.parseInt(keyword);
				} catch (NumberFormatException nfe)
				{
					
				} 
				return new ArrayList<AirlineBooking>();
			}
			return airlineBookingDao.complexSearching(attrType, keyword, sid,  payer_type_id, payment_method_id, status, size, page, null, null);
		}
	}

	@Override
	public int bookingSearchCount(int attrType, String keyword, int sid, int payer_type_id, int payment_method_id, int[] status) {
		if(keyword.equals(""))
		{
			return (int)airlineBookingDao.complexSearchingCount(-1, keyword, sid, payer_type_id, payment_method_id, status);
		} else
		{
			if (attrType==1)
			{
				try{
					Integer.parseInt(keyword);
				} catch (NumberFormatException nfe)
				{
					
				} 
				return new Integer(0);
			}
			return (int)airlineBookingDao.complexSearchingCount(attrType, keyword, sid,  payer_type_id, payment_method_id, status);
			
		}
	}
	
	@Override
	public List<AirlineQA> getAirlineQAsByArea(String area) {
		return airlineQADao.getAirlineQAsByArea(area);
	}
	
	private String randomnBookingRef()
	{
		String str = TimeFormater.format3(Calendar.getInstance());
		return str+RandomGenerator.randomnNumberString(4);
	}

	@Override
	public List<AirlineQA> getAllAirlineQAs() {
		return airlineQADao.getAllAirlineQAs();
	}

	@Override
	public ServiceResponse addAirlineCompany(AirlineCompanyDto airlineCompanyDto) {
		AirlineCompany company = new AirlineCompany();
		company.setCompany_full_name(airlineCompanyDto.getCompany_full_name());
		company.setCompany_display_name(airlineCompanyDto.getCompany_display_name());
		airlineCompanyDao.save(company);
		
		return null;
	}

	@Override
	public ServiceResponse updateAirlineCompany(
			AirlineCompanyDto airlineCompanyDto) {
		AirlineCompany company = new AirlineCompany();
		company.setId(airlineCompanyDto.getId());
		company.setCompany_full_name(airlineCompanyDto.getCompany_full_name());
		company.setCompany_display_name(airlineCompanyDto.getCompany_display_name());
		airlineCompanyDao.update(company);
		
		return null;
	}

	@Override
	public ServiceResponse deleteAirlineCompany(long id) {
		AirlineCompany company;
		ServiceResponse sr = new ServiceResponse();
		
		company = airlineCompanyDao.getAirlineCompanyById(id);
		if(company==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target group was no longer avaliable!");
			return sr;
		}
		
		airlineCompanyDao.delete(id);
		return sr;
	}

	@Override
	public AirlineCompany getAirlineCompany(long id) {
		AirlineCompany company ;
		company = airlineCompanyDao.getAirlineCompanyById(id);
		return(company);
	}

	@Override
	public List<AirlineCompany> getAirlineCompanyList() {
		List<AirlineCompany> al;
		al = airlineCompanyDao.getAllAirlineCompany();
		return al;
	}

	@Override
	public int getAirlineCompanyCount() {
		return airlineCompanyDao.getAllAirlineCompanyCount();
	}

	@Override
	public List<Airport> getAirportsByName(String airportName) {
		return airportDao.getAirportsByName(airportName);
	}

	@Override
	public void deletePastAirline(String departureEndDate) {
		airlineDao.deletePastAirline(departureEndDate);
		
	}

	
}
