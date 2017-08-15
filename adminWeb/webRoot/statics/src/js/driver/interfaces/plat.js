/**
 *description: 平台二维码
 *author:fanwei
 *date:2015/01/09
 */
define(function(require, exports, module){	

	module.exports = {

		platCode: R.uri.reqPrefix + 'plat/platCode', //二维码列表
		publishCode: R.uri.reqPrefix + 'plat/publishCode', //发布二维码
		downloadCode: R.uri.reqPrefix + 'plat/downloadCode', //下载二维码

	};
	
});