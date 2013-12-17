package com.hino.click.extension;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Label;
import org.apache.click.control.Table;
import org.apache.click.element.JsScript;

import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

/**
 * This container lists all groups that belongs to this route
 */
public class GroupCalendarDiv extends Div{
	private static final long serialVersionUID = 4707266054317818562L;

	/**
	 * 
	 * @param name
	 * @param routeId
	 */
	public GroupCalendarDiv(String name, List<Group> groupList, int year, int month)
	{
		super(name);
		
		/*
		 * <table>
		 * 	<tr>
		 * 		<td width="1%"></td><td width="14%">Mon.</td><td width="14%">Tue.</td><td width="14%">Wed.</td><td width="14%">Thur.</td><td width="14%">Fri.</td><td width="14%">Sat.</td><td width="14%">Sun.</td><td width="1%"></td> 
		 * 	</tr>
		 * 	
		 * 	
		 * </table>
		 */
		
		Div calDivTbl = new Div("gc_tbl");
		
		int lm = 0, rm = 0;
    	int ly = 0, ry = 0;
    	
    	if (month == 0) {
    		lm = 11;
    		ly = year - 1;
    	} else {
    		lm = month - 1;
    		ly = year;
    	}
    	
    	if (month == 11) {
    		rm = 0;
    		ry = year + 1;
    	} else {
    		rm = month + 1;
    		ry = year;
    	}
		
		ActionLink alLeft = new ActionLink("gc_Lfet&y=" + ly + "&m=" + (lm + 1) + "#cal", "< ");
		
		ActionLink alRight = new ActionLink("gc_Right&y=" + ry + "&m=" + (rm + 1) + "#cal", " >");
		
		// 一
		Div calDivTblRow = new Div("gc_yearmonth");
		calDivTblRow.setStyle("padding-left", "1%");
		calDivTblRow.setStyle("padding-right", "1%");
		calDivTblRow.setStyle("text-align", "center");
		
		calDivTblRow.setCss("gc-yearmonth");
		
		Label txtym = new Label("gc_lym", " " + year + " 年   " + (month + 1) + " 月    ");
		calDivTblRow.add(alLeft);
		calDivTblRow.add(txtym);
		calDivTblRow.add(alRight);
		
		calDivTbl.add(calDivTblRow);
		
		// 二 
		calDivTblRow = new Div("gc_header");
		calDivTblRow.setStyle("padding-left", "1%");
		calDivTblRow.setStyle("padding-right", "1%");
		
		String[] hds = {"日", "一", "二", "三", "四", "五", "六"};
		
		for(int i = 1; i <= 7; i ++) {
			Div calDivHd = new Div("gc_header" + i);
			calDivHd.setStyle("float", "left");
			calDivHd.setStyle("width", "14%");
			calDivHd.setStyle("text-align", "center");	
			calDivHd.setStyle("color", "grey");
			
			calDivHd.setCss("gc-header");
			
			Label txtHd = new Label("gc_lhd" + i, hds[i - 1]);			
			calDivHd.add(txtHd);
			
			calDivTblRow.add(calDivHd);
		}
		
		calDivTbl.add(calDivTblRow);
		
		// 三
		Calendar firstDay = Calendar.getInstance();
		Calendar theDay = Calendar.getInstance();
		
		firstDay.set(year, month, 1, 0, 0, 0);
		
		int firstDayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
		
		for(int i = 1; i <= 42; i ++) {
			switch (i % 7) {
				case 1:					
					calDivTblRow = new Div("gc_row" + i / 7);
					calDivTblRow.setStyle("padding-left", "1%");
					calDivTblRow.setStyle("padding-right", "1%");
					
					break;
					
				case 0:
					calDivTbl.add(calDivTblRow);
					
					break;
			}
		
			Div cellWeekDay = new Div("gc_cell" + i);
			cellWeekDay.setStyle("float", "left");
			cellWeekDay.setStyle("width", "14%");
			cellWeekDay.setStyle("text-align", "center");			
			cellWeekDay.setStyle("color", "grey");
			
			cellWeekDay.setCss("gc-cell");
			
			theDay.set(year, month, i - firstDayOfWeek + 1, 0, 0, 0);
			
			Label txtWeekDay = new Label("gc_ld" + i, "" + theDay.get(Calendar.DAY_OF_MONTH));
			
			if (getGroupsOnTheDay(groupList, theDay).size() > 0 ) {
				Div innerCellWeekDay = new Div("gc_innercell" + i);
				innerCellWeekDay.setStyle("width", "99%");
				innerCellWeekDay.setStyle("height", "99%");
				innerCellWeekDay.setStyle("text-align", "center");
				innerCellWeekDay.setStyle("background-color", "white");
				innerCellWeekDay.setCss("gc-innercell");
				innerCellWeekDay.setAttribute("ym", "" + theDay.get(Calendar.YEAR) + "-" + (theDay.get(Calendar.MONTH) + 1) + "-" + theDay.get(Calendar.DAY_OF_MONTH));
				innerCellWeekDay.add(txtWeekDay);
				
				cellWeekDay.add(innerCellWeekDay);
			} else {
				
				cellWeekDay.add(txtWeekDay);
			}

			calDivTblRow.add(cellWeekDay);
		}
		
		this.add(calDivTbl);
	}
	
	public List<Group> getGroupsOnTheDay(List<Group> orderedGroupList, Calendar theDay){
		List<Group> groupsOnTheDay = new ArrayList<Group>();

		for(Group g : orderedGroupList) {
			//System.out.println(g.getDepart_date().getTime() + "  =====   " + theDay.getTime());
			
			if (g.getDepart_date().get(Calendar.YEAR) == theDay.get(Calendar.YEAR) &&
					g.getDepart_date().get(Calendar.MONTH) == theDay.get(Calendar.MONTH) &&
							g.getDepart_date().get(Calendar.DAY_OF_MONTH) == theDay.get(Calendar.DAY_OF_MONTH) ) {
				groupsOnTheDay.add(g);
				System.out.println(g.getDepart_date().getTime());
			}
		}
		
		return groupsOnTheDay;
	}

}
