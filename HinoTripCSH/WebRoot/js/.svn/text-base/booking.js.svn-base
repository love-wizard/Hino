function booking_init(){

	// 表单div的宽度
	var conInnerConWidth = jQuery(".innerCon").width();
	// 表单div的个数
	var conSize = jQuery(".innerCon").size();
	// 进度条的高度
	var tabHeight = jQuery(".tab span").height();
	
	// 当前进度的索引
	if(!window.cur) window.cur=0;
	
	/* 如需进度条点击切换表单div，则开放此段代码
	jQuery(".tab span").click(function(){
		var index = jQuery(".tab span").index(this);
	   slide(conInnerConWidth, tabHeight, index);
		return false;
	});
	*/
	
	// 上一步按钮
	jQuery(".prev").click(function(){
	   if(window.cur>0) slide(conInnerConWidth, tabHeight, window.cur-1);
		return false;
	});
	
	// 下一步按钮
	jQuery(".next").click(function(){
	   if(window.cur<conSize-1) {
		   if(validator(window.cur)) {
		   		slide(conInnerConWidth, tabHeight, window.cur+1);
		   }
	   }
		return false;
	});

	// 使href为#的链接无效
	jQuery("a[href=#]").live('click', function(event){
		return false;
	});

	// 为同行者下拉框绑定事件，用于显示同行者的表单div
	jQuery("#morecustomer").change(
		function(){
			//alert(jQuery("#morecustomer option:selected").text()+" "+jQuery("#morecustomer option:selected").val());
			var selectedCusNum = jQuery("#morecustomer option:selected").val();
			for(var index=2; index<=4; index++){
				if(index <= selectedCusNum)
					jQuery("#cus_"+index).css("display","");
				else
					jQuery("#cus_"+index).css("display","none");
			}
			
			// 伸缩背景
			switch(selectedCusNum) {
				case '2':
					jQuery(".full_form_mid").css("height", "580px");break;
				case '3':
					jQuery(".full_form_mid").css("height", "780px");break;
				case '4':
					jQuery(".full_form_mid").css("height", "980px");break;
				default:
					jQuery(".full_form_mid").css("height", "420px");break;
			}
			
			//计算最多可用的积分
			groupmaxcredit *= selectedCusNum;
			
			// 隐藏同行者数量选择下拉框
			jQuery("#more").css("display","none");
		}
	);
	
	// 点击下载协议后显示付款方式说明表单
	jQuery("#agreementlink").click(
		function() {

			jQuery("#paymethodsel option[value='-1']").text("选择付款方式");
			jQuery("#paymethodsel").attr("disabled",false);
			jQuery("#paymethoddetail").attr("disabled",false);
			jQuery("#paymethoddetail").attr("readonly",true);
		   

			
		}
	);
	
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
			var payMethod2 = "\r\n　　PayPal是倍受全球商户和个人追捧的国际贸易支付工具，即时支付，即时到账，全中文操作界面。PayPal庞大的用户群可在全球190个国家交易使用。更大的好处是：完全免费注册！目前跨国交易超过90%的卖家和超过85%的买家认可并使用PayPal电子支付业务。\r\n";
							//"\r\n　　Google Checkout 是完全免费的服务！您只需要以卖家网站上使用的货币支付所购商品的费用即可，其中包括所有适用的税金和运费。Google Checkout 购物服务不会另外收取费用。如选择此项付款方式，完成预订时请点击Google Checkout标准按钮进入Google Checkout官网支付系统进行后续操作。";
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
	
	// 包含抵用积分重新计算总价
	jQuery("#calculator").click(
		function() {
			var usedCredit = jQuery("#credit_use").val();
			if(usedCredit == "") usedCredit = 0;
			if(usedCredit > groupmaxcredit){
				alert("抵用积分不可超过"+groupmaxcredit+"的最大限制");
				return;
			}
			if(usedCredit%100 != 0) {
				alert("抵用积分必须是100的整数倍，如100，200，300...");
				return;
			}
			if(usedCredit > credit){
				alert("您的积分不足"+usedCredit);
				return;
			}
			usedCredit = parseFloat(usedCredit/100);
			var customerNum = jQuery("#morecustomer option:selected").val();
			if(customerNum == "") customerNum = 1;
			var curCustomerPrice = (isvip == "true")?groupvipprice:singleprice;
			var totalPrice = (parseFloat(curCustomerPrice)+parseFloat(singleprice)*parseFloat(customerNum-1)-usedCredit).toFixed(2);
			//alert("singleprice "+singleprice+" "+typeof(singleprice)+" | customerNum "+customerNum+" "+typeof(customerNum)+" | usedCredit "+usedCredit+" "+typeof(usedCredit)+" | "+totalPrice);
			jQuery("#price_total").val(""+totalPrice);
		}	
	);
	
	// 隐藏同行者的表单div
	for(var index=2; index<=4; index++){
		jQuery("#cus_"+index).css("display","none");
	}
	
	// 设定报名信息背景图层初始高度
	jQuery(".full_form_mid").css("height", "420px");

	// 未下载协议之前付款方式表单不可用
	jQuery("#paymethodsel").attr("disabled",true);
	jQuery("#paymethoddetail").attr("disabled",true);

	// 初始化接车地点选项
	for(var pindex=1; pindex<=4; pindex++)
		for(var opindex=0; opindex<pickup.length; opindex++) {
			$("#pickup_"+pindex).append("<option value='"+pickup[opindex]+"'>"+pickup[opindex]+"</option>"); 
		}
}

