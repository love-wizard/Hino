package com.hino.page.zh;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Button;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Label;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Radio;
import org.apache.click.control.RadioGroup;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.EmailField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.extras.control.RegexField;
import org.apache.click.extras.control.TelephoneField;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.click.extension.Link;
import com.hino.dto.CustomerBasicInfoDto;
import com.hino.dto.CustomerPasswordDto;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.QQService;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GoView extends BasePageOther {
	private static final long serialVersionUID = -7633737420541155842L;

	@Bindable private Integer gid;
	@Bindable private Group group;
	
	@Bindable private Button remain_tickets = new Button("remain_tickets", "remain_tickets");
	
    @Override
    public void onInit() {
    	qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);		
    	
    	if(gid!=null)
		{
			group = getCustomerService().getGroupById(gid);
			
			
			
		}
    	
    	remain_tickets.setId("remain_tickets");
    	remain_tickets.addBehavior(new DefaultAjaxBehavior() {
			public ActionResult onAction(Control source) {
				// group ids to be retrieved (colon separated)
				String gids = getContext().getRequest().getParameter("gids");
				String[] gidArr = gids.split(":");
				Group g;
				
				StringBuffer jsondata = new StringBuffer();				
				jsondata.append("{\"rts\": [");
		
				boolean isFirstTimeIn = true;
				for(String gid : gidArr) {
					
					if ("".equals(gid.trim())) continue;
					
					g = getCustomerService().getGroupById(Integer.valueOf(gid.trim()));
					if (null != g) {
						if(! isFirstTimeIn) {
							jsondata.append(",");
						} else {
							isFirstTimeIn = false;
						}
						
						jsondata.append("{\"gid\":\"");
						// 团编号
						jsondata.append(gid);
						jsondata.append("\", \"st\": \"");
						// 剩余座位数
						jsondata.append(g.getSeats() + g.getSeats_groupon() + g.getSeats_seckill() - g.gen_all_seats_for_go());
						jsondata.append("\"}");
					}
				}
				
				jsondata.append("]}");
				System.out.println(jsondata.toString());
				return new ActionResult(jsondata.toString(), ActionResult.JSON);
		}});

    }


    
    public void onRender()
    {
    
    }

    @Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            headElements.add(new CssImport("./images/group.css"));
        }
        return headElements;
    }
}
