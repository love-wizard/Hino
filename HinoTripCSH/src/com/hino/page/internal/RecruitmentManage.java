package com.hino.page.internal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;

import com.hino.model.Airline;
import com.hino.model.AirlineCompany;
import com.hino.model.Airport;
import com.hino.model.Group;
import com.hino.model.Recruitment;
import com.hino.model.Site;
import com.hino.util.TimeFormater;

public class RecruitmentManage extends BasePage {
	public String title = "招聘信息管理";
	public Table table = new Table("recruitmentTable");
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改");
	public ActionLink addRecruitmentLink = new ActionLink("addRecruitment", "添加新招聘信息");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	@SuppressWarnings("serial")
	public void onInit()
	{
		addRecruitmentLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(RecruitmentCAM.class);
                return true;
            }
        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer id = editLink.getValueInteger(); 
                setRedirect("./recruitment_cam.htm?id=" + id);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getHumanResourceService().deleteRecruitmentById(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");

		table.setClass(Table.CLASS_SIMPLE); 		
        table.setPageSize(10);
        table.setShowBanner(true); 
        
        table.addColumn(new Column("id", "编号")); 
        table.addColumn(new Column("position", "岗位名称"));
        
        Column column = new Column("expiry_date", "截止日期");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
				return df.format(((Recruitment) object).getExpiryDate().getTime());
			}
		});
        table.addColumn(column); 

        column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<Recruitment>() {
        	
            public List<Recruitment> getData() { 
                List<Recruitment> rl = getHumanResourceService().getRecruitmentList();
                return rl;
            }

			@Override
			public int size() {
				return getHumanResourceService().getRecruitmentCount();
			} 
        });         
	}
}
