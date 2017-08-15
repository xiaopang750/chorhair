/**
 *description: 业绩管理接口
 *author:fanwei
 *date:2015/01/25
 */
define(function(require, exports, module){	

	module.exports = {

		getAllList: R.uri.reqPrefix + 'performance/getAllList', //查询店铺业绩数据
		getHairList: R.uri.reqPrefix + 'performance/getHairList', //获取理发师业绩
		getHistoryList: R.uri.reqPrefix + 'performance/getHistoryList', //获取历史订单
		getIncome: R.uri.reqPrefix + 'performance/getIncome', //获取店面周收入
		getAvgincome: R.uri.reqPrefix + 'performance/getAvgincome', //获取店面周日均收入
		getPeopleCount: R.uri.reqPrefix + 'performance/getPeopleCount' //获取店面人流情况

	};
});
