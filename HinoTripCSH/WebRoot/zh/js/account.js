jQuery(document).ready(function() {
	
	$(function ShowContent(i) {
		jQuery("#myac_1").hide();
		jQuery("#myac_2").hide();
		jQuery("#myac_3").hide();
		jQuery("#myac_4").hide();
		jQuery("#myac_5").hide();
		
		jQuery("#myac_"+i).show();
        return false;
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
//				if (data.pm=="2")
//				{
					//alert(bookingref + data.topy);	
					if(data.status=="0")
					{	
						jQuery("#pay_detail").html("您的还可以使用 Paypal 支付"+paypal);
					}
					else
					{
						jQuery("#pay_detail").html("");
					}
//				} else if(data.pm=="1")
//				{
//					jQuery("#pay_detail").html("您的支付方式是 银行支付");
//				} else if(data.pm=="3") {
//					jQuery("#pay_detail").html("您的支付方式是 前台支付");
//				}
				
					
				// test "<form method=\"post\" action=\"https://mdepayments.epdq.co.uk/ncol/test/orderstandard.asp\" id=form1 name=form1>" +
				// prod "<form method=\"post\" action=\"https://payments.epdq.co.uk/ncol/prod/orderstandard.asp\" id=form1 name=form1>" +
						
				var epdq_pay_detail = 
					"<form method=\"post\" action=\"https://payments.epdq.co.uk/ncol/prod/orderstandard.asp\" id=form1 name=form1>" +
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
					"<input type=\"hidden\" name=\"TITLE\" value=\""+data.groupname+
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
					"</form>"
					//alert(epdq_pay_detail);
					jQuery("#epdq_pay_detail").html("您的还可以使用 barclaycard 支付"+epdq_pay_detail);
        		//jQuery("#vcv_"+index).val(data.values);
    			//alert("This voucher could be used as: " + data.values + " pounds but must be confirmed before " + data.dates);
    		}
        	
    	});

        return false;
    });
});