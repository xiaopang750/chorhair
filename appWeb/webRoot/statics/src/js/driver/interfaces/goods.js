/**
 *description: 商品接口
 *author:fanwei
 *date:2015/02/04
 */
define(function(require, exports, module){	

	module.exports = {

		all: R.uri.reqPrefix + 'goods/all', //获取所有商品
		getGood: R.uri.reqPrefix + 'goods/getGood', //获取赞
		good: R.uri.reqPrefix + 'goods/good' //赞
	};
	
});
