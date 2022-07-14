/**
 * 
 */
$(function() {
	
	var today = new Date();
	var date = today.getDate() +'-'+ (today.getMonth()+1) +'-'+ today.getFullYear();
	var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	document.getElementById('loginInfo').innerHTML = sessionStorage.getItem('emailId');	
	document.getElementById('loginTimeInfo').innerHTML = date +" : "+time;
	
	document.getElementById("userMsg").innerHTML = "";
	
	var userjson = {
        "user_id": sessionStorage.getItem('emailId')
    }

    $.ajax({
        type: "POST",
        data: JSON.stringify(userjson),
        url: "fetchAllRides.html",
		dataType:"json",
		contentType:"application/json",
        success: function(response, textStatus, jqXHR) {

           	if(response.status == "SUCCESS"){
				
				if(response.content.length==0){
					document.getElementById("userMsg").innerHTML = "You are new to Us...Hope we can enjoy more rides in future!";
				}
				else{
					
					for(var i=0;i<response.content.length;i++){
						if(response.content[i].status =="A"){
							response.content[i].status="Ride in Progress";
						}
						else if(response.content[i].status =="C"){
							response.content[i].status="Ride is Completed";
						}
					}
					
					$('#myRidesTable').dataTable().fnDestroy();
									
                    var t = $('#myRidesTable').dataTable({
                        "aaData": response.content,
                        "aoColumns": [{
                            "mData": "booking_no"
                        }, {
                            "mData": "bike_no"
                        }, {
                            "mData": "begin_station_name"
                        }, {
                            "mData": "drop_station_name"
                        }, {
                            "mData": "begin_date"
                        }, {
                            "mData": "drop_date"
                        }, {
                            "mData": "cost"
                        }, {
                            "mData": "status"
                        }],
                        "bJQueryUI": true,
                        "order": [0, "asc"],
                        "bSaveState": true,
                        "bScrollCollapse": true,
                        'iDisplayLength': 10,
                        "sPaginationType": "full_numbers",
                        "rowCallback": function(row, data) {
                            if (data.grade == "A") {
                                $('td:eq(4)', row).html('<b>A</b>');
                            }
                        }
                    })
				}
	
			}
        },

        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status == "500") {
                window.location = "timeout.html";
            } else if (jqXHR.status == "203") {
                window.location = "errorpage.html";
            }
        }
    }); /*End : fetch details of active ride*/
})