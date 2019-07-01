$(document).ready(function () {
   getUserName();
});

$(function(){
    //菜单点击
    J_iframe
    $(".J_menuItem").on('click',function(){
        var url = $(this).attr('href');
        $("#J_iframe").attr('src',url);
        return false;
    });

});

//设置主界面上的用户名
function getUserName(){
    $.ajax({
        url: "/userServlet",
        data:"judge=getSession",
        xhrFields: {withCredentials: true},
        success: function(data){
            console.log(data)
            data == "null" ? window.location.href='/restaurant/login.html' :$('#userName').text(data);
            //$('#userName').text(data);
        }
    });
}


//注销功能
$('#a_logout').click(function () {
    swal({
            title: "确认注销?",
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
                    url: "/userServlet",
                    data:"judge=loginOut",
                    xhrFields: {withCredentials: true},
                    success: function(data){
                        console.log(data)
                        window.location.href='/login.html';
                    }
                });
               //
            }else {
                swal("已取消", "", "error");
            }
        });
});

//重置弹出框的内容
function formReset() {
    $("#resetPassword input").val("");
    $("#resetPassword input").removeClass("error");
    $("#resetPassword label.error").remove()
}

//保存重置的密码
function saveNewPassword() {
    if(validform().form()) {
        $.ajax({
            type: "POST",
            url: "/userServlet?judge=resetPassword",
            // contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data:{
                "password" : $("#newPassword").val(),
                "name": $('#userName').text()
            },
            success:function (result){
                if(result){
                    //关闭模态窗口
                    $('#resetPassword').modal('hide');
                    swal("修改成功！", "密码已成功修改", "success");
                }else{
                    swal("修改失败！", "密码修改失败", "error");
                }
            },
            error:function (e) {
                console.log(e)
            }
        })
    }
}

//对重置密码弹窗中的数据进行校验
function validform() {
    var icon = "<i class='fa fa-times-circle'></i>";
    return $("#resetPasswordForm").validate({
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
    });
}