package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.IntegerField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.AreaCAMDto;
import com.hino.dto.MenuCAMDto;
import com.hino.dto.MenuRouteCAMDto;
import com.hino.model.AreaBranch;
import com.hino.model.Route;
import com.hino.model.Staff;
import com.hino.model.WebMenu;
import com.hino.model.WebMenuRoute;
import com.hino.util.EscapeHtml;

public class AreaCAM extends BasePage {
	@Bindable
	private Form areaForm = new Form("areaform");
	@Bindable
	private Table areaTable = new Table("areatable");
	@Bindable
	private ActionLink editLink = new ActionLink("edit", "编辑");
	@Bindable
	private ActionLink deleteLink = new ActionLink("delete", "删除");

	private TextField areaName = new TextField("name", "区域名称", 50, true);
	private TextArea areaDesc = new TextArea("description", "区域描述", 50, 10,
			false);
	private Submit save = new Submit("save", "新建/更新");
	@Bindable
	private Integer sid;

	private HiddenField areaid = new HiddenField("areaid", "areaid");

	@SuppressWarnings("serial")
	public void onInit() {
		areaName.setTrim(true);
		areaDesc.setTrim(true);
		areaForm.add(areaName);
		areaForm.add(areaDesc);
		areaForm.add(areaid);

		// System.out.println(">>>>>>>>>>>input id" + sid);
		if (sid != null) {
			AreaBranch ab = getStrategyService().view_areabranch(sid);
			areaName.setValue(ab.getName());
			areaDesc.setValue(ab.getDescription());
			areaid.setValue(sid + "");
		} else {
			areaid.setValue(-1 + "");
		}

		save.setActionListener(new ActionListener() {
			@Override
			public boolean onAction(Control source) {
				if (areaForm.isValid()) {

					Integer aid = 0;
					try {
						aid = Integer.parseInt(areaid.getValue());
					} catch (NumberFormatException nfe) {
						// TODO
					} finally {
						// TODO
					}

					AreaCAMDto acam = new AreaCAMDto();
					acam.setName(areaName.getValue());
					acam.setDescription(areaDesc.getValue());

					if (aid != -1) {
						acam.setId(aid);
						getStrategyService().edit_areabranch(acam);
					} else {
						getStrategyService().create_areabranch(acam);
					}
					areaForm.clearValues();
					sid=null;
					areaid.setValue(-1+"");
				}
				return true;
			}

		});
		areaForm.add(save);

		areaTable.setClass(Table.CLASS_SIMPLE);
		areaTable.addColumn(new Column("id", "区域ID"));
		areaTable.addColumn(new Column("name", "区域名称"));

		Column column = new Column("leader", "地区代表");
		column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Staff s = ((AreaBranch) object).getLeader();
				if (s != null)
					return s.getChinesename()+"("+ s.getStaffno()+")";
				else
					return "未指定";
			}
		});
		areaTable.addColumn(column);

		column = new Column("follows_count", "地区销售代表数");
		column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				List<Staff> ls = ((AreaBranch) object).getFollows();
				return ls.size() + "";
			}
		});
		areaTable.addColumn(column);

		// Add "Edit" and "Delete" link
		column = new Column("action", "操作");
		ActionLink[] links = new ActionLink[] { editLink, deleteLink };
		column.setDecorator(new LinkDecorator(areaTable, links, "id"));
		areaTable.addColumn(column);

		editLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;

			public boolean onAction(Control source) {
				Integer id = editLink.getValueInteger();
				System.out.println("to" + "./area_cam.htm?sid=" + id);
				setRedirect("./area_cam.htm?sid=" + id);
				return true;
			}
		});

		deleteLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;

			public boolean onAction(Control source) {
				Integer id = deleteLink.getValueInteger();
				getStrategyService().delete_areabranch(id);
				return true;
			}
		});
		deleteLink.setAttribute("onclick", "return window.confirm('确定要删除吗?');");

		areaTable.setDataProvider(new DataProvider<AreaBranch>() {
			private static final long serialVersionUID = 2697343651571299765L;

			public List<AreaBranch> getData() {
				return getStrategyService().list_areabranch();
			}
		});
	}

}
