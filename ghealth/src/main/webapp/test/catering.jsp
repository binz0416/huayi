<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/test/jquery.md5.js"></script>
</head>
<body>
	<h2>餐饮</h2>
	<form id="ff">
	查看：/catering/setmeal/list<br/>
	编辑：/catering/setmeal/edit<br/>
	URL:<textarea rows="1" cols="" style="width: 80%;margin: 5px;" id="url"><%=request.getContextPath()%>/catering/setmeal/list</textarea><br/>
	令牌：<input type="text" id="token"><br/>
	令牌用户名：<input type="text" id="loginUser"><br/>
	apiflag：<input type="text" name="apiflag"><br/><br/><br/>
	getPrintData<br/>
	
	setMealId：餐饮ID<input type="text" name="setMealId"><br/>
	cateringId：套餐ID<input type="text" name="cateringId"><br/>

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
