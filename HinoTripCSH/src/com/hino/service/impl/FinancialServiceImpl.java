package com.hino.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hino.dao.AreaBranchDAO;
import com.hino.dao.BookingDAO;
import com.hino.dao.CustomerDAO;
import com.hino.dao.GroupDAO;
import com.hino.dao.GroupProfitDAO;
import com.hino.dao.StaffDAO;
import com.hino.dao.TransferDAO;
import com.hino.dao.VipOrderDAO;
import com.hino.dto.FinancialProfitFeedbackDto;
import com.hino.dto.SalesRewardDto;
import com.hino.model.AreaBranch;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.GroupProfit;
import com.hino.model.Staff;
import com.hino.model.Transfer;
import com.hino.model.VipOrder;
import com.hino.service.BookingGroupManager;
import com.hino.service.FinancialService;
import com.hino.service.VipOrderManager;
import com.hino.util.EmailUtil;
import com.hino.util.Info;
import com.hino.util.PriviledgeParser;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;
public class FinancialServiceImpl implements FinancialService {
	private GroupDAO groupDao;
	private BookingDAO bookingDao;
	private CustomerDAO customerDao;
	private StaffDAO staffDao;
	private TransferDAO transferDao;
	private GroupProfitDAO groupProfitDao;
	private AreaBranchDAO areaBranchDao;
	
	private BookingGroupManager bookingGroupManager;
	
	private VipOrderDAO vipOrderDao;
	private VipOrderManager vipOrderManager;
	private EmailUtil emailUtil;
	
