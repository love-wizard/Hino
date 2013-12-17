package com.hino.util;

import java.util.Random;

import javax.servlet.http.HttpSession;

/**
 * Navigation link: top level >> region level >> route level
 */
public class NavigationUtil {

	/**
	 * Update top level of navigation link (index/region_list)
	 * Clear region and route level navigation link
	 * @param httpSession current session
	 * @param isIndex is this method invoked by Index
	 */
	public static void updateTopLevel(HttpSession httpSession, boolean isIndex, boolean isEn) {
		String index = (isEn == true)?"Home":"首页";
		String classic = (isEn == true)?"Classic":"精品线路";
		if(isIndex) {
			httpSession.setAttribute("navtopname", index);
			httpSession.setAttribute("navtoplink", "./index.htm");
		}
		else {
			httpSession.setAttribute("navtopname", classic);
			httpSession.setAttribute("navtoplink", "./region_list.htm");
		}

		httpSession.removeAttribute("navregname");
		httpSession.removeAttribute("navreglink");
		httpSession.removeAttribute("navrtname");
		httpSession.removeAttribute("navrtlink");
	}

	/**
	 * Update region level of navigation link
	 * Clear route level navigation link
	 * @param httpSession current session
	 * @param regName region name
	 * @param regId region id
	 */
	public static void updateRegionLevel(HttpSession httpSession, String regName, int regId) {
		httpSession.setAttribute("navregname", regName);
		httpSession.setAttribute("navreglink", "./route_list.htm?regid="+regId);
		
		httpSession.removeAttribute("navrtname");
		httpSession.removeAttribute("navrtlink");
	}

	/**
	 * Update route level of navigation link
	 * @param httpSession current session
	 * @param rtName route name
	 * @param rtId route id
	 */
	public static void updateRouteLevel(HttpSession httpSession, String rtName, int rtId) {
		httpSession.setAttribute("navrtname", rtName);
		httpSession.setAttribute("navrtlink", "./route_detail.htm?routeid="+rtId);
	}
	
	/**
	 * Check if current session contains top level navigation link information
	 * @param httpSession current session
	 * @return true: top level navigation available; false: no top level navigation
	 */
	public static boolean hasTopLevel(HttpSession httpSession) {
		return (httpSession.getAttribute("navtoplink") == null)?false:true;
	}
	
	/**
	 * Get top level navigation link name from session
	 * @param httpSession current session
	 * @return top level link name
	 */
	public static String getTopLevelName(HttpSession httpSession) {
		return (String)(httpSession.getAttribute("navtopname"));
	}
	
	/**
	 * Get top level navigation link address from session
	 * @param httpSession current session
	 * @return top level link address
	 */
	public static String getTopLevelAdd(HttpSession httpSession) {
		return (String)(httpSession.getAttribute("navtoplink"));
	}
	
	/**
	 * Check if current session contains region level navigation link information
	 * @param httpSession current session
	 * @return true: region level navigation available; false: no region level navigation
	 */
	public static boolean hasRegionLevel(HttpSession httpSession) {
		return (httpSession.getAttribute("navreglink") == null)?false:true;
	}
	
	/**
	 * Get region level navigation link name from session
	 * @param httpSession current session
	 * @return region level link name
	 */
	public static String getRegionLevelName(HttpSession httpSession) {
		return (String)(httpSession.getAttribute("navregname"));
	}
	
	/**
	 * Get region level navigation link address from session
	 * @param httpSession current session
	 * @return region level link address
	 */
	public static String getRegionLevelAdd(HttpSession httpSession) {
		return (String)(httpSession.getAttribute("navreglink"));
	}
	
	/**
	 * Check if current session contains route level navigation link information
	 * @param httpSession current session
	 * @return true: route level navigation available; false: no route level navigation
	 */
	public static boolean hasRouteLevel(HttpSession httpSession) {
		return (httpSession.getAttribute("navrtlink") == null)?false:true;
	}
	
	/**
	 * Get route level navigation link name from session
	 * @param httpSession current session
	 * @return route level link name
	 */
	public static String getRouteLevelName(HttpSession httpSession) {
		return (String)(httpSession.getAttribute("navrtname"));
	}
	
	/**
	 * Get route level navigation link address from session
	 * @param httpSession current session
	 * @return route level link address
	 */
	public static String getRouteLevelAdd(HttpSession httpSession) {
		return (String)(httpSession.getAttribute("navrtlink"));
	}
}
