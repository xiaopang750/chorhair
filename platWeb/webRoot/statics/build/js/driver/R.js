/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("driver/R",["./config","./interfaces/user","./interfaces/member","./interfaces/performance","./interfaces/upload","./interfaces/goods","./interfaces/service","./interfaces/content","./interfaces/global","./interfaces/customService","./interfaces/system","./interfaces/shop","./interfaces/plat","../lib/ooClass/class","./base/base_util","../util/http/bodyParse","../util/http/request","../widget/dom/tip","../widget/dom/dialog"],function(e){window.R=window.R||{},e("./config");var t=e("../lib/ooClass/class"),i=e("./base/base_util");R.Class=t,R.util=i}),define("driver/config",["driver/interfaces/user","driver/interfaces/member","driver/interfaces/performance","driver/interfaces/upload","driver/interfaces/goods","driver/interfaces/service","driver/interfaces/content","driver/interfaces/global","driver/interfaces/customService","driver/interfaces/system","driver/interfaces/shop","driver/interfaces/plat"],function(e){$("body").attr("nowWay");var t=e("driver/interfaces/user"),i=e("driver/interfaces/member"),n=e("driver/interfaces/performance"),o=e("driver/interfaces/upload"),r=e("driver/interfaces/goods"),a=e("driver/interfaces/service"),s=e("driver/interfaces/content"),c=e("driver/interfaces/global"),u=e("driver/interfaces/customService"),l=e("driver/interfaces/system"),f=e("driver/interfaces/shop"),d=e("driver/interfaces/plat");R.interfaces={user:t,member:i,performance:n,upload:o,goods:r,service:a,content:s,global:c,customService:u,system:l,shop:f,plat:d}}),define("driver/interfaces/user",[],function(e,t,i){i.exports={login:R.uri.reqPrefix+"user/login",regist:R.uri.reqPrefix+"user/regist",loginOut:R.uri.reqPrefix+"user/loginOut"}}),define("driver/interfaces/member",[],function(e,t,i){i.exports={queryPayList:R.uri.reqPrefix+"member/payList",queryReserveList:R.uri.reqPrefix+"member/reserveList",queryScan:R.uri.reqPrefix+"member/queryScan",getEditReserveInfo:R.uri.reqPrefix+"member/getEditReserveInfo",editPreorder:R.uri.reqPrefix+"member/editPreorder",cancelReserve:R.uri.reqPrefix+"member/cancelReserve",createOrder:R.uri.reqPrefix+"member/createOrder",query:R.uri.reqPrefix+"member/query",add:R.uri.reqPrefix+"member/add",edit:R.uri.reqPrefix+"member/edit",editPackage:R.uri.reqPrefix+"member/editPackage",packageInfo:R.uri.reqPrefix+"member/packageInfo",platPackageInfo:R.uri.reqPrefix+"member/platPackageInfo",hasedPackage:R.uri.reqPrefix+"member/hasedPackage",savePackage:R.uri.reqPrefix+"member/savePackage",sum:R.uri.reqPrefix+"member/sum",order:R.uri.reqPrefix+"member/order",registApp:R.uri.reqPrefix+"member/registApp",fastOrder:R.uri.reqPrefix+"member/fastOrder",getTicheng:R.uri.reqPrefix+"member/getTicheng",getOther:R.uri.reqPrefix+"member/getOther",packageGetTicheng:R.uri.reqPrefix+"member/packageGetTicheng",packageGetSale:R.uri.reqPrefix+"member/packageGetSale",getPrice:R.uri.reqPrefix+"member/getPrice",getSinMemberInfo:R.uri.reqPrefix+"member/getSinMemberInfo"}}),define("driver/interfaces/performance",[],function(e,t,i){i.exports={getAllList:R.uri.reqPrefix+"performance/getAllList",getHairList:R.uri.reqPrefix+"performance/getHairList",getHistoryList:R.uri.reqPrefix+"performance/getHistoryList",getIncome:R.uri.reqPrefix+"performance/getIncome",getAvgincome:R.uri.reqPrefix+"performance/getAvgincome",getPeopleCount:R.uri.reqPrefix+"performance/getPeopleCount"}}),define("driver/interfaces/upload",[],function(e,t,i){i.exports={main:R.uri.uploadPrefix+"upload"}}),define("driver/interfaces/goods",[],function(e,t,i){i.exports={goodsEdit:R.uri.reqPrefix+"goods/goodsEdit",goodsEditFind:R.uri.reqPrefix+"goods/goodsEditFind",stockList:R.uri.reqPrefix+"goods/stockList",platAddCombo:R.uri.reqPrefix+"goods/platAddCombo",platGetCombo:R.uri.reqPrefix+"goods/platGetCombo",platEditCombo:R.uri.reqPrefix+"goods/platEditCombo",platStockList:R.uri.reqPrefix+"goods/platStockList",platAddProduct:R.uri.reqPrefix+"goods/platAddProduct",shopComboAttr:R.uri.reqPrefix+"goods/shopComboAttr",saveShopCombo:R.uri.reqPrefix+"goods/saveShopCombo",stockPriceEdit:R.uri.reqPrefix+"goods/stockPriceEdit",addProductNum:R.uri.reqPrefix+"goods/addProductNum",platStockRecord:R.uri.reqPrefix+"goods/platStockRecord",shopStockList:R.uri.reqPrefix+"goods/shopStockList",bookList:R.uri.reqPrefix+"goods/bookList",bookAdd:R.uri.reqPrefix+"goods/bookAdd",bookEdit:R.uri.reqPrefix+"goods/bookEdit",bookData:R.uri.reqPrefix+"goods/bookData",bookConfirm:R.uri.reqPrefix+"goods/bookConfirm",bookSubmit:R.uri.reqPrefix+"goods/bookSubmit",bookRealModify:R.uri.reqPrefix+"goods/bookRealModify",bookPlatRealModify:R.uri.reqPrefix+"goods/bookPlatRealModify",deliveryList:R.uri.reqPrefix+"goods/deliveryList",deliveryConfirm:R.uri.reqPrefix+"goods/deliveryConfirm",deliveryAdd:R.uri.reqPrefix+"goods/deliveryAdd",deliveryEdit:R.uri.reqPrefix+"goods/deliveryEdit",deliveryData:R.uri.reqPrefix+"goods/deliveryData",sendGoods:R.uri.reqPrefix+"goods/sendGoods",bookApprove:R.uri.reqPrefix+"goods/bookApprove",platBookList:R.uri.reqPrefix+"goods/platBookList",getSale:R.uri.reqPrefix+"goods/getSale",confirmSale:R.uri.reqPrefix+"goods/confirmSale"}}),define("driver/interfaces/service",[],function(e,t,i){i.exports={list:R.uri.reqPrefix+"service/list",validate:R.uri.reqPrefix+"service/validate",add:R.uri.reqPrefix+"service/add",edit:R.uri.reqPrefix+"service/edit",remove:R.uri.reqPrefix+"service/remove",regist:R.uri.reqPrefix+"service/regist"}}),define("driver/interfaces/content",[],function(e,t,i){i.exports={list:R.uri.reqPrefix+"content/list",add:R.uri.reqPrefix+"content/add",edit:R.uri.reqPrefix+"content/edit",cancel:R.uri.reqPrefix+"content/cancel",search:R.uri.reqPrefix+"content/search"}}),define("driver/interfaces/global",[],function(e,t,i){i.exports={modifyPass:R.uri.reqPrefix+"global/modifyPass",getArea:R.uri.reqPrefix+"global/getArea",upload:R.uri.uploadPrefix+"uploader",getSignature:R.uri.reqPrefix+"global/getSignature"}}),define("driver/interfaces/customService",[],function(e,t,i){i.exports={msgAll:R.uri.reqPrefix+"customService/msgAll",sendMsg:R.uri.reqPrefix+"customService/sendMsg",historyMsg:R.uri.reqPrefix+"customService/historyMsg",getCustomer:R.uri.reqPrefix+"customService/getCustomer",createGroup:R.uri.reqPrefix+"customService/createGroup",getGroup:R.uri.reqPrefix+"customService/getGroup",getGroupData:R.uri.reqPrefix+"customService/getGroupData",editGroup:R.uri.reqPrefix+"customService/editGroup",removeGroup:R.uri.reqPrefix+"customService/removeGroup"}}),define("driver/interfaces/system",[],function(e,t,i){i.exports={fmLookGroup:R.uri.reqPrefix+"system/fmLookGroup",fmAddGroup:R.uri.reqPrefix+"system/fmAddGroup",fmEditGroup:R.uri.reqPrefix+"system/fmEditGroup",fmFindSub:R.uri.reqPrefix+"system/fmFindSub",fmFindDetail:R.uri.reqPrefix+"system/fmFindDetail",fmAddList:R.uri.reqPrefix+"system/fmAddList",fmEditList:R.uri.reqPrefix+"system/fmEditList",fmDiable:R.uri.reqPrefix+"system/fmDiable",addLookGroup:R.uri.reqPrefix+"system/addLookGroup",addAddGroup:R.uri.reqPrefix+"system/addAddGroup",addEditGroup:R.uri.reqPrefix+"system/addEditGroup",addFindSub:R.uri.reqPrefix+"system/addFindSub",addFindDetail:R.uri.reqPrefix+"system/addFindDetail",addAddList:R.uri.reqPrefix+"system/addAddList",addEditList:R.uri.reqPrefix+"system/addEditList",addDiable:R.uri.reqPrefix+"system/addDiable",cusLookGroup:R.uri.reqPrefix+"system/cusLookGroup",cusLookDetail:R.uri.reqPrefix+"system/cusLookDetail",cusAddList:R.uri.reqPrefix+"system/cusAddList",cusEditList:R.uri.reqPrefix+"system/cusEditList",lookAdapt:R.uri.reqPrefix+"system/lookAdapt",cusAdapt:R.uri.reqPrefix+"system/cusAdapt"}}),define("driver/interfaces/shop",[],function(e,t,i){i.exports={shopList:R.uri.reqPrefix+"shop/shopList",shopInfo:R.uri.reqPrefix+"shop/shopInfo",editShop:R.uri.reqPrefix+"shop/editShop",saveShop:R.uri.reqPrefix+"shop/saveShop",qrCode:R.uri.reqPrefix+"shop/qrCode",downloadQrcode:R.uri.reqPrefix+"shop/downloadQrcode",shopAccount:R.uri.reqPrefix+"shop/shopAccount",editAccount:R.uri.reqPrefix+"shop/editAccount",addAccount:R.uri.reqPrefix+"shop/addAccount"}}),define("driver/interfaces/plat",[],function(e,t,i){i.exports={platCode:R.uri.reqPrefix+"plat/platCode",publishCode:R.uri.reqPrefix+"plat/publishCode",downloadCode:R.uri.reqPrefix+"plat/downloadCode"}}),define("lib/ooClass/class",[],function(e,t,i){function n(e){return this instanceof n||!f(e)?void 0:r(e)}function o(e){var t,i;for(t in e)i=e[t],n.Mutators.hasOwnProperty(t)?n.Mutators[t].call(this,i):this.prototype[t]=i}function r(e){return e.extend=n.extend,e.implement=o,e}function a(){}function s(e,t,i){for(var n in t)if(t.hasOwnProperty(n)){if(i&&-1===d(i,n))continue;"prototype"!==n&&(e[n]=t[n])}}n.create=function(e,t){function i(){e.apply(this,arguments),this.constructor===i&&this.initialize&&this.initialize.apply(this,arguments)}return f(e)||(t=e,e=null),t||(t={}),e||(e=t.Extends||n),t.Extends=e,e!==n&&s(i,e,e.StaticsWhiteList),o.call(i,t),r(i)},n.extend=function(e){return e||(e={}),e.Extends=this,n.create(e)},n.Mutators={Extends:function(e){var t=this.prototype,i=c(e.prototype);s(i,t),i.constructor=this,this.prototype=i,this.superclass=e.prototype},Implements:function(e){l(e)||(e=[e]);for(var t,i=this.prototype;t=e.shift();)s(i,t.prototype||t)},Statics:function(e){s(this,e)}};var c=Object.__proto__?function(e){return{__proto__:e}}:function(e){return a.prototype=e,new a},u=Object.prototype.toString,l=Array.isArray||function(e){return"[object Array]"===u.call(e)},f=function(e){return"[object Function]"===u.call(e)},d=Array.prototype.indexOf?function(e,t){return e.indexOf(t)}:function(e,t){for(var i=0,n=e.length;n>i;i++)if(e[i]===t)return i;return-1};i.exports=n}),define("driver/base/base_util",["lib/ooClass/class","util/http/bodyParse","util/http/request","widget/dom/tip","widget/dom/dialog"],function(e,t,i){var n=e("lib/ooClass/class"),o=e("util/http/bodyParse"),r=e("util/http/request"),a=n.create({initialize:function(){this.reqParam={},this.reqUrl="",this.reqAsync=!0},parse:function(){return o()},render:function(e,t,i,n){var o=t(i);if(n){if("prepend"==n){var r=$(o);return e.prepend(r),r}if("append"==n){var r=$(o);return e.append(r),r}if("after"==n){var r=$(o);return e.after(r),r}if("before"==n){var r=$(o);return e.before(r),r}}else e.html(o)},req:function(e,t,i){var n=r({url:this.reqUrl,data:this.reqParam,async:this.reqAsync,sucDo:function(t,i){e&&e(t,i)},noDataDo:function(e,i){t&&t(e,i)},failDo:function(e){i&&i(e)}});return n},log:function(e){window.console&&console.log(e)},placeHolder:function(){"placeholder"in document.createElement("input")||$("input[placeholder],textarea[placeholder]").each(function(){var e=$(this),t=e.attr("placeholder");""===e.val()&&e.val(t).addClass("placeholder").css("color","#a9a9a9"),e.focus(function(){e.val()===t&&e.val("").removeClass("placeholder").css("color","#555555")}).blur(function(){""===e.val()&&e.val(t).addClass("placeholder").css("color","#a9a9a9")}).closest("form").submit(function(){e.val()===t&&e.val("")})})}});i.exports=a}),define("util/http/bodyParse",[],function(){function e(){var e,t,i,n,o,r,a;if(window.location.search){for(e=decodeURIComponent(window.location.search.split("?")[1]),t=e.split("&"),n=t.length,a={},r=0;n>r;r++)i=t[r].split("="),o=i.length,a[i[0]]=i[1];return a}return{}}return e}),define("util/http/request",["widget/dom/tip","widget/dom/dialog"],function(e){function t(e){var t,o,r,a,s,c,u,l,f,d,p;return t=e.url||null,o=e.data||"",r=!0,a=e.noDataDo||null,s=e.sucDo||null,c=e.failDo||null,l=e.unLoginDo||null,u=e.beforeDo||null,f=e.otherStatus||null,d=e.type||"post",p=e.dataType||"json",t?($.ajax({url:t,data:o,type:d,async:r,dataType:p,timeout:999999,beforeSend:function(){u&&u()},success:function(e){i(t,e,s,a,l,f)},error:function(e){n(t,e.statusText),c&&c(e.statusText)}}),void 0):(window.console&&console.log("请传入请求地址"),void 0)}function i(e,t,i,n,o,r){if("001"==t.code)i&&i(t,t.code);else{if("002"==t.code)return n&&n(t,t.code),void 0;"003"==t.code}if(r)for(var a in r)a==t.err&&r[a](t)}function n(e,t){window.console&&(console.log("接口     "+e+"    接口报错"),console.log(t))}return e("widget/dom/tip"),e("widget/dom/dialog"),t}),define("widget/dom/tip",[],function(e,t,i){function n(){this.timer=null,this.hideTime=2500,this.create()}n.prototype={create:function(){this.oWrap=$("<div><span></span></div>"),this.oWrap.css({position:"fixed",left:"0",top:"0",width:"100%",height:"32px",textAlign:"center",zIndex:8e3,display:"none"}),this.oTip=this.oWrap.children().eq(0),this.oTip.css({height:"32px",lineHeight:"32px",borderTop:"1px solid #aaaaab",borderBottom:"1px solid #b98710",borderLeft:"1px solid #b98710",borderRight:"1px solid #b98710",borderBottomLeftRadius:"3px",borderBottomRightRadius:"3px",color:"#fff",fontSize:"14px",padding:"0 20px",background:"#eaa000",display:"none"}),$("body").append(this.oWrap)},say:function(e){var t=this;clearTimeout(this.timer),this.oTip.html(e),this.show(),this.timer=setTimeout(function(){t.hide()},this.hideTime)},show:function(){this.oWrap.show(),this.oTip.css("display","inline-block")},hide:function(){var e=this;this.oTip.fadeOut(function(){e.oWrap.hide()})}};var o=new n;i.exports=o}),define("widget/dom/dialog",[],function(e,t,i){function n(e){e=e||{},this.type=e.type||"confirm",this.title=e.title||"",this.content=e.content||"",this.enterKey=e.enterKey?e.enterKey:!1,this.escKey=e.escKey?e.escKey:!1,this.index=e.index||4e3,this.box=null,this.boxStr=e.boxStr||'<div class="confirmbox out-box '+this.type+'" sc="confirm-box" sc="box">'+'<h2 class="ba-tc ba-font-18 ba-red ba-mt-20" sc="box-title">'+this.title+"</h2>"+'<p class="ba-font-20 ba-tc ba-mt-10 ba-mb-10" sc="box-content">'+this.content+"</p>"+'<div class="ba-tc">'+'<a href="javascript:;" class="btn btn-danger ba-mr-10" sc="confirm">确定</a>'+'<a href="javascript:;" class="btn btn-primary" sc="close">取消</a>'+"</div>"+"</div>",this.boxSelector=e.boxSelector||"",this.boxTpl=e.boxTpl||"",this.boxData=e.boxData||{},this.onClosed=e.onClosed||null,this.onStart=e.onStart||null,this.onConfirm=e.onConfirm||null,this.overLayHide=e.overLayHide||null,this.init()}n.prototype={init:function(){this.render(),this.events(),this.makeLay()},render:function(){var e,t,i=this.create();e=-i.h/2+"px",t=-i.w/2+"px",this.position(i.box,"50%","50%",e+" 0 0 "+t),this.box=i.box},makeRound:function(){var e='<div class="clearfix level-t"><div class="lt fl"></div><div class="t fl"></div><div class="rt fl"></div></div><div class="clearfix level-m"><div class="l fl"></div><div class="main fl"></div><div class="r fl"></div></div><div class="clearfix level-b"><div class="lb fl"></div><div class="b fl"></div><div class="rb fl"></div></div>';this.boxSelector.append($(e))},makeLay:function(){var e=$("[sc = Dialog-lay]").length;return e?(this.oLay=$("[sc = Dialog-lay]"),void 0):(this.oLay=$('<div sc="Dialog-lay"></div>'),this.oLay.css({width:"100%",height:"100%",position:"fixed",left:0,top:0,zIndex:3e3,background:"#000",opacity:"0.3",display:"none"}),$("body").append(this.oLay),void 0)},set:function(e,t){this.oLay.css({left:e,top:t})},create:function(){var e,t,i;return this.boxSelector?e=$(this.boxSelector):this.boxTpl?(e=$(this.boxTpl(this.boxData)),$("body").append(e)):(e=$(this.boxStr),$("body").append(e)),e.wrap("<div dialog-wrap></div>"),t=e.outerWidth(!0),i=e.outerHeight(!0),this.oBox=e,this.oWrap=this.oBox.parents("[dialog-wrap]"),{box:this.oWrap,w:t,h:i}},events:function(){var e=this;this.box.on("click","[sc = close]",function(){e.close(e.box)}),this.box.on("click","[sc = confirm]",function(){e.onConfirm&&e.onConfirm.call(e,$(this))}),$(document).on("click",function(t){e.overLayHide&&$(t.target).parents()[0]!=e.oBox[0]&&$(t.target)[0]!=e.oBox[0]&&e.close()})},position:function(e,t,i,n){e.css({position:"fixed",left:t,top:i,margin:n,zIndex:this.index,display:"none"})},show:function(){this.oWrap.show(),this.oLay.show(),this.onStart&&this.onStart.call(this)},close:function(e){this.oWrap.hide(),this.oLay.hide(),e!==!1&&this.onClosed&&this.onClosed.call(this)},fix:function(){this.box.css("position","fixed")},dom:function(){return this.oBox},boxTitle:function(){return this.oBox.find("[sc = box-title]")},boxContent:function(){return this.oBox.find("[sc = box-content]")},refreshData:function(e){this.oWrap.html(this.boxTpl(e))}},i.exports=n});