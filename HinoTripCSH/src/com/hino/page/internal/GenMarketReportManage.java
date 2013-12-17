package com.hino.page.internal;

import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.util.Bindable;

import com.hino.model.Resource;
import com.hino.util.TimeFormater;

/**
 * Upload and manage reguation files
 */
public class GenMarketReportManage extends BasePage {
	private static final long serialVersionUID = -6505713148605846998L;
	
    @Bindable private Table marketReportTable = new Table("marketreporttable");
    //@Bindable private ActionLink deleteLink = new ActionLink("delete", "删除", this, "onDeleteClick");

    @Override
    public void onInit() {
		
		// ----- Display regulation file list table -----
		marketReportTable.setClass(Table.CLASS_SIMPLE);
		marketReportTable.setWidth("100%");
		marketReportTable.setPageSize(10);
		marketReportTable.setShowBanner(true);

		Column column = new Column("id");
        column.setWidth("5%");
        marketReportTable.addColumn(column);
        
        column = new Column("uploaddate", "提交时间");
        column.setWidth("20%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Resource curResource = (Resource) object;
				return TimeFormater.format2(curResource.getUpdate_time()); 
			}
        });
        marketReportTable.addColumn(column);
        
        column = new Column("filename","文件名");
        column.setWidth("50%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Resource curResource = (Resource) object;
				return "<a href='files.htm?filename="+curResource.getFilename()+"'>"
					+curResource.getName()+"</a>";
			}
        });
        marketReportTable.addColumn(column);
        
        column = new Column("uploadername","提交人");
        column.setWidth("25%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Resource curResource = (Resource) object;
				return curResource.getAuthor().genFullName();
			}
        });
        marketReportTable.addColumn(column);

        // Add "Delete" link
        /*column = new Column("action", "操作");
        column.setWidth("25%");
        ActionLink[] links = new ActionLink[]{deleteLink};
        column.setDecorator(new LinkDecorator(marketReportTable, links, "id"));
        marketReportTable.addColumn(column);

        deleteLink.setAttribute("onclick", "return window.confirm('确认删除？');");*/

        // Get regulation resources from database as data provider
        marketReportTable.setDataProvider(new PagingDataProvider<Resource>() {
			private static final long serialVersionUID = -3142182769027557789L;

			// regulation files List
			public List<Resource> getData() {
            	int page = marketReportTable.getPageNumber();
                int size = marketReportTable.getPageSize(); 
				List<Resource> allRegulationResources = (List<Resource>) 
					getSalesService().getGenMarketReportList(page, size);
                return allRegulationResources;
            }
			
			@Override
			public int size() {
				return getSalesService().getGenMarketReportCount(); // Remove index menu
			} 
        });
    }
    
	/**
	 * Delete target Resource from database
	 * @return
	 */
    /*public boolean onDeleteClick() {
    	Integer id = deleteLink.getValueInteger();
    	getHumanResourceService().delete_resource(id);
        return true;
    }*/

}
