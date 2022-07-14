/**
 * 
 */
jQuery(function() {
	$('#signup').click(function() {
		/*Start : fetch All Defect List*/
		var usr = {
		    "first_name" : $("#firstName").val(),
		    "last_name" : $("#lastName").val(),	
		    "password" : $("#password").val(),
		    "emailId" : $("#emailId").val(),
		    "ph_no" : $("#ph_no").val(),
		    "dob" : $("#dob").val(),
		    "role" : $("#role").val(),
		    "gender" : $("#gender").val()
		}

	    $.ajax({
	        type: "POST",
			data: JSON.stringify(usr),
	        url: "signup.html",
			dataType:"json",
			contentType:"application/json",
	        success: function(response, textStatus, jqXHR) {
		// temps
		//		$( "#dialog-confirm" ).dialog({
		//	      resizable: false,
		//	      height:140,
		//	      modal: true,
		//	      buttons: {
		//	        "Log in now": function() {
		//	          $( this ).dialog( "close" );
		//	        },
		//	        Cancel: function() {
		//	          $( this ).dialog( "close" );
		//	        }
		//	      }
		//	    });
		//		$.session.set('emailId', emailId);
		//		sessionStorage.setItem('emailId', $("#emailId").val());
		//		console.log('1');
		//		console.log(JSON.stringify(sessionStorage.getItem('emailId')));
		//		console.log('2')
	            window.location = "login1.html";
	        },
	
	        error: function(jqXHR, textStatus, errorThrown) {
	            if (jqXHR.status == "500") {
	                window.location = "timeout.html";
	            } else if (jqXHR.status == "203") {
	                window.location = "errorpage.html";
	            }
	        }
	    }); /*End : fetch All Defect List*/
	
	})
    

})