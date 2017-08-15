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
	 var message=$('#message').val();
	 var receiver=$('#receiver').val();
	 var param = param || {};
	 param.receiver = receiver;
	 param.message = message;
	 var posturl="http://127.0.0.1:8080/server/shortmessage/send.php";
	 var param = {
		data:JSON.stringify(param)
	 };
	 
	 $.post(posturl,param,function(data){
		 var data = JSON.parse(data);
		 document.getElementById("returndata").value=data.msg;
	 }
	 );
 }
</script>

</head>
<body>
	<label>短信内容</label>
	<textarea rows="15" cols="50" id="message"></textarea>
	<label>短信接收方</label>
	<textarea rows="15" cols="80" id="receiver"></textarea>
	
	<button onclick="posttest();">确定</button>
    
	<div>
		<label>返回结果</label>
	<textarea rows="15" cols="100" id="returndata"></textarea>
	</div>
</body>
</html>