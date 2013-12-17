package com.hino.page.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.click.ActionResult;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.model.DiyRoute;
import com.hino.util.EscapeHtml;
import com.hino.util.TimeFormater;

/**
 * List all customers. 
 * Modify credit.
 */
public class DiyRouteList extends BasePage {
	private static final long serialVersionUID = 8865040769293863871L;
	
	@Bindable private Table diyRouteTable = new Table("diyroutetable");
    @Bindable private ActionLink detailLink = new ActionLink("detail", "查看详细");

    @Override
    public void onInit() {

		// ----- Display customer table -----
    	diyRouteTable.setClass(Table.CLASS_SIMPLE);
    	diyRouteTable.setWidth("100%");
    	diyRouteTable.setPageSize(10);
    	diyRouteTable.setShowBanner(true);

        // Use setDecorator to show Chinese characters and special symbos like < and >
        Column column = new Column("id", "ID");
        column.setWidth("5%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				DiyRoute curDiyRoute = (DiyRoute) object;
				return String.valueOf(curDiyRoute.getId());
			}
        });
        diyRouteTable.addColumn(column);
        
        column = new Column("time", "申请时间");
        column.setWidth("5%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				DiyRoute curDiyRoute = (DiyRoute) object;
				return TimeFormater.format1((curDiyRoute.getDiy_time()));
			}
        });
        diyRouteTable.addColumn(column);

        column = new Column("name", "申请人称呼");
        column.setWidth("15%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				DiyRoute curDiyRoute = (DiyRoute) object;
				return curDiyRoute.getName();
			}
        });
        diyRouteTable.addColumn(column);
        
        column = new Column("email","电子邮箱");
        column.setWidth("15%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				DiyRoute curDiyRoute = (DiyRoute) object;
				return curDiyRoute.getEmail();
			}
        });
        diyRouteTable.addColumn(column);
        
        column = new Column("phone","联系电话");
        column.setWidth("15%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				DiyRoute curDiyRoute = (DiyRoute) object;
				return curDiyRoute.getPhone();
			}
        });
        diyRouteTable.addColumn(column);
        
        column = new Column("description","DIY描述");
        column.setWidth("25%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				DiyRoute curDiyRoute = (DiyRoute) object;
				String desc = curDiyRoute.getDescription().replace("\n","<br />");
				if(desc.length() > 50)
					desc = desc.substring(0, 50)+"...";
				return desc;
			}
        });
        diyRouteTable.addColumn(column);

        // Add "credit" and "detail" link
        column = new Column("action", "操作");
        column.setWidth("20%");
        
        // Show DIY request detail information
        detailLink.setAttribute("class", "ajaxDetail");
        detailLink.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {
				DiyRoute targetDiyRoute = 
					(DiyRoute)getTravelResourceService().getDiyRouteById(Long.parseLong(detailLink.getValue()));
				
				Map<String, String> DiyRouteHash = new HashMap<String, String>();
				DiyRouteHash.put("name", targetDiyRoute.getName());
				DiyRouteHash.put("email", targetDiyRoute.getEmail());
				DiyRouteHash.put("phone", targetDiyRoute.getPhone());
				DiyRouteHash.put("description", targetDiyRoute.getDescription().replace("\n","<br />"));

				StringWriter writer = new StringWriter();
				  ObjectMapper mapper = new ObjectMapper();
				  try {
				   mapper.writeValue(writer, DiyRouteHash);
				  } catch (JsonGenerationException e) {
				   e.printStackTrace();
				  } catch (JsonMappingException e) {
				   e.printStackTrace();
				  } catch (IOException e) {
				   e.printStackTrace();
				  }

				return new ActionResult(writer.toString(), ActionResult.JSON);
			}
		});
        ActionLink[] links = new ActionLink[]{detailLink};
        column.setDecorator(new LinkDecorator(diyRouteTable, links, "id"));
        diyRouteTable.addColumn(column);

        // Get menu items from database as data provider
        diyRouteTable.setDataProvider(new PagingDataProvider<DiyRoute>() {
			private static final long serialVersionUID = 8963329061823970607L;

			// Customer data List
			public List<DiyRoute> getData() { 
            	int page = diyRouteTable.getPageNumber();
                int size = diyRouteTable.getPageSize(); 
                List<DiyRoute> pagedList = getTravelResourceService().getPagedDiyRouteList(page, size);
                return pagedList;
            }

			// Menu item data size
			@Override
			public int size() {
				return getTravelResourceService().getDiyRouteCount();
			} 
        });

    }

}
