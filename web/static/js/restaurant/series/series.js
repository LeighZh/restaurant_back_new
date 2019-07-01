var $table = $('#subjectTable');
$(function() {
    querySubjectInfo();
});
function querySubjectInfo(){
    $.ajax({
        type: "POST",
        url: "/CookingServlet?select=query",
        dataType: "json",
        success:function (result) {
            console.log(result)

            var el = document.getElementById('series');
            var childs = el.childNodes;
            for(var i = childs .length - 1; i >= 0; i--) {
                el.removeChild(childs[i]);
            }

            for(var i = 0; i < result.length; i++){
                rowSeries(i + 1,result[i].vegetableId,result[i].vegetableName);
            }
        }
    });
}

function rowSeries(num,id,name){
    var series = "";
    series += '<li class="dd-item col-sm-12" >\n' +
        ' <div class="dd-handle col-sm-9">' + num + "--" + name +'</div>\n' +
        operateFormatter(id) +
        ' </li>'
    $("#series").append(series);
}

// 格式化按钮
function operateFormatter(id,name) {
    a = '<div class="col-sm-3">';
    a += '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal6" onclick="update(\''+id+'\')" style="margin-right:15px; margin-bottom: -1px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;修改</button>'
    a += '<button type="button" class="btn btn-primary" onclick="del('+id+')" style="margin-right:15px; margin-bottom: -1px;"><i class="fa fa-trash-o" ></i>&nbsp;删除</button>'
    a += '</div>'
    return a
}

function add(id,name) {
    resetAuthInfoEditDialog();
    $("#dialogAuthName").val(name);
}
function del(id) {
    swal({
            title: "将删除该分类，确认删除?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false
        },
        function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    type: "POST",
                    url: "/CookingServlet?select=delete",
                    dataType: "json",
                    data:{
                        "name" : id
                    },
                    success:function (result){
                        if(result){
                            querySubjectInfo();
                            swal("删除成功！", "该分类已被删除", "success");
                        }else{
                            swal("删除失败！", "该分类仍被部分餐品引用，暂时不能被删除", "error");
                        }

                    }
                })
            }else {
                swal("已取消", "你取消了删除权限操作", "error");
            }
        });
}
function resetAuthInfoEditDialog() {
    $("#myModal6 input").val("");
    // $("#dialogEditAuthUrl").removeAttr("readonly");
    $("#myModal6 input").removeClass("error");
    $("#myModal6 label.error").remove()
}
function update(id) {
    resetAuthInfoEditDialog();
    $("#dialogEditAuthId").val(id);
}
function saveSubjectInfo() {
        $.ajax({
            type: "POST",
            url: "/CookingServlet?select=add",
            //contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data: {
                name:$("#dialogAuthName").val(),
            },
            success: function (result) {
                console.log(result)
                if(result){
                    querySubjectInfo();
                    //关闭模态窗口
                    $('#myModal5').modal('hide');
                    swal("保存成功！", result.message, "success");
                }else{
                    swal("保存失败！", result.message, "error");
                }
            }
        });

}
function saveEditSubjectInfo() {
        $.ajax({
            type: "POST",
            url: "/CookingServlet?select=update",
           // contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data: {
                id: $("#dialogEditAuthId").val(),
                name:$("#dialogEditAuthName").val()
            },
            success: function (result) {
                if(result){
                    querySubjectInfo();
                    //关闭模态窗口
                    $('#myModal6').modal('hide');
                    swal("保存成功！", result.message, "success");
                }else{
                    swal("保存失败！", result.message, "error");
                }
            }
        });
}