package com.hino.service;

import java.util.Calendar;
import java.util.List;

import com.hino.dto.AddressDto;
import com.hino.dto.BookingReserveDto;
import com.hino.dto.CarRequestDto;
import com.hino.dto.CustomerBasicInfoDto;
import com.hino.dto.CustomerBookingDto;
import com.hino.dto.CustomerEmailCAMDto;
import com.hino.dto.VipOrderReserveDto;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.CustomerHistory;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.VipOrder;
import com.hino.util.ServiceResponse;

public interface CustomerService {
	public ServiceResponse registeration(String email, String fn, String ln, String pwd);
	public Customer view_customer(long cid);
	
	public ServiceResponse reset_vip(Calendar c, long cid);
	public ServiceResponse reset_point(int point, long cid, long uid);
	public ServiceResponse update_customer_basic(CustomerBasicInfoDto basicinfodto);
	public ServiceResponse getCustomerByEmail(String email);
	
	public void reset_password(String password, long cid);
	
	public List<Customer> getPagedCustomerList(int page, int size);
	public int getCustomerCount();
	
	public List<Customer> searchCustomer(int page, int size, String keyword, int type);
	public int searchCustomerCount(String keyword, int type);
	
	public List<Booking> getMyBookingList(long cid);
	public List<Booking> getMyBookingList(String email);

	public ServiceResponse sendEmail(CustomerEmailCAMDto emaildto);

	public Group getGroupById(int id);
	
	public ServiceResponse external_booking(int gid, long cid, List<BookingReserveDto> cbd, int bktype, AddressDto address);
	public ServiceResponse issueTicket(long id, long cid);
	public ServiceResponse addFeedBack(long cid, long id, String feedback);
	public ServiceResponse requestFeedBack(long cid, long id);
	
	public ServiceResponse vip_reserve(VipOrderReserveDto vordto);
	public ServiceResponse vip_card_completed(long vid);
	public VipOrder vip_issue_card(int sid, long vid);
	
	public List<Object[]> output_data();
	public String output_group_emails(int gid);
	
	public void external_car_request(CarRequestDto crdto);
	
	public void vote_tour_survey(int vote, long tourid);
	
	public void update_customer_basic_2(String em, String uni, String ph, String city, String cn, int gender);
	
	public List<Booking> list_sk_win(int x);
	
	public Booking view_booking(String ref, long cid);
	public Booking view_booking(String ref);
	
	/**
	 * Updates customer's basic information
	 * 
	 * @author Devon King
	 * @param basicinfodto
	 * @return
	 */
	public ServiceResponse update_customer_basicinfo(CustomerBasicInfoDto basicinfodto);
	
	/**
	 * Resets account password
	 * 
	 * @author Devon King
	 * @param originalPassword
	 * @param newPassword
	 * @param cid
	 * @return
	 */
	public ServiceResponse resetAccountPassword(String originalPassword, String newPassword, long cid);
	
	public List<VipOrder> getVipOrderByEmail(String email);
	public List<CustomerHistory> getCustomeHistoryByCid(long cid);
	public int getCustomerHistoryCountByCid(long cid);
	ServiceResponse cancel_vip(long cid);
	
	public String bat_adjust_vip_credit(String[] email, int credit, Staff s);
	public void update_booking_payment_method(String booking_ref, int bookingNethod);
	public ServiceResponse sendEmailMultiAtts(CustomerEmailCAMDto emaildto);
	public ServiceResponse repay_booking(int gid, String email,
			BookingReserveDto cbd);
	
}
