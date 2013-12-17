package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;

import com.hino.model.Route;
import com.hino.model.Site;

public class RouteManage extends BasePage{
	public String title = "线路管理";
	public Table table = new Table("routeTable");
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改");
	public ActionLink addRouteLink = new ActionLink("addroute", "添加新线路");	
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	public ActionLink manageScheduleLink = new ActionLink("manageScheduleLink", "管理行程");
	public ActionLink addScheduleLink = new ActionLink("addScheduleLink", "添加管理行程");
	public ActionLink editTagLink = new ActionLink("tag", "设置线路Tag"); //Devon King 2013-02-24
	
	public void onInit()
	{
		//Devon King 2013-02-24
		editTagLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
                setRedirect("./route_tag_manage.htm?id=" + editTagLink.getValueInteger());
                return true;
            }
        });
		
		addRouteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(RouteCAM.class);
                return true;
            }
        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer id = editLink.getValueInteger(); 
                setRedirect("./route_cam.htm?id=" + id);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getTravelResourceService().delete_route(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
		
		this.manageScheduleLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	Integer id = manageScheduleLink.getValueInteger();
                setRedirect("./schedule_manage.htm?id=" + id);
                return true;
            }
        });
		
		this.addScheduleLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	Integer id = addScheduleLink.getValueInteger();
            	setRedirect("./schedule_cam.htm?rid=" + id);
                return true;
            }
        });

		//table = new Table("siteTable");
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setPageSize(10);
        
        table.setShowBanner(true); 
        //table.setSortable(true); 
        
        table.addColumn(new Column("id", "线路ID")); 
        table.addColumn(new Column("name", "线路名称")); 

        Column column = new Column("action", "操作"); 
        //Devon King - 2013/02/24 Add edit tag link
        ActionLink[] links = {editLink, deleteLink, manageScheduleLink,addScheduleLink, editTagLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<Route>() {
        	
            public List<Route> getData() { 
            	int p = table.getPageNumber();
            	//table.get
                int count = table.getPageSize(); 
                List<Route> l = getTravelResourceService().getPagedRouteList(p, count);
                //System.out.println("List site:" +l);
                //System.out.println("List site size:" +l.size());
                return l;
            }

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return getTravelResourceService().getRouteCount();
			} 
        }); 
        
        
	}
}
