/**
 * Authored by Varsha Joshy
 */
$(function() {

    $("#changeBikeTable").hide();
    $("#newLocTable").hide();
    document.getElementById("successMsg").innerHTML = "";

	var today = new Date();
	var date = today.getDate() +'-'+ (today.getMonth()+1) +'-'+ today.getFullYear();
	var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	document.getElementById('loginInfo').innerHTML = sessionStorage.getItem('emailId');	
	document.getElementById('loginTimeInfo').innerHTML = date +" : "+time;	

    var stations;

	/*Start : fetch All Stations*/
    $.ajax({
        type: "POST",
        url: "fetchAllStations.html",
        success: function(response, textStatus, jqXHR) {
            stations = JSON.parse(response);
            if (stations.result.length == 0) {
                document.getElementById("successMsg").innerHTML = "No Stations Available"; 
				$("#bikeId").val("Select")        
            } else {

                var select = document.getElementById("stationId");
                var option = document.createElement("option");
                option.value = "Select";
                option.text = "Select Station";
                select.appendChild(option);

                for (var i = 0; i < stations.result.length; i++) {
                    var option = document.createElement("option");
                    option.value = stations.result[i].station_id;
                    option.text = stations.result[i].station_name;
                    select.appendChild(option);
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
    }); /*End : fetch All Stations*/


	/*Start : fetch details of selected bike*/
    $("#fetchLocBtn").click(function() {

        var bikeId = $('#bikeId').find(":selected").val();

        var bikejson = {
            "bike_id": bikeId
        }

        $.ajax({
            type: "POST",
            data: JSON.stringify(bikejson),
            dataType: "json",
            contentType: "application/json",
            url: "getBikeDetailsByBikeId.html",
            success: function(response, textStatus, jqXHR) {

                //var resultObj = JSON.parse(response);
                $("#changeBikeTable").show();

                $("#bikeId2").val(response.result[0].bikeId);
                $("#bikeNo").val(response.result[0].bikeNumber);
                $("#model").val(response.result[0].modelName);
                $("#spec").val(response.result[0].spec);
                $("#curLoc").val($("#stationId").find(":selected").text());

				if(response.result[0].bikeStatus=="A"){
					 $("#status").val("Available");
				}
				else if(response.result[0].bikeStatus=="N"){
					 $("#status").val("Not Available");
				}
				else if(response.result[0].bikeStatus=="D"){
					 $("#status").val("Defective");
				}
				else{
					 $("#status").val("Available");
				}
            },

            error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == "500") {
                    window.location = "timeout.html";
                } else if (jqXHR.status == "203") {
                    window.location = "errorpage.html";
                }
            }
        }); /*End : fetch details of selected bike*/
    })

    $("#locChangeBtn").click(function() {
        $("#newLocTable").show();

        var select = document.getElementById("newStationId");
        var option = document.createElement("option");
        option.value = "Select";
        option.text = "Select Station";
        select.appendChild(option);


 		for (var i = 0; i < stations.result.length; i++) {
                    var option = document.createElement("option");
                    option.value = stations.result[i].station_id;
                    option.text = stations.result[i].station_name;
                    select.appendChild(option);
         }

    })

    $("#submitBtn").click(function() {

        var bikejson = {
            "bike_id": $("#bikeId").val(),
            "owner_station": $("#newStationId").find(":selected").val()
        }

        $.ajax({
            type: "POST",
            data: JSON.stringify(bikejson),
            dataType: "json",
            contentType: "application/json",
            url: "updateBikeLocation.html",
            success: function(response, textStatus, jqXHR) {

                if(response.status=="SUCCESS"){
					$("#changeRespDiv").hide();
                	document.getElementById("successMsg").innerHTML = "Bike Location changed succesfully.";
                	document.getElementById("stationId").selectedIndex = "0";
                	document.getElementById("bikeId").selectedIndex = "0";	
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
    })
})


/*Start : fetch All bikes available in selected Station*/
function stationChange() {
    var stationId = $('#stationId').find(":selected").val();
    var stationjson = {
        "station_id": stationId
    }

    $.ajax({
        type: "POST",
        data: JSON.stringify(stationjson),
        dataType: "json",
        contentType: "application/json",
        url: "fetchAllBikesByStationId.html",
        success: function(response, textStatus, jqXHR) {

            if (response.result.length == 0) {
              	document.getElementById("successMsg").innerHTML = "No Bikes are available at selected Station";  
				$("#bikeId").val("Select")     
            } else {

                var select = document.getElementById("bikeId");
                var option = document.createElement("option");
                option.value = "Select";
                option.text = "Select Location";
                select.appendChild(option);

                for (var i = 0; i < response.result.length; i++) {
                    var option = document.createElement("option");
                    option.value = response.result[i].bikeId;
                    option.text = response.result[i].bikeNumber;
                    select.appendChild(option);
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
    }); /*End : fetch All bikes available in selected Station*/

}