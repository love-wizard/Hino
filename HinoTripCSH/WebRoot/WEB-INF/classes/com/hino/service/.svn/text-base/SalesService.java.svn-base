package com.hino.service;


import java.io.File;
import java.util.Calendar;
import java.util.List;

import com.hino.dto.BookingReserveDto;
import com.hino.dto.GroupCAMDto;
import com.hino.dto.GroupTagDto;
import com.hino.dto.ReserveBookingDto;
import com.hino.dto.SalesBookingDto1;
import com.hino.dto.VoucherCAMDto;
import com.hino.model.Group;
import com.hino.util.ServiceResponse;
import org.apache.commons.fileupload.FileItem;

import com.hino.model.Booking;
import com.hino.model.CarRequest;
import com.hino.model.GroupTag;
import com.hino.model.Resource;
import com.hino.model.Staff;
import com.hino.model.TourSurvey;
import com.hino.model.Voucher;
import com.hino.model.Route;


public interface SalesService {

	public ServiceResponse create_group(GroupCAMDto scamdto);
	public ServiceResponse delete_group(int id);
	public ServiceResponse sales_edit_group(GroupCAMDto scamdto);
	public Group viewGroupById(int id);

	public int getSalesMarketReportCount(Staff curStaff);

	public List<Resource> getSalesMarketReportList(
			Staff curStaff, int page, int size);

	public void create_new_market_report(Staff curStaff, FileItem marketReportFI);

	public int getGenMarketReportCount();

	// Ken Chen 2012/10/03 add month parameter
	public int count_group(int status, String month);
	public List<Group> list_group_by_status_paging_ordering(final int status, final String month,final int page, final int size, final String orderingAttr, final boolean isAscending);
	public List<Group> list_all_group_by_paging(final int page, final int size, String orderingAttr, boolean isAscending);
	public int count_group_by_status(int status);
	public int getAllGroupCount();

	//list group interal bookable
	public List<Group> list_group_intbookable(String orderingAttr, boolean isAscending);
	//public List<Group> list_group_extbookable();
	
	public List<Resource> getGenMarketReportList(int page, int size);

	public void change_booking_status(GroupCAMDto scamdto);
	public ServiceResponse set_booking_stage(int gid);
	public ServiceResponse set_process_stage(int gid);
	public ServiceResponse set_finish_stage(int gid);
	public ServiceResponse set_cancel_stage(int gid);
	
	//public ServiceResponse internal_reserve_booking(int gid, List<SalesBookingDto1> sbto, Staff rep);
	public ServiceResponse internal_reserve_booking(int gid, List<BookingReserveDto> brl, int sid, int bktype);
	public ServiceResponse delete_booking(long id, Staff s);
	
	public ServiceResponse view_booking(long id, Staff s);
	public ServiceResponse issueTicket(long id, Staff s);
	public List<Booking> list_booking_by_group_orderby_bstatus(int gid);
	public List<Booking> list_booking_by_group_bstatus(int gid, int status);
	public List<Booking> bookingSearch(int attrType, String keyword, int sid, Integer[] status, int size, int page);
	public int bookingSearchCount(int attrType, String keyword, int sid, Integer[] status);
	public int countGroupBooking(int gid, int status);
	
	public List<Booking> findGroupFeedbackbooking(int gid);
	public int countGroupFeedback(int gid);
	
	public List<Booking> list_unconfirmed_external_booking();
	
	public List<CarRequest> list_carrequest(int start, int count, Integer[] status);
	public int countCarRequest(Integer[] status);
	public void setCarRequestRead(long id);
	
	public List<String> create_voucher(VoucherCAMDto vdto);
	public ServiceResponse delte_voucher(long id, String code);
	public Voucher get_voucher(long id, String code);
	public ServiceResponse update_voucher(Voucher v);
	public List<Voucher> list_voucher(String keyword, Long no);
	public int count_list_voucher(String keyword, Long no);
	
	public ServiceResponse check_voucher(String code, int gid);
	
	public ServiceResponse create_tour_survey(TourSurvey ts, FileItem fi);
	public ServiceResponse delete_tour_survey(long id);
	public ServiceResponse change_status_tour_survey(long id, int status);
	public List<TourSurvey> list_survey_stats(int status);
	
	public ServiceResponse create_group_tag(GroupTagDto groupTag);
	public ServiceResponse update_group_tag(GroupTagDto groupTag);
	public ServiceResponse delete_group_tag(long id);
	public ServiceResponse delete_group_tag_by_group_id(int gid);
	public GroupTag get_group_tag(long id);
	public List<GroupTag> list_group_tag_by_group_id(int gid);
	public List<Group> list_group_by_group_tag(int size, int tid, String TValue);
	public List<Route> list_route_by_group_tag(int size, int tid, String TValue);
	public void delete_group_tag_by_route_id(long rid);
	public List<GroupTag> list_group_tag_by_route_id(long rid);
	public List<Group> getTop10HotestGroups();
}
