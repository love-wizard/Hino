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

public class IndexDefaultSearchKeywordsSetting extends BasePage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Bindable private Form defaultSearchKeywordsForm = new Form("defaultSearchKeywordsForm");
	private TextArea keyWords = new TextArea("keyWords", "搜索框默认的搜索文字", 50, 10, false);
	private Submit searchKeywordsSubmit = new Submit("searchKeywordsSubmit", "保存");
	public void onInit()
	{
		List<com.hino.model.Info> searchKeywords;
		searchKeywords = getWebService().getDefalutSearchKeywords();
		
		if (searchKeywords.size() > 0) {
			keyWords.setValue(searchKeywords.get(0).getLinkUrl());
		}
		
		defaultSearchKeywordsForm.add(keyWords);
		defaultSearchKeywordsForm.add(searchKeywordsSubmit);
		searchKeywordsSubmit.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
            	if(defaultSearchKeywordsForm.isValid())
            	{
            		com.hino.model.Info searchKeywords = new com.hino.model.Info();
            		searchKeywords.setLinkUrl(keyWords.getValue());
            		searchKeywords.setSource(Info.INFO_DATA_SOURCE_SEARCH_KEYWORDS);
            		getWebService().SetupDefalutSearchKeywords(searchKeywords);
            		getHeadElements().add(new JsScript("alert('保存搜索框默认的搜索文字操作成功!')"));
            	}
            	
                return true;
            }
        });
		
	}
}
