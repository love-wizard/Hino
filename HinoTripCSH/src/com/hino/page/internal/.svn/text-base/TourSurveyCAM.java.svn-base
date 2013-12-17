package com.hino.page.internal;

import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;
import org.apache.commons.fileupload.FileItem;

import com.hino.click.extension.Image;
import com.hino.dto.SiteCAMDto;
import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.Site;
import com.hino.model.TourSurvey;
import com.hino.util.Info;
import com.hino.util.TimeFormater;

public class TourSurveyCAM extends BasePage {
	private static final String DEFAULT_ID_VALUE = "系统自动生成";

	@Bindable
	private Integer id;

	@Bindable
	private Form form = new Form("ts_cam_form");

	private TextField title = new TextField("ts_title", "名称", 50, true);
	private TextField est_price = new TextField("price", "预计价格（可填范围，文字形式）", 50, true);
	private TextArea desc = new TextArea("desc", "描述", 50, 10, true);
	private FileField fullimgfile = new FileField("siteimg", "图片", true);
	private DateField est_date = new DateField("est_date","预计出发日期", true);

	public Table table = new Table("ts_table");
	public ActionLink setdone = new ActionLink("setdone", "完成");
	public ActionLink seton = new ActionLink("seton", "发布");
	public ActionLink deleteLink = new ActionLink("delete", "删除");

	public void onInit() {
		form.add(title);
		form.add(est_price);
		form.add(desc);
		form.add(fullimgfile);
		form.add(est_date);
	
		form.add(new Submit("ok", "保存", this, "onOkClick"));
		
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");
		
		setdone.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                //setRedirect(TourSurveyCAM.class);
            	Integer id = setdone.getValueInteger();
            	getSalesService().change_status_tour_survey(id, 2);
                return true;
            }
        });
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                //setRedirect(TourSurveyCAM.class);
            	Integer id = deleteLink.getValueInteger();
            	getSalesService().delete_tour_survey(id);
                return true;
            }
        });
		
		seton.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
                //setRedirect(TourSurveyCAM.class);
            	Integer id = seton.getValueInteger();
            	getSalesService().change_status_tour_survey(id, 1);
                return true;
            }
        });
		
		
		table.setClass(Table.CLASS_SIMPLE); 
		
        table.setSortable(true); 
        
        table.addColumn(new Column("id", "TS ID")); 
        table.addColumn(new Column("title", "TS Title")); 
        table.addColumn(new Column("status", "状态0未发布1发布中2结束")); 
        table.addColumn(new Column("est_price", "预计价格"));
        
        
        
        Column column = new Column("est_date", "预计时间"); 
        column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				
				return TimeFormater.format2(((TourSurvey)object).getEst_date());
			}
		});

        
        table.addColumn(column);
        table.addColumn(new Column("posi", "喜欢人数"));
        table.addColumn(new Column("negi", "不喜欢人数"));
        
        column = new Column("action", "操作"); 
        ActionLink[] links = {seton, setdone, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false); 
        table.addColumn(column); 
        
        // Set data provider to populate the table row list from 
        table.setDataProvider(new DataProvider<TourSurvey>() {
        	
            public List<TourSurvey> getData() { 
                List<TourSurvey> l = getSalesService().list_survey_stats(-1);

                return l;
            }
        }); 

	}

	public boolean onOkClick() {

		if (form.isValid()) {
			TourSurvey ts= new TourSurvey();
			// siteid.getValue()
			Calendar c = Calendar.getInstance();
			c.setTime(est_date.getDate());
			ts.setTitle(title.getValue());
			ts.setEst_date(c);
			ts.setDescription(desc.getValue());
			ts.setEst_price(est_price.getValue());
			//ts.setStatus(0);
			FileItem fi = null;
			if (!fullimgfile.getFileItem().getName().trim().equals("")) {
				fi = fullimgfile.getFileItem();
			}
			
			getSalesService().create_tour_survey(ts, fi);
				
			
			// fullimgfile.getFileItem().getClass().getName();

			form.clearValues();
			getHeadElements().add(new JsScript("alert('更新成功')"));
			getHeadElements().add(
					new JsScript("window.location = './tour_survey_manage.htm'"));

			// setRedirect(SiteCAM.class);

		}
		return true;
	}

	

}
