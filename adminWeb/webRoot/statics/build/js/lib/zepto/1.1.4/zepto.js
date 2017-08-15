/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("lib/zepto/1.1.4/zepto",[],function(){var e=function(){function e(e){return null==e?e+"":U[Y.call(e)]||"object"}function t(t){return"function"==e(t)}function n(e){return null!=e&&e==e.window}function i(e){return null!=e&&e.nodeType==e.DOCUMENT_NODE}function r(t){return"object"==e(t)}function o(e){return r(e)&&!n(e)&&Object.getPrototypeOf(e)==Object.prototype}function s(e){return"number"==typeof e.length}function a(e){return R.call(e,function(e){return null!=e})}function c(e){return e.length>0?k.fn.concat.apply([],e):e}function u(e){return e.replace(/::/g,"/").replace(/([A-Z]+)([A-Z][a-z])/g,"$1_$2").replace(/([a-z\d])([A-Z])/g,"$1_$2").replace(/_/g,"-").toLowerCase()}function l(e){return e in N?N[e]:N[e]=RegExp("(^|\\s)"+e+"(\\s|$)")}function f(e,t){return"number"!=typeof t||L[u(e)]?t:t+"px"}function p(e){var t,n;return A[e]||(t=q.createElement(e),q.body.appendChild(t),n=getComputedStyle(t,"").getPropertyValue("display"),t.parentNode.removeChild(t),"none"==n&&(n="block"),A[e]=n),A[e]}function d(e){return"children"in e?_.call(e.children):k.map(e.childNodes,function(e){return 1==e.nodeType?e:w})}function h(e,t,n){for(S in t)n&&(o(t[S])||Z(t[S]))?(o(t[S])&&!o(e[S])&&(e[S]={}),Z(t[S])&&!Z(e[S])&&(e[S]=[]),h(e[S],t[S],n)):t[S]!==w&&(e[S]=t[S])}function m(e,t){return null==t?k(e):k(e).filter(t)}function g(e,n,i,r){return t(n)?n.call(e,i,r):n}function v(e,t,n){null==n?e.removeAttribute(t):e.setAttribute(t,n)}function y(e,t){var n=e.className,i=n&&n.baseVal!==w;return t===w?i?n.baseVal:n:(i?n.baseVal=t:e.className=t,w)}function b(e){var t;try{return e?"true"==e||("false"==e?!1:"null"==e?null:/^0/.test(e)||isNaN(t=Number(e))?/^[\[\{]/.test(e)?k.parseJSON(e):e:t):e}catch(n){return e}}function x(e,t){t(e);for(var n=0,i=e.childNodes.length;i>n;n++)x(e.childNodes[n],t)}var w,S,k,T,C,E,P=[],_=P.slice,R=P.filter,q=window.document,A={},N={},L={"column-count":1,columns:1,"font-weight":1,"line-height":1,opacity:1,"z-index":1,zoom:1},D=/^\s*<(\w+|!)[^>]*>/,j=/^<(\w+)\s*\/?>(?:<\/\1>|)$/,O=/<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,M=/^(?:body|html)$/i,$=/([A-Z])/g,I=["val","css","html","text","data","width","height","offset"],W=["after","prepend","before","append"],F=q.createElement("table"),H=q.createElement("tr"),B={tr:q.createElement("tbody"),tbody:F,thead:F,tfoot:F,td:H,th:H,"*":q.createElement("div")},z=/complete|loaded|interactive/,X=/^[\w-]*$/,U={},Y=U.toString,J={},G=q.createElement("div"),V={tabindex:"tabIndex",readonly:"readOnly","for":"htmlFor","class":"className",maxlength:"maxLength",cellspacing:"cellSpacing",cellpadding:"cellPadding",rowspan:"rowSpan",colspan:"colSpan",usemap:"useMap",frameborder:"frameBorder",contenteditable:"contentEditable"},Z=Array.isArray||function(e){return e instanceof Array};return J.matches=function(e,t){if(!t||!e||1!==e.nodeType)return!1;var n=e.webkitMatchesSelector||e.mozMatchesSelector||e.oMatchesSelector||e.matchesSelector;if(n)return n.call(e,t);var i,r=e.parentNode,o=!r;return o&&(r=G).appendChild(e),i=~J.qsa(r,t).indexOf(e),o&&G.removeChild(e),i},C=function(e){return e.replace(/-+(.)?/g,function(e,t){return t?t.toUpperCase():""})},E=function(e){return R.call(e,function(t,n){return e.indexOf(t)==n})},J.fragment=function(e,t,n){var i,r,s;return j.test(e)&&(i=k(q.createElement(RegExp.$1))),i||(e.replace&&(e=e.replace(O,"<$1></$2>")),t===w&&(t=D.test(e)&&RegExp.$1),t in B||(t="*"),s=B[t],s.innerHTML=""+e,i=k.each(_.call(s.childNodes),function(){s.removeChild(this)})),o(n)&&(r=k(i),k.each(n,function(e,t){I.indexOf(e)>-1?r[e](t):r.attr(e,t)})),i},J.Z=function(e,t){return e=e||[],e.__proto__=k.fn,e.selector=t||"",e},J.isZ=function(e){return e instanceof J.Z},J.init=function(e,n){var i;if(!e)return J.Z();if("string"==typeof e)if(e=e.trim(),"<"==e[0]&&D.test(e))i=J.fragment(e,RegExp.$1,n),e=null;else{if(n!==w)return k(n).find(e);i=J.qsa(q,e)}else{if(t(e))return k(q).ready(e);if(J.isZ(e))return e;if(Z(e))i=a(e);else if(r(e))i=[e],e=null;else if(D.test(e))i=J.fragment(e.trim(),RegExp.$1,n),e=null;else{if(n!==w)return k(n).find(e);i=J.qsa(q,e)}}return J.Z(i,e)},k=function(e,t){return J.init(e,t)},k.extend=function(e){var t,n=_.call(arguments,1);return"boolean"==typeof e&&(t=e,e=n.shift()),n.forEach(function(n){h(e,n,t)}),e},J.qsa=function(e,t){var n,r="#"==t[0],o=!r&&"."==t[0],s=r||o?t.slice(1):t,a=X.test(s);return i(e)&&a&&r?(n=e.getElementById(s))?[n]:[]:1!==e.nodeType&&9!==e.nodeType?[]:_.call(a&&!r?o?e.getElementsByClassName(s):e.getElementsByTagName(t):e.querySelectorAll(t))},k.contains=q.documentElement.contains?function(e,t){return e!==t&&e.contains(t)}:function(e,t){for(;t&&(t=t.parentNode);)if(t===e)return!0;return!1},k.type=e,k.isFunction=t,k.isWindow=n,k.isArray=Z,k.isPlainObject=o,k.isEmptyObject=function(e){var t;for(t in e)return!1;return!0},k.inArray=function(e,t,n){return P.indexOf.call(t,e,n)},k.camelCase=C,k.trim=function(e){return null==e?"":String.prototype.trim.call(e)},k.uuid=0,k.support={},k.expr={},k.map=function(e,t){var n,i,r,o=[];if(s(e))for(i=0;e.length>i;i++)n=t(e[i],i),null!=n&&o.push(n);else for(r in e)n=t(e[r],r),null!=n&&o.push(n);return c(o)},k.each=function(e,t){var n,i;if(s(e)){for(n=0;e.length>n;n++)if(t.call(e[n],n,e[n])===!1)return e}else for(i in e)if(t.call(e[i],i,e[i])===!1)return e;return e},k.grep=function(e,t){return R.call(e,t)},window.JSON&&(k.parseJSON=JSON.parse),k.each("Boolean Number String Function Array Date RegExp Object Error".split(" "),function(e,t){U["[object "+t+"]"]=t.toLowerCase()}),k.fn={forEach:P.forEach,reduce:P.reduce,push:P.push,sort:P.sort,indexOf:P.indexOf,concat:P.concat,map:function(e){return k(k.map(this,function(t,n){return e.call(t,n,t)}))},slice:function(){return k(_.apply(this,arguments))},ready:function(e){return z.test(q.readyState)&&q.body?e(k):q.addEventListener("DOMContentLoaded",function(){e(k)},!1),this},get:function(e){return e===w?_.call(this):this[e>=0?e:e+this.length]},toArray:function(){return this.get()},size:function(){return this.length},remove:function(){return this.each(function(){null!=this.parentNode&&this.parentNode.removeChild(this)})},each:function(e){return P.every.call(this,function(t,n){return e.call(t,n,t)!==!1}),this},filter:function(e){return t(e)?this.not(this.not(e)):k(R.call(this,function(t){return J.matches(t,e)}))},add:function(e,t){return k(E(this.concat(k(e,t))))},is:function(e){return this.length>0&&J.matches(this[0],e)},not:function(e){var n=[];if(t(e)&&e.call!==w)this.each(function(t){e.call(this,t)||n.push(this)});else{var i="string"==typeof e?this.filter(e):s(e)&&t(e.item)?_.call(e):k(e);this.forEach(function(e){0>i.indexOf(e)&&n.push(e)})}return k(n)},has:function(e){return this.filter(function(){return r(e)?k.contains(this,e):k(this).find(e).size()})},eq:function(e){return-1===e?this.slice(e):this.slice(e,+e+1)},first:function(){var e=this[0];return e&&!r(e)?e:k(e)},last:function(){var e=this[this.length-1];return e&&!r(e)?e:k(e)},find:function(e){var t,n=this;return t=e?"object"==typeof e?k(e).filter(function(){var e=this;return P.some.call(n,function(t){return k.contains(t,e)})}):1==this.length?k(J.qsa(this[0],e)):this.map(function(){return J.qsa(this,e)}):[]},closest:function(e,t){var n=this[0],r=!1;for("object"==typeof e&&(r=k(e));n&&!(r?r.indexOf(n)>=0:J.matches(n,e));)n=n!==t&&!i(n)&&n.parentNode;return k(n)},parents:function(e){for(var t=[],n=this;n.length>0;)n=k.map(n,function(e){return(e=e.parentNode)&&!i(e)&&0>t.indexOf(e)?(t.push(e),e):w});return m(t,e)},parent:function(e){return m(E(this.pluck("parentNode")),e)},children:function(e){return m(this.map(function(){return d(this)}),e)},contents:function(){return this.map(function(){return _.call(this.childNodes)})},siblings:function(e){return m(this.map(function(e,t){return R.call(d(t.parentNode),function(e){return e!==t})}),e)},empty:function(){return this.each(function(){this.innerHTML=""})},pluck:function(e){return k.map(this,function(t){return t[e]})},show:function(){return this.each(function(){"none"==this.style.display&&(this.style.display=""),"none"==getComputedStyle(this,"").getPropertyValue("display")&&(this.style.display=p(this.nodeName))})},replaceWith:function(e){return this.before(e).remove()},wrap:function(e){var n=t(e);if(this[0]&&!n)var i=k(e).get(0),r=i.parentNode||this.length>1;return this.each(function(t){k(this).wrapAll(n?e.call(this,t):r?i.cloneNode(!0):i)})},wrapAll:function(e){if(this[0]){k(this[0]).before(e=k(e));for(var t;(t=e.children()).length;)e=t.first();k(e).append(this)}return this},wrapInner:function(e){var n=t(e);return this.each(function(t){var i=k(this),r=i.contents(),o=n?e.call(this,t):e;r.length?r.wrapAll(o):i.append(o)})},unwrap:function(){return this.parent().each(function(){k(this).replaceWith(k(this).children())}),this},clone:function(){return this.map(function(){return this.cloneNode(!0)})},hide:function(){return this.css("display","none")},toggle:function(e){return this.each(function(){var t=k(this);(e===w?"none"==t.css("display"):e)?t.show():t.hide()})},prev:function(e){return k(this.pluck("previousElementSibling")).filter(e||"*")},next:function(e){return k(this.pluck("nextElementSibling")).filter(e||"*")},html:function(e){return 0 in arguments?this.each(function(t){var n=this.innerHTML;k(this).empty().append(g(this,e,t,n))}):0 in this?this[0].innerHTML:null},text:function(e){return 0 in arguments?this.each(function(t){var n=g(this,e,t,this.textContent);this.textContent=null==n?"":""+n}):0 in this?this[0].textContent:null},attr:function(e,t){var n;return"string"!=typeof e||1 in arguments?this.each(function(n){if(1===this.nodeType)if(r(e))for(S in e)v(this,S,e[S]);else v(this,e,g(this,t,n,this.getAttribute(e)))}):this.length&&1===this[0].nodeType?!(n=this[0].getAttribute(e))&&e in this[0]?this[0][e]:n:w},removeAttr:function(e){return this.each(function(){1===this.nodeType&&v(this,e)})},prop:function(e,t){return e=V[e]||e,1 in arguments?this.each(function(n){this[e]=g(this,t,n,this[e])}):this[0]&&this[0][e]},data:function(e,t){var n="data-"+e.replace($,"-$1").toLowerCase(),i=1 in arguments?this.attr(n,t):this.attr(n);return null!==i?b(i):w},val:function(e){return 0 in arguments?this.each(function(t){this.value=g(this,e,t,this.value)}):this[0]&&(this[0].multiple?k(this[0]).find("option").filter(function(){return this.selected}).pluck("value"):this[0].value)},offset:function(e){if(e)return this.each(function(t){var n=k(this),i=g(this,e,t,n.offset()),r=n.offsetParent().offset(),o={top:i.top-r.top,left:i.left-r.left};"static"==n.css("position")&&(o.position="relative"),n.css(o)});if(!this.length)return null;var t=this[0].getBoundingClientRect();return{left:t.left+window.pageXOffset,top:t.top+window.pageYOffset,width:Math.round(t.width),height:Math.round(t.height)}},css:function(t,n){if(2>arguments.length){var i=this[0],r=getComputedStyle(i,"");if(!i)return;if("string"==typeof t)return i.style[C(t)]||r.getPropertyValue(t);if(Z(t)){var o={};return k.each(Z(t)?t:[t],function(e,t){o[t]=i.style[C(t)]||r.getPropertyValue(t)}),o}}var s="";if("string"==e(t))n||0===n?s=u(t)+":"+f(t,n):this.each(function(){this.style.removeProperty(u(t))});else for(S in t)t[S]||0===t[S]?s+=u(S)+":"+f(S,t[S])+";":this.each(function(){this.style.removeProperty(u(S))});return this.each(function(){this.style.cssText+=";"+s})},index:function(e){return e?this.indexOf(k(e)[0]):this.parent().children().indexOf(this[0])},hasClass:function(e){return e?P.some.call(this,function(e){return this.test(y(e))},l(e)):!1},addClass:function(e){return e?this.each(function(t){T=[];var n=y(this),i=g(this,e,t,n);i.split(/\s+/g).forEach(function(e){k(this).hasClass(e)||T.push(e)},this),T.length&&y(this,n+(n?" ":"")+T.join(" "))}):this},removeClass:function(e){return this.each(function(t){return e===w?y(this,""):(T=y(this),g(this,e,t,T).split(/\s+/g).forEach(function(e){T=T.replace(l(e)," ")}),y(this,T.trim()),w)})},toggleClass:function(e,t){return e?this.each(function(n){var i=k(this),r=g(this,e,n,y(this));r.split(/\s+/g).forEach(function(e){(t===w?!i.hasClass(e):t)?i.addClass(e):i.removeClass(e)})}):this},scrollTop:function(e){if(this.length){var t="scrollTop"in this[0];return e===w?t?this[0].scrollTop:this[0].pageYOffset:this.each(t?function(){this.scrollTop=e}:function(){this.scrollTo(this.scrollX,e)})}},scrollLeft:function(e){if(this.length){var t="scrollLeft"in this[0];return e===w?t?this[0].scrollLeft:this[0].pageXOffset:this.each(t?function(){this.scrollLeft=e}:function(){this.scrollTo(e,this.scrollY)})}},position:function(){if(this.length){var e=this[0],t=this.offsetParent(),n=this.offset(),i=M.test(t[0].nodeName)?{top:0,left:0}:t.offset();return n.top-=parseFloat(k(e).css("margin-top"))||0,n.left-=parseFloat(k(e).css("margin-left"))||0,i.top+=parseFloat(k(t[0]).css("border-top-width"))||0,i.left+=parseFloat(k(t[0]).css("border-left-width"))||0,{top:n.top-i.top,left:n.left-i.left}}},offsetParent:function(){return this.map(function(){for(var e=this.offsetParent||q.body;e&&!M.test(e.nodeName)&&"static"==k(e).css("position");)e=e.offsetParent;return e})}},k.fn.detach=k.fn.remove,["width","height"].forEach(function(e){var t=e.replace(/./,function(e){return e[0].toUpperCase()});k.fn[e]=function(r){var o,s=this[0];return r===w?n(s)?s["inner"+t]:i(s)?s.documentElement["scroll"+t]:(o=this.offset())&&o[e]:this.each(function(t){s=k(this),s.css(e,g(this,r,t,s[e]()))})}}),W.forEach(function(t,n){var i=n%2;k.fn[t]=function(){var t,r,o=k.map(arguments,function(n){return t=e(n),"object"==t||"array"==t||null==n?n:J.fragment(n)}),s=this.length>1;return 1>o.length?this:this.each(function(e,t){r=i?t:t.parentNode,t=0==n?t.nextSibling:1==n?t.firstChild:2==n?t:null;var a=k.contains(q.documentElement,r);o.forEach(function(e){if(s)e=e.cloneNode(!0);else if(!r)return k(e).remove();r.insertBefore(e,t),a&&x(e,function(e){null==e.nodeName||"SCRIPT"!==e.nodeName.toUpperCase()||e.type&&"text/javascript"!==e.type||e.src||window.eval.call(window,e.innerHTML)})})})},k.fn[i?t+"To":"insert"+(n?"Before":"After")]=function(e){return k(e)[t](this),this}}),J.Z.prototype=k.fn,J.uniq=E,J.deserializeValue=b,k.zepto=J,k}();window.Zepto=e,void 0===window.$&&(window.$=e),function(e){function t(e){return e._zid||(e._zid=p++)}function n(e,n,o,s){if(n=i(n),n.ns)var a=r(n.ns);return(g[t(e)]||[]).filter(function(e){return!(!e||n.e&&e.e!=n.e||n.ns&&!a.test(e.ns)||o&&t(e.fn)!==t(o)||s&&e.sel!=s)})}function i(e){var t=(""+e).split(".");return{e:t[0],ns:t.slice(1).sort().join(" ")}}function r(e){return RegExp("(?:^| )"+e.replace(" "," .* ?")+"(?: |$)")}function o(e,t){return e.del&&!y&&e.e in b||!!t}function s(e){return x[e]||y&&b[e]||e}function a(n,r,a,c,l,p,d){var h=t(n),m=g[h]||(g[h]=[]);r.split(/\s/).forEach(function(t){if("ready"==t)return e(document).ready(a);var r=i(t);r.fn=a,r.sel=l,r.e in x&&(a=function(t){var n=t.relatedTarget;return!n||n!==this&&!e.contains(this,n)?r.fn.apply(this,arguments):f}),r.del=p;var h=p||a;r.proxy=function(e){if(e=u(e),!e.isImmediatePropagationStopped()){e.data=c;var t=h.apply(n,e._args==f?[e]:[e].concat(e._args));return t===!1&&(e.preventDefault(),e.stopPropagation()),t}},r.i=m.length,m.push(r),"addEventListener"in n&&n.addEventListener(s(r.e),r.proxy,o(r,d))})}function c(e,i,r,a,c){var u=t(e);(i||"").split(/\s/).forEach(function(t){n(e,t,r,a).forEach(function(t){delete g[u][t.i],"removeEventListener"in e&&e.removeEventListener(s(t.e),t.proxy,o(t,c))})})}function u(t,n){return(n||!t.isDefaultPrevented)&&(n||(n=t),e.each(T,function(e,i){var r=n[e];t[e]=function(){return this[i]=w,r&&r.apply(n,arguments)},t[i]=S}),(n.defaultPrevented!==f?n.defaultPrevented:"returnValue"in n?n.returnValue===!1:n.getPreventDefault&&n.getPreventDefault())&&(t.isDefaultPrevented=w)),t}function l(e){var t,n={originalEvent:e};for(t in e)k.test(t)||e[t]===f||(n[t]=e[t]);return u(n,e)}var f,p=1,d=Array.prototype.slice,h=e.isFunction,m=function(e){return"string"==typeof e},g={},v={},y="onfocusin"in window,b={focus:"focusin",blur:"focusout"},x={mouseenter:"mouseover",mouseleave:"mouseout"};v.click=v.mousedown=v.mouseup=v.mousemove="MouseEvents",e.event={add:a,remove:c},e.proxy=function(n,i){var r=2 in arguments&&d.call(arguments,2);if(h(n)){var o=function(){return n.apply(i,r?r.concat(d.call(arguments)):arguments)};return o._zid=t(n),o}if(m(i))return r?(r.unshift(n[i],n),e.proxy.apply(null,r)):e.proxy(n[i],n);throw new TypeError("expected function")},e.fn.bind=function(e,t,n){return this.on(e,t,n)},e.fn.unbind=function(e,t){return this.off(e,t)},e.fn.one=function(e,t,n,i){return this.on(e,t,n,i,1)};var w=function(){return!0},S=function(){return!1},k=/^([A-Z]|returnValue$|layer[XY]$)/,T={preventDefault:"isDefaultPrevented",stopImmediatePropagation:"isImmediatePropagationStopped",stopPropagation:"isPropagationStopped"};e.fn.delegate=function(e,t,n){return this.on(t,e,n)},e.fn.undelegate=function(e,t,n){return this.off(t,e,n)},e.fn.live=function(t,n){return e(document.body).delegate(this.selector,t,n),this},e.fn.die=function(t,n){return e(document.body).undelegate(this.selector,t,n),this},e.fn.on=function(t,n,i,r,o){var s,u,p=this;return t&&!m(t)?(e.each(t,function(e,t){p.on(e,n,i,t,o)}),p):(m(n)||h(r)||r===!1||(r=i,i=n,n=f),(h(i)||i===!1)&&(r=i,i=f),r===!1&&(r=S),p.each(function(p,h){o&&(s=function(e){return c(h,e.type,r),r.apply(this,arguments)}),n&&(u=function(t){var i,o=e(t.target).closest(n,h).get(0);return o&&o!==h?(i=e.extend(l(t),{currentTarget:o,liveFired:h}),(s||r).apply(o,[i].concat(d.call(arguments,1)))):f}),a(h,t,r,i,n,u||s)}))},e.fn.off=function(t,n,i){var r=this;return t&&!m(t)?(e.each(t,function(e,t){r.off(e,n,t)}),r):(m(n)||h(i)||i===!1||(i=n,n=f),i===!1&&(i=S),r.each(function(){c(this,t,i,n)}))},e.fn.trigger=function(t,n){return t=m(t)||e.isPlainObject(t)?e.Event(t):u(t),t._args=n,this.each(function(){"dispatchEvent"in this?this.dispatchEvent(t):e(this).triggerHandler(t,n)})},e.fn.triggerHandler=function(t,i){var r,o;return this.each(function(s,a){r=l(m(t)?e.Event(t):t),r._args=i,r.target=a,e.each(n(a,t.type||t),function(e,t){return o=t.proxy(r),r.isImmediatePropagationStopped()?!1:f})}),o},"focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select keydown keypress keyup error".split(" ").forEach(function(t){e.fn[t]=function(e){return e?this.bind(t,e):this.trigger(t)}}),["focus","blur"].forEach(function(t){e.fn[t]=function(e){return e?this.bind(t,e):this.each(function(){try{this[t]()}catch(e){}}),this}}),e.Event=function(e,t){m(e)||(t=e,e=t.type);var n=document.createEvent(v[e]||"Events"),i=!0;if(t)for(var r in t)"bubbles"==r?i=!!t[r]:n[r]=t[r];return n.initEvent(e,i,!0),u(n)}}(e),function(e){function t(t,n,i){var r=e.Event(n);return e(t).trigger(r,i),!r.isDefaultPrevented()}function n(e,n,i,r){return e.global?t(n||y,i,r):void 0}function i(t){t.global&&0===e.active++&&n(t,null,"ajaxStart")}function r(t){t.global&&!--e.active&&n(t,null,"ajaxStop")}function o(e,t){var i=t.context;return t.beforeSend.call(i,e,t)===!1||n(t,i,"ajaxBeforeSend",[e,t])===!1?!1:(n(t,i,"ajaxSend",[e,t]),void 0)}function s(e,t,i,r){var o=i.context,s="success";i.success.call(o,e,s,t),r&&r.resolveWith(o,[e,s,t]),n(i,o,"ajaxSuccess",[t,i,e]),c(s,t,i)}function a(e,t,i,r,o){var s=r.context;r.error.call(s,i,t,e),o&&o.rejectWith(s,[i,t,e]),n(r,s,"ajaxError",[i,r,e||t]),c(t,i,r)}function c(e,t,i){var o=i.context;i.complete.call(o,t,e),n(i,o,"ajaxComplete",[t,i]),r(i)}function u(){}function l(e){return e&&(e=e.split(";",2)[0]),e&&(e==k?"html":e==S?"json":x.test(e)?"script":w.test(e)&&"xml")||"text"}function f(e,t){return""==t?e:(e+"&"+t).replace(/[&?]{1,2}/,"?")}function p(t){t.processData&&t.data&&"string"!=e.type(t.data)&&(t.data=e.param(t.data,t.traditional)),!t.data||t.type&&"GET"!=t.type.toUpperCase()||(t.url=f(t.url,t.data),t.data=void 0)}function d(t,n,i,r){return e.isFunction(n)&&(r=i,i=n,n=void 0),e.isFunction(i)||(r=i,i=void 0),{url:t,data:n,success:i,dataType:r}}function h(t,n,i,r){var o,s=e.isArray(n),a=e.isPlainObject(n);e.each(n,function(n,c){o=e.type(c),r&&(n=i?r:r+"["+(a||"object"==o||"array"==o?n:"")+"]"),!r&&s?t.add(c.name,c.value):"array"==o||!i&&"object"==o?h(t,c,i,n):t.add(n,c)})}var m,g,v=0,y=window.document,b=/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,x=/^(?:text|application)\/javascript/i,w=/^(?:text|application)\/xml/i,S="application/json",k="text/html",T=/^\s*$/;e.active=0,e.ajaxJSONP=function(t,n){if(!("type"in t))return e.ajax(t);var i,r,c=t.jsonpCallback,u=(e.isFunction(c)?c():c)||"jsonp"+ ++v,l=y.createElement("script"),f=window[u],p=function(t){e(l).triggerHandler("error",t||"abort")},d={abort:p};return n&&n.promise(d),e(l).on("load error",function(o,c){clearTimeout(r),e(l).off().remove(),"error"!=o.type&&i?s(i[0],d,t,n):a(null,c||"error",d,t,n),window[u]=f,i&&e.isFunction(f)&&f(i[0]),f=i=void 0}),o(d,t)===!1?(p("abort"),d):(window[u]=function(){i=arguments},l.src=t.url.replace(/\?(.+)=\?/,"?$1="+u),y.head.appendChild(l),t.timeout>0&&(r=setTimeout(function(){p("timeout")},t.timeout)),d)},e.ajaxSettings={type:"GET",beforeSend:u,success:u,error:u,complete:u,context:null,global:!0,xhr:function(){return new window.XMLHttpRequest},accepts:{script:"text/javascript, application/javascript, application/x-javascript",json:S,xml:"application/xml, text/xml",html:k,text:"text/plain"},crossDomain:!1,timeout:0,processData:!0,cache:!0},e.ajax=function(t){var n=e.extend({},t||{}),r=e.Deferred&&e.Deferred();for(m in e.ajaxSettings)void 0===n[m]&&(n[m]=e.ajaxSettings[m]);i(n),n.crossDomain||(n.crossDomain=/^([\w-]+:)?\/\/([^\/]+)/.test(n.url)&&RegExp.$2!=window.location.host),n.url||(n.url=""+window.location),p(n);var c=n.dataType,d=/\?.+=\?/.test(n.url);if(d&&(c="jsonp"),n.cache!==!1&&(t&&t.cache===!0||"script"!=c&&"jsonp"!=c)||(n.url=f(n.url,"_="+Date.now())),"jsonp"==c)return d||(n.url=f(n.url,n.jsonp?n.jsonp+"=?":n.jsonp===!1?"":"callback=?")),e.ajaxJSONP(n,r);var h,v=n.accepts[c],y={},b=function(e,t){y[e.toLowerCase()]=[e,t]},x=/^([\w-]+:)\/\//.test(n.url)?RegExp.$1:window.location.protocol,w=n.xhr(),S=w.setRequestHeader;if(r&&r.promise(w),n.crossDomain||b("X-Requested-With","XMLHttpRequest"),b("Accept",v||"*/*"),(v=n.mimeType||v)&&(v.indexOf(",")>-1&&(v=v.split(",",2)[0]),w.overrideMimeType&&w.overrideMimeType(v)),(n.contentType||n.contentType!==!1&&n.data&&"GET"!=n.type.toUpperCase())&&b("Content-Type",n.contentType||"application/x-www-form-urlencoded"),n.headers)for(g in n.headers)b(g,n.headers[g]);if(w.setRequestHeader=b,w.onreadystatechange=function(){if(4==w.readyState){w.onreadystatechange=u,clearTimeout(h);var t,i=!1;if(w.status>=200&&300>w.status||304==w.status||0==w.status&&"file:"==x){c=c||l(n.mimeType||w.getResponseHeader("content-type")),t=w.responseText;try{"script"==c?(1,eval)(t):"xml"==c?t=w.responseXML:"json"==c&&(t=T.test(t)?null:e.parseJSON(t))}catch(o){i=o}i?a(i,"parsererror",w,n,r):s(t,w,n,r)}else a(w.statusText||null,w.status?"error":"abort",w,n,r)}},o(w,n)===!1)return w.abort(),a(null,"abort",w,n,r),w;if(n.xhrFields)for(g in n.xhrFields)w[g]=n.xhrFields[g];var k="async"in n?n.async:!0;w.open(n.type,n.url,k,n.username,n.password);for(g in y)S.apply(w,y[g]);return n.timeout>0&&(h=setTimeout(function(){w.onreadystatechange=u,w.abort(),a(null,"timeout",w,n,r)},n.timeout)),w.send(n.data?n.data:null),w},e.get=function(){return e.ajax(d.apply(null,arguments))},e.post=function(){var t=d.apply(null,arguments);return t.type="POST",e.ajax(t)},e.getJSON=function(){var t=d.apply(null,arguments);return t.dataType="json",e.ajax(t)},e.fn.load=function(t,n,i){if(!this.length)return this;var r,o=this,s=t.split(/\s/),a=d(t,n,i),c=a.success;return s.length>1&&(a.url=s[0],r=s[1]),a.success=function(t){o.html(r?e("<div>").html(t.replace(b,"")).find(r):t),c&&c.apply(o,arguments)},e.ajax(a),this};var C=encodeURIComponent;e.param=function(e,t){var n=[];return n.add=function(e,t){this.push(C(e)+"="+C(t))},h(n,e,t),n.join("&").replace(/%20/g,"+")}}(e),function(e){e.fn.serializeArray=function(){var t,n=[];return e([].slice.call(this.get(0).elements)).each(function(){t=e(this);var i=t.attr("type");"fieldset"!=this.nodeName.toLowerCase()&&!this.disabled&&"submit"!=i&&"reset"!=i&&"button"!=i&&("radio"!=i&&"checkbox"!=i||this.checked)&&n.push({name:t.attr("name"),value:t.val()})}),n},e.fn.serialize=function(){var e=[];return this.serializeArray().forEach(function(t){e.push(encodeURIComponent(t.name)+"="+encodeURIComponent(t.value))}),e.join("&")},e.fn.submit=function(t){if(t)this.bind("submit",t);else if(this.length){var n=e.Event("submit");this.eq(0).trigger(n),n.isDefaultPrevented()||this.get(0).submit()}return this}}(e),function(e){"__proto__"in{}||e.extend(e.zepto,{Z:function(t,n){return t=t||[],e.extend(t,e.fn),t.selector=n||"",t.__Z=!0,t},isZ:function(t){return"array"===e.type(t)&&"__Z"in t}});try{getComputedStyle(void 0)}catch(t){var n=getComputedStyle;window.getComputedStyle=function(e){try{return n(e)}catch(t){return null}}}}(e)});