var c_count = 0;

jQuery(document).ready(function() {
	
	//alert("load");
	jQuery("#add_customer").click(function(event){
		var airlprice =jQuery("input[name='price']").val();
		if(!validate_price(airlprice))
		{
			alert("Check Airline Price Format");
			return; 
		} 
		
    	make_div();
        return false;
    });
	
	jQuery("#cancel").click(function(event){
    	window.location = "./airline_booking_record.htm";
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
		
		 jQuery.post("./airline_internal_booking.htm", "load_customer=1&email="+email, function(data) {
        	if(data.result=="0")
        	{
        		alert("Target Customer Not Found");	
        	} else
    		{
    			fill_customer_data(index, data);
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
		var airline;
		var airline_company;
		var airport;
		var depart_time;
		var payer_type;
		var payment_method;
		airline = jQuery("select[name='airlineSelect']").val();
		airline_company = jQuery("input[name='AirlineCompnay']").val();
		airport = jQuery("select[name='airportSelect']").val();
		depart_time = jQuery("input[name='depart_time']").val();
		payer_type = jQuery("select[name='select_payer']").val();
		payment_method = jQuery("select[name='select_payment_method']").val();
		
		var airlneprice =jQuery("input[name='price']").val();
		if(!validate_price(airlneprice))
		{
			alert("Check Airline Price Format");
			return; 
		} 
		
		
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
				
				var phone = jQuery.trim(jQuery("#phone_"+j).val());
				if(phone==""||isNaN(phone))
				{
					alert("check phone format");
					return; 
				} 
				
				if(!validate_price(jQuery("#pay_"+j).val()))
				{
					alert("Check Price Format");
					return; 
				} 
				var price =parseFloat(jQuery("#pay_"+j).val());
				
				var comments = jQuery.trim(jQuery("#comments_"+j).val());
				
				jsondata.push({"fn":fn, "ln":ln, "cn":cn, "email":email, "gender":0, "phone":phone, "price":price, "comments":comments});
			}
		}
    	
//    	if(jQuery.trim(jQuery("#idfield").val())=="")
//    	{
//    		alert("无效的航线无法预订");
//    		return;
//    	}

    	jQuery.post("./airline_internal_booking.htm", "internal_airline_reserve=1&airline=" + airline + 
    			"&airline_company=" + airline_company + 
    			"&airport=" + airport +
    			"&depart_time=" + depart_time +
    			"&price=" + airlneprice +
    			"&payer_type=" + payer_type +
    			"&payment_method=" + payment_method +
    			"&jsondata="+JSON.stringify(jsondata), function(data) {
        	alert(data);
    		window.location = "./airline_booking_record.htm";
    	});
        
    	
	});
	
});

function make_div()
{
	var s = "<div id='div_" + c_count+ "'>" +
		"Email<input id='email_" + c_count+ "' type='text' value=''/><input name='load_" + c_count+ "' type=button value='Search'/></br>" +
		"First Name<input id='fn_" + c_count+ "' type='text' /></br>" +
		"Last Name<input id='ln_" + c_count+ "' type='text' /></br>" +
		"中文名<input id='cn_" + c_count+ "' type='text' /></br>" +
		"Gender<select id='gender_" + c_count+ "'><option value=''></option><option value='0'>Male</option><option value='1'>Female</option></select></br>" +
		"Phone<input id='phone_" + c_count+ "' type='text' /></br>" +
		"Balance<input id='pay_" + c_count+ "' type='text' value=''/></br>" +
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
	jQuery("#pay_"+id).val(jQuery("input[name='price']").val());
	return false;
	
}

function validate_email(email) {
   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
   return reg.test(email);
}

function validate_price(vcode) {
   var reg = /^[0-9]+(.[0-9]{2})?$/; //验证有两位小数的正实数：^[0-9]+(.[0-9]{2})?$ 
   return reg.test(vcode);
}