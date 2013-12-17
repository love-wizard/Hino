//var pick_list = $pickup;
var c_count = 0;
//var pick_list = jQuery("#pickup");
var pickup = "";

jQuery(document).ready(function() {

//	alert(bkt);
	if(bkt==0)
	{
		tar_price = s_price;
		tar_v_price = v_price;
	} else if(bkt==1)
	{
		tar_price = go_price;
		tar_v_price = go_vip_price;
	} else if(bkt==2)
	{
		tar_price = sk_price;
		tar_v_price = sk_vip_price;
	}
	
	for(var i=0;i<ginfo.length;i++)
	{
		pickup += "<option value='"+ ginfo[i]+"'>"+ ginfo[i]+"</option>";	
	}
	
	jQuery("#add_customer").click(function(event){
    	make_div();
        return false;
    });
	
	jQuery("#cancel").click(function(event){
    	window.location = "./sales_rep_booking_select.htm";
        return false;
    });
    
    jQuery('input[name*="load_"]').live('click', function() {
    	
    	var partsArray = jQuery(this).attr("name").split('_');
    	var index = partsArray[1];
    	//alert(index);
    	var email = jQuery.trim(jQuery("#email_"+index).val());
		if(!validate_email(email))
		{
			alert("Check Email Format");
			return; 
		} 
		
		 jQuery.post("./sales_rep_booking_form.htm", "load_customer=1&email="+email, function(data) {
        	if(data.result=="0")
        	{
        		alert("Target Customer Not Found");	
        	} else
    		{
    			fill_customer_data(index, data);
    		}
        	
    	});
    	
	});
    
    jQuery('input[name*="check_"]').live('click', function() {
    	
    	var partsArray = jQuery(this).attr("name").split('_');
    	var index = partsArray[1];
    	//alert(index);
    	var vcode = jQuery.trim(jQuery("#voucher_"+index).val());
    	var groupid = jQuery("#idfield").val();
		if(!validate_voucher(vcode))
		{
			alert("Check Voucher Format");
			return; 
		} 
		 
		jQuery.post("./sales_rep_booking_form.htm", "check_voucher=1&vcode="+vcode+"&groupid="+groupid, function(data) {

        	if(data.result=="0")
        	{
        		alert("Voucher invalid");	
        	} else
    		{
        		jQuery("#pd_voucher_"+index).val(data.values);
    			alert("This voucher could be used as: " + data.values + " pounds but must be confirmed before " + data.dates);
    		}
    	});
	});
    
    jQuery('input[name*="recal_"]').live('click', function() {
    	
    	var partsArray = jQuery(this).attr("name").split('_');
    	var index = partsArray[1];
    	//alert(index);
    	var v_paid = jQuery.trim(jQuery("#act_price_"+index).val());
    	//alert(v_paid);
		var v_voucher = jQuery.trim(jQuery("#pd_voucher_"+index).val());
		var v_point = jQuery.trim(jQuery("#pd_point_"+index).val());
		 
		jQuery("#pay_"+index).val(v_paid-v_voucher-v_point/100);
		
	});
    
	
	jQuery('input[name*="rm_"]').live('click', function() {
    	
    	var partsArray = jQuery(this).attr("name").split('_');
    	var index = partsArray[1];
    	//alert(index);
    	remove_customer(index);
    	
	});
	
	jQuery("#submit").click(function(event){
		var jsondata = [];
    	for(var j=0;j<c_count;j++)
		{
			if(jQuery("#div_"+j).length>0)
			{
				var fn = jQuery.trim(jQuery("#fn_"+j).val());

				
				if(fn=="")
				{
					alert("First Name should not be empty!");
					return; 
				}
					
				var ln = jQuery.trim(jQuery("#ln_"+j).val());
				if(ln=="")
				{
					alert("Last Name should not be empty");
					return; 
				} 
				
				var cn = jQuery.trim(jQuery("#cn_"+j).val());
				if(cn=="")
				{
					alert("Chinese should not be empty");
					return; 
				} 
				
				var email = jQuery.trim(jQuery("#email_"+j).val());
				if(!validate_email(email))
				{
					alert("Check Email Format");
					return; 
				} 
				
				var voucher = jQuery.trim(jQuery("#voucher_"+j).val());
				if(voucher!="")
				{
				if(!validate_voucher(voucher))
				{
					alert("Check Voucher Format");
					return; 
				} 
				}
				
				var gender = jQuery.trim(jQuery("#gender_"+j).val());
				if(gender=="")
				{
					alert("Gender should not be empty");
					return; 
				} 
				var phone = jQuery.trim(jQuery("#phone_"+j).val());
				if(phone==""||isNaN(phone))
				{
					alert("check phone format");
					return; 
				} 
				
				var comments = jQuery.trim(jQuery("#comments_"+j).val());
				
				var pickup_p = jQuery.trim(jQuery("#pickup_"+j).val());
				if(pickup_p=="")
				{
					alert("pick up should not be empty");
					return; 
				} 
				
				var pd_point = parseInt(jQuery.trim(jQuery("#pd_point_"+j).val()));

				if(isNaN(pd_point)||pd_point%100!=0||pd_point>gmaxpoint)
				{
					alert("积分必须为100的整数倍且小于等于该团最大积分限制");
					return; 
				}
				
				var room = jQuery.trim(jQuery("#room_"+j).val());

				jsondata.push({"fn":fn, "ln":ln, "cn":cn, "email":email, "gender":gender, "phone":phone, "comments":comments, "pickup":pickup_p, "pd_point":pd_point,"room":room, "voucher":voucher, "paymethod":0});
			}
		}
    	
    	if(jQuery.trim(jQuery("#idfield").val())=="")
    	{
    		alert("无效的团无法预订");
    		return;
    	}
    	
    	jQuery.post("./sales_rep_booking_form.htm", "internal_reserve=1&gid="+jQuery("#idfield").val()+"&bktype="+jQuery("#bktypefield").val()+"&jsondata="+JSON.stringify(jsondata), function(data) {
        	alert(data);
    		window.location = "./sales_rep_booking_record.htm";
    	});
        
    	
	});
	
});

