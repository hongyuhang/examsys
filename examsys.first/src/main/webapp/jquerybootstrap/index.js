$( document ).ready(function() {
  	// TODO:支持的浏览器检测，如果不支持转向错误页面
	$.ajax({
		url:serverContext + "/category/all",
		dataType:"json",
		success:function(data) {
			if (data.flag) {
				var category = new CategoryObj();
				category.initial(data.data);
			} else {
				alert("服务器出现错误!");
			}
		}
	});
/*	debugger;
	// 测试代码
//	var data = [
//		{
//			categoryName:"语文", 
//			categoryDescription:"考试时长30分钟，20道题",
//			categoryCode:"01"
//		},
//		{
//			categoryName:"数学", 
//			categoryDescription:"考试时长40分钟，30道题",
//			categoryCode:"02"
//		},
//		{
//			categoryName:"英语", 
//			categoryDescription:"考试时长50分钟，40道题",
//			categoryCode:"03"
//		},
//		{
//			categoryName:"综合", 
//			categoryDescription:"考试时长60分钟，50道题",
//			categoryCode:"04"
//		}
//	];
//	var category = new CategoryObj();
//	category.initial(data);
测试代码结束
*/  
	
});
var ROWSIZE = 3;

var CategoryObj = function() {
	this.initial = function(data) {
		// 无效对象则返回
		if (data == undefined || data == null || data.length == 0) {
			return;
		}
		var root = $(".marketing");
		var currentRow;
		$.each(data, function(i, jsonObj) {
			// 换行
			if ((i % ROWSIZE) == 0) {
				currentRow = $("<div class=\"row\">");
				root.append(currentRow);
			}
			var columnDiv = $("<div class=\"col-lg-4\"></div>");
			var thumbnailDiv = $("<div class=\"thumbnail\" style=\"cursor:pointer;\"></div>");
			currentRow.append(columnDiv);
			columnDiv.append(thumbnailDiv);
			
			var childImg = $("<img class=\"img-circle\" src=\"data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==\" alt=\"Generic placeholder image\" style=\"width: 140px; height: 140px;\">");
			var childTitle = $("<h2></h2>");
			// 设定考试名称
			childTitle.html(jsonObj.name);
			var childDescription = $("<p style='text-wrap:normal;'></p>");
			// 设定考试描述
			childDescription.html(jsonObj.description);
//			var childLink = $("<p><a class=\"btn btn-default\" href=\"#\" role\"button\">开始 &raquo;</a></p>");
//			childLink.find("a").attr("cCode", jsonObj.code);
			thumbnailDiv.click(function(){
				window.location.href = serverContext + "/jquerybootstrap/testpaper.html?categoryCode=" + jsonObj.code;
			});
			
			thumbnailDiv.append(childImg);
			thumbnailDiv.append(childTitle);
			thumbnailDiv.append(childDescription);
//			thumbnailDiv.append(childLink);
		})
//		$('.thumbnail').click(function() {
//			alert("即将转向考试页面（敬请期待）");
//		});
	}
};

