package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.LinkDecorator;

import com.hino.model.AirlineQA;
import com.hino.model.Site;

public class AirlineQAManage extends BasePage {
	public String title = "airline问题管理";
	public Table table = new Table("questionTable");
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改");
	public ActionLink addSiteLink = new ActionLink("addquestion", "添加新问题");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	public void onInit()
	{
		addSiteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(AirlineQACAM.class);
                return true;
            }
        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer id = editLink.getValueInteger(); 
                setRedirect("./airline_qa_cam.htm?id=" + id);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getWebService().delete_airlineQA(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");

		//table = new Table("siteTable");
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setPageSize(10);
        
        table.setShowBanner(true); 
        //table.setSortable(true); 
        
        //table.addColumn(new Column("id", "id")); 
        Column colArea = new Column("area", "板块");
        colArea.setWidth("80");
        table.addColumn(colArea); 
        Column colQuestion = new Column("question", "问题");
        colQuestion.setWidth("200");
        table.addColumn(colQuestion); 
        Column colAnswer = new Column("answer", "答案");
        colAnswer.setWidth("600");
        table.addColumn(colAnswer); 
        
        table.setWidth("1100");

        Column column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<AirlineQA>() {
        	
            public List<AirlineQA> getData() { 
            	int p = table.getPageNumber();
            	//table.get
                int count = table.getPageSize(); 
                List<AirlineQA> l = getWebService().getPagedAirlineQAList(p, count);
                return l;
            }

			@Override
			public int size() {
				return getWebService().getAirlineQACount();
			} 
        }); 
        
        
	}

}
