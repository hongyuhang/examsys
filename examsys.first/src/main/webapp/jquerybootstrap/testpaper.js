$( document ).ready(function(){
    $(".digits").countdown({
      image: "img/digits.png",
      format: "mm:ss",
	    stepTime: 60,
	    startTime: "30:00",
	    digitImages: 6,
	    digitWidth: 67,
	    digitHeight: 90,
	    timerEnd: function(){alert("考试结束")},
	    continuous: false,
		start: true
    });
});