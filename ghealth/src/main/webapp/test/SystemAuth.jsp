<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery-3.2.1.min.js"></script>
</head>
<body>
	<h2>用户组</h2>
	<form id="ff">
	查看：/ghealth/Home/auth/list<br/>
	编辑：/ghealth/Home/auth/edit<br/>
	URL:<textarea rows="1" cols="" style="width: 80%;margin: 5px;" id="url"><%=request.getContextPath()%>/Home/auth/list</textarea><br/>
	令牌：<input type="text" id="token"><br/>
	令牌用户名：<input type="text" id="loginUser"><br/>
	apiflag：<input type="text" name="apiflag"><br/><br/><br/>

	OUT:<textarea rows="20" cols="" style="width: 80%;margin: 5px;" id="ret"></textarea><br/>
	</form>
	<button type="button" onclick="post()">提交</button><br/>

</body>
<script type="text/javascript">
	function post() {
		var acitonurl = $("#url").val();
		$.ajax({
			type:'POST',
			url:acitonurl,
			data:$("#ff").serialize(),
			beforeSend: function(request) {
				token = $("#token").val();
				loginUser = $("#loginUser").val();
	            request.setRequestHeader("Authorization", token);
	            request.setRequestHeader("LoginUser", loginUser);
	        },
			success:function(data){
				alert("OK!");
				var aToStr=JSON.stringify(data); 
				$("#ret").val(aToStr);
			},
			error:function(data){
				alert("ERROR!");
				var aToStr=JSON.stringify(data); 
				$("#ret").val(aToStr);
			}
		});
	}
</script>
</html>
