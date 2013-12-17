(function($){
	
	$.confirm = function(params){
		
		if($('#confirmOverlay').length){
			// A confirm is already shown on the page:
			return false;
		}
		
		var buttonHTML = '';
		$.each(params.buttons,function(name,obj){
			
			// Generating the markup for the buttons:
			
			buttonHTML += '<a href="#" class="button '+obj['class']+'">'+name+'<span></span></a>';
			
			if(!obj.action){
				obj.action = function(){};
			}
		});
		
		var markup = [
			'<div id="confirmOverlay">',
			'<div id="confirmBox">',
			'<h1>',params.title,'</h1>',
			'<div id="confirmForm">',
			'<ul>',
				'<li><input type="radio" name="paymeth" value="2" ' + (params.pmval == 2? 'checked' : '') + '>&nbsp;在线支付 (paypal账户)</input></li>',
				'<li><input type="radio" name="paymeth" value="1"' + (params.pmval == 1? 'checked' : '') + '>&nbsp;银行转账 (sort code:206325 account number: 33250482)</input></li>',
				'<li><input type="radio" name="paymeth" value="3"' + (params.pmval == 3? 'checked' : '') + '>&nbsp;现金支付 (直接交至旅游社或者销售代表)</input></li>',
				'<li><input type="radio" name="paymeth" value="4"' + (params.pmval == 4? 'checked' : '') + '>&nbsp;网银在线支付</input></li>',
			'</ul>',
			'</div>',
			'<div id="confirmButtons">',
			buttonHTML,
			'</div>',
			'</div></div>'
		].join('');
		
		$(markup).hide().appendTo('body').fadeIn();
		
		var buttons = $('#confirmBox .button'),
			i = 0;

		$.each(params.buttons,function(name,obj){
			buttons.eq(i++).click(function(){
				
				// Calling the action attribute when a
				// click occurs, and hiding the confirm.
				
				obj.action();
				//$.confirm.hide();
				return false;
			});
		});
	};

	$.confirm.hide = function(){
		$('#confirmOverlay').fadeOut(function(){
			$(this).remove();
		});
	};
	
})(jQuery);