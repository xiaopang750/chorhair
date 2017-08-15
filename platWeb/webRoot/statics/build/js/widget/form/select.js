/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("widget/form/select",["../../lib/template/template","../dom/roll","../../lib/event/scroll"],function(e){function t(e,t,r){this.ele=e,this.index=r,this.options=t,this.first=!0}var r=e("../../lib/template/template");e("../../../css/widget/form/select.css"),e("../dom/roll"),$.fn.select=function(e){e=e||{},e.w=e.width||"100px",e.h=e.height||"30px",e.onselect=e.onselect||null,e.selectClass=e.selectClass||"ui-select";var r,i,n;return r=$(this).length,n=[],$(this).each(function(o){i=new t($(this),e,r-o),i.init(),n.push(i)}),n},t.prototype={init:function(){this.initSelect(),this.makeWrap(),this.getList(),this.events()},render:function(e,t){var i=r.compile(e),n=i(t);this.ele.html(n)},events:function(){var e,t;e=this,this.oHead.on("click",function(){return e.judgeShow(),!1}),this.oUl.on("click","[sc = ui-select-list]",function(){t=$(this).index(),e.selectList(t),e.options.onselect&&e.options.onselect(e.ele.children().eq(t))}),$(document).on("click",function(){e.judgeShow("doc")})},initSelect:function(){this.ele.css("visibility","hidden")},makeWrap:function(){var e,t;this.oWrap=$('<div sc="ui-select-wrap" class="ui-select-wrap"></div>'),this.oHead=$('<div sc="ui-select-head" class="ui-select-head"></div>'),this.oUl=$('<ul sc="ui-select-content" class="ui-select-content"></ul>'),this.oHeadContent=$('<div sc="ui-head-content" class="ui-head-content"></div>'),this.oTrangle=$('<div sc="ui-head-trangle" class="ui-head-trangle"><span class="trangle"></span></div>'),e=this.options.w,t=this.options.h,this.oWrap.addClass(this.options.selectClass),this.oWrap.css({width:e,height:t,position:"relative",zIndex:this.index}),this.oHead.css({width:"100%",height:t}),this.oUl.css({position:"absolute",width:"100%",left:0,top:t,display:"none",overflow:"hidden"}),this.ele.wrap(this.oWrap),this.ele.after(this.oUl),this.ele.before(this.oHead),this.oHead.append(this.oHeadContent),this.oHead.append(this.oTrangle)},getList:function(){var e,t,r,i,n,o,a,s=this.ele.children();for(t=s.length,this.arrList=[],o=!1,this.oUl.html(""),e=0;t>e;e++){i=s.eq(e).attr("ui-html");var r=$('<li sc="ui-select-list">'+i+"</li>");n="selected"==s.eq(e).attr("selected")?!0:!1,n&&(this.oHeadContent.html(i),o=!0),r.css("width","100%"),this.arrList.push(r),this.oUl.append(r)}o||(a=s.eq(0).attr("ui-html"),this.oHeadContent.html(a)),this.oUl.roll(100)},judgeShow:function(e){var t;t=this.oUl.attr("visible"),e?t&&this.listHide():t?this.listHide():this.listShow()},listShow:function(){this.oUl.show(),this.oUl.attr("visible","yes")},listHide:function(){this.oUl.hide(),this.oUl.attr("visible","")},selectList:function(e){var t,r,i;t=this.ele.children().eq(e),t.attr("selected","selected"),r=this.arrList[e],i=r.html(),this.oHeadContent.html(i)}}}),define("widget/dom/roll",["lib/event/scroll"],function(e){function t(e,t){this.ele=e,this.h=t,this.nowTop=0}e("lib/event/scroll"),$.fn.roll=function(e){e=e||60;var r;return $(this).each(function(){r=new t($(this),e),r.init()})},t.prototype={init:function(){this.getHeight(),this.max>this.h&&this.start()},start:function(){this.initWrap(),this.makeWrap(),this.events()},events:function(){var e=this;e=this,this.ele.scroll(function(t){t?e.nowTop+=10:e.nowTop-=10,e.move()})},initWrap:function(){var e,t,r,i;e=this.ele.innerWidth(!0),t=this.ele.css("left"),r=this.ele.css("top"),i=this.ele.children(),this.oContentWrap=$('<div sc="ui-roll-wrap"></div>'),this.ele.append(this.oContentWrap),this.oContentWrap.append(i),this.oContentWrap.css({top:0,left:0,width:this.ele.outerWidth(!0),position:"absolute"}),this.ele.css({height:this.h,overflow:"hidden"})},makeWrap:function(){this.oRollWrap=$("<div></div>"),this.oMove=$("<div></div>"),this.oMove.css({position:"absolute",left:0,top:0,height:"10px",width:"10px",background:"green",overflow:"hidden"}),this.oRollWrap.css({position:"absolute",right:0,top:0,height:this.max,width:"10px",background:"#ccc",overflow:"hidden"}),this.oRollWrap.append(this.oMove),this.ele.append(this.oRollWrap)},getHeight:function(){this.max=this.ele.height()+parseInt(this.ele.css("padding-top"))+parseInt(this.ele.css("padding-bottom"))},move:function(){var e,t,r;e=this.h-10,0>this.nowTop&&(this.nowTop=0),this.nowTop>e&&(this.nowTop=e),t=this.nowTop/e,r=-t*(this.max-this.h),this.oMove.css("top",this.nowTop),this.oContentWrap.css("top",r)}}});