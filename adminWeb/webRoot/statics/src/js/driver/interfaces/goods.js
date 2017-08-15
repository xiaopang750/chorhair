/**
 *description: 商品接口
 *author:fanwei
 *date:2015/01/09
 */
define(function(require, exports, module){	

	module.exports = {

		goodsEdit: R.uri.reqPrefix + 'goods/goodsEdit', //套餐编辑
		goodsEditFind: R.uri.reqPrefix + 'goods/goodsEditFind', //获取套餐编辑数据
		stockList: R.uri.reqPrefix + 'goods/stockList', //库存列表
		stockPriceEdit: R.uri.reqPrefix + 'goods/stockPriceEdit', //库存价格修改
		bookList: R.uri.reqPrefix + 'goods/bookList', //订单列表
		bookAdd: R.uri.reqPrefix + 'goods/bookAdd', //订单添加
		bookEdit: R.uri.reqPrefix + 'goods/bookEdit', //订单编辑
		bookData: R.uri.reqPrefix + 'goods/bookData', //查询订单下的商品
		bookConfirm: R.uri.reqPrefix + 'goods/bookConfirm', //确认收货
		bookSubmit: R.uri.reqPrefix + 'goods/bookSubmit', //提交订单
		bookRealModify: R.uri.reqPrefix + 'goods/bookRealModify', //修改实收
		bookPlatRealModify: R.uri.reqPrefix + 'goods/bookPlatRealModify', //平台修改实收
		deliveryList: R.uri.reqPrefix + 'goods/deliveryList', //出库列表
		deliveryConfirm: R.uri.reqPrefix + 'goods/deliveryConfirm', //确认出库
		deliveryAdd: R.uri.reqPrefix + 'goods/deliveryAdd', //出库添加
		deliveryEdit: R.uri.reqPrefix + 'goods/deliveryEdit', //出库编辑
		deliveryData: R.uri.reqPrefix + 'goods/deliveryData', //获取出库数据
		platBookList: R.uri.reqPrefix + 'goods/platBookList', //平台订货单列表
		bookApprove: R.uri.reqPrefix + 'goods/bookApprove', //通过驳回
		getSale: R.uri.reqPrefix + 'goods/getSale', //根据套餐主键获取抵用券
		confirmSale: R.uri.reqPrefix + 'goods/confirmSale', //确定选择抵用券
		platCombo: R.uri.reqPrefix + 'goods/platCombo', //获取套餐对应商品
		modifyDetail: R.uri.reqPrefix + 'goods/modifyDetail' //获取套餐修改日志
	};
	
});