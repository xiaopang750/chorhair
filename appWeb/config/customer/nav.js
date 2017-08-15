/**
 *description:导航配置
 *author:fanwei
 *date:2015/02/05
 */

var prefix = '/views/customer/';

module.exports = [
	{
		url : prefix + 'user/index',
		logo: 'icon-home',
		text: '首页'
	},
	{
		url : prefix + 'appoint/index',
		logo: 'icon-time',
		text: '我的订单'
	},
	{
		url : prefix + 'record/index',
		logo: 'icon-record',
		text: '美丽记录'
	},
	{
		url : prefix + 'user/info',
		logo: 'icon-info',
		text: '我的账户'
	},
	{
		url : prefix + 'message/list',
		logo: 'icon-mail',
		text: '消息'
	}
]; 