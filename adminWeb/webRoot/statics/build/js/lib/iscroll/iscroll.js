/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("lib/iscroll/iscroll",[],function(e,t,i){(function(e,r){function o(e){return""===a?e:(e=e.charAt(0).toUpperCase()+e.substr(1),a+e)}var n=Math,s=r.createElement("div").style,a=function(){for(var e,t="t,webkitT,MozT,msT,OT".split(","),i=0,r=t.length;r>i;i++)if(e=t[i]+"ransform",e in s)return t[i].substr(0,t[i].length-1);return!1}(),c=a?"-"+a.toLowerCase()+"-":"",l=o("transform"),u=o("transitionProperty"),d=o("transitionDuration"),f=o("transformOrigin"),p=o("transitionTimingFunction"),h=o("transitionDelay"),m=/android/gi.test(navigator.appVersion),g=/iphone|ipad/gi.test(navigator.appVersion),v=/hp-tablet/gi.test(navigator.appVersion),b=o("perspective")in s,x="ontouchstart"in e&&!v,y=a!==!1,w=o("transition")in s,S="onorientationchange"in e?"orientationchange":"resize",P=x?"touchstart":"mousedown",R=x?"touchmove":"mousemove",k=x?"touchend":"mouseup",q=x?"touchcancel":"mouseup",_=function(){if(a===!1)return!1;var e={"":"transitionend",webkit:"webkitTransitionEnd",Moz:"transitionend",O:"otransitionend",ms:"MSTransitionEnd"};return e[a]}(),C=function(){return e.requestAnimationFrame||e.webkitRequestAnimationFrame||e.mozRequestAnimationFrame||e.oRequestAnimationFrame||e.msRequestAnimationFrame||function(e){return setTimeout(e,1)}}(),L=function(){return e.cancelRequestAnimationFrame||e.webkitCancelAnimationFrame||e.webkitCancelRequestAnimationFrame||e.mozCancelRequestAnimationFrame||e.oCancelRequestAnimationFrame||e.msCancelRequestAnimationFrame||clearTimeout}(),T=b?" translateZ(0)":"",A=function(t,i){var o,n=this;n.wrapper="object"==typeof t?t:r.getElementById(t),n.wrapper.style.overflow="hidden",n.scroller=n.wrapper.children[0],n.options={hScroll:!1,vScroll:!0,x:0,y:0,bounce:!1,bounceLock:!1,momentum:!0,lockDirection:!0,useTransform:!0,useTransition:!1,topOffset:0,checkDOMChanges:!1,handleClick:!0,hScrollbar:!1,vScrollbar:!1,fixedScrollbar:m,hideScrollbar:g,fadeScrollbar:g&&b,scrollbarClass:"",zoom:!1,zoomMin:1,zoomMax:4,doubleTapZoom:2,wheelAction:"scroll",snap:!1,snapThreshold:1,onRefresh:null,onBeforeScrollStart:function(e){e.preventDefault()},onScrollStart:null,onBeforeScrollMove:null,onScrollMove:null,onBeforeScrollEnd:null,onScrollEnd:null,onTouchEnd:null,onDestroy:null,onZoomStart:null,onZoom:null,onZoomEnd:null};for(o in i)n.options[o]=i[o];n.x=n.options.x,n.y=n.options.y,n.options.useTransform=y&&n.options.useTransform,n.options.hScrollbar=n.options.hScroll&&n.options.hScrollbar,n.options.vScrollbar=n.options.vScroll&&n.options.vScrollbar,n.options.zoom=n.options.useTransform&&n.options.zoom,n.options.useTransition=w&&n.options.useTransition,n.options.zoom&&m&&(T=""),n.scroller.style[u]=n.options.useTransform?c+"transform":"top left",n.scroller.style[d]="0",n.scroller.style[f]="0 0",n.options.useTransition&&(n.scroller.style[p]="cubic-bezier(0.33,0.66,0.66,1)"),n.options.useTransform?n.scroller.style[l]="translate("+n.x+"px,"+n.y+"px)"+T:n.scroller.style.cssText+=";position:absolute;top:"+n.y+"px;left:"+n.x+"px",n.options.useTransition&&(n.options.fixedScrollbar=!0),n.refresh(),n._bind(S,e),n._bind(P),x||"none"!=n.options.wheelAction&&(n._bind("DOMMouseScroll"),n._bind("mousewheel")),n.options.checkDOMChanges&&(n.checkDOMTime=setInterval(function(){n._checkDOMChanges()},500))};A.prototype={enabled:!0,x:0,y:0,steps:[],scale:1,currPageX:0,currPageY:0,pagesX:[],pagesY:[],aniTime:null,wheelZoomCount:0,handleEvent:function(e){var t=this;switch(e.type){case P:if(!x&&0!==e.button)return;t._start(e);break;case R:t._move(e);break;case k:case q:t._end(e);break;case S:t._resize();break;case"DOMMouseScroll":case"mousewheel":t._wheel(e);break;case _:t._transitionEnd(e)}},_checkDOMChanges:function(){this.moved||this.zoomed||this.animating||this.scrollerW==this.scroller.offsetWidth*this.scale&&this.scrollerH==this.scroller.offsetHeight*this.scale||this.refresh()},_scrollbar:function(e){var t,i=this;return i[e+"Scrollbar"]?(i[e+"ScrollbarWrapper"]||(t=r.createElement("div"),i.options.scrollbarClass?t.className=i.options.scrollbarClass+e.toUpperCase():t.style.cssText="position:absolute;z-index:100;"+("h"==e?"height:7px;bottom:1px;left:2px;right:"+(i.vScrollbar?"7":"2")+"px":"width:7px;bottom:"+(i.hScrollbar?"7":"2")+"px;top:2px;right:1px"),t.style.cssText+=";pointer-events:none;"+c+"transition-property:opacity;"+c+"transition-duration:"+(i.options.fadeScrollbar?"350ms":"0")+";overflow:hidden;opacity:"+(i.options.hideScrollbar?"0":"1"),i.wrapper.appendChild(t),i[e+"ScrollbarWrapper"]=t,t=r.createElement("div"),i.options.scrollbarClass||(t.style.cssText="position:absolute;z-index:100;background:rgba(0,0,0,0.5);border:1px solid rgba(255,255,255,0.9);"+c+"background-clip:padding-box;"+c+"box-sizing:border-box;"+("h"==e?"height:100%":"width:100%")+";"+c+"border-radius:3px;border-radius:3px"),t.style.cssText+=";pointer-events:none;"+c+"transition-property:"+c+"transform;"+c+"transition-timing-function:cubic-bezier(0.33,0.66,0.66,1);"+c+"transition-duration:0;"+c+"transform: translate(0,0)"+T,i.options.useTransition&&(t.style.cssText+=";"+c+"transition-timing-function:cubic-bezier(0.33,0.66,0.66,1)"),i[e+"ScrollbarWrapper"].appendChild(t),i[e+"ScrollbarIndicator"]=t),"h"==e?(i.hScrollbarSize=i.hScrollbarWrapper.clientWidth,i.hScrollbarIndicatorSize=n.max(n.round(i.hScrollbarSize*i.hScrollbarSize/i.scrollerW),8),i.hScrollbarIndicator.style.width=i.hScrollbarIndicatorSize+"px",i.hScrollbarMaxScroll=i.hScrollbarSize-i.hScrollbarIndicatorSize,i.hScrollbarProp=i.hScrollbarMaxScroll/i.maxScrollX):(i.vScrollbarSize=i.vScrollbarWrapper.clientHeight,i.vScrollbarIndicatorSize=n.max(n.round(i.vScrollbarSize*i.vScrollbarSize/i.scrollerH),8),i.vScrollbarIndicator.style.height=i.vScrollbarIndicatorSize+"px",i.vScrollbarMaxScroll=i.vScrollbarSize-i.vScrollbarIndicatorSize,i.vScrollbarProp=i.vScrollbarMaxScroll/i.maxScrollY),i._scrollbarPos(e,!0),void 0):(i[e+"ScrollbarWrapper"]&&(y&&(i[e+"ScrollbarIndicator"].style[l]=""),i[e+"ScrollbarWrapper"].parentNode.removeChild(i[e+"ScrollbarWrapper"]),i[e+"ScrollbarWrapper"]=null,i[e+"ScrollbarIndicator"]=null),void 0)},_resize:function(){var e=this;setTimeout(function(){e.refresh()},m?200:0)},_pos:function(e,t){this.zoomed||(e=this.hScroll?e:0,t=this.vScroll?t:0,this.options.useTransform?this.scroller.style[l]="translate("+e+"px,"+t+"px) scale("+this.scale+")"+T:(e=n.round(e),t=n.round(t),this.scroller.style.left=e+"px",this.scroller.style.top=t+"px"),this.x=e,this.y=t,this._scrollbarPos("h"),this._scrollbarPos("v"))},_scrollbarPos:function(e,t){var i,r=this,o="h"==e?r.x:r.y;r[e+"Scrollbar"]&&(o=r[e+"ScrollbarProp"]*o,0>o?(r.options.fixedScrollbar||(i=r[e+"ScrollbarIndicatorSize"]+n.round(3*o),8>i&&(i=8),r[e+"ScrollbarIndicator"].style["h"==e?"width":"height"]=i+"px"),o=0):o>r[e+"ScrollbarMaxScroll"]&&(r.options.fixedScrollbar?o=r[e+"ScrollbarMaxScroll"]:(i=r[e+"ScrollbarIndicatorSize"]-n.round(3*(o-r[e+"ScrollbarMaxScroll"])),8>i&&(i=8),r[e+"ScrollbarIndicator"].style["h"==e?"width":"height"]=i+"px",o=r[e+"ScrollbarMaxScroll"]+(r[e+"ScrollbarIndicatorSize"]-i))),r[e+"ScrollbarWrapper"].style[h]="0",r[e+"ScrollbarWrapper"].style.opacity=t&&r.options.hideScrollbar?"0":"1",r[e+"ScrollbarIndicator"].style[l]="translate("+("h"==e?o+"px,0)":"0,"+o+"px)")+T)},_start:function(t){var i,r,o,s,a,c=this,u=x?t.touches[0]:t;c.enabled&&(c.options.onBeforeScrollStart&&c.options.onBeforeScrollStart.call(c,t),(c.options.useTransition||c.options.zoom)&&c._transitionTime(0),c.moved=!1,c.animating=!1,c.zoomed=!1,c.distX=0,c.distY=0,c.absDistX=0,c.absDistY=0,c.dirX=0,c.dirY=0,c.options.zoom&&x&&t.touches.length>1&&(s=n.abs(t.touches[0].pageX-t.touches[1].pageX),a=n.abs(t.touches[0].pageY-t.touches[1].pageY),c.touchesDistStart=n.sqrt(s*s+a*a),c.originX=n.abs(t.touches[0].pageX+t.touches[1].pageX-2*c.wrapperOffsetLeft)/2-c.x,c.originY=n.abs(t.touches[0].pageY+t.touches[1].pageY-2*c.wrapperOffsetTop)/2-c.y,c.options.onZoomStart&&c.options.onZoomStart.call(c,t)),c.options.momentum&&(c.options.useTransform?(i=getComputedStyle(c.scroller,null)[l].replace(/[^0-9\-.,]/g,"").split(","),r=+(i[12]||i[4]),o=+(i[13]||i[5])):(r=+getComputedStyle(c.scroller,null).left.replace(/[^0-9-]/g,""),o=+getComputedStyle(c.scroller,null).top.replace(/[^0-9-]/g,"")),(r!=c.x||o!=c.y)&&(c.options.useTransition?c._unbind(_):L(c.aniTime),c.steps=[],c._pos(r,o),c.options.onScrollEnd&&c.options.onScrollEnd.call(c))),c.absStartX=c.x,c.absStartY=c.y,c.startX=c.x,c.startY=c.y,c.pointX=u.pageX,c.pointY=u.pageY,c.startTime=t.timeStamp||Date.now(),c.options.onScrollStart&&c.options.onScrollStart.call(c,t),c._bind(R,e),c._bind(k,e),c._bind(q,e))},_move:function(e){var t,i,r,o=this,s=x?e.touches[0]:e,a=s.pageX-o.pointX,c=s.pageY-o.pointY,u=o.x+a,d=o.y+c,f=e.timeStamp||Date.now();return o.options.onBeforeScrollMove&&o.options.onBeforeScrollMove.call(o,e),o.options.zoom&&x&&e.touches.length>1?(t=n.abs(e.touches[0].pageX-e.touches[1].pageX),i=n.abs(e.touches[0].pageY-e.touches[1].pageY),o.touchesDist=n.sqrt(t*t+i*i),o.zoomed=!0,r=1/o.touchesDistStart*o.touchesDist*this.scale,o.options.zoomMin>r?r=.5*o.options.zoomMin*Math.pow(2,r/o.options.zoomMin):r>o.options.zoomMax&&(r=2*o.options.zoomMax*Math.pow(.5,o.options.zoomMax/r)),o.lastScale=r/this.scale,u=this.originX-this.originX*o.lastScale+this.x,d=this.originY-this.originY*o.lastScale+this.y,this.scroller.style[l]="translate("+u+"px,"+d+"px) scale("+r+")"+T,o.options.onZoom&&o.options.onZoom.call(o,e),void 0):(o.pointX=s.pageX,o.pointY=s.pageY,(u>0||o.maxScrollX>u)&&(u=o.options.bounce?o.x+a/2:u>=0||o.maxScrollX>=0?0:o.maxScrollX),(d>o.minScrollY||o.maxScrollY>d)&&(d=o.options.bounce?o.y+c/2:d>=o.minScrollY||o.maxScrollY>=0?o.minScrollY:o.maxScrollY),o.distX+=a,o.distY+=c,o.absDistX=n.abs(o.distX),o.absDistY=n.abs(o.distY),6>o.absDistX&&6>o.absDistY||(o.options.lockDirection&&(o.absDistX>o.absDistY+5?(d=o.y,c=0):o.absDistY>o.absDistX+5&&(u=o.x,a=0)),o.moved=!0,o._pos(u,d),o.dirX=a>0?-1:0>a?1:0,o.dirY=c>0?-1:0>c?1:0,f-o.startTime>300&&(o.startTime=f,o.startX=o.x,o.startY=o.y),o.options.onScrollMove&&o.options.onScrollMove.call(o,e)),void 0)},_end:function(t){if(!x||0===t.touches.length){var i,o,s,a,c,u,f,p=this,h=x?t.changedTouches[0]:t,m={dist:0,time:0},g={dist:0,time:0},v=(t.timeStamp||Date.now())-p.startTime,b=p.x,y=p.y;if(p._unbind(R,e),p._unbind(k,e),p._unbind(q,e),p.options.onBeforeScrollEnd&&p.options.onBeforeScrollEnd.call(p,t),p.zoomed)return f=p.scale*p.lastScale,f=Math.max(p.options.zoomMin,f),f=Math.min(p.options.zoomMax,f),p.lastScale=f/p.scale,p.scale=f,p.x=p.originX-p.originX*p.lastScale+p.x,p.y=p.originY-p.originY*p.lastScale+p.y,p.scroller.style[d]="200ms",p.scroller.style[l]="translate("+p.x+"px,"+p.y+"px) scale("+p.scale+")"+T,p.zoomed=!1,p.refresh(),p.options.onZoomEnd&&p.options.onZoomEnd.call(p,t),void 0;if(!p.moved)return x&&(p.doubleTapTimer&&p.options.zoom?(clearTimeout(p.doubleTapTimer),p.doubleTapTimer=null,p.options.onZoomStart&&p.options.onZoomStart.call(p,t),p.zoom(p.pointX,p.pointY,1==p.scale?p.options.doubleTapZoom:1),p.options.onZoomEnd&&setTimeout(function(){p.options.onZoomEnd.call(p,t)},200)):this.options.handleClick&&(p.doubleTapTimer=setTimeout(function(){for(p.doubleTapTimer=null,i=h.target;1!=i.nodeType;)i=i.parentNode;"SELECT"!=i.tagName&&"INPUT"!=i.tagName&&"TEXTAREA"!=i.tagName&&(o=r.createEvent("MouseEvents"),o.initMouseEvent("click",!0,!0,t.view,1,h.screenX,h.screenY,h.clientX,h.clientY,t.ctrlKey,t.altKey,t.shiftKey,t.metaKey,0,null),o._fake=!0,i.dispatchEvent(o))},p.options.zoom?250:0))),p._resetPos(400),p.options.onTouchEnd&&p.options.onTouchEnd.call(p,t),void 0;if(300>v&&p.options.momentum&&(m=b?p._momentum(b-p.startX,v,-p.x,p.scrollerW-p.wrapperW+p.x,p.options.bounce?p.wrapperW:0):m,g=y?p._momentum(y-p.startY,v,-p.y,0>p.maxScrollY?p.scrollerH-p.wrapperH+p.y-p.minScrollY:0,p.options.bounce?p.wrapperH:0):g,b=p.x+m.dist,y=p.y+g.dist,(p.x>0&&b>0||p.x<p.maxScrollX&&p.maxScrollX>b)&&(m={dist:0,time:0}),(p.y>p.minScrollY&&y>p.minScrollY||p.y<p.maxScrollY&&p.maxScrollY>y)&&(g={dist:0,time:0})),m.dist||g.dist)return c=n.max(n.max(m.time,g.time),10),p.options.snap&&(s=b-p.absStartX,a=y-p.absStartY,n.abs(s)<p.options.snapThreshold&&n.abs(a)<p.options.snapThreshold?p.scrollTo(p.absStartX,p.absStartY,200):(u=p._snap(b,y),b=u.x,y=u.y,c=n.max(u.time,c))),p.scrollTo(n.round(b),n.round(y),c),p.options.onTouchEnd&&p.options.onTouchEnd.call(p,t),void 0;if(p.options.snap)return s=b-p.absStartX,a=y-p.absStartY,n.abs(s)<p.options.snapThreshold&&n.abs(a)<p.options.snapThreshold?p.scrollTo(p.absStartX,p.absStartY,200):(u=p._snap(p.x,p.y),(u.x!=p.x||u.y!=p.y)&&p.scrollTo(u.x,u.y,u.time)),p.options.onTouchEnd&&p.options.onTouchEnd.call(p,t),void 0;p._resetPos(200),p.options.onTouchEnd&&p.options.onTouchEnd.call(p,t)}},_resetPos:function(e){var t=this,i=t.x>=0?0:t.x<t.maxScrollX?t.maxScrollX:t.x,r=t.y>=t.minScrollY||t.maxScrollY>0?t.minScrollY:t.y<t.maxScrollY?t.maxScrollY:t.y;return i==t.x&&r==t.y?(t.moved&&(t.moved=!1,t.options.onScrollEnd&&t.options.onScrollEnd.call(t)),t.hScrollbar&&t.options.hideScrollbar&&("webkit"==a&&(t.hScrollbarWrapper.style[h]="300ms"),t.hScrollbarWrapper.style.opacity="0"),t.vScrollbar&&t.options.hideScrollbar&&("webkit"==a&&(t.vScrollbarWrapper.style[h]="300ms"),t.vScrollbarWrapper.style.opacity="0"),void 0):(t.scrollTo(i,r,e||0),void 0)},_wheel:function(e){var t,i,r,o,n,s=this;if("wheelDeltaX"in e)t=e.wheelDeltaX/12,i=e.wheelDeltaY/12;else if("wheelDelta"in e)t=i=e.wheelDelta/12;else{if(!("detail"in e))return;t=i=3*-e.detail}return"zoom"==s.options.wheelAction?(n=s.scale*Math.pow(2,1/3*(i?i/Math.abs(i):0)),s.options.zoomMin>n&&(n=s.options.zoomMin),n>s.options.zoomMax&&(n=s.options.zoomMax),n!=s.scale&&(!s.wheelZoomCount&&s.options.onZoomStart&&s.options.onZoomStart.call(s,e),s.wheelZoomCount++,s.zoom(e.pageX,e.pageY,n,400),setTimeout(function(){s.wheelZoomCount--,!s.wheelZoomCount&&s.options.onZoomEnd&&s.options.onZoomEnd.call(s,e)},400)),void 0):(r=s.x+t,o=s.y+i,r>0?r=0:s.maxScrollX>r&&(r=s.maxScrollX),o>s.minScrollY?o=s.minScrollY:s.maxScrollY>o&&(o=s.maxScrollY),0>s.maxScrollY&&s.scrollTo(r,o,0),void 0)},_transitionEnd:function(e){var t=this;e.target==t.scroller&&(t._unbind(_),t._startAni())},_startAni:function(){var e,t,i,r=this,o=r.x,s=r.y,a=Date.now();if(!r.animating){if(!r.steps.length)return r._resetPos(400),void 0;if(e=r.steps.shift(),e.x==o&&e.y==s&&(e.time=0),r.animating=!0,r.moved=!0,r.options.useTransition)return r._transitionTime(e.time),r._pos(e.x,e.y),r.animating=!1,e.time?r._bind(_):r._resetPos(0),void 0;i=function(){var c,l,u=Date.now();return u>=a+e.time?(r._pos(e.x,e.y),r.animating=!1,r.options.onAnimationEnd&&r.options.onAnimationEnd.call(r),r._startAni(),void 0):(u=(u-a)/e.time-1,t=n.sqrt(1-u*u),c=(e.x-o)*t+o,l=(e.y-s)*t+s,r._pos(c,l),r.animating&&(r.aniTime=C(i)),void 0)},i()}},_transitionTime:function(e){e+="ms",this.scroller.style[d]=e,this.hScrollbar&&(this.hScrollbarIndicator.style[d]=e),this.vScrollbar&&(this.vScrollbarIndicator.style[d]=e)},_momentum:function(e,t,i,r,o){var s=6e-4,a=n.abs(e)/t,c=a*a/(2*s),l=0,u=0;return e>0&&c>i?(u=o/(6/(c/a*s)),i+=u,a=a*i/c,c=i):0>e&&c>r&&(u=o/(6/(c/a*s)),r+=u,a=a*r/c,c=r),c*=0>e?-1:1,l=a/s,{dist:c,time:n.round(l)}},_offset:function(e){for(var t=-e.offsetLeft,i=-e.offsetTop;e=e.offsetParent;)t-=e.offsetLeft,i-=e.offsetTop;return e!=this.wrapper&&(t*=this.scale,i*=this.scale),{left:t,top:i}},_snap:function(e,t){var i,r,o,s,a,c,l=this;for(o=l.pagesX.length-1,i=0,r=l.pagesX.length;r>i;i++)if(e>=l.pagesX[i]){o=i;break}for(o==l.currPageX&&o>0&&0>l.dirX&&o--,e=l.pagesX[o],a=n.abs(e-l.pagesX[l.currPageX]),a=a?500*(n.abs(l.x-e)/a):0,l.currPageX=o,o=l.pagesY.length-1,i=0;o>i;i++)if(t>=l.pagesY[i]){o=i;break}return o==l.currPageY&&o>0&&0>l.dirY&&o--,t=l.pagesY[o],c=n.abs(t-l.pagesY[l.currPageY]),c=c?500*(n.abs(l.y-t)/c):0,l.currPageY=o,s=n.round(n.max(a,c))||200,{x:e,y:t,time:s}},_bind:function(e,t,i){(t||this.scroller).addEventListener(e,this,!!i)},_unbind:function(e,t,i){(t||this.scroller).removeEventListener(e,this,!!i)},destroy:function(){var t=this;t.scroller.style[l]="",t.hScrollbar=!1,t.vScrollbar=!1,t._scrollbar("h"),t._scrollbar("v"),t._unbind(S,e),t._unbind(P),t._unbind(R,e),t._unbind(k,e),t._unbind(q,e),t.options.hasTouch||(t._unbind("DOMMouseScroll"),t._unbind("mousewheel")),t.options.useTransition&&t._unbind(_),t.options.checkDOMChanges&&clearInterval(t.checkDOMTime),t.options.onDestroy&&t.options.onDestroy.call(t)},refresh:function(){var e,t,i,r,o=this,s=0,a=0;if(o.scale<o.options.zoomMin&&(o.scale=o.options.zoomMin),o.wrapperW=o.wrapper.clientWidth||1,o.wrapperH=o.wrapper.clientHeight||1,o.minScrollY=-o.options.topOffset||0,o.scrollerW=n.round(o.scroller.offsetWidth*o.scale),o.scrollerH=n.round((o.scroller.offsetHeight+o.minScrollY)*o.scale),o.maxScrollX=o.wrapperW-o.scrollerW,o.maxScrollY=o.wrapperH-o.scrollerH+o.minScrollY,o.dirX=0,o.dirY=0,o.options.onRefresh&&o.options.onRefresh.call(o),o.hScroll=o.options.hScroll&&0>o.maxScrollX,o.vScroll=o.options.vScroll&&(!o.options.bounceLock&&!o.hScroll||o.scrollerH>o.wrapperH),o.hScrollbar=o.hScroll&&o.options.hScrollbar,o.vScrollbar=o.vScroll&&o.options.vScrollbar&&o.scrollerH>o.wrapperH,e=o._offset(o.wrapper),o.wrapperOffsetLeft=-e.left,o.wrapperOffsetTop=-e.top,"string"==typeof o.options.snap)for(o.pagesX=[],o.pagesY=[],r=o.scroller.querySelectorAll(o.options.snap),t=0,i=r.length;i>t;t++)s=o._offset(r[t]),s.left+=o.wrapperOffsetLeft,s.top+=o.wrapperOffsetTop,o.pagesX[t]=s.left<o.maxScrollX?o.maxScrollX:s.left*o.scale,o.pagesY[t]=s.top<o.maxScrollY?o.maxScrollY:s.top*o.scale;else if(o.options.snap){for(o.pagesX=[];s>=o.maxScrollX;)o.pagesX[a]=s,s-=o.wrapperW,a++;for(o.maxScrollX%o.wrapperW&&(o.pagesX[o.pagesX.length]=o.maxScrollX-o.pagesX[o.pagesX.length-1]+o.pagesX[o.pagesX.length-1]),s=0,a=0,o.pagesY=[];s>=o.maxScrollY;)o.pagesY[a]=s,s-=o.wrapperH,a++;o.maxScrollY%o.wrapperH&&(o.pagesY[o.pagesY.length]=o.maxScrollY-o.pagesY[o.pagesY.length-1]+o.pagesY[o.pagesY.length-1])}o._scrollbar("h"),o._scrollbar("v"),o.zoomed||(o.scroller.style[d]="0",o._resetPos(400))},scrollTo:function(e,t,i,r){var o,n,s=this,a=e;for(s.stop(),a.length||(a=[{x:e,y:t,time:i,relative:r}]),o=0,n=a.length;n>o;o++)a[o].relative&&(a[o].x=s.x-a[o].x,a[o].y=s.y-a[o].y),s.steps.push({x:a[o].x,y:a[o].y,time:a[o].time||0});s._startAni()},scrollToElement:function(e,t){var i,r=this;e=e.nodeType?e:r.scroller.querySelector(e),e&&(i=r._offset(e),i.left+=r.wrapperOffsetLeft,i.top+=r.wrapperOffsetTop,i.left=i.left>0?0:i.left<r.maxScrollX?r.maxScrollX:i.left,i.top=i.top>r.minScrollY?r.minScrollY:i.top<r.maxScrollY?r.maxScrollY:i.top,t=void 0===t?n.max(2*n.abs(i.left),2*n.abs(i.top)):t,r.scrollTo(i.left,i.top,t))},scrollToPage:function(e,t,i){var r,o,n=this;i=void 0===i?400:i,n.options.onScrollStart&&n.options.onScrollStart.call(n),n.options.snap?(e="next"==e?n.currPageX+1:"prev"==e?n.currPageX-1:e,t="next"==t?n.currPageY+1:"prev"==t?n.currPageY-1:t,e=0>e?0:e>n.pagesX.length-1?n.pagesX.length-1:e,t=0>t?0:t>n.pagesY.length-1?n.pagesY.length-1:t,n.currPageX=e,n.currPageY=t,r=n.pagesX[e],o=n.pagesY[t]):(r=-n.wrapperW*e,o=-n.wrapperH*t,n.maxScrollX>r&&(r=n.maxScrollX),n.maxScrollY>o&&(o=n.maxScrollY)),n.scrollTo(r,o,i)},disable:function(){this.stop(),this._resetPos(0),this.enabled=!1,this._unbind(R,e),this._unbind(k,e),this._unbind(q,e)},enable:function(){this.enabled=!0},stop:function(){this.options.useTransition?this._unbind(_):L(this.aniTime),this.steps=[],this.moved=!1,this.animating=!1},zoom:function(e,t,i,r){var o=this,n=i/o.scale;o.options.useTransform&&(o.zoomed=!0,r=void 0===r?200:r,e=e-o.wrapperOffsetLeft-o.x,t=t-o.wrapperOffsetTop-o.y,o.x=e-e*n+o.x,o.y=t-t*n+o.y,o.scale=i,o.refresh(),o.x=o.x>0?0:o.x<o.maxScrollX?o.maxScrollX:o.x,o.y=o.y>o.minScrollY?o.minScrollY:o.y<o.maxScrollY?o.maxScrollY:o.y,o.scroller.style[d]=r+"ms",o.scroller.style[l]="translate("+o.x+"px,"+o.y+"px) scale("+i+")"+T,o.zoomed=!1)},isReady:function(){return!this.moved&&!this.zoomed&&!this.animating}},s=null,t!==void 0?t.iScroll=A:e.iScroll=A,i.exports=A})(window,document)});