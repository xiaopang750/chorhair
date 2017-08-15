/**
 *description: 会员
 *author:fanwei
 *date:2015/1/14
 */
module.exports = {

	add: '/customer/register.php',  //店铺会员添加
	edit: '/customer/edit.php',  //店铺编辑
	queryScan: '/customer/queryscan.php', //扫描下单的会员
	packageInfo: '/shopcombo/findbyshop.php',  //店铺套餐信息
	platPackageInfo: '/platcombo/query.php',  //平台套餐信息
	hasedPackage: '/customercombo/findbycustomer.php',  //获取已有的套餐
	savePackage: '/customercombo/select.php',  //店铺会员套餐保存
	editPackage: '/customercombo/edit.php',  //店铺会员套餐编辑
	getIndexInfo: '/order/queryunorder.php', //店铺操作台获取结算单信息
	getReserveInfo: '/order/queryappointment.php', //店铺操作台获取预约单信息
	getEditReserveInfo: '/order/querybyid.php', //获取单条预约单信息
	editPreorder: '/order/editpreorder.php', //编辑预约单
	cancelReserve: '/order/cancelappoint.php', //取消预约单
	createOrder: '/order/createorder.php', //预约单生成订单
	getMemberInfo: '/customer/query.php', //店铺获取会员信息
	getSinMemberInfo: '/customer/querybyid.php', //获取单个会员信息
	order: '/order/save.php', //下单
	sum: '/order/settle.php',  //店铺操作台结算
	getHairer: '/fairer/findlist.php',  //获取店铺所有的理发师
	getPrice: '/shopprice/querybyshop.php',  //获取店铺理发师价格
	getOtherPay: '/shopaddition/querybycombo.php', //获取附加项
	getOrderNum: '/order/querydayorder.php', //获取订单数
	registApp: '/customer/registerapp.php', //给用户发送app密码
	fastOrder: '/order/fastorder.php', //快速下单
	getTicheng: '/shopprice/queryvalidate.php', //根据价格查询提成
	getOther: '/shopprice/queryaward.php', //获取附加项目组
	packageGetTicheng: '/customercombo/querycomboaward.php', //根据套餐主键获取提成方式
	packageGetSale: '/customercombo/queryaward.php' //根据套餐主键获取优惠
};