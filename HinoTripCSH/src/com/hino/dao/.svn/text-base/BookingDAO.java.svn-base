package com.hino.dao;

import java.util.Calendar;
import java.util.List;

import com.hino.model.Booking;

public interface BookingDAO {
	public void save(Booking b);
	public void delete(Booking b);
	public void update(Booking b);
	
	public Booking getBookingById(long id);
	public Booking findBookingById(long id);
	public Booking findBookingByRef(String ref);
	public Booking findBookingByAuth(String auth);
	public boolean checkBooked(int gid, long cid);
	public boolean checkBooked(int gid, String email);
	
	public void changeBookingStatusForGroup(int gid, int statusfrom, int statusto);
	
	public List<Booking> findBookingListByTid(long tid);
	public List<Booking> findBookingListByGid(int gid);
	public List<Booking> findBookingListByGidAndStatus(int gid, int status);
	public List<Booking> findBookingListBySid(int sid);
	public List<Booking> findBookingListByGidAndSid(int gid, long id);
	public List<Booking> findBookingListByCid(long id);
	public List<Booking> findBookingByEmail(String email);
	public List<Booking> findBookingListForReward(int status, int sid, Calendar start, Calendar end);
	public List<Booking> findBookingListForReward(int status, int sid, int gid);
	
	public List<Booking> complexSearching(int attr_type, String keyword, int sid, Integer[] status, int size, int page, Calendar start, Calendar end);
	public long complexSearchingCount(int attr_type, String keyword, int sid, Integer[] status);
	
	//public List<Booking> advancedSearching(int code, );
	public List<Booking> findBookingForGroupTable(int gid);
	public int countGroupBooking(int gid, int status);
	
	public int countGroupFeedbackBooking(int gid);
	public List<Booking> findGroupFeedbackBooking(int gid);
	
	public List<Booking> list_unconfirmed_external_booking();
	public int count_unconfirmed_external_booking();
	
	public List<Booking> list_sk_win(int limit);
	public List<Booking> findEffectiveBookingByVoucher(String vcode);
	public List<Booking> findActiveBookingByGidAndType(int id, int type);
}
