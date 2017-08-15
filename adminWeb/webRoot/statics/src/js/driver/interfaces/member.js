/**
 *description: 会员
 *author:fanwei
 *date:2015/01/25
 */
define(function(require, exports, module){	

	module.exports = {

		queryPayList: R.uri.reqPrefix + 'member/payList', //查询所有结算单
		queryReserveList: R.uri.reqPrefix + 'member/reserveList', //查询所有预约单
		queryScan: R.uri.reqPrefix + 'member/queryScan', //查询下单会员
		getEditReserveInfo: R.uri.reqPrefix + 'member/getEditReserveInfo', //查询单条预约单
		editPreorder: R.uri.reqPrefix + 'member/editPreorder', //编辑预约单
		cancelReserve: R.uri.reqPrefix + 'member/cancelReserve', //取消预约单
		createOrder: R.uri.reqPrefix + 'member/createOrder', //预约单生成订单
		query: R.uri.reqPrefix + 'member/query', //会员查询
		add: R.uri.reqPrefix + 'member/add', //会员添加
		edit: R.uri.reqPrefix + 'member/edit', //会员编辑
		editPackage: R.uri.reqPrefix + 'member/editPackage', //套餐编辑
		packageInfo: R.uri.reqPrefix + 'member/packageInfo', //店铺套餐信息
		platPackageInfo: R.uri.reqPrefix + 'member/platPackageInfo', //平台套餐信息
		hasedPackage: R.uri.reqPrefix + 'member/hasedPackage', //获取已有的套餐
		savePackage: R.uri.reqPrefix + 'member/savePackage', //套餐保存
		sum: R.uri.reqPrefix + 'member/sum', //结算
		order: R.uri.reqPrefix + 'member/order', //下单,
		registApp: R.uri.reqPrefix + 'member/registApp', //给用户发送app密码 
		fastOrder: R.uri.reqPrefix + 'member/fastOrder', //快速下单
		getTicheng: R.uri.reqPrefix + 'member/getTicheng', //根据价格查询提成
		getOther: R.uri.reqPrefix + 'member/getOther', //获取附加项目组
		packageGetTicheng: R.uri.reqPrefix + 'member/packageGetTicheng', //根据套餐主键获取提成方式
		packageGetSale: R.uri.reqPrefix + 'member/packageGetSale', //根据套餐主键获取优惠
		getPrice: R.uri.reqPrefix + 'member/getPrice'

	};
});
