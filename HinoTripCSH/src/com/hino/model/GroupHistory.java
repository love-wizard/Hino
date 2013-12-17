package com.hino.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

import com.hino.util.Info;
import com.hino.util.TimeFormater;

@Entity
@Table(name = "t_group_history")
public class GroupHistory {
	private int id;	
	private int group_id;	
	private String name;
	private String name_en;
	private Calendar audit_time;
	
	private Route route;
	private Calendar depart_date;
	private String ticket_url;

	private int status = 0;//0 planning; 1 opening; 2 processing; 3 finished; 4 canceled;
	
	private int max_point = 0;
	private String pickup_info;
	private String traffic_hotel_info;

	private boolean profit_ready = false;
	private GroupProfit profit;

	private boolean guide_ready = false;
	private boolean caution_ready = false;
	
	private String caution;
	
	private Staff guide1;
	private Staff guide2;

	private boolean multi_days = true;
	
	private String voucher_match;
	//normal group feature
	private int seats = 0;
	private int seats_reserved = 0;
	private int seats_taken = 0;
	private double price;
	private double vip_price;
	
	private String external_indicator = "火热报名中!";
	private String external_indicator_en = "Openning!";
	
	private boolean internalBookable = true;
	private boolean externalBookable = true;
	private boolean internalView = true;
	private boolean externalView = true;
	
	//groupon group feature
	private boolean ext_groupon = false;
	private boolean int_groupon = false;
	private int seats_groupon_min = 0;
	private int seats_groupon = 0;
	private int seats_taken_groupon = 0;
	private int seats_reserved_groupon = 0;
	private double group_price;
	private double group_vip_price;
	private Calendar groupon_end_time;
	
	//seckill group feature
	private boolean ext_seckill = false;
	private boolean int_seckill = false;
	private int seats_seckill = 0;
	private int seats_taken_seckill = 0;
	private int seats_reserved_seckill = 0;
	private double seckill_price;
	private double seckill_vip_price;
	private Calendar seckill_start_time;
	private Calendar seckill_end_time;
	
	private String go_img_1;
	private String go_img_2;
	private String sk_img_1;
	private String sk_img_2;
	
	//Devon King - 2012/09/30 - TD#20 Add a new column of voucher_applied
	private String voucher_applied = "N";
	
	private int priority;
	
