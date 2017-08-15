<%@include file="../include/env.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>商品套餐</title>
	<link rel="stylesheet" href="<%=basePath%>css/core/chorhair.css">
	<link rel="stylesheet" href="<%=basePath%>css/main/goods/index.css">
</head>
<body>
	<div class="series">
		<div class="series-bg">
			<img src="<%=assetsPath%>main/goods/series-bg.jpg" alt="">
		</div>
		<div class="series-list">
			<div class="series-title">
				<p class="cn">系列</p>
				<span class="arrow"></span>
			</div>	
			<div class="series-wrap">
				<span class="series-item" fairtype="1">烫</span>
				<span class="series-item" fairtype="2">染</span>
				<span class="series-item" fairtype="3">护</span>
				<span class="series-item" fairtype="4">养</span>
				<span class="close"></span>
			</div>
		</div>
	</div>
	<span class="icon icon-quanbubankuai" all-series-item></span>
	<ul class="goods-list"></ul>
	<div class="loading ba-none ba-tc ba-mt-15" loading>
		<img src="../statics/assets/lib/loading/loading.gif" alt="">
	</div>
	<script src="../statics/seajs/sea.js"></script>
	<script src="../statics/seajs/config.js"></script>
	<script>seajs.use('main/goods/index');</script>
</body>
</html>