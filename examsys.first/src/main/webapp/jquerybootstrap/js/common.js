	// 获取context path
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
	var serverContext = basePath;

	// 字符串布尔转换成javascript的布尔型

	function stringToBoolean(string) {
	    switch (string.toLowerCase()) {
	    case "true":
	    case "yes":
	    case "1":
	        return true;
	    case "false":
	    case "no":
	    case "0":
	    case null:
	        return false;
	    default:
	        return Boolean(string);
	    }
	}
	
	$.ajaxSetup({
	    error: function(XMLHttpRequest, textStatus, errorThrown) { /* 扩展AJAX出现错误的提示 */
	        //$.messager.progress('close');
	        //$.messager.alert('错误', XMLHttpRequest.responseText.split('<script')[0]);
	        alert("出现错误");
	    }
	});