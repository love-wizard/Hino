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
public class MenuRouteCAM extends BasePage {
	private static final long serialVersionUID = 6898236576792469180L;
	
	@Bindable private Form menuRouteForm = new Form("menurouteform");
    @Bindable private Table menuRouteTable = new Table("menuroutetable");
    @Bindable private ActionLink editLink = new ActionLink("edit", "Edit", this, "onEditClick");
    @Bindable private ActionLink deleteLink = new ActionLink("delete", "Delete", this, "onDeleteClick");

    @Override
    public void onInit() {
		
		// ----- Display menu route form -----
		Select menuSlct = new Select("menuid", "板块", true);
		menuSlct.setDefaultOption(new Option("", "请选择板块"));
        DataProvider<Option> menuDataPrvd = new DataProvider<Option>() {
			private static final long serialVersionUID = 6850715354692755450L;

			public List<Option> getData() {
				List<WebMenu> allMenu = getWebService().getAllMenu();
				List<Option> Options = new ArrayList<Option>();
				for (WebMenu curMenu : allMenu) {
					if(curMenu.getId() != -1) //Show all menu items excluding index
						Options.add(new Option(curMenu.getId(), EscapeHtml.htmlDecode(curMenu.getName())));
				}
                return Options;
            }
        };
        menuSlct.setDataProvider(menuDataPrvd);
        menuRouteForm.add(menuSlct);
        
		Select routeSlct = new Select("routeid", "线路", true);
		routeSlct.setDefaultOption(new Option("请选择线路"));
        DataProvider<Option> routeDataPrvd = new DataProvider<Option>() {
			private static final long serialVersionUID = 8112171327861555252L;

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
        menuRouteForm.add(routeSlct);

		IntegerField priorityFld = new IntegerField("priority", "优先级", 3, true);
		priorityFld.setMaxLength(3);
		priorityFld.setValue("0");

		//Devon King - 2012/09/18 - TD#12 设置一个最小值 
		priorityFld.setMinValue(0);
		
		menuRouteForm.add(priorityFld);

		menuRouteForm.add(new Submit("save", "提交区域内容", this, "onSaveClick"));
		menuRouteForm.add(new Submit("cancel", "取消提交", this, "onCancelClick"));
		// Hidden field contains data base menu item id
		HiddenField menuRouteIdFld = new HiddenField("id", String.class);
		// Set its default value as "null" so that if the form is submitted with "null",
		// it means creating a new menu item, otherwise modifying existing one
		menuRouteIdFld.setValue("null");
		menuRouteForm.add(menuRouteIdFld);
		// PAGE and COLUMN are used for table paging
		menuRouteForm.add(new HiddenField(Table.PAGE, String.class));
		menuRouteForm.add(new HiddenField(Table.COLUMN, String.class));

		// ----- Display menu route table -----
		menuRouteTable.setClass(Table.CLASS_SIMPLE);
		menuRouteTable.setWidth("100%");
		menuRouteTable.setPageSize(10);
		menuRouteTable.setShowBanner(true);

		Column column = new Column("id");
        column.setWidth("5%");
        menuRouteTable.addColumn(column);
        
        // Use setDecorator to show Chinese characters and special symbos like < and >
        column = new Column("menuname", "板块");
        column.setWidth("25%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				MenuRouteCAMDto curMenuRoute = (MenuRouteCAMDto) object;
				return curMenuRoute.getMenuname(); 
			}
        });
        menuRouteTable.addColumn(column);
        
