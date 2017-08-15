/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("widget/dom/fenye",["../../lib/underscore/underscore","../../lib/backbone/backbone"],function(e,t,r){function i(e,t,r,i,n,o,a,s,c,l){r=r||{},i=i||"curpage",this.param=r,this.url=e,this.tpl=t,this.cb=n||null,a=a||$("[data-ele = data-wrap]"),l=l||$("[sc = fenye-wrap]"),this.firstLoad=!0;var u=this,c=c||6;this.btnClass="active",o=o||'<a href="#" class="btn btn-default" sc="pagebtn"></a>',l.hide();var d=Backbone.Model.extend({url:this.url,initialize:function(){var e=this;this.on("change:p",function(t,r){e.getData(r,u.param)}),this.on("change:rnd",function(t,r,i){e.getData(1,i)})},getData:function(e,t){t[i]=e,this.save(null,{data:t})}}),h=Backbone.View.extend({el:$(document),oWrap:a,oView:$("[sc = num]"),max:0,page:window.location.hash.split("#")[1]||1,first:!0,events:{"click [sc = page-prev]":"prev","click [sc = page-next]":"next","click [sc = first]":"home","click [sc = last]":"last","click [sc = pagebtn]":"clickBtn"},initialize:function(){this.listenTo(this.model,"change:data",this.show),this.oPrev=$("[sc = page-prev]"),this.oFirst=$("[sc = first]"),this.oNext=$("[sc = page-next]"),this.oLast=$("[sc = last]")},show:function(){var e;e=this.model.toJSON(),e=e.data,e&&(s&&(e=s(e)),this.max=Math.ceil(e.total/u.param.pagesize),this.render(e),this.showPage(this.page,this.max),l.show(),this.max&&1!=this.max||l.hide(),u.cb&&u.cb(e))},showPage:function(e,t){this.oBtnWrap=$("[sc = num]");var r,i,n,a,s,l,d,h,f;if(i=t,h=Math.floor(c/2),this.oBtnWrap.html(""),c>=t)for(r=0;i>r;r++)n=$(o),n.html(r+1),r+1==e&&n.addClass(u.btnClass),this.oBtnWrap.append(n);else{for(s=parseInt(e)+h>=t?t:parseInt(e)+h,l=1>=parseInt(e)-h?1:parseInt(e)-h,f=parseInt(e)+h,r=l;e>r;r++)n=$(o),n.html(r),this.oBtnWrap.append(n);for(r=e;s>=r;r++)n=$(o),n.html(r),r==e&&n.addClass(u.btnClass),this.oBtnWrap.append(n);t>f&&(d=$("<span>......</span>"),a=$(o),a.html(t),this.oBtnWrap.append(d),this.oBtnWrap.append(a))}1==e?(this.oPrev.addClass("end"),this.oFirst.addClass("end")):(this.oPrev.removeClass("end"),this.oFirst.removeClass("end")),e==t?(this.oNext.addClass("end"),this.oLast.addClass("end")):(this.oNext.removeClass("end"),this.oLast.removeClass("end"))},render:function(e){u.firstLoad&&(u.firstLoad=!1,l.show());var t=u.tpl(e);this.oWrap.html(t)},changeParam:function(e){var t=Math.random();this.model.set("rnd",t,e)},change:function(e){this.model.set("p",e)},home:function(){this.change(1),this.page=1,g.navigate(""+this.page)},last:function(){this.change(this.max),this.page=this.max,g.navigate(""+this.page)},prev:function(){return this.page--,1>this.page?(this.page=1,void 0):(this.change(this.page),g.navigate(""+this.page),void 0)},next:function(){return this.page++,this.page>this.max?(this.page=this.max,void 0):(this.change(this.page),g.navigate(""+this.page),void 0)},clickBtn:function(e){var t,r;t=$(e.target),r=t.html(),this.change(r),this.page=r,g.navigate(""+this.page)}}),f=Backbone.Router.extend({routes:{"":"index",":p":"page"},index:function(){m.change(1)},page:function(e){m.page=e,m.change(e)}}),p=new d,m=new h({model:p});m.change(1);var g=new f;this.V=m,this.M=p,this.R=g}e("../../lib/underscore/underscore"),e("../../lib/backbone/backbone"),i.prototype={refresh:function(e,t,r){this.param=e,t&&(this.url=t),r&&(this.tpl=r),this.M.url=this.url,this.V.changeParam(e),this.R.navigate("1"),this.V.page=1,this.V.home()}},r.exports=i}),define("lib/underscore/underscore",[],function(e,t,r){(function(){var e=this,i=e._,n={},o=Array.prototype,a=Object.prototype,s=Function.prototype,c=o.push,l=o.slice,u=o.concat,d=a.toString,h=a.hasOwnProperty,f=o.forEach,p=o.map,m=o.reduce,g=o.reduceRight,v=o.filter,b=o.every,y=o.some,x=o.indexOf,w=o.lastIndexOf,k=Array.isArray,S=Object.keys,R=s.bind,P=function(e){return e instanceof P?e:this instanceof P?(this._wrapped=e,void 0):new P(e)};t!==void 0?(r!==void 0&&r.exports&&(t=r.exports=P),t._=P):e._=P,P.VERSION="1.6.0";var C=P.each=P.forEach=function(e,t,r){if(null==e)return e;if(f&&e.forEach===f)e.forEach(t,r);else if(e.length===+e.length){for(var i=0,o=e.length;o>i;i++)if(t.call(r,e[i],i,e)===n)return}else for(var a=P.keys(e),i=0,o=a.length;o>i;i++)if(t.call(r,e[a[i]],a[i],e)===n)return;return e};P.map=P.collect=function(e,t,r){var i=[];return null==e?i:p&&e.map===p?e.map(t,r):(C(e,function(e,n,o){i.push(t.call(r,e,n,o))}),i)};var q="Reduce of empty array with no initial value";P.reduce=P.foldl=P.inject=function(e,t,r,i){var n=arguments.length>2;if(null==e&&(e=[]),m&&e.reduce===m)return i&&(t=P.bind(t,i)),n?e.reduce(t,r):e.reduce(t);if(C(e,function(e,o,a){n?r=t.call(i,r,e,o,a):(r=e,n=!0)}),!n)throw new TypeError(q);return r},P.reduceRight=P.foldr=function(e,t,r,i){var n=arguments.length>2;if(null==e&&(e=[]),g&&e.reduceRight===g)return i&&(t=P.bind(t,i)),n?e.reduceRight(t,r):e.reduceRight(t);var o=e.length;if(o!==+o){var a=P.keys(e);o=a.length}if(C(e,function(s,c,l){c=a?a[--o]:--o,n?r=t.call(i,r,e[c],c,l):(r=e[c],n=!0)}),!n)throw new TypeError(q);return r},P.find=P.detect=function(e,t,r){var i;return $(e,function(e,n,o){return t.call(r,e,n,o)?(i=e,!0):void 0}),i},P.filter=P.select=function(e,t,r){var i=[];return null==e?i:v&&e.filter===v?e.filter(t,r):(C(e,function(e,n,o){t.call(r,e,n,o)&&i.push(e)}),i)},P.reject=function(e,t,r){return P.filter(e,function(e,i,n){return!t.call(r,e,i,n)},r)},P.every=P.all=function(e,t,r){t||(t=P.identity);var i=!0;return null==e?i:b&&e.every===b?e.every(t,r):(C(e,function(e,o,a){return(i=i&&t.call(r,e,o,a))?void 0:n}),!!i)};var $=P.some=P.any=function(e,t,r){t||(t=P.identity);var i=!1;return null==e?i:y&&e.some===y?e.some(t,r):(C(e,function(e,o,a){return i||(i=t.call(r,e,o,a))?n:void 0}),!!i)};P.contains=P.include=function(e,t){return null==e?!1:x&&e.indexOf===x?-1!=e.indexOf(t):$(e,function(e){return e===t})},P.invoke=function(e,t){var r=l.call(arguments,2),i=P.isFunction(t);return P.map(e,function(e){return(i?t:e[t]).apply(e,r)})},P.pluck=function(e,t){return P.map(e,P.property(t))},P.where=function(e,t){return P.filter(e,P.matches(t))},P.findWhere=function(e,t){return P.find(e,P.matches(t))},P.max=function(e,t,r){if(!t&&P.isArray(e)&&e[0]===+e[0]&&65535>e.length)return Math.max.apply(Math,e);var i=-1/0,n=-1/0;return C(e,function(e,o,a){var s=t?t.call(r,e,o,a):e;s>n&&(i=e,n=s)}),i},P.min=function(e,t,r){if(!t&&P.isArray(e)&&e[0]===+e[0]&&65535>e.length)return Math.min.apply(Math,e);var i=1/0,n=1/0;return C(e,function(e,o,a){var s=t?t.call(r,e,o,a):e;n>s&&(i=e,n=s)}),i},P.shuffle=function(e){var t,r=0,i=[];return C(e,function(e){t=P.random(r++),i[r-1]=i[t],i[t]=e}),i},P.sample=function(e,t,r){return null==t||r?(e.length!==+e.length&&(e=P.values(e)),e[P.random(e.length-1)]):P.shuffle(e).slice(0,Math.max(0,t))};var T=function(e){return null==e?P.identity:P.isFunction(e)?e:P.property(e)};P.sortBy=function(e,t,r){return t=T(t),P.pluck(P.map(e,function(e,i,n){return{value:e,index:i,criteria:t.call(r,e,i,n)}}).sort(function(e,t){var r=e.criteria,i=t.criteria;if(r!==i){if(r>i||void 0===r)return 1;if(i>r||void 0===i)return-1}return e.index-t.index}),"value")};var A=function(e){return function(t,r,i){var n={};return r=T(r),C(t,function(o,a){var s=r.call(i,o,a,t);e(n,s,o)}),n}};P.groupBy=A(function(e,t,r){P.has(e,t)?e[t].push(r):e[t]=[r]}),P.indexBy=A(function(e,t,r){e[t]=r}),P.countBy=A(function(e,t){P.has(e,t)?e[t]++:e[t]=1}),P.sortedIndex=function(e,t,r,i){r=T(r);for(var n=r.call(i,t),o=0,a=e.length;a>o;){var s=o+a>>>1;n>r.call(i,e[s])?o=s+1:a=s}return o},P.toArray=function(e){return e?P.isArray(e)?l.call(e):e.length===+e.length?P.map(e,P.identity):P.values(e):[]},P.size=function(e){return null==e?0:e.length===+e.length?e.length:P.keys(e).length},P.first=P.head=P.take=function(e,t,r){return null==e?void 0:null==t||r?e[0]:0>t?[]:l.call(e,0,t)},P.initial=function(e,t,r){return l.call(e,0,e.length-(null==t||r?1:t))},P.last=function(e,t,r){return null==e?void 0:null==t||r?e[e.length-1]:l.call(e,Math.max(e.length-t,0))},P.rest=P.tail=P.drop=function(e,t,r){return l.call(e,null==t||r?1:t)},P.compact=function(e){return P.filter(e,P.identity)};var E=function(e,t,r){return t&&P.every(e,P.isArray)?u.apply(r,e):(C(e,function(e){P.isArray(e)||P.isArguments(e)?t?c.apply(r,e):E(e,t,r):r.push(e)}),r)};P.flatten=function(e,t){return E(e,t,[])},P.without=function(e){return P.difference(e,l.call(arguments,1))},P.partition=function(e,t,r){t=T(t);var i=[],n=[];return C(e,function(e){(t.call(r,e)?i:n).push(e)}),[i,n]},P.uniq=P.unique=function(e,t,r,i){P.isFunction(t)&&(i=r,r=t,t=!1);var n=r?P.map(e,r,i):e,o=[],a=[];return C(n,function(r,i){(t?i&&a[a.length-1]===r:P.contains(a,r))||(a.push(r),o.push(e[i]))}),o},P.union=function(){return P.uniq(P.flatten(arguments,!0))},P.intersection=function(e){var t=l.call(arguments,1);return P.filter(P.uniq(e),function(e){return P.every(t,function(t){return P.contains(t,e)})})},P.difference=function(e){var t=u.apply(o,l.call(arguments,1));return P.filter(e,function(e){return!P.contains(t,e)})},P.zip=function(){for(var e=P.max(P.pluck(arguments,"length").concat(0)),t=Array(e),r=0;e>r;r++)t[r]=P.pluck(arguments,""+r);return t},P.object=function(e,t){if(null==e)return{};for(var r={},i=0,n=e.length;n>i;i++)t?r[e[i]]=t[i]:r[e[i][0]]=e[i][1];return r},P.indexOf=function(e,t,r){if(null==e)return-1;var i=0,n=e.length;if(r){if("number"!=typeof r)return i=P.sortedIndex(e,t),e[i]===t?i:-1;i=0>r?Math.max(0,n+r):r}if(x&&e.indexOf===x)return e.indexOf(t,r);for(;n>i;i++)if(e[i]===t)return i;return-1},P.lastIndexOf=function(e,t,r){if(null==e)return-1;var i=null!=r;if(w&&e.lastIndexOf===w)return i?e.lastIndexOf(t,r):e.lastIndexOf(t);for(var n=i?r:e.length;n--;)if(e[n]===t)return n;return-1},P.range=function(e,t,r){1>=arguments.length&&(t=e||0,e=0),r=arguments[2]||1;for(var i=Math.max(Math.ceil((t-e)/r),0),n=0,o=Array(i);i>n;)o[n++]=e,e+=r;return o};var _=function(){};P.bind=function(e,t){var r,i;if(R&&e.bind===R)return R.apply(e,l.call(arguments,1));if(!P.isFunction(e))throw new TypeError;return r=l.call(arguments,2),i=function(){if(!(this instanceof i))return e.apply(t,r.concat(l.call(arguments)));_.prototype=e.prototype;var n=new _;_.prototype=null;var o=e.apply(n,r.concat(l.call(arguments)));return Object(o)===o?o:n}},P.partial=function(e){var t=l.call(arguments,1);return function(){for(var r=0,i=t.slice(),n=0,o=i.length;o>n;n++)i[n]===P&&(i[n]=arguments[r++]);for(;arguments.length>r;)i.push(arguments[r++]);return e.apply(this,i)}},P.bindAll=function(e){var t=l.call(arguments,1);if(0===t.length)throw Error("bindAll must be passed function names");return C(t,function(t){e[t]=P.bind(e[t],e)}),e},P.memoize=function(e,t){var r={};return t||(t=P.identity),function(){var i=t.apply(this,arguments);return P.has(r,i)?r[i]:r[i]=e.apply(this,arguments)}},P.delay=function(e,t){var r=l.call(arguments,2);return setTimeout(function(){return e.apply(null,r)},t)},P.defer=function(e){return P.delay.apply(P,[e,1].concat(l.call(arguments,1)))},P.throttle=function(e,t,r){var i,n,o,a=null,s=0;r||(r={});var c=function(){s=r.leading===!1?0:P.now(),a=null,o=e.apply(i,n),i=n=null};return function(){var l=P.now();s||r.leading!==!1||(s=l);var u=t-(l-s);return i=this,n=arguments,0>=u?(clearTimeout(a),a=null,s=l,o=e.apply(i,n),i=n=null):a||r.trailing===!1||(a=setTimeout(c,u)),o}},P.debounce=function(e,t,r){var i,n,o,a,s,c=function(){var l=P.now()-a;t>l?i=setTimeout(c,t-l):(i=null,r||(s=e.apply(o,n),o=n=null))};return function(){o=this,n=arguments,a=P.now();var l=r&&!i;return i||(i=setTimeout(c,t)),l&&(s=e.apply(o,n),o=n=null),s}},P.once=function(e){var t,r=!1;return function(){return r?t:(r=!0,t=e.apply(this,arguments),e=null,t)}},P.wrap=function(e,t){return P.partial(t,e)},P.compose=function(){var e=arguments;return function(){for(var t=arguments,r=e.length-1;r>=0;r--)t=[e[r].apply(this,t)];return t[0]}},P.after=function(e,t){return function(){return 1>--e?t.apply(this,arguments):void 0}},P.keys=function(e){if(!P.isObject(e))return[];if(S)return S(e);var t=[];for(var r in e)P.has(e,r)&&t.push(r);return t},P.values=function(e){for(var t=P.keys(e),r=t.length,i=Array(r),n=0;r>n;n++)i[n]=e[t[n]];return i},P.pairs=function(e){for(var t=P.keys(e),r=t.length,i=Array(r),n=0;r>n;n++)i[n]=[t[n],e[t[n]]];return i},P.invert=function(e){for(var t={},r=P.keys(e),i=0,n=r.length;n>i;i++)t[e[r[i]]]=r[i];return t},P.functions=P.methods=function(e){var t=[];for(var r in e)P.isFunction(e[r])&&t.push(r);return t.sort()},P.extend=function(e){return C(l.call(arguments,1),function(t){if(t)for(var r in t)e[r]=t[r]}),e},P.pick=function(e){var t={},r=u.apply(o,l.call(arguments,1));return C(r,function(r){r in e&&(t[r]=e[r])}),t},P.omit=function(e){var t={},r=u.apply(o,l.call(arguments,1));for(var i in e)P.contains(r,i)||(t[i]=e[i]);return t},P.defaults=function(e){return C(l.call(arguments,1),function(t){if(t)for(var r in t)void 0===e[r]&&(e[r]=t[r])}),e},P.clone=function(e){return P.isObject(e)?P.isArray(e)?e.slice():P.extend({},e):e},P.tap=function(e,t){return t(e),e};var L=function(e,t,r,i){if(e===t)return 0!==e||1/e==1/t;if(null==e||null==t)return e===t;e instanceof P&&(e=e._wrapped),t instanceof P&&(t=t._wrapped);var n=d.call(e);if(n!=d.call(t))return!1;switch(n){case"[object String]":return e==t+"";case"[object Number]":return e!=+e?t!=+t:0==e?1/e==1/t:e==+t;case"[object Date]":case"[object Boolean]":return+e==+t;case"[object RegExp]":return e.source==t.source&&e.global==t.global&&e.multiline==t.multiline&&e.ignoreCase==t.ignoreCase}if("object"!=typeof e||"object"!=typeof t)return!1;for(var o=r.length;o--;)if(r[o]==e)return i[o]==t;var a=e.constructor,s=t.constructor;if(a!==s&&!(P.isFunction(a)&&a instanceof a&&P.isFunction(s)&&s instanceof s)&&"constructor"in e&&"constructor"in t)return!1;r.push(e),i.push(t);var c=0,l=!0;if("[object Array]"==n){if(c=e.length,l=c==t.length)for(;c--&&(l=L(e[c],t[c],r,i)););}else{for(var u in e)if(P.has(e,u)&&(c++,!(l=P.has(t,u)&&L(e[u],t[u],r,i))))break;if(l){for(u in t)if(P.has(t,u)&&!c--)break;l=!c}}return r.pop(),i.pop(),l};P.isEqual=function(e,t){return L(e,t,[],[])},P.isEmpty=function(e){if(null==e)return!0;if(P.isArray(e)||P.isString(e))return 0===e.length;for(var t in e)if(P.has(e,t))return!1;return!0},P.isElement=function(e){return!(!e||1!==e.nodeType)},P.isArray=k||function(e){return"[object Array]"==d.call(e)},P.isObject=function(e){return e===Object(e)},C(["Arguments","Function","String","Number","Date","RegExp"],function(e){P["is"+e]=function(t){return d.call(t)=="[object "+e+"]"}}),P.isArguments(arguments)||(P.isArguments=function(e){return!(!e||!P.has(e,"callee"))}),P.isFunction=function(e){return"function"==typeof e},P.isFinite=function(e){return isFinite(e)&&!isNaN(parseFloat(e))},P.isNaN=function(e){return P.isNumber(e)&&e!=+e},P.isBoolean=function(e){return e===!0||e===!1||"[object Boolean]"==d.call(e)},P.isNull=function(e){return null===e},P.isUndefined=function(e){return void 0===e},P.has=function(e,t){return h.call(e,t)},P.noConflict=function(){return e._=i,this},P.identity=function(e){return e},P.constant=function(e){return function(){return e}},P.property=function(e){return function(t){return t[e]}},P.matches=function(e){return function(t){if(t===e)return!0;for(var r in e)if(e[r]!==t[r])return!1;return!0}},P.times=function(e,t,r){for(var i=Array(Math.max(0,e)),n=0;e>n;n++)i[n]=t.call(r,n);return i},P.random=function(e,t){return null==t&&(t=e,e=0),e+Math.floor(Math.random()*(t-e+1))},P.now=Date.now||function(){return(new Date).getTime()};var D={escape:{"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#x27;"}};D.unescape=P.invert(D.escape);var N={escape:RegExp("["+P.keys(D.escape).join("")+"]","g"),unescape:RegExp("("+P.keys(D.unescape).join("|")+")","g")};P.each(["escape","unescape"],function(e){P[e]=function(t){return null==t?"":(""+t).replace(N[e],function(t){return D[e][t]})}}),P.result=function(e,t){if(null==e)return void 0;var r=e[t];return P.isFunction(r)?r.call(e):r},P.mixin=function(e){C(P.functions(e),function(t){var r=P[t]=e[t];P.prototype[t]=function(){var e=[this._wrapped];return c.apply(e,arguments),B.call(this,r.apply(P,e))}})};var O=0;P.uniqueId=function(e){var t=++O+"";return e?e+t:t},P.templateSettings={evaluate:/<%([\s\S]+?)%>/g,interpolate:/<%=([\s\S]+?)%>/g,escape:/<%-([\s\S]+?)%>/g};var j=/(.)^/,M={"'":"'","\\":"\\","\r":"r","\n":"n","	":"t","\u2028":"u2028","\u2029":"u2029"},I=/\\|'|\r|\n|\t|\u2028|\u2029/g;P.template=function(e,t,r){var i;r=P.defaults({},r,P.templateSettings);var n=RegExp([(r.escape||j).source,(r.interpolate||j).source,(r.evaluate||j).source].join("|")+"|$","g"),o=0,a="__p+='";e.replace(n,function(t,r,i,n,s){return a+=e.slice(o,s).replace(I,function(e){return"\\"+M[e]}),r&&(a+="'+\n((__t=("+r+"))==null?'':_.escape(__t))+\n'"),i&&(a+="'+\n((__t=("+i+"))==null?'':__t)+\n'"),n&&(a+="';\n"+n+"\n__p+='"),o=s+t.length,t}),a+="';\n",r.variable||(a="with(obj||{}){\n"+a+"}\n"),a="var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};\n"+a+"return __p;\n";try{i=Function(r.variable||"obj","_",a)}catch(s){throw s.source=a,s}if(t)return i(t,P);var c=function(e){return i.call(this,e,P)};return c.source="function("+(r.variable||"obj")+"){\n"+a+"}",c},P.chain=function(e){return P(e).chain()};var B=function(e){return this._chain?P(e).chain():e};P.mixin(P),C(["pop","push","reverse","shift","sort","splice","unshift"],function(e){var t=o[e];P.prototype[e]=function(){var r=this._wrapped;return t.apply(r,arguments),"shift"!=e&&"splice"!=e||0!==r.length||delete r[0],B.call(this,r)}}),C(["concat","join","slice"],function(e){var t=o[e];P.prototype[e]=function(){return B.call(this,t.apply(this._wrapped,arguments))}}),P.extend(P.prototype,{chain:function(){return this._chain=!0,this},value:function(){return this._wrapped}}),"function"==typeof define&&define.amd&&define("underscore",[],function(){return P}),window._=P}).call(this)}),define("lib/backbone/backbone",[],function(){(function(e,t){e.Backbone=t(e,{},e._,e.jQuery||e.Zepto||e.ender||e.$)})(this,function(e,t,r,i){var n=e.Backbone,o=[];o.push;var a=o.slice;o.splice,t.VERSION="1.1.1",t.$=i,t.noConflict=function(){return e.Backbone=n,this},t.emulateHTTP=!1,t.emulateJSON=!0;var s=t.Events={on:function(e,t,r){if(!l(this,"on",e,[t,r])||!t)return this;this._events||(this._events={});var i=this._events[e]||(this._events[e]=[]);return i.push({callback:t,context:r,ctx:r||this}),this},once:function(e,t,i){if(!l(this,"once",e,[t,i])||!t)return this;var n=this,o=r.once(function(){n.off(e,o),t.apply(this,arguments)});return o._callback=t,this.on(e,o,i)},off:function(e,t,i){var n,o,a,s,c,u,d,h;if(!this._events||!l(this,"off",e,[t,i]))return this;if(!e&&!t&&!i)return this._events=void 0,this;for(s=e?[e]:r.keys(this._events),c=0,u=s.length;u>c;c++)if(e=s[c],a=this._events[e]){if(this._events[e]=n=[],t||i)for(d=0,h=a.length;h>d;d++)o=a[d],(t&&t!==o.callback&&t!==o.callback._callback||i&&i!==o.context)&&n.push(o);n.length||delete this._events[e]}return this},trigger:function(e){if(!this._events)return this;var t=a.call(arguments,1);if(!l(this,"trigger",e,t))return this;var r=this._events[e],i=this._events.all;return r&&u(r,t),i&&u(i,arguments),this},stopListening:function(e,t,i){var n=this._listeningTo;if(!n)return this;var o=!t&&!i;i||"object"!=typeof t||(i=this),e&&((n={})[e._listenId]=e);for(var a in n)e=n[a],e.off(t,i,this),(o||r.isEmpty(e._events))&&delete this._listeningTo[a];return this}},c=/\s+/,l=function(e,t,r,i){if(!r)return!0;if("object"==typeof r){for(var n in r)e[t].apply(e,[n,r[n]].concat(i));return!1}if(c.test(r)){for(var o=r.split(c),a=0,s=o.length;s>a;a++)e[t].apply(e,[o[a]].concat(i));return!1}return!0},u=function(e,t){var r,i=-1,n=e.length,o=t[0],a=t[1],s=t[2];switch(t.length){case 0:for(;n>++i;)(r=e[i]).callback.call(r.ctx);return;case 1:for(;n>++i;)(r=e[i]).callback.call(r.ctx,o);return;case 2:for(;n>++i;)(r=e[i]).callback.call(r.ctx,o,a);return;case 3:for(;n>++i;)(r=e[i]).callback.call(r.ctx,o,a,s);return;default:for(;n>++i;)(r=e[i]).callback.apply(r.ctx,t);return}},d={listenTo:"on",listenToOnce:"once"};r.each(d,function(e,t){s[t]=function(t,i,n){var o=this._listeningTo||(this._listeningTo={}),a=t._listenId||(t._listenId=r.uniqueId("l"));return o[a]=t,n||"object"!=typeof i||(n=this),t[e](i,n,this),this}}),s.bind=s.on,s.unbind=s.off,r.extend(t,s);var h=t.Model=function(e,t){var i=e||{};t||(t={}),this.cid=r.uniqueId("c"),this.attributes={},t.collection&&(this.collection=t.collection),t.parse&&(i=this.parse(i,t)||{}),i=r.defaults({},i,r.result(this,"defaults")),this.set(i,t),this.changed={},this.initialize.apply(this,arguments)};r.extend(h.prototype,s,{changed:null,validationError:null,idAttribute:"id",initialize:function(){},toJSON:function(){return r.clone(this.attributes)},sync:function(){return t.sync.apply(this,arguments)},get:function(e){return this.attributes[e]},escape:function(e){return r.escape(this.get(e))},has:function(e){return null!=this.get(e)},set:function(e,t,i){var n,o,a,s,c,l,u,d;if(null==e)return this;if("object"==typeof e?(o=e,i=t):(o={})[e]=t,i||(i={}),!this._validate(o,i))return!1;a=i.unset,c=i.silent,s=[],l=this._changing,this._changing=!0,l||(this._previousAttributes=r.clone(this.attributes),this.changed={}),d=this.attributes,u=this._previousAttributes,this.idAttribute in o&&(this.id=o[this.idAttribute]);for(n in o)t=o[n],r.isEqual(d[n],t)||s.push(n),r.isEqual(u[n],t)?delete this.changed[n]:this.changed[n]=t,a?delete d[n]:d[n]=t;if(!c){s.length&&(this._pending=i);for(var h=0,f=s.length;f>h;h++)this.trigger("change:"+s[h],this,d[s[h]],i)}if(l)return this;if(!c)for(;this._pending;)i=this._pending,this._pending=!1,this.trigger("change",this,i);return this._pending=!1,this._changing=!1,this},unset:function(e,t){return this.set(e,void 0,r.extend({},t,{unset:!0}))},clear:function(e){var t={};for(var i in this.attributes)t[i]=void 0;return this.set(t,r.extend({},e,{unset:!0}))},hasChanged:function(e){return null==e?!r.isEmpty(this.changed):r.has(this.changed,e)},changedAttributes:function(e){if(!e)return this.hasChanged()?r.clone(this.changed):!1;var t,i=!1,n=this._changing?this._previousAttributes:this.attributes;for(var o in e)r.isEqual(n[o],t=e[o])||((i||(i={}))[o]=t);return i},previous:function(e){return null!=e&&this._previousAttributes?this._previousAttributes[e]:null},previousAttributes:function(){return r.clone(this._previousAttributes)},fetch:function(e){e=e?r.clone(e):{},void 0===e.parse&&(e.parse=!0);var t=this,i=e.success;return e.success=function(r){return t.set(t.parse(r,e),e)?(i&&i(t,r,e),t.trigger("sync",t,r,e),void 0):!1},j(this,e),this.sync("read",this,e)},save:function(e,t,i){var n,o,a,s=this.attributes;if(null==e||"object"==typeof e?(n=e,i=t):(n={})[e]=t,i=r.extend({validate:!0},i),n&&!i.wait){if(!this.set(n,i))return!1}else if(!this._validate(n,i))return!1;n&&i.wait&&(this.attributes=r.extend({},s,n)),void 0===i.parse&&(i.parse=!0);var c=this,l=i.success;return i.success=function(e){c.attributes=s;var t=c.parse(e,i);return i.wait&&(t=r.extend(n||{},t)),r.isObject(t)&&!c.set(t,i)?!1:(l&&l(c,e,i),c.trigger("sync",c,e,i),void 0)},j(this,i),o=this.isNew()?"create":i.patch?"patch":"update","patch"===o&&(i.attrs=n),a=this.sync(o,this,i),n&&i.wait&&(this.attributes=s),a},destroy:function(e){e=e?r.clone(e):{};var t=this,i=e.success,n=function(){t.trigger("destroy",t,t.collection,e)};if(e.success=function(r){(e.wait||t.isNew())&&n(),i&&i(t,r,e),t.isNew()||t.trigger("sync",t,r,e)},this.isNew())return e.success(),!1;j(this,e);var o=this.sync("delete",this,e);return e.wait||n(),o},url:function(){var e=r.result(this,"urlRoot")||r.result(this.collection,"url")||O();return this.isNew()?e:e.replace(/([^\/])$/,"$1/")+encodeURIComponent(this.id)},parse:function(e){return e},clone:function(){return new this.constructor(this.attributes)},isNew:function(){return!this.has(this.idAttribute)},isValid:function(e){return this._validate({},r.extend(e||{},{validate:!0}))},_validate:function(e,t){if(!t.validate||!this.validate)return!0;e=r.extend({},this.attributes,e);var i=this.validationError=this.validate(e,t)||null;return i?(this.trigger("invalid",this,i,r.extend(t,{validationError:i})),!1):!0}});var f=["keys","values","pairs","invert","pick","omit"];r.each(f,function(e){h.prototype[e]=function(){var t=a.call(arguments);return t.unshift(this.attributes),r[e].apply(r,t)}});var p=t.Collection=function(e,t){t||(t={}),t.model&&(this.model=t.model),void 0!==t.comparator&&(this.comparator=t.comparator),this._reset(),this.initialize.apply(this,arguments),e&&this.reset(e,r.extend({silent:!0},t))},m={add:!0,remove:!0,merge:!0},g={add:!0,remove:!1};r.extend(p.prototype,s,{model:h,initialize:function(){},toJSON:function(e){return this.map(function(t){return t.toJSON(e)})},sync:function(){return t.sync.apply(this,arguments)},add:function(e,t){return this.set(e,r.extend({merge:!1},t,g))},remove:function(e,t){var i=!r.isArray(e);e=i?[e]:r.clone(e),t||(t={});var n,o,a,s;for(n=0,o=e.length;o>n;n++)s=e[n]=this.get(e[n]),s&&(delete this._byId[s.id],delete this._byId[s.cid],a=this.indexOf(s),this.models.splice(a,1),this.length--,t.silent||(t.index=a,s.trigger("remove",s,this,t)),this._removeReference(s,t));return i?e[0]:e},set:function(e,t){t=r.defaults({},t,m),t.parse&&(e=this.parse(e,t));var i=!r.isArray(e);e=i?e?[e]:[]:r.clone(e);var n,o,a,s,c,l,u,d=t.at,f=this.model,p=this.comparator&&null==d&&t.sort!==!1,g=r.isString(this.comparator)?this.comparator:null,v=[],b=[],y={},x=t.add,w=t.merge,k=t.remove,S=!p&&x&&k?[]:!1;for(n=0,o=e.length;o>n;n++){if(c=e[n]||{},a=c instanceof h?s=c:c[f.prototype.idAttribute||"id"],l=this.get(a))k&&(y[l.cid]=!0),w&&(c=c===s?s.attributes:c,t.parse&&(c=l.parse(c,t)),l.set(c,t),p&&!u&&l.hasChanged(g)&&(u=!0)),e[n]=l;else if(x){if(s=e[n]=this._prepareModel(c,t),!s)continue;v.push(s),this._addReference(s,t)}s=l||s,!S||!s.isNew()&&y[s.id]||S.push(s),y[s.id]=!0}if(k){for(n=0,o=this.length;o>n;++n)y[(s=this.models[n]).cid]||b.push(s);b.length&&this.remove(b,t)}if(v.length||S&&S.length)if(p&&(u=!0),this.length+=v.length,null!=d)for(n=0,o=v.length;o>n;n++)this.models.splice(d+n,0,v[n]);else{S&&(this.models.length=0);var R=S||v;for(n=0,o=R.length;o>n;n++)this.models.push(R[n])}if(u&&this.sort({silent:!0}),!t.silent){for(n=0,o=v.length;o>n;n++)(s=v[n]).trigger("add",s,this,t);(u||S&&S.length)&&this.trigger("sort",this,t)}return i?e[0]:e},reset:function(e,t){t||(t={});for(var i=0,n=this.models.length;n>i;i++)this._removeReference(this.models[i],t);return t.previousModels=this.models,this._reset(),e=this.add(e,r.extend({silent:!0},t)),t.silent||this.trigger("reset",this,t),e},push:function(e,t){return this.add(e,r.extend({at:this.length},t))},pop:function(e){var t=this.at(this.length-1);return this.remove(t,e),t},unshift:function(e,t){return this.add(e,r.extend({at:0},t))},shift:function(e){var t=this.at(0);return this.remove(t,e),t},slice:function(){return a.apply(this.models,arguments)},get:function(e){return null==e?void 0:this._byId[e]||this._byId[e.id]||this._byId[e.cid]},at:function(e){return this.models[e]},where:function(e,t){return r.isEmpty(e)?t?void 0:[]:this[t?"find":"filter"](function(t){for(var r in e)if(e[r]!==t.get(r))return!1;return!0})},findWhere:function(e){return this.where(e,!0)},sort:function(e){if(!this.comparator)throw Error("Cannot sort a set without a comparator");return e||(e={}),r.isString(this.comparator)||1===this.comparator.length?this.models=this.sortBy(this.comparator,this):this.models.sort(r.bind(this.comparator,this)),e.silent||this.trigger("sort",this,e),this},pluck:function(e){return r.invoke(this.models,"get",e)},fetch:function(e){e=e?r.clone(e):{},void 0===e.parse&&(e.parse=!0);var t=e.success,i=this;return e.success=function(r){var n=e.reset?"reset":"set";i[n](r,e),t&&t(i,r,e),i.trigger("sync",i,r,e)},j(this,e),this.sync("read",this,e)},create:function(e,t){if(t=t?r.clone(t):{},!(e=this._prepareModel(e,t)))return!1;t.wait||this.add(e,t);var i=this,n=t.success;return t.success=function(e,r){t.wait&&i.add(e,t),n&&n(e,r,t)},e.save(null,t),e},parse:function(e){return e},clone:function(){return new this.constructor(this.models)},_reset:function(){this.length=0,this.models=[],this._byId={}},_prepareModel:function(e,t){if(e instanceof h)return e;t=t?r.clone(t):{},t.collection=this;var i=new this.model(e,t);return i.validationError?(this.trigger("invalid",this,i.validationError,t),!1):i},_addReference:function(e){this._byId[e.cid]=e,null!=e.id&&(this._byId[e.id]=e),e.collection||(e.collection=this),e.on("all",this._onModelEvent,this)},_removeReference:function(e){this===e.collection&&delete e.collection,e.off("all",this._onModelEvent,this)},_onModelEvent:function(e,t,r,i){("add"!==e&&"remove"!==e||r===this)&&("destroy"===e&&this.remove(t,i),t&&e==="change:"+t.idAttribute&&(delete this._byId[t.previous(t.idAttribute)],null!=t.id&&(this._byId[t.id]=t)),this.trigger.apply(this,arguments))}});var v=["forEach","each","map","collect","reduce","foldl","inject","reduceRight","foldr","find","detect","filter","select","reject","every","all","some","any","include","contains","invoke","max","min","toArray","size","first","head","take","initial","rest","tail","drop","last","without","difference","indexOf","shuffle","lastIndexOf","isEmpty","chain","sample"];r.each(v,function(e){p.prototype[e]=function(){var t=a.call(arguments);return t.unshift(this.models),r[e].apply(r,t)}});var b=["groupBy","countBy","sortBy","indexBy"];r.each(b,function(e){p.prototype[e]=function(t,i){var n=r.isFunction(t)?t:function(e){return e.get(t)};return r[e](this.models,n,i)}});var y=t.View=function(e){this.cid=r.uniqueId("view"),e||(e={}),r.extend(this,r.pick(e,w)),this._ensureElement(),this.initialize.apply(this,arguments),this.delegateEvents()},x=/^(\S+)\s*(.*)$/,w=["model","collection","el","id","attributes","className","tagName","events"];r.extend(y.prototype,s,{tagName:"div",$:function(e){return this.$el.find(e)},initialize:function(){},render:function(){return this},remove:function(){return this.$el.remove(),this.stopListening(),this},setElement:function(e,r){return this.$el&&this.undelegateEvents(),this.$el=e instanceof t.$?e:t.$(e),this.el=this.$el[0],r!==!1&&this.delegateEvents(),this},delegateEvents:function(e){if(!e&&!(e=r.result(this,"events")))return this;this.undelegateEvents();for(var t in e){var i=e[t];if(r.isFunction(i)||(i=this[e[t]]),i){var n=t.match(x),o=n[1],a=n[2];i=r.bind(i,this),o+=".delegateEvents"+this.cid,""===a?this.$el.on(o,i):this.$el.on(o,a,i)}}return this},undelegateEvents:function(){return this.$el.off(".delegateEvents"+this.cid),this},_ensureElement:function(){if(this.el)this.setElement(r.result(this,"el"),!1);else{var e=r.extend({},r.result(this,"attributes"));this.id&&(e.id=r.result(this,"id")),this.className&&(e["class"]=r.result(this,"className"));var i=t.$("<"+r.result(this,"tagName")+">").attr(e);this.setElement(i,!1)}}}),t.sync=function(e,i,n){var o=S[e];r.defaults(n||(n={}),{emulateHTTP:t.emulateHTTP,emulateJSON:t.emulateJSON});var a={type:o,dataType:"json"};if(n.url||(a.url=r.result(i,"url")||O()),null!=n.data||!i||"create"!==e&&"update"!==e&&"patch"!==e||(a.contentType="application/json",a.data=JSON.stringify(n.attrs||i.toJSON(n))),n.emulateJSON&&(a.contentType="application/x-www-form-urlencoded",a.data=a.data?{model:a.data}:{}),n.emulateHTTP&&("PUT"===o||"DELETE"===o||"PATCH"===o)){a.type="POST",n.emulateJSON&&(a.data._method=o);var s=n.beforeSend;n.beforeSend=function(e){return e.setRequestHeader("X-HTTP-Method-Override",o),s?s.apply(this,arguments):void 0}}"GET"===a.type||n.emulateJSON||(a.processData=!1),"PATCH"===a.type&&k&&(a.xhr=function(){return new ActiveXObject("Microsoft.XMLHTTP")});var c=n.xhr=t.ajax(r.extend(a,n));return i.trigger("request",i,c,n),c};var k=!("undefined"==typeof window||!window.ActiveXObject||window.XMLHttpRequest&&(new XMLHttpRequest).dispatchEvent),S={create:"POST",update:"PUT",patch:"PATCH","delete":"DELETE",read:"GET"};t.ajax=function(){return t.$.ajax.apply(t.$,arguments)};var R=t.Router=function(e){e||(e={}),e.routes&&(this.routes=e.routes),this._bindRoutes(),this.initialize.apply(this,arguments)},P=/\((.*?)\)/g,C=/(\(\?)?:\w+/g,q=/\*\w+/g,$=/[\-{}\[\]+?.,\\\^$|#\s]/g;r.extend(R.prototype,s,{initialize:function(){},route:function(e,i,n){r.isRegExp(e)||(e=this._routeToRegExp(e)),r.isFunction(i)&&(n=i,i=""),n||(n=this[i]);
var o=this;return t.history.route(e,function(r){var a=o._extractParameters(e,r);o.execute(n,a),o.trigger.apply(o,["route:"+i].concat(a)),o.trigger("route",i,a),t.history.trigger("route",o,i,a)}),this},execute:function(e,t){e&&e.apply(this,t)},navigate:function(e,r){return t.history.navigate(e,r),this},_bindRoutes:function(){if(this.routes){this.routes=r.result(this,"routes");for(var e,t=r.keys(this.routes);null!=(e=t.pop());)this.route(e,this.routes[e])}},_routeToRegExp:function(e){return e=e.replace($,"\\$&").replace(P,"(?:$1)?").replace(C,function(e,t){return t?e:"([^/?]+)"}).replace(q,"([^?]*?)"),RegExp("^"+e+"(?:\\?(.*))?$")},_extractParameters:function(e,t){var i=e.exec(t).slice(1);return r.map(i,function(e,t){return t===i.length-1?e||null:e?decodeURIComponent(e):null})}});var T=t.History=function(){this.handlers=[],r.bindAll(this,"checkUrl"),"undefined"!=typeof window&&(this.location=window.location,this.history=window.history)},A=/^[#\/]|\s+$/g,E=/^\/+|\/+$/g,_=/msie [\w.]+/,L=/\/$/,D=/#.*$/;T.started=!1,r.extend(T.prototype,s,{interval:50,atRoot:function(){return this.location.pathname.replace(/[^\/]$/,"$&/")===this.root},getHash:function(e){var t=(e||this).location.href.match(/#(.*)$/);return t?t[1]:""},getFragment:function(e,t){if(null==e)if(this._hasPushState||!this._wantsHashChange||t){e=decodeURI(this.location.pathname+this.location.search);var r=this.root.replace(L,"");e.indexOf(r)||(e=e.slice(r.length))}else e=this.getHash();return e.replace(A,"")},start:function(e){if(T.started)throw Error("Backbone.history has already been started");T.started=!0,this.options=r.extend({root:"/"},this.options,e),this.root=this.options.root,this._wantsHashChange=this.options.hashChange!==!1,this._wantsPushState=!!this.options.pushState,this._hasPushState=!!(this.options.pushState&&this.history&&this.history.pushState);var i=this.getFragment(),n=document.documentMode,o=_.exec(navigator.userAgent.toLowerCase())&&(!n||7>=n);if(this.root=("/"+this.root+"/").replace(E,"/"),o&&this._wantsHashChange){var a=t.$('<iframe src="javascript:0" tabindex="-1">');this.iframe=a.hide().appendTo("body")[0].contentWindow,this.navigate(i)}this._hasPushState?t.$(window).on("popstate",this.checkUrl):this._wantsHashChange&&"onhashchange"in window&&!o?t.$(window).on("hashchange",this.checkUrl):this._wantsHashChange&&(this._checkUrlInterval=setInterval(this.checkUrl,this.interval)),this.fragment=i;var s=this.location;if(this._wantsHashChange&&this._wantsPushState){if(!this._hasPushState&&!this.atRoot())return this.fragment=this.getFragment(null,!0),this.location.replace(this.root+"#"+this.fragment),!0;this._hasPushState&&this.atRoot()&&s.hash&&(this.fragment=this.getHash().replace(A,""),this.history.replaceState({},document.title,this.root+this.fragment))}return this.options.silent?void 0:this.loadUrl()},stop:function(){t.$(window).off("popstate",this.checkUrl).off("hashchange",this.checkUrl),clearInterval(this._checkUrlInterval),T.started=!1},route:function(e,t){this.handlers.unshift({route:e,callback:t})},checkUrl:function(){var e=this.getFragment();return e===this.fragment&&this.iframe&&(e=this.getFragment(this.getHash(this.iframe))),e===this.fragment?!1:(this.iframe&&this.navigate(e),this.loadUrl(),void 0)},loadUrl:function(e){return e=this.fragment=this.getFragment(e),r.any(this.handlers,function(t){return t.route.test(e)?(t.callback(e),!0):void 0})},navigate:function(e,t){if(!T.started)return!1;t&&t!==!0||(t={trigger:!!t});var r=this.root+(e=this.getFragment(e||""));if(e=e.replace(D,""),this.fragment!==e){if(this.fragment=e,""===e&&"/"!==r&&(r=r.slice(0,-1)),this._hasPushState)this.history[t.replace?"replaceState":"pushState"]({},document.title,r);else{if(!this._wantsHashChange)return this.location.assign(r);this._updateHash(this.location,e,t.replace),this.iframe&&e!==this.getFragment(this.getHash(this.iframe))&&(t.replace||this.iframe.document.open().close(),this._updateHash(this.iframe.location,e,t.replace))}return t.trigger?this.loadUrl(e):void 0}},_updateHash:function(e,t,r){if(r){var i=e.href.replace(/(javascript:|#).*$/,"");e.replace(i+"#"+t)}else e.hash="#"+t}}),t.history=new T;var N=function(e,t){var i,n=this;i=e&&r.has(e,"constructor")?e.constructor:function(){return n.apply(this,arguments)},r.extend(i,n,t);var o=function(){this.constructor=i};return o.prototype=n.prototype,i.prototype=new o,e&&r.extend(i.prototype,e),i.__super__=n.prototype,i};h.extend=p.extend=R.extend=y.extend=T.extend=N;var O=function(){throw Error('A "url" property or function must be specified')},j=function(e,t){var r=t.error;t.error=function(i){r&&r(e,i,t),e.trigger("error",e,i,t)}};return t})});