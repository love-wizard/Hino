package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.CheckList;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.RouteCAMDto;
import com.hino.model.Route;
import com.hino.model.Schedule;
import com.hino.model.Site;
import com.hino.util.Info;

public class ScheduleManage extends BasePage{
	
	public String caption = "线路行程管理";
	public Table table = new Table("scheduleTable");
	public ActionLink editLink = new ActionLink("edit", "查看/修改");
	//public ActionLink addScheduleLink = new ActionLink("addScheduleLink", "添加新行程");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	private HiddenField routeid = new HiddenField("routeid", "routeid");
	
	//routeId
	@Bindable
	private int id;
	
	public void onInit()
	{
		
//		addScheduleLink.setActionListener(new ActionListener() {
//            private static final long serialVersionUID = 1L;
//            public boolean onAction(Control source) {
//            	
//                setRedirect("./schedule_cam.htm?rid=" + id);
//                return true;
//            }
//        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer sid = editLink.getValueInteger(); 
                setRedirect("./schedule_cam.htm?id=" + sid);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	final Schedule schedule = getTravelResourceService().getScheduleById(id);
            	getTravelResourceService().delete_schedule(id);
            	
            	setRedirect("./schedule_manage.htm?id=" + schedule.getRoute_id());
                return true;
            }
        });
		
		//addScheduleLink.setAttribute("caption", String.valueOf(id));
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
		
		routeid.setValue(String.valueOf(id));
		//table = new Table("siteTable");
		//table.setWidth("300");
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setPageSize(10);
        
        table.setShowBanner(true); 
        //table.setSortable(true); 
        
        table.addColumn(new Column("id", "行程ID")); 
        table.addColumn(new Column("title", "行程名称"));
        table.addColumn(new Column("title_desc", "行程简介"));

        Column column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column);
        
        final Route route;
        if(id!=0)
        {
        	route = getTravelResourceService().getRouteById(id);
        	
        	// Set data provider to populate the table row list from 
            table.setDataProvider(new PagingDataProvider<Schedule>() {
            	
                public List<Schedule> getData() { 
                	int p = table.getPageNumber();
                	//table.get
                    int count = table.getPageSize(); 
                    List<Schedule> l = getTravelResourceService().getRouteScheduleList(route.getId());
                    //System.out.println("List site:" +l);
                    //System.out.println("List site size:" +l.size());
                    return l;
                }

    			@Override
    			public int size() {
    				return getTravelResourceService().list_route_schedule_count(route.getId());
    			}
            });      
        }
        else
        {
        	route = new Route();
        	//return;
        }
          
	}

}
