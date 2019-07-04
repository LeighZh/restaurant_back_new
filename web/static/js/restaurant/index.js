$(document).ready(function () {
    analyzeProblem();
});

//对问题进行分析
function analyzeProblem() {
    var submitCount =0;
    var successCount = 0;
    var formatErrorCount = 0;
    var wrongAnswerCount = 0;
    var compileErrorCount = 0;
    var count = 0;
    //if(id != null && id != ""){
        $.ajax({
            type: "POST",
            url: "/userServlet?judge=getUsers",
            dataType: "json",
            async:false,
            success:function (result){
                console.log(result);

                for(var i = 0; i < result.length; i++){
                    if(result[i].money == 0){
                        submitCount++;
                    }else if(result[i].money < 100 && result[i].money > 0){
                        successCount++;
                    }else if(result[i].money < 300 && result[i].money >= 100){
                        formatErrorCount++;
                    }else if(result[i].money > 300 && result[i].money <= 500){
                        wrongAnswerCount++;
                    }else if(result[i].money > 500 && result[i].money <= 1000){
                        compileErrorCount++;
                    }else {
                        count ++;
                    }

                }
                // submitCount = result[0].submitCount;
                // successCount = result[0].successCount;
                // formatErrorCount = result[0].formatErrorCount;
                // wrongAnswerCount = result[0].wrongAnswerCount;
                // compileErrorCount = result[0].compileErrorCount;
                setEcharts(submitCount,successCount,formatErrorCount,wrongAnswerCount,compileErrorCount,count);
                $('#id').val("");
            }
        })
   // }


}

//饼形图设置
function setEcharts(submitCount,successCount,formatErrorCount,wrongAnswerCount,compileErrorCount,count){
    //$('echarts-pie-chart').removeAttrs('echarts_instance_');
    document.getElementById("echarts-pie-chart");
    var pieChart = echarts.init(document.getElementById("echarts-pie-chart"));
    var pieoption = {
        title : {
            text: '用户消费情况分析',
            subtext:' 消费情况',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['0 元 ','< 100 元','100 -- 300 元','300 -- 500 元','500 -- 1000 元','> 1000  元']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'消费情况',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:submitCount, name:'0 元 '},
                    {value:successCount, name:'< 100 元'},
                    {value: formatErrorCount, name:'100 -- 300 元'},
                    {value:wrongAnswerCount, name:'300 -- 500 元'},
                    {value:compileErrorCount,name:'500 -- 1000 元'},
                    {value:count,name:'>1000 元'}
                ],
                itemStyle:{
                    normal:{
                        label:{
                            show: true,
                            formatter: '{b} : {c} ({d}%)'
                        },
                        labelLine :{show:true}
                    }
                }
            }
        ]
    };
//if(pieoption && typeof pieoption == "object"){
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);
//}

}


