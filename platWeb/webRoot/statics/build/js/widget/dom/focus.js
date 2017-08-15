/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("widget/dom/focus",["../../lib/touch/hammer","../../lib/underscore/underscore"],function(require,exports,module){function Focus(e){e=e||{},this.oWrap=e.oWrap||null,this.oDataWrap=this.oWrap.find("[widget-role = focus-data-wrap]")||null,this.oDotWrap=this.oWrap.find("[widget-role = focus-dot-wrap]")||null,this.requestType=e.requestType||"post",this.speed=e.speed||500,this.auto=e.auto?!0:true,this.roudTime=e.roudTime||5e3,this.url=e.url||"",this.param=e.param||null,this.compailed=e.compailed||'<%_.each(data, function(focus){%><li><img src="<%=focus.slide_pic%>" link="<%=focus.slide_url%>"/></li><%})%>',this.orgWidth=this.oWrap.attr("widget-width"),this.orgHeight=this.oWrap.attr("widget-height"),this.scale=this.oWrap.attr("widget-scale"),this.sensity=e.sensity||50,this._start=0,this._iNow=0,this._wrapWidth=0,this.timer=null,this.roundTimer=null,this._lock=!1,this._ORIENT_DELAY=500,this._ORGDOTBG="#fff",this._ACTDOTBG="#ff5000",this.lock=!1}require("../../lib/touch/hammer"),require("../../lib/underscore/underscore"),Focus.prototype={init:function(){this.load(),this.auto?this.autoPlay():null},addEvent:function(){var e=this;this.oWrap.hammer().on("touch dragleft dragright dragend tap",function(t){switch(t.type){case"touch":clearInterval(e.roundTimer),$.css3(e.oDataWrap,{transition:"none"});break;case"dragleft":t.gesture.preventDefault(),e.dragmove(e.oDataWrap,t);break;case"dragright":t.gesture.preventDefault(),e.dragmove(e.oDataWrap,t);break;case"dragend":var r,i,n;r=t.gesture,i=r.deltaX,n=r.direction,e.judge(i,n),e.autoPlay();break;case"tap":var o=t.srcElement.getAttribute("link");o&&(window.location=o)}}),window.onorientationchange=function(){clearTimeout(e.timer),e.timer=setTimeout(function(){e.setStyle(e.num),e.setDotWrap(),$.css3(e.oDataWrap,{transition:"none",transform:"translateX(0)"}),e.autoPlay()},e._ORIENT_DELAY)}},dragmove:function(e,t){var r,i;i=t.gesture.deltaX,r=i,$.css3(e,{transform:"translateX("+r+"px)"})},autoPlay:function(){var e=this;clearInterval(this.roundTimer),this.roundTimer=setInterval(function(){e.judge(e.sensity+1,"left")},this.roudTime)},judge:function(e,t){Math.abs(e)>this.sensity?"left"==t?(this._iNow++,this._iNow==this._aLi.length&&(this._iNow=0),this.animate(-this._wrapWidth)):(this._iNow--,-1==this._iNow&&(this._iNow=this._aLi.length-1),this.animate(this._wrapWidth)):this.animate(0)},animate:function(e){var t=this;$.css3(this.oDataWrap,{transition:this.speed+"ms",transform:"translateX("+e+"px)"}),setTimeout(function(){t._aDot.eq(t._iNow).css("background",t._ACTDOTBG).siblings().css("background",t._ORGDOTBG),t.postion(t._aLi)},this.speed)},setStyle:function(){var e,t,r;e=this.calc(),t=e.w,r=e.h,this._wrapWidth=t,this._aLi=this.oDataWrap.children(),this.oWrap.css({width:t+"px",height:r+"px",position:"relative",overflow:"hidden"}),this._aLi.css({width:t+"px",height:r+"px",position:"absolute",top:0}),this.oDataWrap.find("img").css({width:"100%",height:"100%",display:"block"}),this.oDataWrap.css({position:"relative"}),this.postion(this._aLi)},postion:function(e){$.css3(this.oDataWrap,{transition:"none",transform:"translateX(0)"}),e.hide(),e.eq(this._iNow%e.length).show(),e.eq(this._iNow%e.length).css({left:0}),this.getPrev(e).css({left:-this._wrapWidth+"px",display:"block"}),this.getNext(e).css({left:this._wrapWidth+"px",display:"block"})},getPrev:function(e){return e.eq((this._iNow+e.length-1)%e.length)},getNext:function(e){return e.eq((this._iNow+1)%e.length)},calc:function(){var e,t;return e=document.documentElement.clientWidth*this.scale,t=e/this.orgWidth*this.orgHeight,{w:e,h:t}},load:function(){var _this=this;$.ajax({url:this.url,dataType:"text",success:function(data){var data=eval("("+data+")");_this.createDomList(data),_this.num=data.data.length,_this.setStyle(_this.num),_this.createDot(),_this.addEvent()}})},createDomList:function(e){var t=_.template(this.compailed,e);this.oDataWrap.html(t)},createDot:function(){var e,t;for(t=this._aLi.length,this.setDotWrap(),e=0;t>e;e++){var r=$('<a href="javascript:;"></a>');r.css({width:"0.5rem",height:"0.5rem",display:"inline-block",background:this._ORGDOTBG,margin:"0.3rem",borderRadius:"0.5rem"}),this.oDotWrap.append(r)}this._aDot=this.oDotWrap.children(),this._aDot.eq(0).css("background",this._ACTDOTBG)},setDotWrap:function(){this.oDotWrap.css({textAlign:"center",position:"absolute",bottom:0,width:this._wrapWidth})}},module.exports=Focus}),define("lib/touch/hammer",[],function(){(function(e,t,r,i){"use strict";function n(e,t,r){return setTimeout(u(e,r),t)}function o(e,t,r){return Array.isArray(e)?(a(e,r[t],r),!0):!1}function a(e,t,r){var n;if(e)if(e.forEach)e.forEach(t,r);else if(e.length!==i)for(n=0;e.length>n;)t.call(r,e[n],n,e),n++;else for(n in e)e.hasOwnProperty(n)&&t.call(r,e[n],n,e)}function s(e,t,r){for(var n=Object.keys(t),o=0;n.length>o;)(!r||r&&e[n[o]]===i)&&(e[n[o]]=t[n[o]]),o++;return e}function c(e,t){return s(e,t,!0)}function l(e,t,r){var i,n=t.prototype;i=e.prototype=Object.create(n),i.constructor=e,i._super=n,r&&s(i,r)}function u(e,t){return function(){return e.apply(t,arguments)}}function d(e,t){return typeof e==ut?e.apply(t?t[0]||i:i,t):e}function h(e,t){return e===i?t:e}function f(e,t,r){a(v(t),function(t){e.addEventListener(t,r,!1)})}function p(e,t,r){a(v(t),function(t){e.removeEventListener(t,r,!1)})}function m(e,t){for(;e;){if(e==t)return!0;e=e.parentNode}return!1}function g(e,t){return e.indexOf(t)>-1}function v(e){return e.trim().split(/\s+/g)}function b(e,t,r){if(e.indexOf&&!r)return e.indexOf(t);for(var i=0;e.length>i;){if(r&&e[i][r]==t||!r&&e[i]===t)return i;i++}return-1}function y(e){return Array.prototype.slice.call(e,0)}function x(e,t,r){for(var i=[],n=[],o=0;e.length>o;){var a=t?e[o][t]:e[o];0>b(n,a)&&i.push(e[o]),n[o]=a,o++}return r&&(i=t?i.sort(function(e,r){return e[t]>r[t]}):i.sort()),i}function w(e,t){for(var r,n,o=t[0].toUpperCase()+t.slice(1),a=0;ct.length>a;){if(r=ct[a],n=r?r+o:t,n in e)return n;a++}return i}function k(){return pt++}function S(e){var t=e.ownerDocument;return t.defaultView||t.parentWindow}function R(e,t){var r=this;this.manager=e,this.callback=t,this.element=e.element,this.target=e.options.inputTarget,this.domHandler=function(t){d(e.options.enable,[e])&&r.handler(t)},this.init()}function P(e){var t,r=e.options.inputClass;return t=r?r:vt?I:bt?H:gt?F:M,new t(e,C)}function C(e,t,r){var i=r.pointers.length,n=r.changedPointers.length,o=t&Rt&&0===i-n,a=t&(Ct|qt)&&0===i-n;r.isFirst=!!o,r.isFinal=!!a,o&&(e.session={}),r.eventType=t,q(e,r),e.emit("hammer.input",r),e.recognize(r),e.session.prevInput=r}function q(e,t){var r=e.session,i=t.pointers,n=i.length;r.firstInput||(r.firstInput=A(t)),n>1&&!r.firstMultiple?r.firstMultiple=A(t):1===n&&(r.firstMultiple=!1);var o=r.firstInput,a=r.firstMultiple,s=a?a.center:o.center,c=t.center=E(i);t.timeStamp=ft(),t.deltaTime=t.timeStamp-o.timeStamp,t.angle=N(s,c),t.distance=L(s,c),$(r,t),t.offsetDirection=D(t.deltaX,t.deltaY),t.scale=a?j(a.pointers,i):1,t.rotation=a?O(a.pointers,i):0,T(r,t);var l=e.element;m(t.srcEvent.target,l)&&(l=t.srcEvent.target),t.target=l}function $(e,t){var r=t.center,i=e.offsetDelta||{},n=e.prevDelta||{},o=e.prevInput||{};(t.eventType===Rt||o.eventType===Ct)&&(n=e.prevDelta={x:o.deltaX||0,y:o.deltaY||0},i=e.offsetDelta={x:r.x,y:r.y}),t.deltaX=n.x+(r.x-i.x),t.deltaY=n.y+(r.y-i.y)}function T(e,t){var r,n,o,a,s=e.lastInterval||t,c=t.timeStamp-s.timeStamp;if(t.eventType!=qt&&(c>St||s.velocity===i)){var l=s.deltaX-t.deltaX,u=s.deltaY-t.deltaY,d=_(c,l,u);n=d.x,o=d.y,r=ht(d.x)>ht(d.y)?d.x:d.y,a=D(l,u),e.lastInterval=t}else r=s.velocity,n=s.velocityX,o=s.velocityY,a=s.direction;t.velocity=r,t.velocityX=n,t.velocityY=o,t.direction=a}function A(e){for(var t=[],r=0;e.pointers.length>r;)t[r]={clientX:dt(e.pointers[r].clientX),clientY:dt(e.pointers[r].clientY)},r++;return{timeStamp:ft(),pointers:t,center:E(t),deltaX:e.deltaX,deltaY:e.deltaY}}function E(e){var t=e.length;if(1===t)return{x:dt(e[0].clientX),y:dt(e[0].clientY)};for(var r=0,i=0,n=0;t>n;)r+=e[n].clientX,i+=e[n].clientY,n++;return{x:dt(r/t),y:dt(i/t)}}function _(e,t,r){return{x:t/e||0,y:r/e||0}}function D(e,t){return e===t?$t:ht(e)>=ht(t)?e>0?Tt:At:t>0?Et:_t}function L(e,t,r){r||(r=Ot);var i=t[r[0]]-e[r[0]],n=t[r[1]]-e[r[1]];return Math.sqrt(i*i+n*n)}function N(e,t,r){r||(r=Ot);var i=t[r[0]]-e[r[0]],n=t[r[1]]-e[r[1]];return 180*Math.atan2(n,i)/Math.PI}function O(e,t){return N(t[1],t[0],jt)-N(e[1],e[0],jt)}function j(e,t){return L(t[0],t[1],jt)/L(e[0],e[1],jt)}function M(){this.evEl=It,this.evWin=Bt,this.allow=!0,this.pressed=!1,R.apply(this,arguments)}function I(){this.evEl=Wt,this.evWin=Ft,R.apply(this,arguments),this.store=this.manager.session.pointerEvents=[]}function B(){this.evTarget=Gt,this.evWin=Jt,this.started=!1,R.apply(this,arguments)}function U(e,t){var r=y(e.touches),i=y(e.changedTouches);return t&(Ct|qt)&&(r=x(r.concat(i),"identifier",!0)),[r,i]}function H(){this.evTarget=Yt,this.targetIds={},R.apply(this,arguments)}function W(e,t){var r=y(e.touches),n=this.targetIds;if(t&(Rt|Pt)&&1===r.length)return n[r[0].identifier]=!0,[r,r];var o,a,s=y(e.changedTouches),c=[],l=this.target;if(a=r.filter(function(e){return m(e.target,l)}),t===Rt)for(o=0;a.length>o;)n[a[o].identifier]=!0,o++;for(o=0;s.length>o;)n[s[o].identifier]&&c.push(s[o]),t&(Ct|qt)&&delete n[s[o].identifier],o++;return c.length?[x(a.concat(c),"identifier",!0),c]:i}function F(){R.apply(this,arguments);var e=u(this.handler,this);this.touch=new H(this.manager,e),this.mouse=new M(this.manager,e)}function z(e,t){this.manager=e,this.set(t)}function G(e){if(g(e,tr))return tr;var t=g(e,rr),r=g(e,ir);return t&&r?rr+" "+ir:t||r?t?rr:ir:g(e,er)?er:Zt}function J(e){this.id=k(),this.manager=null,this.options=c(e||{},this.defaults),this.options.enable=h(this.options.enable,!0),this.state=nr,this.simultaneous={},this.requireFail=[]}function X(e){return e&lr?"cancel":e&sr?"end":e&ar?"move":e&or?"start":""}function Y(e){return e==_t?"down":e==Et?"up":e==Tt?"left":e==At?"right":""}function V(e,t){var r=t.manager;return r?r.get(e):e}function K(){J.apply(this,arguments)}function Q(){K.apply(this,arguments),this.pX=null,this.pY=null}function Z(){K.apply(this,arguments)}function et(){J.apply(this,arguments),this._timer=null,this._input=null}function tt(){K.apply(this,arguments)}function rt(){K.apply(this,arguments)}function it(){J.apply(this,arguments),this.pTime=!1,this.pCenter=!1,this._timer=null,this._input=null,this.count=0}function nt(e,t){return t=t||{},t.recognizers=h(t.recognizers,nt.defaults.preset),new ot(e,t)}function ot(e,t){t=t||{},this.options=c(t,nt.defaults),this.options.inputTarget=this.options.inputTarget||e,this.handlers={},this.session={},this.recognizers=[],this.element=e,this.input=P(this),this.touchAction=new z(this,this.options.touchAction),at(this,!0),a(t.recognizers,function(e){var t=this.add(new e[0](e[1]));e[2]&&t.recognizeWith(e[2]),e[3]&&t.requireFailure(e[3])},this)}function at(e,t){var r=e.element;a(e.options.cssProps,function(e,i){r.style[w(r.style,i)]=t?e:""})}function st(e,r){var i=t.createEvent("Event");i.initEvent(e,!0,!0),i.gesture=r,r.target.dispatchEvent(i)}var ct=["","webkit","moz","MS","ms","o"],lt=t.createElement("div"),ut="function",dt=Math.round,ht=Math.abs,ft=Date.now,pt=1,mt=/mobile|tablet|ip(ad|hone|od)|android/i,gt="ontouchstart"in e,vt=w(e,"PointerEvent")!==i,bt=gt&&mt.test(navigator.userAgent),yt="touch",xt="pen",wt="mouse",kt="kinect",St=25,Rt=1,Pt=2,Ct=4,qt=8,$t=1,Tt=2,At=4,Et=8,_t=16,Dt=Tt|At,Lt=Et|_t,Nt=Dt|Lt,Ot=["x","y"],jt=["clientX","clientY"];R.prototype={handler:function(){},init:function(){this.evEl&&f(this.element,this.evEl,this.domHandler),this.evTarget&&f(this.target,this.evTarget,this.domHandler),this.evWin&&f(S(this.element),this.evWin,this.domHandler)},destroy:function(){this.evEl&&p(this.element,this.evEl,this.domHandler),this.evTarget&&p(this.target,this.evTarget,this.domHandler),this.evWin&&p(S(this.element),this.evWin,this.domHandler)}};var Mt={mousedown:Rt,mousemove:Pt,mouseup:Ct},It="mousedown",Bt="mousemove mouseup";l(M,R,{handler:function(e){var t=Mt[e.type];t&Rt&&0===e.button&&(this.pressed=!0),t&Pt&&1!==e.which&&(t=Ct),this.pressed&&this.allow&&(t&Ct&&(this.pressed=!1),this.callback(this.manager,t,{pointers:[e],changedPointers:[e],pointerType:wt,srcEvent:e}))}});var Ut={pointerdown:Rt,pointermove:Pt,pointerup:Ct,pointercancel:qt,pointerout:qt},Ht={2:yt,3:xt,4:wt,5:kt},Wt="pointerdown",Ft="pointermove pointerup pointercancel";e.MSPointerEvent&&(Wt="MSPointerDown",Ft="MSPointerMove MSPointerUp MSPointerCancel"),l(I,R,{handler:function(e){var t=this.store,r=!1,i=e.type.toLowerCase().replace("ms",""),n=Ut[i],o=Ht[e.pointerType]||e.pointerType,a=o==yt,s=b(t,e.pointerId,"pointerId");n&Rt&&(0===e.button||a)?0>s&&(t.push(e),s=t.length-1):n&(Ct|qt)&&(r=!0),0>s||(t[s]=e,this.callback(this.manager,n,{pointers:t,changedPointers:[e],pointerType:o,srcEvent:e}),r&&t.splice(s,1))}});var zt={touchstart:Rt,touchmove:Pt,touchend:Ct,touchcancel:qt},Gt="touchstart",Jt="touchstart touchmove touchend touchcancel";l(B,R,{handler:function(e){var t=zt[e.type];if(t===Rt&&(this.started=!0),this.started){var r=U.call(this,e,t);t&(Ct|qt)&&0===r[0].length-r[1].length&&(this.started=!1),this.callback(this.manager,t,{pointers:r[0],changedPointers:r[1],pointerType:yt,srcEvent:e})}}});var Xt={touchstart:Rt,touchmove:Pt,touchend:Ct,touchcancel:qt},Yt="touchstart touchmove touchend touchcancel";l(H,R,{handler:function(e){var t=Xt[e.type],r=W.call(this,e,t);r&&this.callback(this.manager,t,{pointers:r[0],changedPointers:r[1],pointerType:yt,srcEvent:e})}}),l(F,R,{handler:function(e,t,r){var i=r.pointerType==yt,n=r.pointerType==wt;if(i)this.mouse.allow=!1;else if(n&&!this.mouse.allow)return;t&(Ct|qt)&&(this.mouse.allow=!0),this.callback(e,t,r)},destroy:function(){this.touch.destroy(),this.mouse.destroy()}});var Vt=w(lt.style,"touchAction"),Kt=Vt!==i,Qt="compute",Zt="auto",er="manipulation",tr="none",rr="pan-x",ir="pan-y";z.prototype={set:function(e){e==Qt&&(e=this.compute()),Kt&&(this.manager.element.style[Vt]=e),this.actions=e.toLowerCase().trim()},update:function(){this.set(this.manager.options.touchAction)},compute:function(){var e=[];return a(this.manager.recognizers,function(t){d(t.options.enable,[t])&&(e=e.concat(t.getTouchAction()))}),G(e.join(" "))},preventDefaults:function(e){if(!Kt){var t=e.srcEvent,r=e.offsetDirection;if(this.manager.session.prevented)return t.preventDefault(),i;var n=this.actions,o=g(n,tr),a=g(n,ir),s=g(n,rr);return o||a&&r&Dt||s&&r&Lt?this.preventSrc(t):i}},preventSrc:function(e){this.manager.session.prevented=!0,e.preventDefault()}};var nr=1,or=2,ar=4,sr=8,cr=sr,lr=16,ur=32;J.prototype={defaults:{},set:function(e){return s(this.options,e),this.manager&&this.manager.touchAction.update(),this},recognizeWith:function(e){if(o(e,"recognizeWith",this))return this;var t=this.simultaneous;return e=V(e,this),t[e.id]||(t[e.id]=e,e.recognizeWith(this)),this},dropRecognizeWith:function(e){return o(e,"dropRecognizeWith",this)?this:(e=V(e,this),delete this.simultaneous[e.id],this)},requireFailure:function(e){if(o(e,"requireFailure",this))return this;var t=this.requireFail;return e=V(e,this),-1===b(t,e)&&(t.push(e),e.requireFailure(this)),this},dropRequireFailure:function(e){if(o(e,"dropRequireFailure",this))return this;e=V(e,this);var t=b(this.requireFail,e);return t>-1&&this.requireFail.splice(t,1),this},hasRequireFailures:function(){return this.requireFail.length>0},canRecognizeWith:function(e){return!!this.simultaneous[e.id]},emit:function(e){function t(t){r.manager.emit(r.options.event+(t?X(i):""),e)}var r=this,i=this.state;sr>i&&t(!0),t(),i>=sr&&t(!0)},tryEmit:function(e){return this.canEmit()?this.emit(e):(this.state=ur,i)},canEmit:function(){for(var e=0;this.requireFail.length>e;){if(!(this.requireFail[e].state&(ur|nr)))return!1;e++}return!0},recognize:function(e){var t=s({},e);return d(this.options.enable,[this,t])?(this.state&(cr|lr|ur)&&(this.state=nr),this.state=this.process(t),this.state&(or|ar|sr|lr)&&this.tryEmit(t),i):(this.reset(),this.state=ur,i)},process:function(){},getTouchAction:function(){},reset:function(){}},l(K,J,{defaults:{pointers:1},attrTest:function(e){var t=this.options.pointers;return 0===t||e.pointers.length===t},process:function(e){var t=this.state,r=e.eventType,i=t&(or|ar),n=this.attrTest(e);return i&&(r&qt||!n)?t|lr:i||n?r&Ct?t|sr:t&or?t|ar:or:ur}}),l(Q,K,{defaults:{event:"pan",threshold:10,pointers:1,direction:Nt},getTouchAction:function(){var e=this.options.direction,t=[];return e&Dt&&t.push(ir),e&Lt&&t.push(rr),t},directionTest:function(e){var t=this.options,r=!0,i=e.distance,n=e.direction,o=e.deltaX,a=e.deltaY;return n&t.direction||(t.direction&Dt?(n=0===o?$t:0>o?Tt:At,r=o!=this.pX,i=Math.abs(e.deltaX)):(n=0===a?$t:0>a?Et:_t,r=a!=this.pY,i=Math.abs(e.deltaY))),e.direction=n,r&&i>t.threshold&&n&t.direction},attrTest:function(e){return K.prototype.attrTest.call(this,e)&&(this.state&or||!(this.state&or)&&this.directionTest(e))},emit:function(e){this.pX=e.deltaX,this.pY=e.deltaY;var t=Y(e.direction);t&&this.manager.emit(this.options.event+t,e),this._super.emit.call(this,e)}}),l(Z,K,{defaults:{event:"pinch",threshold:0,pointers:2},getTouchAction:function(){return[tr]},attrTest:function(e){return this._super.attrTest.call(this,e)&&(Math.abs(e.scale-1)>this.options.threshold||this.state&or)},emit:function(e){if(this._super.emit.call(this,e),1!==e.scale){var t=1>e.scale?"in":"out";this.manager.emit(this.options.event+t,e)}}}),l(et,J,{defaults:{event:"press",pointers:1,time:500,threshold:5},getTouchAction:function(){return[Zt]},process:function(e){var t=this.options,r=e.pointers.length===t.pointers,i=e.distance<t.threshold,o=e.deltaTime>t.time;if(this._input=e,!i||!r||e.eventType&(Ct|qt)&&!o)this.reset();else if(e.eventType&Rt)this.reset(),this._timer=n(function(){this.state=cr,this.tryEmit()},t.time,this);else if(e.eventType&Ct)return cr;return ur},reset:function(){clearTimeout(this._timer)},emit:function(e){this.state===cr&&(e&&e.eventType&Ct?this.manager.emit(this.options.event+"up",e):(this._input.timeStamp=ft(),this.manager.emit(this.options.event,this._input)))}}),l(tt,K,{defaults:{event:"rotate",threshold:0,pointers:2},getTouchAction:function(){return[tr]},attrTest:function(e){return this._super.attrTest.call(this,e)&&(Math.abs(e.rotation)>this.options.threshold||this.state&or)}}),l(rt,K,{defaults:{event:"swipe",threshold:10,velocity:.65,direction:Dt|Lt,pointers:1},getTouchAction:function(){return Q.prototype.getTouchAction.call(this)},attrTest:function(e){var t,r=this.options.direction;return r&(Dt|Lt)?t=e.velocity:r&Dt?t=e.velocityX:r&Lt&&(t=e.velocityY),this._super.attrTest.call(this,e)&&r&e.direction&&e.distance>this.options.threshold&&ht(t)>this.options.velocity&&e.eventType&Ct},emit:function(e){var t=Y(e.direction);t&&this.manager.emit(this.options.event+t,e),this.manager.emit(this.options.event,e)}}),l(it,J,{defaults:{event:"tap",pointers:1,taps:1,interval:300,time:250,threshold:2,posThreshold:10},getTouchAction:function(){return[er]},process:function(e){var t=this.options,r=e.pointers.length===t.pointers,i=e.distance<t.threshold,o=e.deltaTime<t.time;if(this.reset(),e.eventType&Rt&&0===this.count)return this.failTimeout();if(i&&o&&r){if(e.eventType!=Ct)return this.failTimeout();var a=this.pTime?e.timeStamp-this.pTime<t.interval:!0,s=!this.pCenter||L(this.pCenter,e.center)<t.posThreshold;this.pTime=e.timeStamp,this.pCenter=e.center,s&&a?this.count+=1:this.count=1,this._input=e;var c=this.count%t.taps;if(0===c)return this.hasRequireFailures()?(this._timer=n(function(){this.state=cr,this.tryEmit()},t.interval,this),or):cr}return ur},failTimeout:function(){return this._timer=n(function(){this.state=ur},this.options.interval,this),ur},reset:function(){clearTimeout(this._timer)},emit:function(){this.state==cr&&(this._input.tapCount=this.count,this.manager.emit(this.options.event,this._input))}}),nt.VERSION="2.0.4",nt.defaults={domEvents:!1,touchAction:Qt,enable:!0,inputTarget:null,inputClass:null,preset:[[tt,{enable:!1}],[Z,{enable:!1},["rotate"]],[rt,{direction:Dt}],[Q,{direction:Dt},["swipe"]],[it],[it,{event:"doubletap",taps:2},["tap"]],[et]],cssProps:{userSelect:"none",touchSelect:"none",touchCallout:"none",contentZooming:"none",userDrag:"none",tapHighlightColor:"rgba(0,0,0,0)"}};var dr=1,hr=2;ot.prototype={set:function(e){return s(this.options,e),e.touchAction&&this.touchAction.update(),e.inputTarget&&(this.input.destroy(),this.input.target=e.inputTarget,this.input.init()),this},stop:function(e){this.session.stopped=e?hr:dr},recognize:function(e){var t=this.session;if(!t.stopped){this.touchAction.preventDefaults(e);var r,i=this.recognizers,n=t.curRecognizer;(!n||n&&n.state&cr)&&(n=t.curRecognizer=null);for(var o=0;i.length>o;)r=i[o],t.stopped===hr||n&&r!=n&&!r.canRecognizeWith(n)?r.reset():r.recognize(e),!n&&r.state&(or|ar|sr)&&(n=t.curRecognizer=r),o++}},get:function(e){if(e instanceof J)return e;for(var t=this.recognizers,r=0;t.length>r;r++)if(t[r].options.event==e)return t[r];return null},add:function(e){if(o(e,"add",this))return this;var t=this.get(e.options.event);return t&&this.remove(t),this.recognizers.push(e),e.manager=this,this.touchAction.update(),e},remove:function(e){if(o(e,"remove",this))return this;var t=this.recognizers;return e=this.get(e),t.splice(b(t,e),1),this.touchAction.update(),this},on:function(e,t){var r=this.handlers;return a(v(e),function(e){r[e]=r[e]||[],r[e].push(t)}),this},off:function(e,t){var r=this.handlers;return a(v(e),function(e){t?r[e].splice(b(r[e],t),1):delete r[e]}),this},emit:function(e,t){this.options.domEvents&&st(e,t);var r=this.handlers[e]&&this.handlers[e].slice();if(r&&r.length){t.type=e,t.preventDefault=function(){t.srcEvent.preventDefault()};for(var i=0;r.length>i;)r[i](t),i++}},destroy:function(){this.element&&at(this,!1),this.handlers={},this.session={},this.input.destroy(),this.element=null}},s(nt,{INPUT_START:Rt,INPUT_MOVE:Pt,INPUT_END:Ct,INPUT_CANCEL:qt,STATE_POSSIBLE:nr,STATE_BEGAN:or,STATE_CHANGED:ar,STATE_ENDED:sr,STATE_RECOGNIZED:cr,STATE_CANCELLED:lr,STATE_FAILED:ur,DIRECTION_NONE:$t,DIRECTION_LEFT:Tt,DIRECTION_RIGHT:At,DIRECTION_UP:Et,DIRECTION_DOWN:_t,DIRECTION_HORIZONTAL:Dt,DIRECTION_VERTICAL:Lt,DIRECTION_ALL:Nt,Manager:ot,Input:R,TouchAction:z,TouchInput:H,MouseInput:M,PointerEventInput:I,TouchMouseInput:F,SingleTouchInput:B,Recognizer:J,AttrRecognizer:K,Tap:it,Pan:Q,Swipe:rt,Pinch:Z,Rotate:tt,Press:et,on:f,off:p,each:a,merge:c,extend:s,inherit:l,bindFn:u,prefixed:w}),e[r]=nt})(window,document,"Hammer"),function(){function e(e,t){var r=$(e);r.data("hammer")||r.data("hammer",new Hammer(r[0],t))}$.fn.hammer=function(t){return this.each(function(){e(this,t)})},Hammer.Manager.prototype.emit=function(e){return function(t,r){e.call(this,t,r),$(this.element).trigger({type:t,gesture:r})}}(Hammer.Manager.prototype.emit)}()}),define("lib/underscore/underscore",[],function(e,t,r){(function(){var e=this,i=e._,n={},o=Array.prototype,a=Object.prototype,s=Function.prototype,c=o.push,l=o.slice,u=o.concat,d=a.toString,h=a.hasOwnProperty,f=o.forEach,p=o.map,m=o.reduce,g=o.reduceRight,v=o.filter,b=o.every,y=o.some,x=o.indexOf,w=o.lastIndexOf,k=Array.isArray,S=Object.keys,R=s.bind,P=function(e){return e instanceof P?e:this instanceof P?(this._wrapped=e,void 0):new P(e)};t!==void 0?(r!==void 0&&r.exports&&(t=r.exports=P),t._=P):e._=P,P.VERSION="1.6.0";var C=P.each=P.forEach=function(e,t,r){if(null==e)return e;if(f&&e.forEach===f)e.forEach(t,r);else if(e.length===+e.length){for(var i=0,o=e.length;o>i;i++)if(t.call(r,e[i],i,e)===n)return}else for(var a=P.keys(e),i=0,o=a.length;o>i;i++)if(t.call(r,e[a[i]],a[i],e)===n)return;return e};P.map=P.collect=function(e,t,r){var i=[];return null==e?i:p&&e.map===p?e.map(t,r):(C(e,function(e,n,o){i.push(t.call(r,e,n,o))}),i)};var q="Reduce of empty array with no initial value";P.reduce=P.foldl=P.inject=function(e,t,r,i){var n=arguments.length>2;if(null==e&&(e=[]),m&&e.reduce===m)return i&&(t=P.bind(t,i)),n?e.reduce(t,r):e.reduce(t);if(C(e,function(e,o,a){n?r=t.call(i,r,e,o,a):(r=e,n=!0)}),!n)throw new TypeError(q);return r},P.reduceRight=P.foldr=function(e,t,r,i){var n=arguments.length>2;if(null==e&&(e=[]),g&&e.reduceRight===g)return i&&(t=P.bind(t,i)),n?e.reduceRight(t,r):e.reduceRight(t);var o=e.length;if(o!==+o){var a=P.keys(e);o=a.length}if(C(e,function(s,c,l){c=a?a[--o]:--o,n?r=t.call(i,r,e[c],c,l):(r=e[c],n=!0)}),!n)throw new TypeError(q);return r},P.find=P.detect=function(e,t,r){var i;return $(e,function(e,n,o){return t.call(r,e,n,o)?(i=e,!0):void 0}),i},P.filter=P.select=function(e,t,r){var i=[];return null==e?i:v&&e.filter===v?e.filter(t,r):(C(e,function(e,n,o){t.call(r,e,n,o)&&i.push(e)}),i)},P.reject=function(e,t,r){return P.filter(e,function(e,i,n){return!t.call(r,e,i,n)},r)},P.every=P.all=function(e,t,r){t||(t=P.identity);var i=!0;return null==e?i:b&&e.every===b?e.every(t,r):(C(e,function(e,o,a){return(i=i&&t.call(r,e,o,a))?void 0:n}),!!i)};var $=P.some=P.any=function(e,t,r){t||(t=P.identity);var i=!1;return null==e?i:y&&e.some===y?e.some(t,r):(C(e,function(e,o,a){return i||(i=t.call(r,e,o,a))?n:void 0}),!!i)};P.contains=P.include=function(e,t){return null==e?!1:x&&e.indexOf===x?-1!=e.indexOf(t):$(e,function(e){return e===t})},P.invoke=function(e,t){var r=l.call(arguments,2),i=P.isFunction(t);return P.map(e,function(e){return(i?t:e[t]).apply(e,r)})},P.pluck=function(e,t){return P.map(e,P.property(t))},P.where=function(e,t){return P.filter(e,P.matches(t))},P.findWhere=function(e,t){return P.find(e,P.matches(t))},P.max=function(e,t,r){if(!t&&P.isArray(e)&&e[0]===+e[0]&&65535>e.length)return Math.max.apply(Math,e);var i=-1/0,n=-1/0;return C(e,function(e,o,a){var s=t?t.call(r,e,o,a):e;s>n&&(i=e,n=s)}),i},P.min=function(e,t,r){if(!t&&P.isArray(e)&&e[0]===+e[0]&&65535>e.length)return Math.min.apply(Math,e);var i=1/0,n=1/0;return C(e,function(e,o,a){var s=t?t.call(r,e,o,a):e;n>s&&(i=e,n=s)}),i},P.shuffle=function(e){var t,r=0,i=[];return C(e,function(e){t=P.random(r++),i[r-1]=i[t],i[t]=e}),i},P.sample=function(e,t,r){return null==t||r?(e.length!==+e.length&&(e=P.values(e)),e[P.random(e.length-1)]):P.shuffle(e).slice(0,Math.max(0,t))};var T=function(e){return null==e?P.identity:P.isFunction(e)?e:P.property(e)};P.sortBy=function(e,t,r){return t=T(t),P.pluck(P.map(e,function(e,i,n){return{value:e,index:i,criteria:t.call(r,e,i,n)}}).sort(function(e,t){var r=e.criteria,i=t.criteria;if(r!==i){if(r>i||void 0===r)return 1;if(i>r||void 0===i)return-1}return e.index-t.index}),"value")};var A=function(e){return function(t,r,i){var n={};return r=T(r),C(t,function(o,a){var s=r.call(i,o,a,t);e(n,s,o)}),n}};P.groupBy=A(function(e,t,r){P.has(e,t)?e[t].push(r):e[t]=[r]}),P.indexBy=A(function(e,t,r){e[t]=r}),P.countBy=A(function(e,t){P.has(e,t)?e[t]++:e[t]=1}),P.sortedIndex=function(e,t,r,i){r=T(r);for(var n=r.call(i,t),o=0,a=e.length;a>o;){var s=o+a>>>1;n>r.call(i,e[s])?o=s+1:a=s}return o},P.toArray=function(e){return e?P.isArray(e)?l.call(e):e.length===+e.length?P.map(e,P.identity):P.values(e):[]},P.size=function(e){return null==e?0:e.length===+e.length?e.length:P.keys(e).length},P.first=P.head=P.take=function(e,t,r){return null==e?void 0:null==t||r?e[0]:0>t?[]:l.call(e,0,t)},P.initial=function(e,t,r){return l.call(e,0,e.length-(null==t||r?1:t))},P.last=function(e,t,r){return null==e?void 0:null==t||r?e[e.length-1]:l.call(e,Math.max(e.length-t,0))},P.rest=P.tail=P.drop=function(e,t,r){return l.call(e,null==t||r?1:t)},P.compact=function(e){return P.filter(e,P.identity)};var E=function(e,t,r){return t&&P.every(e,P.isArray)?u.apply(r,e):(C(e,function(e){P.isArray(e)||P.isArguments(e)?t?c.apply(r,e):E(e,t,r):r.push(e)}),r)};P.flatten=function(e,t){return E(e,t,[])},P.without=function(e){return P.difference(e,l.call(arguments,1))},P.partition=function(e,t,r){t=T(t);var i=[],n=[];return C(e,function(e){(t.call(r,e)?i:n).push(e)}),[i,n]},P.uniq=P.unique=function(e,t,r,i){P.isFunction(t)&&(i=r,r=t,t=!1);var n=r?P.map(e,r,i):e,o=[],a=[];return C(n,function(r,i){(t?i&&a[a.length-1]===r:P.contains(a,r))||(a.push(r),o.push(e[i]))}),o},P.union=function(){return P.uniq(P.flatten(arguments,!0))},P.intersection=function(e){var t=l.call(arguments,1);return P.filter(P.uniq(e),function(e){return P.every(t,function(t){return P.contains(t,e)})})},P.difference=function(e){var t=u.apply(o,l.call(arguments,1));return P.filter(e,function(e){return!P.contains(t,e)})},P.zip=function(){for(var e=P.max(P.pluck(arguments,"length").concat(0)),t=Array(e),r=0;e>r;r++)t[r]=P.pluck(arguments,""+r);return t},P.object=function(e,t){if(null==e)return{};for(var r={},i=0,n=e.length;n>i;i++)t?r[e[i]]=t[i]:r[e[i][0]]=e[i][1];return r},P.indexOf=function(e,t,r){if(null==e)return-1;var i=0,n=e.length;if(r){if("number"!=typeof r)return i=P.sortedIndex(e,t),e[i]===t?i:-1;i=0>r?Math.max(0,n+r):r}if(x&&e.indexOf===x)return e.indexOf(t,r);for(;n>i;i++)if(e[i]===t)return i;return-1},P.lastIndexOf=function(e,t,r){if(null==e)return-1;var i=null!=r;if(w&&e.lastIndexOf===w)return i?e.lastIndexOf(t,r):e.lastIndexOf(t);for(var n=i?r:e.length;n--;)if(e[n]===t)return n;return-1},P.range=function(e,t,r){1>=arguments.length&&(t=e||0,e=0),r=arguments[2]||1;for(var i=Math.max(Math.ceil((t-e)/r),0),n=0,o=Array(i);i>n;)o[n++]=e,e+=r;return o};var _=function(){};P.bind=function(e,t){var r,i;if(R&&e.bind===R)return R.apply(e,l.call(arguments,1));if(!P.isFunction(e))throw new TypeError;return r=l.call(arguments,2),i=function(){if(!(this instanceof i))return e.apply(t,r.concat(l.call(arguments)));_.prototype=e.prototype;var n=new _;_.prototype=null;var o=e.apply(n,r.concat(l.call(arguments)));return Object(o)===o?o:n}},P.partial=function(e){var t=l.call(arguments,1);return function(){for(var r=0,i=t.slice(),n=0,o=i.length;o>n;n++)i[n]===P&&(i[n]=arguments[r++]);for(;arguments.length>r;)i.push(arguments[r++]);return e.apply(this,i)}},P.bindAll=function(e){var t=l.call(arguments,1);if(0===t.length)throw Error("bindAll must be passed function names");return C(t,function(t){e[t]=P.bind(e[t],e)}),e},P.memoize=function(e,t){var r={};return t||(t=P.identity),function(){var i=t.apply(this,arguments);return P.has(r,i)?r[i]:r[i]=e.apply(this,arguments)}},P.delay=function(e,t){var r=l.call(arguments,2);return setTimeout(function(){return e.apply(null,r)},t)},P.defer=function(e){return P.delay.apply(P,[e,1].concat(l.call(arguments,1)))},P.throttle=function(e,t,r){var i,n,o,a=null,s=0;r||(r={});var c=function(){s=r.leading===!1?0:P.now(),a=null,o=e.apply(i,n),i=n=null};return function(){var l=P.now();s||r.leading!==!1||(s=l);var u=t-(l-s);return i=this,n=arguments,0>=u?(clearTimeout(a),a=null,s=l,o=e.apply(i,n),i=n=null):a||r.trailing===!1||(a=setTimeout(c,u)),o}},P.debounce=function(e,t,r){var i,n,o,a,s,c=function(){var l=P.now()-a;t>l?i=setTimeout(c,t-l):(i=null,r||(s=e.apply(o,n),o=n=null))};return function(){o=this,n=arguments,a=P.now();var l=r&&!i;return i||(i=setTimeout(c,t)),l&&(s=e.apply(o,n),o=n=null),s}},P.once=function(e){var t,r=!1;return function(){return r?t:(r=!0,t=e.apply(this,arguments),e=null,t)}},P.wrap=function(e,t){return P.partial(t,e)},P.compose=function(){var e=arguments;return function(){for(var t=arguments,r=e.length-1;r>=0;r--)t=[e[r].apply(this,t)];return t[0]}},P.after=function(e,t){return function(){return 1>--e?t.apply(this,arguments):void 0}},P.keys=function(e){if(!P.isObject(e))return[];if(S)return S(e);var t=[];for(var r in e)P.has(e,r)&&t.push(r);return t},P.values=function(e){for(var t=P.keys(e),r=t.length,i=Array(r),n=0;r>n;n++)i[n]=e[t[n]];return i},P.pairs=function(e){for(var t=P.keys(e),r=t.length,i=Array(r),n=0;r>n;n++)i[n]=[t[n],e[t[n]]];return i},P.invert=function(e){for(var t={},r=P.keys(e),i=0,n=r.length;n>i;i++)t[e[r[i]]]=r[i];return t},P.functions=P.methods=function(e){var t=[];for(var r in e)P.isFunction(e[r])&&t.push(r);return t.sort()},P.extend=function(e){return C(l.call(arguments,1),function(t){if(t)for(var r in t)e[r]=t[r]}),e},P.pick=function(e){var t={},r=u.apply(o,l.call(arguments,1));return C(r,function(r){r in e&&(t[r]=e[r])}),t},P.omit=function(e){var t={},r=u.apply(o,l.call(arguments,1));
for(var i in e)P.contains(r,i)||(t[i]=e[i]);return t},P.defaults=function(e){return C(l.call(arguments,1),function(t){if(t)for(var r in t)void 0===e[r]&&(e[r]=t[r])}),e},P.clone=function(e){return P.isObject(e)?P.isArray(e)?e.slice():P.extend({},e):e},P.tap=function(e,t){return t(e),e};var D=function(e,t,r,i){if(e===t)return 0!==e||1/e==1/t;if(null==e||null==t)return e===t;e instanceof P&&(e=e._wrapped),t instanceof P&&(t=t._wrapped);var n=d.call(e);if(n!=d.call(t))return!1;switch(n){case"[object String]":return e==t+"";case"[object Number]":return e!=+e?t!=+t:0==e?1/e==1/t:e==+t;case"[object Date]":case"[object Boolean]":return+e==+t;case"[object RegExp]":return e.source==t.source&&e.global==t.global&&e.multiline==t.multiline&&e.ignoreCase==t.ignoreCase}if("object"!=typeof e||"object"!=typeof t)return!1;for(var o=r.length;o--;)if(r[o]==e)return i[o]==t;var a=e.constructor,s=t.constructor;if(a!==s&&!(P.isFunction(a)&&a instanceof a&&P.isFunction(s)&&s instanceof s)&&"constructor"in e&&"constructor"in t)return!1;r.push(e),i.push(t);var c=0,l=!0;if("[object Array]"==n){if(c=e.length,l=c==t.length)for(;c--&&(l=D(e[c],t[c],r,i)););}else{for(var u in e)if(P.has(e,u)&&(c++,!(l=P.has(t,u)&&D(e[u],t[u],r,i))))break;if(l){for(u in t)if(P.has(t,u)&&!c--)break;l=!c}}return r.pop(),i.pop(),l};P.isEqual=function(e,t){return D(e,t,[],[])},P.isEmpty=function(e){if(null==e)return!0;if(P.isArray(e)||P.isString(e))return 0===e.length;for(var t in e)if(P.has(e,t))return!1;return!0},P.isElement=function(e){return!(!e||1!==e.nodeType)},P.isArray=k||function(e){return"[object Array]"==d.call(e)},P.isObject=function(e){return e===Object(e)},C(["Arguments","Function","String","Number","Date","RegExp"],function(e){P["is"+e]=function(t){return d.call(t)=="[object "+e+"]"}}),P.isArguments(arguments)||(P.isArguments=function(e){return!(!e||!P.has(e,"callee"))}),P.isFunction=function(e){return"function"==typeof e},P.isFinite=function(e){return isFinite(e)&&!isNaN(parseFloat(e))},P.isNaN=function(e){return P.isNumber(e)&&e!=+e},P.isBoolean=function(e){return e===!0||e===!1||"[object Boolean]"==d.call(e)},P.isNull=function(e){return null===e},P.isUndefined=function(e){return void 0===e},P.has=function(e,t){return h.call(e,t)},P.noConflict=function(){return e._=i,this},P.identity=function(e){return e},P.constant=function(e){return function(){return e}},P.property=function(e){return function(t){return t[e]}},P.matches=function(e){return function(t){if(t===e)return!0;for(var r in e)if(e[r]!==t[r])return!1;return!0}},P.times=function(e,t,r){for(var i=Array(Math.max(0,e)),n=0;e>n;n++)i[n]=t.call(r,n);return i},P.random=function(e,t){return null==t&&(t=e,e=0),e+Math.floor(Math.random()*(t-e+1))},P.now=Date.now||function(){return(new Date).getTime()};var L={escape:{"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#x27;"}};L.unescape=P.invert(L.escape);var N={escape:RegExp("["+P.keys(L.escape).join("")+"]","g"),unescape:RegExp("("+P.keys(L.unescape).join("|")+")","g")};P.each(["escape","unescape"],function(e){P[e]=function(t){return null==t?"":(""+t).replace(N[e],function(t){return L[e][t]})}}),P.result=function(e,t){if(null==e)return void 0;var r=e[t];return P.isFunction(r)?r.call(e):r},P.mixin=function(e){C(P.functions(e),function(t){var r=P[t]=e[t];P.prototype[t]=function(){var e=[this._wrapped];return c.apply(e,arguments),B.call(this,r.apply(P,e))}})};var O=0;P.uniqueId=function(e){var t=++O+"";return e?e+t:t},P.templateSettings={evaluate:/<%([\s\S]+?)%>/g,interpolate:/<%=([\s\S]+?)%>/g,escape:/<%-([\s\S]+?)%>/g};var j=/(.)^/,M={"'":"'","\\":"\\","\r":"r","\n":"n","	":"t","\u2028":"u2028","\u2029":"u2029"},I=/\\|'|\r|\n|\t|\u2028|\u2029/g;P.template=function(e,t,r){var i;r=P.defaults({},r,P.templateSettings);var n=RegExp([(r.escape||j).source,(r.interpolate||j).source,(r.evaluate||j).source].join("|")+"|$","g"),o=0,a="__p+='";e.replace(n,function(t,r,i,n,s){return a+=e.slice(o,s).replace(I,function(e){return"\\"+M[e]}),r&&(a+="'+\n((__t=("+r+"))==null?'':_.escape(__t))+\n'"),i&&(a+="'+\n((__t=("+i+"))==null?'':__t)+\n'"),n&&(a+="';\n"+n+"\n__p+='"),o=s+t.length,t}),a+="';\n",r.variable||(a="with(obj||{}){\n"+a+"}\n"),a="var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};\n"+a+"return __p;\n";try{i=Function(r.variable||"obj","_",a)}catch(s){throw s.source=a,s}if(t)return i(t,P);var c=function(e){return i.call(this,e,P)};return c.source="function("+(r.variable||"obj")+"){\n"+a+"}",c},P.chain=function(e){return P(e).chain()};var B=function(e){return this._chain?P(e).chain():e};P.mixin(P),C(["pop","push","reverse","shift","sort","splice","unshift"],function(e){var t=o[e];P.prototype[e]=function(){var r=this._wrapped;return t.apply(r,arguments),"shift"!=e&&"splice"!=e||0!==r.length||delete r[0],B.call(this,r)}}),C(["concat","join","slice"],function(e){var t=o[e];P.prototype[e]=function(){return B.call(this,t.apply(this._wrapped,arguments))}}),P.extend(P.prototype,{chain:function(){return this._chain=!0,this},value:function(){return this._wrapped}}),"function"==typeof define&&define.amd&&define("underscore",[],function(){return P}),window._=P}).call(this)});