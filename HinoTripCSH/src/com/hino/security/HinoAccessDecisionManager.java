package com.hino.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.hino.model.Staff;
import com.hino.util.PriviledgeParser;

public class HinoAccessDecisionManager 
	implements AccessDecisionManager {

    //In this method, need to compare authentication with configAttributes.
    // 1, A object is a URL, a filter was find permission configuration by this URL, and pass to here.
    // 2, Check authentication has attribute in permission configuration (configAttributes)
    // 3, If not match corresponding authentication, throw a AccessDeniedException.
	@Override
    public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes == null){
            return ;
        }
        
        // Get Staff from session
        Staff sessionStaff = (Staff)((FilterInvocation)object).getHttpRequest().getSession().getAttribute("staff");
        if(sessionStaff != null) {
        	String staffPriviledge = sessionStaff.getPriviledge();
	        //System.out.println("********************"+object.toString());  //object is a URL.
	        Iterator<ConfigAttribute> ite=configAttributes.iterator();
	        while(ite.hasNext()){
	            ConfigAttribute ca=ite.next();
	            String needRole=((SecurityConfig)ca).getAttribute();
            	// Check if any of this staff's priviledge is the same as this page
            	//System.out.println("**********needRole: "+needRole);
            	//System.out.println("**********staffPriviledge: "+staffPriviledge);
                if(PriviledgeParser.has_priviledge_by_role(staffPriviledge, needRole)){
                    return;
                }
	        }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


}
