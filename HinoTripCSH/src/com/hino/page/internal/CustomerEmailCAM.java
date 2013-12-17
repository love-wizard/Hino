package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;
import org.apache.commons.fileupload.FileItem;

import com.hino.dto.CustomerEmailCAMDto;
import com.hino.model.Staff;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

/**
 * Create and send email to selected customers
 */
public class CustomerEmailCAM extends BasePage {
	private static final long serialVersionUID = 3105898696075578617L;

	@Bindable private Form emailForm = new Form("emailform");
	@Bindable private Button generate_group_emails = new Button("generate_group_emails", "d");

	@Override
	public void onInit() {

		generate_group_emails.setId("generate_group_emails");
		generate_group_emails.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	int gid = Integer.parseInt(getContext().getRequest().getParameter("gid"));
            	//int votetp = Integer.parseInt(getContext().getRequest().getParameter("votetp"));
            	
				String result = getCustomerService().output_group_emails(gid);
				
				return new ActionResult(result+"", ActionResult.HTML);
            }
        });
		
		// ----- Display email form -----
		Select customerFilterlist = new Select("customerfilter", "用户过滤", true);
		customerFilterlist.setDefaultOption(new Option("请选择目标用户"));
        DataProvider<Option> filterDataPrvd = new DataProvider<Option>() {
			private static final long serialVersionUID = -3546837131164777556L;

			public List<Option> getData() {
				List<Option> Options = new ArrayList<Option>();
				Options.add(new Option(Info.CUSTOMER_GROUP, "指定团团员"));
				Options.add(new Option(Info.CUSTOMER_ALL, "所有用户"));
				Options.add(new Option(Info.CUSTOMER_LAST_MONTH, "最近一个月出团团员"));
				Options.add(new Option(Info.CUSTOMER_LAST_THREEE_MONTH, "最近三个月出团团员"));
				Options.add(new Option(Info.CUSTOMER_LAST_SIX_MONTH, "最近六个月出团团员"));
                return Options;
            }
        };
        customerFilterlist.setDataProvider(filterDataPrvd);
        emailForm.add(customerFilterlist);
		
		TextField groupId = new TextField("groupid", "团ID(指定团团员)", 50, false);
		groupId.setTrim(true);
		emailForm.add(groupId);
		
		TextField menuName = new TextField("title", "标题", 50, true);
		menuName.setTrim(true);
		emailForm.add(menuName);
		
		TextArea menuDesc = new TextArea("content", "正文", 50, 10, true);
		menuDesc.setTrim(true);
		emailForm.add(menuDesc);
		
		FileField attachment1 = new FileField("attachment1", "附件一");
		emailForm.add(attachment1);
		
		FileField attachment2 = new FileField("attachment2", "附件二");
		emailForm.add(attachment2);
		
		FileField attachment3 = new FileField("attachment3", "附件三");
		emailForm.add(attachment3);
		
		FileField attachment4 = new FileField("attachment4", "附件四");
		emailForm.add(attachment4);
		
		FileField attachment5 = new FileField("attachment5", "附件五");
		emailForm.add(attachment5);

		emailForm.add(new Submit("send", "发送邮件", this, "onSendClick"));
		emailForm.add(new Submit("cancel", "取消发送", this, "onCancelClick"));

	}

    /**
     * Send email
     * @return
     */
    public boolean onSendClick() {
        if (emailForm.isValid()) {
        	CustomerEmailCAMDto emaildto = new CustomerEmailCAMDto(); 
    		emailForm.copyTo(emaildto);

    		List<FileItem> attachments = new ArrayList<FileItem>();
    		attachments.add(((FileField)emailForm.getField("attachment1")).getFileItem());
    		attachments.add(((FileField)emailForm.getField("attachment2")).getFileItem());
    		attachments.add(((FileField)emailForm.getField("attachment3")).getFileItem());
    		attachments.add(((FileField)emailForm.getField("attachment4")).getFileItem());
    		attachments.add(((FileField)emailForm.getField("attachment5")).getFileItem());
    		
    		// Set attachment FileItem
    		emaildto.setAttachments(attachments);
    		
    		// Set sender
    		emaildto.setSender((Staff)getContext().getSession().getAttribute("staff"));

    		// Send message and email
    		ServiceResponse sr = getCustomerService().sendEmailMultiAtts(emaildto);
    		
    		if(sr.isSucc()) {
	    		// Show javascript message window and redirect the page 
				getHeadElements().add(new JsScript("alert('发送成功')"));
				getHeadElements().add(
						new JsScript("window.location = './customer_email_cam.htm'"));
		        return true;
    		} else {
    			emailForm.setError(Info.toStringInfo(sr.getInfo_code()));
    			return false;
    		}
        }
        return true;
    }

    /**
     * Redirect to customer_email_cam.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(CustomerEmailCAM.class);
        return true;
    }

}
