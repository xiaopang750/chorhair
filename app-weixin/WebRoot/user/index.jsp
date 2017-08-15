<%@include file="../include/env.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta name="format-detection" content="telephone=no">
	<title>虫二美发</title>
	<link rel="stylesheet" href="<%=basePath%>css/core/chorhair.css">
	<link rel="stylesheet" href="<%=basePath%>css/main/user/index.css">
</head>
<body class="home">
	<div class="home-bg">
		<img class="bg" src="<%=assetsPath%>main/home/bg.jpg" alt="">
	</div>
	<div class="shadow" animation>
		<img src="<%=assetsPath%>main/home/shadow.png" alt="">
	</div>
	<div class="text1" animation>
		<img src="<%=assetsPath%>main/home/text1.png" alt="">
	</div>
	<div class="text2" animation>
		<img src="<%=assetsPath%>main/home/text2.png" alt="">
	</div>
	<div class="text3" animation>
		<img src="<%=assetsPath%>main/home/text3.png" alt="">
	</div>
	<div class="text4" animation>
		<img src="<%=assetsPath%>main/home/text4.png" alt="">
	</div>
	<div class="line1" animation>
		<span class="dot"></span>
	</div>
	<div class="line2" animation>
		<span class="dot"></span>
	</div>
	<div class="icons-group" animation>
		<span class="icons-user">
			<i class="icon icon-user"></i>
		</span>
		<span class="icons-shop"><a href="../shop/index.jsp"><i class="icon icon-shop"></i></a></span>
	</div>
	<div class="btn-group">
		<span class="btn-single" animation>单次体验</span>
		<span class="btn-fullyear" animation>全年不限次</span>
	</div>
	<div class="user-info">
		<div class="img"><img user-head-img src="" alt=""></div>
		<div class="name" user-nickname></div>
		<div class="message">
			<p addr-wrap>
				<span class="icon icon-address"></span>
				<span user-addr></span>
			</p>
			<p phone-wrap>
				<span class="icon icon-phone"></span>
				<span phone-num></span>
			</p>
		</div>
		<div class="appoint">
			<span class="icon icon-order"></span>
			<span>我的订单</span>	
		</div>
		<div class="package">
			<span class="icon icon-user"></span>
			<span>我的套餐</span>
		</div>
		<div class="popular">
			<span class="icon icon-erweima"></span>
			<span>我的推广</span>
		</div>
	</div>	

	<script src="../statics/seajs/sea.js"></script>
	<script src="../statics/seajs/config.js"></script>
	<script type="text/javascript">
	document.body.addEventListener('touchmove', function(e) {
	    e.stopPropagation();
	    e.preventDefault();
	});
	</script>
	<script>seajs.use('main/user/index');</script>
</body>
</html>