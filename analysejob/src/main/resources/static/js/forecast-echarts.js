$(function () {
    option1 = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['职业1','职业2','职业3','职业4','职业5','职业6','职业7','职业8','职业9','职业10']
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
        xAxis: {
            type: 'category',
            boundaryGap: false,
            // data: ['第七周','第六周','第五周','第四周','第三周','第二周','第一周']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'职业1',
                type:'line',
                stack: '总量',
                // data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'职业2',
                type:'line',
                stack: '总量',
                // data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'职业3',
                type:'line',
                stack: '总量',
                // data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'职业4',
                type:'line',
                stack: '总量',
                // data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'职业5',
                type:'line',
                stack: '总量',
                // data:[820, 932, 901, 934, 1290, 1330, 1320]
            },
            {
                name:'职业6',
                type:'line',
                stack: '总量',
                // data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'职业7',
                type:'line',
                stack: '总量',
                // data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'职业8',
                type:'line',
                stack: '总量',
                // data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'职业9',
                type:'line',
                stack: '总量',
                // data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'职业10',
                type:'line',
                stack: '总量',
                // data:[820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };

    var myChart1 = echarts.init(document.getElementById('echarts1'))
    myChart1.setOption(option1)
    $.getJSON('/loadForecast', function (data) {
        myChart1.setOption({
            xAxis: {
                data:data.time
            },
            series: [
                {
                    data:data.heat
                },
                // {
                //     data:[220, 182, 191, 234, 290, 330, 310]
                // },
                // {
                //     data:[150, 232, 201, 154, 190, 330, 410]
                // },
                // {
                //     data:[320, 332, 301, 334, 390, 330, 320]
                // },
                // {
                //     data:[820, 932, 901, 934, 1290, 1330, 1320]
                // },
                // {
                //     data:[120, 132, 101, 134, 90, 230, 210]
                // },
                // {
                //     data:[220, 182, 191, 234, 290, 330, 310]
                // },
                // {
                //     data:[150, 232, 201, 154, 190, 330, 410]
                // },
                // {
                //     data:[320, 332, 301, 334, 390, 330, 320]
                // },
                // {
                //     data:[820, 932, 901, 934, 1290, 1330, 1320]
                // }
            ]
        })
    })
    /////////////////////////////////////////////////////////////////////////////////////
    //图表2
    option2 = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['职业1','职业2','职业3','职业4','职业5','职业6','职业7','职业8','职业9','职业10']
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
        xAxis: {
            type: 'category',
            boundaryGap: false,
            // data: ['第七周','第六周','第五周','第四周','第三周','第二周','第一周']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'职业1',
                type:'line',
                stack: '总量',
                // data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'职业2',
                type:'line',
                stack: '总量',
                // data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'职业3',
                type:'line',
                stack: '总量',
                // data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'职业4',
                type:'line',
                stack: '总量',
                // data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'职业5',
                type:'line',
                stack: '总量',
                // data:[820, 932, 901, 934, 1290, 1330, 1320]
            },
            {
                name:'职业6',
                type:'line',
                stack: '总量',
                // data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'职业7',
                type:'line',
                stack: '总量',
                // data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'职业8',
                type:'line',
                stack: '总量',
                // data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'职业9',
                type:'line',
                stack: '总量',
                // data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'职业10',
                type:'line',
                stack: '总量',
                // data:[820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };

    var myChart2 = echarts.init(document.getElementById('echarts2'))
    myChart2.setOption(option2)
    $("#forecast").click(function () {
        $.getJSON('/forecasting?forecastdate=' + $("#forecastdate").val(), function (data) {
            myChart2.setOption({
                xAxis: {
                    data: data.time
                },
                series: [
                    {
                        name:'职业1',
                        data:data.heat
                    },
                    // {
                    //     name:'职业2',
                    //     data:[220, 182, 191, 234, 290, 330, 310]
                    // },
                    // {
                    //     name:'职业3',
                    //     data:[150, 232, 201, 154, 190, 330, 410]
                    // },
                    // {
                    //     name:'职业4',
                    //     data:[320, 332, 301, 334, 390, 330, 320]
                    // },
                    // {
                    //     name:'职业5',
                    //     data:[820, 932, 901, 934, 1290, 1330, 1320]
                    // },
                    // {
                    //     name:'职业6',
                    //     data:[120, 132, 101, 134, 90, 230, 210]
                    // },
                    // {
                    //     name:'职业7',
                    //     data:[220, 182, 191, 234, 290, 330, 310]
                    // },
                    // {
                    //     name:'职业8',
                    //     data:[150, 232, 201, 154, 190, 330, 410]
                    // },
                    // {
                    //     name:'职业9',
                    //     data:[320, 332, 301, 334, 390, 330, 320]
                    // },
                    // {
                    //     name:'职业10',
                    //     data:[820, 932, 901, 934, 1290, 1330, 1320]
                    // }
                ]
            })
        })
        return false
    })
})