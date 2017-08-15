define(function(){!function(t,e,i,n){"use strict";function r(t,e,i){return setTimeout(c(t,i),e)}function s(t,e,i){return Array.isArray(t)?(o(t,i[e],i),!0):!1}function o(t,e,i){var r;if(t)if(t.forEach)t.forEach(e,i);else if(t.length!==n)for(r=0;r<t.length;)e.call(i,t[r],r,t),r++;else for(r in t)t.hasOwnProperty(r)&&e.call(i,t[r],r,t)}function a(t,e,i){for(var r=Object.keys(e),s=0;s<r.length;)(!i||i&&t[r[s]]===n)&&(t[r[s]]=e[r[s]]),s++;return t}function h(t,e){return a(t,e,!0)}function u(t,e,i){var n,r=e.prototype;n=t.prototype=Object.create(r),n.constructor=t,n._super=r,i&&a(n,i)}function c(t,e){return function(){return t.apply(e,arguments)}}function l(t,e){return typeof t==ce?t.apply(e?e[0]||n:n,e):t}function p(t,e){return t===n?e:t}function f(t,e,i){o(g(e),function(e){t.addEventListener(e,i,!1)})}function d(t,e,i){o(g(e),function(e){t.removeEventListener(e,i,!1)})}function v(t,e){for(;t;){if(t==e)return!0;t=t.parentNode}return!1}function m(t,e){return t.indexOf(e)>-1}function g(t){return t.trim().split(/\s+/g)}function T(t,e,i){if(t.indexOf&&!i)return t.indexOf(e);for(var n=0;n<t.length;){if(i&&t[n][i]==e||!i&&t[n]===e)return n;n++}return-1}function y(t){return Array.prototype.slice.call(t,0)}function E(t,e,i){for(var n=[],r=[],s=0;s<t.length;){var o=e?t[s][e]:t[s];T(r,o)<0&&n.push(t[s]),r[s]=o,s++}return i&&(n=e?n.sort(function(t,i){return t[e]>i[e]}):n.sort()),n}function I(t,e){for(var i,r,s=e[0].toUpperCase()+e.slice(1),o=0;o<he.length;){if(i=he[o],r=i?i+s:e,r in t)return r;o++}return n}function A(){return de++}function _(t){var e=t.ownerDocument;return e.defaultView||e.parentWindow}function w(t,e){var i=this;this.manager=t,this.callback=e,this.element=t.element,this.target=t.options.inputTarget,this.domHandler=function(e){l(t.options.enable,[t])&&i.handler(e)},this.init()}function D(t){var e,i=t.options.inputClass;return new(e=i?i:ge?W:Te?L:me?U:x)(t,S)}function S(t,e,i){var n=i.pointers.length,r=i.changedPointers.length,s=e&we&&n-r===0,o=e&(Se|be)&&n-r===0;i.isFirst=!!s,i.isFinal=!!o,s&&(t.session={}),i.eventType=e,b(t,i),t.emit("hammer.input",i),t.recognize(i),t.session.prevInput=i}function b(t,e){var i=t.session,n=e.pointers,r=n.length;i.firstInput||(i.firstInput=z(e)),r>1&&!i.firstMultiple?i.firstMultiple=z(e):1===r&&(i.firstMultiple=!1);var s=i.firstInput,o=i.firstMultiple,a=o?o.center:s.center,h=e.center=M(n);e.timeStamp=fe(),e.deltaTime=e.timeStamp-s.timeStamp,e.angle=X(a,h),e.distance=O(a,h),C(i,e),e.offsetDirection=P(e.deltaX,e.deltaY),e.scale=o?F(o.pointers,n):1,e.rotation=o?Y(o.pointers,n):0,R(i,e);var u=t.element;v(e.srcEvent.target,u)&&(u=e.srcEvent.target),e.target=u}function C(t,e){var i=e.center,n=t.offsetDelta||{},r=t.prevDelta||{},s=t.prevInput||{};(e.eventType===we||s.eventType===Se)&&(r=t.prevDelta={x:s.deltaX||0,y:s.deltaY||0},n=t.offsetDelta={x:i.x,y:i.y}),e.deltaX=r.x+(i.x-n.x),e.deltaY=r.y+(i.y-n.y)}function R(t,e){var i,r,s,o,a=t.lastInterval||e,h=e.timeStamp-a.timeStamp;if(e.eventType!=be&&(h>_e||a.velocity===n)){var u=a.deltaX-e.deltaX,c=a.deltaY-e.deltaY,l=N(h,u,c);r=l.x,s=l.y,i=pe(l.x)>pe(l.y)?l.x:l.y,o=P(u,c),t.lastInterval=e}else i=a.velocity,r=a.velocityX,s=a.velocityY,o=a.direction;e.velocity=i,e.velocityX=r,e.velocityY=s,e.direction=o}function z(t){for(var e=[],i=0;i<t.pointers.length;)e[i]={clientX:le(t.pointers[i].clientX),clientY:le(t.pointers[i].clientY)},i++;return{timeStamp:fe(),pointers:e,center:M(e),deltaX:t.deltaX,deltaY:t.deltaY}}function M(t){var e=t.length;if(1===e)return{x:le(t[0].clientX),y:le(t[0].clientY)};for(var i=0,n=0,r=0;e>r;)i+=t[r].clientX,n+=t[r].clientY,r++;return{x:le(i/e),y:le(n/e)}}function N(t,e,i){return{x:e/t||0,y:i/t||0}}function P(t,e){return t===e?Ce:pe(t)>=pe(e)?t>0?Re:ze:e>0?Me:Ne}function O(t,e,i){i||(i=Ye);var n=e[i[0]]-t[i[0]],r=e[i[1]]-t[i[1]];return Math.sqrt(n*n+r*r)}function X(t,e,i){i||(i=Ye);var n=e[i[0]]-t[i[0]],r=e[i[1]]-t[i[1]];return 180*Math.atan2(r,n)/Math.PI}function Y(t,e){return X(e[1],e[0],Fe)-X(t[1],t[0],Fe)}function F(t,e){return O(e[0],e[1],Fe)/O(t[0],t[1],Fe)}function x(){this.evEl=We,this.evWin=qe,this.allow=!0,this.pressed=!1,w.apply(this,arguments)}function W(){this.evEl=ke,this.evWin=Ue,w.apply(this,arguments),this.store=this.manager.session.pointerEvents=[]}function q(){this.evTarget=Ve,this.evWin=je,this.started=!1,w.apply(this,arguments)}function H(t,e){var i=y(t.touches),n=y(t.changedTouches);return e&(Se|be)&&(i=E(i.concat(n),"identifier",!0)),[i,n]}function L(){this.evTarget=$e,this.targetIds={},w.apply(this,arguments)}function k(t,e){var i=y(t.touches),n=this.targetIds;if(e&(we|De)&&1===i.length)return n[i[0].identifier]=!0,[i,i];var r,s,o=y(t.changedTouches),a=[],h=this.target;if(s=i.filter(function(t){return v(t.target,h)}),e===we)for(r=0;r<s.length;)n[s[r].identifier]=!0,r++;for(r=0;r<o.length;)n[o[r].identifier]&&a.push(o[r]),e&(Se|be)&&delete n[o[r].identifier],r++;return a.length?[E(s.concat(a),"identifier",!0),a]:void 0}function U(){w.apply(this,arguments);var t=c(this.handler,this);this.touch=new L(this.manager,t),this.mouse=new x(this.manager,t)}function G(t,e){this.manager=t,this.set(e)}function V(t){if(m(t,ei))return ei;var e=m(t,ii),i=m(t,ni);return e&&i?ii+" "+ni:e||i?e?ii:ni:m(t,ti)?ti:Qe}function j(t){this.id=A(),this.manager=null,this.options=h(t||{},this.defaults),this.options.enable=p(this.options.enable,!0),this.state=ri,this.simultaneous={},this.requireFail=[]}function Z(t){return t&ui?"cancel":t&ai?"end":t&oi?"move":t&si?"start":""}function $(t){return t==Ne?"down":t==Me?"up":t==Re?"left":t==ze?"right":""}function B(t,e){var i=e.manager;return i?i.get(t):t}function J(){j.apply(this,arguments)}function K(){J.apply(this,arguments),this.pX=null,this.pY=null}function Q(){J.apply(this,arguments)}function te(){j.apply(this,arguments),this._timer=null,this._input=null}function ee(){J.apply(this,arguments)}function ie(){J.apply(this,arguments)}function ne(){j.apply(this,arguments),this.pTime=!1,this.pCenter=!1,this._timer=null,this._input=null,this.count=0}function re(t,e){return e=e||{},e.recognizers=p(e.recognizers,re.defaults.preset),new se(t,e)}function se(t,e){e=e||{},this.options=h(e,re.defaults),this.options.inputTarget=this.options.inputTarget||t,this.handlers={},this.session={},this.recognizers=[],this.element=t,this.input=D(this),this.touchAction=new G(this,this.options.touchAction),oe(this,!0),o(e.recognizers,function(t){var e=this.add(new t[0](t[1]));t[2]&&e.recognizeWith(t[2]),t[3]&&e.requireFailure(t[3])},this)}function oe(t,e){var i=t.element;o(t.options.cssProps,function(t,n){i.style[I(i.style,n)]=e?t:""})}function ae(t,i){var n=e.createEvent("Event");n.initEvent(t,!0,!0),n.gesture=i,i.target.dispatchEvent(n)}var he=["","webkit","moz","MS","ms","o"],ue=e.createElement("div"),ce="function",le=Math.round,pe=Math.abs,fe=Date.now,de=1,ve=/mobile|tablet|ip(ad|hone|od)|android/i,me="ontouchstart"in t,ge=I(t,"PointerEvent")!==n,Te=me&&ve.test(navigator.userAgent),ye="touch",Ee="pen",Ie="mouse",Ae="kinect",_e=25,we=1,De=2,Se=4,be=8,Ce=1,Re=2,ze=4,Me=8,Ne=16,Pe=Re|ze,Oe=Me|Ne,Xe=Pe|Oe,Ye=["x","y"],Fe=["clientX","clientY"];w.prototype={handler:function(){},init:function(){this.evEl&&f(this.element,this.evEl,this.domHandler),this.evTarget&&f(this.target,this.evTarget,this.domHandler),this.evWin&&f(_(this.element),this.evWin,this.domHandler)},destroy:function(){this.evEl&&d(this.element,this.evEl,this.domHandler),this.evTarget&&d(this.target,this.evTarget,this.domHandler),this.evWin&&d(_(this.element),this.evWin,this.domHandler)}};var xe={mousedown:we,mousemove:De,mouseup:Se},We="mousedown",qe="mousemove mouseup";u(x,w,{handler:function(t){var e=xe[t.type];e&we&&0===t.button&&(this.pressed=!0),e&De&&1!==t.which&&(e=Se),this.pressed&&this.allow&&(e&Se&&(this.pressed=!1),this.callback(this.manager,e,{pointers:[t],changedPointers:[t],pointerType:Ie,srcEvent:t}))}});var He={pointerdown:we,pointermove:De,pointerup:Se,pointercancel:be,pointerout:be},Le={2:ye,3:Ee,4:Ie,5:Ae},ke="pointerdown",Ue="pointermove pointerup pointercancel";t.MSPointerEvent&&(ke="MSPointerDown",Ue="MSPointerMove MSPointerUp MSPointerCancel"),u(W,w,{handler:function(t){var e=this.store,i=!1,n=t.type.toLowerCase().replace("ms",""),r=He[n],s=Le[t.pointerType]||t.pointerType,o=s==ye,a=T(e,t.pointerId,"pointerId");r&we&&(0===t.button||o)?0>a&&(e.push(t),a=e.length-1):r&(Se|be)&&(i=!0),0>a||(e[a]=t,this.callback(this.manager,r,{pointers:e,changedPointers:[t],pointerType:s,srcEvent:t}),i&&e.splice(a,1))}});var Ge={touchstart:we,touchmove:De,touchend:Se,touchcancel:be},Ve="touchstart",je="touchstart touchmove touchend touchcancel";u(q,w,{handler:function(t){var e=Ge[t.type];if(e===we&&(this.started=!0),this.started){var i=H.call(this,t,e);e&(Se|be)&&i[0].length-i[1].length===0&&(this.started=!1),this.callback(this.manager,e,{pointers:i[0],changedPointers:i[1],pointerType:ye,srcEvent:t})}}});var Ze={touchstart:we,touchmove:De,touchend:Se,touchcancel:be},$e="touchstart touchmove touchend touchcancel";u(L,w,{handler:function(t){var e=Ze[t.type],i=k.call(this,t,e);i&&this.callback(this.manager,e,{pointers:i[0],changedPointers:i[1],pointerType:ye,srcEvent:t})}}),u(U,w,{handler:function(t,e,i){var n=i.pointerType==ye,r=i.pointerType==Ie;if(n)this.mouse.allow=!1;else if(r&&!this.mouse.allow)return;e&(Se|be)&&(this.mouse.allow=!0),this.callback(t,e,i)},destroy:function(){this.touch.destroy(),this.mouse.destroy()}});var Be=I(ue.style,"touchAction"),Je=Be!==n,Ke="compute",Qe="auto",ti="manipulation",ei="none",ii="pan-x",ni="pan-y";G.prototype={set:function(t){t==Ke&&(t=this.compute()),Je&&(this.manager.element.style[Be]=t),this.actions=t.toLowerCase().trim()},update:function(){this.set(this.manager.options.touchAction)},compute:function(){var t=[];return o(this.manager.recognizers,function(e){l(e.options.enable,[e])&&(t=t.concat(e.getTouchAction()))}),V(t.join(" "))},preventDefaults:function(t){if(!Je){var e=t.srcEvent,i=t.offsetDirection;if(this.manager.session.prevented)return void e.preventDefault();var n=this.actions,r=m(n,ei),s=m(n,ni),o=m(n,ii);return r||s&&i&Pe||o&&i&Oe?this.preventSrc(e):void 0}},preventSrc:function(t){this.manager.session.prevented=!0,t.preventDefault()}};var ri=1,si=2,oi=4,ai=8,hi=ai,ui=16,ci=32;j.prototype={defaults:{},set:function(t){return a(this.options,t),this.manager&&this.manager.touchAction.update(),this},recognizeWith:function(t){if(s(t,"recognizeWith",this))return this;var e=this.simultaneous;return t=B(t,this),e[t.id]||(e[t.id]=t,t.recognizeWith(this)),this},dropRecognizeWith:function(t){return s(t,"dropRecognizeWith",this)?this:(t=B(t,this),delete this.simultaneous[t.id],this)},requireFailure:function(t){if(s(t,"requireFailure",this))return this;var e=this.requireFail;return t=B(t,this),-1===T(e,t)&&(e.push(t),t.requireFailure(this)),this},dropRequireFailure:function(t){if(s(t,"dropRequireFailure",this))return this;t=B(t,this);var e=T(this.requireFail,t);return e>-1&&this.requireFail.splice(e,1),this},hasRequireFailures:function(){return this.requireFail.length>0},canRecognizeWith:function(t){return!!this.simultaneous[t.id]},emit:function(t){function e(e){i.manager.emit(i.options.event+(e?Z(n):""),t)}var i=this,n=this.state;ai>n&&e(!0),e(),n>=ai&&e(!0)},tryEmit:function(t){return this.canEmit()?this.emit(t):void(this.state=ci)},canEmit:function(){for(var t=0;t<this.requireFail.length;){if(!(this.requireFail[t].state&(ci|ri)))return!1;t++}return!0},recognize:function(t){var e=a({},t);return l(this.options.enable,[this,e])?(this.state&(hi|ui|ci)&&(this.state=ri),this.state=this.process(e),void(this.state&(si|oi|ai|ui)&&this.tryEmit(e))):(this.reset(),void(this.state=ci))},process:function(){},getTouchAction:function(){},reset:function(){}},u(J,j,{defaults:{pointers:1},attrTest:function(t){var e=this.options.pointers;return 0===e||t.pointers.length===e},process:function(t){var e=this.state,i=t.eventType,n=e&(si|oi),r=this.attrTest(t);return n&&(i&be||!r)?e|ui:n||r?i&Se?e|ai:e&si?e|oi:si:ci}}),u(K,J,{defaults:{event:"pan",threshold:10,pointers:1,direction:Xe},getTouchAction:function(){var t=this.options.direction,e=[];return t&Pe&&e.push(ni),t&Oe&&e.push(ii),e},directionTest:function(t){var e=this.options,i=!0,n=t.distance,r=t.direction,s=t.deltaX,o=t.deltaY;return r&e.direction||(e.direction&Pe?(r=0===s?Ce:0>s?Re:ze,i=s!=this.pX,n=Math.abs(t.deltaX)):(r=0===o?Ce:0>o?Me:Ne,i=o!=this.pY,n=Math.abs(t.deltaY))),t.direction=r,i&&n>e.threshold&&r&e.direction},attrTest:function(t){return J.prototype.attrTest.call(this,t)&&(this.state&si||!(this.state&si)&&this.directionTest(t))},emit:function(t){this.pX=t.deltaX,this.pY=t.deltaY;var e=$(t.direction);e&&this.manager.emit(this.options.event+e,t),this._super.emit.call(this,t)}}),u(Q,J,{defaults:{event:"pinch",threshold:0,pointers:2},getTouchAction:function(){return[ei]},attrTest:function(t){return this._super.attrTest.call(this,t)&&(Math.abs(t.scale-1)>this.options.threshold||this.state&si)},emit:function(t){if(this._super.emit.call(this,t),1!==t.scale){var e=t.scale<1?"in":"out";this.manager.emit(this.options.event+e,t)}}}),u(te,j,{defaults:{event:"press",pointers:1,time:500,threshold:5},getTouchAction:function(){return[Qe]},process:function(t){var e=this.options,i=t.pointers.length===e.pointers,n=t.distance<e.threshold,s=t.deltaTime>e.time;if(this._input=t,!n||!i||t.eventType&(Se|be)&&!s)this.reset();else if(t.eventType&we)this.reset(),this._timer=r(function(){this.state=hi,this.tryEmit()},e.time,this);else if(t.eventType&Se)return hi;return ci},reset:function(){clearTimeout(this._timer)},emit:function(t){this.state===hi&&(t&&t.eventType&Se?this.manager.emit(this.options.event+"up",t):(this._input.timeStamp=fe(),this.manager.emit(this.options.event,this._input)))}}),u(ee,J,{defaults:{event:"rotate",threshold:0,pointers:2},getTouchAction:function(){return[ei]},attrTest:function(t){return this._super.attrTest.call(this,t)&&(Math.abs(t.rotation)>this.options.threshold||this.state&si)}}),u(ie,J,{defaults:{event:"swipe",threshold:10,velocity:.65,direction:Pe|Oe,pointers:1},getTouchAction:function(){return K.prototype.getTouchAction.call(this)},attrTest:function(t){var e,i=this.options.direction;return i&(Pe|Oe)?e=t.velocity:i&Pe?e=t.velocityX:i&Oe&&(e=t.velocityY),this._super.attrTest.call(this,t)&&i&t.direction&&t.distance>this.options.threshold&&pe(e)>this.options.velocity&&t.eventType&Se},emit:function(t){var e=$(t.direction);e&&this.manager.emit(this.options.event+e,t),this.manager.emit(this.options.event,t)}}),u(ne,j,{defaults:{event:"tap",pointers:1,taps:1,interval:300,time:250,threshold:2,posThreshold:10},getTouchAction:function(){return[ti]},process:function(t){var e=this.options,i=t.pointers.length===e.pointers,n=t.distance<e.threshold,s=t.deltaTime<e.time;if(this.reset(),t.eventType&we&&0===this.count)return this.failTimeout();if(n&&s&&i){if(t.eventType!=Se)return this.failTimeout();var o=this.pTime?t.timeStamp-this.pTime<e.interval:!0,a=!this.pCenter||O(this.pCenter,t.center)<e.posThreshold;this.pTime=t.timeStamp,this.pCenter=t.center,a&&o?this.count+=1:this.count=1,this._input=t;var h=this.count%e.taps;if(0===h)return this.hasRequireFailures()?(this._timer=r(function(){this.state=hi,this.tryEmit()},e.interval,this),si):hi}return ci},failTimeout:function(){return this._timer=r(function(){this.state=ci},this.options.interval,this),ci},reset:function(){clearTimeout(this._timer)},emit:function(){this.state==hi&&(this._input.tapCount=this.count,this.manager.emit(this.options.event,this._input))}}),re.VERSION="2.0.4",re.defaults={domEvents:!1,touchAction:Ke,enable:!0,inputTarget:null,inputClass:null,preset:[[ee,{enable:!1}],[Q,{enable:!1},["rotate"]],[ie,{direction:Pe}],[K,{direction:Pe},["swipe"]],[ne],[ne,{event:"doubletap",taps:2},["tap"]],[te]],cssProps:{userSelect:"none",touchSelect:"none",touchCallout:"none",contentZooming:"none",userDrag:"none",tapHighlightColor:"rgba(0,0,0,0)"}};var li=1,pi=2;se.prototype={set:function(t){return a(this.options,t),t.touchAction&&this.touchAction.update(),t.inputTarget&&(this.input.destroy(),this.input.target=t.inputTarget,this.input.init()),this},stop:function(t){this.session.stopped=t?pi:li},recognize:function(t){var e=this.session;if(!e.stopped){this.touchAction.preventDefaults(t);var i,n=this.recognizers,r=e.curRecognizer;(!r||r&&r.state&hi)&&(r=e.curRecognizer=null);for(var s=0;s<n.length;)i=n[s],e.stopped===pi||r&&i!=r&&!i.canRecognizeWith(r)?i.reset():i.recognize(t),!r&&i.state&(si|oi|ai)&&(r=e.curRecognizer=i),s++}},get:function(t){if(t instanceof j)return t;for(var e=this.recognizers,i=0;i<e.length;i++)if(e[i].options.event==t)return e[i];return null},add:function(t){if(s(t,"add",this))return this;var e=this.get(t.options.event);return e&&this.remove(e),this.recognizers.push(t),t.manager=this,this.touchAction.update(),t},remove:function(t){if(s(t,"remove",this))return this;var e=this.recognizers;return t=this.get(t),e.splice(T(e,t),1),this.touchAction.update(),this},on:function(t,e){var i=this.handlers;return o(g(t),function(t){i[t]=i[t]||[],i[t].push(e)}),this},off:function(t,e){var i=this.handlers;return o(g(t),function(t){e?i[t].splice(T(i[t],e),1):delete i[t]}),this},emit:function(t,e){this.options.domEvents&&ae(t,e);var i=this.handlers[t]&&this.handlers[t].slice();if(i&&i.length){e.type=t,e.preventDefault=function(){e.srcEvent.preventDefault()};for(var n=0;n<i.length;)i[n](e),n++}},destroy:function(){this.element&&oe(this,!1),this.handlers={},this.session={},this.input.destroy(),this.element=null}},a(re,{INPUT_START:we,INPUT_MOVE:De,INPUT_END:Se,INPUT_CANCEL:be,STATE_POSSIBLE:ri,STATE_BEGAN:si,STATE_CHANGED:oi,STATE_ENDED:ai,STATE_RECOGNIZED:hi,STATE_CANCELLED:ui,STATE_FAILED:ci,DIRECTION_NONE:Ce,DIRECTION_LEFT:Re,DIRECTION_RIGHT:ze,DIRECTION_UP:Me,DIRECTION_DOWN:Ne,DIRECTION_HORIZONTAL:Pe,DIRECTION_VERTICAL:Oe,DIRECTION_ALL:Xe,Manager:se,Input:w,TouchAction:G,TouchInput:L,MouseInput:x,PointerEventInput:W,TouchMouseInput:U,SingleTouchInput:q,Recognizer:j,AttrRecognizer:J,Tap:ne,Pan:K,Swipe:ie,Pinch:Q,Rotate:ee,Press:te,on:f,off:d,each:o,merge:h,extend:a,inherit:u,bindFn:c,prefixed:I}),t[i]=re}(window,document,"Hammer"),function(){function t(t,e){var i=$(t);i.data("hammer")||i.data("hammer",new Hammer(i[0],e))}$.fn.hammer=function(e){return this.each(function(){t(this,e)})},Hammer.Manager.prototype.emit=function(t){return function(e,i){t.call(this,e,i),$(this.element).trigger({type:e,gesture:i})}}(Hammer.Manager.prototype.emit)}()});