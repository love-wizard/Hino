jQuery(document).ready(
		function() {

			jQuery("#cancel").click(function(event) {
				clear_form();
				return false;
			});

			jQuery("#find").click(
					function() {

						var email = jQuery.trim(jQuery("#email").val());
						if (!validate_email(email)) {
							alert("请检查Email格式");
							return;
						}

						jQuery.post("./sales_rep_order_vip.htm",
								"load_customer=1&email=" + email,
								function(data) {
									if (data.result == "0") {
										alert("找不到客户");
									} else {
										jQuery("#customer").val(data.fn);
										jQuery("#reciever").val(data.rn);
										jQuery("#phone").val(data.ph);
										jQuery("#address").val(data.ad);

										if (!check_vip_applied()) {
											return;
										}
									}
								});
					});

			jQuery("#submit").click(
					function(event) {
						var email = jQuery.trim(jQuery("#email").val());
						if (!validate_email(email)) {
							alert("请检查Email格式");
							return;
						}

//						if (!check_vip_applied()) {
//							return;
//						}
				
						var rn = jQuery("#reciever").val();
						if (rn == "") {
							alert("Reciever Should Not Be None!");
							return;
						}

						var phone = jQuery.trim(jQuery("#phone").val());
						if (phone == "" || isNaN(phone)) {
							alert("电话不能为空且必须为数字");
							return;
						}

						var address = jQuery("#address").val();
						var delivery = jQuery("#delivery").val();
						var expireYear = jQuery("#expireYear").val(); // Ken Chen 23/09/2012 添加vip过期时间，默认为9999-12-31
						if (expireYear != "" && !(/^\d+$/.test(expireYear))) {
							alert(expireYear + "有效期不是正整数");
							return false;
						}
						else if(expireYear == "")
						{
							expireYear = 0 ;
						}
							
							
						var jsondata = {
							"name" : rn,
							"phone" : phone,
							"email" : email,
							"address" : address,
							"delivery" : delivery,
							"expire_year" : expireYear
						};
						
						//alert(email);
						
						jQuery.post("./sales_rep_order_vip.htm",
								"internal_reserve=1&jsondata="
										+ JSON.stringify(jsondata), function(
										data) {
									alert(data);
									clear_form();
								});

					});

		})

function clear_form() {
	jQuery("#address").val("");
	jQuery("#email").val("");
	jQuery("#phone").val("");
	jQuery("#reciever").val("");
	jQuery("#customer").val("");
	jQuery("#expireYear").val("");
}

// Ken Chen 2012/09/24 Add vip apply before validation
function check_vip_applied() {
	var email = jQuery.trim(jQuery("#email").val());
	jQuery.post("./sales_rep_order_vip.htm", "check_vip_applied=1&email="
			+ email, function(data) {
		if (data.result == "1") {
			alert("该客户正在申请过VIP，请不要重复申请！");
			return false;
		} else {
			return true;
		}
	});
	
}

function validate_email(email) {
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	return reg.test(email);
}