define(function(a){a("../../driver/global"),a("../../touch/hammer");{var e=R.Class.create(R.util,{initialize:function(){this.checkLogin(),this.param={},this.loadData(this.param),this.events()},loadData:function(e){var t=this;this.req("/customercombo/querybyuser.php",e,function(r){var i=$(".package-list");if(""!=r.data){var s=JSON.parse(r.data),n={packageArr:s},p=a("../../tpl/user/package");t.render(i,p,n),$("[package-wrap]").find(".no-result").remove()}else{if(i.html(""),e)var c=$('<p class="no-result">暂无办理该类型套餐</p>');else var c=$('<p class="no-result">暂无办理套餐</p>');$("[package-wrap]").find(".no-result").length||$("[package-wrap]").append(c)}})},events:function(){var a=this;$(".package-tab-item").hammer().on("tap",function(){$(this).addClass("active").siblings().removeClass("active"),a.param.fairtype=$(this).attr("package-type"),a.loadData(a.param)})}});new e}});