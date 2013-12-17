package com.hino.page.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.IntegerField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.MenuRouteCAMDto;
import com.hino.dto.QQServiceCAMDto;
import com.hino.model.QQService;
import com.hino.model.Route;
import com.hino.model.WebMenu;
import com.hino.model.WebMenuRoute;
import com.hino.util.EscapeHtml;

/**
 * Create and manage external pages' menu items
 */
public class IndexQQCAM extends BasePage {
	private static final long serialVersionUID = 999L;
	
	@Bindable private Form indexQQForm = new Form("indexQQForm");
    @Bindable private Table indexQQTable = new Table("indexQQTable");
    @Bindable private ActionLink editLink = new ActionLink("edit", "Edit", this, "onEditClick");
    @Bindable private ActionLink deleteLink = new ActionLink("delete", "Delete", this, "onDeleteClick");

    @Override
    public void onInit() {
		
		// ----- Display menu route form -----
    	TextField serviceName = new TextField("serviceName", "客服名称", 45, true);
    	TextField QQNumber = new TextField("QQNumber", "客服QQ号码", 45, true);
    	IntegerField priorityFld = new IntegerField("priority", "页面显示顺序", 3, true);
    	priorityFld.setMaxLength(3);
		priorityFld.setValue("0");
		priorityFld.setMinValue(0);
		
        indexQQForm.add(serviceName);
        indexQQForm.add(QQNumber);
		indexQQForm.add(priorityFld);

		indexQQForm.add(new Submit("save", "提交客服QQ", this, "onSaveClick"));
		indexQQForm.add(new Submit("cancel", "取消提交", this, "onCancelClick"));
		// Hidden field contains data base QQ item id
		HiddenField QQIdFld = new HiddenField("id", String.class);
		// Set its default value as "null" so that if the form is submitted with "null",
		// it means creating a new menu item, otherwise modifying existing one
		QQIdFld.setValue("null");
		indexQQForm.add(QQIdFld);
		// PAGE and COLUMN are used for table paging
		indexQQForm.add(new HiddenField(Table.PAGE, String.class));
		indexQQForm.add(new HiddenField(Table.COLUMN, String.class));

		// ----- Display menu route table -----
		indexQQTable.setClass(Table.CLASS_SIMPLE);
		indexQQTable.setWidth("100%");
		indexQQTable.setPageSize(10);
		indexQQTable.setShowBanner(true);

		Column column = new Column("id");
        column.setWidth("5%");
        indexQQTable.addColumn(column);
        
        column = new Column("serviceName", "客服名称");
        column.setWidth("25%");
        indexQQTable.addColumn(column);
        
        column = new Column("qqNumber", "QQ号码");
        column.setWidth("25%");
        indexQQTable.addColumn(column);
        
        column = new Column("priority", "页面显示顺序");
        column.setWidth("25%");
        indexQQTable.addColumn(column);

        // Add "Edit" and "Delete" link
        column = new Column("action", "操作");
        column.setWidth("20%");
        ActionLink[] links = new ActionLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(indexQQTable, links, "id"));
        indexQQTable.addColumn(column);

        deleteLink.setAttribute("onclick", "return window.confirm('确认删除该客服QQ？');");

        // Get QQ items from database as data provider
        indexQQTable.setDataProvider(new PagingDataProvider<QQService>() {
			private static final long serialVersionUID = 7286714200152315366L;

			// Menu item data List
			public List<QQService> getData() { 
            	int page = indexQQTable.getPageNumber();
                int size = indexQQTable.getPageSize(); 
                List<QQService> qqServiceList = getWebService().getIndexQQService();
                
                return qqServiceList;
            }

			// Menu item data size
			@Override
			public int size() {
				List<QQService> qqServiceList = getWebService().getIndexQQService();
                return qqServiceList.size();
			} 
        });
    }

	/**
	 * Get target indexQQ object and copy its values into form
	 * @return
	 */
	public boolean onEditClick() {
        Integer id = editLink.getValueInteger();
        QQService qqService = null;
        qqService = getWebService().getIndexQQService(id);

        if (qqService != null) {
        	indexQQForm.getField("id").setValue(String.valueOf(qqService.getId()));
        	indexQQForm.getField("QQNumber").setValue(qqService.getQqNumber());
        	indexQQForm.getField("id").setReadonly(true);
        	indexQQForm.getField("serviceName").setValue(qqService.getServiceName());
        	indexQQForm.getField("priority").setValue(String.valueOf(qqService.getPriority()));
        }
        return true;
    }

	/**
	 * Delete target WebMenuRoute from database
	 * @return
	 */
    public boolean onDeleteClick() {
    	Integer id = deleteLink.getValueInteger();
    	getWebService().DeleteQQService(id);
        return true;
    }

    /**
     * Create a new menu route item or modify an existing one
     * @return
     */
    public boolean onSaveClick() {
        if (indexQQForm.isValid()) {
        	QQServiceCAMDto qqServiceDto = new QQServiceCAMDto(); 
    		//indexQQForm.copyTo(qqServiceDto);
        	if(indexQQForm.getField("id").getValue().compareTo("null") == 0)
        	{
        		qqServiceDto.setId(0);
        	}
        	else
        	{
        		qqServiceDto.setId(Integer.parseInt(indexQQForm.getField("id").getValue()));
        	}
    		qqServiceDto.setQqNumber(indexQQForm.getField("QQNumber").getValue());
    		qqServiceDto.setServiceName((indexQQForm.getField("serviceName").getValue()));
    		qqServiceDto.setPriority(Integer.parseInt(indexQQForm.getField("priority").getValue()));

    		// "null" means it is a new menu item
    		if(qqServiceDto.getId() == 0) {
    			getWebService().AddQQService(qqServiceDto);
    		}
    		else {
    			getWebService().UpdateQQService(qqServiceDto);
    		}
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('操作成功')"));
			getHeadElements().add(
					new JsScript("window.location = './index_qq_cam.htm'"));
        }
        return true;
    }

    /**
     * Redirect to menu_route_cam.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(IndexQQCAM.class);
        return true;
    }

    /**
     * @see org.apache.click.Page#onGet()
     */
    @Override
    public void onGet() {
    	indexQQForm.getField(Table.PAGE).setValue("" + indexQQTable.getPageNumber());
    	indexQQForm.getField(Table.COLUMN).setValue(indexQQTable.getSortedColumn());
    }

    /**
     * @see org.apache.click.Page#onPost()
     */
    @Override
    public void onPost() {
        String pageNumber = indexQQForm.getField(Table.PAGE).getValue();
        indexQQTable.setPageNumber(Integer.parseInt(pageNumber));
        indexQQTable.setSortedColumn(indexQQForm.getField(Table.COLUMN).getValue());
    }

}
