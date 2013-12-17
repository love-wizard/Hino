jQuery(document).ready(function() {
	$('#survey').ajaxForm(function() {
    });
	jQuery(".tianxie").click(function(event){
		var gid = $(this).attr("groupid"); 
//		alert(gid);
		jQuery("#group_id").val(gid);
		$('#survey').clearForm();
		jQuery("#survey_div").show();
	});
	
	jQuery(".commit_survey").click(function(event){

//		$("#survey").submit(function(e){
			var gid = jQuery("#group_id").val();
			var jsondata = $('#survey').formSerialize();
			jsondata = "gid=" + gid + "&" + jsondata;
//			alert(jsondata);
			
			jQuery.post("./myaccount.htm?commit_survey=1", jsondata, function(data) {
	        	if(data.result=="0")
	        	{
	        		alert("提交成功！");
	        		$('#survey').clearForm();
	        		location.reload();
	        	}else
	    		{
	        		alert("提交失败！"+data.data);
	    		}
	        	
	    	});
//		});
	});
	
	
	jQuery(".search-btn").click(function(event){
		var dest =  jQuery(this).siblings(".search-bar").val();
		if (jQuery.trim(dest) == "") return;
		
		jQuery("#txt_s_k").val(dest);
		jQuery("#frm_s").attr("action", "./search_result.htm?idx=1&st=2&dcode=-1");
		jQuery("#frm_s").submit();
	});
	
	jQuery(".search-btn").submit(function(event){
		
		jQuery(this).click();
	});
	
	jQuery(".show-bookingref").mouseover(function(event){
		var bref = $(this).attr("bookingref"); 
		$(this).parent().parent().parent().find(".text-bookingref").html(bref);
	});
	
	jQuery(".user_l .myac").click(function(event){
		jQuery(".myac").each(function(index){
			var thisId = jQuery(this).attr("id");
			var targetId = thisId.replace("action_", "");
			jQuery("#" + targetId).hide();
			jQuery(this).removeClass("nav-highlight");
			jQuery(this).attr("style", "");
			
			var cname = jQuery(this).siblings("img").attr("class");
			cname = cname.replace("_100", "");
			jQuery(this).siblings("img").removeClass(cname + "_100");
			jQuery(this).siblings("img").addClass(cname);
		});
		
		if (jQuery("#myac_5").is(":visible")) {
			jQuery("#myac_5").hide();
		}
		
		var tcname = jQuery(this).siblings("img").attr("class");
		tcname = tcname.replace("_100", "");
		jQuery(this).siblings("img").removeClass(tcname);
		jQuery(this).siblings("img").addClass(tcname + "_100");
		
		jQuery(this).attr("style", "color:#0d81c8");
		jQuery(this).addClass("nav-highlight");
		var thisId2 = jQuery(this).attr("id");
		var targetId2 = thisId2.replace("action_", "");
		jQuery("#" + targetId2).show();
	});	
	
	jQuery("#upgrade_vip").click(function(event){	
		var message = "请联系：03339009888（英国免费电话）"+"\n"+
						"   或海诺客服QQ: 2355254129" +"\n"+
						"     办理VIP卡，感谢支持！";
		alert(message);
	});
	
	
	jQuery(".view_booking").click(function(event){
		var bookingref = jQuery(this).attr("name");
		
		jQuery.post("./myaccount.htm", "view_booking=1&bookingref="+bookingref, function(data) {

        	if(data.result=="1")
        	{
        		alert("Voucher invalid for this group");
        		return;
        		
        	} else
    		{
        		jQuery("#myac_1").hide();
				jQuery("#myac_2").hide();
				jQuery("#myac_3").hide();
				jQuery("#myac_4").hide();
				jQuery("#myac_5").show();
				
				jQuery("#booking_detail").html(data.bookingsum);
				
				
				
				var paypal = 
					"<form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\">"+
					"<input type=\"hidden\" name=\"cmd\" value=\"_xclick\">"+
					"<input type=\"hidden\" name=\"business\" value=\"justin@hinotravel.com\">"+
					"<input type=\"hidden\" name=\"lc\" value=\"GB\">"+
					"<input type=\"hidden\" name=\"item_name\" value=\""+data.groupname+": "+bookingref+"\">"+
					"<input type=\"hidden\" name=\"item_number\" value=\""+bookingref+"\">"+
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
		
				//Ken Chen 2012/12/28 TD#80 对未确认的订单可用使用Paypal支付
				//Ken Chen 2013-04-01 TD#130 境外团的支付方式里面，请把支付方式3 paypal去掉，境外团不能用paypal支付
				jQuery("#pay_detail").hide();
				if(data.status=="0" && data.overseaRoute == "0")
				{	jQuery("#pay_detail").show();
					jQuery("#pay_detail").html("您的还可以使用 Paypal 支付"+paypal);
				}

//				if (data.pm=="2")
//				{
//					//alert(bookingref + data.topy);	
//					jQuery("#pay_detail").html("您的支付方式是 Paypal 支付"+paypal);
//				} else if(data.pm=="1")
//				{
//					jQuery("#pay_detail").html("您的支付方式是 银行支付");
//				} else if(data.pm=="3") {
//					jQuery("#pay_detail").html("您的支付方式是 前台支付");
//				}
//				
				
				// test "<form method=\"post\" action=\"https://mdepayments.epdq.co.uk/ncol/test/orderstandard.asp\" id=form1 name=form1>" +
				// prod "<form method=\"post\" action=\"https://payments.epdq.co.uk/ncol/prod/orderstandard.asp\" id=form1 name=form1>" +
				
				var epdq_pay_detail = 
					"<form method=\"post\" action=\"https://mdepayments.epdq.co.uk/ncol/test/orderstandard.asp\" id=form1 name=form1>" +
					"<input type=\"hidden\" name=\"PSPID\" value=\"epdq1010898\">"+
					"<input type=\"hidden\" name=\"ORDERID\" value=\""+ bookingref +"\">"+
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
					"<input type=\"hidden\" name=\"ACCEPTURL\" value=\"http://www.hinotravel.com/zh/route_detail.htm?routeid=98\">"+
					"<input type=\"hidden\" name=\"DECLINEURL\" value=\"http://www.hinotravel.com/zh/route_detail.htm?routeid=99\">"+
					"<input type=\"hidden\" name=\"EXCEPTIONURL\" value=\"http://www.hinotravel.com/zh/route_detail.htm?routeid=100\">"+
					"<input type=\"hidden\" name=\"CANCELURL\" value=\"http://www.hinotravel.com/zh/route_detail.htm?routeid=101\">"+
					"<input type=\"submit\" value=\"\" id=submit2 name=submit2>" + 
					"<input type=\"image\" src=\"../images/barclaycard_pay.png\" title=\"网银在线支付\" border=\"0\" name=\"submit\" alt=\"网银在线支付\">"+
					"</form>"
					//alert(epdq_pay_detail);
					jQuery("#epdq_pay_detail").html("您的还可以使用网银在线支付"+epdq_pay_detail);
    		}
        	
    	});

        return false;
    });
	
	$('.immediate_pay').click(function(){
		var bookingref = jQuery(this).parent().parent().attr("bookingref");
		var pmval = jQuery(this).attr("pmval");

		$.confirm({
			'title'		: '选择付款方式',
			'pmval'		: pmval,
			'buttons'	: {
				'立即付款'	: {
					'class'	: 'blue',
					'action': function(){
						var pm = $('#confirmForm input[name="paymeth"]:checked').val();
						
						if(pm == null || pm == undefined) return false;
						
						$.confirm.hide();

						if(pmval == pm && pm == 1) {
							alert("您的支付方式是 银行转账 (sort code:206325 account number: 33250482)");
							
						} else if(pmval == pm && pm == 3) {
							alert("您的支付方式是 前台支付");
							
						} else {
							
							immediate_pay(bookingref, pm);
						}					
					}
				},
				'取消'	: {
					'class'	: 'gray',
					'action': function(){
						$.confirm.hide();
					}	// Nothing to do in this case. You can as well omit the action property.
				}
			}
		});
		
	});
	
	function immediate_pay(bookingref, pmval){
		
		jQuery.post("./myaccount.htm", "view_booking=1&bookingref="+bookingref+"&pmval="+pmval, function(data) {

        	if(data.result=="1")
        	{
        		alert("Voucher invalid for this group");
        		return;
        		
        	} else
    		{
        		
				if (data.pm=="2")
				{
					jQuery("#frmPaypal input[name='item_name']").val(data.groupname+": "+bookingref);
					jQuery("#frmPaypal input[name='item_number']").val(data.groupname+": "+bookingref);
					jQuery("#frmPaypal input[name='amount']").val(data.topy);
					
					jQuery("#frmPaypal").submit();
				} else if(data.pm=="1")
				{
					alert("您的支付方式是 银行转账 (sort code:206325 account number: 33250482)");
				} else if(data.pm=="3") {
					alert("您的支付方式是 前台支付");
				} else if(data.pm == "4") {
					
					jQuery("#frmEpdq input[name='ORDERID']").val(bookingref);
					jQuery("#frmEpdq input[name='AMOUNT']").val(data.topy * 100);
					jQuery("#frmEpdq input[name='TITLE']").val(data.groupname_en);
					
					jQuery("#frmEpdq").submit();
				}
    		}
        	
    	});

        return false;
    }
	
	var prevDate = "";
	
	$(".gc-innercell").click(function(event){

		var curDate = $(this).attr("ym");
		
		if(prevDate != curDate) {
			
			jQuery.ajax({
				url: "./myaccount.htm",
				type: "POST",
				data: {
					"ajax_groups": "1", "date": curDate
				},
				dataType: "JSON",
				success: function(data, textStatus) {
					
					var ymd = curDate.split("-");
					
					$(".latest-groups .cat-title span").html(ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日");
					
					if(data.groups.length == 0){
						// to do
					}
					
					$(".latest-groups .list ul li").remove();
					
					jQuery.each(data.groups, function(idx, groups) {
						var itm = "";
						itm += "<li>";
						itm += 		"<div class='pic'>";
						itm += 			"<a href='./go_view.htm?gid=" + groups['id'] + "'>";
						itm += 				"<img width='160px' height='96px' src='" + groups['tu'] + "' />";
						itm += 			"</a>";
						itm += 		"</div>";
						itm += 		"<div class='title' title='" + groups['n'] + "'>" + groups.sn + "</div>";
						itm += "</li>";

						$(".latest-groups .list ul").append(itm);
					});
					
					prevDate = curDate;
				}
			});
		} 
	});
	
});