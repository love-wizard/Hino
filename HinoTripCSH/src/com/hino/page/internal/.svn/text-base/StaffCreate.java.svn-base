package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.Reset;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.CheckList;
import org.apache.click.util.Bindable;

import com.hino.dto.StaffCreateDto;
import com.hino.model.AreaBranch;
import com.hino.model.Staff;
import com.hino.util.Info;
import com.hino.util.PriviledgeParser;
import com.hino.util.ServiceResponse;

public class StaffCreate extends BasePage {
	private CheckList priviledge = new CheckList("privilege", "权限选择");
	private TextField staffno = new TextField("staffno", "员工工号", true);
	private TextField staffemail = new TextField("staffemail", "员工邮箱", true);

	@Bindable
	private Form form = new Form("newStaffForm");
	private Select area = new Select("area", "销售代表区域选择");

	private Submit submit = new Submit("submit", "创建");
	private Reset reset = new Reset("reset", "重置");

	public StaffCreate() {

		staffno.setTrim(true);
		staffemail.setTrim(true);

		form.add(staffno);
		form.add(staffemail);
		form.add(priviledge);
		form.add(area);
		form.add(submit);
		form.add(reset);
	}

	@SuppressWarnings("unchecked")
	public void onInit() {
		submit.setActionListener(new ActionListener() {

			@Override
			public boolean onAction(Control source) {
				if (form.isValid()) {

					Integer areaid = null;
					try {
						areaid = Integer.parseInt(area.getValue());
					} catch (NumberFormatException nfe) {
						// TODO
					} finally {
						// TODO
					}

					List<Integer> pil = new ArrayList<Integer>();
					List<String> str = priviledge.getSelectedValues();

					try {
						for (int i = 0; i < str.size(); i++) {
							pil.add(Integer.parseInt(str.get(i)));
						}
					} catch (NumberFormatException nfe) {
						// TODO
					} finally {
						// TODO
					}

					String privstr = PriviledgeParser.make_pri_string(pil);
					StaffCreateDto scdto = new StaffCreateDto();

					scdto.setStaffno(staffno.getValue());
					scdto.setStaffemail(staffemail.getValue());
					scdto.setPriviledge(privstr);
					scdto.setAreaselect(areaid);

					// System.out.println(scdto);
					ServiceResponse sr = getHumanResourceService().create_new_staff(scdto);

					if (sr.isSucc()) {
						// Send staff number and initial password to the new staff
						getHumanResourceService().send_staff_passwd_email((Staff)sr.getResponse());
						form.clearValues();
						getHeadElements().add(new JsScript("alert('创建成功')"));
						getHeadElements()
								.add(
										new JsScript(
												"window.location = './staff_create.htm'"));
					} else {
						getHeadElements().add(new JsScript("alert('创建失败')"));
						Info.printInfo(sr.getInfo_code());
					}

				} else {
					// TODO return error auto
				}
				return true;
			}

		});

		priviledge.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				optionList.add(new Option("0", "销售代表"));
				optionList.add(new Option("1", "地区代表"));
				optionList.add(new Option("2", "财务管理"));
				optionList.add(new Option("3", "市场管理"));
				optionList.add(new Option("4", "人力资源管理"));
				optionList.add(new Option("5", "导游"));
				optionList.add(new Option("6", "销售管理"));
				optionList.add(new Option("7", "导游管理"));
				optionList.add(new Option("8", "旅游资料管理"));
				optionList.add(new Option("9", "网站管理"));
				return optionList;
			}
		});

		area.setDataProvider(new DataProvider() {
			public List getData() {
				List optionList = new ArrayList();
				List<AreaBranch> abl = getHumanResourceService()
						.list_all_area();
				optionList.add(new Option(-1, ""));
				for (int i = 0; i < abl.size(); i++) {
					optionList.add(new Option(abl.get(i).getId(), abl.get(i)
							.getName()));
				}

				return optionList;
			}
		});

	}

}
