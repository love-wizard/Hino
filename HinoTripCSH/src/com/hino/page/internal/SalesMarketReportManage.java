package com.hino.page.internal;

import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;
import org.apache.commons.fileupload.FileItem;

import com.hino.model.Resource;
import com.hino.model.Staff;
import com.hino.util.TimeFormater;

/**
 * Upload and manage reguation files
 */
public class SalesMarketReportManage extends BasePage {
	private static final long serialVersionUID = -210678223198537921L;
	
	@Bindable private Form marketReportForm = new Form("marketreportform");
    @Bindable private Table marketReportTable = new Table("marketreporttable");
    //@Bindable private ActionLink deleteLink = new ActionLink("delete", "删除", this, "onDeleteClick");

    @Override
    public void onInit() {
		
		// ----- Display market report file upload form -----
		FileField marketReportFile = new FileField("marketreportfile", "上传市场调研报告");
		marketReportFile.setRequired(true);
		marketReportForm.add(marketReportFile);
		// PAGE and COLUMN are used for table paging
		marketReportForm.add(new HiddenField(Table.PAGE, String.class));
		marketReportForm.add(new HiddenField(Table.COLUMN, String.class));

		marketReportForm.add(new Submit("save", "提交新市场调研报告", this, "onSaveClick"));
		marketReportForm.add(new Submit("cancel", "取消提交", this, "onCancelClick"));
		
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

        // Add "Delete" link
        /*column = new Column("action", "操作");
        column.setWidth("25%");
        ActionLink[] links = new ActionLink[]{deleteLink};
        column.setDecorator(new LinkDecorator(marketReportTable, links, "id"));
        marketReportTable.addColumn(column);

        deleteLink.setAttribute("onclick", "return window.confirm('确认删除？');");*/

        // Get regulation resources from database as data provider
        marketReportTable.setDataProvider(new PagingDataProvider<Resource>() {
			private static final long serialVersionUID = -4437119622265700453L;

			// regulation files List
			public List<Resource> getData() {
            	int page = marketReportTable.getPageNumber();
                int size = marketReportTable.getPageSize(); 
				Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");
				List<Resource> allRegulationResources = (List<Resource>) 
					getSalesService().getSalesMarketReportList(curStaff, page, size);
                return allRegulationResources;
            }
			
			@Override
			public int size() {
				Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");
				return getSalesService().getSalesMarketReportCount(curStaff); // Remove index menu
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

    /**
     * Upload market report file and create a new resource record
     * @return
     */
    public boolean onSaveClick() {
        if (marketReportForm.isValid()) {
    		FileItem marketReportFI = 
    				((FileField)marketReportForm.getField("marketreportfile")).getFileItem();
    		
    		Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");
    		
    		getSalesService().create_new_market_report(curStaff, marketReportFI);
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('上传市场调研报告成功')"));
			getHeadElements().add(
					new JsScript("window.location = './sales_market_report_manage.htm'"));
        }
        return true;
    }

    /**
     * Redirect to sales_market_report_manage.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(SalesMarketReportManage.class);
        return true;
    }

    /**
     * @see org.apache.click.Page#onGet()
     */
    @Override
    public void onGet() {
    	marketReportForm.getField(Table.PAGE).setValue("" + marketReportTable.getPageNumber());
    	marketReportForm.getField(Table.COLUMN).setValue(marketReportTable.getSortedColumn());
    }

    /**
     * @see org.apache.click.Page#onPost()
     */
    @Override
    public void onPost() {
        String pageNumber = marketReportForm.getField(Table.PAGE).getValue();
        marketReportTable.setPageNumber(Integer.parseInt(pageNumber));
        marketReportTable.setSortedColumn(marketReportForm.getField(Table.COLUMN).getValue());
    }

}
