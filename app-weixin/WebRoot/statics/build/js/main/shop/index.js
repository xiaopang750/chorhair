define(function(t){t("../../driver/global");{var e=R.Class.create(R.util,{initialize:function(){this.setHeight(),this.loadData(),this.events()},setHeight:function(){$(".shop-bg").height($(".shop-bg").width())},loadData:function(){var e=this,i={pagesize:"10",curpage:"1",contenttype:"shop"};this.req("/content/querybykey.php",i,function(i){if(""!=i.data){var s=JSON.parse(i.data),a={shopArr:s,pkUser:localStorage.pkUser},n=$(".shop-list"),h=t("../../tpl/shop/list");e.render(n,h,a,"append");var o=$(".shop-list .img").width();$(".shop-list li").height(o)}})},events:function(){}});new e}});