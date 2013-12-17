package com.hino.page.internal;

import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.MenuCAMDto;
import com.hino.model.WebMenu;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;

/**
 * Create and manage external pages' menu items
 */
public class MenuCAM extends BasePage {
	
	private static final long serialVersionUID = 9195298213706223248L;

	@Bindable private Form menuForm = new Form("menuform");
    @Bindable private Table menuTable = new Table("menutable");
    @Bindable private ActionLink editLink = new ActionLink("edit", "编辑描述", this, "onEditClick");
    @Bindable private ActionLink deleteLink = new ActionLink("delete", "删除板块", this, "onDeleteClick");
    @Bindable private Image img = new Image("image");
    @Bindable private Image img_en = new Image("image_en");

    @Override
    public void onInit() {
		
		// ----- Display menu form -----
		TextField menuName = new TextField("name", "板块名称", 50, true);
		menuName.setTrim(true);
		menuForm.add(menuName);
		TextField menuName_en = new TextField("name_en", "板块名称(English)", 50, true);
		menuName_en.setTrim(true);
		menuForm.add(menuName_en);
		
		FileField img = new FileField("img", "上传板块图");
		menuForm.add(img);
		FileField img_en = new FileField("img_en", "上传板块图(English)");
		menuForm.add(img_en);
		
		TextArea menuDesc = new TextArea("description", "板块描述", 50, 10, false);
		menuDesc.setTrim(true);
		menuForm.add(menuDesc);
		
		TextArea menuDesc_en = new TextArea("description_en", "板块描述(English)", 50, 10, false);
		menuDesc.setTrim(true);
		menuForm.add(menuDesc_en);
		
		menuForm.add(new Submit("save", "提交板块", this, "onSaveClick"));
		menuForm.add(new Submit("cancel", "取消提交", this, "onCancelClick"));
		// Hidden field contains data base menu item id
		HiddenField menuIdFld = new HiddenField("id", String.class);
		// Set its default value as "null" so that if the form is submitted with "null",
		// it means creating a new menu item, otherwise modifying existing one
		menuIdFld.setValue("null");
		menuForm.add(menuIdFld);
		// PAGE and COLUMN are used for table paging
		menuForm.add(new HiddenField(Table.PAGE, String.class));
		menuForm.add(new HiddenField(Table.COLUMN, String.class));

		// ----- Display menu table -----
		menuTable.setClass(Table.CLASS_SIMPLE);
		menuTable.setWidth("100%");
		menuTable.setPageSize(10);
		menuTable.setShowBanner(true);

		Column column = new Column("id");
        column.setWidth("5%");
        menuTable.addColumn(column);
        
        // Use setDecorator to show Chinese characters and special symbos like < and >
        column = new Column("name", "板块名称");
        column.setWidth("30%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				WebMenu curMenu = (WebMenu) object;
				return curMenu.getName(); 
			}
        });
        menuTable.addColumn(column);
        
        column = new Column("description","板块描述");
        column.setWidth("50%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				WebMenu curMenu = (WebMenu) object;
				if(curMenu.getDescription() != null) {
					// Replace "\n" to "<br />" to make new lines in HTML
					return curMenu.getDescription().replace("\n","<br />"); 
				}
				else
					return "";
			}
        });
        menuTable.addColumn(column);

        // Add "Edit" and "Delete" link
        column = new Column("action", "操作");
        column.setWidth("15%");
        ActionLink[] links = new ActionLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(menuTable, links, "id"));
        menuTable.addColumn(column);

        deleteLink.setAttribute("onclick", "return window.confirm('确认删除板块？');");

        // Get menu items from database as data provider
        menuTable.setDataProvider(new PagingDataProvider<WebMenu>() {
        	
			private static final long serialVersionUID = 2697343651571299765L;

			// Menu item data List
			public List<WebMenu> getData() { 
            	int page = menuTable.getPageNumber();
                int size = menuTable.getPageSize(); 
                List<WebMenu> pagedList = getWebService().getPagedMenuList(page, size);
                for(int index=0; index<pagedList.size(); index++) {
                	if(pagedList.get(index).getId() == -1) {
                		pagedList.remove(index); // Remove index menu
                	}
                }
                return pagedList;
            }

			// Menu item data size
			@Override
			public int size() {
				return getWebService().getMenuCount()-1; // Remove index menu
			} 
        });
    }

	/**
	 * Get target WebMenu object and copy its values into form
	 * @return
	 */
	public boolean onEditClick() {
        Integer id = editLink.getValueInteger();
        WebMenu targetMenu = getWebService().getMenuById(id);
        if (targetMenu != null) {
        	menuForm.getField("id").setValue(String.valueOf(targetMenu.getId()));
        	// Cange &lt; back into < for TextField and TextArea
        	//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+menuForm.getField("name")==null);
        	menuForm.getField("name").setValue(EscapeHtml.htmlDecode(targetMenu.getName()));
        	//menuForm.getField("name").setDisabled(true);//Disable won't be submitted to server
        	menuForm.getField("name").setReadonly(true);
        	
        	menuForm.getField("name_en").setValue(EscapeHtml.htmlDecode(targetMenu.getName_en()));
        	//menuForm.getField("name").setDisabled(true);//Disable won't be submitted to server
        	menuForm.getField("name_en").setReadonly(true);
        	menuForm.getField("description").setValue(EscapeHtml.htmlDecode(targetMenu.getDescription()));
        	menuForm.getField("description_en").setValue(EscapeHtml.htmlDecode(targetMenu.getDescription_en()));
        	if(targetMenu.getImageUrl() != null && targetMenu.getImageUrl().length() > 0) {
    			img.setSrc(Info.INTERNAL_PATH_PREFIX+targetMenu.getImageUrl());
    		}
        	if(targetMenu.getImageUrl_en() != null && targetMenu.getImageUrl_en().length() > 0) {
    			img_en.setSrc(Info.INTERNAL_PATH_PREFIX+targetMenu.getImageUrl_en());
    		}
        }
        return true;
    }

	/**
	 * Delete target WebMenu from database
	 * @return
	 */
    public boolean onDeleteClick() {
    	Integer id = deleteLink.getValueInteger();
    	getWebService().delete_menu(id);
        return true;
    }

    /**
     * Create a new menu item or modify an existing one
     * @return
     */
    public boolean onSaveClick() {
        if (menuForm.isValid()) {
        	MenuCAMDto menudto = new MenuCAMDto(); 
    		menuForm.copyTo(menudto);

    		// Change < into &lt;
    		menudto.setName(EscapeHtml.htmlEncode(menudto.getName()));
    		menudto.setDescription(EscapeHtml.htmlEncode(menudto.getDescription()));
    		menudto.setName_en(EscapeHtml.htmlEncode(menudto.getName_en()));
    		menudto.setDescription_en(EscapeHtml.htmlEncode(menudto.getDescription_en()));
    		// Set image FileItem
    		menudto.setImage(((FileField)menuForm.getField("img")).getFileItem());
    		menudto.setImage_en(((FileField)menuForm.getField("img_en")).getFileItem());

    		// "null" means it is a new menu item
    		if(menudto.getId().compareTo("null") == 0) {
    			getWebService().create_menu(menudto);
    		}
    		else {
    			getWebService().modify_menu(menudto);
    		}
    		
    		/* The following lines can add error messages into the form.
    		 * If database operation leads to errors, these lines can be used.
    			if(!menuCResult.isSucc()) {
    			menuForm.setError("Database operation failed.");
    		}*/
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('板块操作成功')"));
			getHeadElements().add(
					new JsScript("window.location = './menu_cam.htm'"));
        }
        return true;
    }

    /**
     * Redirect to menu_cam.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(MenuCAM.class);
        return true;
    }

    /**
     * @see org.apache.click.Page#onGet()
     */
    @Override
    public void onGet() {
    	menuForm.getField(Table.PAGE).setValue("" + menuTable.getPageNumber());
    	menuForm.getField(Table.COLUMN).setValue(menuTable.getSortedColumn());
    }

    /**
     * @see org.apache.click.Page#onPost()
     */
    @Override
    public void onPost() {
        String pageNumber = menuForm.getField(Table.PAGE).getValue();
        menuTable.setPageNumber(Integer.parseInt(pageNumber));
        menuTable.setSortedColumn(menuForm.getField(Table.COLUMN).getValue());
    }

}
