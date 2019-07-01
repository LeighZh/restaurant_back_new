$(document).ready(function () {
    //indexRoleSelect();
    queryUserInfo();
});
var icon = "<i class='fa fa-times-circle'></i>";
//重置form内的标签
function resetForm() {
    $(".form-horizontal input").val("");
    $(".form-horizontal select").val("");
    queryUserInfo();
}
function resetUserInfoDialog() {
    $("#myModal5 input").val("");
    $("#myModal5 select").val("");
    $("#myModal5 input").removeClass("error");
    $("#myModal5 select").removeClass("error");
    $("#myModal5 label.error").remove()
}
function queryUserInfo() {
    $.ajax({
        type: "POST",
        url: "/userServlet?judge=getAdmins",
        // contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data:{
            "id" :  $('#userAccount').val(),
            "name" : $('#userName').val(),
            "role" : $('#userRole').val(),
        },
        success:function (result) {
            console.log(result)
            //result = jQuery.parseJSON(result);
            //console.log(result)

            var dataTable = $('#userInfoTable');
            if ($.fn.dataTable.isDataTable(dataTable)) {
                dataTable.DataTable().destroy();
            }
            dataTable.DataTable({
                "serverSide": false,
                "autoWidth" : false,
                "bSort": false,
                "data" : result,
                "columns" : [{
                    "data" : "userId",
                },{
                    "data" : "userName",
                },{
                    "data" : "role",
                    "defaultContent": "管理员"

                }],
                "columnDefs": [{
                    "render" : function(data, type, row) {
                        var a = "";
                        a += "<button type='button' class='btn btn-primary' onclick='showEditUser(\""+row.userId+"\")' data-toggle='modal' data-target='#myModal5' title='编辑用户' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='deleteUser(\""+row.userId+"\")' title='删除用户' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-user-times'></i>&nbsp;删除</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='reSetPassord(\""+row.userId+"\")' data-toggle='modal' data-target='#resetPassword' title='重置密码' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-refresh'></i>&nbsp;重置密码</button>"
                        return a;
                    },
                    "targets" :3
                }]
            });
        }
    })
}

function deleteUser(id) {
    swal({
            title: "确认删除?",
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
                    url: "/userServlet?judge=delete",
                    // contentType: "application/json;charset=UTF-8",
                    dataType: "json",
                    data:{
                        "id" : id
                    },
                    success:function (result){
                        if(result){
                            queryUserInfo();
                            swal("删除成功！", "用户已被删除", "success");
                        }else{
                            swal("删除失败！", "用户暂时不能被删除", "error");
                        }

                    }
                })
            }else {
                swal("已取消", "你取消了删除用户操作", "error");
            }
        });
}
//展示用户编辑详情模态窗口
function showEditUser(id) {
    resetUserInfoDialog();
    if(id!=''){
        $("#dialogTitle").html("编辑用户")
         $("#dialogUserAccount").attr("readonly",true)
        $("#dialogUserAccount").val(id);

        $.ajax({
            type: "POST",
            url: "/userServlet?judge=getUsers",
            // contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data:{
                "id" :  id,
            },
            success:function (result){
                console.log(result)
                //result = jQuery.parseJSON(result);
                //console.log(result)
                if(result.length >= 0){
                    $("#dialogUserAccount").val(result[0].userId)
                    $("#dialogUserName").val(result[0].userName)
                    $("#dialogUserRole").val(result[0].role)
                }

            }
        })
    }else{
        $("#dialogUserAccount").attr("readonly",true)
        $("#dialogTitle").html("添加用户")
    }

}
//新增或更新用户信息
function saveOrUpdateUserInfo() {
    if($("#dialogUserInfo").validate({
        rules: {
            dialogUserName: {
                required: true,
                maxlength: 32
            }
        },
        messages: {
            dialogUserNamet: {
                required: icon + "登录名不能为空",
                minlength: icon + "登录名最长为32"
            }
        }
    }).form()){
        $.ajax({
            type: "POST",
            url: "/userServlet?judge=saveOrUpdateAdmin",
            dataType: "json",
            //contentType: "application/json;charset=UTF-8",
            data:{
                "id" : $("#dialogUserAccount").val(),
                "name" : $("#dialogUserName").val(),
                "role" : $("#dialogUserRole").val()
            },
            success:function (result){
                if(result){
                    queryUserInfo();
                    //关闭模态窗口
                    $('#myModal5').modal('hide');
                    swal("保存成功！", "success");
                }else{
                    swal("保存失败！", result.message, "error");
                }
            }
        });
    }
}

//重置弹出框的内容
function rePwdformReset() {
    $("#resetPassword input").val("");
    $("#resetPassword input").removeClass("error");
    $("#resetPassword label.error").remove()
}

function reSetPassord(id) {
    rePwdformReset();
    $("#resetPasswordUserId").val(id)
}

//保存重置的密码
function saveNewPassword() {
    if($("#resetPasswordForm").validate({
        rules: {
            newPassword: {
                required: true,
                minlength: 6
            },
            verifyPassword: {
                required: true,
                equalTo: "#newPassword"
            }
        },
        messages: {

            newPassword: {
                required: icon + "请填写新密码",
                minlength: icon + "密码最少为6位"
            },
            verifyPassword: {
                required: icon + "请再次输入新密码",
                equalTo: icon + "两次密码输入不一致"
            }
        }
    }).form()) {
        $.ajax({
            type: "POST",
            url: "/userServlet?judge=resetAdminPassword",
            // contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data:{
                "id" : $("#resetPasswordUserId").val(),
                "password" : $("#newPassword").val()
            },
            success:function (result){
                if(result){
                    //关闭模态窗口
                    $('#resetPassword').modal('hide');
                    swal("修改成功！", "密码已成功修改", "success");
                }else{
                    swal("修改失败！", "密码修改失败", "error");
                }

            }
        })
    }
}



