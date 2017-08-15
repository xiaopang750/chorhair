/**
 *description: 商品接口
 *author:fanwei
 *date:2015/1/14
 */
module.exports = {

	getGoodsList: '/shopcombo/findbyshop.php', //获取商品套餐接口
	goodsEditFind: '/shopcombo/findbyid.php', //获取套餐编辑数据
	goodsEdit: '/shopcombo/edit.php', //编辑商品套餐
	platAddCombo: '/platcombo/addplatcombo.php', //平台新增套餐
	platGetCombo: '/platcombo/getbyid.php', //获取平台套餐编辑数据
	platEditCombo: '/platcombo/editplatcombo.php', //平台套餐修改
	shopComboAttr: '/platcombo/findbyshopid.php', //店铺套餐属性
	saveShopCombo: '/platcombo/editproperty.php', //店铺套餐属性保存
	modifyDetail: '/editrecord/findbycombo.php', //修改详情
	stockList: '/shopproduct/findbyshop.php', //库存列表
	stockPriceEdit: '/platproduct/editprice.php', //平台库存价格修改
	addProductNum: '/platproduct/editproductnum.php', //新增平台库存数量
	platStockList: '/platproduct/findproduct.php', //平台库存列表
	platAddProduct: '/platproduct/addproduct.php', //平台新增商品
	platStockRecord: '/platproducttrace/gettrace.php', //平台库存修改记录
	shopStockList: '/shopproduct/findbyshop.php', //店铺库存列表
	bookList: '/book/findbyshop.php', //订单列表
	bookAdd: '/book/save.php', //订单添加
	bookEdit: '/book/edit.php', //订单编辑
	bookData: '/platbook/querydetail.php', //查询订单下的商品
	bookConfirm: '/book/confirmgood.php', //确认收货
	bookSubmit: '/book/commit.php', //提交订单
	bookRealModify: '/book/shopeditnum.php', //修改实收
	bookPlatRealModify: '/platbook/plateditnum.php', //平台修改发货数量
	sendGoods: '/platbook/sendgoods.php', //平台订货单发货
	deliveryList: '/delivery/findbyshop.php', //出库列表
	deliveryConfirm: '/delivery/confirmdelivery.php', //确认出库
	deliveryAdd: '/delivery/save.php', //出库添加
	deliveryEdit: '/delivery/edit.php', //出库编辑
	deliveryData: '/delivery/querydetail.php', //获取出库数据
	platBookList: '/platbook/platquery.php', //平台订货单列表
	bookApprove: '/platbook/approve.php', //通过驳回
	getSale: '/platcombo/queryaward.php', //根据套餐主键获取抵用券
	confirmSale: '/platcombo/saveaward.php' //确定选择抵用券
};