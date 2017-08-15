/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("tpl/system/fmRight",["./fmRightlist","../templates"],function(e){return e("./fmRightlist"),e("../templates")("system/fmRight",function(e,t){"use strict";var r=this,i=(r.$helpers,r.$escape),n=e.data,o=function(i,n){n=n||e;var o=r.$include(i,n,t);return a+=o},a="";return a+=' <table width="100%" class="ba-mb-20"> <tr> <td> 名称 </td> <td> <input type="text" class="form-control" value="',a+=i(n.servicerank),a+='" ',"look"==n.type&&(a+='disabled="disabled"'),a+=' org-sub-name="servicerank" re="^.{1,10}$" msg="请填写10位数以内的名称" tip="名称不能为空"> </td> <td> 价位 </td> <td> <input type="text" class="form-control" value="',a+=i(n.price),a+='" ',"look"==n.type&&(a+='disabled="disabled"'),a+=' org-sub-name="price" re="^\\d+$" msg="价位只能为数字" tip="价位不能为空"> </td> </tr> </table> <table width="100%" class="ba-mb-10 table bordered"> <thead> <tr> <td width="20%">序号</td> <td width="20%">提成名称</td> <td width="20%">提成方式</td> <td width="20%">提成值</td> <td width="20%">操作</td> </tr> </thead> <tbody data-list> ',o("./fmRightlist",n),a+=" </tbody> </table> ","look"!=n.type&&(a+=' <div class="ba-tc ba-mb-20"> <span class="fa fa-plus" data-list-plus></span> </div> <div class="ba-tc"> <a href="javascript:;" class="btn btn-primary" confirm-save>保存</a> </div> '),new String(a)})}),define("tpl/system/fmRightlist",["tpl/templates"],function(e){return e("tpl/templates")("system/fmRightlist",function(e){"use strict";var t=this,r=(t.$helpers,t.$each),i=e.list,n=(e.$value,e.$index,t.$escape),o=e.type,a="";return a+=" ",r(i,function(e,t){a+=" <tr sub-name-list> <td list-sort> ",a+=n(t+1),a+=' </td> <td can-edit sub-name="awardname" re="^.{1,10}$" msg="提成名称为10个字以内" tip="提成名称不能为空"> ',a+=n(e.awardname),a+=" </td> <td> <select ","look"==o&&(a+='disabled="disabled"'),a+=' sub-name="israte" re="^.+$" msg="请选择提成方式" tip="请选择提成方式" class="form-control"> <option value="">请选择</option> <option value="1" ',1==e.israte&&(a+='selected="selected"'),a+='>固定额度</option> <option value="2" ',2==e.israte&&(a+='selected="selected"'),a+='>比例提成</option> </select> </td> <td can-edit sub-name="awardmoney" ',a+=1==e.israte?'re="^\\d+$" msg="固定额度只能为数字" tip="固定额度不能为空"':2==e.israte?'re="^([1-9]\\d?)|100$" msg="提成比例为100以内的数字" tip="提成比例不能为空"':'ignorecheck="yes"',a+="> ",a+=n(e.awardmoney),a+=" </td> <td> ","look"!=o&&(a+=' <a href="javascript:;" aid="',a+=n(e.pkFaireraward),a+='" edit>编辑</a> <a href="javascript:;" addAfterNeedId aid="',a+=n(e.pkFaireraward),a+='" disable="',a+=n(e.isvalidate),a+='"> ',a+="Y"==e.isvalidate?" 禁用 ":" 启用 ",a+=" </a> "),a+=' <span class="ba-none" sub-name="pkFaireraward" ignorecheck="yes">',a+=n(e.pkFaireraward),a+="</span> </td> </tr> "}),new String(a)})}),!function(){function e(e,t){return(/string|function/.test(typeof t)?s:a)(e,t)}function t(e,r){return"string"!=typeof e&&(r=typeof e,"number"===r?e+="":e="function"===r?t(e.call(e)):""),e}function r(e){return d[e]}function i(e){return t(e).replace(/&(?![\w#]+;)|[<>"']/g,r)}function n(e,t){if(f(e))for(var r=0,i=e.length;i>r;r++)t.call(e,e[r],r,e);else for(r in e)t.call(e,e[r],r)}function o(e,t){var r=/(\/)[^/]+\1\.\.\1/,i=("./"+e).replace(/[^/]+$/,""),n=i+t;for(n=n.replace(/\/\.\//g,"/");n.match(r);)n=n.replace(r,"/");return n}function a(t,r){var i=e.get(t)||c({filename:t,name:"Render Error",message:"Template not found"});return r?i(r):i}function s(e,t){if("string"==typeof t){var r=t;t=function(){return new u(r)}}var i=l[e]=function(r){try{return new t(r,e)+""}catch(i){return c(i)()}};return i.prototype=t.prototype=h,i.toString=function(){return t+""},i}function c(e){var t="{Template Error}",r=e.stack||"";if(r)r=r.split("\n").slice(0,2).join("\n");else for(var i in e)r+="<"+i+">\n"+e[i]+"\n\n";return function(){return"object"==typeof console&&console.error(t+"\n\n"+r),t}}var l=e.cache={},u=this.String,d={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"},f=Array.isArray||function(e){return"[object Array]"==={}.toString.call(e)},h=e.utils={$helpers:{},$include:function(e,t,r){return e=o(r,e),a(e,t)},$string:t,$escape:i,$each:n},p=e.helpers=h.$helpers;e.get=function(e){return l[e.replace(/^\.\//,"")]},e.helper=function(e,t){p[e]=t},define("tpl/templates",[],function(){return e}),e.helper("getRoute",function(e){return R.route[e].url}),e.helper("userType",function(){return R.userType}),e.helper("timeLeft",function(e,t){var r=new Date(t.replace(/\-/gi,"/")).getTime(),i=new Date(e.replace(/\-/gi,"/")).getTime(),n=i-r,o=parseInt(n/1e3/60/60/24,10);return 0>o&&(o=0),o}),e.helper("imgPath",function(e){return e?R.uri.assets+e:R.uri.assets}),e.helper("jp",function(e){return JSON.parse(e)}),e.helper("cut",function(e,t){var r;return"string"!=typeof e?e:(r=e.length,t>=r?e:e.substring(0,t)+"...")}),e.helper("rnd",function(e,t,r){return parseInt(Math.random()*(t+1-r)+r)}),e.helper("changeIndex",function(e){var t={0:"一",1:"二",2:"三",3:"四",4:"五",5:"六"};return t[e]}),e.helper("cutDomain",function(e){e.length;var t=/http\:\S+\d+\//gi,r=e.match(t)[0];return r}),e.helper("cutDomainName",function(e){e.length;var t=/http\:\S+\d+\//gi,r=e.match(t)[0],i=r.length,n=e.substring(i);return n=n.replace(/\.html/,"")}),e.helper("nowTime",function(){function e(e){return 10>e?"0"+e:e}var t,r,i,n,o,i,a,s;return t=new Date,r=t.getFullYear(),month=t.getMonth()+1,n=t.getDate(),o=t.getHours(),i=t.getMinutes(),a=t.getSeconds(),s=r+"-"+e(month)+"-"+e(n)+" "+e(o)+":"+e(i)+":"+e(a)}),e.helper("toDouble",function(e){return 10>e?"0"+e:e})}();