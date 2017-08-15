/**
 *description: 商品接口
 *author:fanwei
 *date:2015/1/14
 */
module.exports = {

	shopList: '/shop/findshopinfolist.php', //获取店铺列表接口
	shopInfo: '/shop/findshopbyid.php', //获取店铺信息接口
	editShop: '/shop/editshopinfo.php', //编辑店铺信息
	saveShop: '/shop/saveshopinfo.php', //保存店铺信息
	qrCode: '/weixinqrcode/queryshoplist.php', //获取店铺二维码列表
	downloadQrcode: '/wxqrcode/postdownload.php', //下载店铺二维码
	shopAccount: '/shopuser/shopuserlist.php', //获取店铺账号
	editAccount: '/shopuser/edit.php', //编辑店铺账号
	addAccount: '/shopuser/register.php', //添加店铺账号

};