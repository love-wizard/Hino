jQuery(document).ready(function() {
	
	jQuery("#cecam_search_action").click(function(event){
		
		var groupid= jQuery("#cecam_groupid").val();
		
		jQuery.post("./customer_email_cam.htm", "generate_group_emails=1&gid="+groupid, function(data) {
		
	        	
				jQuery("#cecam_group_emails").val(data);
	        	
	    });
	});
});