package com.hino.page.internal;

import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.IndexImageSettingDto;
import com.hino.model.Group;
import com.hino.model.NoticeBoard;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class IndexImageSetting extends BasePage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Bindable private Form form = new Form("imageform");
	@Bindable private Form idxNaviForm = new Form("idxnaviform");
	private TextArea famousPlaces = new TextArea("famousplaces", "著名景点", 50, 10, false);
	private Submit idxNaviSubmit = new Submit("idxnavisumbit", "保存导航栏数据");
	private FileField image1 = new FileField("image1", "中文版图片1", false);
	private FileField image2 = new FileField("image2", "中文版图片2", false);
	private FileField image3 = new FileField("image3", "中文版图片3", false);
	private FileField image4 = new FileField("image4", "中文版图片4", false);
	private FileField image5 = new FileField("image5", "中文版图片5", false);
	private FileField image6 = new FileField("image6", "中文版图片6", false);
	
	private FileField image1_en = new FileField("image1_en", "英文版图片1", false);
	private FileField image2_en = new FileField("image2_en", "英文版图片2", false);
	private FileField image3_en = new FileField("image3_en", "英文版图片3", false);
	private FileField image4_en = new FileField("image4_en", "英文版图片4", false);
	private FileField image5_en = new FileField("image5_en", "英文版图片5", false);
	
	private TextField link_img1 = new TextField("link1","Link1",50,false);
	private TextField link_img2 = new TextField("link2","Link2",50,false);
	private TextField link_img3 = new TextField("link3","Link3",50,false);
	private TextField link_img4 = new TextField("link4","Link4",50,false);
	private TextField link_img5 = new TextField("link5","Link5",50,false);
	private TextField link_img6 = new TextField("link6","Link6",50,false);
	
	private TextField link_img1_en = new TextField("link1_en","Link1_en",50,false);
	private TextField link_img2_en = new TextField("link2_en","Link2_en",50,false);
	private TextField link_img3_en = new TextField("link3_en","Link3_en",50,false);
	private TextField link_img4_en = new TextField("link4_en","Link4_en",50,false);
	private TextField link_img5_en = new TextField("link5_en","Link5_en",50,false);
	
	private FileField imgVip = new FileField("imgVip", "VIP图片", false);
	private TextField link_imgVip = new TextField("link_imgVip","link_imgVip",50,false);
	
	private DateField index_from = new DateField("index_from",  "公告起始", 20, false);
	private DateField index_to = new DateField("index_to",  "公告截止", 20, false);
	private TextArea index_text = new TextArea("index_text", "公告文字", 50, 10, false);
	private TextField index_link = new TextField("index_link","公告链接",50,false);
	
	public Table table = new Table("NoticeTable");
	public ActionLink cancelLink = new ActionLink("cancel", "停止公告");
	public ActionLink deleteLink = new ActionLink("delete", "删除公告");
	
	private Submit submit = new Submit("sumbit", "更新图片及链接");
	private Submit update_text1 = new Submit("update1", "增加公告");
	public void onInit()
	{
		List<com.hino.model.Info> famousPlace;
		famousPlace = getWebService().getIndexNavigationFamousPlace();
		
		if (famousPlace.size() > 0) {
			famousPlaces.setValue(famousPlace.get(0).getLinkUrl());
		}
		
		idxNaviForm.add(famousPlaces);
		idxNaviForm.add(idxNaviSubmit);
		idxNaviSubmit.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	if(idxNaviForm.isValid())
            	{
            		com.hino.model.Info inFamousPlaces = new com.hino.model.Info();
            		inFamousPlaces.setLinkUrl(famousPlaces.getValue());
            		inFamousPlaces.setSource("FAMOUS_PLACE");
            		getWebService().indexNavigationSetting(inFamousPlaces);
            		getHeadElements().add(new JsScript("alert('首页导航栏数据录入操作成功!')"));
            	}
            	
                return true;
            }
        });
		
		cancelLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	ServiceResponse sr = getNoticeService().CancelNotice(cancelLink.getValueInteger());
            	if(sr.isSucc())
            	{
            		getHeadElements().add(new JsScript("alert('操作执行成功')"));
            	} else
            	{
            		getHeadElements().add(new JsScript("alert(\"" +EscapeHtml.nrTonnrr(sr.list_msg())+ "\")"));
            	}
                return true;
            }
        });
		
		deleteLink.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	ServiceResponse sr = getNoticeService().DeleteNotice((deleteLink.getValueInteger()));
            	if(sr.isSucc())
            	{
            		getHeadElements().add(new JsScript("alert('操作执行成功')"));
            	} else
            	{
            		getHeadElements().add(new JsScript("alert(\"" +EscapeHtml.nrTonnrr(sr.list_msg())+ "\")"));
            	}
                return true;
            }
        });
		
		form.add(image1);
		form.add(link_img1);
		form.add(image2);
		form.add(link_img2);
		form.add(image3);
		form.add(link_img3);
		form.add(image4);
		form.add(link_img4);
		form.add(image5);
		form.add(link_img5);
		form.add(image6);
		form.add(link_img6);
		
		form.add(image1_en);
		form.add(link_img1_en);
		form.add(image2_en);
		form.add(link_img2_en);
		form.add(image3_en);
		form.add(link_img3_en);
		form.add(image4_en);
		form.add(link_img4_en);
		form.add(image5_en);
		form.add(link_img5_en);

		
		form.add(imgVip);
		form.add(link_imgVip);
		
		form.add(submit);
		form.add(index_from);
		form.add(index_to);
		form.add(index_text);
		form.add(index_link);
		form.add(update_text1);
		
		if(getNoticeService().GetCount()==0){
			index_text.setValue(Info.indexText);
		}else{
			index_text.setValue(getNoticeService().GetLastNoticeBoard().getContent());
		}
		
		link_img1.setValue(Info.imgLink1);
		link_img2.setValue(Info.imgLink2);
		link_img3.setValue(Info.imgLink3);
		link_img4.setValue(Info.imgLink4);
		link_img5.setValue(Info.imgLink5);
		link_img6.setValue(Info.imgLink6);

		List<com.hino.model.Info> info;
		info = this.getWebService().getIndexInfo();
		if(null!=info)
		{
			link_img1.setValue( info.get(0).getLinkUrl());
			link_img2.setValue( info.get(1).getLinkUrl());
			link_img3.setValue( info.get(2).getLinkUrl());
			link_img4.setValue( info.get(3).getLinkUrl());
			link_img5.setValue( info.get(4).getLinkUrl());
			link_img6.setValue( info.get(6).getLinkUrl());
			if(info.size()>5)
			{
				link_imgVip.setValue(info.get(5).getLinkUrl());
			}
		}
	
					
		link_img1_en.setValue(Info.imgLink11);
		link_img2_en.setValue(Info.imgLink22);
		link_img3_en.setValue(Info.imgLink33);
		link_img4_en.setValue(Info.imgLink44);
		link_img5_en.setValue(Info.imgLink55);
		
		table.setClass(Table.CLASS_SIMPLE);
		table.setPageSize(25);
	
		table.setShowBanner(true); //table.setSortable(true);
		Column column = new Column("id", "公告ID");
		column.setSortable(true);
		table.addColumn(column); 
		
		column = new Column("content", "公告内容");
		column.setSortable(true);
		column.setWidth("600");
		table.addColumn(column);
		
		column = new Column("startDate", "公告起始");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar startDate = ((NoticeBoard) object).getStartDate();
				if (startDate != null)
					return TimeFormater.format2(startDate);
				else
					return "未指定";
			}
		});
		column.setSortable(true);
		table.addColumn(column);
		
		
		column = new Column("endDate", "公告截止");
		column.setDecorator(new Decorator(){
			@Override
			public String render(Object object, Context context) {
				Calendar endDate = ((NoticeBoard) object).getEndDate();
				if (endDate != null)
					return TimeFormater.format2(endDate);
				else
					return "未指定";
			}
		});
		column.setSortable(true);
		table.addColumn(column);
		
		column = new Column("status", "公告状态");
		column.setSortable(true);
		table.addColumn(column);
		
		column = new Column("action", "操作");
		
		ActionLink[] links = {cancelLink, deleteLink}; 
		column.setDecorator(new LinkDecorator(table, links, "id")); 
		column.setSortable(false); 
		table.addColumn(column);
		
		form.restoreState(getContext());
		submit.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	if(form.isValid())
            	{
            		IndexImageSettingDto iisdto = new IndexImageSettingDto();
            		
            		iisdto.setImage1(image1.getFileItem());
            		iisdto.setImage2(image2.getFileItem());
            		iisdto.setImage3(image3.getFileItem());
            		iisdto.setImage4(image4.getFileItem());
            		iisdto.setImage5(image5.getFileItem());
            		iisdto.setImage6(image6.getFileItem());
            		iisdto.setImage1_en(image1_en.getFileItem());
            		iisdto.setImage2_en(image2_en.getFileItem());
            		iisdto.setImage3_en(image3_en.getFileItem());
            		iisdto.setImage4_en(image4_en.getFileItem());
            		iisdto.setImage5_en(image5_en.getFileItem());
            		
            		iisdto.setLink1(link_img1.getValue());
            		iisdto.setLink2(link_img2.getValue());
            		iisdto.setLink3(link_img3.getValue());
            		iisdto.setLink4(link_img4.getValue());
            		iisdto.setLink5(link_img5.getValue());
            		iisdto.setLink6(link_img6.getValue());
            		iisdto.setLink1_en(link_img1_en.getValue());
            		iisdto.setLink2_en(link_img2_en.getValue());
            		iisdto.setLink3_en(link_img3_en.getValue());
            		iisdto.setLink4_en(link_img4_en.getValue());
            		iisdto.setLink5_en(link_img5_en.getValue());
            		
            		iisdto.setImgVip(imgVip.getFileItem());
            		iisdto.setLinkVip(link_imgVip.getValue());

            		getWebService().indexImageSetting(iisdto);
            		getHeadElements().add(new JsScript("alert('操作执行成功!')"));
            	}
            	
                return true;
            }
        });
		
		update_text1.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            		Calendar startDate = Calendar.getInstance();
            		Calendar endDate = Calendar.getInstance();
            		startDate.setTime(index_from.getDate());
            		endDate.setTime(index_to.getDate());
            		if(startDate.before(endDate)){
	            		if(getNoticeService().CheckNoticeBoard(startDate, endDate)){
	            		getNoticeService().SaveNoticeBoard(index_text.getValue(),startDate,endDate,index_link.getValue());
	            		//Info.indexText = index_text.getValue();
	            		getHeadElements().add(new JsScript("alert('操作执行成功!')"));
	            		}else{
	            			getHeadElements().add(new JsScript("alert('同一日期以内不能发布多条不同的公告!')"));
	            		}
            		}else{
            		getHeadElements().add(new JsScript("alert('公告截止时间不能小于起始时间！')"));
	            	}
            	
                return true;
            }
        });
	}
	public void onRender() {
		// Set data provider to populate the table row list from
		table.setDataProvider(new PagingDataProvider<NoticeBoard>() {
			public List<NoticeBoard> getData() {
/*				int p = table.getPageNumber();
				int count = table.getPageSize();*/
				String sortColumn = table.getSortedColumn();
				
				boolean ascending = table.isSortedAscending();
				if (sortColumn ==null)
				{
					sortColumn = "id";
				}

				return getNoticeService().GetAllNoticeBoards();
							
			}

			@Override
			public int size() {
				return 0;
			}

		});
	}
}
