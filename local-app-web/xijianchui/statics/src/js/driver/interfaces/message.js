/**
 *description: 消息接口
 *author:fanwei
 *date:2015/02/04
 */
define(function(require, exports, module){	

	module.exports = {

		all: R.uri.reqPrefix + 'message/all', //获取所有消息
		ignore: R.uri.reqPrefix + 'message/ignore' //忽略
	};
	
});
