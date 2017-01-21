var contents = {};
var topTitle = {};
var answersInfo = {};
$( document ).ready(function(){
	// 请求服务器
	$.ajax({
		url:serverContext + "/testpaper/01",
		dataType:"json",
		async:false,
		success:function(data) {
			if (data.flag) {
				contents = new AnswerContents();
				contents.initial(data.data);
				contents.show();
				
				topTitle = new TopTitle();
				topTitle.noAnswerCount = contents.questions.length;
				
				answersInfo = new AnswersInfo();
				answersInfo.showAll(contents);
				
				console.log(contents.questions);
			} else {
				alert("服务器出现错误!");
			}
		},
		error:function(){
			alert("服务器出现错误!");
		}
	});
	
    $(".digits").countdown({
      image: "img/digits.png",
      format: "mm:ss",
	    stepTime: 60,
	    startTime: "30:00", // 需要修改为从服务器读取应该的倒计时数字
	    digitImages: 6,
	    digitWidth: 67,
	    digitHeight: 90,
	    timerEnd: function(){alert("考试结束")},
	    continuous: false,
		start: true
    });
    
});

/**
 * 标题的数据结构
 */
TopTitle = function() {
	this.answerCount = 0 ;
	this.noAnswerCount = 0;
}

/**
 * 答题情况
 */
AnswersInfo = function() {
	var self = this;
	this.infos = [];
	
	this.show = function(index, selected) {
		if (selected) {
			self.infos[index].removeClass("btn-warning");
			self.infos[index].addClass("btn-info");
		} else {
			self.infos[index].removeClass("btn-info");
			self.infos[index].addClass("btn-warning");
		}
	}
	// 显示全部考题的按钮
	this.showAll = function(contents) {
		$('#answerinfo').children().remove();
		$.each(contents.questions, function(i, obj){
			var question = $('<button type="button" class="btn btn-sm"></button>');
			if (obj.selected.length > 0) {
				question.addClass("btn-info vpad vmar");
			} else {
				question.addClass("btn-warning vpad vmar");
			}
			
			var icon = $('<span class="glyphicon glyphicon-remove-circle"></span>');
			var text = self.formatText(obj.index);
			var textNode = $('<span></span>');
			textNode.html(text);
			question.append(icon);
			question.append(textNode);
			question.click(function(){
				contents.currentIndex = obj.index;
				contents.show();
			})
			
			self.infos.push(question);
			$('#answerinfo').append(question);
		})
	}
	
	this.formatText = function(index) {
		if (index < 9) {
			return "Q00" + (index + 1);
		}
		
		if (index >= 9 && index < 100) {
			return "Q0" + (index + 1);
		}
	}
}

/**
 * 考题区域
 */
function AnswerContents () {
	this.currentIndex = 0;
	var self = this;
	this.qCount = 0;
	this.questions = [];
	// 初始化
	this.initial = function(data) {
		// 遍历考题
		for(key in data.testContent) {
			$.each(data.testContent[key], function(i, json){
				var question = new Question();
				question.qType = key;
				question.questionCode = json.itemId;
				question.questionContent = json.itemContont;
				question.score = json.score;
				$.each(json.options, function(j, obj) {
					var option = new Option();
					option.optionId = obj.optionId;
					option.optionContent = obj.optionContent;
					question.options.push(option);
				});
				// 判断题的情况
				if (question.qType == 3) {
					var right = new Option();
					right.optionId = 1;
					right.optionContent = "对";
					question.options.push(right);
					var wrong = new Option();
					wrong.optionId = 0;
					wrong.optionContent = "错";	
					question.options.push(wrong);
				}
				question.index = self.qCount++;
				self.questions.push(question);
			});
		}
		
		// 注册前一题后一题按钮的动作
		$('#preBtn').click(function(){
			if (self.currentIndex == 0) {
				return;
			}
			self.currentIndex--;
			self.show();
		});
		$('#nextBtn').click(function(){
			if (self.currentIndex == self.questions.length) {
				return;
			}
			self.currentIndex++;
			self.show();
		});
		
		console.log(self.questions);
	}
	
	/**
	 * 在画面上显示当前的考题
	 */
	this.show = function(){
		// 取序号
		$('#questionIndex').html(self.currentIndex + 1);
		// 取考题内容
		$('#questionContent').html(self.questions[self.currentIndex].questionContent);
		// 取备选答案
		$('#options').children().remove();
		
		$.each(self.questions[self.currentIndex].options, function(i, obj) {
			var optionLi = $('<li class="list-group-item"></li>');
			var option = {};
			// 单选或者判断
			if (self.questions[self.currentIndex].qType == "1" || self.questions[self.currentIndex].qType == "3") {
				option = $('<input name="option" type="radio" oid="' + obj.optionId + '"></input>');
				option.click(function(){
					self.questions[self.currentIndex].selected = [];
					self.questions[self.currentIndex].selected.push(obj.optionId);
//					$(this).attr("oid", obj.optionId);
					answersInfo.show(self.currentIndex, true);
				});
			}
			// 多选
			if (self.questions[self.currentIndex].qType == "2") {
				option = $('<input name="option" type="checkbox" oid="' + obj.optionId + '"></input>');
				option.click(function(){
					
				});
			}	
			
			var text = $('<span style="margin-left:8px;"></span>');
			text.html(obj.optionContent);
//			option.attr("val", obj.optionId);
			optionLi.append(option);
			optionLi.append(text);
			$('#options').append(optionLi);
		});
		// 如果曾经选择过，给附上初值
		if (self.questions[self.currentIndex].selected.length > 0) {
			$.each(self.questions[self.currentIndex].selected, function(j, value) {
				$("input:radio[oid='" + value + "']").attr("checked",true);
			});
		}
	}
	
	
}

/**
 * 考题数据结构
 */
function Question () {
	// 选择的答案
	this.index = 0;
	this.selected = [];
	this.options = [];
	this.questionCode = "";
	this.questionContent = "";
	this.categoryCode = "";
	this.score = "";
	this.qType = ""; // 1:单选\n2:多选\n3:判断\n4:客观
}

/**
 * 备选答案数据结构
 */
function Option () {
	this.optionId = "";
	this.optionContent = "";
}

