<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String env= System.getenv("CHORHAIR");%>
 

<!DOCTYPE html>
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
<script type="text/javascript" src='../javascripts/jquery-1.8.2.js'> </script>
 <script type="text/javascript">
   
 function posttest(){
	 var posturl=document.getElementById("url").value;
	 var data=document.getElementById("data").value;
	 
	 var param = {
		data:data
	 };
	 
	 $.post(posturl,param,function(data){
		 document.getElementById("returndata").value=data;
	 }
	 );
 }
</script>

</head>
<body>
	<h3>It's a test page.</h3>
	<label>请求地址</label>
	<textarea rows="2" cols="50" id="url"></textarea>
	<label>数据</label>
	<textarea rows="2" cols="50" id="data"></textarea>
	
	
	<button onclick="posttest();">确定</button>
    
	<div>
		<label>返回结果</label>
	<textarea rows="10" cols="100" id="returndata"></textarea>
	</div>
</body>
</html>