jQuery(document).ready(function() {

	/*
    // Register a 'click' handler on the submit button
    jQuery("#form_save").click(function(event){
        // Post form to server
        postForm(event);

        // Prevent the default browser behavior of navigating to the link
        return false;
    })*/
    
    jQuery("#changestatus").click(function(event){
    	var staffid = jQuery("#staffid").val();
        jQuery.post("./staff_view_single.htm", "changestatus=1&id="+staffid, function(data) {
        	//if(jQuery("#"))
        	if(data=="0")
        	{ alert("修改状态为 激活");
        		jQuery("#status_div").html("activated");
        	} else
        	{ alert("修改状态为 未激活");
        		jQuery("#status_div").html("inactivated");
        	}
        	
    	});
        return false;
    })
    
    
     jQuery("#resetpriviledge").click(function(event){
        // Post form to server
    	var staffid = jQuery("#staffid").val();
    	var form = jQuery("#pri_change_form");
    	var formData = form.serialize();
    	//alert("send");
        jQuery.post("./staff_view_single.htm", "resetpriviledge=1&id="+staffid+"&"+formData, function(data) {
        // Update the target div with the server response and style the div by adding a CSS class
        alert(data);
    	});
        return false;
    })
})



/*
function postForm(event) {
    // Retrieve the Form and submit button elements
    var form = jQuery("#form");
    var submit = jQuery(event.currentTarget);

    // The server URL can be retrieved from the Form 'action' event
    var url = form.attr('action');

    // Use jQuery serialize function to serialize the Form controls into key/value pairs
    // Note: the jQuery serialize function will *not* add the button name/value
    // that submitted the form. We will add the submit button name/value manually
    var formData = form.serialize();

    // Append the form ID attribute so that Click can identify the Ajax target Control
    formData+='&'+form.attr('id')+'=1';

    // Append the name/value pair of the Submit button that submitted the Form
    formData+='&'+submit.attr('name')+'='+submit.attr('value');

    jQuery.post(url, formData, function(data) {
        // Update the target div with the server response and style the div by adding a CSS class
        var div = jQuery('#target');
        div.addClass('infoMsg');
        div.html(data);
    });
}
*/
