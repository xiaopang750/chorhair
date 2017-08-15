/**
 *description:网站访问路由
 *author:wanwc
 *date:2015/5/20
 */
var prefix = '/views/';

module.exports = {	
	'user/login': {
		url : prefix + 'user/login',
		title : '登录',
		belongIndex: '0'
	},
	'user/regist': {
		url : prefix + 'user/regist',
		title : '注册',
		belongIndex: '0'
	},
	'user/add': {
		url : prefix + 'user/add',
		title : '注册新用户',
		belongIndex: '10'
	},
	'user/manage': {
		url : prefix + 'user/manage',
		title : '用户管理',
		belongIndex: '10'
	},
	'user/order': {
		url : prefix + 'user/order',
		title : '下单',
		belongIndex: '1'
	},
	'user/appoint': {
		url : prefix + 'user/appoint',
		title : '预约单',
		belongIndex: '1'
	},
	'user/index': {
		url : prefix + 'user/index',
		title : '操作平台',
		belongIndex: '1'
	},
	'performance/index': {
		url : prefix + 'performance/index',
		title : '店面收入',
		belongIndex: '2'
	},
	'performance/hairer': {
		url : prefix + 'performance/hairer',
		title : '发型师业绩',
		belongIndex: '2'
	},
    'performance/history': {
        url : prefix + 'performance/history',
        title : '历史订单',
        belongIndex: '2'
    },
    'performance/people': {
        url : prefix + 'performance/people',
        title : '店面人流情况',
        belongIndex: '2'
    },
	'service/list': {
		url : prefix + 'service/list',
		title : '发型师管理',
		belongIndex: '3'
	},
	'service/addEdit': {
		url : prefix + 'service/addEdit',
		title : '发型师编辑',
		belongIndex: '3'
	},
	'goods/list': {
		url : prefix + 'goods/list',
		title : '商品管理',
		belongIndex: '4-1'
	},
	'goods/packageEdit': {
		url : prefix + 'goods/packageEdit',
		title : '店铺属性',
		belongIndex: '4-1'
	},
	'goods/detail': {
		url : prefix + 'goods/detail',
		title : '修改日志',
		belongIndex: '4-1'
	},
	'goods/addCombo': {
		url : prefix + 'goods/addCombo',
		title : '新增套餐',
		belongIndex: '4-1'
	},
	'goods/stockList': {
		url : prefix + 'goods/stockList',
		title : '库存管理',
		belongIndex: '4-2'
	},
	'goods/shopStockList': {
		url : prefix + 'goods/shopStockList',
		title : '库存管理',
		belongIndex: '4-2'
	},
	'goods/addGoods': {
		url : prefix + 'goods/addGoods',
		title : '新增商品',
		belongIndex: '4-2'
	},
	'goods/stockRecord': {
		url : prefix + 'goods/stockRecord',
		title : '修改记录',
		belongIndex: '4-2'
	},
	'goods/bookList': {
		url : prefix + 'goods/bookList',
		title : '历史订货单',
		belongIndex: '4-3'
	},
	'goods/bookAddEdit': {
		url : prefix + 'goods/bookAddEdit',
		title : '订货管理',
		belongIndex: '4-3'
	},
	'goods/deliveryList': {
		url : prefix + 'goods/deliveryList',
		title : '历史出库单',
		belongIndex: '4-4'
	},
	'goods/deliveryAddEdit': {
		url : prefix + 'goods/deliveryAddEdit',
		title : '出库管理',
		belongIndex: '4-4'
	},
	'goods/platBookList': {
		url : prefix + 'goods/platBookList',
		title : '历史订货单',
		belongIndex: '4-3'
	},
	'goods/platBookAddEdit': {
		url : prefix + 'goods/platBookAddEdit',
		title : '订货管理',
		belongIndex: '4-3'
	},
	'customService/index': {
		url : prefix + 'customService/index',
		title : '客服管理',
		belongIndex: '5'
	},
	'content/index': {
		url : prefix + 'content/index',
		title : '内容管理',
		belongIndex: '6'
	},
	'content/addEdit': {
		url : prefix + 'content/addEdit',
		title : '内容管理',
		belongIndex: '6'
	},
    'test/index': {
        url : prefix + 'content/addEdit',
        title : '测试',
        belongIndex: ''
    },
    'system/fileManage': {
        url : prefix + 'system/fileManage',
        title : '套餐提成',
        belongIndex: '7-1'
    },
    'system/additional': {
        url : prefix + 'system/additional',
        title : '附加项目',
        belongIndex: '7-2'
    },
    'system/debit': {
        url : prefix + 'system/debit',
        title : '抵用券',
        belongIndex: '7-3'
    },
    'shop/index': {
        url : prefix + 'shop/index',
        title : '店铺信息',
        belongIndex: '8-1'
    },
    'shop/account': {
        url : prefix + 'shop/account',
        title : '店铺账号',
        belongIndex: '8-2'
    },
    'plat/index': {
        url : prefix + 'plat/index',
        title : '二维码',
        belongIndex: '9-1'
    }

};