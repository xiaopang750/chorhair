<%@include file="../include/env.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>我的订单</title>
	<link rel="stylesheet" href="<%=basePath%>css/core/chorhair.css">
	<link rel="stylesheet" href="<%=basePath%>css/main/appoint/index.css">
</head>
<body>
	<div class="appoint-wrap">
		<div class="appoint-bg">
			<img src="<%=assetsPath%>main/appoint/appoint-bg.jpg" alt="">
		</div>
		<div class="appoint-tab">
			<div class="appoint-tab-item active" orderstatus="servering">
				<span>服务中</span>
			</div>
			<div class="appoint-tab-item" orderstatus="servered">
				<span>已完成</span>
			</div>
			<div class="appoint-tab-item" orderstatus="appointment">
				<span>预约单</span>
			</div>
		</div>
	</div>
	<div appoint-wrap>
		<ul class="appoint-list" appoint-list></ul>
	</div>	

	<script src="../statics/seajs/sea.js"></script>
	<script src="../statics/seajs/config.js"></script>
	<script>seajs.use('main/appoint/index');</script>
</body>
</html>