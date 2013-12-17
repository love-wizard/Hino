package com.hino.page.zh;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
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
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GroupList extends BasePageOther {
	private static final long serialVersionUID = -7633737420541155842L;
	
	@Bindable private Table booking_table = new Table("booking_table");
	
    @Override
    public void onInit() {
	    	qqList = getWebService().getIndexQQService();
			addModel("qqList", qqList);		
    		
    		booking_table.setClass(Table.CLASS_BLUE1);
    		booking_table.setHoverRows(true);
    		booking_table.setShowBanner(true);
    		booking_table.setPageSize(50);
    		booking_table.setSortable(true);
    		
            Column column = new Column("gname", "团名称");
            column.setDecorator(new Decorator(){
    			@Override
    			public String render(Object object, Context context) {
    				Group g = (Group)object;
    				return g.getName();
    			}
    		});
            column.setSortable(false);
    		booking_table.addColumn(column);
    		
    		column = new Column("depart_date", "出团日期");
    		column.setDecorator(new Decorator(){
    			@Override
    			public String render(Object object, Context context) {
    				Group g = (Group)object;
    				return TimeFormater.format2(g.getDepart_date());
    			}
    		});
    		booking_table.addColumn(column);
    		
    		
    		column = new Column("price", "价格");
    		booking_table.addColumn(column);
    	
    		column = new Column("vip_price", "VIP价格");
    		booking_table.addColumn(column);
    		
    		column = new Column("depart_city", "出发城市");
    		column.setDecorator(new Decorator(){
    			@Override
    			public String render(Object object, Context context) {
    				Group g = ((Group) object);
    				return g.genPickupCity()+"";
    			}
    		});
    		column.setSortable(false);
    		booking_table.addColumn(column);
    		
    		column = new Column("external_indicator", "信息");
    		column.setSortable(false);
    		booking_table.addColumn(column);
    		
    		//ticketLink.setParameter("type", value)
    		column = new Column("action", "报名");
    		column.setDecorator(new Decorator(){
    			@Override
    			public String render(Object object, Context context) {
    				Group g = ((Group) object);
    				
    				if(g.getExternalBookable()) {
    					return "<a href=\"./booking.htm?gid="+String.valueOf(g.getId())+"\" class='bmgroup'>报名</a>";
    				} else {
    					return "已满团";
    				}
    				
    			}
    		});
    		column.setSortable(false);
    		
    		
    		
    		booking_table.addColumn(column);
    		
    		
    	

    }


    
    public void onRender()
    {
		booking_table.setDataProvider(new PagingDataProvider<Group>(){
			
			@Override
			public int size() {
				return getWebService().getGroupExListViewCount();
			}

			@Override
			public Iterable<Group> getData() {
				int p = booking_table.getPageNumber();
				int count = booking_table.getPageSize();
				String sortColumn = booking_table.getSortedColumn();
				
				boolean ascending = booking_table.isSortedAscending();
				if (sortColumn ==null)
				{
					sortColumn = "depart_date";
				}
				return getWebService().getGroupExListView(p, sortColumn, ascending);
			}
			
		});
    		
    	
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
