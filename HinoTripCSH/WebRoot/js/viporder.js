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
			
			var payMethod1 = "\r\n　　请根据您选择的旅游线路，团次价格，报名人数和附加服务的情况，通过银行柜台汇款或者网上银行转帐到Hino travel的账户中。（请尽量选择同行转账以提高到账时间方便您出行的确认）\r\n"+
							"　Barclays Business Debit: Hino Travel Limited\r\n"+
							"　Sort Code: 20-63-25\r\n"+
							"　Account number: 33250482\r\n"+
							"　　请务必在付款当天发送邮件到： info@hinotravel.com ，告知：付款人姓名，您参团的路线名称和时间，支付时间，支付金额，付款方式和您留下的reference(团号+姓名)（例如：张晓 明，支付3人9月12日苏格兰3日游，8月15日今天，通过Natwest银行网上转账方式支付600英镑到Hino Travel账户，留下的reference是Scotland3 Zhang Xiaoming）,并填写好顾客信息表一并发至邮箱: info@hinotravel.com\r\n"+
							"\r\n"+
							"　　或拨打我们的工作电话07803175369 通知我们员工. 以方便我们及时为您查账确认\r\n"+
							"\r\n"+
							"　　公司邮件回复（2个工作日内完成）\r\n"+
							"　　公司会在收到您的付款通知邮件和顾客信息表之后的48个工作小时之内，出具电子票到您的邮箱内。您需打印并携带电子票，到选定上车地点上车，电子票将是您参团的凭证。感谢您的理解和配合！\r\n"+
							"\r\n"+
							"　　备注：报名以到款日期为序，满团后到款的游客公司将及时与您联系协商转团或者退款退团。\r\n";
			var payMethod2 = "\r\n　　Google Checkout 是完全免费的服务！您只需要以卖家网站上使用的货币支付所购商品的费用即可，其中包括所有适用的税金和运费。Google Checkout 购物服务不会另外收取费用。\r\n"+
							"\r\n　　如选择此项付款方式，完成预订时请点击Google Checkout标准按钮进入Google Checkout官网支付系统进行后续操作。";
			var payMethod3 = "\r\n　　公司地址：Days Building, 17-31 Wollaton Street, Nottingham, NG1 5FW. (near Royal Theater)\r\n"+
							"　　公司目前只接受现金付款，多谢您的配合";


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
		errormsg = errormsg+"请选择付款方式\n";
	if(delivery=="")
		errormsg = errormsg+"请选择会员卡交付方式\n";
	if(name=="")
		errormsg = errormsg+"请填写持卡人姓名\n";
	if(phone=="")
		errormsg = errormsg+"请填写电话号码\n";
	if(delivery==0 && address=="")
		errormsg = errormsg+"请填写邮寄地址\n";
		
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
    			alert("升级VIP提交完成");
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