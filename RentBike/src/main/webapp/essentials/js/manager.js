/**
 * Authored by varsha Joshy (03-11-2021)
 */
$(function() {

    var today = new Date();
    var date = today.getDate() + '-' + (today.getMonth() + 1) + '-' + today.getFullYear();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    document.getElementById('loginInfo').innerHTML = sessionStorage.getItem('emailId');
    document.getElementById('loginTimeInfo').innerHTML = date + " : " + time;
})