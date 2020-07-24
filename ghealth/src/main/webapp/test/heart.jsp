<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery.md5.js"></script>
</head>
<body>
	<h2>用户管理</h2>
	<form id="ff">
	查看：/ghealth/dataunscramble/list<br/>
	新增：/ghealth/dataunscramble/additional<br/>
	编辑：/ghealth/dataunscramble/edit<br/>
	删除：/ghealth/dataunscramble/dele<br/>
	URL:<textarea rows="1" cols="" style="width: 80%;margin: 5px;" id="url"><%=request.getContextPath()%>/dataunscramble/list</textarea><br/>
	令牌：<input type="text" id="token"><br/>
	令牌用户名：<input type="text" id="loginUser"><br/>
	apiflag：<input type="text" name="apiflag"><br/><br/><br/>
	
	currentPage<input type="text" name="currentPage"><br/>
	pagesize<input type="text" name="pagesize"><br/>
	customerId<input type="text" name="customerId"><br/>
	checkinTime<input type="text" name="checkinTime"><br/>
	archivesId<input type="text" name="archivesId"><br/>

	OUT:<textarea rows="20" cols="" style="width: 80%;margin: 5px;" id="ret"></textarea><br/>
	</form>
	<button type="button" onclick="post()">提交</button><br/>

</body>
<script type="text/javascript">
	function post() {
		var acitonurl = $("#url").val();
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
