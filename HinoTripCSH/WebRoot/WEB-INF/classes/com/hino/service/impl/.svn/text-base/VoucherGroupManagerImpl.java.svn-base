package com.hino.service.impl;

import java.util.Calendar;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.Voucher;
import com.hino.service.VoucherGroupManager;
import com.hino.util.ServiceResponse;

public class VoucherGroupManagerImpl implements VoucherGroupManager {

	@Override
	public ServiceResponse check_voucher(Group g, Voucher v) {
		ServiceResponse sr=new ServiceResponse();
		if (v==null)
		{
			System.out.println("e1");
			sr.setSucc(false);
			sr.addMsg("voucher not found");
			return sr;
			
		}
		
		if (g==null)
		{
			System.out.println("e2");
			sr.setSucc(false);
			sr.addMsg("target group not found");
			return sr;
		}
		
		if (Calendar.getInstance().after(v.getExpire_date()))
		{
			System.out.println("e3");
			sr.setSucc(false);
			sr.addMsg("expired");
			return sr;
		}
		
		if (v.getNo_used()>=v.getNo_of_use())
		{
			System.out.println("e32");
			sr.setSucc(false);
			sr.addMsg("no usage");
			//Devon King - indicate voucher is used out
			sr.addInfo(1);
			return sr;
		}
		
		if (v.getMatch_type().equals(""))
		{
			System.out.println("e4");
			sr.setSucc(true);
			sr.setResponse(v);
		} else 
		if(g.getVoucher_match()!=null&&g.getVoucher_match().contains(v.getMatch_type()))
		{
			System.out.println("e5");
			sr.setSucc(true);
			sr.setResponse(v);
		} else
		{
			System.out.println("e6");
			sr.setSucc(false);
			sr.addMsg("voucher limitation for this group");
		}
		return sr;
	}
	
	@Override
	public ServiceResponse check_voucher(int gid, String vcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse check_voucher(Group g, String vcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse check_voucher(int gid, Voucher v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED ,readOnly=false, isolation=Isolation.READ_COMMITTED)
	public boolean use_voucher(Voucher v) {
		if (v.getNo_used()<v.getNo_of_use())
		{
			v.setNo_used(v.getNo_used()+1); return true;
		}
		else {return false;}
		
	}

	@Override
	public boolean use_voucher(String vcode) {
		// TODO Auto-generated method stub
		return false;
	}

}
