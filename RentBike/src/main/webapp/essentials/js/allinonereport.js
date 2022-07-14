/**
 * 
 */
$(function() {

    var today = new Date();
    var date = today.getDate() + '-' + (today.getMonth() + 1) + '-' + today.getFullYear();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    document.getElementById('loginInfo').innerHTML = sessionStorage.getItem('emailId');
    document.getElementById('loginTimeInfo').innerHTML = date + " : " + time;

    //getReportData method name

    $("#tabs").tabs({
        collapsible: true
    });

    $("#generateBtn1").click(function() {
        var year = $('#year1').find(":selected").val();
        var month = $('#month1').find(":selected").val();

        var monthText = getMonthName(month);

        var datajson = {
            "content": {
                "year": year,
                "month": month
            },
            "method": "getActiveStationReport"
        }

        $.ajax({
            type: "POST",
            data: JSON.stringify(datajson),
            url: "getReportData.html",
            dataType: "json",
            contentType: "application/json",
            success: function(response, textStatus, jqXHR) {

                var data = new Array();
                for (var i = 0; i < response.result.length; i++) {
                    var obj = {
                        label: response.result[i].stationName,
                        y: response.result[i].noOfBikes
                    };
                    data.push(obj);
                }

                var myText = "Station wise Rides during " + monthText + " " + year;

                var chart1 = new CanvasJS.Chart("stationBar", {
                    theme: "theme3",
                    animationEnabled: true,
                    title: {
                        text: myText,
                        fontSize: 15,
                        fontColor: "black"
                    },
                    width: 500,
                    axisX: {
                        title: "Bike Stations",
                        labelAngle: 45,
                        titleFontSize: 12,
                        titleFontColor: "black"
                    },
                    axisY: {
                        title: "Number of Rides",
                        titleFontSize: 12,
                        titleFontColor: "black"
                    },
                    legend: {
                        verticalAlign: "bottom",
                        horizontalAlign: "center"
                    },
                    data: [{
                        type: "column",
                        showInLegend: true,
                        dataPoints: data
                    }]
                });
                chart1.render();


                var chart2 = new CanvasJS.Chart("stationPie", {
                    animationEnabled: true,
                    title: {
                        text: "Station wise Rides during " + monthText + " " + year,
						fontSize: 15,
                    },
                    data: [{
                        type: "pie",
                        startAngle: 240,
                        yValueFormatString: "##0.00\"%\"",
                        indexLabel: "{label} {y}",
                        dataPoints: data
                    }]
                });
                chart2.render();
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

    $("#generateBtn2").click(function() {
        var year = $('#year1').find(":selected").val();
        var month = $('#month1').find(":selected").val();

        var monthText = getMonthName(month);

        var datajson = {
            "content": {
                "year": year,
                "month": month
            },
            "method": "getActiveBikeReport"
        }

        $.ajax({
            type: "POST",
            data: JSON.stringify(datajson),
            dataType: "json",
            contentType: "application/json",
            url: "getReportData.html",
            success: function(response, textStatus, jqXHR) {

                if (response.result.length > 0) {

                    var data = new Array();
                    for (var i = 0; i < response.result.length; i++) {
                        var obj = {
                            label: response.result[i].bikeName,
                            y: response.result[i].noOfBikes
                        };
                        data.push(obj);
                    }

                    var myText = "Bike wise Rides during " + monthText + " " + year;

                    var chart1 = new CanvasJS.Chart("bikeBar", {
                        theme: "theme3",
                        animationEnabled: true,
                        title: {
                            text: myText,
                            fontSize: 15,
                            fontColor: "black"
                        },
                        width: 500,
                        axisX: {
                            title: "Bike Numbers",
                            labelAngle: 45,
                            titleFontSize: 12,
                            titleFontColor: "black"
                        },
                        axisY: {
                            title: "Number of Rides",
                            titleFontSize: 12,
                            titleFontColor: "black"
                        },
                        legend: {
                            verticalAlign: "bottom",
                            horizontalAlign: "center"
                        },
                        data: [{
                            type: "column",
                            showInLegend: true,
                            dataPoints: data
                        }]
                    });
                    chart1.render();


					 var chart2 = new CanvasJS.Chart("bikePie", {
		                    animationEnabled: true,
		                    title: {
		                        text: "Bike wise Rides during " + monthText + " " + year,
								fontSize: 15,
		                    },
		                    data: [{
		                        type: "pie",
		                        startAngle: 240,
		                        yValueFormatString: "##0.00\"%\"",
		                        indexLabel: "{label} {y}",
		                        dataPoints: data
		                    }]
		                });
		                chart2.render();
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

    $("#generateBtn3").click(function() {
        var year = $('#year3').find(":selected").val();

        var datajson = {
            "content": {
                "year": year
            },
            "method": "getProfitReport"
        }

        $.ajax({
            type: "POST",
            data: JSON.stringify(datajson),
            dataType: "json",
            contentType: "application/json",
            url: "getReportData.html",
            success: function(response, textStatus, jqXHR) {

                if (response.result.length > 0) {

                    var data = new Array();
                    for (var i = 0; i < response.result.length; i++) {
                        var obj = {
                            label: getMonthName(response.result[i].month),
                            y: parseFloat(response.result[i].profit)
                        };
                        data.push(obj);
                    }

                    var myText = "Month wise profit earned during " + year;

                    var chart1 = new CanvasJS.Chart("profitBar", {
                        theme: "theme3",
                        animationEnabled: true,
                        title: {
                            text: myText,
                            fontSize: 15,
                            fontColor: "black"
                        },
                        width: 500,
                        axisX: {
                            title: "Bike Stations",
                            labelAngle: 45,
                            titleFontSize: 12,
                            titleFontColor: "black"
                        },
                        axisY: {
                            title: "Number of Rides",
                            titleFontSize: 12,
                            titleFontColor: "black"
                        },
                        legend: {
                            verticalAlign: "bottom",
                            horizontalAlign: "center"
                        },
                        data: [{
                            type: "column",
                            showInLegend: true,
                            dataPoints: data
                        }]
                    });
                    chart1.render();

					var chart2 = new CanvasJS.Chart("profitPie", {
	                    animationEnabled: true,
	                    title: {
	                        text:  "Month wise profit earned during " + year,
							fontSize: 15,
	                    },
	                    data: [{
	                        type: "pie",
	                        startAngle: 240,
	                        yValueFormatString: "##0.00\"%\"",
	                        indexLabel: "{label} {y}",
	                        dataPoints: data
	                    }]
	                });
	                chart2.render();
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

    $("#generateBtn4").click(function() {
        var year = $('#year4').find(":selected").val();
        var month = $('#month4').find(":selected").val();
		var monthText = getMonthName(month);       

        var datajson = {
            "content": {
                "year": year,
                "month": month
            },
            "method": "getDefectedBikeReport"
        }

        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(datajson),
            url: "getReportData.html",
            success: function(response, textStatus, jqXHR) {

                if (response.result.length > 0) {

                    var data = new Array();
                    for (var i = 0; i < response.result.length; i++) {
                        var obj = {
                            label: response.result[i].stationName,
                            y: parseFloat(response.result[i].noOfBikes)
                        };
                        data.push(obj);
                    }

		                var myText = "Station wise defect report during " + monthText + " " + year;
		
		                var chart1 = new CanvasJS.Chart("defectBar", {
		                    theme: "theme3",
		                    animationEnabled: true,
		                    title: {
		                        text: myText,
		                        fontSize: 15,
		                        fontColor: "black"
		                    },
		                    width: 500,
		                    axisX: {
		                        title: "Bike Stations",
		                        labelAngle: 45,
		                        titleFontSize: 12,
		                        titleFontColor: "black"
		                    },
		                    axisY: {
		                        title: "Number of Rides",
		                        titleFontSize: 12,
		                        titleFontColor: "black"
		                    },
		                    legend: {
		                        verticalAlign: "bottom",
		                        horizontalAlign: "center"
		                    },
		                    data: [{
		                        type: "column",
		                        showInLegend: true,
		                        dataPoints: data
		                    }]
		                });
		                chart1.render();


						 var chart2 = new CanvasJS.Chart("defectPie", {
		                    animationEnabled: true,
		                    title: {
		                        text: "Station wise defect report during " + monthText + " " + year,
								fontSize: 15,
		                    },
		                    data: [{
		                        type: "pie",
		                        startAngle: 240,
		                        yValueFormatString: "##0.00\"%\"",
		                        indexLabel: "{label} {y}",
		                        dataPoints: data
		                    }]
		                });
		                chart2.render();
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


function getMonthName (month){
	
	var monthText = "";

        if (month == "1") {
            monthText = "Jan"
        } else if (month == "2") {
            monthText = "Feb"
        } else if (month == "3") {
            monthText = "Mar"
        } else if (month == "4") {
            monthText = "Apr"
        } else if (month == "5") {
            monthText = "May"
        } else if (month == "6") {
            monthText = "Jun"
        } else if (month == "7") {
            monthText = "Jul"
        } else if (month == "8") {
            monthText = "Aug"
        } else if (month == "9") {
            monthText = "Sep"
        } else if (month == "10") {
            monthText = "Oct"
        } else if (month == "11") {
            monthText = "Nov"
        } else if (month == "12") {
            monthText = "Dec"
        }

	return monthText;
}