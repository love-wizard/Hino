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
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.IntegerField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.MenuRouteCAMDto;
import com.hino.model.Route;
import com.hino.model.WebMenu;
import com.hino.model.WebMenuRoute;
import com.hino.util.EscapeHtml;

/**
 * Create and manage external pages' menu items
 */
public class IndexRouteCAM extends BasePage {
	private static final long serialVersionUID = -1048110692543418703L;
	
	@Bindable private Form indexRouteForm = new Form("indexrouteform");
    @Bindable private Table indexRouteTable = new Table("indexroutetable");
    @Bindable private ActionLink editLink = new ActionLink("edit", "Edit", this, "onEditClick");
    @Bindable private ActionLink deleteLink = new ActionLink("delete", "Delete", this, "onDeleteClick");

    @Override
    public void onInit() {
		
		// ----- Display menu route form -----
		Select routeSlct = new Select("routeid", "线路", true);
		routeSlct.setDefaultOption(new Option("请选择线路"));
        DataProvider<Option> routeDataPrvd = new DataProvider<Option>() {
			private static final long serialVersionUID = -8107625833664571011L;

			public List<Option> getData() {
				List<Route> allRoute = getTravelResourceService().getAllRoute();
				List<Option> Options = new ArrayList<Option>();
				for(Route curRoute : allRoute) {
					Options.add(new Option(curRoute.getId(), EscapeHtml.htmlDecode(curRoute.getName())));
				}
                return Options;
            }
        };
        routeSlct.setDataProvider(routeDataPrvd);
        indexRouteForm.add(routeSlct);

		IntegerField priorityFld = new IntegerField("priority", "优先级", 3, true);
		priorityFld.setMaxLength(3);
		priorityFld.setValue("0");
		indexRouteForm.add(priorityFld);

		indexRouteForm.add(new Submit("save", "提交区域内容", this, "onSaveClick"));
		indexRouteForm.add(new Submit("cancel", "取消提交", this, "onCancelClick"));
		// Hidden field contains data base menu item id
		HiddenField menuRouteIdFld = new HiddenField("id", String.class);
		// Set its default value as "null" so that if the form is submitted with "null",
		// it means creating a new menu item, otherwise modifying existing one
		menuRouteIdFld.setValue("null");
		indexRouteForm.add(menuRouteIdFld);
		// PAGE and COLUMN are used for table paging
		indexRouteForm.add(new HiddenField(Table.PAGE, String.class));
		indexRouteForm.add(new HiddenField(Table.COLUMN, String.class));

		// ----- Display menu route table -----
		indexRouteTable.setClass(Table.CLASS_SIMPLE);
		indexRouteTable.setWidth("100%");
		indexRouteTable.setPageSize(10);
		indexRouteTable.setShowBanner(true);

		Column column = new Column("id");
        column.setWidth("15%");
        indexRouteTable.addColumn(column);
        
        column = new Column("routename", "线路");
        column.setWidth("40%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				MenuRouteCAMDto curMenuRoute = (MenuRouteCAMDto) object;
				return curMenuRoute.getRoutename(); 
			}
        });
        indexRouteTable.addColumn(column);
        
        column = new Column("priority", "优先级");
        column.setWidth("25%");
        indexRouteTable.addColumn(column);

        // Add "Edit" and "Delete" link
        column = new Column("action", "操作");
        column.setWidth("20%");
        ActionLink[] links = new ActionLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(indexRouteTable, links, "id"));
        indexRouteTable.addColumn(column);

        deleteLink.setAttribute("onclick", "return window.confirm('确认删除热门线路？');");

        // Get menu items from database as data provider
        indexRouteTable.setDataProvider(new PagingDataProvider<MenuRouteCAMDto>() {
			private static final long serialVersionUID = -42825907804983119L;

			// Menu item data List
			public List<MenuRouteCAMDto> getData() { 
            	int page = indexRouteTable.getPageNumber();
                int size = indexRouteTable.getPageSize();
                WebMenu index = getWebService().getIndexMenu();
                List<MenuRouteCAMDto> pagedMenuRouteDto = new ArrayList<MenuRouteCAMDto>();
                int count = 0;
            	List<WebMenuRoute> curMenuRouteList = index.getMenu_routes();
            	Collections.sort(curMenuRouteList);
            	for(WebMenuRoute curMenuRoute : curMenuRouteList) {
            		if(count >= (page*size) && count < (page*size+size)) {
            			MenuRouteCAMDto curMenuRouteCAMDto = new MenuRouteCAMDto();
            			curMenuRouteCAMDto.setId(String.valueOf(curMenuRoute.getId()));
            			curMenuRouteCAMDto.setMenuid(String.valueOf((index.getId())));
            			curMenuRouteCAMDto.setMenuname(index.getName());
            			curMenuRouteCAMDto.setRouteid(String.valueOf(curMenuRoute.getRoute().getId()));
            			curMenuRouteCAMDto.setRoutename(curMenuRoute.getRoute().getName());
            			curMenuRouteCAMDto.setPriority(String.valueOf(curMenuRoute.getPriority()));
            			pagedMenuRouteDto.add(curMenuRouteCAMDto);
            		}
            		count++;
            	}
                return pagedMenuRouteDto;
            }

			// Menu item data size
			@Override
			public int size() {
				WebMenu index = getWebService().getIndexMenu();
                return index.getMenu_routes().size();
			} 
        });
    }

	/**
	 * Get target WebMenuRoute object and copy its values into form
	 * @return
	 */
	public boolean onEditClick() {
        Integer id = editLink.getValueInteger();
        MenuRouteCAMDto editMenuRouteDto = null;
        WebMenu index = getWebService().getIndexMenu();
    	List<WebMenuRoute> curMenuRouteList = index.getMenu_routes();
    	for(WebMenuRoute curMenuRoute : curMenuRouteList) {
    		if(curMenuRoute.getId() == id) {
    	        editMenuRouteDto = new MenuRouteCAMDto();
    			editMenuRouteDto.setId(String.valueOf(id));
    			editMenuRouteDto.setMenuid(String.valueOf(index.getId()));
    			editMenuRouteDto.setMenuname(index.getName());
    			editMenuRouteDto.setPriority(String.valueOf(curMenuRoute.getPriority()));
    			editMenuRouteDto.setRouteid(String.valueOf(curMenuRoute.getRoute().getId()));
    			editMenuRouteDto.setRoutename(curMenuRoute.getRoute().getName());
    		}
    	}

        if (editMenuRouteDto != null) {
        	indexRouteForm.getField("id").setValue(editMenuRouteDto.getId());
        	indexRouteForm.getField("routeid").setValue(editMenuRouteDto.getRouteid());
        	indexRouteForm.getField("routeid").setReadonly(true);
        	indexRouteForm.getField("priority").setValue(editMenuRouteDto.getPriority());
        }
        return true;
    }

	/**
	 * Delete target WebMenuRoute from database
	 * @return
	 */
    public boolean onDeleteClick() {
    	Integer id = deleteLink.getValueInteger();
    	getWebService().delete_menu_route(id);
        return true;
    }

    /**
     * Create a new menu route item or modify an existing one
     * @return
     */
    public boolean onSaveClick() {
        if (indexRouteForm.isValid()) {
        	MenuRouteCAMDto menuroutedto = new MenuRouteCAMDto(); 
    		indexRouteForm.copyTo(menuroutedto);
    		menuroutedto.setMenuid("-1");

    		// "null" means it is a new menu item
    		if(menuroutedto.getId().compareTo("null") == 0) {
    			getWebService().create_menu_route(menuroutedto);
    		}
    		else {
    			getWebService().modify_menu_route(menuroutedto);
    		}
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('热门线路操作成功')"));
			getHeadElements().add(
					new JsScript("window.location = './index_route_cam.htm'"));
        }
        return true;
    }

    /**
     * Redirect to menu_route_cam.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(IndexRouteCAM.class);
        return true;
    }

    /**
     * @see org.apache.click.Page#onGet()
     */
    @Override
    public void onGet() {
    	indexRouteForm.getField(Table.PAGE).setValue("" + indexRouteTable.getPageNumber());
    	indexRouteForm.getField(Table.COLUMN).setValue(indexRouteTable.getSortedColumn());
    }

    /**
     * @see org.apache.click.Page#onPost()
     */
    @Override
    public void onPost() {
        String pageNumber = indexRouteForm.getField(Table.PAGE).getValue();
        indexRouteTable.setPageNumber(Integer.parseInt(pageNumber));
        indexRouteTable.setSortedColumn(indexRouteForm.getField(Table.COLUMN).getValue());
    }

}
