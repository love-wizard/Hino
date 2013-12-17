package com.hino.page.zh;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hino.click.extension.Link;
import com.hino.dto.BookingReserveDto;
import com.hino.dto.CustomerBasicInfoDto;
import com.hino.dto.CustomerPasswordDto;
import com.hino.model.Booking;
import com.hino.model.Customer;
import com.hino.model.Group;
import com.hino.model.TourSurvey;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GoDream extends BasePageOther {
	private static final long serialVersionUID = -7633737420541155842L;
	
	@Bindable private Table booking_table = new Table("booking_table");
	@Bindable private List<TourSurvey> ts_list;
    
    
    @Bindable
	public Button vote_ts = new Button("vote_ts", "vote_ts");
    @Override
    public void onInit() {
    	qqList = getWebService().getIndexQQService();
		addModel("qqList", qqList);		
    	vote_ts.setId("vote_ts");
    	vote_ts.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	int tsid = Integer.parseInt(getContext().getRequest().getParameter("tsid"));
            	int votetp = Integer.parseInt(getContext().getRequest().getParameter("votetp"));
            	
				getCustomerService().vote_tour_survey(votetp, tsid);
				
				return new ActionResult(votetp+"", ActionResult.HTML);
            }
        });

    }


    
    public void onRender()
    {
    	ts_list= this.getSalesService().list_survey_stats(1);
    	
    		
    	
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
