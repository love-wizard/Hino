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

import com.hino.dto.GuideReviewUpdateDto;
import com.hino.model.Group;
import com.hino.util.TimeFormater;

/**
 * Guide manager can upload guide review for every group
 */
public class GeneralGuideReview extends BasePage {
	private static final long serialVersionUID = 8303078310974295764L;
	
	@Bindable private Form groupForm = new Form("groupform");
    @Bindable private Table groupTable = new Table("grouptable");
    @Bindable private ActionLink showGuide1UploadLink = new ActionLink("showupload", "上传导游一反馈", this, "onShowUploadClick");
    @Bindable private ActionLink showGuide2UploadLink = new ActionLink("showupload", "上传导游二反馈", this, "onShowUploadClick");

	@Override
	public void onInit() {

		// ----- upload form -----
		HiddenField groupIdFld = new HiddenField("groupid", String.class);
		groupIdFld.setId("targetGroupId");
		groupIdFld.setValue("null");
		groupForm.add(groupIdFld);
		HiddenField guideFld = new HiddenField("guidetype", String.class);
		guideFld.setId("targetGuideType");
		guideFld.setValue("null");
		groupForm.add(guideFld);
		FileField reviewFile = new FileField("reviewfile", "上传导游反馈");
		reviewFile.setRequired(true);
		groupForm.add(reviewFile);
		groupForm.add(new Submit("save", "提交导游反馈", this, "onUploadClick"));
		// PAGE and COLUMN are used for table paging
		groupForm.add(new HiddenField(Table.PAGE, String.class));
		groupForm.add(new HiddenField(Table.COLUMN, String.class));

		// ----- Display groups table -----
		groupTable.setClass(Table.CLASS_SIMPLE);
		groupTable.setWidth("100%");
		groupTable.setPageSize(10);
		groupTable.setShowBanner(true);

		Column column = new Column("id");
        column.setWidth("3%");
        groupTable.addColumn(column);
        
        // Use setDecorator to show Chinese characters and special symbos like < and >
        column = new Column("name", "团名称");
        column.setWidth("15%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return curGroup.getName(); 
			}
        });
        groupTable.addColumn(column);
        
        column = new Column("departdate","出团时间");
        column.setWidth("10%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return TimeFormater.format2(curGroup.getDepart_date());
			}
        });
        groupTable.addColumn(column);

        column = new Column("guidename","导游一 | 导游二");
        column.setWidth("20%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				String guideName = "";
				if(curGroup.getGuide1() != null)
					guideName = guideName + curGroup.getGuide1().genFullName() + " | ";
				else
					guideName = guideName + "未指派 | ";
				if(curGroup.getGuide2() != null)
					guideName = guideName + curGroup.getGuide2().genFullName();
				else
					guideName = guideName + "未指派";
				return guideName;
			}
        });
        groupTable.addColumn(column);

        column = new Column("guide1report","导游一报告");
        column.setWidth("8%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				if(curGroup.getGuide1() != null
					&& curGroup.getGuide1ReportUrl() != null)
					return "<a href='files.htm?filename="+curGroup.getGuide1ReportUrl()+"'>下载</a>";
				else
					return "尚无报告";
			}
        });
        groupTable.addColumn(column);

        column = new Column("guide2report","导游二报告");
        column.setWidth("8%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				if(curGroup.getGuide2() != null
					&& curGroup.getGuide2ReportUrl() != null)
					return "<a href='files.htm?filename="+curGroup.getGuide2ReportUrl()+"'>下载</a>";
				else
					return "尚无报告";
			}
        });
        groupTable.addColumn(column);

        column = new Column("guide1review","导游一反馈");
        column.setWidth("8%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				if(curGroup.getGuide1() != null
					&& curGroup.getGuide1ReviewUrl() != null)
					return "<a href='files.htm?filename="+curGroup.getGuide1ReviewUrl()+"'>下载</a>";
				else
					return "未上传";
			}
        });
        groupTable.addColumn(column);

        column = new Column("guide2review","导游二反馈");
        column.setWidth("8%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				if(curGroup.getGuide2() != null
					&& curGroup.getGuide2ReviewUrl() != null)
					return "<a href='files.htm?filename="+curGroup.getGuide2ReviewUrl()+"'>下载</a>";
				else
					return "未上传";
			}
        });
        groupTable.addColumn(column);

        column = new Column("action", "操作");
        column.setWidth("20%");
        showGuide1UploadLink.setAttribute("class", "uploadGuide1Button");
        showGuide2UploadLink.setAttribute("class", "uploadGuide2Button");
        ActionLink[] links = new ActionLink[]{showGuide1UploadLink, showGuide2UploadLink};
        column.setDecorator(new LinkDecorator(groupTable, links, "id"));
        groupTable.addColumn(column);

        // Get groups from database as data provider
        groupTable.setDataProvider(new PagingDataProvider<Group>() {
			private static final long serialVersionUID = -7133910663327969693L;

			// group data List
			public List<Group> getData() { 
            	int page = groupTable.getPageNumber();
                int size = groupTable.getPageSize(); 
                List<Group> pagedList = getGuideService().getPagedReviewableGroupList(page, size);
                return pagedList;
            }

			// Menu item data size
			@Override
			public int size() {
				return getGuideService().getReviewableGroupCount();
			} 
        });

	}

	/**
	 * Show review upload form
	 * @return
	 */
    public boolean onShowUploadClick() {
    	// Do nothing here, javascript will set upload form target group id 
    	// and show upload facebox
        return false;
    }

    /**
     * Upload guide group review
     * @return
     */
    public boolean onUploadClick() {
    	
        if (groupForm.isValid()) {
        	GuideReviewUpdateDto guidereviewdto = new GuideReviewUpdateDto(); 
        	groupForm.copyTo(guidereviewdto);
    		
    		// Set report FileItem
        	guidereviewdto.setReviewfile(
    				((FileField)groupForm.getField("reviewfile")).getFileItem());
    		
        	getGuideService().uploadGuideReview(guidereviewdto);
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('上传导游反馈成功')"));
			getHeadElements().add(
					new JsScript("window.location = './general_guide_review.htm'"));
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
