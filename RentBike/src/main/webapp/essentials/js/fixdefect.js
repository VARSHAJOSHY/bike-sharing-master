/**
 * Authored by Varsha Joshy (02:11:2021)
 */
jQuery(function() {

	var today = new Date();
	var date = today.getDate() +'-'+ (today.getMonth()+1) +'-'+ today.getFullYear();
	var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	document.getElementById('loginInfo').innerHTML = sessionStorage.getItem('emailId');	
	document.getElementById('loginTimeInfo').innerHTML = date +" : "+time;	
	
	document.getElementById('msgId').innerHTML ="";
	
    /*Start : fetch All Defect List*/

    $.ajax({
        type: "POST",
		dataType: "json",
        contentType: "application/json",
        url: "fetchAllDefectedBikes.html",

        success: function(response, textStatus, jqXHR) {

            if (response.result.length == 0) {
                document.getElementById('msgId').innerHTML ="No defects are available";
				$("#defectDiv").hide();
            } else {
                $('#defectTable').dataTable().fnDestroy();

                var t = $('#defectTable').dataTable({
                    "aaData": response.result,
                    "aoColumns": [{
                        "mData": "bike_id"
                    }, {
                        "mData": "bike_no"
                    }, {
                        "mData": "bike_model"
                    }, {
                        "mData": "station_name"
                    }, {
                        "mData": "issues_desc"
                    }, {
                        "mData": "req_by_name"
                    }, {
                        "mData": "create_date"
                    }, {
                        "data": null,
						render: function ( data, type, row ) {
                			return '<select id="actionId'+data.bike_id+'" class="form-control" style="font-size: 94%;"><option value="Select">Select</option><option value="F">Fixed</option><option value="U">Unrepairable</option></select>';
            			},
						 "targets": -1
                    }, {
                        "data": null,
						render: function ( data, type, row ) {
                			return '<button type="button" id="submitBtn" class="btn btn-block btn-info" style="font-size: 94%;">SUBMIT</button>';
            			},
						 "targets": -1
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
    });  /*End : fetch All Defect List*/


    $('#defectTable').on('click', 'tbody td', function() {
        var table = $('#defectTable').DataTable();
		var row = table.row($(this).parents('tr'));

		var tmp = table.row(this).data();
		var tmpBikeId = tmp.bike_id;
		var issueStatus = $('#actionId'+tmpBikeId).find(":selected").val();
		var bikeStatus="";
		
		if(issueStatus=="U"){
			bikeStatus="D";
		}
		else if(issueStatus=="F"){
			bikeStatus="A";
		}
		
		
        var bikejson = {
            "bike_id": tmpBikeId,
            "repair_status": issueStatus,
			"bike_status" : bikeStatus,
			"repairBy_email_id":sessionStorage.getItem('emailId')
        }

		if($(this).parent().children().index($(this)) == 8) {
		        $.ajax({
		            type: "POST",
					dataType: "json",
        			contentType: "application/json",
		            data: JSON.stringify(bikejson),
		            url: "updateDefectStatus.html",
		            success: function(response, textStatus, jqXHR) {
		                
						if(response.status=="SUCCESS"){
							//remove the row 
							//table.row( $(this).parents('tr') ).remove().draw();
							row.remove().draw();
						}
		            },
		
		            error: function(jqXHR, textStatus, errorThrown) {
		                if (jqXHR.status == "500") {
		                    window.location = "timeout.html";
		                } else if (jqXHR.status == "203") {
		                    window.location = "errorpage.html";
		                }
		            }
		        }); /*End : Update the defect*/
		}

    });
})