<%@include file="../include/env.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>我的积分</title>
	<link rel="stylesheet" href="<%=basePath%>css/core/chorhair.css">
	<link rel="stylesheet" href="<%=basePath%>css/main/user/integral.css">
</head>
<body>
	<div class="user-img"><img user-head-img src="" alt=""></div>
	<p class="user-name" user-nickname></p>
	<p class="user-integral">
		<span>我的积分：</span>
		<strong class="integral-num"><span class="value">0</span><span class="unit">分</span></strong>
	</p>
	<p class="user-promotion">
		<span>我的推广人数：</span>
		<strong class="promotion-num"><span class="value">0</span><span class="unit">人</span></strong>
	</p>
	<script src="../statics/seajs/sea.js"></script>
	<script src="../statics/seajs/config.js"></script>
	<script>seajs.use('main/user/integral');</script>
</body>
</html>