(function($){
	
	jQuery.confirm = function(params){
		
		if($('#confirmOverlay').length){
			// A confirm is already shown on the page:
			return false;
		}
		
		var buttonHTML = '';
		$.each(params.buttons,function(name,obj){
			
			// Generating the markup for the buttons:
			
			buttonHTML += '<a href="#" class="button '+obj['class']+'"><span></span></a>';
			
			if(!obj.action){
				obj.action = function(){};
			}
		});
		
		var markup = [
			'<div id="confirmOverlay">',
				'<div id="confirmBox">',
					'<div style="width: 100%; text-align: right; color: red; margin-top: 10px;height: 30px;line-height: 30px; font-size: 16px; font-weight: bold;"><em id="confirmClose" style="cursor: pointer;">X&nbsp;&nbsp;&nbsp;</em></div>',
					'<div id="confirmForm">',
						'姓：<input class="surname"></input><br/>',
						'<div style="height: 5px;"/>',
						'订单号：<input class="pnr"></input>',
					'</div>',
					'<div id="confirmButtons">',
						//buttonHTML,
					'</div>',
					'<div style="height:18px;"></div>',
					'<div id="confirmText">当你完成机票预定以后我们会将订单号发送到你的邮箱中</div>',
				'</div>',
			'</div>'
		].join('');
		
		$(markup).hide().appendTo('body').fadeIn();
		/*		
		var buttons = $('#confirmBox .button'),
			i = 0;

		$.each(params.buttons,function(name,obj){
			buttons.eq(i++).click(function(){
				
				// Calling the action attribute when a
				// click occurs, and hiding the confirm.
				
				obj.action();
				$.confirm.hide();
				return false;
			});
		});
		alert(5);
		*/
		$("#confirmButtons").click(function(){
			var pnr = $(".pnr").val();
			var name = $(".surname").val();
			pnr = $.trim(pnr);
			name = $.trim(name);
//			alert(pnr);
			window.open("https://mytripandmore.com/Itinerary.aspx?PNR="+ pnr + "&clockFormat=24&lastName=" + name);
			$.confirm.hide();
			return false;
		});
		
		$("#confirmClose").click(function(){
			$.confirm.hide();
			return false;
		});
	};

	$.confirm.hide = function(){
		$('#confirmOverlay').fadeOut(function(){
			$(this).remove();
		});
	};

})(jQuery);