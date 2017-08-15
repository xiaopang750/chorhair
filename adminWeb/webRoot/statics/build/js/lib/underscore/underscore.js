/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("lib/underscore/underscore",[],function(e,t,n){(function(){var e=this,i=e._,r={},o=Array.prototype,s=Object.prototype,a=Function.prototype,c=o.push,u=o.slice,l=o.concat,f=s.toString,p=s.hasOwnProperty,d=o.forEach,h=o.map,m=o.reduce,g=o.reduceRight,v=o.filter,y=o.every,b=o.some,x=o.indexOf,w=o.lastIndexOf,S=Array.isArray,k=Object.keys,T=a.bind,C=function(e){return e instanceof C?e:this instanceof C?(this._wrapped=e,void 0):new C(e)};t!==void 0?(n!==void 0&&n.exports&&(t=n.exports=C),t._=C):e._=C,C.VERSION="1.6.0";var E=C.each=C.forEach=function(e,t,n){if(null==e)return e;if(d&&e.forEach===d)e.forEach(t,n);else if(e.length===+e.length){for(var i=0,o=e.length;o>i;i++)if(t.call(n,e[i],i,e)===r)return}else for(var s=C.keys(e),i=0,o=s.length;o>i;i++)if(t.call(n,e[s[i]],s[i],e)===r)return;return e};C.map=C.collect=function(e,t,n){var i=[];return null==e?i:h&&e.map===h?e.map(t,n):(E(e,function(e,r,o){i.push(t.call(n,e,r,o))}),i)};var P="Reduce of empty array with no initial value";C.reduce=C.foldl=C.inject=function(e,t,n,i){var r=arguments.length>2;if(null==e&&(e=[]),m&&e.reduce===m)return i&&(t=C.bind(t,i)),r?e.reduce(t,n):e.reduce(t);if(E(e,function(e,o,s){r?n=t.call(i,n,e,o,s):(n=e,r=!0)}),!r)throw new TypeError(P);return n},C.reduceRight=C.foldr=function(e,t,n,i){var r=arguments.length>2;if(null==e&&(e=[]),g&&e.reduceRight===g)return i&&(t=C.bind(t,i)),r?e.reduceRight(t,n):e.reduceRight(t);var o=e.length;if(o!==+o){var s=C.keys(e);o=s.length}if(E(e,function(a,c,u){c=s?s[--o]:--o,r?n=t.call(i,n,e[c],c,u):(n=e[c],r=!0)}),!r)throw new TypeError(P);return n},C.find=C.detect=function(e,t,n){var i;return _(e,function(e,r,o){return t.call(n,e,r,o)?(i=e,!0):void 0}),i},C.filter=C.select=function(e,t,n){var i=[];return null==e?i:v&&e.filter===v?e.filter(t,n):(E(e,function(e,r,o){t.call(n,e,r,o)&&i.push(e)}),i)},C.reject=function(e,t,n){return C.filter(e,function(e,i,r){return!t.call(n,e,i,r)},n)},C.every=C.all=function(e,t,n){t||(t=C.identity);var i=!0;return null==e?i:y&&e.every===y?e.every(t,n):(E(e,function(e,o,s){return(i=i&&t.call(n,e,o,s))?void 0:r}),!!i)};var _=C.some=C.any=function(e,t,n){t||(t=C.identity);var i=!1;return null==e?i:b&&e.some===b?e.some(t,n):(E(e,function(e,o,s){return i||(i=t.call(n,e,o,s))?r:void 0}),!!i)};C.contains=C.include=function(e,t){return null==e?!1:x&&e.indexOf===x?-1!=e.indexOf(t):_(e,function(e){return e===t})},C.invoke=function(e,t){var n=u.call(arguments,2),i=C.isFunction(t);return C.map(e,function(e){return(i?t:e[t]).apply(e,n)})},C.pluck=function(e,t){return C.map(e,C.property(t))},C.where=function(e,t){return C.filter(e,C.matches(t))},C.findWhere=function(e,t){return C.find(e,C.matches(t))},C.max=function(e,t,n){if(!t&&C.isArray(e)&&e[0]===+e[0]&&65535>e.length)return Math.max.apply(Math,e);var i=-1/0,r=-1/0;return E(e,function(e,o,s){var a=t?t.call(n,e,o,s):e;a>r&&(i=e,r=a)}),i},C.min=function(e,t,n){if(!t&&C.isArray(e)&&e[0]===+e[0]&&65535>e.length)return Math.min.apply(Math,e);var i=1/0,r=1/0;return E(e,function(e,o,s){var a=t?t.call(n,e,o,s):e;r>a&&(i=e,r=a)}),i},C.shuffle=function(e){var t,n=0,i=[];return E(e,function(e){t=C.random(n++),i[n-1]=i[t],i[t]=e}),i},C.sample=function(e,t,n){return null==t||n?(e.length!==+e.length&&(e=C.values(e)),e[C.random(e.length-1)]):C.shuffle(e).slice(0,Math.max(0,t))};var R=function(e){return null==e?C.identity:C.isFunction(e)?e:C.property(e)};C.sortBy=function(e,t,n){return t=R(t),C.pluck(C.map(e,function(e,i,r){return{value:e,index:i,criteria:t.call(n,e,i,r)}}).sort(function(e,t){var n=e.criteria,i=t.criteria;if(n!==i){if(n>i||void 0===n)return 1;if(i>n||void 0===i)return-1}return e.index-t.index}),"value")};var q=function(e){return function(t,n,i){var r={};return n=R(n),E(t,function(o,s){var a=n.call(i,o,s,t);e(r,a,o)}),r}};C.groupBy=q(function(e,t,n){C.has(e,t)?e[t].push(n):e[t]=[n]}),C.indexBy=q(function(e,t,n){e[t]=n}),C.countBy=q(function(e,t){C.has(e,t)?e[t]++:e[t]=1}),C.sortedIndex=function(e,t,n,i){n=R(n);for(var r=n.call(i,t),o=0,s=e.length;s>o;){var a=o+s>>>1;r>n.call(i,e[a])?o=a+1:s=a}return o},C.toArray=function(e){return e?C.isArray(e)?u.call(e):e.length===+e.length?C.map(e,C.identity):C.values(e):[]},C.size=function(e){return null==e?0:e.length===+e.length?e.length:C.keys(e).length},C.first=C.head=C.take=function(e,t,n){return null==e?void 0:null==t||n?e[0]:0>t?[]:u.call(e,0,t)},C.initial=function(e,t,n){return u.call(e,0,e.length-(null==t||n?1:t))},C.last=function(e,t,n){return null==e?void 0:null==t||n?e[e.length-1]:u.call(e,Math.max(e.length-t,0))},C.rest=C.tail=C.drop=function(e,t,n){return u.call(e,null==t||n?1:t)},C.compact=function(e){return C.filter(e,C.identity)};var A=function(e,t,n){return t&&C.every(e,C.isArray)?l.apply(n,e):(E(e,function(e){C.isArray(e)||C.isArguments(e)?t?c.apply(n,e):A(e,t,n):n.push(e)}),n)};C.flatten=function(e,t){return A(e,t,[])},C.without=function(e){return C.difference(e,u.call(arguments,1))},C.partition=function(e,t,n){t=R(t);var i=[],r=[];return E(e,function(e){(t.call(n,e)?i:r).push(e)}),[i,r]},C.uniq=C.unique=function(e,t,n,i){C.isFunction(t)&&(i=n,n=t,t=!1);var r=n?C.map(e,n,i):e,o=[],s=[];return E(r,function(n,i){(t?i&&s[s.length-1]===n:C.contains(s,n))||(s.push(n),o.push(e[i]))}),o},C.union=function(){return C.uniq(C.flatten(arguments,!0))},C.intersection=function(e){var t=u.call(arguments,1);return C.filter(C.uniq(e),function(e){return C.every(t,function(t){return C.contains(t,e)})})},C.difference=function(e){var t=l.apply(o,u.call(arguments,1));return C.filter(e,function(e){return!C.contains(t,e)})},C.zip=function(){for(var e=C.max(C.pluck(arguments,"length").concat(0)),t=Array(e),n=0;e>n;n++)t[n]=C.pluck(arguments,""+n);return t},C.object=function(e,t){if(null==e)return{};for(var n={},i=0,r=e.length;r>i;i++)t?n[e[i]]=t[i]:n[e[i][0]]=e[i][1];return n},C.indexOf=function(e,t,n){if(null==e)return-1;var i=0,r=e.length;if(n){if("number"!=typeof n)return i=C.sortedIndex(e,t),e[i]===t?i:-1;i=0>n?Math.max(0,r+n):n}if(x&&e.indexOf===x)return e.indexOf(t,n);for(;r>i;i++)if(e[i]===t)return i;return-1},C.lastIndexOf=function(e,t,n){if(null==e)return-1;var i=null!=n;if(w&&e.lastIndexOf===w)return i?e.lastIndexOf(t,n):e.lastIndexOf(t);for(var r=i?n:e.length;r--;)if(e[r]===t)return r;return-1},C.range=function(e,t,n){1>=arguments.length&&(t=e||0,e=0),n=arguments[2]||1;for(var i=Math.max(Math.ceil((t-e)/n),0),r=0,o=Array(i);i>r;)o[r++]=e,e+=n;return o};var N=function(){};C.bind=function(e,t){var n,i;if(T&&e.bind===T)return T.apply(e,u.call(arguments,1));if(!C.isFunction(e))throw new TypeError;return n=u.call(arguments,2),i=function(){if(!(this instanceof i))return e.apply(t,n.concat(u.call(arguments)));N.prototype=e.prototype;var r=new N;N.prototype=null;var o=e.apply(r,n.concat(u.call(arguments)));return Object(o)===o?o:r}},C.partial=function(e){var t=u.call(arguments,1);return function(){for(var n=0,i=t.slice(),r=0,o=i.length;o>r;r++)i[r]===C&&(i[r]=arguments[n++]);for(;arguments.length>n;)i.push(arguments[n++]);return e.apply(this,i)}},C.bindAll=function(e){var t=u.call(arguments,1);if(0===t.length)throw Error("bindAll must be passed function names");return E(t,function(t){e[t]=C.bind(e[t],e)}),e},C.memoize=function(e,t){var n={};return t||(t=C.identity),function(){var i=t.apply(this,arguments);return C.has(n,i)?n[i]:n[i]=e.apply(this,arguments)}},C.delay=function(e,t){var n=u.call(arguments,2);return setTimeout(function(){return e.apply(null,n)},t)},C.defer=function(e){return C.delay.apply(C,[e,1].concat(u.call(arguments,1)))},C.throttle=function(e,t,n){var i,r,o,s=null,a=0;n||(n={});var c=function(){a=n.leading===!1?0:C.now(),s=null,o=e.apply(i,r),i=r=null};return function(){var u=C.now();a||n.leading!==!1||(a=u);var l=t-(u-a);return i=this,r=arguments,0>=l?(clearTimeout(s),s=null,a=u,o=e.apply(i,r),i=r=null):s||n.trailing===!1||(s=setTimeout(c,l)),o}},C.debounce=function(e,t,n){var i,r,o,s,a,c=function(){var u=C.now()-s;t>u?i=setTimeout(c,t-u):(i=null,n||(a=e.apply(o,r),o=r=null))};return function(){o=this,r=arguments,s=C.now();var u=n&&!i;return i||(i=setTimeout(c,t)),u&&(a=e.apply(o,r),o=r=null),a}},C.once=function(e){var t,n=!1;return function(){return n?t:(n=!0,t=e.apply(this,arguments),e=null,t)}},C.wrap=function(e,t){return C.partial(t,e)},C.compose=function(){var e=arguments;return function(){for(var t=arguments,n=e.length-1;n>=0;n--)t=[e[n].apply(this,t)];return t[0]}},C.after=function(e,t){return function(){return 1>--e?t.apply(this,arguments):void 0}},C.keys=function(e){if(!C.isObject(e))return[];if(k)return k(e);var t=[];for(var n in e)C.has(e,n)&&t.push(n);return t},C.values=function(e){for(var t=C.keys(e),n=t.length,i=Array(n),r=0;n>r;r++)i[r]=e[t[r]];return i},C.pairs=function(e){for(var t=C.keys(e),n=t.length,i=Array(n),r=0;n>r;r++)i[r]=[t[r],e[t[r]]];return i},C.invert=function(e){for(var t={},n=C.keys(e),i=0,r=n.length;r>i;i++)t[e[n[i]]]=n[i];return t},C.functions=C.methods=function(e){var t=[];for(var n in e)C.isFunction(e[n])&&t.push(n);return t.sort()},C.extend=function(e){return E(u.call(arguments,1),function(t){if(t)for(var n in t)e[n]=t[n]}),e},C.pick=function(e){var t={},n=l.apply(o,u.call(arguments,1));return E(n,function(n){n in e&&(t[n]=e[n])}),t},C.omit=function(e){var t={},n=l.apply(o,u.call(arguments,1));for(var i in e)C.contains(n,i)||(t[i]=e[i]);return t},C.defaults=function(e){return E(u.call(arguments,1),function(t){if(t)for(var n in t)void 0===e[n]&&(e[n]=t[n])}),e},C.clone=function(e){return C.isObject(e)?C.isArray(e)?e.slice():C.extend({},e):e},C.tap=function(e,t){return t(e),e};var L=function(e,t,n,i){if(e===t)return 0!==e||1/e==1/t;if(null==e||null==t)return e===t;e instanceof C&&(e=e._wrapped),t instanceof C&&(t=t._wrapped);var r=f.call(e);if(r!=f.call(t))return!1;switch(r){case"[object String]":return e==t+"";case"[object Number]":return e!=+e?t!=+t:0==e?1/e==1/t:e==+t;case"[object Date]":case"[object Boolean]":return+e==+t;case"[object RegExp]":return e.source==t.source&&e.global==t.global&&e.multiline==t.multiline&&e.ignoreCase==t.ignoreCase}if("object"!=typeof e||"object"!=typeof t)return!1;for(var o=n.length;o--;)if(n[o]==e)return i[o]==t;var s=e.constructor,a=t.constructor;if(s!==a&&!(C.isFunction(s)&&s instanceof s&&C.isFunction(a)&&a instanceof a)&&"constructor"in e&&"constructor"in t)return!1;n.push(e),i.push(t);var c=0,u=!0;if("[object Array]"==r){if(c=e.length,u=c==t.length)for(;c--&&(u=L(e[c],t[c],n,i)););}else{for(var l in e)if(C.has(e,l)&&(c++,!(u=C.has(t,l)&&L(e[l],t[l],n,i))))break;if(u){for(l in t)if(C.has(t,l)&&!c--)break;u=!c}}return n.pop(),i.pop(),u};C.isEqual=function(e,t){return L(e,t,[],[])},C.isEmpty=function(e){if(null==e)return!0;if(C.isArray(e)||C.isString(e))return 0===e.length;for(var t in e)if(C.has(e,t))return!1;return!0},C.isElement=function(e){return!(!e||1!==e.nodeType)},C.isArray=S||function(e){return"[object Array]"==f.call(e)},C.isObject=function(e){return e===Object(e)},E(["Arguments","Function","String","Number","Date","RegExp"],function(e){C["is"+e]=function(t){return f.call(t)=="[object "+e+"]"}}),C.isArguments(arguments)||(C.isArguments=function(e){return!(!e||!C.has(e,"callee"))}),C.isFunction=function(e){return"function"==typeof e},C.isFinite=function(e){return isFinite(e)&&!isNaN(parseFloat(e))},C.isNaN=function(e){return C.isNumber(e)&&e!=+e},C.isBoolean=function(e){return e===!0||e===!1||"[object Boolean]"==f.call(e)},C.isNull=function(e){return null===e},C.isUndefined=function(e){return void 0===e},C.has=function(e,t){return p.call(e,t)},C.noConflict=function(){return e._=i,this},C.identity=function(e){return e},C.constant=function(e){return function(){return e}},C.property=function(e){return function(t){return t[e]}},C.matches=function(e){return function(t){if(t===e)return!0;for(var n in e)if(e[n]!==t[n])return!1;return!0}},C.times=function(e,t,n){for(var i=Array(Math.max(0,e)),r=0;e>r;r++)i[r]=t.call(n,r);return i},C.random=function(e,t){return null==t&&(t=e,e=0),e+Math.floor(Math.random()*(t-e+1))},C.now=Date.now||function(){return(new Date).getTime()};var D={escape:{"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#x27;"}};D.unescape=C.invert(D.escape);var O={escape:RegExp("["+C.keys(D.escape).join("")+"]","g"),unescape:RegExp("("+C.keys(D.unescape).join("|")+")","g")};C.each(["escape","unescape"],function(e){C[e]=function(t){return null==t?"":(""+t).replace(O[e],function(t){return D[e][t]})}}),C.result=function(e,t){if(null==e)return void 0;var n=e[t];return C.isFunction(n)?n.call(e):n},C.mixin=function(e){E(C.functions(e),function(t){var n=C[t]=e[t];C.prototype[t]=function(){var e=[this._wrapped];return c.apply(e,arguments),W.call(this,n.apply(C,e))}})};var j=0;C.uniqueId=function(e){var t=++j+"";return e?e+t:t},C.templateSettings={evaluate:/<%([\s\S]+?)%>/g,interpolate:/<%=([\s\S]+?)%>/g,escape:/<%-([\s\S]+?)%>/g};var M=/(.)^/,$={"'":"'","\\":"\\","\r":"r","\n":"n","	":"t","\u2028":"u2028","\u2029":"u2029"},I=/\\|'|\r|\n|\t|\u2028|\u2029/g;C.template=function(e,t,n){var i;n=C.defaults({},n,C.templateSettings);var r=RegExp([(n.escape||M).source,(n.interpolate||M).source,(n.evaluate||M).source].join("|")+"|$","g"),o=0,s="__p+='";e.replace(r,function(t,n,i,r,a){return s+=e.slice(o,a).replace(I,function(e){return"\\"+$[e]}),n&&(s+="'+\n((__t=("+n+"))==null?'':_.escape(__t))+\n'"),i&&(s+="'+\n((__t=("+i+"))==null?'':__t)+\n'"),r&&(s+="';\n"+r+"\n__p+='"),o=a+t.length,t}),s+="';\n",n.variable||(s="with(obj||{}){\n"+s+"}\n"),s="var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};\n"+s+"return __p;\n";try{i=Function(n.variable||"obj","_",s)}catch(a){throw a.source=s,a}if(t)return i(t,C);var c=function(e){return i.call(this,e,C)};return c.source="function("+(n.variable||"obj")+"){\n"+s+"}",c},C.chain=function(e){return C(e).chain()};var W=function(e){return this._chain?C(e).chain():e};C.mixin(C),E(["pop","push","reverse","shift","sort","splice","unshift"],function(e){var t=o[e];C.prototype[e]=function(){var n=this._wrapped;return t.apply(n,arguments),"shift"!=e&&"splice"!=e||0!==n.length||delete n[0],W.call(this,n)}}),E(["concat","join","slice"],function(e){var t=o[e];C.prototype[e]=function(){return W.call(this,t.apply(this._wrapped,arguments))}}),C.extend(C.prototype,{chain:function(){return this._chain=!0,this},value:function(){return this._wrapped}}),"function"==typeof define&&define.amd&&define("underscore",[],function(){return C}),window._=C}).call(this)});