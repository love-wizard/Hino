package com.hino.page.internal;

import java.util.List;

import org.apache.click.Context;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.util.TimeFormater;

/**
 * Guides can view groups they applied, generate and print group information,
 * and upload group report.
 */
public class GuideGroupCAM extends BasePage {
	private static final long serialVersionUID = -994601086690891342L;

	@Bindable private Table applicableGroupTable = new Table("applicablegrouptable");
	@Bindable private Table appliedGroupTable = new Table("appliedgrouptable");
    @Bindable private ActionLink applygrouplink = new ActionLink("applygrouplink", "Apply", this, "onApplyGroup");

	@Override
	public void onInit() {

		final Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");

		// ----- Display applicable groups table -----
		applicableGroupTable.setClass(Table.CLASS_SIMPLE);
		applicableGroupTable.setWidth("100%");
		applicableGroupTable.setPageSize(10);
		applicableGroupTable.setShowBanner(true);

		Column applicableColumn = new Column("id");
        applicableColumn.setWidth("5%");
        applicableGroupTable.addColumn(applicableColumn);
        
        // Use setDecorator to show Chinese characters and special symbos like < and >
        applicableColumn = new Column("name", "Tour Name");
        applicableColumn.setWidth("25%");
        applicableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return curGroup.getName(); 
			}
        });
        applicableGroupTable.addColumn(applicableColumn);
        
        applicableColumn = new Column("departdate","Date");
        applicableColumn.setWidth("20%");
        applicableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return TimeFormater.format2(curGroup.getDepart_date());
			}
        });
        applicableGroupTable.addColumn(applicableColumn);
        
        applicableColumn = new Column("seatstaken","Confirm No.");
        applicableColumn.setWidth("10%");
        applicableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				//Kevin Zhong - 02/10/2012 - TD10 get all seats
				return String.valueOf(curGroup.gen_all_seats_for_go());
			}
        });
        applicableGroupTable.addColumn(applicableColumn);
        
        applicableColumn = new Column("guideone","Tour Guide 1");
        applicableColumn.setWidth("10%");
        applicableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return (curGroup.getGuide1() == null)?"Avaliable":"Assigned";
			}
        });
        applicableGroupTable.addColumn(applicableColumn);
        
        applicableColumn = new Column("guidetwo","Tour Guide 2");
        applicableColumn.setWidth("10%");
        applicableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return (curGroup.getGuide2() == null)?"Avaliable":"Assigned";
			}
        });
        applicableGroupTable.addColumn(applicableColumn);

        applicableColumn = new Column("action", "Process");
        applicableColumn.setWidth("10%");
        ActionLink[] links = new ActionLink[]{applygrouplink};
        applicableColumn.setDecorator(new LinkDecorator(applicableGroupTable, links, "id"));
        applicableGroupTable.addColumn(applicableColumn);

        // Get groups from database as data provider
        applicableGroupTable.setDataProvider(new PagingDataProvider<Group>() {
			private static final long serialVersionUID = 696995129383241546L;

			// group data List
			public List<Group> getData() { 
            	int page = applicableGroupTable.getPageNumber();
                int size = applicableGroupTable.getPageSize(); 
                List<Group> pagedList = getGuideService().getPagedApplicableGroup(curStaff, page, size);
                return pagedList;
            }

			// Group item data size
			@Override
			public int size() {
				return getGuideService().getApplicableGroupCount(curStaff);
			} 
        });


		// ----- Display applied groups table -----
		appliedGroupTable.setClass(Table.CLASS_SIMPLE);
		appliedGroupTable.setWidth("100%");
		appliedGroupTable.setPageSize(10);
		appliedGroupTable.setShowBanner(true);

		Column appliedColumn = new Column("id");
        appliedColumn.setWidth("5%");
        appliedGroupTable.addColumn(appliedColumn);
        
        // Use setDecorator to show Chinese characters and special symbos like < and >
        appliedColumn = new Column("appliedname", "Tour Name");
        appliedColumn.setWidth("25%");
        appliedColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return curGroup.getName(); 
			}
        });
        appliedGroupTable.addColumn(appliedColumn);
        
        appliedColumn = new Column("applieddepartdate","Date");
        appliedColumn.setWidth("20%");
        appliedColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return TimeFormater.format2(curGroup.getDepart_date());
			}
        });
        appliedGroupTable.addColumn(appliedColumn);
        
        appliedColumn = new Column("appliedseatstaken","Confirm No.");
        appliedColumn.setWidth("10%");
        appliedColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return String.valueOf(curGroup.getSeats_taken());
			}
        });
        appliedGroupTable.addColumn(appliedColumn);
        
        appliedColumn = new Column("appliedguideone","Tour Guide 1");
        appliedColumn.setWidth("10%");
        appliedColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return (curGroup.getGuide1() == null)?"Avaliable":"Assigned";
			}
        });
        appliedGroupTable.addColumn(appliedColumn);
        
        appliedColumn = new Column("appliedguidetwo","Tour Guide 2");
        appliedColumn.setWidth("10%");
        appliedColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return (curGroup.getGuide2() == null)?"Avaliable":"Assigned";
			}
        });
        appliedGroupTable.addColumn(appliedColumn);

        /*appliedColumn = new Column("appliedaction", "操作");
        appliedColumn.setWidth("10%");
        ActionLink[] links = new ActionLink[]{applygrouplink};
        appliedColumn.setDecorator(new LinkDecorator(appliedGroupTable, links, "id"));
        appliedGroupTable.addColumn(appliedColumn);*/

        // Get groups from database as data provider
        appliedGroupTable.setDataProvider(new PagingDataProvider<Group>() {
			private static final long serialVersionUID = 8599801944867796184L;

			// group data List
			public List<Group> getData() { 
            	int page = appliedGroupTable.getPageNumber();
                int size = appliedGroupTable.getPageSize(); 
                List<Group> pagedList = getGuideService().getPagedAppliedGroup(curStaff, page, size);
                return pagedList;
            }

			// Group item data size
			@Override
			public int size() {
				return getGuideService().getAppliedGroupCount(curStaff);
			} 
        });

	}

	/**
	 * Apply for a group
	 * @return
	 */
	public boolean onApplyGroup() {
    	Integer id = applygrouplink.getValueInteger();
    	getGuideService().applyGroup(id, (Staff)getContext().getSession().getAttribute("staff"));
        return true;
    }

}
