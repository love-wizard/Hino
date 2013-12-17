package com.hino.page.internal;

import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.AirlineCompanyDto;
import com.hino.dto.AirlineQACAMDto;
import com.hino.dto.AirportDto;
import com.hino.model.AirlineCompany;
import com.hino.model.AirlineQA;
import com.hino.model.Airport;
import com.hino.model.Site;

public class AirlineCompanyManage extends BasePage {
	private static final String DEFAULT_ID_VALUE = "系统自动生成";
	public String title = "航空公司管理";
	@Bindable
	private Integer id;
	@Bindable
	private Form form = new Form("airline_company_cam_form");
	public Table table = new Table("airlineCompanyTable");
	private HiddenField airlinecompanyid = new HiddenField("airlinecompanyid", "airlinecompanyid");
	private TextField airlineCompanyFullName= new TextField("airlineCompanyFullName", "航空公司全称", 50, true);
	private TextField airlineCompanyDisplayName = new TextField("airlineCompanyDisplayName", "航空公司显示名字", 10, true);
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改");
	public ActionLink addSiteLink = new ActionLink("addairport", "添加新航空公司航空公司");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	public void onInit()
	{
		airlinecompanyid.setDisabled(true);
		addSiteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(AirlineCompanyManage.class);
                return true;
            }
        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer id = editLink.getValueInteger(); 
                setRedirect("./airline_company_manage.htm?id=" + id);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getAirlineService().deleteAirlineCompany(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
		
		form.add(airlinecompanyid);
		form.add(airlineCompanyFullName);
		form.add(airlineCompanyDisplayName);
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));
		
		//table = new Table("siteTable");
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setPageSize(10);
        
        table.setShowBanner(true); 
        //table.setSortable(true); 
        
        table.addColumn(new Column("company_full_name", "航空公司全名")); 
        table.addColumn(new Column("company_display_name", "显示名字"));      
         
        Column column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<AirlineCompany>() {
        	
            public List<AirlineCompany> getData() { 
//            	int p = table.getPageNumber();
//            	//table.get
//                int count = table.getPageSize(); 
                List<AirlineCompany> l = getAirlineService().getAirlineCompanyList();
                //System.out.println("List site:" +l);
                //System.out.println("List site size:" +l.size());
                return l;
            }

			@Override
			public int size() {
				return getAirlineService().getAirlineCompanyCount();
			} 
        }); 
        
        if (id == null) {
        	airlinecompanyid.setValue(DEFAULT_ID_VALUE);
		} else {
			AirlineCompany a = getAirlineService().getAirlineCompany(id);
			
			if(a!=null)
			{
				airlinecompanyid.setValue("" + a.getId());
				airlineCompanyFullName.setValue(a.getCompany_full_name());
				airlineCompanyDisplayName.setValue(a.getCompany_display_name());
			} else
			{
				getHeadElements().add(new JsScript("alert('不存在')"));
				getHeadElements().add(
						new JsScript("window.location = './airline_company_manage.htm'"));
			}
		}
        
	}

	public boolean onOkClick() {

		if (form.isValid()) {
			AirlineCompanyDto apdto = new AirlineCompanyDto();
			// siteid.getValue()
			apdto.setCompany_full_name(airlineCompanyFullName.getValue());
			apdto.setCompany_display_name(airlineCompanyDisplayName.getValue());
			
			try {
				if (!airlinecompanyid.getValue().equals(DEFAULT_ID_VALUE)) {
					apdto.setId(Integer.parseInt(airlinecompanyid.getValue()));
					getAirlineService().updateAirlineCompany(apdto);
				} else{
					getAirlineService().addAirlineCompany(apdto);
				}
			} catch (NumberFormatException nfe) {
				//
			} finally {
				//
			}

			// fullimgfile.getFileItem().getClass().getName();

			form.clearValues();
			getHeadElements().add(new JsScript("alert('更新成功')"));
			getHeadElements().add(
					new JsScript("window.location = './airline_company_manage.htm'"));

			// setRedirect(SiteCAM.class);

		}
		return true;
	}
	
	public boolean onCancelClick() {
		setRedirect(AirlineCompanyManage.class);
		return true;
	}
}
