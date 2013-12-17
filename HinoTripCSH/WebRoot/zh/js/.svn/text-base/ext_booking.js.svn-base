var add_count=1;
var total_price=0;
var pay_method=1;
var total_vip_dis = 0;
var total_voucher_dis=0;
var total_point_dis=0;

jQuery(document).ready(function() {		
	if(bt==2)
	{
		jQuery('#pm_2').attr("disabled",true);
		jQuery('#pm_3').attr("disabled",true);
	}
	
	if(bt!=0)
	{
		jQuery("input[id*='pt']").attr("disabled",true);
		//Devon King - 2012/09/30 - TD#20 Do not disable voucher in here
		//jQuery("input[id*='vc']").attr("disabled",true);
	}
    // Register a 'click' handler on the submit button
    jQuery("#add_additional").click(function(event){
        // Post form to server
        //postForm(event);
    	//var jsondata = [];
    	//var username = jQuery("#login_form_username").val();
    	//var password = jQuery("#login_form_password").val();
    	
    	//jsondata.push({"username": username, "password": password});
        //jQuery.post("./index.htm", "login_action=1&un="+username+"&ps="+password, function(data) {
        	make_div();
    	//alert('gg');
    		//window.location = "./sales_rep_booking_record.htm";
    	//});
        // Prevent the default browser behavior of navigating to the link
        return false;
    });
        
    // 订购几张就几个div	
	for(var bni = 0; bni < booking_num - 1; bni ++ ){
		jQuery("#add_additional").click();		
	}
    
    jQuery("#next_button").live('click',function(event){
    	var jsondata = [];
    	total_price=0;
		total_vip_dis = 0;
		total_voucher_dis=0;
		total_point_dis=0;
		
		
		
    	for(var j=0;j<add_count;j++)
		{
			if(jQuery("#pf_"+j).length>0)
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
				
				var email = jQuery.trim(jQuery("#em_"+j).val());
				if(!validate_email(email))
				{
					alert("Check Email Format");
					return; 
				} 
				
				var gender = 0
				if (jQuery("#male_"+j).attr("checked")=='checked') {
					gender = 0;
				} else
				{
					gender = 1;
				}
				//if(gender=="")
				//{
					//alert("Gender should not be empty");
					//return; 
				//} 
				
				var phone = jQuery.trim(jQuery("#ph_"+j).val());
				if(phone==""||isNaN(phone))
				{
					alert("check phone format");
					return; 
				} 
				
				var comments = jQuery.trim(jQuery("#cm_"+j).val());
				
				var pickup_p = jQuery.trim(jQuery("#pickup_"+j).val());
				if(pickup_p=="")
				{
					alert("pick up should not be empty");
					return; 
				}
				
				var isUsedVoucher = false;
				var voucher = jQuery.trim(jQuery("#vc_"+j).val());
				if(voucher!="")
				{
					if(!validate_voucher(voucher))
					{
						alert("Check Voucher Format");
					    return;	
					}
					
		    		jQuery.ajax({
		    			  type: "POST",
		    			  url: "./booking.htm",
		    			  async: false,
		    			  data: {"check_voucher":"1", "vcode":voucher, "groupid":groupid},
		    			  success: function(data) {
		    				    if(data.result=="0")
					        	{
					        		alert("Voucher invalid for this group");
					        		return;	
					        	} else if(data.result=="1")
					            {
					            	alert("此优惠券的使用次数已被用完了！");
					            	isUsedVoucher = true;
					            	return;	
					        	} else
					    		{
					        		//jQuery("#vcv_"+index).val(data.values);
					    			//alert("This voucher could be used as: " + data.values + " pounds but must be confirmed before " + data.dates);
					    		}
		    			  }
		    		});
		    		
		    		if (isUsedVoucher) {
		    			return;	
		    		}
					
		    		/*
					jQuery.post("./booking.htm", "check_voucher=1&vcode="+voucher+"&groupid="+groupid, function(data) {

			        	if(data.result=="0")
			        	{
			        		alert("Voucher invalid for this group");
			        		return;	
			        	} else if(data.result=="1")
			            {
			            	alert("The voucher has been used and cannot be used again");
			            	return;	
			        	} else
			    		{
			        		//jQuery("#vcv_"+index).val(data.values);
			    			//alert("This voucher could be used as: " + data.values + " pounds but must be confirmed before " + data.dates);
			    		}
			        	
			    	});*/
				}
				
				var pd_point = parseInt(jQuery.trim(jQuery("#pt_"+j).val()));

				if(isNaN(pd_point)||pd_point%100!=0||pd_point>groupmaxcredit)
				{
					alert("积分必须为100的整数倍且小于等于该团最大积分限制");
					return; 
				}
				
				var act_price = 0;
				var vip_dis = 0;
				var voucher_dis=0;
				var point_dis=0;
				if(bt==0)
				{
					if(isvip=="false")
					{
					act_price = groupprice - pd_point/100 - parseInt(jQuery.trim(jQuery("#vcv_"+j).val()));
					} else
					{
					act_price = groupvipprice - pd_point/100 - parseInt(jQuery.trim(jQuery("#vcv_"+j).val()));
					vip_dis=groupprice-groupvipprice;
					
					}
					
				} else if(bt==1)
				{
					if(isvip=="false")
					{
					act_price = goprice - pd_point/100 - parseInt(jQuery.trim(jQuery("#vcv_"+j).val()));
					} else
					{
					act_price = go_vip_price - pd_point/100 - parseInt(jQuery.trim(jQuery("#vcv_"+j).val()));
					vip_dis=goprice-go_vip_price;
					
					}
					
				} else if(bt==2)
				{
					if(isvip=="false")
					{
					act_price = skprice - pd_point/100 - parseInt(jQuery.trim(jQuery("#vcv_"+j).val()));
					} else
					{
					act_price = sk_vip_price - pd_point/100 - parseInt(jQuery.trim(jQuery("#vcv_"+j).val()));
					vip_dis=skprice-sk_vip_price;
					
					}
					
				}
				voucher_dis = parseInt(jQuery.trim(jQuery("#vcv_"+j).val()));
				//alert(voucher_dis);
				point_dis = pd_point/100;
				
				total_price = total_price + act_price;
				total_vip_dis = total_vip_dis+vip_dis;
				total_voucher_dis=total_voucher_dis+voucher_dis;
				total_point_dis=total_point_dis+point_dis;
				
				var room = jQuery.trim(jQuery("#rm_"+j).val());

				jsondata.push({"fn":fn, "ln":ln, "cn":cn, "email":email, "gender":gender, "phone":phone, "comments":comments, "pickup":pickup_p,"room":room, "pd_point":pd_point, "voucher":voucher});
			}
		}

        jQuery("#data_page").hide();
        jQuery("#pay_page").show();
        jQuery("#final_page").hide();
        jQuery("#info_review").html("");
        for (var i=0;i<jsondata.length;i++)
    	{
    		jQuery("#info_review").append("姓名:"+jsondata[i].fn + " " + jsondata[i].ln +"<br/> Email: " + jsondata[i].email +"<br/> 电话: " + jsondata[i].phone+"<br/> 接车: " + jsondata[i].pickup +"<br/>");
    	}
        
        // Prevent the default browser behavior of navigating to the link
        return false;
    });
    
    jQuery("#back_info_page").click(function(event){
    	jQuery("#data_page").show();
        jQuery("#pay_page").hide();
        jQuery("#final_page").hide();
        jQuery("#result_page").hide();
    });
    
    jQuery("#go_final_page").click(function(event){
    	jQuery("#data_page").hide();
        jQuery("#pay_page").hide();
        jQuery("#final_page").show();
        jQuery("#result_page").hide();
    	if(jQuery('#pm_1').attr("checked")=='checked')
    	{
    		pay_method = 2;
    		jQuery("#pay_way_2").show();
    		jQuery("#pay_way_1").hide();
    		jQuery("#pay_way_3").hide();
    		jQuery("#pay_way_4").hide();
    	}
    	if(jQuery('#pm_2').attr("checked")=='checked')
    	{
    		pay_method = 1;
    		jQuery("#pay_way_3").show();
    		jQuery("#pay_way_1").hide();
    		jQuery("#pay_way_2").hide();
    		jQuery("#pay_way_4").hide();
    	}
    	if(jQuery('#pm_3').attr("checked")=='checked')
    	{
    		pay_method = 3;
    		jQuery("#pay_way_1").show();
    		jQuery("#pay_way_3").hide();
    		jQuery("#pay_way_2").hide();
    		jQuery("#pay_way_4").hide();
    	}
    	
    	if(jQuery('#pm_4').attr("checked")=='checked')
    	{
    		pay_method = 4;
    		jQuery("#pay_way_1").hide();
    		jQuery("#pay_way_3").hide();
    		jQuery("#pay_way_2").hide();
    		jQuery("#pay_way_4").show();
    	}
    	
    	
    	
    	jQuery("#payment_info").html("");
    	jQuery("#payment_info").append("VIP折扣     "+total_vip_dis+"<br/>");
    	jQuery("#payment_info").append("优惠券折扣  "+total_voucher_dis+"<br/>");
    	jQuery("#payment_info").append("积分折扣    "+total_point_dis+"<br/>");
    	jQuery("#payment_info").append("应付价格    "+total_price+"<br/>");
    	jQuery("#payment_info").append("注意：优惠券及积分情况如发生变化可能导致预订确认失败<br/>");
    	

    	
    });
    
    jQuery("#back_to_pay").click(function(event){
    	jQuery("#data_page").hide();
        jQuery("#pay_page").show();
        jQuery("#final_page").hide();
        jQuery("#result_page").hide();
    	
    	
    });
    
    
    
    jQuery("#reserve_button").click(function(event){
    	var jsondata = [];
    	for(var j=0;j<add_count;j++)
		{
			if(jQuery("#pf_"+j).length>0)
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
				
				var email = jQuery.trim(jQuery("#em_"+j).val());
				if(!validate_email(email))
				{
					alert("Check Email Format");
					return; 
				} 
				
				var gender = 0
				if (jQuery("#male_"+j).attr("checked")=='checked') {
					gender = 0;
				} else
				{
					gender = 1;
				}
				//if(gender=="")
				//{
					//alert("Gender should not be empty");
					//return; 
				//} 
				
				var phone = jQuery.trim(jQuery("#ph_"+j).val());
				if(phone==""||isNaN(phone))
				{
					alert("check phone format");
					return; 
				} 
				
				var comments = jQuery.trim(jQuery("#cm_"+j).val());
				
				var pickup_p = jQuery.trim(jQuery("#pickup_"+j).val());
				if(pickup_p=="")
				{
					alert("pick up should not be empty");
					return; 
				}
				

				var voucher = jQuery.trim(jQuery("#vc_"+j).val());
				if(voucher!="")
				{
					if(!validate_voucher(voucher))
					{
						alert("Check Voucher Format");						
					    return;	
					}
					
					jQuery.post("./booking.htm", "check_voucher=1&vcode="+voucher+"&groupid="+groupid, function(data) {

			        	if(data.result=="0")
			        	{
			        		alert("Voucher invalid for this group");
			        	
			        	} else if(data.result=="1")
			            {
			        		alert("此优惠券的使用次数已被用完了！");
			        	} else
			    		{
			        		//jQuery("#vcv_"+index).val(data.values);
			    			//alert("This voucher could be used as: " + data.values + " pounds but must be confirmed before " + data.dates);
			    		}
			        	
			    	});
				}
				
				var pd_point = parseInt(jQuery.trim(jQuery("#pt_"+j).val()));

				if(isNaN(pd_point)||pd_point%100!=0||pd_point>groupmaxcredit)
				{
					alert("积分必须为100的整数倍且小于等于该团最大积分限制");
					return; 
				}
				
				
				var room = jQuery.trim(jQuery("#rm_"+j).val());

				jsondata.push({"fn":fn, "ln":ln, "cn":cn, "email":email, "gender":gender, "phone":phone, "comments":comments, "pickup":pickup_p,"room":room, "pd_point":pd_point, "voucher":voucher, "paymethod":pay_method});
			}
		}
        // Post form to server
        //postForm(event);
    	//var jsondata = [];
    	//var username = jQuery("#login_form_username").val();
    	//var password = jQuery("#login_form_password").val();
    	//alert(jsondata)
    	//jsondata.push({"username": username, "password": password});
    	var rd = jQuery("#road").val();
    	if(rd == "$road") rd = ""; 
    	var ct = jQuery("#city").val();
    	if(ct == "$city") ct = "";
    	var ot = jQuery("#other").val();
    	if(ot == "$other") ot = "";
    	var pc = jQuery("#postcode").val();
    	if(pc == "$postcode") pc = "";
    	var pt = jQuery("#post").val();
    	if(pt == "$post") pt = "0";
    	
        jQuery.post("./booking.htm", 
        		"external_reserve=1&jsondata="+JSON.stringify(jsondata)+"&bt="+bt
        		+"&gid="+groupid+"&rd="+rd+"&ct="+ct+"&ot="+ot
        		+"&pc="+pc+"&pt="+pt, function(data) {
        	//alert(jsondata[0].gender);
        	jQuery("#booking_result").html(data.message);
//        	alert(pay_method);
//        	alert(data);
//        	alert(data.result);
//        	alert(data.message);
        	jQuery("#data_page").hide();
		    jQuery("#pay_page").hide();
		    jQuery("#final_page").hide();
		    jQuery("#result_page").show();
		    
//		    alert(data.message);
//		    alert(data.groupname);
//        	alert(data.message);
//        	alert(data.topy);
		    
		    var paypal = 
				"<form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\">"+
				"<input type=\"hidden\" name=\"cmd\" value=\"_xclick\">"+
				"<input type=\"hidden\" name=\"business\" value=\"justin@hinotravel.com\">"+
				"<input type=\"hidden\" name=\"lc\" value=\"GB\">"+
				"<input type=\"hidden\" name=\"item_name\" value=\""+data.groupname+": "+data.message+"\">"+
				"<input type=\"hidden\" name=\"item_number\" value=\""+data.message+"\">"+
				"<input type=\"hidden\" name=\"amount\" value=\""+data.topy+"\">"+
				"<input type=\"hidden\" name=\"currency_code\" value=\"GBP\">"+
				"<input type=\"hidden\" name=\"button_subtype\" value=\"services\">"+
				"<input type=\"hidden\" name=\"no_note\" value=\"0\">"+
				"<input type=\"hidden\" name=\"cn\" value=\"Add special instructions to the seller\">"+
				"<input type=\"hidden\" name=\"no_shipping\" value=\"1\">"+
				"<input type=\"hidden\" name=\"rm\" value=\"1\">"+
				"<input type=\"hidden\" name=\"return\" value=\"http://www.hinotravel.com\">"+
				"<input type=\"hidden\" name=\"cancel_return\" value=\"http://www.hinotravel.com\">"+
				"<input type=\"hidden\" name=\"shipping\" value=\"0.00\">"+
				"<input type=\"hidden\" name=\"bn\" value=\"PP-BuyNowBF:btn_paynowCC_LG.gif:NonHosted\">"+
				"<input type=\"image\" src=\"../images/btn_buynowCC_LG.gif\" border=\"0\" name=\"submit\" alt=\"PayPal - The safer, easier way to pay online.\">"+
				"<img alt=\"\" border=\"0\" src=\"https://www.paypalobjects.com/WEBSCR-640-20110429-1/en_GB/i/scr/pixel.gif\" width=\"1\" height=\"1\">"+
				"</form>";
//		    
//		    alert(pay_method);
//		    alert(data.result==0);

			if(pay_method==2&&data.result==0)
			{
				jQuery("#paypal_area").html(paypal);
			}
			if(pay_method==4&&data.result==0)
			{
//				alert(data.result==0);
				// test "<form method=\"post\" action=\"https://mdepayments.epdq.co.uk/ncol/test/orderstandard.asp\" id=form1 name=form1>" +
				// prod "<form method=\"post\" action=\"https://payments.epdq.co.uk/ncol/prod/orderstandard.asp\" id=form1 name=form1>" +
				var epdq_pay_detail = 
					"<form method=\"post\" action=\"https://payments.epdq.co.uk/ncol/prod/orderstandard.asp\" id=form1 name=form1>" +
					"<input type=\"hidden\" name=\"PSPID\" value=\"epdq1010898\">"+
					"<input type=\"hidden\" name=\"ORDERID\" value=\""+ data.message +"\">"+
					"<input type=\"hidden\" name=\"AMOUNT\" value=\""+ data.topy * 100 +"\">"+
					"<input type=\"hidden\" name=\"CURRENCY\" value=\"GBP\">"+
					"<input type=\"hidden\" name=\"LANGUAGE\" value=\"en_US\">"+
					"<input type=\"hidden\" name=\"CN\" value=\"\">"+
					"<input type=\"hidden\" name=\"EMAIL\" value=\"\">"+
					"<input type=\"hidden\" name=\"OWNERZIP\" value=\"\">"+
					"<input type=\"hidden\" name=\"OWNERADDRESS\" value=\"\">"+
					"<input type=\"hidden\" name=\"OWNERCTY\" value=\"\">"+
					"<input type=\"hidden\" name=\"OWNERTOWN\" value=\"\">"+
					"<input type=\"hidden\" name=\"OWNERTELNO\" value=\"\">"+
					"<input type=\"hidden\" name=\"SHASIGN\" value=\"\">"+
					"<input type=\"hidden\" name=\"TITLE\" value=\""+ data.groupname_en +"\">"+
					"<input type=\"hidden\" name=\"BGCOLOR\" value=\"\">"+
					"<input type=\"hidden\" name=\"TXTCOLOR\" value=\"\">"+
					"<input type=\"hidden\" name=\"TBLBGCOLOR\" value=\"\">"+
					"<input type=\"hidden\" name=\"TBLTXTCOLOR\" value=\"\">"+
					"<input type=\"hidden\" name=\"BUTTONBGCOLOR\" value=\"\">"+
					"<input type=\"hidden\" name=\"BUTTONTXTCOLOR\" value=\"\">"+
					"<input type=\"hidden\" name=\"LOGO\" value=\"\">"+
					"<input type=\"hidden\" name=\"FONTTYPE\" value=\"\">"+
					"<input type=\"hidden\" name=\"ACCEPTURL\" value=\"http://www.hinotravel.com\">"+
					"<input type=\"hidden\" name=\"DECLINEURL\" value=\"http://www.hinotravel.com\">"+
					"<input type=\"hidden\" name=\"EXCEPTIONURL\" value=\"http://www.hinotravel.com\">"+
					"<input type=\"hidden\" name=\"CANCELURL\" value=\"http://www.hinotravel.com\">"+
					"<input type=\"submit\" value=\"\" id=submit2 name=submit2>" + 
					"<input type=\"image\" src=\"../images/barclaycard_pay.png\" title=\"网银在线支付\" border=\"0\" name=\"submit\" alt=\"网银在线支付\">"+
					"</form>"
//					alert(epdq_pay_detail);
					jQuery("#epdq_pay_detail").html(epdq_pay_detail);		
			}
//			alert(pay_method);
//			alert(data.result);
    		//window.location = "./sales_rep_booking_record.htm";
			
			
    	});
        // Prevent the default browser behavior of navigating to the link
        return false;
    });
    
    jQuery('a[id*="del_"]').live('click', function() {
    	
    	var partsArray = jQuery(this).attr("id").split('_');
    	var index = partsArray[1];
    	//alert(index);
    	del_div(index);
    	
	});

    jQuery('input[id*="vc_"]').live('blur',function() {
    	
    	var partsArray = jQuery(this).attr("id").split('_');
    	var index = partsArray[1];
    	//alert(index);
    	var vcode = jQuery.trim(jQuery("#vc_"+index).val());
    	//var groupid = jQuery("#idfield").val();
		if(!validate_voucher(vcode))
		{
			alert("Check Voucher Format");
			return; 
		} 
		 
		
		jQuery.post("./booking.htm", "check_voucher=1&vcode="+vcode+"&groupid="+groupid, function(data) {

        	if(data.result=="0")
        	{
        		alert("Voucher invalid");
        	}else if(data.result=="1")
            {
        		alert("此优惠券的使用次数已被用完了！");
        	} else
    		{
        		jQuery("#vcv_"+index).val(data.values);
    			//alert("This voucher could be used as: " + data.values + " pounds for "+data.usage+" times but must be confirmed before " + data.dates);
        		alert("This voucher could be used as: " + data.values + " pounds for 1 time but must be confirmed before " + data.dates);
    		}
        	
    	});
			
	});
       
    // 初始化接车地点选项
	for(var opindex=0; opindex<pickup.length; opindex++) {
		jQuery("#pickup_0").append("<option value='"+pickup[opindex]+"'>"+pickup[opindex]+"</option>"); 
	}
});

