/**
 * 
 */
$(function() {

    $("#rideId").val("");
    $("#bikeId").val("");
    $("#model").val("");
    $("#sLoc").val("");


    $("#reviewStar").starRating({
        initialRating: 0,
        strokeColor: '#894A00',
        strokeWidth: 10,
        starSize: 20,
        callback: function(currentRating, $el) {
            $("#starMark").val(currentRating);
        }
    })

    $("#destinationDiv").hide();
    document.getElementById("cost").innerHTML = "";
    document.getElementById("successMsg").innerHTML = "";

    var today = new Date();
    var date = today.getDate() + '-' + (today.getMonth() + 1) + '-' + today.getFullYear();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    document.getElementById('loginInfo').innerHTML = sessionStorage.getItem('emailId');
    document.getElementById('loginTimeInfo').innerHTML = date + " : " + time;


    /*Start : fetch details of active ride*/

    var userjson = {
        "email_id": sessionStorage.getItem('emailId')
    }

    $.ajax({
        type: "POST",
        data: JSON.stringify(userjson),
        url: "fetchActiveRideDetailsByEmailId.html",
        dataType: "json",
        contentType: "application/json",
        success: function(response, textStatus, jqXHR) {

            //var resultObj = JSON.parse(response);
            if (response.result.length == 0) {
                document.getElementById("successMsg").innerHTML = "No Active rides are currently available.";
            } else {
				$("#reqId").val(response.result[0].booking_id)
                $("#rideId").val(response.result[0].booking_no);
                $("#bikeId").val(response.result[0].bike_id);
                $("#model").val(response.result[0].bike_model);
                $("#sLoc").val(response.result[0].begin_station_name);
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


    $("#stopBtn").click(function() {
        //display destination station in drop down
        $("#destinationDiv").show();
        $("#rideBtn").hide();
        $("#stopBtn").hide();

        $.ajax({
            type: "POST",
            url: "fetchAllStations.html",
            dataType: "json",
            contentType: "application/json",
            success: function(response, textStatus, jqXHR) {

                if (response.result.length == 0) {
                    if (null != para) {
                        para.value = "No Stations Avaiable Nearby";
                    }
                } else {

                    var select = document.getElementById("destStationId");
                    var option = document.createElement("option");
                    option.value = "Select";
                    option.text = "Select Station";
                    select.appendChild(option);

                    for (var i = 0; i < response.result.length; i++) {
                        var option = document.createElement("option");
                        option.value = response.result[i].station_id;
                        option.text = response.result[i].station_name;
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
        }); /*End : fetch details of active ride*/

    })

    $("#goBtn").click(function() {

        var ridejson = {
            "ride_id": $("#rideId").val(),
            "dest_loc": $("#destStationId").val()
        }

        $.ajax({
            type: "POST",
            data: JSON.stringify(ridejson),
            url: "calculateCost.html",
            dataType: "json",
            contentType: "application/json",
            success: function(response, textStatus, jqXHR) {

                dialog.dialog("open");
                document.getElementById("cost").innerHTML = response.ride_cost;
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

    var dialog = $("#dialog-form").dialog({
        autoOpen: false,
        height: 390,
        width: 500,
        modal: true,
        buttons: {
            "PAY": function() {

                var ridejson = {
                    "ride_id": $("#rideId").val(),
                    "dest_loc": $("#destStationId").val()
                }

                $.ajax({
                    type: "POST",
                    url: "endRide.html",
                    data: JSON.stringify(ridejson),
                    dataType: "json",
                    contentType: "application/json",
                    success: function(response, textStatus, jqXHR) {

                        if (response.status == "SUCCESS") {
                            dialog.dialog("close");
                            document.getElementById("cost").innerHTML = "";
                            $(".content").hide();
                            document.getElementById("successMsg").innerHTML = "Your ride has been completed succesfully. Thank you for riding with Uu! PLEASE RATE US!";

                            $("#rate-dialog-form").dialog("open");
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
            }
        },
        close: function() {
            //form[ 0 ].reset();
            //allFields.removeClass( "ui-state-error" );
        }
    })


    var ratedialog = $("#rate-dialog-form").dialog({
        autoOpen: false,
        height: 300,
        width: 500,
        modal: true,
        buttons: {
            "SUBMIT": function() {

                var ridejson = {
                    "booking_id": $("#reqId").val(),
                    "review_desc": $("#issueDesc").val(),
                    "email_id": sessionStorage.getItem('emailId'),
                    "no_of_stars": $("#starMark").val()
                }

                $.ajax({
                    type: "POST",
                    data: JSON.stringify(ridejson),
                    url: "reviewRide.html",
                    dataType: "json",
                    contentType: "application/json",
                    success: function(response, textStatus, jqXHR) {
                        ratedialog.dialog("close");
						document.getElementById("successMsg").innerHTML = "Thank you for your feedback!";
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status == "500") {
                            window.location = "timeout.html";
                        } else if (jqXHR.status == "203") {
                            window.location = "errorpage.html";
                        }
                    }
                }); /*End : fetch details of active ride*/
            },
            "CANCEL": function() {
                 ratedialog.dialog("close");
            }
        }
    })
})