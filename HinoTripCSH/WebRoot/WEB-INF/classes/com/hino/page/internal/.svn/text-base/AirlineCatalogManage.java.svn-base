package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Checkbox;
import org.apache.click.control.Column;
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
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.AirlineCatalogDto;
import com.hino.dto.MenuRouteCAMDto;
import com.hino.model.AirlineCatalog;
import com.hino.model.AirlineCatalog;
import com.hino.model.WebMenu;
import com.hino.model.WebMenuRoute;
import com.hino.util.EscapeHtml;

public class AirlineCatalogManage extends BasePage {
	public String title = "airline类别管理";
	@Bindable
	private Form form = new Form("airline_catalog_cam_form");
	public Table table = new Table("airlineCatalogTable");
	private TextField AirlineCatalogName = new TextField("AirlineCatalogName", "类别名称", 50, true);
	private Checkbox defaultCatalog = new Checkbox("defaultCatalog", "设为默认分类");
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改",this, "onEditClick");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	public void onInit()
	{
		
//		
//		editLink.setActionListener(new ActionListener() {
//			private static final long serialVersionUID = 1L;
//			public boolean onAction(Control source) {
//            	Integer id = editLink.getValueInteger(); 
//                setRedirect("./airline_cam.htm?id=" + id);
//                return true;
//            }
//		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getAirlineService().deleteAirlineCatalog(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
		
		Select select = new Select("category", "分类");
		//select.add(new Option("提问种类"));
        
//        DataProvider<Option> DataPrvd = new DataProvider<Option>() {
//			private static final long serialVersionUID = 6850715354692755450L;
//
//			public List<Option> getData() {
//				List<Option> Options = new ArrayList<Option>();
//				Option opt = new Option("航线分类");
//				Options.add(1, opt);
//                return Options;
//            }
//        };
		Option opt = new Option(1,"航线分类");
        select.add(opt);
        select.setDefaultOption(opt);
		
		form.add(select);
		form.add(AirlineCatalogName);
		form.add(defaultCatalog);
		HiddenField id = new HiddenField("id", String.class);
		id.setValue("null");
		form.add(id);
		form.add(new Submit("save", "提交", this, "onSaveClick"));
		form.add(new Submit("cancel", "取消提交", this, "onCancelClick"));
		

		//table = new Table("AirlineCatalogTable");
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setPageSize(10);
        
        table.setShowBanner(true); 
        //table.setSortable(true); 
        
        table.addColumn(new Column("catalog_type", "类别")); 
        table.addColumn(new Column("catalog_name", "名称"));
        table.addColumn(new Column("catalog_default", "默认分类")); 
         
        Column column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<AirlineCatalog>() {
        	
            public List<AirlineCatalog> getData() { 
            	int p = table.getPageNumber();
                int count = table.getPageSize(); 
                List<AirlineCatalog> l = getAirlineService().getAirlineCatalogList();
                return l;
            }

			@Override
			public int size() {
				return getAirlineService().getAirlineCatalogCount();
			} 
        }); 
        
        
	}
	
	
	public boolean onEditClick() {
        Integer id = editLink.getValueInteger();
        AirlineCatalog ac = getAirlineService().getAirlineCatalog(id); 
        if (ac != null) {
        	form.getField("id").setValue(String.valueOf(ac.getId()));
        	form.getField("category").setValue(ac.getCatalog_type());
        	form.getField("AirlineCatalogName").setValue(ac.getCatalog_name());
        	((Checkbox)form.getField("defaultCatalog")).setChecked(ac.isCatalog_default());
        }
        return true;
    }
	
	public boolean onSaveClick() {
        if (form.isValid()) {
        	AirlineCatalogDto acDto = new AirlineCatalogDto(); 
    		//form.copyTo(acDto);
    		if(form.getField("id").getValue().endsWith("null"))
    		{
    			acDto.setId(-1);
    		}
    		else
    		{
    			acDto.setId( Integer.parseInt(form.getField("id").getValue()));
    		}
    		
    		acDto.setCatalog_name(form.getField("AirlineCatalogName").getValue());
    		acDto.setCatalog_type(form.getField("category").getValue());
    		acDto.setCatalog_default(((Checkbox)form.getField("defaultCatalog")).isChecked());

    		// "null" means it is a new menu item
    		if(acDto.getId() == -1) {
    			getAirlineService().addAirlineCatalog(acDto);
    		}
    		else {
    			getAirlineService().updateAirlineCatalog(acDto);
    		}
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('航线分类操作成功')"));
			getHeadElements().add(
					new JsScript("window.location = './airline_catalog_manage.htm'"));
        }
        return true;
    }

    /**
     * Redirect to menu_route_cam.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(AirlineCatalogManage.class);
        return true;
    }

}
