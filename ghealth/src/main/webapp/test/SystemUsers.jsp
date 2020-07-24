<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery.md5.js"></script>
</head>
<body>
	<h2>用户管理</h2>
	<form id="ff">
	查看：/ghealth/users/list<br/>
	新增：/ghealth/users/additional<br/>
	编辑：/ghealth/users/edit<br/>
	删除：/ghealth/users/dele<br/>
	URL:<textarea rows="1" cols="" style="width: 80%;margin: 5px;" id="url"><%=request.getContextPath()%>/users/list</textarea><br/>
	令牌：<input type="text" id="token"><br/>
	令牌用户名：<input type="text" id="loginUser"><br/>
	apiflag：<input type="text" name="apiflag"><br/><br/><br/>
	
	username：用户名（需要check）<input type="text" name="username"><br/>
	password：密码（前台MD5加密）<input type="text" name="password"><br/>
	fullname：全名<input type="text" name="fullname"><br/>
	groupid：用户组<input type="text" name="groupid"><br/>
	sex：性别<input type="text" name="sex"><br/>
	email：邮箱地址（前台validate check）<input type="text" name="email"><br/>
	tel：电话号码（前台validate check）<input type="text" name="tel"><br/>
	position：职位（可为空）<input type="text" name="position"><br/>
	page<input type="text" name="page" value="1"><br/>

	OUT:<textarea rows="20" cols="" style="width: 80%;margin: 5px;" id="ret"></textarea><br/>
	</form>
	<button type="button" onclick="post()">提交</button><br/>

</body>
<script type="text/javascript">
	function post() {
		var acitonurl = $("#url").val();
		$("input[name='password']").val($.md5($("input[name='password']").val()));
		var jsonuserinfo = JSON.stringify($('#ff').serializeObject());  
		$.ajax({
			type:'POST',
			url:acitonurl,
            dataType:"json",      
            contentType:"application/json", 
			data:jsonuserinfo,
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
	$.fn.serializeObject = function()    
	{    
	   var o = {};    
	   var a = this.serializeArray();    
	   $.each(a, function() {    
	       if (o[this.name]) {    
	           if (!o[this.name].push) {    
	               o[this.name] = [o[this.name]];    
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
