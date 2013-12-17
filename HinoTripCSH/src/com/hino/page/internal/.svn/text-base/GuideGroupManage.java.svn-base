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
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.GuideReportUpdateDto;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.util.TimeFormater;

/**
 * Guides can view groups they applied, generate and print group information,
 * and upload group report.
 */
public class GuideGroupManage extends BasePage {
	private static final long serialVersionUID = 3692307117701640236L;
	
	@Bindable private Form groupForm = new Form("groupform");
    @Bindable private Table groupTable = new Table("grouptable");
    @Bindable private ActionLink genInfoTabLink = new ActionLink("geninfotable", "Create List", this, "onGenInfoTabClick");
    @Bindable private ActionLink showUploadLink = new ActionLink("showupload", "Upload Tour Report", this, "onShowUploadClick");

	@Override
	public void onInit() {

		final Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");
		
		// ----- upload form -----
		HiddenField groupIdFld = new HiddenField("groupid", String.class);
		groupIdFld.setId("targetGroupId");
		groupIdFld.setValue("null");
		groupForm.add(groupIdFld);
		FileField reportFile = new FileField("reportfile", "Upload Tour Report");
		reportFile.setRequired(true);
		groupForm.add(reportFile);
		groupForm.add(new Submit("save", "Submit Tour Report", this, "onUploadClick"));
		// PAGE and COLUMN are used for table paging
		groupForm.add(new HiddenField(Table.PAGE, String.class));
		groupForm.add(new HiddenField(Table.COLUMN, String.class));

		// ----- Display groups table -----
		groupTable.setClass(Table.CLASS_SIMPLE);
		groupTable.setWidth("100%");
		groupTable.setPageSize(10);
		groupTable.setShowBanner(true);

		Column column = new Column("id");
        column.setWidth("5%");
        groupTable.addColumn(column);
        
        // Use setDecorator to show Chinese characters and special symbos like < and >
        column = new Column("name", "Tour Name");
        column.setWidth("25%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return curGroup.getName(); 
			}
        });
        groupTable.addColumn(column);
        
        column = new Column("departdate","Date");
        column.setWidth("20%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return TimeFormater.format2(curGroup.getDepart_date());
			}
        });
        groupTable.addColumn(column);

        column = new Column("seatstaken","Confirm No.");
        column.setWidth("10%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return String.valueOf(curGroup.getSeats_taken());
			}
        });
        groupTable.addColumn(column);

        column = new Column("report","Report");
        column.setWidth("10%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				if(curGroup.getGuide1() != null
					&& curGroup.getGuide1().getId() == curStaff.getId()
					&& curGroup.getGuide1ReportUrl() != null)
					return "<a href='files.htm?filename="+curGroup.getGuide1ReportUrl()+"'>下载</a>";
				else if(curGroup.getGuide2() != null
					&& curGroup.getGuide2().getId() == curStaff.getId()
					&& curGroup.getGuide2ReportUrl() != null)
					return "<a href='files.htm?filename="+curGroup.getGuide2ReportUrl()+"'>下载</a>";
				else
					return "Waiting Upload";
			}
        });
        groupTable.addColumn(column);

        column = new Column("feedback","Feedback");
        column.setWidth("10%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				if(curGroup.getGuide1() != null
					&& curGroup.getGuide1().getId() == curStaff.getId()
					&& curGroup.getGuide1ReviewUrl() != null)
					return "<a href='files.htm?filename="+curGroup.getGuide1ReviewUrl()+"'>下载</a>";
				else if(curGroup.getGuide2() != null
					&& curGroup.getGuide2().getId() == curStaff.getId()
					&& curGroup.getGuide2ReviewUrl() != null)
					return "<a href='files.htm?filename="+curGroup.getGuide2ReviewUrl()+"'>下载</a>";
				else
					return "Waiting Feedback";
			}
        });
        groupTable.addColumn(column);

        column = new Column("action", "Process");
        column.setWidth("20%");
        showUploadLink.setAttribute("class", "uploadButton");
        ActionLink[] links = new ActionLink[]{genInfoTabLink, showUploadLink};
        column.setDecorator(new LinkDecorator(groupTable, links, "id"));
        groupTable.addColumn(column);

        // Get groups from database as data provider
        groupTable.setDataProvider(new PagingDataProvider<Group>() {
			private static final long serialVersionUID = 8922328039647029075L;

			// group data List
			public List<Group> getData() { 
            	int page = groupTable.getPageNumber();
                int size = groupTable.getPageSize(); 
                List<Group> pagedList = getGuideService().getPagedAssignedGroupList(curStaff, page, size);
                return pagedList;
            }

			// Menu item data size
			@Override
			public int size() {
				return getGuideService().getAssignedGroupCount(curStaff);
			} 
        });

	}

	/**
	 * Generate group information
	 * @return
	 */
	public boolean onGenInfoTabClick() {
		setRedirect("./group_table.htm?groupid="+genInfoTabLink.getValueInteger());
		getHeadElements().add(new JsScript("alert('NOT IMPLEMENTED YET!')"));
        return true;
    }

	/**
	 * Show report upload form
	 * @return
	 */
    public boolean onShowUploadClick() {
    	// Do nothing here, javascript will set upload form target group id 
    	// and show upload facebox
        return false;
    }

    /**
     * Upload guide group report
     * @return
     */
    public boolean onUploadClick() {
    	
        if (groupForm.isValid()) {
        	GuideReportUpdateDto guidereportdto = new GuideReportUpdateDto(); 
        	groupForm.copyTo(guidereportdto);
    		
    		// Set report FileItem
        	guidereportdto.setReportfile(
    				((FileField)groupForm.getField("reportfile")).getFileItem());
    		
    		// Set guide object
        	guidereportdto.setGuide((Staff)getContext().getSession().getAttribute("staff"));

        	getGuideService().uploadGuideReport(guidereportdto);
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('Upload Tour Report Successfully')"));
			getHeadElements().add(
					new JsScript("window.location = './guide_group_manage.htm'"));
        }
        return true;
    }

    /**
     * @see org.apache.click.Page#onGet()
     */
    @Override
    public void onGet() {
    	groupForm.getField(Table.PAGE).setValue("" + groupTable.getPageNumber());
    	groupForm.getField(Table.COLUMN).setValue(groupTable.getSortedColumn());
    }

    /**
     * @see org.apache.click.Page#onPost()
     */
    @Override
    public void onPost() {
        String pageNumber = groupForm.getField(Table.PAGE).getValue();
        groupTable.setPageNumber(Integer.parseInt(pageNumber));
        groupTable.setSortedColumn(groupForm.getField(Table.COLUMN).getValue());
    }

}
