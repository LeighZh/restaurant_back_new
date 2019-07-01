$(document).ready(function () {
    indexTimeSelect();
    indexSpendSelect();
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
        url: "/userServlet?judge=getUsers",
        // contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data:{
            "loginName" :  $('#userAccount').val(),
            "createTime" : $('#userCreateTime').val(),
            "money" : $('#userSpend').val(),
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
                    "data" : "createTime",
                },{
                    "data" : "trueName",
                    "defaultContent": "未知"
                },{
                    "data" : "phone",
                    "defaultContent":"未知"
                },{
                    "data" : "email",
                    "defaultContent": "未知"
                },{
                    "data" : "address",
                    "defaultContent": "未知"
                },{
                    "data" : "money",

                }],
                "columnDefs": [{
                    "render" : function(data, type, row) {
                        var a = "";
                        a += "<button type='button' class='btn btn-primary' onclick='showEditUser(\""+row.userId+"\")' data-toggle='modal' data-target='#myModal5' title='编辑用户' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='deleteUser(\""+row.userId+"\")' title='删除用户' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-user-times'></i>&nbsp;删除</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='reSetPassord(\""+row.userId+"\")' data-toggle='modal' data-target='#resetPassword' title='重置密码' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-refresh'></i>&nbsp;重置密码</button>"
                        return a;
                    },
                    "targets" :8
                }]
            });
        }
    })
}
function indexTimeSelect(){
    // 今天
    var today = new Date();
    today.setHours(0);
    today.setMinutes(0);
    today.setSeconds(0);
    today.setMilliseconds(0);

    var oneday = 1000 * 60 * 60 * 24;
    var timeSelectInfo = "";
    timeSelectInfo += "<option value='"+today.format("yyyy-MM-dd hh:mm:ss")+"'>"+"一天内"+"</option>";
    timeSelectInfo += "<option value='"+(new Date(today- oneday * (today.getDay() - 1))).format("yyyy-MM-dd hh:mm:ss")+"'>"+"一周内"+"</option>";
    timeSelectInfo += "<option value='"+(new Date(today - oneday * (today.getDate() - 1)).format("yyyy-MM-dd hh:mm:ss"))+"'>"+"一月内"+"</option>";
    timeSelectInfo += "<option value='"+(new Date("1 01," + today.getFullYear() + " 00:00:00")).format("yyyy-MM-dd hh:mm:ss")+"'>"+"一年内"+"</option>";
    timeSelectInfo += "<option value=0>"+"不限"+"</option>";
    $("#userCreateTime").append(timeSelectInfo);
}
function indexSpendSelect(){

    var SpendSelectInfo = "";
    SpendSelectInfo += "<option value='"+0+"'>"+"0"+"</option>";
    SpendSelectInfo += "<option value='"+100+"'>"+"<= 100"+"</option>";
    SpendSelectInfo += "<option value='"+1000+"'>"+"<= 1000"+"</option>";
    SpendSelectInfo += "<option value='"+10000+"'>"+"<= 10000"+"</option>";
    SpendSelectInfo += "<option value=0>"+"不限"+"</option>";
    $("#userSpend").append(SpendSelectInfo);
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
        // $("#dialogUserAccount").attr("readonly",true)
        $("#dialogUserId").val(id);

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
                    $("#dialogUserAccount").val(result[0].userName)
                    $("#dialogUserName").val(result[0].trueName)
                    $("#dialogUserPhone").val(result[0].phone)
                    $("#dialogUserEmail").val(result[0].email)
                    $("#dialogUserAddress").val(result[0].address)
                }

            }
        })
    }else{
        //$("#dialogUserAccount").attr("readonly",false)
        $("#dialogTitle").html("添加用户")
    }

}
//新增或更新用户信息
function saveOrUpdateUserInfo() {
    if($("#dialogUserInfo").validate({
        rules: {
            dialogUserAccount: {
                required: true,
                maxlength: 32
            },
            dialogUserPhone: {
                required: true,
                maxlength: 11
            }
        },
        messages: {
            dialogUserAccount: {
                required: icon + "登录名不能为空",
                minlength: icon + "登录名最长为32"
            },
            dialogUserPhone: {
                required: icon + "电话号码不能为空",
                equalTo: icon + "电话号码最长为11"
            }
        }
    }).form()){
        $.ajax({

            type: "POST",
            url: "/userServlet?judge=saveOrUpdateUser",
            dataType: "json",
            //contentType: "application/json;charset=UTF-8",
            data:{
                "id" : $("#dialogUserId").val(),
                "loginName" : $("#dialogUserAccount").val(),
                "trueName" : $("#dialogUserName").val(),
                "phone" : $("#dialogUserPhone").val(),
                "email" : $("#dialogUserEmail").val(),
                "address" : $("#dialogUserAddress").val()
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
            url: "/userServlet?judge=resetUserPassword",
            // contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data:{
                "id" : $("#resetPasswordUserId").val(),
                "newPassword" : $("#newPassword").val()
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

Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}


