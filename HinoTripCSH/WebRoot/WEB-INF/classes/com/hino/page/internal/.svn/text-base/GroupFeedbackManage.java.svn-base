package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.FileField;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.GuideReportUpdateDto;
import com.hino.model.Group;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GroupFeedbackManage extends BasePage{
	@Bindable private Table groupTable = new Table("grouptable");
    @Bindable private ActionLink feedbackLink = new ActionLink("feedbackdetail", "查看反馈详细");

	@Override
	public void onInit() {
		feedbackLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	setRedirect("./group_feedback_single.htm?gid=" + feedbackLink.getValueInteger());
                return true;
            }
        });
		
		// ----- Display groups table -----
		groupTable.setClass(Table.CLASS_SIMPLE);
		groupTable.setWidth("100%");
		groupTable.setPageSize(10);
		groupTable.setShowBanner(true);

		Column column = new Column("id");
        column.setWidth("5%");
        groupTable.addColumn(column);
        
        // Use setDecorator to show Chinese characters and special symbos like < and >
        column = new Column("name", "团名称");
        column.setWidth("25%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return curGroup.getName(); 
			}
        });
        groupTable.addColumn(column);
        
        column = new Column("departdate","出团时间");
        column.setWidth("20%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return TimeFormater.format2(curGroup.getDepart_date());
			}
        });
        groupTable.addColumn(column);

        column = new Column("seatstaken","确认人数");
        column.setWidth("10%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return String.valueOf(curGroup.getSeats_taken());
			}
        });
        groupTable.addColumn(column);

        column = new Column("report","获取反馈数");
        column.setWidth("10%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return getSalesService().countGroupFeedback(curGroup.getId()) + "";
				
			}
        });
        groupTable.addColumn(column);

        column = new Column("action", "操作");
        column.setWidth("20%");

        ActionLink[] links = new ActionLink[]{feedbackLink};
        column.setDecorator(new LinkDecorator(groupTable, links, "id"));
        groupTable.addColumn(column);

        // Get groups from database as data provider
        

	}
	
	public void onRender()
	{
		groupTable.setDataProvider(new PagingDataProvider<Group>() {
			private static final long serialVersionUID = 8922328039647029075L;

			public List<Group> getData() { 
            	int page = groupTable.getPageNumber();
                int size = groupTable.getPageSize(); 
                List<Group> pagedList = getSalesService().list_group_by_status_paging_ordering(Info.GS_FINISHED, null, page, size, "depart_date", true);
                return pagedList;
            }

			@Override
			public int size() {
				return getSalesService().count_group_by_status(Info.GS_FINISHED);
			} 
        });
	}

}
