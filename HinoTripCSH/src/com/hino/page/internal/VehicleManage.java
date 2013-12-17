package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;

import com.hino.model.Site;
import com.hino.model.Vehicle;

public class VehicleManage extends BasePage{
	public String title = "车辆管理";
	public Table table = new Table("vehicleTable");
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改");
	public ActionLink addSiteLink = new ActionLink("addvehicle", "添加新车辆");
	public ActionLink deleteLink = new ActionLink("delete", "删除");

	public void onInit()
	{
		addSiteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(VehicleCAM.class);
                return true;
            }
        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer id = editLink.getValueInteger(); 
                setRedirect("./vehicle_cam.htm?id=" + id);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getTravelResourceService().delete_vehicle(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");

		//table = new Table("siteTable");
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setPageSize(10);
        
        table.setShowBanner(true); 
        //table.setSortable(true); 
        
        table.addColumn(new Column("id", "车辆ID")); 
        table.addColumn(new Column("name", "名称")); 

        Column column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<Vehicle>() {
        	
            public List<Vehicle> getData() { 
            	int p = table.getPageNumber();
            	//table.get
                int count = table.getPageSize(); 
                List<Vehicle> l = getTravelResourceService().getPagedVehicleList(p, count);
                //System.out.println("List site:" +l);
                //System.out.println("List site size:" +l.size());
                return l;
            }

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return getTravelResourceService().getVehicleCount();
			} 
        }); 
        
        
	}
}
