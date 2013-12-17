$(document).ready(function() {
	$(".scroll").each(function(index){
		//$(this).scrollbars({draggerSize:"32px"});
		$(this).scrollbars();
	});
	
	var firstSchedule = $("#schedule_day_1");
	if (firstSchedule != undefined && firstSchedule != null) {	
		showSchedule(1, firstSchedule);
	}
	
	function cur(ele, currentClass, tag) {
		
		ele= $(ele)? $(ele): ele;
		
		if(!tag) {
			ele.addClass(currentClass).siblings().removeClass(currentClass);

		} else {
			ele.addClass(currentClass).siblings(tag).removeClass(currentClass);

		}
	}

	$.fn.tab = function(options) {
		var org = {
			tabId:    "",
			tabTag:   "",
			conId:    "",
			conTag:   "",
			curClass: "cur",
			act:      "click",
			dft:      0,
			effact:   null,
			auto:     false,
			autotime: 3000,
			aniSpeed: 500
		};

		$.extend(org, options);
		
		var t = false;
		var k = 0;
		var _this = $(this);
		var tagwrp = $(org.tabId);
		var conwrp = $(org.conId);
		var tag = tagwrp.find(org.tabTag);
		var con = conwrp.find(org.conTag);	
		var len = tag.length;
		var taght = parseInt(tagwrp.css("height"));
		var conwh = conwrp.get(0).offsetWidth;
		var conht = conwrp.get(0).offsetHeight;
		var curtag = tag.eq(org.dft);
	
		//prepare
		cur(curtag, org.curClass);
		con.eq(org.dft).show().siblings(org.conTag).hide();

		if(org.effact == "scrollx") {
			
			var padding = parseInt(con.css("padding-left")) + parseInt(con.css("padding-right"));
			
			_this.css({
				"position"  : "relative",
				"height"    : taght + conht + "px",
				"overflow"  : "hidden" 
			});				

			conwrp.css({
				"width"     : len * conwh + "px",
				"height"    : conht + "px",
				"position"  : "absolute",
				"top"       : taght + "px"
			});

			con.css({
				"float"     : "left",
				"width"     : conwh - padding + "px",
				"display"   : "block"
			});
		}

		if(org.effact == "scrolly") {
	
			var padding = parseInt(con.css("padding-top")) + parseInt(con.css("padding-bottom"));										
	
			_this.css({
				"position"  : "relative",
				"height"    : taght + conht + "px",
				"overflow"  : "hidden" 
			});
	
			tagwrp.css({
				"z-index"   : 100
			});		
	
			conwrp.css({
				"width"     : "100%",
				"height"    : len * conht + "px",
				"position"  : "absolute",
				"z-index"   : 99
			});		
	
			con.css({
				"height"    : conht - padding + "px",
				"float"     : "none",
				"display"   : "block"
			});
		}

		tag.css({"cursor" : "pointer"}).each(function(i) {
			
			tag.eq(i).bind(org.act, function() {
				
				cur(this, org.curClass);
				k = i;
				
				switch(org.effact) {
					case "slow": 
						con.eq(i).show("slow").siblings(org.conTag).hide("slow");
						break;
	
					case "fast": 
						con.eq(i).show("fast").siblings(org.conTag).hide("fast");
						break;
	
					case "scrollx": 
						conwrp.animate({left: -i * conwh + "px"}, org.aniSpeed);
						break;
	
					case "scrolly": 
						conwrp.animate({top: -i * conht + taght + "px"}, org.aniSpeed);
						break;
						
					default: 
						con.eq(i).show().siblings(org.conTag).hide();
						break;
						
				}//End of switch
			});
		});

		if(org.auto) {
			
			var drive = function() {
	
				if(org.act == "mouseover") {
					tag.eq(k).mouseover();
					
				} else if(org.act == "click") {
					tag.eq(k).click();
					
				}
				
				k++;
				if(k == len) k = 0;
				
			};
	
			t = setInterval(drive, org.autotime);
		}
	}; //End of $.fn.tab	

	//Drive
	$("#testtab").tab({
		tabId:"#tabtag",
		tabTag:"li",
		conId:"#tabcon",
		conTag:"div.f",
		act:"mouseover",
		effact: "fast",	
		dft:0
	});
	
		
	//Set Default State of each portfolio piece
	$(".paging").show();
	$(".paging a:first").addClass("active");
		
	//Get size of images, how many there are, then determin the size of the image reel.
	var imageWidth = $(".window").width();
	var imageSum = $(".image_reel img").size();
	var imageReelWidth = imageWidth * imageSum;
	
	//Adjust the image reel to its new size
	$(".image_reel").css({'width' : imageReelWidth});
	
	//Paging + Slider Function
	rotate = function(){	
		var triggerID = $active.attr("rel") - 1; //Get number of times to slide
		var image_reelPosition = triggerID * imageWidth; //Determines the distance the image reel needs to slide

		$(".paging a").removeClass('active'); //Remove all active class
		$active.addClass('active'); //Add active class (the $active is declared in the rotateSwitch function)
		
		//Slider Animation
		$(".image_reel").animate({ 
			left: -image_reelPosition
		}, 500 );
		
	}; 
	
	//Rotation + Timing Event
	rotateSwitch = function(){		
		play = setInterval(function(){ //Set timer - this will repeat itself every 3 seconds
			$active = $('.paging a.active').next();
			if ( $active.length === 0) { //If paging reaches the end...
				$active = $('.paging a:first'); //go back to first
			}
			rotate(); //Trigger the paging and slider function
		}, 7000); //Timer speed in milliseconds (3 seconds)
	};
	
	rotateSwitch(); //Run function on launch
	
	//On Hover
	$(".image_reel a").hover(function() {
		clearInterval(play); //Stop the rotation
	}, function() {
		rotateSwitch(); //Resume rotation
	});	
	
	//On Click
	$(".paging a").click(function() {	
		$active = $(this); //Activate the clicked paging
		//Reset Timer
		clearInterval(play); //Stop the rotation
		rotate(); //Trigger rotation immediately
		rotateSwitch(); // Resume rotation
		return false; //Prevent browser jump to link anchor
	});	
	
	if ($("#thumbnail").html() != undefined && $("#thumbnail").html() != null) {	
		$("#thumbnail li a").click(function(){		
			$(".zoompic img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail li div.current").html("");
			$("#thumbnail li div.current").removeClass("current");		
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_1").html() != undefined && $("#thumbnail_1").html() != null) {	

		$("#thumbnail_1" + " li a").click(function(){		
			$(".zoompic_1" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_1" + " li div.current").html("");
			$("#thumbnail_1" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_2").html() != undefined && $("#thumbnail_2").html() != null) {	

		$("#thumbnail_2" + " li a").click(function(){		
			$(".zoompic_2" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_2" + " li div.current").html("");
			$("#thumbnail_2" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_3").html() != undefined && $("#thumbnail_3").html() != null) {	

		$("#thumbnail_3" + " li a").click(function(){		
			$(".zoompic_3" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_3" + " li div.current").html("");
			$("#thumbnail_3" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_4").html() != undefined && $("#thumbnail_4").html() != null) {	

		$("#thumbnail_4" + " li a").click(function(){		
			$(".zoompic_4" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_4" + " li div.current").html("");
			$("#thumbnail_4" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_5").html() != undefined && $("#thumbnail_5").html() != null) {	

		$("#thumbnail_5" + " li a").click(function(){		
			$(".zoompic_5" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_5" + " li div.current").html("");
			$("#thumbnail_5" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_6").html() != undefined && $("#thumbnail_6").html() != null) {	

		$("#thumbnail_6" + " li a").click(function(){		
			$(".zoompic_6" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_6" + " li div.current").html("");
			$("#thumbnail_6" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_7").html() != undefined && $("#thumbnail_7").html() != null) {	

		$("#thumbnail_7" + " li a").click(function(){		
			$(".zoompic_7" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_7" + " li div.current").html("");
			$("#thumbnail_7" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_8").html() != undefined && $("#thumbnail_8").html() != null) {	

		$("#thumbnail_8" + " li a").click(function(){		
			$(".zoompic_8" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_8" + " li div.current").html("");
			$("#thumbnail_8" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_9").html() != undefined && $("#thumbnail_9").html() != null) {	

		$("#thumbnail_9" + " li a").click(function(){		
			$(".zoompic_9" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_9" + " li div.current").html("");
			$("#thumbnail_9" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
	if ($("#thumbnail_10").html() != undefined && $("#thumbnail_10").html() != null) {	

		$("#thumbnail_10" + " li a").click(function(){
			$(".zoompic_10" + " img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title"), "style": "display:block;" });
			$("#thumbnail_10" + " li div.current").html("");
			$("#thumbnail_10" + " li div.current").removeClass("current");					
			$(this).parents("li").find("div").html("<img src='./img/arrow_sight.jpg'>");	
			$(this).parents("li").find("div").addClass("current");
			return false;
		});
	};
	
    jQuery("#btn_line_s").click(function(event){
    	var keyword = $.trim(jQuery("#txt_line_s_k").val());
    	var dc = $.cookies.get("WebSiteCompanyInfo");
    	if (dc <= 0) {
    		dc = 186;
    	}
    	
    	if (keyword != ""){
    		jQuery("#frm_line_s").attr("action", "./search_result.htm?idx=2&st=2&dcode=" + dc);
    		jQuery("#frm_line_s").submit();
    	} else{
    		alert("请您先输入搜索关键词，然后再试一遍！");
    	}
    	
        // Prevent the default browser behavior of navigating to the link
        return false;
    });
 /*   
    jQuery("#jqzoom").jqzoom(
		{
			zoomWidth: 510,
			zoomHeight: 400,
		    position: "right",  //放大图片出现的位置
		    preload: 1, 
		    lens:1 
		}
    );
    
    jQuery("#jqzoom").find(".jqZoomPup").css("z-index", "50000");
*/
    if (jQuery("#firstDayHasGroup").val() != undefined) {
    	showGroup(jQuery("#firstDayHasGroup").val());
    }    
});
