define(function(t){t("../../driver/global"),t("../../touch/hammer");{var n=R.Class.create(R.util,{initialize:function(){this.windowH=$(window).height(),this.menu=$(".user-info"),this.userInfo(),this.setHeight(),void 0==sessionStorage.firstLoad?(sessionStorage.firstLoad=!0,this.animation()):(sessionStorage.firstLoad=!1,this.setPos()),this.events()},userInfo:function(){this.req("/weixin/userinfo.php",this.param,function(t){var n=JSON.parse(t.data);$("[user-head-img]").attr("src",n.headimageurl),$("[user-nickname]").html(n.nickname),""!=n.addr?$("[user-addr]").html(n.addr):$("[addr-wrap]").hide(),n.cellphone?(sessionStorage.userPhone=n.cellphone,$("[phone-wrap]").show(),$("[phone-num]").html(sessionStorage.userPhone)):$("[phone-wrap]").hide()})},setHeight:function(){$("body.home").height(this.windowH),$(".home-bg").height(this.windowH),$(".shadow").height(this.windowH),$(".user-info .img").height($(".user-info .img").width()-10),this.menu.height(this.windowH)},setPos:function(){$("[animation]").addClass("old")},animation:function(){setTimeout(function(){$(".shadow").css("-webkit-transform","translateX(0) translateY(0)")},100),setTimeout(function(){$(".text1").css("-webkit-transform","translateX(0) rotate(0)")},300),setTimeout(function(){$(".text2").css("-webkit-transform","translateX(0) rotate(0)")},650),setTimeout(function(){$(".line1").css("-webkit-transform","translateX(0)"),$(".line2").css("-webkit-transform","translateX(0)")},1200),setTimeout(function(){$(".text3").css("opacity","1"),$(".text4").css("opacity","1")},1700),setTimeout(function(){$(".btn-single").css("-webkit-transform","translateY(0)")},1900),setTimeout(function(){$(".btn-fullyear").css("-webkit-transform","translateY(0)")},2100)},events:function(){$(".btn-single").on("click",function(){window.location="../goods/index.jsp?type=2"}),$(".btn-fullyear").on("click",function(){window.location="../goods/index.jsp?type=1"}),$(".icons-user").on("click",function(){return $("body").css("-webkit-transform","translateX(70%)"),!1}),$(".user-info").hammer().on("tap",function(){return!1}),$("body").hammer().on("tap",function(){$("body").css("-webkit-transform","translateX(0)")}),$(".user-info .appoint").on("click",function(){window.location=R.pkUser?"../appoint/index.jsp":"../user/login.jsp"}),$(".user-info .package").on("click",function(){window.location=R.pkUser?"../user/package.jsp":"../user/login.jsp"}),$(".user-info .popular").on("click",function(){window.location=R.pkUser?"../user/promotion.jsp":"../user/login.jsp"})}});new n}});