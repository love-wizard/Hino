jQuery(document).ready(function() {

    // Register a 'click' handler on the submit button
    jQuery("#reg_step1_button").click(function(event){
        // Post form to server
        //postForm(event);
    	//var jsondata = [];
    	var bcontinue = true;
    	var fn = jQuery.trim(jQuery("#reg_fn").val());
				
		if(fn=="")
		{
			alert("First Name should not be empty!");
			return; 
		}
		
		var ln = jQuery.trim(jQuery("#reg_ln").val());
		if(ln=="")
		{
			alert("Last Name should not be empty");
			return; 
		}
		var cn = jQuery.trim(jQuery("#reg_cn").val());
		
		var pass1=jQuery.trim(jQuery("#reg_ps1").val());
		var pass2=jQuery.trim(jQuery("#reg_ps2").val());
		
		if (pass1!=pass2)
		{
			alert("两次密码不一致！");
			return;
		}
		
		if (pass1.length<6)
		{
			alert("密码须大于5位！");
			return;
		}
		
		
    	var em = jQuery.trim(jQuery("#reg_email").val());
    	if (!validate_email(em))
    	{
    		alert("请检查email格式");
    		return;
    	} else 
    	{
    		jQuery.ajax({
    			  type: "POST",
    			  url: "./registration.htm",
    			  async: false,
    			  data: {"reg_step1":"1", "em":em},
    			  success: function(data) {
    		        	if(data.result=="1")
    		        	{
    		        		bcontinue = false;
    		        		alert("该Email地址已被注册！");
    		        		return;
    		        		
    		        	} else
    		    		{
    		        		//jQuery("#vcv_"+index).val(data.values);
    		    			//alert("This voucher could be used as: " + data.values + " pounds but must be confirmed before " + data.dates);
    		    		}
    			  }
    		});
    		
    		/*
    		jQuery.post("./registration.htm", 
    				"reg_step1=1&em="+em, function(data) {
	        	if(data.result=="1")
	        	{
	        		bcontinue = false;
	        		alert("该Email地址已被注册！");
	        		return;
	        		
	        	} else
	    		{
	        		//jQuery("#vcv_"+index).val(data.values);
	    			//alert("This voucher could be used as: " + data.values + " pounds but must be confirmed before " + data.dates);
	    		}
	        	
	    	});*/
    	}
   	
    	if (!bcontinue) {
    		return;
    	}
    	
    	jQuery("#reg_step1").hide();
    	jQuery("#reg_step2").show();
    	jQuery("#reg_step3").hide();
    	
    	jQuery("#step_area").attr("class", "setep2");
    	
    	//check
    	
    });
    
    jQuery("#reg_step2_button").click(function(event){
    	
        // Post form to server
        //postForm(event);
    	//var jsondata = [];
    	
    	var fn = jQuery.trim(jQuery("#reg_fn").val());
				
		if(fn=="")
		{
			alert("First Name should not be empty!");
			return; 
		}
			
		var ln = jQuery.trim(jQuery("#reg_ln").val());
		if(ln=="")
		{
			alert("Last Name should not be empty");
			return; 
		}
		var cn = jQuery.trim(jQuery("#reg_cn").val());
		//alert(cn);
		var pass1=jQuery.trim(jQuery("#reg_ps1").val());
		var pass2=jQuery.trim(jQuery("#reg_ps2").val());
		
		if (pass1!=pass2)
		{
			alert("两次密码不一致！");
			return;
		}
		
		if (pass1.length<6)
		{
			alert("密码须大于5位！");
			return;
		}
		
		var uni = jQuery.trim(jQuery("#reg_uni").val());
		var city = jQuery.trim(jQuery("#reg_city").val());
		var phone = jQuery.trim(jQuery("#reg_ph").val());
		var gender = 0;
		if (jQuery("#reg_female").attr("checked")=='checked') {
			gender = 1;
		} 
		
    	var em = jQuery.trim(jQuery("#reg_email").val());
    	if (!validate_email(em))
    	{
    		alert("请检查email格式");
    		return;
    	} else 
    	{
    		/*
    		jQuery.post("./registration.htm", "reg_step2=1&em="+em+"&fn="+fn+"&ln="+ln+"&cn="+cn+"&pass1="+pass1+"&pass2="+pass2+"&uni="+uni+"&city="+city+"&ph="+phone+"&gender="+gender, function(data) {

	        	if(data.result=="1")
	        	{
	        		alert("注册失败");
	        		return;
	        		
	        	} else
	    		{
	        		//alert("succ");
	        		//jQuery("#vcv_"+index).val(data.values);
	    			//alert("This voucher could be used as: " + data.values + " pounds but must be confirmed before " + data.dates);
	        		//jQuery("#reg_result").html("姓名:" + ln + " " + fn +"<br/>中文名:"+ cn+ "<br/>EMAIL:" + em + "<br/>");
	    		}
	        	
	    	});
    		*/
    		
    		jQuery.ajax({
    			type: "POST",
    			url: "./registration.htm", 
    			async: false,
    			data: {"reg_step2":"1", "em":em, "fn":fn, "ln":ln,"cn":cn,"pass1":pass1,"pass2":pass2,"uni":uni,"city":city,"ph":phone,"gender":gender}, 
				success: function(data) {
		        	if(data.result=="1")
		        	{
		        		alert("注册失败");
		        		return;
		        		
		        	} else
		    		{
		        		//alert("succ");
		        		//jQuery("#vcv_"+index).val(data.values);
		    			//alert("This voucher could be used as: " + data.values + " pounds but must be confirmed before " + data.dates);
		        		//jQuery("#reg_result").html("姓名:" + ln + " " + fn +"<br/>中文名:"+ cn+ "<br/>EMAIL:" + em + "<br/>");
		    		}
		        	
		    	}
    		});
    		
    		jQuery("#reg_result").html("姓名:" + ln + " " + fn +"<br/>中文名:"+ cn+ "<br/>EMAIL:" + em + "<br/>");
    	}

    	jQuery("#step_area").attr("class", "setep3");
    	jQuery("#reg_step1").hide();
    	jQuery("#reg_step2").hide();
    	jQuery("#reg_step3").show();
    });
    
    jQuery("#reg_step3_button").click(function(event){
    	
		var password = jQuery.trim(jQuery("#reg_ps1").val());
		var username = jQuery.trim(jQuery("#reg_email").val());
    	jQuery.post("./index.htm", "login_action=1&un="+username+"&ps="+password, function(data) {
        	//alert(data);
        	if(data.result==0)
        	{
        		//alert("欢迎回来"+data.fn + " " + data.ln);
        		window.location="./index.htm";
        		
        	} else
        	{
        		alert(data.result);
        	}
    		//window.location = "./sales_rep_booking_record.htm";
    	});
    	
    });
    
	
});

function validate_email(email) {
   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
   return reg.test(email);
}


