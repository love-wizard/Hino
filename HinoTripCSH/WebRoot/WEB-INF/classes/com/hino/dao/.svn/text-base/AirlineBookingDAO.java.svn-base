package com.hino.dao;

import java.util.Calendar;
import java.util.List;

import com.hino.model.AirlineBooking;
import com.hino.model.Booking;
import com.hino.util.ServiceResponse;

public interface AirlineBookingDAO {
	public void update(AirlineBooking ab);
	public void save(AirlineBooking ab);
	public void delete(long id);
	public AirlineBooking getAirlineBookingById(long id);
	public List<AirlineBooking> getAllAirlineBooking();
	public int getAllAirlineBookingCount();
	public List<AirlineBooking> getAirlineBookingList(long status);
	public int getAirlineBookingListCount(long status);
	public List<AirlineBooking> complexSearching(int attr_type, String keyword, int sid, int payer_type_id, int payment_method_id, int[] status, int size, int page, Calendar start, Calendar end);
	public long complexSearchingCount(int attr_type, String keyword, int sid, int payer_type_id, int payment_method_id, int[] status);
	}
