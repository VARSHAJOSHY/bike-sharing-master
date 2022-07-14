/**
 * Authored by Varsha Joshy
 */
$(function() {

    for (var i = 0; i < 3; i++) {
        document.getElementById('custName' + (i + 1)).innerHTML = "";
        document.getElementById('review' + (i + 1)).innerHTML = "";
    }

    /*Start : fetch Top 3 review*/
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        url: "fetchTop5UserReview.html",
        success: function(response, textStatus, jqXHR) {

            if (response.result.length == 0) {
                para.value = "No Stations Avaiable Nearby";
            } else {

                for (var i = 0; i < 3; i++) {
                    document.getElementById('custName' + (i + 1)).innerHTML = response.result[i].user_first_name + " " + response.result[i].user_last_name;
                    document.getElementById('review' + (i + 1)).innerHTML = response.result[i].review_desc;

                    var para = document.getElementById('star' + (i + 1));

                    $("#star" + (i + 1)).starRating({
                        initialRating: response.result[i].review_no_of_stars,
                        readOnly: true,
                        strokeColor: '#894A00',
                        strokeWidth: 10,
                        starSize: 20
                    });
                }
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            /*  if (jqXHR.status == "500") {
                  window.location = "timeout.html";
              } else if (jqXHR.status == "203") {
                  window.location = "errorpage.html";
              }*/
        }
    }); /*End : fetch Top 3 review*/
})