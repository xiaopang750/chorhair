<%@include file="../include/env.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>我的套餐</title>
	<link rel="stylesheet" href="<%=basePath%>css/core/chorhair.css">
	<link rel="stylesheet" href="<%=basePath%>css/main/user/package.css">
</head>
<body>
	<div class="package-wrap">
		<div class="package-bg">
			<img src="<%=assetsPath%>main/appoint/appoint-bg.jpg" alt="">
		</div>
		<div class="package-tab">
			<div class="package-tab-item" package-type="1">
				<span>烫</span>
			</div>
			<div class="package-tab-item" package-type="2">
				<span>染</span>
			</div>
			<div class="package-tab-item" package-type="3">
				<span>护</span>
			</div>
			<div class="package-tab-item" package-type="4">
				<span>养</span>
			</div>
		</div>
	</div>
	<div package-wrap>
		<ul class="package-list"></ul>	
	</div>

	<script src="../statics/seajs/sea.js"></script>
	<script src="../statics/seajs/config.js"></script>
	<script>seajs.use('main/user/package');</script>
</body>
</html>