	public GroupHistory() {
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getVoucher_applied() {
		return voucher_applied;
	}

	public void setVoucher_applied(String voucher_applied) {
		//Ken Chen - 2012/10/09 Hibernate get null from db
		if(voucher_applied!=null && voucher_applied.length()!=0)
		{
			this.voucher_applied = voucher_applied;
		}
		else
		{
			this.voucher_applied = "N";
		}
	}

	public double getGroup_vip_price() {
		return group_vip_price;
	}

	public void setGroup_vip_price(double groupVipPrice) {
		group_vip_price = groupVipPrice;
	}

	public double getSeckill_vip_price() {
		return seckill_vip_price;
	}

	public void setSeckill_vip_price(double seckillVipPrice) {
		seckill_vip_price = seckillVipPrice;
	}

	public long genTM_go_end()
	{
		if(groupon_end_time==null)
			return 0L;
		return groupon_end_time.getTimeInMillis();
	}
	
	public long genTM_sk_end()
	{
		if(seckill_end_time==null)
			return 0L;
		return seckill_end_time.getTimeInMillis();
	}
	
	public long genTM_sk_start()
	{
		if(seckill_start_time==null)
			return 0L;
		return seckill_start_time.getTimeInMillis();
	}
	
	public String getGo_img_1() {
		return go_img_1;
	}

	public void setGo_img_1(String goImg_1) {
		go_img_1 = goImg_1;
	}

	public String getGo_img_2() {
		return go_img_2;
	}

	public void setGo_img_2(String goImg_2) {
		go_img_2 = goImg_2;
	}

	public String getSk_img_1() {
		return sk_img_1;
	}

	public void setSk_img_1(String skImg_1) {
		sk_img_1 = skImg_1;
	}

	public String getSk_img_2() {
		return sk_img_2;
	}

	public void setSk_img_2(String skImg_2) {
		sk_img_2 = skImg_2;
	}

	public int getSeats_groupon_min() {
		return seats_groupon_min;
	}

	public void setSeats_groupon_min(int seatsGrouponMin) {
		seats_groupon_min = seatsGrouponMin;
	}

	public int getSeats_groupon() {
		return seats_groupon;
	}

	public void setSeats_groupon(int seatsGroupon) {
		seats_groupon = seatsGroupon;
	}

	public int getSeats_seckill() {
		return seats_seckill;
	}

	public void setSeats_seckill(int seatsSeckill) {
		seats_seckill = seatsSeckill;
	}

	public int getSeats_taken_groupon() {
		return seats_taken_groupon;
	}

	public void setSeats_taken_groupon(int seatsTakenGroupon) {
		seats_taken_groupon = seatsTakenGroupon;
	}

	public int getSeats_taken_seckill() {
		return seats_taken_seckill;
	}

	public void setSeats_taken_seckill(int seatsTakenSeckill) {
		seats_taken_seckill = seatsTakenSeckill;
	}

	public String getVoucher_match() {
		return voucher_match;
	}

	public void setVoucher_match(String voucherMatch) {
		voucher_match = voucherMatch;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String nameEn) {
		name_en = nameEn;
	}

	public double getVip_price() {
		return vip_price;
	}

	public void setVip_price(double vipPrice) {
		vip_price = vipPrice;
	}

	public int getMax_point() {
		return max_point;
	}

	public void setMax_point(int maxPoint) {
		max_point = maxPoint;
	}
	
	public boolean getExt_groupon() {
		return ext_groupon;
	}

	public void setExt_groupon(boolean extGroupon) {
		ext_groupon = extGroupon;
	}

	public boolean getExt_seckill() {
		return ext_seckill;
	}

	public void setExt_seckill(boolean extSeckill) {
		ext_seckill = extSeckill;
	}

	public boolean getInt_groupon() {
		return int_groupon;
	}

	public void setInt_groupon(boolean intGroupon) {
		int_groupon = intGroupon;
	}

	public boolean getInt_seckill() {
		return int_seckill;
	}

	public void setInt_seckill(boolean intSeckill) {
		int_seckill = intSeckill;
	}

	public double getGroup_price() {
		return group_price;
	}

	public void setGroup_price(double groupPrice) {
		group_price = groupPrice;
	}

	public double getSeckill_price() {
		return seckill_price;
	}

	public void setSeckill_price(double seckillPrice) {
		seckill_price = seckillPrice;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="group_guide",
		joinColumns={@JoinColumn(name="group_id")},
		inverseJoinColumns={@JoinColumn(name="guide_id")}
		)
	public Set<Staff> getApplying_guide() {
		return applying_guide;
	}
	
	public void setApplying_guide(Set<Staff> applyingGuide) {
		applying_guide = applyingGuide;
	}

	private Set<Staff> applying_guide;

	private String guide1ReportUrl;
	private String guide2ReportUrl;
	private String guide1ReviewUrl;
	private String guide2ReviewUrl;

	public boolean getInternalView() {
		return internalView;
	}
	public void setInternalView(boolean internalView) {
		this.internalView = internalView;
	}
	public boolean getExternalView() {
		return externalView;
	}
	public void setExternalView(boolean externalView) {
		this.externalView = externalView;
	}
	
	public int getSeats_reserved() {
		return seats_reserved;
	}
	public void setSeats_reserved(int seatsReserved) {
		seats_reserved = seatsReserved;
	}
	public boolean getCaution_ready() {
		return caution_ready;
	}
	public void setCaution_ready(boolean cautionReady) {
		caution_ready = cautionReady;
	}
	public String getCaution() {
		return caution;
	}
	public void setCaution(String caution) {
		this.caution = caution;
	}
	
	public String getExternal_indicator() {
		return external_indicator;
	}
	public void setExternal_indicator(String externalIndicator) {
		external_indicator = externalIndicator;
	}
	
	public boolean getInternalBookable() {
		return internalBookable;
	}
	public void setInternalBookable(boolean internalBookable) {
		this.internalBookable = internalBookable;
	}
	public boolean getExternalBookable() {
		return externalBookable;
	}
	public void setExternalBookable(boolean externalBookable) {
		this.externalBookable = externalBookable;
	}
	
	public String getTicket_url() {
		return ticket_url;
	}
	public void setTicket_url(String ticketUrl) {
		ticket_url = ticketUrl;
	}
	
	public String getExternal_indicator_en() {
		return external_indicator_en;
	}

	public void setExternal_indicator_en(String externalIndicatorEn) {
		external_indicator_en = externalIndicatorEn;
	}

	public Calendar getDepart_date() {
		return depart_date;
	}
	public boolean getGuide_ready() {
		return guide_ready;
	}

	@OneToOne
	public Staff getGuide1() {
		return guide1;
	}

	public String getGuide1ReportUrl() {
		return guide1ReportUrl;
	}

	public String getGuide1ReviewUrl() {
		return guide1ReviewUrl;
	}

	@OneToOne
	public Staff getGuide2() {
		return guide2;
	}

	public String getGuide2ReportUrl() {
		return guide2ReportUrl;
	}

	public String getGuide2ReviewUrl() {
		return guide2ReviewUrl;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPickup_info() {
		return pickup_info;
	}
	public String genPickup_info() {
		return  pickup_info.replaceAll("(\r?\n()+)", "");
		
		
	}
	public double getPrice() {
		return price;
	}

	@OneToOne
	public GroupProfit getProfit() {
		return profit;
	}

	public boolean getProfit_ready() {
		return profit_ready;
	}


	@OneToOne
	public Route getRoute() {
		return route;
	}

	public int getSeats() {
		return seats;
	}

	public int getSeats_taken() {
		return seats_taken;
	}

	public int getStatus() {
		return status;
	}

	public String getTraffic_hotel_info() {
		return traffic_hotel_info;
	}

	public void setDepart_date(Calendar departDate) {
		depart_date = departDate;
	}

	public void setGuide_ready(boolean guideReady) {
		guide_ready = guideReady;
	}

	public void setGuide1(Staff guide1) {
		this.guide1 = guide1;
	}

	public void setGuide1ReportUrl(String guide1ReportUrl) {
		this.guide1ReportUrl = guide1ReportUrl;
	}

	public void setGuide1ReviewUrl(String guide1ReviewUrl) {
		this.guide1ReviewUrl = guide1ReviewUrl;
	}

	public void setGuide2(Staff guide2) {
		this.guide2 = guide2;
	}

	public void setGuide2ReportUrl(String guide2ReportUrl) {
		this.guide2ReportUrl = guide2ReportUrl;
	}

	public void setGuide2ReviewUrl(String guide2ReviewUrl) {
		this.guide2ReviewUrl = guide2ReviewUrl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPickup_info(String pickupInfo) {
		pickup_info = pickupInfo;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProfit(GroupProfit profit) {
		this.profit = profit;
	}

	public void setProfit_ready(boolean profitReady) {
		profit_ready = profitReady;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public void setSeats_taken(int seatsTaken) {
		seats_taken = seatsTaken;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTraffic_hotel_info(String trafficHotelInfo) {
		traffic_hotel_info = trafficHotelInfo;
	}
	
	public int getSeats_reserved_groupon() {
		return seats_reserved_groupon;
	}

	public void setSeats_reserved_groupon(int seatsReservedGroupon) {
		seats_reserved_groupon = seatsReservedGroupon;
	}

	public Calendar getGroupon_end_time() {
		return groupon_end_time;
	}

	public void setGroupon_end_time(Calendar grouponEndTime) {
		groupon_end_time = grouponEndTime;
	}

	public int getSeats_reserved_seckill() {
		return seats_reserved_seckill;
	}

	public void setSeats_reserved_seckill(int seatsReservedSeckill) {
		seats_reserved_seckill = seatsReservedSeckill;
	}

	public Calendar getSeckill_start_time() {
		return seckill_start_time;
	}

	public void setSeckill_start_time(Calendar seckillStartTime) {
		seckill_start_time = seckillStartTime;
	}

	public Calendar getSeckill_end_time() {
		return seckill_end_time;
	}

	public void setSeckill_end_time(Calendar seckillEndTime) {
		seckill_end_time = seckillEndTime;
	}

	public boolean getMulti_days() {
		return multi_days;
	}

	public void setMulti_days(boolean multiDays) {
		multi_days = multiDays;
	}

	public String genStatusStr() {
		return Info.GS_STR_CN[status];
	}
	
	public String genBookingInfo()
	{
		//Kevin Zhong - TD#4
		return name + "/ " + name_en + " " + TimeFormater.format2(depart_date)+ " Total" + seats + " /Unpaid " + seats_reserved +"/Confirm" + seats_taken  + "/Available" + (seats - seats_reserved - seats_taken);
	}
	
	//+Kevin Zhong - TD#4
	public String genGroupBookingInfo()
	{		
		return name + "/ " + name_en + " " + TimeFormater.format2(depart_date)+ " Total" + seats_groupon + " /Unpaid " + seats_reserved_groupon +"/Confirm" + seats_taken_groupon  + "/Available" + (seats_groupon - seats_reserved_groupon - seats_taken_groupon);
	}
	//-Kevin Zhong - TD#4
	
	public String genBookingInfo2()
	{
		return name + "/ " + name_en + " " + TimeFormater.format2(depart_date) + " Unpaid " + (seats_reserved+seats_reserved_groupon+seats_reserved_seckill)+"/Confirm" + (seats_taken+seats_taken_groupon+seats_taken_seckill) + "/Total" + (seats+seats_seckill+seats_groupon);
	}
	
	public List<String> genPickupFull()
	{
		ArrayList<String> al = new ArrayList<String>();; 
		if(pickup_info==null)
		{
			al.add("Pick up hasn't been set");
		} else
		{
			String[] p = pickup_info.split(";");
			for (int x=0;x<p.length;x++)
			{
				al.add(p[x]);
			}
		}
		return al;
	}
	
	public List<String> genPickupCity()
	{
		ArrayList<String> al = new ArrayList<String>();
		String temp = "";
		if(pickup_info==null)
		{
			al.add("Pick up hasn't been set");
		} else
		{
			String[] p = pickup_info.split(";");
			for (int x=0;x<p.length;x++)
			{
				temp = p[x].split("-")[0].trim();
				if(!al.contains(temp))
				{
					//System.out.println("dosen't contains " + temp);
					al.add(temp);
				}
			}
		}
		//System.out.println(al);
		return al;
	}
	
	public List<String> genFuzzyPickupCity()
	{
		ArrayList<String> al = new ArrayList<String>();
		String temp = "";
		if(pickup_info==null)
		{
			al.add("Pick up hasn't been set");
		} else
		{
			String[] p = pickup_info.split(";");
			String[] w;
			for (int x=0;x<p.length;x++)
			{
				temp = p[x].split("-")[0].trim();
				w = temp.split(" ");
				for(int y = 0; y < w.length; y ++) {
					temp = w[y].trim();
					if(!al.contains(temp)){
						//System.out.println("dosen't contains " + temp);
						al.add(temp);
					}
				}
			}
		}
		//System.out.println(al);
		return al;
	}
	
	public String genDepartDateString()
	{
		return TimeFormater.format2(depart_date);
	}
	
	public String genProfitStatus()
	{
		return profit_ready?"清算结束":"未清算";
	}
	
	public boolean booking_reserve(int x)
	{
		if(status==Info.GS_OPENNING)
		{
			//Devon King - 
			if (seats_taken + seats_reserved + x > seats)
				return false;
			else 
				seats_reserved = seats_reserved + x;
		} else
		{
			return false;
		}
		return true;
	}
	
	public boolean booking_delete(int x)
	{
		//Devon King - Avoiding to be a negative value
		if (seats_reserved - x < 0) {
			return false;
		}
			
		seats_reserved = seats_reserved - x;
		return true;
	}
	
	public boolean booking_confirm(int x)
	{
		if(status==Info.GS_OPENNING)
		{
			if((seats_taken+x)>seats)
			{
				return false;
			} else
			{
				seats_taken = seats_taken + x;
				seats_reserved = seats_reserved - x;
			} 
		} else
		{
			return false;
		}
		return true;
	}
	
	public boolean booking_cancel(int x)
	{
		//Devon King - Avoiding to be a negative value
		if (seats - x < 0) {
			return false;
		}
		
		seats_taken = seats_taken - x;
		return true;
	}
	
	public boolean go_booking_cancel(int x)
	{
		//Devon King - Avoiding to be a negative value
		if (seats_taken_groupon - x < 0) {
			return false;
		}
		
		seats_taken_groupon = seats_taken_groupon - x;
		return true;
	}
	
	public boolean go_booking_reserve(int x)
	{
		if(status==Info.GS_OPENNING&&Calendar.getInstance().before(this.groupon_end_time))
		{
			seats_reserved_groupon = seats_reserved_groupon + x;
		} else
		{
			return false;
		}
		return true;
	}
	
	public boolean go_booking_delete(int x)
	{
		//Devon King - Avoiding to be a negative value
		if(seats_reserved_groupon - x < 0) {
			return false;
		}
		
		seats_reserved_groupon = seats_reserved_groupon - x;
		return true;
	}
	
	public boolean go_booking_confirm(int x)
	{
		if(status==Info.GS_OPENNING)
		{
			if((seats_taken_groupon+x)>seats_groupon)
			{
				return false;
			} else
			{
				seats_taken_groupon = seats_taken_groupon + x;
				seats_reserved_groupon = seats_reserved_groupon - x;
			} 
		} else
		{
			return false;
		}
		return true;
	}
	
	public boolean sk_booking_reserve(int x)
	{
		if(status==Info.GS_OPENNING&&Calendar.getInstance().before(seckill_end_time)&&Calendar.getInstance().after(seckill_start_time))
		{
			seats_reserved_seckill = seats_reserved_seckill + x;
		} else
		{
			return false;
		}
		return true;
	}
	
	public boolean sk_booking_delete(int x)
	{
		//Devon King - Avoiding to be a negative value
		if (seats_reserved_seckill - x < 0) {
			return false;
		}
		
		seats_reserved_seckill = seats_reserved_seckill - x;
		return true;
	}
	
	public boolean sk_booking_confirm(int x)
	{
		if(status==Info.GS_OPENNING)
		{
			if((seats_taken_seckill+x)>seats_seckill)
			{
				return false;
			} else
			{
				seats_taken_seckill = seats_taken_seckill + x;
				seats_reserved_seckill = seats_reserved_seckill - x;
			} 
		} else
		{
			return false;
		}
		return true;
	}
	
	public boolean sk_booking_cancel(int x)
	{
		//Devon King - Avoiding to be a negative value
		if (seats_taken_seckill - x < 0) {
			return false;
		}
		
		seats_taken_seckill = seats_taken_seckill - x;
		return true;
	}
	
	public String go_dis_rate()
	{
		double xx = group_price/price*100;
		BigDecimal   b=new   BigDecimal(xx); 
	      return ""+b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"%"; 

	}
	
	public String go_dis_save()
	{
		double xx = price-group_price;
		
	      return "" +xx; 

	}
	
	public String gen_depart_date()
	{
		return TimeFormater.format2(depart_date);
	}
	
	public String day_diff()
	{
       long tm = groupon_end_time.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        
        int ms = (int)(tm%1000); tm /= 1000;
        int sc = (int)(tm%60);   tm /= 60;
        int mn = (int)(tm%60);   tm /= 60;
        int hr = (int)(tm%24); 
        long dy = tm/24;
        return ""+dy + "天";
	}
	
	public boolean genSK_over()
	{
		if(seats_taken_seckill>=seats_seckill)
		{
			return true;
		} else return false;
	}
	
	public int gen_seats_for_go()
	{
		return this.seats_taken_groupon+this.seats_reserved_groupon;
	}
	
	public int gen_seats_remin_sk()
	{
		return this.seats_seckill-seats_taken_seckill;
	}
	
	public int gen_all_seats_for_go()
	{
		return this.seats_taken_groupon+this.seats_reserved_groupon+this.seats_reserved+this.seats_reserved_seckill+this.seats_taken+this.seats_taken_seckill;
	}
	
	public int genGroupTypeCode(){
		String routeName = route.getName();
		String s[] = routeName.split("【|】");
		if(s.length>1)
		{
			String routeType = s[1];
			
			if("常规团".equalsIgnoreCase(routeType)){
				return Info.ROUTE_TYPE_CGT;
			}else if ("精品小团".equalsIgnoreCase(routeType)) {
				return Info.ROUTE_TYPE_JPXT;
			}else if ("团购".equalsIgnoreCase(routeType)) {
				return Info.ROUTE_TYPE_CZTG;
			}else if ("超值团购".equalsIgnoreCase(routeType)) {
				return Info.ROUTE_TYPE_CZTG;
			}else if ("DIY".equalsIgnoreCase(routeType)) {
				return Info.ROUTE_TYPE_DIY;
			}else if ("热门推荐".equalsIgnoreCase(routeType)) {
				return Info.ROUTE_TYPE_RMTJ;
			}else {
				return Info.ROUTE_TYPE_CGT;
			}
		}
		
		return Info.ROUTE_TYPE_CGT;
	}
	
	public int genRemainGoSeats(){
		return this.getSeats_groupon() - this.gen_seats_for_go();
	}
	
	public String genMobileGroupName(){
		String oname = this.name; 
		return Info.cutString(oname, 16, "...");
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public Calendar getAudit_time() {
		return audit_time;
	}

	public void setAudit_time(Calendar audit_time) {
		this.audit_time = audit_time;
	}
	
	public GroupHistory SetGroupHistory(Group g)
	{
		GroupHistory gh = new GroupHistory();
		
		gh.name = g.getName();
		gh.name_en = g.getName_en();
		gh.route = g.getRoute();
		gh.depart_date = g.getDepart_date();
		gh.ticket_url = g.getTicket_url();
		gh.status = g.getStatus();
		gh.max_point = g.getMax_point();
		gh.pickup_info = g.getPickup_info();
		gh.traffic_hotel_info = g.getTraffic_hotel_info();
		gh.profit_ready = g.getProfit_ready();
		gh.profit = g.getProfit();
		gh.guide_ready = g.getGuide_ready();
		gh.caution_ready = g.getCaution_ready();
		gh.caution = g.getCaution();
		gh.guide1 = g.getGuide1();
		gh.guide2 = g.getGuide2();
		gh.multi_days = g.getMulti_days();
		gh.voucher_match = g.getVoucher_match();
		gh.seats = g.getSeats();
		gh.seats_reserved = g.getSeats_reserved();
		gh.seats_taken = g.getSeats_taken();
		gh.price = g.getPrice();
		gh.vip_price = g.getVip_price();
		
		gh.external_indicator = g.getExternal_indicator();
		gh.external_indicator_en = g.getExternal_indicator_en();
		
		gh.internalBookable = g.getInternalBookable();
		gh.externalBookable = g.getExternalBookable();
		gh.internalView = g.getInternalView();
		gh.externalView = g.getExternalView();
		gh.ext_groupon = g.getExt_groupon();
		gh.int_groupon = g.getInt_groupon();
		gh.seats_groupon_min = g.getSeats_groupon_min();
		gh.seats_groupon = g.getSeats_groupon();
		gh.seats_taken_groupon = g.getSeats_taken_groupon();
		gh.seats_reserved_groupon = g.getSeats_reserved_groupon();
		gh.group_price = g.getGroup_price();
		gh.group_vip_price = g.getGroup_vip_price();
		gh.groupon_end_time = g.getGroupon_end_time();
		
		//seckill group feature
		gh.ext_seckill = g.getExt_seckill();
		gh.int_seckill = g.getInt_seckill();
		gh.seats_seckill = g.getSeats_seckill();
		gh.seats_taken_seckill = g.getSeats_taken_seckill();
		gh.seats_reserved_seckill = g.getSeats_reserved_seckill();
		gh.seckill_price = g.getSeckill_price();
		gh.seckill_vip_price = g.getSeckill_vip_price();
		gh.seckill_start_time = g.getSeckill_start_time();
		gh.seckill_end_time = g.getSeckill_end_time();
		
		gh.go_img_1 = g.getGo_img_1();
		gh.go_img_2 = g.getGo_img_2();
		gh.sk_img_1 = g.getSk_img_1();
		gh.sk_img_2 = g.getSk_img_2();
		
		gh.voucher_applied = g.getVoucher_applied();
		
		gh.priority = g.getPriority();
		
		gh.group_id = g.getId();
		Calendar realtime = Calendar.getInstance();;
		gh.audit_time = realtime;
		
		return gh;
		
	}
	

	public GroupHistory(Group g) {
	{
		this.name = g.getName();
		this.name_en = g.getName_en();
		this.route = g.getRoute();
		this.depart_date = g.getDepart_date();
		this.ticket_url = g.getTicket_url();
		this.status = g.getStatus();
		this.max_point = g.getMax_point();
		this.pickup_info = g.getPickup_info();
		this.traffic_hotel_info = g.getTraffic_hotel_info();
		this.profit_ready = g.getProfit_ready();
		this.profit = g.getProfit();
		this.guide_ready = g.getGuide_ready();
		this.caution_ready = g.getCaution_ready();
		this.caution = g.getCaution();
		this.guide1 = g.getGuide1();
		this.guide2 = g.getGuide2();
		this.multi_days = g.getMulti_days();
		this.voucher_match = g.getVoucher_match();
		this.seats = g.getSeats();
		this.seats_reserved = g.getSeats_reserved();
		this.seats_taken = g.getSeats_taken();
		this.price = g.getPrice();
		this.vip_price = g.getVip_price();
		
		this.external_indicator = g.getExternal_indicator();
		this.external_indicator_en = g.getExternal_indicator_en();
		
		this.internalBookable = g.getInternalBookable();
		this.externalBookable = g.getExternalBookable();
		this.internalView = g.getInternalView();
		this.externalView = g.getExternalView();
		this.ext_groupon = g.getExt_groupon();
		this.int_groupon = g.getInt_groupon();
		this.seats_groupon_min = g.getSeats_groupon_min();
		this.seats_groupon = g.getSeats_groupon();
		this.seats_taken_groupon = g.getSeats_taken_groupon();
		this.seats_reserved_groupon = g.getSeats_reserved_groupon();
		this.group_price = g.getGroup_price();
		this.group_vip_price = g.getGroup_vip_price();
		this.groupon_end_time = g.getGroupon_end_time();
		
		//seckill group feature
		this.ext_seckill = g.getExt_seckill();
		this.int_seckill = g.getInt_seckill();
		this.seats_seckill = g.getSeats_seckill();
		this.seats_taken_seckill = g.getSeats_taken_seckill();
		this.seats_reserved_seckill = g.getSeats_reserved_seckill();
		this.seckill_price = g.getSeckill_price();
		this.seckill_vip_price = g.getSeckill_vip_price();
		this.seckill_start_time = g.getSeckill_start_time();
		this.seckill_end_time = g.getSeckill_end_time();
		
		this.go_img_1 = g.getGo_img_1();
		this.go_img_2 = g.getGo_img_2();
		this.sk_img_1 = g.getSk_img_1();
		this.sk_img_2 = g.getSk_img_2();
		
		this.voucher_applied = g.getVoucher_applied();
		
		this.priority = g.getPriority();
		
		this.group_id = g.getId();
		Calendar realtime = Calendar.getInstance();;
		this.audit_time = realtime;
		
	}
	}
}
