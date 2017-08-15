/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("main/shop/user/login",["../../../driver/R","../../../driver/config","../../../driver/interfaces/user","../../../driver/interfaces/member","../../../driver/interfaces/performance","../../../driver/interfaces/upload","../../../driver/interfaces/goods","../../../driver/interfaces/service","../../../driver/interfaces/content","../../../driver/interfaces/global","../../../driver/interfaces/customService","../../../driver/interfaces/system","../../../driver/interfaces/shop","../../../driver/interfaces/plat","../../../lib/ooClass/class","../../../driver/base/base_util","../../../util/http/bodyParse","../../../util/http/request","../../../widget/dom/tip","../../../widget/dom/dialog","../../../widget/form/ajaxForm","../../../util/cookie/cookie","../../../widget/dom/enterDo","../../../widget/dom/placeholder"],function(e){e("../../../driver/R");var t=e("../../../widget/form/ajaxForm"),i=e("../../../widget/dom/tip"),r=e("../../../util/cookie/cookie"),n=e("../../../widget/dom/enterDo"),o=e("../../../widget/dom/placeholder"),a=R.Class.create(R.util,{initialize:function(){this.aEenter=$("[enter-do]"),this.oRemember=$("[remember]"),this.oName=$("[name = usercode]"),this.oPass=$("[name = loginpassword]"),this.nowUserName="",this.form(),this.events(),this.showUserCode(),new o(this.oName),new o(this.oPass)},events:function(){var e=this;n(this.aEenter,function(){e.oLoginForm.subMit()})},showUserCode:function(){var e=r.getCookie("userName");e&&this.oName.val(e)},form:function(){var e=this;this.oLoginForm=new t({subUrl:R.interfaces.user.login,fnSumbit:function(t){return e.nowUserName=t.usercode,t.usergroup=R.nowWayId,t.isRemember=e.oRemember.attr("checked")?1:0,t},sucDo:function(t){r.setCookie("userName",e.nowUserName,"30day"),window.location=t.data.url},failDo:function(e){i.say(e.msg)}}),this.oLoginForm.upload()}});new a}),define("driver/R",["driver/config","driver/interfaces/user","driver/interfaces/member","driver/interfaces/performance","driver/interfaces/upload","driver/interfaces/goods","driver/interfaces/service","driver/interfaces/content","driver/interfaces/global","driver/interfaces/customService","driver/interfaces/system","driver/interfaces/shop","driver/interfaces/plat","lib/ooClass/class","driver/base/base_util","util/http/bodyParse","util/http/request","widget/dom/tip","widget/dom/dialog"],function(e){window.R=window.R||{},e("driver/config");var t=e("lib/ooClass/class"),i=e("driver/base/base_util");R.Class=t,R.util=i}),define("driver/config",["driver/interfaces/user","driver/interfaces/member","driver/interfaces/performance","driver/interfaces/upload","driver/interfaces/goods","driver/interfaces/service","driver/interfaces/content","driver/interfaces/global","driver/interfaces/customService","driver/interfaces/system","driver/interfaces/shop","driver/interfaces/plat"],function(e){var t=$("body").attr("nowWay"),i=e("driver/interfaces/user"),r=e("driver/interfaces/member"),n=e("driver/interfaces/performance"),o=e("driver/interfaces/upload"),a=e("driver/interfaces/goods"),s=e("driver/interfaces/service"),c=e("driver/interfaces/content"),l=e("driver/interfaces/global"),u=e("driver/interfaces/customService"),d=e("driver/interfaces/system"),f=e("driver/interfaces/shop"),h=e("driver/interfaces/plat");R.interfaces={user:i,member:r,performance:n,upload:o,goods:a,service:s,content:c,global:l,customService:u,system:d,shop:f,plat:h},R.nowWay=t}),define("driver/interfaces/user",[],function(e,t,i){i.exports={login:R.uri.reqPrefix+"user/login",regist:R.uri.reqPrefix+"user/regist",loginOut:R.uri.reqPrefix+"user/loginOut"}}),define("driver/interfaces/member",[],function(e,t,i){i.exports={queryPayList:R.uri.reqPrefix+"member/payList",queryReserveList:R.uri.reqPrefix+"member/reserveList",queryScan:R.uri.reqPrefix+"member/queryScan",getEditReserveInfo:R.uri.reqPrefix+"member/getEditReserveInfo",editPreorder:R.uri.reqPrefix+"member/editPreorder",cancelReserve:R.uri.reqPrefix+"member/cancelReserve",createOrder:R.uri.reqPrefix+"member/createOrder",query:R.uri.reqPrefix+"member/query",add:R.uri.reqPrefix+"member/add",edit:R.uri.reqPrefix+"member/edit",editPackage:R.uri.reqPrefix+"member/editPackage",packageInfo:R.uri.reqPrefix+"member/packageInfo",platPackageInfo:R.uri.reqPrefix+"member/platPackageInfo",hasedPackage:R.uri.reqPrefix+"member/hasedPackage",savePackage:R.uri.reqPrefix+"member/savePackage",sum:R.uri.reqPrefix+"member/sum",order:R.uri.reqPrefix+"member/order",registApp:R.uri.reqPrefix+"member/registApp",fastOrder:R.uri.reqPrefix+"member/fastOrder",getTicheng:R.uri.reqPrefix+"member/getTicheng",getOther:R.uri.reqPrefix+"member/getOther",packageGetTicheng:R.uri.reqPrefix+"member/packageGetTicheng",packageGetSale:R.uri.reqPrefix+"member/packageGetSale",getPrice:R.uri.reqPrefix+"member/getPrice"}}),define("driver/interfaces/performance",[],function(e,t,i){i.exports={getAllList:R.uri.reqPrefix+"performance/getAllList",getHairList:R.uri.reqPrefix+"performance/getHairList",getHistoryList:R.uri.reqPrefix+"performance/getHistoryList"}}),define("driver/interfaces/upload",[],function(e,t,i){i.exports={main:R.uri.uploadPrefix+"upload"}}),define("driver/interfaces/goods",[],function(e,t,i){i.exports={goodsEdit:R.uri.reqPrefix+"goods/goodsEdit",goodsEditFind:R.uri.reqPrefix+"goods/goodsEditFind",stockList:R.uri.reqPrefix+"goods/stockList",stockPriceEdit:R.uri.reqPrefix+"goods/stockPriceEdit",bookList:R.uri.reqPrefix+"goods/bookList",bookAdd:R.uri.reqPrefix+"goods/bookAdd",bookEdit:R.uri.reqPrefix+"goods/bookEdit",bookData:R.uri.reqPrefix+"goods/bookData",bookConfirm:R.uri.reqPrefix+"goods/bookConfirm",bookSubmit:R.uri.reqPrefix+"goods/bookSubmit",bookRealModify:R.uri.reqPrefix+"goods/bookRealModify",bookPlatRealModify:R.uri.reqPrefix+"goods/bookPlatRealModify",deliveryList:R.uri.reqPrefix+"goods/deliveryList",deliveryConfirm:R.uri.reqPrefix+"goods/deliveryConfirm",deliveryAdd:R.uri.reqPrefix+"goods/deliveryAdd",deliveryEdit:R.uri.reqPrefix+"goods/deliveryEdit",deliveryData:R.uri.reqPrefix+"goods/deliveryData",platBookList:R.uri.reqPrefix+"goods/platBookList",bookApprove:R.uri.reqPrefix+"goods/bookApprove",getSale:R.uri.reqPrefix+"goods/getSale",confirmSale:R.uri.reqPrefix+"goods/confirmSale",platCombo:R.uri.reqPrefix+"goods/platCombo",modifyDetail:R.uri.reqPrefix+"goods/modifyDetail"}}),define("driver/interfaces/service",[],function(e,t,i){i.exports={list:R.uri.reqPrefix+"service/list",validate:R.uri.reqPrefix+"service/validate",add:R.uri.reqPrefix+"service/add",edit:R.uri.reqPrefix+"service/edit",remove:R.uri.reqPrefix+"service/remove",regist:R.uri.reqPrefix+"service/regist"}}),define("driver/interfaces/content",[],function(e,t,i){i.exports={list:R.uri.reqPrefix+"content/list",add:R.uri.reqPrefix+"content/add",edit:R.uri.reqPrefix+"content/edit",cancel:R.uri.reqPrefix+"content/cancel",search:R.uri.reqPrefix+"content/search"}}),define("driver/interfaces/global",[],function(e,t,i){i.exports={modifyPass:R.uri.reqPrefix+"global/modifyPass",getArea:R.uri.reqPrefix+"global/getArea",upload:R.uri.uploadPrefix+"uploader",getSignature:R.uri.reqPrefix+"global/getSignature"}}),define("driver/interfaces/customService",[],function(e,t,i){i.exports={msgAll:R.uri.reqPrefix+"customService/msgAll",sendMsg:R.uri.reqPrefix+"customService/sendMsg",historyMsg:R.uri.reqPrefix+"customService/historyMsg",getCustomer:R.uri.reqPrefix+"customService/getCustomer"}}),define("driver/interfaces/system",[],function(e,t,i){i.exports={fmLookGroup:R.uri.reqPrefix+"system/fmLookGroup",fmAddGroup:R.uri.reqPrefix+"system/fmAddGroup",fmEditGroup:R.uri.reqPrefix+"system/fmEditGroup",fmFindSub:R.uri.reqPrefix+"system/fmFindSub",fmFindDetail:R.uri.reqPrefix+"system/fmFindDetail",fmAddList:R.uri.reqPrefix+"system/fmAddList",fmEditList:R.uri.reqPrefix+"system/fmEditList",fmDiable:R.uri.reqPrefix+"system/fmDiable",addLookGroup:R.uri.reqPrefix+"system/addLookGroup",addAddGroup:R.uri.reqPrefix+"system/addAddGroup",addEditGroup:R.uri.reqPrefix+"system/addEditGroup",addFindSub:R.uri.reqPrefix+"system/addFindSub",addFindDetail:R.uri.reqPrefix+"system/addFindDetail",addAddList:R.uri.reqPrefix+"system/addAddList",addEditList:R.uri.reqPrefix+"system/addEditList",addDiable:R.uri.reqPrefix+"system/addDiable",cusLookGroup:R.uri.reqPrefix+"system/cusLookGroup",cusLookDetail:R.uri.reqPrefix+"system/cusLookDetail",cusAddList:R.uri.reqPrefix+"system/cusAddList",cusEditList:R.uri.reqPrefix+"system/cusEditList",lookAdapt:R.uri.reqPrefix+"system/lookAdapt",cusAdapt:R.uri.reqPrefix+"system/cusAdapt"}}),define("driver/interfaces/shop",[],function(e,t,i){i.exports={shopList:R.uri.reqPrefix+"shop/shopList",shopInfo:R.uri.reqPrefix+"shop/shopInfo",editShop:R.uri.reqPrefix+"shop/editShop",saveShop:R.uri.reqPrefix+"shop/saveShop",qrCode:R.uri.reqPrefix+"shop/qrCode",downloadQrcode:R.uri.reqPrefix+"shop/downloadQrcode"}}),define("driver/interfaces/plat",[],function(e,t,i){i.exports={platCode:R.uri.reqPrefix+"plat/platCode",publishCode:R.uri.reqPrefix+"plat/publishCode",downloadCode:R.uri.reqPrefix+"plat/downloadCode"}}),define("lib/ooClass/class",[],function(e,t,i){function r(e){return this instanceof r||!d(e)?void 0:o(e)}function n(e){var t,i;for(t in e)i=e[t],r.Mutators.hasOwnProperty(t)?r.Mutators[t].call(this,i):this.prototype[t]=i}function o(e){return e.extend=r.extend,e.implement=n,e}function a(){}function s(e,t,i){for(var r in t)if(t.hasOwnProperty(r)){if(i&&-1===f(i,r))continue;"prototype"!==r&&(e[r]=t[r])}}r.create=function(e,t){function i(){e.apply(this,arguments),this.constructor===i&&this.initialize&&this.initialize.apply(this,arguments)}return d(e)||(t=e,e=null),t||(t={}),e||(e=t.Extends||r),t.Extends=e,e!==r&&s(i,e,e.StaticsWhiteList),n.call(i,t),o(i)},r.extend=function(e){return e||(e={}),e.Extends=this,r.create(e)},r.Mutators={Extends:function(e){var t=this.prototype,i=c(e.prototype);s(i,t),i.constructor=this,this.prototype=i,this.superclass=e.prototype},Implements:function(e){u(e)||(e=[e]);for(var t,i=this.prototype;t=e.shift();)s(i,t.prototype||t)},Statics:function(e){s(this,e)}};var c=Object.__proto__?function(e){return{__proto__:e}}:function(e){return a.prototype=e,new a},l=Object.prototype.toString,u=Array.isArray||function(e){return"[object Array]"===l.call(e)},d=function(e){return"[object Function]"===l.call(e)},f=Array.prototype.indexOf?function(e,t){return e.indexOf(t)}:function(e,t){for(var i=0,r=e.length;r>i;i++)if(e[i]===t)return i;return-1};i.exports=r}),define("driver/base/base_util",["lib/ooClass/class","util/http/bodyParse","util/http/request","widget/dom/tip","widget/dom/dialog"],function(e,t,i){var r=e("lib/ooClass/class"),n=e("util/http/bodyParse"),o=e("util/http/request"),a=r.create({initialize:function(){this.reqParam={},this.reqUrl="",this.reqAsync=!0},parse:function(){return n()},render:function(e,t,i,r){var n=t(i);if(r){if("prepend"==r){var o=$(n);return e.prepend(o),o}if("append"==r){var o=$(n);return e.append(o),o}if("after"==r){var o=$(n);return e.after(o),o}if("before"==r){var o=$(n);return e.before(o),o}}else e.html(n)},req:function(e,t,i){var r=o({url:this.reqUrl,data:this.reqParam,async:this.reqAsync,sucDo:function(t,i){e&&e(t,i)},noDataDo:function(e,i){t&&t(e,i)},failDo:function(e){i&&i(e)}});return r},log:function(e){window.console&&console.log(e)},placeHolder:function(){"placeholder"in document.createElement("input")||$("input[placeholder],textarea[placeholder]").each(function(){var e=$(this),t=e.attr("placeholder");""===e.val()&&e.val(t).addClass("placeholder").css("color","#a9a9a9"),e.focus(function(){e.val()===t&&e.val("").removeClass("placeholder").css("color","#555555")}).blur(function(){""===e.val()&&e.val(t).addClass("placeholder").css("color","#a9a9a9")}).closest("form").submit(function(){e.val()===t&&e.val("")})})}});i.exports=a}),define("util/http/bodyParse",[],function(){function e(){var e,t,i,r,n,o,a;if(window.location.search){for(e=decodeURIComponent(window.location.search.split("?")[1]),t=e.split("&"),r=t.length,a={},o=0;r>o;o++)i=t[o].split("="),n=i.length,a[i[0]]=i[1];return a}return{}}return e}),define("util/http/request",["widget/dom/tip","widget/dom/dialog"],function(e){function t(e){var t,n,o,a,s,c,l,u,d,f,h;return t=e.url||null,n=e.data||"",o=!0,a=e.noDataDo||null,s=e.sucDo||null,c=e.failDo||null,u=e.unLoginDo||null,l=e.beforeDo||null,d=e.otherStatus||null,f=e.type||"post",h=e.dataType||"json",t?($.ajax({url:t,data:n,type:f,async:o,dataType:h,timeout:999999,beforeSend:function(){l&&l()},success:function(e){i(t,e,s,a,u,d)},error:function(e){r(t,e.statusText),c&&c(e.statusText)}}),void 0):(window.console&&console.log("请传入请求地址"),void 0)}function i(e,t,i,r,n,o){if("001"==t.code)i&&i(t,t.code);else{if("002"==t.code)return r&&r(t,t.code),void 0;"003"==t.code}if(o)for(var a in o)a==t.err&&o[a](t)}function r(e,t){window.console&&(console.log("接口     "+e+"    接口报错"),console.log(t))}return e("widget/dom/tip"),e("widget/dom/dialog"),t}),define("widget/dom/tip",[],function(e,t,i){function r(){this.timer=null,this.hideTime=2500,this.create()}r.prototype={create:function(){this.oWrap=$("<div><span></span></div>"),this.oWrap.css({position:"fixed",left:"0",top:"0",width:"100%",height:"32px",textAlign:"center",zIndex:8e3,display:"none"}),this.oTip=this.oWrap.children().eq(0),this.oTip.css({height:"32px",lineHeight:"32px",borderTop:"1px solid #aaaaab",borderBottom:"1px solid #b98710",borderLeft:"1px solid #b98710",borderRight:"1px solid #b98710",borderBottomLeftRadius:"3px",borderBottomRightRadius:"3px",color:"#fff",fontSize:"14px",padding:"0 20px",background:"#eaa000",display:"none"}),$("body").append(this.oWrap)},say:function(e){var t=this;clearTimeout(this.timer),this.oTip.html(e),this.show(),this.timer=setTimeout(function(){t.hide()},this.hideTime)},show:function(){this.oWrap.show(),this.oTip.css("display","inline-block")},hide:function(){var e=this;this.oTip.fadeOut(function(){e.oWrap.hide()})}};var n=new r;i.exports=n}),define("widget/dom/dialog",[],function(e,t,i){function r(e){e=e||{},this.type=e.type||"confirm",this.title=e.title||"",this.content=e.content||"",this.enterKey=e.enterKey?e.enterKey:!1,this.escKey=e.escKey?e.escKey:!1,this.index=e.index||4e3,this.box=null,this.boxStr=e.boxStr||'<div class="confirmbox out-box '+this.type+'" sc="confirm-box" sc="box">'+'<h2 class="ba-tc ba-font-18 ba-red ba-mt-20" sc="box-title">'+this.title+"</h2>"+'<p class="ba-font-20 ba-tc ba-mt-10 ba-mb-10" sc="box-content">'+this.content+"</p>"+'<div class="ba-tc">'+'<a href="javascript:;" class="btn btn-danger ba-mr-10" sc="confirm">确定</a>'+'<a href="javascript:;" class="btn btn-primary" sc="close">取消</a>'+"</div>"+"</div>",this.boxSelector=e.boxSelector||"",this.boxTpl=e.boxTpl||"",this.boxData=e.boxData||{},this.onClosed=e.onClosed||null,this.onStart=e.onStart||null,this.onConfirm=e.onConfirm||null,this.overLayHide=e.overLayHide||null,this.init()}r.prototype={init:function(){this.render(),this.events(),this.makeLay()},render:function(){var e,t,i=this.create();e=-i.h/2+"px",t=-i.w/2+"px",this.position(i.box,"50%","50%",e+" 0 0 "+t),this.box=i.box},makeRound:function(){var e='<div class="clearfix level-t"><div class="lt fl"></div><div class="t fl"></div><div class="rt fl"></div></div><div class="clearfix level-m"><div class="l fl"></div><div class="main fl"></div><div class="r fl"></div></div><div class="clearfix level-b"><div class="lb fl"></div><div class="b fl"></div><div class="rb fl"></div></div>';this.boxSelector.append($(e))},makeLay:function(){var e=$("[sc = Dialog-lay]").length;return e?(this.oLay=$("[sc = Dialog-lay]"),void 0):(this.oLay=$('<div sc="Dialog-lay"></div>'),this.oLay.css({width:"100%",height:"100%",position:"fixed",left:0,top:0,zIndex:3e3,background:"#000",opacity:"0.3",display:"none"}),$("body").append(this.oLay),void 0)},set:function(e,t){this.oLay.css({left:e,top:t})},create:function(){var e,t,i;return this.boxSelector?e=$(this.boxSelector):this.boxTpl?(e=$(this.boxTpl(this.boxData)),$("body").append(e)):(e=$(this.boxStr),$("body").append(e)),e.wrap("<div dialog-wrap></div>"),t=e.outerWidth(!0),i=e.outerHeight(!0),this.oBox=e,this.oWrap=this.oBox.parents("[dialog-wrap]"),{box:this.oWrap,w:t,h:i}},events:function(){var e=this;this.box.on("click","[sc = close]",function(){e.close(e.box)}),this.box.on("click","[sc = confirm]",function(){e.onConfirm&&e.onConfirm.call(e,$(this))}),$(document).on("click",function(t){e.overLayHide&&$(t.target).parents()[0]!=e.oBox[0]&&$(t.target)[0]!=e.oBox[0]&&e.close()})},position:function(e,t,i,r){e.css({position:"fixed",left:t,top:i,margin:r,zIndex:this.index,display:"none"})},show:function(){this.oWrap.show(),this.oLay.show(),this.onStart&&this.onStart.call(this)},close:function(e){this.oWrap.hide(),this.oLay.hide(),e!==!1&&this.onClosed&&this.onClosed.call(this)},fix:function(){this.box.css("position","fixed")},dom:function(){return this.oBox},boxTitle:function(){return this.oBox.find("[sc = box-title]")},boxContent:function(){return this.oBox.find("[sc = box-content]")},refreshData:function(e){this.oWrap.html(this.boxTpl(e))}},i.exports=r}),define("widget/form/ajaxForm",["util/http/request","widget/dom/tip","widget/dom/dialog","util/http/bodyParse"],function(e,t,i){function r(e){this.oWrap=e.boundName?$("[script-bound = "+e.boundName+"]"):$("[script-bound = form-check]"),this.oSub=e.btnName?this.oWrap.find("[script-role = "+e.btnName+"]"):this.oWrap.find("[script-role = confirm-btn]"),this.loadOffset=0,this.oLoading=$("[loading-wrap]"),this.otherCheck=e.otherCheck||null,this.subUrl=e.subUrl||"",this.arrSelect=[],this.arrInput=[],this.arrRadio=[],this.arrcheckBox=[],this.arrOther=[],this.otherJude=e.otherJude||null,this.fnSumbit=e.fnSumbit||null,this.sucDo=e.sucDo||null,this.failDo=e.failDo||null,this.noSub=e.noSub||null,this.closeCheck=e.closeCheck?e.closeCheck:!1,this.loaddingUrl=e.loaddingUrl||"../../../img/lib/loading/btn_loading.gif",this.param={}}var n=e("util/http/request"),o=e("util/http/bodyParse");r.prototype={reload:function(e){e=e||{},this.oWrap=e.boundName?$("[script-bound = "+e.boundName+"]"):$("[script-bound = form-check]"),this.oSub=e.btnName?this.oWrap.find("[script-role = "+e.btnName+"]"):this.oWrap.find("[script-role = confirm-btn]"),this.oSub.unbind("click"),this.getEle(),this.subMitEvent()},upload:function(){this.getEle(),this.subMitEvent()},getEle:function(){var e=this,t=this.oWrap.find("*");t.each(function(i){t.eq(i).attr("form_check")&&(e.makeWrapTip(t.eq(i)),e.judgeType(t.eq(i)))})},judgeType:function(e){"sys"==e.attr("form_check")?"SELECT"==e.get(0).tagName?(this.arrSelect.push(e),this.checkSelect(e)):e.attr("radio_group")?(this.arrRadio.push(e),this.checkRadio(e)):e.attr("checkbox_group")?(this.arrcheckBox.push(e),this.checkBox(e)):("text"==e.attr("type")||"password"==e.attr("type")||"file"==e.attr("type")||"TEXTAREA"==e.get(0).tagName)&&(this.arrInput.push(e),this.checkText(e)):"self"==e.attr("form_check")&&("SELECT"==e.get(0).tagName?this.arrOther.push(e):e.attr("radio_group")?this.arrOther.push(e):e.attr("checkbox_group")?this.arrOther.push(e):("text"==e.attr("type")||"password"==e.attr("type")||"file"==e.attr("type")||"TEXTAREA"==e.tagName)&&this.arrOther.push(e),this.checkother(e))},makeWrapTip:function(e){if("true"==e.attr("ischeck")||"free"==e.attr("ischeck")){e.parent().css({position:"relative"});var t=$('<span class="wrong_area" script-role="wrong_area"></span>'),i='<span class="wrong_content" script-role="wrong_name" script-role="wrong_name"></span><span class="wrrong_bot"></span>';t.html(i),e.before(t),this.tipPosition(e,t)}},tipPosition:function(e,t){var i=e.position().left;e.innerWidth();var r=i,n=e.outerHeight(!0)-1+e.position().top;(e.attr("checkbox_group")||"checkbox"==e.attr("type"))&&(n+=10),t.css({position:"absolute",left:r,top:n,zIndex:1e3})},tipWrong:function(e,t,i){t.find("[script-role = wrong_name]").html(i),this.tipPosition(e,t),setTimeout(function(){t.addClass("wrong")},0),t.attr("iswrong","true"),e.parents("[script-role = check-wrap]").addClass("has-error")},tipRight:function(e,t){t.removeClass("wrong"),t.attr("iswrong","false"),e.parents("[script-role = check-wrap]").removeClass("has-error")},checkText:function(e){var t,i;i=this,e.attr("pressUrl")||e.blur(function(){i.inputJude($(this))}),e.focus(function(){t=$(this).parent().find("[script-role = wrong_area]"),i.tipRight(e,t)})},getTip:function(e){return e.parent().find("[script-role = wrong_area]")},pressInput:function(e,t){var i=this;dataJson={},oTip=e.parent().find("[script-role = wrong_area]"),pressUrl=e.attr("pressUrl"),param=e.attr("param"),dataJson[param]=e.val(),dataJson.pid=t;var r=e;n({url:pressUrl,data:dataJson,sucDo:function(){i.tipRight(e,oTip)},noDataDo:function(e){i.tipWrong(r,oTip,e)}})},inputJude:function(e){if(!this.closeCheck){var t,i,r,n,a,s;if(s=this,e.attr("pressUrl")){var c=o().pid;s.pressInput(e,c)}else t=e.parent().find("[script-role = wrong_area]"),i=e.attr("tip"),r=e.attr("wrong"),n=RegExp("^"+e.attr("re")+"$","gi"),a=$.trim(e.val()),"true"==e.attr("ischeck")?e.val()?n.test(a)?s.tipRight(e,t):s.tipWrong(e,t,r):s.tipWrong(e,t,i):"free"==e.attr("ischeck")?""!=a&&(n.test(a)?s.tipRight(e,t):s.tipWrong(e,t,r)):a&&(n.test(a)?s.tipRight(e,t):s.tipWrong(e,t,r))}},checkSelect:function(e){var t;t=this,e.change(function(){t.selectJude($(this))})},selectJude:function(e){if(!this.closeCheck&&("true"==e.attr("ischeck")||"free"==e.attr("ischeck"))){var t,i;t=e.parent().find("[script-role = wrong_area]"),i=e.attr("tip"),e.val()?this.tipRight(e,t):this.tipWrong(e,t,i)}},checkRadio:function(e){var t=this;e.click(function(){t.radioJudge(e)})},radioJudge:function(e){var t=this;if(!this.closeCheck){var i=e.find("input[type = radio]"),r=!1,n=e.parent().find("[script-role = wrong_area]"),o=e.attr("tip");setTimeout(function(){i.each(function(e){i.eq(e).attr("checked")&&(r=!0)}),("true"==e.attr("ischeck")||"free"==e.attr("ischeck"))&&(r?t.tipRight(e,n):t.tipWrong(e,n,o))},1)}},checkBox:function(e){var t=this;e.click(function(){t.checkJudge(e)})},checkJudge:function(e){if(!this.closeCheck){var t=e.find("input[type = checkbox]"),i=!1,r=e.parent().find("[script-role = wrong_area]"),n=e.attr("tip");t.each(function(e){t.eq(e).attr("checked")&&(i=!0)}),("true"==e.attr("ischeck")||"free"==e.attr("ischeck"))&&(i?this.tipRight(e,r):this.tipWrong(t.eq(0),r,n))}},checkother:function(e){var t=this,i=e.attr("name");e.attr("tip");var r,n=e.attr("tip"),o=e.attr("wrong");this.oSub.click(function(){r=e.parent().find("[script-role = wrong_area]"),t.otherCheck[i][0](e)?t.otherCheck[i][1](e)?t.otherCheck[i][0](e)&&t.otherCheck[i][1](e)&&t.tipRight(e,r):t.tipWrong(e,r,o):t.tipWrong(e,r,n)})},subMitEvent:function(){var e=this;this.oSub.click(function(){var t=$(this).attr("disabled");"disabled"!=t&&e.subMit($(this))})},refresh:function(){var e=this.oWrap.find("[script-role = wrong_area]");e.attr("iswrong","false"),e.removeClass("wrong"),$("[script-role = check-wrap]").removeClass("has-error")},subMit:function(e){var t,i,r=this;t=this.oWrap.find("[script-role = wrong_area]"),i=!0,this.map(this.arrSelect,function(e){this.selectJude(this.arrSelect[e])}),this.map(this.arrInput,function(e){this.inputJude(this.arrInput[e])}),this.map(this.arrRadio,function(e){this.radioJudge(this.arrRadio[e])}),this.map(this.arrcheckBox,function(e){this.checkJudge(this.arrcheckBox[e])}),setTimeout(function(){if(t.each(function(e){"true"==t.eq(e).attr("iswrong")&&(i=!1)}),i)if(r.otherJude){var n=!0;if(r.map(r.otherJude,function(e){r.otherJude[e]()||(n=!1)}),!n)return;r.getAllData(e)}else r.getAllData(e)},2)},map:function(e,t){var i,r;for(r=e.length,i=0;r>i;i++)t.call(this,i)},getAllParam:function(){var e=this;return this.getInputData(this.arrInput),this.getSelectData(this.arrSelect),this.map(this.arrRadio,function(t){e.getRadioData(e.arrRadio[t])}),this.param},getAllData:function(e){var t=this;if(this.getAllParam(),this.map(this.arrcheckBox,function(e){t.getCheckData(t.arrcheckBox[e])}),this.noSub)return this.noSub(this.param),void 0;if(this.fnSumbit){var i=this.fnSumbit(this.param);if(0==i)return;this.param={};for(key in i)this.param[key]=i[key]}n({url:this.subUrl,data:this.param,beforeDo:function(){t.loadingShow()},sucDo:function(i){t.loadingHide(),t.sucDo&&t.sucDo(i,e,t.param)},noDataDo:function(e){t.loadingHide(),t.failDo&&t.failDo(e)}})},getInputData:function(e){var t=this;this.map(e,function(i){"false"!=e[i].attr("no_upload")&&("free"!=e[i].attr("ischeck")||e[i].val())&&(t.param[e[i].attr("name")]=e[i].val())})},getSelectData:function(e){var t=this;this.map(e,function(i){"false"!=e[i].attr("no_upload")&&("free"!=e[i].attr("ischeck")||e[i].val())&&(t.param[e[i].attr("name")]=e[i].get(0).children[e[i].get(0).selectedIndex].id)})},getRadioData:function(e){if("false"!=e.attr("no_upload")){var t=e.find("input[type = radio]"),i=e.attr("name"),r="";t.each(function(e){"checked"==t.eq(e).attr("checked")&&(r=t.eq(e).val())}),this.param[i]=r}},getCheckData:function(e){if("false"!=e.attr("no_upload")){var t=e.find("input[type = checkbox]"),i=e.attr("name"),r=[];t.each(function(e){"checked"==t.eq(e).attr("checked")&&r.push(t.eq(e).attr("id"))}),this.param[i]=r.join(",")}},makeLoading:function(e){var t,i,r,n;return r=$("<img src="+this.loaddingUrl+' width="16" height="16">'),n=e.attr("offsetLoad")?parseInt(e.attr("offsetLoad")):this.loadOffset,e.css({position:"relative"}),t=Math.floor((e.outerWidth()-16)/2-n),i=Math.floor((e.outerHeight()-16)/2),r.css({position:"absolute",left:t+"px",top:i+"px",zIndex:2,display:"none"}),e.append(r),r},loadingShow:function(){this.oSub.addClass("disabled"),this.oSub.attr("disabled","disabled")},loadingHide:function(){this.oSub.removeClass("disabled"),this.oSub.removeAttr("disabled"),this.oLoading.hide()},clear:function(){var e=this;this.map(this.arrSelect,function(t){e.arrSelect[t][0].selectedIndex=0}),this.oWrap.find("[form_check]").val(""),this.map(this.arrRadio,function(){e.arrRadio.removeAttr("checked")}),this.map(this.arrcheckBox,function(){e.arrcheckBox.removeAttr("checked")})}},i.exports=r}),define("util/cookie/cookie",[],function(){var e=e||{};return e.setCookie=function(e,t,i){var r,n,o,a;return r=i.match(/\d+/)[0],n=i.substring(r.length),o={sec:r,min:60*r,hour:60*60*r,day:24*60*60*r,year:365*24*60*60*r},o[n]||"-1"!=i.indexOf("-")?(a=new Date,a.setSeconds(a.getSeconds()+(o[n]||-1)),document.cookie=e+"="+t+";expires="+a.toGMTString()+";path=/",void 0):(window.console&&console.log("cookie.js: 没有传入时间，或传入的时间后缀不正确(1sec, 2min, 3hour, 4day 5year)"),void 0)},e.getCookie=function(e){var t,i,r,n;for(t=document.cookie.split("; "),n=t.length,r=0;n>r;r++)if(i=t[r].split("="),i[0]===e)return i[1];return""},e.removeCookie=function(t){e.setCookie(t,1,"-1sec")},e}),define("widget/dom/enterDo",[],function(){function e(e,t){e.keydown(function(e){13==e.which&&t&&t($(this))})}return e}),define("widget/dom/placeholder",[],function(e,t,i){function r(e,t){this.oInput=e,this.options=t||{},this.oInput.length&&this.start()}r.prototype={start:function(){this.hasProperty();var e=this.oInput.attr("text"),t=this.oInput.parent();this.oHolder=$('<span style="color:#a9a9a9">'+e+"</span>"),t.append(this.oHolder),this.oInput[0].offsetParent!=t[0]&&t.css("position","relative");var i=parseInt(this.oInput.css("paddingLeft")),r=this.oInput.position().left+i,n=this.oInput.position().top+(this.oInput.outerHeight(!0)-this.oHolder.outerHeight(!0))/2,o=this.oInput.val();this.oHolder.css({position:"absolute",left:r,top:n,visibility:"hidden"}),o||this.show(),this.events()},events:function(){var e=this;this.oInput.focus(function(){e.hide()}),this.oInput.blur(function(){e.oInput.val()||e.show()}),this.oHolder.click(function(){e.oInput.trigger("focus")})},hasProperty:function(){return"placeholder"in this.oInput.get(0)?!0:!1},hide:function(){this.oHolder&&this.oHolder.css({visibility:"hidden"})},show:function(){this.oHolder&&this.oHolder.css({visibility:"visible"})}},i.exports=r});