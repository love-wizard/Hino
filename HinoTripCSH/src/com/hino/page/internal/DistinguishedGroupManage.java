package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;
import com.hino.model.DistinguishedGroup;

public class DistinguishedGroupManage extends BasePage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String caption = "尊享团管理";
	public Table table = new Table("distinguishedGroupTable");
	public ActionLink addDistinguishedGroupLink = new ActionLink("addDistinguishedGroup", "添加新尊享团");	
	public ActionLink editLink = new ActionLink("edit", "查看/修改");
	//public ActionLink manageOptionLink = new ActionLink("manageOptionLink", "管理尊享团项目");
	//public ActionLink addOptionLink = new ActionLink("addOption", "添加新尊享团项目");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	public void onInit()
	{
		addDistinguishedGroupLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(DistinguishedGroupCAM.class);
                return true;
            }
        });
		
//		addOptionLink.setActionListener(new ActionListener() {
//            private static final long serialVersionUID = 1L;
//            public boolean onAction(Control source) {
//            	
//                setRedirect("./distinguished_group_cam.htm");
//                return true;
//            }
//        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer sid = editLink.getValueInteger(); 
                setRedirect("./distinguished_group_cam.htm?id=" + sid);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	final DistinguishedGroup distinguishedGroup = getTravelResourceService().getDistinguishedGroupById(id);
            	if (null != distinguishedGroup) {
            		getTravelResourceService().deleteDistinguishedGroup(id);
            	}
            	setRedirect("./distinguished_group_manage.htm");
            	return true;
            }
        });
		
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");

		table.setClass(Table.CLASS_SIMPLE); 
        table.setPageSize(10);
        table.setShowBanner(true); 

        table.addColumn(new Column("id", "尊享团编号")); 
        table.addColumn(new Column("title", "尊享团名称"));

        Column column = new Column("action", "操作"); 
        //ActionLink[] links = {editLink, deleteLink, manageOptionLink, addOptionLink};
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column);
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<DistinguishedGroup>() {
        	
            public List<DistinguishedGroup> getData() { 
            	int p = table.getPageNumber();
                int count = table.getPageSize(); 
                List<DistinguishedGroup> l = getTravelResourceService().getPagedDistinguishedGroupList(p, count);
                return l;
            }

			@Override
			public int size() {
				return getTravelResourceService().getDistinguishedGroupCount();
			} 
        });     
	}

}
