/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("util/bom/unloadTip",[],function(){function e(e){$(window).bind("beforeunload",function(){return window.__dontTip?void 0:e})}return e});