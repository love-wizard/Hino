package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.model.ServiceOption;
import com.hino.model.DistinguishedGroup;

public class ServiceOptionManage extends BasePage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String caption = "尊享团服务项目管理";
	public Table table = new Table("serviceOptionTable");
	public ActionLink editLink = new ActionLink("edit", "查看/修改");
	public ActionLink addServiceOptionLink = new ActionLink("addServiceOption", "添加新服务项目");	
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	public void onInit()
	{
		addServiceOptionLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(ServiceOptionCAM.class);
                return true;
            }
        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer sid = editLink.getValueInteger(); 
                setRedirect("./service_option_cam.htm?id=" + sid);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	final ServiceOption serviceOption = getTravelResourceService().getServiceOptionById(id);
            	if (null != serviceOption) {
            		getTravelResourceService().deleteServiceOption(id);
            	}
            	
            	setRedirect("./service_option_manage.htm");
                return true;
            }
        });

		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");

		table.setClass(Table.CLASS_SIMPLE); 
        table.setPageSize(10);
        table.setShowBanner(true); 
        
        table.addColumn(new Column("id", "项目编号")); 
        table.addColumn(new Column("title", "项目名称"));

        Column column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column);

		// Set data provider to populate the table row list from 
	    table.setDataProvider(new PagingDataProvider<ServiceOption>() {
	    	
	        public List<ServiceOption> getData() { 
	        	int p = table.getPageNumber();
	            int count = table.getPageSize(); 
	            List<ServiceOption> l = getTravelResourceService().getServiceOptions();
	
	            return l;
	        }
	
			@Override
			public int size() {
				return getTravelResourceService().getServiceOptionCount();
			}
	    });      
          
	}

}
