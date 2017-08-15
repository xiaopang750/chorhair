<%@include file="../include/env.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>店铺展示</title>
	<link rel="stylesheet" href="<%=basePath%>css/core/chorhair.css">
	<link rel="stylesheet" href="<%=basePath%>css/main/shop/index.css">
</head>
<body>
	<div class="shop-head">
		<div class="shop-bg">
			<img src="<%=assetsPath%>main/shop/shop-bg.jpg" alt="">
		</div>
		<div class="shop-title">
			<p class="cn">店铺</p>
			<p class="en">Shop</p>
		</div>
	</div>
	<ul class="shop-list"></ul>

	<script src="../statics/seajs/sea.js"></script>
	<script src="../statics/seajs/config.js"></script>
	<script>seajs.use('main/shop/index');</script>
</body>
</html>