package com.hino.service;

import java.util.Calendar;
import java.util.List;

import com.hino.dto.AddressDto;
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
import com.hino.model.Staff;
import com.hino.util.ServiceResponse;

public interface AirlineService {
	public ServiceResponse addAirport(AirportDto airportDto);
	public ServiceResponse updateAirport(AirportDto airportDto);
	public ServiceResponse deleteAirport(long id);
	public Airport getAirport(long id);
	public List<Airport> getAirportList();
	public int getAirportCount();
	
	public ServiceResponse addAirlineCatalog(AirlineCatalogDto airlineCatalogtDto);
	public ServiceResponse updateAirlineCatalog(AirlineCatalogDto airlineCatalogtDto);
	public ServiceResponse deleteAirlineCatalog(long id);
	public AirlineCatalog getAirlineCatalog(long id);
	public List<AirlineCatalog> getAirlineCatalogList();
	public int getAirlineCatalogCount();
	
	public ServiceResponse addAirlineTips(AirlineTipsDto airlineTipsDto);
	public ServiceResponse updateAirlineTips(AirlineTipsDto airlineTipsDto);
	public ServiceResponse deleteAirlineTips(long id);
	public AirlineTips getAirlineTips(long id);
	public List<AirlineTips> getAirlineTipsList(long tid);
	
	public ServiceResponse addAirline(AirlineDto airlineDto);
	public ServiceResponse updateAirline(AirlineDto airlineDto);
	public ServiceResponse deleteAirline(long id);
	public Airline getAirline(long id);
	public List<Airline> getAirlineList();
	public int getAirlineCount();
	public List<Airline> getAirlineList(long did, long aid, Calendar departureDate, Calendar returnDate ,boolean isRoundTrip);	
	public List<AirlineQA> getAllAirlineQAs();
	public List<AirlineQA> getAirlineQAsByArea(String area);
	public List<Airline> getAirlinesByCat(long catId);
	public List<Airline> getAirlinesByCriteria(long type, long departCity,
			long landCity, long noofPeople, String departDate,
			String returnDate);
	
	public ServiceResponse addAirlineBooking(AirlineBookingDto ab, Staff s);
	public ServiceResponse updateAirlineBooking(AirlineBookingDto ab, Staff s);
	public ServiceResponse deleteAirlineBooking(long id);
	public AirlineBooking getAirlineBooking(long id);
	public List<AirlineBooking> getAirlineListBooking();
	public int getAirlineBookingCount();
	public List<AirlineBooking> getAirlineLisBooking(long did, long aid, Calendar departureDate, Calendar returnDate ,boolean isRoundTrip, long status);
	public ServiceResponse internal_reserve_booking(List<AirlineBookingDto> bookingList, Staff s);
	public ServiceResponse modify_booking_price(long bid, double newpd);
	public ServiceResponse delete_booking(long bid);
	public ServiceResponse confirm_booking(long bid);
	public ServiceResponse view_booking(long id, Staff s);
	public List<AirlineBooking> bookingSearch(int attrType, String keyword, int sid, int payer_type_id, int payment_method_id, int[] status, int size, int page);
	public int bookingSearchCount(int attrType, String keyword, int sid, int payer_type_id, int payment_method_id, int[] status);
	
	public ServiceResponse addAirlineCompany(AirlineCompanyDto airlineCompanyDto);
	public ServiceResponse updateAirlineCompany(AirlineCompanyDto airlineCompanyDto);
	public ServiceResponse deleteAirlineCompany(long id);
	public AirlineCompany getAirlineCompany(long id);
	public List<AirlineCompany> getAirlineCompanyList();
	public int getAirlineCompanyCount();
	public List<Airport> getAirportsByName(String airportName);
	public void deletePastAirline(String departureEndDate);
	
}
