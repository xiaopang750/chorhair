/**
 *description: 我的订单
 *author:fanwei
 *date:2015/02/04
 */
define(function(require, exports, module){	

	module.exports = {

		list: R.uri.reqPrefix + 'appoint/list', //获取所有订单
		detail: R.uri.reqPrefix + 'appoint/detail', //订单详情
		arg: R.uri.reqPrefix + 'appoint/arg', //订单评论
		score: R.uri.reqPrefix + 'appoint/score', //订单评分
		addtion: R.uri.reqPrefix + 'appoint/addtion', //附加项修改
		packageUpload: R.uri.reqPrefix + 'appoint/packageUpload' //套餐使用者头像上传
	};
	
});
