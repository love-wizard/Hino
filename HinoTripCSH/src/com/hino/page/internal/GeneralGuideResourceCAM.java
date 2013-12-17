package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.model.Resource;
import com.hino.model.Staff;
import com.hino.util.Info;

/**
 * Upload and manage reguation files
 */
public class GeneralGuideResourceCAM extends BasePage {
	private static final long serialVersionUID = -7966373114878080910L;
	
	@Bindable private Form mapForm = new Form("mapform");
    @Bindable private Table mapTable = new Table("maptable");
    @Bindable private ActionLink mapDeleteLink = new ActionLink("mapdelete", "删除", this, "onMapDeleteClick");

	@Bindable private Form genGuidebookForm = new Form("genguidebookform");
    @Bindable private Table genGuidebookTable = new Table("genguidebooktable");
    @Bindable private ActionLink genGuidebookDeleteLink = new ActionLink("genguidebookdelete", "删除", this, "onGenGuidebookDeleteClick");

    @Bindable private Table guidebookTable = new Table("guidebooktable");

    @Override
    public void onInit() {
		
		// ----- Display map file upload form -----
		FileField mapFile = new FileField("mapfile", "上传地图");
		mapForm.add(mapFile);

		mapForm.add(new Submit("savemap", "上传地图", this, "onMapSaveClick"));
		
		// ----- Display map file list table -----
		mapTable.setClass(Table.CLASS_SIMPLE);
		mapTable.setWidth("100%");

		Column mapColumn = new Column("id");
		mapColumn.setWidth("5%");
        mapTable.addColumn(mapColumn);
        
        mapColumn = new Column("mapfilename","文件名");
        mapColumn.setWidth("50%");
        mapColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Resource curResource = (Resource) object;
				return "<a href='files.htm?filename="+curResource.getFilename()+"'>"
					+curResource.getName()+"</a>";
			}
        });
        mapTable.addColumn(mapColumn);

        // Add "Delete" link
        mapColumn = new Column("mapaction", "操作");
        mapColumn.setWidth("25%");
        ActionLink[] maplinks = new ActionLink[]{mapDeleteLink};
        mapColumn.setDecorator(new LinkDecorator(mapTable, maplinks, "id"));
        mapTable.addColumn(mapColumn);

        mapDeleteLink.setAttribute("onclick", "return window.confirm('确认删除？');");

        // Get resources from database as data provider
        mapTable.setDataProvider(new DataProvider<Resource>() {
			private static final long serialVersionUID = 1909822229128694120L;

			// regulation files List
			public List<Resource> getData() {
				ArrayList<Resource> allMapResources = new ArrayList<Resource>();
				ArrayList<Resource> curResources = (ArrayList<Resource>) 
					getHumanResourceService().getResourceFileList(Info.FILE_CATEGORY_INDEX_MAP);
				if(curResources.size() > 0)
					allMapResources.addAll(curResources);
                return allMapResources;
            }
        });
        		
        
		// ----- Display general guidebook file upload form -----
		FileField genGuidebookFile = new FileField("genguidebookfile", "上传公司导游辞");
		genGuidebookForm.add(genGuidebookFile);

		genGuidebookForm.add(new Submit("savegenguidebook", "上传公司导游辞", this, "onGenGuidebookSaveClick"));
		
		// ----- Display general guidebook file list table -----
		genGuidebookTable.setClass(Table.CLASS_SIMPLE);
		genGuidebookTable.setWidth("100%");

		Column genGuidebookColumn = new Column("id");
        genGuidebookColumn.setWidth("5%");
        genGuidebookTable.addColumn(genGuidebookColumn);
        
        genGuidebookColumn = new Column("genguidebookfilename","文件名");
        genGuidebookColumn.setWidth("50%");
        genGuidebookColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Resource curResource = (Resource) object;
				return "<a href='files.htm?filename="+curResource.getFilename()+"'>"
					+curResource.getName()+"</a>";
			}
        });
        genGuidebookTable.addColumn(genGuidebookColumn);

        // Add "Delete" link
        genGuidebookColumn = new Column("genguidebookaction", "操作");
        genGuidebookColumn.setWidth("25%");
        ActionLink[] genguidebooklinks = new ActionLink[]{genGuidebookDeleteLink};
        genGuidebookColumn.setDecorator(new LinkDecorator(genGuidebookTable, genguidebooklinks, "id"));
        genGuidebookTable.addColumn(genGuidebookColumn);

        genGuidebookDeleteLink.setAttribute("onclick", "return window.confirm('确认删除？');");

        // Get resources from database as data provider
        genGuidebookTable.setDataProvider(new DataProvider<Resource>() {
			private static final long serialVersionUID = -1063703722550064011L;

			// regulation files List
			public List<Resource> getData() {
				ArrayList<Resource> allGenGuidebookResources = new ArrayList<Resource>();
				ArrayList<Resource> curResources = (ArrayList<Resource>) 
					getHumanResourceService().getResourceFileList(Info.FILE_CATEGORY_INDEX_GENERAL_GUIDEBOOK);
				if(curResources.size() > 0)
					allGenGuidebookResources.addAll(curResources);
                return allGenGuidebookResources;
            }
        });

		// ----- Display guidebook file list table -----
		guidebookTable.setClass(Table.CLASS_SIMPLE);
		guidebookTable.setWidth("100%");

		Column guidebookColumn = new Column("id");
		guidebookColumn.setWidth("5%");
        guidebookTable.addColumn(guidebookColumn);
        
        guidebookColumn = new Column("guidebookfilename","文件名");
        guidebookColumn.setWidth("50%");
        guidebookColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Resource curResource = (Resource) object;
				return "<a href='files.htm?filename="+curResource.getFilename()+"'>"
					+curResource.getName()+"</a>";
			}
        });
        guidebookTable.addColumn(guidebookColumn);

        // Get resources from database as data provider
        guidebookTable.setDataProvider(new DataProvider<Resource>() {
			private static final long serialVersionUID = 2089592152304372668L;

			// regulation files List
			public List<Resource> getData() {
				ArrayList<Resource> allGuidebookResources = new ArrayList<Resource>();
				ArrayList<Resource> curResources = (ArrayList<Resource>) 
					getHumanResourceService().getResourceFileList(Info.FILE_CATEGORY_INDEX_GUIDEBOOK);
				if(curResources.size() > 0)
					allGuidebookResources.addAll(curResources);
                return allGuidebookResources;
            }
        });
        
    }
    
	/**
	 * Delete target Resource from database
	 * @return
	 */
    public boolean onMapDeleteClick() {
    	Integer id = mapDeleteLink.getValueInteger();
    	getHumanResourceService().delete_resource(id);
        return true;
    }

    /**
     * Upload resource file and create a new resource record
     * @return
     */
    public boolean onMapSaveClick() {
        if (mapForm.isValid()) {
    		getHumanResourceService().create_new_resource(
    				Info.FILE_CATEGORY_INDEX_MAP, 
    				(Staff)getContext().getSession().getAttribute("staff"), 
    				((FileField)mapForm.getField("mapfile")).getFileItem());
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('上传地图成功')"));
			getHeadElements().add(
					new JsScript("window.location = './general_guide_resource_cam.htm'"));
        }
        return true;
    }
    
	/**
	 * Delete target Resource from database
	 * @return
	 */
    public boolean onGenGuidebookDeleteClick() {
    	Integer id = genGuidebookDeleteLink.getValueInteger();
    	getHumanResourceService().delete_resource(id);
        return true;
    }

    /**
     * Upload resource file and create a new resource record
     * @return
     */
    public boolean onGenGuidebookSaveClick() {
        if (genGuidebookForm.isValid()) {
    		getHumanResourceService().create_new_resource(
    				Info.FILE_CATEGORY_INDEX_GENERAL_GUIDEBOOK, 
    				(Staff)getContext().getSession().getAttribute("staff"), 
    				((FileField)genGuidebookForm.getField("genguidebookfile")).getFileItem());
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('上传公司导游辞成功')"));
			getHeadElements().add(
					new JsScript("window.location = './general_guide_resource_cam.htm'"));
        }
        return true;
    }

}
