var prefix = '/views/hairer/';

module.exports = {	
	'user/login': {
		url : prefix + 'user/login',
		title : '登录',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'appoint/index',
		isCanLook: true,
		barBg: '#f5f6f6'
	},
	'user/download': {
		url : prefix + 'user/download',
		title : '推荐下载',
		pageLevel: "2",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/info',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'user/account': {
		url : prefix + 'user/account',
		title : '我的账户',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: '',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'user/info': {
		url : prefix + 'user/info',
		title : '个人信息',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: '',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'user/forget': {
		url : prefix + 'user/forget',
		title : '忘记密码',
		pageLevel: "-1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/login',
		isCanLook: true,
		barBg: '#f5f6f6'
	},
	'message/list': {
		url : prefix + 'message/list',
		title : '我的消息',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/index',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'message/detail': {
		url : prefix + 'message/detail',
		title : '详细详情',
		pageLevel: "2",
		rightText: "",
		rightUrl: "",
		backUrl: '',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'appoint/index': {
		url : prefix + 'appoint/index',
		title : '我的预约',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: '',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'appoint/detail': {
		url : prefix + 'appoint/detail',
		title : '预约详情',
		pageLevel: "2",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'appoint/index',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'login/out': {
		url: '/main/hairer/user/loginOut', //退出登录接口
		title: '',
		isCanLook: true,
		barBg: '#f5f6f6'
	}

};