/**
 *description:网站导航配置
 *author:fanwei
 *date:2015/1/25
 */

var view = require('./view');

module.exports = [
	{
		name: "前台",
		icon: "fa-calendar-o",
		url: view['user/index'].url,
		belongIndex: "1",
		power: view['user/index'].power
	},
	{
		name: "业绩",
		icon: "fa-bar-chart",
		url: view['performance/index'].url,
		belongIndex: "2",
		power: view['performance/index'].power
	},
	{
		name: "伙计",
		icon: "fa-user",
		url: view['service/list'].url,
		belongIndex: "3",
		power: view['service/list'].power
	},
	{
		name: "商品",
		icon: "fa-cube",
		url: view['goods/list'].url,
		belongIndex: "4",
		power: view['goods/list'].power,
		children:[
			{
				name: "套餐",
				icon: "fa-cube",
				url: view['goods/list'].url,
				belongIndex: "4-1",
				power: view['goods/list'].power
			},
			{
				name: "库存",
				icon: "fa-cube",
				url: view['goods/stockList'].url,
				belongIndex: "4-2",
				power: view['goods/stockList'].power
			},
			{
				name: "订货",
				icon: "fa-cube",
				url: view['goods/bookList'].url,
				belongIndex: "4-3",
				power: view['goods/bookList'].power
			},
			{
				name: "出库",
				icon: "fa-cube",
				url: view['goods/deliveryList'].url,
				belongIndex: "4-4",
				power: view['goods/deliveryList'].power
			}
		]
	},
	{
		name: "沟通",
		icon: "fa-bell",
		url: view['customService/index'].url,
		belongIndex: "5",
		power: view['customService/index'].power
	},
	{
		name: "内容",
		icon: "fa-star-o",
		url: view['content/index'].url,
		belongIndex: "6",
		power: view['content/index'].power
	},
	{
		name: "提成",
		icon: "fa-calculator",
		url: view['system/fileManage'].url,
		belongIndex: "7",
		power: view['system/fileManage'].power,
		children:[
			{
				name: "套餐提成",
				icon: "fa-cube",
				url: view['system/fileManage'].url,
				belongIndex: "7-1",
				power: view['system/fileManage'].power
			},
			{
				name: "附加项目",
				icon: "fa-cube",
				url: view['system/additional'].url,
				belongIndex: "7-2",
				power: view['system/additional'].power
			},
			{
				name: "抵用券",
				icon: "fa-cube",
				url: view['system/debit'].url,
				belongIndex: "7-3",
				power: view['system/debit'].power
			}
		]
	},
	{
		name: "店铺",
		icon: "fa-home",
		url: view['shop/index'].url,
		belongIndex: "8",
		power: view['shop/index'].power,
		children:[
			{
				name: "店铺管理",
				icon: "fa-cube",
				url: view['shop/index'].url,
				belongIndex: "8-1",
				power: view['shop/index'].power
			},
			{
				name: "二维码",
				icon: "fa-cube",
				url: view['shop/qrcode'].url,
				belongIndex: "8-2",
				power: view['shop/qrcode'].power
			}
		]
	},
	{
		name: "平台",
		icon: "fa-cube",
		url: view['plat/index'].url,
		belongIndex: "9",
		power: view['plat/index'].power,
		children:[
			{
				name: "二维码",
				icon: "fa-cube",
				url: view['plat/index'].url,
				belongIndex: "9-1",
				power: view['plat/index'].power
			},
		]
	}
];