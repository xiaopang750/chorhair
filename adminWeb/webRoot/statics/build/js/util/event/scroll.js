/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("util/event/scroll",[],function(){function e(e,t){this.ele=e.get(0),this.callBack=t}$.fn.scroll=function(t){var r;return $(this).each(function(){r=new e($(this),t),r.init()})},e.prototype={init:function(){var e=this;document.addEventListener?(this.ele.addEventListener("mousewheel",function(t){e.wheel(t,e.callBack)},!1),this.ele.addEventListener("DOMMouseScroll",function(t){e.wheel(t,e.callBack)},!1)):this.ele.attachEvent("onmousewheel",function(t){return e.wheel(t,e.callBack),!1})},wheel:function(e,t){var r,i,n=e||event;return i=this,r=n.wheelDelta?0>n.wheelDelta:n.detail>0,t&&t(r),n.preventDefault?(n.preventDefault(),void 0):!1}}});