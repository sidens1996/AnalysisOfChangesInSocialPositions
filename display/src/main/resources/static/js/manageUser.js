<!--jquery实现全选框按钮-->
$(function () {
    var flag = true;
    $('#selectall').click(function () {
        if (flag) {
            $('input:checkbox').prop("checked", true);
            flag = false;
        } else {
            $('input:checkbox').prop("checked", false);
            flag = true;
        }
    });
});

//编辑按钮确定选择input的数量
$(function() {
    $('#updateuser').click(function () {
        if($('input:checked').length === 1) {
            var userid = $('input:checked').parent().next().text();
            $('#updateModal form').attr("action","updateuser.action?userid=" + userid);
            $('#updateModal input:first').val($('input:checked').parent().next().next().text());
            $('#updateModal input:eq(1)').val($('input:checked').parent().next().next().next().text());
            $('#updateModal input:last').val($('input:checked').parent().next().next().next().next().text());
            var position = $('input:checked').parent().next().next().next().next().next().text();
            $('#updateModal option').filter(function(){return $(this).text()== position;}).attr("selected",true);
            $('#updateModal').modal('show');
        }else {
            alert("编辑时只能选1个对象！");
        }
    });
});

//删除按钮
$(function () {
    $('#deleteuser').click(function() {
        if ($('input:checked').length == 1) {
            var userid = $('input:checked').parent().next().text();
            $.post("deleteuser.action",{userid:userid});
            $('input:checked').parent('td').parent().remove();
            alert("删除成功!");
        } else {
            alert('请选择删除对象!')
        }
    });
});

// //提交新增用户数据
// $(function () {
//     $('#addModal').on("submit",function (e) {
//         var username = document.getElementsByName("username").value
//         var password = document.getElementsByName("password").value
//         var realName = document.getElementsByName("realname").value
//         var sex = document.getElementsByName("gender").value
//
//         var data = $("#addUser").serialize()
//         var submitData = decodeURIComponent(data,true)
//
//         $.ajax({
//             url:"",
//             data:submitData,
//             cache:false,
//             async:true,
//             success:function (data,textStatus,jqXHR) {
//
//             },
//
//             error:function (jqXHR,status,error) {
//                 console.log(status)
//             }
//         })
//
//
//     })
// })