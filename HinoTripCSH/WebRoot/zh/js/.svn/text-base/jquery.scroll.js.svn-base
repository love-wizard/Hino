//滚动插件
(function($){
	$.fn.extend({
		Scroll:function(opt, callback){
			//参数初始化
			if(!opt) var opt={};
			var control = opt.control? opt.control: "ul"; //滚动控件，默认为ul 
			var _this = this.eq(0).find((control == "ul"? "ul": "table tbody") + ":first");
			var lineH = _this.find((control == "ul"? "li": "tr") + ":first").height(); //获取行高		
			var displayH = this.height(); //获取容器的高度
			var contentH = this.find((control == "ul"? "ul": "table")).height(); //获取内容的高度
			var line = opt.line? parseInt(opt.line,10): parseInt(this.height()/lineH, 10); //每次滚动的行数，默认为一屏，即父容器高度
			var speed = opt.speed? parseInt(opt.speed, 10): 500; //卷动速度，数值越大，速度越慢（毫秒）
			var timer = opt.timer? parseInt(opt.timer, 10): 3000; //滚动的时间间隔（毫秒）						
			
			//内容高度在容器高度范围内
			if(contentH <= displayH) return;
			
			if(line == 0) line = 1;
			var upHeight = 0 - line * lineH;
			
			//滚动函数
			scrollUp=function(){
				_this.animate({
					marginTop:upHeight
				},
				speed,function(){
					for(var i=1; i<=line; i++){
						_this.find((control == "ul"? "li": "tr") + ":first").appendTo(_this);
					}
					_this.css({marginTop: 0});
				});
			};
			
			//鼠标事件绑定
			_this.hover(function(){
				clearInterval(timerID);
			},
			function(){
				timerID = setInterval("scrollUp()", timer);
			}).mouseout();
		}        
	});
})(jQuery);