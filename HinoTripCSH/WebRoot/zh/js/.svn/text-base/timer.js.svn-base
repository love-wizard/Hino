function newdate(ds) {
	//var date = new date();
	//Ken Chen 2013-04-09 //夏令时问题临时解决办法，提前一小时 10月份要修改回去；夏令时于当地标准时间 星期日, 31 三月 2013, 01:00 开始 27 十月 2013, 02:00 结束
	ds = ds + 60*60*1000;
	var d=new Date(ds);
	//alert(d.getFullYear());
	var hours = d.getUTCHours();
	var mins = d.getUTCMinutes();
	var secs = d.getUTCSeconds();
	
	//夏令时问题临时解决办法
	if(hours<23)
	{
		//hours = hours + 1;	
	} else
	{
	 hours = 0;
	}
	
	
	if (hours.toString().length<2)
	{
		hours = "0"+hours;
	}
	if (mins.toString().length<2)
	{
		mins = "0"+mins;
	}
	if (secs.toString().length<2)
	{
		secs = "0"+secs;
	}
	
	jQuery("#time_area").html(hours+":"+mins+":"+secs);
	//return d;//alert(d);
}

function adder()
{
	now_time = now_time + 1000;
	newdate(now_time);
	//
	jQuery(".seckill_flow").each(function(){		
		if(now_time < sk_end.getTime() && now_time > sk_start.getTime())
		{			
			jQuery(this).removeClass("seckill_flow_sel_prod");
			jQuery(this).addClass("seckill_flow_begin");
			//alert(now_time + "   " + sk_start.getTime() + "   " + sk_end.getTime());
		} else
		{
			jQuery(this).removeClass("seckill_flow_begin");
			jQuery(this).addClass("seckill_flow_sel_prod");
		}
	});
	
	jQuery("input[name='sk_start']").each(function(){
		var start = parseInt(jQuery(this).val());
		var end = parseInt(jQuery(this).next().val());
		var over = jQuery(this).next().next().val();
		var re =jQuery(this).parent().parent().find(".me").text(); 
		if(now_time<end&&now_time>start&&over!='true')
		{
			jQuery(this).parent().parent().find(".qg").show();
			jQuery(this).parent().parent().find(".qg_timer").hide();
			jQuery(this).parent().attr("class","qg_bt bt1");
			jQuery(this).parent().parent().find(".me").html(re.replace("仅限", "还剩"));
		} else if (now_time<start) {
			jQuery(this).parent().parent().find(".qg").hide();
			jQuery(this).parent().parent().find(".qg_timer").show();
			jQuery(this).parent().attr("class","qg_bt bt3");
			var tDiff = (start - now_time);
			var h = Math.floor(tDiff / 3600000);
			var m = Math.floor((tDiff % 3600000) / 60000);
			var s = Math.floor((tDiff % 3600000 % 60000) / 1000);
			jQuery(this).parent().parent().find(".qg_timer").html("&nbsp;" + (h < 10? "0" + h: h) + " : " + (m < 10? "0" + m: m) + " : " + (s < 10? "0" + s: s));
			jQuery(this).parent().parent().find(".me").html(re.replace("还剩", "仅限"));
		} else
		{
			jQuery(this).parent().parent().find(".qg").show();
			jQuery(this).parent().parent().find(".qg_timer").hide(); 
			jQuery(this).parent().attr("class","qg_bt bt2");
			jQuery(this).parent().parent().find(".me").html(re.replace("还剩", "仅限"));
		}
	});
}

var sk_start = new Date();
var sk_end = new Date();

jQuery(document).ready(function() {
	
	now_time = now_time - 600000;// + 60 * 60 * 1000 ;
	sk_start.setTime(now_time);
	sk_start.setHours(15, 0, 0, 0);
	
	sk_end.setTime(now_time);
	sk_end.setHours(16, 0, 0, 0);

	adder();
	
	//alert(now_time);
	setInterval(adder,1000);
	//newdate(now_time);
	
	jQuery("a.skbuy").click(function(){
		var start = parseInt(jQuery(this).parent().prev().prev().prev().val());
		var end = parseInt(jQuery(this).parent().prev().prev().val());
		var over = jQuery(this).parent().prev().val();
		if(now_time<end&&now_time>start&&over!='true')
		{
			
		} else
		{
			alert("无法抢购该团请确认时间和状态！");
			return false;
		}
	});
	
});

//window.onload = function() {
//	//TODO begin 处理登陆用户时区
//    //获得登陆用户时区与GMT时区的差值 赵奇英 2011-2-20
//    var exp = new Date();
//    var gmtHours = -(exp.getTimezoneOffset()/60);
//    setCookie('customer_timezone',gmtHours,1);
//    //判断是否为夏令时
//    date = exp.format('yyyy-MM-dd HH:mm:ss');
//    if(inDaylightTime(date)){
//        setCookie('inDaylightTime',1,1);
//    }
//}
////设置Cookie
//function setCookie(c_name,value,expiredays){
//    var exdate=new Date()
//    exdate.setDate(exdate.getDate()+expiredays)
//    document.cookie=c_name+ "=" +escape(value)+
//    ((expiredays==null) ? "" : "; expires="+exdate.toGMTString())
//}
////判断时间是东半球还是西半球
//function isEastEarthTime(newDate)
//{
//    var dj= newDate.getGMTOffset(false);
//    if (dj.indexOf("-") == -1){
//        return true;
//    } else {
//        return false;
//    }
//}
////是否是夏令时
//function inDaylightTime(date){
//	var start = new Date(date.getTime());
//	start.setMonth(0);
//	start.setDate(1);
//	start.setHours(0);
//	start.setMinutes(0);
//	start.setSeconds(0);
//	var middle = new Date(start.getTime());
//	middle.setMonth(6);
//	// 如果年始和年中时差相同，则认为此国家没有夏令时
//	if ((middle.getTimezoneOffset() - start.getTimezoneOffset()) == 0) {
//	    return false;
//	}
//	
//	var margin = 0;
//	if (this.isEastEarthTime(date)) {
//	    margin = middle.getTimezoneOffset();
//	} else {
//	    margin = start.getTimezoneOffset();
//	}
//	if (date.getTimezoneOffset() == margin) {
//	    return true;
//	}
//	return false;
//}
////DONE end
//	
