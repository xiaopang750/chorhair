/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("widget/dom/modify",["./selectText","../../lib/until/until","../../widget/dom/tip"],function(e){function t(e,t){this.ele=e,this.org=this.ele.html(),this.name=t.inputName,this.onblur=t.onblur}var r=e("./selectText"),i=e("../../lib/until/until"),n=new i,o=e("../../widget/dom/tip");$.fn.modify=function(e){e=e||{},e.inputName=e.inputName||"",e.onblur=e.onblur||null;var r;return $(this).each(function(){r=new t($(this),e),r.init()})},t.prototype={init:function(){this.initWidget(),this.makeInput(),this.events()},events:function(){var e=this;this.oWrap.on("click","[data-ele = ui-modify-btn]",function(){var t;return t=$(this).html(),"修改"==t?e.editShow($(this)):"保存"==t&&e.save($(this)),!1})},initWidget:function(){this.oWrap=this.ele.parent(),this.oWrap.css("position","relative")},save:function(e){var t,r,i,a,s,c,l;i=e.attr("page"),a=e.attr("site"),s=e.parents("[data-ele = list-wrap]").find("[data-name = modify]").val()+".html",c={},l={},c.pageUrl=s,r=this.oInput.val(),t=this,l={pkPage:i,pkSite:a,type:"pageBasicInfo",content:JSON.stringify(c)},n.reqUrl=R.interfaces.modify_site_url,n.reqParam=l,n.req(function(i){t.eidtHide(r),e.html("修改"),o.say(i.msg)},function(r){t.eidtHide(),e.html("修改"),o.say(r.msg)})},makeInput:function(){this.oInput=$("<input data-name="+this.name+' modify-ele="modify-nodo">'),this.oInput.hide(),this.oWrap.append(this.oInput),this.text=this.ele.html(),this.oInput.val(this.text)},position:function(){var e,t,r,i;e=this.ele.position().left,t=this.ele.position().top,r=this.ele.innerWidth(!0),i=this.ele.innerHeight(!0),this.oInput.css({position:"absolute",left:e,top:t,width:r,height:i,border:"1px solid #ccc",fontSize:"12px",display:"block"})},_judgeShow:function(){var e;e=this.oInput.is(":visible"),e?this.oInput.hide():this.oInput.show()},editShow:function(e){var t=this.oInput.val().length;e.html("保存"),this.position(),this.oInput.show(),r(this.oInput,t,t)},eidtHide:function(e){e?this.ele.html(e):(this.ele.html(this.org),this.oInput.val(this.org)),this.oInput.hide()}}}),define("widget/dom/selectText",[],function(){function e(e,t,r){var i,n;if(i=e.val().length,n=e[0],n.setSelectionRange)n.setSelectionRange(t,r);else{var o=n.createTextRange();o.collapse(!0),o.moveStart("character",i),o.moveEnd("character",r-t),o.select()}}return e}),define("widget/dom/tip",[],function(e,t,r){function i(){this.timer=null,this.hideTime=2500,this.create()}i.prototype={create:function(){this.oWrap=$("<div><span></span></div>"),this.oWrap.css({position:"fixed",left:"0",top:"0",width:"100%",height:"32px",textAlign:"center",zIndex:8e3,display:"none"}),this.oTip=this.oWrap.children().eq(0),this.oTip.css({height:"32px",lineHeight:"32px",borderTop:"1px solid #aaaaab",borderBottom:"1px solid #b98710",borderLeft:"1px solid #b98710",borderRight:"1px solid #b98710",borderBottomLeftRadius:"3px",borderBottomRightRadius:"3px",color:"#fff",fontSize:"14px",padding:"0 20px",background:"#eaa000",display:"none"}),$("body").append(this.oWrap)},say:function(e){var t=this;clearTimeout(this.timer),this.oTip.html(e),this.show(),this.timer=setTimeout(function(){t.hide()},this.hideTime)},show:function(){this.oWrap.show(),this.oTip.css("display","inline-block")},hide:function(){var e=this;this.oTip.fadeOut(function(){e.oWrap.hide()})}};var n=new i;r.exports=n});