package com.hino.click.extension;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.apache.click.control.Label;

import com.hino.model.Route;
import com.hino.model.WebMenuRoute;
import com.hino.util.Info;
import com.hino.util.NavigationUtil;

/**
 * This container lists route thumb image and description by 3*n matrix
 */
public class NavigationDiv extends Div{
	private static final long serialVersionUID = -4476718995817808663L;

	/**
	 * Create navigation link
	 * 
	 * @param name name of navigation div
	 * @param httpSession current session contains regid and routeid
	 * @param isRouteList true: current page is RouteList; false: current page is RouteDetail
	 */
	public NavigationDiv(String name, HttpSession httpSession, boolean isRouteList)
	{
		super(name);
		
		/*
		 * <div class="more_top"></div>
	     * <div class="more_bg">
	     * <a href="#">首页</a> >> <a href="route_detail.htm?routeid=123">样本线路</a>
	     * <div style="clear: both"></div>
	     * </div>
	     * <div class="more_bot"></div>
	     */
		
		
		
		Div divMoreTop = new Div("divMoreTop");
		divMoreTop.setCss("more_top");
	     
		Div divMoreBg = new Div("divMoreBg");
		divMoreBg.setCss("more_bg");
	    divMoreBg.add(new Label("navDescLabel", "　"));
	    if(NavigationUtil.hasTopLevel(httpSession)) {
		    Link aLevelOne = new Link("aLevelOne");
		    aLevelOne.setHref(NavigationUtil.getTopLevelAdd(httpSession));
		    aLevelOne.add(new Label("aLinkLabel1", NavigationUtil.getTopLevelName(httpSession)));
		    divMoreBg.add(aLevelOne);
	    }
	    if(NavigationUtil.hasRegionLevel(httpSession)) {
		    divMoreBg.add(new Label("sep1", " >> "));
		    Link aLevelTwo = new Link("aLevelTwo");
		    aLevelTwo.setHref(NavigationUtil.getRegionLevelAdd(httpSession));
		    aLevelTwo.add(new Label("aLinkLabel2", NavigationUtil.getRegionLevelName(httpSession)));
		    divMoreBg.add(aLevelTwo);
	    }
	    if(NavigationUtil.hasRouteLevel(httpSession)) {
		    divMoreBg.add(new Label("sep2", " >> "));
		    Link aLevelThree = new Link("aLevelThree");
		    aLevelThree.setHref(NavigationUtil.getRouteLevelAdd(httpSession));
		    aLevelThree.add(new Label("aLinkLabel3", NavigationUtil.getRouteLevelName(httpSession)));
		    divMoreBg.add(aLevelThree);
	    }
	     
	    Div divMoreBot = new Div("divMoreBot");
	    divMoreBot.setCss("more_bot");
	     
	    this.add(divMoreTop);
	    this.add(divMoreBg);
	    this.add(divMoreBot);
	}

}