	public EmailUtil getEmailUtil() {
		return emailUtil;
	}

	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}

	public VipOrderManager getVipOrderManager() {
		return vipOrderManager;
	}

	public void setVipOrderManager(VipOrderManager vipOrderManager) {
		this.vipOrderManager = vipOrderManager;
	}

	public VipOrderDAO getVipOrderDao() {
		return vipOrderDao;
	}

	public void setVipOrderDao(VipOrderDAO vipOrderDao) {
		this.vipOrderDao = vipOrderDao;
	}
	
	public BookingGroupManager getBookingGroupManager() {
		return bookingGroupManager;
	}

	public void setBookingGroupManager(BookingGroupManager bookingGroupManager) {
		this.bookingGroupManager = bookingGroupManager;
	}

	public GroupProfitDAO getGroupProfitDao() {
		return groupProfitDao;
	}

	public void setGroupProfitDao(GroupProfitDAO groupProfitDao) {
		this.groupProfitDao = groupProfitDao;
	}
	
	public TransferDAO getTransferDao() {
		return transferDao;
	}

	public void setTransferDao(TransferDAO transferDao) {
		this.transferDao = transferDao;
	}

	public StaffDAO getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDAO staffDao) {
		this.staffDao = staffDao;
	}
	
	public AreaBranchDAO getAreaBranchDao() {
		return areaBranchDao;
	}

	public void setAreaBranchDao(AreaBranchDAO areaBranchDao) {
		this.areaBranchDao = areaBranchDao;
	}	
	

	public CustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	public GroupDAO getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDAO groupDao) {
		this.groupDao = groupDao;
	}

	public BookingDAO getBookingDao() {
		return bookingDao;
	}

	public void setBookingDao(BookingDAO bookingDao) {
		this.bookingDao = bookingDao;
	}

	//refund process status 2->3 
	@Override
	public ServiceResponse cancel_booking(long id) {
		Booking b = bookingDao.findBookingById(id);
		int gid = b.getGroup().getId();

		return bookingGroupManager.cancel_booking(b.getId(), gid);
	}
	
	@Override
	public ServiceResponse delete_booking(long id){
		Booking b = bookingDao.findBookingById(id);
		int gid = b.getGroup().getId();
		return bookingGroupManager.delete_booking(b.getId(), gid);
	}
	
	@Override
	public ServiceResponse refuse_booking(long id) {
		return bookingGroupManager.refuse_booking(id);
	}

	@Override
	public ServiceResponse confirm_booking(long id) {
		Booking b = bookingDao.findBookingById(id);
		int gid = b.getGroup().getId();
		ServiceResponse sr = new ServiceResponse();
		//Ken Chen 2013/04/07 TD#129 秒杀确认只可以确认当天的
//		if(b.getBook_type()==Info.BOOKING_TYPE_SECKILL)
//		{
//			Calendar realCalendar = Calendar.getInstance();
//			if(!TimeFormater.format2(b.getBooking_time()).equals(TimeFormater.format2(realCalendar)))
//			{
//				sr.setSucc(false);
//				sr.addMsg("确认失败。秒杀的订单，仅可确认当天的！");
//				return sr;
//			}
//		}
    	
		sr = bookingGroupManager.confirm_booking(b.getId(), gid);
		
		if (sr.isSucc())
		{
			//Kevin Zhong - Uncomment the confirmation email
			//Ken chen 2012/12/20 Add E-Ticket info
			Booking booking = (Booking)sr.getResponse();	
			String content = ""+
			"亲爱的游客"+ booking.genFullname()+",\n\n"+
			"很高兴通知您，您的订单（"+booking.getBookingRef()+"）已确认！"+"\n"+
			"您的电子票（"+booking.getTicket_auth()+")！"+"\n"+
			"具体出团信息（接车时间地点，导游信息等），将在出团前一天，由导游发送到您报名时提供的电话号码中，如有电话更改，请及时联系海诺旅游办公室，电话：03339009888."+"\n\n"+
			"感谢您选择海诺旅游，祝您旅途愉快！\n"+
			"旅途中有任何问题，请您不吝联系我们，海诺旅游一定尽力为您解决问题，保证您的旅行质量！\n\n"+
			"海诺旅游 \n\n"+
			"Dear"+ booking.genFullname()+",\n\n"+
			"Your booking ("+booking.getBookingRef()+") has been confirmed successfully!"+"\n"+
			"Your Electronic Ticket（"+booking.getTicket_auth()+")！"+"\n"+
			"Your tour guide will send you a message with departure time one day before your departure date."+"\n\n"+
			"If you have any questions, please contact Hino office 03339009888.\n"+
			"Thank you very much！\n\n"+
			"Best Regards, \n" +
			"Bessie";
		
			try {
				emailUtil.send(booking.getEmail(), Info.EMAIL_TO, 
						"[Hino Travel]Booking confirmation information",
						content,
						null, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		return sr;
	}
	
	@Override
	public ServiceResponse view_booking(long id) {
		ServiceResponse sr = new ServiceResponse();
		Booking b = bookingDao.findBookingById(id);
		
		if(b==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target booking was no longer avaliable!");
			return sr;
		} else
		{
			sr.setSucc(true);
			sr.setResponse(b);
			return sr;
		}
	}

	@Override
	public List<Booking> bookingSearch(int attrType, String keyword, int sid,
			Integer[] status, int size, int page) {
		
		if (attrType==1)
		{
			try{
				Integer.parseInt(keyword);
			} catch (NumberFormatException nfe)
			{
				return new ArrayList<Booking>();
			} 
		}
		
		if(attrType==10)
		{
			Staff s = staffDao.findbyStaffno(keyword);
			if(s==null)
			{
				return new ArrayList<Booking>();
			} else
			{
				return bookingDao.complexSearching(-1, keyword, s.getId(), status, size, page, null, null);
			}
			
		}
		
		if(attrType==11)
		{
			return bookingDao.complexSearching(-1, keyword, -1, status, size, page, null, null);
		}
		
		if(attrType==12)
		{
			return bookingDao.complexSearching(-1, keyword, sid, new Integer[]{0}, -1, -1, null, null);
		}
		return bookingDao.complexSearching(attrType, keyword, sid, status, size, page, null, null);
	}

	@Override
	public int bookingSearchCount(int attrType, String keyword, int sid,
			Integer[] status) {
		if(attrType==10)
		{
			Staff s = staffDao.findbyStaffno(keyword);
			if(s==null)
			{
				return 0;
			} else
			{
				return (int)bookingDao.complexSearchingCount(-1, keyword, s.getId(), status);
			}
			
		}
		if (attrType==1)
		{
			try{
				Integer.parseInt(keyword);
			} catch (NumberFormatException nfe)
			{
				return 0;
			} 
			
		}
		
		if(attrType==11)
		{
			return (int)bookingDao.complexSearchingCount(-1, keyword, -1, status);
		}
		return (int)bookingDao.complexSearchingCount(attrType, keyword, sid, status);
	}

	//transactional
	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse transfer_declare(List<Long> bl, Staff s, Calendar tt, Calendar dt, double amount, String ref, String tranm, String payment) {
		ServiceResponse sr = new ServiceResponse();
		Booking b;
		List<Booking> abl = new ArrayList<Booking>();
		for (int i=0;i<bl.size();i++)
		{
			b = bookingDao.getBookingById(bl.get(i));
			if(b==null)
			{
				sr.setSucc(false);
				return sr;
			} else if(b.getStatus()!=Info.BKS_RESERVING)
			{
				sr.setSucc(false);
				return sr;
			} else
			{
				abl.add(b);
			}
		}
		
		Transfer t = new Transfer();
		t.setComment(ref);
		t.setDec_time(dt);
		t.setTrans_time(tt);
		t.setTrans_method(tranm);
		t.setRep(s);
		t.setP_amount(amount);
		t.setStatus(0);
		t.setPayment_method_id(Integer.parseInt(payment));
		
		t.setStatus(Info.TS_REVIEWING);
		transferDao.save(t);
		for (int i=0;i<abl.size();i++)
		{
			b = abl.get(i);
			b.setStatus(Info.BKS_TRANSFERING);
			b.setTransfer(t);
		}
		
		sr.setSucc(true);
		return sr;
	}

	@Override
	public List<Transfer> transferSearch(int type,Object crit, Integer[] status, long pid, int page, int size) {
		switch (type)
		{
			case -1:
				return new ArrayList<Transfer>();
			case 0: 
				return transferDao.complexSearch(null, -1, -1, status, page, size, -1, pid);
			case 1: 
				return transferDao.complexSearch(null, -1, -1, status, page, size, (Long)crit, pid);
			case 2:
				return transferDao.complexSearch(null, (Integer)crit, -1, status, page, size, -1, pid);
			default: 
			break;
		}
		return transferDao.complexSearch(null, (Integer)crit, -1, status, page, size, -1, pid);
	}
	
	@Override
	public int transferSearchCount(int type, Object crit, Integer[] status, long pid) {
		switch (type)
		{
			case -1:
				return 0;
			case 0: 
				return (int)transferDao.complexSearchCount(null, -1, -1, status, -1, pid);
			case 1: 
				return (int)transferDao.complexSearchCount(null, -1, -1, status, (Long)crit, pid);
			case 2:
				return (int)transferDao.complexSearchCount(null, (Integer)crit, -1, status, -1, pid);
			default: 
			break;
		}
		return (int)transferDao.complexSearchCount(null, (Integer)crit, -1, status, -1, pid);
	}

	@Override
	public ServiceResponse transfer_confirm(long tid, String feedback) {
		ServiceResponse sr = new ServiceResponse();
		ServiceResponse sr2 = new ServiceResponse();
		Transfer t = transferDao.findTransferById(tid);
		if(t==null||t.getStatus()!=Info.TS_REVIEWING)
		{
			sr.setSucc(false);
			sr.addMsg("只能确认审核中的申报！");
			return sr;
		}
		
		List<Booking> bl = bookingDao.findBookingListByTid(t.getId());

		for (int i=0;i<bl.size();i++)
		{
			Booking b = bl.get(i);
			long bid = b.getId();
			int gid = b.getGroup().getId();
			
			sr2 = bookingGroupManager.confirm_booking(bid, gid);
			if(!sr2.isSucc())
			{
				sr.addMsg(sr2.list_msg());
			}
		}
		t.setStatus(Info.TS_PASSED);
		t.setFeedback(feedback);
		transferDao.update(t);
		sr.setSucc(true);
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse transfer_delete(long tid, Staff s) {
		ServiceResponse sr = new ServiceResponse();
		Transfer t = transferDao.getTransferById(tid);
		if(t==null||t.getStatus()==Info.TS_PASSED)
		{
			sr.setSucc(false);
			sr.addMsg("不能删除已审核的申报！");
			return sr;
		}
		
		if(s==null)
		{
			sr.setSucc(false);
			sr.addMsg("无权执行该操作！");
			return sr;
		}
		
		if(PriviledgeParser.has_priviledge(s.getPriviledge(),PriviledgeParser.FINIANCE)||s.getId()==t.getRep().getId())
		{
			List<Booking> bl = bookingDao.findBookingListByTid(t.getId());
			Booking b;
			for (int i=0;i<bl.size();i++)
			{
				b = bookingDao.getBookingById(bl.get(i).getId());
				if(b.getStatus()==Info.BKS_TRANSFERING)
				{
					b.setStatus(Info.BKS_RESERVING);
					
				} else
				{
					sr.addMsg("警告：订单状态已经更改 -" + b.getBookingRef());
				}
				b.setTransfer(null);
			}
		} else
		{
			sr.setSucc(false);
			sr.addMsg("无权执行该操作！");
			return sr;
		}
		transferDao.delete(t);
		sr.setSucc(true);
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse transfer_refuse(long tid, Staff s, String feedback) {
		ServiceResponse sr = new ServiceResponse();
		Transfer t = transferDao.getTransferById(tid);
		if(t==null||t.getStatus()!=Info.TS_REVIEWING)
		{
			sr.setSucc(false);
			sr.addMsg("只能退回审核中的申报！");
			return sr;
		}
		
		if(s==null)
		{
			sr.setSucc(false);
			sr.addMsg("无权执行该操作！");
			return sr;
		}
		
		t.setStatus(Info.TS_FAILED);
		t.setFeedback(feedback);
		sr.setSucc(true);
		return sr;
	}

	@Override
	public ServiceResponse transfer_booking_view(long tid, Staff s) {
		ServiceResponse sr = new ServiceResponse();
		Transfer t = transferDao.findTransferById(tid);
		if(t==null)
		{
			sr.setSucc(false);
			return sr;
		}
		
		if(s==null)
		{
			sr.setSucc(false);
			sr.addMsg("无权执行该操作！");
			return sr;
		}
		
		if(PriviledgeParser.has_priviledge(s.getPriviledge(),PriviledgeParser.FINIANCE)||s.getId()==t.getRep().getId())
		{
			List<Booking> bl = bookingDao.findBookingListByTid(t.getId());
			sr.setResponse(bl);
		} else
		{
			sr.setSucc(false);
			sr.addMsg("无权执行该操作！");
			return sr;
		}
		
		sr.setSucc(true);
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void transfer_feedback(long tid, String fb) {
		Transfer t = transferDao.getTransferById(tid);
		if (t!=null)
		{
			t.setFeedback(fb);
		}
		
	}

	@Override
	public List<Group> profit_group_list(Integer[] status, Boolean[] ready,
			int page, int size) {
		return groupDao.list_group_status_profit(status, ready, page, size);
	}

	@Override
	public int profit_group_list_count(Integer[] status, Boolean[] ready) {
		return (int)groupDao.list_group_status_profit_count(status, ready);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public boolean profit_finish_setting(int gid) {
		Group g = groupDao.getGroupById(gid);
		if (g==null)
		{
			return false;
		} else
		{
			g.setProfit_ready(true);
			return true;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public boolean profit_save(int gid, GroupProfit gp) {
		Group g = groupDao.getGroupById(gid);
		if(g==null)
		{
			return false;
		} else
		{
			GroupProfit ogp = g.getProfit();
			gp.setLast_modify(Calendar.getInstance());
			if(ogp==null)
			{
				gp.setId(gid);
				groupProfitDao.save(gp);
				g.setProfit(gp);
			} else
			{
				
				ogp.setBus_exp(gp.getBus_exp());
				ogp.setComments(gp.getComments());
				ogp.setDriver_exp(gp.getDriver_exp());
				ogp.setFood_exp(gp.getFood_exp());
				ogp.setGift_exp(gp.getGift_exp());
				ogp.setGuide_exp(gp.getGuide_exp());
				ogp.setHotel_exp(gp.getHotel_exp());
				ogp.setLast_modify(gp.getLast_modify());
				ogp.setOthers_exp(gp.getOthers_exp());
				ogp.setPlane_exp(gp.getPlane_exp());
				ogp.setPoint_exp(gp.getPoint_exp());
				ogp.setPoint_in(gp.getPoint_in());
				ogp.setRep_order(gp.getRep_order());
				ogp.setReward_rate(gp.getReward_rate());
				ogp.setReward_type(gp.getReward_type());
				ogp.setSales_exp(gp.getSales_exp());
				ogp.setSales_in(gp.getSales_in());
				ogp.setSales_total(gp.getSales_total());
				ogp.setShip_exp(gp.getShip_exp());
				ogp.setTips_in(gp.getTips_in());
				ogp.setTotal_exp(gp.getTotal_exp());
				ogp.setTotal_in(gp.getTotal_in());
				ogp.setTotal_order(gp.getTotal_order());
				ogp.setTotal_profit(gp.getTotal_profit());
				
				//groupProfitDao.update(gp);
			}
		}
		return true;
	}

	@Override
	public FinancialProfitFeedbackDto profit_reload_sales(int gid) {
		List<Booking> bl = bookingDao.findBookingListByGidAndStatus(gid, Info.BKS_CONFIRMED);
		Booking b;
		double sales_in = 0;
		double t_sales_in = 0;
		int point_in = 0;
		int point_exp = 0;
		int rep_order = 0;
		int total_order = 0;
		for(int i=0;i<bl.size();i++)
		{
			b = bl.get(i);
			t_sales_in+=b.getPd_credit();
			point_in+=b.getPd_point();
			point_exp+=b.genProducePoint();
			if(b.getRep()!=null)
			{
				sales_in+=b.getPd_credit();
				rep_order ++;
			}
			total_order++;
		}
		FinancialProfitFeedbackDto fpfdto = new FinancialProfitFeedbackDto();
		fpfdto.setPoint_exp(point_exp);
		fpfdto.setPoint_in(point_in);
		fpfdto.setSales_in(sales_in);
		fpfdto.setT_sales_in(t_sales_in);
		fpfdto.setRep_order(rep_order);
		fpfdto.setTotal_order(total_order);
		return fpfdto;
	}


	@Override
	public List<SalesRewardDto> sales_summary(Calendar start, Calendar end) {
		List<Integer> lp = new ArrayList<Integer>();
		lp.add(PriviledgeParser.SALES_REP);
		List<Staff> staffs = staffDao.findbyPriviledge(lp);
		List<SalesRewardDto> ls = new ArrayList<SalesRewardDto>();
		
		SalesRewardDto srdto;
		for (Staff s : staffs)
		{
			
			srdto = new SalesRewardDto();
			double sales_total = 0;
			double sales_reward = 0;
			List<Booking> sb = bookingDao.findBookingListForReward(Info.BKS_CONFIRMED, s.getId(), start, end);
			for(Booking b : sb)
			{
				sales_total += b.getPd_credit();
				sales_reward += b.genReward();
				
			}
			
			srdto.setFullname(s.getChinesename()+"(" + s.genFullName()+")");
			srdto.setRep_order(sb.size());
			srdto.setStaffno(s.getStaffno());
			srdto.setSales_total(sales_total);
			srdto.setSales_reward(sales_reward);
			
			ls.add(srdto);
		}
		
		
		return ls;
	}

	@Override
	public List<SalesRewardDto> sales_summary_forarea(int gid, int areaid) {
		List<Integer> lp = new ArrayList<Integer>();
		lp.add(PriviledgeParser.SALES_REP);
		List<Staff> staffs = staffDao.findbyPriviledge(lp);
		List<SalesRewardDto> ls = new ArrayList<SalesRewardDto>();
		
		SalesRewardDto srdto;
		for (Staff s : staffs)
		{
			if(s.getAreaid()!=areaid)
			{
				continue;
			} else
			{
				srdto = new SalesRewardDto();
				double sales_total = 0;
				double sales_reward = 0;
				List<Booking> sb = bookingDao.findBookingListForReward(Info.BKS_CONFIRMED, s.getId(), gid);
				for(Booking b : sb)
				{
					sales_total += b.getPd_credit();
					sales_reward += b.genReward();
					
				}
				
				srdto.setFullname(s.getChinesename()+"(" + s.genFullName()+")");
				srdto.setRep_order(sb.size());
				srdto.setStaffno(s.getStaffno());
				srdto.setSales_total(sales_total);
				srdto.setSales_reward(sales_reward);
			}
			
			
			ls.add(srdto);
		}
		
		
		return ls;
	}
	
	@Override
	public List<SalesRewardDto> sales_summary_forLeader(int leaderId, Calendar start, Calendar end) {
	
		List<SalesRewardDto> ls = new ArrayList<SalesRewardDto>();
		Staff leader = staffDao.find(leaderId);
		if(leader != null)
		{
			if(PriviledgeParser.has_priviledge(leader.getPriviledge(), PriviledgeParser.AREA_REP)){
				AreaBranch area = areaBranchDao.getAreaBranchById(leader.getAreaid());
				if(area.getLeader().getId()==leaderId)
				{
					List<Staff> staffs = area.getFollows();
					SalesRewardDto srdto;
					for (Staff s : staffs)
					{
						srdto = new SalesRewardDto();
						double sales_total = 0;
						double sales_reward = 0;
						List<Booking> sb = bookingDao.findBookingListForReward(Info.BKS_CONFIRMED, s.getId(), start,  end );
						for(Booking b : sb)
						{
							sales_total += b.getPd_credit();
							sales_reward += b.genReward();
							
						}
						
						srdto.setFullname(s.getChinesename()+"(" + s.genFullName()+")");
						srdto.setRep_order(sb.size());
						srdto.setStaffno(s.getStaffno());
						srdto.setSales_total(sales_total);
						srdto.setSales_reward(sales_reward);

						ls.add(srdto);
					}
				}
            }

		}

		return ls;
	}


	@Override
	public long count_list_vip_order(String email, Integer[] status) {
		return vipOrderDao.count_order(status, email);
	}

	@Override
	public List<VipOrder> list_vip_order(String email, Integer[] status, int page, int size) {
		return vipOrderDao.list_by_paging(status, page, size, email);
	}

	@Override
	public ServiceResponse vip_order_confirm(long vid) {
		return vipOrderManager.confirm(vid);
	}

	@Override
	public ServiceResponse vip_order_delete(long vid) {
		return vipOrderManager.delete(vid);
	}

	@Override
	public void vip_set_paid(long vid) {
		vipOrderManager.setPaid(vid);
		
	}

	@Override
	public ServiceResponse modify_booking_price(long id, double price, Staff s) {
		ServiceResponse sr = new ServiceResponse();
		if (PriviledgeParser.has_priviledge(s.getPriviledge(),PriviledgeParser.FINIANCE))
		{
			return bookingGroupManager.modify_booking_price(id, price);
		} else 
		{
			sr.addMsg("only financae staff able to change");
		}
		return sr;
	}

	

}
