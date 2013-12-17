package com.hino.click.extension;

import java.util.List;

import org.apache.click.control.AbstractContainer;
import org.apache.click.control.Label;

import com.hino.model.Group;
import com.hino.util.TimeFormater;

/**
 * This container lists groups to show upcoming groups
 */
public class RecentGroupDiv extends Div{
	private static final long serialVersionUID = -5530051183245171756L;

	public RecentGroupDiv(String name, List<Group> groupList, String title, boolean isEn)
	{
		super(name);
		
		// Create group Column 1
		Div divGroupCol1 = new Div("divGroupCol1");
		divGroupCol1.setCss("index_col1");
		
		Head3 groupCol1H3 = new Head3("groupCol1H3");
		groupCol1H3.add(new Label("groupCol1Label", title));
		
		Ul groupCol1Ul = new Ul("groupCol1Ul");
		groupCol1Ul.setCss("ls");
		
		for(int col1LiIndex=0; col1LiIndex<6; col1LiIndex++) {
			Group curGroup = null;
			if(groupList != null && groupList.size()>col1LiIndex)
				curGroup = groupList.get(col1LiIndex);
			
			Li curLi = new Li("col1Li"+col1LiIndex);
			
			Link curLink = new Link("col1Link"+col1LiIndex);
			if(curGroup != null) {
				String groupName = (isEn == true)?curGroup.getName_en():curGroup.getName();
				curLink.add(new Label("col1Label"+col1LiIndex, 
					"["+TimeFormater.format2(curGroup.getDepart_date())+"] "+groupName));
				curLink.setHref("route_detail.htm?routeid="+curGroup.getRoute().getId());
			} else {
				curLink.add(new Label("col1Label"+col1LiIndex, "Comming soon..."));
				curLink.setHref("#");
			}
			
			curLi.add(curLink);
			
			groupCol1Ul.add(curLi);  
		}
		
		divGroupCol1.add(groupCol1H3);
		divGroupCol1.add(groupCol1Ul);

		// Create group Column 2
		Div divGroupCol2 = new Div("divGroupCol2");
		divGroupCol2.setCss("index_col2");
		
		Head3 groupCol2H3 = new Head3("groupCol2H3");
		groupCol2H3.setStyle("background", "url()");
		String moreStr = (isEn == true)?
			"<a href=\"./group_list.htm\">　　　　　　　　　　View All &gt;&gt;</a>":
			"<a href=\"./group_list.htm\">　　　　　　　　　　查看全部    &gt;&gt;</a>";
			
		groupCol2H3.add(new Label("groupCol2Label", moreStr));
		
		Ul groupCol2Ul = new Ul("groupCol2Ul");
		groupCol2Ul.setCss("ls");
		
		for(int col2LiIndex=6; col2LiIndex<12; col2LiIndex++) {
			Group curGroup = null;
			if(groupList != null && groupList.size()>col2LiIndex)
				curGroup = groupList.get(col2LiIndex);
			
			Li curLi = new Li("col1Li"+col2LiIndex);
			
			Link curLink = new Link("col2Link"+col2LiIndex);
			if(curGroup != null) {
				String groupName = (isEn == true)?curGroup.getName_en():curGroup.getName();
				curLink.add(new Label("col2Label"+col2LiIndex, 
					"["+TimeFormater.format2(curGroup.getDepart_date())+"] "+groupName));
				curLink.setHref("route_detail.htm?routeid="+curGroup.getRoute().getId());
			} else {
				curLink.add(new Label("col2Label"+col2LiIndex, "Comming soon..."));
				curLink.setHref("#");
			}
			
			curLi.add(curLink);
			
			groupCol2Ul.add(curLi);  
		}

		divGroupCol2.add(groupCol2H3);
		divGroupCol2.add(groupCol2Ul);
		
		/*
		 * example HTML:
		 *
		 * 	<div class="index_col1">
		 * 	<h3>最新出团线路</h3>
		 * 	<ul class="ls">
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕 </a></li>
		 * 	</ul>
		 * </div>
		 * <div class="index_col2">
		 * 	<h3>　</h3>
		 * 	<ul class="ls">
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 * 		<li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 *      <li><a href="#">[2011/02/03] 新年回国游，标题长也不怕</a></li>
		 *  </ul>
		 * </div>
		 */

		this.add(divGroupCol1);
		this.add(divGroupCol2);
			
	}
	
}
