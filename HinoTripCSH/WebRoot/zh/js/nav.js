$(function(){
		$(".menu li:has(ul)").hover(function(){//has()找到有ul的li进行过滤
							$(this).children("ul").stop(true,true).slideDown()//stop()现将所有的动画停止
							},function(){
								$(this).children("ul").stop(true,true).slideUp()
								})   
		   })
		   
		   