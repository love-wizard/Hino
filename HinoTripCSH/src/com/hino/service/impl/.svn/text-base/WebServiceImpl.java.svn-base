package com.hino.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hino.dao.AirlineQADAO;
import com.hino.dao.BookingDAO;
import com.hino.dao.CustomerDAO;
import com.hino.dao.GroupDAO;
import com.hino.dao.InfoDAO;
import com.hino.dao.QQServiceDAO;
import com.hino.dao.RouteDAO;
import com.hino.dao.StaffDAO;
import com.hino.dao.WebMenuDAO;
import com.hino.dao.WebMenuRouteDAO;
import com.hino.dto.AirlineQACAMDto;
import com.hino.dto.CustomerBasicInfoDto;
import com.hino.dto.CustomerLoginDto;
import com.hino.dto.CustomerPasswordDto;
import com.hino.dto.GroupSumByPickupDto;
import com.hino.dto.IndexImageSettingDto;
import com.hino.dto.MenuCAMDto;
import com.hino.dto.MenuRouteCAMDto;
import com.hino.dto.QQServiceCAMDto;
import com.hino.dto.StaffLoginDto;
import com.hino.model.AirlineQA;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.QQService;
import com.hino.model.Route;
import com.hino.model.Site;
import com.hino.model.Staff;
import com.hino.model.WebMenu;
import com.hino.model.WebMenuRoute;
import com.hino.service.WebService;
import com.hino.util.EmailUtil;
import com.hino.util.FileCenter;
import com.hino.util.Info;
import com.hino.util.Page;
import com.hino.util.RandomGenerator;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class WebServiceImpl implements WebService {

	private CustomerDAO customerDao;
	private StaffDAO staffDao;
	private WebMenuDAO webMenuDao;
	private RouteDAO routeDao;
	private WebMenuRouteDAO webMenuRouteDao;
	private FileCenter fileCenter;
	private EmailUtil emailUtil;
	private BookingDAO bookingDao;
	private GroupDAO groupDao;
	private InfoDAO infoDao;
	private QQServiceDAO qqServiceDao;
	private AirlineQADAO airlineQADAO;
	
	public AirlineQADAO getAirlineQADAO() {
		return airlineQADAO;
	}

	public void setAirlineQADAO(AirlineQADAO airlineQADAO) {
		this.airlineQADAO = airlineQADAO;
	}

	public InfoDAO getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(InfoDAO infoDao) {
		this.infoDao = infoDao;
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

	public EmailUtil getEmailUtil() {
		return emailUtil;
	}

	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}

	public FileCenter getFileCenter() {
		return fileCenter;
	}

	public void setFileCenter(FileCenter fileCenter) {
		this.fileCenter = fileCenter;
	}

	public WebMenuRouteDAO getWebMenuRouteDao() {
		return webMenuRouteDao;
	}

	public void setWebMenuRouteDao(WebMenuRouteDAO webMenuRouteDao) {
		this.webMenuRouteDao = webMenuRouteDao;
	}

	public RouteDAO getRouteDao() {
		return routeDao;
	}

	public void setRouteDao(RouteDAO routeDao) {
		this.routeDao = routeDao;
	}

	public WebMenuDAO getWebMenuDao() {
		return webMenuDao;
	}

	public void setWebMenuDao(WebMenuDAO webMenuDao) {
		this.webMenuDao = webMenuDao;
	}

	public StaffDAO getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDAO staffDao) {
		this.staffDao = staffDao;
	}

	public CustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public ServiceResponse customer_login(CustomerLoginDto cldto) {
		ServiceResponse sr = new ServiceResponse();
		Customer c = customerDao.findCustomerByEmail(cldto.getEmail());
		if (c == null) {
			sr.addInfo(Info.CUSTOMER_LOGIN_FAILED_USERNAME_NOT_FOUND);
		} else {
			// 密码验证，状态验证
			if (c.getPassword().equals(cldto.getPassword())) 
			{
				c.setLast_login_time(Calendar.getInstance());
				customerDao.update_login_time(c);
				sr.setSucc(true);
				sr.setResponse(c);
			} else {
				sr.addInfo(Info.CUSTOMER_LOGIN_FAILED_PASSWORD_NOT_MATCHED);
			}
		}
		return sr;
	}
	
	@Override
	public ServiceResponse customer_resetpwd(CustomerLoginDto cldto) {
		ServiceResponse sr = new ServiceResponse();
		Customer c = customerDao.findCustomerByEmail(cldto.getEmail());
		if (c == null) {
			sr.addInfo(Info.CUSTOMER_LOGIN_FAILED_USERNAME_NOT_FOUND);
		} else {
			// 密码验证，状态验证
			String password = RandomGenerator.randomString(10);
			c.setPassword(password);
			
			try {

				String title = null;
				String content = null;
				title = "海诺旅游-密码重置信息";
				content = "亲爱的"
						+ c.genFullName()
						+ ":\n\n"
						+ "您的密码已经重置.\n"
						+ "请您使用以下账户信息登录海诺旅游官网查询并报名您所喜爱的团次."
						+ "\n"
						+ "账户名称:"
						+ c.getEmail()
						+ "\n"
						+ "重置登录密码:"
						+ c.getPassword()
						+ "\n\n"
						+ "感谢您选择海诺旅游,期待与您同行！"
						+ "\n"
						+ "更多团次信息请登录海诺官网: http://www.hinotravel.com/zh/index.htm "
						+ "\n"
						+ "海诺旅游咨询群:"
						+ "\n"
						+ "诺丁汉新生旅游群: 227023501"
						+ "\n"
						+ "莱斯特新生旅游群: 252114483"
						+ "\n"
						+ "拉夫堡新生旅游群: 252115012"
						+ "\n"
						+ "伦  敦新生旅游群: 257848277"
						+ "\n\n"
						+ "Dear "
						+ c.genFullName()
						+ ":\n\n"
						+ "Welcome to Hino Travel website. "
						+ "\n"
						+ "It is a pleasure to tell you that your password has been reset successfully."
						+ "\n"
						+ "Please use the details below to login to and continue with your purchase."
						+ "\n" + "Username:" + c.getEmail() + "\n"
						+ "Password:" + c.getPassword() + "\n\n"
						+ "Best wishes," + "\n" + "Hino Travel" + "\n";
				emailUtil.send(c.getEmail(), Info.EMAIL_TO, title, content,
						null, null);
				
				customerDao.update_password(c);
				sr.addInfo(Info.CUSTOMER_RESET_PASSWORD_SUCCEED);
				sr.setSucc(true);
			} catch (Exception e) {
				System.out.println("FAIL" + e.getMessage());
				sr.addInfo(Info.CUSTOMER_RESET_PASSWORD_FAILED);
			}
		}
		return sr;
	}

	@Override
	public ServiceResponse staff_login(StaffLoginDto sldto) {
		ServiceResponse sr = new ServiceResponse();
		Staff s = staffDao.findbyStaffno(sldto.getStaffno());
		if (s == null) {
			sr.addInfo(Info.UNDEFINED);
		} else {
			// 密码验证，状态验证
			if (s.getPassword().equals(sldto.getPassword())
					&& (s.getStatus() == 0)) {
				s.setLast_login_time(Calendar.getInstance());
				staffDao.update_login_time(s);
				sr.setSucc(true);
				sr.setResponse(s);
			} else {
				sr.addInfo(Info.UNDEFINED);
			}
		}
		return sr;
	}

	/**
	 * Create new item for external menu
	 */
	@Override
	public void create_menu(MenuCAMDto menucamdto) {
		WebMenu newMenu = new WebMenu();
		newMenu.setName(menucamdto.getName());
		newMenu.setName_en(menucamdto.getName_en());
		if(menucamdto.getDescription()!=null)
		{
			newMenu.setDescription(menucamdto.getDescription());
		}
		
		if(menucamdto.getDescription_en()!=null)
		{
			newMenu.setDescription_en(menucamdto.getDescription_en());
		}
		
		String imgUrl = "";
		if (menucamdto.getImage().getName().trim().compareTo("") != 0) {
			try {
				imgUrl = fileCenter.save_file(menucamdto.getImage(), false);
				newMenu.setImageUrl(imgUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		if (menucamdto.getImage_en().getName().trim().compareTo("") != 0) {
			try {
				imgUrl = fileCenter.save_file(menucamdto.getImage_en(), false);
				newMenu.setImageUrl_en(imgUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		webMenuDao.save(newMenu);
	}

	@Override
	public List<WebMenu> getPagedMenuList(int start, int count) {
		return webMenuDao.list_menu_by_paging(start, count);
	}

	@Override
	public int getMenuCount() {
		return webMenuDao.getMenuCount();
	}

	@Override
	public void delete_menu(long id) {
		webMenuDao.delete(id);
	}

	@Override
	public WebMenu getMenuById(long id) {
		return webMenuDao.getWebMenuById(id);
	}

	@Override
	public void modify_menu(MenuCAMDto menucamdto) {
		WebMenu targetMenu = getMenuById(Long.parseLong(menucamdto.getId()));
		targetMenu.setName(menucamdto.getName());
		targetMenu.setName_en(menucamdto.getName_en());
		if(menucamdto.getDescription() != null)
		{
			targetMenu.setDescription((menucamdto.getDescription()));
		}
		
		if(menucamdto.getDescription_en()!= null)
		{
			targetMenu.setDescription_en((menucamdto.getDescription_en()));
		}
		
		String imgUrl = "";
		if (menucamdto.getImage().getName().trim().compareTo("") != 0) {
			try {
				imgUrl = fileCenter.save_file(menucamdto.getImage(), false);
				targetMenu.setImageUrl(imgUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (menucamdto.getImage_en().getName().trim().compareTo("") != 0) {
			try {
				imgUrl = fileCenter.save_file(menucamdto.getImage_en(), false);
				targetMenu.setImageUrl_en(imgUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		webMenuDao.update(targetMenu);
	}

	@Override
	public List<WebMenu> getAllMenu() {
		return webMenuDao.getAllWebMenu();
	}

	@Override
	public void create_menu_route(MenuRouteCAMDto menuroutedto) {
		Route targetRoute = routeDao.getRouteById(Long.parseLong(menuroutedto.getRouteid()));
		
		WebMenuRoute newMenuRoute = new WebMenuRoute();
		newMenuRoute.setRoute(targetRoute);
		newMenuRoute.setPriority(Long.parseLong(menuroutedto.getPriority()));
		webMenuRouteDao.save(newMenuRoute);
		
		WebMenu targetMenu = webMenuDao.getWebMenuById(Long.parseLong(menuroutedto.getMenuid()));
		targetMenu.getMenu_routes().add(newMenuRoute);
		webMenuDao.update(targetMenu);

	}

	@Override
	public void delete_menu_route(long id) {
		List<WebMenu> allMenu = getAllMenu();
        for(WebMenu curMenu : allMenu) {
        	int indexToBeRemoved = -1;
        	List<WebMenuRoute> curMenuRouteList = curMenu.getMenu_routes();
        	for(int index=0; index<curMenuRouteList.size(); index++) {
        		if(curMenuRouteList.get(index).getId() == id) {
        			indexToBeRemoved = index;
        		}
        	}
        	if(indexToBeRemoved != -1) {
        		curMenuRouteList.remove(indexToBeRemoved);
        		webMenuDao.update(curMenu);
        	}
		}
		webMenuRouteDao.delete(id);
	}

	@Override
	public void modify_menu_route(MenuRouteCAMDto menuroutedto) {
		WebMenuRoute targetWebMenuRoute = new WebMenuRoute();
		targetWebMenuRoute.setId(Long.parseLong(menuroutedto.getId()));
		targetWebMenuRoute.setPriority(Long.parseLong(menuroutedto.getPriority()));
		targetWebMenuRoute.setRoute(routeDao.getRouteById(Long.parseLong(menuroutedto.getRouteid())));
		webMenuRouteDao.update(targetWebMenuRoute);
	}

	@Override
	public WebMenu getIndexMenu() {
		WebMenu index = getMenuById(-1);
		if(index == null) { // Create index menu
			webMenuDao.create_index_menu();
			index = getMenuById(-1);
		}
		return index;
	}

	@Override
	public ServiceResponse customerResetPsdEmail(String email, boolean isEn) {
		ServiceResponse rsp = new ServiceResponse();
		
		// Find out the customer first
		Customer targetCustomer = customerDao.findCustomerByEmail(email);
		
		// If there is no customer with such email, return error code
		if(targetCustomer == null) {
			rsp.setSucc(false);
			rsp.addInfo(Info.RESET_PASSWORD_FAILED_EMAIL_NOT_EXIST);
			return rsp;
		}
		
		// Generate reset code: id+16RandomChar
		String resetCode = String.valueOf(targetCustomer.getId())+RandomGenerator.randomString(16);
		targetCustomer.setResetcode(resetCode);
		customerDao.update_resetcode(targetCustomer);
		
		try {
			String title = null;
			String content = null;
			if(isEn == true) {
				title = "[Hino Travel]Resetting your password";
				content = "Dear customer:\n\n"+
								"　　We noticed that you required to reset your Hino Travel password. Please visit the following web page to continue:\n"+
								"　　http://www.hinotravel.com/HinoTripCSH/en/reset_password.htm?resetCode="+resetCode+"\n"+
								"　　If you are not going to reset your password, you can simply ignore this email.\n"+
								"　　Thank you very much for using Hino Travel online.\n\n"+
								"Hino Travel\n"+
								TimeFormater.format2(Calendar.getInstance());
			}
			else {
				title = "[海诺旅游在线系统]用户重置密码邮件";
				content = "尊敬的用户，您好：\n\n"+
								"　　您在海诺在线系统的密码重置请求已经受理，请点击访问以下地址进入重置密码页面：\n"+
								"　　http://www.hinotravel.com/HinoTripCSH/reset_password.htm?resetCode="+resetCode+"\n"+
								"　　如果此地址没有显示为可访问的链接形式，请手动复制粘贴到浏览器地址栏。\n"+
								"　　如果您并没有申请重置密码，则可以安全地忽略此邮件，无须访问此链接。\n"+
								"　　感谢使用海诺在线系统。\n\n"+
								"海诺旅游\n"+
								TimeFormater.format2(Calendar.getInstance());
			}
			emailUtil.send(email, Info.EMAIL_TO, title, content, null, null);
		} catch (Exception e) {
			rsp.setSucc(false);
			rsp.addInfo(Info.RESET_PASSWORD_FAILED_SEND_EMAIL_ERROR);
			return rsp;
		}
		
		rsp.setSucc(true);
		return rsp;
	}

	@Override
	public ServiceResponse customerResetPsd(String resetcode, String password) {
		ServiceResponse rsp = new ServiceResponse();
		
		// Find out the customer first
		Customer targetCustomer = customerDao.getCustomerByResetcode(resetcode);
		
		// If there is no customer with such resetcode, return error code
		if(targetCustomer == null) {
			rsp.setSucc(false);
			rsp.addInfo(Info.RESET_PASSWORD_FAILED_RESETCODE_NOT_EXIST);
			return rsp;
		}

		// Set new password and remove reset code
		targetCustomer.setPassword(password);
		customerDao.update_password(targetCustomer);
		targetCustomer.setResetcode(null);
		customerDao.update_resetcode(targetCustomer);
		rsp.setSucc(true);
		return rsp;
	}

	@Override
	public ServiceResponse staffResetPsdEmail(String email) {
		ServiceResponse rsp = new ServiceResponse();
		
		// Find out the customer first
		Staff targetStaff = staffDao.getStaffByEmail(email);
		
		// If there is no customer with such email, return error code
		if(targetStaff == null) {
			rsp.setSucc(false);
			rsp.addInfo(Info.RESET_PASSWORD_FAILED_EMAIL_NOT_EXIST);
			return rsp;
		}
		
		// Generate reset code: id+16RandomChar
		String resetCode = String.valueOf(targetStaff.getId())+RandomGenerator.randomString(16);
		targetStaff.setResetcode(resetCode);
		staffDao.update_resetcode(targetStaff);
		
		try {
			String title = "[海诺旅游后台管理系统]用户重置密码邮件";
			String content = "尊敬的海诺员工，您好：\n\n"+
							"　　您在海诺在线系统的密码重置请求已经受理，请点击访问以下地址进入重置密码页面：\n"+
							"　　http://www.hinotravel.com/HinoTripCSH/internal/reset_password.htm?resetCode="+resetCode+"\n"+
							"　　如果此地址没有显示为可访问的链接形式，请手动复制粘贴到浏览器地址栏。\n"+
							"　　如果您并没有申请重置密码，则可以安全地忽略此邮件，无须访问此链接。\n"+
							"海诺旅游\n"+
							TimeFormater.format2(Calendar.getInstance());
			emailUtil.send(email, Info.EMAIL_TO, title, content, null, null);
		} catch (Exception e) {
			rsp.setSucc(false);
			rsp.addInfo(Info.RESET_PASSWORD_FAILED_SEND_EMAIL_ERROR);
			return rsp;
		}
		
		rsp.setSucc(true);
		return rsp;
	}

	@Override
	public ServiceResponse staffResetPsd(String resetcode, String password) {
		ServiceResponse rsp = new ServiceResponse();
		
		// Find out the customer first
		Staff targetStaff = staffDao.getStaffByResetcode(resetcode);
		
		// If there is no customer with such resetcode, return error code
		if(targetStaff == null) {
			rsp.setSucc(false);
			rsp.addInfo(Info.RESET_PASSWORD_FAILED_RESETCODE_NOT_EXIST);
			return rsp;
		}

		// Set new password and remove reset code
		targetStaff.setPassword(password);
		staffDao.update_password(targetStaff);
		targetStaff.setResetcode(null);
		staffDao.update_resetcode(targetStaff);
		rsp.setSucc(true);
		return rsp;
	}
	
	@Override
	public ServiceResponse update_customer_psd(CustomerPasswordDto psddto) {
		ServiceResponse sr = new ServiceResponse();
		
		Customer targetCustomer = customerDao.find((int)psddto.getId());
		
		// Check if old password is correct
		if(targetCustomer.getPassword().compareTo(psddto.getOldpsd()) != 0) {
			sr.addInfo(Info.CUSTOMER_UPDATE_FAILED_PASSWORD_ERROR);
			sr.setSucc(false);
			return sr;
		}
		
		targetCustomer.setPassword(psddto.getNewpsd());
		
		customerDao.update_password(targetCustomer);
		
		sr.setSucc(true);
		return sr;
	}
	
	

	@Override
	public List<Group> getRecentGroup() {
		Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.MONTH, 2);
		List<Group> result = groupDao.list_group_by_date(startTime, endTime, Info.GS_OPENNING, 12);
		return result;
	}
	
	@Override
	public List<Group> getGroupByRouteId(int routeid) {
		return groupDao.list_external_viewable_group(routeid);
	}
	
	@Override
	public List<Group> getGroupByRouteIdOriginal(int routeid){
		return groupDao.list_external_viewable_group_original(routeid);
	}
	
	@Override
	public List<Group> getGroupByRouteIdOriginal(int routeid, Calendar startDate, Calendar endDate) {
		return groupDao.list_external_viewable_group_original(routeid, startDate, endDate);
	}
	
	@Override
	public List<Group> getGroupByRouteIdOriginalByPriority(int routeid, Calendar startDate, Calendar endDate) {
		return groupDao.list_ext_view_ori_group_by_priority(routeid, startDate, endDate);
	}	
	
	@Override
	public List<Group> getGroupByRouteId(int routeid, Calendar startDate, Calendar endDate) {
		return groupDao.list_external_viewable_group(routeid, startDate, endDate);
	}
	
	@Override
	public List<Group> getGroupByRouteIdAndDate(Calendar startDate, Calendar endDate, int routeId, int status, int max, int orderByPrice){
		return groupDao.list_group_by_route_and_date(routeId, startDate, endDate, status, max, orderByPrice, "");
	}

	@Override
	public void indexImageSetting(IndexImageSettingDto iisdto){
		try {
			if(iisdto.getImage1_en().getName().length()>0){
				fileCenter.save_to(iisdto.getImage1_en(), "images"+File.separator, "album01_en.jpg");
			}
			
			if(iisdto.getImage2_en().getName().length()>0){
				fileCenter.save_to(iisdto.getImage2_en(), "images"+File.separator, "album02_en.jpg");
			}
			
			if(iisdto.getImage3_en().getName().length()>0){
				fileCenter.save_to(iisdto.getImage3_en(), "images"+File.separator, "album03_en.jpg");
			}
			
			if(iisdto.getImage4_en().getName().length()>0){
				fileCenter.save_to(iisdto.getImage4_en(), "images"+File.separator, "album04_en.jpg");
			}
			
			if(iisdto.getImage5_en().getName().length()>0){
				fileCenter.save_to(iisdto.getImage5_en(), "images"+File.separator, "album05_en.jpg");
			}
			
			if(iisdto.getImage1().getName().length()>0){
				fileCenter.save_to(iisdto.getImage1(), Info.EXTERNAL_PATH_PREFIX, "album01.jpg");
			}
			
			if(iisdto.getImage2().getName().length()>0){
				fileCenter.save_to(iisdto.getImage2(), Info.EXTERNAL_PATH_PREFIX, "album02.jpg");
			}
			
			if(iisdto.getImage3().getName().length()>0){
				fileCenter.save_to(iisdto.getImage3(), Info.EXTERNAL_PATH_PREFIX, "album03.jpg");
			}
			
			if(iisdto.getImage4().getName().length()>0){
				fileCenter.save_to(iisdto.getImage4(), Info.EXTERNAL_PATH_PREFIX, "album04.jpg");
			}
			
			if(iisdto.getImage5().getName().length()>0){
				fileCenter.save_to(iisdto.getImage5(), Info.EXTERNAL_PATH_PREFIX, "album05.jpg");
			}
			
			if(iisdto.getImage6().getName().length()>0){
				fileCenter.save_to(iisdto.getImage6(), Info.EXTERNAL_PATH_PREFIX, "album06.jpg");
			}
			
			if(iisdto.getImgVip().getName().length()>0){
				fileCenter.save_to(iisdto.getImgVip(), Info.EXTERNAL_PATH_PREFIX, "vip.jpg");
			}
			
			
			Info.imgLink1 = iisdto.getLink1();
			Info.imgLink2 = iisdto.getLink2();
			Info.imgLink3 = iisdto.getLink3();
			Info.imgLink4 = iisdto.getLink4();
			Info.imgLink5 = iisdto.getLink5();
			Info.imgLink6 = iisdto.getLink6();
			Info.imgLink11 = iisdto.getLink1_en();
			Info.imgLink22 = iisdto.getLink1_en();
			Info.imgLink33 = iisdto.getLink1_en();
			Info.imgLink44 = iisdto.getLink1_en();
			Info.imgLink44 = iisdto.getLink1_en();
			
			com.hino.model.Info info = new com.hino.model.Info();
			info = infoDao.getInfoById(1);
			if(null==info)
			{
				info.setImage(iisdto.getImage1().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink1());
				infoDao.save(info);
			}
			else{
				info.setImage(iisdto.getImage1().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink1());
				infoDao.update(info);
			}
			
			info = infoDao.getInfoById(2);
			if(null==info)
			{
				info.setImage(iisdto.getImage2().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink2());
				infoDao.save(info);
			}
			else{
				info.setImage(iisdto.getImage2().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink2());
				infoDao.update(info);
			}
			
			info = infoDao.getInfoById(3);
			if(null==info)
			{
				info.setImage(iisdto.getImage3().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink3());
				infoDao.save(info);
			}
			else{
				info.setImage(iisdto.getImage3().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink3());
				infoDao.update(info);
			}
			
			
			info = infoDao.getInfoById(4);
			if(null==info)
			{
				info.setImage(iisdto.getImage4().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink4());
				infoDao.save(info);
			}
			else{
				info.setImage(iisdto.getImage4().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink4());
				infoDao.update(info);
			}
			
			
			info = infoDao.getInfoById(5);
			if(null==info)
			{
				info.setImage(iisdto.getImage5().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink5());
				infoDao.save(info);
			}
			else{
				info.setImage(iisdto.getImage5().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink5());
				infoDao.update(info);
			}
			info = null;
			info = infoDao.getInfoById(6);
			if(null==info)
			{
				info.setImage(iisdto.getImgVip().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLinkVip());
				infoDao.save(info);
			}
			else{
				info.setImage(iisdto.getImgVip().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLinkVip());
				infoDao.update(info);
			}
			
			info = null;
			info = infoDao.getInfoById(9);
			if(null==info)
			{
				info.setImage(iisdto.getImage6().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink6());
				infoDao.save(info);
			}
			else{
				info.setImage(iisdto.getImage6().getFieldName());
				info.setSource("INDEX");
				info.setLinkUrl(iisdto.getLink6());
				infoDao.update(info);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public List<Group> getGroupExListView(int page, String orderingAttr, boolean isAscending) {
		return groupDao.list_external_viewable_group_paging(page, 50, orderingAttr, isAscending);
	}

	@Override
	public int getGroupExListViewCount() {
		return (int)groupDao.list_external_viewable_group_count();
	}

	@Override
	public List<Group> getGoGroup(int size, int type) {
		return groupDao.getGoGroup(size, type);
	}

	@Override
	public List<Group> getGoGroup(Page page, int type) {
		return groupDao.getGoGroup(page, type);
	}

	@Override
	public List<Group> getSecGroup(int size, int type) {
		return groupDao.getSecGroup(size, type);
	}

	@Override
	public List<WebMenuRoute> getOrderedMenuRoutes(long webMenuId) {
		
		return routeDao.getOrderedRoutesByWebMenuId(webMenuId);
	}

	@Override
	public List<com.hino.model.Info> getIndexInfo() {
		
		return infoDao.getIndexInfo();	
	}
	@Override
	public List<com.hino.model.Info> getInfo(String source) {
		return infoDao.getInfo(source);	
	}

	@Override
	public void indexNavigationSetting(com.hino.model.Info inFamousPlaces) {
		List<com.hino.model.Info> infos = infoDao.getIndexNavigationFamousPlace();
		com.hino.model.Info info = null;
		if(infos.size() > 0) {
			info = infos.get(0);
			info.setLinkUrl(inFamousPlaces.getLinkUrl());
			infoDao.update(info);
		} else {
			info = inFamousPlaces;
			infoDao.save(info);
			
		}
		
		
	}

	@Override
	public List<com.hino.model.Info> getIndexNavigationFamousPlace() {
		return infoDao.getIndexNavigationFamousPlace();
	}

	@Override
	public List<QQService> getIndexQQService() {
		return qqServiceDao.getIndexQQService();
	}

	public QQServiceDAO getQqServiceDao() {
		return qqServiceDao;
	}

	public void setQqServiceDao(QQServiceDAO qqServiceDao) {
		this.qqServiceDao = qqServiceDao;
	}

	@Override
	public QQService getIndexQQService(int id) {
		return this.qqServiceDao.getQQServiceById(id);
	}

	@Override
	public void AddQQService(QQServiceCAMDto qqCamDto) {
		QQService qqService = new QQService();
		qqService.setQqNumber(qqCamDto.getQqNumber());
		qqService.setServiceName(qqCamDto.getServiceName());
		qqService.setPriority(qqCamDto.getPriority());
		qqService.setUsedPlace(qqCamDto.getUsedPlace());
		this.qqServiceDao.save(qqService);
		
	}

	@Override
	public void UpdateQQService(QQServiceCAMDto qqCamDto) {
		QQService qqService = new QQService();
		qqService.setId(qqCamDto.getId());
		qqService.setQqNumber(qqCamDto.getQqNumber());
		qqService.setServiceName(qqCamDto.getServiceName());
		qqService.setPriority(qqCamDto.getPriority());
		qqService.setUsedPlace(qqCamDto.getUsedPlace());
		this.qqServiceDao.update(qqService);
		
	}

	@Override
	public void DeleteQQService(long id) {
		this.qqServiceDao.delete(id);
		
	}

	@Override
	public List<com.hino.model.Info> getDefalutSearchKeywords() {
		List<com.hino.model.Info> infos = infoDao.getDefaultSearchKeywords();
		return infos;
	}

	@Override
	public void SetupDefalutSearchKeywords(com.hino.model.Info keyWords) {
		List<com.hino.model.Info> infos = infoDao.getDefaultSearchKeywords();
		com.hino.model.Info info = null;
		if(infos.size() > 0) {
			info = infos.get(0);
			info.setLinkUrl(keyWords.getLinkUrl());
			infoDao.update(info);
		} else {
			info = keyWords;
			infoDao.save(info);
		}
		
		
		
	}

	@Override
	public List<GroupSumByPickupDto> getGroupSumByPickupCity(String pickupCity, String destination) {
		return groupDao.getGroupSumByPickupCity(pickupCity, destination);
	}

	@Override
	public int getAirlineQACount() {
		return airlineQADAO.getAirlineQACount();
	}

	@Override
	public List<AirlineQA> getPagedAirlineQAList(int pageNumber, int pageSize) {
		return airlineQADAO.list_AirlineQA_by_paging(pageNumber, pageSize);
	}

	@Override
	public void modify_airlineQA(AirlineQACAMDto qaCamDto) {
		AirlineQA qa = getAirlineQAById(qaCamDto.getId());
		qa.setArea(qaCamDto.getArea());
		qa.setQuestion(qaCamDto.getQuestion());
		qa.setAnswer(qaCamDto.getAnswer());
		this.airlineQADAO.update(qa);
	}

	@Override
	public void create_airlineQA(AirlineQACAMDto qaCamDto) {
		AirlineQA qa = new AirlineQA();
		qa.setArea(qaCamDto.getArea());
		qa.setQuestion(qaCamDto.getQuestion());
		qa.setAnswer(qaCamDto.getAnswer());
		this.airlineQADAO.save(qa);
	}

	@Override
	public AirlineQA getAirlineQAById(long id) {
		return airlineQADAO.getAirlineQAById(id);
	}

	@Override
	public void delete_airlineQA(long id) {
		airlineQADAO.delete(id);
	}

	@Override
	public void airlineImageSetting(IndexImageSettingDto airlineImageSettingDto) {
		try {			
			
			if(airlineImageSettingDto.getImage1().getName().length()>0){
				fileCenter.save_to(airlineImageSettingDto.getImage1(), Info.EXTERNAL_PATH_PREFIX, "airline_image01.jpg");
			}
			
			if(airlineImageSettingDto.getImage2().getName().length()>0){
				fileCenter.save_to(airlineImageSettingDto.getImage2(), Info.EXTERNAL_PATH_PREFIX, "airline_image02.jpg");
			}
			
			if(airlineImageSettingDto.getImage3().getName().length()>0){
				fileCenter.save_to(airlineImageSettingDto.getImage3(), Info.EXTERNAL_PATH_PREFIX, "airline_image03.jpg");
			}
			
			if(airlineImageSettingDto.getImage4().getName().length()>0){
				fileCenter.save_to(airlineImageSettingDto.getImage4(), Info.EXTERNAL_PATH_PREFIX, "airline_image04.jpg");
			}
			
			if(airlineImageSettingDto.getImage5().getName().length()>0){
				fileCenter.save_to(airlineImageSettingDto.getImage5(), Info.EXTERNAL_PATH_PREFIX, "airline_image05.jpg");
			}
			
			if(airlineImageSettingDto.getImage6().getName().length()>0){
				fileCenter.save_to(airlineImageSettingDto.getImage6(), Info.EXTERNAL_PATH_PREFIX, "airline_image06.jpg");
			}			

			com.hino.model.Info info = infoDao.getInfoById(Info.AIRLINE_IMAGE_ID_1);
			if(null == info)
			{
				info = new com.hino.model.Info();
				info.setImage("airline_image01.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink1());
				infoDao.save(info);
			} else{
				
				info.setImage("airline_image01.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink1());
				infoDao.update(info);
			}
			
			info = infoDao.getInfoById(Info.AIRLINE_IMAGE_ID_2);
			if(null == info)
			{
				info = new com.hino.model.Info();
				info.setImage("airline_image02.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink2());
				infoDao.save(info);
			} else {
				info.setImage("airline_image02.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink2());
				infoDao.update(info);
			}
			
			info = infoDao.getInfoById(Info.AIRLINE_IMAGE_ID_3);
			if(null == info)
			{
				info = new com.hino.model.Info();
				info.setImage("airline_image03.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink3());
				infoDao.save(info);
			} else {
				info.setImage("airline_image03.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink3());
				infoDao.update(info);
			}
			
			
			info = infoDao.getInfoById(Info.AIRLINE_IMAGE_ID_4);
			if(null == info)
			{
				info = new com.hino.model.Info();
				info.setImage("airline_image04.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink4());
				infoDao.save(info);
			} else {
				info.setImage("airline_image04.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink4());
				infoDao.update(info);
			}
			
			info = infoDao.getInfoById(Info.AIRLINE_IMAGE_ID_5);
			if(null == info)
			{
				info = new com.hino.model.Info();
				info.setImage("airline_image05.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink5());
				infoDao.save(info);
			} else {
				info.setImage("airline_image05.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink5());
				infoDao.update(info);
			}
				
			info = infoDao.getInfoById(Info.AIRLINE_IMAGE_ID_6);
			if(null == info)
			{
				info = new com.hino.model.Info();
				info.setImage("airline_image06.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink6());
				infoDao.save(info);
			} else {
				info.setImage("airline_image06.jpg");
				info.setSource("AIRLINE");
				info.setLinkUrl(airlineImageSettingDto.getLink6());
				infoDao.update(info);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Group> getGroupsByDateRange(int size, Calendar start, Calendar end) {
		return groupDao.getGroupsByDateRange(size, start, end);
	}
}
