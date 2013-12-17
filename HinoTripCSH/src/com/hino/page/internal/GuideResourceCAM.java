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
public class GuideResourceCAM extends BasePage {
	private static final long serialVersionUID = -1710448938215807530L;

	@Bindable private Table mapTable = new Table("maptable");

    @Bindable private Table genGuidebookTable = new Table("genguidebooktable");

	@Bindable private Form guidebookForm = new Form("guidebookform");
    @Bindable private Table guidebookTable = new Table("guidebooktable");
    @Bindable private ActionLink guidebookDeleteLink = new ActionLink("guidebookdelete", "删除", this, "onGuidebookDeleteClick");

    @Override
    public void onInit() {
		
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

        // Get resources from database as data provider
        mapTable.setDataProvider(new DataProvider<Resource>() {
			private static final long serialVersionUID = 1845045358445922373L;

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

        // Get resources from database as data provider
        genGuidebookTable.setDataProvider(new DataProvider<Resource>() {
			private static final long serialVersionUID = 4443259818357498876L;

			// regulation files List
			public List<Resource> getData() {
				ArrayList<Resource> allGenGuidebookResources = new ArrayList<Resource>();
				ArrayList<Resource> allResources = (ArrayList<Resource>) 
					getHumanResourceService().getResourceFileList(Info.FILE_CATEGORY_INDEX_GENERAL_GUIDEBOOK);
				if(allResources.size() > 0) {
					Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");
					for(Resource curResource : allResources) {
						if(curResource.getAuthor().getId() == curStaff.getId())
							allGenGuidebookResources.add(curResource);
					}
				}
                return allGenGuidebookResources;
            }
        });

		
		// ----- Display guidebook file upload form -----
		FileField guidebookFile = new FileField("guidebookfile", "上传导游导游辞");
		guidebookForm.add(guidebookFile);

		guidebookForm.add(new Submit("saveguidebook", "上传导游导游辞", this, "onGuidebookSaveClick"));
		
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

        // Add "Delete" link
        guidebookColumn = new Column("guidebookaction", "操作");
        guidebookColumn.setWidth("25%");
        ActionLink[] guidebooklinks = new ActionLink[]{guidebookDeleteLink};
        guidebookColumn.setDecorator(new LinkDecorator(guidebookTable, guidebooklinks, "id"));
        guidebookTable.addColumn(guidebookColumn);

        guidebookDeleteLink.setAttribute("onclick", "return window.confirm('确认删除？');");

        // Get resources from database as data provider
        guidebookTable.setDataProvider(new DataProvider<Resource>() {
			private static final long serialVersionUID = 1845045358445922373L;

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
    public boolean onGuidebookDeleteClick() {
    	Integer id = guidebookDeleteLink.getValueInteger();
    	Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");
    	Resource targetRes = getHumanResourceService().get_resource(id);
    	if(targetRes.getAuthor().getId() == curStaff.getId())
    		getHumanResourceService().delete_resource(id);
        return true;
    }

    /**
     * Upload resource file and create a new resource record
     * @return
     */
    public boolean onGuidebookSaveClick() {
        if (guidebookForm.isValid()) {
    		getHumanResourceService().create_new_resource(
    				Info.FILE_CATEGORY_INDEX_GUIDEBOOK, 
    				(Staff)getContext().getSession().getAttribute("staff"), 
    				((FileField)guidebookForm.getField("guidebookfile")).getFileItem());
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('上传导游导游辞成功')"));
			getHeadElements().add(
					new JsScript("window.location = './guide_resource_cam.htm'"));
        }
        return true;
    }
}
