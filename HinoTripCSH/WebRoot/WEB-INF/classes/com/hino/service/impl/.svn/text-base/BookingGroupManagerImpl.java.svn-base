package com.hino.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hino.dao.BookingDAO;
import com.hino.dao.CustomerDAO;
import com.hino.dao.GroupDAO;
import com.hino.dao.StaffDAO;
import com.hino.dao.VoucherDAO;
import com.hino.dto.BookingReserveDto;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.Voucher;
import com.hino.dto.AddressDto;
import com.hino.service.BookingGroupManager;
import com.hino.service.CustomerService;
import com.hino.service.VoucherGroupManager;
import com.hino.util.Info;
import com.hino.util.RandomGenerator;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class BookingGroupManagerImpl implements BookingGroupManager {
	private GroupDAO groupDao;
	private BookingDAO bookingDao;
	private CustomerDAO customerDao;
	private StaffDAO staffDao;
	private VoucherDAO voucherDao;
	private VoucherGroupManager voucherGroupManager;
	private CustomerService customerService;
	
	
	public VoucherGroupManager getVoucherGroupManager() {
		return voucherGroupManager;
	}

	public void setVoucherGroupManager(VoucherGroupManager voucherGroupManager) {
		this.voucherGroupManager = voucherGroupManager;
	}

	public VoucherDAO getVoucherDao() {
		return voucherDao;
	}

	public void setVoucherDao(VoucherDAO voucherDao) {
		this.voucherDao = voucherDao;
	}

	public StaffDAO getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDAO staffDao) {
		this.staffDao = staffDao;
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

	public CustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse cancel_booking(long bid, int gid) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(gid);
		if(g==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target group was no longer avaliable!");
			return sr;
		}
		
		Booking b = bookingDao.getBookingById(bid);
		if(b==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target booking was no longer avaliable!");
			return sr;
		}
	
		if(b.getStatus()==Info.BKS_CONFIRMED)
		{

			if(b.getBook_type()==0)
			{
				g.booking_cancel(1);
			} else if(b.getBook_type()==1)
			{
				g.go_booking_cancel(1);
			} else if (b.getBook_type()==2)
			{
				g.sk_booking_cancel(1);
			}

			b.setStatus(Info.BKS_CANCELED);
			b.setRefund(false);
			b.setTicket_auth(null);

			Customer c = b.getReferer();
			if(c!=null)
			{
				if (c.withdraw(b.genProducePoint()))
				{
					sr.setSucc(true);
				} else 
				{
					sr.setSucc(false);
					sr.addMsg("Booking has been canceled but point can not be retrieved!");
				}
				c.deposit(b.getPd_point());
			}
			
			//Devon King - recycle voucher
			Voucher v = null;
			String vs = b.getVoucher();
			if(vs != null && !vs.equals(""))
			{
			   v = voucherDao.getVoucherByCode(vs);
			   if(v.getNo_used() - 1 >= 0) {
	               v.setNo_used(v.getNo_used() - 1);
	               voucherDao.update(v);
			   }
			}
			
			//TODO send email
			return sr;
		} else
		{
			sr.setSucc(false);
			sr.addMsg("Only confirmed booking could be canceled!");
			return sr;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse confirm_booking(long bid, int gid) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(gid);
		if(g==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target group was no longer avaliable!");
			return sr;
		}
		
		Booking b = bookingDao.getBookingById(bid);
		if(b==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target booking was no longer avaliable!");
			return sr;
		}
		
		if(b.getStatus()!=Info.BKS_RESERVING&&b.getStatus()!=Info.BKS_TRANSFERING)
		{
			sr.setSucc(false);
			sr.addMsg("Only reserving or transfering booking could be confirmed");
			return sr;
		}
		
		if(b.getBook_type()==0&&g.getSeats_taken()>=g.getSeats())
		{
			sr.setSucc(false);
			sr.addMsg("No space in this group, confirmation failed!");
			return sr;
		}
		
		if(b.getBook_type()==1&&g.getSeats_taken_groupon()>=g.getSeats_groupon())
		{
			sr.setSucc(false);
			sr.addMsg("No space in this group, confirmation failed!");
			return sr;
		}
		
		if(b.getBook_type()==2&&g.getSeats_taken_seckill()>=g.getSeats_seckill())
		{
			sr.setSucc(false);
			sr.addMsg("No space in this group, confirmation failed!");
			return sr;
		}
		

		Customer c = b.getCustomer();
		if(c!=null)
		{
			if (c.withdraw(b.getPd_point()))
			{
				
			} else 
			{
				sr.setSucc(false);
				sr.addMsg("Customer do not have enough point as stated in booking!");
				return sr;
			}
		}
		
		
		//check voucher
		Voucher v = null;
		String vs = b.getVoucher();
		if(vs!=null&&!vs.equals(""))
		{
			v=voucherDao.getVoucherByCode(vs);
            ServiceResponse sr2 = voucherGroupManager.check_voucher(g, v);
           if (!sr2.isSucc())
           {
        	   sr.setSucc(false);
   			   sr.addMsg("voucher invalid! The failed reason: " + sr2.getMsg_list().get(0));
   			   return sr;
           } else
           {
        	   //Devon King - use voucher at the time of request a booking 
        	   //voucherGroupManager.use_voucher(v);
           }
		}
		
		//All fine then book
		
		if(b.getBook_type()==0)
		{
			g.booking_confirm(1);
		} else if(b.getBook_type()==1)
		{
			g.go_booking_confirm(1);
		} else if (b.getBook_type()==2)
		{
			g.sk_booking_confirm(1);
		}
		
		Customer r = b.getReferer();
		if(r!=null)
		{
			r.deposit(b.genProducePoint());
		}
		
		b.setStatus(Info.BKS_CONFIRMED);
		b.genTicketAuthCode();
		sr.setResponse(b);
		sr.setSucc(true);
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse delete_booking(long bid, int gid) {
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.getGroupById(gid);
		if(g==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target group was no longer avaliable!");
			return sr;
		}
		
		Booking b = bookingDao.getBookingById(bid);
		
		if(b==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target booking was no longer avaliable!");
			return sr;
		}
		
		if(b.getStatus()==Info.BKS_RESERVING)
		{

			boolean seatsReduced = true;
			if(b.getBook_type()==0)
			{
				seatsReduced = g.booking_delete(1);
			} else if(b.getBook_type()==1)
			{
				seatsReduced = g.go_booking_delete(1);
			} else if (b.getBook_type()==2)
			{
				seatsReduced = g.sk_booking_delete(1);
			}
			
			//Devon King - Has the reserved seats updated successful?
			if (!seatsReduced) {
				sr.setSucc(false);
				sr.addMsg("Failed to update reserved seats.");
				return sr;
			}
			
			//Devon King - recycle voucher
			Voucher v = null;
			String vs = b.getVoucher();
			if(vs != null && !vs.equals(""))
			{
			   v = voucherDao.getVoucherByCode(vs);
			   if(v.getNo_used() - 1 >= 0) {
	               v.setNo_used(v.getNo_used() - 1);
	               voucherDao.update(v);
			   }
			}
			
			bookingDao.delete(b);
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
	public ServiceResponse refuse_booking(long bid) {
		ServiceResponse sr = new ServiceResponse();
		Booking b = bookingDao.getBookingById(bid);
		
		if(b==null)
		{
			sr.setSucc(false);
			sr.addMsg("Target booking was no longer avaliable!");
			return sr;
		}
		
		if(b.getStatus()==Info.BKS_TRANSFERING)
		{
			b.setStatus(Info.BKS_RESERVING);
			sr.setSucc(true);
			return sr;
		} else
		{
			sr.setSucc(false);
			sr.addMsg("Only transfering booking could be refused!");
			return sr;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse reserve_booking(int gid, List<BookingReserveDto> brl, boolean isexternal, int sid, long cid, int bktype, AddressDto address) {
		ServiceResponse sr = new ServiceResponse();
		
		Group g = groupDao.getGroupById(gid);
		
		if(g==null)
		{
			sr.setSucc(false);
			sr.addMsg("Group doesn't exist.");
			return sr;
		}
		
		if(g.getStatus()!=Info.GS_OPENNING)
		{
			sr.setSucc(false);
			sr.addMsg("Group is not openning.");
			return sr;
		}
		
		if(brl==null)
		{
			sr.setSucc(false);
			sr.addMsg("No booking info.");
			return sr;
		}
		
		if(brl.size()==0)
		{
			sr.setSucc(false);
			sr.addMsg("Invalid booking size.");
			return sr;
		}
		
		int availableSeats = 0;
		if(bktype==0) { // normal
			availableSeats = g.getSeats() - g.getSeats_taken() - g.getSeats_reserved();
		} else if (bktype==1) { // group
			availableSeats = g.getSeats_groupon() - g.getSeats_taken_groupon() - g.getSeats_reserved_groupon();			
		} else if(bktype==2) { // second kill
			availableSeats = g.getSeats_seckill() - g.getSeats_taken_seckill() - g.getSeats_reserved_seckill();			
		}
		
		if(brl.size() > availableSeats) {
			sr.setSucc(false);
			sr.addMsg("No enough seats for the booking - only " + availableSeats + " seats are available for the time being!");
			return sr;
		}
		
		//Customer c = customerDao.getCustomerById(cid);
		
		//if all those validation is passed then reserve space
		
		//CustomerBookingDto sbdto1;
		BookingReserveDto brdto;
		
		String curRef = randomnBookingRef();
		
		//need deep testing 21/11/2011 
		// TODO needs to amend for the unregister user
		if(isexternal)
		{
			//ken chen 29/11/2/12 支持非注册用户直接参团
			if(Info.INVAILD_LONG_VALUE==cid)
			{
				//TODO 非注册用户	
				
			}
			else
			{
				if(bookingDao.checkBooked(gid, cid))
				{
					sr.setSucc(false);
					sr.addMsg("You've already reserved with this group.");
					return sr;
				}
			}
		}
		
		Staff s = staffDao.find(sid);
		
		for(int i=0;i<brl.size();i++)
		{
			Booking b = new Booking();
			brdto = brl.get(i);
			Customer c=null;
			if(isexternal)
			{
				c = customerDao.find(cid);
				if(c==null)
				{
					//TODO check 非注册用户，需要的信息
					if(bookingDao.checkBooked(gid, brdto.getEmail()))
					{
						sr.setSucc(false);
						sr.addMsg("You've already reserved with this group.");
						return sr;
					}
					
					// ken chen 2012/12/01 auto register custome
					String email = brdto.getEmail();
	            	String fn = brdto.getFn();
	            	String ln = brdto.getLn();
	            	String cn = brdto.getCn();
	            	String ph = brdto.getPhone();
	            	String uni = null;
	            	String city = null;
	            	int gender = brdto.getGender();
	            	String pass1 = RandomGenerator.randomString(8);
	            	
					getCustomerService().registeration(email, fn, ln, pass1);
					getCustomerService().update_customer_basic_2(email, uni, ph, city, cn, gender);
					
					c = customerDao.findCustomerByEmail(brdto.getEmail());
					if(c==null)
					{
						sr.setSucc(false);
						sr.addMsg("Automatic register customer error.");
						return sr;
					}
					
					//使用注册是送的积分
					if(c.getPoint()>=g.getMax_point())
					{
						brdto.setPd_point(g.getMax_point());
						c.withdraw(g.getMax_point());
					}
					else
					{
						brdto.setPd_point(c.getPoint());
						c.withdraw(c.getPoint());
					}	

				}
				else
				{
					b.setReferer(c);
				}
			} else
			{
				c = customerDao.findCustomerByEmail(brdto.getEmail());
				if(s!=null)
				{
					b.setRep(s);
				}
			}
			
			if(address != null && address.isPost()) {
				c.setAddress(address.genAddress());
				c.setCity(address.getCity());
				c.setPostcode(address.getPostcode());
				customerDao.update(c);
			}
			
			b.setBooking_method(brdto.getPaymethod());
			b.setBooking_time(Calendar.getInstance());
			b.setEmail(brdto.getEmail());
			b.setBookingRef(curRef+"P"+i);
			b.setChinesename(brdto.getCn());
			b.setFirstname(brdto.getFn());
			b.setLastname(brdto.getLn());
			b.setStatus(Info.BKS_RESERVING);
			b.setRoom(brdto.getRoom());
			b.setPickup(brdto.getPickup());
			b.setGender(brdto.getGender());
			b.setPhone(brdto.getPhone());
            b.setVoucher(brdto.getVoucher());
			b.setGroup(g);
			sr.addMsg(b.getBookingRef());
			
			
			//Ken Chen 2014/04/14 TD#137 route_detail booking bktype = 0 
			//ToDo 有些团购建立的路线不对，是建立在常规团上的（ gid in(1530), route_id in (14,87)）
			if(g.genGroupTypeCode()==Info.ROUTE_TYPE_CZTG)
			{
				if(bktype ==0)
				{
					//如果是团购的话，需要设置正确的booking类型
					bktype = 1;
				}
			}
			b.setBook_type(bktype);
			//check voucher
			Voucher v = null;
			double v_value=0;
			String vs = b.getVoucher();
			if(vs!=null&&!vs.equals(""))
			{
			   v=voucherDao.getVoucherByCode(vs);
               ServiceResponse sr2 = voucherGroupManager.check_voucher(g, v);
               if (!sr2.isSucc())
               {
            	   //System.out.println("Reserving failed: voucher not applicateble");
               } else
               {
            	   v_value=v.getValue();
               }
			}
			
			b.setPd_voucher(v_value);
			b.setCustomer(c);
			
			//normal
			if(b.getBook_type()==0)
			{
				
				if(c!=null&&c.genIsVip())
				{	
					//external primary has vip price
					if(i!=0&&isexternal)
					{
						b.setPd_credit(g.getPrice()-brdto.getPd_point()/100-v_value);
					} else
					{
						b.setPd_credit(g.getVip_price()-brdto.getPd_point()/100-v_value);
					}
				} else
				{
					b.setPd_credit(g.getPrice()-brdto.getPd_point()/100 - v_value);
				}
				System.out.println("000000000000000000000000paid value:" + b.getPd_credit());
			}
			else if(b.getBook_type()==1) //group on
			{
				if(c!=null&&c.genIsVip())
				{
					b.setPd_credit(g.getGroup_vip_price());
				} else
				{
					b.setPd_credit(g.getGroup_price());
				}
				System.out.println("1111111111111111111111111paid value:" + b.getPd_credit());
			}
			else if(b.getBook_type()==2) //second skill
			{
				if(c!=null&&c.genIsVip())
				{
					b.setPd_credit(g.getSeckill_vip_price());
				} else
				{
					b.setPd_credit(g.getSeckill_price());
				}
				System.out.println("2222222222222222222222222paid value:" + b.getPd_credit());
			}
					
			//b.setPd_credit(g.getVip_price()+g.getPrice()*(brl.size()-1)-brdto.getPoint()/100);
			
			if(brdto.getPd_point()>g.getMax_point())
			{
				sr.addMsg("Point usage exceeds max usage!");
				sr.setSucc(false);
				return sr;
			}
			b.setPd_point(brdto.getPd_point());
			b.setPd_voucher(v_value);
			sr.setResponse(b);
			
			bookingDao.save(b);
			
			//Devon - Use voucher at booking request stage 
			if (null != v) {
				v.setNo_used(v.getNo_used() + 1);
				voucherDao.update(v);
			}
		}
		
		boolean success = false;
		if(bktype==0)
		{
			success = g.booking_reserve(brl.size());
		} else if (bktype==1)
		{
			success = g.go_booking_reserve(brl.size());
		} else if(bktype==2)
		{
			success = g.sk_booking_reserve(brl.size());
		}
		if(!success)
		{
			sr.addMsg("Modify group reserved seats error!");
			sr.setSucc(false);
			return sr;
		}
		
		sr.setSucc(true);
		return sr;
	}
	
	private String randomnBookingRef()
	{
		String str = TimeFormater.format3(Calendar.getInstance());
		return str+RandomGenerator.randomnNumberString(4);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse modify_booking_price(long bid, double newpd) {
		ServiceResponse sr = new ServiceResponse();
		Booking b = bookingDao.getBookingById(bid);
		if (b!=null&&b.getStatus()==Info.BKS_RESERVING)
		{
			b.setPd_credit(newpd);
			sr.setSucc(true);
		}else
		{
			sr.addMsg("unable to change pd credit for this booking check status!");
		}
		return sr;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public ServiceResponse repay_booking(int gid, String email,
			BookingReserveDto brl) {
ServiceResponse sr = new ServiceResponse();
		
		Group g = groupDao.getGroupById(gid);
		
		if(g==null)
		{
			sr.setSucc(false);
			sr.addMsg("Group does not exist.");
			return sr;
		}
		
		if(brl==null)
		{
			sr.setSucc(false);
			sr.addMsg("No booking info.");
			return sr;
		}


		BookingReserveDto brdto = brl;
		
		String curRef = randomnBookingRef();
		Booking b = new Booking();
		Customer c=null;
		
		c = customerDao.findCustomerByEmail(brdto.getEmail());
		
		if(c==null){
			sr.setSucc(false);
			sr.addMsg("Customer does not exist.");
			return sr;
		}
		
		b.setCustomer(c);
		b.setBooking_time(Calendar.getInstance());
		b.setEmail(brdto.getEmail());
		b.setBookingRef("1111"+ curRef);
		b.setChinesename(c.getChinesename());
		b.setPd_credit(brdto.getPd_credit());
		b.setStatus(Info.BKS_RESERVING);
		b.setRepay("Y");
		b.setGroup(g);
		sr.addMsg("链接为www.hinotravel.com/zh/repay.htm?ref=" + b.getBookingRef());

		sr.setResponse(b);
		
		bookingDao.save(b);
		
		sr.setSucc(true);
		return sr;
	
	}

}
