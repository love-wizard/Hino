package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.CheckList;
import org.apache.click.util.Bindable;

import com.hino.dto.MsgEmailCAMDto;
import com.hino.model.Staff;
import com.hino.util.Info;

/**
 * Create and send system message or email to staff
 */
public class MsgEmailCAM extends BasePage {
	private static final long serialVersionUID = 3105898696075578617L;
	
	@Bindable private Form msgEmailForm = new Form("msgemailform");
	@Bindable private Form staffFilterForm = new Form("stafffilterform");

	@Override
	public void onInit() {

		// ----- Display staff filter form -----
		TextField searchContent = new TextField("staffFilterStr", "员工筛选", 50, false);
		searchContent.setTrim(true);
		staffFilterForm.add(searchContent);
		CheckList priviledge = new CheckList("privilegeFilter", "权限选择");
		priviledge.setDataProvider(new DataProvider<Option>() {
			private static final long serialVersionUID = -1498048132337103457L;

			public List<Option> getData() {
				List<Option> optionList = new ArrayList<Option>();
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
		staffFilterForm.add(priviledge);
		staffFilterForm.add(new Submit("search", "筛选", this, "onSearchClick"));
		
		// ----- Display message email form -----
		CheckList staffChecklist = new CheckList("staffidlist", "选择员工");
		// List all staffs by default
		List<Option> staffOptions = new ArrayList<Option>();
		List<Staff> targetStaffList = getHumanResourceService().list_all_staff();
		for(Staff curStaff : targetStaffList) {
			staffOptions.add(new Option(curStaff.getId(), 
				curStaff.getStaffno()+" "+curStaff.getChinesename()+
				"("+curStaff.getLastname()+" "+curStaff.getFirstname()+")"));
		}
		staffChecklist.setOptionList(staffOptions);
		staffChecklist.setRequired(true);
		msgEmailForm.add(staffChecklist);
		
		CheckList typeChecklist = new CheckList("typelist", "发送方式");
		List<Option> typeOptions = new ArrayList<Option>();
		typeOptions.add(new Option(Info.TYPE_MSG, "系统消息"));
		typeOptions.add(new Option(Info.TYPE_EMAIL, "电子邮件"));
		typeChecklist.setOptionList(typeOptions);
		typeChecklist.setRequired(true);
		msgEmailForm.add(typeChecklist);
		
		TextField menuName = new TextField("title", "标题", 50, true);
		menuName.setTrim(true);
		msgEmailForm.add(menuName);
		TextArea menuDesc = new TextArea("content", "正文", 50, 10, true);
		menuDesc.setTrim(true);
		msgEmailForm.add(menuDesc);
		FileField attachment = new FileField("attachment", "附件");
		msgEmailForm.add(attachment);

		msgEmailForm.add(new Submit("send", "发送消息", this, "onSendClick"));
		msgEmailForm.add(new Submit("cancel", "取消发送", this, "onCancelClick"));

	}

    /**
     * Send message or email
     * @return
     */
    public boolean onSendClick() {
        if (msgEmailForm.isValid()) {
        	MsgEmailCAMDto msgemaildto = new MsgEmailCAMDto(); 
    		msgEmailForm.copyTo(msgemaildto);

    		// Set attachment FileItem
    		msgemaildto.setAttachment(((FileField)msgEmailForm.getField("attachment")).getFileItem());
    		
    		// Set sender
    		msgemaildto.setSender((Staff)getContext().getSession().getAttribute("staff"));

    		// Send message and email
    		getHumanResourceService().sendMsgEmail(msgemaildto);
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('发送成功')"));
			getHeadElements().add(
					new JsScript("window.location = './msg_email_cam.htm'"));
        }
        return true;
    }

    /**
     * Redirect to msg_email_cam.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(MsgEmailCAM.class);
        return true;
    }

    /**
     * Staff filter
     * @return
     */
    public boolean onSearchClick() {
    	String stringStaffFilter = staffFilterForm.getFieldValue("staffFilterStr");
    	List<String> priFilterStrList = ((CheckList)staffFilterForm.getField("privilegeFilter")).getSelectedValues();
    	List<Integer> priFilterIntList = new ArrayList<Integer>();
    	try {
			for (int i = 0; i < priFilterStrList.size(); i++) {
				priFilterIntList.add(Integer.parseInt(priFilterStrList.get(i)));
			}
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getStackTrace());
		}
    	
    	List<Option> staffOptions = new ArrayList<Option>();
		List<Staff> targetStaffList = null;
		
		// List filtered staff when staffFilterStr is posted
		if((stringStaffFilter != null && stringStaffFilter != "") || (priFilterIntList.size() > 0))
			targetStaffList = getHumanResourceService().list_filtered_staff(stringStaffFilter, priFilterIntList);
		else
			targetStaffList = getHumanResourceService().list_all_staff();
		
		for(Staff curStaff : targetStaffList) {
			staffOptions.add(new Option(curStaff.getId(), 
				curStaff.getStaffno()+" "+curStaff.getChinesename()+
				"("+curStaff.getLastname()+" "+curStaff.getFirstname()+")"));
		}
		((CheckList)msgEmailForm.getField("staffidlist")).setOptionList(staffOptions);
        return true;
    }

}
