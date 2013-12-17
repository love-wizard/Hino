package com.hino.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import com.hino.util.PriviledgeParser;

public class HinoInvocationSecurityMetadataSource 
	implements FilterInvocationSecurityMetadataSource {
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();;
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public HinoInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        
		/*
		* 板块名                 |网站管理    |旅游资料管理    |导游管理    |销售管理    |导游    |人力资源管理    |市场管理    |财务管理    |地区代表    |销售代表
		* (代表位)    |(9)      |(8)          |(7)      |(6)      |(5)  |(4)          |(3)      |(2)      |(1)      |(0)
		* 系统消息            |√        |√            |√        |√        |√    |√            |√        |√        |√        |√
		* 个人信息            |√        |√            |√        |√        |√    |√            |√        |√        |√        |√
		* 公司战略            |         |             |         |         |     |√            |         |         |         |
		* 市场管理            |         |             |         |         |     |             |√        |         |         |
		* 销售管理            |         |             |         |√        |     |             |         |         |         |
		* 人力管理            |         |             |         |         |     |√            |         |         |         |
		* 财务管理            |         |             |         |         |     |             |         |√        |         |
		* 旅游资料管理  |         |√            |         |         |     |             |         |         |         |
		* 网站管理            |√        |             |         |         |     |             |         |         |         |
		* 导游管理            |         |             |√        |         |     |             |         |         |         |
		* 导游入口            |         |             |         |√        |     |             |         |         |         |
		* 销售代表入口  |         |             |         |         |     |             |         |         |         |√
		* 地区代表入口  |         |             |         |         |     |             |         |         |√        |
		* 用户反馈信息  |         |             |√        |√        |     |√            |√        |         |         |			
		*/
        
        //系统消息
        //resourceMap.put("/internal/msg_email_manage.htm", atts);
        
        //个人信息
        //resourceMap.put("/internal/basic_info_manage.htm", atts);
        //resourceMap.put("/internal/password_manage.htm", atts);
        
        //公司战略
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.HR)));
        resourceMap.put("/internal/area_cam.htm", atts);
        
        //市场管理
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.MARKETING)));
        resourceMap.put("/internal/customer_list.htm", atts);
        resourceMap.put("/internal/customer_email_cam.htm", atts);
        resourceMap.put("/internal/customer_vip_order_record.htm", atts);
        resourceMap.put("/internal/data_export_index.htm", atts);
        resourceMap.put("/internal/tour_survey_manage.htm", atts);

        //销售管理
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.SALES_MANAGE)));
        resourceMap.put("/internal/general_sales_group_cam.htm", atts);
        resourceMap.put("/internal/general_sales_group_manage.htm", atts);
        resourceMap.put("/internal/general_sales_group_booking.htm", atts);
        resourceMap.put("/internal/diy_route_list.htm", atts);
        resourceMap.put("/internal/gen_market_report_manage.htm", atts);
        resourceMap.put("/internal/general_external_booking.htm", atts);
        resourceMap.put("/internal/financial_vip_order_record.htm", atts);
        resourceMap.put("/internal/sales_carrequest_record.htm", atts);
        resourceMap.put("/internal/general_sales_voucher_cam.htm", atts);
        resourceMap.put("/internal/general_sales_voucher_view.htm", atts);
        
        //人力管理
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.HR)));
        resourceMap.put("/internal/staff_create.htm", atts);
        resourceMap.put("/internal/staff_view.htm", atts);
        resourceMap.put("/internal/staff_view_single.htm", atts);
        resourceMap.put("/internal/msg_email_cam.htm", atts);
        resourceMap.put("/internal/regulation_manage.htm", atts);
        
        //财务管理
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.FINIANCE)));
        resourceMap.put("/internal/financial_booking_record.htm", atts);
        resourceMap.put("/internal/financial_transfer_record.htm", atts);
        resourceMap.put("/internal/financial_group_profit_manage.htm", atts);
        resourceMap.put("/internal/financial_group_profit_setting_view.htm", atts);
        
        //旅游资料管理
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.RESOURCE)));
        resourceMap.put("/internal/site_manage.htm", atts);
        resourceMap.put("/internal/site_cam.htm", atts);
        resourceMap.put("/internal/route_manage.htm", atts);
        resourceMap.put("/internal/route_cam.htm", atts);
        resourceMap.put("/internal/vehicle_manage.htm", atts);
        resourceMap.put("/internal/vehicle_cam.htm", atts);
        
        //网站管理
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.WEBMASTER)));
        resourceMap.put("/internal/index_route_cam.htm", atts);
        resourceMap.put("/internal/menu_cam.htm", atts);
        resourceMap.put("/internal/menu_route_cam.htm", atts);
        resourceMap.put("/internal/index_image_setting.htm", atts);
        
        //导游管理
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.GUIDE_MANAGE)));
        resourceMap.put("/internal/general_guide_assign.htm", atts);
        resourceMap.put("/internal/general_guide_resource_cam.htm", atts);
        resourceMap.put("/internal/general_guide_review.htm", atts);
        
        //导游入口
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.GUIDE)));
        resourceMap.put("/internal/guide_group_cam.htm", atts);
        resourceMap.put("/internal/guide_group_manage.htm", atts);
        resourceMap.put("/internal/guide_resource_cam.htm", atts);
        
        //销售代表入口
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.SALES_REP)));
        resourceMap.put("/internal/sales_rep_booking_form.htm", atts);
        resourceMap.put("/internal/sales_rep_booking_select.htm", atts);
        resourceMap.put("/internal/sales_rep_booking_record.htm", atts);
        resourceMap.put("/internal/sales_rep_transfer_form.htm", atts);
        resourceMap.put("/internal/sales_rep_transfer_record.htm", atts);
        resourceMap.put("/internal/sales_market_report_manage.htm", atts);
        resourceMap.put("/internal/sales_rep_order_vip.htm", atts);
        resourceMap.put("/internal/general_sales_voucher_view.htm", atts);
        
        //地区代表入口
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.AREA_REP)));
        resourceMap.put("/internal/area_rep_profit_view.htm", atts);
        resourceMap.put("/internal/area_rep_profit_staff_view.htm", atts);
        
        //用户反馈信息
        atts = new ArrayList<ConfigAttribute>();
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.SALES_MANAGE)));
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.GUIDE_MANAGE)));
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.HR)));
        atts.add(new SecurityConfig(PriviledgeParser.pri_map.get(PriviledgeParser.MARKETING)));
        resourceMap.put("/internal/group_feedback_manage.htm", atts);
        resourceMap.put("/internal/group_feedback_single.htm", atts);
    }

    // According to a URL, Find out permission configuration of this URL.
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        // guess object is a URL.
        String url = ((FilterInvocation)object).getRequestUrl();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (urlMatcher.pathMatchesUrl(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
    
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

}