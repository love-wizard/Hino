
function distime()
{
var clock=new Date
var hours=clock.getHours()
var time=parseInt(hours);
if(1<=time && time<=8){time="早上好"+"!欢迎来到海诺旅游 hinotravel.com";} 
else if(8<time && time<=12){time="上午好"+"!欢迎来到海诺旅游 hinotravel.com";}
else if(12<time && time<=14){time="中午好"+"!欢迎来到海诺旅游 hinotravel.com";}
else if(14<time && time<=18){time="下午好"+"!欢迎来到海诺旅游 hinotravel.com";}
else if(18<time && time<=24){time="晚上好"+"!欢迎来到海诺旅游 hinotravel.com";}
document.getElementById("timebox").innerHTML=time
	}
setInterval("distime()",1000)

