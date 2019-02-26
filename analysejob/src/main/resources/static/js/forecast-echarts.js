$(function () {
    $.getJSON('/loadForecast', function (data) {
        option1 = {
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            legend: {
                data:data.jobNames
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: data.trends[0].time
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name:data.jobNames[0],
                    type:'line',
                    stack: '总量',
                    data:data.trends[0].heat
                },
                {
                    name:data.jobNames[1],
                    type:'line',
                    stack: '总量',
                    data:data.trends[1].heat
                },
                {
                    name:data.jobNames[2],
                    type:'line',
                    stack: '总量',
                    data:data.trends[2].heat
                },
                {
                    name:data.jobNames[3],
                    type:'line',
                    stack: '总量',
                    data:data.trends[3].heat
                },
                {
                    name:data.jobNames[4],
                    type:'line',
                    stack: '总量',
                    data:data.trends[4].heat
                },
                {
                    name:data.jobNames[5],
                    type:'line',
                    stack: '总量',
                    data:data.trends[5].heat
                },
                {
                    name:data.jobNames[6],
                    type:'line',
                    stack: '总量',
                    data:data.trends[6].heat
                },
                {
                    name:data.jobNames[7],
                    type:'line',
                    stack: '总量',
                    data:data.trends[7].heat
                },
                {
                    name:data.jobNames[8],
                    type:'line',
                    stack: '总量',
                    data:data.trends[8].heat
                },
                {
                    name:data.jobNames[9],
                    type:'line',
                    stack: '总量',
                    data:data.trends[9].heat
                }
            ]
        };
        var myChart1 = echarts.init(document.getElementById('echarts1'))
        myChart1.setOption(option1)
        $("#hide1").hide();
        $("#hide2").hide();
    })
    /////////////////////////////////////////////////////////////////////////////////////
    //图表2
    ///////////////////////////////////////////////////////////////////////////////////

    $("#forecast").click(function () {
        $("#hide1").show();
        $("#hide2").show();
        $.getJSON('/forecasting?forecastdate=' + $("#forecastdate").val(), function (data) {
            $("#block1").show()
            option2 = {
                tooltip: {
                    trigger: 'axis'
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                legend: {
                    data:data.jobNames
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: data.trends[0].time
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name:data.jobNames[0],
                        type:'line',
                        stack: '总量',
                        data:data.trends[0].heat
                    },
                    {
                        name:data.jobNames[1],
                        type:'line',
                        stack: '总量',
                        data:data.trends[1].heat
                    },
                    {
                        name:data.jobNames[2],
                        type:'line',
                        stack: '总量',
                        data:data.trends[2].heat
                    },
                    {
                        name:data.jobNames[3],
                        type:'line',
                        stack: '总量',
                        data:data.trends[3].heat
                    },
                    {
                        name:data.jobNames[4],
                        type:'line',
                        stack: '总量',
                        data:data.trends[4].heat
                    },
                    {
                        name:data.jobNames[5],
                        type:'line',
                        stack: '总量',
                        data:data.trends[5].heat
                    },
                    {
                        name:data.jobNames[6],
                        type:'line',
                        stack: '总量',
                        data:data.trends[6].heat
                    },
                    {
                        name:data.jobNames[7],
                        type:'line',
                        stack: '总量',
                        data:data.trends[7].heat
                    },
                    {
                        name:data.jobNames[8],
                        type:'line',
                        stack: '总量',
                        data:data.trends[8].heat
                    },
                    {
                        name:data.jobNames[9],
                        type:'line',
                        stack: '总量',
                        data:data.trends[9].heat
                    }
                ]
            };

            var myChart2 = echarts.init(document.getElementById('echarts2'))
            myChart2.setOption(option2)
            $("#hide1").hide()
            $("#hide2").hide()
        })
        return false
    })
})