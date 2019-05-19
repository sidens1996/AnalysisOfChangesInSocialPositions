$(function () {
    $("#begin").click(function () {
        $.getJSON("/admin/scriptBegin",function () {})
        alert("脚本开始!")
        $(this).addClass("disabled")
        $("#end").removeClass("disabled")
    })

    $("#end").click(function () {
        $.get("/admin/scriptEnd",function () {})
        alert("脚本结束!")
        $(this).addClass("disabled")
        $("#begin").removeClass("disabled")
    })
})