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

import com.hino.dto.AirlineQACAMDto;
import com.hino.dto.AirportDto;
import com.hino.model.AirlineQA;
import com.hino.model.Airport;
import com.hino.model.Site;

public class AirportManage extends BasePage {
	private static final String DEFAULT_ID_VALUE = "系统自动生成";
	public String title = "机场管理";
	@Bindable
	private Integer id;
	@Bindable
	private Form form = new Form("airline_cam_form");
	public Table table = new Table("airportTable");
	private HiddenField airportid = new HiddenField("airportid", "airportid");
	private TextField airportName= new TextField("AirPortName", "机场", 50, true);
	private TextField airportCity = new TextField("AirPortCity", "城市", 50, true);
	public ActionLink editLink = new ActionLink("edit", "查看详细并修改");
	public ActionLink addSiteLink = new ActionLink("addairport", "添加新机场");
	public ActionLink deleteLink = new ActionLink("delete", "删除");
	
	public void onInit()
	{
		airportid.setDisabled(true);
		addSiteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                setRedirect(SiteCAM.class);
                return true;
            }
        });
		
		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			public boolean onAction(Control source) {
            	Integer id = editLink.getValueInteger(); 
                setRedirect("./airport_manage.htm?id=" + id);
                return true;
            }
		});
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;

            public boolean onAction(Control source) {
            	Integer id = deleteLink.getValueInteger();
            	getAirlineService().deleteAirport(id);
                return true;
            }
        });
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
		
		form.add(airportid);
		form.add(airportCity);
		form.add(airportName);
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		form.add(new Submit("cancel", "取消", this, "onCancelClick"));
		
		//table = new Table("siteTable");
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setPageSize(10);
        
        table.setShowBanner(true); 
        //table.setSortable(true); 
        
        table.addColumn(new Column("airport_city", "城市")); 
        table.addColumn(new Column("airport_name", "机场"));      
         
        Column column = new Column("action", "操作"); 
        ActionLink[] links = {editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new PagingDataProvider<Airport>() {
        	
            public List<Airport> getData() { 
//            	int p = table.getPageNumber();
//            	//table.get
//                int count = table.getPageSize(); 
                List<Airport> l = getAirlineService().getAirportList();
                //System.out.println("List site:" +l);
                //System.out.println("List site size:" +l.size());
                return l;
            }

			@Override
			public int size() {
				return getAirlineService().getAirportCount();
			} 
        }); 
        
        if (id == null) {
			airportid.setValue(DEFAULT_ID_VALUE);
		} else {
			Airport a = getAirlineService().getAirport(id);
			
			if(a!=null)
			{
				airportid.setValue("" + a.getId());
				airportCity.setValue(a.getAirport_city());
				airportName.setValue(a.getAirport_name());
			} else
			{
				getHeadElements().add(new JsScript("alert('不存在')"));
				getHeadElements().add(
						new JsScript("window.location = './site_manage.htm'"));
			}
		}
        
	}

	public boolean onOkClick() {

		if (form.isValid()) {
			AirportDto apdto = new AirportDto();
			// siteid.getValue()
			apdto.setAirport_city(airportCity.getValue());
			apdto.setAirport_name(airportName.getValue());
			
			try {
				if (!airportid.getValue().equals(DEFAULT_ID_VALUE)) {
					apdto.setId(Integer.parseInt(airportid.getValue()));
					getAirlineService().updateAirport(apdto);
				} else{
					getAirlineService().addAirport(apdto);
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
					new JsScript("window.location = './airport_manage.htm'"));

			// setRedirect(SiteCAM.class);

		}
		return true;
	}
	
	public boolean onCancelClick() {
		setRedirect(AirlineCompanyManage.class);
		return true;
	}
}
