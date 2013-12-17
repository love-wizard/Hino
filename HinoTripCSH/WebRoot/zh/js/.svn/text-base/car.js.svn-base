jQuery(document).ready(function() {
	var cs_title = "";
	var cs_mail = "";
	var cs_phone="";
	var cs_car="";
	var cs_nod="";
	var cs_nop="";
	var cs_date="";
	var cs_comment="";
	
	jQuery("#show_car1").click(function(event){
		jQuery("#car_1").show();
		jQuery("#car_2").hide();
		jQuery("#car_3").hide();
		jQuery("#car_4").hide();
		jQuery("#car_5").hide();
		jQuery("#car_6").hide();
        return false;
    });
	
	jQuery("#show_car2").click(function(event){
		jQuery("#car_1").hide();
		jQuery("#car_2").show();
		jQuery("#car_3").hide();
		jQuery("#car_4").hide();
		jQuery("#car_5").hide();
		jQuery("#car_6").hide();
        return false;
    });
	
	jQuery("#show_car3").click(function(event){
		jQuery("#car_1").hide();
		jQuery("#car_2").hide();
		jQuery("#car_3").show();
		jQuery("#car_4").hide();
		jQuery("#car_5").hide();
		jQuery("#car_6").hide();
        return false;
    });
	
	jQuery("#show_car4").click(function(event){
		jQuery("#car_1").hide();
		jQuery("#car_2").hide();
		jQuery("#car_3").hide();
		jQuery("#car_4").show();
		jQuery("#car_5").hide();
		jQuery("#car_6").hide();
        return false;
    });
	
	jQuery("#submit_1").click(function(event){

		
		cs_title = jQuery("#textfield").val();
		cs_mail = jQuery("#textfield2").val();
		cs_phone= jQuery("#textfield3").val();
		cs_car=jQuery("#textfield4").val();
		cs_nod=jQuery("#textfield5").val();
		cs_nop=jQuery("#textfield6").val();
		cs_date=jQuery("#textfield7").val();
		cs_comment=jQuery("#textfield8").val();
		
		if (!validate_email(cs_mail))
		{
			alert("Invalid email address!");
			return false;
		}
		
		if (cs_title=="")
		{
			alert("You must enter the name!");
			return false;
		}
		
		if (cs_phone=="")
		{
			alert("You must enter the phone number!");
			return false;
		}
		
		if(isNaN(cs_nop))
		{
			alert("check person format");
			return; 
		} 
		
		if(isNaN(cs_nod))
		{
			alert("check day format");
			return; 
		} 
		
		if(!jQuery('#checkbox').attr("checked")==true)
    	{
			alert("Please confirm the agreement!");
			return false;
    	}

		jQuery("#qdxx").html("");
    	jQuery("#qdxx").append("邮箱 :"+cs_mail+"<br/>");
    	jQuery("#qdxx").append("车型 :"+cs_car+"<br/>");
    	jQuery("#qdxx").append("天数 :"+cs_nod+"<br/>");
    	jQuery("#qdxx").append("人数 :"+cs_nop+"<br/>");
    	jQuery("#qdxx").append("开始日期:"+cs_date+"<br/>");
    	jQuery("#qdxx").append("其他信息:"+cs_comment+"<br/>");
		
		jQuery("#car_1").hide();
		jQuery("#car_2").hide();
		jQuery("#car_3").hide();
		jQuery("#car_4").hide();
		jQuery("#car_5").hide();
		jQuery("#car_6").show();
		
		
        return false;
    });
	
	jQuery("#submit_2").click(function(event){
        alert("submit");
		
		jQuery.post("./car_service.htm", "car_request=1&name="+cs_title+"&nop="+cs_nop+"&nod="+cs_nod+"&car="+cs_car+"&email="+cs_mail+"&phone="+cs_phone+"&date="+cs_date+"&detail="+cs_comment, function(data) {
			alert("returned");
			jQuery("#car_1").hide();
			jQuery("#car_2").hide();
			jQuery("#car_3").hide();
			jQuery("#car_4").hide();
			jQuery("#car_5").show();
			jQuery("#car_6").hide();
			
			jQuery("#order_info").html("");
			//jQuery("#order_info").append("<p>订单编号："+cs_ref_no+"</p>");
			jQuery("#order_info").append("<p class='process'>您的订单已成功受理 </p>");
			jQuery("#order_info").append("<p class='order_c'>我们会在2个工作日内联系您 </p>");
			jQuery("#order_info").append("<p>您的订购详细信息已经发送到</p>");
			jQuery("#order_info").append("<p>订单编号："+cs_mail+"</p>");
		   
    	});
        // Prevent the default browser behavior of navigating to the link
		

    	  

        return false;
    });
	
	jQuery("#back_1").click(function(event){
		jQuery("#car_1").show();
		jQuery("#car_2").hide();
		jQuery("#car_3").hide();
		jQuery("#car_4").hide();
		jQuery("#car_5").hide();
		jQuery("#car_6").hide();
        return false;
    });
	
});

function validate_email(email) {
	   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	   return reg.test(email);
}