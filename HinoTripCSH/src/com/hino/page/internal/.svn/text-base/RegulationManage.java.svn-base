package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.RegulationManageDto;
import com.hino.model.Resource;
import com.hino.model.Staff;
import com.hino.util.Info;

/**
 * Upload and manage reguation files
 */
public class RegulationManage extends BasePage {
	private static final long serialVersionUID = -2235171641740005231L;
	
	@Bindable private Form regulationForm = new Form("regulationform");
    @Bindable private Table regulationTable = new Table("regulationtable");
    @Bindable private ActionLink deleteLink = new ActionLink("delete", "删除", this, "onDeleteClick");

    @Override
    public void onInit() {
		
		// ----- Display regulation file upload form -----
		Select categorySlct = new Select("category", "规章制度分类", true);
		categorySlct.setDefaultOption(new Option("", "请选择分类"));
        DataProvider<Option> categoryDataPrvd = new DataProvider<Option>() {
			private static final long serialVersionUID = -6052715838503445458L;

			public List<Option> getData() {
				List<Option> Options = new ArrayList<Option>();
				for (int index=Info.FILE_CATEGORY_INDEX_REG_SALESMAN; 
					index<Info.FILE_CATEGORY_INDEX_REG_FINANCE; index++) {
					Options.add(new Option(index, Info.FILE_CATEGORY_NAME[index]));
				}
                return Options;
            }
        };
        categorySlct.setDataProvider(categoryDataPrvd);
        regulationForm.add(categorySlct);

		FileField regulationFile = new FileField("regulationfile", "上传规章制度文件");
		regulationFile.setRequired(true);
		regulationForm.add(regulationFile);

		regulationForm.add(new Submit("save", "提交新规章制度", this, "onSaveClick"));
		regulationForm.add(new Submit("cancel", "取消提交", this, "onCancelClick"));
		
		// ----- Display regulation file list table -----
		regulationTable.setClass(Table.CLASS_SIMPLE);
		regulationTable.setWidth("100%");

		Column column = new Column("id");
        column.setWidth("5%");
        regulationTable.addColumn(column);
        
        column = new Column("category", "规章制度分类");
        column.setWidth("20%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Resource curResource = (Resource) object;
				return Info.FILE_CATEGORY_NAME[curResource.getCategory()]; 
			}
        });
        regulationTable.addColumn(column);
        
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
        regulationTable.addColumn(column);

        // Add "Delete" link
        column = new Column("action", "操作");
        column.setWidth("25%");
        ActionLink[] links = new ActionLink[]{deleteLink};
        column.setDecorator(new LinkDecorator(regulationTable, links, "id"));
        regulationTable.addColumn(column);

        deleteLink.setAttribute("onclick", "return window.confirm('确认删除？');");

        // Get regulation resources from database as data provider
        regulationTable.setDataProvider(new DataProvider<Resource>() {
			private static final long serialVersionUID = 1223944093304256886L;

			// regulation files List
			public List<Resource> getData() {
				ArrayList<Resource> allRegulationResources = new ArrayList<Resource>();
				for (int index=Info.FILE_CATEGORY_INDEX_REG_SALESMAN; 
						index<Info.FILE_CATEGORY_INDEX_REG_FINANCE; index++) {
					ArrayList<Resource> curCatResources = (ArrayList<Resource>) 
						getHumanResourceService().getResourceFileList(index);
					if(curCatResources.size() > 0)
						allRegulationResources.addAll(curCatResources);
				}
                return allRegulationResources;
            }
        });
    }
    
	/**
	 * Delete target Resource from database
	 * @return
	 */
    public boolean onDeleteClick() {
    	Integer id = deleteLink.getValueInteger();
    	getHumanResourceService().delete_resource(id);
        return true;
    }

    /**
     * Upload regulation file and create a new resource record
     * @return
     */
    public boolean onSaveClick() {
        if (regulationForm.isValid()) {
        	RegulationManageDto regulationdto = new RegulationManageDto(); 
    		regulationForm.copyTo(regulationdto);
    		
    		// Set regulation FileItem
    		regulationdto.setRegulationfile(
    				((FileField)regulationForm.getField("regulationfile")).getFileItem());
    		
    		// Set author
    		regulationdto.setAuthor((Staff)getContext().getSession().getAttribute("staff"));

    		getHumanResourceService().create_new_regulation(regulationdto);
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('上传规章制度成功')"));
			getHeadElements().add(
					new JsScript("window.location = './regulation_manage.htm'"));
        }
        return true;
    }

    /**
     * Redirect to menu_cam.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(RegulationManage.class);
        return true;
    }

}
