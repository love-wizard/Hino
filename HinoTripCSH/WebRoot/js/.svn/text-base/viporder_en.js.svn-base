function vip_order_init(){

	// 使href为#的链接无效
	jQuery("a[href=#]").live('click', function(event){
		return false;
	});

	// 点击下载协议后显示付款方式说明表单
	/*jQuery("#agreementlink").click(
		function() {
			jQuery("#paymethodsel option[value='-1']").text("选择付款方式");
			jQuery("#paymethodsel").attr("disabled",false);
			jQuery("#paymethoddetail").attr("disabled",false);
			jQuery("#paymethoddetail").attr("readonly",true);
		}
	);*/
	
	// 显示付款方式的详细信息
	jQuery("#paymethodsel").change(
		function(){
			//alert(jQuery("#paymethodsel option:selected").text()+" "+jQuery("#paymethodsel option:selected").val());
			var selectedPayMethod = jQuery("#paymethodsel option:selected").val();
			
			var payMethod1 = "\r\n　　Please make band transfer to the following receiver:\r\n"+
							"　Barclays Business Debit: Hino Travel Limited\r\n"+
							"　Sort Code: 20-63-25\r\n"+
							"　Account number: 33250482\r\n"+
							"　　After transer, please send an email to \"info@hinotravel.com\" telling us payer name, the group name you choose, depart date, transfer time, transfer amount, and booking reference number. (e.g. james bond, 3 people, England 3 days, 12 Sep., 15 Aug. transfered £600, Ref No. 201108150821226669P0)\r\n"+
							"\r\n"+
							"　　Or directly call us at 07803175369\r\n"+
							"\r\n"+
							"　　Reply email will be sent within 2 working days.\r\n"+
							"　　Please print e-ticket in the reply email and take it with you. Thank you very much!\r\n"+
							"\r\n"+
							"　　Remark: We will contact you if any changes made to your booking.\r\n";
			var payMethod2 = "\r\n　　Google Checkout is a free service. There is no extra fee by using Google Checkout.\r\n"+
							"\r\n　　By choose this payment, please click Google Checkout button to make payment in Google Checkout official website.";
			var payMethod3 = "\r\n　　Please visit our company and pay by cash: Days Building, 17-31 Wollaton Street, Nottingham, NG1 5FW. (near Royal Theater)\r\n"+
							"　　This payment is cash only, thank you very much.";


			switch(selectedPayMethod) {
				case '1':
					jQuery("#paymethoddetail").html(payMethod1);break;
				case '2':
					jQuery("#paymethoddetail").html(payMethod2);break;
				case '3':
					jQuery("#paymethoddetail").html(payMethod3);break;
				default:
					jQuery("#paymethoddetail").html(" ");break;
			}
		}
	);

	// 隐藏升级结果div
	jQuery("#order_div").css("display","block");
	jQuery("#order_result").css("display","none");

	// 未下载协议之前付款方式表单不可用
	/*jQuery("#paymethodsel").attr("disabled",true);
	jQuery("#paymethoddetail").attr("disabled",true);*/

	// 提交表单事件绑定
	jQuery("#vip_submit").click(function(){
		if(validator()) {
			jQuery("#order_div").css("display","none");
			jQuery("#order_result").css("display","block");
		}
		return false;
	});
	jQuery("#vip_calcel").click(function(){
		window.location = './index.htm'
		return false;
	});
}

/**
 * 表单验证，如验证失败，提示当前显示表单中的问题并返回false；否则做显示VIP升级结果
 * @param {Object} index
 */
function validator(index) {
	var errormsg = "";
	var delivery = jQuery("#delivery").val();
	var name = jQuery("#name").val();
	var phone = jQuery("#phone").val();
	var address = jQuery("#address").val();
	var order_method = jQuery("#paymethodsel").val();

	if(order_method<1 || order_method>3)
		errormsg = errormsg+"Please choose payment method\n";
	if(delivery=="")
		errormsg = errormsg+"Please choose delivery method\n";
	if(name=="")
		errormsg = errormsg+"Please enter VIP name\n";
	if(phone=="")
		errormsg = errormsg+"Please enter phone\n";
	if(delivery==0 && address=="")
		errormsg = errormsg+"Please enter post address\n";
		
	if(errormsg != "") {
		alert(errormsg);
		return false;
	} else {
		// ajax升级VIP
		var customerNum = jQuery("#morecustomer option:selected").val();
		if(customerNum == "") customerNum=1;
		var jsondata = [];
		jsondata.push({"delivery":delivery, "name":name, "phone":phone, "address":address, "order_method":order_method});

		jQuery.post(
			"./vip_order.htm", 
			"vip_order=1"+"&jsondata="+JSON.stringify(jsondata), 
			function(data) {
    			alert("VIP upgrading applied");
    			jQuery("#refno").html(data);
    			
    			// 如果Google Checkout，显示按钮
    			if(jQuery("#paymethodsel option:selected").val() == 2) {
    				var googlecheckout = "<form method=\"POST\" action=\"https://checkout.google.com/api/checkout/v2/checkoutForm/Merchant/876069353760388\" accept-charset=\"utf-8\">"+
    					"<input type=\"hidden\" name=\"item_name_1\" value=\"VIP upgrading\"/>"+
					  	"<input type=\"hidden\" name=\"item_description_1\" value=\""+email+"\"/>"+
					  	"<input type=\"hidden\" name=\"item_quantity_1\" value=\""+1+"\"/>"+
					  	"<input type=\"hidden\" name=\"item_price_1\" value=\"10\"/>"+
					  	"<input type=\"hidden\" name=\"item_currency_1\" value=\"GBP\"/>"+
					  	"<input type=\"hidden\" name=\"_charset_\"/>"+
					  	"<input type=\"image\" name=\"Google Checkout\" alt=\"Fast checkout through Google\" src=\"http://checkout.google.com/buttons/checkout.gif?merchant_id=876069353760388&w=180&h=46&style=trans&variant=text&loc=en_GB\" height=\"46\" width=\"180\"/>"+
						"</form>";
    				
    				jQuery("#pay").html(googlecheckout);
    			}
    			else
    				jQuery("#pay").html(jQuery("#paymethodsel option:selected").text());
			}
		);
		return true;
	}
}

jQuery(document).ready(vip_order_init);