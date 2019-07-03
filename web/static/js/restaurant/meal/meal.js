$(document).ready(function () {
    indexSeriesSelect();
    queryUserInfo();
});
var icon = "<i class='fa fa-times-circle'></i>";
//重置form内的标签
function resetForm() {
    $(".form-horizontal input").val("");
    $(".form-horizontal select").val("");
    queryUserInfo();
}
function resetUserInfoDialog(id) {
    $("#myModal5 input").val("");
    $("#myModal5 select").val("");
    $("#myModal5 input").removeClass("error");
    $("#myModal5 select").removeClass("error");
    $("#myModal5 label.error").remove()
}
function queryUserInfo() {
    $.ajax({
        type: "POST",
        url: "/MenuServlet?judge=query",
        // contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data:{
            "id" :  $('#id').val(),
            "name" : $('#name').val(),
            "series" : $('#series').val(),
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
                    "data" : "mealId",
                },{
                    "data" : "mealName",
                },{
                    "data" : "seriesName",
                    "defaultContent": "无"
                },{
                    "data" : "mealSummarize",
                    "defaultContent": "无"
                },{
                    "data" : "mealPrice",
                    "defaultContent": "未知"
                },{
                    "data" : "mealImage",
                    "defaultContent": "无"
                }],
                "columnDefs": [{
                    "render" : function(data, type, row) {
                        var a = "";
                        a += "<button type='button' class='btn btn-primary' onclick='showEditUser(\""+row.mealId+"\")' data-toggle='modal' data-target='#myModal5' title='编辑用户' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='deleteUser(\""+row.mealId+"\")' title='删除用户' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-user-times'></i>&nbsp;删除</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='showDetail(\""+row.mealId+"\")' data-toggle='modal' data-target='#resetPassword' title='菜品详情' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-list'></i>&nbsp;详情</button>"
                        return a;
                    },
                    "targets" :6
                }]
            });
        }
    })
}
function indexSeriesSelect() {
    $.ajax({
        type: "POST",
        url: "/CookingServlet?select=query",
        dataType: "json",
        success:function (result){
            console.log("indexSeries：" + result)
            var roleSelectInfo = "";
            for (var i=0; i<result.length; i++){
                roleSelectInfo += "<option value='"+result[i].vegetableId+"'>"+result[i].vegetableName+"</option>"
            }
            $("#series").append(roleSelectInfo);
            $("#dialogSeries").append(roleSelectInfo)
            $("#dialogMealSeries").append(roleSelectInfo)
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
                    url: "/MenuServlet?judge=delete",
                    // contentType: "application/json;charset=UTF-8",
                    dataType: "json",
                    data:{
                        "id" : id
                    },
                    success:function (result){
                        if(result){
                            queryUserInfo();
                            swal("删除成功！", "餐品已被删除", "success");
                        }else{
                            swal("删除失败！", "餐品暂时不能被删除", "error");
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
    resetUserInfoDialog(id);
    if(id!=''){
        $("#dialogTitle").html("编辑餐品")
        $("#dialogUserAccount").attr("readonly",true);
        $("#dialogUserAccount").val(id);

        $.ajax({
            type: "POST",
            url: "/MenuServlet?judge=query",
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
                    $("#dialogUserAccount").val(result[0].mealId)
                    $("#dialogUserName").val(result[0].mealName)
                    $("#dialogSeries").val(result[0].mealSeriesId)
                    // $('#problemSubject').html('<option value="' + result[0].subjectid + '">' + seriesName + '</option>').trigger("change");
                    $("#dialogDescribe").val(result[0].mealSummarize)
                    $("#dialogPrice").val(result[0].mealPrice)
                }

            }
        })
    }else{
        $("#dialogUserAccount").attr("readonly",true);
        $("#dialogTitle").html("添加餐品")
        //setProgress(0);
    }
}
//展示菜单详情
function showDetail(id) {
    if (id != '') {
        $("#dialogMealTitle").html("餐品详情")
        $("#dialogMealAccount").attr("readonly", true);
        $("#dialogMealAccount").val(id);

        $.ajax({
            type: "POST",
            url: "/MenuServlet?judge=query",
            // contentType: "application/json;charset=UTF-8",
            dataType: "json",
            async: false,
            data: {
                "id": id,
            },
            success: function (result) {
                console.log(result)
                //result = jQuery.parseJSON(result);
                //console.log(result)
                if (result.length >= 0) {
                    $("#dialogMealAccount").val(result[0].mealId)
                    $("#dialogMealName").val(result[0].mealName)
                    $("#dialogMealName").attr("readonly", true);
                    $("#dialogMealSeries").val(result[0].mealSeriesId)
                    $("#dialogMealSeries").attr("readonly", true);
                    $("#dialogMealDescribe").val(result[0].mealSummarize)
                    $("#dialogMealDescribe").attr("readonly", true);
                    $("#dialogMealSummarize").val(result[0].mealDescription)
                    $("#dialogMealSummarize").attr("readonly", true);
                    $("#dialogMealPrice").val(result[0].mealPrice)
                    $("#dialogMealPrice").attr("readonly", true);
                }

            }
        })
    }
}

//新增或更新用户信息
function saveOrUpdateUserInfo() {
        $.ajax({
            type: "POST",
            url: "/MenuServlet?judge=saveOrUpdate",
            dataType: "json",
            //contentType: "application/json;charset=UTF-8",
            data: {
                "mealId": $("#dialogUserAccount").val(),
                "mealName": $("#dialogUserName").val(),
                "seriesId": $("#dialogSeries").val(),
                "mealSummarize": $("#dialogDescribe").val(),
                "mealPrice": $("#dialogPrice").val(),
            },
            success: function (result) {
                if (result) {
                    //uploadFile($("#dialogUserAccount").val());
                    queryUserInfo();
                    //关闭模态窗口
                    $('#myModal5').modal('hide');
                    swal("保存成功！", "success");
                } else {
                    swal("请选择菜系！", result.message, "error");
                }
            }
        });
    }


    function setProgress(w) {
        $('#progressBar').width(w + '%');
        $('#progressBar').text(w + '%');
        $("#showInfo").html("");
    }

    function showProgress() {
        $('#progressBar').parent().show();
    }

    function hideProgress() {
        $('#progressBar').parent().hide();
    }

    function getSize(size) {
        var fileSize = '0KB';
        if (size > 1024 * 1024) {
            fileSize = (Math.round(size / (1024 * 1024))).toString() + 'MB';
        } else {
            fileSize = (Math.round(size / 1024)).toString() + 'KB';
        }
        return fileSize;
    }

//上传成功后回调
    function uploadComplete(evt) {
        $('#myModal5').modal('hide');
        resetForm();
        swal("上传成功！", "", "success");
        setProgress(0);
    };

//上传失败回调
    function uploadFailed(evt) {
        swal("上传失败！", evt.target.responseText, "error");
    }

//终止上传
    function cancelUpload() {
        xhr.abort();
    }

//上传取消后回调
    function uploadCanceled(evt) {
        swal("上传失败！", '上传取消,上传被用户取消或者浏览器断开连接:' + evt.target.responseText, "error");
    }

    function progressFunction(evt) {
        var progressBar = document.getElementById("progressBar");
        var percentageDiv = document.getElementById("percentage");
        if (evt.lengthComputable) {
            var completePercent = Math.round(evt.loaded / evt.total * 100)
                + '%';
            $('#progressBar').width(completePercent);
            $('#progressBar').text(completePercent);

            var time = $("#time");
            var nt = new Date().getTime();     //获取当前时间
            var pertime = (nt - ot) / 1000;        //计算出上次调用该方法时到现在的时间差，单位为s
            ot = new Date().getTime();          //重新赋值时间，用于下次计算

            var perload = evt.loaded - oloaded; //计算该分段上传的文件大小，单位b
            oloaded = evt.loaded;               //重新赋值已上传文件大小

            //上传速度计算
            var speed = perload / pertime;//单位b/s
            var bspeed = speed;
            var units = 'b/s';//单位名称
            if (speed / 1024 > 1) {
                speed = speed / 1024;
                units = 'k/s';
            }
            if (speed / 1024 > 1) {
                speed = speed / 1024;
                units = 'M/s';
            }
            speed = speed.toFixed(1);
            //剩余时间
            var resttime = ((evt.total - evt.loaded) / bspeed).toFixed(1);
            $("#showInfo").html(speed + units + '，剩余时间：' + resttime + 's');
        }
    }

    var ot;//上传开始时间
    var oloaded;//已上传文件大小
    var xhr;

//上传文件类
    function uploadFile(id) {
        //alert($('#uploadFile').val());
        if ($('#uploadFile').val() == '') {
            swal('未选择文件', '请选择文件');
            return;
        }
        var formData = new FormData();
        formData.append('file', $('#uploadFile')[0].files[0]);
        var length = getSize($('#uploadFile')[0].files[0].size);
        if ($('#uploadFile')[0].files[0].size >= 1073741824 * 5)//后面的5表示1G*5=5G 上限为5G 可修改
        {
            swal("上传失败！", "上传文件过大！最大不能超过1GB", "error");
            return;
        }

        var FileName = $('#uploadFile')[0].files[0].name;
        $.ajax({
            type: "POST",
            url: "/FileServlet",
            dataType: "json",
            data: {
                name: FileName,
            },
            success: function (result) {
                //if(result.flag == "1"){
                showProgress();
                var uploadGo = "/myFile/uploadMyFile?flag=" + flag;
                xhr = new XMLHttpRequest();
                xhr.open("post", uploadGo, true);
                xhr.onloadstart = function () {
                    ot = new Date().getTime();   //设置上传开始时间
                    oloaded = 0;//已上传的文件大小为0
                };
                xhr.upload.addEventListener("progress", progressFunction, false);
                xhr.addEventListener("load", uploadComplete, false);
                xhr.addEventListener("error", uploadFailed, false);
                xhr.addEventListener("abort", uploadCanceled, false);
                xhr.send(formData);
                //swal("上传成功！", "", "success");
                //}else{
                //swal("上传失败！", result.message, "error");
                //}
            }
        })

    }



