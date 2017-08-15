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
		belongIndex: "1"
	},
	{
		name: "用户",
		icon: "fa-users",
		url: view['user/manage'].url,
		belongIndex: "10"
	},
	{
		name: "业绩",
		icon: "fa-bar-chart",
		url: view['performance/index'].url,
		belongIndex: "2"
	},
	{
		name: "伙计",
		icon: "fa-user",
		url: view['service/list'].url,
		belongIndex: "3"
	},
	{
		name: "商品",
		icon: "fa-cube",
		url: view['goods/list'].url,
		belongIndex: "4",
		children:[
			{
				name: "套餐",
				icon: "fa-cube",
				url: view['goods/list'].url,
				belongIndex: "4-1"
			},
			{
				name: "库存",
				icon: "fa-cube",
				url: view['goods/stockList'].url,
				belongIndex: "4-2"
			},
			{
				name: "订货",
				icon: "fa-cube",
				url: view['goods/platBookList'].url,
				belongIndex: "4-3"
			}/*,
			{
				name: "出库",
				icon: "fa-cube",
				url: view['goods/deliveryList'].url,
				belongIndex: "4-4"
			}*/
		]
	},
	{
		name: "沟通",
		icon: "fa-bell",
		url: view['customService/index'].url,
		belongIndex: "5"
	},
	{
		name: "内容",
		icon: "fa-star-o",
		url: view['content/index'].url,
		belongIndex: "6"
	},
	{
		name: "提成",
		icon: "fa-calculator",
		url: view['system/fileManage'].url,
		belongIndex: "7",
		children:[
			{
				name: "套餐提成",
				icon: "fa-cube",
				url: view['system/fileManage'].url,
				belongIndex: "7-1",
			},
			{
				name: "附加项目",
				icon: "fa-cube",
				url: view['system/additional'].url,
				belongIndex: "7-2",
			},
			{
				name: "抵用券",
				icon: "fa-cube",
				url: view['system/debit'].url,
				belongIndex: "7-3",
			}
		]
	},
	{
		name: "店铺",
		icon: "fa-home",
		url: view['shop/index'].url,
		belongIndex: "8",
		children:[
			{
				name: "店铺信息",
				icon: "fa-cube",
				url: view['shop/index'].url,
				belongIndex: "8-1",
			},
			{
				name: "店铺账号",
				icon: "fa-cube",
				url: view['shop/account'].url,
				belongIndex: "8-2",
			}
		]
	},
	{
		name: "设置",
		icon: "fa-sun-o",
		url: view['plat/index'].url,
		belongIndex: "9",
		children:[
			{
				name: "二维码",
				icon: "fa-cube",
				url: view['plat/index'].url,
				belongIndex: "9-1",
			},
		]
	}
];