package com.hino.page.internal;

import org.apache.click.control.FileField;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Radio;
import org.apache.click.control.RadioGroup;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.EmailField;
import org.apache.click.extras.control.RegexField;
import org.apache.click.extras.control.TelephoneField;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.StaffBasicInfoDto;
import com.hino.model.Staff;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

/**
 * Show staff basic information in form to provide modification feature
 */
public class BasicInfoManage extends BasePage {
	private static final long serialVersionUID = -5540284222528345128L;
	
	@Bindable private Form basicInfoForm = new Form("basicinfoform");

	@Override
	public void onInit() {
		
		// ----- Display staff basic information form -----
		Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");
		if(curStaff==null)
		{
			setRedirect(Index.class);
		} else {
		// Staff id
		HiddenField staffIdFld = new HiddenField("id", String.class);
		staffIdFld.setValue(String.valueOf(curStaff.getId()));
		basicInfoForm.add(staffIdFld);
		
		TextField staffNum = new TextField("staffnum", "ID", 50, false);
		staffNum.setValue(curStaff.getStaffno());
		staffNum.setDisabled(true);
		basicInfoForm.add(staffNum);
		
		RegexField lastName = new RegexField("lastname", "Last Name", 50, true);
		lastName.setValue(curStaff.getLastname());
		lastName.setPattern("[a-zA-Z ]++");
		basicInfoForm.add(lastName);
		
		RegexField firstName = new RegexField("firstname", "First Name", 50, true);
		firstName.setValue(curStaff.getFirstname());
		firstName.setPattern("[a-zA-Z ]++");
		basicInfoForm.add(firstName);
		
		RegexField chineseName = new RegexField("chinesename", "Chinese Name", 50, false);
		chineseName.setValue(EscapeHtml.htmlDecode(curStaff.getChinesename()));
		basicInfoForm.add(chineseName);
		
		RadioGroup gender = new RadioGroup("gender", "Gender", true);
		gender.add(new Radio("0", "Male "));
		gender.add(new Radio("1", "Female "));
		gender.setValue(String.valueOf(curStaff.getGender()));
		gender.setVerticalLayout(true);
		basicInfoForm.add(gender);

		if(curStaff.getAvatar_url() != null && curStaff.getAvatar_url().length() > 0) {
			Image curAvatar = new Image();
			curAvatar.setSrc(Info.INTERNAL_PATH_PREFIX+curStaff.getAvatar_url());
			basicInfoForm.add(curAvatar);
		}
		FileField newAvatar = new FileField("avatar", "Upload Avatar");
		basicInfoForm.add(newAvatar);
		
		EmailField email = new EmailField("email", "Email", 50, true);
		email.setValue(curStaff.getEmail());
		basicInfoForm.add(email);
		
		TelephoneField telephone = new TelephoneField("telephone", "Mobile", 50, true);
		telephone.setValue(curStaff.getPhone());
		basicInfoForm.add(telephone);
		
		DateField dob = new DateField("dob", "Birthday", 45, true);
		dob.setFormatPattern("yyyy-MM-dd");
		dob.setValue(TimeFormater.format2(curStaff.getDob()));
		basicInfoForm.add(dob);
		
		TextArea address = new TextArea("address", "Address", 50, 10, false);
		address.setValue(EscapeHtml.htmlDecode(curStaff.getAddress()));
		address.setTrim(true);
		basicInfoForm.add(address);	
		
		TextField city = new TextField("city", "City", 50, false);
		city.setValue(EscapeHtml.htmlDecode(curStaff.getCity()));
		basicInfoForm.add(city);
		
		TextField postcode = new TextField("postcode", "Post code", 50, false);
		postcode.setValue(EscapeHtml.htmlDecode(curStaff.getPostcode()));
		basicInfoForm.add(postcode);
		
		basicInfoForm.add(new Submit("save", "Save Modification", this, "onSaveClick"));
		}
	}

    /**
     * Update staff basic information
     * @return
     */
    public boolean onSaveClick() {
        if (basicInfoForm.isValid()) {
        	StaffBasicInfoDto basicinfodto = new StaffBasicInfoDto();
        	basicInfoForm.copyTo(basicinfodto);
        	
    		// Set avatar FileItem
        	basicinfodto.setAvatar(((FileField)basicInfoForm.getField("avatar")).getFileItem());
    		
        	// Convert String into Calendar
        	basicinfodto.setDob(TimeFormater.parse2(basicInfoForm.getField("dob").getValue()));
        	
        	basicinfodto.setChinesename(EscapeHtml.htmlEncode(basicinfodto.getChinesename()));
        	basicinfodto.setAddress(EscapeHtml.htmlEncode(basicinfodto.getAddress()));
        	basicinfodto.setCity(EscapeHtml.htmlEncode(basicinfodto.getCity()));
        	basicinfodto.setPostcode(EscapeHtml.htmlEncode(basicinfodto.getPostcode()));
        	
        	ServiceResponse updateRes = getHumanResourceService().update_staff_basic(basicinfodto);
        	
        	if(updateRes.isSucc() == true) {
        		// Update session
        		getContext().getSession().setAttribute("staff", updateRes.getResponse());
        		
    			getHeadElements().add(new JsScript("alert('Saved successfully')"));
    			getHeadElements().add(
    					new JsScript("window.location = './basic_info_manage.htm'"));
    	        return true;
        	}
        	else {
        		basicInfoForm.setError(Info.toStringInfo(updateRes.getInfo_code()));
        		return false;
        	}
        }
    	return true;
    }

}
