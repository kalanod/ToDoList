$(".form-check-input").change(function () {
    $.ajax({
        url: "/Gradle___com_calanco___ToDoList_1_0_SNAPSHOT_war/switch",
        method: 'POST',
        data: {
            id: this.id
        }
    });
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
        url: "/Gradle___com_calanco___ToDoList_1_0_SNAPSHOT_war/add",
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
