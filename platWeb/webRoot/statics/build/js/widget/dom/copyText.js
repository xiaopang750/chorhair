/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("widget/dom/copyText",["./tip"],function(e){function t(e){var t,i;t=e.val(),i=e[0],window.clipboardData?(window.clipboardData.setData("text",t),r.say("已复制到剪贴版")):(r.say("复制失败，请按ctrl+c复制"),i.setSelectionRange&&(i.select(),i.setSelectionRange(0,t.length)))}var r=e("./tip");return t}),define("widget/dom/tip",[],function(e,t,r){function i(){this.timer=null,this.hideTime=2500,this.create()}i.prototype={create:function(){this.oWrap=$("<div><span></span></div>"),this.oWrap.css({position:"fixed",left:"0",top:"0",width:"100%",height:"32px",textAlign:"center",zIndex:8e3,display:"none"}),this.oTip=this.oWrap.children().eq(0),this.oTip.css({height:"32px",lineHeight:"32px",borderTop:"1px solid #aaaaab",borderBottom:"1px solid #b98710",borderLeft:"1px solid #b98710",borderRight:"1px solid #b98710",borderBottomLeftRadius:"3px",borderBottomRightRadius:"3px",color:"#fff",fontSize:"14px",padding:"0 20px",background:"#eaa000",display:"none"}),$("body").append(this.oWrap)},say:function(e){var t=this;clearTimeout(this.timer),this.oTip.html(e),this.show(),this.timer=setTimeout(function(){t.hide()},this.hideTime)},show:function(){this.oWrap.show(),this.oTip.css("display","inline-block")},hide:function(){var e=this;this.oTip.fadeOut(function(){e.oWrap.hide()})}};var n=new i;r.exports=n});