<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery.md5.js"></script>
</head>
<body>
	<h2>API</h2>
	<%=request.getContextPath()%>/wearable/heartbook<br/>
	<form id="ff">
	POST_URL:<textarea rows="1" cols="" style="width: 80%;margin: 5px;" id="url"><%=request.getContextPath()%>/wearable/heartbook</textarea><br/>

	OUT:<textarea rows="20" cols="" style="width: 80%;margin: 5px;" id="ret"></textarea><br/>
	</form>
	<button type="button" onclick="post()">提交</button><br/>

</body>
<script type="text/javascript">
	function post() {
		var acitonurl = $("#url").val();
		$.ajax({
			type : 'POST',
			url : acitonurl,
			dataType : "json",
			contentType : "application/json",
			data : '',
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
</script>
</html>
