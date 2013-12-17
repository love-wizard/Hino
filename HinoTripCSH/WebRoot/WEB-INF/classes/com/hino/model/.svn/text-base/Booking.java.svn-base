package com.hino.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hino.util.Info;
import com.hino.util.TimeFormater;

@Entity
public class Booking {
	private long id;
	private String bookingRef;

	private Customer customer;
	
	private String firstname;
	private String lastname;
	private String chinesename;
	private String email;
	private String phone;
	private int gender;
	
	private String pickup;
	private String room;
	private String comments;
	
	private Staff rep;
	private int booking_method;
	private Group group;
	
	private Calendar booking_time;
	private int status;
	private boolean refund;
	
	private double pd_credit = 0;
	private int pd_point = 0;
	
	private String ticket_auth;
	private String feedback;
	
	private String voucher;
	private double pd_voucher = 0;
	
	private String repay;
	
	private int book_type = 0; //0=normal //1=groupon 2=seckill
	
	private Customer referer;
	
	public int getBook_type() {
		return book_type;
	}
	public void setBook_type(int bookType) {
		book_type = bookType;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public String getRepay() {
		return repay;
	}
	public void setRepay(String repay) {
		this.repay = repay;
	}
	public double getPd_voucher() {
		return pd_voucher;
	}
	public void setPd_voucher(double pdVoucher) {
		pd_voucher = pdVoucher;
	}

	private int group_type = 0; //normal 0 groupon 1 seckill 2
	
	public int getGroup_type() {
		return group_type;
	}
	public void setGroup_type(int groupType) {
		group_type = groupType;
	}
	
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	private Transfer transfer;
	
	@OneToOne
	public Transfer getTransfer() {
		return transfer;
	}
	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getBookingRef() {
		return bookingRef;
	}
	public void setBookingRef(String bookingRef) {
		this.bookingRef = bookingRef;
	}
	
	@OneToOne
	public Customer getCustomer() {
		return customer;
	}
	
	@OneToOne
	public Customer getReferer() {
		return referer;
	}
	
	public void setReferer(Customer customer) {
		this.referer = customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getChinesename() {
		return chinesename;
	}
	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPickup() {
		return pickup;
	}
	public void setPickup(String pickup) {
		this.pickup = pickup;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@OneToOne
	public Staff getRep() {
		return rep;
	}
	public void setRep(Staff rep) {
		this.rep = rep;
	}

	public int getBooking_method() {
		return booking_method;
	}
	public void setBooking_method(int bookingNethod) {
		booking_method = bookingNethod;
	}
	
	@OneToOne
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getBooking_time() {
		return booking_time;
	}
	public void setBooking_time(Calendar bookingTime) {
		booking_time = bookingTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean getRefund()
	{
		return this.refund;
	}
	public void setRefund(boolean refund)
	{
		this.refund = refund;
	}
	public double getPd_credit() {
		return pd_credit;
	}
	public void setPd_credit(double pdCredit) {
		pd_credit = pdCredit;
	}
	public int getPd_point() {
		return pd_point;
	}
	public void setPd_point(int pdPoint) {
		pd_point = pdPoint;
	}
	
	public String getTicket_auth() {
		return ticket_auth;
	}
	public void setTicket_auth(String ticketAuth) {
		ticket_auth = ticketAuth;
	}
	
	public String genFullname()
	{
		return firstname + " " + lastname;
	}
	
	public String genStatusStr()
	{
		return Info.BKS_STR_CN[status];
	}
	
	public String genGenderStr()
	{
		return (gender==0)?"Male":"Female";
	}
	
	public String genRefundStr()
	{
		return (refund)?"退款":"无";
	}
	
	public String genSalesStr()
	{
		if(rep==null)
		{
			return "无";
		} else
		{
			return rep.getStaffno()+"("+rep.getChinesename()+")";
		}
	}
	
	public String genBookingMethodStr()
	{
		return Info.BKM_STR_CN[booking_method];
	}
	
	public double genTotalPaid()
	{
		return this.pd_credit + this.pd_point/100;
	}
	
	public int genProducePoint()
	{
		return ((int)pd_credit)*5;
	}
	
	public void genTicketAuthCode(){
		
		String toEnc = bookingRef + email + "12345";// Value to encrypt
		
		MessageDigest mdEnc = null;
		try {
			mdEnc = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Encryption algorithm
		
		mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
		
		ticket_auth = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
	}
	
	public String genTicketTitle(boolean inEnglish)
	{
		if(inEnglish)
		{
			return group.getName_en()+ "E-Ticket (" + TimeFormater.format2(group.getDepart_date()) + ")";
		} else
		{
			return group.getName()+ "票据 (" + TimeFormater.format2(group.getDepart_date()) + ")";
		}
		
	}
	
	public double genReward()
	{
		GroupProfit gp = group.getProfit();
		if(gp==null)
		{
			return 0;
		} else
		{
			if(gp.getReward_type()==0)
			{
				return gp.getReward_rate()*this.getPd_credit();
			} else
			{
				return gp.getReward_rate();
			}
			
		}
		
	}
	
	public String genFeedback(int i)
	{
		if(feedback==null)
		{
			return "无反馈";
		}
		
		String[] str = feedback.split("-");
		if(i==0)
		{
			if(str.length>7)
			return str[0] +"-"+ str[1] +"-"+ str[2] +"-"+ str[3] +"-"+ str[4]+"-"+ str[5] +"-"+str[6];
		}
		
		if(i==1)
		{
			if(str.length>8)
			return str[7];
		}
		
		if(i==2)
		{
			if(str.length>9)
			return str[8];
		}
		
		return "数据错误";
	}
	
	public String genBookingSummary()
	{
		//Ken Chen 2012/12/28 Add booking status
		return "订单号：" +this.bookingRef + "\n 订单状态：" + this.genStatusStr() + "\n 订单金额：" + this.pd_credit +  "\n 出游路线：" + group.getRoute().getName() + "\n 出发时期：" + this.group.genDepartDateString() + "\n 游客姓名：" + this.firstname + " " + this.lastname +"("+ this.chinesename+") \n 接车地点：" + this.pickup;
	}
}
