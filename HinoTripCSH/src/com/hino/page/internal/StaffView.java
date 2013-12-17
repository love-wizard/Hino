package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.LinkDecorator;
import com.hino.model.Staff;
import com.hino.util.PriviledgeParser;
import com.hino.util.TimeFormater;

public class StaffView extends BasePage{
	public Table table = new Table("staffTable");
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	
	public void onInit()
	{
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer id = editLink.getValueInteger(); 
                setRedirect("./staff_view_single.htm?id=" + id);//TODO change
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getHumanResourceService().delete_staff(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
		
		table.setClass(Table.CLASS_SIMPLE); 
		table.addColumn(new Column("id", "员工ID")); 
        table.addColumn(new Column("staffno", "工号")); 
        table.addColumn(new Column("chinesename", "中文名"));
        table.addColumn(new Column("area", "所属地区"));
        Column column = new Column("priviledge", "权限");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Staff s = (Staff) object;
                return PriviledgeParser.make_pri_string_inline(s.getPriviledge());
			}
        });
        table.addColumn(column);
        
        column = new Column("status", "状态");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Staff s = (Staff) object;
                return s.genStatusStr();
			}
        });
        table.addColumn(column);
        
        column = new Column("last_login", "上次登录");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Staff s = (Staff) object;
                return TimeFormater.format1(s.getLast_login_time());
			}
        });
        table.addColumn(column);
        
        column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new DataProvider<Staff>() {
        	
            public List<Staff> getData() { 
                List<Staff> l = getHumanResourceService().list_all_staff();
                return l;
            }

        }); 
	}
}
