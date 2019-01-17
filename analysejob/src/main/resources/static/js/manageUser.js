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

//jquery监听修改按钮
$(function () {
    $("a.update").on("click", function () {
        var $tr = $(this).closest("tr")
        var $uid = $tr.children("td").eq(0).find("input").attr("data-id")
        var $username = $tr.children("td").eq(1).text()
        var $password = $tr.children("td").eq(2).text()
        var $sex = $tr.children("td").eq(3).text()
        var $age = $tr.children("td").eq(4).text()
        var $email = $tr.children("td").eq(5).text()
        var $telephone = $tr.children("td").eq(6).text()
        var $degree = $tr.children("td").eq(7).text()
        var $workyears = $tr.children("td").eq(8).text()
        var $profession = $tr.children("td").eq(9).text()
        var $role = $tr.children("td").eq(10).text()
        var $status = $tr.children("td").eq(11).text()
        var $realname = $tr.children("td").eq(12).text()
        var $description = $tr.children("td").eq(13).text()
        //模态框表单的url动态加载
        $('#updateModal form').attr("action", "/admin/updateUser?uid=" + $uid)
        $('#updateModal input:eq(0)').val($username)
        $('#updateModal input:eq(1)').val($password)
        $('#updateModal input:eq(2)').val($realname)
        //radio性别
        if ($sex == "男") {
            $('#updateModal input[value="男"]').attr("checked", true)
        } else if ($sex == "女") {
            $('#updateModal input[value="女"]').attr("checked", true)
        }
        $('#updateModal input:eq(5)').val($age)
        $('#updateModal input:eq(6)').val($email)
        $('#updateModal input:eq(7)').val($telephone)
        $('#updateModal select:eq(0) option').filter(function(){return $(this).text()== $degree;}).attr("selected",true);
        $('#updateModal select:eq(1) option').filter(function(){return $(this).text()== $workyears;}).attr("selected",true);
        $('#updateModal input:eq(8)').val($profession)
        $('#updateModal select:eq(2) option').filter(function(){return $(this).text()== $role;}).attr("selected",true);
        $('#updateModal select:eq(3) option').filter(function(){return $(this).text()== $status;}).attr("selected",true);
        //description
        $('#updateModal textarea:eq(0)').val($description)
        //模态框出现
        $('#updateModal').modal('show');
    })
})

//删除按钮
$(function () {
    $("a.delete").on("click", function () {
        if(window.confirm('你确定要删除吗？')){
            var $tr = $(this).closest("tr")
            var $uid = $tr.children("td").eq(0).find("input").attr("data-id")
            // $.post("/admin/deleteUser",{uid:$uid});
            // $tr.remove()
            window.location.href="/admin/deleteUser?uid=" + $uid
        }
    })
})

//批量删除
$(function () {
    $('#deleteusers').click(function() {
        var $checkboxs = $(".table td input:checked")
        if ($checkboxs.length > 0) {
            if(window.confirm('你确定要删除吗？')){
                var uids = ""
                for (var i = 0; i < $checkboxs.length; i++) {
                    uids += $checkboxs.eq(i).attr("data-id") + ","
                    $checkboxs.eq(i).closest("tr").remove()
                }
                uids = uids.substring(0, uids.length - 1)
                // $.post("/admin/deleteUsers", {uids:uids})
                window.location.href="/admin/deleteUsers?uids=" + uids
            }
        } else {
            alert("请选择删除对象!");
        }
    });
});