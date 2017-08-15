/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("driver/base/base_util",["../../lib/ooClass/class","../../util/http/bodyParse","../../util/http/request","../../widget/dom/tip","../../widget/dom/dialog"],function(e,t,i){var n=e("../../lib/ooClass/class"),o=e("../../util/http/bodyParse"),r=e("../../util/http/request"),a=n.create({initialize:function(){this.reqParam={},this.reqUrl="",this.reqAsync=!0},parse:function(){return o()},render:function(e,t,i,n){var o=t(i);if(n){if("prepend"==n){var r=$(o);return e.prepend(r),r}if("append"==n){var r=$(o);return e.append(r),r}if("after"==n){var r=$(o);return e.after(r),r}if("before"==n){var r=$(o);return e.before(r),r}}else e.html(o)},req:function(e,t,i){var n=r({url:this.reqUrl,data:this.reqParam,async:this.reqAsync,sucDo:function(t,i){e&&e(t,i)},noDataDo:function(e,i){t&&t(e,i)},failDo:function(e){i&&i(e)}});return n},log:function(e){window.console&&console.log(e)},placeHolder:function(){"placeholder"in document.createElement("input")||$("input[placeholder],textarea[placeholder]").each(function(){var e=$(this),t=e.attr("placeholder");""===e.val()&&e.val(t).addClass("placeholder").css("color","#a9a9a9"),e.focus(function(){e.val()===t&&e.val("").removeClass("placeholder").css("color","#555555")}).blur(function(){""===e.val()&&e.val(t).addClass("placeholder").css("color","#a9a9a9")}).closest("form").submit(function(){e.val()===t&&e.val("")})})}});i.exports=a}),define("lib/ooClass/class",[],function(e,t,i){function n(e){return this instanceof n||!f(e)?void 0:r(e)}function o(e){var t,i;for(t in e)i=e[t],n.Mutators.hasOwnProperty(t)?n.Mutators[t].call(this,i):this.prototype[t]=i}function r(e){return e.extend=n.extend,e.implement=o,e}function a(){}function s(e,t,i){for(var n in t)if(t.hasOwnProperty(n)){if(i&&-1===d(i,n))continue;"prototype"!==n&&(e[n]=t[n])}}n.create=function(e,t){function i(){e.apply(this,arguments),this.constructor===i&&this.initialize&&this.initialize.apply(this,arguments)}return f(e)||(t=e,e=null),t||(t={}),e||(e=t.Extends||n),t.Extends=e,e!==n&&s(i,e,e.StaticsWhiteList),o.call(i,t),r(i)},n.extend=function(e){return e||(e={}),e.Extends=this,n.create(e)},n.Mutators={Extends:function(e){var t=this.prototype,i=c(e.prototype);s(i,t),i.constructor=this,this.prototype=i,this.superclass=e.prototype},Implements:function(e){l(e)||(e=[e]);for(var t,i=this.prototype;t=e.shift();)s(i,t.prototype||t)},Statics:function(e){s(this,e)}};var c=Object.__proto__?function(e){return{__proto__:e}}:function(e){return a.prototype=e,new a},u=Object.prototype.toString,l=Array.isArray||function(e){return"[object Array]"===u.call(e)},f=function(e){return"[object Function]"===u.call(e)},d=Array.prototype.indexOf?function(e,t){return e.indexOf(t)}:function(e,t){for(var i=0,n=e.length;n>i;i++)if(e[i]===t)return i;return-1};i.exports=n}),define("util/http/bodyParse",[],function(){function e(){var e,t,i,n,o,r,a;if(window.location.search){for(e=decodeURIComponent(window.location.search.split("?")[1]),t=e.split("&"),n=t.length,a={},r=0;n>r;r++)i=t[r].split("="),o=i.length,a[i[0]]=i[1];return a}return{}}return e}),define("util/http/request",["widget/dom/tip","widget/dom/dialog"],function(e){function t(e){var t,o,r,a,s,c,u,l,f,d,p;return t=e.url||null,o=e.data||"",r=!0,a=e.noDataDo||null,s=e.sucDo||null,c=e.failDo||null,l=e.unLoginDo||null,u=e.beforeDo||null,f=e.otherStatus||null,d=e.type||"post",p=e.dataType||"json",t?($.ajax({url:t,data:o,type:d,async:r,dataType:p,timeout:999999,beforeSend:function(){u&&u()},success:function(e){i(t,e,s,a,l,f)},error:function(e){n(t,e.statusText),c&&c(e.statusText)}}),void 0):(window.console&&console.log("请传入请求地址"),void 0)}function i(e,t,i,n,o,r){if("001"==t.code)i&&i(t,t.code);else{if("002"==t.code)return n&&n(t,t.code),void 0;"003"==t.code}if(r)for(var a in r)a==t.err&&r[a](t)}function n(e,t){window.console&&(console.log("接口     "+e+"    接口报错"),console.log(t))}return e("widget/dom/tip"),e("widget/dom/dialog"),t}),define("widget/dom/tip",[],function(e,t,i){function n(){this.timer=null,this.hideTime=2500,this.create()}n.prototype={create:function(){this.oWrap=$("<div><span></span></div>"),this.oWrap.css({position:"fixed",left:"0",top:"0",width:"100%",height:"32px",textAlign:"center",zIndex:8e3,display:"none"}),this.oTip=this.oWrap.children().eq(0),this.oTip.css({height:"32px",lineHeight:"32px",borderTop:"1px solid #aaaaab",borderBottom:"1px solid #b98710",borderLeft:"1px solid #b98710",borderRight:"1px solid #b98710",borderBottomLeftRadius:"3px",borderBottomRightRadius:"3px",color:"#fff",fontSize:"14px",padding:"0 20px",background:"#eaa000",display:"none"}),$("body").append(this.oWrap)},say:function(e){var t=this;clearTimeout(this.timer),this.oTip.html(e),this.show(),this.timer=setTimeout(function(){t.hide()},this.hideTime)},show:function(){this.oWrap.show(),this.oTip.css("display","inline-block")},hide:function(){var e=this;this.oTip.fadeOut(function(){e.oWrap.hide()})}};var o=new n;i.exports=o}),define("widget/dom/dialog",[],function(e,t,i){function n(e){e=e||{},this.type=e.type||"confirm",this.title=e.title||"",this.content=e.content||"",this.enterKey=e.enterKey?e.enterKey:!1,this.escKey=e.escKey?e.escKey:!1,this.index=e.index||4e3,this.box=null,this.boxStr=e.boxStr||'<div class="confirmbox out-box '+this.type+'" sc="confirm-box" sc="box">'+'<h2 class="ba-tc ba-font-18 ba-red ba-mt-20" sc="box-title">'+this.title+"</h2>"+'<p class="ba-font-20 ba-tc ba-mt-10 ba-mb-10" sc="box-content">'+this.content+"</p>"+'<div class="ba-tc">'+'<a href="javascript:;" class="btn btn-danger ba-mr-10" sc="confirm">确定</a>'+'<a href="javascript:;" class="btn btn-primary" sc="close">取消</a>'+"</div>"+"</div>",this.boxSelector=e.boxSelector||"",this.boxTpl=e.boxTpl||"",this.boxData=e.boxData||{},this.onClosed=e.onClosed||null,this.onStart=e.onStart||null,this.onConfirm=e.onConfirm||null,this.overLayHide=e.overLayHide||null,this.init()}n.prototype={init:function(){this.render(),this.events(),this.makeLay()},render:function(){var e,t,i=this.create();e=-i.h/2+"px",t=-i.w/2+"px",this.position(i.box,"50%","50%",e+" 0 0 "+t),this.box=i.box},makeRound:function(){var e='<div class="clearfix level-t"><div class="lt fl"></div><div class="t fl"></div><div class="rt fl"></div></div><div class="clearfix level-m"><div class="l fl"></div><div class="main fl"></div><div class="r fl"></div></div><div class="clearfix level-b"><div class="lb fl"></div><div class="b fl"></div><div class="rb fl"></div></div>';this.boxSelector.append($(e))},makeLay:function(){var e=$("[sc = Dialog-lay]").length;return e?(this.oLay=$("[sc = Dialog-lay]"),void 0):(this.oLay=$('<div sc="Dialog-lay"></div>'),this.oLay.css({width:"100%",height:"100%",position:"fixed",left:0,top:0,zIndex:3e3,background:"#000",opacity:"0.3",display:"none"}),$("body").append(this.oLay),void 0)},set:function(e,t){this.oLay.css({left:e,top:t})},create:function(){var e,t,i;return this.boxSelector?e=$(this.boxSelector):this.boxTpl?(e=$(this.boxTpl(this.boxData)),$("body").append(e)):(e=$(this.boxStr),$("body").append(e)),e.wrap("<div dialog-wrap></div>"),t=e.outerWidth(!0),i=e.outerHeight(!0),this.oBox=e,this.oWrap=this.oBox.parents("[dialog-wrap]"),{box:this.oWrap,w:t,h:i}},events:function(){var e=this;this.box.on("click","[sc = close]",function(){e.close(e.box)}),this.box.on("click","[sc = confirm]",function(){e.onConfirm&&e.onConfirm.call(e,$(this))}),$(document).on("click",function(t){e.overLayHide&&$(t.target).parents()[0]!=e.oBox[0]&&$(t.target)[0]!=e.oBox[0]&&e.close()})},position:function(e,t,i,n){e.css({position:"fixed",left:t,top:i,margin:n,zIndex:this.index,display:"none"})},show:function(){this.oWrap.show(),this.oLay.show(),this.onStart&&this.onStart.call(this)},close:function(e){this.oWrap.hide(),this.oLay.hide(),e!==!1&&this.onClosed&&this.onClosed.call(this)},fix:function(){this.box.css("position","fixed")},dom:function(){return this.oBox},boxTitle:function(){return this.oBox.find("[sc = box-title]")},boxContent:function(){return this.oBox.find("[sc = box-content]")},refreshData:function(e){this.oWrap.html(this.boxTpl(e))}},i.exports=n});