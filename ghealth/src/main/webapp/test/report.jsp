<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/test/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/test/jquery.md5.js"></script>
	<script type="text/javascript" src="http://172.18.130.247:8080//WebReport/ReportServer?op=emb&resource=finereport.js"></script>
</head>
<body>
	<h2>预估成本</h2>
	<form id="ff">
		UT<br/>
		业务报表：http://localhost:8075/WebReport/ReportServer?reportlet=Business.cpt<br/> 
		预估报表：http://localhost:8075/WebReport/ReportServer?reportlet=Forcast.cpt<br/> 
		回款报表：http://localhost:8075/WebReport/ReportServer?reportlet=Returnmoney.cpt<br/> 
		预算报表：http://localhost:8075/WebReport/ReportServer?reportlet=Plan.cpt<br/> 
		渠道报表：http://localhost:8075/WebReport/ReportServer?reportlet=Vendor.cpt<br/> 
		审计归档：http://localhost:8075/WebReport/ReportServer?reportlet=Audit.cpt<br/> 
		CT<br/>
		业务报表：http://172.18.130.247:8080/WebReport/ReportServer?reportlet=Business.cpt<br/>
		预估报表：http://172.18.130.247:8080/WebReport/ReportServer?reportlet=Forcast.cpt<br/>
		回款报表：http://172.18.130.247:8080/WebReport/ReportServer?reportlet=Returnmoney.cpt<br/> 
		预算报表：http://172.18.130.247:8080/WebReport/ReportServer?reportlet=Plan.cpt<br/> 
		渠道报表：http://172.18.130.247:8080/WebReport/ReportServer?reportlet=Vendor.cpt<br/> 
		审计归档：http://172.18.130.247:8080/WebReport/ReportServer?reportlet=Audit.cpt<br/> 
		DATA URL<br/>
		业务报表：http://localhost:8080/ghealth/report/business/report?Authorization=${Authorization}&LoginUser=${LoginUser}&apiflag=${apiflag}&tablename=Business_cost_forecast&uid=${uid}<br/> 
		数据作成<br/>
		/ghealth/report/business/reportBefore<br/>
		数据取得<br/>
		/ghealth/report/business/report<br/>
		URL:<textarea rows="1" cols="" style="width: 80%; margin: 5px;" id="url">/ghealth/report/business/reportBefore</textarea><br /> 
		令牌：<input type="text" id="token"><br/>
		令牌用户名：<input type="text" id="loginUser"><br/>
		apiflag<input type="text" id="apiflag"><br/>
		uid<input type="text" id="uid"><br/>
		
		contractItemId<input type="text" name="contractItemId" value="CV00003-002"><br/>
		
		OUT:<textarea rows="20" cols="" style="width: 80%;margin: 5px;" id="ret"></textarea><br/>
	</form>
	<button type="button" onclick="post()">数据作成</button>
	<button type="button" onclick="setmyData()">设定数据取得</button>
	<a target="_blank" href="" id="myData">数据取得</a> 
	<button type="button" onclick="setData()">设定报表<</button>
	<a target="_blank" href="" id="myId">报表</a> 
	
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
	function setmyData() {
		var acitonurl = $("#url").val();
		token = $("#token").val();
		acitonurl = acitonurl + "?Authorization=" + token;
		loginUser = $("#loginUser").val();
		acitonurl = acitonurl + "&LoginUser=" + loginUser;
		apiflag = $("#apiflag").val();
		acitonurl = acitonurl + "&apiflag=" + apiflag;
		acitonurl = acitonurl + "&tablename=Business_cost_forecast";
		uid = $("#uid").val();
		acitonurl = acitonurl + "&uid=" + uid;
		var addr = FR.cjkEncode(acitonurl);
		$("#myData").attr("href",addr); 
	}
	function setData() {
		var acitonurl = $("#url").val();
		token = $("#token").val();
		acitonurl = acitonurl + "&Authorization=" + token;
		loginUser = $("#loginUser").val();
		acitonurl = acitonurl + "&LoginUser=" + loginUser;
		apiflag = $("#apiflag").val();
		acitonurl = acitonurl + "&apiflag=" + apiflag;
		uid = $("#uid").val();
		acitonurl = acitonurl + "&uid=" + uid;
		var addr = FR.cjkEncode(acitonurl);
		$("#myId").attr("href",addr); 
	}
</script>
</html>
