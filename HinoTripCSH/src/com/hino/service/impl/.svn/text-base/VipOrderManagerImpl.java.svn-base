package com.hino.service.impl;

import java.util.Calendar;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hino.dao.CustomerDAO;
import com.hino.dao.StaffDAO;
import com.hino.dao.VipOrderDAO;
import com.hino.dto.VipOrderReserveDto;
import com.hino.model.Customer;
import com.hino.model.Staff;
import com.hino.model.VipOrder;
import com.hino.service.VipOrderManager;
import com.hino.util.EmailUtil;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class VipOrderManagerImpl implements VipOrderManager {
	private VipOrderDAO vipOrderDao;
	private StaffDAO staffDao;
	private CustomerDAO customerDao;
	private EmailUtil emailUtil;
	
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

	public VipOrderDAO getVipOrderDao() {
		return vipOrderDao;
	}

	public void setVipOrderDao(VipOrderDAO vipOrderDao) {
		this.vipOrderDao = vipOrderDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse confirm(long vid) {
		ServiceResponse sr = new ServiceResponse();
		VipOrder vo = vipOrderDao.getVipOrderById(vid);
		if(vo==null)
		{
			sr.addMsg("Target Order Not Found");
			sr.setSucc(false);
			return sr;
		} else
		{
			Customer c = vo.getCustomer();
			if(vo.getStatus()==Info.VS_RESERVING)
			{
				if(c==null)
				{
					sr.addMsg("Target Customer Not Found");
					sr.setSucc(false);
					return sr;
				} else
				{
					// Ken Chen 2012/09/29 Add VIP valid year, defalut 9999/12/31
					Calendar cld = Calendar.getInstance();
					if(vo.getExpire_year()>0)
					{
						cld.add(Calendar.YEAR, vo.getExpire_year());
					}
					else
					{
						cld.set(9999, 11, 31);
					}
					c.setVip_valid(cld);
					vo.setStatus(Info.VS_CONIFRMED);									
				}
				
			} else if (vo.getStatus()==Info.VS_CONIFRMED_NOTPAID)
			{
				vo.setStatus(Info.VS_CONIFRMED);
			} else
			{
				sr.addMsg("Only reserving order could be confirmed");
				sr.setSucc(false);
				return sr;
			}
			//+Kevin Zhong - 23/09/2012 send a vip order email
			try{
				String content = "尊敬的会员" + c.genFullName()+"\n\n"+
						"很高兴通知您，您的海诺会员账号"+c.getEmail()+"已经成功升级为海诺VIP账号！"+"\n\n"+
						"您可以通过海诺官网www.hinotravel.com登陆进行报名，或在联系海诺地区销售代表时告知您拥有VIP会员资格，即可享受海诺旅游VIP会员专享优惠价格！"+"\n\n"+
						"感谢您选择海诺旅游,祝您旅途愉快!"+"\n\n"+
						"海诺旅游"+"\n\n"+
						"Dear"+ c.genFullName()+"\n\n"+
						"Your account"+c.getEmail()+"has been updated to VIP account successfully."+"\n\n"+
						"You can login your account to participate tours to enjoy VIP discount, or inform our sales that you have the priority to enjoy VIP service."+"\n\n"+
						"Thank you."+"\n\n"+
						"Best Regards,"+"\n"+
						"Hino Travel"+"\n";
				
				emailUtil.send(c.getEmail(), Info.EMAIL_TO, 
								"You have been updated to VIP! ",
								content,
								null, null);
			}catch(Exception e){
				System.out.println("FAIL"+e.getMessage());
			}
			//-Kevin Zhong - 23/09/2012	
			sr.setSucc(true);
			return sr;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse delete(long vid) {
		ServiceResponse sr = new ServiceResponse();
		VipOrder vo = vipOrderDao.getVipOrderById(vid);
		if(vo==null)
		{
			sr.addMsg("Target Order Not Found");
			sr.setSucc(false);
			return sr;
		} else
		{
			vipOrderDao.delete(vo);
			sr.setSucc(true);
			return sr;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse reserve(VipOrderReserveDto vordto) {
		ServiceResponse sr = new ServiceResponse();
		VipOrder vo = new VipOrder();
		vo.setReciever(vordto.getName());
		vo.setEmail(vordto.getEmail());
		vo.setPhone(vordto.getPhone());
		vo.setAddress(vordto.getAddress());
		vo.setDelivery(vordto.getDelivery());
		vo.setOrder_method(vordto.getOrder_method());
		vo.setOrder_time(Calendar.getInstance());
		vo.setStatus(Info.VS_RESERVING);
		vo.setExpire_year(vordto.getExpire_year());
		Customer c = customerDao.findCustomerByEmail(vordto.getEmail());
		if(c==null)
		{
			sr.addMsg("Target Customer Not Found");
			sr.setSucc(false);
			return sr;
		} else
		{
			vo.setCustomer(c);
		}
		
		if(vordto.getStaffid()!=-1)
		{ 
			Staff s = staffDao.find(vordto.getStaffid());
			if(s==null)
			{
				sr.addMsg("Target Staff Not Found");
				sr.setSucc(false);
				return sr;
			} else
			{
				vo.setStaff(s);

				if(c==null)
				{
					sr.addMsg("Target Customer Not Found");
					sr.setSucc(false);
					return sr;
				} else
				{
					Calendar cld = Calendar.getInstance();
					// Ken Chen 2012/09/29 Add VIP valid year, defalut 9999/12/31
					if(vo.getExpire_year()>0)
					{
						cld.add(Calendar.YEAR, vo.getExpire_year());
					}
					else
					{
						cld.clear();
						cld.set(9999, 11, 31);
					}
					
					c.setVip_valid(cld);
					vo.setStatus(Info.VS_CONIFRMED_NOTPAID);
				}
			}
		}
		
		sr.setSucc(true);
		vipOrderDao.save(vo);
		return sr;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public ServiceResponse complete(long vid) {
		ServiceResponse sr = new ServiceResponse();
		VipOrder vo = vipOrderDao.getVipOrderById(vid);
		if(vo==null)
		{
			sr.addMsg("Target Order Not Found");
			sr.setSucc(false);
			return sr;
		} else
		{
			if(vo.getStatus()!=Info.VS_CONIFRMED)
			{
				sr.addMsg("Only confirmed vip set to be delivered");
				sr.setSucc(false);
				return sr;
			}
			vo.setStatus(Info.VS_DELIVERED);
			sr.setSucc(true);
			return sr;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void setPaid(long vid) {
		VipOrder vo = vipOrderDao.getVipOrderById(vid);
		if(vo==null)
		{
			
		} else
		{
			vo.setPay_status(1);
		}
	}

}
