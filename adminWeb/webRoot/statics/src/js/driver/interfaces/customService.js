/**
 *description: 客服
 *author:fanwei
 *date:2015/02/08
 */
define(function(require, exports, module){	

	module.exports = {

		msgAll: R.uri.reqPrefix + 'customService/msgAll', //获取所有联系人
		sendMsg: R.uri.reqPrefix + 'customService/sendMsg', //发送消息
		historyMsg: R.uri.reqPrefix + 'customService/historyMsg', //获取历史消息
		getCustomer: R.uri.reqPrefix + 'customService/getCustomer' //查找消费者快速下单

	};
});
