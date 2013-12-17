package com.hino.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hino.dao.BookingDAO;
import com.hino.dao.CarRequestDAO;
import com.hino.dao.CustomerDAO;
import com.hino.dao.CustomerHistoryDAO;
import com.hino.dao.GroupDAO;
import com.hino.dao.TourSurveyDAO;
import com.hino.dao.VipOrderDAO;
import com.hino.dao.impl.CustomerHistoryDAOImpl;
import com.hino.dto.AddressDto;
import com.hino.dto.BookingReserveDto;
import com.hino.dto.CarRequestDto;
import com.hino.dto.CustomerBasicInfoDto;
import com.hino.dto.CustomerBookingDto;
import com.hino.dto.CustomerEmailCAMDto;
import com.hino.dto.SalesBookingDto1;
import com.hino.dto.VipOrderReserveDto;
import com.hino.model.Booking;
import com.hino.model.CarRequest;
import com.hino.model.Customer;
import com.hino.model.CustomerHistory;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.model.TourSurvey;
import com.hino.model.VipOrder;
import com.hino.service.BookingGroupManager;
import com.hino.service.CustomerService;
import com.hino.service.VipOrderManager;
import com.hino.util.DataExporter;
import com.hino.util.EmailUtil;
import com.hino.util.EscapeHtml;
import com.hino.util.FileCenter;
import com.hino.util.Info;
import com.hino.util.PriviledgeParser;
import com.hino.util.RandomGenerator;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDAO customerDao;
	private BookingDAO bookingDao;
	private GroupDAO groupDao;
	private FileCenter fileCenter;
	private EmailUtil emailUtil;
	private VipOrderManager vipOrderManager;
	private BookingGroupManager bookingGroupManager;
	private VipOrderDAO vipOrderDao;
	private CarRequestDAO carRequestDao;
	private DataExporter de;
	private TourSurveyDAO tourSurveyDao;
	private CustomerHistoryDAO customerHistoryDao;
	
	public TourSurveyDAO getTourSurveyDao() {
		return tourSurveyDao;
	}

	public void setTourSurveyDao(TourSurveyDAO tourSurveyDao) {
		this.tourSurveyDao = tourSurveyDao;
	}
	
	public CarRequestDAO getCarRequestDao() {
		return carRequestDao;
	}

	public void setCarRequestDao(CarRequestDAO carRequestDao) {
		this.carRequestDao = carRequestDao;
	}
	
	public DataExporter getDe() {
		return de;
	}

	public void setDe(DataExporter de) {
		this.de = de;
	}
	
	public VipOrderDAO getVipOrderDao() {
		return vipOrderDao;
	}

	public void setVipOrderDao(VipOrderDAO vipOrderDao) {
		this.vipOrderDao = vipOrderDao;
	}

	public VipOrderManager getVipOrderManager() {
		return vipOrderManager;
	}

	public void setVipOrderManager(VipOrderManager vipOrderManager) {
		this.vipOrderManager = vipOrderManager;
	}

	public BookingGroupManager getBookingGroupManager() {
		return bookingGroupManager;
	}

	public void setBookingGroupManager(BookingGroupManager bookingGroupManager) {
		this.bookingGroupManager = bookingGroupManager;
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

	public void setGroupDao(GroupDAO groupDao) {
		this.groupDao = groupDao;
	}

	public FileCenter getFileCenter() {
		return fileCenter;
	}

	public void setFileCenter(FileCenter fileCenter) {
		this.fileCenter = fileCenter;
	}

	public EmailUtil getEmailUtil() {
		return emailUtil;
	}

	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}

	public CustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse registeration(String email, String fn, String ln, String pwd) {
		ServiceResponse sr = new ServiceResponse();
		if (customerDao.findCustomerByEmail(email)==null)
		{	Customer c = new Customer();
			c.setEmail(email);
			c.setFirstname(fn);
			c.setLastname(ln);
			c.setPassword(pwd); //TODO md5?
			c.setReg_time(Calendar.getInstance());
			c.setLast_login_time(Calendar.getInstance());
			c.setPoint(500); //default point 
			customerDao.save(c);
			//+Kevin Zhong - 23/09/2012 send a confirmation email
			try{
				String title = null;
				String content = null;
				title = "欢迎您加入海诺旅游";
				content = "亲爱的"+ c.genFullName()+":\n\n"+
						"欢迎您加入海诺旅游.\n"+
						"请您使用以下账户信息登录海诺旅游官网查询并报名您所喜爱的团次."+"\n"+
						"账户名称:"+ c.getEmail()+"\n"+
						"登录密码:"+ c.getPassword()+"\n\n"+
						"感谢您选择海诺旅游,期待与您同行！"+"\n"+
						"更多团次信息请登录海诺官网: http://www.hinotravel.com/zh/index.htm "+"\n"+
						"海诺旅游咨询群:"+"\n"+
						"诺丁汉新生旅游群: 227023501"+"\n"+
						"莱斯特新生旅游群: 252114483"+"\n"+
						"拉夫堡新生旅游群: 252115012"+"\n"+
						"伦    敦新生旅游群: 257848277"+"\n\n"+
						"Dear "+ c.genFullName()+":\n\n"+
						"Welcome to Hino Travel website. "+"\n"+
						"It is a pleasure to tell you that your account has been created successfully."+"\n"+
						"Please use the details below to login to and continue with your purchase."+"\n"+
						"Username:"+ c.getEmail()+"\n"+
						"Password:"+ c.getPassword()+"\n\n"+
						"Best wishes,"+"\n"+
						"Hino Travel"+"\n";
				emailUtil.send(c.getEmail(), Info.EMAIL_TO, title, content, null, null);
			}catch(Exception e){
				System.out.println("FAIL"+e.getMessage());
			}
			//-Kevin Zhong - 23/09/2012
			sr.setSucc(true);
			sr.setResponse(c);
		} else
		{
			sr.addInfo(Info.CUSTOMER_REG_FAILED_EMAIL_EXIST);
		}
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse reset_point(int point, long cid, long uid) {
		ServiceResponse sr = new ServiceResponse();
		Customer c = customerDao.getCustomerById(cid);
		CustomerHistory ch = new CustomerHistory();
		if (c==null)
		{
			sr.addMsg("target customer no longer avaliable!");
			sr.setSucc(false);
		} else 
		{
			customerHistoryDao.SaveCustomerHistory(c, ch);
			ch.setCreateUid(uid);
			
			c.setPoint(point);
			customerHistoryDao.save(ch);
			sr.setSucc(true);
		}
		return sr;
		
	}

	@Override
	public ServiceResponse getCustomerByEmail(String email) {
		ServiceResponse sr = new ServiceResponse();
		Customer c = customerDao.findCustomerByEmail(email);
		if (c==null)
		{
			sr.addInfo(Info.CUSTOMER_LOGIN_FAILED_USERNAME_NOT_FOUND);
		} else
		{
			sr.setSucc(true);
			sr.setResponse(c);
		}
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void reset_password(String password, long cid) {
		Customer c = customerDao.getCustomerById(cid);
		if(c==null)
		{
			
		} else
		{
			c.setPassword(password);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse resetAccountPassword(String originalPassword, String newPassword, long cid) {
		ServiceResponse sr = new ServiceResponse();
		Customer c = customerDao.getCustomerById(cid);
		if(c==null)
		{
			sr.addInfo(Info.CUSTOMER_LOGIN_FAILED_USERNAME_NOT_FOUND);
		} else {
			
			// Check if the original password is matching
			if (originalPassword.equals(c.getPassword())){
				c.setPassword(newPassword);
				
				sr.setSucc(true);
				sr.setResponse(c);
			}else{
				sr.addInfo(Info.CUSTOMER_LOGIN_FAILED_PASSWORD_NOT_MATCHED);
			}
		}
		return sr;
	}

	@Override
	public List<Customer> getPagedCustomerList(int page, int size) {
		return customerDao.list_customer_by_paging(page, size);
	}

	@Override
	public int getCustomerCount() {
		return customerDao.getCustomerCount();
	}

	@Override
	public ServiceResponse sendEmail(CustomerEmailCAMDto emaildto) {
		ServiceResponse sr = new ServiceResponse();		
		HashSet<String> customerEmails = new HashSet<String>(); 
		
		switch(emaildto.getCustomerfilter()) {
			case Info.CUSTOMER_GROUP:
				get_customer_email_group(customerEmails, emaildto.getGroupid()); break;
			case Info.CUSTOMER_ALL:
				get_customer_email_all(customerEmails); break;
			case Info.CUSTOMER_LAST_MONTH:
				get_customer_email_by_month(customerEmails, 1); break;
			case Info.CUSTOMER_LAST_THREEE_MONTH:
				get_customer_email_by_month(customerEmails, 3); break;
			case Info.CUSTOMER_LAST_SIX_MONTH:
				get_customer_email_by_month(customerEmails, 6); break;
			default:
				customerEmails = null;			
		}
		
		if (customerEmails == null || customerEmails.size() <= 0)
		{
			sr.setSucc(false);
			sr.addInfo(Info.CUSTOMER_EMAIL_NOT_FOUND);
		} else {
			// Upload attachment
			String attUrl = "";
			if (emaildto.getAttachment().getName().trim().compareTo("") != 0) {
				try {
					attUrl = fileCenter.save_file(emaildto.getAttachment(), true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			send_email(emaildto, customerEmails, attUrl);
			
			sr.setSucc(true);
		}
		return sr;
		
	}

	private void get_customer_email_by_month(HashSet<String> customerEmails, int month) {
		Calendar endTime = Calendar.getInstance();
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.MONTH, -month);
        
		List<Booking> allBooking = bookingDao.complexSearching(7, null, -1, new Integer[]{1}, -1, -1, startTime, endTime);
		for(Booking curBooking : allBooking) {
			customerEmails.add(curBooking.getCustomer().getEmail());
		}
	}

	private void get_customer_email_all(HashSet<String> customerEmails) {
		List<Customer> allCustomer = customerDao.list_all_customer();
		for(Customer curCustomer : allCustomer) {
			customerEmails.add(curCustomer.getEmail());
		}
	}

	private void get_customer_email_group(HashSet<String> customerEmails, int groupid) {
		List<Booking> allBooking = bookingDao.findBookingListByGid(groupid);
		for(Booking curBooking : allBooking) {
			customerEmails.add(curBooking.getEmail());
		}
	}

	private void send_email(CustomerEmailCAMDto medto, HashSet<String> customerEmails, String attUrl) {
		String toList = "";
		try {
		    for(String curEmail : customerEmails) {
		    	if(toList.compareTo("") != 0)
		    		toList = toList+",";
		    	toList = toList + curEmail;
		    }
		    
		    String filePath = new FileCenter().getProtectedDir()+attUrl;
		    
		    emailUtil.send(toList, Info.EMAIL_BC, 
		    		EscapeHtml.htmlDecode(medto.getTitle()),
		    		EscapeHtml.htmlDecode(medto.getContent()),
		    		filePath, attUrl);
		} catch (Exception e) {
			//System.out.println("FAIL"+e.getMessage());
		}
	}
	
	private void send_email(CustomerEmailCAMDto medto, HashSet<String> customerEmails, String[] attUrls) {
		String toList = "";
		try {
		    for(String curEmail : customerEmails) {
		    	if(toList.compareTo("") != 0)
		    		toList = toList+",";
		    	toList = toList + curEmail;
		    }
		    
		    String[] filePaths = new String[attUrls.length];
		    
		    for(int i = 0; i < attUrls.length; i ++) {
		    	filePaths[i] = new FileCenter().getProtectedDir() + attUrls[i];
		    }
		    
		    //String filePath = new FileCenter().getProtectedDir()+attUrl;
		    
		    emailUtil.sendWithMultiAtts(toList, Info.EMAIL_BC, 
		    		EscapeHtml.htmlDecode(medto.getTitle()),
		    		EscapeHtml.htmlDecode(medto.getContent()),
		    		filePaths, attUrls);
		} catch (Exception e) {
			//System.out.println("FAIL"+e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Group getGroupById(int id) {
		return groupDao.viewGroupById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Booking> getMyBookingList(long cid) {
		if(cid!=-1)
		{
			return bookingDao.findBookingListByCid(cid);
		}
		return new ArrayList<Booking>();
	}

	@Override
	public ServiceResponse external_booking(int gid, long cid, List<BookingReserveDto> cbd, int bktype, AddressDto address) {

		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.viewGroupById(gid);
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
		
		if(bktype==0)	//normal booking
		{
			if(!g.getExternalBookable())
			{
				sr.setSucc(false);
				sr.addMsg("Group is not bookable.");
				return sr;
			}
			
			//Devon King - 10/04/2013 - Check if seats have been sold out
			if(g.getSeats_taken() >= g.getSeats())
			{
				sr.setSucc(false);
				sr.addMsg("剩余票数已不足，请重新预订!");
				return sr;
			}
		} else if(bktype==1) //group on
		{
			if(!g.getExt_groupon())
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
			
			//Devon King - 10/04/2013 - Check if seats have been sold out
			if(g.getSeats_taken_groupon() >= g.getSeats_groupon())
			{
				sr.setSucc(false);
				sr.addMsg("剩余票数已不足，请重新预订!");
				return sr;
			}
		} else if(bktype==2) //second kill
		{
			if(!g.getExt_seckill())
			{
				sr.setSucc(false);
				sr.addMsg("Target group can't reserve internal group purchase!");
				return sr;
			}
			
			//check end time
			if(Calendar.getInstance().after(g.getSeckill_end_time())||Calendar.getInstance().before(g.getSeckill_start_time()))
			{
				sr.setSucc(false);
				sr.addMsg("Target second kill no available at this time!");
				return sr;
			}
			
			//Devon King - 10/04/2013 - Check if seats have been sold out
			if(g.getSeats_taken_seckill() >= g.getSeats_seckill())
			{
				sr.setSucc(false);
				sr.addMsg("剩余票数已不足，请重新预订!");
				return sr;
			}
		} else if(bktype==3){
			
		}
		else 
		{
			sr.setSucc(false);
			sr.addMsg("Error type of booking!");
			return sr;
		}
		
		
		sr = bookingGroupManager.reserve_booking(gid, cbd, true, -1, cid, bktype, address);
		if(!sr.isSucc())
		{
			return sr;
		}

		//+ Devon King - 2012/09/04 - Uncomment this to enable the booking mail function
		// Send booking information email
		Booking mainBooking = (Booking)sr.getResponse();
		String bookingMethod = null, bookingMethodEn = null;
		switch(mainBooking.getBooking_method()) {
		case Info.BKM_EXTERNAL_BANKTRANSFER:
			bookingMethod = "银行转账付款";
			bookingMethodEn = "Bank transfer";
			break;
		case Info.BKM_EXTERNAL_GOOGLECHECKOUT:
			bookingMethod = "在线支付";
			bookingMethodEn = "Online checkout";
			break;
		case Info.BKM_EXTERNAL_PAYATBRANCH:
			bookingMethod = "公司前台支付";
			bookingMethodEn = "Cash";
			break;
		case Info.BKM_EXTERNAL_BARCLAYCARD:
			bookingMethod = "Barclaycard";
			bookingMethodEn = "Barclaycard";
			break;
		}
		
		//Kevin Zhong - 14/11/2012 - dont send email when type is seckill
		if(bktype!=2){
			//Devon King - 09/11/2012 - TD#68 Format dates
			SimpleDateFormat zhDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
			SimpleDateFormat enDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			String content = ""+
			"尊敬的游客 "+ mainBooking.genFullname()+"\n"+
			"恭喜您！"+"\n"+
			"您已经成功预订了海诺旅游 "+mainBooking.getBookingRef() +" "+ zhDateFormat.format(mainBooking.getGroup().getDepart_date().getTime()) +" "+mainBooking.getGroup().getName()+
			"以下是给您的基本信息包括：\n"+
			"游客姓名:  "+mainBooking.genFullname()+"\n"+
			"游客性别:  "+mainBooking.genGenderStr()+"\n"+
			"接车点：  "+mainBooking.getPickup()+"\n"+
			"联系电话:  "+mainBooking.getPhone()+"\n"+
			"房间分配情况： "+mainBooking.getRoom()+"\n"+"\n"+
	
			"请仔细核对报名信息，若信息无误，请及时付款。\n\n"+
			"付款方式： \n"+
			"\t"+"1.通过银行转账付款"+"\n"+
			"\t"+ "    "+"银行转账付款信息如下："+"\n"+
			"\t"+ "    "+"银行账户:Hino Travel"+"\n"+
			"\t"+ "  "+"Sort code: 20-63-25"+"\n"+
			"\t"+ "  "+"Account number: 33250482"+"\n"+
			"\t"+ "    "+"请务必在reference中写清楚您的名字拼音。"+"\n"+
			"\t"+"2.联系当地销售代表缴纳现金"+"\n"+
			"\t"+"3.到海诺旅游办公室缴纳现金"+"\n"+
			"\t"+ "    "+"海诺办公室地址：Crystal One，Ramada Hotel，17-31 Wollaton Street，Nottingham NG1 5FW."+"\n"+"\n"+
			"付款之后请发送邮件到：info@hinotravel.com 写清您的付款金额和银行转账reference内容。"+"\n"+"\n"+
			"如有任何问题请拨打03339009888咨询。\n" +
			"感谢您选择海诺旅游，祝您旅途愉快！"+"\n"+"\n"+
			"海诺旅游"+"\n"+
					
			"Dear "+ mainBooking.genFullname()+"\n"+
			"You have booked Hino Travel "+mainBooking.getBookingRef() +" "+ enDateFormat.format(mainBooking.getGroup().getDepart_date().getTime()) +" "+mainBooking.getGroup().getName()+
			"\n" + 
			"Name: "+mainBooking.genFullname()+"\n"+
			"Gender: "+mainBooking.genGenderStr()+"\n"+
			"Pick-up point: "+mainBooking.getPickup()+"\n"+
			"Contact no.: "+mainBooking.getPhone()+"\n"+
			"Roommate: "+mainBooking.getRoom()+"\n"+"\n"+
			
			"Please check the information carefully.\n\n"+
			"Payment method： \n"+
			"\t"+"1.Bank transfer："+"\n"+
			"\t"+ "  "+"Payment information:"+"\n"+
			"\t"+ "  "+"Account name:Hino Travel"+"\n"+
			"\t"+ "  "+"Sort code: 20-63-25"+"\n"+
			"\t"+ "  "+"Account number: 33250482"+"\n"+
			"\t"+ "  "+"Please write down your name and trip name in the reference."+"\n"+
			"\t"+"2.Pay cash to our sales in your town."+"\n"+
			"\t"+"3.Come to office of Hino Travel to pay your tour fee. "+"\n"+
			"\t"+ "  "+"Address: Crystal One，Ramada Hotel，17-31 Wollaton Street，Nottingham NG1 5FW."+"\n"+"\n"+
			"Thank you for travelling with us, wish you a pleasant journey!"+"\n"+"\n"+
			"Best Regards,"+"\n"+"\n"+
			"Hino Travel"+"\n" ;
			
			try {
				/*String filePath = new FileCenter().getContractDir()+"Hino_Customer_Contract.pdf";
				emailUtil.send(mainBooking.getEmail(), Info.EMAIL_TO, 
						"[Hino Travel]Booking reservation information",
						content,
						filePath, "Hino_Customer_Contract.pdf");*/
				//String filePath = new FileCenter().getContractDir()+"Hino_Customer_Contract.pdf";
				emailUtil.send(mainBooking.getEmail(), Info.EMAIL_TO, 
						"[Hino Travel]Booking reservation information",
						content,
						"", "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			//- Devon King - 2012/09/04
		}
		return sr;
	}
	
	@Override
	public ServiceResponse repay_booking(int gid, String email, BookingReserveDto cbd) {
		
		ServiceResponse sr = new ServiceResponse();
		Group g = groupDao.viewGroupById(gid);
		if(g==null)
		{
			sr.setSucc(false);
			sr.addMsg("Group does not exist.");
			return sr;
		}

		sr = bookingGroupManager.repay_booking(gid, email, cbd);
		if(!sr.isSucc())
		{
			return sr;
		}

		return sr;
	}
	
	private String randomnBookingRef()
	{
		String str = TimeFormater.format3(Calendar.getInstance());
		return str+RandomGenerator.randomnNumberString(4);
	}

	@Override
	public Customer view_customer(long cid) {
		return customerDao.find(cid);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse update_customer_basic(CustomerBasicInfoDto basicinfodto) {
		ServiceResponse sr = new ServiceResponse();
		
		Customer targetCustomer = customerDao.getCustomerById(basicinfodto.getId());
		
		// Set new attributes' values
		targetCustomer.setLastname(basicinfodto.getLastname());
		targetCustomer.setFirstname(basicinfodto.getFirstname());
		targetCustomer.setChinesename(basicinfodto.getChinesename());
		targetCustomer.setGender(basicinfodto.getGender());
		targetCustomer.setPhone(basicinfodto.getTelephone());
		targetCustomer.setDob(basicinfodto.getDob());
		targetCustomer.setAddress(basicinfodto.getAddress());
		targetCustomer.setCity(basicinfodto.getCity());
		targetCustomer.setPostcode(basicinfodto.getPostcode());
		targetCustomer.setUniversity(basicinfodto.getUniversity());
		
		sr.setSucc(true);
		sr.setResponse(targetCustomer);
		return sr;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse update_customer_basicinfo(CustomerBasicInfoDto basicinfodto) {
		ServiceResponse sr = new ServiceResponse();
		
		Customer targetCustomer = customerDao.getCustomerById(basicinfodto.getId());
		
		// Set new attributes' values
		targetCustomer.setLastname(basicinfodto.getLastname());
		targetCustomer.setFirstname(basicinfodto.getFirstname());
		targetCustomer.setChinesename(basicinfodto.getChinesename());
		targetCustomer.setPhone(basicinfodto.getTelephone());
		targetCustomer.setAddress(basicinfodto.getAddress());
		targetCustomer.setUniversity(basicinfodto.getUniversity());
		
		sr.setSucc(true);
		sr.setResponse(targetCustomer);
		return sr;
	}	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS ,readOnly=true, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse issueTicket(long id, long cid) {
		ServiceResponse sr = new ServiceResponse();
		
		Booking b = bookingDao.findBookingById(id);
		if(b==null||b.getStatus()!=Info.BKS_CONFIRMED)
		{
			sr.setSucc(false);
			sr.addMsg("Ticket could only be issued under confirmed status!");
			return sr;
		} 
		
		if(b.getCustomer().getId()==cid)
		{	
			sr.setSucc(true);
			sr.setResponse(b.getTicket_auth());
			return sr;	
		}
		
		sr.addMsg("Access denied!");
		sr.setSucc(false);
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse addFeedBack(long cid, long id, String feedback) {
		ServiceResponse sr = new ServiceResponse();
		Booking b = bookingDao.getBookingById(id);
		if(b==null||b.getCustomer()==null||b.getCustomer().getId()!=cid)
		{
			sr.addMsg("Unable to execute this action！");
			sr.setSucc(false);
			return sr;
		}
		
		
		if(b.getStatus()!=Info.BKS_CONFIRMED||b.getGroup().getStatus()!=Info.GS_FINISHED)
		{
			sr.addMsg("The Questionaire System has not been started yet！");
			sr.setSucc(false);
			return sr;
		}
		
		
		if(b.getFeedback()==null)
		{
			b.setFeedback(feedback);
			b.getCustomer().deposit(50);
			sr.setSucc(true);
		} else
		{
			sr.addMsg("You have completed this qustionaire, thank you！");
			sr.setSucc(false);
		}
		return sr;
	}

	@Override
	public ServiceResponse requestFeedBack(long cid, long id) {
		ServiceResponse sr = new ServiceResponse();
		Booking b = bookingDao.findBookingById(id);
		if(b==null||b.getCustomer()==null||b.getCustomer().getId()!=cid)
		{
			sr.addMsg("Unable to execute this action！");
			sr.setSucc(false);
			return sr;
		}
		
		
		if(b.getStatus()!=Info.BKS_CONFIRMED||b.getGroup().getStatus()!=Info.GS_FINISHED)
		{
			sr.addMsg("The Questionaire System has not been started yet！");
			sr.setSucc(false);
			return sr;
		}
		
		
		if(b.getFeedback()==null)
		{
			sr.setSucc(true);
		} else
		{
			sr.addMsg("You have completed this qustionaire, thank you！");
			sr.setSucc(false);
		}
		return sr;
	}

	@Override
	public List<Customer> searchCustomer(int page, int size, String keyword, int type) {
		List<Customer> lc = null;
		if(type == 0)
		{
		lc = new ArrayList<Customer>();
		Customer c = customerDao.findCustomerByEmail(keyword);
		if (c!=null)
		lc.add(c);
		}
		return lc;
	}

	@Override
	public int searchCustomerCount(String keyword, int type) {
		if(type == 0)
		{
			if (customerDao.exist_email(keyword))
			return 1;
			else
			return 0;
		}
		return 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse reset_vip(Calendar c, long cid) {
		ServiceResponse sr = new ServiceResponse();
		Customer cs = customerDao.getCustomerById(cid);
		if(cs == null)
		{
			sr.addMsg("用户不存在");
			sr.setSucc(false);
			return sr;
		} else
		{
			if(c==null)
			{
				sr.addMsg("日期未指定");
				sr.setSucc(false);
				return sr;
			}
			
			cs.setVip_valid(c);
			sr.setSucc(true);
			return sr;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse cancel_vip(long cid) {
		ServiceResponse sr = new ServiceResponse();
		Customer cs = customerDao.getCustomerById(cid);
		if(cs == null)
		{
			sr.addMsg("用户不存在");
			sr.setSucc(false);
			return sr;
		} else
		{
			customerDao.cancelVip(cs);
			sr.setSucc(true);
			return sr;
		}
	}


	@Override
	public ServiceResponse vip_reserve(VipOrderReserveDto vordto) {
		return vipOrderManager.reserve(vordto);
		
	}

	@Override
	public ServiceResponse vip_card_completed(long vid) {
		return vipOrderManager.complete(vid);
	}

	@Override
	public VipOrder vip_issue_card(int sid, long vid) {
		return vipOrderDao.viewVipOrderById(vid);
	}

	@Override
	public List<Booking> getMyBookingList(String email) {
		return bookingDao.findBookingByEmail(email);
	}

	@Override
	public List<Object[]> output_data() {
		return de.fetchData("select distinct email from Customer");
	}

	@Override
	public void external_car_request(CarRequestDto crdto) {
		CarRequest cr = new CarRequest();
		cr.setCar_type(crdto.getCar_type());
		Calendar c = Calendar.getInstance();
		c.setTime(crdto.getDate());
		cr.setDate(c);
		cr.setDetail(crdto.getDetail());
		cr.setEmail(crdto.getEmail());
		cr.setName(crdto.getName());
		cr.setNo_of_days(crdto.getNo_of_days());
		cr.setNo_of_persons(crdto.getNo_of_persons());
		cr.setPhone(crdto.getPhone());
		cr.setStatus(0);
		carRequestDao.save(cr);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void vote_tour_survey(int vote, long tourid) {
		TourSurvey ts = tourSurveyDao.getTourSurveyById(tourid);
		if(vote==0)
		{
			ts.setPosi(ts.getPosi()+1);
		} else
		{
			ts.setNegi(ts.getNegi()+1);
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void update_customer_basic_2(String em, String uni, String ph,
			String city, String cn, int gender) {
		Customer cus = customerDao.findCustomerByEmail(em);
		cus.setUniversity(uni);
		cus.setPhone(ph);
		cus.setCity(city);
		cus.setChinesename(cn);
		cus.setGender(gender);
		
	}

	@Override
	public List<Booking> list_sk_win(int x) {
		// TODO Auto-generated method stub
		return bookingDao.list_sk_win(10);
	}

	@Override
	@Transactional(readOnly = true)
	public Booking view_booking(String ref, long cid) {
		Booking b = bookingDao.findBookingByRef(ref);
		if (b==null) {
			
			System.out.println("11111111111111111111111111");
			return b;
		}
		else if (b.getCustomer()==null) {
			System.out.println("22222222222222222222222");
			System.out.println(b.getGroup().getName());
			
				return null;
		} else if (b.getCustomer().getId()!=cid) {
			System.out.println("33333333333333333333333333");
			return null;
		} else {
			System.out.println("444444444444444444444444444444");
			return b;
		}

	}
	
	@Override
	@Transactional(readOnly = true)
	public Booking view_booking(String ref) {
		Booking b = bookingDao.findBookingByRef(ref);
		if (b==null) {
			
			System.out.println("11111111111111111111111111");
			return b;
		}
		else if (b.getCustomer()==null) {
			System.out.println("22222222222222222222222");
			System.out.println(b.getGroup().getName());
			
				return null;
		} else {
			System.out.println("444444444444444444444444444444");
			return b;
		}

	}

	@Override
	public String output_group_emails(int gid) {
		List<Object[]> list = de.fetchData("select distinct email from Booking where group_id="+gid);
		StringBuffer sb = new StringBuffer();
		for (Object[] obj : list)
		{
			sb.append(obj[0]);
			sb.append(";");
		}
		return sb.toString();
	}

	@Override
	public List<VipOrder> getVipOrderByEmail(String email) {
		return vipOrderDao.getVipOrderByEmail(email);
	}

	@Override
	public List<CustomerHistory> getCustomeHistoryByCid(long cid) {
		return this.customerHistoryDao.list_customer_history(cid);
	}
	
	@Override
	public int getCustomerHistoryCountByCid(long cid) {
		return customerHistoryDao.getCustomerHistoryCount(cid);
	}

	public CustomerHistoryDAO getCustomerHistoryDao() {
		return customerHistoryDao;
	}

	public void setCustomerHistoryDao(CustomerHistoryDAO customerHistoryDao) {
		this.customerHistoryDao = customerHistoryDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public String bat_adjust_vip_credit(String[] email, int credit, Staff s) {
		// TODO Auto-generated method stub
		List<String> existEmail = new ArrayList<String>();
		List<String> errEmail = new ArrayList<String>();
		//check the email first
		ServiceResponse sr = new ServiceResponse() ;
		for(String e : email)
		{
			if(this.customerDao.exist_email(e))
			{
				existEmail.add(e);
			}
			else
			{
				errEmail.add(e);
			}
		}
		
		for(String e : existEmail)
		{
			Customer c = customerDao.findCustomerByEmail(e);
			CustomerHistory ch = new CustomerHistory();
			
			customerHistoryDao.SaveCustomerHistory(c, ch);
			ch.setCreateUid(s.getId());
			if(c.getPoint()+credit<0)
			{
				c.setPoint(0);
			}
			else
			{
				c.setPoint(c.getPoint()+credit);
			}
			customerHistoryDao.save(ch);
			
		}
		if(errEmail.size()>0)
		{
			StringBuffer sb = new StringBuffer();
			for(String s1 : errEmail)
			{
				sb.append(s1);
				sb.append("\r");
			}
			return sb.toString();
		}
		else
		{
			return null;
		}
	}

	@Override
	public void update_booking_payment_method(String booking_ref, int bookingNethod) {
		Booking b = bookingDao.findBookingByRef(booking_ref);
		if(null != b) {
			b.setBooking_method(bookingNethod);
			bookingDao.update(b);
		}
	}

	@Override
	public ServiceResponse sendEmailMultiAtts(CustomerEmailCAMDto emaildto) {
		ServiceResponse sr = new ServiceResponse();		
		HashSet<String> customerEmails = new HashSet<String>(); 
		
		switch(emaildto.getCustomerfilter()) {
			case Info.CUSTOMER_GROUP:
				get_customer_email_group(customerEmails, emaildto.getGroupid()); break;
			case Info.CUSTOMER_ALL:
				get_customer_email_all(customerEmails); break;
			case Info.CUSTOMER_LAST_MONTH:
				get_customer_email_by_month(customerEmails, 1); break;
			case Info.CUSTOMER_LAST_THREEE_MONTH:
				get_customer_email_by_month(customerEmails, 3); break;
			case Info.CUSTOMER_LAST_SIX_MONTH:
				get_customer_email_by_month(customerEmails, 6); break;
			default:
				customerEmails = null;			
		}
		
		if (customerEmails == null || customerEmails.size() <= 0)
		{
			sr.setSucc(false);
			sr.addInfo(Info.CUSTOMER_EMAIL_NOT_FOUND);
		} else {
			// Upload attachment
			int idx = 0;
			int attCount = emaildto.getAttachments().size();
			String[] attUrls = new String[attCount];
			
			for(FileItem fi : emaildto.getAttachments()) {
				if (fi.getName().trim().compareTo("") != 0) {
					try {
						attUrls[idx ++] = fileCenter.save_file(fi, true);						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			send_email(emaildto, customerEmails, attUrls);
			
			sr.setSucc(true);
		}
		return sr;
	}

	

}