function make_div()
{
	var s = "<div id='div_" + c_count+ "'>" +
			"Email<input id='email_" + c_count+ "' type='text' /><input name='load_" + c_count+ "' type=button value='Search'/></br>" +
			"First Name<input id='fn_" + c_count+ "' type='text' /></br>" +
			"Last Name<input id='ln_" + c_count+ "' type='text' /></br>" +
			"中文名<input id='cn_" + c_count+ "' type='text' /></br>" +
			"Gender<select id='gender_" + c_count+ "'><option value=''></option><option value='0'>Male</option><option value='1'>Female</option></select></br>" +
			"Phone<input id='phone_" + c_count+ "' type='text' /></br>" +
			"Pick up<select id='pickup_" + c_count+ "'><option value=''></option>"+pickup+"</select></br>" +
			"Use point<input id='pd_point_" + c_count+ "' type='text' value='0' />Points Available<input type='text' id='pt_"+c_count+"' value='0' disabled='disabled' /></br>" +
			"Balance<input id='pay_" + c_count+ "' type='text' disabled='true' value='"+tar_price+"'/><input id='act_price_" + c_count+ "' type='hidden' value='"+tar_price+"'/><input name='recal_" + c_count+ "' type=button value='Recalc'/></br>" +
//			"Balance<input id='pay_" + c_count+ "' type='text' value='"+tar_price+"'/><input id='act_price_" + c_count+ "' type='hidden' value='"+tar_price+"'/><input name='recal_" + c_count+ "' type=button value='Recalc'/></br>" +
			"Use voucher<input id='voucher_" + c_count+ "' type='text' value='' />Voucher value<input type='text' id='pd_voucher_"+c_count+"' value='0' disabled='disabled' /><input name='check_" + c_count+ "' type=button value='Check'/></br>" +
			"Roommate<input id='room_" + c_count+ "' type='text' /></br>" +
			"Comments<input id='comments_" + c_count+ "' type='text' /></br>" +
			"<input name='rm_" + c_count+ "' type=button value='delete this'/>" +
			"</div><hr/>";
	jQuery("#customer_field").append(s);
	c_count++;
}

function remove_customer(id)
{
	jQuery("#div_"+id).remove();
}

function fill_customer_data(id, c_data)
{
//	alert(c_data.isvip);
//	alert(tar_v_price);
//	alert(tar_price);
	jQuery("#fn_"+id).val(c_data.fn);
	jQuery("#ln_"+id).val(c_data.ln);
	jQuery("#cn_"+id).val(c_data.cn);
	jQuery("#gender_"+id).val(c_data.gender);
	jQuery("#phone_"+id).val(c_data.phone);
	jQuery("#pt_"+id).val(c_data.pt);
	
	if(c_data.isvip=="true")
	{
		jQuery("#pay_"+id).val(tar_v_price);
		jQuery("#act_price_"+id).val(tar_v_price);
	} else
	{
		jQuery("#pay_"+id).val(tar_price);
		jQuery("#act_price_"+id).val(tar_price);
	}
	
}

function validate_email(email) {
   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
   return reg.test(email);
}

function validate_voucher(vcode) {
   var reg = /^[A-Z0-9]{16}$/;
   return reg.test(vcode);
}