        column = new Column("routename", "线路");
        column.setWidth("25%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				MenuRouteCAMDto curMenuRoute = (MenuRouteCAMDto) object;
				return curMenuRoute.getRoutename(); 
			}
        });
        menuRouteTable.addColumn(column);
        
        column = new Column("priority", "优先级");
        column.setWidth("25%");
        menuRouteTable.addColumn(column);

        // Add "Edit" and "Delete" link
        column = new Column("action", "操作");
        column.setWidth("20%");
        ActionLink[] links = new ActionLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(menuRouteTable, links, "id"));
        menuRouteTable.addColumn(column);

        deleteLink.setAttribute("onclick", "return window.confirm('确认删除板块内容？');");

        // Get menu items from database as data provider
        menuRouteTable.setDataProvider(new PagingDataProvider<MenuRouteCAMDto>() {
			private static final long serialVersionUID = 7286714200152315305L;

			// Menu item data List
			public List<MenuRouteCAMDto> getData() { 
            	int page = menuRouteTable.getPageNumber();
                int size = menuRouteTable.getPageSize(); 
                List<WebMenu> allMenu = getWebService().getAllMenu();
                List<MenuRouteCAMDto> pagedMenuRouteDto = new ArrayList<MenuRouteCAMDto>();
                int count = 0;
                for(WebMenu curMenu : allMenu) {
                	if(curMenu.getId() == -1) 
                		continue; //Show all menu items excluding index
                	List<WebMenuRoute> curMenuRouteList = curMenu.getMenu_routes();
                	Collections.sort(curMenuRouteList);
                	for(WebMenuRoute curMenuRoute : curMenuRouteList) {
                		if(count >= (page*size) && count < (page*size+size)) {
                			MenuRouteCAMDto curMenuRouteCAMDto = new MenuRouteCAMDto();
                			curMenuRouteCAMDto.setId(String.valueOf(curMenuRoute.getId()));
                			curMenuRouteCAMDto.setMenuid(String.valueOf(curMenu.getId()));
                			curMenuRouteCAMDto.setMenuname(curMenu.getName());
                			curMenuRouteCAMDto.setRouteid(String.valueOf(curMenuRoute.getRoute().getId()));
                			curMenuRouteCAMDto.setRoutename(curMenuRoute.getRoute().getName());
                			curMenuRouteCAMDto.setPriority(String.valueOf(curMenuRoute.getPriority()));
                			pagedMenuRouteDto.add(curMenuRouteCAMDto);
                		}
                		count++;
                	}
				}
                return pagedMenuRouteDto;
            }

			// Menu item data size
			@Override
			public int size() {
                List<WebMenu> allMenu = getWebService().getAllMenu();
                int count = 0;
                for(WebMenu curMenu : allMenu) {
                	count += curMenu.getMenu_routes().size();
				}
                return count;
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
        List<WebMenu> allMenu = getWebService().getAllMenu();
        for(WebMenu curMenu : allMenu) {
        	List<WebMenuRoute> curMenuRouteList = curMenu.getMenu_routes();
        	for(WebMenuRoute curMenuRoute : curMenuRouteList) {
        		if(curMenuRoute.getId() == id) {
        	        editMenuRouteDto = new MenuRouteCAMDto();
        			editMenuRouteDto.setId(String.valueOf(id));
        			editMenuRouteDto.setMenuid(String.valueOf(curMenu.getId()));
        			editMenuRouteDto.setMenuname(curMenu.getName());
        			editMenuRouteDto.setPriority(String.valueOf(curMenuRoute.getPriority()));
        			editMenuRouteDto.setRouteid(String.valueOf(curMenuRoute.getRoute().getId()));
        			editMenuRouteDto.setRoutename(curMenuRoute.getRoute().getName());
        		}
        	}
		}

        if (editMenuRouteDto != null) {
        	menuRouteForm.getField("id").setValue(editMenuRouteDto.getId());
        	menuRouteForm.getField("menuid").setValue(editMenuRouteDto.getMenuid());
        	menuRouteForm.getField("menuid").setReadonly(true);
        	menuRouteForm.getField("routeid").setValue(editMenuRouteDto.getRouteid());
        	menuRouteForm.getField("routeid").setReadonly(true);
        	menuRouteForm.getField("priority").setValue(editMenuRouteDto.getPriority());
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
        if (menuRouteForm.isValid()) {
        	MenuRouteCAMDto menuroutedto = new MenuRouteCAMDto(); 
    		menuRouteForm.copyTo(menuroutedto);

    		// "null" means it is a new menu item
    		if(menuroutedto.getId().compareTo("null") == 0) {
    			getWebService().create_menu_route(menuroutedto);
    		}
    		else {
    			getWebService().modify_menu_route(menuroutedto);
    		}
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('板块内容操作成功')"));
			getHeadElements().add(
					new JsScript("window.location = './menu_route_cam.htm'"));
        }
        return true;
    }

    /**
     * Redirect to menu_route_cam.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(MenuRouteCAM.class);
        return true;
    }

    /**
     * @see org.apache.click.Page#onGet()
     */
    @Override
    public void onGet() {
    	menuRouteForm.getField(Table.PAGE).setValue("" + menuRouteTable.getPageNumber());
    	menuRouteForm.getField(Table.COLUMN).setValue(menuRouteTable.getSortedColumn());
    }

    /**
     * @see org.apache.click.Page#onPost()
     */
    @Override
    public void onPost() {
        String pageNumber = menuRouteForm.getField(Table.PAGE).getValue();
        menuRouteTable.setPageNumber(Integer.parseInt(pageNumber));
        menuRouteTable.setSortedColumn(menuRouteForm.getField(Table.COLUMN).getValue());
    }

}
