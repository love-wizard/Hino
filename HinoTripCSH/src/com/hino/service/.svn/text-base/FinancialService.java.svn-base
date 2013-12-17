package com.hino.service;

import java.util.Calendar;
import java.util.List;

import com.hino.dto.FinancialProfitFeedbackDto;
import com.hino.dto.SalesRewardDto;
import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.GroupProfit;
import com.hino.model.Staff;
import com.hino.model.Transfer;
import com.hino.model.VipOrder;
import com.hino.util.ServiceResponse;

public interface FinancialService {
	public ServiceResponse modify_booking_price(long id, double price, Staff s);
	public ServiceResponse confirm_booking(long id);
	public ServiceResponse cancel_booking(long id);
	public ServiceResponse refuse_booking(long id);
	public ServiceResponse delete_booking(long id);
	public ServiceResponse view_booking(long id);
	
	public ServiceResponse transfer_declare(List<Long> bl, Staff s, Calendar tt, Calendar dt, double amount, String ref, String tranm, String payment);
	public ServiceResponse transfer_refuse(long tid, Staff s, String feedback);
	public ServiceResponse transfer_delete(long tid, Staff s);
	public ServiceResponse transfer_confirm(long tid, String feedback);
	public ServiceResponse transfer_booking_view(long tid, Staff s);
	public void transfer_feedback(long tid, String fb);
	
	public List<Transfer> transferSearch(int type, Object crit, Integer[] status, long pid, int page, int size);
	public int transferSearchCount(int type, Object crit, Integer[] status, long pid);
	public List<Booking> bookingSearch(int attrType, String keyword, int sid, Integer[] status, int size, int page);
	public int bookingSearchCount(int attrType, String keyword, int sid, Integer[] status);
	
	public List<Group> profit_group_list(Integer[] status, Boolean[] ready,int page, int size);
	public int profit_group_list_count(Integer[] status, Boolean[] ready);
	
	public boolean profit_finish_setting(int gid);
	public boolean profit_save(int gid, GroupProfit gp);
	
	public FinancialProfitFeedbackDto profit_reload_sales(int gid);
	
	public List<SalesRewardDto> sales_summary(Calendar start, Calendar end);
	public List<SalesRewardDto> sales_summary_forarea(int gid, int areaid);
	public List<SalesRewardDto> sales_summary_forLeader(int leaderId, Calendar start, Calendar end);
	
	public List<VipOrder> list_vip_order(String email, Integer[] status, int page, int size);
	public long count_list_vip_order(String email, Integer[] status);
	public ServiceResponse vip_order_confirm(long vid);
	public ServiceResponse vip_order_delete(long vid);
	public void vip_set_paid(long vid);
	
}
