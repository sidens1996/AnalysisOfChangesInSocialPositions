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
        var $jid = $tr.children("td").eq(0).find("input").attr("data-id")
        var $job_name = $tr.children("td").eq(1).text()
        var $salary = $tr.children("td").eq(2).text()
        var $job_city = $tr.children("td").eq(3).text()
        var $work_years = $tr.children("td").eq(4).text()
        var $degree_need = $tr.children("td").eq(5).text()
        var $tags = $tr.children("td").eq(6).text()
        var $company_field = $tr.children("td").eq(7).text()
        var $company_development = $tr.children("td").eq(8).text()
        var $company_scale = $tr.children("td").eq(9).text()
        var $publish_time = $tr.children("td").eq(10).text().substring(0,10)
        var $description = $tr.children("td").eq(11).text()
        //模态框表单的url动态加载
        $('#updateModal form').attr("action", "/admin/updateData?jid=" + $jid)
        $('#updateModal input:eq(0)').val($job_name)
        $('#updateModal input:eq(1)').val($salary)
        $('#updateModal input:eq(2)').val($job_city)
        $('#updateModal select:eq(0) option').filter(function(){return $(this).text()== $work_years;}).attr("selected",true);
        $('#updateModal select:eq(1) option').filter(function(){return $(this).text()== $degree_need;}).attr("selected",true);
        $('#updateModal input:eq(3)').val($tags)
        $('#updateModal input:eq(4)').val($company_field)
        $('#updateModal select:eq(2) option').filter(function(){return $(this).text()== $company_development;}).attr("selected",true);
        $('#updateModal select:eq(3) option').filter(function(){return $(this).text()== $company_scale;}).attr("selected",true);
        //description
        $('#updateModal textarea:eq(0)').val($description)
        $('#updateModal input:eq(5)').val($publish_time)
        //模态框出现
        $('#updateModal').modal('show');
    })
})

//删除按钮
$(function () {
    $("a.delete").on("click", function () {
        if(window.confirm('你确定要删除吗？')){
            var $tr = $(this).closest("tr")
            var $jid = $tr.children("td").eq(0).find("input").attr("data-id")
            // $.post("/admin/deleteUser",{uid:$uid});
            // $tr.remove()
            window.location.href="/admin/deleteData?jid=" + $jid
        }
    })
})

//批量删除
$(function () {
    $('#deletejobs').click(function() {
        var $checkboxs = $(".table td input:checked")
        if ($checkboxs.length > 0) {
            if(window.confirm('你确定要删除吗？')){
                var jids = ""
                for (var i = 0; i < $checkboxs.length; i++) {
                    jids += $checkboxs.eq(i).attr("data-id") + ","
                }
                jids = jids.substring(0, jids.length - 1)
                window.location.href="/admin/deleteDatas?jids=" + jids
            }
        } else {
            alert("请选择删除对象!");
        }
    });
});