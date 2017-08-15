<%@include file="../include/env.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>绑定手机号</title>
	<link rel="stylesheet" href="<%=basePath%>css/core/chorhair.css">
	<link rel="stylesheet" href="<%=basePath%>css/main/user/login.css">
</head>
<body class="login">
	<div class="bg">
		<img src="<%=assetsPath%>main/user/login-bg.jpg" alt="">
	</div>
	<div class="logo">
		<img src="<%=assetsPath%>main/user/logo.png" alt="">
	</div>
	<div class="input-wrap">
		<input user-phone type="number" placeholder="请输入手机号">
	</div>
	<div class="code-wrap">
		<div class="code-bg"><input user-code type="number" placeholder="验证码"></div>
		<button class="send-code active" send-code>发送验证码</button>
	</div>
	<button class="btn-submit" btn-submit>提交</button>
	<script src="../statics/seajs/sea.js"></script>
	<script src="../statics/seajs/config.js"></script>
	<script>seajs.use('main/user/login');</script>
</body>
</html>