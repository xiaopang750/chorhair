!function(e,t){"use strict";function n(){Hammer.READY||(Hammer.event.determineEventTypes(),Hammer.utils.each(Hammer.gestures,function(e){Hammer.detection.register(e)}),Hammer.event.onTouch(Hammer.DOCUMENT,Hammer.EVENT_MOVE,Hammer.detection.detect),Hammer.event.onTouch(Hammer.DOCUMENT,Hammer.EVENT_END,Hammer.detection.detect),Hammer.READY=!0)}e.Hammer=function(e,t){return new Hammer.Instance(e,t||{})},Hammer.defaults={stop_browser_behavior:{userSelect:"none",touchAction:"none",touchCallout:"none",contentZooming:"none",userDrag:"none",tapHighlightColor:"rgba(0,0,0,0)"}},Hammer.HAS_POINTEREVENTS=e.navigator.pointerEnabled||e.navigator.msPointerEnabled,Hammer.HAS_TOUCHEVENTS="ontouchstart"in e,Hammer.MOBILE_REGEX=/mobile|tablet|ip(ad|hone|od)|android|silk/i,Hammer.NO_MOUSEEVENTS=Hammer.HAS_TOUCHEVENTS&&e.navigator.userAgent.match(Hammer.MOBILE_REGEX),Hammer.EVENT_TYPES={},Hammer.DIRECTION_DOWN="down",Hammer.DIRECTION_LEFT="left",Hammer.DIRECTION_UP="up",Hammer.DIRECTION_RIGHT="right",Hammer.POINTER_MOUSE="mouse",Hammer.POINTER_TOUCH="touch",Hammer.POINTER_PEN="pen",Hammer.EVENT_START="start",Hammer.EVENT_MOVE="move",Hammer.EVENT_END="end",Hammer.DOCUMENT=e.document,Hammer.plugins=Hammer.plugins||{},Hammer.gestures=Hammer.gestures||{},Hammer.READY=!1,Hammer.utils={extend:function(e,n,r){for(var a in n)e[a]!==t&&r||(e[a]=n[a]);return e},each:function(e,n,r){var a,i;if("forEach"in e)e.forEach(n,r);else if(e.length!==t){for(a=0,i=e.length;i>a;a++)if(n.call(r,e[a],a,e)===!1)return}else for(a in e)if(e.hasOwnProperty(a)&&n.call(r,e[a],a,e)===!1)return},hasParent:function(e,t){for(;e;){if(e==t)return!0;e=e.parentNode}return!1},getCenter:function(e){var t=[],n=[];return Hammer.utils.each(e,function(e){t.push("undefined"!=typeof e.clientX?e.clientX:e.pageX),n.push("undefined"!=typeof e.clientY?e.clientY:e.pageY)}),{pageX:(Math.min.apply(Math,t)+Math.max.apply(Math,t))/2,pageY:(Math.min.apply(Math,n)+Math.max.apply(Math,n))/2}},getVelocity:function(e,t,n){return{x:Math.abs(t/e)||0,y:Math.abs(n/e)||0}},getAngle:function(e,t){var n=t.pageY-e.pageY,r=t.pageX-e.pageX;return 180*Math.atan2(n,r)/Math.PI},getDirection:function(e,t){var n=Math.abs(e.pageX-t.pageX),r=Math.abs(e.pageY-t.pageY);return n>=r?e.pageX-t.pageX>0?Hammer.DIRECTION_LEFT:Hammer.DIRECTION_RIGHT:e.pageY-t.pageY>0?Hammer.DIRECTION_UP:Hammer.DIRECTION_DOWN},getDistance:function(e,t){var n=t.pageX-e.pageX,r=t.pageY-e.pageY;return Math.sqrt(n*n+r*r)},getScale:function(e,t){return e.length>=2&&t.length>=2?this.getDistance(t[0],t[1])/this.getDistance(e[0],e[1]):1},getRotation:function(e,t){return e.length>=2&&t.length>=2?this.getAngle(t[1],t[0])-this.getAngle(e[1],e[0]):0},isVertical:function(e){return e==Hammer.DIRECTION_UP||e==Hammer.DIRECTION_DOWN},stopDefaultBrowserBehavior:function(e,t){t&&e&&e.style&&(Hammer.utils.each(["webkit","khtml","moz","Moz","ms","o",""],function(n){Hammer.utils.each(t,function(t){n&&(t=n+t.substring(0,1).toUpperCase()+t.substring(1)),t in e.style&&(e.style[t]=t)})}),"none"==t.userSelect&&(e.onselectstart=function(){return!1}),"none"==t.userDrag&&(e.ondragstart=function(){return!1}))}},Hammer.Instance=function(e,t){var r=this;return n(),this.element=e,this.enabled=!0,this.options=Hammer.utils.extend(Hammer.utils.extend({},Hammer.defaults),t||{}),this.options.stop_browser_behavior&&Hammer.utils.stopDefaultBrowserBehavior(this.element,this.options.stop_browser_behavior),Hammer.event.onTouch(e,Hammer.EVENT_START,function(e){r.enabled&&Hammer.detection.startDetect(r,e)}),this},Hammer.Instance.prototype={on:function(e,t){var n=e.split(" ");return Hammer.utils.each(n,function(e){this.element.addEventListener(e,t,!1)},this),this},off:function(e,t){var n=e.split(" ");return Hammer.utils.each(n,function(e){this.element.removeEventListener(e,t,!1)},this),this},trigger:function(e,t){t||(t={});var n=Hammer.DOCUMENT.createEvent("Event");n.initEvent(e,!0,!0),n.gesture=t;var r=this.element;return Hammer.utils.hasParent(t.target,r)&&(r=t.target),r.dispatchEvent(n),this},enable:function(e){return this.enabled=e,this}};var r=null,a=!1,i=!1;Hammer.event={bindDom:function(e,t,n){var r=t.split(" ");Hammer.utils.each(r,function(t){e.addEventListener(t,n,!1)})},onTouch:function(e,t,n){var m=this;this.bindDom(e,Hammer.EVENT_TYPES[t],function(o){var s=o.type.toLowerCase();if(!s.match(/mouse/)||!i){s.match(/touch/)||s.match(/pointerdown/)||s.match(/mouse/)&&1===o.which?a=!0:s.match(/mouse/)&&!o.which&&(a=!1),s.match(/touch|pointer/)&&(i=!0);var c=0;a&&(Hammer.HAS_POINTEREVENTS&&t!=Hammer.EVENT_END?c=Hammer.PointerEvent.updatePointer(t,o):s.match(/touch/)?c=o.touches.length:i||(c=s.match(/up/)?0:1),c>0&&t==Hammer.EVENT_END?t=Hammer.EVENT_MOVE:c||(t=Hammer.EVENT_END),(c||null===r)&&(r=o),n.call(Hammer.detection,m.collectEventData(e,t,m.getTouchList(r,t),o)),Hammer.HAS_POINTEREVENTS&&t==Hammer.EVENT_END&&(c=Hammer.PointerEvent.updatePointer(t,o))),c||(r=null,a=!1,i=!1,Hammer.PointerEvent.reset())}})},determineEventTypes:function(){var e;e=Hammer.HAS_POINTEREVENTS?Hammer.PointerEvent.getEvents():Hammer.NO_MOUSEEVENTS?["touchstart","touchmove","touchend touchcancel"]:["touchstart mousedown","touchmove mousemove","touchend touchcancel mouseup"],Hammer.EVENT_TYPES[Hammer.EVENT_START]=e[0],Hammer.EVENT_TYPES[Hammer.EVENT_MOVE]=e[1],Hammer.EVENT_TYPES[Hammer.EVENT_END]=e[2]},getTouchList:function(e){return Hammer.HAS_POINTEREVENTS?Hammer.PointerEvent.getTouchList():e.touches?e.touches:(e.identifier=1,[e])},collectEventData:function(e,t,n,r){var a=Hammer.POINTER_TOUCH;return(r.type.match(/mouse/)||Hammer.PointerEvent.matchType(Hammer.POINTER_MOUSE,r))&&(a=Hammer.POINTER_MOUSE),{center:Hammer.utils.getCenter(n),timeStamp:(new Date).getTime(),target:r.target,touches:n,eventType:t,pointerType:a,srcEvent:r,preventDefault:function(){this.srcEvent.preventManipulation&&this.srcEvent.preventManipulation(),this.srcEvent.preventDefault&&this.srcEvent.preventDefault()},stopPropagation:function(){this.srcEvent.stopPropagation()},stopDetect:function(){return Hammer.detection.stopDetect()}}}},Hammer.PointerEvent={pointers:{},getTouchList:function(){var e=this,t=[];return Hammer.utils.each(e.pointers,function(e){t.push(e)}),t},updatePointer:function(e,t){return e==Hammer.EVENT_END?this.pointers={}:(t.identifier=t.pointerId,this.pointers[t.pointerId]=t),Object.keys(this.pointers).length},matchType:function(e,t){if(!t.pointerType)return!1;var n=t.pointerType,r={};return r[Hammer.POINTER_MOUSE]=n===t.MSPOINTER_TYPE_MOUSE||n===Hammer.POINTER_MOUSE,r[Hammer.POINTER_TOUCH]=n===t.MSPOINTER_TYPE_TOUCH||n===Hammer.POINTER_TOUCH,r[Hammer.POINTER_PEN]=n===t.MSPOINTER_TYPE_PEN||n===Hammer.POINTER_PEN,r[e]},getEvents:function(){return["pointerdown MSPointerDown","pointermove MSPointerMove","pointerup pointercancel MSPointerUp MSPointerCancel"]},reset:function(){this.pointers={}}},Hammer.detection={gestures:[],current:null,previous:null,stopped:!1,startDetect:function(e,t){this.current||(this.stopped=!1,this.current={inst:e,startEvent:Hammer.utils.extend({},t),lastEvent:!1,name:""},this.detect(t))},detect:function(e){if(this.current&&!this.stopped){e=this.extendEventData(e);var t=this.current.inst.options;return Hammer.utils.each(this.gestures,function(n){return this.stopped||t[n.name]===!1||n.handler.call(n,e,this.current.inst)!==!1?void 0:(this.stopDetect(),!1)},this),this.current&&(this.current.lastEvent=e),e.eventType==Hammer.EVENT_END&&!e.touches.length-1&&this.stopDetect(),e}},stopDetect:function(){this.previous=Hammer.utils.extend({},this.current),this.current=null,this.stopped=!0},extendEventData:function(e){var t=this.current.startEvent;!t||e.touches.length==t.touches.length&&e.touches!==t.touches||(t.touches=[],Hammer.utils.each(e.touches,function(e){t.touches.push(Hammer.utils.extend({},e))}));var n,r,a=e.timeStamp-t.timeStamp,i=e.center.pageX-t.center.pageX,m=e.center.pageY-t.center.pageY,o=Hammer.utils.getVelocity(a,i,m);return"end"===e.eventType?(n=this.current.lastEvent&&this.current.lastEvent.interimAngle,r=this.current.lastEvent&&this.current.lastEvent.interimDirection):(n=this.current.lastEvent&&Hammer.utils.getAngle(this.current.lastEvent.center,e.center),r=this.current.lastEvent&&Hammer.utils.getDirection(this.current.lastEvent.center,e.center)),Hammer.utils.extend(e,{deltaTime:a,deltaX:i,deltaY:m,velocityX:o.x,velocityY:o.y,distance:Hammer.utils.getDistance(t.center,e.center),angle:Hammer.utils.getAngle(t.center,e.center),interimAngle:n,direction:Hammer.utils.getDirection(t.center,e.center),interimDirection:r,scale:Hammer.utils.getScale(t.touches,e.touches),rotation:Hammer.utils.getRotation(t.touches,e.touches),startEvent:t}),e},register:function(e){var n=e.defaults||{};return n[e.name]===t&&(n[e.name]=!0),Hammer.utils.extend(Hammer.defaults,n,!0),e.index=e.index||1e3,this.gestures.push(e),this.gestures.sort(function(e,t){return e.index<t.index?-1:e.index>t.index?1:0}),this.gestures}},Hammer.gestures.Drag={name:"drag",index:50,defaults:{drag_min_distance:10,correct_for_drag_min_distance:!0,drag_max_touches:1,drag_block_horizontal:!1,drag_block_vertical:!1,drag_lock_to_axis:!1,drag_lock_min_distance:25},triggered:!1,handler:function(e,t){if(Hammer.detection.current.name!=this.name&&this.triggered)return t.trigger(this.name+"end",e),void(this.triggered=!1);if(!(t.options.drag_max_touches>0&&e.touches.length>t.options.drag_max_touches))switch(e.eventType){case Hammer.EVENT_START:this.triggered=!1;break;case Hammer.EVENT_MOVE:if(e.distance<t.options.drag_min_distance&&Hammer.detection.current.name!=this.name)return;if(Hammer.detection.current.name!=this.name&&(Hammer.detection.current.name=this.name,t.options.correct_for_drag_min_distance&&e.distance>0)){var n=Math.abs(t.options.drag_min_distance/e.distance);Hammer.detection.current.startEvent.center.pageX+=e.deltaX*n,Hammer.detection.current.startEvent.center.pageY+=e.deltaY*n,e=Hammer.detection.extendEventData(e)}(Hammer.detection.current.lastEvent.drag_locked_to_axis||t.options.drag_lock_to_axis&&t.options.drag_lock_min_distance<=e.distance)&&(e.drag_locked_to_axis=!0);var r=Hammer.detection.current.lastEvent.direction;e.drag_locked_to_axis&&r!==e.direction&&(e.direction=Hammer.utils.isVertical(r)?e.deltaY<0?Hammer.DIRECTION_UP:Hammer.DIRECTION_DOWN:e.deltaX<0?Hammer.DIRECTION_LEFT:Hammer.DIRECTION_RIGHT),this.triggered||(t.trigger(this.name+"start",e),this.triggered=!0),t.trigger(this.name,e),t.trigger(this.name+e.direction,e),(t.options.drag_block_vertical&&Hammer.utils.isVertical(e.direction)||t.options.drag_block_horizontal&&!Hammer.utils.isVertical(e.direction))&&e.preventDefault();break;case Hammer.EVENT_END:this.triggered&&t.trigger(this.name+"end",e),this.triggered=!1}}},Hammer.gestures.Hold={name:"hold",index:10,defaults:{hold_timeout:500,hold_threshold:1},timer:null,handler:function(e,t){switch(e.eventType){case Hammer.EVENT_START:clearTimeout(this.timer),Hammer.detection.current.name=this.name,this.timer=setTimeout(function(){"hold"==Hammer.detection.current.name&&t.trigger("hold",e)},t.options.hold_timeout);break;case Hammer.EVENT_MOVE:e.distance>t.options.hold_threshold&&clearTimeout(this.timer);break;case Hammer.EVENT_END:clearTimeout(this.timer)}}},Hammer.gestures.Release={name:"release",index:1/0,handler:function(e,t){e.eventType==Hammer.EVENT_END&&t.trigger(this.name,e)}},Hammer.gestures.Swipe={name:"swipe",index:40,defaults:{swipe_min_touches:1,swipe_max_touches:1,swipe_velocity:.7},handler:function(e,t){if(e.eventType==Hammer.EVENT_END){if(t.options.swipe_max_touches>0&&e.touches.length<t.options.swipe_min_touches&&e.touches.length>t.options.swipe_max_touches)return;(e.velocityX>t.options.swipe_velocity||e.velocityY>t.options.swipe_velocity)&&(t.trigger(this.name,e),t.trigger(this.name+e.direction,e))}}},Hammer.gestures.Tap={name:"tap",index:100,defaults:{tap_max_touchtime:250,tap_max_distance:10,tap_always:!0,doubletap_distance:20,doubletap_interval:300},handler:function(e,t){if(e.eventType==Hammer.EVENT_END&&"touchcancel"!=e.srcEvent.type){var n=Hammer.detection.previous,r=!1;if(e.deltaTime>t.options.tap_max_touchtime||e.distance>t.options.tap_max_distance)return;n&&"tap"==n.name&&e.timeStamp-n.lastEvent.timeStamp<t.options.doubletap_interval&&e.distance<t.options.doubletap_distance&&(t.trigger("doubletap",e),r=!0),(!r||t.options.tap_always)&&(Hammer.detection.current.name="tap",t.trigger(Hammer.detection.current.name,e))}}},Hammer.gestures.Touch={name:"touch",index:-1/0,defaults:{prevent_default:!1,prevent_mouseevents:!1},handler:function(e,t){return t.options.prevent_mouseevents&&e.pointerType==Hammer.POINTER_MOUSE?void e.stopDetect():(t.options.prevent_default&&e.preventDefault(),void(e.eventType==Hammer.EVENT_START&&t.trigger(this.name,e)))}},Hammer.gestures.Transform={name:"transform",index:45,defaults:{transform_min_scale:.01,transform_min_rotation:1,transform_always_block:!1},triggered:!1,handler:function(e,t){if(Hammer.detection.current.name!=this.name&&this.triggered)return t.trigger(this.name+"end",e),void(this.triggered=!1);if(!(e.touches.length<2))switch(t.options.transform_always_block&&e.preventDefault(),e.eventType){case Hammer.EVENT_START:this.triggered=!1;break;case Hammer.EVENT_MOVE:var n=Math.abs(1-e.scale),r=Math.abs(e.rotation);if(n<t.options.transform_min_scale&&r<t.options.transform_min_rotation)return;Hammer.detection.current.name=this.name,this.triggered||(t.trigger(this.name+"start",e),this.triggered=!0),t.trigger(this.name,e),r>t.options.transform_min_rotation&&t.trigger("rotate",e),n>t.options.transform_min_scale&&(t.trigger("pinch",e),t.trigger("pinch"+(e.scale<1?"in":"out"),e));break;case Hammer.EVENT_END:this.triggered&&t.trigger(this.name+"end",e),this.triggered=!1}}},"function"==typeof define&&"object"==typeof define.amd&&define.amd?define(function(){return Hammer}):"object"==typeof module&&"object"==typeof module.exports?module.exports=Hammer:e.Hammer=Hammer}(this),function(e,t){"use strict";function n(e,n){e.event.bindDom=function(e,r,a){n(e).on(r,function(e){var n=e.originalEvent||e;n.pageX===t&&(n.pageX=e.pageX,n.pageY=e.pageY),n.target||(n.target=e.target),n.which===t&&(n.which=n.button),n.preventDefault||(n.preventDefault=e.preventDefault),n.stopPropagation||(n.stopPropagation=e.stopPropagation),a.call(this,n)})},e.Instance.prototype.on=function(e,t){return n(this.element).on(e,t)},e.Instance.prototype.off=function(e,t){return n(this.element).off(e,t)},e.Instance.prototype.trigger=function(e,t){var r=n(this.element);return r.has(t.target).length&&(r=n(t.target)),r.trigger({type:e,gesture:t})},n.fn.hammer=function(t){return this.each(function(){var r=n(this),a=r.data("hammer");a?a&&t&&e.utils.extend(a.options,t):r.data("hammer",new e(this,t||{}))})}}"function"==typeof define&&"object"==typeof define.amd&&define.amd?define(["hammerjs","jquery"],n):n(e.Hammer,e.jQuery||e.Zepto)}(this);