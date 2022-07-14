/**
 * Authored by Jinxin
 */
jQuery(function() {
	
	$("#emailId").val("");
	$("#password").val("");
	
	$('#login').click(function() {
		/*Start : fetch All Defect List*/
		var usr = {	
		    "password" : $("#password").val(),
		    "emailId" : $("#emailId").val()
		}

	    $.ajax({
	        type: "POST",
			data: JSON.stringify(usr),
	        url: "login.html",
			dataType:"json",
			contentType:"application/json",
	        success: function(response, textStatus, jqXHR) {
				sessionStorage.setItem('emailId', $("#emailId").val());
				if(response.page == "manager.html"){
					sessionStorage.setItem('role', "M");
					window.location.href = response.page;
				} else if (response.page == "operator.html") {
					sessionStorage.setItem('role', "O");
					window.location.href = response.page;
				} else if (response.page == "customer.html"){
					sessionStorage.setItem('role', "C");
					window.location.href = response.page;
				} else {
					window.confirm("Your email ID or password is incorrect. Please check.");
					window.location.href = "login1.html";
				}
				
	        },
	
	        error: function(jqXHR, textStatus, errorThrown) {
	            if (jqXHR.status == "500") {
	                //window.location = "timeout.html";
					window.confirm("Your email ID or password is incorrect. Please check.");
	            } else if (jqXHR.status == "203") {
	                //window.location = "errorpage.html";
					window.confirm("Your email ID or password is incorrect. Please check.");
	            }
	        }
	    }); /*End : fetch All Defect List*/
	
	})
    

})