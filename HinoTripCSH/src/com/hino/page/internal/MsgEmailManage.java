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

import com.hino.click.extension.Image;
import com.hino.model.Message;
import com.hino.model.Staff;
import com.hino.util.TimeFormater;

/**
 * System message list
 */
public class MsgEmailManage extends BasePage {
	private static final long serialVersionUID = 8962466304055094136L;
	
	@Bindable private Table msgTable = new Table("msgtable");
    @Bindable private ActionLink detailLink = new ActionLink("detail", "View Detail", this, "onDetailClick");
	@SuppressWarnings("unused")
	@Bindable private String title = null;
	@SuppressWarnings("unused")
	@Bindable private String date = null;
	@SuppressWarnings("unused")
	@Bindable private String content = null;
	@SuppressWarnings("unused")
	@Bindable private String attachment = null;

	private Staff curStaff; 
	@Override
	public void onInit() {
		
		// ----- Display sytem message table -----
		msgTable.setClass(Table.CLASS_SIMPLE);
		msgTable.setWidth("100%");
		msgTable.setPageSize(10);
		msgTable.setShowBanner(true);

		Column column = new Column("id");
        column.setWidth("5%");
        msgTable.addColumn(column);
        
        column = new Column("date", "Date");
        column.setWidth("10%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Message curMsg = (Message) object;
				return TimeFormater.format2(curMsg.getCreate_time());
			}
        });
        msgTable.addColumn(column);
        
        // Use setDecorator to show Chinese characters and special symbos like < and >
        column = new Column("status", "Status");
        column.setWidth("5%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Message curMsg = (Message) object;
				Image isReadImg = null;
				if(curMsg.getIsRead() == false) {
					isReadImg = new Image();
					isReadImg.setSrc("./images/new.gif");
				}
				return (isReadImg == null)?"":isReadImg.toString(); 
			}
        });
        msgTable.addColumn(column);
        
        column = new Column("title","Title");
        column.setWidth("25%");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Message curMsg = (Message) object;
				// Replace "\n" to "<br />" to make new lines in HTML
				return curMsg.getTitle().replace("\n","<br />"); 
			}
        });
        msgTable.addColumn(column);

        // Add "Edit" and "Delete" link
        column = new Column("action", "Process");
        column.setWidth("15%");
        ActionLink[] links = new ActionLink[]{detailLink};
        column.setDecorator(new LinkDecorator(msgTable, links, "id"));
        msgTable.addColumn(column);
        curStaff = (Staff)getContext().getSession().getAttribute("staff");
        if(curStaff==null)
        {
        	setRedirect(Index.class);
        } else
        {
        	// Get menu items from database as data provider
            msgTable.setDataProvider(new PagingDataProvider<Message>() {
    			private static final long serialVersionUID = 566531542989001730L;
                
    			// Menu item data List
    			public List<Message> getData() { 
                	int page = msgTable.getPageNumber();
                    int size = msgTable.getPageSize(); 
                    List<Message> pagedList = getHumanResourceService().getPagedMsgList(curStaff, page, size);
                    return pagedList;
                }

    			// Menu item data size
    			@Override
    			public int size() {
    				return getHumanResourceService().getMsgCount(curStaff);
    			} 
            });
        }
        

	}

	/**
	 * Get target WebMenu object and copy its values into form
	 * @return
	 */
	public boolean onDetailClick() {
        Integer id = detailLink.getValueInteger();
        Message targetsg = getHumanResourceService().getMsgById(id);
        targetsg.setIsRead(true);
        getHumanResourceService().updateMsg(targetsg);
        if (targetsg != null) {
        	title = targetsg.getTitle();
        	date = TimeFormater.format2(targetsg.getCreate_time());
        	content = targetsg.getMessage().replace("\n","<br />");
        	if(targetsg.getAtt_url().compareTo("") != 0)
        		attachment = "files.htm?filename="+targetsg.getAtt_url();
        }
        return true;
    }
	
}
