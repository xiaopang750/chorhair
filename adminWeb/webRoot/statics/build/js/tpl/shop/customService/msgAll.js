/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("tpl/shop/customService/msgAll",["../../templates"],function(e){return e("../../templates")("shop/customService/msgAll",function(e){"use strict";var t=this,i=(t.$helpers,e.customer),r=t.$escape,n=t.$each,o=(e.$value,e.$index,e.fairer),a="";return a+=" ",i.length&&(a+=' <li class="group" group="001"> <span class="group-head ba-pointer"> <span class="fa fa-caret-right"></span> <span class="fa fa-caret-down"></span> 消费者(',a+=r(i.length),a+=') </span> <ul class="user-wrap"> ',n(i,function(e){a+=' <li cancelBubble="true"> <input type="checkbox" id="group',a+=r(e.pkUser),a+='" msgUid="',a+=r(e.pkUser),a+='" cancelBubble="true"> <label uid="',a+=r(e.pkUser),a+='" for="group',a+=r(e.pkUser),a+='" class="ba-pointer" cancelBubble="true">',a+=r(e.customername),a+="</label> </li> "}),a+=" </ul> </li> "),a+=" <!-- ",o.length&&(a+=' <li class="group" group="002"> <span class="group-head ba-pointer"> <span class="fa fa-caret-right"></span> <span class="fa fa-caret-down"></span> 理发师(',a+=r(o.length),a+=') </span> <ul class="user-wrap"> ',n(o,function(e){a+=' <li user-list="true" cancelBubble="true"> <input type="checkbox" id="group',a+=r(e.pkUser),a+='" msgUid="',a+=r(e.pkUser),a+='" cancelBubble="true"> <label uid="',a+=r(e.pkUser),a+='" for="group',a+=r(e.pkUser),a+='" class="ba-pointer" cancelBubble="true">',a+=r(e.fairername),a+="</label> </li> "}),a+=" </ul> </li> "),a+=" -->",new String(a)})}),!function(){function e(e,t){return(/string|function/.test(typeof t)?s:a)(e,t)}function t(e,i){return"string"!=typeof e&&(i=typeof e,"number"===i?e+="":e="function"===i?t(e.call(e)):""),e}function i(e){return d[e]}function r(e){return t(e).replace(/&(?![\w#]+;)|[<>"']/g,i)}function n(e,t){if(f(e))for(var i=0,r=e.length;r>i;i++)t.call(e,e[i],i,e);else for(i in e)t.call(e,e[i],i)}function o(e,t){var i=/(\/)[^/]+\1\.\.\1/,r=("./"+e).replace(/[^/]+$/,""),n=r+t;for(n=n.replace(/\/\.\//g,"/");n.match(i);)n=n.replace(i,"/");return n}function a(t,i){var r=e.get(t)||c({filename:t,name:"Render Error",message:"Template not found"});return i?r(i):r}function s(e,t){if("string"==typeof t){var i=t;t=function(){return new u(i)}}var r=l[e]=function(i){try{return new t(i,e)+""}catch(r){return c(r)()}};return r.prototype=t.prototype=h,r.toString=function(){return t+""},r}function c(e){var t="{Template Error}",i=e.stack||"";if(i)i=i.split("\n").slice(0,2).join("\n");else for(var r in e)i+="<"+r+">\n"+e[r]+"\n\n";return function(){return"object"==typeof console&&console.error(t+"\n\n"+i),t}}var l=e.cache={},u=this.String,d={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"},f=Array.isArray||function(e){return"[object Array]"==={}.toString.call(e)},h=e.utils={$helpers:{},$include:function(e,t,i){return e=o(i,e),a(e,t)},$string:t,$escape:r,$each:n},p=e.helpers=h.$helpers;e.get=function(e){return l[e.replace(/^\.\//,"")]},e.helper=function(e,t){p[e]=t},define("tpl/templates",[],function(){return e}),e.helper("getRoute",function(e){return R.route[e].url}),e.helper("userType",function(){return R.userType}),e.helper("timeLeft",function(e,t){var i=new Date(t.replace(/\-/gi,"/")).getTime(),r=new Date(e.replace(/\-/gi,"/")).getTime(),n=r-i,o=parseInt(n/1e3/60/60/24,10);return 0>o&&(o=0),o}),e.helper("imgPath",function(e){return e?R.uri.assets+e:R.uri.assets}),e.helper("jp",function(e){return JSON.parse(e)}),e.helper("cut",function(e,t){var i;return"string"!=typeof e?e:(i=e.length,t>=i?e:e.substring(0,t)+"...")}),e.helper("rnd",function(e,t,i){return parseInt(Math.random()*(t+1-i)+i)}),e.helper("changeIndex",function(e){var t={0:"一",1:"二",2:"三",3:"四",4:"五",5:"六"};return t[e]}),e.helper("cutDomain",function(e){e.length;var t=/http\:\S+\d+\//gi,i=e.match(t)[0];return i}),e.helper("cutDomainName",function(e){e.length;var t=/http\:\S+\d+\//gi,i=e.match(t)[0],r=i.length,n=e.substring(r);return n=n.replace(/\.html/,"")}),e.helper("nowTime",function(){function e(e){return 10>e?"0"+e:e}var t,i,r,n,o,r,a,s;return t=new Date,i=t.getFullYear(),month=t.getMonth()+1,n=t.getDate(),o=t.getHours(),r=t.getMinutes(),a=t.getSeconds(),s=i+"-"+e(month)+"-"+e(n)+" "+e(o)+":"+e(r)+":"+e(a)}),e.helper("toDouble",function(e){return 10>e?"0"+e:e})}();