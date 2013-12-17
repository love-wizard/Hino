jQuery(document).ready(function() {

		//getCookie();
		
    // Register a 'click' handler on the submit button
    jQuery("#login_button").click(function(event){
        // Post form to server
        //postForm(event);
    	//var jsondata = [];
    	var username = jQuery("#login_form_username").val();
    	var password = jQuery("#login_form_password").val();
    	jQuery.post("./index.htm", "login_action=1&un="+username+"&ps="+password, function(data) {
        	//alert(data);
        	if(data.result==0)
        	{
        		alert("欢迎回来"+data.fn + " " + data.ln);
        		window.location.reload();
        		
        		//jQuery("#nouser").hide();
        		//jQuery("#hasuser").show();
        	} else
        	{
        		alert(data.result);
        	}
    		//window.location = "./sales_rep_booking_record.htm";
    	});
        // Prevent the default browser behavior of navigating to the link
        return false;
    	//jsondata.push({"username": username, "password": password});
       
    });
    
    jQuery("#logout_button").click(function(event){
        // Post form to server
        //postForm(event);
    	//var jsondata = [];

    	
    	//jsondata.push({"username": username, "password": password});
        jQuery.post("./index.htm", "logout_action=1", function(data) {
        	//alert(data);
    		alert(data.result);
    		//Devon King - 2012/10/11 - TD#33 Redirect to the index page
    		window.location.href = "./index.htm"; //reload();
    		//window.location = "./sales_rep_booking_record.htm";
    	});
        // Prevent the default browser behavior of navigating to the link
        return false;
    });
    
    jQuery(".logout_button").click(function(event){
    	jQuery("#logout_button").click();
    	return false;
    });

    // Ken Chen 2013-03-24 TD#128 添加重置密码功能
    jQuery("#resetpwd_button").click(
	function(event) {
			
		var uname = jQuery("#login_form_username").val();
		var upassword = jQuery("#login_form_password").val();
		jQuery.post("./index.htm","resetpwd_action=1&un=" + uname + "&ps=" + upassword, function(data) {
			//alert(data);
			if (data.result == 0) {
				alert("重置密码已经发送给 " + data.fn + " 邮箱。请重新登陆后修改密码！");
				//window.location.reload();
			} else {
				alert(data.result);
			}
		});
		return false;
	});
    
    jQuery("#btn_s").click(function(event){
    // Created by - Devon King - 201/09/02 - 响应搜索按钮
    	var keyword = $.trim(jQuery("#txt_s_k").val());
    	
    	if (keyword != ""){
    		jQuery("#frm_s").attr("action", "./search_result.htm?idx=1&st=2&dcode=" + jQuery("#ct_code").val());
    		jQuery("#frm_s").submit();
    	} else{
    		alert("请您先输入搜索关键词，然后再试一遍！");
    	}
    	
        // Prevent the default browser behavior of navigating to the link
        return false;
    });
        
    $("#top").mouseover(function(){
		var showBigADTimes = $.cookies.get("ShowBigAD");
		//alert(showBigADTimes);
		if (showBigADTimes < 2) {
			$("#banner").css("margin-bottom","0px");
			$("#top").slideUp(0, function() {
				$("#banner").slideDown(1000);
			});
		}
		
		$.cookies.set("ShowBigAD", showBigADTimes + 1);
	});
    
    $("#banner").mouseout(function(){
      $("#banner").css("margin-bottom","-43px");
      $("#top").show();
      
  	  $("#banner").slideUp(1000,function(){
  			
  		});
  	  });
    /*
    $(".choice").mouseover(function(){
        $(".more_city").show();
        
        $(".choice").addClass("over_choic");
    });
    */
    $(".more_city").mouseover(function(){
    
    	$(".more_city").show();
    	$(".choice").addClass("over_choic");
    });
    
    $(".choice").mouseout(function(){
    	$(".more_city").hide();
    	$(".choice").removeClass("over_choic");
    });
   
    $(".more_city").mouseout(function(){
    	$(".more_city").hide();
    	$(".choice").removeClass("over_choic");
    });  
    
     jQuery("#diy_button").click(function(event){
        // Post form to server
        //postForm(event);
    	//var jsondata = [];
    	//alert("dd");
    	var title = jQuery("#textfield").val();
    	var email = jQuery("#textfield2").val();
    	var phone = jQuery("#textfield3").val();
    	var desc = "目的地:" + jQuery("#textfield4").val() +"\n 出发日期：" + jQuery("#textfield5").val() + "\n 行程天数：" + jQuery("#textfield6").val() + "\n 出游人数 成人：" + jQuery("#textfield7").val()+ "\n 儿童："+ jQuery("#textfield8").val()+ "\n 婴儿：" + jQuery("#textfield9").val()+ "\n 出游人员关系：" + jQuery("#select").val()+"\n 护照签发地："+jQuery("#textfield10").val()+"\n"; 
    	
    	var purpose = jQuery("#select2").val();
    	var buget = jQuery("#textfield11").val();

    	desc = desc+"出游目的：" +purpose + "\n 出行方式:" +jQuery("#select3").val()+jQuery("#select4").val()+ "\n预算" +buget+ "\n酒店星级" +jQuery("#select5").val()+ "\n我想要" +jQuery("#textfield12").val()+ "\n我想吃" +jQuery("#textfield13").val()+ "\n我想玩" +jQuery("#textfield14").val()+ "\n";
    	
    	//jsondata.push({"username": username, "password": password});
        jQuery.post("./diy.htm", "diy_action=1&tt="+title+"&em="+email+"&ph="+phone+"&desc="+desc, function(data) {
        	//alert(data);
        	if(data.result==0)
        	{

        		alert("亲爱的："+data.name + "感谢您选择海诺旅游DIY自由行，您的提交已经生效！我们将在2个工作日以内和您取得联系， 届时将会有专业的，DIY咨询师为您的出行提供详细旅游规划！" );
        		//window.location.reload();
        		//alert("od");
        		//jQuery("#nouser").hide();
        		//jQuery("#hasuser").show();
        	} else
        	{
        		alert(data.result);
        	}
    		//window.location = "./sales_rep_booking_record.htm";
    	});
        // Prevent the default browser behavior of navigating to the link
        return false;
    });
     
//     Ken Chen 2012/12/13 不需要登陆或者未注册用户均可用报团 begin
//     jQuery("a.bmgroup").click(function(event){
//	    	if(!logged)
//	    	{
//	    		alert("请先登录！");
//	    		return false;
//	    	}
//	    });
    
//    jQuery("a.skbuy").click(function(event){
//	    	if(!logged)
//	    	{
//	    		alert("请先登录！");
//	    		return false;
//	    	}
//	    });
//     
//   Ken Chen 2012/12/13 不需要登陆或者未注册用户均可用报团 end
     
     
    jQuery('input[id*="votep_"]').live('click', function() {
    	
    	var partsArray = jQuery(this).attr("id").split('_');
    	var index = partsArray[1];
    	//alert(index);
    	
    	jQuery.post("./go_dream.htm", "vote_ts=1&tsid="+index+"&votetp=0", function(data) {
        	//alert(data);
    		alert("感谢您的投票！");
        	window.location.reload();
        		//alert("od");
        		//jQuery("#nouser").hide();
        		//jQuery("#hasuser").show();
        	
    		//window.location = "./sales_rep_booking_record.htm";
    	});
	});
    jQuery('input[id*="voten_"]').live('click', function() {
    	
    	var partsArray = jQuery(this).attr("id").split('_');
    	var index = partsArray[1];
    	//alert(index);
    	
    	jQuery.post("./go_dream.htm", "vote_ts=1&tsid="+index+"&votetp=1", function(data) {
        	//alert(data);
    		alert("感谢您的投票！");
        	window.location.reload();
        		//alert("od");
        		//jQuery("#nouser").hide();
        		//jQuery("#hasuser").show();
        	
    		//window.location = "./sales_rep_booking_record.htm";
    	});
	});
    
});

