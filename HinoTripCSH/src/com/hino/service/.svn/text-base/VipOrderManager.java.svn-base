package com.hino.service;

import com.hino.dto.VipOrderReserveDto;
import com.hino.util.ServiceResponse;

public interface VipOrderManager {
	public ServiceResponse reserve(VipOrderReserveDto vordto);
	
	public ServiceResponse confirm(long vid);
	
	public ServiceResponse complete(long vid);
	
	public ServiceResponse delete(long vid);
	
	public void setPaid(long vid);
}
