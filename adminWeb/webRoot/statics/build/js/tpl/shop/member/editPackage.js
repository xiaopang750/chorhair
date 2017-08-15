/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("tpl/shop/member/editPackage",["../../templates"],function(e){return e("../../templates")("shop/member/editPackage",function(e){"use strict";var t=this,r=(t.$helpers,e.nowWay),i=t.$escape,n=e.comboname,o=e.totalcount,a=e.combobuytime,s=e.combobegintime,c=e.comboendtime,l=e.fairername,u=e.commissionpeople,d=t.$each,f=(e.$value,e.$index,e.discount),h=e.combomoney,p=e.pkShopCombo,m=e.pkCustomerCombo,g="";return g+=' <div class="edit-box" script-bound="package-wrap"> <table class="no-border table ba-tl" width="100%"> <tr> <td width="90"> 选择套餐名称： </td> <td colspan="3" width="300"> <div script-role="check-wrap"> <input type="text" class="form-control col-8 ',"edit"==r&&(g+="noInput"),g+='" form_check="sys" ischeck="true" name="comboname" tip="此项为必填" wrong="此项为必填" re="(.+)" comboname value="',g+=i(n),g+='" ',"edit"==r&&(g+='infoDisabled="infoDisabled"'),g+='> </div> </td> </tr> <tr> <td width="90"> 套餐次数： </td> <td colspan="3"> <input type="text" class="form-control col-8 ',"edit"==r&&(g+="noInput"),g+='" value="',g+=i(o),g+='" disabled="disabled" pack-count> </td> </tr> <!-- <tr> <td width="90"> 购买时间： </td> <td colspan="3"> <input type="text" class="form-control combobuytime" name="combobuytime" value="',g+=i(a),g+='" disabled="disabled"> </td> </tr> <tr> <td width="90"> 开卡时间： </td> <td> <div script-role="check-wrap"> <input type="text" class="form-control" box-start name="combobegintime" value="',g+=i(s),g+='" disabled="disabled"> </div> </td> <td> 结束时间： </td> <td> <div script-role="check-wrap"> <input type="text" class="form-control" box-end name="comboendtime" value="',g+=i(c),g+='" disabled="disabled"> </div> </td> </tr> --> <tr> <td width="90" style="vertical-align:top;"> 提成方式： </td> <td colspan="3"> <div script-role="check-wrap"> <!-- <input type="text" class="form-control col-8 ',"edit"==r&&(g+="noInput"),g+='" form_check="sys" ischeck="true" name="fairername" tip="此项为必填" wrong="此项为必填" re="(.+)" fairer readonly="readonly" value="',g+=i(l),g+='" ',"edit"==r&&(g+='infoDisabled="infoDisabled"'),g+="> --> ","edit"!=r?g+=' <ul class="ba-clearfix" ticheng-list-wrap> </ul> ':(g+=" ",u.length&&(g+=" ",d(u,function(e){g+=' <li class="form-group ba-fl col-3 ba-mr-20" ticheng-item> <label class="ba-mb-2 ba-mr-5 ba-fl">',g+=i(e.awardname),g+=':</label> <input type="text" class="form-control noInput" readonly="readonly" value="',g+=i(e.fairername),g+='"> </li> '}),g+=" "),g+=" "),g+=' </div> </td> </tr> <tr> <td width="90"> 优惠金额： </td> <td colspan="3"> <div script-role="check-wrap"> <input type="text" class="form-control col-6 ',"edit"==r&&(g+="noInput"),g+='" sale form_check="sys" ischeck="free" name="discount" tip="此项为必填" wrong="优惠金额格式不正确" re="((\\d|\\.)+)" value="',g+=i(f),g+='" ',"edit"==r&&(g+='disabled="disabled"'),g+='> </div> </td> </tr> <tr sale-wrap class="ba-none"> <td width="90"> 选择抵用券： </td> <td colspan="3"> <div script-role="check-wrap"> <select sale-list-wrap class="form-control"> </select> </div> </td> </tr> <tr> <td width="90"> 合计： </td> <td colspan="3"> <span sum> ',h?(g+=" ",g+=i(h),g+=" "):g+=" 0 ",g+=' </span>元 </td> </tr> </table> <div class="ba-tc"> <a href="javascript:;" class="btn btn-primary" script-role="package-btn" money="',g+=i(h),g+='" pkShopCombo="',g+=i(p),g+='" fairerStr="',g+=i(u),g+='" pkCustomerCombo="',g+=i(m),g+='"> 确定 </a> <a href="javascript:;" class="btn btn-danger" sc="close">取消</a> </div> </div> ',new String(g)})}),!function(){function e(e,t){return(/string|function/.test(typeof t)?s:a)(e,t)}function t(e,r){return"string"!=typeof e&&(r=typeof e,"number"===r?e+="":e="function"===r?t(e.call(e)):""),e}function r(e){return d[e]}function i(e){return t(e).replace(/&(?![\w#]+;)|[<>"']/g,r)}function n(e,t){if(f(e))for(var r=0,i=e.length;i>r;r++)t.call(e,e[r],r,e);else for(r in e)t.call(e,e[r],r)}function o(e,t){var r=/(\/)[^/]+\1\.\.\1/,i=("./"+e).replace(/[^/]+$/,""),n=i+t;for(n=n.replace(/\/\.\//g,"/");n.match(r);)n=n.replace(r,"/");return n}function a(t,r){var i=e.get(t)||c({filename:t,name:"Render Error",message:"Template not found"});return r?i(r):i}function s(e,t){if("string"==typeof t){var r=t;t=function(){return new u(r)}}var i=l[e]=function(r){try{return new t(r,e)+""}catch(i){return c(i)()}};return i.prototype=t.prototype=h,i.toString=function(){return t+""},i}function c(e){var t="{Template Error}",r=e.stack||"";if(r)r=r.split("\n").slice(0,2).join("\n");else for(var i in e)r+="<"+i+">\n"+e[i]+"\n\n";return function(){return"object"==typeof console&&console.error(t+"\n\n"+r),t}}var l=e.cache={},u=this.String,d={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"},f=Array.isArray||function(e){return"[object Array]"==={}.toString.call(e)},h=e.utils={$helpers:{},$include:function(e,t,r){return e=o(r,e),a(e,t)},$string:t,$escape:i,$each:n},p=e.helpers=h.$helpers;e.get=function(e){return l[e.replace(/^\.\//,"")]},e.helper=function(e,t){p[e]=t},define("tpl/templates",[],function(){return e}),e.helper("getRoute",function(e){return R.route[e].url}),e.helper("userType",function(){return R.userType}),e.helper("timeLeft",function(e,t){var r=new Date(t.replace(/\-/gi,"/")).getTime(),i=new Date(e.replace(/\-/gi,"/")).getTime(),n=i-r,o=parseInt(n/1e3/60/60/24,10);return 0>o&&(o=0),o}),e.helper("imgPath",function(e){return e?R.uri.assets+e:R.uri.assets}),e.helper("jp",function(e){return JSON.parse(e)}),e.helper("cut",function(e,t){var r;return"string"!=typeof e?e:(r=e.length,t>=r?e:e.substring(0,t)+"...")}),e.helper("rnd",function(e,t,r){return parseInt(Math.random()*(t+1-r)+r)}),e.helper("changeIndex",function(e){var t={0:"一",1:"二",2:"三",3:"四",4:"五",5:"六"};return t[e]}),e.helper("cutDomain",function(e){e.length;var t=/http\:\S+\d+\//gi,r=e.match(t)[0];return r}),e.helper("cutDomainName",function(e){e.length;var t=/http\:\S+\d+\//gi,r=e.match(t)[0],i=r.length,n=e.substring(i);return n=n.replace(/\.html/,"")}),e.helper("nowTime",function(){function e(e){return 10>e?"0"+e:e}var t,r,i,n,o,i,a,s;return t=new Date,r=t.getFullYear(),month=t.getMonth()+1,n=t.getDate(),o=t.getHours(),i=t.getMinutes(),a=t.getSeconds(),s=r+"-"+e(month)+"-"+e(n)+" "+e(o)+":"+e(i)+":"+e(a)}),e.helper("toDouble",function(e){return 10>e?"0"+e:e})}();