function del_div(index)
{
	jQuery("#pf_"+index).remove();	
}


function make_div()
{
	//alert(add_count);
	var add_div = "<div class='pay_frm' id='pf_"+add_count+"'>" + 
           	  "<div class='number'><span class='t'>第" + add_count+ "位</span> <span class='x'><a href='#' id='del_"+add_count+"'><img src='images/X.png' /></a></span></div>" + 
                "<form id='form1' name='form1' method='post' action=''>" +
                "<table border='0' align='center' cellpadding='0' cellspacing='0'>" +
                  "<tr>" +
                    "<td align='right' class='text'>姓 &nbsp;&nbsp;名：</td>" +
                    "<td class='text'><input type='text' name='textfield2' id='ln_" + add_count+ "' class='pay_input w2'/> " +
                    "<input type='text' name='textfield3' id='fn_" + add_count+ "' class='pay_input w2'/></td>" +
                  "</tr>" +
                  "<tr>" +
                    "<td align='right' class='text'>中文名：</td>" +
                    "<td class='text'><input type='text' name='textfield4' id='cn_" + add_count+ "' class='pay_input w1'/></td>" +
                  "</tr>" +
                  "<tr>" +
                    "<td align='right' class='text'>性 &nbsp;&nbsp;别：</td>" +
                    "<td class='text'>" +
                      "<input name='gd_" + add_count+ "' type='radio' id='male_"+ add_count+"' />" +
                      "男" +
                      "<input type='radio' name='gd_" + add_count+ "' id='female_"+ add_count+"' />" +
                      "女" +
                    "</td>" +
                  "</tr>" +
                  "<tr>" +
                    "<td align='right' class='text'>Email：</td>" +
                    "<td class='text'><input type='text' id='em_" + add_count+ "' class='pay_input w1'/></td>" +
                  "</tr>" +
                  "<tr>" +
                    "<td align='right' class='text'>电 &nbsp;&nbsp;话：</td>" +
                    "<td class='text'><input type='text' id='ph_" + add_count+ "' class='pay_input w1'/></td>" +
                  "</tr>" +
                  "<tr>" +
                    "<td align='right' class='text'>接车点：</td>" +
                    "<td class='text'><label>" +
                    "<select id='pickup_" + add_count+ "' class='w5'>" +
                    "</select>" +
                    "</label></td>" +
                  "</tr>" +
                  "<tr>" +
                    "<td align='right' class='text'>住 &nbsp;&nbsp;宿：</td>" +
                    "<td class='text'><input type='text' id='cm_" + add_count+ "' class='pay_input w1'/></td>" +
                  "</tr>" +
                  "<tr>" +
                    "<td align='right' class='text'>其他需求</td>" +
                    "<td class='text'><input type='text' id='cm_" + add_count+ "' class='pay_input w1'/></td>" +
                  "</tr>" +
                  "<tr>" +
                    "<td align='right' class='text'>使用积分</td>" +
                    "<td class='text'><input type='text' id='pt_" + add_count+ "' value=0 class='pay_input w3'/></td>" +
                  "</tr>" +
                  "<tr>" +
                    "<td align='right' class='text'>使用优惠券</td>" +
                    "<td class='text'><input type='text' id='vc_"+add_count+"' class='pay_input w3'/>抵值<input type='text' id='vcv_"+add_count+"' disabled='disabled' value=0 class='pay_input w4'/></td>" +
                  "</tr>" +
              "</table>" +
            "</form>" +
           "</div>" +
        "</div>";
	
	
	jQuery("#add_customer").append(add_div);
	for(var opindex=0; opindex<pickup.length; opindex++) {
		jQuery("#pickup_"+add_count).append("<option value='"+pickup[opindex]+"'>"+pickup[opindex]+"</option>"); 
	}
	add_count++;
	if(bt!=0)
	{
		jQuery("input[id*='pt']").attr("disabled",true);
		jQuery("input[id*='vc']").attr("disabled",true);
	}
	//alert(add_count);
}

function validate_email(email) {
   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
   return reg.test(email);
}

function validate_voucher(vcode) {
   var reg = /^[A-Z0-9]{16}$/;   
   return reg.test(vcode);
}

function calc_total_price()
{
	
}