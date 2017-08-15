/**
 *description:网站访问路由
 *author:fanwei
 *date:2015/1/25
 */

/*
	power:
	1: 店铺
	2: 平台
	power代表此页面有谁（即那种用户）可以查看
*/ 
var prefix = '/views/shop/';

module.exports = {	
	'user/login': {
		url : prefix + 'user/login',
		title : '登录',
		belongIndex: '0',
		power:'1,2'
	},
	'user/regist': {
		url : prefix + 'user/regist',
		title : '注册',
		belongIndex: '0',
		power:'1,2'
	},
	'user/add': {
		url : prefix + 'user/add',
		title : '注册新用户',
		belongIndex: '1',
		power:'1,2'
	},
	'user/manage': {
		url : prefix + 'user/manage',
		title : '用户管理',
		belongIndex: '1',
		power:'1,2'
	},
	'user/order': {
		url : prefix + 'user/order',
		title : '下单',
		belongIndex: '1',
		power:'1,2'
	},
	'user/appoint': {
		url : prefix + 'user/appoint',
		title : '预约单',
		belongIndex: '1',
		power:'1,2'
	},
	'user/index': {
		url : prefix + 'user/index',
		title : '操作平台',
		belongIndex: '1',
		power:'1,2'
	},
	'performance/index': {
		url : prefix + 'performance/index',
		title : '店面业绩',
		belongIndex: '2',
		power:'1,2'
	},
	'performance/hairer': {
		url : prefix + 'performance/hairer',
		title : '发型师业绩',
		belongIndex: '2',
		power:'1,2'
	},
    'performance/history': {
        url : prefix + 'performance/history',
        title : '历史订单',
        belongIndex: '2',
        power:'1,2'
    },
	'service/list': {
		url : prefix + 'service/list',
		title : '发型师管理',
		belongIndex: '3',
		power:'1,2'
	},
	'service/addEdit': {
		url : prefix + 'service/addEdit',
		title : '发型师编辑',
		belongIndex: '3',
		power:'1,2'
	},
	'goods/list': {
		url : prefix + 'goods/list',
		title : '商品管理',
		belongIndex: '4-1',
		power:'1,2'
	},
	'goods/packageEdit': {
		url : prefix + 'goods/packageEdit',
		title : '套餐修改',
		belongIndex: '4-1',
		power:'1,2'
	},
	'goods/detail': {
		url : prefix + 'goods/detail',
		title : '修改日志',
		belongIndex: '4-1',
		power:'1,2'
	},
	'goods/stockList': {
		url : prefix + 'goods/stockList',
		title : '库存管理',
		belongIndex: '4-2',
		power:'1,2'
	},
	'goods/bookList': {
		url : prefix + 'goods/bookList',
		title : '历史订货单',
		belongIndex: '4-3',
		power:'1,2'
	},
	'goods/bookAddEdit': {
		url : prefix + 'goods/bookAddEdit',
		title : '订货管理',
		belongIndex: '4-3',
		power:'1,2'
	},
	'goods/deliveryList': {
		url : prefix + 'goods/deliveryList',
		title : '历史出库单',
		belongIndex: '4-4',
		power:'1,2'
	},
	'goods/deliveryAddEdit': {
		url : prefix + 'goods/deliveryAddEdit',
		title : '出库管理',
		belongIndex: '4-4',
		power:'1,2'
	},
	'goods/platBookList': {
		url : prefix + 'goods/platBookList',
		title : '历史订货单',
		belongIndex: '4-3',
		power:'1,2'
	},
	'goods/platBookAddEdit': {
		url : prefix + 'goods/platBookAddEdit',
		title : '订货管理',
		belongIndex: '4-3',
		power:'1,2'
	},
	'customService/index': {
		url : prefix + 'customService/index',
		title : '客服管理',
		belongIndex: '5',
		power:'2'
	},
	'content/index': {
		url : prefix + 'content/index',
		title : '内容管理',
		belongIndex: '6',
		power:'1,2'
	},
	'content/addEdit': {
		url : prefix + 'content/addEdit',
		title : '内容管理',
		belongIndex: '6',
		power:'1,2'
	},
    'test/index': {
        url : prefix + 'content/addEdit',
        title : '测试',
        belongIndex: '',
        power:'1,2'
    },
    'system/fileManage': {
        url : prefix + 'system/fileManage',
        title : '套餐提成',
        belongIndex: '7-1',
        power:'1,2'
    },
    'system/additional': {
        url : prefix + 'system/additional',
        title : '附加项目',
        belongIndex: '7-2',
        power:'1,2'
    },
    'system/debit': {
        url : prefix + 'system/debit',
        title : '抵用券',
        belongIndex: '7-3',
        power:'2'
    },
    'shop/index': {
        url : prefix + 'shop/index',
        title : '店铺管理',
        belongIndex: '8-1',
        power:'1,2'
    },
    'shop/qrcode': {
        url : prefix + 'shop/qrcode',
        title : '二维码',
        belongIndex: '8-2',
        power:'1'
    },
    'plat/index': {
        url : prefix + 'plat/index',
        title : '二维码',
        belongIndex: '9-1',
        power:'2'
    }

};