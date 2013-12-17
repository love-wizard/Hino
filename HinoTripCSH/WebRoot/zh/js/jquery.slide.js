/*
 *jQuery 鍥剧墖杞炕(鐒︾偣鍥�)鎻掍欢
 *Version锛�1.1.0201
 *Author锛歊obin
 *Date锛�2013-01-10
 *EDIT.Robin.201302012319
 *Demo锛歨ttp://www.lisibin.org/demo/slide/
 *copyright:鐞嗚涓婂彲浠ュ厤璐逛娇鐢紝浣嗚淇濈暀寮€鍙戣€呬俊鎭€�
 */
 (function($){
 	$.fn.slide = function(options){
 		var defaults = {
 			direction : "left",	//婊氬姩鏂瑰悜 left || top
 			duration : 300,	//婊氬姩鏃堕棿 
 			easing : "linear", // 杩囧害鏁堟灉 linear || swing
 			delay : 4000, //婊氬姩寤惰繜
 			arraw : true, //绠ご
 			title : true, //鏍囬鏍�
 			btn : true, //鎸夐挳
 			trigger : "mouseenter" //瑙﹀彂浜嬩欢 click || mouseenter
 		}
 		var options = $.extend(defaults, options);
 		//璁＄畻鐩稿叧鏁版嵁
 		var wrap = $(this), ul = wrap.find('ul'), lis = ul.find('li'), len = lis.length, bWidth = wrap.width(), bHeight = wrap.height(), liHeight = lis.first().height(), title = "", btn = '', arraw = '', index = 0, direc = '', offset = '', timer = '', prveAllow = true, nextAllow = true;
 		//鏍囬鏍�
 		if (options.title) {
 			var btnBg = "<div id='btn-bg'></div>";
 			wrap.append(btnBg);
 		};
 		
 		if(options.direction == "left"){
 			ul.width( bWidth * len );
 			ul.height( bHeight );
 		}else{
 			ul.width( bWidth );
 			ul.height( bHeight * len );
 		}
 		//绠ご
 		if (options.arraw){
 			arraw = "<a id='prev'></a><a id='next'></a>";
 			wrap.append(arraw);
 			wrap.find('#prev').click(function(){
 				if(prveAllow){
 					prveAllow = false;
	 				pause();
	 				index--;
	 				if (index == -1) {
	 					lis.last().css({"position":"relative", "left": -bWidth * len});
	 					wrap.find('#btn span').eq(len-1).addClass('current').siblings().removeClass('current');
	 					ul.animate({"left": bWidth+"px"},options.duration,function(){
							lis.last().css({"position":"static", "left": 0});
							ul.css("left", -bWidth*(len-1));
							prveAllow = true;
	 					})
	 				}else if (index == -2) {
	 					index = 2;
	 					showPic(index);
	 				}else{
	 					showPic(index);
	 				}
 				}
 			})
 			wrap.find('#next').click(function(){
 				if (nextAllow) {
 					nextAllow = false;
 					pause();
	 				index++;
	 				if (index == len + 1) { index = 1};
	 				showPic(index);
 				}
 			})
 		}
 		//鎸夐挳
 		if (options.btn) {
 			btn = "<div id='btn'>";
 			for( var i = 0; i < len; i++){
 				btn += "<span>"+(i+1)+"</span>";
 			}
 			btn += "</div>";
 			wrap.append(btn);
 			wrap.find('#btn span').bind(options.trigger, function(){
 				index = $(this).index();
 				showPic(index);
 			}).eq(0).trigger(options.trigger)
 		}else{
 			wrap.find('#btn-bg').text( lis.eq(0).find('img').attr('alt'));
 		}
 		//鑷姩婊氬姩
 		wrap.hover(function(){
 			pause();
 		},function(){
 			start();
 		}).trigger('mouseleave')
 		//
 		function pause(){clearInterval(timer); }
 		function start(){
 			timer = setInterval(function(){
 				index++;
 				if (index == len + 1) { index = 1};
 				showPic(index);
 			},options.delay)
 		}
 		 //鎵ц鍑芥暟
 		function showPic(index){
 			if(options.direction == "left"){
 				offset = bWidth * index * -1;
 				direc = { "left" : offset+"px"};
 			}else{
 				offset = bHeight * index * -1;
 				direc = { "top" : offset+"px"};
 			}
 			if(options.title){
 				if(index == len){ 
 					wrap.find('#btn-bg').text( lis.eq(0).find('img').attr('alt'));
 				}
 				wrap.find('#btn-bg').text( lis.eq(index).find('img').attr('alt'));
 			}
 			if (index == len) {
 				if(options.direction == "left"){
					lis.first().css({"position":"relative", "left": bWidth * len});
						wrap.find('#btn span').eq(0).addClass('current').siblings().removeClass('current');
						ul.animate({"left": -1 * bWidth * len+"px"},options.duration,function(){
							lis.first().css({"position":"static", "left": 0});
							ul.css("left", 0);
							prveAllow = true;
							nextAllow = true;
					});
				}else{
					lis.first().css({"position":"relative", "top": bHeight * len});
						wrap.find('#btn span').eq(0).addClass('current').siblings().removeClass('current');
						ul.animate({"top": -1 * bHeight * len+"px"},options.duration,function(){
							lis.first().css({"position":"static", "top": 0});
							ul.css("top", 0);
							prveAllow = true;
							nextAllow = true;
					});
				}
			}else{
				wrap.find('#btn span').eq(index).addClass('current').siblings().removeClass('current');
 				wrap.find(ul).stop(true,false).animate( direc, options.duration, options.easing, function(){
	 			prveAllow = true;
	 			nextAllow = true;
 			});
			}
 		}
 	}
})(jQuery)