//分子公司ID-Name对照表
var sitecitys = new Array();
sitecitys[178] = "诺丁汉";
sitecitys[179] = "曼彻斯特";
sitecitys[180] = "伯明翰";
sitecitys[181] = "华 威";
sitecitys[182] = "考文垂";
sitecitys[183] = "拉夫堡";
sitecitys[184] = "谢非尔德";
sitecitys[185] = "莱斯特";
sitecitys[186] = "伦敦";
sitecitys[0] = "所有出发地";

//用Cookie保存分子公司ID
function SaveCookie(companyId) {
//    $.cookies.set("WebSiteCompanyInfo", companyId, { expires: 1, path: '/', domain: 'hinotravel.com' });
	$.cookies.set("WebSiteCompanyInfo", companyId, {expries: 30 });
}

function getCookie(){
	
	if ($.cookies.get("WebSiteCompanyInfo")) {	
		//$(".city_name").text(sitecitys[$.cookies.get("WebSiteCompanyInfo")] + "站");	
		ShowPage($.cookies.get("WebSiteCompanyInfo"));
	}
	
}

function HighlightDepartCity(obj){		
	$("#dpt_ct_tbl tr:not(:first, :last) td:nth-child(1)").html("");
	$("#dpt_ct_tbl tr:not(:first, :last)").removeClass("dpt_ct_light");
	$(obj).addClass("dpt_ct_light");
	$(obj).find("td:nth-child(1)").html("<img src='./img/depart_city_bullet.png' height='8px' width='8px'/>");
}

function ShowPage(companyId) {	
    
//    if ($.cookie.get("WebSiteCompanyInfo") != null) {
//        companyId = $.cookie.get("WebSiteCompanyInfo");
//    } else {
//        companyId = 186;
//    }
    
  
    //$("#top" + companyId).addClass("cur");
    $(".city_name").text(sitecitys[companyId] + (companyId == 0? "": "站"));
    //$(".choice").text("更多城市");   
    $("#ct_code").val(companyId);

	//新版首页
	if($("#area").size()>0){
	  $("#area").text(sitecitys[companyId] +"主站");
	}

	if($("a[id^='departuremenu']").size()>0){
		$("a[id^='departuremenu']").text(sitecitys[companyId] +"出发");
	}

    SaveCookie(companyId);
    
    //var e = $.Event("keyup", { keyCode: 32 }); 
	$("#txt_s_k").keyup();	
}

function ShowHelpContent(helpId){
	window.location.href= "./service_center.htm?helpId="  + helpId;
}

function getShowBigAdCookie()
{
	expireDate = new Date();
	expireDate.setTime( expireDate.getTime() + ( 12 * 60 * 60 * 1000 ) );
	//expireDate.setTime(expireDate.getTime()+(60 * 1000));
	
	if ($.cookies.get("ShowBigAD")) {	
		displayBigAD = false;
	}
	else
	{
		$.cookies.set("ShowBigAD", 1, {expiresAt:expireDate});
		displayBigAD = true;
	}
	return displayBigAD;
}

