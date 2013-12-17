package com.hino.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import java.util.Calendar;


import org.apache.commons.fileupload.FileItem;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.hino.dao.BookingDAO;
import com.hino.dao.CarRequestDAO;
import com.hino.dao.CustomerDAO;
import com.hino.dao.GroupDAO;
import com.hino.dao.GroupHistoryDAO;
import com.hino.dao.GroupTagDAO;
import com.hino.dao.ResourceDAO;
import com.hino.dao.RouteDAO;
import com.hino.dao.StaffDAO;
import com.hino.dao.TourSurveyDAO;
import com.hino.dao.VoucherDAO;
import com.hino.dao.impl.GroupHistoryDAOImpl;

import com.hino.dto.BookingReserveDto;
import com.hino.dto.GroupCAMDto;
import com.hino.dto.GroupTagDto;
import com.hino.dto.ReserveBookingDto;
import com.hino.dto.SalesBookingDto1;
import com.hino.dto.VoucherCAMDto;
import com.hino.model.Group;

import com.hino.model.Booking;
import com.hino.model.CarRequest;
import com.hino.model.Customer;
import com.hino.model.GroupHistory;
import com.hino.model.GroupTag;
import com.hino.model.Resource;
import com.hino.model.Route;
import com.hino.model.Staff;
import com.hino.model.TourSurvey;
import com.hino.model.Voucher;

import com.hino.service.BookingGroupManager;
import com.hino.service.SalesService;
import com.hino.service.VoucherGroupManager;
import com.hino.util.FileCenter;
import com.hino.util.HinoMailUtil;
import com.hino.util.PriviledgeParser;
import com.hino.util.RandomGenerator;
import com.hino.util.TimeFormater;

import com.hino.util.ServiceResponse;

import com.hino.util.Info;

public class SalesServiceImpl implements SalesService {
	private FileCenter fileCenter;
	private GroupDAO groupDao;
	private StaffDAO staffDao;
	private BookingDAO bookingDao;
	private ResourceDAO resourceDao;
	private RouteDAO routeDao;
	private CustomerDAO customerDao;
	private CarRequestDAO carRequestDao;
	private VoucherDAO voucherDao;
	private TourSurveyDAO tourSurveyDao;
	private BookingGroupManager bookingGroupManager;
	private VoucherGroupManager voucherGroupManager;
	private GroupTagDAO groupTagDao ;
	private GroupHistoryDAO groupHistoryDao;
	
	public TourSurveyDAO getTourSurveyDao() {
		return tourSurveyDao;
	}

	public void setTourSurveyDao(TourSurveyDAO tourSurveyDao) {
		this.tourSurveyDao = tourSurveyDao;
	}

	public VoucherGroupManager getVoucherGroupManager() {
		return voucherGroupManager;
	}

	public void setVoucherGroupManager(VoucherGroupManager voucherGroupManager) {
		this.voucherGroupManager = voucherGroupManager;
	}

	public CarRequestDAO getCarRequestDao() {
		return carRequestDao;
	}

	public VoucherDAO getVoucherDao() {
		return voucherDao;
	}

	public void setVoucherDao(VoucherDAO voucherDao) {
		this.voucherDao = voucherDao;
	}

	public void setCarRequestDao(CarRequestDAO carRequestDao) {
		this.carRequestDao = carRequestDao;
	}


	
	public BookingGroupManager getBookingGroupManager() {
		return bookingGroupManager;
	}

	public void setBookingGroupManager(BookingGroupManager bookingGroupManager) {
		this.bookingGroupManager = bookingGroupManager;
	}
	
	public CustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	public FileCenter getFileCenter() {
		return fileCenter;
	}

	public BookingDAO getBookingDao() {
		return bookingDao;
	}

	public void setBookingDao(BookingDAO bookingDao) {
		this.bookingDao = bookingDao;
	}
	public GroupDAO getGroupDao() {
		return groupDao;
	}

	public ResourceDAO getResourceDao() {
		return resourceDao;
	}

	public RouteDAO getRouteDao() {
		return routeDao;
	}

