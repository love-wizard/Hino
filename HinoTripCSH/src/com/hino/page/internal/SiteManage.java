package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;

import com.hino.model.Site;

public class SiteManage extends BasePage {
	public String title = "景点管理";
	public Table table = new Table("siteTable");
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改");
	public ActionLink addSiteLink = new ActionLink("addsite", "添加新景点");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	public void onInit()
	{
		addSiteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(SiteCAM.class);
                return true;
            }
        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer id = editLink.getValueInteger(); 
                setRedirect("./site_cam.htm?id=" + id);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getTravelResourceService().delete_site(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");

		//table = new Table("siteTable");
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setPageSize(10);
        
        table.setShowBanner(true); 
        //table.setSortable(true); 
        
        table.addColumn(new Column("id", "景点ID")); 
        table.addColumn(new Column("name", "景点名称")); 

        Column column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<Site>() {
        	
            public List<Site> getData() { 
            	int p = table.getPageNumber();
            	//table.get
                int count = table.getPageSize(); 
                List<Site> l = getTravelResourceService().getPagedSiteList(p, count);
                //System.out.println("List site:" +l);
                //System.out.println("List site size:" +l.size());
                return l;
            }

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return getTravelResourceService().getSiteCount();
			} 
        }); 
        
        
	}

}
