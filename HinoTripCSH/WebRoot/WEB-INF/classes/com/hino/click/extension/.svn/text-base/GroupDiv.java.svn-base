package com.hino.click.extension;

import java.util.List;

import org.apache.click.control.Label;

import com.hino.model.Group;
import com.hino.util.TimeFormater;

/**
 * This container lists all groups that belongs to this route
 */
public class GroupDiv extends Div{
	private static final long serialVersionUID = 4707266054317818562L;

	/**
	 * 
	 * @param name
	 * @param routeId
	 */
	public GroupDiv(String name, List<Group> groupList, boolean isEn)
	{
		super(name);
		
		/*
		 * <ul class="group_ul">
		 * 	<li class="group_li">
		 * 	<div class="group_lspan">
		 * 		<img src="./images/group_t.png" title="出团时间" />　Feb. 28 2011
		 * 		<img src="./images/group_m.png" title="价格" />　£123
		 * 	</div>
		 * 	<div>
		 * 		<img src="./images/group_p.png" title="接车地点" />　
		 * 		<img src="./images/group_i.png" title="提示信息" />　
		 * 		<a href="#">报名</a>
		 *  </div>
		 * 	</li>
		 * </ul>
	     */

		if(groupList != null && groupList.size() > 0) {
			Ul groupUl = new Ul("groupul");
			groupUl.setCss("group_ul");
			
			for(int index=0; index<groupList.size(); index++) {
				Group curGroup = groupList.get(index);
				if(curGroup.getExternalView()) {
					Li curLi = new Li("groupli"+index);
					curLi.setCss("group_li");
					
					Div leftDiv = new Div("groupl"+index);
					leftDiv.setCss("group_lspan");

					String groupName = (isEn == true)?curGroup.getName_en():curGroup.getName();					
					Label nameLb = new Label("groupnlb"+index, " "+groupName);					
					leftDiv.add(nameLb);
					
					Image timeImg = new Image("groupt"+index);
					if(isEn) {
						timeImg.setSrc("../images/group_t.png");
						timeImg.setTitle("Depart date");
					}
					else {
						timeImg.setSrc("./images/group_t.png");
						timeImg.setTitle("出团时间");
					}
					
					Label timeLb = new Label("grouptlb"+index, " "+TimeFormater.format2(curGroup.getDepart_date())+"　");
					
					leftDiv.add(timeImg);
					leftDiv.add(timeLb);

					Image priceImg = new Image("groupm"+index);
					if(isEn) {
						priceImg.setSrc("../images/group_m.png");
						priceImg.setTitle("Price");
					}
					else {
						priceImg.setSrc("./images/group_m.png");
						priceImg.setTitle("如何成为VIP? 详情请注册并登录系统!");
					}
					
					Label priceLb = new Label("groupmlb"+index, 
						" £"+String.valueOf(curGroup.getPrice())+" / <strong>VIP</strong> £"+String.valueOf(curGroup.getVip_price()));
					leftDiv.add(priceImg);
					leftDiv.add(priceLb);
					
					Div rightDiv = new Div("groupr"+index);
					
					Image pickupImg = new Image("groupp"+index);
					if(isEn) {
						pickupImg.setSrc("../images/group_p.png");
						List<String> city = curGroup.genPickupCity();
						String allCity = "";
						if(city != null && city.size() > 0) {
							for(String curCity : city) {
								if(allCity.length() > 0)
									allCity = allCity + "<br />";
								allCity = allCity + curCity;
							}
						}
						allCity = "Pick up points:<br />"+allCity;
						pickupImg.setTitle(allCity);
					}
					else {
						pickupImg.setSrc("./images/group_p.png");
						List<String> city = curGroup.genPickupCity();
						String allCity = "";
						if(city != null && city.size() > 0) {
							for(String curCity : city) {
								if(allCity.length() > 0)
									allCity = allCity + "<br />";
								allCity = allCity + curCity;
							}
						}
						allCity = "本团提供接车的城市：<br />"+allCity;
						pickupImg.setTitle(allCity);
					}
					
					Label spaceLb = new Label("spacelb"+index, " ");
					
					Image hintImg = new Image("groupi"+index);
					if(isEn) {
						hintImg.setSrc("../images/group_i.png");
						hintImg.setTitle(curGroup.getExternal_indicator_en());
					}
					else {
						hintImg.setSrc("./images/group_i.png");
						hintImg.setTitle(curGroup.getExternal_indicator());
					}
					
					rightDiv.add(pickupImg);
					rightDiv.add(spaceLb);
					rightDiv.add(hintImg);

					String booking = null;
					String full = null;
					String qBooking = null;
					if(isEn) {
						booking = "　Booking";
						full = "　Full";
						qBooking = " Quick Booking";
					}
					else {
						booking = "　报名";
						full = "　已满团";
						qBooking = "　快速报名";
					}
					if(curGroup.getExternalBookable()) {
						Link bookingLink = new Link("booking"+index);
						bookingLink.setHref("./booking.htm?gid="+String.valueOf(curGroup.getId()));
						bookingLink.add(new Label("bookinglb"+index, booking));
						Link qbookingLink = new Link("qbooking"+index);
						qbookingLink.setHref("./booking.htm?gid="+String.valueOf(curGroup.getId()));
						qbookingLink.add(new Label("bookinglb2"+index, qBooking));
						rightDiv.add(bookingLink);
						rightDiv.add(qbookingLink);
					} else {
						rightDiv.add(new Label("fulllb"+index, full));
					}
					
					curLi.add(leftDiv);
					curLi.add(rightDiv);
					groupUl.add(curLi);
				}
				
				this.add(groupUl);
			}
		}
	}

}
