/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("driver/config",["./interfaces/user","./interfaces/member","./interfaces/performance","./interfaces/upload","./interfaces/goods","./interfaces/service","./interfaces/content","./interfaces/global","./interfaces/customService","./interfaces/system","./interfaces/shop","./interfaces/plat"],function(e){var t=$("body").attr("nowWay"),i=e("./interfaces/user"),o=e("./interfaces/member"),r=e("./interfaces/performance"),n=e("./interfaces/upload"),s=e("./interfaces/goods"),a=e("./interfaces/service"),c=e("./interfaces/content"),u=e("./interfaces/global"),l=e("./interfaces/customService"),d=e("./interfaces/system"),f=e("./interfaces/shop"),p=e("./interfaces/plat");R.interfaces={user:i,member:o,performance:r,upload:n,goods:s,service:a,content:c,global:u,customService:l,system:d,shop:f,plat:p},R.nowWay=t}),define("driver/interfaces/user",[],function(e,t,i){i.exports={login:R.uri.reqPrefix+"user/login",regist:R.uri.reqPrefix+"user/regist",loginOut:R.uri.reqPrefix+"user/loginOut"}}),define("driver/interfaces/member",[],function(e,t,i){i.exports={queryPayList:R.uri.reqPrefix+"member/payList",queryReserveList:R.uri.reqPrefix+"member/reserveList",queryScan:R.uri.reqPrefix+"member/queryScan",getEditReserveInfo:R.uri.reqPrefix+"member/getEditReserveInfo",editPreorder:R.uri.reqPrefix+"member/editPreorder",cancelReserve:R.uri.reqPrefix+"member/cancelReserve",createOrder:R.uri.reqPrefix+"member/createOrder",query:R.uri.reqPrefix+"member/query",add:R.uri.reqPrefix+"member/add",edit:R.uri.reqPrefix+"member/edit",editPackage:R.uri.reqPrefix+"member/editPackage",packageInfo:R.uri.reqPrefix+"member/packageInfo",platPackageInfo:R.uri.reqPrefix+"member/platPackageInfo",hasedPackage:R.uri.reqPrefix+"member/hasedPackage",savePackage:R.uri.reqPrefix+"member/savePackage",sum:R.uri.reqPrefix+"member/sum",order:R.uri.reqPrefix+"member/order",registApp:R.uri.reqPrefix+"member/registApp",fastOrder:R.uri.reqPrefix+"member/fastOrder",getTicheng:R.uri.reqPrefix+"member/getTicheng",getOther:R.uri.reqPrefix+"member/getOther",packageGetTicheng:R.uri.reqPrefix+"member/packageGetTicheng",packageGetSale:R.uri.reqPrefix+"member/packageGetSale",getPrice:R.uri.reqPrefix+"member/getPrice"}}),define("driver/interfaces/performance",[],function(e,t,i){i.exports={getAllList:R.uri.reqPrefix+"performance/getAllList",getHairList:R.uri.reqPrefix+"performance/getHairList",getHistoryList:R.uri.reqPrefix+"performance/getHistoryList"}}),define("driver/interfaces/upload",[],function(e,t,i){i.exports={main:R.uri.uploadPrefix+"upload"}}),define("driver/interfaces/goods",[],function(e,t,i){i.exports={goodsEdit:R.uri.reqPrefix+"goods/goodsEdit",goodsEditFind:R.uri.reqPrefix+"goods/goodsEditFind",stockList:R.uri.reqPrefix+"goods/stockList",stockPriceEdit:R.uri.reqPrefix+"goods/stockPriceEdit",bookList:R.uri.reqPrefix+"goods/bookList",bookAdd:R.uri.reqPrefix+"goods/bookAdd",bookEdit:R.uri.reqPrefix+"goods/bookEdit",bookData:R.uri.reqPrefix+"goods/bookData",bookConfirm:R.uri.reqPrefix+"goods/bookConfirm",bookSubmit:R.uri.reqPrefix+"goods/bookSubmit",bookRealModify:R.uri.reqPrefix+"goods/bookRealModify",bookPlatRealModify:R.uri.reqPrefix+"goods/bookPlatRealModify",deliveryList:R.uri.reqPrefix+"goods/deliveryList",deliveryConfirm:R.uri.reqPrefix+"goods/deliveryConfirm",deliveryAdd:R.uri.reqPrefix+"goods/deliveryAdd",deliveryEdit:R.uri.reqPrefix+"goods/deliveryEdit",deliveryData:R.uri.reqPrefix+"goods/deliveryData",platBookList:R.uri.reqPrefix+"goods/platBookList",bookApprove:R.uri.reqPrefix+"goods/bookApprove",getSale:R.uri.reqPrefix+"goods/getSale",confirmSale:R.uri.reqPrefix+"goods/confirmSale",platCombo:R.uri.reqPrefix+"goods/platCombo",modifyDetail:R.uri.reqPrefix+"goods/modifyDetail"}}),define("driver/interfaces/service",[],function(e,t,i){i.exports={list:R.uri.reqPrefix+"service/list",validate:R.uri.reqPrefix+"service/validate",add:R.uri.reqPrefix+"service/add",edit:R.uri.reqPrefix+"service/edit",remove:R.uri.reqPrefix+"service/remove",regist:R.uri.reqPrefix+"service/regist"}}),define("driver/interfaces/content",[],function(e,t,i){i.exports={list:R.uri.reqPrefix+"content/list",add:R.uri.reqPrefix+"content/add",edit:R.uri.reqPrefix+"content/edit",cancel:R.uri.reqPrefix+"content/cancel",search:R.uri.reqPrefix+"content/search"}}),define("driver/interfaces/global",[],function(e,t,i){i.exports={modifyPass:R.uri.reqPrefix+"global/modifyPass",getArea:R.uri.reqPrefix+"global/getArea",upload:R.uri.uploadPrefix+"uploader",getSignature:R.uri.reqPrefix+"global/getSignature"}}),define("driver/interfaces/customService",[],function(e,t,i){i.exports={msgAll:R.uri.reqPrefix+"customService/msgAll",sendMsg:R.uri.reqPrefix+"customService/sendMsg",historyMsg:R.uri.reqPrefix+"customService/historyMsg",getCustomer:R.uri.reqPrefix+"customService/getCustomer"}}),define("driver/interfaces/system",[],function(e,t,i){i.exports={fmLookGroup:R.uri.reqPrefix+"system/fmLookGroup",fmAddGroup:R.uri.reqPrefix+"system/fmAddGroup",fmEditGroup:R.uri.reqPrefix+"system/fmEditGroup",fmFindSub:R.uri.reqPrefix+"system/fmFindSub",fmFindDetail:R.uri.reqPrefix+"system/fmFindDetail",fmAddList:R.uri.reqPrefix+"system/fmAddList",fmEditList:R.uri.reqPrefix+"system/fmEditList",fmDiable:R.uri.reqPrefix+"system/fmDiable",addLookGroup:R.uri.reqPrefix+"system/addLookGroup",addAddGroup:R.uri.reqPrefix+"system/addAddGroup",addEditGroup:R.uri.reqPrefix+"system/addEditGroup",addFindSub:R.uri.reqPrefix+"system/addFindSub",addFindDetail:R.uri.reqPrefix+"system/addFindDetail",addAddList:R.uri.reqPrefix+"system/addAddList",addEditList:R.uri.reqPrefix+"system/addEditList",addDiable:R.uri.reqPrefix+"system/addDiable",cusLookGroup:R.uri.reqPrefix+"system/cusLookGroup",cusLookDetail:R.uri.reqPrefix+"system/cusLookDetail",cusAddList:R.uri.reqPrefix+"system/cusAddList",cusEditList:R.uri.reqPrefix+"system/cusEditList",lookAdapt:R.uri.reqPrefix+"system/lookAdapt",cusAdapt:R.uri.reqPrefix+"system/cusAdapt"}}),define("driver/interfaces/shop",[],function(e,t,i){i.exports={shopList:R.uri.reqPrefix+"shop/shopList",shopInfo:R.uri.reqPrefix+"shop/shopInfo",editShop:R.uri.reqPrefix+"shop/editShop",saveShop:R.uri.reqPrefix+"shop/saveShop",qrCode:R.uri.reqPrefix+"shop/qrCode",downloadQrcode:R.uri.reqPrefix+"shop/downloadQrcode"}}),define("driver/interfaces/plat",[],function(e,t,i){i.exports={platCode:R.uri.reqPrefix+"plat/platCode",publishCode:R.uri.reqPrefix+"plat/publishCode",downloadCode:R.uri.reqPrefix+"plat/downloadCode"}});