$( document ).ready(function() {
    console.log( "ready!" );

$('#url-submit').click(function(event) {
    if($('#username').val() === "" || $('#url').val() === ""){
        $("#url-response-holder").hide();
        $("#error-holder").show();
        return;
    }
    $.fn.urlSubmit({"user": $('#username').val(), "url": $('#url').val()});
    event.preventDefault();
});

$.fn.urlSubmit = function (formElement) {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: "/service/shorten",
        data: JSON.stringify(formElement),
        success: function (response) {
            $("#error-holder").hide();
            $("#url-response-holder").show();
            const elem = $("#url-link");
            elem.attr("href", "/s/" + response);
            elem.text( "http://localhost:8080/s/" + response);
        },
        error: function (error) {
            console.log("error")
        }
    });
};

});