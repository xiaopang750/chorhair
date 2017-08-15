/**
 *description: 商品接口
 *author:fanwei
 *date:2015/01/09
 */
define(function(require, exports, module){	

	module.exports = {

		shopList: R.uri.reqPrefix + 'shop/shopList', //店铺列表
		shopInfo: R.uri.reqPrefix + 'shop/shopInfo', //店铺详情
		editShop: R.uri.reqPrefix + 'shop/editShop', //店铺信息编辑
		saveShop: R.uri.reqPrefix + 'shop/saveShop', //保存店铺信息
		qrCode: R.uri.reqPrefix + 'shop/qrCode', //店铺二维码
		downloadQrcode: R.uri.reqPrefix + 'shop/downloadQrcode', //下载店铺二维码
		shopAccount: R.uri.reqPrefix + 'shop/shopAccount', //获取店铺账号
		editAccount: R.uri.reqPrefix + 'shop/editAccount', //编辑店铺账号
		addAccount: R.uri.reqPrefix + 'shop/addAccount', //添加店铺账号

	};
	
});