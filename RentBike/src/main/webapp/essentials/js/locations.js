/**
 * 
 */
$(function() {
    let map;
})


function Zipcode_to_LatLng(zipcode,loc) {

  // var loc = "";
	var myurl = "http://api.postcodes.io/postcodes/" + zipcode;
    $.ajax({
        type:"GET",
        url: myurl,
        dataType: "json",
        contentType: "application/json",
        
		success: function(data) {
			alert(data);
 			loc = { position: new google.maps.LatLng(data.result.latitude, data.result.longitude)};
            //return loc;
        },
        error: function(errorMsg) {

            alert("error");
        }
    });
}

function initMap() {
	
	//var station_zipcode = ["G116EU", "G116DN"];
    var myLatlng = new google.maps.LatLng(55.86729, -4.250057) //"postcode":"G4 0BA"
    map = new google.maps.Map(document.getElementById("map"), {
        center: myLatlng,
        zoom: 12,
    });

    const icon_Bike = {
        url: "essentials/css/bicycle.svg",
        scaledSize: new google.maps.Size(40, 40)
    }
    const features = [{
            position: new google.maps.LatLng(55.886078, -4.300397),//"postcode":"G12 0QH"
        },
        {
            position: new google.maps.LatLng(55.885182, -4.299257),//"postcode":"G12 0QA"
        },
        {
            position: new google.maps.LatLng(55.859243, -4.254883),//"postcode":"G1 3HL"
        },
        {
            position: new google.maps.LatLng(55.871751, -4.28836),//"postcode":"G12 8QQ"
        },
        {
            position: new google.maps.LatLng(55.870544, -4.308733),//"postcode":"G11 6BU"
        }
    ];

    // Create markers.
    new google.maps.Marker({
        position: myLatlng,
        map: map,
    });

	/*var locArray = new Array();
		
    for (let i = 0; i < station_zipcode.length; i++) {
			var out="";
        	Zipcode_to_LatLng(station_zipcode[i], function(outloc){
				out= outloc;
			});
			alert("inside for loop:"+out);
			locArray.push(out);
    }*/

    for (let i = 0; i < features.length; i++) {
        const marker = new google.maps.Marker({
            position: features[i].position,
            icon: icon_Bike,
            map: map,
        });
    }
}