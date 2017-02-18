$( document ).ready(function(){
	var url = window.location.href;
	if (url.indexOf("?") < 0) {
		$.alert({
		    title: '错误提示',
		    content: '错误的参数！',
		});
		window.location = serverContext + "/index.html";
	}
	// 解析参数
	var args = url.substr(url.indexOf("?") + 1).split("&");
	if (args == null || args == undefined || args.length != 2) {
		$.alert({
		    title: '错误提示',
		    content: '错误的参数！',
		});
		window.location = serverContext + "/index.html";
	}
	console.log(args);
	var categoryCode = args[0].split("=")[1];
	var userId = args[1].split("=")[1];
	
	// 请求服务器
	$.ajax({
		url:serverContext + "/testpapergrade/" + categoryCode + "/" + userId,
		dataType:"json",
//		async:false,
		success:function(data) {
			if (data.flag) {
				console.log(data.data);
				// 设置总结语
				if (data.data.passFlag == 1) {
					$('.jumbotron').children().remove();
					var obj = $("<h1>恭喜你！通过了考试！<br>你的得分是" + data.data.score + "分！</h1>");
					$('.jumbotron').append(obj);
				} else {
					$('.jumbotron').children().remove();
					var obj = $("<h1>很遗憾！没有通过考试！<br>你的得分是" + data.data.score + "分！</h1>");
					$('.jumbotron').append(obj);
				}
				
				
				// 发送请求到后台取得成绩数据，画图形
				var chart = new GradeChart();
				chart.initial(data.data.questions);
				
			} else {
				$.alert({
				    title: '错误提示',
				    content: '服务器出现错误!',
				});
				window.location = serverContext + "/index.html";
			}
		},
		error:function(){
			$.alert({
			    title: '错误提示',
			    content: '服务器出现错误!',
			});
			window.location = serverContext + "/index.html";
		}
	});
	

	

});

function getName(code) {
	if (code == 1)
		return "单选题";
	if (code == 2)
		return "多选题";
	if (code == 3)
		return "判断题";
}

GradeChart = function() {
	// 图表初始化
	this.initial = function(data) {
		var gradeChart = echarts.init(document.getElementById('grade'));
		
		var totalIndicator = [];
		var correctIndicator = [];
		var code = data[0].code;
		var indicator = {};
		indicator.text = getName(code);
		indicator.max = 0;
		
		$.each(data, function(i, obj){
			// 算出总共有多少题
			if (code != obj.code) {
				code = obj.code;
				totalIndicator.push(indicator);
				indicator = {};
				indicator.text = getName(obj.code);
				indicator.max = 0;
			}
			indicator.max = indicator.max + obj.qcount;
			// 统计正确的有多少题
			if (obj.correctFlag == 1) {
				correctIndicator.push(obj.qcount);
			}
		});
		totalIndicator.push(indicator);
		
		// 形成chart的option
	// TODO:可以考虑计算平均分
	var option = {
            title: {
                text: '成绩分析图'
            },
            tooltip: {},
            legend: {
                data:['正确率']
            },
            radar: [
				{
		            indicator: totalIndicator,
		            center: ['50%', '50%'],
		            radius: 120
	        		}
            ],
            series: [
		        {
		            name: '成绩分析',
		            type: 'radar',
		            radarIndex: 0,
		            data: [
		                {
		                    value: correctIndicator,
		                    name: '正确率',
		                    areaStyle: {
		                        normal: {
		                            opacity: 0.9,
		                            color: new echarts.graphic.RadialGradient(0.5, 0.5, 1, [
		                                {
		                                    color: '#B8D3E4',
		                                    offset: 0
		                                },
		                                {
		                                    color: '#72ACD1',
		                                    offset: 1
		                                }
		                            ])
		                        }
		                    }
		                }
		            ]
		        }
            ]
        };
	gradeChart.setOption(option);
	}
}



/**
 * 考试成绩数据结构
 */
function TestPaperGrade () {
	// 选择的答案
	this.grade = 0;
	this.passed = false;
	this.itemTypes = [];
}

/**
 * 考题类型数据结构
 */
function ItemType () {
	// 选择的答案
	this.totalScore = 0;
	this.score = 0;
	this.itemTypeName = "";
}
