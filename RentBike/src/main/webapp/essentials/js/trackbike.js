/**
 * 
 */
$(function() {

    var today = new Date();
    var date = today.getDate() + '-' + (today.getMonth() + 1) + '-' + today.getFullYear();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    document.getElementById('loginInfo').innerHTML = sessionStorage.getItem('emailId');
    document.getElementById('loginTimeInfo').innerHTML = date + " : " + time;

	document.getElementById('msg').innerHTML = "";


    $.ajax({
        type: "POST",
        url: "fetchAllStations.html",
        success: function(response, textStatus, jqXHR) {

            var resultObj = JSON.parse(response);

            if (resultObj.result.length == 0) {
                if (null != para) {
                    para.value = "No Stations Avaiable Nearby";
                }
            } else {
				$("#stationId").empty();
                var select = document.getElementById("stationId");
                var option = document.createElement("option");
                option.value = "Select";
                option.text = "Select Station";
                select.appendChild(option);

                for (var i = 0; i < resultObj.result.length; i++) {
                    var option = document.createElement("option");
                    option.value = resultObj.result[i].station_id;
                    option.text = resultObj.result[i].station_name;
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


    $("#trackBtn").click(function() {
	
		document.getElementById('msg').innerHTML  = "";
        var bikeId = $('#bikeId').find(":selected").val();
        var bikejson = {
            "bike_id": bikeId
        }
        $.ajax({
            type: "POST",
            data: JSON.stringify(bikejson),
            dataType: "json",
            contentType: "application/json",
            url: "getPastStationIds.html",
            success: function(response, textStatus, jqXHR) {

                if (response.stations.length == 0) {                   
                       document.getElementById('msg').innerHTML  = "No Ride History Available";
						$("#trackDiag").hide();
                  
                } else {
					$("#trackDiag").show();
					var data=new Array();
					for(var i=0;i<response.stations.length;i++){
						var obj = {
    						label: (i+1) +" : "+response.stations[i],
    						y: 1
						};
						data.push(obj);
					}

                    var chart = new CanvasJS.Chart("trackDiag", {
                        theme: "light2",
                        title: {
                            text: "Tracking Details of Bike ID " + bikeId +" & Bike No "+$('#bikeId').find(":selected").text()
                        },
                        axisX: {
                            gridThickness: 0,
                            tickLength: 0,
                            lineThickness: 0,
                            labelFormatter: function() {
                                return " ";
                            }
                        },
                        axisY: {
                            gridThickness: 0,
                            tickLength: 0,
                            lineThickness: 0,
                            labelFormatter: function() {
                                return " ";
                            }
                        },
                        data: [{
                            type: "line",
                            dataPoints: data
                        }]
                    });
                    chart.render();
                
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
                if (null != para) {
                    para.value = "No Stations Avaiable Nearby";
                }
            } else {
				$("#bikeId").empty();
                var select = document.getElementById("bikeId");
                var option = document.createElement("option");
                option.value = "Select";
                option.text = "Select Bike";
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
    }); /*End : fetch All Nearby Stations*/

}