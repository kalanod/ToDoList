$(".form-check-input").change(function () {
    $.ajax({
        url: location.protocol + '//' + location.host + location.pathname + "/switch",
        method: 'POST',
        data: {
            id: this.id
        }
    });
})
$("#tmp").hide()
$(".btnAdd").click(function (){
    $("#tmp").val(this.id)
})
$("#root").click(function (){
    $("#tmp").val(this.id)
})
$(".doAdd").click(function (){
    $.ajax({
        url: location.protocol + '//' + location.host + location.pathname + "/add",
        method: 'POST',
        data: {
            id: $("#tmp").val(),
            title: $("#floatingInput").val()
        }
    });
    location.reload()
})
