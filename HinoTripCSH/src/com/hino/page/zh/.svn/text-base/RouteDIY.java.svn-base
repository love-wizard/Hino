package com.hino.page.zh;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.EmailField;
import org.apache.click.extras.control.TelephoneField;
import org.apache.click.util.Bindable;

import com.hino.dto.CustomerLoginDto;
import com.hino.dto.DiyRouteDto;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

public class RouteDIY extends BasePageOther {
	private static final long serialVersionUID = -421125561789927053L;

	@Bindable private String css="diy.css";
	
	@Bindable private Button diy_submit = new Button("diy_action","dy");
	@Bindable private List<Route> DIYlist;	//DIY tag group
	@Bindable private List<Route> DIYUKlist;	//DIY tag group
	@Bindable private List<Route> DIYEurolist;	//DIY tag group
	
    @Override
    public void onInit() {
    	qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);
    	diy_submit.setId("diy_action");
    	diy_submit.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	String title = getContext().getRequest().getParameter("tt");
            	String email = getContext().getRequest().getParameter("em");
            	String phone = getContext().getRequest().getParameter("ph");
            	String desc = getContext().getRequest().getParameter("desc");

                DiyRouteDto diydto = new DiyRouteDto(); 
                diydto.setEmail(email);
                diydto.setName(title);
                diydto.setDescription(desc);
                diydto.setPhone(phone);
                
                getTravelResourceService().create_dir_route(diydto);
               
                String jsondata = "";
            	
                jsondata = "{" + "\"result\":\"0\","+ "\"name\":\"" +title + "\"}";

                return new ActionResult(jsondata, ActionResult.JSON);
            }
        });
		

    }
 
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./images/diy.css"));
        }
        return headElements;
    }
	
	@Override
    public void onRender() {
//		DIYlist= (List<Group>)this.getSalesService().list_group_by_group_tag(100,Info.GROUP_TAG_SELL_TYPE, Info.GROUP_TAG_SELL_TYPE_DIY);
//		if(DIYUKlist==null)
//		{
//			DIYUKlist = new ArrayList<Group>();
//		}
//		if(DIYEurolist==null)
//		{
//			DIYEurolist = new ArrayList<Group>();
//		}
//		
//		for(Group g: DIYlist)
//		{
//			List<Group> gList = (List<Group>)this.getSalesService().list_group_by_group_tag(100,Info.GROUP_TAG_DESTINATION, Info.GROUP_TAG_DESTINATION_UK);
//			if(gList.size()>0){
//				for(Group g1: gList)
//				{
//					if(g1.getId() == g.getId())
//					{
//						DIYUKlist.add(g);
//					}
//					
//				}
//			}
//			
//			gList = (List<Group>)this.getSalesService().list_group_by_group_tag(100,Info.GROUP_TAG_DESTINATION, Info.GROUP_TAG_DESTINATION_EUROPE);
//			if(gList.size()>0){
//				for(Group g1: gList)
//				{
//					if(g1.getId() == g.getId())
//					{
//						DIYEurolist.add(g);
//					}
//					
//				}
//			}
//			
//		}
		DIYlist= (List<Route>)this.getSalesService().list_route_by_group_tag(100,Info.GROUP_TAG_DESTINATION,Info.GROUP_TAG_DESTINATION_OTHER);
		DIYUKlist= (List<Route>)this.getSalesService().list_route_by_group_tag(100,Info.GROUP_TAG_DESTINATION,Info.GROUP_TAG_DESTINATION_UK);
		DIYEurolist= (List<Route>)this.getSalesService().list_route_by_group_tag(100,Info.GROUP_TAG_DESTINATION,Info.GROUP_TAG_DESTINATION_EUROPE);
        addModel("DIYlist", DIYlist);
        addModel("DIYUKlist", DIYUKlist);
        addModel("DIYEurolist", DIYEurolist);
	}
 
}
