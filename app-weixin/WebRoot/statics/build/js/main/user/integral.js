define(function(i){i("../../driver/global");{var e=R.Class.create(R.util,{initialize:function(){this.checkLogin(),this.setHeight(),this.userInfo(),this.load()},setHeight:function(){$(".user-img").height($(".user-img").width()-10)},userInfo:function(){this.req("/weixin/userinfo.php",this.param,function(i){var e=JSON.parse(i.data);$("[user-head-img]").attr("src",e.headimageurl),$("[user-nickname]").html(e.nickname)})},load:function(){this.req("/weixinqrcode/querycodescan.php",this.param,function(i){if(i.data){var e=JSON.parse(i.data);$(".integral-num .value").html(e.count),$(".promotion-num .value").html(e.count)}})}});new e}});