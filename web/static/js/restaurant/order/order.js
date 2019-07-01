$(document).ready(function () {
    queryOrderInfo();
    indexTimeSelect();
});
var icon = "<i class='fa fa-times-circle'></i>";
//重置form内的标签
function resetForm() {
    $(".form-horizontal input").val("");
    $(".form-horizontal select").val("");
    queryOrderInfo();
}
function queryOrderInfo() {
   // alert($('#createTime').val())
    $.ajax({
        type: "POST",
        url: "/RecordServlet?judge=getOrders",
        dataType: "json",
        //contentType: "application/json;charset=UTF-8",
        data:{
            "orderId" : $('#orderId').val(),
            "userName" : $('#userName').val(),
            "createTime" : $('#createTime').val(),

        },
        success:function (result) {
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
                    "data" : "recordId"
                },{
                    "data" : "userName"
                },{
                    "data" : "consumptionMoney"
                },{
                    "data" : "state"
                },{
                    "data" : "time"
                }],
                "columnDefs": [{
                    "render" : function(data, type, row) {
                        var a = "";
                       // a += "<button type='button' class='btn btn-primary' onclick='showEditOrder(\""+row.recordId+"\")' data-toggle='modal' data-target='#myModal5' title='编辑用户' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='deleteOrder(\""+row.recordId+"\")' title='删除用户' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-user-times'></i>&nbsp;删除</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='showOrder(\""+row.recordId+"\")' data-toggle='modal' data-target='#courseListDialog' title='课程列表' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-list'></i>&nbsp;订单详情</button>"
                        return a;
                    },
                    "targets" :5
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
    $("#createTime").append(timeSelectInfo);
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

function deleteOrder(id) {
    //alert(id)
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
                    url: "/RecordServlet?judge=deleteOrder",
                    // contentType: "application/json;charset=UTF-8",
                    dataType: "json",
                    data:{
                        "orderId" : id
                    },
                    success:function (result){
                        if(result){
                            queryOrderInfo();
                            swal("删除成功！", "订单已被删除", "success");
                        }else{
                            swal("删除失败！", "订单暂时不能被删除", "error");
                        }

                    }
                })
            }else {
                swal("已取消", "你取消了删除订单操作", "error");
            }
        });
}


function showOrder(id) {
    console.log(id)
    $('#CLDuserId').val(id);
    $('#CLDtbody').html("");
    $.ajax({
        type: "POST",
        url: "/IndentServlet?judge=getTheOrder",
        dataType: "json",
        data: {
            "orderId": id
        },
        success: function (result) {
            var innerHtml = ''
            for (i = 0; i < result.length; i++){
                innerHtml += "<tr>";
                innerHtml += "<td>"+result[i].indentId+"</td>";
                innerHtml += "<td>"+result[i].mealName+"</td>";
                innerHtml += (result[i].mealImage == "undefined") ? "<td>"+""+"</td>" : "<td><img width='40' src='\""+result[i].mealImage +"\'></td>";
                innerHtml += "<td>"+result[i].money+"</td>";
                innerHtml += "<td>"+result[i].count+"</td>";
                innerHtml += "<td>"+result[i].sumMoney+"</td>";
                innerHtml += "</tr>";
            }
            $('#CLDtbody').append(innerHtml)
        }
    });
}
