/**
 * 
 */
$(function() {

    $("#stationDiv").hide();
    $("#stationTable").hide();
    $("#bikeTable").hide();
    $("#bikeDiv").hide();
    document.getElementById("msgId").innerHTML = "";
	sessionStorage.getItem('emailId')
	
	var today = new Date();
	var date = today.getDate() +'-'+ (today.getMonth()+1) +'-'+ today.getFullYear();
	var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	document.getElementById('loginInfo').innerHTML = sessionStorage.getItem('emailId');	
	document.getElementById('loginTimeInfo').innerHTML = date +" : "+time;	

    /*Start : fetch All Nearby Stations*/

    var zipjson = {
        "zip_code": $("#zipcode").val()
    }

    $("#searchStid").click(function() {
		 document.getElementById("msgId").innerHTML = "";

        $.ajax({
            type: "POST",
            data: JSON.stringify(zipjson),
            url: "fetchAllStations.html",
			dataType:"json",
			contentType:"application/json",
            success: function(response, textStatus, jqXHR) {

                    $("#stationTable").show();
                    $("#stationDiv").show();

                    $('#stationTable').dataTable().fnDestroy();

                    var t = $('#stationTable').dataTable({
                        "aaData": response.result,
                        "aoColumns": [{
                            "mData": "station_id"
                        }, {
                            "mData": "station_name"
                        }, {
                            "mData": "station_address"
                        }, {
                            "mData": "station_zipCode"
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

            },

            error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == "500") {
                    window.location = "timeout.html";
                } else if (jqXHR.status == "203") {
                    window.location = "errorpage.html";
                }
            }
        }); /*End : fetch All Nearby Stations*/
    })

    /*Start : fetch All bikes in selected station*/

    var stationTable = $('#stationTable').DataTable();

    $('#stationTable tbody').on('click', 'tr', function() {
        $(this).toggleClass('selected');

 		var stationjson = {
        	"station_id": $(this).find("td:first").text()
    	}

		$("#selectStationId").val($(this).find("td:first").text());

        $.ajax({
            type: "POST",
			data: JSON.stringify(stationjson),
            url: "fetchFreeBikes.html",
			dataType:"json",
			contentType:"application/json",
            success: function(response, textStatus, jqXHR) {

                $("#stationTable").hide();
                $("#stationDiv").hide();

               // var resultObj = JSON.parse(response);

                if (response.content.length == 0) {
                       document.getElementById("msgId").innerHTML = "No Bikes are avaiable in the selected Station";
                } else {
                    $("#bikeTable").show();
                    $("#bikeDiv").show();

                    $('#bikeTable').dataTable().fnDestroy();

                    var bikeTable = $('#bikeTable').dataTable({
                        "aaData": response.content,
                        "aoColumns": [{
                            "mData": "bike_id"
                        }, {
                            "mData": "bike_no"
                        }, {
                            "mData": "bike_id"
                        }, {
                            "mData": "bike_no"
                        }],
                        "bJQueryUI": true,
                        "order": [0, "asc"],
                        "bSaveState": true,
                        "bScrollCollapse": true,
                        'iDisplayLength': 10,
                        "sPaginationType": "full_numbers"
                    })
                }

            },

            error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == "500") {
                    window.location = "timeout.html";
                } else if (jqXHR.status == "203") {
                    window.location = "errorpage.html";
                }
            }
        }); /*End : fetch All Nearby Stations*/

    });

    var bikeTable = $('#bikeTable').DataTable();

    $('#bikeTable tbody').on('click', 'tr', function() {
        $(this).toggleClass('selected');

        var bikeId = $(this).find("td:first").text();
        $("#selectBikeId").val(bikeId);

        $("#dialog-confirm").dialog("open");
    });

    $("#dialog-confirm").dialog({
        autoOpen: false,
        resizable: false,
        height: "auto",
        width: 400,
        modal: true,
        buttons: {
            "OK": function() {
                $(this).dialog("close");
                $("#stationDiv").hide();
                $("#stationTable").hide();
                $("#bikeTable").hide();
                $("#bikeDiv").hide();

				var bikejson = {
        			"bike_no": $("#selectBikeId").val(),
					"userName": sessionStorage.getItem('emailId'),
					"pickup_loc":$("#selectStationId").val()
    			}

                $.ajax({
                    type: "POST",
					data: JSON.stringify(bikejson),
            		url: "rentBike.html",
					dataType:"json",
					contentType:"application/json",
                    success: function(response, textStatus, jqXHR) {
	
						if(response.status == "FAILED" && response.response == "User ride already in progress"){
							 $("#zipcode").val("");
							document.getElementById("msgId").innerHTML = "Unable to process your request; Active ride is already in your bucket!";
						}
						else if(response.status == "FAILED" ){
							 $("#zipcode").val("");
							document.getElementById("msgId").innerHTML = "Technical Failure! Inconvenience Regretted.";
						}
						else if(response.status == "SUCCESS"){
	                        $("#zipcode").val("");
	                        document.getElementById("msgId").innerHTML = "Ride is started successfully with ride id " + response.booking_no;
						}	
                    },

                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status == "500") {
                            window.location = "timeout.html";
                        } else if (jqXHR.status == "203") {
                            window.location = "errorpage.html";
                        }
                    }
                }); /*End : fetch All Nearby Stations*/

            },
            "Report Defect": function() {
                $(this).dialog("close");
				$("#defect_dialog-confirm").dialog("open");
            }
        }
    });


	 $("#defect_dialog-confirm").dialog({
        autoOpen: false,
        resizable: false,
        height: "auto",
        width: 400,
        modal: true,
        buttons: {
            "SUBMIT": function() {
                $(this).dialog("close");
                $("#stationDiv").hide();
                $("#stationTable").hide();
                $("#bikeTable").hide();
                $("#bikeDiv").hide();

				var bikejson = {
        			"bike_id": $("#selectBikeId").val(),
					"email_id": sessionStorage.getItem('emailId'),
					"issue_desc":$("#issueDesc").val()
    			}

                $.ajax({
                    type: "POST",
					data: JSON.stringify(bikejson),
            		url: "reportDefect.html",
					dataType:"json",
					contentType:"application/json",
                    success: function(response, textStatus, jqXHR) {
	
						if(response.status == "FAILED" ){
							 $("#zipcode").val("");
							document.getElementById("msgId").innerHTML = "Technical Failure! Inconvenience Regretted.";
						}
						else if(response.status == "SUCCESS"){
	                        
	                        document.getElementById("msgId").innerHTML = "Thank you for your feedback. We will resolve the issue asap.";
						}	
                    },

                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status == "500") {
                            window.location = "timeout.html";
                        } else if (jqXHR.status == "203") {
                            window.location = "errorpage.html";
                        }
                    }
                }); /*End : fetch All Nearby Stations*/

            },
            "CANCEL": function() {
                $(this).dialog("close");
            }
        }
    });

})