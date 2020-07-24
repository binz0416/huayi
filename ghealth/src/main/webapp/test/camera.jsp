<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery.md5.js"></script>
</head>
<body>
	<h2>摄像头对接模拟</h2>
	<%=request.getContextPath()%>/spacecapsule/camera<br/>
	<%=request.getContextPath()%>/setmeal/camera<br/>
	<form id="ff">
	POST_URL:<textarea rows="1" cols="" style="width: 80%;margin: 5px;" id="url"><%=request.getContextPath()%>/setmeal/camera</textarea><br/>
	<div id="camera_data">
	摄像头编号<input type="text" name="cameraNo"><br/>
	抓拍时间戳  unix时间戳 <input type="text" name="captureTime"><br/>
	人脸库id<input type="text" name="faceId"><br/>
	</div>
	OUT:<textarea rows="20" cols="" style="width: 80%;margin: 5px;" id="ret"></textarea><br/>
	</form>
	<button type="button" onclick="post()">提交</button><br/>

</body>
<script type="text/javascript">
	$("input[name='captureTime']").each(function() {
		var timestamp = Date.parse(new Date()); 
		$(this).val(timestamp);
	})
	function post() {
		var acitonurl = $("#url").val();
		var jsonuserinfo = JSON.stringify($('#ff').serializeObject());
		$.ajax({
			type : 'POST',
			url : acitonurl,
			dataType : "json",
			contentType : "application/json",
			data : jsonuserinfo,
			success : function(data) {
				alert("OK!");
				var aToStr = JSON.stringify(data);
				$("#ret").val(aToStr);
			},
			error : function(data) {
				alert("ERROR!");
				var aToStr = JSON.stringify(data);
				$("#ret").val(aToStr);
			}
		});
	}
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
</script>
</html>
