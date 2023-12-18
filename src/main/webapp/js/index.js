$(".form-check-input").change(function () {
    $.ajax({
        url: "/ToDoList-1.0-SNAPSHOT/switch",
        method: 'POST',
        data: {
            id: this.id
        }
    });
})
var el;
$(".doShare").click(function (){
    $.ajax({
        url: "/ToDoList-1.0-SNAPSHOT/share",
        method: 'POST',
        data: {
            idUser2: $("#sharefloatingInput").val(),
            idElem: el
        }
    });
})
$(".shareBtn").click(function (){
    el = this.id.split("_")[1]
})

$(".form-select").hide()
$("#start").hide()
$("#tmp").hide()
$(".btnAdd").click(function (){
    $("#tmp").val(this.id)
    $(".form-select").val(null)
    $("#start").val(null)
})
$("#root").click(function (){
    $("#tmp").val(-1)
    $(".form-select").show()
    $("#start").show()
})
$(".close").click(function (){
    $(".form-select").hide()
    $("#start").hide()
});
$("#srtBtn").click(function (){

})
$(".doAdd").click(function (){
    $.ajax({
        url: "/ToDoList-1.0-SNAPSHOT/add",
        method: 'POST',
        data: {
            id: $("#tmp").val(),
            title: $("#floatingInput").val(),
            type: $(".form-select").val(),
            date: $("#start").val()
        }
    });
    location.reload()
})