	public StaffDAO getStaffDao() {
		return staffDao;
	}
	public void setFileCenter(FileCenter fileCenter) {
		this.fileCenter = fileCenter;
	}

	public void setGroupDao(GroupDAO groupDao) {
		this.groupDao = groupDao;
	}

	public void setResourceDao(ResourceDAO resourceDao) {
		this.resourceDao = resourceDao;
	}

	public void setRouteDao(RouteDAO routeDao) {
		this.routeDao = routeDao;
	}

	public void setStaffDao(StaffDAO staffDao) {
		this.staffDao = staffDao;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse create_group(GroupCAMDto gdto) {
		ServiceResponse sr = new ServiceResponse();
		Group g = new Group();
		g.setName(gdto.getName());
		g.setName_en(gdto.getName_en());
		g.setPickup_info(gdto.getPickup_info());
		g.setTraffic_hotel_info(gdto.getTraffic_hotel_info());
		g.setSeats(gdto.getSeats());
		g.setPrice(gdto.getPrice());
		g.setVip_price(gdto.getVip_price());
		g.setMax_point(gdto.getMax_point());
		g.setRoute(routeDao.getRouteById(gdto.getRouteid()));
		g.setDepart_date(gdto.getDepart_date());
		
		g.setMulti_days(gdto.isMulti_days());
		g.setVoucher_match(gdto.getVoucher_match());
		
		g.setExternal_indicator(gdto.getExternal_indicator());
		g.setExternal_indicator_en(gdto.getExternal_indicator_en());
		g.setInternalBookable(gdto.isInternalBookable());
		g.setExternalBookable(gdto.isExternalBookable());
		g.setInternalView(gdto.isInternalView());
		g.setExternalView(gdto.isExternalView());
		
		g.setExt_groupon(gdto.isExt_groupon());
		g.setInt_groupon(gdto.isInt_groupon());
		g.setExt_seckill(gdto.isExt_seckill());
		g.setInt_seckill(gdto.isInt_seckill());
		
		g.setSeats_groupon_min(gdto.getSeats_groupon_min());
		g.setSeats_groupon(gdto.getSeats_groupon());
		g.setGroup_price(gdto.getGroup_price());
		g.setGroup_vip_price(gdto.getGroup_vip_price());
		g.setGroupon_end_time(gdto.getGroupon_end_time());
		g.setSeats_seckill(gdto.getSeats_seckill());
		g.setSeckill_price(gdto.getSeckill_price());
		g.setSeckill_vip_price(gdto.getSeckill_vip_price());
		g.setSeckill_start_time(gdto.getSeckill_start_time());
		g.setSeckill_end_time(gdto.getSeckill_end_time());
		
		if (gdto.getGo_img_1()!= null) {
			try {
				g.setGo_img_1(fileCenter.save_file(gdto.getGo_img_1(),false));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (gdto.getGo_img_2()!= null) {
			try {
				g.setGo_img_2(fileCenter.save_file(gdto.getGo_img_2(),false));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (gdto.getSk_img_1()!= null) {
			try {
				g.setSk_img_1(fileCenter.save_file(gdto.getSk_img_1(),false));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (gdto.getSk_img_2()!= null) {
			try {
				g.setSk_img_2(fileCenter.save_file(gdto.getSk_img_2(),false));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
				
		if (gdto.getTicket_file()!=null)
		{
			try {
				g.setTicket_url(fileCenter.save_file(gdto.getTicket_file(), false));
				
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
		
		//Devon King - 2012/09/30 - TD#20 Persists voucher choice 
		g.setVoucher_applied(gdto.getVoucher_applied());
		g.setPriority(gdto.getPriority());
		
		groupDao.save(g);
		sr.setSucc(true);
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse delete_group(int id) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(id);
		if(g==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target group was no longer avaliable!");
			return sr;
		} else if(g.getStatus()==Info.GS_PLANNING)
		{
			groupDao.delete(g);
			
			//Ken Chen 2013-06-22 记录被更新前的团信息到History表
			GroupHistory gh = new GroupHistory(g);
			this.groupHistoryDao.save(gh);
			
			sr.setSucc(true);
			return sr;
		} else
		{
			sr.addMsg("Could't delete group in status other than planning!");
			sr.setSucc(false);
			return sr;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse sales_edit_group(GroupCAMDto gdto) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(gdto.getId());
		
		List<Booking> grouponBookings = bookingDao.findActiveBookingByGidAndType(g.getId(), Info.BOOKING_TYPE_GROUPON);
		if (grouponBookings.size() > gdto.getSeats_groupon()) {			
			sr.setSucc(false);
			sr.addMsg("The new groupon seats' number can't less than the number of seats (" + grouponBookings.size() + ") have already been booked!");
			return sr;
		}
		
		List<Booking> normalBookings = bookingDao.findActiveBookingByGidAndType(g.getId(), Info.BOOKING_TYPE_EXTERNAL);
		if (normalBookings.size() > gdto.getSeats()) {			
			sr.setSucc(false);
			sr.addMsg("The new normal seats' number can't less than the number of seats (" + normalBookings.size() + ") have already been booked!");
			return sr;
		}
		
		List<Booking> seckillBookings = bookingDao.findActiveBookingByGidAndType(g.getId(), Info.BOOKING_TYPE_SECKILL);
		if (seckillBookings.size() > gdto.getSeats_seckill()) {			
			sr.setSucc(false);
			sr.addMsg("The new seckill seats' number can't less than the number of seats (" + seckillBookings.size() + ") have already been booked!");
			return sr;
		}
		
		g.setName(gdto.getName());
		g.setName_en(gdto.getName_en());
		g.setPickup_info(gdto.getPickup_info());
		g.setTraffic_hotel_info(gdto.getTraffic_hotel_info());
		g.setSeats(gdto.getSeats());
		g.setPrice(gdto.getPrice());
		g.setVip_price(gdto.getVip_price());
		g.setMax_point(gdto.getMax_point());
		g.setRoute(routeDao.getRouteById(gdto.getRouteid()));
		g.setDepart_date(gdto.getDepart_date());
		
		g.setMulti_days(gdto.isMulti_days());
		g.setVoucher_match(gdto.getVoucher_match());
		/*
		g.setExternal_indicator(gdto.getExternal_indicator());
		g.setExternal_indicator_en(gdto.getExternal_indicator_en());
		g.setInternalBookable(gdto.isInternalBookable());
		g.setExternalBookable(gdto.isExternalBookable());
		g.setInternalView(gdto.isInternalView());
		g.setExternalView(gdto.isExternalView());
		
		
		g.setExt_groupon(gdto.isExt_groupon());
		g.setInt_groupon(gdto.isInt_groupon());
		g.setExt_seckill(gdto.isExt_seckill());
		g.setInt_seckill(gdto.isInt_seckill());
		*/
		
		g.setSeats_groupon_min(gdto.getSeats_groupon_min());
		g.setSeats_groupon(gdto.getSeats_groupon());
		g.setGroup_price(gdto.getGroup_price());
		g.setGroup_vip_price(gdto.getGroup_vip_price());
		g.setGroupon_end_time(gdto.getGroupon_end_time());
		g.setSeats_seckill(gdto.getSeats_seckill());
		g.setSeckill_price(gdto.getSeckill_price());
		g.setSeckill_vip_price(gdto.getSeckill_vip_price());
		g.setSeckill_start_time(gdto.getSeckill_start_time());
		g.setSeckill_end_time(gdto.getSeckill_end_time());
		
		if (gdto.getGo_img_1()!= null) {
			try {
				g.setGo_img_1(fileCenter.save_file(gdto.getGo_img_1(),false));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (gdto.getGo_img_2()!= null) {
			try {
				g.setGo_img_2(fileCenter.save_file(gdto.getGo_img_2(),false));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (gdto.getSk_img_1()!= null) {
			try {
				g.setSk_img_1(fileCenter.save_file(gdto.getSk_img_1(),false));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (gdto.getSk_img_2()!= null) {
			try {
				g.setSk_img_2(fileCenter.save_file(gdto.getSk_img_2(),false));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if(gdto.getTicket_file()!=null)
		{
			try {
				g.setTicket_url(fileCenter.save_file(gdto.getTicket_file(), false));
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
		
		//Devon King - 2012/09/30 - TD#20 Persists voucher choice 
		g.setVoucher_applied(gdto.getVoucher_applied());
		g.setPriority(gdto.getPriority());
		
		//Ken Chen 2013-06-22 记录被更新前的团信息到History表
		GroupHistory gh = new GroupHistory(g);
		this.groupHistoryDao.save(gh);
		
		sr.setSucc(true);
		return sr;
	}

	@Override
	public Group viewGroupById(int id) {
		return groupDao.viewGroupById(id);
	}


	@Override
	public int getAllGroupCount() {
		return  groupDao.getAllGroupCount();
	}

	@Override
	public List<Group> list_all_group_by_paging(int page, int size,
			String orderingAttr, boolean isAscending) {
		return  groupDao.list_all_group_by_paging(page, size, orderingAttr, isAscending);
	}

	@Override
	public List<Group> list_group_by_status_paging_ordering(int status, String month,
			int page, int size, String orderingAttr, boolean isAscending) {
		return groupDao.list_group_by_status_paging_ordering(status, month, page, size, orderingAttr, isAscending);
	}


	@Override
	public int getSalesMarketReportCount(Staff curStaff) {
		return resourceDao.getSalesMarketReportCount(curStaff);
	}

	@Override
	public List<Resource> getSalesMarketReportList(
			Staff curStaff, int page, int size) {
		return resourceDao.list_sales_market_report_by_paging(curStaff, page, size);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void create_new_market_report(Staff curStaff, FileItem marketReportFI) {
		// Upload market report
		String marketReportUrl = "";
		if (marketReportFI.getName().trim().compareTo("") != 0) {
			try {
				marketReportUrl = fileCenter.save_file(marketReportFI, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Resource newMarkerReport = new Resource();
		newMarkerReport.setAuthor(curStaff);
		newMarkerReport.setCategory(Info.FILE_CATEGORY_INDEX_REP_MARKET);
		newMarkerReport.setFilename(marketReportUrl);
		newMarkerReport.setName(fileCenter.getFileItemName(marketReportFI));
		newMarkerReport.setUpdate_time(Calendar.getInstance());
		
		resourceDao.save(newMarkerReport);
	}

	@Override
	public int getGenMarketReportCount() {
		return resourceDao.getGenMarketReportCount();
	}
	
	@Override
	public List<Resource> getGenMarketReportList(int page, int size) {
		return resourceDao.list_gen_market_report_by_paging(page, size);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse set_booking_stage(int gid) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(gid);
		GroupHistory gh = new GroupHistory(g);
		
		if(g!=null)
		{
			if(g.getStatus()!=Info.GS_PLANNING)
			{
				sr.setSucc(false);
				sr.addMsg("Only group in planning status could be set to openning");
				return sr;
			}
			//Ken Chen 2013-06-22 记录被更新前的团信息到History表
			
			this.groupHistoryDao.save(gh);
			
			g.setStatus(Info.GS_OPENNING);
			sr.setSucc(true);
		} else
		{
			sr.addMsg("Target group was no longer avaliable!");
			sr.setSucc(false);
		}
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse set_finish_stage(int gid) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(gid);
		GroupHistory gh = new GroupHistory(g);
		
		if(g!=null)
		{
			if(g.getStatus()!=Info.GS_PROCESSING)
			{
				sr.setSucc(false);
				sr.addMsg("Only group in processing status could be set to finish");
				return sr;
			}
			
			//Ken Chen 2013-06-22 记录被更新前的团信息到History表
			
			this.groupHistoryDao.save(gh);
			g.setStatus(Info.GS_FINISHED);
			sr.setSucc(true);
		} else
		{
			sr.addMsg("Target group was no longer avaliable!");
			sr.setSucc(false);
		}
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse set_process_stage(int gid) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(gid);
		GroupHistory gh = new GroupHistory(g);
		
		if(g!=null)
		{
			if(g.getStatus()!=Info.GS_OPENNING)
			{
				sr.setSucc(false);
				sr.addMsg("Only group in openning status could be set to processing");
				return sr;
			}
			
			if(!g.getGuide_ready())
			{
				sr.setSucc(false);
				sr.addMsg("Couldn't set to processing since guide hasn't been allocated");
				return sr;
			}
			
			if(bookingDao.countGroupBooking(g.getId(), Info.BKS_TRANSFERING)!=0)
			{
				sr.setSucc(false);
				sr.addMsg("Couldn't set to processing since existing booking with status of transfering");
				return sr;
			}
			
			bookingDao.changeBookingStatusForGroup(g.getId(), Info.BKS_RESERVING, Info.BKS_CANCELED);
			
			this.groupHistoryDao.save(gh);
			
			g.setStatus(Info.GS_PROCESSING);
			sr.setSucc(true);
		} else
		{
			sr.addMsg("Target group was no longer avaliable!");
			sr.setSucc(false);
		}
		return sr;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse set_cancel_stage(int gid) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(gid);
		// set all belonging booking to canceled status
		if(g!=null)
		{	
			if(bookingDao.countGroupBooking(gid, Info.BKS_TRANSFERING)>0)
			{
				sr.setSucc(false);
				sr.addMsg("Couldn't set to cancel since existing booking with status of transfering");
				return sr;
			} 
			
			if(bookingDao.countGroupBooking(gid, Info.BKS_CONFIRMED)>0)
			{
				sr.setSucc(false);
				sr.addMsg("Couldn't set to cancel since existing booking with status of confirming");
				return sr;
			}
			
			//Ken Chen 2013-06-22 记录被更新前的团信息到History表
			GroupHistory gh = new GroupHistory(g);
			this.groupHistoryDao.save(gh);
			
			g.setStatus(Info.GS_CANCELED);
			groupDao.update(g);	
			bookingDao.changeBookingStatusForGroup(g.getId(), Info.BKS_RESERVING, Info.BKS_CANCELED);
			sr.setSucc(true);
		} else
		{
			sr.addMsg("Target group was no longer avaliable!");
			sr.setSucc(false);
		}
		
		return sr;
	}

	@Override
	public List<Group> list_group_intbookable(String orderingAttr, boolean isAscending) {
		if(orderingAttr==null)
		{
			orderingAttr="depart_date";
		}
		return groupDao.list_group_by_viewable(orderingAttr, isAscending);
	}

	@Override
	public int count_group_by_status(int status) {
		return groupDao.count_group_by_status(status);
	}
	
	//Ken Chen 2012/10/03 Add two function
	public int count_group(int status) {
		return groupDao.count_group_by_status(status);
	}
	
	@Override
	public int count_group(int status, final String month) {
		return groupDao.count_group(status, month);
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void change_booking_status(GroupCAMDto sdto) {
		Group g = groupDao.getGroupById(sdto.getId());
		if(g!=null)
		{	GroupHistory gh = new GroupHistory(g);
		
			g.setInternalView(sdto.isInternalView());
			g.setExternalView(sdto.isExternalView());
			g.setInternalBookable(sdto.isInternalBookable());
			g.setExternalBookable(sdto.isExternalBookable());
			g.setExternal_indicator(sdto.getExternal_indicator());
			g.setExternal_indicator_en(sdto.getExternal_indicator_en());
			g.setExt_groupon(sdto.isExt_groupon());
			g.setInt_groupon(sdto.isInt_groupon());
			g.setExt_seckill(sdto.isExt_seckill());
			g.setInt_seckill(sdto.isInt_seckill());
			
			this.groupHistoryDao.save(gh);
		}
	}

	@Override
	public ServiceResponse delete_booking(long id, Staff s) {
		
		ServiceResponse sr = new ServiceResponse();
		Booking b = bookingDao.findBookingById(id);
		if(s==null)
		{
			sr.setSucc(false);
			sr.addMsg("Access denied!");
			return sr;
		}
		
		if(PriviledgeParser.has_priviledge(s.getPriviledge(), PriviledgeParser.SALES_MANAGE)||s.getId()==b.getRep().getId())
		{
			return bookingGroupManager.delete_booking(b.getId(), b.getGroup().getId());
		} else
		{
			sr.setSucc(false);
			sr.addMsg("Access denied!");
			return sr;
		}
	}

	@Override
	public List<Booking> list_booking_by_group_orderby_bstatus(int gid) {
		return bookingDao.findBookingListByGid(gid);
	}
	
	@Override
	public List<Booking> list_booking_by_group_bstatus(int gid, int status) {
		return bookingDao.findBookingListByGidAndStatus(gid, status);
	}

	@Override
	public List<Booking> bookingSearch(int attrType, String keyword, int sid,
			Integer[] status, int size, int page) {
		if(keyword.equals(""))
		{
			return bookingDao.complexSearching(-1, keyword, sid, status, size, page, null, null);
		} else
		{
			if (attrType==1)
			{
				try{
					Integer.parseInt(keyword);
				} catch (NumberFormatException nfe)
				{
					
				} 
				return new ArrayList<Booking>();
			}
			return bookingDao.complexSearching(attrType, keyword, sid, status, size, page, null, null);
		}
	}

	@Override
	public int bookingSearchCount(int attrType, String keyword, int sid, Integer[] status) {
		if(keyword.equals(""))
		{
			return (int)bookingDao.complexSearchingCount(-1, keyword, sid, status);
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
			return (int)bookingDao.complexSearchingCount(attrType, keyword, sid, status);
			
		}
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS ,readOnly=true, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse view_booking(long id, Staff s) {
		ServiceResponse sr = new ServiceResponse();
		Booking b = null;
		if(s==null)
		{
			sr.addMsg("Access denied!");
			sr.setSucc(false);
			return sr;
		}
		
		if(PriviledgeParser.has_priviledge(s.getPriviledge(), PriviledgeParser.SALES_MANAGE))
		{
			b = bookingDao.findBookingById(id);
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
			b = bookingDao.findBookingById(id);
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
	@Transactional(propagation=Propagation.SUPPORTS ,readOnly=true, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse issueTicket(long id, Staff s) {
		ServiceResponse sr = new ServiceResponse();
		if(s==null)
		{
			sr.addMsg("Access denied!");
			sr.setSucc(false);
			return sr;
		}
		
		Booking b = bookingDao.findBookingById(id);
		if(b==null||b.getStatus()!=Info.BKS_CONFIRMED)
		{
			sr.setSucc(false);
			sr.addMsg("Ticket could only be issued under confirmed status!");
			return sr;
		} 
		
		if(PriviledgeParser.has_priviledge(s.getPriviledge(), PriviledgeParser.SALES_MANAGE))
		{	
			sr.setSucc(true);
			sr.setResponse(b.getTicket_auth());
			return sr;	
		}
		
		if(PriviledgeParser.has_priviledge(s.getPriviledge(), PriviledgeParser.SALES_REP))
		{
			Staff rep = b.getRep();
			if(rep!=null)
			{
				if(rep.getId()==s.getId())
				{
					sr.setSucc(true);
					sr.setResponse(b.getTicket_auth());
					return sr;
				}
			} 
		}
		
		sr.addMsg("Access denied!");
		sr.setSucc(false);
		return sr;
	}

	@Override
	public int countGroupBooking(int gid, int status) {
		return bookingDao.countGroupBooking(gid, status);
	}

	@Override
	public int countGroupFeedback(int gid) {
		return bookingDao.countGroupFeedbackBooking(gid);
	}

	@Override
	public List<Booking> findGroupFeedbackbooking(int gid) {
		return bookingDao.findGroupFeedbackBooking(gid);
	}

	@Override
	public ServiceResponse internal_reserve_booking(int gid,
			List<BookingReserveDto> brl, int sid, int bktype) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.viewGroupById(gid);
		
		if(g==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target group was no longer avaliable!");
			//System.out.print("No Group");
			return sr;
		}
		
		if(g.getStatus()!=Info.GS_OPENNING)
		{
			sr.setSucc(false);
			sr.addMsg("Target group hasn't open!");
			return sr;
		}
		
		if(bktype==0)
		{
			if(!g.getInternalBookable())
			{
				sr.setSucc(false);
				sr.addMsg("Can't book this group internally!");
				return sr;
			}
		} else if(bktype==1) //group type info
		{
			if(!g.getInt_groupon())
			{
				sr.setSucc(false);
				sr.addMsg("Target group can't reserve internal group purchase!");
				return sr;
			}
			
			//check end time
			if(Calendar.getInstance().after(g.getGroupon_end_time()))
			{
				sr.setSucc(false);
				sr.addMsg("Target group purchase had been closed!");
				return sr;
			}
		} else
		{
			sr.setSucc(false);
			sr.addMsg("Error type of booking!");
			return sr;
		}
		
		Staff rep = staffDao.find(sid);
		if(rep==null)
		{
			sr.setSucc(false);
			sr.addMsg("Access denied!");
			return sr;
		}
		
		if(!PriviledgeParser.has_priviledge(rep.getPriviledge(),PriviledgeParser.SALES_REP))
		{
			sr.setSucc(false);
			sr.addMsg("Access denied!");
			return sr;
		}
		
		sr = bookingGroupManager.reserve_booking(gid, brl, false, rep.getId(), -1, bktype, null);
		
		if(!sr.isSucc()) {
			return sr;
		}
	
		//Ken Chen 2012/12/17 send email to client 
		Booking mainBooking = (Booking)sr.getResponse();
		HinoMailUtil.SendBookingEmail(mainBooking); 
		return sr;
	}

	@Override
	public List<Booking> list_unconfirmed_external_booking() {
		return bookingDao.list_unconfirmed_external_booking();
	}

	@Override
	public int countCarRequest(Integer[] status) {
		return (int)carRequestDao.getCarRequestCount(status);
	}

	@Override
	public List<CarRequest> list_carrequest(int start, int count,
			Integer[] status) {
		return carRequestDao.list_carrequest_by_paging(start, count, status);
	}

	@Override
	public void setCarRequestRead(long id) {
		CarRequest cr = carRequestDao.getCarRequestById(id);
		cr.setStatus(1);
		carRequestDao.update(cr);
		
	}

	@Override
	public List<String> create_voucher(VoucherCAMDto vdto) {
		Calendar exp = vdto.getExpire_date();
		int no_of_use = vdto.getNo_of_use();
		//String code = vdto.getCode();
		String mt = vdto.getMatch_type();
		double value = vdto.getValue();
		List<String> list = new ArrayList<String>();
		String temp_code;
		for (int i=0;i<vdto.getNo_of_create();i++)
		{
			Voucher v = new Voucher();
			temp_code = RandomGenerator.randomString(16);
			v.setCode(temp_code);
			v.setExpire_date(exp);
			v.setMatch_type(mt);
			v.setValue(value);
			v.setNo_of_use(no_of_use);
			voucherDao.save(v);
			list.add(temp_code);
			
		}
		return list;
	}

	@Override
	public ServiceResponse delte_voucher(long id, String code) {
		return null;
	}

	@Override
	public ServiceResponse update_voucher(Voucher v) {
		// TODO Auto-generated method stub
		voucherDao.update(v);
		return null;
	}

	@Override
	public Voucher get_voucher(long id, String code) {
		// TODO Auto-generated method stub

		if (code!=null)
		{
			List<Voucher> list = voucherDao.list_voucher(code, null, null, -1, -1);
			if (list.size()>0)
			return list.get(0);
			else 
			return null;
		} else
		{
			List<Voucher> list = voucherDao.list_voucher(null, null, id, -1, -1);
			if (list.size()>0)
			return list.get(0);
			else 
			return null;
		}
	}

	@Override
	public List<Voucher> list_voucher(String keyword, Long no) {

		if (keyword==null)
		{
			return voucherDao.list_voucher(null, null, no, -1, -1);
		} else
		{
			return voucherDao.list_voucher(keyword, null, null, -1, -1);
		}

	}

	@Override
	public int count_list_voucher(String keyword, Long no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServiceResponse check_voucher(String code, int gid) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(gid);
		Voucher v = voucherDao.getVoucherByCode(code);
		
		return voucherGroupManager.check_voucher(g, v);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse change_status_tour_survey(long id, int status) {
		TourSurvey ts = tourSurveyDao.getTourSurveyById(id);
		ts.setStatus(status);
		return null;
	}

	@Override
	public ServiceResponse create_tour_survey(TourSurvey ts, FileItem fi) {
		
		if (fi!= null) {
			try {
				ts.setImg_link(fileCenter.save_file(fi,false));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		ts.setPosted_date(Calendar.getInstance());
		tourSurveyDao.save(ts);
		return null;
	}

	@Override
	public ServiceResponse delete_tour_survey(long id) {
		tourSurveyDao.delete(id);
		return null;
	}

	@Override
	public List<TourSurvey> list_survey_stats(int status) {
		return tourSurveyDao.list_survey_by_status(status);
	}

	
	
	@Override
	public ServiceResponse create_group_tag(GroupTagDto groupTagDto) {
		GroupTag gTag = new GroupTag();
		gTag.setGroup_id(groupTagDto.getGroup_id());
		gTag.setTag_id(groupTagDto.getTag_id());
		gTag.setValue(groupTagDto.getValue());
		gTag.setRoute_id(groupTagDto.getRoute_id());
		
		this.groupTagDao.save(gTag);
		return null;
	}

	@Override
	public ServiceResponse update_group_tag(GroupTagDto groupTagDto) {
		
		GroupTag gTag = new GroupTag();
		gTag.setGroup_id(groupTagDto.getGroup_id());
		gTag.setTag_id(groupTagDto.getTag_id());
		gTag.setValue(groupTagDto.getValue());
		
		this.groupTagDao.update(gTag);
		return null;
	}

	@Override
	public ServiceResponse delete_group_tag(long id) {
		this.groupTagDao.delete(id);
		return null;
	}

	@Override
	public GroupTag get_group_tag(long id) {
		return this.groupTagDao.getGroupTag(id);
	}

	@Override
	public List<GroupTag> list_group_tag_by_group_id(int gid) {
		return this.groupTagDao.getGroupTagByGroupId(gid);
	}

	@Override
	public List<Group> list_group_by_group_tag(int size, int tid, String TValue) {
		return this.groupDao.getGroupByTag(size, tid, TValue);
	}

	public GroupTagDAO getGroupTagDao() {
		return groupTagDao;
	}

	public void setGroupTagDao(GroupTagDAO groupTagDao) {
		this.groupTagDao = groupTagDao;
	}

	@Override
	public ServiceResponse delete_group_tag_by_group_id(int gid) {
		this.groupTagDao.deleteGroupTag(gid);
		return null;
	}

	@Override
	public List<Route> list_route_by_group_tag(int size, int tid, String TValue) {		
		return routeDao.getRouteByTag(size, tid, TValue);
	}

	@Override
	public void delete_group_tag_by_route_id(long rid) {
		this.groupTagDao.deleteGroupTagByRouteId(rid);
	}

	@Override
	public List<GroupTag> list_group_tag_by_route_id(long rid) {
		return this.groupTagDao.getGroupTagByRouteId(rid);
	}

	public GroupHistoryDAO getGroupHistoryDao() {
		return groupHistoryDao;
	}

	public void setGroupHistoryDao(GroupHistoryDAO groupHistoryDao) {
		this.groupHistoryDao = groupHistoryDao;
	}

	@Override
	public List<Group> getTop10HotestGroups() {		
		return this.groupTagDao.getTop10HotestGroups();
	}
	
	
}
