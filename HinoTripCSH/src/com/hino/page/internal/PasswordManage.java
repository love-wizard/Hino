package com.hino.page.internal;

import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Submit;
import org.apache.click.element.JsScript;
import org.apache.click.util.Bindable;

import com.hino.dto.StaffPasswordDto;
import com.hino.model.Staff;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;

/**
 * Modify staff password
 */
public class PasswordManage extends BasePage {
	private static final long serialVersionUID = -4800852538523216997L;
	
	@Bindable private Form passwordForm = new Form("passwordform");

	@Override
	public void onInit() {
		
		// ----- Display password form -----
		Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");

		HiddenField staffIdFld = new HiddenField("id", String.class);
		staffIdFld.setValue(String.valueOf(curStaff.getId()));
		passwordForm.add(staffIdFld);

		PasswordField oldPsd = new PasswordField("oldpsd", "Old Password", 50, true);
		passwordForm.add(oldPsd);
		PasswordField newPsd = new PasswordField("newpsd", "New Password", 50, true);
		passwordForm.add(newPsd);
		PasswordField confPsd = new PasswordField("confpsd", "Repeat New Password", 50, true);
		passwordForm.add(confPsd);

		passwordForm.add(new Submit("save", "Save", this, "onSaveClick"));
		passwordForm.add(new Submit("cancel", "Cancel", this, "onCancelClick"));
	}

    /**
     * Update password
     * @return
     */
    public boolean onSaveClick() {
        if (passwordForm.isValid()) {
        	StaffPasswordDto psddto = new StaffPasswordDto();
    		passwordForm.copyTo(psddto);

    		if(psddto.getNewpsd().compareTo(psddto.getConfpsd()) == 0) {
    			ServiceResponse updateRes = getHumanResourceService().update_staff_psd(psddto);
    			
    			if(updateRes.isSucc()) {
	    			getHeadElements().add(new JsScript("alert('Password Changed')"));
	    			getHeadElements().add(
	    					new JsScript("window.location = './password_manage.htm'"));
	    			return true;
    			}
    			else {
        			passwordForm.setError(Info.toStringInfo(updateRes.getInfo_code()));
        			return false;
    			}
    		}
    		else {
    			passwordForm.setError("Password not matched");
    			return false;
    		}
        }
        return true;
    }

    /**
     * Redirect to password_manage.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(PasswordManage.class);
        return true;
    }

}
