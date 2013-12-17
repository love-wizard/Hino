package com.hino.service;

import com.hino.model.Group;
import com.hino.model.Voucher;
import com.hino.util.ServiceResponse;

public interface VoucherGroupManager {
	public ServiceResponse check_voucher(Group g, Voucher v);
	public ServiceResponse check_voucher(int gid, String vcode);
	public ServiceResponse check_voucher(Group g, String vcode);
	public ServiceResponse check_voucher(int gid, Voucher v);
	
	public boolean use_voucher(Voucher v);
	public boolean use_voucher(String vcode);
	
}
