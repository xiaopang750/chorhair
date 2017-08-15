/**
 *description:系统模块
 *author:fanwei
 *date:2015/03/25
 */
define(function(require, exports, module){	

	module.exports = {
		//档案管理
		fmLookGroup: R.uri.reqPrefix + 'system/fmLookGroup',//查看组
		fmAddGroup: R.uri.reqPrefix + 'system/fmAddGroup', //添加组
		fmEditGroup: R.uri.reqPrefix + 'system/fmEditGroup',//编辑组
		fmFindSub: R.uri.reqPrefix + 'system/fmFindSub',// 根据组找子集列表
		fmFindDetail: R.uri.reqPrefix + 'system/fmFindDetail',//根据组找详情子集列表
		fmAddList: R.uri.reqPrefix + 'system/fmAddList',//添加详情
		fmEditList: R.uri.reqPrefix + 'system/fmEditList',//编辑详情
		fmDiable: R.uri.reqPrefix + 'system/fmDiable',//禁用
		//附加项目
		addLookGroup: R.uri.reqPrefix + 'system/addLookGroup',//查看组
		addAddGroup: R.uri.reqPrefix + 'system/addAddGroup',//添加组
		addEditGroup: R.uri.reqPrefix + 'system/addEditGroup',//编辑组
		addFindSub: R.uri.reqPrefix + 'system/addFindSub',// 根据组找子集列表
		addFindDetail: R.uri.reqPrefix + 'system/addFindDetail',//根据组找详情子集列表
		addAddList: R.uri.reqPrefix + 'system/addAddList',//添加详情
		addEditList: R.uri.reqPrefix + 'system/addEditList',//编辑详情
		addDiable: R.uri.reqPrefix + 'system/addDiable', //禁用
		//抵用券
		cusLookGroup: R.uri.reqPrefix + 'system/cusLookGroup',//查看组
		cusLookDetail: R.uri.reqPrefix + 'system/cusLookDetail',//根据组查看详情
		cusAddList: R.uri.reqPrefix + 'system/cusAddList',// 添加详情
		cusEditList: R.uri.reqPrefix + 'system/cusEditList',//编辑详情
		lookAdapt: R.uri.reqPrefix + 'system/lookAdapt',//获取适用套餐列表
		cusAdapt: R.uri.reqPrefix + 'system/cusAdapt'//适用套餐选择

	};
	
});