/**
 * 表单div滑动
 * @param {Object} conInnerConWidth
 * @param {Object} tabHeight
 * @param {Object} index 要滑动到显示的表单div
 */
function slide(conInnerConWidth, tabHeight, index){
	// 所有的进度条移除current的CSS（当前链接文字颜色高亮）
	jQuery(".tab span").removeClass("current");
	// 目标进度条加载current的CSS
	jQuery(".tab span").eq(index).addClass("current");
	// 进度条背景上下移动以突出显示当前的进度
	jQuery(".tab").css("background-position","0 -"+index*tabHeight+"px");
	// 表单div滑动动画效果
	jQuery(".maskCon").animate({marginLeft:-index*conInnerConWidth+"px"});
	window.cur = index;
}

/**
 * 表单验证，如验证失败，提示当前显示表单中的问题并返回false；否则做下一步必要的初始化并返回true
 * @param {Object} index
 */
function validator(index) {
	var errormsg = "";

	switch(index) {
		case 0:
			// 判断个人信息是否填写完整。必填项：名，姓，接车地点，性别
			var msg = ["","您","第一位同行者","第二位同行者","第三位同行者"];
			var customerNum = jQuery("#morecustomer option:selected").val();
			if(customerNum == "") customerNum=1;
			for(var customer=1; customer<=customerNum; customer++) {
				if(jQuery("#fn_"+customer).val()=="" || jQuery("#ln_"+customer).val()=="" || 
						jQuery("#gender_"+customer).val()=="" || jQuery("#pickup_"+customer).val()==""
						|| (customer==1 && jQuery("#phone_1").val()=="" ))
					errormsg = errormsg+"请填写完整"+msg[customer]+"的个人信息\n";
			}
			break;
		case 1:
			// 判断付款方式是否选取
			if(jQuery("#paymethodsel").val() == "-1")
				errormsg = errormsg+"请选择付款方式";
			// 输出预订信息
			else {
				var usedCredit = jQuery("#credit_use").val();
				if(usedCredit == "") usedCredit = 0;
				usedCredit = parseFloat(usedCredit/100);
				var customerNum = jQuery("#morecustomer option:selected").val();
				if(customerNum == "") customerNum=1;
				jQuery("#ticketno").val(""+customerNum);
				jQuery("#price_ori").val(""+singleprice+" (VIP "+groupvipprice+")");
				jQuery("#credit_total").val(""+credit);
				var curCustomerPrice = (isvip == "true")?groupvipprice:singleprice;
				var totalPrice = (parseFloat(curCustomerPrice)+parseFloat(singleprice)*parseFloat(customerNum-1)-usedCredit).toFixed(2);
				jQuery("#price_total").val(""+totalPrice);
			}
			// 输出可用积分值
			jQuery("#max_credit").html(groupmaxcredit);
			break;
		case 2:
			// 检查抵用积分值是否符合要求
			var usedCredit = jQuery("#credit_use").val();
			if(usedCredit == "") usedCredit = 0;
			if(usedCredit > groupmaxcredit){
				errormsg = "抵用积分不可超过"+groupmaxcredit+"的最大限制";
			}
			if(usedCredit%100 != 0) {
				errormsg = "抵用积分必须是100的整数倍，如100，200，300...";
			}
			if(usedCredit > credit){
				errormsg = "您的积分不足"+usedCredit;
			}
			if(errormsg != "") {
				break;
			}
			var customerNum = jQuery("#morecustomer option:selected").val();
			if(customerNum == "") customerNum = 1;
			var curCustomerPrice = (isvip == "true")?groupvipprice:singleprice;
			var totalPrice = (parseFloat(curCustomerPrice)+parseFloat(singleprice)*parseFloat(customerNum-1)-parseFloat(usedCredit/100)).toFixed(2);
			//alert("singleprice "+singleprice+" "+typeof(singleprice)+" | customerNum "+customerNum+" "+typeof(customerNum)+" | usedCredit "+usedCredit+" "+typeof(usedCredit)+" | "+totalPrice);

			// ajax预订
			var customerNum = jQuery("#morecustomer option:selected").val();
			if(customerNum == "") customerNum=1;
			var jsondata = [];
			for(var index=1; index<=customerNum; index++) {
				var fn = jQuery("#fn_"+index).val();
				var ln = jQuery("#ln_"+index).val();
				var cn = jQuery("#cn_"+index).val();
				var email = jQuery("#email_"+index).val();
				var gender = jQuery("#gender_"+index).val();
				var phone = jQuery("#phone_"+index).val();
				var pickup = jQuery("#pickup_"+index).val();
				var room = jQuery("#room_"+index).val();
				var point = usedCredit;
				var paymethod = jQuery("#paymethodsel").val();
				jsondata.push({"fn":fn, "ln":ln, "cn":cn, "email":email, "gender":gender, "phone":phone, "pickup":pickup, "room":room, "pd_point":point, "paymethod":paymethod});
			}
			jQuery.post(
				"./booking.htm", 
				"external_reserve=1&gid="+groupid+"&jsondata="+JSON.stringify(jsondata), 
				function(data) {
        			alert("预订完毕");
        			jQuery("#refno").html(data);
        			
        			// 如果Google Checkout，显示按钮
        			if(jQuery("#paymethodsel option:selected").val() == 2) {
        				
        				/*var googlecheckout = 
        					"<form method=\"POST\" action=\"https://checkout.google.com/api/checkout/v2/checkoutForm/Merchant/876069353760388\" accept-charset=\"utf-8\">"+
        					"<input type=\"hidden\" name=\"item_name_1\" value=\""+groupname+"\"/>"+
						  	"<input type=\"hidden\" name=\"item_description_1\" value=\""+data+groupdesc+"\"/>"+
						  	"<input type=\"hidden\" name=\"item_quantity_1\" value=\""+1+"\"/>"+
						  	"<input type=\"hidden\" name=\"item_price_1\" value=\""+totalPrice+"\"/>"+
						  	"<input type=\"hidden\" name=\"item_currency_1\" value=\"GBP\"/>"+
						  	"<input type=\"hidden\" name=\"_charset_\"/>"+
						  	"<input type=\"image\" name=\"Google Checkout\" alt=\"Fast checkout through Google\" src=\"http://checkout.google.com/buttons/checkout.gif?merchant_id=876069353760388&w=180&h=46&style=trans&variant=text&loc=en_GB\" height=\"46\" width=\"180\"/>"+
							"</form>";*/

        				var paypal = 
        					"<form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\">"+
							"<input type=\"hidden\" name=\"cmd\" value=\"_xclick\">"+
							"<input type=\"hidden\" name=\"business\" value=\"justin@hinotravel.com\">"+
							"<input type=\"hidden\" name=\"lc\" value=\"GB\">"+
							"<input type=\"hidden\" name=\"item_name\" value=\""+groupname+": "+(data.replace(/<br\/>/g, " &nbsp;"))+"\">"+
							"<input type=\"hidden\" name=\"item_number\" value=\""+(data.replace(/<br\/>/g, " &nbsp;"))+"\">"+
							"<input type=\"hidden\" name=\"amount\" value=\""+totalPrice+"\">"+
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
							"<input type=\"image\" src=\"https://www.paypalobjects.com/WEBSCR-640-20110429-1/en_GB/i/btn/btn_paynowCC_LG.gif\" border=\"0\" name=\"submit\" alt=\"PayPal - The safer, easier way to pay online.\">"+
							"<img alt=\"\" border=\"0\" src=\"https://www.paypalobjects.com/WEBSCR-640-20110429-1/en_GB/i/scr/pixel.gif\" width=\"1\" height=\"1\">"+
							"</form>";
        				
        				jQuery("#pay").html(paypal);
        				
        			}
        			else
        				jQuery("#pay").html(jQuery("#paymethodsel option:selected").text());
        			
        			// 隐藏上一步下一步按钮
        			jQuery(".nav").css("display","none");
    			}
			);
			break;
		default:
			errormsg = "预订错误，请关闭此页面并打开新浏览器窗口重试。";
			break;
	}
	
	if(errormsg != "") {
		alert(errormsg);
		return false;
	}
	else
		return true;
}

jQuery(document).ready(booking_init);