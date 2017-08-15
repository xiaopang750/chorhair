<%@include file="../include/env.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>我的推广</title>
	<link rel="stylesheet" href="<%=basePath%>css/core/chorhair.css">
	<link rel="stylesheet" href="<%=basePath%>css/main/user/promotion.css">
</head>
<body class="promotion">
	<div class="promotion-wrap">
		<p>二维码</p>
		<p class="loading">正在生成二维码...</p>
	</div>
	<div class="promotion-btn">推广记录</div>

	<script src="../statics/seajs/sea.js"></script>
	<script src="../statics/seajs/config.js"></script>
	<script>seajs.use('main/user/promotion');</script>
</body>
</html>