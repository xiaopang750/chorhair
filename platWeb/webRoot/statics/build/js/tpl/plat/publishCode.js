/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("tpl/plat/publishCode",["../templates"],function(e){return e("../templates")("plat/publishCode",' <div class="dialog-box publish-box" script-bound="form-check"> <span class="fa ba-fr fa-close" sc="close"></span> <div class="ba-mt-10"> <label class="ba-mr-10"> <input type="radio" name="code-type" code-type="shop"><span>店铺二维码</span> </label> <label> <input type="radio" name="code-type" code-type="plat"><span>平台二维码</span> </label> </div> <div class="select-area ba-mt-20 ba-clearfix"> <div class="related-select" area-wrap script-role="check-wrap"> <select class="form-control" ischeck="true" form_check="sys" tip="请选择省" wrong="" re=".+" province name="province" pkArea="" areaname=""> <option value="">请选择省</option> </select> </div> <div class="related-select" area-wrap script-role="check-wrap"> <select class="form-control" ischeck="true" form_check="sys" tip="请选择市" wrong="" re=".+" city name="city" pkArea="" areaname=""> <option value="">请选择市</option> </select> </div> <div class="related-select" area-wrap script-role="check-wrap"> <select class="form-control" ischeck="true" form_check="sys" tip="请选择区" wrong="" re=".+" area name="county" pkArea="" areaname=""> <option value="">请选择区</option> </select> </div> <div class="shop-select" script-role="check-wrap"> <select class="form-control" ischeck="true" form_check="sys" tip="请选择区" wrong="" re=".+" shop> <option value="">请选择店铺</option> </select> </div> </div> <div class="field ba-mt-20" user-defined> <input class="form-control" type="text" placeholder="描述"> </div> <div class="generate ba-tc ba-mt-25 ba-mb-20"> <a href="javascript:;" class="btn btn-primary" sc="publish-confirm" script-role="confirm-btn">生成二维码</a> </div> </div>')}),!function(){function e(e,t){return(/string|function/.test(typeof t)?s:a)(e,t)}function t(e,r){return"string"!=typeof e&&(r=typeof e,"number"===r?e+="":e="function"===r?t(e.call(e)):""),e}function r(e){return d[e]}function i(e){return t(e).replace(/&(?![\w#]+;)|[<>"']/g,r)}function n(e,t){if(h(e))for(var r=0,i=e.length;i>r;r++)t.call(e,e[r],r,e);else for(r in e)t.call(e,e[r],r)}function o(e,t){var r=/(\/)[^/]+\1\.\.\1/,i=("./"+e).replace(/[^/]+$/,""),n=i+t;for(n=n.replace(/\/\.\//g,"/");n.match(r);)n=n.replace(r,"/");return n}function a(t,r){var i=e.get(t)||c({filename:t,name:"Render Error",message:"Template not found"});return r?i(r):i}function s(e,t){if("string"==typeof t){var r=t;t=function(){return new u(r)}}var i=l[e]=function(r){try{return new t(r,e)+""}catch(i){return c(i)()}};return i.prototype=t.prototype=f,i.toString=function(){return t+""},i}function c(e){var t="{Template Error}",r=e.stack||"";if(r)r=r.split("\n").slice(0,2).join("\n");else for(var i in e)r+="<"+i+">\n"+e[i]+"\n\n";return function(){return"object"==typeof console&&console.error(t+"\n\n"+r),t}}var l=e.cache={},u=this.String,d={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"},h=Array.isArray||function(e){return"[object Array]"==={}.toString.call(e)},f=e.utils={$helpers:{},$include:function(e,t,r){return e=o(r,e),a(e,t)},$string:t,$escape:i,$each:n},p=e.helpers=f.$helpers;e.get=function(e){return l[e.replace(/^\.\//,"")]},e.helper=function(e,t){p[e]=t},define("tpl/templates",[],function(){return e}),e.helper("getRoute",function(e){return R.route[e].url}),e.helper("userType",function(){return R.userType}),e.helper("timeLeft",function(e,t){var r=new Date(t.replace(/\-/gi,"/")).getTime(),i=new Date(e.replace(/\-/gi,"/")).getTime(),n=i-r,o=parseInt(n/1e3/60/60/24,10);return 0>o&&(o=0),o}),e.helper("imgPath",function(e){return e?R.uri.assets+e:R.uri.assets}),e.helper("jp",function(e){return JSON.parse(e)}),e.helper("cut",function(e,t){var r;return"string"!=typeof e?e:(r=e.length,t>=r?e:e.substring(0,t)+"...")}),e.helper("rnd",function(e,t,r){return parseInt(Math.random()*(t+1-r)+r)}),e.helper("changeIndex",function(e){var t={0:"一",1:"二",2:"三",3:"四",4:"五",5:"六"};return t[e]}),e.helper("cutDomain",function(e){e.length;var t=/http\:\S+\d+\//gi,r=e.match(t)[0];return r}),e.helper("cutDomainName",function(e){e.length;var t=/http\:\S+\d+\//gi,r=e.match(t)[0],i=r.length,n=e.substring(i);return n=n.replace(/\.html/,"")}),e.helper("nowTime",function(){function e(e){return 10>e?"0"+e:e}var t,r,i,n,o,i,a,s;return t=new Date,r=t.getFullYear(),month=t.getMonth()+1,n=t.getDate(),o=t.getHours(),i=t.getMinutes(),a=t.getSeconds(),s=r+"-"+e(month)+"-"+e(n)+" "+e(o)+":"+e(i)+":"+e(a)}),e.helper("toDouble",function(e){return 10>e?"0"+e:e})}();