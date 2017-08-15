/**
 *description: 系统模块
 *author:fanwei
 *date:2015/3/25
 */
module.exports = {

	//档案管理
	fmLookGroup: '/pricegroup/findbyshop.php',//查看组
	fmAddGroup: '/pricegroup/save.php', //添加组
	fmEditGroup: '/pricegroup/edit.php',//编辑组
	fmFindSub: '/pricegroup/findprice.php',// 根据组找子集列表
	fmFindDetail: '/pricegroup/findaward.php',//根据组找详情子集列表
	fmAddList: '/pricegroup/saveprice.php',//添加详情
	fmEditList: '/pricegroup/editprice.php',//编辑详情
	fmDiable: '/pricegroup/validate.php',//禁用
	//附加项目
	addLookGroup: '/additiongroup/findbyshop.php',//查看组
	addAddGroup: '/additiongroup/save.php',//添加组
	addEditGroup: '/additiongroup/edit.php',//编辑组
	addFindSub: '/additiongroup/findaddition.php',// 根据组找子集列表
	addFindDetail: '/additiongroup/findaward.php',//根据组找详情子集列表
	addAddList: '/additiongroup/saveaddition.php',//添加详情
	addEditList: '/additiongroup/editaddition.php',//编辑详情
	addDiable: '/additiongroup/validate.php', //禁用
	//抵用券
	cusLookGroup: '/customeraward/findgroup.php',//查看组
	cusLookDetail: '/customeraward/findbygroup.php',//根据组查看详情
	cusAddList: '/customeraward/save.php',// 添加详情
	cusEditList: '/customeraward/edit.php',//编辑详情
	lookAdapt: '/customeraward/findlimit.php',//获取适用套餐列表
	cusAdapt: '/customeraward/editlimit.php'//适用套餐选择
};