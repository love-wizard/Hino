package com.hino.page.internal;

import java.util.Calendar;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ControlRegistry;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
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
import org.apache.click.extras.control.DoubleField;
import org.apache.click.extras.control.IntegerField;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.GroupCAMDto;
import com.hino.dto.VoucherCAMDto;
import com.hino.model.Group;
import com.hino.model.Route;
import com.hino.model.Voucher;
import com.hino.util.EscapeHtml;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class GeneralSalesVoucherCAM extends BasePage{

	@Bindable private Form form = new Form("voucher_cam_form");
	
	private DateField expire_date = new DateField("expire_date", "过期时间", 15, true);
	private TextField match_code = new TextField("match_code", "对应码", false);
	private DoubleField value = new DoubleField("value", "抵用价值", 5,true);
	private IntegerField no_of_use = new IntegerField("no_of_use", "可使用次数", 5, true);
	private IntegerField no_of_voucher = new IntegerField("no_of_voucher", "生成优惠券数量", 5, true);
	private TextArea result = new TextArea("result", "生成结果", 50, 10);
	private Submit generate = new Submit("generate", "Generate");

	public void onInit() {
		//routeid.setDisabled(true);
		addControl(form);
		expire_date.setFormatPattern("yyyy-MM-dd");
		
		no_of_use.setValue("1");
		value.setValue("0.0");
		no_of_voucher.setValue("1");	
		
		form.add(expire_date);
		form.add(match_code);
		form.add(value);
		form.add(no_of_use);
		form.add(no_of_voucher);
		form.add(result);
		

        generate.addBehavior(new DefaultAjaxBehavior() {

            @Override
            public ActionResult onAction(Control source) {
                // Return a success response
                // Form data can be saved here
            	VoucherCAMDto vcam = new VoucherCAMDto();
    			Calendar c = Calendar.getInstance();
    			c.setTime(expire_date.getDate());
    			
            	vcam.setExpire_date(c);
            	vcam.setNo_of_create(no_of_voucher.getInteger());
            	vcam.setNo_of_use(no_of_use.getInteger());
            	vcam.setValue(value.getDouble());
            	vcam.setMatch_type(match_code.getValue());
            	
            	
            	List<String> list = getSalesService().create_voucher(vcam);
            	StringBuffer sb = new StringBuffer();
            	for(String s: list)
            	{
            		sb = sb.append(s);
            		sb.append("\r\n");
            	}
            	
                return new ActionResult(sb.toString(), ActionResult.HTML);
            }
        });

        // NOTE: we explicitly register the Form as an Ajax target so that the
        // Fom#onProcess method can be invoked. The save button's Behavior will
        // still handle the request though
        
        form.add(generate);
        ControlRegistry.registerAjaxTarget(form);
		
		
		
	}
	

}
