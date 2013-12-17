package com.hino.service;

import java.util.List;

import com.hino.dto.AddressDto;
import com.hino.dto.BookingReserveDto;
import com.hino.util.ServiceResponse;

public interface BookingGroupManager {
	public ServiceResponse modify_booking_price(long bid, double newpd);
	public ServiceResponse delete_booking(long bid, int gid);
	public ServiceResponse confirm_booking(long bid, int gid);
	public ServiceResponse cancel_booking(long bid, int gid);
	public ServiceResponse refuse_booking(long bid);
	public ServiceResponse reserve_booking(int gid, List<BookingReserveDto> brl, boolean isexternal, int sid, long cid, int bktype, AddressDto address);
	public ServiceResponse repay_booking(int gid, String email, BookingReserveDto cbd);
}
